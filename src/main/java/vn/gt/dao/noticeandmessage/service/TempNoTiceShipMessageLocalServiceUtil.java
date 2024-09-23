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
 * The utility for the temp no tice ship message local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.TempNoticeShipMessageLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see TempNoTiceShipMessageLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.TempNoTiceShipMessageLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.TempNoticeShipMessageLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.TempNoticeShipMessageLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class TempNoTiceShipMessageLocalServiceUtil {
public TempNoTiceShipMessageLocalServiceUtil(TempNoticeShipMessageLocalServiceImpl service) {
TempNoTiceShipMessageLocalServiceUtil._service = service;
}
public static TempNoticeShipMessageLocalServiceImpl getService() {
return _service;
}
private static TempNoticeShipMessageLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.TempNoticeShipMessageLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the temp no tice ship message to the database. Also notifies the appropriate model listeners.
	*
	* @param tempNoTiceShipMessage the temp no tice ship message
	* @return the temp no tice ship message that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempNoticeShipMessage addTempNoTiceShipMessage(
		com.fds.nsw.nghiepvu.model.TempNoticeShipMessage tempNoTiceShipMessage)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addTempNoticeShipMessage(tempNoTiceShipMessage);
	}

	/**
	* Creates a new temp no tice ship message with the primary key. Does not add the temp no tice ship message to the database.
	*
	* @param id the primary key for the new temp no tice ship message
	* @return the new temp no tice ship message
	*/
	public static com.fds.nsw.nghiepvu.model.TempNoticeShipMessage createTempNoTiceShipMessage(
		long id) {
		return getService().createTempNoticeShipMessage(id);
	}

	/**
	* Deletes the temp no tice ship message with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the temp no tice ship message
	* @throws PortalException if a temp no tice ship message with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempNoTiceShipMessage(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempNoticeShipMessage(id);
	}

	/**
	* Deletes the temp no tice ship message from the database. Also notifies the appropriate model listeners.
	*
	* @param tempNoTiceShipMessage the temp no tice ship message
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempNoTiceShipMessage(
		com.fds.nsw.nghiepvu.model.TempNoticeShipMessage tempNoTiceShipMessage)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempNoticeShipMessage(tempNoTiceShipMessage);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.TempNoticeShipMessage fetchTempNoTiceShipMessage(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchTempNoticeShipMessage(id);
	}

	/**
	* Returns the temp no tice ship message with the primary key.
	*
	* @param id the primary key of the temp no tice ship message
	* @return the temp no tice ship message
	* @throws PortalException if a temp no tice ship message with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempNoticeShipMessage getTempNoTiceShipMessage(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempNoticeShipMessage(id);
	}

	

	/**
	* Returns a range of all the temp no tice ship messages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of temp no tice ship messages
	* @param end the upper bound of the range of temp no tice ship messages (not inclusive)
	* @return the range of temp no tice ship messages
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.TempNoticeShipMessage> getTempNoTiceShipMessages(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempNoticeShipMessages(start, end);
	}

	/**
	* Returns the number of temp no tice ship messages.
	*
	* @return the number of temp no tice ship messages
	* @throws SystemException if a system exception occurred
	*/
	public static int getTempNoTiceShipMessagesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempNoticeShipMessagesCount();
	}

	/**
	* Updates the temp no tice ship message in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempNoTiceShipMessage the temp no tice ship message
	* @return the temp no tice ship message that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempNoticeShipMessage updateTempNoTiceShipMessage(
		com.fds.nsw.nghiepvu.model.TempNoticeShipMessage tempNoTiceShipMessage)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateTempNoticeShipMessage(tempNoTiceShipMessage);
	}

	/**
	* Updates the temp no tice ship message in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempNoTiceShipMessage the temp no tice ship message
	* @param merge whether to merge the temp no tice ship message with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the temp no tice ship message that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempNoticeShipMessage updateTempNoTiceShipMessage(
		com.fds.nsw.nghiepvu.model.TempNoticeShipMessage tempNoTiceShipMessage,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .updateTempNoticeShipMessage(tempNoTiceShipMessage, merge);
	}

	

	

	public static int countBydocumentNameAnddocumentYear(long documentName,
		int documentYear) {
		return getService()
				   .countBydocumentNameAnddocumentYear(documentName,
			documentYear);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempNoticeShipMessage> findByRequestCode(
		java.lang.String requestCode) {
		return getService().findByRequestCode(requestCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempNoticeShipMessage> findBydocumentNameAnddocumentYear(
		long documentName, int documentYear) {
		return getService()
				   .findBydocumentNameAnddocumentYear(documentName, documentYear);
	}

	public static com.fds.nsw.nghiepvu.model.TempNoticeShipMessage findTempNoTiceShipMessageByRequestCode(
		java.lang.String requestCode) {
		return getService().findTempNoticeShipMessageByRequestCode(requestCode);
	}

	public static com.fds.nsw.nghiepvu.model.TempNoticeShipMessage findTempNoTiceShipMessageXbByRequestCode(
		java.lang.String requestCode) {
		return getService().findTempNoticeShipMessageXbByRequestCode(requestCode);
	}

	public static com.fds.nsw.nghiepvu.model.TempNoticeShipMessage findTempNoTiceShipMessageTbByRequestCode(
		java.lang.String requestCode) {
		return getService().findTempNoticeShipMessageTbByRequestCode(requestCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempNoticeShipMessage> findBydocumentNameAndDocumentYearAndNoticeShipType(
		long documentName, int documentYear, java.lang.String noticeShipType)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .findBydocumentNameAndDocumentYearAndNoticeShipType(documentName,
			documentYear, noticeShipType);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempNoticeShipMessage> findByDocumentNameAndDocumentYearAndNoticeShipTypeOrderByColumn(
		long documentName, int documentYear, java.lang.String noticeShipType,
		java.lang.String orderByColumn, boolean ascOrdesc)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .findByDocumentNameAndDocumentYearAndNoticeShipTypeOrderByColumn(documentName,
			documentYear, noticeShipType, orderByColumn, ascOrdesc);
	}

	public static com.fds.nsw.nghiepvu.model.TempNoticeShipMessage getXacBaoLastByDocumentNameAndDocumentYear(
		long documentName, long documentYear) {
		return getService()
				   .getXacBaoLastByDocumentNameAndDocumentYear(documentName,
			documentYear);
	}

	public static com.fds.nsw.nghiepvu.model.TempNoticeShipMessage getThongBaoLastByDocumentNameAndDocumentYear(
		long documentName, long documentYear) {
		return getService()
				   .getThongBaoLastByDocumentNameAndDocumentYear(documentName,
			documentYear);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}