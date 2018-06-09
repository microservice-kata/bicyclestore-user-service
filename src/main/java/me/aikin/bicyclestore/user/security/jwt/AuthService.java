package me.aikin.bicyclestore.user.security.jwt;

import me.aikin.bicyclestore.user.security.UserPrincipal;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {
    String generateToken(Authentication authentication);

    boolean validateToken(HttpServletRequest request);

    UserPrincipal getAuthorizedCurrentUser(HttpServletRequest request);
}
