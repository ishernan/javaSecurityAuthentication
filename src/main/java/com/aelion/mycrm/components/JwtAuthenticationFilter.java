package com.aelion.mycrm.components;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.aelion.mycrm.exceptions.JwtTokenMissingException;
import com.aelion.mycrm.services.UserAuthService;

import io.jsonwebtoken.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserAuthService userAuthService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Get Authorization header
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer")) {
            throw new JwtTokenMissingException("No JWT token found in the request headers");
        }

        String token = header.substring("Bearer".length() + 1);

//        this.jwtUtil.validateToken(token);

        String userName = this.jwtUtil.getUserName(token);

        UserDetails userDetails = this.userAuthService.loadUserByUsername(userName);

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
            );
        usernamePasswordAuthenticationToken.setDetails(
                new WebAuthenticationDetailsSource()
                    .buildDetails(request)
            );

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

//        filterChain.doFilter(request, response);
    }

}
