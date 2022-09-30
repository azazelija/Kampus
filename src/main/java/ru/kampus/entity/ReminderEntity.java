package ru.kampus.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "reminder")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReminderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "title")
    String title;

    @Column(name = "description")
    String description;

    @Column(name = "remind_date")
    Date remindDate;

    @Column(name = "remind_time")
    Time remindTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    UserEntity owner;
}
