package ru.onbording.employeeservice.config.jwt.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.onbording.employeeservice.config.jwt.JwtProvider;
import ru.onbording.employeeservice.config.jwt.dto.AuthRequestDto;
import ru.onbording.employeeservice.config.jwt.dto.AuthResponseDto;
import ru.onbording.employeeservice.config.jwt.service.CustomUserDetailsService;
import ru.onbording.employeeservice.entity.UserEntity;

@Slf4j
@RestController
public class AuthController {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/auth")
    public ResponseEntity<AuthResponseDto> auth(@RequestBody AuthRequestDto authRequestDto) {
        log.info("вызов метода auth '{}'", authRequestDto);
        UserEntity userEntity = customUserDetailsService.findByLoginAndPassword(authRequestDto.getLogin(), authRequestDto.getPassword());
        return ResponseEntity.ok(new AuthResponseDto(jwtProvider.generateToken(userEntity.getLogin()), JwtProvider.AUTHORIZATION_TYPE_NAME_STARTS_WITH));
    }
}