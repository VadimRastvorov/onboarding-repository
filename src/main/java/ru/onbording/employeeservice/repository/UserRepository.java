package ru.onbording.employeeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.onbording.employeeservice.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByLogin(String login);
}