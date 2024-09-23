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
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.TempCargoDeclarationFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.TempCargoDeclarationPersistenceImpl;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

/**
 * The implementation of the temp cargo declaration local service.
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link vn.gt.dao.noticeandmessage.service.TempCargoDeclarationLocalService}
 * interface.
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.noticeandmessage.service.base.TempCargoDeclarationLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.tempCargoItemsLocalService
 */
public class TempCargoDeclarationLocalServiceImpl {
	@Autowired
	TempCargoDeclarationPersistenceImpl persistence;
	@Autowired
	TempCargoDeclarationFinderImpl finder;
	/*
	 * NOTE FOR DEVELOPERS: Never reference this interface directly. Always use
	 * {@link
	 * vn.gt.dao.noticeandmessage.service.tempCargoItemsLocalService} to
	 * access the temp cargo declaration local service.
	 */

	public List<TempCargoDeclaration> findBydocumentNameAnddocumentYearRequestState(long documentName, int documentYear,
			int requestState) {
		try {
			return persistence.findBydocumentNameAnddocumentYearRequestState(documentName,
					documentYear, requestState);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int countBydocumentNameAnddocumentYear(long documentName, int documentYear) throws SystemException {
		return persistence.countBydocumentNameAnddocumentYear(documentName, documentYear);
	}

	public List<TempCargoDeclaration> findBydocumentNameAnddocumentYear(long documentName, int documentYear, int start,
			int end) throws SystemException {
		return persistence.findBydocumentNameAnddocumentYear(documentName, documentYear, start,
				end);
	}

	public List<TempCargoDeclaration> findByRequestCode(java.lang.String requestCode) throws SystemException {
		return persistence.findByRequestCode(requestCode);
	}

	public TempCargoDeclaration findTempCargoDeclarationByRequestCode(String requestCode) {
		try {
			return finder.findTempCargoDeclarationByRequestCode(requestCode);
		} catch (Exception es) {
			es.printStackTrace();
		}
		return null;
	}

	public List<TempCargoDeclaration> findBydocumentNameAnddocumentYear(long documentName, int documentYear)
			throws SystemException {
		return finder.findBydocumentNameAnddocumentYear(documentName, documentYear);
	}

	public TempCargoDeclaration getLastByDocumentNameAndDocumentYear(long documentName, int documentYear) {
		try {
			return finder.getLastByDocumentNameAndDocumentYear(documentName, documentYear);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new TempCargoDeclaration();
	}

	public List<TempCargoDeclaration> findByDocumentNameAndDocumentYearOrderByDescRequestDate(long documentName,
			int documentYear) throws SystemException {
		return finder.findByDocumentNameAndDocumentYearOrderByDescRequestDate(documentName,
				documentYear);
	}

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link
	 * vn.gt.dao.noticeandmessage.service.tempCargoItemsLocalService} to
	 * access the temp cargo declaration local service.
	 */

	/**
	 * Adds the temp cargo declaration to the database. Also notifies the
	 * appropriate model listeners.
	 *
	 * @param tempCargoDeclaration the temp cargo declaration
	 * @return the temp cargo declaration that was added
	 * @throws SystemException if a system exception occurred
	 */
	public TempCargoDeclaration addTempCargoDeclaration(TempCargoDeclaration tempCargoDeclaration)
			throws SystemException {
		

		tempCargoDeclaration = persistence.updateImpl(tempCargoDeclaration, false);

		return tempCargoDeclaration;
	}

	/**
	 * Creates a new temp cargo declaration with the primary key. Does not add the
	 * temp cargo declaration to the database.
	 *
	 * @param id the primary key for the new temp cargo declaration
	 * @return the new temp cargo declaration
	 */
	public TempCargoDeclaration createTempCargoDeclaration(long id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the temp cargo declaration with the primary key from the database.
	 * Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the temp cargo declaration
	 * @throws PortalException if a temp cargo declaration with the primary key
	 *                         could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteTempCargoDeclaration(long id) throws PortalException, SystemException {
		TempCargoDeclaration tempCargoDeclaration = persistence.remove(id);

	}

	/**
	 * Deletes the temp cargo declaration from the database. Also notifies the
	 * appropriate model listeners.
	 *
	 * @param tempCargoDeclaration the temp cargo declaration
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteTempCargoDeclaration(TempCargoDeclaration tempCargoDeclaration) throws SystemException {
		persistence.remove(tempCargoDeclaration);

	}

	public TempCargoDeclaration fetchTempCargoDeclaration(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the temp cargo declaration with the primary key.
	 *
	 * @param id the primary key of the temp cargo declaration
	 * @return the temp cargo declaration
	 * @throws PortalException if a temp cargo declaration with the primary key
	 *                         could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCargoDeclaration getTempCargoDeclaration(long id) throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public TempCargoDeclaration getPersistedModel(Serializable primaryKeyObj) throws PortalException, SystemException {
		return persistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the temp cargo declarations.
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
	 * @param start the lower bound of the range of temp cargo declarations
	 * @param end   the upper bound of the range of temp cargo declarations (not
	 *              inclusive)
	 * @return the range of temp cargo declarations
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempCargoDeclaration> getTempCargoDeclarations(int start, int end) throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of temp cargo declarations.
	 *
	 * @return the number of temp cargo declarations
	 * @throws SystemException if a system exception occurred
	 */
	public int getTempCargoDeclarationsCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the temp cargo declaration in the database or adds it if it does not
	 * yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param tempCargoDeclaration the temp cargo declaration
	 * @return the temp cargo declaration that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public TempCargoDeclaration updateTempCargoDeclaration(TempCargoDeclaration tempCargoDeclaration)
			throws SystemException {
		return updateTempCargoDeclaration(tempCargoDeclaration, true);
	}

	/**
	 * Updates the temp cargo declaration in the database or adds it if it does not
	 * yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param tempCargoDeclaration the temp cargo declaration
	 * @param merge                whether to merge the temp cargo declaration with
	 *                             the current session. See
	 *                             
	 *                             for an explanation.
	 * @return the temp cargo declaration that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public TempCargoDeclaration updateTempCargoDeclaration(TempCargoDeclaration tempCargoDeclaration, boolean merge)
			throws SystemException {
		

		tempCargoDeclaration = persistence.updateImpl(tempCargoDeclaration, merge);

		return tempCargoDeclaration;
	}

}