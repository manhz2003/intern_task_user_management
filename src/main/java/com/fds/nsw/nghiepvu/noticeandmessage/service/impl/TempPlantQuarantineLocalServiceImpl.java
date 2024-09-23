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
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.TempPlantQuarantineFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.TempPlantQuarantinePersistenceImpl;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;import lombok.extern.slf4j.Slf4j;@Slf4j @Service









/**
 * The implementation of the temp plant quarantine local service.
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the
 * {@link vn.gt.dao.noticeandmessage.service.TempPlantQuarantineLocalService} interface.
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can
 * only be accessed from within the same VM.
 * </p>
 * 
 * @author win_64
 * @see vn.gt.dao.noticeandmessage.service.base.TempPlantQuarantineLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.TempPlantQuarantineLocalServiceUtil
 */
public class TempPlantQuarantineLocalServiceImpl { @Autowired
TempPlantQuarantinePersistenceImpl persistence;@Autowired
TempPlantQuarantineFinderImpl finder;
	/*
	 * NOTE FOR DEVELOPERS: Never reference this interface directly. Always use {@link
	 * vn.gt.dao.noticeandmessage.service.TempPlantQuarantineLocalServiceUtil} to access the temp plant quarantine local service.
	 */
	
	public int countBydocumentNameAnddocumentYear(long documentName, int documentYear) {
		try {
			return finder.countBydocumentNameAnddocumentYear(documentName, documentYear);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public List<TempPlantQuarantine> findBydocumentNameAnddocumentYear(long documentName, int documentYear) {
		try {
			return finder.findBydocumentNameAnddocumentYear(documentName, documentYear);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<TempPlantQuarantine> findBydocumentNameAnddocumentYearRequestState(long documentName, int documentYear, int requestState) {
		try {
			return persistence.findBydocumentNameAnddocumentYearRequestState(documentName, documentYear, requestState);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<TempPlantQuarantine> findByRequestCode(java.lang.String requestCode) throws SystemException {
		return persistence.findByRequestCode(requestCode);
	}
	
	public List<TempPlantQuarantine> findBydocumentNameAnddocumentYear(long documentName, int documentYear, int start, int end)
			throws SystemException {
		return persistence.findBydocumentNameAnddocumentYear(documentName, documentYear, start, end);
	}
	
	public TempPlantQuarantine getLastByDocumentNameAndDocumentYear(long documentName, int documentYear) {
		try {
			return finder.getLastByDocumentNameAndDocumentYear(documentName, documentYear);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<TempPlantQuarantine> findByDocumentNameAndDocumentYearOrderByDescRequestDate(long documentName, int documentYear) {
		return finder.findByDocumentNameAndDocumentYearOrderByDescRequestDate(documentName, documentYear);
	}


	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link vn.gt.dao.noticeandmessage.service.TempPlantQuarantineLocalServiceUtil} to access the temp plant quarantine local service.
	 */

	/**
	 * Adds the temp plant quarantine to the database. Also notifies the appropriate model listeners.
	 *
	 * @param tempPlantQuarantine the temp plant quarantine
	 * @return the temp plant quarantine that was added
	 * @throws SystemException if a system exception occurred
	 */
	public TempPlantQuarantine addTempPlantQuarantine(
			TempPlantQuarantine tempPlantQuarantine) throws SystemException {

		tempPlantQuarantine = persistence.updateImpl(tempPlantQuarantine,
				false);





		return tempPlantQuarantine;
	}

	/**
	 * Creates a new temp plant quarantine with the primary key. Does not add the temp plant quarantine to the database.
	 *
	 * @param id the primary key for the new temp plant quarantine
	 * @return the new temp plant quarantine
	 */
	public TempPlantQuarantine createTempPlantQuarantine(long id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the temp plant quarantine with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the temp plant quarantine
	 * @throws PortalException if a temp plant quarantine with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteTempPlantQuarantine(long id)
			throws PortalException, SystemException {
		TempPlantQuarantine tempPlantQuarantine = persistence.remove(id);




	}

	/**
	 * Deletes the temp plant quarantine from the database. Also notifies the appropriate model listeners.
	 *
	 * @param tempPlantQuarantine the temp plant quarantine
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteTempPlantQuarantine(
			TempPlantQuarantine tempPlantQuarantine) throws SystemException {
		persistence.remove(tempPlantQuarantine);




	}













	public TempPlantQuarantine fetchTempPlantQuarantine(long id)
			throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the temp plant quarantine with the primary key.
	 *
	 * @param id the primary key of the temp plant quarantine
	 * @return the temp plant quarantine
	 * @throws PortalException if a temp plant quarantine with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempPlantQuarantine getTempPlantQuarantine(long id)
			throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	/**
	 * Returns a range of all the temp plant quarantines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of temp plant quarantines
	 * @param end the upper bound of the range of temp plant quarantines (not inclusive)
	 * @return the range of temp plant quarantines
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempPlantQuarantine> getTempPlantQuarantines(int start, int end)
			throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of temp plant quarantines.
	 *
	 * @return the number of temp plant quarantines
	 * @throws SystemException if a system exception occurred
	 */
	public int getTempPlantQuarantinesCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the temp plant quarantine in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param tempPlantQuarantine the temp plant quarantine
	 * @return the temp plant quarantine that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public TempPlantQuarantine updateTempPlantQuarantine(
			TempPlantQuarantine tempPlantQuarantine) throws SystemException {
		return updateTempPlantQuarantine(tempPlantQuarantine, true);
	}

	/**
	 * Updates the temp plant quarantine in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param tempPlantQuarantine the temp plant quarantine
	 * @param merge whether to merge the temp plant quarantine with the current session. See  for an explanation.
	 * @return the temp plant quarantine that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public TempPlantQuarantine updateTempPlantQuarantine(
			TempPlantQuarantine tempPlantQuarantine, boolean merge)
			throws SystemException {

		tempPlantQuarantine = persistence.updateImpl(tempPlantQuarantine,
				merge);





		return tempPlantQuarantine;
	}
	
}