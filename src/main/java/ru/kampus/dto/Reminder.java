package ru.kampus.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reminder {

    Long id;

    String title;

    String description;

    Date remindDate;

    Time remindTime;

    User owner;
}

