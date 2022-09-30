package ru.kampus.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import ru.kampus.dto.Role;
import ru.kampus.entity.RoleEntity;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface RoleMapper extends EntityMapper<RoleEntity, Role> {
}
