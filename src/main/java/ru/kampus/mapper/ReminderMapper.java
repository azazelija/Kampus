package ru.kampus.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.kampus.entity.ReminderEntity;
import ru.kampus.dto.Reminder;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ReminderMapper extends EntityMapper<ReminderEntity, Reminder> {
}
