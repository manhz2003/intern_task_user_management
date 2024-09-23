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
 * The utility for the temp ship stores declaration local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.TempShipStoresDeclarationLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see TempShipStoresDeclarationLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.TempShipStoresDeclarationLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.TempShipStoresDeclarationLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.TempShipStoresDeclarationLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class TempShipStoresDeclarationLocalServiceUtil {
public TempShipStoresDeclarationLocalServiceUtil(TempShipStoresDeclarationLocalServiceImpl service) {
TempShipStoresDeclarationLocalServiceUtil._service = service;
}
public static TempShipStoresDeclarationLocalServiceImpl getService() {
return _service;
}
private static TempShipStoresDeclarationLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.TempShipStoresDeclarationLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the temp ship stores declaration to the database. Also notifies the appropriate model listeners.
	*
	* @param tempShipStoresDeclaration the temp ship stores declaration
	* @return the temp ship stores declaration that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempShipStoresDeclaration addTempShipStoresDeclaration(
		com.fds.nsw.nghiepvu.model.TempShipStoresDeclaration tempShipStoresDeclaration)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .addTempShipStoresDeclaration(tempShipStoresDeclaration);
	}

	/**
	* Creates a new temp ship stores declaration with the primary key. Does not add the temp ship stores declaration to the database.
	*
	* @param id the primary key for the new temp ship stores declaration
	* @return the new temp ship stores declaration
	*/
	public static com.fds.nsw.nghiepvu.model.TempShipStoresDeclaration createTempShipStoresDeclaration(
		long id) {
		return getService().createTempShipStoresDeclaration(id);
	}

	/**
	* Deletes the temp ship stores declaration with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the temp ship stores declaration
	* @throws PortalException if a temp ship stores declaration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempShipStoresDeclaration(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempShipStoresDeclaration(id);
	}

	/**
	* Deletes the temp ship stores declaration from the database. Also notifies the appropriate model listeners.
	*
	* @param tempShipStoresDeclaration the temp ship stores declaration
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempShipStoresDeclaration(
		com.fds.nsw.nghiepvu.model.TempShipStoresDeclaration tempShipStoresDeclaration)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempShipStoresDeclaration(tempShipStoresDeclaration);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.TempShipStoresDeclaration fetchTempShipStoresDeclaration(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchTempShipStoresDeclaration(id);
	}

	/**
	* Returns the temp ship stores declaration with the primary key.
	*
	* @param id the primary key of the temp ship stores declaration
	* @return the temp ship stores declaration
	* @throws PortalException if a temp ship stores declaration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempShipStoresDeclaration getTempShipStoresDeclaration(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempShipStoresDeclaration(id);
	}

	

	/**
	* Returns a range of all the temp ship stores declarations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of temp ship stores declarations
	* @param end the upper bound of the range of temp ship stores declarations (not inclusive)
	* @return the range of temp ship stores declarations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.TempShipStoresDeclaration> getTempShipStoresDeclarations(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempShipStoresDeclarations(start, end);
	}

	/**
	* Returns the number of temp ship stores declarations.
	*
	* @return the number of temp ship stores declarations
	* @throws SystemException if a system exception occurred
	*/
	public static int getTempShipStoresDeclarationsCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempShipStoresDeclarationsCount();
	}

	/**
	* Updates the temp ship stores declaration in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempShipStoresDeclaration the temp ship stores declaration
	* @return the temp ship stores declaration that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempShipStoresDeclaration updateTempShipStoresDeclaration(
		com.fds.nsw.nghiepvu.model.TempShipStoresDeclaration tempShipStoresDeclaration)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .updateTempShipStoresDeclaration(tempShipStoresDeclaration);
	}

	/**
	* Updates the temp ship stores declaration in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempShipStoresDeclaration the temp ship stores declaration
	* @param merge whether to merge the temp ship stores declaration with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the temp ship stores declaration that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempShipStoresDeclaration updateTempShipStoresDeclaration(
		com.fds.nsw.nghiepvu.model.TempShipStoresDeclaration tempShipStoresDeclaration,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .updateTempShipStoresDeclaration(tempShipStoresDeclaration,
			merge);
	}

	

	

	public static int countBydocumentNameAnddocumentYear(long documentName,
		int documentYear) {
		return getService()
				   .countBydocumentNameAnddocumentYear(documentName,
			documentYear);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempShipStoresDeclaration> findBydocumentNameAnddocumentYear(
		long documentName, int documentYear) {
		return getService()
				   .findBydocumentNameAnddocumentYear(documentName, documentYear);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempShipStoresDeclaration> findBydocumentNameAnddocumentYearRequestState(
		long documentName, int documentYear, int requestState) {
		return getService()
				   .findBydocumentNameAnddocumentYearRequestState(documentName,
			documentYear, requestState);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempShipStoresDeclaration> findByRequestCode(
		java.lang.String requestCode) {
		return getService().findByRequestCode(requestCode);
	}

	public static com.fds.nsw.nghiepvu.model.TempShipStoresDeclaration findTempShipStoresDeclarationByRequestCode(
		java.lang.String requestCode) {
		return getService()
				   .findTempShipStoresDeclarationByRequestCode(requestCode);
	}

	public static com.fds.nsw.nghiepvu.model.TempShipStoresDeclaration getLastByDocumentNameAndDocumentYear(
		long documentName, int documentYear) {
		return getService()
				   .getLastByDocumentNameAndDocumentYear(documentName,
			documentYear);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempShipStoresDeclaration> findByDocumentNameAndDocumentYearOrderByDescRequestDate(
		long documentName, int documentYear) {
		return getService()
				   .findByDocumentNameAndDocumentYearOrderByDescRequestDate(documentName,
			documentYear);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}