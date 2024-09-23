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

package com.fds.nsw.nghiepvu.danhmuc.service.impl;import com.fds.nsw.nghiepvu.danhmuc.service.finder.DmShipTypeFinderImpl;import java.util.ArrayList; import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;

import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmShipTypePersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import lombok.extern.slf4j.Slf4j;@Slf4j @Service






/**
 * The implementation of the dm ship type local service.
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the
 * {@link vn.gt.dao.danhmuc.service.DmShipTypeLocalService} interface.
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can
 * only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.danhmuc.service.base.DmShipTypeLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.DmShipTypeLocalServiceUtil
 */
public class DmShipTypeLocalServiceImpl { @Autowired
DmShipTypePersistenceImpl persistence;
	@Autowired
	DmShipTypeFinderImpl finder;
	/*
	 * NOTE FOR DEVELOPERS: Never reference this interface directly. Always use {@link vn.gt.dao.danhmuc.service.DmShipTypeLocalServiceUtil} to access
	 * the dm ship type local service.
	 */

	public List<DmShipType> findByShipTypeNameVN(String shipTypeNameVN,
												 int start, int end) {
		try {
			return persistence.findByF_shipTypeNameVN(shipTypeNameVN,
					start, end);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<DmShipType> findShipTypes(String shipTypeNameVN,
										  String isDelete, String shipTypeCodeGroup, int start, int end) {
		try {
			return finder.findShipTypes(shipTypeNameVN, isDelete,
					shipTypeCodeGroup, start, end);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public long countShipTypes(String shipTypeNameVN, String isDelete,
							   String shipTypeCodeGroup) {
		try {
			return finder.countShipTypes(shipTypeNameVN, isDelete,
					shipTypeCodeGroup);
		} catch (Exception e) {
			log.error(e.getMessage());
			return 0;
		}
	}


	public DmShipType getByShipTypeCode(String shipTypeCode) {
		try {
			List<DmShipType> dmShipTypeCodes = persistence.findByShipTypeCode(shipTypeCode);
			if (dmShipTypeCodes != null && dmShipTypeCodes.size() > 0) {
				return dmShipTypeCodes.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link vn.gt.dao.danhmuc.service.DmShipTypeLocalServiceUtil} to access the dm ship type local service.
	 */

	/**
	 * Adds the dm ship type to the database. Also notifies the appropriate model listeners.
	 *
	 * @param dmShipType the dm ship type
	 * @return the dm ship type that was added
	 * @throws SystemException if a system exception occurred
	 */
	public DmShipType addDmShipType(DmShipType dmShipType)
			throws SystemException {

		dmShipType = persistence.updateImpl(dmShipType, false);





		return dmShipType;
	}

	/**
	 * Creates a new dm ship type with the primary key. Does not add the dm ship type to the database.
	 *
	 * @param id the primary key for the new dm ship type
	 * @return the new dm ship type
	 */
	public DmShipType createDmShipType(int id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the dm ship type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm ship type
	 * @throws PortalException if a dm ship type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteDmShipType(int id)
			throws PortalException, SystemException {
		DmShipType dmShipType = persistence.remove(id);




	}

	/**
	 * Deletes the dm ship type from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dmShipType the dm ship type
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteDmShipType(DmShipType dmShipType)
			throws SystemException {
		persistence.remove(dmShipType);




	}













	public DmShipType fetchDmShipType(int id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the dm ship type with the primary key.
	 *
	 * @param id the primary key of the dm ship type
	 * @return the dm ship type
	 * @throws PortalException if a dm ship type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmShipType getDmShipType(int id)
			throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}



	/**
	 * Returns a range of all the dm ship types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm ship types
	 * @param end the upper bound of the range of dm ship types (not inclusive)
	 * @return the range of dm ship types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmShipType> getDmShipTypes(int start, int end)
			throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of dm ship types.
	 *
	 * @return the number of dm ship types
	 * @throws SystemException if a system exception occurred
	 */
	public int getDmShipTypesCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the dm ship type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param dmShipType the dm ship type
	 * @return the dm ship type that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public DmShipType updateDmShipType(DmShipType dmShipType)
			throws SystemException {
		return updateDmShipType(dmShipType, true);
	}

	/**
	 * Updates the dm ship type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param dmShipType the dm ship type
	 * @param merge whether to merge the dm ship type with the current session. See  for an explanation.
	 * @return the dm ship type that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public DmShipType updateDmShipType(DmShipType dmShipType, boolean merge)
			throws SystemException {

		dmShipType = persistence.updateImpl(dmShipType, merge);





		return dmShipType;
	}
}