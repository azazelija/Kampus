package ru.kampus.rest;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kampus.dto.Reminder;
import ru.kampus.service.IReminderService;

import java.time.OffsetDateTime;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ReminderController {

    IReminderService reminderService;

    @PostMapping("/reminder/create")
    public ResponseEntity<Reminder> create(@RequestBody Reminder reminder) {
        Reminder createdReminder = reminderService.create(reminder);
        return new ResponseEntity<>(createdReminder, HttpStatus.CREATED);
    }

    @PutMapping("/reminder")
    public ResponseEntity<Reminder> update(@RequestBody Reminder reminder) {
        Reminder createdReminder = reminderService.update(reminder);
        return new ResponseEntity<>(createdReminder, HttpStatus.CREATED);
    }

    //Не соответсвует BestPractice, требует использовать http метод без указания глагола в url
    @DeleteMapping("/reminder/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        reminderService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/sort/{by}")
    public ResponseEntity<List<Reminder>> sort(@PathVariable String by) {
        List<Reminder> reminderList = reminderService.sort(by);
        return new ResponseEntity<>(reminderList, HttpStatus.OK);
    }

    @GetMapping("/filter/{by}")
    public ResponseEntity<List<Reminder>> filter(@PathVariable String by, @RequestBody String dateTime) {
        List<Reminder> reminderList = reminderService.filter(by, dateTime);
        return new ResponseEntity<>(reminderList, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponsePage<Reminder> getAll(Pageable pageable) {
        Page reminderPage = reminderService.getAll(pageable);
        return new ResponsePage<>(reminderPage);
    }
}
