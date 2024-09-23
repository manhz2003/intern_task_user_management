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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;
import com.fds.nsw.nghiepvu.model.IssueAcceptance;
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.IssueAcceptanceFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.IssueAcceptancePersistenceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

/**
 * The implementation of the issue acceptance local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link vn.gt.dao.noticeandmessage.service.IssueAcceptanceLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.noticeandmessage.service.base.IssueAcceptanceLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.IssueAcceptanceLocalServiceUtil
 */
public class IssueAcceptanceLocalServiceImpl {
	@Autowired
	IssueAcceptancePersistenceImpl persistence;
	@Autowired
	IssueAcceptanceFinderImpl finder;

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link
	 * vn.gt.dao.noticeandmessage.service.IssueAcceptanceLocalServiceUtil} to access
	 * the issue acceptance local service.
	 */
	public IssueAcceptance fetchByRequestCode(String requestCode) throws SystemException {
		return persistence.fetchByRequestCode(requestCode);
	}

	public List<IssueAcceptance> findIssueAcceptanceByDocumentYearAndDocumentYear(long documentName, int documentYear) {
		try {
			return finder.findIssueAcceptanceByDocumentYearAndDocumentYear(documentName, documentYear);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ArrayList<IssueAcceptance>();
	}

	public List<IssueAcceptance> findIssueAcceptanceByDocumentYearAndDocumentYearAndRequestState(long documentName,
			int documentYear, int requestState) {
		try {
			return finder.findIssueAcceptanceByDocumentYearAndDocumentYearAndRequestState(documentName,
					documentYear, requestState);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ArrayList<IssueAcceptance>();
	}

	public int countByDocumentYearAndDocumentYear(long documentName, int documentYear) throws SystemException {
		return finder.countByDocumentYearAndDocumentYear(documentName, documentYear);
	}

	public IssueAcceptance findIssueAcceptanceByRequestCode(String requestCode) {
		try {
			return finder.findByRequestCode(requestCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public IssueAcceptance findLastestIssueAcceptanceByPortNameAndCallSign(String nameOfShip, String callSign) {
		try {
			return finder.findLastestIssueAcceptanceByPortNameAndCallSign(nameOfShip, callSign);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public IssueAcceptance findByRequestCode(String requestCode) {
		try {
			return finder.findByRequestCode(requestCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public IssueAcceptance getByRequestCode(String requestCode) {
		try {
			return finder.findByRequestCode(requestCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<IssueAcceptance> findIssueAcceptanceInfo(String requestState, String maritimeCode, String portCode,
			String shipName, String callSign, String shipDateFrom, String shipDateTo, int start, int end) {
		try {
			return finder.findIssueAcceptanceInfo(requestState, maritimeCode, portCode, shipName,
					callSign, shipDateFrom, shipDateTo, start, end);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int countIssueAcceptanceInfo(String requestState, String maritimeCode, String portCode, String shipName,
			String callSign, String shipDateFrom, String shipDateTo) {
		try {
			return finder.countIssueAcceptanceInfo(requestState, maritimeCode, portCode, shipName,
					callSign, shipDateFrom, shipDateTo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<IssueAcceptance> findByDocumentYearAndDocumentYearOrderByColumn(long documentName, int documentYear,
			String orderByColumn, boolean ascOrdesc) {
		try {
			return finder.findByDocumentYearAndDocumentYearOrderByColumn(documentName, documentYear,
					orderByColumn, ascOrdesc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<IssueAcceptance>();
	}

	public int countByDocumentNameAndDocumentYear(long documentName, int documentYear) {
		try {
			return finder.countByDocumentYearAndDocumentYear(documentName, documentYear);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public String capGiayPhepSo(String maritimeReference) throws SystemException {
		return finder.capGiayPhepSo(maritimeReference);
	}

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link
	 * vn.gt.dao.noticeandmessage.service.IssueAcceptanceLocalServiceUtil} to access
	 * the issue acceptance local service.
	 */

	/**
	 * Adds the issue acceptance to the database. Also notifies the appropriate
	 * model listeners.
	 *
	 * @param issueAcceptance the issue acceptance
	 * @return the issue acceptance that was added
	 * @throws SystemException if a system exception occurred
	 */
	public IssueAcceptance addIssueAcceptance(IssueAcceptance issueAcceptance) throws SystemException {
		

		issueAcceptance = persistence.updateImpl(issueAcceptance, false);

		return issueAcceptance;
	}

	/**
	 * Creates a new issue acceptance with the primary key. Does not add the issue
	 * acceptance to the database.
	 *
	 * @param id the primary key for the new issue acceptance
	 * @return the new issue acceptance
	 */
	public IssueAcceptance createIssueAcceptance(long id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the issue acceptance with the primary key from the database. Also
	 * notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the issue acceptance
	 * @throws PortalException if a issue acceptance with the primary key could not
	 *                         be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteIssueAcceptance(long id) throws PortalException, SystemException {
		IssueAcceptance issueAcceptance = persistence.remove(id);

	}

	/**
	 * Deletes the issue acceptance from the database. Also notifies the appropriate
	 * model listeners.
	 *
	 * @param issueAcceptance the issue acceptance
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteIssueAcceptance(IssueAcceptance issueAcceptance) throws SystemException {
		persistence.remove(issueAcceptance);

	}

	public IssueAcceptance fetchIssueAcceptance(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the issue acceptance with the primary key.
	 *
	 * @param id the primary key of the issue acceptance
	 * @return the issue acceptance
	 * @throws PortalException if a issue acceptance with the primary key could not
	 *                         be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueAcceptance getIssueAcceptance(long id) throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}


	/**
	 * Returns a range of all the issue acceptances.
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
	 * @param start the lower bound of the range of issue acceptances
	 * @param end   the upper bound of the range of issue acceptances (not
	 *              inclusive)
	 * @return the range of issue acceptances
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssueAcceptance> getIssueAcceptances(int start, int end) throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of issue acceptances.
	 *
	 * @return the number of issue acceptances
	 * @throws SystemException if a system exception occurred
	 */
	public int getIssueAcceptancesCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the issue acceptance in the database or adds it if it does not yet
	 * exist. Also notifies the appropriate model listeners.
	 *
	 * @param issueAcceptance the issue acceptance
	 * @return the issue acceptance that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public IssueAcceptance updateIssueAcceptance(IssueAcceptance issueAcceptance) throws SystemException {
		return updateIssueAcceptance(issueAcceptance, true);
	}

	/**
	 * Updates the issue acceptance in the database or adds it if it does not yet
	 * exist. Also notifies the appropriate model listeners.
	 *
	 * @param issueAcceptance the issue acceptance
	 * @param merge           whether to merge the issue acceptance with the current
	 *                        session. See
	 *                        
	 *                        for an explanation.
	 * @return the issue acceptance that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public IssueAcceptance updateIssueAcceptance(IssueAcceptance issueAcceptance, boolean merge)
			throws SystemException {
		

		issueAcceptance = persistence.updateImpl(issueAcceptance, merge);

		return issueAcceptance;
	}
}