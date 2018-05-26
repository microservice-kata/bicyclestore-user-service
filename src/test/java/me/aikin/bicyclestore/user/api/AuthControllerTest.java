package me.aikin.bicyclestore.user.api;

import me.aikin.bicyclestore.user.api.auth.playload.LoginRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class AuthControllerTest extends ApiBaseTest {
    @Test
    public void should_can_signin() {
        LoginRequest loginRequest = LoginRequest.builder()
            .usernameOrEmail("aikin")
            .password("password")
            .build();

        given().
            body(loginRequest).
        when().
            post("/api/auth/signin").
        then().
            statusCode(is(HttpStatus.OK)).
            body("[0].accessToken", equalTo("APPROVAL"));

    }
}
