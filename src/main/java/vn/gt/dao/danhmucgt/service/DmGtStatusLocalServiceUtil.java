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

package vn.gt.dao.danhmucgt.service;






/**
 * The utility for the dm gt status local service. This utility wraps {@link vn.gt.dao.danhmucgt.service.impl.DmGtStatusLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmGtStatusLocalService
 * @see vn.gt.dao.danhmucgt.service.base.DmGtStatusLocalServiceBaseImpl
 * @see vn.gt.dao.danhmucgt.service.impl.DmGtStatusLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmucgt.service.impl.DmGtStatusLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class DmGtStatusLocalServiceUtil {
public DmGtStatusLocalServiceUtil(DmGtStatusLocalServiceImpl service) {
DmGtStatusLocalServiceUtil._service = service;
}
public static DmGtStatusLocalServiceImpl getService() {
return _service;
}
private static DmGtStatusLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmucgt.service.impl.DmGtStatusLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dm gt status to the database. Also notifies the appropriate model listeners.
	*
	* @param dmGtStatus the dm gt status
	* @return the dm gt status that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmGtStatus addDmGtStatus(
		com.fds.nsw.nghiepvu.model.DmGtStatus dmGtStatus)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmGtStatus(dmGtStatus);
	}

	/**
	* Creates a new dm gt status with the primary key. Does not add the dm gt status to the database.
	*
	* @param id the primary key for the new dm gt status
	* @return the new dm gt status
	*/
	public static com.fds.nsw.nghiepvu.model.DmGtStatus createDmGtStatus(long id) {
		return getService().createDmGtStatus(id);
	}

	/**
	* Deletes the dm gt status with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the dm gt status
	* @throws PortalException if a dm gt status with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmGtStatus(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmGtStatus(id);
	}

	/**
	* Deletes the dm gt status from the database. Also notifies the appropriate model listeners.
	*
	* @param dmGtStatus the dm gt status
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmGtStatus(
		com.fds.nsw.nghiepvu.model.DmGtStatus dmGtStatus)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmGtStatus(dmGtStatus);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.DmGtStatus fetchDmGtStatus(long id)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmGtStatus(id);
	}

	/**
	* Returns the dm gt status with the primary key.
	*
	* @param id the primary key of the dm gt status
	* @return the dm gt status
	* @throws PortalException if a dm gt status with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmGtStatus getDmGtStatus(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmGtStatus(id);
	}

	

	/**
	* Returns a range of all the dm gt statuses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of dm gt statuses
	* @param end the upper bound of the range of dm gt statuses (not inclusive)
	* @return the range of dm gt statuses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmGtStatus> getDmGtStatuses(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmGtStatuses(start, end);
	}

	/**
	* Returns the number of dm gt statuses.
	*
	* @return the number of dm gt statuses
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmGtStatusesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmGtStatusesCount();
	}

	/**
	* Updates the dm gt status in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmGtStatus the dm gt status
	* @return the dm gt status that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmGtStatus updateDmGtStatus(
		com.fds.nsw.nghiepvu.model.DmGtStatus dmGtStatus)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmGtStatus(dmGtStatus);
	}

	/**
	* Updates the dm gt status in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmGtStatus the dm gt status
	* @param merge whether to merge the dm gt status with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm gt status that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmGtStatus updateDmGtStatus(
		com.fds.nsw.nghiepvu.model.DmGtStatus dmGtStatus, boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmGtStatus(dmGtStatus, merge);
	}

	

	

	public static com.fds.nsw.nghiepvu.model.DmGtStatus findByStatusCode(
		int statusCode, int type) {
		return getService().findByStatusCode(statusCode, type);
	}

	public static java.lang.String findNameByStatusCode(int statusCode, int type) {
		return getService().findNameByStatusCode(statusCode, type);
	}

	public static int countByStatusCode(int statusCode, int type) {
		return getService().countByStatusCode(statusCode, type);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmGtStatus> findByType(
		int type) {
		return getService().findByType(type);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}