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
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.IssueShiftingOrderFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.IssueShiftingOrderPersistenceImpl;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

/**
 * The implementation of the issue shifting order local service.
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link vn.gt.dao.noticeandmessage.service.IssueShiftingOrderLocalService}
 * interface.
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.noticeandmessage.service.base.IssueShiftingOrderLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.issueShiftingOrderLocalService
 */
public class IssueShiftingOrderLocalServiceImpl {
	@Autowired
	IssueShiftingOrderPersistenceImpl persistence;
	@Autowired
	IssueShiftingOrderFinderImpl finder;

	/*
	 * NOTE FOR DEVELOPERS: Never reference this interface directly. Always use
	 * {@link vn.gt.dao.noticeandmessage.service.issueShiftingOrderLocalService}
	 * to access the issue shifting order local service.
	 */
	public List<IssueShiftingOrder> findIssueShiftingOrderByDocumentYearAndDocumentYear(long documentName,
			int documentYear) {
		try {
			return finder.findIssueShiftingOrderByDocumentYearAndDocumentYear(documentName,
					documentYear);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public List<IssueShiftingOrder> findIssueShiftingOrderByDocumentYearAndDocumentYear(long documentName,
			int documentYear, int requestState) {
		try {
			return finder.findIssueShiftingOrderByDocumentYearAndDocumentYearAndRequestState(
					documentName, documentYear, requestState);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public List<IssueShiftingOrder> findByRequestCode(String requestCode) {
		try {
			return persistence.findByRequestCode(requestCode);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public IssueShiftingOrder getByRequestCode(String requestCode) {
		try {
			List<IssueShiftingOrder> list = persistence.findByRequestCode(requestCode);
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public List<IssueShiftingOrder> findIssueShiftingOrderInfo(String requestState, String maritimeCode,
			String portCode, String shipName, String callSign, String shipDateFrom, String shipDateTo,
			String certificateNumber, int start, int end) {
		try {
			return finder.findIssueShiftingOrderInfo(requestState, maritimeCode, portCode, shipName,
					callSign, shipDateFrom, shipDateTo, certificateNumber, start, end);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public int countIssueShiftingOrderInfo(String requestState, String maritimeCode, String portCode, String shipName,
			String callSign, String shipDateFrom, String shipDateTo, String certificateNumber) {
		try {
			return finder.countIssueShiftingOrderInfo(requestState, maritimeCode, portCode, shipName,
					callSign, shipDateFrom, shipDateTo, certificateNumber);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return 0;
	}

	public List<IssueShiftingOrder> findIssueShiftingOrderInfoByCertificateNumber(String requestState,
			String maritimeCode, String portCode, String shipName, String callSign, String shipDateFrom,
			String shipDateTo, String certificateNumber, int start, int end) {
		try {
			return finder.findIssueShiftingOrderInfoByCertificateNumber(requestState, maritimeCode,
					portCode, shipName, callSign, shipDateFrom, shipDateTo, certificateNumber, start, end);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public int countIssueShiftingOrderInfoByCertificateNumber(String requestState, String maritimeCode, String portCode,
			String shipName, String callSign, String shipDateFrom, String shipDateTo, String certificateNumber) {
		try {
			return finder.countIssueShiftingOrderInfoByCertificateNumber(requestState, maritimeCode,
					portCode, shipName, callSign, shipDateFrom, shipDateTo, certificateNumber);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return 0;
	}

	public List<IssueShiftingOrder> findByDocumentYearAndDocumentYearOrderByColumn(long documentName, int documentYear,
			String nameColumn, boolean ascOrdesc) {
		try {
			return finder.findByDocumentYearAndDocumentYearOrderByColumn(documentName, documentYear,
					nameColumn, ascOrdesc);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return new ArrayList<IssueShiftingOrder>();
	}

	public IssueShiftingOrder getByDocumentNameAndDocumentYearAndVersionNo(long documentName, int documentYear,
			String versionNo) {
		try {
			List<IssueShiftingOrder> list = persistence
					.findByDocumentNameAndDocumentYearAndVersionNo(documentName, documentYear, versionNo.trim());
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		} catch (SystemException e) {
			log.error(e.getMessage());
		}
		return new IssueShiftingOrder();
	}

	public IssueShiftingOrder getVersionNoMaxByDocumentYearAndDocumentYear(long documentName, int documentYear) {
		try {
			return finder.getVersionNoMaxByDocumentYearAndDocumentYear(documentName, documentYear);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public int countByDocumentNameAndDocumentYear(long documentName, int documentYear) {
		try {
			return finder.countByDocumentNameAndDocumentYear(documentName, documentYear);
		} catch (Exception e) {
			log.error(e.getMessage());
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
	 * vn.gt.dao.noticeandmessage.service.issueShiftingOrderLocalService} to
	 * access the issue shifting order local service.
	 */

	/**
	 * Adds the issue shifting order to the database. Also notifies the appropriate
	 * model listeners.
	 *
	 * @param issueShiftingOrder the issue shifting order
	 * @return the issue shifting order that was added
	 * @throws SystemException if a system exception occurred
	 */
	public IssueShiftingOrder addIssueShiftingOrder(IssueShiftingOrder issueShiftingOrder) throws SystemException {
		

		issueShiftingOrder = persistence.updateImpl(issueShiftingOrder, false);

		return issueShiftingOrder;
	}

	/**
	 * Creates a new issue shifting order with the primary key. Does not add the
	 * issue shifting order to the database.
	 *
	 * @param id the primary key for the new issue shifting order
	 * @return the new issue shifting order
	 */
	public IssueShiftingOrder createIssueShiftingOrder(long id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the issue shifting order with the primary key from the database. Also
	 * notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the issue shifting order
	 * @throws PortalException if a issue shifting order with the primary key could
	 *                         not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteIssueShiftingOrder(long id) throws PortalException, SystemException {
		IssueShiftingOrder issueShiftingOrder = persistence.remove(id);

	}

	/**
	 * Deletes the issue shifting order from the database. Also notifies the
	 * appropriate model listeners.
	 *
	 * @param issueShiftingOrder the issue shifting order
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteIssueShiftingOrder(IssueShiftingOrder issueShiftingOrder) throws SystemException {
		persistence.remove(issueShiftingOrder);

	}

	public IssueShiftingOrder fetchIssueShiftingOrder(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the issue shifting order with the primary key.
	 *
	 * @param id the primary key of the issue shifting order
	 * @return the issue shifting order
	 * @throws PortalException if a issue shifting order with the primary key could
	 *                         not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueShiftingOrder getIssueShiftingOrder(long id) throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public IssueShiftingOrder getPersistedModel(Serializable primaryKeyObj) throws PortalException, SystemException {
		return persistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the issue shifting orders.
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
	 * @param start the lower bound of the range of issue shifting orders
	 * @param end   the upper bound of the range of issue shifting orders (not
	 *              inclusive)
	 * @return the range of issue shifting orders
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssueShiftingOrder> getIssueShiftingOrders(int start, int end) throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of issue shifting orders.
	 *
	 * @return the number of issue shifting orders
	 * @throws SystemException if a system exception occurred
	 */
	public int getIssueShiftingOrdersCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the issue shifting order in the database or adds it if it does not
	 * yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param issueShiftingOrder the issue shifting order
	 * @return the issue shifting order that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public IssueShiftingOrder updateIssueShiftingOrder(IssueShiftingOrder issueShiftingOrder) throws SystemException {
		return updateIssueShiftingOrder(issueShiftingOrder, true);
	}

	/**
	 * Updates the issue shifting order in the database or adds it if it does not
	 * yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param issueShiftingOrder the issue shifting order
	 * @param merge              whether to merge the issue shifting order with the
	 *                           current session. See
	 *                           
	 *                           for an explanation.
	 * @return the issue shifting order that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public IssueShiftingOrder updateIssueShiftingOrder(IssueShiftingOrder issueShiftingOrder, boolean merge)
			throws SystemException {
		

		issueShiftingOrder = persistence.updateImpl(issueShiftingOrder, merge);

		return issueShiftingOrder;
	}

}