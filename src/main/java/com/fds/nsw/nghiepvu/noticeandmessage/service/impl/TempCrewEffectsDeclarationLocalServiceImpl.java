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
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.TempCrewEffectsDeclarationFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.TempCrewEffectsDeclarationPersistenceImpl;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

/**
 * The implementation of the temp crew effects declaration local service.
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link vn.gt.dao.noticeandmessage.service.TempCrewEffectsDeclarationLocalService}
 * interface.
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.noticeandmessage.service.base.TempCrewEffectsDeclarationLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.tempCrewEffectsDeclarationLocalService
 */
public class TempCrewEffectsDeclarationLocalServiceImpl {
	@Autowired
	TempCrewEffectsDeclarationPersistenceImpl persistence;
	@Autowired
	TempCrewEffectsDeclarationFinderImpl finder;

	/*
	 * NOTE FOR DEVELOPERS: Never reference this interface directly. Always use
	 * {@link vn.gt.dao.noticeandmessage.service.
	 * tempCrewEffectsDeclarationLocalService} to access the temp crew effects
	 * declaration local service.
	 */
	public List<TempCrewEffectsDeclaration> findBydocumentNameAnddocumentYear(long documentName, int documentYear)
			throws SystemException {
		return finder.findBydocumentNameAnddocumentYear(documentName, documentYear);
	}

	public List<TempCrewEffectsDeclaration> findBydocumentNameAnddocumentYear(long documentName, int documentYear,
			int start, int end) throws SystemException {
		return persistence.findBydocumentNameAnddocumentYear(documentName, documentYear,
				start, end);
	}

	public List<TempCrewEffectsDeclaration> findByRequestCode(java.lang.String requestCode) throws SystemException {
		return persistence.findByRequestCode(requestCode);
	}

	public int countBydocumentNameAnddocumentYear(long documentName, int documentYear) throws SystemException {
		return finder.countBydocumentNameAnddocumentYear(documentName, documentYear);
	}

	public TempCrewEffectsDeclaration findTempCrewEffectsDeclarationByRequestCode(String requestCode) {
		try {
			return finder.findTempCrewEffectsDeclarationByRequestCode(requestCode);
		} catch (Exception es) {
			es.printStackTrace();
		}
		return null;
	}

	public List<TempCrewEffectsDeclaration> findBydocumentNameAnddocumentYearRequestState(long documentName,
			int documentYear, int requestState) {
		try {
			return persistence.findBydocumentNameAnddocumentYearRequestState(documentName,
					documentYear, requestState);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public TempCrewEffectsDeclaration getLastByDocumentNameAndDocumentYear(long documentName, int documentYear) {
		try {
			return finder.getLastByDocumentNameAndDocumentYear(documentName, documentYear);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<TempCrewEffectsDeclaration> findByDocumentNameAndDocumentYearOrderByDescRequestDate(long documentName,
			int documentYear) {
		return finder.findByDocumentNameAndDocumentYearOrderByDescRequestDate(documentName,
				documentYear);
	}

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link
	 * vn.gt.dao.noticeandmessage.service.
	 * tempCrewEffectsDeclarationLocalService} to access the temp crew effects
	 * declaration local service.
	 */

	/**
	 * Adds the temp crew effects declaration to the database. Also notifies the
	 * appropriate model listeners.
	 *
	 * @param tempCrewEffectsDeclaration the temp crew effects declaration
	 * @return the temp crew effects declaration that was added
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewEffectsDeclaration addTempCrewEffectsDeclaration(
			TempCrewEffectsDeclaration tempCrewEffectsDeclaration) throws SystemException {
		

		tempCrewEffectsDeclaration = persistence.updateImpl(tempCrewEffectsDeclaration, false);

		return tempCrewEffectsDeclaration;
	}

	/**
	 * Creates a new temp crew effects declaration with the primary key. Does not
	 * add the temp crew effects declaration to the database.
	 *
	 * @param id the primary key for the new temp crew effects declaration
	 * @return the new temp crew effects declaration
	 */
	public TempCrewEffectsDeclaration createTempCrewEffectsDeclaration(long id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the temp crew effects declaration with the primary key from the
	 * database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the temp crew effects declaration
	 * @throws PortalException if a temp crew effects declaration with the primary
	 *                         key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteTempCrewEffectsDeclaration(long id) throws PortalException, SystemException {
		TempCrewEffectsDeclaration tempCrewEffectsDeclaration = persistence.remove(id);

	}

	/**
	 * Deletes the temp crew effects declaration from the database. Also notifies
	 * the appropriate model listeners.
	 *
	 * @param tempCrewEffectsDeclaration the temp crew effects declaration
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteTempCrewEffectsDeclaration(TempCrewEffectsDeclaration tempCrewEffectsDeclaration)
			throws SystemException {
		persistence.remove(tempCrewEffectsDeclaration);

	}

	public TempCrewEffectsDeclaration fetchTempCrewEffectsDeclaration(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the temp crew effects declaration with the primary key.
	 *
	 * @param id the primary key of the temp crew effects declaration
	 * @return the temp crew effects declaration
	 * @throws PortalException if a temp crew effects declaration with the primary
	 *                         key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewEffectsDeclaration getTempCrewEffectsDeclaration(long id) throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public TempCrewEffectsDeclaration getPersistedModel(Serializable primaryKeyObj) throws PortalException, SystemException {
		return persistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the temp crew effects declarations.
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
	 * @param start the lower bound of the range of temp crew effects declarations
	 * @param end   the upper bound of the range of temp crew effects declarations
	 *              (not inclusive)
	 * @return the range of temp crew effects declarations
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempCrewEffectsDeclaration> getTempCrewEffectsDeclarations(int start, int end) throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of temp crew effects declarations.
	 *
	 * @return the number of temp crew effects declarations
	 * @throws SystemException if a system exception occurred
	 */
	public int getTempCrewEffectsDeclarationsCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the temp crew effects declaration in the database or adds it if it
	 * does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param tempCrewEffectsDeclaration the temp crew effects declaration
	 * @return the temp crew effects declaration that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewEffectsDeclaration updateTempCrewEffectsDeclaration(
			TempCrewEffectsDeclaration tempCrewEffectsDeclaration) throws SystemException {
		return updateTempCrewEffectsDeclaration(tempCrewEffectsDeclaration, true);
	}

	/**
	 * Updates the temp crew effects declaration in the database or adds it if it
	 * does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param tempCrewEffectsDeclaration the temp crew effects declaration
	 * @param merge                      whether to merge the temp crew effects
	 *                                   declaration with the current session. See
	 *                                   
	 *                                   for an explanation.
	 * @return the temp crew effects declaration that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewEffectsDeclaration updateTempCrewEffectsDeclaration(
			TempCrewEffectsDeclaration tempCrewEffectsDeclaration, boolean merge) throws SystemException {
		

		tempCrewEffectsDeclaration = persistence.updateImpl(tempCrewEffectsDeclaration, merge);

		return tempCrewEffectsDeclaration;
	}
}