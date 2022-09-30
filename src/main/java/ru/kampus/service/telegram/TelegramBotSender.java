package ru.kampus.service.telegram;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBotSender extends TelegramLongPollingBot {

    @Value("${bot.name}")
    private String botUsername;

    @Value("${bot.token}")
    private String botToken;

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            if ("/start".equals(update.getMessage().getText())) {
                execute(SendMessage.builder()
                        .text("Use ChatId")
                        .chatId(update.getMessage().getChatId())
                        .build());
            }
            if ("ChatId".equals(update.getMessage().getText())) {
                execute(SendMessage.builder()
                        .text(update.getMessage().getChatId().toString())
                        .chatId(update.getMessage().getChatId())
                        .build());
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String telegramId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(telegramId);
        sendMessage.setText(message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
