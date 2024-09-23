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

package com.fds.nsw.nghiepvu.danhmucgt.service.impl;import java.util.ArrayList; import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;

import com.fds.nsw.nghiepvu.danhmucgt.service.persistence.DmGtVersionPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;import lombok.extern.slf4j.Slf4j;@Slf4j @Service



/**
 * The implementation of the dm gt version local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link vn.gt.dao.danhmucgt.service.DmGtVersionLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.danhmucgt.service.base.DmGtVersionLocalServiceBaseImpl
 * @see vn.gt.dao.danhmucgt.service.DmGtVersionLocalServiceUtil
 */
public class DmGtVersionLocalServiceImpl { @Autowired
DmGtVersionPersistenceImpl persistence;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link vn.gt.dao.danhmucgt.service.DmGtVersionLocalServiceUtil} to access the dm gt version local service.
	 */

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link vn.gt.dao.danhmucgt.service.DmGtVersionLocalServiceUtil} to access the dm gt version local service.
	 */

	/**
	 * Adds the dm gt version to the database. Also notifies the appropriate model listeners.
	 *
	 * @param dmGtVersion the dm gt version
	 * @return the dm gt version that was added
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtVersion addDmGtVersion(DmGtVersion dmGtVersion)
			throws SystemException {

		dmGtVersion = persistence.updateImpl(dmGtVersion, false);





		return dmGtVersion;
	}

	/**
	 * Creates a new dm gt version with the primary key. Does not add the dm gt version to the database.
	 *
	 * @param id the primary key for the new dm gt version
	 * @return the new dm gt version
	 */
	public DmGtVersion createDmGtVersion(int id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the dm gt version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm gt version
	 * @throws PortalException if a dm gt version with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteDmGtVersion(int id)
			throws PortalException, SystemException {
		DmGtVersion dmGtVersion = persistence.remove(id);




	}

	/**
	 * Deletes the dm gt version from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dmGtVersion the dm gt version
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteDmGtVersion(DmGtVersion dmGtVersion)
			throws SystemException {
		persistence.remove(dmGtVersion);




	}













	public DmGtVersion fetchDmGtVersion(int id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the dm gt version with the primary key.
	 *
	 * @param id the primary key of the dm gt version
	 * @return the dm gt version
	 * @throws PortalException if a dm gt version with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtVersion getDmGtVersion(int id)
			throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}


	/**
	 * Returns a range of all the dm gt versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm gt versions
	 * @param end the upper bound of the range of dm gt versions (not inclusive)
	 * @return the range of dm gt versions
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtVersion> getDmGtVersions(int start, int end)
			throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of dm gt versions.
	 *
	 * @return the number of dm gt versions
	 * @throws SystemException if a system exception occurred
	 */
	public int getDmGtVersionsCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the dm gt version in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param dmGtVersion the dm gt version
	 * @return the dm gt version that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtVersion updateDmGtVersion(DmGtVersion dmGtVersion)
			throws SystemException {
		return updateDmGtVersion(dmGtVersion, true);
	}

	/**
	 * Updates the dm gt version in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param dmGtVersion the dm gt version
	 * @param merge whether to merge the dm gt version with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	 * @return the dm gt version that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtVersion updateDmGtVersion(DmGtVersion dmGtVersion, boolean merge)
			throws SystemException {

		dmGtVersion = persistence.updateImpl(dmGtVersion, merge);





		return dmGtVersion;
	}
}