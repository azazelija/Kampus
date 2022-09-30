package ru.kampus.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    Long id;

    String username;

    String password;

    String telegramId;

    String email;

    Role role;

    List<Reminder> reminders;
}
