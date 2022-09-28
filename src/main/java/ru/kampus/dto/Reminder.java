package ru.kampus.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
import java.sql.Time;
import java.time.OffsetDateTime;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class Reminder {

    Long id;

    String title;

    String description;

    Date remindDate;

    Time remindTime;
}

