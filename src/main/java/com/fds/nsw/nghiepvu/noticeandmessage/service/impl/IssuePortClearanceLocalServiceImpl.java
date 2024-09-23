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
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.IssuePortClearanceFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.IssuePortClearancePersistenceImpl;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

/**
 * The implementation of the issue port clearance local service.
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link vn.gt.dao.noticeandmessage.service.IssuePortClearanceLocalService}
 * interface.
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.noticeandmessage.service.base.IssuePortClearanceLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.issuePortClearanceLocalService
 */
public class IssuePortClearanceLocalServiceImpl {
	@Autowired
	IssuePortClearancePersistenceImpl persistence;
	@Autowired
	IssuePortClearanceFinderImpl finder;
	/*
	 * NOTE FOR DEVELOPERS: Never reference this interface directly. Always use
	 * {@link vn.gt.dao.noticeandmessage.service.issuePortClearanceLocalService}
	 * to access the issue port clearance local service.
	 */

	public List<IssuePortClearance> findIssuePortClearanceByDocumentYearAndDocumentYear(long documentName,
			int documentYear) {
		try {
			return finder.findIssuePortClearanceByDocumentYearAndDocumentYear(documentName,
					documentYear);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ArrayList<IssuePortClearance>();
	}

	public List<IssuePortClearance> findIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState(
			long documentName, int documentYear, int requestState) {
		try {
			return persistence
					.findByfindIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState(documentName,
							documentYear, requestState);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ArrayList<IssuePortClearance>();
	}

	public int countByDocumentYearAndDocumentYear(long documentName, int documentYear) throws SystemException {
		return finder.countByDocumentYearAndDocumentYear(documentName, documentYear);
	}

	public List<IssuePortClearance> findIssuePortClearanceByRequestCode(String requestCode) {
		try {
			return persistence.findByRequestCode(requestCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public IssuePortClearance findLastestIssuePortClearanceByPortNameAndCallSign(String nameOfShip, String callSign) {
		try {
			return finder.findLastestIssuePortClearanceByPortNameAndCallSign(nameOfShip, callSign);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public IssuePortClearance findLatestCertificateOfIssuePortClearance(String nameOfShip, String callSign,
			String certificateNo) {
		try {
			return finder.findLatestCertificateOfIssuePortClearance(nameOfShip, callSign,
					certificateNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public IssuePortClearance findByRequestCode(String requestCode) {
		try {
			return finder.findByRequestCode(requestCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public IssuePortClearance getByRequestCode(String requestCode) {
		try {
			return finder.findByRequestCode(requestCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<IssuePortClearance> findIssuePortClearanceInfo(String requestState, String maritimeCode,
			String portCode, String shipName, String callSign, String shipDateFrom, String shipDateTo,
			String certificateNumber, int start, int end) {
		try {
			return finder.findIssuePortClearanceInfo(requestState, maritimeCode, portCode, shipName,
					callSign, shipDateFrom, shipDateTo, certificateNumber, start, end);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int countIssuePortClearanceInfo(String requestState, String maritimeCode, String portCode, String shipName,
			String callSign, String shipDateFrom, String shipDateTo, String certificateNumber) {
		try {
			return finder.countIssuePortClearanceInfo(requestState, maritimeCode, portCode, shipName,
					callSign, shipDateFrom, shipDateTo, certificateNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<IssuePortClearance> findIssuePortClearanceInfoByCertificateNumber(String requestState,
			String maritimeCode, String portCode, String shipName, String callSign, String shipDateFrom,
			String shipDateTo, String certificateNumber, int start, int end) {
		try {
			return finder.findIssuePortClearanceInfoByCertificateNumber(requestState, maritimeCode,
					portCode, shipName, callSign, shipDateFrom, shipDateTo, certificateNumber, start, end);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int countIssuePortClearanceInfoByCertificateNumber(String requestState, String maritimeCode, String portCode,
			String shipName, String callSign, String shipDateFrom, String shipDateTo, String certificateNumber) {
		try {
			return finder.countIssuePortClearanceInfoByCertificateNumber(requestState, maritimeCode,
					portCode, shipName, callSign, shipDateFrom, shipDateTo, certificateNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<IssuePortClearance> findByDocumentYearAndDocumentYearOrderByColumn(long documentName, int documentYear,
			String orderByColumn, boolean ascOrdesc) {
		try {
			return finder.findByDocumentYearAndDocumentYearOrderByColumn(documentName, documentYear,
					orderByColumn, ascOrdesc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<IssuePortClearance>();
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

	public IssuePortClearance findByF_LAST_numberPortClearance(long documentName, int documentYear,
			String numberPortClearance) {
		try {
			return persistence.findByF_LAST_numberPortClearance_First(numberPortClearance, null);
		} catch (Exception e) {
			return null;
		}
	}

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link
	 * vn.gt.dao.noticeandmessage.service.issuePortClearanceLocalService} to
	 * access the issue port clearance local service.
	 */

	/**
	 * Adds the issue port clearance to the database. Also notifies the appropriate
	 * model listeners.
	 *
	 * @param issuePortClearance the issue port clearance
	 * @return the issue port clearance that was added
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePortClearance addIssuePortClearance(IssuePortClearance issuePortClearance) throws SystemException {
		

		issuePortClearance = persistence.updateImpl(issuePortClearance, false);

		return issuePortClearance;
	}

	/**
	 * Creates a new issue port clearance with the primary key. Does not add the
	 * issue port clearance to the database.
	 *
	 * @param id the primary key for the new issue port clearance
	 * @return the new issue port clearance
	 */
	public IssuePortClearance createIssuePortClearance(long id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the issue port clearance with the primary key from the database. Also
	 * notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the issue port clearance
	 * @throws PortalException if a issue port clearance with the primary key could
	 *                         not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteIssuePortClearance(long id) throws PortalException, SystemException {
		IssuePortClearance issuePortClearance = persistence.remove(id);

	}

	/**
	 * Deletes the issue port clearance from the database. Also notifies the
	 * appropriate model listeners.
	 *
	 * @param issuePortClearance the issue port clearance
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteIssuePortClearance(IssuePortClearance issuePortClearance) throws SystemException {
		persistence.remove(issuePortClearance);

	}

	public IssuePortClearance fetchIssuePortClearance(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the issue port clearance with the primary key.
	 *
	 * @param id the primary key of the issue port clearance
	 * @return the issue port clearance
	 * @throws PortalException if a issue port clearance with the primary key could
	 *                         not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePortClearance getIssuePortClearance(long id) throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}



	/**
	 * Returns a range of all the issue port clearances.
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
	 * @param start the lower bound of the range of issue port clearances
	 * @param end   the upper bound of the range of issue port clearances (not
	 *              inclusive)
	 * @return the range of issue port clearances
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssuePortClearance> getIssuePortClearances(int start, int end) throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of issue port clearances.
	 *
	 * @return the number of issue port clearances
	 * @throws SystemException if a system exception occurred
	 */
	public int getIssuePortClearancesCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the issue port clearance in the database or adds it if it does not
	 * yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param issuePortClearance the issue port clearance
	 * @return the issue port clearance that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePortClearance updateIssuePortClearance(IssuePortClearance issuePortClearance) throws SystemException {
		return updateIssuePortClearance(issuePortClearance, true);
	}

	/**
	 * Updates the issue port clearance in the database or adds it if it does not
	 * yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param issuePortClearance the issue port clearance
	 * @param merge              whether to merge the issue port clearance with the
	 *                           current session. See
	 *                           
	 *                           for an explanation.
	 * @return the issue port clearance that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePortClearance updateIssuePortClearance(IssuePortClearance issuePortClearance, boolean merge)
			throws SystemException {
		

		issuePortClearance = persistence.updateImpl(issuePortClearance, merge);

		return issuePortClearance;
	}
}