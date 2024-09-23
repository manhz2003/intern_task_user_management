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

import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmHistoryGoodsPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import lombok.extern.slf4j.Slf4j;@Slf4j @Service









/**
 * The implementation of the dm history goods local service.
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the
 * {@link vn.gt.dao.danhmuc.service.DmHistoryGoodsLocalService} interface.
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can
 * only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.danhmuc.service.base.DmHistoryGoodsLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.DmHistoryGoodsLocalServiceUtil
 */
public class DmHistoryGoodsLocalServiceImpl { @Autowired
DmHistoryGoodsPersistenceImpl persistence;

	/*
	 * NOTE FOR DEVELOPERS: Never reference this interface directly. Always use {@link vn.gt.dao.danhmuc.service.DmHistoryGoodsLocalServiceUtil} to
	 * access the dm history goods local service.
	 */
	public DmHistoryGoods findByGoodsItemCodeAndSyncVersion(String goodsItemCode, String syncVersion) {
		try {
			return persistence.findByGoodsItemCodeAndSyncVersion(goodsItemCode, syncVersion);
		} catch (NoSuchDmHistoryGoodsException e) {
			log.info("com.fds.nsw.nghiepvu.service.exception.NoSuchDmHistoryGoodsException: No DmHistoryGoods exists with the key {goodsItemCode=" + goodsItemCode +", syncVersion=" + syncVersion +"}");
		} catch (SystemException e) {
			log.info("com.fds.nsw.nghiepvu.service.exception.NoSuchDmHistoryGoodsException: No DmHistoryGoods exists with the key {goodsItemCode=" + goodsItemCode +", syncVersion=" + syncVersion +"}");
		}
		return null;
	}

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link vn.gt.dao.danhmuc.service.DmHistoryGoodsLocalServiceUtil} to access the dm history goods local service.
	 */

	/**
	 * Adds the dm history goods to the database. Also notifies the appropriate model listeners.
	 *
	 * @param dmHistoryGoods the dm history goods
	 * @return the dm history goods that was added
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryGoods addDmHistoryGoods(DmHistoryGoods dmHistoryGoods)
			throws SystemException {

		dmHistoryGoods = persistence.updateImpl(dmHistoryGoods, false);





		return dmHistoryGoods;
	}

	/**
	 * Creates a new dm history goods with the primary key. Does not add the dm history goods to the database.
	 *
	 * @param id the primary key for the new dm history goods
	 * @return the new dm history goods
	 */
	public DmHistoryGoods createDmHistoryGoods(int id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the dm history goods with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm history goods
	 * @throws PortalException if a dm history goods with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteDmHistoryGoods(int id)
			throws PortalException, SystemException {
		DmHistoryGoods dmHistoryGoods = persistence.remove(id);




	}

	/**
	 * Deletes the dm history goods from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dmHistoryGoods the dm history goods
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteDmHistoryGoods(DmHistoryGoods dmHistoryGoods)
			throws SystemException {
		persistence.remove(dmHistoryGoods);




	}













	public DmHistoryGoods fetchDmHistoryGoods(int id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the dm history goods with the primary key.
	 *
	 * @param id the primary key of the dm history goods
	 * @return the dm history goods
	 * @throws PortalException if a dm history goods with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryGoods getDmHistoryGoods(int id)
			throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}
	
	/**
	 * Returns a range of all the dm history goodses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history goodses
	 * @param end the upper bound of the range of dm history goodses (not inclusive)
	 * @return the range of dm history goodses
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryGoods> getDmHistoryGoodses(int start, int end)
			throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of dm history goodses.
	 *
	 * @return the number of dm history goodses
	 * @throws SystemException if a system exception occurred
	 */
	public int getDmHistoryGoodsesCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the dm history goods in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param dmHistoryGoods the dm history goods
	 * @return the dm history goods that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryGoods updateDmHistoryGoods(DmHistoryGoods dmHistoryGoods)
			throws SystemException {
		return updateDmHistoryGoods(dmHistoryGoods, true);
	}

	/**
	 * Updates the dm history goods in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param dmHistoryGoods the dm history goods
	 * @param merge whether to merge the dm history goods with the current session. See  for an explanation.
	 * @return the dm history goods that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryGoods updateDmHistoryGoods(DmHistoryGoods dmHistoryGoods,
											   boolean merge) throws SystemException {

		dmHistoryGoods = persistence.updateImpl(dmHistoryGoods, merge);





		return dmHistoryGoods;
	}
}