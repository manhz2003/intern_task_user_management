/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.fds.nsw.liferay.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;
import com.fds.nsw.kernel.util.OrderByComparator;
import com.fds.nsw.liferay.model.Role;
import com.fds.nsw.liferay.model.User;
import com.fds.nsw.liferay.service.impl.UserLocalServiceImpl;

/**
 * The utility for the user local service. This utility wraps
 * {@link impl.UserLocalServiceImpl} and is the primary access point for service
 * operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserLocalService
 * @see base.UserLocalServiceBaseImpl
 * @see impl.UserLocalServiceImpl
 * @generated
 */
@Component
public class UserLocalServiceUtil {
	
	public UserLocalServiceUtil(UserLocalServiceImpl service) {
		UserLocalServiceUtil._service = service;
	}

	/**
	 * Returns the user with the email address.
	 *
	 * @param companyId    the primary key of the user's company
	 * @param emailAddress the user's email address
	 * @return the user with the email address, or <code>null</code> if a user with
	 *         the email address could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public static User fetchUserByEmailAddress(long companyId, java.lang.String emailAddress) throws SystemException {
		return getService().fetchUserByEmailAddress(companyId, emailAddress);
	}

	/**
	 * Returns the user with the primary key.
	 *
	 * @param userId the primary key of the user
	 * @return the user with the primary key, or <code>null</code> if a user with
	 *         the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public static User fetchUserById(long userId) throws SystemException {
		return getService().fetchUserById(userId);
	}

	public static User fetchUser(long userId) throws SystemException {
		return getService().fetchUserById(userId);
	}


	/**
	 * Returns the user with the screen name.
	 *
	 * @param companyId  the primary key of the user's company
	 * @param screenName the user's screen name
	 * @return the user with the screen name, or <code>null</code> if a user with
	 *         the screen name could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public static User fetchUserByScreenName(long companyId, java.lang.String screenName) throws SystemException {
		return getService().fetchUserByScreenName(companyId, screenName);
	}

	/**
	 * Returns a range of all the users belonging to the company.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end -
	* start</code> instances. <code>start</code> and <code>end</code> are not
	 * primary keys, they are indexes in the result set. Thus, <code>0</code> refers
	 * to the first result in the set. Setting both <code>start</code> and
	 * <code>end</code> to
	 *  will return the
	 * full result set.
	 * </p>
	 *
	 * @param companyId the primary key of the company
	 * @param start     the lower bound of the range of users
	 * @param end       the upper bound of the range of users (not inclusive)
	 * @return the range of users belonging to the company
	 * @throws SystemException if a system exception occurred
	 */
	public static java.util.List<User> getCompanyUsers(long companyId, int start, int end) throws SystemException {
		return getService().getCompanyUsers(companyId, start, end);
	}

	/**
	 * Returns the number of users belonging to the company.
	 *
	 * @param companyId the primary key of the company
	 * @return the number of users belonging to the company
	 * @throws SystemException if a system exception occurred
	 */
	public static int getCompanyUsersCount(long companyId) throws SystemException {
		return getService().getCompanyUsersCount(companyId);
	}

	/**
	 * Returns the user with the contact ID.
	 *
	 * @param contactId the user's contact ID
	 * @return the user with the contact ID
	 * @throws PortalException if a user with the contact ID could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public static User getUserByContactId(long contactId) throws PortalException, SystemException {
		return getService().getUserByContactId(contactId);
	}

	/**
	 * Returns the user with the email address.
	 *
	 * @param companyId    the primary key of the user's company
	 * @param emailAddress the user's email address
	 * @return the user with the email address
	 * @throws PortalException if a user with the email address could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public static User getUserByEmailAddress(long companyId, java.lang.String emailAddress)
			throws PortalException, SystemException {
		return getService().getUserByEmailAddress(companyId, emailAddress);
	}

	/**
	 * Returns the user with the Facebook ID.
	 *
	 * @param companyId  the primary key of the user's company
	 * @param facebookId the user's Facebook ID
	 * @return the user with the Facebook ID
	 * @throws PortalException if a user with the Facebook ID could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public static User getUserByFacebookId(long companyId, long facebookId) throws PortalException, SystemException {
		return getService().getUserByFacebookId(companyId, facebookId);
	}

	/**
	 * Returns the user with the primary key.
	 *
	 * @param userId the primary key of the user
	 * @return the user with the primary key
	 * @throws PortalException if a user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public static User getUserById(long userId) throws PortalException, SystemException {
		return getService().getUserById(userId);
	}

	/**
	 * Returns the user with the primary key from the company.
	 *
	 * @param companyId the primary key of the user's company
	 * @param userId    the primary key of the user
	 * @return the user with the primary key
	 * @throws PortalException if a user with the primary key from the company could
	 *                         not be found
	 * @throws SystemException if a system exception occurred
	 */
	public static User getUserById(long companyId, long userId) throws PortalException, SystemException {
		return getService().getUserById(companyId, userId);
	}

	/**
	 * Returns the user with the OpenID.
	 *
	 * @param companyId the primary key of the user's company
	 * @param openId    the user's OpenID
	 * @return the user with the OpenID
	 * @throws PortalException if a user with the OpenID could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public static User getUserByOpenId(long companyId, java.lang.String openId)
			throws PortalException, SystemException {
		return getService().getUserByOpenId(companyId, openId);
	}

	/**
	 * Returns the user with the portrait ID.
	 *
	 * @param portraitId the user's portrait ID
	 * @return the user with the portrait ID
	 * @throws PortalException if a user with the portrait ID could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public static User getUserByPortraitId(long portraitId) throws PortalException, SystemException {
		return getService().getUserByPortraitId(portraitId);
	}

	/**
	 * Returns the user with the screen name.
	 *
	 * @param companyId  the primary key of the user's company
	 * @param screenName the user's screen name
	 * @return the user with the screen name
	 * @throws PortalException if a user with the screen name could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public static User getUserByScreenName(long companyId, java.lang.String screenName)
			throws PortalException, SystemException {
		return getService().getUserByScreenName(companyId, screenName);
	}

	/**
	 * Returns the user with the universally unique identifier.
	 *
	 * @param uuid the user's universally unique identifier
	 * @return the user with the universally unique identifier
	 * @throws PortalException if a user with the universally unique identifier
	 *                         could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public static User getUserByUuid(java.lang.String uuid) throws PortalException, SystemException {
		return getService().getUserByUuid(uuid);
	}

	/**
	 * Returns the primary key of the user with the email address.
	 *
	 * @param companyId    the primary key of the user's company
	 * @param emailAddress the user's email address
	 * @return the primary key of the user with the email address
	 * @throws PortalException if a user with the email address could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public static long getUserIdByEmailAddress(long companyId, java.lang.String emailAddress)
			throws PortalException, SystemException {
		return getService().getUserIdByEmailAddress(companyId, emailAddress);
	}
	
	public static List<Role> getRoles(long userId, int start, int end, OrderByComparator orderByComparator)
			throws SystemException{
		return getService().getRoles(userId, start, end, orderByComparator);
	}

	/**
	 * Returns the primary key of the user with the screen name.
	 *
	 * @param companyId  the primary key of the user's company
	 * @param screenName the user's screen name
	 * @return the primary key of the user with the screen name
	 * @throws PortalException if a user with the screen name could not be found
	 * @throws SystemException if a system exception occurred
	 */

	public static User loadGetDefaultUser(long companyId) throws PortalException, SystemException {
		return getService().loadGetDefaultUser(companyId);
	}

	public static UserLocalServiceImpl getService() {

		return _service;
	}

	private static UserLocalServiceImpl _service;
}