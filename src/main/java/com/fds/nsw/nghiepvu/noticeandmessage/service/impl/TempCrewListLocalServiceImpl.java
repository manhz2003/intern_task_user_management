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
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.TempCrewListFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.TempCrewListPersistenceImpl;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

/**
 * The implementation of the temp crew list local service.
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link vn.gt.dao.noticeandmessage.service.TempCrewListLocalService}
 * interface.
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.noticeandmessage.service.base.TempCrewListLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.TempCrewListLocalServiceUtil
 */
public class TempCrewListLocalServiceImpl {
	@Autowired
	TempCrewListPersistenceImpl persistence;
	@Autowired
	TempCrewListFinderImpl finder;

	/*
	 * NOTE FOR DEVELOPERS: Never reference this interface directly. Always use
	 * {@link vn.gt.dao.noticeandmessage.service.TempCrewListLocalServiceUtil} to
	 * access the temp crew list local service.
	 */
	public List<TempCrewList> findBydocumentNameAnddocumentYear(long documentName, int documentYear)
			throws SystemException {
		return finder.findBydocumentNameAnddocumentYear(documentName, documentYear);
	}

	public List<TempCrewList> findBydocumentNameAnddocumentYear(long documentName, int documentYear, int start, int end)
			throws SystemException {
		return persistence.findBydocumentNameAnddocumentYear(documentName, documentYear, start, end);
	}

	public List<TempCrewList> findByRequestCode(java.lang.String requestCode) throws SystemException {
		return persistence.findByRequestCode(requestCode);
	}

	public TempCrewList findTempCrewListByRequestCode(String requestCode) {
		try {
			return finder.findTempCrewListByRequestCode(requestCode);
		} catch (Exception es) {
			es.printStackTrace();
		}
		return null;
	}

	public TempCrewList getLastByDocumentNameAndDocumentYear(long documentName, int documentYear)
			throws SystemException {
		try {
			return finder.getLastByDocumentNameAndDocumentYear(documentName, documentYear);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<TempCrewList> findByDocumentNameAndDocumentYearOrderByDescRequestDate(long documentName,
			int documentYear) throws SystemException {
		return finder.findByDocumentNameAndDocumentYearOrderByDescRequestDate(documentName, documentYear);
	}

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link
	 * vn.gt.dao.noticeandmessage.service.TempCrewListLocalServiceUtil} to access
	 * the temp crew list local service.
	 */

	/**
	 * Adds the temp crew list to the database. Also notifies the appropriate model
	 * listeners.
	 *
	 * @param tempCrewList the temp crew list
	 * @return the temp crew list that was added
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewList addTempCrewList(TempCrewList tempCrewList) throws SystemException {
		

		tempCrewList = persistence.updateImpl(tempCrewList, false);

		return tempCrewList;
	}

	/**
	 * Creates a new temp crew list with the primary key. Does not add the temp crew
	 * list to the database.
	 *
	 * @param id the primary key for the new temp crew list
	 * @return the new temp crew list
	 */
	public TempCrewList createTempCrewList(long id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the temp crew list with the primary key from the database. Also
	 * notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the temp crew list
	 * @throws PortalException if a temp crew list with the primary key could not be
	 *                         found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteTempCrewList(long id) throws PortalException, SystemException {
		TempCrewList tempCrewList = persistence.remove(id);

	}

	/**
	 * Deletes the temp crew list from the database. Also notifies the appropriate
	 * model listeners.
	 *
	 * @param tempCrewList the temp crew list
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteTempCrewList(TempCrewList tempCrewList) throws SystemException {
		persistence.remove(tempCrewList);

	}

	public TempCrewList fetchTempCrewList(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the temp crew list with the primary key.
	 *
	 * @param id the primary key of the temp crew list
	 * @return the temp crew list
	 * @throws PortalException if a temp crew list with the primary key could not be
	 *                         found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewList getTempCrewList(long id) throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public TempCrewList getPersistedModel(Serializable primaryKeyObj) throws PortalException, SystemException {
		return persistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the temp crew lists.
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
	 * @param start the lower bound of the range of temp crew lists
	 * @param end   the upper bound of the range of temp crew lists (not inclusive)
	 * @return the range of temp crew lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempCrewList> getTempCrewLists(int start, int end) throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of temp crew lists.
	 *
	 * @return the number of temp crew lists
	 * @throws SystemException if a system exception occurred
	 */
	public int getTempCrewListsCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the temp crew list in the database or adds it if it does not yet
	 * exist. Also notifies the appropriate model listeners.
	 *
	 * @param tempCrewList the temp crew list
	 * @return the temp crew list that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewList updateTempCrewList(TempCrewList tempCrewList) throws SystemException {
		return updateTempCrewList(tempCrewList, true);
	}

	/**
	 * Updates the temp crew list in the database or adds it if it does not yet
	 * exist. Also notifies the appropriate model listeners.
	 *
	 * @param tempCrewList the temp crew list
	 * @param merge        whether to merge the temp crew list with the current
	 *                     session. See
	 *                     
	 *                     for an explanation.
	 * @return the temp crew list that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewList updateTempCrewList(TempCrewList tempCrewList, boolean merge) throws SystemException {
		

		tempCrewList = persistence.updateImpl(tempCrewList, merge);

		return tempCrewList;
	}
}