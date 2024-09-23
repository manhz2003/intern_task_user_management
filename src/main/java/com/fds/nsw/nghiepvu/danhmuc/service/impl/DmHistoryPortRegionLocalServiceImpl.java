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

import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmHistoryPortRegionPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import lombok.extern.slf4j.Slf4j;@Slf4j @Service






/**
 * The implementation of the dm history port region local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link vn.gt.dao.danhmuc.service.DmHistoryPortRegionLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.danhmuc.service.base.DmHistoryPortRegionLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.DmHistoryPortRegionLocalServiceUtil
 */
public class DmHistoryPortRegionLocalServiceImpl
	{ @Autowired
	DmHistoryPortRegionPersistenceImpl persistence;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link vn.gt.dao.danhmuc.service.DmHistoryPortRegionLocalServiceUtil} to access the dm history port region local service.
	 */
	public DmHistoryPortRegion findByPortRegionCodeAndSyncVersion(
			String portRegionCode, String syncVersion) {
		try {
			return persistence.findByPortRegionCodeAndSyncVersion(portRegionCode, syncVersion);
		} catch (NoSuchDmHistoryPortRegionException e) {
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return null;
	}

		/*
		 * NOTE FOR DEVELOPERS:
		 *
		 * Never modify or reference this class directly. Always use {@link vn.gt.dao.danhmuc.service.DmHistoryPortRegionLocalServiceUtil} to access the dm history port region local service.
		 */

		/**
		 * Adds the dm history port region to the database. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryPortRegion the dm history port region
		 * @return the dm history port region that was added
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryPortRegion addDmHistoryPortRegion(
				DmHistoryPortRegion dmHistoryPortRegion) throws SystemException {

			dmHistoryPortRegion = persistence.updateImpl(dmHistoryPortRegion,
					false);





			return dmHistoryPortRegion;
		}

		/**
		 * Creates a new dm history port region with the primary key. Does not add the dm history port region to the database.
		 *
		 * @param id the primary key for the new dm history port region
		 * @return the new dm history port region
		 */
		public DmHistoryPortRegion createDmHistoryPortRegion(int id) {
			return persistence.create(id);
		}

		/**
		 * Deletes the dm history port region with the primary key from the database. Also notifies the appropriate model listeners.
		 *
		 * @param id the primary key of the dm history port region
		 * @throws PortalException if a dm history port region with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteDmHistoryPortRegion(int id)
				throws PortalException, SystemException {
			DmHistoryPortRegion dmHistoryPortRegion = persistence.remove(id);




		}

		/**
		 * Deletes the dm history port region from the database. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryPortRegion the dm history port region
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteDmHistoryPortRegion(
				DmHistoryPortRegion dmHistoryPortRegion) throws SystemException {
			persistence.remove(dmHistoryPortRegion);




		}













		public DmHistoryPortRegion fetchDmHistoryPortRegion(int id)
				throws SystemException {
			return persistence.fetchByPrimaryKey(id);
		}

		/**
		 * Returns the dm history port region with the primary key.
		 *
		 * @param id the primary key of the dm history port region
		 * @return the dm history port region
		 * @throws PortalException if a dm history port region with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryPortRegion getDmHistoryPortRegion(int id)
				throws PortalException, SystemException {
			return persistence.findByPrimaryKey(id);
		}

		/**
		 * Returns a range of all the dm history port regions.
		 *
		 * <p>
		 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
		 * </p>
		 *
		 * @param start the lower bound of the range of dm history port regions
		 * @param end the upper bound of the range of dm history port regions (not inclusive)
		 * @return the range of dm history port regions
		 * @throws SystemException if a system exception occurred
		 */
		public List<DmHistoryPortRegion> getDmHistoryPortRegions(int start, int end)
				throws SystemException {
			return persistence.findAll(start, end);
		}

		/**
		 * Returns the number of dm history port regions.
		 *
		 * @return the number of dm history port regions
		 * @throws SystemException if a system exception occurred
		 */
		public int getDmHistoryPortRegionsCount() throws SystemException {
			return persistence.countAll();
		}

		/**
		 * Updates the dm history port region in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryPortRegion the dm history port region
		 * @return the dm history port region that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryPortRegion updateDmHistoryPortRegion(
				DmHistoryPortRegion dmHistoryPortRegion) throws SystemException {
			return updateDmHistoryPortRegion(dmHistoryPortRegion, true);
		}

		/**
		 * Updates the dm history port region in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryPortRegion the dm history port region
		 * @param merge whether to merge the dm history port region with the current session. See  for an explanation.
		 * @return the dm history port region that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryPortRegion updateDmHistoryPortRegion(
				DmHistoryPortRegion dmHistoryPortRegion, boolean merge)
				throws SystemException {

			dmHistoryPortRegion = persistence.updateImpl(dmHistoryPortRegion,
					merge);





			return dmHistoryPortRegion;
		}
}