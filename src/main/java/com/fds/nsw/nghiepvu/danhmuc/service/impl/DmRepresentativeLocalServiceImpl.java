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


import java.util.ArrayList; import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;

import com.fds.nsw.nghiepvu.danhmuc.service.finder.DmRepresentativeFinderImpl;
import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmRepresentativePersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import lombok.extern.slf4j.Slf4j;@Slf4j @Service









/**
 * The implementation of the dm representative local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link vn.gt.dao.danhmuc.service.DmRepresentativeLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.danhmuc.service.base.DmRepresentativeLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.DmRepresentativeLocalServiceUtil
 */
public class DmRepresentativeLocalServiceImpl { @Autowired
DmRepresentativePersistenceImpl persistence;@Autowired
DmRepresentativeFinderImpl finder;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link vn.gt.dao.danhmuc.service.DmRepresentativeLocalServiceUtil} to access the dm representative local service.
	 */

	public List<DmRepresentative> findDmRepresentatives(String maritimeCode,
														String repNameVN, int repLevel, String isDelete,
														String repCodeGroup, int start, int end) {
		try {
			return finder.findDmRepresentatives(maritimeCode,
					repNameVN, repLevel, isDelete, repCodeGroup, start, end);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public long countDmRepresentatives(String maritimeCode, String repNameVN,
									   int repLevel, String isDelete, String repCodeGroup) {
		try {
			return finder.countDmRepresentatives(maritimeCode,
					repNameVN, repLevel, isDelete, repCodeGroup);
		} catch (Exception e) {
			log.error(e.getMessage());
			return 0;
		}
	}


	public DmRepresentative getByRepCode(String repCode) {
		try {
			return persistence.findByRepCode(repCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<DmRepresentative> findByMaritimeCode(String maritimeCode) {
		try {
			//return persistence.findByMaritimeCode(maritimeCode);
			return finder.findDmRepresentativeByMaritimeCode(maritimeCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<DmRepresentative>();
	}
	
	public List<DmRepresentative> findDanhSachDmRepresentative(String maritimeCode, int start, int end) {
		try {
			return finder.findDanhSachDmRepresentative(maritimeCode, start, end);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<DmRepresentative>();
	}
	
	public List<DmRepresentative> findDmRepresentativeByMaritimeCode(String maritimeCode) {
		try {
			return finder.findDmRepresentativeByMaritimeCode(maritimeCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<DmRepresentative>();
	}
	
	public int countDanhSachDmRepresentative(String maritimeCode) {
		try {
			return finder.countDanhSachDmRepresentative(maritimeCode);
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link vn.gt.dao.danhmuc.service.DmRepresentativeLocalServiceUtil} to access the dm representative local service.
	 */

	/**
	 * Adds the dm representative to the database. Also notifies the appropriate model listeners.
	 *
	 * @param dmRepresentative the dm representative
	 * @return the dm representative that was added
	 * @throws SystemException if a system exception occurred
	 */
	public DmRepresentative addDmRepresentative(
			DmRepresentative dmRepresentative) throws SystemException {

		dmRepresentative = persistence.updateImpl(dmRepresentative,
				false);





		return dmRepresentative;
	}

	/**
	 * Creates a new dm representative with the primary key. Does not add the dm representative to the database.
	 *
	 * @param id the primary key for the new dm representative
	 * @return the new dm representative
	 */
	public DmRepresentative createDmRepresentative(int id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the dm representative with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm representative
	 * @throws PortalException if a dm representative with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteDmRepresentative(int id)
			throws PortalException, SystemException {
		DmRepresentative dmRepresentative = persistence.remove(id);




	}

	/**
	 * Deletes the dm representative from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dmRepresentative the dm representative
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteDmRepresentative(DmRepresentative dmRepresentative)
			throws SystemException {
		persistence.remove(dmRepresentative);




	}













	public DmRepresentative fetchDmRepresentative(int id)
			throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the dm representative with the primary key.
	 *
	 * @param id the primary key of the dm representative
	 * @return the dm representative
	 * @throws PortalException if a dm representative with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmRepresentative getDmRepresentative(int id)
			throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}


	/**
	 * Returns a range of all the dm representatives.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm representatives
	 * @param end the upper bound of the range of dm representatives (not inclusive)
	 * @return the range of dm representatives
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmRepresentative> getDmRepresentatives(int start, int end)
			throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of dm representatives.
	 *
	 * @return the number of dm representatives
	 * @throws SystemException if a system exception occurred
	 */
	public int getDmRepresentativesCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the dm representative in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param dmRepresentative the dm representative
	 * @return the dm representative that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public DmRepresentative updateDmRepresentative(
			DmRepresentative dmRepresentative) throws SystemException {
		return updateDmRepresentative(dmRepresentative, true);
	}

	/**
	 * Updates the dm representative in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param dmRepresentative the dm representative
	 * @param merge whether to merge the dm representative with the current session. See  for an explanation.
	 * @return the dm representative that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public DmRepresentative updateDmRepresentative(
			DmRepresentative dmRepresentative, boolean merge)
			throws SystemException {

		dmRepresentative = persistence.updateImpl(dmRepresentative,
				merge);





		return dmRepresentative;
	}
}