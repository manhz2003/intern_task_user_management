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

import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmHistoryStatePersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import lombok.extern.slf4j.Slf4j;@Slf4j @Service









/**
 * The implementation of the dm history state local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link vn.gt.dao.danhmuc.service.DmHistoryStateLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.danhmuc.service.base.DmHistoryStateLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.DmHistoryStateLocalServiceUtil
 */
public class DmHistoryStateLocalServiceImpl
	{ @Autowired
	DmHistoryStatePersistenceImpl persistence;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link vn.gt.dao.danhmuc.service.DmHistoryStateLocalServiceUtil} to access the dm history state local service.
	 */
	public DmHistoryState getByStateCode(String stateCode) {
		try {
			List<DmHistoryState> dmHistoryStates = persistence.findByStateCode(stateCode);
			if (dmHistoryStates != null && dmHistoryStates.size() > 0) { return dmHistoryStates.get(0); }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public DmHistoryState findByStateCodeAndSyncVersion(String stateCode, String syncVersion) {
		try {
			return persistence.findByStateCodeAndSyncVersion(stateCode, syncVersion);
		} catch (NoSuchDmHistoryStateException e) {
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return null;
	}

		/*
		 * NOTE FOR DEVELOPERS:
		 *
		 * Never modify or reference this class directly. Always use {@link vn.gt.dao.danhmuc.service.DmHistoryStateLocalServiceUtil} to access the dm history state local service.
		 */

		/**
		 * Adds the dm history state to the database. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryState the dm history state
		 * @return the dm history state that was added
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryState addDmHistoryState(DmHistoryState dmHistoryState)
				throws SystemException {

			dmHistoryState = persistence.updateImpl(dmHistoryState, false);





			return dmHistoryState;
		}

		/**
		 * Creates a new dm history state with the primary key. Does not add the dm history state to the database.
		 *
		 * @param id the primary key for the new dm history state
		 * @return the new dm history state
		 */
		public DmHistoryState createDmHistoryState(int id) {
			return persistence.create(id);
		}

		/**
		 * Deletes the dm history state with the primary key from the database. Also notifies the appropriate model listeners.
		 *
		 * @param id the primary key of the dm history state
		 * @throws PortalException if a dm history state with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteDmHistoryState(int id)
				throws PortalException, SystemException {
			DmHistoryState dmHistoryState = persistence.remove(id);




		}

		/**
		 * Deletes the dm history state from the database. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryState the dm history state
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteDmHistoryState(DmHistoryState dmHistoryState)
				throws SystemException {
			persistence.remove(dmHistoryState);




		}













		public DmHistoryState fetchDmHistoryState(int id) throws SystemException {
			return persistence.fetchByPrimaryKey(id);
		}

		/**
		 * Returns the dm history state with the primary key.
		 *
		 * @param id the primary key of the dm history state
		 * @return the dm history state
		 * @throws PortalException if a dm history state with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryState getDmHistoryState(int id)
				throws PortalException, SystemException {
			return persistence.findByPrimaryKey(id);
		}


		/**
		 * Returns a range of all the dm history states.
		 *
		 * <p>
		 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
		 * </p>
		 *
		 * @param start the lower bound of the range of dm history states
		 * @param end the upper bound of the range of dm history states (not inclusive)
		 * @return the range of dm history states
		 * @throws SystemException if a system exception occurred
		 */
		public List<DmHistoryState> getDmHistoryStates(int start, int end)
				throws SystemException {
			return persistence.findAll(start, end);
		}

		/**
		 * Returns the number of dm history states.
		 *
		 * @return the number of dm history states
		 * @throws SystemException if a system exception occurred
		 */
		public int getDmHistoryStatesCount() throws SystemException {
			return persistence.countAll();
		}

		/**
		 * Updates the dm history state in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryState the dm history state
		 * @return the dm history state that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryState updateDmHistoryState(DmHistoryState dmHistoryState)
				throws SystemException {
			return updateDmHistoryState(dmHistoryState, true);
		}

		/**
		 * Updates the dm history state in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryState the dm history state
		 * @param merge whether to merge the dm history state with the current session. See  for an explanation.
		 * @return the dm history state that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryState updateDmHistoryState(DmHistoryState dmHistoryState,
												   boolean merge) throws SystemException {

			dmHistoryState = persistence.updateImpl(dmHistoryState, merge);





			return dmHistoryState;
		}
}