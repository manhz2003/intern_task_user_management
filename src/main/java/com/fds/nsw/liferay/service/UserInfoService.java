package com.fds.nsw.liferay.service;

import com.fds.nsw.kernel.exception.SystemException;
import com.fds.nsw.kernel.util.DigestUtil;
import com.fds.nsw.liferay.model.User;
import com.fds.nsw.liferay.model.UserInfoDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        User userInfo = null;
        try {
            userInfo = UserLocalServiceUtil.fetchUserByEmailAddress(10154, emailAddress);
        } catch (SystemException e) {
            throw new RuntimeException(e);
        }

        return new UserInfoDetails(userInfo);
    }

}
