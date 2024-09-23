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
 * The utility for the temp passenger list local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.TempPassengerListLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see TempPassengerListLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.TempPassengerListLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.TempPassengerListLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.TempPassengerListLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class TempPassengerListLocalServiceUtil {
public TempPassengerListLocalServiceUtil(TempPassengerListLocalServiceImpl service) {
TempPassengerListLocalServiceUtil._service = service;
}
public static TempPassengerListLocalServiceImpl getService() {
return _service;
}
private static TempPassengerListLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.TempPassengerListLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the temp passenger list to the database. Also notifies the appropriate model listeners.
	*
	* @param tempPassengerList the temp passenger list
	* @return the temp passenger list that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempPassengerList addTempPassengerList(
		com.fds.nsw.nghiepvu.model.TempPassengerList tempPassengerList)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addTempPassengerList(tempPassengerList);
	}

	/**
	* Creates a new temp passenger list with the primary key. Does not add the temp passenger list to the database.
	*
	* @param id the primary key for the new temp passenger list
	* @return the new temp passenger list
	*/
	public static com.fds.nsw.nghiepvu.model.TempPassengerList createTempPassengerList(
		long id) {
		return getService().createTempPassengerList(id);
	}

	/**
	* Deletes the temp passenger list with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the temp passenger list
	* @throws PortalException if a temp passenger list with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempPassengerList(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempPassengerList(id);
	}

	/**
	* Deletes the temp passenger list from the database. Also notifies the appropriate model listeners.
	*
	* @param tempPassengerList the temp passenger list
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempPassengerList(
		com.fds.nsw.nghiepvu.model.TempPassengerList tempPassengerList)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempPassengerList(tempPassengerList);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.TempPassengerList fetchTempPassengerList(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchTempPassengerList(id);
	}

	/**
	* Returns the temp passenger list with the primary key.
	*
	* @param id the primary key of the temp passenger list
	* @return the temp passenger list
	* @throws PortalException if a temp passenger list with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempPassengerList getTempPassengerList(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempPassengerList(id);
	}

	

	/**
	* Returns a range of all the temp passenger lists.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of temp passenger lists
	* @param end the upper bound of the range of temp passenger lists (not inclusive)
	* @return the range of temp passenger lists
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.TempPassengerList> getTempPassengerLists(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempPassengerLists(start, end);
	}

	/**
	* Returns the number of temp passenger lists.
	*
	* @return the number of temp passenger lists
	* @throws SystemException if a system exception occurred
	*/
	public static int getTempPassengerListsCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempPassengerListsCount();
	}

	/**
	* Updates the temp passenger list in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempPassengerList the temp passenger list
	* @return the temp passenger list that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempPassengerList updateTempPassengerList(
		com.fds.nsw.nghiepvu.model.TempPassengerList tempPassengerList)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateTempPassengerList(tempPassengerList);
	}

	/**
	* Updates the temp passenger list in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempPassengerList the temp passenger list
	* @param merge whether to merge the temp passenger list with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the temp passenger list that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempPassengerList updateTempPassengerList(
		com.fds.nsw.nghiepvu.model.TempPassengerList tempPassengerList,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateTempPassengerList(tempPassengerList, merge);
	}

	

	

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempPassengerList> findBydocumentNameAnddocumentYear(
		long documentName, int documentYear)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .findBydocumentNameAnddocumentYear(documentName, documentYear);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempPassengerList> findBydocumentNameAnddocumentYearRequestState(
		long documentName, int documentYear, int requestState) {
		return getService()
				   .findBydocumentNameAnddocumentYearRequestState(documentName,
			documentYear, requestState);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempPassengerList> findBydocumentNameAndDocumentYear(
		long documentName, int documentYear, int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .findBydocumentNameAndDocumentYear(documentName,
			documentYear, start, end);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempPassengerList> findByRequestCode(
		java.lang.String requestCode)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().findByRequestCode(requestCode);
	}

	public static com.fds.nsw.nghiepvu.model.TempPassengerList findTempPassengerListByRequestCode(
		java.lang.String requestCode) {
		return getService().findTempPassengerListByRequestCode(requestCode);
	}

	public static com.fds.nsw.nghiepvu.model.TempPassengerList getLastByDocumentNameAndDocumentYear(
		long documentName, int documentYear) {
		return getService()
				   .getLastByDocumentNameAndDocumentYear(documentName,
			documentYear);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempPassengerList> findByDocumentNameAndDocumentYearOrderByDescRequestDate(
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