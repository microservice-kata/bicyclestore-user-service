package me.aikin.bicyclestore.user.unit;

import me.aikin.bicyclestore.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_return_about_when_call_about() {
        assertEquals("aikin", userService.getUserName());
    }
}
