package com.ahmet.e_commerce_oct_back.JWT;

import com.ahmet.e_commerce_oct_back.entities.AppUser;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtCreate {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtCreate.class);

    private static final long EXPIRE_DATE = 3 * 24 * 60 * 60 * 1000; //3 days

    @Value("${app.jwt.secret}")
    private String secretKey;

    public String createToken(AppUser user) {
        String token = Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuer("com.ahmet")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DATE))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
        return token;
    }

    public boolean isValid(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            LOGGER.error("Jwt expire " + e);
        } catch (IllegalArgumentException e) {
            LOGGER.error("Token is empty or null, has only white space " + e);
        } catch (MalformedJwtException e) {
            LOGGER.error("Jwt is invalid " + e);
        } catch (UnsupportedJwtException e) {
            LOGGER.error("Jwt is not supported " + e);
        } catch (SignatureException e) {
            LOGGER.error("Signature validation failed " + e);
        }
        return false;
    }

    public String getSubject(String token) {
        return parseClaims(token).getSubject();
    }

    public Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }
}
