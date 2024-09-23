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

package com.fds.nsw.nghiepvu.danhmuc.service.impl;import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;

import com.fds.nsw.nghiepvu.danhmuc.service.finder.HistorySyncVersionFinderImpl;
import com.fds.nsw.nghiepvu.danhmuc.service.persistence.HistorySyncVersionPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import lombok.extern.slf4j.Slf4j;@Slf4j @Service









/**
 * The implementation of the history sync version local service.
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the
 * {@link vn.gt.dao.danhmuc.service.HistorySyncVersionLocalService} interface.
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can
 * only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.danhmuc.service.base.HistorySyncVersionLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.HistorySyncVersionLocalServiceUtil
 */
public class HistorySyncVersionLocalServiceImpl { @Autowired
HistorySyncVersionPersistenceImpl persistence;@Autowired
HistorySyncVersionFinderImpl finder;
	/*
	 * NOTE FOR DEVELOPERS: Never reference this interface directly. Always use {@link vn.gt.dao.danhmuc.service.HistorySyncVersionLocalServiceUtil}
	 * to access the history sync version local service.
	 */
	public String getsyncVersion(Date requestedDate, String categoryID) throws SystemException {
		List<HistorySyncVersion> historySyncVersions = finder.getsyncVersion(requestedDate, categoryID);
		if (historySyncVersions != null && historySyncVersions.size() > 0) {
			return historySyncVersions.get(0).getSyncVersion();
		}
		return "";
	}

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link vn.gt.dao.danhmuc.service.HistorySyncVersionLocalServiceUtil} to access the history sync version local service.
	 */

	/**
	 * Adds the history sync version to the database. Also notifies the appropriate model listeners.
	 *
	 * @param historySyncVersion the history sync version
	 * @return the history sync version that was added
	 * @throws SystemException if a system exception occurred
	 */
	public HistorySyncVersion addHistorySyncVersion(
			HistorySyncVersion historySyncVersion) throws SystemException {

		historySyncVersion = persistence.updateImpl(historySyncVersion,
				false);





		return historySyncVersion;
	}

	/**
	 * Creates a new history sync version with the primary key. Does not add the history sync version to the database.
	 *
	 * @param id the primary key for the new history sync version
	 * @return the new history sync version
	 */
	public HistorySyncVersion createHistorySyncVersion(long id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the history sync version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the history sync version
	 * @throws PortalException if a history sync version with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteHistorySyncVersion(long id)
			throws PortalException, SystemException {
		HistorySyncVersion historySyncVersion = persistence.remove(id);




	}

	/**
	 * Deletes the history sync version from the database. Also notifies the appropriate model listeners.
	 *
	 * @param historySyncVersion the history sync version
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteHistorySyncVersion(HistorySyncVersion historySyncVersion)
			throws SystemException {
		persistence.remove(historySyncVersion);




	}













	public HistorySyncVersion fetchHistorySyncVersion(long id)
			throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the history sync version with the primary key.
	 *
	 * @param id the primary key of the history sync version
	 * @return the history sync version
	 * @throws PortalException if a history sync version with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HistorySyncVersion getHistorySyncVersion(long id)
			throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}


	/**
	 * Returns a range of all the history sync versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of history sync versions
	 * @param end the upper bound of the range of history sync versions (not inclusive)
	 * @return the range of history sync versions
	 * @throws SystemException if a system exception occurred
	 */
	public List<HistorySyncVersion> getHistorySyncVersions(int start, int end)
			throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of history sync versions.
	 *
	 * @return the number of history sync versions
	 * @throws SystemException if a system exception occurred
	 */
	public int getHistorySyncVersionsCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the history sync version in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param historySyncVersion the history sync version
	 * @return the history sync version that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public HistorySyncVersion updateHistorySyncVersion(
			HistorySyncVersion historySyncVersion) throws SystemException {
		return updateHistorySyncVersion(historySyncVersion, true);
	}

	/**
	 * Updates the history sync version in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param historySyncVersion the history sync version
	 * @param merge whether to merge the history sync version with the current session. See  for an explanation.
	 * @return the history sync version that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public HistorySyncVersion updateHistorySyncVersion(
			HistorySyncVersion historySyncVersion, boolean merge)
			throws SystemException {

		historySyncVersion = persistence.updateImpl(historySyncVersion,
				merge);





		return historySyncVersion;
	}
}