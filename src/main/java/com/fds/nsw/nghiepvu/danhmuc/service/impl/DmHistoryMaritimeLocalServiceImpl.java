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

import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;

import org.springframework.stereotype.Service;

import com.fds.nsw.nghiepvu.danhmuc.service.finder.DmHistoryMaritimeFinderImpl;
import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmHistoryMaritimePersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import lombok.extern.slf4j.Slf4j;@Slf4j @Service









/**
 * The implementation of the dm history maritime local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link vn.gt.dao.danhmuc.service.DmHistoryMaritimeLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.danhmuc.service.base.DmHistoryMaritimeLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.DmHistoryMaritimeLocalServiceUtil
 */
public class DmHistoryMaritimeLocalServiceImpl
	{ @Autowired
	DmHistoryMaritimePersistenceImpl persistence;

		@Autowired
		DmHistoryMaritimeFinderImpl finder;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link vn.gt.dao.danhmuc.service.DmHistoryMaritimeLocalServiceUtil} to access the dm history maritime local service.
	 */

		/*
		 * NOTE FOR DEVELOPERS:
		 *
		 * Never modify or reference this class directly. Always use {@link vn.gt.dao.danhmuc.service.DmHistoryMaritimeLocalServiceUtil} to access the dm history maritime local service.
		 */

		/**
		 * Adds the dm history maritime to the database. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryMaritime the dm history maritime
		 * @return the dm history maritime that was added
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryMaritime addDmHistoryMaritime(
				DmHistoryMaritime dmHistoryMaritime) throws SystemException {

			dmHistoryMaritime = persistence.updateImpl(dmHistoryMaritime,
					false);





			return dmHistoryMaritime;
		}

		/**
		 * Creates a new dm history maritime with the primary key. Does not add the dm history maritime to the database.
		 *
		 * @param id the primary key for the new dm history maritime
		 * @return the new dm history maritime
		 */
		public DmHistoryMaritime createDmHistoryMaritime(int id) {
			return persistence.create(id);
		}

		/**
		 * Deletes the dm history maritime with the primary key from the database. Also notifies the appropriate model listeners.
		 *
		 * @param id the primary key of the dm history maritime
		 * @throws PortalException if a dm history maritime with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteDmHistoryMaritime(int id)
				throws PortalException, SystemException {
			DmHistoryMaritime dmHistoryMaritime = persistence.remove(id);




		}

		/**
		 * Deletes the dm history maritime from the database. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryMaritime the dm history maritime
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteDmHistoryMaritime(DmHistoryMaritime dmHistoryMaritime)
				throws SystemException {
			persistence.remove(dmHistoryMaritime);




		}













		public DmHistoryMaritime fetchDmHistoryMaritime(int id)
				throws SystemException {
			return persistence.fetchByPrimaryKey(id);
		}

		/**
		 * Returns the dm history maritime with the primary key.
		 *
		 * @param id the primary key of the dm history maritime
		 * @return the dm history maritime
		 * @throws PortalException if a dm history maritime with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryMaritime getDmHistoryMaritime(int id)
				throws PortalException, SystemException {
			return persistence.findByPrimaryKey(id);
		}


		/**
		 * Returns a range of all the dm history maritimes.
		 *
		 * <p>
		 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
		 * </p>
		 *
		 * @param start the lower bound of the range of dm history maritimes
		 * @param end the upper bound of the range of dm history maritimes (not inclusive)
		 * @return the range of dm history maritimes
		 * @throws SystemException if a system exception occurred
		 */
		public List<DmHistoryMaritime> getDmHistoryMaritimes(int start, int end)
				throws SystemException {
			return persistence.findAll(start, end);
		}

		/**
		 * Returns the number of dm history maritimes.
		 *
		 * @return the number of dm history maritimes
		 * @throws SystemException if a system exception occurred
		 */
		public int getDmHistoryMaritimesCount() throws SystemException {
			return persistence.countAll();
		}

		/**
		 * Updates the dm history maritime in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryMaritime the dm history maritime
		 * @return the dm history maritime that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryMaritime updateDmHistoryMaritime(
				DmHistoryMaritime dmHistoryMaritime) throws SystemException {
			return updateDmHistoryMaritime(dmHistoryMaritime, true);
		}

		/**
		 * Updates the dm history maritime in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryMaritime the dm history maritime
		 * @param merge whether to merge the dm history maritime with the current session. See  for an explanation.
		 * @return the dm history maritime that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryMaritime updateDmHistoryMaritime(
				DmHistoryMaritime dmHistoryMaritime, boolean merge)
				throws SystemException {

			dmHistoryMaritime = persistence.updateImpl(dmHistoryMaritime,
					merge);





			return dmHistoryMaritime;
		}
	public DmHistoryMaritime getByMaritimeCode(String maritimeCode) {
		try {
			List<DmHistoryMaritime> dmHistoryMaritimes = persistence.findByMaritimeCode(maritimeCode);
			if (dmHistoryMaritimes != null && dmHistoryMaritimes.size() > 0) { return dmHistoryMaritimes.get(0); }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<DmHistoryMaritime> findAllDmHistoryMaritimeOrderAsc() {

		List<DmHistoryMaritime> result = null;
		
		try {
			result = finder.findAllDmHistoryMaritimeOrderAsc();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public DmHistoryMaritime getByMaritimeCodeAndSyncVersion(String maritimeCode, String syncVersion) {
		try {
			return persistence.findByMaritimeCodeAndSyncVersion(maritimeCode, syncVersion);
		} catch (NoSuchDmHistoryMaritimeException e) {
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return null;
	}
}