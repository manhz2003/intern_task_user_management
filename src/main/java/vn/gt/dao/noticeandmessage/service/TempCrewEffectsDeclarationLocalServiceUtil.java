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
 * The utility for the temp crew effects declaration local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.TempCrewEffectsDeclarationLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see TempCrewEffectsDeclarationLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.TempCrewEffectsDeclarationLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.TempCrewEffectsDeclarationLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.TempCrewEffectsDeclarationLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class TempCrewEffectsDeclarationLocalServiceUtil {
public TempCrewEffectsDeclarationLocalServiceUtil(TempCrewEffectsDeclarationLocalServiceImpl service) {
TempCrewEffectsDeclarationLocalServiceUtil._service = service;
}
public static TempCrewEffectsDeclarationLocalServiceImpl getService() {
return _service;
}
private static TempCrewEffectsDeclarationLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.TempCrewEffectsDeclarationLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the temp crew effects declaration to the database. Also notifies the appropriate model listeners.
	*
	* @param tempCrewEffectsDeclaration the temp crew effects declaration
	* @return the temp crew effects declaration that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempCrewEffectsDeclaration addTempCrewEffectsDeclaration(
		com.fds.nsw.nghiepvu.model.TempCrewEffectsDeclaration tempCrewEffectsDeclaration)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .addTempCrewEffectsDeclaration(tempCrewEffectsDeclaration);
	}

	/**
	* Creates a new temp crew effects declaration with the primary key. Does not add the temp crew effects declaration to the database.
	*
	* @param id the primary key for the new temp crew effects declaration
	* @return the new temp crew effects declaration
	*/
	public static com.fds.nsw.nghiepvu.model.TempCrewEffectsDeclaration createTempCrewEffectsDeclaration(
		long id) {
		return getService().createTempCrewEffectsDeclaration(id);
	}

	/**
	* Deletes the temp crew effects declaration with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the temp crew effects declaration
	* @throws PortalException if a temp crew effects declaration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempCrewEffectsDeclaration(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempCrewEffectsDeclaration(id);
	}

	/**
	* Deletes the temp crew effects declaration from the database. Also notifies the appropriate model listeners.
	*
	* @param tempCrewEffectsDeclaration the temp crew effects declaration
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempCrewEffectsDeclaration(
		com.fds.nsw.nghiepvu.model.TempCrewEffectsDeclaration tempCrewEffectsDeclaration)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempCrewEffectsDeclaration(tempCrewEffectsDeclaration);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.TempCrewEffectsDeclaration fetchTempCrewEffectsDeclaration(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchTempCrewEffectsDeclaration(id);
	}

	/**
	* Returns the temp crew effects declaration with the primary key.
	*
	* @param id the primary key of the temp crew effects declaration
	* @return the temp crew effects declaration
	* @throws PortalException if a temp crew effects declaration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempCrewEffectsDeclaration getTempCrewEffectsDeclaration(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempCrewEffectsDeclaration(id);
	}

	

	/**
	* Returns a range of all the temp crew effects declarations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of temp crew effects declarations
	* @param end the upper bound of the range of temp crew effects declarations (not inclusive)
	* @return the range of temp crew effects declarations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.TempCrewEffectsDeclaration> getTempCrewEffectsDeclarations(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempCrewEffectsDeclarations(start, end);
	}

	/**
	* Returns the number of temp crew effects declarations.
	*
	* @return the number of temp crew effects declarations
	* @throws SystemException if a system exception occurred
	*/
	public static int getTempCrewEffectsDeclarationsCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempCrewEffectsDeclarationsCount();
	}

	/**
	* Updates the temp crew effects declaration in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempCrewEffectsDeclaration the temp crew effects declaration
	* @return the temp crew effects declaration that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempCrewEffectsDeclaration updateTempCrewEffectsDeclaration(
		com.fds.nsw.nghiepvu.model.TempCrewEffectsDeclaration tempCrewEffectsDeclaration)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .updateTempCrewEffectsDeclaration(tempCrewEffectsDeclaration);
	}

	/**
	* Updates the temp crew effects declaration in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempCrewEffectsDeclaration the temp crew effects declaration
	* @param merge whether to merge the temp crew effects declaration with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the temp crew effects declaration that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempCrewEffectsDeclaration updateTempCrewEffectsDeclaration(
		com.fds.nsw.nghiepvu.model.TempCrewEffectsDeclaration tempCrewEffectsDeclaration,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .updateTempCrewEffectsDeclaration(tempCrewEffectsDeclaration,
			merge);
	}

	

	

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempCrewEffectsDeclaration> findBydocumentNameAnddocumentYear(
		long documentName, int documentYear)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .findBydocumentNameAnddocumentYear(documentName, documentYear);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempCrewEffectsDeclaration> findBydocumentNameAnddocumentYear(
		long documentName, int documentYear, int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .findBydocumentNameAnddocumentYear(documentName,
			documentYear, start, end);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempCrewEffectsDeclaration> findByRequestCode(
		java.lang.String requestCode)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().findByRequestCode(requestCode);
	}

	public static int countBydocumentNameAnddocumentYear(long documentName,
		int documentYear)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .countBydocumentNameAnddocumentYear(documentName,
			documentYear);
	}

	public static com.fds.nsw.nghiepvu.model.TempCrewEffectsDeclaration findTempCrewEffectsDeclarationByRequestCode(
		java.lang.String requestCode) {
		return getService()
				   .findTempCrewEffectsDeclarationByRequestCode(requestCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempCrewEffectsDeclaration> findBydocumentNameAnddocumentYearRequestState(
		long documentName, int documentYear, int requestState) {
		return getService()
				   .findBydocumentNameAnddocumentYearRequestState(documentName,
			documentYear, requestState);
	}

	public static com.fds.nsw.nghiepvu.model.TempCrewEffectsDeclaration getLastByDocumentNameAndDocumentYear(
		long documentName, int documentYear) {
		return getService()
				   .getLastByDocumentNameAndDocumentYear(documentName,
			documentYear);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempCrewEffectsDeclaration> findByDocumentNameAndDocumentYearOrderByDescRequestDate(
		long documentName, int documentYear) {
		return getService()
				   .findByDocumentNameAndDocumentYearOrderByDescRequestDate(documentName,
			documentYear);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}