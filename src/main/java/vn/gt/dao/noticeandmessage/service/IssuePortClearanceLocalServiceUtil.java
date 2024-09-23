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
 * The utility for the issue port clearance local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.IssuePortClearanceLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see IssuePortClearanceLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.IssuePortClearanceLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.IssuePortClearanceLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.IssuePortClearanceLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class IssuePortClearanceLocalServiceUtil {
public IssuePortClearanceLocalServiceUtil(IssuePortClearanceLocalServiceImpl service) {
IssuePortClearanceLocalServiceUtil._service = service;
}
public static IssuePortClearanceLocalServiceImpl getService() {
return _service;
}
private static IssuePortClearanceLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.IssuePortClearanceLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the issue port clearance to the database. Also notifies the appropriate model listeners.
	*
	* @param issuePortClearance the issue port clearance
	* @return the issue port clearance that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.IssuePortClearance addIssuePortClearance(
		com.fds.nsw.nghiepvu.model.IssuePortClearance issuePortClearance)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addIssuePortClearance(issuePortClearance);
	}

	/**
	* Creates a new issue port clearance with the primary key. Does not add the issue port clearance to the database.
	*
	* @param id the primary key for the new issue port clearance
	* @return the new issue port clearance
	*/
	public static com.fds.nsw.nghiepvu.model.IssuePortClearance createIssuePortClearance(
		long id) {
		return getService().createIssuePortClearance(id);
	}

	/**
	* Deletes the issue port clearance with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the issue port clearance
	* @throws PortalException if a issue port clearance with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteIssuePortClearance(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteIssuePortClearance(id);
	}

	/**
	* Deletes the issue port clearance from the database. Also notifies the appropriate model listeners.
	*
	* @param issuePortClearance the issue port clearance
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteIssuePortClearance(
		com.fds.nsw.nghiepvu.model.IssuePortClearance issuePortClearance)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteIssuePortClearance(issuePortClearance);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.IssuePortClearance fetchIssuePortClearance(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchIssuePortClearance(id);
	}

	/**
	* Returns the issue port clearance with the primary key.
	*
	* @param id the primary key of the issue port clearance
	* @return the issue port clearance
	* @throws PortalException if a issue port clearance with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.IssuePortClearance getIssuePortClearance(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getIssuePortClearance(id);
	}

	

	/**
	* Returns a range of all the issue port clearances.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of issue port clearances
	* @param end the upper bound of the range of issue port clearances (not inclusive)
	* @return the range of issue port clearances
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.IssuePortClearance> getIssuePortClearances(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getIssuePortClearances(start, end);
	}

	/**
	* Returns the number of issue port clearances.
	*
	* @return the number of issue port clearances
	* @throws SystemException if a system exception occurred
	*/
	public static int getIssuePortClearancesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getIssuePortClearancesCount();
	}

	/**
	* Updates the issue port clearance in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param issuePortClearance the issue port clearance
	* @return the issue port clearance that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.IssuePortClearance updateIssuePortClearance(
		com.fds.nsw.nghiepvu.model.IssuePortClearance issuePortClearance)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateIssuePortClearance(issuePortClearance);
	}

	/**
	* Updates the issue port clearance in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param issuePortClearance the issue port clearance
	* @param merge whether to merge the issue port clearance with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the issue port clearance that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.IssuePortClearance updateIssuePortClearance(
		com.fds.nsw.nghiepvu.model.IssuePortClearance issuePortClearance,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateIssuePortClearance(issuePortClearance, merge);
	}

	

	

	public static java.util.List<com.fds.nsw.nghiepvu.model.IssuePortClearance> findIssuePortClearanceByDocumentYearAndDocumentYear(
		long documentName, int documentYear) {
		return getService()
				   .findIssuePortClearanceByDocumentYearAndDocumentYear(documentName,
			documentYear);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.IssuePortClearance> findIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState(
		long documentName, int documentYear, int requestState) {
		return getService()
				   .findIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState(documentName,
			documentYear, requestState);
	}

	public static int countByDocumentYearAndDocumentYear(long documentName,
		int documentYear)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .countByDocumentYearAndDocumentYear(documentName,
			documentYear);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.IssuePortClearance> findIssuePortClearanceByRequestCode(
		java.lang.String requestCode) {
		return getService().findIssuePortClearanceByRequestCode(requestCode);
	}

	public static com.fds.nsw.nghiepvu.model.IssuePortClearance findLastestIssuePortClearanceByPortNameAndCallSign(
		java.lang.String nameOfShip, java.lang.String callSign) {
		return getService()
				   .findLastestIssuePortClearanceByPortNameAndCallSign(nameOfShip,
			callSign);
	}

	public static com.fds.nsw.nghiepvu.model.IssuePortClearance findLatestCertificateOfIssuePortClearance(
		java.lang.String nameOfShip, java.lang.String callSign,
		java.lang.String certificateNo) {
		return getService()
				   .findLatestCertificateOfIssuePortClearance(nameOfShip,
			callSign, certificateNo);
	}

	public static com.fds.nsw.nghiepvu.model.IssuePortClearance findByRequestCode(
		java.lang.String requestCode) {
		return getService().findByRequestCode(requestCode);
	}

	public static com.fds.nsw.nghiepvu.model.IssuePortClearance getByRequestCode(
		java.lang.String requestCode) {
		return getService().getByRequestCode(requestCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.IssuePortClearance> findIssuePortClearanceInfo(
		java.lang.String requestState, java.lang.String maritimeCode,
		java.lang.String portCode, java.lang.String shipName,
		java.lang.String callSign, java.lang.String shipDateFrom,
		java.lang.String shipDateTo, java.lang.String certificateNumber,
		int start, int end) {
		return getService()
				   .findIssuePortClearanceInfo(requestState, maritimeCode,
			portCode, shipName, callSign, shipDateFrom, shipDateTo,
			certificateNumber, start, end);
	}

	public static int countIssuePortClearanceInfo(
		java.lang.String requestState, java.lang.String maritimeCode,
		java.lang.String portCode, java.lang.String shipName,
		java.lang.String callSign, java.lang.String shipDateFrom,
		java.lang.String shipDateTo, java.lang.String certificateNumber) {
		return getService()
				   .countIssuePortClearanceInfo(requestState, maritimeCode,
			portCode, shipName, callSign, shipDateFrom, shipDateTo,
			certificateNumber);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.IssuePortClearance> findIssuePortClearanceInfoByCertificateNumber(
		java.lang.String requestState, java.lang.String maritimeCode,
		java.lang.String portCode, java.lang.String shipName,
		java.lang.String callSign, java.lang.String shipDateFrom,
		java.lang.String shipDateTo, java.lang.String certificateNumber,
		int start, int end) {
		return getService()
				   .findIssuePortClearanceInfoByCertificateNumber(requestState,
			maritimeCode, portCode, shipName, callSign, shipDateFrom,
			shipDateTo, certificateNumber, start, end);
	}

	public static int countIssuePortClearanceInfoByCertificateNumber(
		java.lang.String requestState, java.lang.String maritimeCode,
		java.lang.String portCode, java.lang.String shipName,
		java.lang.String callSign, java.lang.String shipDateFrom,
		java.lang.String shipDateTo, java.lang.String certificateNumber) {
		return getService()
				   .countIssuePortClearanceInfoByCertificateNumber(requestState,
			maritimeCode, portCode, shipName, callSign, shipDateFrom,
			shipDateTo, certificateNumber);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.IssuePortClearance> findByDocumentYearAndDocumentYearOrderByColumn(
		long documentName, int documentYear, java.lang.String orderByColumn,
		boolean ascOrdesc) {
		return getService()
				   .findByDocumentYearAndDocumentYearOrderByColumn(documentName,
			documentYear, orderByColumn, ascOrdesc);
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

	public static com.fds.nsw.nghiepvu.model.IssuePortClearance findByF_LAST_numberPortClearance(
		long documentName, int documentYear,
		java.lang.String numberPortClearance) {
		return getService()
				   .findByF_LAST_numberPortClearance(documentName,
			documentYear, numberPortClearance);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}