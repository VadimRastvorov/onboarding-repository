package ru.onbording.employeeservice.mapper.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.onbording.employeeservice.dto.auth.UserDto;
import ru.onbording.employeeservice.entity.RoleEntity;
import ru.onbording.employeeservice.entity.UserEntity;
import ru.onbording.employeeservice.mapper.Mapper;
import ru.onbording.employeeservice.repository.RoleRepository;

@Service
public class UserMapperImpl implements Mapper<UserEntity, UserDto> {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleEntityRepository;

    public UserDto entityToDto(UserEntity entity) {

        return UserDto.builder()
                .login(entity.getLogin())
                .password(entity.getPassword())
                .role(entity.getRoleEntity().getName())
                .build();
    }

    public UserEntity dtoToEntity(UserDto dto) {
        return UserEntity.builder()
                .login(dto.getLogin())
                .password(passwordEncoder.encode(dto.getPassword()))
                .roleEntity(findRole(dto.getRole()))
                .build();
    }

    private RoleEntity findRole(String role) {
        if (role != null && !role.isBlank()) {
            RoleEntity roleEntity = roleEntityRepository.findByName(role);
            if (roleEntity != null) {
                return roleEntity;
            }
        }
        return roleEntityRepository.findByName("ROLE_USER");
    }
}
