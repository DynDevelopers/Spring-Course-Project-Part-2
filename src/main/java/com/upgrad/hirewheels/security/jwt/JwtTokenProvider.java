package com.upgrad.hirewheels.security.jwt;

import com.upgrad.hirewheels.entities.User;
import com.upgrad.hirewheels.exceptions.UserNotFoundException;
import com.upgrad.hirewheels.services.UserService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey = "secret";

    @Value("${security.jwt.token.expire-length:600000}")
    private long validityInMilliseconds = 600000;//10 minutes

    @Value("${security.jwt.token.expire-length:3600000}")
    private long refreshValidityInMilliseconds = 3600000; //60 minutes

    @Autowired
    private UserService userService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String emailId) {
        Claims claims = Jwts.claims().setSubject(emailId);
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Authentication getAuthentication(String token) throws UserNotFoundException {
        User user = this.userService.getUserByEmail(getUsername(token));
        return new UsernamePasswordAuthenticationToken(user, "", null);
    }

    public String getUsername(String token) {
        String subject = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
        String issuer = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getIssuer();
        String audience = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getAudience();
        String id = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getId();
        System.out.println(subject + " " + issuer + " " + audience + " " + id );
        return subject;
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("X-Access-Token");
        if (bearerToken != null) {
            return bearerToken.trim();
        }
        return null;
    }

    public boolean validateToken(String token) throws InvalidJwtAuthenticationException {
        if (!userService.isTokenPresent(token))
            throw new InvalidJwtAuthenticationException("Expired or invalid JWT token");

        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

            if (claims.getBody().getExpiration().before(new Date()))
                return false;

            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidJwtAuthenticationException("Expired or invalid JWT token");
        }
    }
}
