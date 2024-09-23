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

import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmHistoryPackagePersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import lombok.extern.slf4j.Slf4j;
@Slf4j @Service






public class DmHistoryPackageLocalServiceImpl
	{ @Autowired
	DmHistoryPackagePersistenceImpl persistence;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link vn.gt.dao.danhmuc.service.DmHistoryPackageLocalServiceUtil} to access the dm history package com.fds.nsw.danhmuc.service.impl;import java.util.ArrayList; import java.util.List;import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;import com.fds.nsw.nghiepvu.model.*; import com.fds.nsw.kernel.exception.SystemException; import com.fds.nsw.service.exception.*;import lombok.extern.slf4j.Slf4j;@Slf4j @Service
	 */
	
	public DmHistoryPackage getHistoryPackageByPackageCodeAndSyncVersion(String packageCode, String syncVersion) {
		try {
			return persistence.findByPackageCodeAndSyncVersion(packageCode, syncVersion);
		} catch (NoSuchDmHistoryPackageException e) {
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return null;
	}

		/*
		 * NOTE FOR DEVELOPERS:
		 *
		 * Never modify or reference this class directly. Always use {@link vn.gt.dao.danhmuc.service.DmHistoryPackageLocalServiceUtil} to access the dm history package local service.
		 */

		/**
		 * Adds the dm history package to the database. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryPackage the dm history package
		 * @return the dm history package that was added
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryPackage addDmHistoryPackage(
				DmHistoryPackage dmHistoryPackage) throws SystemException {

			dmHistoryPackage = persistence.updateImpl(dmHistoryPackage,
					false);





			return dmHistoryPackage;
		}

		/**
		 * Creates a new dm history package with the primary key. Does not add the dm history package to the database.
		 *
		 * @param id the primary key for the new dm history package
		 * @return the new dm history package
		 */
		public DmHistoryPackage createDmHistoryPackage(int id) {
			return persistence.create(id);
		}

		/**
		 * Deletes the dm history package with the primary key from the database. Also notifies the appropriate model listeners.
		 *
		 * @param id the primary key of the dm history package
		 * @throws PortalException if a dm history package with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteDmHistoryPackage(int id)
				throws PortalException, SystemException {
			DmHistoryPackage dmHistoryPackage = persistence.remove(id);




		}

		/**
		 * Deletes the dm history package from the database. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryPackage the dm history package
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteDmHistoryPackage(DmHistoryPackage dmHistoryPackage)
				throws SystemException {
			persistence.remove(dmHistoryPackage);




		}













		public DmHistoryPackage fetchDmHistoryPackage(int id)
				throws SystemException {
			return persistence.fetchByPrimaryKey(id);
		}

		/**
		 * Returns the dm history package with the primary key.
		 *
		 * @param id the primary key of the dm history package
		 * @return the dm history package
		 * @throws PortalException if a dm history package with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryPackage getDmHistoryPackage(int id)
				throws PortalException, SystemException {
			return persistence.findByPrimaryKey(id);
		}

		/**
		 * Returns a range of all the dm history packages.
		 *
		 * <p>
		 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
		 * </p>
		 *
		 * @param start the lower bound of the range of dm history packages
		 * @param end the upper bound of the range of dm history packages (not inclusive)
		 * @return the range of dm history packages
		 * @throws SystemException if a system exception occurred
		 */
		public List<DmHistoryPackage> getDmHistoryPackages(int start, int end)
				throws SystemException {
			return persistence.findAll(start, end);
		}

		/**
		 * Returns the number of dm history packages.
		 *
		 * @return the number of dm history packages
		 * @throws SystemException if a system exception occurred
		 */
		public int getDmHistoryPackagesCount() throws SystemException {
			return persistence.countAll();
		}

		/**
		 * Updates the dm history package in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryPackage the dm history package
		 * @return the dm history package that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryPackage updateDmHistoryPackage(
				DmHistoryPackage dmHistoryPackage) throws SystemException {
			return updateDmHistoryPackage(dmHistoryPackage, true);
		}

		/**
		 * Updates the dm history package in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryPackage the dm history package
		 * @param merge whether to merge the dm history package with the current session. See  for an explanation.
		 * @return the dm history package that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryPackage updateDmHistoryPackage(
				DmHistoryPackage dmHistoryPackage, boolean merge)
				throws SystemException {

			dmHistoryPackage = persistence.updateImpl(dmHistoryPackage,
					merge);





			return dmHistoryPackage;
		}
}