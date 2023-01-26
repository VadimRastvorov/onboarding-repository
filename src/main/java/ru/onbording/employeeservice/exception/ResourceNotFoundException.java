package ru.onbording.employeeservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Long id) {
        super("Не найдена запись с id '" + id + "'");
    }

    public ResourceNotFoundException(UUID id) {
        super("Не найдена запись с id '" + id + "'");
    }

}
