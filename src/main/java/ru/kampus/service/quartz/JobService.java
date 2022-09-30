package ru.kampus.service.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kampus.dto.Reminder;
import ru.kampus.service.reminder.ReminderSenderService;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

@Service
public class JobService {

    @Autowired
    QuartzFactory quartzFactory;

    @Autowired
    ReminderSenderService senderService;

    public void createReminderJob(Reminder reminder) {
        Date triggerDate = parse(reminder.getRemindDate(), reminder.getRemindTime());
        quartzFactory.addJob(new SimpleJob(), reminder.getId().toString(), reminder.getId().toString(), triggerDate);
    }

    public void updateReminderJob(Reminder reminder) {
        Date triggerDate = parse(reminder.getRemindDate(), reminder.getRemindTime());
        quartzFactory.rescheduleJob(reminder.getId().toString(), triggerDate);
    }

    public void deleteReminderJob(Reminder reminder) {
        quartzFactory.removeJob(reminder.getId().toString(), reminder.getId().toString());
    }

    private Date parse(java.sql.Date date, Time time) {
        // Construct date and time objects
        Calendar dateCal = Calendar.getInstance();
        dateCal.setTime(date);
        Calendar timeCal = Calendar.getInstance();
        timeCal.setTime(time);

        // Extract the time of the "time" object to the "date"
        dateCal.set(Calendar.HOUR_OF_DAY, timeCal.get(Calendar.HOUR_OF_DAY));
        dateCal.set(Calendar.MINUTE, timeCal.get(Calendar.MINUTE));
        dateCal.set(Calendar.SECOND, timeCal.get(Calendar.SECOND));

        // Get the time value!
        return dateCal.getTime();
    }
}
