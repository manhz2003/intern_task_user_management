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

package com.fds.nsw.nghiepvu.danhmuc.service.impl;import java.util.ArrayList; import java.util.List;import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;

import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;

import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmHistoryPassportTypePersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import lombok.extern.slf4j.Slf4j;@Slf4j @Service






/**
 * The implementation of the dm history passport type local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link vn.gt.dao.danhmuc.service.DmHistoryPassportTypeLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.danhmuc.service.base.DmHistoryPassportTypeLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.DmHistoryPassportTypeLocalServiceUtil
 */
public class DmHistoryPassportTypeLocalServiceImpl
	{ @Autowired
	DmHistoryPassportTypePersistenceImpl persistence;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link vn.gt.dao.danhmuc.service.DmHistoryPassportTypeLocalServiceUtil} to access the dm history passport type local service.
	 */
	public DmHistoryPassportType findByPassportTypeCodeAndSyncVersion(
			String passportTypeCode, String syncVersion) {
		try {
			return persistence.findByPassportTypeCodeAndSyncVersion(passportTypeCode, syncVersion);
		} catch (NoSuchDmHistoryPassportTypeException e) {
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return null;
	}

		/*
		 * NOTE FOR DEVELOPERS:
		 *
		 * Never modify or reference this class directly. Always use {@link vn.gt.dao.danhmuc.service.DmHistoryPassportTypeLocalServiceUtil} to access the dm history passport type local service.
		 */

		/**
		 * Adds the dm history passport type to the database. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryPassportType the dm history passport type
		 * @return the dm history passport type that was added
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryPassportType addDmHistoryPassportType(
				DmHistoryPassportType dmHistoryPassportType) throws SystemException {

			dmHistoryPassportType = persistence.updateImpl(dmHistoryPassportType,
					false);





			return dmHistoryPassportType;
		}

		/**
		 * Creates a new dm history passport type with the primary key. Does not add the dm history passport type to the database.
		 *
		 * @param id the primary key for the new dm history passport type
		 * @return the new dm history passport type
		 */
		public DmHistoryPassportType createDmHistoryPassportType(int id) {
			return persistence.create(id);
		}

		/**
		 * Deletes the dm history passport type with the primary key from the database. Also notifies the appropriate model listeners.
		 *
		 * @param id the primary key of the dm history passport type
		 * @throws PortalException if a dm history passport type with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteDmHistoryPassportType(int id)
				throws PortalException, SystemException {
			DmHistoryPassportType dmHistoryPassportType = persistence.remove(id);




		}

		/**
		 * Deletes the dm history passport type from the database. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryPassportType the dm history passport type
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteDmHistoryPassportType(
				DmHistoryPassportType dmHistoryPassportType) throws SystemException {
			persistence.remove(dmHistoryPassportType);




		}













		public DmHistoryPassportType fetchDmHistoryPassportType(int id)
				throws SystemException {
			return persistence.fetchByPrimaryKey(id);
		}

		/**
		 * Returns the dm history passport type with the primary key.
		 *
		 * @param id the primary key of the dm history passport type
		 * @return the dm history passport type
		 * @throws PortalException if a dm history passport type with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryPassportType getDmHistoryPassportType(int id)
				throws PortalException, SystemException {
			return persistence.findByPrimaryKey(id);
		}


		/**
		 * Returns a range of all the dm history passport types.
		 *
		 * <p>
		 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
		 * </p>
		 *
		 * @param start the lower bound of the range of dm history passport types
		 * @param end the upper bound of the range of dm history passport types (not inclusive)
		 * @return the range of dm history passport types
		 * @throws SystemException if a system exception occurred
		 */
		public List<DmHistoryPassportType> getDmHistoryPassportTypes(int start,
																	 int end) throws SystemException {
			return persistence.findAll(start, end);
		}

		/**
		 * Returns the number of dm history passport types.
		 *
		 * @return the number of dm history passport types
		 * @throws SystemException if a system exception occurred
		 */
		public int getDmHistoryPassportTypesCount() throws SystemException {
			return persistence.countAll();
		}

		/**
		 * Updates the dm history passport type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryPassportType the dm history passport type
		 * @return the dm history passport type that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryPassportType updateDmHistoryPassportType(
				DmHistoryPassportType dmHistoryPassportType) throws SystemException {
			return updateDmHistoryPassportType(dmHistoryPassportType, true);
		}

		/**
		 * Updates the dm history passport type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryPassportType the dm history passport type
		 * @param merge whether to merge the dm history passport type with the current session. See  for an explanation.
		 * @return the dm history passport type that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryPassportType updateDmHistoryPassportType(
				DmHistoryPassportType dmHistoryPassportType, boolean merge)
				throws SystemException {

			dmHistoryPassportType = persistence.updateImpl(dmHistoryPassportType,
					merge);





			return dmHistoryPassportType;
		}
}