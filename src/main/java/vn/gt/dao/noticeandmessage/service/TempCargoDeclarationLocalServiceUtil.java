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
 * The utility for the temp cargo declaration local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.TempCargoDeclarationLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see TempCargoDeclarationLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.TempCargoDeclarationLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.TempCargoDeclarationLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.TempCargoDeclarationLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class TempCargoDeclarationLocalServiceUtil {
public TempCargoDeclarationLocalServiceUtil(TempCargoDeclarationLocalServiceImpl service) {
TempCargoDeclarationLocalServiceUtil._service = service;
}
public static TempCargoDeclarationLocalServiceImpl getService() {
return _service;
}
private static TempCargoDeclarationLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.TempCargoDeclarationLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the temp cargo declaration to the database. Also notifies the appropriate model listeners.
	*
	* @param tempCargoDeclaration the temp cargo declaration
	* @return the temp cargo declaration that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempCargoDeclaration addTempCargoDeclaration(
		com.fds.nsw.nghiepvu.model.TempCargoDeclaration tempCargoDeclaration)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addTempCargoDeclaration(tempCargoDeclaration);
	}

	/**
	* Creates a new temp cargo declaration with the primary key. Does not add the temp cargo declaration to the database.
	*
	* @param id the primary key for the new temp cargo declaration
	* @return the new temp cargo declaration
	*/
	public static com.fds.nsw.nghiepvu.model.TempCargoDeclaration createTempCargoDeclaration(
		long id) {
		return getService().createTempCargoDeclaration(id);
	}

	/**
	* Deletes the temp cargo declaration with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the temp cargo declaration
	* @throws PortalException if a temp cargo declaration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempCargoDeclaration(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempCargoDeclaration(id);
	}

	/**
	* Deletes the temp cargo declaration from the database. Also notifies the appropriate model listeners.
	*
	* @param tempCargoDeclaration the temp cargo declaration
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempCargoDeclaration(
		com.fds.nsw.nghiepvu.model.TempCargoDeclaration tempCargoDeclaration)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempCargoDeclaration(tempCargoDeclaration);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.TempCargoDeclaration fetchTempCargoDeclaration(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchTempCargoDeclaration(id);
	}

	/**
	* Returns the temp cargo declaration with the primary key.
	*
	* @param id the primary key of the temp cargo declaration
	* @return the temp cargo declaration
	* @throws PortalException if a temp cargo declaration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempCargoDeclaration getTempCargoDeclaration(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempCargoDeclaration(id);
	}

	

	/**
	* Returns a range of all the temp cargo declarations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of temp cargo declarations
	* @param end the upper bound of the range of temp cargo declarations (not inclusive)
	* @return the range of temp cargo declarations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.TempCargoDeclaration> getTempCargoDeclarations(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempCargoDeclarations(start, end);
	}

	/**
	* Returns the number of temp cargo declarations.
	*
	* @return the number of temp cargo declarations
	* @throws SystemException if a system exception occurred
	*/
	public static int getTempCargoDeclarationsCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempCargoDeclarationsCount();
	}

	/**
	* Updates the temp cargo declaration in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempCargoDeclaration the temp cargo declaration
	* @return the temp cargo declaration that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempCargoDeclaration updateTempCargoDeclaration(
		com.fds.nsw.nghiepvu.model.TempCargoDeclaration tempCargoDeclaration)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateTempCargoDeclaration(tempCargoDeclaration);
	}

	/**
	* Updates the temp cargo declaration in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempCargoDeclaration the temp cargo declaration
	* @param merge whether to merge the temp cargo declaration with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the temp cargo declaration that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempCargoDeclaration updateTempCargoDeclaration(
		com.fds.nsw.nghiepvu.model.TempCargoDeclaration tempCargoDeclaration,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .updateTempCargoDeclaration(tempCargoDeclaration, merge);
	}

	

	

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempCargoDeclaration> findBydocumentNameAnddocumentYearRequestState(
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

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempCargoDeclaration> findBydocumentNameAnddocumentYear(
		long documentName, int documentYear, int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .findBydocumentNameAnddocumentYear(documentName,
			documentYear, start, end);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempCargoDeclaration> findByRequestCode(
		java.lang.String requestCode)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().findByRequestCode(requestCode);
	}

	public static com.fds.nsw.nghiepvu.model.TempCargoDeclaration findTempCargoDeclarationByRequestCode(
		java.lang.String requestCode) {
		return getService().findTempCargoDeclarationByRequestCode(requestCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempCargoDeclaration> findBydocumentNameAnddocumentYear(
		long documentName, int documentYear)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .findBydocumentNameAnddocumentYear(documentName, documentYear);
	}

	public static com.fds.nsw.nghiepvu.model.TempCargoDeclaration getLastByDocumentNameAndDocumentYear(
		long documentName, int documentYear) {
		return getService()
				   .getLastByDocumentNameAndDocumentYear(documentName,
			documentYear);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempCargoDeclaration> findByDocumentNameAndDocumentYearOrderByDescRequestDate(
		long documentName, int documentYear)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .findByDocumentNameAndDocumentYearOrderByDescRequestDate(documentName,
			documentYear);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}