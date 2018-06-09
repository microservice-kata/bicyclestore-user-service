package me.aikin.bicyclestore.user.api;

import me.aikin.bicyclestore.user.api.auth.playload.LoginRequest;
import me.aikin.bicyclestore.user.api.auth.playload.SignUpRequest;
import me.aikin.bicyclestore.user.domain.Role;
import me.aikin.bicyclestore.user.domain.RoleName;
import me.aikin.bicyclestore.user.domain.User;
import me.aikin.bicyclestore.user.repository.RoleRepository;
import me.aikin.bicyclestore.user.repository.UserRepository;
import me.aikin.bicyclestore.user.security.JwtTokenProvider;
import me.aikin.bicyclestore.user.security.UserPrincipal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthControllerTest extends ApiBaseTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    JwtTokenProvider tokenProvider;

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

        String accessToken =
        given().
            body(loginRequest).
        when().
            post("/api/auth/signin").
        then().
            statusCode(is(200)).
            body("accessToken", notNullValue()).
            body("tokenType", equalTo("Bearer")).
        extract().
            path("accessToken");

        UserPrincipal currentUser = tokenProvider.getAuthorizedCurrentUser(accessToken);
        assertEquals(currentUser.getId(), user.getId());
        assertEquals(currentUser.getName(), user.getName());
        assertEquals(currentUser.getUsername(), user.getUsername());
    }

    @Test
    public void should_can_signup() {
        //TODO: refactor: should setup role by default migration seed
        Role roleAdmin = Role.builder().name(RoleName.ROLE_ADMIN).build();
        Role roleUser = Role.builder().name(RoleName.ROLE_USER).build();
        roleRepository.saveAndFlush(roleUser);
        roleRepository.saveAndFlush(roleAdmin);

        SignUpRequest signUpRequest = SignUpRequest.builder()
            .name("aikin")
            .username("aikin")
            .email("1@aikin.me")
            .password("xexie@34$345sdk")
            .build();

        given().
            body(signUpRequest).
        when().
            post("/api/auth/signup").
        then().
            statusCode(is(201)).
            header("location", equalTo("http://localhost/users/aikin"));
    }

    @Test
    @Transactional // TODO: should fix could not initialize proxy - no Session https://stackoverflow.com/questions/46810929/how-to-use-fetchtype-lazy-with-manytomany
    public void should_set_user_be_role_user_when_signup() {
        //TODO: refactor: should setup role by default migration seed
        Role roleAdmin = Role.builder().name(RoleName.ROLE_ADMIN).build();
        Role roleUser = Role.builder().name(RoleName.ROLE_USER).build();
        roleRepository.saveAndFlush(roleUser);
        roleRepository.saveAndFlush(roleAdmin);

        SignUpRequest signUpRequest = SignUpRequest.builder()
            .name("aikin")
            .username("aikin")
            .email("1@aikin.me")
            .password("xexie@34$345sdk")
            .build();

        given().
            body(signUpRequest).
        when().
            post("/api/auth/signup").
        then().
            statusCode(is(201)).
            header("location", equalTo("http://localhost/users/aikin"));

        Optional<User> user = userRepository.findByUsernameOrEmail(signUpRequest.getUsername(), signUpRequest.getEmail());

        assertTrue(user.isPresent());
        assertEquals(1, user.get().getRoles().size());
        assertEquals(RoleName.ROLE_USER, user.get().getRoles().stream().findFirst().get().getName());
    }

    @Test
    public void should_return_bad_request_when_username_exist() {
        String rawPassword = "password@aikin";
        String encodePassword = passwordEncoder.encode(rawPassword);
        User existedUser = User.builder()
            .email("1@aikin.me")
            .name("aikin")
            .username("aikin")
            .password(encodePassword)
            .build();
        userRepository.saveAndFlush(existedUser);

        SignUpRequest signUpRequest = SignUpRequest.builder()
            .name("otherUser")
            .username(existedUser.getUsername())
            .email("1@other.me")
            .password("xexie@34$345sdk")
            .build();

        given().
            body(signUpRequest).
        when().
            post("/api/auth/signup").
        then().
            statusCode(is(400));
    }

    @Test
    public void should_return_bad_request_when_email_exist() {
        String rawPassword = "password@aikin";
        String encodePassword = passwordEncoder.encode(rawPassword);
        User existedUser = User.builder()
            .email("1@aikin.me")
            .name("aikin")
            .username("aikin")
            .password(encodePassword)
            .build();
        userRepository.saveAndFlush(existedUser);

        SignUpRequest signUpRequest = SignUpRequest.builder()
            .name("otherUser")
            .username("otherUserName")
            .email(existedUser.getEmail())
            .password("xexie@34$345sdk")
            .build();

        given().
            body(signUpRequest).
        when().
            post("/api/auth/signup").
        then().
            statusCode(is(400));
    }

}
