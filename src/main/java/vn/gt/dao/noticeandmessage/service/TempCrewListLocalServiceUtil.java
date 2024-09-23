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
 * The utility for the temp crew list local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.TempCrewListLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see TempCrewListLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.TempCrewListLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.TempCrewListLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.TempCrewListLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class TempCrewListLocalServiceUtil {
public TempCrewListLocalServiceUtil(TempCrewListLocalServiceImpl service) {
TempCrewListLocalServiceUtil._service = service;
}
public static TempCrewListLocalServiceImpl getService() {
return _service;
}
private static TempCrewListLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.TempCrewListLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the temp crew list to the database. Also notifies the appropriate model listeners.
	*
	* @param tempCrewList the temp crew list
	* @return the temp crew list that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempCrewList addTempCrewList(
		com.fds.nsw.nghiepvu.model.TempCrewList tempCrewList)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addTempCrewList(tempCrewList);
	}

	/**
	* Creates a new temp crew list with the primary key. Does not add the temp crew list to the database.
	*
	* @param id the primary key for the new temp crew list
	* @return the new temp crew list
	*/
	public static com.fds.nsw.nghiepvu.model.TempCrewList createTempCrewList(
		long id) {
		return getService().createTempCrewList(id);
	}

	/**
	* Deletes the temp crew list with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the temp crew list
	* @throws PortalException if a temp crew list with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempCrewList(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempCrewList(id);
	}

	/**
	* Deletes the temp crew list from the database. Also notifies the appropriate model listeners.
	*
	* @param tempCrewList the temp crew list
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempCrewList(
		com.fds.nsw.nghiepvu.model.TempCrewList tempCrewList)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempCrewList(tempCrewList);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.TempCrewList fetchTempCrewList(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchTempCrewList(id);
	}

	/**
	* Returns the temp crew list with the primary key.
	*
	* @param id the primary key of the temp crew list
	* @return the temp crew list
	* @throws PortalException if a temp crew list with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempCrewList getTempCrewList(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempCrewList(id);
	}

	

	/**
	* Returns a range of all the temp crew lists.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of temp crew lists
	* @param end the upper bound of the range of temp crew lists (not inclusive)
	* @return the range of temp crew lists
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.TempCrewList> getTempCrewLists(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempCrewLists(start, end);
	}

	/**
	* Returns the number of temp crew lists.
	*
	* @return the number of temp crew lists
	* @throws SystemException if a system exception occurred
	*/
	public static int getTempCrewListsCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempCrewListsCount();
	}

	/**
	* Updates the temp crew list in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempCrewList the temp crew list
	* @return the temp crew list that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempCrewList updateTempCrewList(
		com.fds.nsw.nghiepvu.model.TempCrewList tempCrewList)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateTempCrewList(tempCrewList);
	}

	/**
	* Updates the temp crew list in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempCrewList the temp crew list
	* @param merge whether to merge the temp crew list with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the temp crew list that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempCrewList updateTempCrewList(
		com.fds.nsw.nghiepvu.model.TempCrewList tempCrewList,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateTempCrewList(tempCrewList, merge);
	}

	

	

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempCrewList> findBydocumentNameAnddocumentYear(
		long documentName, int documentYear)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .findBydocumentNameAnddocumentYear(documentName, documentYear);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempCrewList> findBydocumentNameAnddocumentYear(
		long documentName, int documentYear, int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .findBydocumentNameAnddocumentYear(documentName,
			documentYear, start, end);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempCrewList> findByRequestCode(
		java.lang.String requestCode)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().findByRequestCode(requestCode);
	}

	public static com.fds.nsw.nghiepvu.model.TempCrewList findTempCrewListByRequestCode(
		java.lang.String requestCode) {
		return getService().findTempCrewListByRequestCode(requestCode);
	}

	public static com.fds.nsw.nghiepvu.model.TempCrewList getLastByDocumentNameAndDocumentYear(
		long documentName, int documentYear)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .getLastByDocumentNameAndDocumentYear(documentName,
			documentYear);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempCrewList> findByDocumentNameAndDocumentYearOrderByDescRequestDate(
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