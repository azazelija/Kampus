package ru.kampus.service.reminder;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.kampus.dto.Reminder;
import ru.kampus.entity.ReminderEntity;
import ru.kampus.entity.UserEntity;
import ru.kampus.mapper.ReminderMapper;
import ru.kampus.mapper.UserMapper;
import ru.kampus.repository.ReminderRepository;
import ru.kampus.repository.UserRepository;
import ru.kampus.security.AuthenctificationService;
import ru.kampus.service.IReminderService;
import ru.kampus.service.quartz.JobService;
import ru.kampus.service.quartz.QuartzFactory;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import static ru.kampus.repository.specifications.ReminderSpecification.matchDate;
import static ru.kampus.repository.specifications.ReminderSpecification.matchTime;

@Service
@AllArgsConstructor
public class ReminderService implements IReminderService {

    ReminderRepository reminderRepository;

    UserRepository userRepository;

    ReminderMapper reminderMapper;

    UserMapper userMapper;

    JobService jobService;

    AuthenctificationService authenctificationService;

    @Override
    public Reminder create(Reminder reminder) {
        String username = authenctificationService.getCurrentUsername();
        UserEntity userEntity = userRepository.findByUsername(username);

        ReminderEntity reminderEntity = reminderMapper.modelToEntity(reminder);
        reminderEntity.setOwner(userEntity);
        reminderEntity = reminderRepository.save(reminderEntity);

        reminder.setId(reminderEntity.getId());
        reminder.setOwner(userMapper.entityToModel(userEntity));

        jobService.createReminderJob(reminder);
        return reminder;
    }

    @Override
    public Reminder update(Reminder reminder) {
        if (reminder.getId() != null) {
            reminderRepository.findById(reminder.getId()).orElseThrow(() -> new IllegalArgumentException("Updated reminder not exist. Entity not found"));
        } else {
            throw new IllegalArgumentException("Updated reminder must have ID");
        }
        ReminderEntity reminderEntity = reminderMapper.modelToEntity(reminder);
        reminderEntity = reminderRepository.save(reminderEntity);

        reminder.setId(reminderEntity.getId());
        return reminder;
    }

    @Override
    public void delete(Long id) {
        reminderRepository.deleteById(id);
    }

    @Override
    public List<Reminder> sort(String by) {
        if ("name".equals(by)) {
            List<ReminderEntity> sortedReminders = reminderRepository.findAll(Sort.by("title"));
            return reminderMapper.entityToModelList(sortedReminders);
        } else if ("date".equals(by)) {
            List<ReminderEntity> sortedReminders = reminderRepository.findAll(Sort.by("remindDate").descending());
            return reminderMapper.entityToModelList(sortedReminders);
        } else if ("time".equals(by)) {
            List<ReminderEntity> sortedReminders = reminderRepository.findAll(Sort.by("remindTime").descending());
            return reminderMapper.entityToModelList(sortedReminders);
        } else {
            throw new IllegalArgumentException("Данный тип сортировки не поддерживается. Используйте сортировку по дате date, названию name или времени time");
        }
    }

    @Override
    public List<Reminder> filter(String by, String dateTime) {
        if ("date".equals(by)) {
            Date queryDate = Date.valueOf(dateTime);
            List<ReminderEntity> sortedReminders = reminderRepository.findAll(matchDate(queryDate));
            return reminderMapper.entityToModelList(sortedReminders);
        } else if ("time".equals(by)) {
            Time queryTime = Time.valueOf(dateTime);
            List<ReminderEntity> sortedReminders = reminderRepository.findAll(matchTime(queryTime));
            return reminderMapper.entityToModelList(sortedReminders);
        } else {
            throw new IllegalArgumentException("Данный тип фильтрации не поддерживается. Используйте фильтрацию по дате date или времени time");
        }
    }

    @Override
    public Page<Reminder> getAll(Pageable pageable) {
        Page<ReminderEntity> allReminder = reminderRepository.findAll(pageable);
        return allReminder.map(reminderMapper::entityToModel);
    }
}
