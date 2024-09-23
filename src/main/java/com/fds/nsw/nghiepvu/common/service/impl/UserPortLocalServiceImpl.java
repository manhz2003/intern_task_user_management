/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.fds.nsw.nghiepvu.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;

import com.fds.nsw.nghiepvu.common.service.persistence.UserPortPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.kernel.exception.SystemException;

import lombok.extern.slf4j.Slf4j;
@Slf4j @Service
public class UserPortLocalServiceImpl {
	@Autowired
	UserPortPersistenceImpl persistence;
	/*
	 * NOTE FOR DEVELOPERS: Never reference this interface directly. Always use {@link vn.gt.dao.common.service.UserPortLocalServiceUtil} to access
	 * the user port local service.
	 */
	
	public List<UserPort> findByPortCode(String portCode, int start, int end) {
		try {
			return persistence.findByPortCode(portCode, start, end);
		} catch (Exception e) {
			
		}
		
		return null;
	}
	
	public UserPort findByUserId(long userId) {
		try {
			return persistence.fetchByUserId(userId);
		} catch (SystemException e) {
			
		}
		
		return null;
	}
	
	public int countByPortCode(String portHarbourCode) {
		try {
			return persistence.countByPortCode(portHarbourCode);
		} catch (Exception e) {
		}
		
		return 0;
	}
	
	public List<UserPort> findAll(int start, int end) {
		try {
			return persistence.findAll(start, end);
		} catch (Exception e) {
			
		}
		
		return null;
	}

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link vn.gt.dao.common.service.UserPortLocalServiceUtil} to access the user port local service.
	 */

	/**
	 * Adds the user port to the database. Also notifies the appropriate model listeners.
	 *
	 * @param userPort the user port
	 * @return the user port that was added
	 * @throws SystemException if a system exception occurred
	 */
	public UserPort addUserPort(UserPort userPort) throws SystemException {

		userPort = persistence.updateImpl(userPort, false);





		return userPort;
	}

	/**
	 * Creates a new user port with the primary key. Does not add the user port to the database.
	 *
	 * @param id the primary key for the new user port
	 * @return the new user port
	 */
	public UserPort createUserPort(long id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the user port with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the user port
	 * @throws PortalException if a user port with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteUserPort(long id) throws PortalException, SystemException {
		UserPort userPort = persistence.remove(id);




	}

	/**
	 * Deletes the user port from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userPort the user port
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteUserPort(UserPort userPort) throws SystemException {
		persistence.remove(userPort);
	}

	public List<UserPort> fetchByDepartmentCode(String departmentCode,
												int start, int end) {
		try {
			return persistence.findByF_departmentCode(departmentCode,
					start, end);
		} catch (Exception e) {
			return null;
		}
	}

	public long countByDepartmentCode(String departmentCode) {
		try {
			return persistence.countByF_departmentCode(departmentCode);
		} catch (Exception e) {
			return 0;
		}
	}

	public UserPort fetchUserPort(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the user port with the primary key.
	 *
	 * @param id the primary key of the user port
	 * @return the user port
	 * @throws PortalException if a user port with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserPort getUserPort(long id)
			throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}


	/**
	 * Returns a range of all the user ports.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of user ports
	 * @param end the upper bound of the range of user ports (not inclusive)
	 * @return the range of user ports
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserPort> getUserPorts(int start, int end)
			throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of user ports.
	 *
	 * @return the number of user ports
	 * @throws SystemException if a system exception occurred
	 */
	public int getUserPortsCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the user port in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param userPort the user port
	 * @return the user port that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public UserPort updateUserPort(UserPort userPort) throws SystemException {
		return updateUserPort(userPort, true);
	}

	/**
	 * Updates the user port in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param userPort the user port
	 * @param merge whether to merge the user port with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	 * @return the user port that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public UserPort updateUserPort(UserPort userPort, boolean merge)
			throws SystemException {

		userPort = persistence.updateImpl(userPort, merge);





		return userPort;
	}
}