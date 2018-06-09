package me.aikin.bicyclestore.user.security.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import me.aikin.bicyclestore.user.utils.json.JsonHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Map;

@Slf4j
@Component
public class JwtTokenRepositoryImpl implements JwtTokenRepository  {
    @Value("${security.jwt.secret:_SEMS_JWT_SECRET_201805260909999}")
    private String jwtSecret;

    @Value("${security.jwt.expiration-in-seconds}")
    private long jwtExpirationInSeconds;

    @Override
    public String generateToken(Map<String, Object> claims) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInSeconds);
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(new Date())
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact();
    }

    @Override
    public String extractAuthorizedPayload(String jwtToken) {
        Claims claims = Jwts.parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(jwtToken)
            .getBody();
        return JsonHelper.toJson(claims);
    }

    @Override
    public boolean validateToken(String jwtToken) {
        if (!StringUtils.hasText(jwtToken)) {
            return false;
        }
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwtToken);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }
}
