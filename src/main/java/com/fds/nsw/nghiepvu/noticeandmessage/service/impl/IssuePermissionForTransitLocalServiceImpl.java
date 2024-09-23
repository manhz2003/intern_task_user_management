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

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.IssuePermissionForTransitFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.IssuePermissionForTransitPersistenceImpl;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

/**
 * The implementation of the issue permission for transit local service.
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link vn.gt.dao.noticeandmessage.service.IssuePermissionForTransitLocalService}
 * interface.
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.noticeandmessage.service.base.IssuePermissionForTransitLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.IssuePermissionForTransitLocalServiceUtil
 */
public class IssuePermissionForTransitLocalServiceImpl {
	@Autowired
	IssuePermissionForTransitPersistenceImpl persistence;
	@Autowired
	IssuePermissionForTransitFinderImpl finder;

	/*
	 * NOTE FOR DEVELOPERS: Never reference this interface directly. Always use
	 * {@link
	 * vn.gt.dao.noticeandmessage.service.IssuePermissionForTransitLocalServiceUtil}
	 * to access the issue permission for transit local service.
	 */
	public List<IssuePermissionForTransit> findIssuePermissionForTransitByDocumentYearAndDocumentYear(long documentName,
			int documentYear) {
		try {
			return persistence
					.findByfindIssuePermissionForTransitByDocumentYearAndDocumentYear(documentName, documentYear);
		} catch (Exception e) {
		}

		return null;
	}

	public IssuePermissionForTransit findIssuePermissionForTransitByDocumentYearAndDocumentYearAndRequestState(
			long documentName, int documentYear, int requestState) {
		try {
			return finder
					.findIssuePermissionForTransitByDocumentYearAndDocumentYearAndRequestState(documentName,
							documentYear, requestState);
		} catch (Exception e) {
		}

		return null;
	}

	public java.util.List<IssuePermissionForTransit> findByrequestCode(java.lang.String requestCode)
			throws SystemException {
		return persistence.findByrequestCode(requestCode);
	}

	public IssuePermissionForTransit getByrequestCode(String requestCode) {
		try {
			List<IssuePermissionForTransit> list = persistence.findByrequestCode(requestCode);
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return null;
	}

	public java.util.List<IssuePermissionForTransit> findBydocumentNameAndDocumentYear(long documentName,
			int documentYear) throws SystemException {
		return persistence.findBydocumentNameAndDocumentYear(documentName, documentYear);
	}

	public java.util.List<IssuePermissionForTransit> findBydocumentNameAndDocumentYear(long documentName,
			int documentYear, int start, int end) throws SystemException {
		return persistence.findBydocumentNameAndDocumentYear(documentName, documentYear, start,
				end);
	}

	public List<IssuePermissionForTransit> findIssuePermissionForTransitInfo(String requestState, String maritimeCode,
			String portCode, String shipName, String callSign, String shipDateFrom, String shipDateTo,
			String certificateNumber, int start, int end) {
		try {
			return finder.findIssuePermissionForTransitInfo(requestState, maritimeCode,
					portCode, shipName, callSign, shipDateFrom, shipDateTo, certificateNumber, start, end);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int countIssuePermissionForTransitInfo(String requestState, String maritimeCode, String portCode,
			String shipName, String callSign, String shipDateFrom, String shipDateTo, String certificateNumber) {
		try {
			return finder.countIssuePermissionForTransitInfo(requestState, maritimeCode,
					portCode, shipName, callSign, shipDateFrom, shipDateTo, certificateNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<IssuePermissionForTransit> findIssuePermissionForTransitInfoByCertificateNumber(String requestState,
			String maritimeCode, String portCode, String shipName, String callSign, String shipDateFrom,
			String shipDateTo, String certificateNumber, int start, int end) {
		try {
			return finder.findIssuePermissionForTransitInfoByCertificateNumber(requestState,
					maritimeCode, portCode, shipName, callSign, shipDateFrom, shipDateTo, certificateNumber, start,
					end);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int countIssuePermissionForTransitInfoByCertificateNumber(String requestState, String maritimeCode,
			String portCode, String shipName, String callSign, String shipDateFrom, String shipDateTo,
			String certificateNumber) {
		try {
			return finder.countIssuePermissionForTransitInfoByCertificateNumber(requestState,
					maritimeCode, portCode, shipName, callSign, shipDateFrom, shipDateTo, certificateNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int countByDocumentNameAndDocumentYear(long documentName, int documentYear) {
		try {
			return finder.countByDocumentNameAndDocumentYear(documentName, documentYear);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<IssuePermissionForTransit> findByDocumentYearAndDocumentYearOrderByColumn(long documentName,
			int documentYear, String orderByColumn, boolean ascOrdesc) {
		try {
			return finder.findByDocumentYearAndDocumentYearOrderByColumn(documentName,
					documentYear, orderByColumn, ascOrdesc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<IssuePermissionForTransit>();
	}

	public IssuePermissionForTransit getByDocumentNameAndDocumentYearAndVersionNo(long documentName, int documentYear,
			String versionNo) {
		try {
			return persistence.findByDocumentNameAndDocumentYearAndVersionNo(documentName,
					documentYear, versionNo);
		} catch (NoSuchIssuePermissionForTransitException e) {
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String capGiayPhepSo(String maritimeReference) throws SystemException {
		return finder.capGiayPhepSo(maritimeReference);
	}

	public IssuePermissionForTransit findByF_LAST_numberPortClearance(long documentName, int documentYear,
			String numberPortClearance) {
		try {
			return persistence.findByF_LAST_numberPortClearance_First(documentName,
					documentYear, numberPortClearance, null);
		} catch (Exception e) {
			return null;
		}
	}

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link
	 * vn.gt.dao.noticeandmessage.service.IssuePermissionForTransitLocalServiceUtil}
	 * to access the issue permission for transit local service.
	 */

	/**
	 * Adds the issue permission for transit to the database. Also notifies the
	 * appropriate model listeners.
	 *
	 * @param issuePermissionForTransit the issue permission for transit
	 * @return the issue permission for transit that was added
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePermissionForTransit addIssuePermissionForTransit(IssuePermissionForTransit issuePermissionForTransit)
			throws SystemException {
		

		issuePermissionForTransit = persistence.updateImpl(issuePermissionForTransit, false);

		return issuePermissionForTransit;
	}

	/**
	 * Creates a new issue permission for transit with the primary key. Does not add
	 * the issue permission for transit to the database.
	 *
	 * @param id the primary key for the new issue permission for transit
	 * @return the new issue permission for transit
	 */
	public IssuePermissionForTransit createIssuePermissionForTransit(long id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the issue permission for transit with the primary key from the
	 * database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the issue permission for transit
	 * @throws PortalException if a issue permission for transit with the primary
	 *                         key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteIssuePermissionForTransit(long id) throws PortalException, SystemException {
		IssuePermissionForTransit issuePermissionForTransit = persistence.remove(id);

	}

	/**
	 * Deletes the issue permission for transit from the database. Also notifies the
	 * appropriate model listeners.
	 *
	 * @param issuePermissionForTransit the issue permission for transit
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteIssuePermissionForTransit(IssuePermissionForTransit issuePermissionForTransit)
			throws SystemException {
		persistence.remove(issuePermissionForTransit);

	}

	public IssuePermissionForTransit fetchIssuePermissionForTransit(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the issue permission for transit with the primary key.
	 *
	 * @param id the primary key of the issue permission for transit
	 * @return the issue permission for transit
	 * @throws PortalException if a issue permission for transit with the primary
	 *                         key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePermissionForTransit getIssuePermissionForTransit(long id) throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	/**
	 * Returns a range of all the issue permission for transits.
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
	 * @param start the lower bound of the range of issue permission for transits
	 * @param end   the upper bound of the range of issue permission for transits
	 *              (not inclusive)
	 * @return the range of issue permission for transits
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssuePermissionForTransit> getIssuePermissionForTransits(int start, int end) throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of issue permission for transits.
	 *
	 * @return the number of issue permission for transits
	 * @throws SystemException if a system exception occurred
	 */
	public int getIssuePermissionForTransitsCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the issue permission for transit in the database or adds it if it
	 * does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param issuePermissionForTransit the issue permission for transit
	 * @return the issue permission for transit that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePermissionForTransit updateIssuePermissionForTransit(
			IssuePermissionForTransit issuePermissionForTransit) throws SystemException {
		return updateIssuePermissionForTransit(issuePermissionForTransit, true);
	}

	/**
	 * Updates the issue permission for transit in the database or adds it if it
	 * does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param issuePermissionForTransit the issue permission for transit
	 * @param merge                     whether to merge the issue permission for
	 *                                  transit with the current session. See
	 *                                  
	 *                                  for an explanation.
	 * @return the issue permission for transit that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePermissionForTransit updateIssuePermissionForTransit(
			IssuePermissionForTransit issuePermissionForTransit, boolean merge) throws SystemException {
		

		issuePermissionForTransit = persistence.updateImpl(issuePermissionForTransit, merge);

		return issuePermissionForTransit;
	}

}