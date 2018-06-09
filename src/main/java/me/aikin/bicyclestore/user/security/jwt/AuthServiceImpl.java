package me.aikin.bicyclestore.user.security.jwt;

import me.aikin.bicyclestore.user.exception.InvalidCredentialException;
import me.aikin.bicyclestore.user.security.UserPrincipal;
import me.aikin.bicyclestore.user.utils.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
public class AuthServiceImpl implements AuthService {

    @Value("${security.jwt.token-prefix:Bearer}")
    private String tokenPrefix;

    @Value("${security.jwt.token-key:Authorization}")
    private String tokenKey;

    @Autowired
    private JwtTokenRepository jwtTokenRepository;


    @Override
    public String generateToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Map<String, Object> claims = JsonHelper.parseJson(JsonHelper.toJson(userPrincipal), Map.class);
        return jwtTokenRepository.generateToken(claims);
    }

    @Override
    public boolean validateToken(HttpServletRequest request) {
        String jwtToken = getJwtFromRequest(request);
        return StringUtils.hasText(jwtToken) && jwtTokenRepository.validateToken(jwtToken);
    }

    @Override
    public UserPrincipal getAuthorizedCurrentUser(HttpServletRequest request) {
        String jwtToken = getJwtFromRequest(request);
        return JsonHelper.parseJson(jwtTokenRepository.extractAuthorizedPayload(jwtToken), UserPrincipal.class);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(tokenKey);
        String prefix = String.join(" ", tokenPrefix);
        if (validateToken(request) && StringUtils.hasText(bearerToken) && bearerToken.startsWith(prefix)) {
            return bearerToken.substring(prefix.length(), bearerToken.length());
        }
        throw new InvalidCredentialException();
    }
}
