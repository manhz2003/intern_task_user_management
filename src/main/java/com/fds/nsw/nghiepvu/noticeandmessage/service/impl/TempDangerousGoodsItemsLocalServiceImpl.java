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

package com.fds.nsw.nghiepvu.noticeandmessage.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.TempDangerousGoodsItemsPersistenceImpl;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

/**
 * The implementation of the temp dangerous goods items local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link vn.gt.dao.noticeandmessage.service.TempDangerousGoodsItemsLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.noticeandmessage.service.base.TempDangerousGoodsItemsLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.TempDangerousGoodsItemsLocalServiceUtil
 */
public class TempDangerousGoodsItemsLocalServiceImpl {
	@Autowired
	TempDangerousGoodsItemsPersistenceImpl persistence;
	

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link
	 * vn.gt.dao.noticeandmessage.service.TempDangerousGoodsItemsLocalServiceUtil}
	 * to access the temp dangerous goods items local service.
	 */
	public List<TempDangerousGoodsItems> findByRequestCode(String requestCode) throws SystemException {
		return persistence.findByRequestCode(requestCode);
	}

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link
	 * vn.gt.dao.noticeandmessage.service.TempDangerousGoodsItemsLocalServiceUtil}
	 * to access the temp dangerous goods items local service.
	 */

	/**
	 * Adds the temp dangerous goods items to the database. Also notifies the
	 * appropriate model listeners.
	 *
	 * @param tempDangerousGoodsItems the temp dangerous goods items
	 * @return the temp dangerous goods items that was added
	 * @throws SystemException if a system exception occurred
	 */
	public TempDangerousGoodsItems addTempDangerousGoodsItems(TempDangerousGoodsItems tempDangerousGoodsItems)
			throws SystemException {
		

		tempDangerousGoodsItems = persistence.updateImpl(tempDangerousGoodsItems, false);

		return tempDangerousGoodsItems;
	}

	/**
	 * Creates a new temp dangerous goods items with the primary key. Does not add
	 * the temp dangerous goods items to the database.
	 *
	 * @param id the primary key for the new temp dangerous goods items
	 * @return the new temp dangerous goods items
	 */
	public TempDangerousGoodsItems createTempDangerousGoodsItems(long id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the temp dangerous goods items with the primary key from the
	 * database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the temp dangerous goods items
	 * @throws PortalException if a temp dangerous goods items with the primary key
	 *                         could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteTempDangerousGoodsItems(long id) throws PortalException, SystemException {
		TempDangerousGoodsItems tempDangerousGoodsItems = persistence.remove(id);

	}

	/**
	 * Deletes the temp dangerous goods items from the database. Also notifies the
	 * appropriate model listeners.
	 *
	 * @param tempDangerousGoodsItems the temp dangerous goods items
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteTempDangerousGoodsItems(TempDangerousGoodsItems tempDangerousGoodsItems) throws SystemException {
		persistence.remove(tempDangerousGoodsItems);

	}

	public TempDangerousGoodsItems fetchTempDangerousGoodsItems(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the temp dangerous goods items with the primary key.
	 *
	 * @param id the primary key of the temp dangerous goods items
	 * @return the temp dangerous goods items
	 * @throws PortalException if a temp dangerous goods items with the primary key
	 *                         could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDangerousGoodsItems getTempDangerousGoodsItems(long id) throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public TempDangerousGoodsItems getPersistedModel(Serializable primaryKeyObj) throws PortalException, SystemException {
		return persistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the temp dangerous goods itemses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code>
	 * instances. <code>start</code> and <code>end</code> are not primary keys, they
	 * are indexes in the result set. Thus, <code>0</code> refers to the first
	 * result in the set. Setting both <code>start</code> and <code>end</code> to
	 *  will return the
	 * full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of temp dangerous goods itemses
	 * @param end   the upper bound of the range of temp dangerous goods itemses
	 *              (not inclusive)
	 * @return the range of temp dangerous goods itemses
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDangerousGoodsItems> getTempDangerousGoodsItemses(int start, int end) throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of temp dangerous goods itemses.
	 *
	 * @return the number of temp dangerous goods itemses
	 * @throws SystemException if a system exception occurred
	 */
	public int getTempDangerousGoodsItemsesCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the temp dangerous goods items in the database or adds it if it does
	 * not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param tempDangerousGoodsItems the temp dangerous goods items
	 * @return the temp dangerous goods items that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public TempDangerousGoodsItems updateTempDangerousGoodsItems(TempDangerousGoodsItems tempDangerousGoodsItems)
			throws SystemException {
		return updateTempDangerousGoodsItems(tempDangerousGoodsItems, true);
	}

	/**
	 * Updates the temp dangerous goods items in the database or adds it if it does
	 * not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param tempDangerousGoodsItems the temp dangerous goods items
	 * @param merge                   whether to merge the temp dangerous goods
	 *                                items with the current session. See
	 *                                
	 *                                for an explanation.
	 * @return the temp dangerous goods items that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public TempDangerousGoodsItems updateTempDangerousGoodsItems(TempDangerousGoodsItems tempDangerousGoodsItems,
			boolean merge) throws SystemException {
		

		tempDangerousGoodsItems = persistence.updateImpl(tempDangerousGoodsItems, merge);

		return tempDangerousGoodsItems;
	}
}