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
 * The utility for the temp document local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.TempDocumentLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see TempDocumentLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.TempDocumentLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.TempDocumentLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.TempDocumentLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class TempDocumentLocalServiceUtil {
public TempDocumentLocalServiceUtil(TempDocumentLocalServiceImpl service) {
TempDocumentLocalServiceUtil._service = service;
}
public static TempDocumentLocalServiceImpl getService() {
return _service;
}
private static TempDocumentLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.TempDocumentLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the temp document to the database. Also notifies the appropriate model listeners.
	*
	* @param tempDocument the temp document
	* @return the temp document that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempDocument addTempDocument(
		com.fds.nsw.nghiepvu.model.TempDocument tempDocument)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addTempDocument(tempDocument);
	}

	/**
	* Creates a new temp document with the primary key. Does not add the temp document to the database.
	*
	* @param id the primary key for the new temp document
	* @return the new temp document
	*/
	public static com.fds.nsw.nghiepvu.model.TempDocument createTempDocument(
		long id) {
		return getService().createTempDocument(id);
	}

	/**
	* Deletes the temp document with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the temp document
	* @throws PortalException if a temp document with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempDocument(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempDocument(id);
	}

	/**
	* Deletes the temp document from the database. Also notifies the appropriate model listeners.
	*
	* @param tempDocument the temp document
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempDocument(
		com.fds.nsw.nghiepvu.model.TempDocument tempDocument)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempDocument(tempDocument);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.TempDocument fetchTempDocument(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchTempDocument(id);
	}

	/**
	* Returns the temp document with the primary key.
	*
	* @param id the primary key of the temp document
	* @return the temp document
	* @throws PortalException if a temp document with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempDocument getTempDocument(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempDocument(id);
	}

	

	/**
	* Returns a range of all the temp documents.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of temp documents
	* @param end the upper bound of the range of temp documents (not inclusive)
	* @return the range of temp documents
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.TempDocument> getTempDocuments(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempDocuments(start, end);
	}

	/**
	* Returns the number of temp documents.
	*
	* @return the number of temp documents
	* @throws SystemException if a system exception occurred
	*/
	public static int getTempDocumentsCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempDocumentsCount();
	}

	/**
	* Updates the temp document in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempDocument the temp document
	* @return the temp document that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempDocument updateTempDocument(
		com.fds.nsw.nghiepvu.model.TempDocument tempDocument)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateTempDocument(tempDocument);
	}

	/**
	* Updates the temp document in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempDocument the temp document
	* @param merge whether to merge the temp document with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the temp document that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempDocument updateTempDocument(
		com.fds.nsw.nghiepvu.model.TempDocument tempDocument,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateTempDocument(tempDocument, merge);
	}

	

	

	public static com.fds.nsw.nghiepvu.model.TempDocument getTemDocumentByRequestCode(
		java.lang.String requestCode) {
		return getService().getTemDocumentByRequestCode(requestCode);
	}

	public static com.fds.nsw.nghiepvu.model.TempDocument getByDocumentNameAndDocumentYear(
		long documentName, int documentYear) throws java.lang.Exception {
		return getService()
				   .getByDocumentNameAndDocumentYear(documentName, documentYear);
	}

	public static java.lang.String getCallSignByDocumentNameAndDocumentYear(
		long documentName, int documentYear) {
		return getService()
				   .getCallSignByDocumentNameAndDocumentYear(documentName,
			documentYear);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempDocument> findTemDocumentByDocumentNameAndDocumentYear(
		long documentName, int documentYear) {
		return getService()
				   .findTemDocumentByDocumentNameAndDocumentYear(documentName,
			documentYear);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempDocument> findTempDocumentArivalByMaritimeCodeAndDateFromAndDateTo(
		java.lang.String maritimeCode, java.lang.String dateFrom,
		java.lang.String dateTo, java.lang.String documentTypeCode,
		java.lang.String requestState, java.lang.String documentStatusCode) {
		return getService()
				   .findTempDocumentArivalByMaritimeCodeAndDateFromAndDateTo(maritimeCode,
			dateFrom, dateTo, documentTypeCode, requestState, documentStatusCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempDocument> findTempDocumentLeaveByMaritimeCodeAndDateFromAndDateTo(
		java.lang.String maritimeCode, java.lang.String dateFrom,
		java.lang.String dateTo, java.lang.String documentTypeCode,
		java.lang.String requestState, java.lang.String documentStatusCode) {
		return getService()
				   .findTempDocumentLeaveByMaritimeCodeAndDateFromAndDateTo(maritimeCode,
			dateFrom, dateTo, documentTypeCode, requestState, documentStatusCode);
	}

	public static long countTempDocumentForStatisticsReport(
		java.lang.String maritimeCode, java.lang.String dateFrom,
		java.lang.String dateTo, java.lang.String documentTypeCode,
		java.lang.String requestState, java.lang.String documentStatusCode) {
		return getService()
				   .countTempDocumentForStatisticsReport(maritimeCode,
			dateFrom, dateTo, documentTypeCode, requestState, documentStatusCode);
	}

	public static long countTempDocumentCompletionForStatisticsReport(
		java.lang.String maritimeCode, java.lang.String dateFrom,
		java.lang.String dateTo, java.lang.String documentTypeCode,
		java.lang.String requestState, java.lang.String documentStatusCode) {
		return getService()
				   .countTempDocumentCompletionForStatisticsReport(maritimeCode,
			dateFrom, dateTo, documentTypeCode, requestState, documentStatusCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.baocaothongke.model.FlowChartDataByDate> countTempDocumentForStatisticsByDate(
		java.lang.String maritimeCode, java.lang.String dateFrom,
		java.lang.String dateTo, java.lang.String documentTypeCode,
		java.lang.String requestState, java.lang.String documentStatusCode) {
		return getService()
				   .countTempDocumentForStatisticsByDate(maritimeCode,
			dateFrom, dateTo, documentTypeCode, requestState, documentStatusCode);
	}

	public static long countTempDocumentJoinIssueShiftingOrderForStatisticsReport(
		java.lang.String maritimeCode, java.lang.String dateFrom,
		java.lang.String dateTo, java.lang.String documentTypeCode,
		java.lang.String requestState, java.lang.String issRequestState,
		java.lang.String documentStatusCode) {
		return getService()
				   .countTempDocumentJoinIssueShiftingOrderForStatisticsReport(maritimeCode,
			dateFrom, dateTo, documentTypeCode, requestState, issRequestState,
			documentStatusCode);
	}

	public static long countTempDocumentJoinIssuePermissionTransitForStatisticsReport(
		java.lang.String maritimeCode, java.lang.String dateFrom,
		java.lang.String dateTo, java.lang.String documentTypeCode,
		java.lang.String requestState, java.lang.String issRequestState,
		java.lang.String documentStatusCode) {
		return getService()
				   .countTempDocumentJoinIssuePermissionTransitForStatisticsReport(maritimeCode,
			dateFrom, dateTo, documentTypeCode, requestState, issRequestState,
			documentStatusCode);
	}

	public static long countTempDocumentJoinIssuePortClearanceForStatisticsReport(
		java.lang.String maritimeCode, java.lang.String dateFrom,
		java.lang.String dateTo, java.lang.String documentTypeCode,
		java.lang.String requestState, java.lang.String issRequestState,
		java.lang.String documentStatusCode) {
		return getService()
				   .countTempDocumentJoinIssuePortClearanceForStatisticsReport(maritimeCode,
			dateFrom, dateTo, documentTypeCode, requestState, issRequestState,
			documentStatusCode);
	}

	public static long countTempDocumentJoinIssueAcceptanceForStatisticsReport(
		java.lang.String maritimeCode, java.lang.String dateFrom,
		java.lang.String dateTo, java.lang.String documentTypeCode,
		java.lang.String requestState, java.lang.String issRequestState,
		java.lang.String documentStatusCode) {
		return getService()
				   .countTempDocumentJoinIssueAcceptanceForStatisticsReport(maritimeCode,
			dateFrom, dateTo, documentTypeCode, requestState, issRequestState,
			documentStatusCode);
	}

	public static long countTempDocumentSignedIssueShiftingOrderForStatisticsReport(
		java.lang.String maritimeCode, java.lang.String dateFrom,
		java.lang.String dateTo, java.lang.String documentTypeCode,
		java.lang.String requestState, java.lang.String issRequestState,
		java.lang.String documentStatusCode) {
		return getService()
				   .countTempDocumentSignedIssueShiftingOrderForStatisticsReport(maritimeCode,
			dateFrom, dateTo, documentTypeCode, requestState, issRequestState,
			documentStatusCode);
	}

	public static long countTempDocumentSignedIssuePermissionTransitForStatisticsReport(
		java.lang.String maritimeCode, java.lang.String dateFrom,
		java.lang.String dateTo, java.lang.String documentTypeCode,
		java.lang.String requestState, java.lang.String issRequestState,
		java.lang.String documentStatusCode) {
		return getService()
				   .countTempDocumentSignedIssuePermissionTransitForStatisticsReport(maritimeCode,
			dateFrom, dateTo, documentTypeCode, requestState, issRequestState,
			documentStatusCode);
	}

	public static long countTempDocumentSignedIssuePortClearanceForStatisticsReport(
		java.lang.String maritimeCode, java.lang.String dateFrom,
		java.lang.String dateTo, java.lang.String documentTypeCode,
		java.lang.String requestState, java.lang.String issRequestState,
		java.lang.String documentStatusCode) {
		return getService()
				   .countTempDocumentSignedIssuePortClearanceForStatisticsReport(maritimeCode,
			dateFrom, dateTo, documentTypeCode, requestState, issRequestState,
			documentStatusCode);
	}

	public static long countTempDocumentSignedIssueAcceptanceForStatisticsReport(
		java.lang.String maritimeCode, java.lang.String dateFrom,
		java.lang.String dateTo, java.lang.String documentTypeCode,
		java.lang.String requestState, java.lang.String issRequestState,
		java.lang.String documentStatusCode) {
		return getService()
				   .countTempDocumentSignedIssueAcceptanceForStatisticsReport(maritimeCode,
			dateFrom, dateTo, documentTypeCode, requestState, issRequestState,
			documentStatusCode);
	}

	public static com.fds.nsw.nghiepvu.model.TempDocument findTemDocumentByDocumentNameDocumentYear(
		long documentName, int documentYear) {
		return getService()
				   .findTemDocumentByDocumentNameDocumentYear(documentName,
			documentYear);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempDocument> findTempDocumentByRequestState(
		java.lang.String requestState, java.lang.String documentTypeCode,
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .findTempDocumentByRequestState(requestState,
			documentTypeCode, start, end);
	}

	public static int countTempDocumentByRequestState(
		java.lang.String requestState, java.lang.String documentTypeCode) {
		return getService()
				   .countTempDocumentByRequestState(requestState,
			documentTypeCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempDocument> findTempDocumentByDocumentStatusCode(
		java.lang.String documentStatusCode, java.lang.String documentTypeCode,
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .findTempDocumentByDocumentStatusCode(documentStatusCode,
			documentTypeCode, start, end);
	}

	public static int countTempDocumentByDocumentStatusCode(
		java.lang.String documentStatusCode, java.lang.String documentTypeCode) {
		return getService()
				   .countTempDocumentByDocumentStatusCode(documentStatusCode,
			documentTypeCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempDocument> findTempDocumentByKeHoach(
		java.lang.String requestState, java.lang.String documentTypeCode,
		java.lang.String maritimeCode, java.lang.String shipName,
		java.lang.String positionCode, java.lang.String portRegionCode,
		java.lang.String mabankhai, java.lang.String stateCode,
		java.lang.String shipDateFromStart, java.lang.String shipDateFromEnd,
		java.lang.String shipDateToStart, java.lang.String shipDateToEnd,
		java.lang.String callSign, java.lang.String imo,
		java.lang.String ngayLamThuTucFrom, java.lang.String ngayLamThuTucTo,
		int start, int end) {
		return getService()
				   .findTempDocumentByKeHoach(requestState, documentTypeCode,
			maritimeCode, shipName, positionCode, portRegionCode, mabankhai,
			stateCode, shipDateFromStart, shipDateFromEnd, shipDateToStart,
			shipDateToEnd, callSign, imo, ngayLamThuTucFrom, ngayLamThuTucTo,
			start, end);
	}

	public static int countTempDocumentByKeHoach(
		java.lang.String requestState, java.lang.String documentTypeCode,
		java.lang.String maritimeCode, java.lang.String shipName,
		java.lang.String positionCode, java.lang.String portRegionCode,
		java.lang.String mabankhai, java.lang.String stateCode,
		java.lang.String shipDateFromStart, java.lang.String shipDateFromEnd,
		java.lang.String shipDateToStart, java.lang.String shipDateToEnd,
		java.lang.String callSign, java.lang.String imo,
		java.lang.String ngayLamThuTucFrom, java.lang.String ngayLamThuTucTo) {
		return getService()
				   .countTempDocumentByKeHoach(requestState, documentTypeCode,
			maritimeCode, shipName, positionCode, portRegionCode, mabankhai,
			stateCode, shipDateFromStart, shipDateFromEnd, shipDateToStart,
			shipDateToEnd, callSign, imo, ngayLamThuTucFrom, ngayLamThuTucTo);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempDocument> findTempDocumentByKeHoach(
		java.lang.String requestState, java.lang.String documentTypeCode,
		java.lang.String maritimeCode, java.lang.String shipName,
		java.lang.String positionCode, java.lang.String portRegionCode,
		java.lang.String mabankhai, java.lang.String stateCode,
		java.lang.String shipDateFromStart, java.lang.String shipDateFromEnd,
		java.lang.String shipDateToStart, java.lang.String shipDateToEnd,
		java.lang.String callSign, java.lang.String imo,
		java.lang.String ngayLamThuTucFrom, java.lang.String ngayLamThuTucTo,
		java.lang.String nameOfShipownersAgents,
		java.lang.String portRegionCodeNext, int start, int end) {
		return getService()
				   .findTempDocumentByKeHoach(requestState, documentTypeCode,
			maritimeCode, shipName, positionCode, portRegionCode, mabankhai,
			stateCode, shipDateFromStart, shipDateFromEnd, shipDateToStart,
			shipDateToEnd, callSign, imo, ngayLamThuTucFrom, ngayLamThuTucTo,
			nameOfShipownersAgents, portRegionCodeNext, start, end);
	}

	public static int countTempDocumentByKeHoach(
		java.lang.String requestState, java.lang.String documentTypeCode,
		java.lang.String maritimeCode, java.lang.String shipName,
		java.lang.String positionCode, java.lang.String portRegionCode,
		java.lang.String mabankhai, java.lang.String stateCode,
		java.lang.String shipDateFromStart, java.lang.String shipDateFromEnd,
		java.lang.String shipDateToStart, java.lang.String shipDateToEnd,
		java.lang.String callSign, java.lang.String imo,
		java.lang.String ngayLamThuTucFrom, java.lang.String ngayLamThuTucTo,
		java.lang.String nameOfShipownersAgents,
		java.lang.String portRegionCodeNext) {
		return getService()
				   .countTempDocumentByKeHoach(requestState, documentTypeCode,
			maritimeCode, shipName, positionCode, portRegionCode, mabankhai,
			stateCode, shipDateFromStart, shipDateFromEnd, shipDateToStart,
			shipDateToEnd, callSign, imo, ngayLamThuTucFrom, ngayLamThuTucTo,
			nameOfShipownersAgents, portRegionCodeNext);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempDocument> findAll(
		int start, int end) {
		return getService().findAll(start, end);
	}

	public static int countAll() {
		return getService().countAll();
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempDocument> findTempDocumentByThuTuc(
		java.lang.String documentTypeCode, java.lang.String documentName,
		java.lang.String documentYear, java.lang.String maLoaiHoSo,
		java.lang.String shipName, java.lang.String shipTypeCode,
		java.lang.String stateCode, java.lang.String shipCaptain,
		java.lang.String callSign, java.lang.String userCreated, int start,
		int end) throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .findTempDocumentByThuTuc(documentTypeCode, documentName,
			documentYear, maLoaiHoSo, shipName, shipTypeCode, stateCode,
			shipCaptain, callSign, userCreated, start, end);
	}

	public static int countTempDocumentByThuTuc(
		java.lang.String documentTypeCode, java.lang.String documentName,
		java.lang.String documentYear, java.lang.String maLoaiHoSo,
		java.lang.String shipName, java.lang.String shipTypeCode,
		java.lang.String stateCode, java.lang.String shipCaptain,
		java.lang.String callSign, java.lang.String userCreated)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .countTempDocumentByThuTuc(documentTypeCode, documentName,
			documentYear, maLoaiHoSo, shipName, shipTypeCode, stateCode,
			shipCaptain, callSign, userCreated);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempDocument> findDanhSachHoSoRoleThuTuc(
		java.lang.String documentTypeCode, java.lang.String maritimeCode,
		java.lang.String portCode, java.lang.String lastPortCode,
		java.lang.String shipName, java.lang.String stateCode,
		java.lang.String callSign, java.lang.String imo,
		java.lang.String shipPosition, java.lang.String shipDateFromStart,
		java.lang.String shipDateFromEnd, java.lang.String shipDateToStart,
		java.lang.String shipDateToEnd, java.lang.String documentStatusCode,
		java.lang.String arrivalShipAgency,
		java.lang.String nameArrivalShipAgency,
		java.lang.String departureShipAgency,
		java.lang.String nameDepartureShipAgency,
		java.lang.String portRegionCode, java.lang.String ngayLamThuTucFrom,
		java.lang.String ngayLamThuTucTo, java.lang.String maBanKhai,
		int start, int end, boolean isDTND, boolean isDTNDCam)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .findDanhSachHoSoRoleThuTuc(documentTypeCode, maritimeCode,
			portCode, lastPortCode, shipName, stateCode, callSign, imo,
			shipPosition, shipDateFromStart, shipDateFromEnd, shipDateToStart,
			shipDateToEnd, documentStatusCode, arrivalShipAgency,
			nameArrivalShipAgency, departureShipAgency,
			nameDepartureShipAgency, portRegionCode, ngayLamThuTucFrom,
			ngayLamThuTucTo, maBanKhai, start, end, isDTND, isDTNDCam);
	}

	public static int countDanhSachHoSoRoleThuTuc(
		java.lang.String documentTypeCode, java.lang.String maritimeCode,
		java.lang.String portCode, java.lang.String lastPortCode,
		java.lang.String shipName, java.lang.String stateCode,
		java.lang.String callSign, java.lang.String imo,
		java.lang.String shipPosition, java.lang.String shipDateFromStart,
		java.lang.String shipDateFromEnd, java.lang.String shipDateToStart,
		java.lang.String shipDateToEnd, java.lang.String documentStatusCode,
		java.lang.String arrivalShipAgency,
		java.lang.String nameArrivalShipAgency,
		java.lang.String departureShipAgency,
		java.lang.String nameDepartureShipAgency,
		java.lang.String portRegionCode, java.lang.String ngayLamThuTucFrom,
		java.lang.String ngayLamThuTucTo, java.lang.String maBanKhai,
		boolean isDTND, boolean isDTNDCam)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .countDanhSachHoSoRoleThuTuc(documentTypeCode, maritimeCode,
			portCode, lastPortCode, shipName, stateCode, callSign, imo,
			shipPosition, shipDateFromStart, shipDateFromEnd, shipDateToStart,
			shipDateToEnd, documentStatusCode, arrivalShipAgency,
			nameArrivalShipAgency, departureShipAgency,
			nameDepartureShipAgency, portRegionCode, ngayLamThuTucFrom,
			ngayLamThuTucTo, maBanKhai, isDTND, isDTNDCam);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempDocument> findDanhSachHoSoRoleThuTuc(
		java.lang.String documentTypeCode, java.lang.String maritimeCode,
		java.lang.String portCode, java.lang.String lastPortCode,
		java.lang.String shipName, java.lang.String stateCode,
		java.lang.String callSign, java.lang.String imo,
		java.lang.String shipPosition, java.lang.String shipDateFromStart,
		java.lang.String shipDateFromEnd, java.lang.String shipDateToStart,
		java.lang.String shipDateToEnd, java.lang.String documentStatusCode,
		java.lang.String arrivalShipAgency,
		java.lang.String nameArrivalShipAgency,
		java.lang.String departureShipAgency,
		java.lang.String nameDepartureShipAgency,
		java.lang.String portRegionCode, java.lang.String ngayLamThuTucFrom,
		java.lang.String ngayLamThuTucTo, java.lang.String maBanKhai,
		java.lang.String nameOfShipownersAgents,
		java.lang.String maritimeCodeNext, int start, int end, boolean isDTND,
		boolean isDTNDCam)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .findDanhSachHoSoRoleThuTuc(documentTypeCode, maritimeCode,
			portCode, lastPortCode, shipName, stateCode, callSign, imo,
			shipPosition, shipDateFromStart, shipDateFromEnd, shipDateToStart,
			shipDateToEnd, documentStatusCode, arrivalShipAgency,
			nameArrivalShipAgency, departureShipAgency,
			nameDepartureShipAgency, portRegionCode, ngayLamThuTucFrom,
			ngayLamThuTucTo, maBanKhai, nameOfShipownersAgents,
			maritimeCodeNext, start, end, isDTND, isDTNDCam);
	}

	public static int countDanhSachHoSoRoleThuTuc(
		java.lang.String documentTypeCode, java.lang.String maritimeCode,
		java.lang.String portCode, java.lang.String lastPortCode,
		java.lang.String shipName, java.lang.String stateCode,
		java.lang.String callSign, java.lang.String imo,
		java.lang.String shipPosition, java.lang.String shipDateFromStart,
		java.lang.String shipDateFromEnd, java.lang.String shipDateToStart,
		java.lang.String shipDateToEnd, java.lang.String documentStatusCode,
		java.lang.String arrivalShipAgency,
		java.lang.String nameArrivalShipAgency,
		java.lang.String departureShipAgency,
		java.lang.String nameDepartureShipAgency,
		java.lang.String portRegionCode, java.lang.String ngayLamThuTucFrom,
		java.lang.String ngayLamThuTucTo, java.lang.String maBanKhai,
		java.lang.String nameOfShipownersAgents,
		java.lang.String maritimeCodeNext, boolean isDTND, boolean isDTNDCam)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .countDanhSachHoSoRoleThuTuc(documentTypeCode, maritimeCode,
			portCode, lastPortCode, shipName, stateCode, callSign, imo,
			shipPosition, shipDateFromStart, shipDateFromEnd, shipDateToStart,
			shipDateToEnd, documentStatusCode, arrivalShipAgency,
			nameArrivalShipAgency, departureShipAgency,
			nameDepartureShipAgency, portRegionCode, ngayLamThuTucFrom,
			ngayLamThuTucTo, maBanKhai, nameOfShipownersAgents,
			maritimeCodeNext, isDTND, isDTNDCam);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempDocument> findTempDocumentInfo(
		java.lang.String documentStatusCode, java.lang.String maritimeCode,
		java.lang.String portCode, java.lang.String shipName,
		java.lang.String callSign, java.lang.String shipDateFrom,
		java.lang.String shipDateTo, int start, int end) {
		return getService()
				   .findTempDocumentInfo(documentStatusCode, maritimeCode,
			portCode, shipName, callSign, shipDateFrom, shipDateTo, start, end);
	}

	public static int countTempDocumentInfo(
		java.lang.String documentStatusCode, java.lang.String maritimeCode,
		java.lang.String portCode, java.lang.String shipName,
		java.lang.String callSign, java.lang.String shipDateFrom,
		java.lang.String shipDateTo)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .countTempDocumentInfo(documentStatusCode, maritimeCode,
			portCode, shipName, callSign, shipDateFrom, shipDateTo);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempDocument> findKeHoachYCBS()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().findKeHoachYCBS();
	}

	public static com.fds.nsw.nghiepvu.model.TempDocument fetchByF_B_N_Y_TYPE(
		long documentName, int documentYear, java.lang.String documentTypeCode)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .fetchByF_B_N_Y_TYPE(documentName, documentYear,
			documentTypeCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempDocument> findByTempDocumentByShipNameSMSAndDocumentTypeCode(
		java.lang.String shipName, java.lang.String callSign,
		java.lang.String shipTypeCode, int documentYear,
		java.lang.String documentTypeCode) {
		return getService()
				   .findByTempDocumentByShipNameSMSAndDocumentTypeCode(shipName,
			callSign, shipTypeCode, documentYear, documentTypeCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempDocument> findDanhSachHoSoVanThuChoDongDau(
		java.lang.String documentTypeCode, java.lang.String maritimeCode,
		java.lang.String portCode, java.lang.String lastPortCode,
		java.lang.String shipName, java.lang.String stateCode,
		java.lang.String callSign, java.lang.String imo,
		java.lang.String shipPosition, java.lang.String shipDateFromStart,
		java.lang.String shipDateFromEnd, java.lang.String shipDateToStart,
		java.lang.String shipDateToEnd, java.lang.String documentStatusCode,
		java.lang.String arrivalShipAgency,
		java.lang.String nameArrivalShipAgency,
		java.lang.String departureShipAgency,
		java.lang.String nameDepartureShipAgency,
		java.lang.String portRegionCode, java.lang.String ngayLamThuTucFrom,
		java.lang.String ngayLamThuTucTo, java.lang.String maBanKhai,
		int start, int end) // 1/4
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .findDanhSachHoSoVanThuChoDongDau(documentTypeCode,
			maritimeCode, portCode, lastPortCode, shipName, stateCode,
			callSign, imo, shipPosition, shipDateFromStart, shipDateFromEnd,
			shipDateToStart, shipDateToEnd, documentStatusCode,
			arrivalShipAgency, nameArrivalShipAgency, departureShipAgency,
			nameDepartureShipAgency, portRegionCode, ngayLamThuTucFrom,
			ngayLamThuTucTo, maBanKhai, start, end);
	}

	public static int countDanhSachHoSoVanThuChoDongDau(
		java.lang.String documentTypeCode, java.lang.String maritimeCode,
		java.lang.String portCode, java.lang.String lastPortCode,
		java.lang.String shipName, java.lang.String stateCode,
		java.lang.String callSign, java.lang.String imo,
		java.lang.String shipPosition, java.lang.String shipDateFromStart,
		java.lang.String shipDateFromEnd, java.lang.String shipDateToStart,
		java.lang.String shipDateToEnd, java.lang.String documentStatusCode,
		java.lang.String arrivalShipAgency,
		java.lang.String nameArrivalShipAgency,
		java.lang.String departureShipAgency,
		java.lang.String nameDepartureShipAgency,
		java.lang.String portRegionCode, java.lang.String ngayLamThuTucFrom,
		java.lang.String ngayLamThuTucTo, java.lang.String maBanKhai)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .countDanhSachHoSoVanThuChoDongDau(documentTypeCode,
			maritimeCode, portCode, lastPortCode, shipName, stateCode,
			callSign, imo, shipPosition, shipDateFromStart, shipDateFromEnd,
			shipDateToStart, shipDateToEnd, documentStatusCode,
			arrivalShipAgency, nameArrivalShipAgency, departureShipAgency,
			nameDepartureShipAgency, portRegionCode, ngayLamThuTucFrom,
			ngayLamThuTucTo, maBanKhai);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempDocument> findDanhSachHoSoVanThuChoDongDau(
		java.lang.String documentTypeCode, java.lang.String maritimeCode,
		java.lang.String portCode, java.lang.String lastPortCode,
		java.lang.String shipName, java.lang.String stateCode,
		java.lang.String callSign, java.lang.String imo,
		java.lang.String shipPosition, java.lang.String shipDateFromStart,
		java.lang.String shipDateFromEnd, java.lang.String shipDateToStart,
		java.lang.String shipDateToEnd, java.lang.String documentStatusCode,
		java.lang.String arrivalShipAgency,
		java.lang.String nameArrivalShipAgency,
		java.lang.String departureShipAgency,
		java.lang.String nameDepartureShipAgency,
		java.lang.String portRegionCode, java.lang.String ngayLamThuTucFrom,
		java.lang.String ngayLamThuTucTo, java.lang.String maBanKhai,
		java.lang.String nameOfShipownersAgents,
		java.lang.String maritimeCodeNext,int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .findDanhSachHoSoVanThuChoDongDau(documentTypeCode,
			maritimeCode, portCode, lastPortCode, shipName, stateCode,
			callSign, imo, shipPosition, shipDateFromStart, shipDateFromEnd,
			shipDateToStart, shipDateToEnd, documentStatusCode,
			arrivalShipAgency, nameArrivalShipAgency, departureShipAgency,
			nameDepartureShipAgency, portRegionCode, ngayLamThuTucFrom,
			ngayLamThuTucTo, maBanKhai, nameOfShipownersAgents,
			maritimeCodeNext, start, end);
	}

	public static int countDanhSachHoSoVanThuChoDongDau(
		java.lang.String documentTypeCode, java.lang.String maritimeCode,
		java.lang.String portCode, java.lang.String lastPortCode,
		java.lang.String shipName, java.lang.String stateCode,
		java.lang.String callSign, java.lang.String imo,
		java.lang.String shipPosition, java.lang.String shipDateFromStart,
		java.lang.String shipDateFromEnd, java.lang.String shipDateToStart,
		java.lang.String shipDateToEnd, java.lang.String documentStatusCode,
		java.lang.String arrivalShipAgency,
		java.lang.String nameArrivalShipAgency,
		java.lang.String departureShipAgency,
		java.lang.String nameDepartureShipAgency,
		java.lang.String portRegionCode, java.lang.String ngayLamThuTucFrom,
		java.lang.String ngayLamThuTucTo, java.lang.String maBanKhai,
		java.lang.String nameOfShipownersAgents,
		java.lang.String maritimeCodeNext)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .countDanhSachHoSoVanThuChoDongDau(documentTypeCode,
			maritimeCode, portCode, lastPortCode, shipName, stateCode,
			callSign, imo, shipPosition, shipDateFromStart, shipDateFromEnd,
			shipDateToStart, shipDateToEnd, documentStatusCode,
			arrivalShipAgency, nameArrivalShipAgency, departureShipAgency,
			nameDepartureShipAgency, portRegionCode, ngayLamThuTucFrom,
			ngayLamThuTucTo, maBanKhai, nameOfShipownersAgents, maritimeCodeNext);
	}

	public static com.fds.nsw.nghiepvu.model.TempDocument findTempDocumentLeftByIMOandCallSign(
			java.lang.String maritimeCode, java.util.Date DateOfArrival,
			java.lang.String callSign, java.lang.String imo,
			java.lang.String documentTypeCode, java.lang.String requestState,
			java.lang.String documentStatusCode) {
		return getService()
				.findTempDocumentLeftByIMOandCallSign(maritimeCode,
						DateOfArrival, callSign, imo, documentTypeCode, requestState,
						documentStatusCode);
	}

	public static org.json.JSONObject findTempDocument(
			java.lang.String searchQuery, java.lang.String countQuery, int start,
			int end)
			throws com.fds.nsw.kernel.exception.SystemException,
			org.json.JSONException {
		return getService().findTempDocument(searchQuery, countQuery, start, end);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempDebitnote> findTempDebitNoteTauBien_PTTND(
			java.lang.String maritimeCode, java.lang.String positionCode,
			java.lang.String portRegionCode, java.lang.String mabankhai,
			java.lang.String stateCode, java.lang.String imo,
			java.lang.String markasdelted, java.lang.String lastPortCode,
			int start, int end, java.lang.String shipName,
			java.lang.String callSign, int flag)
			throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				.findTempDebitNoteTauBien_PTTND(maritimeCode, positionCode,
						portRegionCode, mabankhai, stateCode, imo, markasdelted,
						lastPortCode, start, end, shipName, callSign, flag);
	}

	public static long countTempDebitNoteTauBien_PTTND(
			java.lang.String maritimeCode, java.lang.String positionCode,
			java.lang.String portRegionCode, java.lang.String mabankhai,
			java.lang.String stateCode, java.lang.String imo,
			java.lang.String markasdelted, java.lang.String lastPortCode,
			java.lang.String shipName, java.lang.String callSign, int flag)
			throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				.countTempDebitNoteTauBien_PTTND(maritimeCode, positionCode,
						portRegionCode, mabankhai, stateCode, imo, markasdelted,
						lastPortCode, shipName, callSign, flag);
	}


	public static java.util.List<com.fds.nsw.nghiepvu.model.TempDocument> findDanhSachHoSoLanhDaoChoKySo(
		java.lang.String documentTypeCode, java.lang.String maritimeCode,
		java.lang.String portCode, java.lang.String lastPortCode,
		java.lang.String shipName, java.lang.String stateCode,
		java.lang.String callSign, java.lang.String imo,
		java.lang.String shipPosition, java.lang.String shipDateFromStart,
		java.lang.String shipDateFromEnd, java.lang.String shipDateToStart,
		java.lang.String shipDateToEnd, java.lang.String documentStatusCode,
		java.lang.String arrivalShipAgency,
		java.lang.String nameArrivalShipAgency,
		java.lang.String departureShipAgency,
		java.lang.String nameDepartureShipAgency,
		java.lang.String portRegionCode, java.lang.String ngayLamThuTucFrom,
		java.lang.String ngayLamThuTucTo, java.lang.String maBanKhai,
		java.lang.String representative, int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .findDanhSachHoSoLanhDaoChoKySo(documentTypeCode,
			maritimeCode, portCode, lastPortCode, shipName, stateCode,
			callSign, imo, shipPosition, shipDateFromStart, shipDateFromEnd,
			shipDateToStart, shipDateToEnd, documentStatusCode,
			arrivalShipAgency, nameArrivalShipAgency, departureShipAgency,
			nameDepartureShipAgency, portRegionCode, ngayLamThuTucFrom,
			ngayLamThuTucTo, maBanKhai, representative, start, end);
	}

	public static int countDanhSachHoSoLanhDaoChoKySo(
		java.lang.String documentTypeCode, java.lang.String maritimeCode,
		java.lang.String portCode, java.lang.String lastPortCode,
		java.lang.String shipName, java.lang.String stateCode,
		java.lang.String callSign, java.lang.String imo,
		java.lang.String shipPosition, java.lang.String shipDateFromStart,
		java.lang.String shipDateFromEnd, java.lang.String shipDateToStart,
		java.lang.String shipDateToEnd, java.lang.String documentStatusCode,
		java.lang.String arrivalShipAgency,
		java.lang.String nameArrivalShipAgency,
		java.lang.String departureShipAgency,
		java.lang.String nameDepartureShipAgency,
		java.lang.String portRegionCode, java.lang.String ngayLamThuTucFrom,
		java.lang.String ngayLamThuTucTo, java.lang.String maBanKhai,
		java.lang.String representative)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .countDanhSachHoSoLanhDaoChoKySo(documentTypeCode,
			maritimeCode, portCode, lastPortCode, shipName, stateCode,
			callSign, imo, shipPosition, shipDateFromStart, shipDateFromEnd,
			shipDateToStart, shipDateToEnd, documentStatusCode,
			arrivalShipAgency, nameArrivalShipAgency, departureShipAgency,
			nameDepartureShipAgency, portRegionCode, ngayLamThuTucFrom,
			ngayLamThuTucTo, maBanKhai, representative);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempDocument> findDanhSachHoSoLanhDaoChoKySo(
		java.lang.String documentTypeCode, java.lang.String maritimeCode,
		java.lang.String portCode, java.lang.String lastPortCode,
		java.lang.String shipName, java.lang.String stateCode,
		java.lang.String callSign, java.lang.String imo,
		java.lang.String shipPosition, java.lang.String shipDateFromStart,
		java.lang.String shipDateFromEnd, java.lang.String shipDateToStart,
		java.lang.String shipDateToEnd, java.lang.String documentStatusCode,
		java.lang.String arrivalShipAgency,
		java.lang.String nameArrivalShipAgency,
		java.lang.String departureShipAgency,
		java.lang.String nameDepartureShipAgency,
		java.lang.String portRegionCode, java.lang.String ngayLamThuTucFrom,
		java.lang.String ngayLamThuTucTo, java.lang.String maBanKhai,
		java.lang.String representative,
		java.lang.String nameOfShipownersAgents,
		java.lang.String maritimeCodeNext,java.lang.String daKy,  int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .findDanhSachHoSoLanhDaoChoKySo(documentTypeCode,
			maritimeCode, portCode, lastPortCode, shipName, stateCode,
			callSign, imo, shipPosition, shipDateFromStart, shipDateFromEnd,
			shipDateToStart, shipDateToEnd, documentStatusCode,
			arrivalShipAgency, nameArrivalShipAgency, departureShipAgency,
			nameDepartureShipAgency, portRegionCode, ngayLamThuTucFrom,
			ngayLamThuTucTo, maBanKhai, representative, nameOfShipownersAgents,
			maritimeCodeNext,daKy, start, end);
	}

	public static int countDanhSachHoSoLanhDaoChoKySo(
		java.lang.String documentTypeCode, java.lang.String maritimeCode,
		java.lang.String portCode, java.lang.String lastPortCode,
		java.lang.String shipName, java.lang.String stateCode,
		java.lang.String callSign, java.lang.String imo,
		java.lang.String shipPosition, java.lang.String shipDateFromStart,
		java.lang.String shipDateFromEnd, java.lang.String shipDateToStart,
		java.lang.String shipDateToEnd, java.lang.String documentStatusCode,
		java.lang.String arrivalShipAgency,
		java.lang.String nameArrivalShipAgency,
		java.lang.String departureShipAgency,
		java.lang.String nameDepartureShipAgency,
		java.lang.String portRegionCode, java.lang.String ngayLamThuTucFrom,
		java.lang.String ngayLamThuTucTo, java.lang.String maBanKhai,
		java.lang.String representative,
		java.lang.String nameOfShipownersAgents,
		java.lang.String maritimeCodeNext, java.lang.String daKy)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .countDanhSachHoSoLanhDaoChoKySo(documentTypeCode,
			maritimeCode, portCode, lastPortCode, shipName, stateCode,
			callSign, imo, shipPosition, shipDateFromStart, shipDateFromEnd,
			shipDateToStart, shipDateToEnd, documentStatusCode,
			arrivalShipAgency, nameArrivalShipAgency, departureShipAgency,
			nameDepartureShipAgency, portRegionCode, ngayLamThuTucFrom,
			ngayLamThuTucTo, maBanKhai, representative, nameOfShipownersAgents,
			maritimeCodeNext,daKy);
	}

	public static long countByF_documentTypeCode_maritimeCode(
		java.lang.String documentTypeCode, java.lang.String maritimeCode) {
		return getService()
				   .countByF_documentTypeCode_maritimeCode(documentTypeCode,
			maritimeCode);
	}

	public static long countByF_documentTypeCode_maritimeCodeFinder(
		java.lang.String documentTypeCode, java.lang.String maritimeCode,
		java.lang.String shipName, java.lang.String documentName,
		java.lang.String callSign) {
		return getService()
				   .countByF_documentTypeCode_maritimeCodeFinder(documentTypeCode,
			maritimeCode, shipName, documentName, callSign);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempDebitnote> findTempDebitNote(
		java.lang.String maritimeCode, java.lang.String positionCode,
		java.lang.String portRegionCode, java.lang.String mabankhai,
		java.lang.String stateCode, java.lang.String imo,
		java.lang.String markasdelted, java.lang.String lastPortCode,
		int start, int end, java.lang.String shipName, java.lang.String callSign)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .findTempDebitNote(maritimeCode, positionCode,
			portRegionCode, mabankhai, stateCode, imo, markasdelted,
			lastPortCode, start, end, shipName, callSign);
	}

	public static long countTempDebitNote(java.lang.String maritimeCode,
		java.lang.String positionCode, java.lang.String portRegionCode,
		java.lang.String mabankhai, java.lang.String stateCode,
		java.lang.String imo, java.lang.String markasdelted,
		java.lang.String lastPortCode, int start, int end,
		java.lang.String shipName, java.lang.String callSign)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .countTempDebitNote(maritimeCode, positionCode,
			portRegionCode, mabankhai, stateCode, imo, markasdelted,
			lastPortCode, start, end, shipName, callSign);
	}

	public static boolean isValid_ShipAgencyCode_IMO_MaritimeCode(
		long documentName, int documentYear, java.lang.String imo,
		java.lang.String maritimeCode, java.lang.String ShipAgencyCode)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .isValid_ShipAgencyCode_IMO_MaritimeCode(documentName,
			documentYear, imo, maritimeCode, ShipAgencyCode);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}