package ru.kampus.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.kampus.dto.User;
import ru.kampus.entity.ReminderEntity;
import ru.kampus.dto.Reminder;
import ru.kampus.entity.UserEntity;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ReminderMapper extends EntityMapper<ReminderEntity, Reminder> {

    @Mapping(target = "owner.reminders", ignore = true)
    User entityToModel(UserEntity entity);

    UserEntity modelToEntity(User model);
}
