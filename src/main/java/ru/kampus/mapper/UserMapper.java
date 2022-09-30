package ru.kampus.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.kampus.dto.User;
import ru.kampus.entity.UserEntity;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper extends EntityMapper<UserEntity, User> {

    @Mapping(target = "role.users", ignore = true)
    User entityToModel(UserEntity entity);

    UserEntity modelToEntity(User model);
}
