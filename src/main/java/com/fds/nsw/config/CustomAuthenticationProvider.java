package com.fds.nsw.config;

import com.fds.nsw.kernel.util.DigestUtil;
import com.fds.nsw.liferay.service.UserInfoService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

    public CustomAuthenticationProvider(UserInfoService userInfoService) {
        this.setUserDetailsService(userInfoService);
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws BadCredentialsException {
        String providedPassword = authentication.getCredentials().toString();

        // Sử dụng DigestUtil để băm mật khẩu đầu vào bằng SHA-256
        String hashedPassword = DigestUtil.digestBase64("SHA-1", providedPassword);

        // So sánh mật khẩu đã băm với mật khẩu đã lưu trong hệ thống
        if (!userDetails.getPassword().equals(hashedPassword)) {
            throw new BadCredentialsException("Invalid credentials");
        }
    }
}
