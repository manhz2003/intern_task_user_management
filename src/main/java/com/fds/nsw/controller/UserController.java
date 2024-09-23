package com.fds.nsw.controller;

import com.fds.nsw.kernel.util.AuthUtil;
import com.fds.nsw.liferay.core.Constants;
import com.fds.nsw.liferay.core.ResourceRequest;
import com.fds.nsw.liferay.core.ThemeDisplay;
import com.fds.nsw.liferay.core.WebKeys;
import com.fds.nsw.liferay.service.JwtService;
import com.fds.nsw.liferay.service.UserLocalServiceUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
@Slf4j
public class UserController {


    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/token")
    public ResponseEntity<?> authenticateAndGetToken(HttpServletRequest request,
                                                     @RequestHeader("Authorization") String authorization, HttpServletResponse response) {
        try {

            String[] credentials = AuthUtil.extractAndDecodeHeader(authorization);
            String username = credentials[0];
            String password = credentials[1];

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    username, password);
            Authentication authentication = authenticationManager.authenticate(authToken);

            if (authentication.isAuthenticated()) {
                String token = jwtService.generateToken(username);

                //save theme display
//                ResourceRequest resourceRequest = new ResourceRequest(request);
//                request.getSession().setAttribute(WebKeys.THEME_DISPLAY, new ThemeDisplay(UserLocalServiceUtil.fetchUserByEmailAddress(10154, username)));


                //save cookie
                Cookie cookie = new Cookie(Constants.KEY_TOKEN, token);
                cookie.setHttpOnly(true);
                cookie.setSecure(true);
                cookie.setPath("/");
                cookie.setMaxAge(1*1*60*60);

                response.addCookie(cookie);

                return ResponseEntity.ok(token);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Authentication failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/user/userProfile")
    public String userProfile() {
        log.info("test");

        return "Đăng nhập thành công";
    }
}
