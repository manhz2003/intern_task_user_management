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

package com.fds.nsw.nghiepvu.danhmuc.service.impl;import java.util.ArrayList; import java.util.List;import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;

import org.springframework.stereotype.Service;

import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmHistoryRepresentativePersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import lombok.extern.slf4j.Slf4j;
@Slf4j @Service








/**
 * The implementation of the dm history representative local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link vn.gt.dao.danhmuc.service.DmHistoryRepresentativeLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.danhmuc.service.base.DmHistoryRepresentativeLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.DmHistoryRepresentativeLocalServiceUtil
 */
public class DmHistoryRepresentativeLocalServiceImpl { @Autowired
DmHistoryRepresentativePersistenceImpl persistence;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link vn.gt.dao.danhmuc.service.DmHistoryRepresentativeLocalServiceUtil} to access the dm history representative local service.
	 */

	public DmHistoryRepresentative getByRepCode(String repCode) {
		try {
			return persistence.findByRepCode(repCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public DmHistoryRepresentative getByRepCodeAndSyncVersion(String syncVersion, String repCode) {
		try {
			return persistence.findByRepCodeAndSyncVersion(syncVersion, repCode);
		} catch (Exception e) {
			log.info("No DmHistoryRepresentative exists with the key {repCode=" + repCode + ", syncVersion=" + syncVersion +"}");
		}
		return null;
	}

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link vn.gt.dao.danhmuc.service.DmHistoryRepresentativeLocalServiceUtil} to access the dm history representative local service.
	 */

	/**
	 * Adds the dm history representative to the database. Also notifies the appropriate model listeners.
	 *
	 * @param dmHistoryRepresentative the dm history representative
	 * @return the dm history representative that was added
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryRepresentative addDmHistoryRepresentative(
			DmHistoryRepresentative dmHistoryRepresentative)
			throws SystemException {

		dmHistoryRepresentative = persistence.updateImpl(dmHistoryRepresentative,
				false);





		return dmHistoryRepresentative;
	}

	/**
	 * Creates a new dm history representative with the primary key. Does not add the dm history representative to the database.
	 *
	 * @param id the primary key for the new dm history representative
	 * @return the new dm history representative
	 */
	public DmHistoryRepresentative createDmHistoryRepresentative(int id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the dm history representative with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm history representative
	 * @throws PortalException if a dm history representative with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteDmHistoryRepresentative(int id)
			throws PortalException, SystemException {
		DmHistoryRepresentative dmHistoryRepresentative = persistence.remove(id);




	}

	/**
	 * Deletes the dm history representative from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dmHistoryRepresentative the dm history representative
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteDmHistoryRepresentative(
			DmHistoryRepresentative dmHistoryRepresentative)
			throws SystemException {
		persistence.remove(dmHistoryRepresentative);




	}


	public DmHistoryRepresentative fetchDmHistoryRepresentative(int id)
			throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the dm history representative with the primary key.
	 *
	 * @param id the primary key of the dm history representative
	 * @return the dm history representative
	 * @throws PortalException if a dm history representative with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryRepresentative getDmHistoryRepresentative(int id)
			throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}


	/**
	 * Returns a range of all the dm history representatives.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history representatives
	 * @param end   the upper bound of the range of dm history representatives (not inclusive)
	 * @return the range of dm history representatives
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryRepresentative> getDmHistoryRepresentatives(
			int start, int end) throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of dm history representatives.
	 *
	 * @return the number of dm history representatives
	 * @throws SystemException if a system exception occurred
	 */
	public int getDmHistoryRepresentativesCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the dm history representative in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param dmHistoryRepresentative the dm history representative
	 * @return the dm history representative that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryRepresentative updateDmHistoryRepresentative(
			DmHistoryRepresentative dmHistoryRepresentative)
			throws SystemException {
		return updateDmHistoryRepresentative(dmHistoryRepresentative, true);
	}

	/**
	 * Updates the dm history representative in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param dmHistoryRepresentative the dm history representative
	 * @param merge                   whether to merge the dm history representative with the current session. See  for an explanation.
	 * @return the dm history representative that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryRepresentative updateDmHistoryRepresentative(
			DmHistoryRepresentative dmHistoryRepresentative, boolean merge)
			throws SystemException {

		dmHistoryRepresentative = persistence.updateImpl(dmHistoryRepresentative,
				merge);





		return dmHistoryRepresentative;
	}

}