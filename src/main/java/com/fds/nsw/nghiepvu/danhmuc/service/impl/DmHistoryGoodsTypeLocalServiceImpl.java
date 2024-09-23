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

import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;

import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmHistoryGoodsTypePersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import lombok.extern.slf4j.Slf4j;@Slf4j @Service




/**
 * The implementation of the dm history goods type local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link vn.gt.dao.danhmuc.service.DmHistoryGoodsTypeLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.danhmuc.service.base.DmHistoryGoodsTypeLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.DmHistoryGoodsTypeLocalServiceUtil
 */
public class DmHistoryGoodsTypeLocalServiceImpl
	{ @Autowired
	DmHistoryGoodsTypePersistenceImpl persistence;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link vn.gt.dao.danhmuc.service.DmHistoryGoodsTypeLocalServiceUtil} to access the dm history goods type local service.
	 */
	public DmHistoryGoodsType findByGoodsTypeCodeAndSyncVersion(
			String goodsTypeCode, String syncVersion) {
		try {
			DmHistoryGoodsType goodsType = persistence.findByGoodsTypeCodeAndSyncVersion(goodsTypeCode, syncVersion);
			if (goodsType != null) return goodsType;
			else return null;
		} catch (NoSuchDmHistoryGoodsTypeException e) {
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return null;
	}

		/*
		 * NOTE FOR DEVELOPERS:
		 *
		 * Never modify or reference this class directly. Always use {@link vn.gt.dao.danhmuc.service.DmHistoryGoodsTypeLocalServiceUtil} to access the dm history goods type local service.
		 */

		/**
		 * Adds the dm history goods type to the database. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryGoodsType the dm history goods type
		 * @return the dm history goods type that was added
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryGoodsType addDmHistoryGoodsType(
				DmHistoryGoodsType dmHistoryGoodsType) throws SystemException {

			dmHistoryGoodsType = persistence.updateImpl(dmHistoryGoodsType,
					false);





			return dmHistoryGoodsType;
		}

		/**
		 * Creates a new dm history goods type with the primary key. Does not add the dm history goods type to the database.
		 *
		 * @param id the primary key for the new dm history goods type
		 * @return the new dm history goods type
		 */
		public DmHistoryGoodsType createDmHistoryGoodsType(int id) {
			return persistence.create(id);
		}

		/**
		 * Deletes the dm history goods type with the primary key from the database. Also notifies the appropriate model listeners.
		 *
		 * @param id the primary key of the dm history goods type
		 * @throws PortalException if a dm history goods type with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteDmHistoryGoodsType(int id)
				throws PortalException, SystemException {
			DmHistoryGoodsType dmHistoryGoodsType = persistence.remove(id);




		}

		/**
		 * Deletes the dm history goods type from the database. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryGoodsType the dm history goods type
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteDmHistoryGoodsType(DmHistoryGoodsType dmHistoryGoodsType)
				throws SystemException {
			persistence.remove(dmHistoryGoodsType);




		}













		public DmHistoryGoodsType fetchDmHistoryGoodsType(int id)
				throws SystemException {
			return persistence.fetchByPrimaryKey(id);
		}

		/**
		 * Returns the dm history goods type with the primary key.
		 *
		 * @param id the primary key of the dm history goods type
		 * @return the dm history goods type
		 * @throws PortalException if a dm history goods type with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryGoodsType getDmHistoryGoodsType(int id)
				throws PortalException, SystemException {
			return persistence.findByPrimaryKey(id);
		}


		/**
		 * Returns a range of all the dm history goods types.
		 *
		 * <p>
		 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
		 * </p>
		 *
		 * @param start the lower bound of the range of dm history goods types
		 * @param end the upper bound of the range of dm history goods types (not inclusive)
		 * @return the range of dm history goods types
		 * @throws SystemException if a system exception occurred
		 */
		public List<DmHistoryGoodsType> getDmHistoryGoodsTypes(int start, int end)
				throws SystemException {
			return persistence.findAll(start, end);
		}

		/**
		 * Returns the number of dm history goods types.
		 *
		 * @return the number of dm history goods types
		 * @throws SystemException if a system exception occurred
		 */
		public int getDmHistoryGoodsTypesCount() throws SystemException {
			return persistence.countAll();
		}

		/**
		 * Updates the dm history goods type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryGoodsType the dm history goods type
		 * @return the dm history goods type that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryGoodsType updateDmHistoryGoodsType(
				DmHistoryGoodsType dmHistoryGoodsType) throws SystemException {
			return updateDmHistoryGoodsType(dmHistoryGoodsType, true);
		}

		/**
		 * Updates the dm history goods type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryGoodsType the dm history goods type
		 * @param merge whether to merge the dm history goods type with the current session. See  for an explanation.
		 * @return the dm history goods type that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryGoodsType updateDmHistoryGoodsType(
				DmHistoryGoodsType dmHistoryGoodsType, boolean merge)
				throws SystemException {

			dmHistoryGoodsType = persistence.updateImpl(dmHistoryGoodsType,
					merge);





			return dmHistoryGoodsType;
		}
}