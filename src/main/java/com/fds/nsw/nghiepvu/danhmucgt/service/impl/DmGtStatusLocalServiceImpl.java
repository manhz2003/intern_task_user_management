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

import com.fds.nsw.nghiepvu.danhmucgt.service.finder.DmGtStatusFinderImpl;
import com.fds.nsw.nghiepvu.danhmucgt.service.persistence.DmGtStatusPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;import lombok.extern.slf4j.Slf4j;@Slf4j @Service



/**
 * The implementation of the dm gt status local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link vn.gt.dao.danhmucgt.service.DmGtStatusLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.danhmucgt.service.base.DmGtStatusLocalServiceBaseImpl
 * @see vn.gt.dao.danhmucgt.service.dmGtStatusLocalService
 */
public class DmGtStatusLocalServiceImpl { @Autowired
DmGtStatusPersistenceImpl persistence;@Autowired
DmGtStatusFinderImpl finder;
	/*
	 * NOTE FOR DEVELOPERS:
	 * 
	 * Never reference this interface directly. Always use {@link
	 * vn.gt.dao.danhmucgt.service.dmGtStatusLocalService} to access the dm
	 * gt status local service.
	 */

	public DmGtStatus findByStatusCode(int statusCode, int type) {
		try {
			List<DmGtStatus> dmGtStatus = persistence.findByStatusCode(statusCode, type);
			if (dmGtStatus != null && dmGtStatus.size() > 0) {
				return dmGtStatus.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String findNameByStatusCode(int statusCode, int type) {
		try {

			List<DmGtStatus> dmGtStatus = persistence.findByStatusCode(statusCode, type);
			if (dmGtStatus != null && dmGtStatus.size() > 0) {
				return dmGtStatus.get(0).getStatusName();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public int countByStatusCode(int statusCode, int type) {
		try {
			return persistence.countByStatusCode(statusCode, type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<DmGtStatus> findByType(int type) {
		try {
			return finder.findByType(type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<DmGtStatus>();
	}

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link vn.gt.dao.danhmucgt.service.dmGtStatusLocalService} to access the dm gt status local service.
	 */

	/**
	 * Adds the dm gt status to the database. Also notifies the appropriate model listeners.
	 *
	 * @param dmGtStatus the dm gt status
	 * @return the dm gt status that was added
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtStatus addDmGtStatus(DmGtStatus dmGtStatus)
			throws SystemException {

		dmGtStatus = persistence.updateImpl(dmGtStatus, false);





		return dmGtStatus;
	}

	/**
	 * Creates a new dm gt status with the primary key. Does not add the dm gt status to the database.
	 *
	 * @param id the primary key for the new dm gt status
	 * @return the new dm gt status
	 */
	public DmGtStatus createDmGtStatus(long id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the dm gt status with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm gt status
	 * @throws PortalException if a dm gt status with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteDmGtStatus(long id)
			throws PortalException, SystemException {
		DmGtStatus dmGtStatus = persistence.remove(id);




	}

	/**
	 * Deletes the dm gt status from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dmGtStatus the dm gt status
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteDmGtStatus(DmGtStatus dmGtStatus)
			throws SystemException {
		persistence.remove(dmGtStatus);




	}













	public DmGtStatus fetchDmGtStatus(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the dm gt status with the primary key.
	 *
	 * @param id the primary key of the dm gt status
	 * @return the dm gt status
	 * @throws PortalException if a dm gt status with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtStatus getDmGtStatus(long id)
			throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}



	/**
	 * Returns a range of all the dm gt statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm gt statuses
	 * @param end the upper bound of the range of dm gt statuses (not inclusive)
	 * @return the range of dm gt statuses
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtStatus> getDmGtStatuses(int start, int end)
			throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of dm gt statuses.
	 *
	 * @return the number of dm gt statuses
	 * @throws SystemException if a system exception occurred
	 */
	public int getDmGtStatusesCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the dm gt status in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param dmGtStatus the dm gt status
	 * @return the dm gt status that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtStatus updateDmGtStatus(DmGtStatus dmGtStatus)
			throws SystemException {
		return updateDmGtStatus(dmGtStatus, true);
	}

	/**
	 * Updates the dm gt status in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param dmGtStatus the dm gt status
	 * @param merge whether to merge the dm gt status with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	 * @return the dm gt status that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtStatus updateDmGtStatus(DmGtStatus dmGtStatus, boolean merge)
			throws SystemException {

		dmGtStatus = persistence.updateImpl(dmGtStatus, merge);





		return dmGtStatus;
	}
}