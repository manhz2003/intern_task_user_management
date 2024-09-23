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
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.TempCrewDetailsPersistenceImpl;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

/**
 * The implementation of the temp crew details local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link vn.gt.dao.noticeandmessage.service.TempCrewDetailsLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.noticeandmessage.service.base.TempCrewDetailsLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.TempCrewDetailsLocalServiceUtil
 */
public class TempCrewDetailsLocalServiceImpl {
	@Autowired
	TempCrewDetailsPersistenceImpl persistence;
	

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link
	 * vn.gt.dao.noticeandmessage.service.TempCrewDetailsLocalServiceUtil} to access
	 * the temp crew details local service.
	 */
	public java.util.List<TempCrewDetails> findByRequestCode(java.lang.String requestCode)
			throws SystemException {
		return persistence.findByRequestCode(requestCode);
	}

	public java.util.List<TempCrewDetails> findByGivenNameAndPassportNumber(java.lang.String givenName,
			java.lang.String passportNumber) throws SystemException {
		return persistence.findByGivenNameAndPassportNumber(givenName, passportNumber);
	}

	public java.util.List<TempCrewDetails> findByCrewCode(java.lang.String crewCode)
			throws SystemException {
		return persistence.findByRequestCode(crewCode);
	}

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link
	 * vn.gt.dao.noticeandmessage.service.TempCrewDetailsLocalServiceUtil} to access
	 * the temp crew details local service.
	 */

	/**
	 * Adds the temp crew details to the database. Also notifies the appropriate
	 * model listeners.
	 *
	 * @param tempCrewDetails the temp crew details
	 * @return the temp crew details that was added
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewDetails addTempCrewDetails(TempCrewDetails tempCrewDetails) throws SystemException {
		

		tempCrewDetails = persistence.updateImpl(tempCrewDetails, false);

		return tempCrewDetails;
	}

	/**
	 * Creates a new temp crew details with the primary key. Does not add the temp
	 * crew details to the database.
	 *
	 * @param id the primary key for the new temp crew details
	 * @return the new temp crew details
	 */
	public TempCrewDetails createTempCrewDetails(long id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the temp crew details with the primary key from the database. Also
	 * notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the temp crew details
	 * @throws PortalException if a temp crew details with the primary key could not
	 *                         be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteTempCrewDetails(long id) throws PortalException, SystemException {
		TempCrewDetails tempCrewDetails = persistence.remove(id);

	}

	/**
	 * Deletes the temp crew details from the database. Also notifies the
	 * appropriate model listeners.
	 *
	 * @param tempCrewDetails the temp crew details
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteTempCrewDetails(TempCrewDetails tempCrewDetails) throws SystemException {
		persistence.remove(tempCrewDetails);

	}

	public TempCrewDetails fetchTempCrewDetails(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the temp crew details with the primary key.
	 *
	 * @param id the primary key of the temp crew details
	 * @return the temp crew details
	 * @throws PortalException if a temp crew details with the primary key could not
	 *                         be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewDetails getTempCrewDetails(long id) throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public TempCrewDetails getPersistedModel(Serializable primaryKeyObj) throws PortalException, SystemException {
		return persistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the temp crew detailses.
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
	 * @param start the lower bound of the range of temp crew detailses
	 * @param end   the upper bound of the range of temp crew detailses (not
	 *              inclusive)
	 * @return the range of temp crew detailses
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempCrewDetails> getTempCrewDetailses(int start, int end) throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of temp crew detailses.
	 *
	 * @return the number of temp crew detailses
	 * @throws SystemException if a system exception occurred
	 */
	public int getTempCrewDetailsesCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the temp crew details in the database or adds it if it does not yet
	 * exist. Also notifies the appropriate model listeners.
	 *
	 * @param tempCrewDetails the temp crew details
	 * @return the temp crew details that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewDetails updateTempCrewDetails(TempCrewDetails tempCrewDetails) throws SystemException {
		return updateTempCrewDetails(tempCrewDetails, true);
	}

	/**
	 * Updates the temp crew details in the database or adds it if it does not yet
	 * exist. Also notifies the appropriate model listeners.
	 *
	 * @param tempCrewDetails the temp crew details
	 * @param merge           whether to merge the temp crew details with the
	 *                        current session. See
	 *                        
	 *                        for an explanation.
	 * @return the temp crew details that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewDetails updateTempCrewDetails(TempCrewDetails tempCrewDetails, boolean merge)
			throws SystemException {
		

		tempCrewDetails = persistence.updateImpl(tempCrewDetails, merge);

		return tempCrewDetails;
	}
}