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
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.TempDangerousGoodsManifestFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.TempDangerousGoodsManifestPersistenceImpl;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

/**
 * The implementation of the temp dangerous goods nanifest local service.
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link vn.gt.dao.noticeandmessage.service.TempDangerousGoodsManifestLocalService}
 * interface.
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.noticeandmessage.service.base.TempDangerousGoodsManifestLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.TempDangerousGoodsManifestLocalServiceUtil
 */
public class TempDangerousGoodsManifestLocalServiceImpl {
	@Autowired
	TempDangerousGoodsManifestPersistenceImpl persistence;
	@Autowired
	TempDangerousGoodsManifestFinderImpl finder;
	/*
	 * NOTE FOR DEVELOPERS: Never reference this interface directly. Always use
	 * {@link vn.gt.dao.noticeandmessage.service.
	 * TempDangerousGoodsManifestLocalServiceUtil} to access the temp dangerous
	 * goods nanifest local service.
	 */

	public List<TempDangerousGoodsManifest> findBydocumentNameAnddocumentYearRequestState(long documentName,
			int documentYear, int requestState) {
		try {
			return persistence.findBydocumentNameAnddocumentYearRequestState(documentName,
					documentYear, requestState);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<TempDangerousGoodsManifest> findByRequestCode(String requestCode) throws SystemException {
		return persistence.findByRequestCode(requestCode);
	}

	public int countBydocumentNameAnddocumentYear(long documentName, int documentYear) {
		try {
			return finder.countBydocumentNameAnddocumentYear(documentName, documentYear);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<TempDangerousGoodsManifest> findBydocumentNameAnddocumentYear(long documentName, int documentYear) {
		try {
			return finder.findBydocumentNameAnddocumentYear(documentName, documentYear);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public TempDangerousGoodsManifest getLastByDocumentNameAndDocumentYear(long documentName, int documentYear) {
		try {
			return finder.getLastByDocumentNameAndDocumentYear(documentName, documentYear);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<TempDangerousGoodsManifest> findByDocumentNameAndDocumentYearOrderByDescRequestDate(long documentName,
			int documentYear) {
		return finder.findByDocumentNameAndDocumentYearOrderByDescRequestDate(documentName,
				documentYear);
	}

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link
	 * vn.gt.dao.noticeandmessage.service.
	 * TempDangerousGoodsManifestLocalServiceUtil} to access the temp dangerous
	 * goods nanifest local service.
	 */

	/**
	 * Adds the temp dangerous goods nanifest to the database. Also notifies the
	 * appropriate model listeners.
	 *
	 * @param tempDangerousGoodsNanifest the temp dangerous goods nanifest
	 * @return the temp dangerous goods nanifest that was added
	 * @throws SystemException if a system exception occurred
	 */
	public TempDangerousGoodsManifest addTempDangerousGoodsManifest(
			TempDangerousGoodsManifest tempDangerousGoodsNanifest) throws SystemException {
		

		tempDangerousGoodsNanifest = persistence.updateImpl(tempDangerousGoodsNanifest, false);

		return tempDangerousGoodsNanifest;
	}

	/**
	 * Creates a new temp dangerous goods nanifest with the primary key. Does not
	 * add the temp dangerous goods nanifest to the database.
	 *
	 * @param id the primary key for the new temp dangerous goods nanifest
	 * @return the new temp dangerous goods nanifest
	 */
	public TempDangerousGoodsManifest createTempDangerousGoodsManifest(long id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the temp dangerous goods nanifest with the primary key from the
	 * database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the temp dangerous goods nanifest
	 * @throws PortalException if a temp dangerous goods nanifest with the primary
	 *                         key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteTempDangerousGoodsManifest(long id) throws PortalException, SystemException {
		TempDangerousGoodsManifest tempDangerousGoodsNanifest = persistence.remove(id);

	}

	/**
	 * Deletes the temp dangerous goods nanifest from the database. Also notifies
	 * the appropriate model listeners.
	 *
	 * @param tempDangerousGoodsNanifest the temp dangerous goods nanifest
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteTempDangerousGoodsManifest(TempDangerousGoodsManifest tempDangerousGoodsNanifest)
			throws SystemException {
		persistence.remove(tempDangerousGoodsNanifest);

	}

	public TempDangerousGoodsManifest fetchTempDangerousGoodsManifest(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the temp dangerous goods nanifest with the primary key.
	 *
	 * @param id the primary key of the temp dangerous goods nanifest
	 * @return the temp dangerous goods nanifest
	 * @throws PortalException if a temp dangerous goods nanifest with the primary
	 *                         key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDangerousGoodsManifest getTempDangerousGoodsManifest(long id) throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public TempDangerousGoodsManifest getPersistedModel(Serializable primaryKeyObj) throws PortalException, SystemException {
		return persistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the temp dangerous goods nanifests.
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
	 * @param start the lower bound of the range of temp dangerous goods nanifests
	 * @param end   the upper bound of the range of temp dangerous goods nanifests
	 *              (not inclusive)
	 * @return the range of temp dangerous goods nanifests
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDangerousGoodsManifest> getTempDangerousGoodsManifests(int start, int end) throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of temp dangerous goods nanifests.
	 *
	 * @return the number of temp dangerous goods nanifests
	 * @throws SystemException if a system exception occurred
	 */
	public int getTempDangerousGoodsManifestsCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the temp dangerous goods nanifest in the database or adds it if it
	 * does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param tempDangerousGoodsNanifest the temp dangerous goods nanifest
	 * @return the temp dangerous goods nanifest that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public TempDangerousGoodsManifest updateTempDangerousGoodsManifest(
			TempDangerousGoodsManifest tempDangerousGoodsNanifest) throws SystemException {
		return updateTempDangerousGoodsManifest(tempDangerousGoodsNanifest, true);
	}

	/**
	 * Updates the temp dangerous goods nanifest in the database or adds it if it
	 * does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param tempDangerousGoodsNanifest the temp dangerous goods nanifest
	 * @param merge                      whether to merge the temp dangerous goods
	 *                                   nanifest with the current session. See
	 *                                   
	 *                                   for an explanation.
	 * @return the temp dangerous goods nanifest that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public TempDangerousGoodsManifest updateTempDangerousGoodsManifest(
			TempDangerousGoodsManifest tempDangerousGoodsNanifest, boolean merge) throws SystemException {
		

		tempDangerousGoodsNanifest = persistence.updateImpl(tempDangerousGoodsNanifest, merge);

		return tempDangerousGoodsNanifest;
	}
}