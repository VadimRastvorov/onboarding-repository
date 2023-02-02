package ru.onbording.employeeservice.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.onbording.employeeservice.dto.auth.UserDto;
import ru.onbording.employeeservice.entity.UserEntity;
import ru.onbording.employeeservice.mapper.Mapper;
import ru.onbording.employeeservice.repository.UserRepository;

@AllArgsConstructor
@Service
public class UserService {

    @Autowired
    private UserRepository userEntityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private final Mapper<UserEntity, UserDto> userDtoMapper;

    public UserDto saveUser(UserDto userDto) {
        UserEntity userEntity = findByLogin(userDto.getLogin());
        if (userEntity != null) {
            return userDtoMapper.entityToDto(userEntity);
        }
        userEntity = userDtoMapper.dtoToEntity(userDto);
        return userDtoMapper.entityToDto(userEntityRepository.save(userEntity));
    }

    public UserEntity findByLogin(String login) {
        return userEntityRepository.findByLogin(login);
    }

    public UserEntity findByLoginAndPassword(String login, String password) {
        UserEntity userEntity = findByLogin(login);
        if (userEntity != null) {
            if (passwordEncoder.matches(password, userEntity.getPassword())) {
                return userEntity;
            }
        }
        return null;
    }
}