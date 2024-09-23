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

package com.fds.nsw.nghiepvu.danhmuc.service.impl;import com.fds.nsw.nghiepvu.danhmuc.service.finder.DmUnitGeneralFinderImpl;import java.util.ArrayList; import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;

import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmUnitGeneralPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import lombok.extern.slf4j.Slf4j;@Slf4j @Service






/**
 * The implementation of the dm unit general local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link vn.gt.dao.danhmuc.service.DmUnitGeneralLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.danhmuc.service.base.DmUnitGeneralLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.DmUnitGeneralLocalServiceUtil
 */
public class DmUnitGeneralLocalServiceImpl { @Autowired
DmUnitGeneralPersistenceImpl persistence;
	@Autowired
	DmUnitGeneralFinderImpl finder;


	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link vn.gt.dao.danhmuc.service.DmUnitGeneralLocalServiceUtil} to access the dm unit general local service.
	 */

	public List<DmUnitGeneral> findByUnitName(String unitName, int start,
											  int end) {
		try {
			return persistence.findByF_unitname(unitName, start,
					end);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<DmUnitGeneral> findUnitGenerals(String unitName,
												String isDelete, String unitCodeGroup, int start, int end) {
		try {
			return finder.findUnitGenerals(unitName, isDelete,
					unitCodeGroup, start, end);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public long countUnitGenerals(String unitName, String isDelete,
								  String unitCodeGroup) {
		try {
			return finder.countUnitGenerals(unitName, isDelete,
					unitCodeGroup);
		} catch (Exception e) {
			log.error(e.getMessage());
			return 0;
		}
	}

	public DmUnitGeneral getByUnitCode(String unitCode) {
		try {
			List<DmUnitGeneral> dmUnitCodes = persistence.findByUnitCode(unitCode);
			if (dmUnitCodes != null && dmUnitCodes.size() > 0) { return dmUnitCodes.get(0); }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public DmUnitGeneral getByUnitCodeAndSyncVersion(String unitCode, String syncVersion) {
		try {
			return persistence.findByUnitCodeAndSyncVersion(unitCode, syncVersion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link vn.gt.dao.danhmuc.service.DmUnitGeneralLocalServiceUtil} to access the dm unit general local service.
	 */

	/**
	 * Adds the dm unit general to the database. Also notifies the appropriate model listeners.
	 *
	 * @param dmUnitGeneral the dm unit general
	 * @return the dm unit general that was added
	 * @throws SystemException if a system exception occurred
	 */
	public DmUnitGeneral addDmUnitGeneral(DmUnitGeneral dmUnitGeneral)
			throws SystemException {

		dmUnitGeneral = persistence.updateImpl(dmUnitGeneral, false);





		return dmUnitGeneral;
	}

	/**
	 * Creates a new dm unit general with the primary key. Does not add the dm unit general to the database.
	 *
	 * @param id the primary key for the new dm unit general
	 * @return the new dm unit general
	 */
	public DmUnitGeneral createDmUnitGeneral(int id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the dm unit general with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm unit general
	 * @throws PortalException if a dm unit general with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteDmUnitGeneral(int id)
			throws PortalException, SystemException {
		DmUnitGeneral dmUnitGeneral = persistence.remove(id);




	}

	/**
	 * Deletes the dm unit general from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dmUnitGeneral the dm unit general
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteDmUnitGeneral(DmUnitGeneral dmUnitGeneral)
			throws SystemException {
		persistence.remove(dmUnitGeneral);




	}













	public DmUnitGeneral fetchDmUnitGeneral(int id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the dm unit general with the primary key.
	 *
	 * @param id the primary key of the dm unit general
	 * @return the dm unit general
	 * @throws PortalException if a dm unit general with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmUnitGeneral getDmUnitGeneral(int id)
			throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}


	/**
	 * Returns a range of all the dm unit generals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm unit generals
	 * @param end the upper bound of the range of dm unit generals (not inclusive)
	 * @return the range of dm unit generals
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmUnitGeneral> getDmUnitGenerals(int start, int end)
			throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of dm unit generals.
	 *
	 * @return the number of dm unit generals
	 * @throws SystemException if a system exception occurred
	 */
	public int getDmUnitGeneralsCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the dm unit general in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param dmUnitGeneral the dm unit general
	 * @return the dm unit general that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public DmUnitGeneral updateDmUnitGeneral(DmUnitGeneral dmUnitGeneral)
			throws SystemException {
		return updateDmUnitGeneral(dmUnitGeneral, true);
	}

	/**
	 * Updates the dm unit general in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param dmUnitGeneral the dm unit general
	 * @param merge whether to merge the dm unit general with the current session. See  for an explanation.
	 * @return the dm unit general that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public DmUnitGeneral updateDmUnitGeneral(DmUnitGeneral dmUnitGeneral,
											 boolean merge) throws SystemException {

		dmUnitGeneral = persistence.updateImpl(dmUnitGeneral, merge);





		return dmUnitGeneral;
	}
}