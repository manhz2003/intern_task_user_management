package com.fds.nsw.liferay.core;

import com.fds.nsw.kernel.exception.SystemException;
import com.fds.nsw.liferay.model.User;
import com.fds.nsw.liferay.service.UserLocalServiceUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PortalUtil {

	public static User getUser(HttpServletRequest request) {
		//todo set User
		User user = (User) request.getAttribute("USER");

		if (user != null) {
			return user;
		}

		long userId = (long) request.getAttribute("USER_ID");

		if (userId <= 0) {
			return null;
		}

		try {
			user = UserLocalServiceUtil.fetchUserById(userId);
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e.getMessage());
		}
		request.setAttribute("USER", user);

		return user;
	}

	public static long getUserId(HttpServletRequest request) {
		//todo set User
		return (Long)request.getAttribute(WebKeys.USER_ID);
	}

}
