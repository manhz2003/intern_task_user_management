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
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.TempPassengerListFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.TempPassengerListPersistenceImpl;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;import lombok.extern.slf4j.Slf4j;@Slf4j @Service


public class TempPassengerListLocalServiceImpl { @Autowired
TempPassengerListPersistenceImpl persistence;@Autowired
TempPassengerListFinderImpl finder;
	/*
	 * NOTE FOR DEVELOPERS: Never reference this interface directly. Always use {@link
	 * vn.gt.dao.noticeandmessage.service.TempPassengerListLocalServiceUtil} to access the temp passenger list local service.
	 */
	public List<TempPassengerList> findBydocumentNameAnddocumentYear(long documentName, int documentYear) throws SystemException {
		return finder.findBydocumentNameAnddocumentYear(documentName, documentYear);
	}
	
	public List<TempPassengerList> findBydocumentNameAnddocumentYearRequestState(long documentName, int documentYear, int requestState) {
		try {
			return persistence.findBydocumentNameAnddocumentYearRequestState(documentName, documentYear, requestState);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<TempPassengerList> findBydocumentNameAndDocumentYear(long documentName, int documentYear, int start, int end) throws SystemException {
		return persistence.findBydocumentNameAnddocumentYear(documentName, documentYear, start, end);
	}
	
	public List<TempPassengerList> findByRequestCode(java.lang.String requestCode) throws SystemException {
		return persistence.findByRequestCode(requestCode);
	}
	
	public TempPassengerList findTempPassengerListByRequestCode(String requestCode) {
		try {
			return finder.findTempPassengerListByRequestCode(requestCode);
		} catch (Exception es) {
			es.printStackTrace();
		}
		return null;
	}
	
	public TempPassengerList getLastByDocumentNameAndDocumentYear(long documentName, int documentYear) {
		try {
			return finder.getLastByDocumentNameAndDocumentYear(documentName, documentYear);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<TempPassengerList> findByDocumentNameAndDocumentYearOrderByDescRequestDate(long documentName, int documentYear) throws SystemException {
		return finder.findByDocumentNameAndDocumentYearOrderByDescRequestDate(documentName, documentYear);
	}


	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link vn.gt.dao.noticeandmessage.service.TempPassengerListLocalServiceUtil} to access the temp passenger list local service.
	 */

	/**
	 * Adds the temp passenger list to the database. Also notifies the appropriate model listeners.
	 *
	 * @param tempPassengerList the temp passenger list
	 * @return the temp passenger list that was added
	 * @throws SystemException if a system exception occurred
	 */
	public TempPassengerList addTempPassengerList(
			TempPassengerList tempPassengerList) throws SystemException {

		tempPassengerList = persistence.updateImpl(tempPassengerList,
				false);





		return tempPassengerList;
	}

	/**
	 * Creates a new temp passenger list with the primary key. Does not add the temp passenger list to the database.
	 *
	 * @param id the primary key for the new temp passenger list
	 * @return the new temp passenger list
	 */
	public TempPassengerList createTempPassengerList(long id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the temp passenger list with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the temp passenger list
	 * @throws PortalException if a temp passenger list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteTempPassengerList(long id)
			throws PortalException, SystemException {
		TempPassengerList tempPassengerList = persistence.remove(id);




	}

	/**
	 * Deletes the temp passenger list from the database. Also notifies the appropriate model listeners.
	 *
	 * @param tempPassengerList the temp passenger list
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteTempPassengerList(TempPassengerList tempPassengerList)
			throws SystemException {
		persistence.remove(tempPassengerList);




	}













	public TempPassengerList fetchTempPassengerList(long id)
			throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the temp passenger list with the primary key.
	 *
	 * @param id the primary key of the temp passenger list
	 * @return the temp passenger list
	 * @throws PortalException if a temp passenger list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempPassengerList getTempPassengerList(long id)
			throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}


	/**
	 * Returns a range of all the temp passenger lists.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of temp passenger lists
	 * @param end the upper bound of the range of temp passenger lists (not inclusive)
	 * @return the range of temp passenger lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempPassengerList> getTempPassengerLists(int start, int end)
			throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of temp passenger lists.
	 *
	 * @return the number of temp passenger lists
	 * @throws SystemException if a system exception occurred
	 */
	public int getTempPassengerListsCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the temp passenger list in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param tempPassengerList the temp passenger list
	 * @return the temp passenger list that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public TempPassengerList updateTempPassengerList(
			TempPassengerList tempPassengerList) throws SystemException {
		return updateTempPassengerList(tempPassengerList, true);
	}

	/**
	 * Updates the temp passenger list in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param tempPassengerList the temp passenger list
	 * @param merge whether to merge the temp passenger list with the current session. See  for an explanation.
	 * @return the temp passenger list that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public TempPassengerList updateTempPassengerList(
			TempPassengerList tempPassengerList, boolean merge)
			throws SystemException {

		tempPassengerList = persistence.updateImpl(tempPassengerList,
				merge);





		return tempPassengerList;
	}
}