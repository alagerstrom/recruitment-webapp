package se.kth.iv1201.boblaghei.rest.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import se.kth.iv1201.boblaghei.entity.Role;
import se.kth.iv1201.boblaghei.entity.User;
import se.kth.iv1201.boblaghei.repository.UserRepository;
import se.kth.iv1201.boblaghei.service.SecurityService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

public class TokenAuthenticationService {

    public static final int EXPIRATION_TIME = 3600 * 1000 * 24;
    public static final String SECRET = "sdagfoowaegh0293tu0298ofiqh09f2q09fse98yre89rge80gwe89gwe98ygwe0y823rour132ojbfwqjlnhiouasdfklblawjfdblfsc";
    public static final String TOKEN_PREFIX = "";
    public static final String AUTHORIZATION = "Authorization";

    public void addAuthentication(HttpServletResponse response, String username) throws IOException {
        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        response.addHeader(AUTHORIZATION, TOKEN_PREFIX + token);
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(AUTHORIZATION);
        if (token == null && request.getCookies() != null) {
            for (Cookie cookie : request.getCookies())
                if (cookie.getName().equals(AUTHORIZATION))
                    token = cookie.getValue();
        }

        if (token != null && !token.equals("")) {
            String username = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();
            if (username != null) {
                return new UsernamePasswordAuthenticationToken(username, null, Arrays.asList(Role.ROLE_RECRUITER, Role.ROLE_APPLICANT));
            }
        }
        return null;
    }
}
