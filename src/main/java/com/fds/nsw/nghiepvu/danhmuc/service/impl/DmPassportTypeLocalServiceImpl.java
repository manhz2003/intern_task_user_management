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

package com.fds.nsw.nghiepvu.danhmuc.service.impl;
import com.fds.nsw.nghiepvu.danhmuc.service.finder.DmPassportTypeFinderImpl;
import java.util.ArrayList; import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;

import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmPassportTypePersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import lombok.extern.slf4j.Slf4j;@Slf4j @Service






/**
 * The implementation of the dm passport type local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link vn.gt.dao.danhmuc.service.DmPassportTypeLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.danhmuc.service.base.DmPassportTypeLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.DmPassportTypeLocalServiceUtil
 */
public class DmPassportTypeLocalServiceImpl
	{ @Autowired
	DmPassportTypePersistenceImpl persistence;

		@Autowired
		DmPassportTypeFinderImpl finder;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link vn.gt.dao.danhmuc.service.DmPassportTypeLocalServiceUtil} to access the dm passport type local service.
	 */
	public DmPassportType getByPassportTypeCode(String passportTypeCode) {
		try {
			List<DmPassportType> dmPassportTypes = persistence.findByPassportTypeCode(passportTypeCode);
			if (dmPassportTypes != null && dmPassportTypes.size() > 0) { return dmPassportTypes.get(0); }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

		public List<DmPassportType> findByPassportNameVN(String passportTypeNameVN,
														 int start, int end) {
			try {
				return persistence.findByF_passportTypeNameVN(
						passportTypeNameVN, start, end);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

		public List<DmPassportType> findPassportTypes(String passportTypeNameVN,
													  String isDelete, String passportTypeCodeGroup, int start, int end) {
			try {
				return finder.findPassportTypes(passportTypeNameVN,
						isDelete, passportTypeCodeGroup, start, end);
			} catch (Exception e) {
				log.error(e.getMessage());
				return null;
			}
		}

		public long countPassportTypes(String passportTypeNameVN, String isDelete,
									   String passportTypeCodeGroup, int start, int end) {
			try {
				return finder.countPassportTypes(passportTypeNameVN,
						isDelete, passportTypeCodeGroup);
			} catch (Exception e) {
				log.error(e.getMessage());
				return 0;
			}
		}

		
		/*
		 * NOTE FOR DEVELOPERS:
		 *
		 * Never modify or reference this class directly. Always use {@link vn.gt.dao.danhmuc.service.DmPassportTypeLocalServiceUtil} to access the dm passport type local service.
		 */

		/**
		 * Adds the dm passport type to the database. Also notifies the appropriate model listeners.
		 *
		 * @param dmPassportType the dm passport type
		 * @return the dm passport type that was added
		 * @throws SystemException if a system exception occurred
		 */
		public DmPassportType addDmPassportType(DmPassportType dmPassportType)
				throws SystemException {

			dmPassportType = persistence.updateImpl(dmPassportType, false);





			return dmPassportType;
		}

		/**
		 * Creates a new dm passport type with the primary key. Does not add the dm passport type to the database.
		 *
		 * @param id the primary key for the new dm passport type
		 * @return the new dm passport type
		 */
		public DmPassportType createDmPassportType(int id) {
			return persistence.create(id);
		}

		/**
		 * Deletes the dm passport type with the primary key from the database. Also notifies the appropriate model listeners.
		 *
		 * @param id the primary key of the dm passport type
		 * @throws PortalException if a dm passport type with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteDmPassportType(int id)
				throws PortalException, SystemException {
			DmPassportType dmPassportType = persistence.remove(id);




		}

		/**
		 * Deletes the dm passport type from the database. Also notifies the appropriate model listeners.
		 *
		 * @param dmPassportType the dm passport type
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteDmPassportType(DmPassportType dmPassportType)
				throws SystemException {
			persistence.remove(dmPassportType);




		}













		public DmPassportType fetchDmPassportType(int id) throws SystemException {
			return persistence.fetchByPrimaryKey(id);
		}

		/**
		 * Returns the dm passport type with the primary key.
		 *
		 * @param id the primary key of the dm passport type
		 * @return the dm passport type
		 * @throws PortalException if a dm passport type with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public DmPassportType getDmPassportType(int id)
				throws PortalException, SystemException {
			return persistence.findByPrimaryKey(id);
		}


		/**
		 * Returns a range of all the dm passport types.
		 *
		 * <p>
		 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
		 * </p>
		 *
		 * @param start the lower bound of the range of dm passport types
		 * @param end the upper bound of the range of dm passport types (not inclusive)
		 * @return the range of dm passport types
		 * @throws SystemException if a system exception occurred
		 */
		public List<DmPassportType> getDmPassportTypes(int start, int end)
				throws SystemException {
			return persistence.findAll(start, end);
		}

		/**
		 * Returns the number of dm passport types.
		 *
		 * @return the number of dm passport types
		 * @throws SystemException if a system exception occurred
		 */
		public int getDmPassportTypesCount() throws SystemException {
			return persistence.countAll();
		}

		/**
		 * Updates the dm passport type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param dmPassportType the dm passport type
		 * @return the dm passport type that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public DmPassportType updateDmPassportType(DmPassportType dmPassportType)
				throws SystemException {
			return updateDmPassportType(dmPassportType, true);
		}

		/**
		 * Updates the dm passport type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param dmPassportType the dm passport type
		 * @param merge whether to merge the dm passport type with the current session. See  for an explanation.
		 * @return the dm passport type that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public DmPassportType updateDmPassportType(DmPassportType dmPassportType,
												   boolean merge) throws SystemException {

			dmPassportType = persistence.updateImpl(dmPassportType, merge);





			return dmPassportType;
		}
}