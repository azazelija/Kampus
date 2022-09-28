package ru.kampus.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.kampus.dto.Reminder;

import java.util.List;

public interface IReminderService {

    public Reminder create(Reminder reminder);

    public Reminder update(Reminder reminder);

    public void delete(Long id);

    public List<Reminder> sort(String by);

    public List<Reminder> filter(String by, String dateTime);

    public Page<Reminder> getAll(Pageable pageable);
}
