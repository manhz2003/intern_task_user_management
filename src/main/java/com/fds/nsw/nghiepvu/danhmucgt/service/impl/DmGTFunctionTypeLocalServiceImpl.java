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

import com.fds.nsw.nghiepvu.danhmucgt.service.persistence.DmGtFunctionTypePersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;import lombok.extern.slf4j.Slf4j;@Slf4j @Service

/**
 * The implementation of the dm g t function type local service.
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the
 * {@link vn.gt.dao.danhmucgt.service.DmGTFunctionTypeLocalService} interface.
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can
 * only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.danhmucgt.service.base.DmGTFunctionTypeLocalServiceBaseImpl
 * @see vn.gt.dao.danhmucgt.service.dmGTFunctionTypeLocalService
 */
public class DmGTFunctionTypeLocalServiceImpl { @Autowired
DmGtFunctionTypePersistenceImpl persistence;
	/*
	 * NOTE FOR DEVELOPERS: Never reference this interface directly. Always use {@link vn.gt.dao.danhmucgt.service.dmGTFunctionTypeLocalService}
	 * to access the dm g t function type local service.
	 */

	public String findNameByFunctionTypeCode(String functionTypeCode) {
		try {
			return persistence.findByFunctionTypeCode(functionTypeCode).getFunctionTypeName();
		} catch (Exception e) {
			log.error("No  DmGtFunctionType exists with the key {functionTypeCode= " + functionTypeCode + " }");
		}
		return "";
	}
	
	public int countByFunctionTypeCode(String functionTypeCode) {
		try {
			return persistence.countByFunctionTypeCode(functionTypeCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link vn.gt.dao.danhmucgt.service.dmGTFunctionTypeLocalService} to access the dm g t function type local service.
	 */

	/**
	 * Adds the dm g t function type to the database. Also notifies the appropriate model listeners.
	 *
	 * @param dmGTFunctionType the dm g t function type
	 * @return the dm g t function type that was added
	 * @throws SystemException if a system exception occurred
	 */
	public  DmGtFunctionType addDmGTFunctionType(
			 DmGtFunctionType dmGTFunctionType) throws SystemException {

		dmGTFunctionType = persistence.updateImpl(dmGTFunctionType,
				false);





		return dmGTFunctionType;
	}

	/**
	 * Creates a new dm g t function type with the primary key. Does not add the dm g t function type to the database.
	 *
	 * @param id the primary key for the new dm g t function type
	 * @return the new dm g t function type
	 */
	public  DmGtFunctionType createDmGTFunctionType(long id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the dm g t function type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm g t function type
	 * @throws PortalException if a dm g t function type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteDmGTFunctionType(long id)
			throws PortalException, SystemException {
		 DmGtFunctionType dmGTFunctionType = persistence.remove(id);




	}

	/**
	 * Deletes the dm g t function type from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dmGTFunctionType the dm g t function type
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteDmGTFunctionType( DmGtFunctionType dmGTFunctionType)
			throws SystemException {
		persistence.remove(dmGTFunctionType);




	}













	public  DmGtFunctionType fetchDmGTFunctionType(long id)
			throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the dm g t function type with the primary key.
	 *
	 * @param id the primary key of the dm g t function type
	 * @return the dm g t function type
	 * @throws PortalException if a dm g t function type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public  DmGtFunctionType getDmGTFunctionType(long id)
			throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}



	/**
	 * Returns a range of all the dm g t function types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm g t function types
	 * @param end the upper bound of the range of dm g t function types (not inclusive)
	 * @return the range of dm g t function types
	 * @throws SystemException if a system exception occurred
	 */
	public List< DmGtFunctionType> getDmGTFunctionTypes(int start, int end)
			throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of dm g t function types.
	 *
	 * @return the number of dm g t function types
	 * @throws SystemException if a system exception occurred
	 */
	public int getDmGTFunctionTypesCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the dm g t function type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param dmGTFunctionType the dm g t function type
	 * @return the dm g t function type that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public  DmGtFunctionType updateDmGTFunctionType(
			 DmGtFunctionType dmGTFunctionType) throws SystemException {
		return updateDmGTFunctionType(dmGTFunctionType, true);
	}

	/**
	 * Updates the dm g t function type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param dmGTFunctionType the dm g t function type
	 * @param merge whether to merge the dm g t function type with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	 * @return the dm g t function type that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public  DmGtFunctionType updateDmGTFunctionType(
			 DmGtFunctionType dmGTFunctionType, boolean merge)
			throws SystemException {

		dmGTFunctionType = persistence.updateImpl(dmGTFunctionType,
				merge);





		return dmGTFunctionType;
	}
	
}