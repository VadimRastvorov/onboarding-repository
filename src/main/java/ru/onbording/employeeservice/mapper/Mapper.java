package ru.onbording.employeeservice.mapper;

import org.springframework.stereotype.Service;


public interface Mapper<E, D> {
    E dtoToEntity(D dto);

    D entityToDto(E entity);
}
