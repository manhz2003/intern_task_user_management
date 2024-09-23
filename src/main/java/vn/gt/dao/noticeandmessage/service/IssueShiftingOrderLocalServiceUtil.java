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

package vn.gt.dao.noticeandmessage.service;






/**
 * The utility for the issue shifting order local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.IssueShiftingOrderLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see IssueShiftingOrderLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.IssueShiftingOrderLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.IssueShiftingOrderLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.IssueShiftingOrderLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class IssueShiftingOrderLocalServiceUtil {
public IssueShiftingOrderLocalServiceUtil(IssueShiftingOrderLocalServiceImpl service) {
IssueShiftingOrderLocalServiceUtil._service = service;
}
public static IssueShiftingOrderLocalServiceImpl getService() {
return _service;
}
private static IssueShiftingOrderLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.IssueShiftingOrderLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the issue shifting order to the database. Also notifies the appropriate model listeners.
	*
	* @param issueShiftingOrder the issue shifting order
	* @return the issue shifting order that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.IssueShiftingOrder addIssueShiftingOrder(
		com.fds.nsw.nghiepvu.model.IssueShiftingOrder issueShiftingOrder)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addIssueShiftingOrder(issueShiftingOrder);
	}

	/**
	* Creates a new issue shifting order with the primary key. Does not add the issue shifting order to the database.
	*
	* @param id the primary key for the new issue shifting order
	* @return the new issue shifting order
	*/
	public static com.fds.nsw.nghiepvu.model.IssueShiftingOrder createIssueShiftingOrder(
		long id) {
		return getService().createIssueShiftingOrder(id);
	}

	/**
	* Deletes the issue shifting order with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the issue shifting order
	* @throws PortalException if a issue shifting order with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteIssueShiftingOrder(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteIssueShiftingOrder(id);
	}

	/**
	* Deletes the issue shifting order from the database. Also notifies the appropriate model listeners.
	*
	* @param issueShiftingOrder the issue shifting order
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteIssueShiftingOrder(
		com.fds.nsw.nghiepvu.model.IssueShiftingOrder issueShiftingOrder)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteIssueShiftingOrder(issueShiftingOrder);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.IssueShiftingOrder fetchIssueShiftingOrder(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchIssueShiftingOrder(id);
	}

	/**
	* Returns the issue shifting order with the primary key.
	*
	* @param id the primary key of the issue shifting order
	* @return the issue shifting order
	* @throws PortalException if a issue shifting order with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.IssueShiftingOrder getIssueShiftingOrder(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getIssueShiftingOrder(id);
	}

	

	/**
	* Returns a range of all the issue shifting orders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of issue shifting orders
	* @param end the upper bound of the range of issue shifting orders (not inclusive)
	* @return the range of issue shifting orders
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.IssueShiftingOrder> getIssueShiftingOrders(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getIssueShiftingOrders(start, end);
	}

	/**
	* Returns the number of issue shifting orders.
	*
	* @return the number of issue shifting orders
	* @throws SystemException if a system exception occurred
	*/
	public static int getIssueShiftingOrdersCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getIssueShiftingOrdersCount();
	}

	/**
	* Updates the issue shifting order in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param issueShiftingOrder the issue shifting order
	* @return the issue shifting order that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.IssueShiftingOrder updateIssueShiftingOrder(
		com.fds.nsw.nghiepvu.model.IssueShiftingOrder issueShiftingOrder)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateIssueShiftingOrder(issueShiftingOrder);
	}

	/**
	* Updates the issue shifting order in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param issueShiftingOrder the issue shifting order
	* @param merge whether to merge the issue shifting order with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the issue shifting order that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.IssueShiftingOrder updateIssueShiftingOrder(
		com.fds.nsw.nghiepvu.model.IssueShiftingOrder issueShiftingOrder,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateIssueShiftingOrder(issueShiftingOrder, merge);
	}

	

	

	public static java.util.List<com.fds.nsw.nghiepvu.model.IssueShiftingOrder> findIssueShiftingOrderByDocumentYearAndDocumentYear(
		long documentName, int documentYear) {
		return getService()
				   .findIssueShiftingOrderByDocumentYearAndDocumentYear(documentName,
			documentYear);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.IssueShiftingOrder> findIssueShiftingOrderByDocumentYearAndDocumentYear(
		long documentName, int documentYear, int requestState) {
		return getService()
				   .findIssueShiftingOrderByDocumentYearAndDocumentYear(documentName,
			documentYear, requestState);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.IssueShiftingOrder> findByRequestCode(
		java.lang.String requestCode) {
		return getService().findByRequestCode(requestCode);
	}

	public static com.fds.nsw.nghiepvu.model.IssueShiftingOrder getByRequestCode(
		java.lang.String requestCode) {
		return getService().getByRequestCode(requestCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.IssueShiftingOrder> findIssueShiftingOrderInfo(
		java.lang.String requestState, java.lang.String maritimeCode,
		java.lang.String portCode, java.lang.String shipName,
		java.lang.String callSign, java.lang.String shipDateFrom,
		java.lang.String shipDateTo, java.lang.String certificateNumber,
		int start, int end) {
		return getService()
				   .findIssueShiftingOrderInfo(requestState, maritimeCode,
			portCode, shipName, callSign, shipDateFrom, shipDateTo,
			certificateNumber, start, end);
	}

	public static int countIssueShiftingOrderInfo(
		java.lang.String requestState, java.lang.String maritimeCode,
		java.lang.String portCode, java.lang.String shipName,
		java.lang.String callSign, java.lang.String shipDateFrom,
		java.lang.String shipDateTo, java.lang.String certificateNumber) {
		return getService()
				   .countIssueShiftingOrderInfo(requestState, maritimeCode,
			portCode, shipName, callSign, shipDateFrom, shipDateTo,
			certificateNumber);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.IssueShiftingOrder> findIssueShiftingOrderInfoByCertificateNumber(
		java.lang.String requestState, java.lang.String maritimeCode,
		java.lang.String portCode, java.lang.String shipName,
		java.lang.String callSign, java.lang.String shipDateFrom,
		java.lang.String shipDateTo, java.lang.String certificateNumber,
		int start, int end) {
		return getService()
				   .findIssueShiftingOrderInfoByCertificateNumber(requestState,
			maritimeCode, portCode, shipName, callSign, shipDateFrom,
			shipDateTo, certificateNumber, start, end);
	}

	public static int countIssueShiftingOrderInfoByCertificateNumber(
		java.lang.String requestState, java.lang.String maritimeCode,
		java.lang.String portCode, java.lang.String shipName,
		java.lang.String callSign, java.lang.String shipDateFrom,
		java.lang.String shipDateTo, java.lang.String certificateNumber) {
		return getService()
				   .countIssueShiftingOrderInfoByCertificateNumber(requestState,
			maritimeCode, portCode, shipName, callSign, shipDateFrom,
			shipDateTo, certificateNumber);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.IssueShiftingOrder> findByDocumentYearAndDocumentYearOrderByColumn(
		long documentName, int documentYear, java.lang.String nameColumn,
		boolean ascOrdesc) {
		return getService()
				   .findByDocumentYearAndDocumentYearOrderByColumn(documentName,
			documentYear, nameColumn, ascOrdesc);
	}

	public static com.fds.nsw.nghiepvu.model.IssueShiftingOrder getByDocumentNameAndDocumentYearAndVersionNo(
		long documentName, int documentYear, java.lang.String versionNo) {
		return getService()
				   .getByDocumentNameAndDocumentYearAndVersionNo(documentName,
			documentYear, versionNo);
	}

	public static com.fds.nsw.nghiepvu.model.IssueShiftingOrder getVersionNoMaxByDocumentYearAndDocumentYear(
		long documentName, int documentYear) {
		return getService()
				   .getVersionNoMaxByDocumentYearAndDocumentYear(documentName,
			documentYear);
	}

	public static int countByDocumentNameAndDocumentYear(long documentName,
		int documentYear) {
		return getService()
				   .countByDocumentNameAndDocumentYear(documentName,
			documentYear);
	}

	public static java.lang.String capGiayPhepSo(
		java.lang.String maritimeReference)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().capGiayPhepSo(maritimeReference);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}