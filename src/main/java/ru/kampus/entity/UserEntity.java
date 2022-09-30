package ru.kampus.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "username")
    String username;

    @Column(name = "password")
    String password;

    @Column(name = "telegram_id")
    String telegramId;

    @Column(name = "email")
    String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_name")
    RoleEntity role;

    @OneToMany(mappedBy = "owner")
    List<ReminderEntity> reminders;
}
