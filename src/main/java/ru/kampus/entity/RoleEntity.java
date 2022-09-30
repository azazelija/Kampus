package ru.kampus.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import ru.kampus.dto.RoleEnum;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleEntity {

    @Id
    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    RoleEnum roleName;

    @OneToMany(mappedBy = "role")
    List<UserEntity> users;
}
