package com.company.coursya.jwt.impl;

import com.company.coursya.jwt.JwtService;
import com.company.coursya.service.UserService;
import com.company.coursya.shared.util.Constants;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilterImpl extends OncePerRequestFilter {

    public static final String AUTHORIZATION = "Authorization";
    public static final String PREFIX = "Bearer";
    private final JwtService jwtService;
    private final UserService userService;

    Claims claims = null;
    private String email;

    public JwtFilterImpl(JwtService jwtService, UserService customDetailsService) {
        this.jwtService = jwtService;
        this.userService = customDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Rutas sin token
        if (Constants.VALID_PATHS.contains(request.getServletPath())) {
            filterChain.doFilter(request, response);
            return;
        }

        final String authorizationHeader = request.getHeader(AUTHORIZATION);
        String token = null;

        if (authorizationHeader != null && authorizationHeader.startsWith(PREFIX)) {
            token = authorizationHeader.substring(7);
            email = jwtService.extractUsername(token);
            claims = jwtService.extractAllClaims(token);
        }

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userService.findUserDetailsByEmail(email);
            if (jwtService.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }

    public Boolean isAdmin() {
        return "admin".equalsIgnoreCase((String) claims.get("role"));
    }

    public Boolean isUser() {
        return "user".equalsIgnoreCase((String) claims.get("role"));
    }
}
