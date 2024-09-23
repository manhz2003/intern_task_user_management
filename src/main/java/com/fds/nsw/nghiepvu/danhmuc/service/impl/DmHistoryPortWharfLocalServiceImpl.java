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

import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmHistoryPortWharfPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import lombok.extern.slf4j.Slf4j;@Slf4j @Service






/**
 * The implementation of the dm history port wharf local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link vn.gt.dao.danhmuc.service.DmHistoryPortWharfLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.danhmuc.service.base.DmHistoryPortWharfLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.DmHistoryPortWharfLocalServiceUtil
 */
public class DmHistoryPortWharfLocalServiceImpl
	{ @Autowired
	DmHistoryPortWharfPersistenceImpl persistence;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link vn.gt.dao.danhmuc.service.DmHistoryPortWharfLocalServiceUtil} to access the dm history port wharf local service.
	 */
	public DmHistoryPortWharf findByPortWharfCodeAndSyncVersion(
			String portWharfCode, String syncVersion) {
		try {
			return persistence.findByPortWharfCodeAndSyncVersion(portWharfCode, syncVersion);
		} catch (NoSuchDmHistoryPortWharfException e) {
			//e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return null;
	}

		/*
		 * NOTE FOR DEVELOPERS:
		 *
		 * Never modify or reference this class directly. Always use {@link vn.gt.dao.danhmuc.service.DmHistoryPortWharfLocalServiceUtil} to access the dm history port wharf local service.
		 */

		/**
		 * Adds the dm history port wharf to the database. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryPortWharf the dm history port wharf
		 * @return the dm history port wharf that was added
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryPortWharf addDmHistoryPortWharf(
				DmHistoryPortWharf dmHistoryPortWharf) throws SystemException {

			dmHistoryPortWharf = persistence.updateImpl(dmHistoryPortWharf,
					false);





			return dmHistoryPortWharf;
		}

		/**
		 * Creates a new dm history port wharf with the primary key. Does not add the dm history port wharf to the database.
		 *
		 * @param id the primary key for the new dm history port wharf
		 * @return the new dm history port wharf
		 */
		public DmHistoryPortWharf createDmHistoryPortWharf(int id) {
			return persistence.create(id);
		}

		/**
		 * Deletes the dm history port wharf with the primary key from the database. Also notifies the appropriate model listeners.
		 *
		 * @param id the primary key of the dm history port wharf
		 * @throws PortalException if a dm history port wharf with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteDmHistoryPortWharf(int id)
				throws PortalException, SystemException {
			DmHistoryPortWharf dmHistoryPortWharf = persistence.remove(id);




		}

		/**
		 * Deletes the dm history port wharf from the database. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryPortWharf the dm history port wharf
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteDmHistoryPortWharf(DmHistoryPortWharf dmHistoryPortWharf)
				throws SystemException {
			persistence.remove(dmHistoryPortWharf);




		}













		public DmHistoryPortWharf fetchDmHistoryPortWharf(int id)
				throws SystemException {
			return persistence.fetchByPrimaryKey(id);
		}

		/**
		 * Returns the dm history port wharf with the primary key.
		 *
		 * @param id the primary key of the dm history port wharf
		 * @return the dm history port wharf
		 * @throws PortalException if a dm history port wharf with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryPortWharf getDmHistoryPortWharf(int id)
				throws PortalException, SystemException {
			return persistence.findByPrimaryKey(id);
		}


		/**
		 * Returns a range of all the dm history port wharfs.
		 *
		 * <p>
		 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
		 * </p>
		 *
		 * @param start the lower bound of the range of dm history port wharfs
		 * @param end the upper bound of the range of dm history port wharfs (not inclusive)
		 * @return the range of dm history port wharfs
		 * @throws SystemException if a system exception occurred
		 */
		public List<DmHistoryPortWharf> getDmHistoryPortWharfs(int start, int end)
				throws SystemException {
			return persistence.findAll(start, end);
		}

		/**
		 * Returns the number of dm history port wharfs.
		 *
		 * @return the number of dm history port wharfs
		 * @throws SystemException if a system exception occurred
		 */
		public int getDmHistoryPortWharfsCount() throws SystemException {
			return persistence.countAll();
		}

		/**
		 * Updates the dm history port wharf in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryPortWharf the dm history port wharf
		 * @return the dm history port wharf that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryPortWharf updateDmHistoryPortWharf(
				DmHistoryPortWharf dmHistoryPortWharf) throws SystemException {
			return updateDmHistoryPortWharf(dmHistoryPortWharf, true);
		}

		/**
		 * Updates the dm history port wharf in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryPortWharf the dm history port wharf
		 * @param merge whether to merge the dm history port wharf with the current session. See  for an explanation.
		 * @return the dm history port wharf that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryPortWharf updateDmHistoryPortWharf(
				DmHistoryPortWharf dmHistoryPortWharf, boolean merge)
				throws SystemException {

			dmHistoryPortWharf = persistence.updateImpl(dmHistoryPortWharf,
					merge);





			return dmHistoryPortWharf;
		}
}