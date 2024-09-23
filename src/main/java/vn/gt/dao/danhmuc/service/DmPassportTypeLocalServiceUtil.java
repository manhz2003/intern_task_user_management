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
 * The utility for the dm passport type local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmPassportTypeLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmPassportTypeLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmPassportTypeLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmPassportTypeLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmPassportTypeLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class DmPassportTypeLocalServiceUtil {
public DmPassportTypeLocalServiceUtil(DmPassportTypeLocalServiceImpl service) {
DmPassportTypeLocalServiceUtil._service = service;
}
public static DmPassportTypeLocalServiceImpl getService() {
return _service;
}
private static DmPassportTypeLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmPassportTypeLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dm passport type to the database. Also notifies the appropriate model listeners.
	*
	* @param dmPassportType the dm passport type
	* @return the dm passport type that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmPassportType addDmPassportType(
		com.fds.nsw.nghiepvu.model.DmPassportType dmPassportType)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmPassportType(dmPassportType);
	}

	/**
	* Creates a new dm passport type with the primary key. Does not add the dm passport type to the database.
	*
	* @param id the primary key for the new dm passport type
	* @return the new dm passport type
	*/
	public static com.fds.nsw.nghiepvu.model.DmPassportType createDmPassportType(
		int id) {
		return getService().createDmPassportType(id);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmPassportType> findByPassportNameVN(
			java.lang.String passportTypeNameVN, int start, int end) {
		return getService().findByPassportNameVN(passportTypeNameVN, start, end);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmPassportType> findPassportTypes(
			java.lang.String passportTypeNameVN, java.lang.String isDelete,
			java.lang.String passportTypeCodeGroup, int start, int end) {
		return getService()
				.findPassportTypes(passportTypeNameVN, isDelete,
						passportTypeCodeGroup, start, end);
	}

	public static long countPassportTypes(java.lang.String passportTypeNameVN,
										  java.lang.String isDelete, java.lang.String passportTypeCodeGroup,
										  int start, int end) {
		return getService()
				.countPassportTypes(passportTypeNameVN, isDelete,
						passportTypeCodeGroup, start, end);
	}


	/**
	* Deletes the dm passport type with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the dm passport type
	* @throws PortalException if a dm passport type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmPassportType(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmPassportType(id);
	}

	/**
	* Deletes the dm passport type from the database. Also notifies the appropriate model listeners.
	*
	* @param dmPassportType the dm passport type
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmPassportType(
		com.fds.nsw.nghiepvu.model.DmPassportType dmPassportType)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmPassportType(dmPassportType);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.DmPassportType fetchDmPassportType(
		int id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmPassportType(id);
	}

	/**
	* Returns the dm passport type with the primary key.
	*
	* @param id the primary key of the dm passport type
	* @return the dm passport type
	* @throws PortalException if a dm passport type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmPassportType getDmPassportType(
		int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmPassportType(id);
	}

	

	/**
	* Returns a range of all the dm passport types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of dm passport types
	* @param end the upper bound of the range of dm passport types (not inclusive)
	* @return the range of dm passport types
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmPassportType> getDmPassportTypes(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmPassportTypes(start, end);
	}

	/**
	* Returns the number of dm passport types.
	*
	* @return the number of dm passport types
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmPassportTypesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmPassportTypesCount();
	}

	/**
	* Updates the dm passport type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmPassportType the dm passport type
	* @return the dm passport type that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmPassportType updateDmPassportType(
		com.fds.nsw.nghiepvu.model.DmPassportType dmPassportType)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmPassportType(dmPassportType);
	}

	/**
	* Updates the dm passport type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmPassportType the dm passport type
	* @param merge whether to merge the dm passport type with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm passport type that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmPassportType updateDmPassportType(
		com.fds.nsw.nghiepvu.model.DmPassportType dmPassportType, boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmPassportType(dmPassportType, merge);
	}

	

	

	public static com.fds.nsw.nghiepvu.model.DmPassportType getByPassportTypeCode(
		java.lang.String passportTypeCode) {
		return getService().getByPassportTypeCode(passportTypeCode);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}