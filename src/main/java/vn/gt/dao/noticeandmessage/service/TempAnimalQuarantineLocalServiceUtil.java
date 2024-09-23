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
 * The utility for the temp animal quarantine local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.TempAnimalQuarantineLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see TempAnimalQuarantineLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.TempAnimalQuarantineLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.TempAnimalQuarantineLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.TempAnimalQuarantineLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class TempAnimalQuarantineLocalServiceUtil {
public TempAnimalQuarantineLocalServiceUtil(TempAnimalQuarantineLocalServiceImpl service) {
TempAnimalQuarantineLocalServiceUtil._service = service;
}
public static TempAnimalQuarantineLocalServiceImpl getService() {
return _service;
}
private static TempAnimalQuarantineLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.TempAnimalQuarantineLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the temp animal quarantine to the database. Also notifies the appropriate model listeners.
	*
	* @param tempAnimalQuarantine the temp animal quarantine
	* @return the temp animal quarantine that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempAnimalQuarantine addTempAnimalQuarantine(
		com.fds.nsw.nghiepvu.model.TempAnimalQuarantine tempAnimalQuarantine)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addTempAnimalQuarantine(tempAnimalQuarantine);
	}

	/**
	* Creates a new temp animal quarantine with the primary key. Does not add the temp animal quarantine to the database.
	*
	* @param id the primary key for the new temp animal quarantine
	* @return the new temp animal quarantine
	*/
	public static com.fds.nsw.nghiepvu.model.TempAnimalQuarantine createTempAnimalQuarantine(
		long id) {
		return getService().createTempAnimalQuarantine(id);
	}

	/**
	* Deletes the temp animal quarantine with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the temp animal quarantine
	* @throws PortalException if a temp animal quarantine with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempAnimalQuarantine(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempAnimalQuarantine(id);
	}

	/**
	* Deletes the temp animal quarantine from the database. Also notifies the appropriate model listeners.
	*
	* @param tempAnimalQuarantine the temp animal quarantine
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempAnimalQuarantine(
		com.fds.nsw.nghiepvu.model.TempAnimalQuarantine tempAnimalQuarantine)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempAnimalQuarantine(tempAnimalQuarantine);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.TempAnimalQuarantine fetchTempAnimalQuarantine(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchTempAnimalQuarantine(id);
	}

	/**
	* Returns the temp animal quarantine with the primary key.
	*
	* @param id the primary key of the temp animal quarantine
	* @return the temp animal quarantine
	* @throws PortalException if a temp animal quarantine with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempAnimalQuarantine getTempAnimalQuarantine(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempAnimalQuarantine(id);
	}

	

	/**
	* Returns a range of all the temp animal quarantines.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of temp animal quarantines
	* @param end the upper bound of the range of temp animal quarantines (not inclusive)
	* @return the range of temp animal quarantines
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.TempAnimalQuarantine> getTempAnimalQuarantines(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempAnimalQuarantines(start, end);
	}

	/**
	* Returns the number of temp animal quarantines.
	*
	* @return the number of temp animal quarantines
	* @throws SystemException if a system exception occurred
	*/
	public static int getTempAnimalQuarantinesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempAnimalQuarantinesCount();
	}

	/**
	* Updates the temp animal quarantine in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempAnimalQuarantine the temp animal quarantine
	* @return the temp animal quarantine that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempAnimalQuarantine updateTempAnimalQuarantine(
		com.fds.nsw.nghiepvu.model.TempAnimalQuarantine tempAnimalQuarantine)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateTempAnimalQuarantine(tempAnimalQuarantine);
	}

	/**
	* Updates the temp animal quarantine in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempAnimalQuarantine the temp animal quarantine
	* @param merge whether to merge the temp animal quarantine with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the temp animal quarantine that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempAnimalQuarantine updateTempAnimalQuarantine(
		com.fds.nsw.nghiepvu.model.TempAnimalQuarantine tempAnimalQuarantine,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .updateTempAnimalQuarantine(tempAnimalQuarantine, merge);
	}

	

	

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempAnimalQuarantine> findBydocumentNameAnddocumentYear(
		long documentName, int documentYear) {
		return getService()
				   .findBydocumentNameAnddocumentYear(documentName, documentYear);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempAnimalQuarantine> findBydocumentNameAnddocumentYearRequestState(
		long documentName, int documentYear, int requestState) {
		return getService()
				   .findBydocumentNameAnddocumentYearRequestState(documentName,
			documentYear, requestState);
	}

	public static int countBydocumentNameAnddocumentYear(long documentName,
		int documentYear) {
		return getService()
				   .countBydocumentNameAnddocumentYear(documentName,
			documentYear);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempAnimalQuarantine> findByRequestCode(
		java.lang.String requestCode) {
		return getService().findByRequestCode(requestCode);
	}

	public static com.fds.nsw.nghiepvu.model.TempAnimalQuarantine getLastByDocumentNameAndDocumentYear(
		long documentName, int documentYear) {
		return getService()
				   .getLastByDocumentNameAndDocumentYear(documentName,
			documentYear);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempAnimalQuarantine> findByDocumentNameAndDocumentYearOrderByDescRequestDate(
		long documentName, int documentYear) {
		return getService()
				   .findByDocumentNameAndDocumentYearOrderByDescRequestDate(documentName,
			documentYear);
	}

	public static void clearService() {
		_service = null;
	}

	

	


}