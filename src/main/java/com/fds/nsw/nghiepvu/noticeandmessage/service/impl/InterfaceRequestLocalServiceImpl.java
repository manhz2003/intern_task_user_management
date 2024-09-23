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
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.InterfaceRequestFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.InterfaceRequestPersistenceImpl;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

/**
 * The implementation of the interface request local service.
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link vn.gt.dao.noticeandmessage.service.InterfaceRequestLocalService}
 * interface.
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.noticeandmessage.service.base.InterfaceRequestLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.interfaceRequestLocalService
 */
public class InterfaceRequestLocalServiceImpl {
	@Autowired
	InterfaceRequestPersistenceImpl persistence;
	@Autowired
	InterfaceRequestFinderImpl finder;

	public InterfaceRequest findByRequestCode(String requestCode) {
		try {
			return finder.findInterfaceRequestByRequestCode(requestCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public InterfaceRequest findInterfaceRequestByRequestCode(String requestCode) {
		try {
			return finder.findInterfaceRequestByRequestCode(requestCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Date getRequestedDateByRequestCode(String requestCode) {
		try {
			return finder.getRequestedDateByRequestCode(requestCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getFunctionTypeByRequestCode(String requestCode) {
		try {
			return finder.getFunctionTypeByRequestCode(requestCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getRemarksByRequestCode(String requestCode) {
		try {
			return finder.getRemarksByRequestCode(requestCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int updateInterfaceRequest(String sql) {
		try {
			return finder.updateInterfaceRequest(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public InterfaceRequest fetchByF_BY_documentNameRef(String documentNameRef, String businessType) {
		try {
			return persistence.fetchByF_BY_documentNameRef(documentNameRef, businessType);
		} catch (SystemException e) {
			return null;
		}
	}

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link
	 * vn.gt.dao.noticeandmessage.service.interfaceRequestLocalService} to
	 * access the interface request local service.
	 */

	/**
	 * Adds the interface request to the database. Also notifies the appropriate
	 * model listeners.
	 *
	 * @param interfaceRequest the interface request
	 * @return the interface request that was added
	 * @throws SystemException if a system exception occurred
	 */
	public InterfaceRequest addInterfaceRequest(InterfaceRequest interfaceRequest) throws SystemException {
		

		interfaceRequest = persistence.updateImpl(interfaceRequest, false);

		return interfaceRequest;
	}

	/**
	 * Creates a new interface request with the primary key. Does not add the
	 * interface request to the database.
	 *
	 * @param id the primary key for the new interface request
	 * @return the new interface request
	 */
	public InterfaceRequest createInterfaceRequest(long id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the interface request with the primary key from the database. Also
	 * notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the interface request
	 * @throws PortalException if a interface request with the primary key could not
	 *                         be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteInterfaceRequest(long id) throws PortalException, SystemException {
		InterfaceRequest interfaceRequest = persistence.remove(id);

	}

	/**
	 * Deletes the interface request from the database. Also notifies the
	 * appropriate model listeners.
	 *
	 * @param interfaceRequest the interface request
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteInterfaceRequest(InterfaceRequest interfaceRequest) throws SystemException {
		persistence.remove(interfaceRequest);

	}

	public InterfaceRequest fetchInterfaceRequest(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the interface request with the primary key.
	 *
	 * @param id the primary key of the interface request
	 * @return the interface request
	 * @throws PortalException if a interface request with the primary key could not
	 *                         be found
	 * @throws SystemException if a system exception occurred
	 */
	public InterfaceRequest getInterfaceRequest(long id) throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}



	/**
	 * Returns a range of all the interface requests.
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
	 * @param start the lower bound of the range of interface requests
	 * @param end   the upper bound of the range of interface requests (not
	 *              inclusive)
	 * @return the range of interface requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<InterfaceRequest> getInterfaceRequests(int start, int end) throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of interface requests.
	 *
	 * @return the number of interface requests
	 * @throws SystemException if a system exception occurred
	 */
	public int getInterfaceRequestsCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the interface request in the database or adds it if it does not yet
	 * exist. Also notifies the appropriate model listeners.
	 *
	 * @param interfaceRequest the interface request
	 * @return the interface request that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public InterfaceRequest updateInterfaceRequest(InterfaceRequest interfaceRequest) throws SystemException {
		return updateInterfaceRequest(interfaceRequest, true);
	}

	/**
	 * Updates the interface request in the database or adds it if it does not yet
	 * exist. Also notifies the appropriate model listeners.
	 *
	 * @param interfaceRequest the interface request
	 * @param merge            whether to merge the interface request with the
	 *                         current session. See
	 *                         
	 *                         for an explanation.
	 * @return the interface request that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public InterfaceRequest updateInterfaceRequest(InterfaceRequest interfaceRequest, boolean merge)
			throws SystemException {
		

		interfaceRequest = persistence.updateImpl(interfaceRequest, merge);

		return interfaceRequest;
	}
}