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

import com.fds.nsw.nghiepvu.danhmucgt.service.finder.DmGTShipPositionFinderImpl;
import com.fds.nsw.nghiepvu.danhmucgt.service.persistence.DmGtShipPositionPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;import lombok.extern.slf4j.Slf4j;@Slf4j @Service



/**
 * The implementation of the dm g t ship position local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link vn.gt.dao.danhmucgt.service.DmGTShipPositionLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.danhmucgt.service.base.DmGTShipPositionLocalServiceBaseImpl
 * @see vn.gt.dao.danhmucgt.service.DmGTShipPositionLocalServiceUtil
 */
public class DmGTShipPositionLocalServiceImpl
	{ @Autowired
	DmGtShipPositionPersistenceImpl persistence;
		
		@Autowired
		DmGTShipPositionFinderImpl finder;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link vn.gt.dao.danhmucgt.service.DmGTShipPositionLocalServiceUtil} to access the dm g t ship position local service.
	 */
	
	public java.util.List<DmGtShipPosition> findByPositionCode(
			java.lang.String positionCode){
		try{
			return persistence.findByPositionCode(positionCode);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public int countByPositionCode(java.lang.String positionCode){
		try{
			
			return persistence.countByPositionCode(positionCode);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public DmGtShipPosition getByPositionCode(String positionCode) {
		try {
			List<DmGtShipPosition> dmGTShipPositions = persistence.findByPositionCode(positionCode);
			if (dmGTShipPositions != null && dmGTShipPositions.size() > 0) { return dmGTShipPositions.get(0); }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public java.util.List<DmGtShipPosition> findByRoleAndThuTuc(
			java.lang.String positionCode)
			throws SystemException{
		return finder.findByRoleAndThuTuc(positionCode);
	}

		/*
		 * NOTE FOR DEVELOPERS:
		 *
		 * Never modify or reference this class directly. Always use {@link vn.gt.dao.danhmucgt.service.DmGTShipPositionLocalServiceUtil} to access the dm g t ship position local service.
		 */

		/**
		 * Adds the dm g t ship position to the database. Also notifies the appropriate model listeners.
		 *
		 * @param dmGTShipPosition the dm g t ship position
		 * @return the dm g t ship position that was added
		 * @throws SystemException if a system exception occurred
		 */
		public DmGtShipPosition addDmGTShipPosition(
				DmGtShipPosition dmGTShipPosition) throws SystemException {

			dmGTShipPosition = persistence.updateImpl(dmGTShipPosition,
					false);





			return dmGTShipPosition;
		}

		/**
		 * Creates a new dm g t ship position with the primary key. Does not add the dm g t ship position to the database.
		 *
		 * @param id the primary key for the new dm g t ship position
		 * @return the new dm g t ship position
		 */
		public DmGtShipPosition createDmGTShipPosition(long id) {
			return persistence.create(id);
		}

		/**
		 * Deletes the dm g t ship position with the primary key from the database. Also notifies the appropriate model listeners.
		 *
		 * @param id the primary key of the dm g t ship position
		 * @throws PortalException if a dm g t ship position with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteDmGTShipPosition(long id)
				throws PortalException, SystemException {
			DmGtShipPosition dmGTShipPosition = persistence.remove(id);




		}

		/**
		 * Deletes the dm g t ship position from the database. Also notifies the appropriate model listeners.
		 *
		 * @param dmGTShipPosition the dm g t ship position
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteDmGTShipPosition(DmGtShipPosition dmGTShipPosition)
				throws SystemException {
			persistence.remove(dmGTShipPosition);




		}













		public DmGtShipPosition fetchDmGTShipPosition(long id)
				throws SystemException {
			return persistence.fetchByPrimaryKey(id);
		}

		/**
		 * Returns the dm g t ship position with the primary key.
		 *
		 * @param id the primary key of the dm g t ship position
		 * @return the dm g t ship position
		 * @throws PortalException if a dm g t ship position with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public DmGtShipPosition getDmGTShipPosition(long id)
				throws PortalException, SystemException {
			return persistence.findByPrimaryKey(id);
		}


		/**
		 * Returns a range of all the dm g t ship positions.
		 *
		 * <p>
		 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
		 * </p>
		 *
		 * @param start the lower bound of the range of dm g t ship positions
		 * @param end the upper bound of the range of dm g t ship positions (not inclusive)
		 * @return the range of dm g t ship positions
		 * @throws SystemException if a system exception occurred
		 */
		public List<DmGtShipPosition> getDmGTShipPositions(int start, int end)
				throws SystemException {
			return persistence.findAll(start, end);
		}

		/**
		 * Returns the number of dm g t ship positions.
		 *
		 * @return the number of dm g t ship positions
		 * @throws SystemException if a system exception occurred
		 */
		public int getDmGTShipPositionsCount() throws SystemException {
			return persistence.countAll();
		}

		/**
		 * Updates the dm g t ship position in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param dmGTShipPosition the dm g t ship position
		 * @return the dm g t ship position that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public DmGtShipPosition updateDmGTShipPosition(
				DmGtShipPosition dmGTShipPosition) throws SystemException {
			return updateDmGTShipPosition(dmGTShipPosition, true);
		}

		/**
		 * Updates the dm g t ship position in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param dmGTShipPosition the dm g t ship position
		 * @param merge whether to merge the dm g t ship position with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
		 * @return the dm g t ship position that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public DmGtShipPosition updateDmGTShipPosition(
				DmGtShipPosition dmGTShipPosition, boolean merge)
				throws SystemException {

			dmGTShipPosition = persistence.updateImpl(dmGTShipPosition,
					merge);





			return dmGTShipPosition;
		}
}