package dev.levimaciell.chatAPI.security;

import com.google.gson.Gson;
import dev.levimaciell.chatAPI.authentication.ErrorAuthDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        Gson gson = new Gson();

        var errorDto = new ErrorAuthDto(
                LocalDateTime.now().toString(),
                "Authentication failed!"
        );

        var errorDtoJson = gson.toJson(errorDto);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(errorDtoJson);
    }
}
