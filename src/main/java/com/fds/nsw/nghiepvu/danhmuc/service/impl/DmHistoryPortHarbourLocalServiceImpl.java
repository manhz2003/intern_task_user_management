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

import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmHistoryPortHarbourPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import lombok.extern.slf4j.Slf4j;@Slf4j @Service











/**
 * The implementation of the dm history port harbour local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link vn.gt.dao.danhmuc.service.DmHistoryPortHarbourLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.danhmuc.service.base.DmHistoryPortHarbourLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.DmHistoryPortHarbourLocalServiceUtil
 */
public class DmHistoryPortHarbourLocalServiceImpl
	{ @Autowired
	DmHistoryPortHarbourPersistenceImpl persistence;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link vn.gt.dao.danhmuc.service.DmHistoryPortHarbourLocalServiceUtil} to access the dm history port harbour local service.
	 */
	public DmHistoryPortHarbour findByPortHarbourCodeAndSyncVersion(String portHarbourCode, String syncVersion) {
		try {
			return persistence.findByPortHarbourCodeAndSyncVersion(portHarbourCode, syncVersion);
		} catch (NoSuchDmHistoryPortHarbourException e) {
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<DmHistoryPortHarbour> findByPortRegionCode(String portRegionCode) {
		try {
			return persistence.findByPortRegionCode(portRegionCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<DmHistoryPortHarbour>();
	}

		/*
		 * NOTE FOR DEVELOPERS:
		 *
		 * Never modify or reference this class directly. Always use {@link vn.gt.dao.danhmuc.service.DmHistoryPortHarbourLocalServiceUtil} to access the dm history port harbour local service.
		 */

		/**
		 * Adds the dm history port harbour to the database. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryPortHarbour the dm history port harbour
		 * @return the dm history port harbour that was added
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryPortHarbour addDmHistoryPortHarbour(
				DmHistoryPortHarbour dmHistoryPortHarbour) throws SystemException {

			dmHistoryPortHarbour = persistence.updateImpl(dmHistoryPortHarbour,
					false);





			return dmHistoryPortHarbour;
		}

		/**
		 * Creates a new dm history port harbour with the primary key. Does not add the dm history port harbour to the database.
		 *
		 * @param id the primary key for the new dm history port harbour
		 * @return the new dm history port harbour
		 */
		public DmHistoryPortHarbour createDmHistoryPortHarbour(int id) {
			return persistence.create(id);
		}

		/**
		 * Deletes the dm history port harbour with the primary key from the database. Also notifies the appropriate model listeners.
		 *
		 * @param id the primary key of the dm history port harbour
		 * @throws PortalException if a dm history port harbour with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteDmHistoryPortHarbour(int id)
				throws PortalException, SystemException {
			DmHistoryPortHarbour dmHistoryPortHarbour = persistence.remove(id);




		}

		/**
		 * Deletes the dm history port harbour from the database. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryPortHarbour the dm history port harbour
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteDmHistoryPortHarbour(
				DmHistoryPortHarbour dmHistoryPortHarbour) throws SystemException {
			persistence.remove(dmHistoryPortHarbour);




		}













		public DmHistoryPortHarbour fetchDmHistoryPortHarbour(int id)
				throws SystemException {
			return persistence.fetchByPrimaryKey(id);
		}

		/**
		 * Returns the dm history port harbour with the primary key.
		 *
		 * @param id the primary key of the dm history port harbour
		 * @return the dm history port harbour
		 * @throws PortalException if a dm history port harbour with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryPortHarbour getDmHistoryPortHarbour(int id)
				throws PortalException, SystemException {
			return persistence.findByPrimaryKey(id);
		}


		/**
		 * Returns a range of all the dm history port harbours.
		 *
		 * <p>
		 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
		 * </p>
		 *
		 * @param start the lower bound of the range of dm history port harbours
		 * @param end the upper bound of the range of dm history port harbours (not inclusive)
		 * @return the range of dm history port harbours
		 * @throws SystemException if a system exception occurred
		 */
		public List<DmHistoryPortHarbour> getDmHistoryPortHarbours(int start,
																   int end) throws SystemException {
			return persistence.findAll(start, end);
		}

		/**
		 * Returns the number of dm history port harbours.
		 *
		 * @return the number of dm history port harbours
		 * @throws SystemException if a system exception occurred
		 */
		public int getDmHistoryPortHarboursCount() throws SystemException {
			return persistence.countAll();
		}

		/**
		 * Updates the dm history port harbour in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryPortHarbour the dm history port harbour
		 * @return the dm history port harbour that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryPortHarbour updateDmHistoryPortHarbour(
				DmHistoryPortHarbour dmHistoryPortHarbour) throws SystemException {
			return updateDmHistoryPortHarbour(dmHistoryPortHarbour, true);
		}

		/**
		 * Updates the dm history port harbour in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryPortHarbour the dm history port harbour
		 * @param merge whether to merge the dm history port harbour with the current session. See  for an explanation.
		 * @return the dm history port harbour that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryPortHarbour updateDmHistoryPortHarbour(
				DmHistoryPortHarbour dmHistoryPortHarbour, boolean merge)
				throws SystemException {

			dmHistoryPortHarbour = persistence.updateImpl(dmHistoryPortHarbour,
					merge);





			return dmHistoryPortHarbour;
		}
}