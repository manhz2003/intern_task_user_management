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
 * The utility for the temp health crew passenger list local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.TempHealthCrewPassengerListLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see TempHealthCrewPassengerListLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.TempHealthCrewPassengerListLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.TempHealthCrewPassengerListLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.TempHealthCrewPassengerListLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class TempHealthCrewPassengerListLocalServiceUtil {
public TempHealthCrewPassengerListLocalServiceUtil(TempHealthCrewPassengerListLocalServiceImpl service) {
TempHealthCrewPassengerListLocalServiceUtil._service = service;
}
public static TempHealthCrewPassengerListLocalServiceImpl getService() {
return _service;
}
private static TempHealthCrewPassengerListLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.TempHealthCrewPassengerListLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the temp health crew passenger list to the database. Also notifies the appropriate model listeners.
	*
	* @param tempHealthCrewPassengerList the temp health crew passenger list
	* @return the temp health crew passenger list that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempHealthCrewPaxList addTempHealthCrewPassengerList(
		com.fds.nsw.nghiepvu.model.TempHealthCrewPaxList tempHealthCrewPassengerList)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .addTempHealthCrewPassengerList(tempHealthCrewPassengerList);
	}

	/**
	* Creates a new temp health crew passenger list with the primary key. Does not add the temp health crew passenger list to the database.
	*
	* @param id the primary key for the new temp health crew passenger list
	* @return the new temp health crew passenger list
	*/
	public static com.fds.nsw.nghiepvu.model.TempHealthCrewPaxList createTempHealthCrewPassengerList(
		long id) {
		return getService().createTempHealthCrewPassengerList(id);
	}

	/**
	* Deletes the temp health crew passenger list with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the temp health crew passenger list
	* @throws PortalException if a temp health crew passenger list with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempHealthCrewPassengerList(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempHealthCrewPassengerList(id);
	}

	/**
	* Deletes the temp health crew passenger list from the database. Also notifies the appropriate model listeners.
	*
	* @param tempHealthCrewPassengerList the temp health crew passenger list
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempHealthCrewPassengerList(
		com.fds.nsw.nghiepvu.model.TempHealthCrewPaxList tempHealthCrewPassengerList)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService()
			.deleteTempHealthCrewPassengerList(tempHealthCrewPassengerList);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.TempHealthCrewPaxList fetchTempHealthCrewPassengerList(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchTempHealthCrewPassengerList(id);
	}

	/**
	* Returns the temp health crew passenger list with the primary key.
	*
	* @param id the primary key of the temp health crew passenger list
	* @return the temp health crew passenger list
	* @throws PortalException if a temp health crew passenger list with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempHealthCrewPaxList getTempHealthCrewPassengerList(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempHealthCrewPassengerList(id);
	}

	

	/**
	* Returns a range of all the temp health crew passenger lists.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of temp health crew passenger lists
	* @param end the upper bound of the range of temp health crew passenger lists (not inclusive)
	* @return the range of temp health crew passenger lists
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.TempHealthCrewPaxList> getTempHealthCrewPassengerLists(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempHealthCrewPassengerLists(start, end);
	}

	/**
	* Returns the number of temp health crew passenger lists.
	*
	* @return the number of temp health crew passenger lists
	* @throws SystemException if a system exception occurred
	*/
	public static int getTempHealthCrewPassengerListsCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempHealthCrewPassengerListsCount();
	}

	/**
	* Updates the temp health crew passenger list in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempHealthCrewPassengerList the temp health crew passenger list
	* @return the temp health crew passenger list that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempHealthCrewPaxList updateTempHealthCrewPassengerList(
		com.fds.nsw.nghiepvu.model.TempHealthCrewPaxList tempHealthCrewPassengerList)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .updateTempHealthCrewPassengerList(tempHealthCrewPassengerList);
	}

	/**
	* Updates the temp health crew passenger list in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempHealthCrewPassengerList the temp health crew passenger list
	* @param merge whether to merge the temp health crew passenger list with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the temp health crew passenger list that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempHealthCrewPaxList updateTempHealthCrewPassengerList(
		com.fds.nsw.nghiepvu.model.TempHealthCrewPaxList tempHealthCrewPassengerList,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .updateTempHealthCrewPassengerList(tempHealthCrewPassengerList,
			merge);
	}

	

	

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempHealthCrewPaxList> findByRequestCode(
		java.lang.String requestCode) {
		return getService().findByRequestCode(requestCode);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}