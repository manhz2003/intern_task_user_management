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
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.TempGeneralDeclarationFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.TempGeneralDeclarationPersistenceImpl;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;import lombok.extern.slf4j.Slf4j;@Slf4j @Service









/**
 * The implementation of the temp general declaration local service.
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the
 * {@link vn.gt.dao.noticeandmessage.service.TempGeneralDeclarationLocalService} interface.
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can
 * only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.noticeandmessage.service.base.TempGeneralDeclarationLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.tempGeneralDeclarationLocalService
 */
public class TempGeneralDeclarationLocalServiceImpl { @Autowired
TempGeneralDeclarationPersistenceImpl persistence;@Autowired
TempGeneralDeclarationFinderImpl finder;
	/*
	 * NOTE FOR DEVELOPERS: Never reference this interface directly. Always use {@link
	 * vn.gt.dao.noticeandmessage.service.tempGeneralDeclarationLocalService} to access the temp general declaration local service.
	 */
	
	public List<TempGeneralDeclaration> findByDocumentNameAndDocumentYearRequestState(long documentName, int documentYear, int requestState) {
		try {
			return persistence.findByDocumentNameAndDocumentYearRequestState(documentName, documentYear, requestState);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}
	
	public List<TempGeneralDeclaration> findByRequestCode(String requestCode) {
		try {
			return persistence.findByRequestCode(requestCode);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}
	
	public int countByDocumentNameAndDocumentYear(long documentName, int documentYear) {
		try {
			return persistence.countByDocumentNameAndDocumentYear(documentName, documentYear);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return 0;
	}
	
	public List<TempGeneralDeclaration> findByDocumentNameAndDocumentYear(long documentName, int documentYear) {
		try {
			return finder.findByDocumentNameAndDocumentYear(documentName, documentYear);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}
	
	public TempGeneralDeclaration findTempGeneralDeclarationByRequestCode(String requestCode) {
		try {
			return finder.findTempGeneralDeclarationByRequestCode(requestCode);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}
	
	public TempGeneralDeclaration getLastByDocumentNameAndDocumentYear(long documentName, int documentYear) {
		try {
			return finder.getLastByDocumentNameAndDocumentYear(documentName, documentYear);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}
	
	public TempGeneralDeclaration getByRequestCode(String requestCode) {
		try {
			return finder.getByRequestCode(requestCode);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}
	
	public List<TempGeneralDeclaration> findByDocumentNameAndDocumentYearOrderByDescRequestDate(long documentName, int documentYear) {
		return finder.findByDocumentNameAndDocumentYearOrderByDescRequestDate(documentName, documentYear);
	}


	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link vn.gt.dao.noticeandmessage.service.tempGeneralDeclarationLocalService} to access the temp general declaration local service.
	 */

	/**
	 * Adds the temp general declaration to the database. Also notifies the appropriate model listeners.
	 *
	 * @param tempGeneralDeclaration the temp general declaration
	 * @return the temp general declaration that was added
	 * @throws SystemException if a system exception occurred
	 */
	public TempGeneralDeclaration addTempGeneralDeclaration(
			TempGeneralDeclaration tempGeneralDeclaration)
			throws SystemException {

		tempGeneralDeclaration = persistence.updateImpl(tempGeneralDeclaration,
				false);





		return tempGeneralDeclaration;
	}

	/**
	 * Creates a new temp general declaration with the primary key. Does not add the temp general declaration to the database.
	 *
	 * @param id the primary key for the new temp general declaration
	 * @return the new temp general declaration
	 */
	public TempGeneralDeclaration createTempGeneralDeclaration(long id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the temp general declaration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the temp general declaration
	 * @throws PortalException if a temp general declaration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteTempGeneralDeclaration(long id)
			throws PortalException, SystemException {
		TempGeneralDeclaration tempGeneralDeclaration = persistence.remove(id);




	}

	/**
	 * Deletes the temp general declaration from the database. Also notifies the appropriate model listeners.
	 *
	 * @param tempGeneralDeclaration the temp general declaration
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteTempGeneralDeclaration(
			TempGeneralDeclaration tempGeneralDeclaration)
			throws SystemException {
		persistence.remove(tempGeneralDeclaration);




	}













	public TempGeneralDeclaration fetchTempGeneralDeclaration(long id)
			throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the temp general declaration with the primary key.
	 *
	 * @param id the primary key of the temp general declaration
	 * @return the temp general declaration
	 * @throws PortalException if a temp general declaration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempGeneralDeclaration getTempGeneralDeclaration(long id)
			throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}


	/**
	 * Returns a range of all the temp general declarations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of temp general declarations
	 * @param end the upper bound of the range of temp general declarations (not inclusive)
	 * @return the range of temp general declarations
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempGeneralDeclaration> getTempGeneralDeclarations(int start,
																   int end) throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of temp general declarations.
	 *
	 * @return the number of temp general declarations
	 * @throws SystemException if a system exception occurred
	 */
	public int getTempGeneralDeclarationsCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the temp general declaration in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param tempGeneralDeclaration the temp general declaration
	 * @return the temp general declaration that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public TempGeneralDeclaration updateTempGeneralDeclaration(
			TempGeneralDeclaration tempGeneralDeclaration)
			throws SystemException {
		return updateTempGeneralDeclaration(tempGeneralDeclaration, true);
	}

	/**
	 * Updates the temp general declaration in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param tempGeneralDeclaration the temp general declaration
	 * @param merge whether to merge the temp general declaration with the current session. See  for an explanation.
	 * @return the temp general declaration that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public TempGeneralDeclaration updateTempGeneralDeclaration(
			TempGeneralDeclaration tempGeneralDeclaration, boolean merge)
			throws SystemException {

		tempGeneralDeclaration = persistence.updateImpl(tempGeneralDeclaration,
				merge);





		return tempGeneralDeclaration;
	}
	
	
}