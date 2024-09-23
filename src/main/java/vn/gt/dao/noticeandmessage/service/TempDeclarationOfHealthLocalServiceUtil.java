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
 * The utility for the temp declaration of health local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.TempDeclarationOfHealthLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see TempDeclarationOfHealthLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.TempDeclarationOfHealthLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.TempDeclarationOfHealthLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.TempDeclarationOfHealthLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class TempDeclarationOfHealthLocalServiceUtil {
public TempDeclarationOfHealthLocalServiceUtil(TempDeclarationOfHealthLocalServiceImpl service) {
TempDeclarationOfHealthLocalServiceUtil._service = service;
}
public static TempDeclarationOfHealthLocalServiceImpl getService() {
return _service;
}
private static TempDeclarationOfHealthLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.TempDeclarationOfHealthLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the temp declaration of health to the database. Also notifies the appropriate model listeners.
	*
	* @param tempDeclarationOfHealth the temp declaration of health
	* @return the temp declaration of health that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempDeclarationOfHealth addTempDeclarationOfHealth(
		com.fds.nsw.nghiepvu.model.TempDeclarationOfHealth tempDeclarationOfHealth)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addTempDeclarationOfHealth(tempDeclarationOfHealth);
	}

	/**
	* Creates a new temp declaration of health with the primary key. Does not add the temp declaration of health to the database.
	*
	* @param id the primary key for the new temp declaration of health
	* @return the new temp declaration of health
	*/
	public static com.fds.nsw.nghiepvu.model.TempDeclarationOfHealth createTempDeclarationOfHealth(
		long id) {
		return getService().createTempDeclarationOfHealth(id);
	}

	/**
	* Deletes the temp declaration of health with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the temp declaration of health
	* @throws PortalException if a temp declaration of health with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempDeclarationOfHealth(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempDeclarationOfHealth(id);
	}

	/**
	* Deletes the temp declaration of health from the database. Also notifies the appropriate model listeners.
	*
	* @param tempDeclarationOfHealth the temp declaration of health
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempDeclarationOfHealth(
		com.fds.nsw.nghiepvu.model.TempDeclarationOfHealth tempDeclarationOfHealth)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempDeclarationOfHealth(tempDeclarationOfHealth);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.TempDeclarationOfHealth fetchTempDeclarationOfHealth(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchTempDeclarationOfHealth(id);
	}

	/**
	* Returns the temp declaration of health with the primary key.
	*
	* @param id the primary key of the temp declaration of health
	* @return the temp declaration of health
	* @throws PortalException if a temp declaration of health with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempDeclarationOfHealth getTempDeclarationOfHealth(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempDeclarationOfHealth(id);
	}

	

	/**
	* Returns a range of all the temp declaration of healths.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of temp declaration of healths
	* @param end the upper bound of the range of temp declaration of healths (not inclusive)
	* @return the range of temp declaration of healths
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.TempDeclarationOfHealth> getTempDeclarationOfHealths(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempDeclarationOfHealths(start, end);
	}

	/**
	* Returns the number of temp declaration of healths.
	*
	* @return the number of temp declaration of healths
	* @throws SystemException if a system exception occurred
	*/
	public static int getTempDeclarationOfHealthsCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempDeclarationOfHealthsCount();
	}

	/**
	* Updates the temp declaration of health in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempDeclarationOfHealth the temp declaration of health
	* @return the temp declaration of health that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempDeclarationOfHealth updateTempDeclarationOfHealth(
		com.fds.nsw.nghiepvu.model.TempDeclarationOfHealth tempDeclarationOfHealth)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .updateTempDeclarationOfHealth(tempDeclarationOfHealth);
	}

	/**
	* Updates the temp declaration of health in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempDeclarationOfHealth the temp declaration of health
	* @param merge whether to merge the temp declaration of health with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the temp declaration of health that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempDeclarationOfHealth updateTempDeclarationOfHealth(
		com.fds.nsw.nghiepvu.model.TempDeclarationOfHealth tempDeclarationOfHealth,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .updateTempDeclarationOfHealth(tempDeclarationOfHealth, merge);
	}

	

	

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempDeclarationOfHealth> findBydocumentNameAnddocumentYearRequestState(
		long documentName, int documentYear, int requestState) {
		return getService()
				   .findBydocumentNameAnddocumentYearRequestState(documentName,
			documentYear, requestState);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempDeclarationOfHealth> findByRequestCode(
		java.lang.String requestCode) {
		return getService().findByRequestCode(requestCode);
	}

	public static int countBydocumentNameAnddocumentYear(long documentName,
		int documentYear) {
		return getService()
				   .countBydocumentNameAnddocumentYear(documentName,
			documentYear);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempDeclarationOfHealth> findBydocumentNameAnddocumentYear(
		long documentName, int documentYear) {
		return getService()
				   .findBydocumentNameAnddocumentYear(documentName, documentYear);
	}

	public static com.fds.nsw.nghiepvu.model.TempDeclarationOfHealth getLastByDocumentNameAndDocumentYear(
		long documentName, int documentYear) {
		return getService()
				   .getLastByDocumentNameAndDocumentYear(documentName,
			documentYear);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempDeclarationOfHealth> findByDocumentNameAndDocumentYearOrderByDescRequestDate(
		long documentName, int documentYear) {
		return getService()
				   .findByDocumentNameAndDocumentYearOrderByDescRequestDate(documentName,
			documentYear);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}