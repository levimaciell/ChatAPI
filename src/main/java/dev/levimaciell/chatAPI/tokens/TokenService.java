package dev.levimaciell.chatAPI.tokens;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import dev.levimaciell.chatAPI.user.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("security.token.secret")
    private String secret;

    @Value("spring.application.name")
    private String issuer;

    public String createToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer(issuer)
                    .withSubject(user.getUsername())
                    .withExpiresAt(tokenExpirationDate(120))
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new TokenServiceException("Error while generating a JWT Token" + exception.getMessage());
        }
    }

    public String validateTokenAndGetSubject(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(token).getSubject();

        } catch (JWTVerificationException exception){
            throw new TokenServiceException("JWT Token is expired or invalid!");
        }
    }

    //TODO: put the minutes variable inside the properties file
    private Instant tokenExpirationDate(Integer minutes){
        return LocalDateTime.now().plusMinutes(minutes).toInstant(ZoneOffset.of("-03:00"));
    }



}
