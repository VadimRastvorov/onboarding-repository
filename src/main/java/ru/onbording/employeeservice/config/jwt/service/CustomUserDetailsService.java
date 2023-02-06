package ru.onbording.employeeservice.config.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.onbording.employeeservice.config.jwt.entity.CustomUserDetails;
import ru.onbording.employeeservice.config.jwt.repository.UserRepository;
import ru.onbording.employeeservice.entity.UserEntity;

@Service(value = "customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userEntityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userEntityRepository.findByLogin(username);
        return CustomUserDetails.fromUserEntityToCustomUserDetails(userEntity);
    }

    public UserEntity findByLoginAndPassword(String login, String password) {
        UserEntity userEntity = userEntityRepository.findByLogin(login);
        if (userEntity != null) {
            if (passwordEncoder.matches(password, userEntity.getPassword())) {
                return userEntity;
            }
        }
        return null;
    }
}