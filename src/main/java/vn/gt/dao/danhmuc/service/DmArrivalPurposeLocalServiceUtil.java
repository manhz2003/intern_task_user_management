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


import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmArrivalPurposeLocalServiceImpl;
import org.springframework.stereotype.Component;

/**
 * The utility for the dm arrival purpose local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmArrivalPurposeLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmArrivalPurposeLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmArrivalPurposeLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmArrivalPurposeLocalServiceImpl
 * @generated
 */

@Component public class DmArrivalPurposeLocalServiceUtil {
public DmArrivalPurposeLocalServiceUtil(DmArrivalPurposeLocalServiceImpl service) {
DmArrivalPurposeLocalServiceUtil._service = service;
}
public static DmArrivalPurposeLocalServiceImpl getService() {
return _service;
}
private static DmArrivalPurposeLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmArrivalPurposeLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dm arrival purpose to the database. Also notifies the appropriate model listeners.
	*
	* @param dmArrivalPurpose the dm arrival purpose
	* @return the dm arrival purpose that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmArrivalPurpose addDmArrivalPurpose(
		com.fds.nsw.nghiepvu.model.DmArrivalPurpose dmArrivalPurpose)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmArrivalPurpose(dmArrivalPurpose);
	}

	/**
	* Creates a new dm arrival purpose with the primary key. Does not add the dm arrival purpose to the database.
	*
	* @param id the primary key for the new dm arrival purpose
	* @return the new dm arrival purpose
	*/
	public static com.fds.nsw.nghiepvu.model.DmArrivalPurpose createDmArrivalPurpose(
		int id) {
		return getService().createDmArrivalPurpose(id);
	}

	/**
	* Deletes the dm arrival purpose with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the dm arrival purpose
	* @throws PortalException if a dm arrival purpose with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmArrivalPurpose(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmArrivalPurpose(id);
	}

	/**
	* Deletes the dm arrival purpose from the database. Also notifies the appropriate model listeners.
	*
	* @param dmArrivalPurpose the dm arrival purpose
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmArrivalPurpose(
		com.fds.nsw.nghiepvu.model.DmArrivalPurpose dmArrivalPurpose)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmArrivalPurpose(dmArrivalPurpose);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.DmArrivalPurpose fetchDmArrivalPurpose(
		int id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmArrivalPurpose(id);
	}

	/**
	* Returns the dm arrival purpose with the primary key.
	*
	* @param id the primary key of the dm arrival purpose
	* @return the dm arrival purpose
	* @throws PortalException if a dm arrival purpose with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmArrivalPurpose getDmArrivalPurpose(
		int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmArrivalPurpose(id);
	}
    //
	

	/**
	* Returns a range of all the dm arrival purposes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of dm arrival purposes
	* @param end the upper bound of the range of dm arrival purposes (not inclusive)
	* @return the range of dm arrival purposes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmArrivalPurpose> getDmArrivalPurposes(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmArrivalPurposes(start, end);
	}

	/**
	* Returns the number of dm arrival purposes.
	*
	* @return the number of dm arrival purposes
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmArrivalPurposesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmArrivalPurposesCount();
	}

	/**
	* Updates the dm arrival purpose in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmArrivalPurpose the dm arrival purpose
	* @return the dm arrival purpose that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmArrivalPurpose updateDmArrivalPurpose(
		com.fds.nsw.nghiepvu.model.DmArrivalPurpose dmArrivalPurpose)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmArrivalPurpose(dmArrivalPurpose);
	}

	/**
	* Updates the dm arrival purpose in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmArrivalPurpose the dm arrival purpose
	* @param merge whether to merge the dm arrival purpose with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm arrival purpose that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmArrivalPurpose updateDmArrivalPurpose(
		com.fds.nsw.nghiepvu.model.DmArrivalPurpose dmArrivalPurpose, boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmArrivalPurpose(dmArrivalPurpose, merge);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
												java.lang.String[] parameterTypes, java.lang.Object[] arguments)
			throws java.lang.Throwable {
		return null;
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmArrivalPurpose> findByPurposeName(
			java.lang.String purposeName, int start, int end) {
		return getService().findByPurposeName(purposeName, start, end);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmArrivalPurpose> findArrivalPurposes(
			java.lang.String purposeName, java.lang.String isDelete,
			java.lang.String purposeCodeGroup, int start, int end) {
		return getService()
				.findArrivalPurposes(purposeName, isDelete,
						purposeCodeGroup, start, end);
	}

	public static long countArrivalPurposes(java.lang.String purposeName,
											java.lang.String isDelete, java.lang.String purposeCodeGroup) {
		return getService()
				.countArrivalPurposes(purposeName, isDelete, purposeCodeGroup);
	}






	public static com.fds.nsw.nghiepvu.model.DmArrivalPurpose getByPortCode(
		java.lang.String purposeCode) {
		return getService().getByPortCode(purposeCode);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}