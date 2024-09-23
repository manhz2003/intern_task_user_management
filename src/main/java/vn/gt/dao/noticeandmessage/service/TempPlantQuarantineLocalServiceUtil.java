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
 * The utility for the temp plant quarantine local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.TempPlantQuarantineLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see TempPlantQuarantineLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.TempPlantQuarantineLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.TempPlantQuarantineLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.TempPlantQuarantineLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class TempPlantQuarantineLocalServiceUtil {
public TempPlantQuarantineLocalServiceUtil(TempPlantQuarantineLocalServiceImpl service) {
TempPlantQuarantineLocalServiceUtil._service = service;
}
public static TempPlantQuarantineLocalServiceImpl getService() {
return _service;
}
private static TempPlantQuarantineLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.TempPlantQuarantineLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the temp plant quarantine to the database. Also notifies the appropriate model listeners.
	*
	* @param tempPlantQuarantine the temp plant quarantine
	* @return the temp plant quarantine that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempPlantQuarantine addTempPlantQuarantine(
		com.fds.nsw.nghiepvu.model.TempPlantQuarantine tempPlantQuarantine)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addTempPlantQuarantine(tempPlantQuarantine);
	}

	/**
	* Creates a new temp plant quarantine with the primary key. Does not add the temp plant quarantine to the database.
	*
	* @param id the primary key for the new temp plant quarantine
	* @return the new temp plant quarantine
	*/
	public static com.fds.nsw.nghiepvu.model.TempPlantQuarantine createTempPlantQuarantine(
		long id) {
		return getService().createTempPlantQuarantine(id);
	}

	/**
	* Deletes the temp plant quarantine with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the temp plant quarantine
	* @throws PortalException if a temp plant quarantine with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempPlantQuarantine(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempPlantQuarantine(id);
	}

	/**
	* Deletes the temp plant quarantine from the database. Also notifies the appropriate model listeners.
	*
	* @param tempPlantQuarantine the temp plant quarantine
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempPlantQuarantine(
		com.fds.nsw.nghiepvu.model.TempPlantQuarantine tempPlantQuarantine)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempPlantQuarantine(tempPlantQuarantine);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.TempPlantQuarantine fetchTempPlantQuarantine(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchTempPlantQuarantine(id);
	}

	/**
	* Returns the temp plant quarantine with the primary key.
	*
	* @param id the primary key of the temp plant quarantine
	* @return the temp plant quarantine
	* @throws PortalException if a temp plant quarantine with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempPlantQuarantine getTempPlantQuarantine(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempPlantQuarantine(id);
	}

	

	/**
	* Returns a range of all the temp plant quarantines.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of temp plant quarantines
	* @param end the upper bound of the range of temp plant quarantines (not inclusive)
	* @return the range of temp plant quarantines
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.TempPlantQuarantine> getTempPlantQuarantines(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempPlantQuarantines(start, end);
	}

	/**
	* Returns the number of temp plant quarantines.
	*
	* @return the number of temp plant quarantines
	* @throws SystemException if a system exception occurred
	*/
	public static int getTempPlantQuarantinesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempPlantQuarantinesCount();
	}

	/**
	* Updates the temp plant quarantine in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempPlantQuarantine the temp plant quarantine
	* @return the temp plant quarantine that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempPlantQuarantine updateTempPlantQuarantine(
		com.fds.nsw.nghiepvu.model.TempPlantQuarantine tempPlantQuarantine)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateTempPlantQuarantine(tempPlantQuarantine);
	}

	/**
	* Updates the temp plant quarantine in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempPlantQuarantine the temp plant quarantine
	* @param merge whether to merge the temp plant quarantine with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the temp plant quarantine that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempPlantQuarantine updateTempPlantQuarantine(
		com.fds.nsw.nghiepvu.model.TempPlantQuarantine tempPlantQuarantine,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateTempPlantQuarantine(tempPlantQuarantine, merge);
	}

	

	

	public static int countBydocumentNameAnddocumentYear(long documentName,
		int documentYear) {
		return getService()
				   .countBydocumentNameAnddocumentYear(documentName,
			documentYear);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempPlantQuarantine> findBydocumentNameAnddocumentYear(
		long documentName, int documentYear) {
		return getService()
				   .findBydocumentNameAnddocumentYear(documentName, documentYear);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempPlantQuarantine> findBydocumentNameAnddocumentYearRequestState(
		long documentName, int documentYear, int requestState) {
		return getService()
				   .findBydocumentNameAnddocumentYearRequestState(documentName,
			documentYear, requestState);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempPlantQuarantine> findByRequestCode(
		java.lang.String requestCode)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().findByRequestCode(requestCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempPlantQuarantine> findBydocumentNameAnddocumentYear(
		long documentName, int documentYear, int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .findBydocumentNameAnddocumentYear(documentName,
			documentYear, start, end);
	}

	public static com.fds.nsw.nghiepvu.model.TempPlantQuarantine getLastByDocumentNameAndDocumentYear(
		long documentName, int documentYear) {
		return getService()
				   .getLastByDocumentNameAndDocumentYear(documentName,
			documentYear);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempPlantQuarantine> findByDocumentNameAndDocumentYearOrderByDescRequestDate(
		long documentName, int documentYear) {
		return getService()
				   .findByDocumentNameAndDocumentYearOrderByDescRequestDate(documentName,
			documentYear);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}