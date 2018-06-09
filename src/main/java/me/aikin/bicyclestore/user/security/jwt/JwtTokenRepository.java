package me.aikin.bicyclestore.user.security.jwt;

import java.util.Map;

public interface JwtTokenRepository {
    String generateToken(Map<String, Object> claims);

    String extractAuthorizedPayload(String jwtToken);

    boolean validateToken(String jwtToken);
}
