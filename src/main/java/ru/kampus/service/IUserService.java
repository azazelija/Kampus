package ru.kampus.service;

import ru.kampus.dto.User;

public interface IUserService {

    public User register(User user);

    public User getByUsername(String username);

    public User updateTelegramId(String username, String telegramId);
}
