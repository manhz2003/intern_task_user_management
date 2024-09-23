package com.fds.nsw.liferay.service;

import com.fds.nsw.kernel.exception.SystemException;
import com.fds.nsw.liferay.core.Constants;
import com.fds.nsw.liferay.core.ResourceRequest;
import com.fds.nsw.liferay.core.ThemeDisplay;
import com.fds.nsw.liferay.core.WebKeys;
import com.fds.nsw.liferay.model.User;
import com.fds.nsw.liferay.model.UserInfoDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private  JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Retrieve all cookies from the request
        Cookie[] cookies = request.getCookies();
        String token = null;
        String username = null;

        if (cookies != null) {
            // Find the specific cookie (e.g., "jwtToken")
            Cookie jwtCookie = Arrays.stream(cookies)
                    .filter(cookie -> Constants.KEY_TOKEN.equals(cookie.getName()))
                    .findFirst()
                    .orElse(null);

            if (jwtCookie != null) {
                token = jwtCookie.getValue();
                // Process the JWT token here (e.g., validate it, extract claims, etc.)
//                System.out.println("Token from Cookie: " + token);
                username = jwtService.extractUsername(token);
                // You can continue processing this JWT for authentication/authorization
            }
        }


        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = null;
            try {
                User user = UserLocalServiceUtil.fetchUserByEmailAddress(10154, username);
                userDetails = new UserInfoDetails(user);
                request.getSession().setAttribute(WebKeys.THEME_DISPLAY, new ThemeDisplay(user));
            } catch (SystemException e) {
                throw new RuntimeException(e);
            }

            if (jwtService.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
