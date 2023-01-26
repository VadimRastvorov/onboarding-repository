package ru.onbording.employeeservice.mapper;

public interface Mapper<E, D> {
    E dtoToEntity(D dto);

    D entityToDto(E entity);
}
