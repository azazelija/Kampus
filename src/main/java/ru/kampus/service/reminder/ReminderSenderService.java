package ru.kampus.service.reminder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kampus.dto.Reminder;
import ru.kampus.dto.User;
import ru.kampus.service.mail.EmailService;
import ru.kampus.service.telegram.TelegramBotSender;

@Service
public class ReminderSenderService {

    @Autowired
    EmailService emailService;

    @Autowired
    TelegramBotSender telegramBotSender;

    public void sendMessage(Reminder reminder) {
        User owner = reminder.getOwner();
        emailService.sendSimpleEmail(owner.getEmail(), reminder.getTitle(), reminder.getDescription());
        telegramBotSender.sendMessage(owner.getTelegramId(), reminder.getTitle() + "\n" + reminder.getDescription());
    }
}
