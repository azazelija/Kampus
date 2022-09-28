package ru.kampus.mapper;

import org.mapstruct.InheritInverseConfiguration;

import java.util.List;

public interface EntityMapper<E, M> {

    M entityToModel(E entity);

    @InheritInverseConfiguration
    E modelToEntity(M model);

    List<M> entityToModelList(List<E> entities);

    List<E> modelToEntityList(List<M> models);
}
