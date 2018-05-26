package me.aikin.bicyclestore.user.api;

import me.aikin.bicyclestore.user.api.auth.playload.LoginRequest;
import me.aikin.bicyclestore.user.domain.User;
import me.aikin.bicyclestore.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.hamcrest.CoreMatchers.*;

public class AuthControllerTest extends ApiBaseTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void should_can_signin() {

        String rawPassword = "password@aikin";
        String encodePassword = passwordEncoder.encode(rawPassword);
        User user = User.builder()
            .email("1@aikin.me")
            .name("aikin")
            .username("aikin")
            .password(encodePassword)
            .build();
        userRepository.saveAndFlush(user);

        LoginRequest loginRequest = LoginRequest.builder()
            .usernameOrEmail(user.getUsername())
            .password(rawPassword)
            .build();
        given().
            body(loginRequest).
            when().
            post("/api/auth/signin").
            then().
            statusCode(is(200)).
            body("accessToken", notNullValue()).
            body("tokenType", equalTo("Bearer"));


    }
}
