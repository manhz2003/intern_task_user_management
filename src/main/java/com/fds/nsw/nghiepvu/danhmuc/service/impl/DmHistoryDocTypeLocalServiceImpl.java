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

import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmHistoryDocTypePersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import lombok.extern.slf4j.Slf4j;@Slf4j @Service







/**
 * The implementation of the dm history doc type local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link vn.gt.dao.danhmuc.service.DmHistoryDocTypeLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.danhmuc.service.base.DmHistoryDocTypeLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.DmHistoryDocTypeLocalServiceUtil
 */
public class DmHistoryDocTypeLocalServiceImpl
	{ @Autowired
	DmHistoryDocTypePersistenceImpl persistence;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link vn.gt.dao.danhmuc.service.DmHistoryDocTypeLocalServiceUtil} to access the dm history doc type local service.
	 */
	public DmHistoryDocType findByDocumentType(String documentType) {
		try {
			return persistence.findByDocumentType(documentType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public DmHistoryDocType findByDocumentTypeAndSyncVersion(
			String documentType, String syncVersion) {
		try {
			return persistence.findByDocumentTypeAndSyncVersion(documentType, syncVersion);
		} catch (NoSuchDmHistoryDocTypeException e) {
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return null;
	}

		/*
		 * NOTE FOR DEVELOPERS:
		 *
		 * Never modify or reference this class directly. Always use {@link vn.gt.dao.danhmuc.service.DmHistoryDocTypeLocalServiceUtil} to access the dm history doc type local service.
		 */

		/**
		 * Adds the dm history doc type to the database. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryDocType the dm history doc type
		 * @return the dm history doc type that was added
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryDocType addDmHistoryDocType(
				DmHistoryDocType dmHistoryDocType) throws SystemException {

			dmHistoryDocType = persistence.updateImpl(dmHistoryDocType,
					false);





			return dmHistoryDocType;
		}

		/**
		 * Creates a new dm history doc type with the primary key. Does not add the dm history doc type to the database.
		 *
		 * @param id the primary key for the new dm history doc type
		 * @return the new dm history doc type
		 */
		public DmHistoryDocType createDmHistoryDocType(int id) {
			return persistence.create(id);
		}

		/**
		 * Deletes the dm history doc type with the primary key from the database. Also notifies the appropriate model listeners.
		 *
		 * @param id the primary key of the dm history doc type
		 * @throws PortalException if a dm history doc type with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteDmHistoryDocType(int id)
				throws PortalException, SystemException {
			DmHistoryDocType dmHistoryDocType = persistence.remove(id);




		}

		/**
		 * Deletes the dm history doc type from the database. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryDocType the dm history doc type
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteDmHistoryDocType(DmHistoryDocType dmHistoryDocType)
				throws SystemException {
			persistence.remove(dmHistoryDocType);




		}













		public DmHistoryDocType fetchDmHistoryDocType(int id)
				throws SystemException {
			return persistence.fetchByPrimaryKey(id);
		}

		/**
		 * Returns the dm history doc type with the primary key.
		 *
		 * @param id the primary key of the dm history doc type
		 * @return the dm history doc type
		 * @throws PortalException if a dm history doc type with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryDocType getDmHistoryDocType(int id)
				throws PortalException, SystemException {
			return persistence.findByPrimaryKey(id);
		}

		/**
		 * Returns a range of all the dm history doc types.
		 *
		 * <p>
		 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
		 * </p>
		 *
		 * @param start the lower bound of the range of dm history doc types
		 * @param end the upper bound of the range of dm history doc types (not inclusive)
		 * @return the range of dm history doc types
		 * @throws SystemException if a system exception occurred
		 */
		public List<DmHistoryDocType> getDmHistoryDocTypes(int start, int end)
				throws SystemException {
			return persistence.findAll(start, end);
		}

		/**
		 * Returns the number of dm history doc types.
		 *
		 * @return the number of dm history doc types
		 * @throws SystemException if a system exception occurred
		 */
		public int getDmHistoryDocTypesCount() throws SystemException {
			return persistence.countAll();
		}

		/**
		 * Updates the dm history doc type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryDocType the dm history doc type
		 * @return the dm history doc type that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryDocType updateDmHistoryDocType(
				DmHistoryDocType dmHistoryDocType) throws SystemException {
			return updateDmHistoryDocType(dmHistoryDocType, true);
		}

		/**
		 * Updates the dm history doc type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryDocType the dm history doc type
		 * @param merge whether to merge the dm history doc type with the current session. See  for an explanation.
		 * @return the dm history doc type that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryDocType updateDmHistoryDocType(
				DmHistoryDocType dmHistoryDocType, boolean merge)
				throws SystemException {

			dmHistoryDocType = persistence.updateImpl(dmHistoryDocType,
					merge);





			return dmHistoryDocType;
		}
}