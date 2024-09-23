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


import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmHistoryPassportTypeLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class DmHistoryPassportTypeLocalServiceUtil {
public DmHistoryPassportTypeLocalServiceUtil(DmHistoryPassportTypeLocalServiceImpl service) {
DmHistoryPassportTypeLocalServiceUtil._service = service;
}
public static DmHistoryPassportTypeLocalServiceImpl getService() {
return _service;
}
private static DmHistoryPassportTypeLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmHistoryPassportTypeLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dm history passport type to the database. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryPassportType the dm history passport type
	* @return the dm history passport type that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryPassportType addDmHistoryPassportType(
		com.fds.nsw.nghiepvu.model.DmHistoryPassportType dmHistoryPassportType)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmHistoryPassportType(dmHistoryPassportType);
	}

	/**
	* Creates a new dm history passport type with the primary key. Does not add the dm history passport type to the database.
	*
	* @param id the primary key for the new dm history passport type
	* @return the new dm history passport type
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryPassportType createDmHistoryPassportType(
		int id) {
		return getService().createDmHistoryPassportType(id);
	}

	/**
	* Deletes the dm history passport type with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the dm history passport type
	* @throws PortalException if a dm history passport type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmHistoryPassportType(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmHistoryPassportType(id);
	}

	/**
	* Deletes the dm history passport type from the database. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryPassportType the dm history passport type
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmHistoryPassportType(
		com.fds.nsw.nghiepvu.model.DmHistoryPassportType dmHistoryPassportType)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmHistoryPassportType(dmHistoryPassportType);
	}

	

	public static com.fds.nsw.nghiepvu.model.DmHistoryPassportType fetchDmHistoryPassportType(
		int id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmHistoryPassportType(id);
	}

	/**
	* Returns the dm history passport type with the primary key.
	*
	* @param id the primary key of the dm history passport type
	* @return the dm history passport type
	* @throws PortalException if a dm history passport type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryPassportType getDmHistoryPassportType(
		int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryPassportType(id);
	}

	

	/**
	* Returns a range of all the dm history passport types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of dm history passport types
	* @param end the upper bound of the range of dm history passport types (not inclusive)
	* @return the range of dm history passport types
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmHistoryPassportType> getDmHistoryPassportTypes(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryPassportTypes(start, end);
	}

	/**
	* Returns the number of dm history passport types.
	*
	* @return the number of dm history passport types
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmHistoryPassportTypesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryPassportTypesCount();
	}

	/**
	* Updates the dm history passport type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryPassportType the dm history passport type
	* @return the dm history passport type that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryPassportType updateDmHistoryPassportType(
		com.fds.nsw.nghiepvu.model.DmHistoryPassportType dmHistoryPassportType)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmHistoryPassportType(dmHistoryPassportType);
	}

	/**
	* Updates the dm history passport type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryPassportType the dm history passport type
	* @param merge whether to merge the dm history passport type with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm history passport type that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryPassportType updateDmHistoryPassportType(
		com.fds.nsw.nghiepvu.model.DmHistoryPassportType dmHistoryPassportType,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .updateDmHistoryPassportType(dmHistoryPassportType, merge);
	}

	

	

	public static com.fds.nsw.nghiepvu.model.DmHistoryPassportType findByPassportTypeCodeAndSyncVersion(
		java.lang.String passportTypeCode, java.lang.String syncVersion) {
		return getService()
				   .findByPassportTypeCodeAndSyncVersion(passportTypeCode,
			syncVersion);
	}

	public static void clearService() {
		_service = null;
	}

	

	


}