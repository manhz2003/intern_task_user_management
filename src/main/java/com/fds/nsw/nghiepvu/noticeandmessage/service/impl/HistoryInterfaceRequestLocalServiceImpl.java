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

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.HistoryInterfaceRequestFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.HistoryInterfaceRequestPersistenceImpl;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

/**
 * The implementation of the history interface request local service.
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link vn.gt.dao.noticeandmessage.service.HistoryInterfaceRequestLocalService}
 * interface.
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.noticeandmessage.service.base.HistoryInterfaceRequestLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.historyInterfaceRequestLocalService
 */
public class HistoryInterfaceRequestLocalServiceImpl {
	@Autowired
	HistoryInterfaceRequestPersistenceImpl persistence;
	@Autowired
	HistoryInterfaceRequestFinderImpl finder;
	/*
	 * NOTE FOR DEVELOPERS: Never reference this interface directly. Always use
	 * {@link
	 * vn.gt.dao.noticeandmessage.service.historyInterfaceRequestLocalService}
	 * to access the history interface request local service.
	 */

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link
	 * vn.gt.dao.noticeandmessage.service.historyInterfaceRequestLocalService}
	 * to access the history interface request local service.
	 */

	/**
	 * Adds the history interface request to the database. Also notifies the
	 * appropriate model listeners.
	 *
	 * @param historyInterfaceRequest the history interface request
	 * @return the history interface request that was added
	 * @throws SystemException if a system exception occurred
	 */
	public HistoryInterfaceRequest addHistoryInterfaceRequest(HistoryInterfaceRequest historyInterfaceRequest)
			throws SystemException {

		historyInterfaceRequest = persistence.updateImpl(historyInterfaceRequest, false);

		return historyInterfaceRequest;
	}

	/**
	 * Creates a new history interface request with the primary key. Does not add
	 * the history interface request to the database.
	 *
	 * @param id the primary key for the new history interface request
	 * @return the new history interface request
	 */
	public HistoryInterfaceRequest createHistoryInterfaceRequest(long id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the history interface request with the primary key from the database.
	 * Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the history interface request
	 * @throws PortalException if a history interface request with the primary key
	 *                         could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteHistoryInterfaceRequest(long id) throws PortalException, SystemException {
		HistoryInterfaceRequest historyInterfaceRequest = persistence.remove(id);

	}

	/**
	 * Deletes the history interface request from the database. Also notifies the
	 * appropriate model listeners.
	 *
	 * @param historyInterfaceRequest the history interface request
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteHistoryInterfaceRequest(HistoryInterfaceRequest historyInterfaceRequest) throws SystemException {
		persistence.remove(historyInterfaceRequest);

	}

	public HistoryInterfaceRequest fetchHistoryInterfaceRequest(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the history interface request with the primary key.
	 *
	 * @param id the primary key of the history interface request
	 * @return the history interface request
	 * @throws PortalException if a history interface request with the primary key
	 *                         could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HistoryInterfaceRequest getHistoryInterfaceRequest(long id) throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	/**
	 * Returns a range of all the history interface requests.
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
	 * @param start the lower bound of the range of history interface requests
	 * @param end   the upper bound of the range of history interface requests (not
	 *              inclusive)
	 * @return the range of history interface requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<HistoryInterfaceRequest> getHistoryInterfaceRequests(int start, int end) throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of history interface requests.
	 *
	 * @return the number of history interface requests
	 * @throws SystemException if a system exception occurred
	 */
	public int getHistoryInterfaceRequestsCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the history interface request in the database or adds it if it does
	 * not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param historyInterfaceRequest the history interface request
	 * @return the history interface request that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public HistoryInterfaceRequest updateHistoryInterfaceRequest(HistoryInterfaceRequest historyInterfaceRequest)
			throws SystemException {
		return updateHistoryInterfaceRequest(historyInterfaceRequest, true);
	}

	/**
	 * Updates the history interface request in the database or adds it if it does
	 * not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param historyInterfaceRequest the history interface request
	 * @param merge                   whether to merge the history interface request
	 *                                with the current session. See
	 *                                
	 *                                for an explanation.
	 * @return the history interface request that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public HistoryInterfaceRequest updateHistoryInterfaceRequest(HistoryInterfaceRequest historyInterfaceRequest,
			boolean merge) throws SystemException {

		historyInterfaceRequest = persistence.updateImpl(historyInterfaceRequest, merge);

		return historyInterfaceRequest;
	}

	public HistoryInterfaceRequest findByRequestCode(String requestCode) {
		try {
			return finder.findHistoryInterfaceRequestByRequestCode(requestCode);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public HistoryInterfaceRequest findHistoryInterfaceRequestByRequestCode(String requestCode) {
		try {
			return finder.findHistoryInterfaceRequestByRequestCode(requestCode);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public int updateHistoryInterfaceRequest(String sql) {
		try {
			return finder.updateHistoryInterfaceRequest(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}