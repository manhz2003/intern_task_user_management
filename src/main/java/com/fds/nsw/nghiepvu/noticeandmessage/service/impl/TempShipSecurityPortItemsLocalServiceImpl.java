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
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.TempShipSecurityPortItemsFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.TempShipSecurityPortItemsPersistenceImpl;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;import lombok.extern.slf4j.Slf4j;@Slf4j @Service








/**
 * The implementation of the temp ship security port items local service.
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the
 * {@link vn.gt.dao.noticeandmessage.service.TempShipSecurityPortItemsLocalService} interface.
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can
 * only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.noticeandmessage.service.base.TempShipSecurityPortItemsLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.TempShipSecurityPortItemsLocalServiceUtil
 */
public class TempShipSecurityPortItemsLocalServiceImpl { @Autowired
TempShipSecurityPortItemsPersistenceImpl persistence;@Autowired
TempShipSecurityPortItemsFinderImpl finder;
	/*
	 * NOTE FOR DEVELOPERS: Never reference this interface directly. Always use {@link
	 * vn.gt.dao.noticeandmessage.service.TempShipSecurityPortItemsLocalServiceUtil} to access the temp ship security port items local service.
	 */
	public List<TempShipSecurityPortItems> findByRequestCode(String requestCode) throws SystemException {
		return persistence.findByRequestCode(requestCode);
	}
	
	public List<TempShipSecurityPortItems> findByRequestCodeOrderByDateArrivalASC(String requestCode) throws SystemException {
		return finder.findByRequestCodeOrderByDateArrivalASC(requestCode);
	}

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link vn.gt.dao.noticeandmessage.service.TempShipSecurityPortItemsLocalServiceUtil} to access the temp ship security port items local service.
	 */

	/**
	 * Adds the temp ship security port items to the database. Also notifies the appropriate model listeners.
	 *
	 * @param tempShipSecurityPortItems the temp ship security port items
	 * @return the temp ship security port items that was added
	 * @throws SystemException if a system exception occurred
	 */
	public TempShipSecurityPortItems addTempShipSecurityPortItems(
			TempShipSecurityPortItems tempShipSecurityPortItems)
			throws SystemException {

		tempShipSecurityPortItems = persistence.updateImpl(tempShipSecurityPortItems,
				false);





		return tempShipSecurityPortItems;
	}

	/**
	 * Creates a new temp ship security port items with the primary key. Does not add the temp ship security port items to the database.
	 *
	 * @param id the primary key for the new temp ship security port items
	 * @return the new temp ship security port items
	 */
	public TempShipSecurityPortItems createTempShipSecurityPortItems(long id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the temp ship security port items with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the temp ship security port items
	 * @throws PortalException if a temp ship security port items with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteTempShipSecurityPortItems(long id)
			throws PortalException, SystemException {
		TempShipSecurityPortItems tempShipSecurityPortItems = persistence.remove(id);




	}

	/**
	 * Deletes the temp ship security port items from the database. Also notifies the appropriate model listeners.
	 *
	 * @param tempShipSecurityPortItems the temp ship security port items
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteTempShipSecurityPortItems(
			TempShipSecurityPortItems tempShipSecurityPortItems)
			throws SystemException {
		persistence.remove(tempShipSecurityPortItems);




	}













	public TempShipSecurityPortItems fetchTempShipSecurityPortItems(long id)
			throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the temp ship security port items with the primary key.
	 *
	 * @param id the primary key of the temp ship security port items
	 * @return the temp ship security port items
	 * @throws PortalException if a temp ship security port items with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempShipSecurityPortItems getTempShipSecurityPortItems(long id)
			throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}



	/**
	 * Returns a range of all the temp ship security port itemses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of temp ship security port itemses
	 * @param end the upper bound of the range of temp ship security port itemses (not inclusive)
	 * @return the range of temp ship security port itemses
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempShipSecurityPortItems> getTempShipSecurityPortItemses(
			int start, int end) throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of temp ship security port itemses.
	 *
	 * @return the number of temp ship security port itemses
	 * @throws SystemException if a system exception occurred
	 */
	public int getTempShipSecurityPortItemsesCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the temp ship security port items in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param tempShipSecurityPortItems the temp ship security port items
	 * @return the temp ship security port items that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public TempShipSecurityPortItems updateTempShipSecurityPortItems(
			TempShipSecurityPortItems tempShipSecurityPortItems)
			throws SystemException {
		return updateTempShipSecurityPortItems(tempShipSecurityPortItems, true);
	}

	/**
	 * Updates the temp ship security port items in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param tempShipSecurityPortItems the temp ship security port items
	 * @param merge whether to merge the temp ship security port items with the current session. See  for an explanation.
	 * @return the temp ship security port items that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public TempShipSecurityPortItems updateTempShipSecurityPortItems(
			TempShipSecurityPortItems tempShipSecurityPortItems, boolean merge)
			throws SystemException {

		tempShipSecurityPortItems = persistence.updateImpl(tempShipSecurityPortItems,
				merge);





		return tempShipSecurityPortItems;
	}
}