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
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.TempCrewEffectsItemsFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.TempCrewEffectsItemsPersistenceImpl;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

/**
 * The implementation of the temp crew effects items local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link vn.gt.dao.noticeandmessage.service.TempCrewEffectsItemsLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.noticeandmessage.service.base.TempCrewEffectsItemsLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.TempCrewEffectsItemsLocalServiceUtil
 */
public class TempCrewEffectsItemsLocalServiceImpl {
	@Autowired
	TempCrewEffectsItemsPersistenceImpl persistence;
	@Autowired
	TempCrewEffectsItemsFinderImpl finder;

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link
	 * vn.gt.dao.noticeandmessage.service.TempCrewEffectsItemsLocalServiceUtil} to
	 * access the temp crew effects items local service.
	 */
	public java.util.List<TempCrewEffectsItems> findByRequestCode(java.lang.String requestCode)
			throws SystemException {
		return persistence.findByRequestCode(requestCode);
	}

	public TempCrewEffectsItems findTempCrewEffectsItemsByRequestCode(String requestCode) {
		try {
			return finder.findTempCrewEffectsItemsByRequestCode(requestCode);
		} catch (Exception es) {
			es.printStackTrace();
		}
		return null;
	}

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link
	 * vn.gt.dao.noticeandmessage.service.TempCrewEffectsItemsLocalServiceUtil} to
	 * access the temp crew effects items local service.
	 */

	/**
	 * Adds the temp crew effects items to the database. Also notifies the
	 * appropriate model listeners.
	 *
	 * @param tempCrewEffectsItems the temp crew effects items
	 * @return the temp crew effects items that was added
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewEffectsItems addTempCrewEffectsItems(TempCrewEffectsItems tempCrewEffectsItems)
			throws SystemException {
		

		tempCrewEffectsItems = persistence.updateImpl(tempCrewEffectsItems, false);

		return tempCrewEffectsItems;
	}

	/**
	 * Creates a new temp crew effects items with the primary key. Does not add the
	 * temp crew effects items to the database.
	 *
	 * @param id the primary key for the new temp crew effects items
	 * @return the new temp crew effects items
	 */
	public TempCrewEffectsItems createTempCrewEffectsItems(long id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the temp crew effects items with the primary key from the database.
	 * Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the temp crew effects items
	 * @throws PortalException if a temp crew effects items with the primary key
	 *                         could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteTempCrewEffectsItems(long id) throws PortalException, SystemException {
		TempCrewEffectsItems tempCrewEffectsItems = persistence.remove(id);

	}

	/**
	 * Deletes the temp crew effects items from the database. Also notifies the
	 * appropriate model listeners.
	 *
	 * @param tempCrewEffectsItems the temp crew effects items
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteTempCrewEffectsItems(TempCrewEffectsItems tempCrewEffectsItems) throws SystemException {
		persistence.remove(tempCrewEffectsItems);

	}

	public TempCrewEffectsItems fetchTempCrewEffectsItems(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the temp crew effects items with the primary key.
	 *
	 * @param id the primary key of the temp crew effects items
	 * @return the temp crew effects items
	 * @throws PortalException if a temp crew effects items with the primary key
	 *                         could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewEffectsItems getTempCrewEffectsItems(long id) throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public TempCrewEffectsItems getPersistedModel(Serializable primaryKeyObj) throws PortalException, SystemException {
		return persistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the temp crew effects itemses.
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
	 * @param start the lower bound of the range of temp crew effects itemses
	 * @param end   the upper bound of the range of temp crew effects itemses (not
	 *              inclusive)
	 * @return the range of temp crew effects itemses
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempCrewEffectsItems> getTempCrewEffectsItemses(int start, int end) throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of temp crew effects itemses.
	 *
	 * @return the number of temp crew effects itemses
	 * @throws SystemException if a system exception occurred
	 */
	public int getTempCrewEffectsItemsesCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the temp crew effects items in the database or adds it if it does not
	 * yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param tempCrewEffectsItems the temp crew effects items
	 * @return the temp crew effects items that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewEffectsItems updateTempCrewEffectsItems(TempCrewEffectsItems tempCrewEffectsItems)
			throws SystemException {
		return updateTempCrewEffectsItems(tempCrewEffectsItems, true);
	}

	/**
	 * Updates the temp crew effects items in the database or adds it if it does not
	 * yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param tempCrewEffectsItems the temp crew effects items
	 * @param merge                whether to merge the temp crew effects items with
	 *                             the current session. See
	 *                             
	 *                             for an explanation.
	 * @return the temp crew effects items that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewEffectsItems updateTempCrewEffectsItems(TempCrewEffectsItems tempCrewEffectsItems, boolean merge)
			throws SystemException {
		

		tempCrewEffectsItems = persistence.updateImpl(tempCrewEffectsItems, merge);

		return tempCrewEffectsItems;
	}
}