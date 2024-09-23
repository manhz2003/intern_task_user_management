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
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.TempDeclarationOfHealthFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.TempDeclarationOfHealthPersistenceImpl;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

/**
 * The implementation of the temp declaration of health local service.
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link vn.gt.dao.noticeandmessage.service.TempDeclarationOfHealthLocalService}
 * interface.
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.noticeandmessage.service.base.TempDeclarationOfHealthLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.TempDeclarationOfHealthLocalServiceUtil
 */
public class TempDeclarationOfHealthLocalServiceImpl {
	@Autowired
	TempDeclarationOfHealthPersistenceImpl persistence;
	@Autowired
	TempDeclarationOfHealthFinderImpl finder;
	/*
	 * NOTE FOR DEVELOPERS: Never reference this interface directly. Always use
	 * {@link
	 * vn.gt.dao.noticeandmessage.service.TempDeclarationOfHealthLocalServiceUtil}
	 * to access the temp declaration of health local service.
	 */

	public List<TempDeclarationOfHealth> findBydocumentNameAnddocumentYearRequestState(long documentName,
			int documentYear, int requestState) {
		try {
			return persistence.findBydocumentNameAnddocumentYearRequestState(documentName,
					documentYear, requestState);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<TempDeclarationOfHealth> findByRequestCode(String requestCode) {
		try {
			return persistence.findByRequestCode(requestCode);
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

	public List<TempDeclarationOfHealth> findBydocumentNameAnddocumentYear(long documentName, int documentYear) {
		try {
			return finder.findBydocumentNameAnddocumentYear(documentName, documentYear);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public TempDeclarationOfHealth getLastByDocumentNameAndDocumentYear(long documentName, int documentYear) {
		try {
			return finder.getLastByDocumentNameAndDocumentYear(documentName, documentYear);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<TempDeclarationOfHealth> findByDocumentNameAndDocumentYearOrderByDescRequestDate(long documentName,
			int documentYear) {
		return finder.findByDocumentNameAndDocumentYearOrderByDescRequestDate(documentName,
				documentYear);
	}

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link
	 * vn.gt.dao.noticeandmessage.service.TempDeclarationOfHealthLocalServiceUtil}
	 * to access the temp declaration of health local service.
	 */

	/**
	 * Adds the temp declaration of health to the database. Also notifies the
	 * appropriate model listeners.
	 *
	 * @param tempDeclarationOfHealth the temp declaration of health
	 * @return the temp declaration of health that was added
	 * @throws SystemException if a system exception occurred
	 */
	public TempDeclarationOfHealth addTempDeclarationOfHealth(TempDeclarationOfHealth tempDeclarationOfHealth)
			throws SystemException {
		

		tempDeclarationOfHealth = persistence.updateImpl(tempDeclarationOfHealth, false);

		return tempDeclarationOfHealth;
	}

	/**
	 * Creates a new temp declaration of health with the primary key. Does not add
	 * the temp declaration of health to the database.
	 *
	 * @param id the primary key for the new temp declaration of health
	 * @return the new temp declaration of health
	 */
	public TempDeclarationOfHealth createTempDeclarationOfHealth(long id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the temp declaration of health with the primary key from the
	 * database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the temp declaration of health
	 * @throws PortalException if a temp declaration of health with the primary key
	 *                         could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteTempDeclarationOfHealth(long id) throws PortalException, SystemException {
		TempDeclarationOfHealth tempDeclarationOfHealth = persistence.remove(id);

	}

	/**
	 * Deletes the temp declaration of health from the database. Also notifies the
	 * appropriate model listeners.
	 *
	 * @param tempDeclarationOfHealth the temp declaration of health
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteTempDeclarationOfHealth(TempDeclarationOfHealth tempDeclarationOfHealth) throws SystemException {
		persistence.remove(tempDeclarationOfHealth);

	}

	public TempDeclarationOfHealth fetchTempDeclarationOfHealth(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the temp declaration of health with the primary key.
	 *
	 * @param id the primary key of the temp declaration of health
	 * @return the temp declaration of health
	 * @throws PortalException if a temp declaration of health with the primary key
	 *                         could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDeclarationOfHealth getTempDeclarationOfHealth(long id) throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public TempDeclarationOfHealth getPersistedModel(Serializable primaryKeyObj) throws PortalException, SystemException {
		return persistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the temp declaration of healths.
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
	 * @param start the lower bound of the range of temp declaration of healths
	 * @param end   the upper bound of the range of temp declaration of healths (not
	 *              inclusive)
	 * @return the range of temp declaration of healths
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDeclarationOfHealth> getTempDeclarationOfHealths(int start, int end) throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of temp declaration of healths.
	 *
	 * @return the number of temp declaration of healths
	 * @throws SystemException if a system exception occurred
	 */
	public int getTempDeclarationOfHealthsCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the temp declaration of health in the database or adds it if it does
	 * not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param tempDeclarationOfHealth the temp declaration of health
	 * @return the temp declaration of health that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public TempDeclarationOfHealth updateTempDeclarationOfHealth(TempDeclarationOfHealth tempDeclarationOfHealth)
			throws SystemException {
		return updateTempDeclarationOfHealth(tempDeclarationOfHealth, true);
	}

	/**
	 * Updates the temp declaration of health in the database or adds it if it does
	 * not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param tempDeclarationOfHealth the temp declaration of health
	 * @param merge                   whether to merge the temp declaration of
	 *                                health with the current session. See
	 *                                
	 *                                for an explanation.
	 * @return the temp declaration of health that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public TempDeclarationOfHealth updateTempDeclarationOfHealth(TempDeclarationOfHealth tempDeclarationOfHealth,
			boolean merge) throws SystemException {
		

		tempDeclarationOfHealth = persistence.updateImpl(tempDeclarationOfHealth, merge);

		return tempDeclarationOfHealth;
	}

}