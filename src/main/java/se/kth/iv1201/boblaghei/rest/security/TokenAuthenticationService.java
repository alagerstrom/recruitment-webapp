package se.kth.iv1201.boblaghei.rest.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;

class TokenAuthenticationService {

    static final int EXPIRATION_TIME = 3600 * 1000 * 24;
    static final String SECRET = "sdagfoowaegh0293tu0298ofiqh09f2q09fse98yre89rge80gwe89gwe98ygwe0y823rour132ojbfwqjlnhiouasdfklblawjfdblfsc";
    static final String TOKEN_PREFIX = "Bearer";
    static final String AUTHORIZATION = "Authorization";

    static void addAuthentication(HttpServletResponse response, String username) throws IOException {
        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        response.addCookie(new Cookie(AUTHORIZATION, TOKEN_PREFIX + token));
        response.addHeader(AUTHORIZATION, TOKEN_PREFIX + token);
    }

    static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(AUTHORIZATION);
        if (token == null && request.getCookies() != null){
            for (Cookie cookie : request.getCookies())
                if (cookie.getName().equals(AUTHORIZATION))
                    token = cookie.getValue();
        }

        if (token != null) {
            String user = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();
            if (user != null)
                return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
        }
        return null;
    }
}
