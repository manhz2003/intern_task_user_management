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

package com.fds.nsw.nghiepvu.noticeandmessage.service.impl;import java.util.ArrayList; import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.TempGoodsItemsPersistenceImpl;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;import lombok.extern.slf4j.Slf4j;@Slf4j @Service



/**
 * The implementation of the temp goods items local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link vn.gt.dao.noticeandmessage.service.TempGoodsItemsLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.noticeandmessage.service.base.TempGoodsItemsLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.TempGoodsItemsLocalServiceUtil
 */
public class TempGoodsItemsLocalServiceImpl
	{ @Autowired
	TempGoodsItemsPersistenceImpl persistence;	 
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link vn.gt.dao.noticeandmessage.service.TempGoodsItemsLocalServiceUtil} to access the temp goods items local service.
	 */
	public java.util.List<TempGoodsItems> findByRequestCode(
			java.lang.String requestCode)
			throws SystemException{
		return persistence.findByRequestCode(requestCode);
	}

		/*
		 * NOTE FOR DEVELOPERS:
		 *
		 * Never modify or reference this class directly. Always use {@link vn.gt.dao.noticeandmessage.service.TempGoodsItemsLocalServiceUtil} to access the temp goods items local service.
		 */

		/**
		 * Adds the temp goods items to the database. Also notifies the appropriate model listeners.
		 *
		 * @param tempGoodsItems the temp goods items
		 * @return the temp goods items that was added
		 * @throws SystemException if a system exception occurred
		 */
		public TempGoodsItems addTempGoodsItems(TempGoodsItems tempGoodsItems)
				throws SystemException {

			tempGoodsItems = persistence.updateImpl(tempGoodsItems, false);





			return tempGoodsItems;
		}

		/**
		 * Creates a new temp goods items with the primary key. Does not add the temp goods items to the database.
		 *
		 * @param id the primary key for the new temp goods items
		 * @return the new temp goods items
		 */
		public TempGoodsItems createTempGoodsItems(long id) {
			return persistence.create(id);
		}

		/**
		 * Deletes the temp goods items with the primary key from the database. Also notifies the appropriate model listeners.
		 *
		 * @param id the primary key of the temp goods items
		 * @throws PortalException if a temp goods items with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteTempGoodsItems(long id)
				throws PortalException, SystemException {
			TempGoodsItems tempGoodsItems = persistence.remove(id);




		}

		/**
		 * Deletes the temp goods items from the database. Also notifies the appropriate model listeners.
		 *
		 * @param tempGoodsItems the temp goods items
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteTempGoodsItems(TempGoodsItems tempGoodsItems)
				throws SystemException {
			persistence.remove(tempGoodsItems);




		}













		public TempGoodsItems fetchTempGoodsItems(long id)
				throws SystemException {
			return persistence.fetchByPrimaryKey(id);
		}

		/**
		 * Returns the temp goods items with the primary key.
		 *
		 * @param id the primary key of the temp goods items
		 * @return the temp goods items
		 * @throws PortalException if a temp goods items with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public TempGoodsItems getTempGoodsItems(long id)
				throws PortalException, SystemException {
			return persistence.findByPrimaryKey(id);
		}


		/**
		 * Returns a range of all the temp goods itemses.
		 *
		 * <p>
		 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
		 * </p>
		 *
		 * @param start the lower bound of the range of temp goods itemses
		 * @param end the upper bound of the range of temp goods itemses (not inclusive)
		 * @return the range of temp goods itemses
		 * @throws SystemException if a system exception occurred
		 */
		public List<TempGoodsItems> getTempGoodsItemses(int start, int end)
				throws SystemException {
			return persistence.findAll(start, end);
		}

		/**
		 * Returns the number of temp goods itemses.
		 *
		 * @return the number of temp goods itemses
		 * @throws SystemException if a system exception occurred
		 */
		public int getTempGoodsItemsesCount() throws SystemException {
			return persistence.countAll();
		}

		/**
		 * Updates the temp goods items in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param tempGoodsItems the temp goods items
		 * @return the temp goods items that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public TempGoodsItems updateTempGoodsItems(TempGoodsItems tempGoodsItems)
				throws SystemException {
			return updateTempGoodsItems(tempGoodsItems, true);
		}

		/**
		 * Updates the temp goods items in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param tempGoodsItems the temp goods items
		 * @param merge whether to merge the temp goods items with the current session. See  for an explanation.
		 * @return the temp goods items that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public TempGoodsItems updateTempGoodsItems(TempGoodsItems tempGoodsItems,
												   boolean merge) throws SystemException {

			tempGoodsItems = persistence.updateImpl(tempGoodsItems, merge);





			return tempGoodsItems;
		}
}