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
 * The utility for the temp ship security message local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.TempShipSecurityMessageLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see TempShipSecurityMessageLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.TempShipSecurityMessageLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.TempShipSecurityMessageLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.TempShipSecurityMessageLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class TempShipSecurityMessageLocalServiceUtil {
public TempShipSecurityMessageLocalServiceUtil(TempShipSecurityMessageLocalServiceImpl service) {
TempShipSecurityMessageLocalServiceUtil._service = service;
}
public static TempShipSecurityMessageLocalServiceImpl getService() {
return _service;
}
private static TempShipSecurityMessageLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.TempShipSecurityMessageLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the temp ship security message to the database. Also notifies the appropriate model listeners.
	*
	* @param tempShipSecurityMessage the temp ship security message
	* @return the temp ship security message that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempShipSecurityMessage addTempShipSecurityMessage(
		com.fds.nsw.nghiepvu.model.TempShipSecurityMessage tempShipSecurityMessage)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addTempShipSecurityMessage(tempShipSecurityMessage);
	}

	/**
	* Creates a new temp ship security message with the primary key. Does not add the temp ship security message to the database.
	*
	* @param id the primary key for the new temp ship security message
	* @return the new temp ship security message
	*/
	public static com.fds.nsw.nghiepvu.model.TempShipSecurityMessage createTempShipSecurityMessage(
		long id) {
		return getService().createTempShipSecurityMessage(id);
	}

	/**
	* Deletes the temp ship security message with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the temp ship security message
	* @throws PortalException if a temp ship security message with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempShipSecurityMessage(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempShipSecurityMessage(id);
	}

	/**
	* Deletes the temp ship security message from the database. Also notifies the appropriate model listeners.
	*
	* @param tempShipSecurityMessage the temp ship security message
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempShipSecurityMessage(
		com.fds.nsw.nghiepvu.model.TempShipSecurityMessage tempShipSecurityMessage)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempShipSecurityMessage(tempShipSecurityMessage);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.TempShipSecurityMessage fetchTempShipSecurityMessage(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchTempShipSecurityMessage(id);
	}

	/**
	* Returns the temp ship security message with the primary key.
	*
	* @param id the primary key of the temp ship security message
	* @return the temp ship security message
	* @throws PortalException if a temp ship security message with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempShipSecurityMessage getTempShipSecurityMessage(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempShipSecurityMessage(id);
	}

	

	/**
	* Returns a range of all the temp ship security messages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of temp ship security messages
	* @param end the upper bound of the range of temp ship security messages (not inclusive)
	* @return the range of temp ship security messages
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.TempShipSecurityMessage> getTempShipSecurityMessages(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempShipSecurityMessages(start, end);
	}

	/**
	* Returns the number of temp ship security messages.
	*
	* @return the number of temp ship security messages
	* @throws SystemException if a system exception occurred
	*/
	public static int getTempShipSecurityMessagesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempShipSecurityMessagesCount();
	}

	/**
	* Updates the temp ship security message in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempShipSecurityMessage the temp ship security message
	* @return the temp ship security message that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempShipSecurityMessage updateTempShipSecurityMessage(
		com.fds.nsw.nghiepvu.model.TempShipSecurityMessage tempShipSecurityMessage)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .updateTempShipSecurityMessage(tempShipSecurityMessage);
	}

	/**
	* Updates the temp ship security message in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempShipSecurityMessage the temp ship security message
	* @param merge whether to merge the temp ship security message with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the temp ship security message that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempShipSecurityMessage updateTempShipSecurityMessage(
		com.fds.nsw.nghiepvu.model.TempShipSecurityMessage tempShipSecurityMessage,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .updateTempShipSecurityMessage(tempShipSecurityMessage, merge);
	}

	

	

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempShipSecurityMessage> findBydocumentNameAnddocumentYearRequestState(
		long documentName, int documentYear, int requestState) {
		return getService()
				   .findBydocumentNameAnddocumentYearRequestState(documentName,
			documentYear, requestState);
	}

	public static int countBydocumentNameAnddocumentYear(long documentName,
		int documentYear)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .countBydocumentNameAnddocumentYear(documentName,
			documentYear);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempShipSecurityMessage> findByRequestCode(
		java.lang.String requestCode)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().findByRequestCode(requestCode);
	}

	public static com.fds.nsw.nghiepvu.model.TempShipSecurityMessage getByRequestCode(
		java.lang.String requestCode)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getByRequestCode(requestCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempShipSecurityMessage> findBydocumentNameAndDocumentYear(
		long documentName, int documentYear, int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .findBydocumentNameAndDocumentYear(documentName,
			documentYear, start, end);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempShipSecurityMessage> findBydocumentNameAnddocumentYear(
		long documentName, int documentYear)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .findBydocumentNameAnddocumentYear(documentName, documentYear);
	}

	public static com.fds.nsw.nghiepvu.model.TempShipSecurityMessage getLastByDocumentNameAndDocumentYear(
		long documentName, int documentYear) {
		return getService()
				   .getLastByDocumentNameAndDocumentYear(documentName,
			documentYear);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempShipSecurityMessage> findByDocumentNameAndDocumentYearOrderByDescRequestDate(
		long documentName, int documentYear) {
		return getService()
				   .findByDocumentNameAndDocumentYearOrderByDescRequestDate(documentName,
			documentYear);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}