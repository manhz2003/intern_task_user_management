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
 * The utility for the issue permission for transit local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.IssuePermissionForTransitLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see IssuePermissionForTransitLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.IssuePermissionForTransitLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.IssuePermissionForTransitLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.IssuePermissionForTransitLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class IssuePermissionForTransitLocalServiceUtil {
public IssuePermissionForTransitLocalServiceUtil(IssuePermissionForTransitLocalServiceImpl service) {
IssuePermissionForTransitLocalServiceUtil._service = service;
}
public static IssuePermissionForTransitLocalServiceImpl getService() {
return _service;
}
private static IssuePermissionForTransitLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.IssuePermissionForTransitLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the issue permission for transit to the database. Also notifies the appropriate model listeners.
	*
	* @param issuePermissionForTransit the issue permission for transit
	* @return the issue permission for transit that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.IssuePermissionForTransit addIssuePermissionForTransit(
		com.fds.nsw.nghiepvu.model.IssuePermissionForTransit issuePermissionForTransit)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .addIssuePermissionForTransit(issuePermissionForTransit);
	}

	/**
	* Creates a new issue permission for transit with the primary key. Does not add the issue permission for transit to the database.
	*
	* @param id the primary key for the new issue permission for transit
	* @return the new issue permission for transit
	*/
	public static com.fds.nsw.nghiepvu.model.IssuePermissionForTransit createIssuePermissionForTransit(
		long id) {
		return getService().createIssuePermissionForTransit(id);
	}

	/**
	* Deletes the issue permission for transit with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the issue permission for transit
	* @throws PortalException if a issue permission for transit with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteIssuePermissionForTransit(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteIssuePermissionForTransit(id);
	}

	/**
	* Deletes the issue permission for transit from the database. Also notifies the appropriate model listeners.
	*
	* @param issuePermissionForTransit the issue permission for transit
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteIssuePermissionForTransit(
		com.fds.nsw.nghiepvu.model.IssuePermissionForTransit issuePermissionForTransit)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteIssuePermissionForTransit(issuePermissionForTransit);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.IssuePermissionForTransit fetchIssuePermissionForTransit(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchIssuePermissionForTransit(id);
	}

	/**
	* Returns the issue permission for transit with the primary key.
	*
	* @param id the primary key of the issue permission for transit
	* @return the issue permission for transit
	* @throws PortalException if a issue permission for transit with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.IssuePermissionForTransit getIssuePermissionForTransit(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getIssuePermissionForTransit(id);
	}

	

	/**
	* Returns a range of all the issue permission for transits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of issue permission for transits
	* @param end the upper bound of the range of issue permission for transits (not inclusive)
	* @return the range of issue permission for transits
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.IssuePermissionForTransit> getIssuePermissionForTransits(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getIssuePermissionForTransits(start, end);
	}

	/**
	* Returns the number of issue permission for transits.
	*
	* @return the number of issue permission for transits
	* @throws SystemException if a system exception occurred
	*/
	public static int getIssuePermissionForTransitsCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getIssuePermissionForTransitsCount();
	}

	/**
	* Updates the issue permission for transit in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param issuePermissionForTransit the issue permission for transit
	* @return the issue permission for transit that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.IssuePermissionForTransit updateIssuePermissionForTransit(
		com.fds.nsw.nghiepvu.model.IssuePermissionForTransit issuePermissionForTransit)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .updateIssuePermissionForTransit(issuePermissionForTransit);
	}

	/**
	* Updates the issue permission for transit in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param issuePermissionForTransit the issue permission for transit
	* @param merge whether to merge the issue permission for transit with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the issue permission for transit that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.IssuePermissionForTransit updateIssuePermissionForTransit(
		com.fds.nsw.nghiepvu.model.IssuePermissionForTransit issuePermissionForTransit,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .updateIssuePermissionForTransit(issuePermissionForTransit,
			merge);
	}

	

	

	public static java.util.List<com.fds.nsw.nghiepvu.model.IssuePermissionForTransit> findIssuePermissionForTransitByDocumentYearAndDocumentYear(
		long documentName, int documentYear) {
		return getService()
				   .findIssuePermissionForTransitByDocumentYearAndDocumentYear(documentName,
			documentYear);
	}

	public static com.fds.nsw.nghiepvu.model.IssuePermissionForTransit findIssuePermissionForTransitByDocumentYearAndDocumentYearAndRequestState(
		long documentName, int documentYear, int requestState) {
		return getService()
				   .findIssuePermissionForTransitByDocumentYearAndDocumentYearAndRequestState(documentName,
			documentYear, requestState);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.IssuePermissionForTransit> findByrequestCode(
		java.lang.String requestCode)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().findByrequestCode(requestCode);
	}

	public static com.fds.nsw.nghiepvu.model.IssuePermissionForTransit getByrequestCode(
		java.lang.String requestCode) {
		return getService().getByrequestCode(requestCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.IssuePermissionForTransit> findBydocumentNameAndDocumentYear(
		long documentName, int documentYear)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .findBydocumentNameAndDocumentYear(documentName, documentYear);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.IssuePermissionForTransit> findBydocumentNameAndDocumentYear(
		long documentName, int documentYear, int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .findBydocumentNameAndDocumentYear(documentName,
			documentYear, start, end);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.IssuePermissionForTransit> findIssuePermissionForTransitInfo(
		java.lang.String requestState, java.lang.String maritimeCode,
		java.lang.String portCode, java.lang.String shipName,
		java.lang.String callSign, java.lang.String shipDateFrom,
		java.lang.String shipDateTo, java.lang.String certificateNumber,
		int start, int end) {
		return getService()
				   .findIssuePermissionForTransitInfo(requestState,
			maritimeCode, portCode, shipName, callSign, shipDateFrom,
			shipDateTo, certificateNumber, start, end);
	}

	public static int countIssuePermissionForTransitInfo(
		java.lang.String requestState, java.lang.String maritimeCode,
		java.lang.String portCode, java.lang.String shipName,
		java.lang.String callSign, java.lang.String shipDateFrom,
		java.lang.String shipDateTo, java.lang.String certificateNumber) {
		return getService()
				   .countIssuePermissionForTransitInfo(requestState,
			maritimeCode, portCode, shipName, callSign, shipDateFrom,
			shipDateTo, certificateNumber);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.IssuePermissionForTransit> findIssuePermissionForTransitInfoByCertificateNumber(
		java.lang.String requestState, java.lang.String maritimeCode,
		java.lang.String portCode, java.lang.String shipName,
		java.lang.String callSign, java.lang.String shipDateFrom,
		java.lang.String shipDateTo, java.lang.String certificateNumber,
		int start, int end) {
		return getService()
				   .findIssuePermissionForTransitInfoByCertificateNumber(requestState,
			maritimeCode, portCode, shipName, callSign, shipDateFrom,
			shipDateTo, certificateNumber, start, end);
	}

	public static int countIssuePermissionForTransitInfoByCertificateNumber(
		java.lang.String requestState, java.lang.String maritimeCode,
		java.lang.String portCode, java.lang.String shipName,
		java.lang.String callSign, java.lang.String shipDateFrom,
		java.lang.String shipDateTo, java.lang.String certificateNumber) {
		return getService()
				   .countIssuePermissionForTransitInfoByCertificateNumber(requestState,
			maritimeCode, portCode, shipName, callSign, shipDateFrom,
			shipDateTo, certificateNumber);
	}

	public static int countByDocumentNameAndDocumentYear(long documentName,
		int documentYear) {
		return getService()
				   .countByDocumentNameAndDocumentYear(documentName,
			documentYear);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.IssuePermissionForTransit> findByDocumentYearAndDocumentYearOrderByColumn(
		long documentName, int documentYear, java.lang.String orderByColumn,
		boolean ascOrdesc) {
		return getService()
				   .findByDocumentYearAndDocumentYearOrderByColumn(documentName,
			documentYear, orderByColumn, ascOrdesc);
	}

	public static com.fds.nsw.nghiepvu.model.IssuePermissionForTransit getByDocumentNameAndDocumentYearAndVersionNo(
		long documentName, int documentYear, java.lang.String versionNo) {
		return getService()
				   .getByDocumentNameAndDocumentYearAndVersionNo(documentName,
			documentYear, versionNo);
	}

	public static java.lang.String capGiayPhepSo(
		java.lang.String maritimeReference)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().capGiayPhepSo(maritimeReference);
	}

	public static com.fds.nsw.nghiepvu.model.IssuePermissionForTransit findByF_LAST_numberPortClearance(
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