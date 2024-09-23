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
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.TempCargoItemsPersistenceImpl;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

/**
 * The implementation of the temp cargo items local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link vn.gt.dao.noticeandmessage.service.TempCargoItemsLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.noticeandmessage.service.base.TempCargoItemsLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.tempCargoItemsLocalService
 */
public class TempCargoItemsLocalServiceImpl {
	@Autowired
	TempCargoItemsPersistenceImpl persistence;


	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link
	 * vn.gt.dao.noticeandmessage.service.tempCargoItemsLocalService} to access
	 * the temp cargo items local service.
	 */
	public List<TempCargoItems> findByRequestCode(String requestCode) {
		try {
			return persistence.findByRequestCode(requestCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<TempCargoItems>();
	}

	public List<TempCargoItems> findBydocumentNameAnddocumentYearAndRequestCode(long documentName, int documentYear,
			String requestCode) {
		try {
			return persistence.findBydocumentNameAnddocumentYearAndRequestCode(documentName, documentYear,
					requestCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<TempCargoItems>();
	}

	public List<TempCargoItems> findBydocumentNameAnddocumentYear(long documentName, int documentYear) {
		try {
			return persistence.findBydocumentNameAnddocumentYear(documentName, documentYear);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<TempCargoItems>();
	}

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link
	 * vn.gt.dao.noticeandmessage.service.tempCargoItemsLocalService} to access
	 * the temp cargo items local service.
	 */

	/**
	 * Adds the temp cargo items to the database. Also notifies the appropriate
	 * model listeners.
	 *
	 * @param tempCargoItems the temp cargo items
	 * @return the temp cargo items that was added
	 * @throws SystemException if a system exception occurred
	 */
	public TempCargoItems addTempCargoItems(TempCargoItems tempCargoItems) throws SystemException {
		

		tempCargoItems = persistence.updateImpl(tempCargoItems, false);

		return tempCargoItems;
	}

	/**
	 * Creates a new temp cargo items with the primary key. Does not add the temp
	 * cargo items to the database.
	 *
	 * @param id the primary key for the new temp cargo items
	 * @return the new temp cargo items
	 */
	public TempCargoItems createTempCargoItems(long id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the temp cargo items with the primary key from the database. Also
	 * notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the temp cargo items
	 * @throws PortalException if a temp cargo items with the primary key could not
	 *                         be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteTempCargoItems(long id) throws PortalException, SystemException {
		TempCargoItems tempCargoItems = persistence.remove(id);

	}

	/**
	 * Deletes the temp cargo items from the database. Also notifies the appropriate
	 * model listeners.
	 *
	 * @param tempCargoItems the temp cargo items
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteTempCargoItems(TempCargoItems tempCargoItems) throws SystemException {
		persistence.remove(tempCargoItems);

	}

	public TempCargoItems fetchTempCargoItems(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the temp cargo items with the primary key.
	 *
	 * @param id the primary key of the temp cargo items
	 * @return the temp cargo items
	 * @throws PortalException if a temp cargo items with the primary key could not
	 *                         be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCargoItems getTempCargoItems(long id) throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public TempCargoItems getPersistedModel(Serializable primaryKeyObj) throws PortalException, SystemException {
		return persistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the temp cargo itemses.
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
	 * @param start the lower bound of the range of temp cargo itemses
	 * @param end   the upper bound of the range of temp cargo itemses (not
	 *              inclusive)
	 * @return the range of temp cargo itemses
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempCargoItems> getTempCargoItemses(int start, int end) throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of temp cargo itemses.
	 *
	 * @return the number of temp cargo itemses
	 * @throws SystemException if a system exception occurred
	 */
	public int getTempCargoItemsesCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the temp cargo items in the database or adds it if it does not yet
	 * exist. Also notifies the appropriate model listeners.
	 *
	 * @param tempCargoItems the temp cargo items
	 * @return the temp cargo items that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public TempCargoItems updateTempCargoItems(TempCargoItems tempCargoItems) throws SystemException {
		return updateTempCargoItems(tempCargoItems, true);
	}

	/**
	 * Updates the temp cargo items in the database or adds it if it does not yet
	 * exist. Also notifies the appropriate model listeners.
	 *
	 * @param tempCargoItems the temp cargo items
	 * @param merge          whether to merge the temp cargo items with the current
	 *                       session. See
	 *                       
	 *                       for an explanation.
	 * @return the temp cargo items that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public TempCargoItems updateTempCargoItems(TempCargoItems tempCargoItems, boolean merge) throws SystemException {
		

		tempCargoItems = persistence.updateImpl(tempCargoItems, merge);

		return tempCargoItems;
	}
}