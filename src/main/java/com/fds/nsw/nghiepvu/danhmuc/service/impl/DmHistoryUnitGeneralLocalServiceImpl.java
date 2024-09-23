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

import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmHistoryUnitGeneralPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import lombok.extern.slf4j.Slf4j;@Slf4j @Service








/**
 * The implementation of the dm history unit general local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link vn.gt.dao.danhmuc.service.DmHistoryUnitGeneralLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.danhmuc.service.base.DmHistoryUnitGeneralLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.DmHistoryUnitGeneralLocalServiceUtil
 */
public class DmHistoryUnitGeneralLocalServiceImpl
	{ @Autowired
	DmHistoryUnitGeneralPersistenceImpl persistence;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link vn.gt.dao.danhmuc.service.DmHistoryUnitGeneralLocalServiceUtil} to access the dm history unit general local service.
	 */
	public DmHistoryUnitGeneral getByUnitCodeAndSyncVersion(String unitCode, String syncVersion) {
		try {
			return persistence.findByUnitCodeAndSyncVersion(unitCode, syncVersion);
		} catch (Exception e) {
		}
		return new DmHistoryUnitGeneral();
	}

		/*
		 * NOTE FOR DEVELOPERS:
		 *
		 * Never modify or reference this class directly. Always use {@link vn.gt.dao.danhmuc.service.DmHistoryUnitGeneralLocalServiceUtil} to access the dm history unit general local service.
		 */

		/**
		 * Adds the dm history unit general to the database. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryUnitGeneral the dm history unit general
		 * @return the dm history unit general that was added
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryUnitGeneral addDmHistoryUnitGeneral(
				DmHistoryUnitGeneral dmHistoryUnitGeneral) throws SystemException {

			dmHistoryUnitGeneral = persistence.updateImpl(dmHistoryUnitGeneral,
					false);





			return dmHistoryUnitGeneral;
		}

		/**
		 * Creates a new dm history unit general with the primary key. Does not add the dm history unit general to the database.
		 *
		 * @param id the primary key for the new dm history unit general
		 * @return the new dm history unit general
		 */
		public DmHistoryUnitGeneral createDmHistoryUnitGeneral(int id) {
			return persistence.create(id);
		}

		/**
		 * Deletes the dm history unit general with the primary key from the database. Also notifies the appropriate model listeners.
		 *
		 * @param id the primary key of the dm history unit general
		 * @throws PortalException if a dm history unit general with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteDmHistoryUnitGeneral(int id)
				throws PortalException, SystemException {
			DmHistoryUnitGeneral dmHistoryUnitGeneral = persistence.remove(id);




		}

		/**
		 * Deletes the dm history unit general from the database. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryUnitGeneral the dm history unit general
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteDmHistoryUnitGeneral(
				DmHistoryUnitGeneral dmHistoryUnitGeneral) throws SystemException {
			persistence.remove(dmHistoryUnitGeneral);




		}













		public DmHistoryUnitGeneral fetchDmHistoryUnitGeneral(int id)
				throws SystemException {
			return persistence.fetchByPrimaryKey(id);
		}

		/**
		 * Returns the dm history unit general with the primary key.
		 *
		 * @param id the primary key of the dm history unit general
		 * @return the dm history unit general
		 * @throws PortalException if a dm history unit general with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryUnitGeneral getDmHistoryUnitGeneral(int id)
				throws PortalException, SystemException {
			return persistence.findByPrimaryKey(id);
		}


		/**
		 * Returns a range of all the dm history unit generals.
		 *
		 * <p>
		 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
		 * </p>
		 *
		 * @param start the lower bound of the range of dm history unit generals
		 * @param end the upper bound of the range of dm history unit generals (not inclusive)
		 * @return the range of dm history unit generals
		 * @throws SystemException if a system exception occurred
		 */
		public List<DmHistoryUnitGeneral> getDmHistoryUnitGenerals(int start,
																   int end) throws SystemException {
			return persistence.findAll(start, end);
		}

		/**
		 * Returns the number of dm history unit generals.
		 *
		 * @return the number of dm history unit generals
		 * @throws SystemException if a system exception occurred
		 */
		public int getDmHistoryUnitGeneralsCount() throws SystemException {
			return persistence.countAll();
		}

		/**
		 * Updates the dm history unit general in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryUnitGeneral the dm history unit general
		 * @return the dm history unit general that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryUnitGeneral updateDmHistoryUnitGeneral(
				DmHistoryUnitGeneral dmHistoryUnitGeneral) throws SystemException {
			return updateDmHistoryUnitGeneral(dmHistoryUnitGeneral, true);
		}

		/**
		 * Updates the dm history unit general in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryUnitGeneral the dm history unit general
		 * @param merge whether to merge the dm history unit general with the current session. See  for an explanation.
		 * @return the dm history unit general that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryUnitGeneral updateDmHistoryUnitGeneral(
				DmHistoryUnitGeneral dmHistoryUnitGeneral, boolean merge)
				throws SystemException {

			dmHistoryUnitGeneral = persistence.updateImpl(dmHistoryUnitGeneral,
					merge);





			return dmHistoryUnitGeneral;
		}
}