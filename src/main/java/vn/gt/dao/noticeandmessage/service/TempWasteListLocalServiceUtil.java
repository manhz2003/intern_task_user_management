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
 * The utility for the temp waste list local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.TempWasteListLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see TempWasteListLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.TempWasteListLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.TempWasteListLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.TempWasteListLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class TempWasteListLocalServiceUtil {
public TempWasteListLocalServiceUtil(TempWasteListLocalServiceImpl service) {
TempWasteListLocalServiceUtil._service = service;
}
public static TempWasteListLocalServiceImpl getService() {
return _service;
}
private static TempWasteListLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.TempWasteListLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the temp waste list to the database. Also notifies the appropriate model listeners.
	*
	* @param tempWasteList the temp waste list
	* @return the temp waste list that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempWasteList addTempWasteList(
		com.fds.nsw.nghiepvu.model.TempWasteList tempWasteList)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addTempWasteList(tempWasteList);
	}

	/**
	* Creates a new temp waste list with the primary key. Does not add the temp waste list to the database.
	*
	* @param id the primary key for the new temp waste list
	* @return the new temp waste list
	*/
	public static com.fds.nsw.nghiepvu.model.TempWasteList createTempWasteList(
		long id) {
		return getService().createTempWasteList(id);
	}

	/**
	* Deletes the temp waste list with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the temp waste list
	* @throws PortalException if a temp waste list with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempWasteList(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempWasteList(id);
	}

	/**
	* Deletes the temp waste list from the database. Also notifies the appropriate model listeners.
	*
	* @param tempWasteList the temp waste list
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempWasteList(
		com.fds.nsw.nghiepvu.model.TempWasteList tempWasteList)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempWasteList(tempWasteList);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.TempWasteList fetchTempWasteList(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchTempWasteList(id);
	}

	/**
	* Returns the temp waste list with the primary key.
	*
	* @param id the primary key of the temp waste list
	* @return the temp waste list
	* @throws PortalException if a temp waste list with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempWasteList getTempWasteList(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempWasteList(id);
	}

	

	/**
	* Returns a range of all the temp waste lists.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of temp waste lists
	* @param end the upper bound of the range of temp waste lists (not inclusive)
	* @return the range of temp waste lists
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.TempWasteList> getTempWasteLists(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempWasteLists(start, end);
	}

	/**
	* Returns the number of temp waste lists.
	*
	* @return the number of temp waste lists
	* @throws SystemException if a system exception occurred
	*/
	public static int getTempWasteListsCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempWasteListsCount();
	}

	/**
	* Updates the temp waste list in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempWasteList the temp waste list
	* @return the temp waste list that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempWasteList updateTempWasteList(
		com.fds.nsw.nghiepvu.model.TempWasteList tempWasteList)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateTempWasteList(tempWasteList);
	}

	/**
	* Updates the temp waste list in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempWasteList the temp waste list
	* @param merge whether to merge the temp waste list with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the temp waste list that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempWasteList updateTempWasteList(
		com.fds.nsw.nghiepvu.model.TempWasteList tempWasteList,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateTempWasteList(tempWasteList, merge);
	}

	

	

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempWasteList> getWasteLists(
		java.lang.String requestCode) {
		return getService().getWasteLists(requestCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempWasteList> getWasteLists(
		long documentName, int documentYear) {
		return getService().getWasteLists(documentName, documentYear);
	}

	public static com.fds.nsw.nghiepvu.model.TempWasteList getWasteList(
		java.lang.String requestCode, java.lang.String typeCode) {
		return getService().getWasteList(requestCode, typeCode);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}