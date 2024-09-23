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
import com.fds.nsw.kernel.exception.PortalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmDocTypePersistenceImpl;
import com.fds.nsw.nghiepvu.danhmuc.service.finder.DmDocTypeFinderImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmDocTypeLocalServiceImpl { @Autowired
DmDocTypePersistenceImpl persistence; @Autowired DmDocTypeFinderImpl finder;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link vn.gt.dao.danhmuc.service.DmDocTypeLocalServiceUtil} to access the dm doc type local service.
	 */
	public DmDocType getByDocumentTypeCode(String documentTypeCode) {
		try {
			List<DmDocType> dmTypeCodes = persistence.findByDocumentTypeCode(documentTypeCode);
			if (dmTypeCodes != null && dmTypeCodes.size() > 0) {
				return dmTypeCodes.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link vn.gt.dao.danhmuc.service.DmDocTypeLocalServiceUtil} to access the dm doc type local service.
	 */

	/**
	 * Adds the dm doc type to the database. Also notifies the appropriate model listeners.
	 *
	 * @param dmDocType the dm doc type
	 * @return the dm doc type that was added
	 * @throws SystemException if a system exception occurred
	 */
	public DmDocType addDmDocType(DmDocType dmDocType)
			throws SystemException {

		dmDocType = persistence.updateImpl(dmDocType, false);





		return dmDocType;
	}

	/**
	 * Creates a new dm doc type with the primary key. Does not add the dm doc type to the database.
	 *
	 * @param id the primary key for the new dm doc type
	 * @return the new dm doc type
	 */
	public DmDocType createDmDocType(int id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the dm doc type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm doc type
	 * @throws PortalException if a dm doc type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteDmDocType(int id) throws PortalException, SystemException {
		DmDocType dmDocType = persistence.remove(id);




	}

	public List<DmDocType> findByDocumentTypeName(String documentTypeName,
												  int start, int end) {
		try {
			return persistence.findByF_documentTypeName(
					documentTypeName, start, end);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public DmDocType findByDocumentType(String documentType) {
		try {
			return persistence.findByF_documentType(documentType);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<DmDocType> findDocTypes(String documentTypeName,
										String isDelete, String documentTypeCodeGroup, int start, int end) {
		try {
			return finder.findDocTypes(documentTypeName, isDelete,
					documentTypeCodeGroup, start, end);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public long countDocTypes(String documentTypeName, String isDelete,
							  String documentTypeCodeGroup) {
		try {
			return finder.countDocTypes(documentTypeName, isDelete,
					documentTypeCodeGroup);
		} catch (Exception e) {
			log.error(e.getMessage());
			return 0;
		}
	}


	/**
	 * Deletes the dm doc type from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dmDocType the dm doc type
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteDmDocType(DmDocType dmDocType) throws SystemException {
		persistence.remove(dmDocType);

	}


	public DmDocType fetchDmDocType(int id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the dm doc type with the primary key.
	 *
	 * @param id the primary key of the dm doc type
	 * @return the dm doc type
	 * @throws PortalException if a dm doc type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmDocType getDmDocType(int id)
			throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}


	/**
	 * Returns a range of all the dm doc types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm doc types
	 * @param end the upper bound of the range of dm doc types (not inclusive)
	 * @return the range of dm doc types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmDocType> getDmDocTypes(int start, int end)
			throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of dm doc types.
	 *
	 * @return the number of dm doc types
	 * @throws SystemException if a system exception occurred
	 */
	public int getDmDocTypesCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the dm doc type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param dmDocType the dm doc type
	 * @return the dm doc type that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public DmDocType updateDmDocType(DmDocType dmDocType)
			throws SystemException {
		return updateDmDocType(dmDocType, true);
	}

	/**
	 * Updates the dm doc type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param dmDocType the dm doc type
	 * @param merge whether to merge the dm doc type with the current session. See  for an explanation.
	 * @return the dm doc type that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public DmDocType updateDmDocType(DmDocType dmDocType, boolean merge)
			throws SystemException {

		dmDocType = persistence.updateImpl(dmDocType, merge);





		return dmDocType;
	}
}