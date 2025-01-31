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

import com.fds.nsw.nghiepvu.danhmucgt.service.persistence.DmGtBusinessTypePersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;import lombok.extern.slf4j.Slf4j;@Slf4j @Service



/**
 * The implementation of the dm g t business type local service.
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the
 * {@link vn.gt.dao.danhmucgt.service.DmGTBusinessTypeLocalService} interface.
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can
 * only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.danhmucgt.service.base.DmGTBusinessTypeLocalServiceBaseImpl
 * @see vn.gt.dao.danhmucgt.service.DmGTBusinessTypeLocalServiceUtil
 */
public class DmGTBusinessTypeLocalServiceImpl {
	@Autowired
	DmGtBusinessTypePersistenceImpl persistence;
	/*
	 * NOTE FOR DEVELOPERS: Never reference this interface directly. Always use {@link vn.gt.dao.danhmucgt.service.DmGTBusinessTypeLocalServiceUtil}
	 * to access the dm g t business type local service.
	 */
	
	public int countByBusinessTypeCode(int businessTypeCode) {
		try {
			return persistence.countByBusinessTypeCode(businessTypeCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public DmGtBusinessType getByBusinessTypeCode(int businessTypeCode) {
		try {
			return persistence.findByBusinessTypeCode(businessTypeCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link vn.gt.dao.danhmucgt.service.DmGTBusinessTypeLocalServiceUtil} to access the dm g t business type local service.
	 */

	/**
	 * Adds the dm g t business type to the database. Also notifies the appropriate model listeners.
	 *
	 * @param dmGTBusinessType the dm g t business type
	 * @return the dm g t business type that was added
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtBusinessType addDmGTBusinessType(
			DmGtBusinessType dmGTBusinessType) throws SystemException {

		dmGTBusinessType = persistence.updateImpl(dmGTBusinessType,
				false);





		return dmGTBusinessType;
	}

	/**
	 * Creates a new dm g t business type with the primary key. Does not add the dm g t business type to the database.
	 *
	 * @param id the primary key for the new dm g t business type
	 * @return the new dm g t business type
	 */
	public DmGtBusinessType createDmGTBusinessType(long id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the dm g t business type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm g t business type
	 * @throws PortalException if a dm g t business type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteDmGTBusinessType(long id)
			throws PortalException, SystemException {
		DmGtBusinessType dmGTBusinessType = persistence.remove(id);




	}

	/**
	 * Deletes the dm g t business type from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dmGTBusinessType the dm g t business type
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteDmGTBusinessType(DmGtBusinessType dmGTBusinessType)
			throws SystemException {
		persistence.remove(dmGTBusinessType);




	}













	public DmGtBusinessType fetchDmGTBusinessType(long id)
			throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the dm g t business type with the primary key.
	 *
	 * @param id the primary key of the dm g t business type
	 * @return the dm g t business type
	 * @throws PortalException if a dm g t business type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtBusinessType getDmGTBusinessType(long id)
			throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}



	/**
	 * Returns a range of all the dm g t business types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm g t business types
	 * @param end the upper bound of the range of dm g t business types (not inclusive)
	 * @return the range of dm g t business types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtBusinessType> getDmGTBusinessTypes(int start, int end)
			throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of dm g t business types.
	 *
	 * @return the number of dm g t business types
	 * @throws SystemException if a system exception occurred
	 */
	public int getDmGTBusinessTypesCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the dm g t business type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param dmGTBusinessType the dm g t business type
	 * @return the dm g t business type that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtBusinessType updateDmGTBusinessType(
			DmGtBusinessType dmGTBusinessType) throws SystemException {
		return updateDmGTBusinessType(dmGTBusinessType, true);
	}

	/**
	 * Updates the dm g t business type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param dmGTBusinessType the dm g t business type
	 * @param merge whether to merge the dm g t business type with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	 * @return the dm g t business type that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtBusinessType updateDmGTBusinessType(
			DmGtBusinessType dmGTBusinessType, boolean merge)
			throws SystemException {

		dmGTBusinessType = persistence.updateImpl(dmGTBusinessType,
				merge);





		return dmGTBusinessType;
	}
	
}