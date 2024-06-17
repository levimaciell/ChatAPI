package dev.levimaciell.chatAPI.security;

import dev.levimaciell.chatAPI.tokens.TokenService;
import dev.levimaciell.chatAPI.user.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService service;

    @Autowired
    private UserRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        var token = getToken(request);

        if(token != null){
            var subject = service.validateTokenAndGetSubject(token);
            var user = repository.findByUsername(subject);

            if(user != null){
                var authentication = new UsernamePasswordAuthenticationToken
                        (user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request,response);
    }

    private String getToken(HttpServletRequest request) {

        var token = request.getHeader("Authorization");

        if(token != null)
            return token.replace("Bearer ", "");

        return null;

    }
}
