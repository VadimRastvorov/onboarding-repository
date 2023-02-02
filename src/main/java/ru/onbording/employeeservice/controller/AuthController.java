package ru.onbording.employeeservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.onbording.employeeservice.config.jwt.JwtProvider;
import ru.onbording.employeeservice.dto.auth.AuthResponseDto;
import ru.onbording.employeeservice.dto.auth.UserDto;
import ru.onbording.employeeservice.entity.UserEntity;
import ru.onbording.employeeservice.service.UserService;

@Slf4j
@RestController
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {
        log.info("вызов метода register '{}'", userDto);
        return ResponseEntity.ok(userService.saveUser(userDto));
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthResponseDto> auth(@RequestBody UserDto userDto) {
        log.info("вызов метода auth '{}'", userDto);
        UserEntity userEntity = userService.findByLoginAndPassword(userDto.getLogin(), userDto.getPassword());
        return ResponseEntity.ok(new AuthResponseDto(jwtProvider.generateToken(userEntity.getLogin())));
    }
}