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
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.TempAnimalQuarantineFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.TempAnimalQuarantinePersistenceImpl;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TempAnimalQuarantineLocalServiceImpl {
	@Autowired
	TempAnimalQuarantinePersistenceImpl persistence;
	@Autowired
	TempAnimalQuarantineFinderImpl finder;
	/*
	 * NOTE FOR DEVELOPERS: Never reference this interface directly. Always use
	 * {@link
	 * vn.gt.dao.noticeandmessage.service.TempAnimalQuarantineLocalServiceUtil} to
	 * access the temp animal quarantine local service.
	 */

	public List<TempAnimalQuarantine> findBydocumentNameAnddocumentYear(long documentName, int documentYear) {
		try {
			return finder.findBydocumentNameAnddocumentYear(documentName, documentYear);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<TempAnimalQuarantine> findBydocumentNameAnddocumentYearRequestState(long documentName, int documentYear,
			int requestState) {
		try {
			return persistence.findBydocumentNameAnddocumentYearRequestState(documentName,
					documentYear, requestState);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int countBydocumentNameAnddocumentYear(long documentName, int documentYear) {
		try {
			return finder.countBydocumentNameAnddocumentYear(documentName, documentYear);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	public List<TempAnimalQuarantine> findByRequestCode(String requestCode) {

		try {
			return persistence.findByRequestCode(requestCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public TempAnimalQuarantine getLastByDocumentNameAndDocumentYear(long documentName, int documentYear) {
		try {
			return finder.getLastByDocumentNameAndDocumentYear(documentName, documentYear);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<TempAnimalQuarantine> findByDocumentNameAndDocumentYearOrderByDescRequestDate(long documentName,
			int documentYear) {
		return finder.findByDocumentNameAndDocumentYearOrderByDescRequestDate(documentName,
				documentYear);
	}

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link
	 * vn.gt.dao.noticeandmessage.service.TempAnimalQuarantineLocalServiceUtil} to
	 * access the temp animal quarantine local service.
	 */

	/**
	 * Adds the temp animal quarantine to the database. Also notifies the
	 * appropriate model listeners.
	 *
	 * @param tempAnimalQuarantine the temp animal quarantine
	 * @return the temp animal quarantine that was added
	 * @throws SystemException if a system exception occurred
	 */
	public TempAnimalQuarantine addTempAnimalQuarantine(TempAnimalQuarantine tempAnimalQuarantine)
			throws SystemException {
		

		tempAnimalQuarantine = persistence.updateImpl(tempAnimalQuarantine, false);

		return tempAnimalQuarantine;
	}

	/**
	 * Creates a new temp animal quarantine with the primary key. Does not add the
	 * temp animal quarantine to the database.
	 *
	 * @param id the primary key for the new temp animal quarantine
	 * @return the new temp animal quarantine
	 */
	public TempAnimalQuarantine createTempAnimalQuarantine(long id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the temp animal quarantine with the primary key from the database.
	 * Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the temp animal quarantine
	 * @throws PortalException if a temp animal quarantine with the primary key
	 *                         could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteTempAnimalQuarantine(long id) throws PortalException, SystemException {
		TempAnimalQuarantine tempAnimalQuarantine = persistence.remove(id);

	}

	/**
	 * Deletes the temp animal quarantine from the database. Also notifies the
	 * appropriate model listeners.
	 *
	 * @param tempAnimalQuarantine the temp animal quarantine
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteTempAnimalQuarantine(TempAnimalQuarantine tempAnimalQuarantine) throws SystemException {
		persistence.remove(tempAnimalQuarantine);

	}

	public TempAnimalQuarantine fetchTempAnimalQuarantine(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the temp animal quarantine with the primary key.
	 *
	 * @param id the primary key of the temp animal quarantine
	 * @return the temp animal quarantine
	 * @throws PortalException if a temp animal quarantine with the primary key
	 *                         could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempAnimalQuarantine getTempAnimalQuarantine(long id) throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public TempAnimalQuarantine getPersistedModel(Serializable primaryKeyObj) throws PortalException, SystemException {
		return persistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the temp animal quarantines.
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
	 * @param start the lower bound of the range of temp animal quarantines
	 * @param end   the upper bound of the range of temp animal quarantines (not
	 *              inclusive)
	 * @return the range of temp animal quarantines
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempAnimalQuarantine> getTempAnimalQuarantines(int start, int end) throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of temp animal quarantines.
	 *
	 * @return the number of temp animal quarantines
	 * @throws SystemException if a system exception occurred
	 */
	public int getTempAnimalQuarantinesCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the temp animal quarantine in the database or adds it if it does not
	 * yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param tempAnimalQuarantine the temp animal quarantine
	 * @return the temp animal quarantine that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public TempAnimalQuarantine updateTempAnimalQuarantine(TempAnimalQuarantine tempAnimalQuarantine)
			throws SystemException {
		return updateTempAnimalQuarantine(tempAnimalQuarantine, true);
	}

	/**
	 * Updates the temp animal quarantine in the database or adds it if it does not
	 * yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param tempAnimalQuarantine the temp animal quarantine
	 * @param merge                whether to merge the temp animal quarantine with
	 *                             the current session. See
	 *                             
	 *                             for an explanation.
	 * @return the temp animal quarantine that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public TempAnimalQuarantine updateTempAnimalQuarantine(TempAnimalQuarantine tempAnimalQuarantine, boolean merge)
			throws SystemException {
		

		tempAnimalQuarantine = persistence.updateImpl(tempAnimalQuarantine, merge);

		return tempAnimalQuarantine;
	}

}