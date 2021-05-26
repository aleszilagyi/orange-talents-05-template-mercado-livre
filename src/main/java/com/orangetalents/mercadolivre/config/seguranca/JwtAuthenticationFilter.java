package com.orangetalents.mercadolivre.config.seguranca;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private TokenManager tokenManager;
    private UserService userService;

    public JwtAuthenticationFilter(TokenManager tokenManager, UserService userService) {
        this.tokenManager = tokenManager;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Optional<String> possibleToken = getTokenFromRequest(request);
        if (possibleToken.isPresent()) {
            String token = possibleToken.get();
            if (token.startsWith("Bearer ")) {
                token = token.substring(7, token.length());
            }
            if (tokenManager.isValid(token)) {
                authenticateClient(token);
            }
        }

        filterChain.doFilter(request, response);
    }

    private void authenticateClient(String token) {
        String username = tokenManager.getUserName(token);
        UserDetails userDetails = userService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    public Optional<String> getTokenFromRequest(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization");
        return Optional.ofNullable(authToken);
    }
}
