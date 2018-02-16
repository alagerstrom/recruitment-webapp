package se.kth.iv1201.boblaghei.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import se.kth.iv1201.boblaghei.entity.User;
import se.kth.iv1201.boblaghei.repository.UserRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class TokenService {

    @Autowired
    UserRepository userRepository;

    public static final int EXPIRATION_TIME = 3600 * 1000 * 24;
    public static final String SECRET = "sdagfoowaegh0293tu0298ofiqh09f2q09fse98yre89rge80gwe89gwe98ygwe0y823rour132ojbfwqjlnhiouasdfklblawjfdblfsc";
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String AUTHORIZATION = "Authorization";

    public Authentication getAuthentication(String token) {
        String username = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        UserDetails userDetails = userRepository.getUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
    }

    public String createToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public String resolveToken(HttpServletRequest request) {
        String token = request.getHeader(AUTHORIZATION);
        if (token == null && request.getCookies() != null) {
            for (Cookie cookie : request.getCookies())
                if (cookie.getName().equals(AUTHORIZATION))
                    token = cookie.getValue();
        }
        if (token == null)
            return null;
        return token.replace(TOKEN_PREFIX, "");
    }

    public boolean isValidToken(String token) {
        if (token.isEmpty())
            return false;
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new RuntimeException("Invalid token");
        }
    }
}
