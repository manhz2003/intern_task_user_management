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
 * The utility for the issue acceptance local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.IssueAcceptanceLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see IssueAcceptanceLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.IssueAcceptanceLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.IssueAcceptanceLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.IssueAcceptanceLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class IssueAcceptanceLocalServiceUtil {
public IssueAcceptanceLocalServiceUtil(IssueAcceptanceLocalServiceImpl service) {
IssueAcceptanceLocalServiceUtil._service = service;
}
public static IssueAcceptanceLocalServiceImpl getService() {
return _service;
}
private static IssueAcceptanceLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.IssueAcceptanceLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the issue acceptance to the database. Also notifies the appropriate model listeners.
	*
	* @param issueAcceptance the issue acceptance
	* @return the issue acceptance that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.IssueAcceptance addIssueAcceptance(
		com.fds.nsw.nghiepvu.model.IssueAcceptance issueAcceptance)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addIssueAcceptance(issueAcceptance);
	}

	/**
	* Creates a new issue acceptance with the primary key. Does not add the issue acceptance to the database.
	*
	* @param id the primary key for the new issue acceptance
	* @return the new issue acceptance
	*/
	public static com.fds.nsw.nghiepvu.model.IssueAcceptance createIssueAcceptance(
		long id) {
		return getService().createIssueAcceptance(id);
	}

	/**
	* Deletes the issue acceptance with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the issue acceptance
	* @throws PortalException if a issue acceptance with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteIssueAcceptance(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteIssueAcceptance(id);
	}

	/**
	* Deletes the issue acceptance from the database. Also notifies the appropriate model listeners.
	*
	* @param issueAcceptance the issue acceptance
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteIssueAcceptance(
		com.fds.nsw.nghiepvu.model.IssueAcceptance issueAcceptance)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteIssueAcceptance(issueAcceptance);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.IssueAcceptance fetchIssueAcceptance(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchIssueAcceptance(id);
	}

	/**
	* Returns the issue acceptance with the primary key.
	*
	* @param id the primary key of the issue acceptance
	* @return the issue acceptance
	* @throws PortalException if a issue acceptance with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.IssueAcceptance getIssueAcceptance(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getIssueAcceptance(id);
	}

	

	/**
	* Returns a range of all the issue acceptances.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of issue acceptances
	* @param end the upper bound of the range of issue acceptances (not inclusive)
	* @return the range of issue acceptances
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.IssueAcceptance> getIssueAcceptances(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getIssueAcceptances(start, end);
	}

	/**
	* Returns the number of issue acceptances.
	*
	* @return the number of issue acceptances
	* @throws SystemException if a system exception occurred
	*/
	public static int getIssueAcceptancesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getIssueAcceptancesCount();
	}

	/**
	* Updates the issue acceptance in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param issueAcceptance the issue acceptance
	* @return the issue acceptance that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.IssueAcceptance updateIssueAcceptance(
		com.fds.nsw.nghiepvu.model.IssueAcceptance issueAcceptance)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateIssueAcceptance(issueAcceptance);
	}

	/**
	* Updates the issue acceptance in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param issueAcceptance the issue acceptance
	* @param merge whether to merge the issue acceptance with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the issue acceptance that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.IssueAcceptance updateIssueAcceptance(
		com.fds.nsw.nghiepvu.model.IssueAcceptance issueAcceptance,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateIssueAcceptance(issueAcceptance, merge);
	}

	

	

	public static com.fds.nsw.nghiepvu.model.IssueAcceptance fetchByRequestCode(
		java.lang.String requestCode)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchByRequestCode(requestCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.IssueAcceptance> findIssueAcceptanceByDocumentYearAndDocumentYear(
		long documentName, int documentYear) {
		return getService()
				   .findIssueAcceptanceByDocumentYearAndDocumentYear(documentName,
			documentYear);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.IssueAcceptance> findIssueAcceptanceByDocumentYearAndDocumentYearAndRequestState(
		long documentName, int documentYear, int requestState) {
		return getService()
				   .findIssueAcceptanceByDocumentYearAndDocumentYearAndRequestState(documentName,
			documentYear, requestState);
	}

	public static int countByDocumentYearAndDocumentYear(long documentName,
		int documentYear)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .countByDocumentYearAndDocumentYear(documentName,
			documentYear);
	}

	public static com.fds.nsw.nghiepvu.model.IssueAcceptance findIssueAcceptanceByRequestCode(
		java.lang.String requestCode) {
		return getService().findIssueAcceptanceByRequestCode(requestCode);
	}

	public static com.fds.nsw.nghiepvu.model.IssueAcceptance findLastestIssueAcceptanceByPortNameAndCallSign(
		java.lang.String nameOfShip, java.lang.String callSign) {
		return getService()
				   .findLastestIssueAcceptanceByPortNameAndCallSign(nameOfShip,
			callSign);
	}

	public static com.fds.nsw.nghiepvu.model.IssueAcceptance findByRequestCode(
		java.lang.String requestCode) {
		return getService().findByRequestCode(requestCode);
	}

	public static com.fds.nsw.nghiepvu.model.IssueAcceptance getByRequestCode(
		java.lang.String requestCode) {
		return getService().getByRequestCode(requestCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.IssueAcceptance> findIssueAcceptanceInfo(
		java.lang.String requestState, java.lang.String maritimeCode,
		java.lang.String portCode, java.lang.String shipName,
		java.lang.String callSign, java.lang.String shipDateFrom,
		java.lang.String shipDateTo, int start, int end) {
		return getService()
				   .findIssueAcceptanceInfo(requestState, maritimeCode,
			portCode, shipName, callSign, shipDateFrom, shipDateTo, start, end);
	}

	public static int countIssueAcceptanceInfo(java.lang.String requestState,
		java.lang.String maritimeCode, java.lang.String portCode,
		java.lang.String shipName, java.lang.String callSign,
		java.lang.String shipDateFrom, java.lang.String shipDateTo) {
		return getService()
				   .countIssueAcceptanceInfo(requestState, maritimeCode,
			portCode, shipName, callSign, shipDateFrom, shipDateTo);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.IssueAcceptance> findByDocumentYearAndDocumentYearOrderByColumn(
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

	public static void clearService() {
		_service = null;
	}

	

	

	
}