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

package vn.gt.dao.danhmuc.service;
/**
 * The utility for the dm history arrival purpose local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmHistoryArrivalPurposeLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmHistoryArrivalPurposeLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmHistoryArrivalPurposeLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmHistoryArrivalPurposeLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmHistoryArrivalPurposeLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class DmHistoryArrivalPurposeLocalServiceUtil {
public DmHistoryArrivalPurposeLocalServiceUtil(DmHistoryArrivalPurposeLocalServiceImpl service) {
DmHistoryArrivalPurposeLocalServiceUtil._service = service;
}
public static DmHistoryArrivalPurposeLocalServiceImpl getService() {
return _service;
}
private static DmHistoryArrivalPurposeLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmHistoryArrivalPurposeLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dm history arrival purpose to the database. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryArrivalPurpose the dm history arrival purpose
	* @return the dm history arrival purpose that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryArrivalPurpose addDmHistoryArrivalPurpose(
		com.fds.nsw.nghiepvu.model.DmHistoryArrivalPurpose dmHistoryArrivalPurpose)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmHistoryArrivalPurpose(dmHistoryArrivalPurpose);
	}

	/**
	* Creates a new dm history arrival purpose with the primary key. Does not add the dm history arrival purpose to the database.
	*
	* @param id the primary key for the new dm history arrival purpose
	* @return the new dm history arrival purpose
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryArrivalPurpose createDmHistoryArrivalPurpose(
		int id) {
		return getService().createDmHistoryArrivalPurpose(id);
	}

	/**
	* Deletes the dm history arrival purpose with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the dm history arrival purpose
	* @throws PortalException if a dm history arrival purpose with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmHistoryArrivalPurpose(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmHistoryArrivalPurpose(id);
	}

	/**
	* Deletes the dm history arrival purpose from the database. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryArrivalPurpose the dm history arrival purpose
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmHistoryArrivalPurpose(
		com.fds.nsw.nghiepvu.model.DmHistoryArrivalPurpose dmHistoryArrivalPurpose)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmHistoryArrivalPurpose(dmHistoryArrivalPurpose);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.DmHistoryArrivalPurpose fetchDmHistoryArrivalPurpose(
		int id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmHistoryArrivalPurpose(id);
	}

	/**
	* Returns the dm history arrival purpose with the primary key.
	*
	* @param id the primary key of the dm history arrival purpose
	* @return the dm history arrival purpose
	* @throws PortalException if a dm history arrival purpose with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryArrivalPurpose getDmHistoryArrivalPurpose(
		int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryArrivalPurpose(id);
	}

	

	/**
	* Returns a range of all the dm history arrival purposes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of dm history arrival purposes
	* @param end the upper bound of the range of dm history arrival purposes (not inclusive)
	* @return the range of dm history arrival purposes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmHistoryArrivalPurpose> getDmHistoryArrivalPurposes(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryArrivalPurposes(start, end);
	}

	/**
	* Returns the number of dm history arrival purposes.
	*
	* @return the number of dm history arrival purposes
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmHistoryArrivalPurposesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryArrivalPurposesCount();
	}

	/**
	* Updates the dm history arrival purpose in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryArrivalPurpose the dm history arrival purpose
	* @return the dm history arrival purpose that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryArrivalPurpose updateDmHistoryArrivalPurpose(
		com.fds.nsw.nghiepvu.model.DmHistoryArrivalPurpose dmHistoryArrivalPurpose)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .updateDmHistoryArrivalPurpose(dmHistoryArrivalPurpose);
	}

	/**
	* Updates the dm history arrival purpose in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryArrivalPurpose the dm history arrival purpose
	* @param merge whether to merge the dm history arrival purpose with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm history arrival purpose that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryArrivalPurpose updateDmHistoryArrivalPurpose(
		com.fds.nsw.nghiepvu.model.DmHistoryArrivalPurpose dmHistoryArrivalPurpose,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .updateDmHistoryArrivalPurpose(dmHistoryArrivalPurpose, merge);
	}

	

	

	public static com.fds.nsw.nghiepvu.model.DmHistoryArrivalPurpose findByPurposeCodeAndSyncVersion(
		java.lang.String purposeCode, java.lang.String syncVersion) {
		return getService()
				   .findByPurposeCodeAndSyncVersion(purposeCode, syncVersion);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}