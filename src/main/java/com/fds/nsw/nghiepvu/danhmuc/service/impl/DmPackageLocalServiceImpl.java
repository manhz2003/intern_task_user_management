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

package com.fds.nsw.nghiepvu.danhmuc.service.impl;import java.util.ArrayList; import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;

import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmPackagePersistenceImpl;
import com.fds.nsw.nghiepvu.danhmuc.service.finder.DmPackageFinderImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import lombok.extern.slf4j.Slf4j;@Slf4j @Service


public class DmPackageLocalServiceImpl { @Autowired
DmPackagePersistenceImpl persistence;
	@Autowired DmPackageFinderImpl finder;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link vn.gt.dao.danhmuc.service.DmPackageLocalServiceUtil} to access the dm package com.fds.nsw.danhmuc.service.impl;import java.util.ArrayList; import java.util.List;import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;import com.fds.nsw.nghiepvu.model.*; import com.fds.nsw.kernel.exception.SystemException; import com.fds.nsw.service.exception.*;import lombok.extern.slf4j.Slf4j;@Slf4j @Service
	 */
	public DmPackage getByPackageCode(String packageCode) {
		try {
			List<DmPackage> dmPackages = persistence.findByPackageCode(packageCode);
			if (dmPackages != null && dmPackages.size() > 0) {
				return dmPackages.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<DmPackage> findByPackage(String packageNameVN, int start, int end){
		try{
			return persistence.findByF_packgageNameVN(packageNameVN, start, end);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public List<DmPackage> findPackages(String packageCode,
										String packageNameVN, String isDelete, int start, int end){
		try{
			return finder.findPackages(packageCode, packageNameVN, isDelete, start, end);
		}
		catch(Exception e){
			log.error(e.getMessage());
			return null;
		}
	}

	public long countPackages(String packageCode,
							  String packageNameVN, String isDelete){
		try{
			return finder.countPackages(packageCode, packageNameVN, isDelete);
		}
		catch(Exception e){
			log.error(e.getMessage());
			return 0;
		}
	}



	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link vn.gt.dao.danhmuc.service.DmPackageLocalServiceUtil} to access the dm package local service.
	 */

	/**
	 * Adds the dm package to the database. Also notifies the appropriate model listeners.
	 *
	 * @param dmPackage the dm package
	 * @return the dm package that was added
	 * @throws SystemException if a system exception occurred
	 */
	public DmPackage addDmPackage(DmPackage dmPackage)
			throws SystemException {

		dmPackage = persistence.updateImpl(dmPackage, false);





		return dmPackage;
	}

	/**
	 * Creates a new dm package with the primary key. Does not add the dm package to the database.
	 *
	 * @param id the primary key for the new dm package
	 * @return the new dm package
	 */
	public DmPackage createDmPackage(int id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the dm package with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm package
	 * @throws PortalException if a dm package with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteDmPackage(int id) throws PortalException, SystemException {
		DmPackage dmPackage = persistence.remove(id);




	}

	/**
	 * Deletes the dm package from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dmPackage the dm package
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteDmPackage(DmPackage dmPackage) throws SystemException {
		persistence.remove(dmPackage);




	}













	public DmPackage fetchDmPackage(int id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the dm package with the primary key.
	 *
	 * @param id the primary key of the dm package
	 * @return the dm package
	 * @throws PortalException if a dm package with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPackage getDmPackage(int id)
			throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}


	/**
	 * Returns a range of all the dm packages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm packages
	 * @param end the upper bound of the range of dm packages (not inclusive)
	 * @return the range of dm packages
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPackage> getDmPackages(int start, int end)
			throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of dm packages.
	 *
	 * @return the number of dm packages
	 * @throws SystemException if a system exception occurred
	 */
	public int getDmPackagesCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the dm package in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param dmPackage the dm package
	 * @return the dm package that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public DmPackage updateDmPackage(DmPackage dmPackage)
			throws SystemException {
		return updateDmPackage(dmPackage, true);
	}

	/**
	 * Updates the dm package in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param dmPackage the dm package
	 * @param merge whether to merge the dm package with the current session. See  for an explanation.
	 * @return the dm package that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public DmPackage updateDmPackage(DmPackage dmPackage, boolean merge)
			throws SystemException {

		dmPackage = persistence.updateImpl(dmPackage, merge);





		return dmPackage;
	}
}