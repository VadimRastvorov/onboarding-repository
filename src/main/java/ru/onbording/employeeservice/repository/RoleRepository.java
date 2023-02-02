package ru.onbording.employeeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.onbording.employeeservice.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    RoleEntity findByName(String name);
}