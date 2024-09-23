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

package com.fds.nsw.liferay.service.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fds.flex.common.utility.string.StringUtil;
import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;
import com.fds.nsw.kernel.util.OrderByComparator;
import com.fds.nsw.liferay.model.Role;
import com.fds.nsw.liferay.model.User;
import com.fds.nsw.liferay.service.exception.NoSuchUserException;
import com.fds.nsw.liferay.service.persistence.GroupPersistenceImpl;
import com.fds.nsw.liferay.service.persistence.OrganizationPersistenceImpl;
import com.fds.nsw.liferay.service.persistence.UserPersistenceImpl;

/**
 * The implementation of the user local service.
 *
 * @author Brian Wing Shun Chan
 * @author Scott Lee
 * @author Raymond Augé
 * @author Jorge Ferrer
 * @author Julio Camarero
 * @author Wesley Gong
 * @author Zsigmond Rab
 */
@Service
public class UserLocalServiceImpl {
	@Autowired
	UserPersistenceImpl persistence;
	
	@Autowired
	GroupPersistenceImpl groupPersistence;
	
	@Autowired
	OrganizationPersistenceImpl organizationPersistence;

	/**
	 * Returns the user with the email address.
	 *
	 * @param companyId    the primary key of the user's company
	 * @param emailAddress the user's email address
	 * @return the user with the email address, or <code>null</code> if a user with
	 *         the email address could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public User fetchUserByEmailAddress(long companyId, String emailAddress) throws SystemException {

		return persistence.fetchByC_EA(companyId, emailAddress);
	}

	/**
	 * Returns the user with the primary key.
	 *
	 * @param userId the primary key of the user
	 * @return the user with the primary key, or <code>null</code> if a user with
	 *         the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public User fetchUserById(long userId) throws SystemException {
		return persistence.fetchByPrimaryKey(userId);
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
	public User fetchUserByScreenName(long companyId, String screenName) throws SystemException {

		screenName = getScreenName(screenName);

		return persistence.fetchByC_SN(companyId, screenName);
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
	public List<User> getCompanyUsers(long companyId, int start, int end) throws SystemException {

		return persistence.findByCompanyId(companyId, start, end);
	}

	/**
	 * Returns the number of users belonging to the company.
	 *
	 * @param companyId the primary key of the company
	 * @return the number of users belonging to the company
	 * @throws SystemException if a system exception occurred
	 */
	public int getCompanyUsersCount(long companyId) throws SystemException {
		return persistence.countByCompanyId(companyId);
	}



	/**
	 * Returns the user with the contact ID.
	 *
	 * @param contactId the user's contact ID
	 * @return the user with the contact ID
	 * @throws PortalException if a user with the contact ID could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public User getUserByContactId(long contactId) throws PortalException, SystemException {

		return persistence.findByContactId(contactId);
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
	public User getUserByEmailAddress(long companyId, String emailAddress) throws PortalException, SystemException {

		emailAddress = emailAddress.trim().toLowerCase();

		return persistence.findByC_EA(companyId, emailAddress);
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
	public User getUserByFacebookId(long companyId, long facebookId) throws PortalException, SystemException {

		return persistence.findByC_FID(companyId, facebookId);
	}

	/**
	 * Returns the user with the primary key.
	 *
	 * @param userId the primary key of the user
	 * @return the user with the primary key
	 * @throws PortalException if a user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public User getUserById(long userId) throws PortalException, SystemException {

		return persistence.findByPrimaryKey(userId);
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
	public User getUserById(long companyId, long userId) throws PortalException, SystemException {

		return persistence.findByC_U(companyId, userId);
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
	public User getUserByOpenId(long companyId, String openId) throws PortalException, SystemException {

		return persistence.findByC_O(companyId, openId);
	}

	/**
	 * Returns the user with the portrait ID.
	 *
	 * @param portraitId the user's portrait ID
	 * @return the user with the portrait ID
	 * @throws PortalException if a user with the portrait ID could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public User getUserByPortraitId(long portraitId) throws PortalException, SystemException {

		return persistence.findByPortraitId(portraitId);
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
	public User getUserByScreenName(long companyId, String screenName) throws PortalException, SystemException {

		screenName = getScreenName(screenName);

		return persistence.findByC_SN(companyId, screenName);
	}
	
	protected String getScreenName(String screenName) {
		return StringUtil.trim(screenName).toLowerCase();
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
	public User getUserByUuid(String uuid) throws PortalException, SystemException {

		List<User> users = persistence.findByUuid(uuid);

		if (users.isEmpty()) {
			throw new NoSuchUserException();
		} else {
			return users.get(0);
		}
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
	public long getUserIdByEmailAddress(long companyId, String emailAddress) throws PortalException, SystemException {

		emailAddress = emailAddress.trim().toLowerCase();

		User user = persistence.findByC_EA(companyId, emailAddress);

		return user.getUserId();
	}

	public User loadGetDefaultUser(long companyId) throws PortalException, SystemException {

		return persistence.findByC_DU(companyId, true);
	}
	
	public List<Role> getRoles(long pk, int start, int end, OrderByComparator orderByComparator)
			throws SystemException{
		return persistence.getRoles(pk, start, end, orderByComparator);
	}


	/**
	 * Attempts to authenticate the user by their login and password, while using
	 * the AuthPipeline.
	 *
	 * <p>
	 * Authentication type specifies what <code>login</code> contains.The valid
	 * values are:
	 * </p>
	 *
	 * <ul>
	 * <li><code>CompanyConstants.AUTH_TYPE_EA</code> - <code>login</code> is the
	 * user's email address</li>
	 * <li><code>CompanyConstants.AUTH_TYPE_SN</code> - <code>login</code> is the
	 * user's screen name</li>
	 * <li><code>CompanyConstants.AUTH_TYPE_ID</code> - <code>login</code> is the
	 * user's primary key</li>
	 * </ul>
	 *
	 * @param companyId    the primary key of the user's company
	 * @param login        either the user's email address, screen name, or primary
	 *                     key depending on the value of <code>authType</code>
	 * @param password     the user's password
	 * @param authType     the type of authentication to perform
	 * @param headerMap    the header map from the authentication request
	 * @param parameterMap the parameter map from the authentication request
	 * @param resultsMap   the map of authentication results (may be nil). After a
	 *                     succesful authentication the user's primary key will be
	 *                     placed under the key <code>userId</code>.
	 * @return the authentication status. This can be
	 *         {@link com.liferay.portal.security.auth.Authenticator#FAILURE}
	 *         indicating that the user's credentials are invalid,
	 *         {@link com.liferay.portal.security.auth.Authenticator#SUCCESS}
	 *         indicating a successful login, or
	 *         {@link com.liferay.portal.security.auth.Authenticator#DNE} indicating
	 *         that a user with that login does not exist.
	 * @throws PortalException if <code>login</code> or <code>password</code> was
	 *                         <code>null</code>
	 * @throws SystemException if a system exception occurred
	 * @see com.liferay.portal.security.auth.AuthPipeline
	 */
	/*
	protected int authenticate(long companyId, String login, String password, String authType,
			Map<String, String[]> headerMap, Map<String, String[]> parameterMap, Map<String, Object> resultsMap)
			throws PortalException, SystemException {

		if (PropsValues.AUTH_LOGIN_DISABLED) {
			return Authenticator.FAILURE;
		}

		login = login.trim().toLowerCase();

		long userId = GetterUtil.getLong(login);

		// User input validation

		if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
			if (Validator.isNull(login)) {
				throw new UserEmailAddressException();
			}
		} else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
			if (Validator.isNull(login)) {
				throw new UserScreenNameException();
			}
		} else if (authType.equals(CompanyConstants.AUTH_TYPE_ID)) {
			if (Validator.isNull(login)) {
				throw new UserIdException();
			}
		}

		if (Validator.isNull(password)) {
			throw new UserPasswordException(UserPasswordException.PASSWORD_INVALID);
		}

		int authResult = Authenticator.FAILURE;

		// Pre-authentication pipeline

		if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
			authResult = AuthPipeline.authenticateByEmailAddress(PropsKeys.AUTH_PIPELINE_PRE, companyId, login,
					password, headerMap, parameterMap);
		} else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
			authResult = AuthPipeline.authenticateByScreenName(PropsKeys.AUTH_PIPELINE_PRE, companyId, login, password,
					headerMap, parameterMap);
		} else if (authType.equals(CompanyConstants.AUTH_TYPE_ID)) {
			authResult = AuthPipeline.authenticateByUserId(PropsKeys.AUTH_PIPELINE_PRE, companyId, userId, password,
					headerMap, parameterMap);
		}

		// Get user

		User user = null;

		try {
			if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
				user = persistence.findByC_EA(companyId, login);
			} else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
				user = persistence.findByC_SN(companyId, login);
			} else if (authType.equals(CompanyConstants.AUTH_TYPE_ID)) {
				user = persistence.findByC_U(companyId, GetterUtil.getLong(login));
			}
		} catch (NoSuchUserException nsue) {
			return Authenticator.DNE;
		}

		if (user.isDefaultUser()) {
			if (_log.isInfoEnabled()) {
				log.info("Authentication is disabled for the default user");
			}

			return Authenticator.DNE;
		} else if (!user.isActive()) {
			if (_log.isInfoEnabled()) {
				log.info("Authentication is disabled for inactive user " + user.getUserId());
			}

			return Authenticator.FAILURE;
		}

		if (!user.isPasswordEncrypted()) {
			user.setPassword(PwdEncryptor.encrypt(user.getPassword()));
			user.setPasswordEncrypted(true);

			persistence.update(user, false);
		}

		// Check password policy to see if the is account locked out or if the
		// password is expired

		checkLockout(user);

		checkPasswordExpired(user);

		// Authenticate against the User_ table

		if ((authResult == Authenticator.SUCCESS) && PropsValues.AUTH_PIPELINE_ENABLE_LIFERAY_CHECK) {

			boolean authenticated = PwdAuthenticator.authenticate(login, password, user.getPassword());

			if (authenticated) {
				authResult = Authenticator.SUCCESS;
			} else {
				authResult = Authenticator.FAILURE;
			}
		}

		// Post-authentication pipeline

		if (authResult == Authenticator.SUCCESS) {
			if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
				authResult = AuthPipeline.authenticateByEmailAddress(PropsKeys.AUTH_PIPELINE_POST, companyId, login,
						password, headerMap, parameterMap);
			} else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
				authResult = AuthPipeline.authenticateByScreenName(PropsKeys.AUTH_PIPELINE_POST, companyId, login,
						password, headerMap, parameterMap);
			} else if (authType.equals(CompanyConstants.AUTH_TYPE_ID)) {
				authResult = AuthPipeline.authenticateByUserId(PropsKeys.AUTH_PIPELINE_POST, companyId, userId,
						password, headerMap, parameterMap);
			}
		}

		if (authResult == Authenticator.SUCCESS) {
			if (resultsMap != null) {
				resultsMap.put("userId", user.getUserId());
			}

			// Update digest

			boolean updateDigest = true;

			if (PropsValues.AUTH_PIPELINE_ENABLE_LIFERAY_CHECK) {
				if (Validator.isNotNull(user.getDigest())) {
					updateDigest = false;
				}
			}

			if (updateDigest) {
				String digest = user.getDigest(password);

				user.setDigest(digest);

				persistence.update(user, false);
			}
		}

		// Execute code triggered by authentication failure

		if (authResult == Authenticator.FAILURE) {
			try {
				if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
					AuthPipeline.onFailureByEmailAddress(PropsKeys.AUTH_FAILURE, companyId, login, headerMap,
							parameterMap);
				} else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
					AuthPipeline.onFailureByScreenName(PropsKeys.AUTH_FAILURE, companyId, login, headerMap,
							parameterMap);
				} else if (authType.equals(CompanyConstants.AUTH_TYPE_ID)) {
					AuthPipeline.onFailureByUserId(PropsKeys.AUTH_FAILURE, companyId, userId, headerMap, parameterMap);
				}

				try {
					user = persistence.findByPrimaryKey(user.getUserId());
				} catch (NoSuchUserException nsue) {
					return Authenticator.DNE;
				}

				// Let LDAP handle max failure event

				if (!LDAPSettingsUtil.isPasswordPolicyEnabled(user.getCompanyId())) {

					PasswordPolicy passwordPolicy = user.getPasswordPolicy();

					int failedLoginAttempts = user.getFailedLoginAttempts();
					int maxFailures = passwordPolicy.getMaxFailure();

					if ((failedLoginAttempts >= maxFailures) && (maxFailures != 0)) {

						if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
							AuthPipeline.onMaxFailuresByEmailAddress(PropsKeys.AUTH_MAX_FAILURES, companyId, login,
									headerMap, parameterMap);
						} else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {

							AuthPipeline.onMaxFailuresByScreenName(PropsKeys.AUTH_MAX_FAILURES, companyId, login,
									headerMap, parameterMap);
						} else if (authType.equals(CompanyConstants.AUTH_TYPE_ID)) {

							AuthPipeline.onMaxFailuresByUserId(PropsKeys.AUTH_MAX_FAILURES, companyId, userId,
									headerMap, parameterMap);
						}
					}
				}
			} catch (Exception e) {
				_log.error(e, e);
			}
		}

		return authResult;
	}
	*/

	protected long[] getUserIds(List<User> users) {
		long[] userIds = new long[users.size()];

		for (int i = 0; i < users.size(); i++) {
			User user = users.get(i);

			userIds[i] = user.getUserId();
		}

		return userIds;
	}

	




	private static Map<Long, User> _defaultUsers = new ConcurrentHashMap<Long, User>();

}