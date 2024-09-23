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

import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmGoodsTypePersistenceImpl;
import com.fds.nsw.nghiepvu.danhmuc.service.finder.DmGoodsTypeFinderImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import lombok.extern.slf4j.Slf4j;@Slf4j @Service





/**
 * The implementation of the dm goods type local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link vn.gt.dao.danhmuc.service.DmGoodsTypeLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.danhmuc.service.base.DmGoodsTypeLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.DmGoodsTypeLocalServiceUtil
 */
public class DmGoodsTypeLocalServiceImpl { @Autowired
DmGoodsTypePersistenceImpl persistence;@Autowired
DmGoodsTypeFinderImpl finder;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link vn.gt.dao.danhmuc.service.DmGoodsTypeLocalServiceUtil} to access the dm goods type local service.
	 */
	public DmGoodsType getByGoodsTypeCode(String goodsTypeCode) {
		try {
			List<DmGoodsType> dmGoodsTypes =  persistence.findByGoodsTypeCode(goodsTypeCode);
			if (dmGoodsTypes != null && dmGoodsTypes.size() > 0) {
				return dmGoodsTypes.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<DmGoodsType> FByGoodsTypeNameVN(String goodsTypeNameVN,
												int start, int end) {
		try {
			return persistence.findByF_goodsTypeNameVNbyLike(
					goodsTypeNameVN, start, end);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<DmGoodsType> findGoodsType(String goodsTypeNameVN,
										   String isDelete, String goodsTypeCodeGroup, int start, int end) {
		try {
			return finder.findGoodsType(goodsTypeNameVN, isDelete,
					goodsTypeCodeGroup, start, end);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public long countGoodsType(String goodsTypeNameVN, String isDelete,
							   String goodsTypeCodeGroup) {
		try {
			return finder.countGoodsType(goodsTypeNameVN, isDelete,
					goodsTypeCodeGroup);
		} catch (Exception e) {
			log.error(e.getMessage());
			return 0;
		}
	}


	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link vn.gt.dao.danhmuc.service.DmGoodsTypeLocalServiceUtil} to access the dm goods type local service.
	 */

	/**
	 * Adds the dm goods type to the database. Also notifies the appropriate model listeners.
	 *
	 * @param dmGoodsType the dm goods type
	 * @return the dm goods type that was added
	 * @throws SystemException if a system exception occurred
	 */
	public DmGoodsType addDmGoodsType(DmGoodsType dmGoodsType)
			throws SystemException {

		dmGoodsType = persistence.updateImpl(dmGoodsType, false);





		return dmGoodsType;
	}

	/**
	 * Creates a new dm goods type with the primary key. Does not add the dm goods type to the database.
	 *
	 * @param id the primary key for the new dm goods type
	 * @return the new dm goods type
	 */
	public DmGoodsType createDmGoodsType(int id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the dm goods type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm goods type
	 * @throws PortalException if a dm goods type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteDmGoodsType(int id)
			throws PortalException, SystemException {
		DmGoodsType dmGoodsType = persistence.remove(id);




	}

	/**
	 * Deletes the dm goods type from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dmGoodsType the dm goods type
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteDmGoodsType(DmGoodsType dmGoodsType)
			throws SystemException {
		persistence.remove(dmGoodsType);




	}













	public DmGoodsType fetchDmGoodsType(int id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the dm goods type with the primary key.
	 *
	 * @param id the primary key of the dm goods type
	 * @return the dm goods type
	 * @throws PortalException if a dm goods type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGoodsType getDmGoodsType(int id)
			throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	

	/**
	 * Returns a range of all the dm goods types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm goods types
	 * @param end the upper bound of the range of dm goods types (not inclusive)
	 * @return the range of dm goods types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGoodsType> getDmGoodsTypes(int start, int end)
			throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of dm goods types.
	 *
	 * @return the number of dm goods types
	 * @throws SystemException if a system exception occurred
	 */
	public int getDmGoodsTypesCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the dm goods type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param dmGoodsType the dm goods type
	 * @return the dm goods type that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public DmGoodsType updateDmGoodsType(DmGoodsType dmGoodsType)
			throws SystemException {
		return updateDmGoodsType(dmGoodsType, true);
	}

	/**
	 * Updates the dm goods type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param dmGoodsType the dm goods type
	 * @param merge whether to merge the dm goods type with the current session. See  for an explanation.
	 * @return the dm goods type that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public DmGoodsType updateDmGoodsType(DmGoodsType dmGoodsType, boolean merge)
			throws SystemException {

		dmGoodsType = persistence.updateImpl(dmGoodsType, merge);





		return dmGoodsType;
	}
}