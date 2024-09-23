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
 * The utility for the dm g t business type local service. This utility wraps {@link vn.gt.dao.danhmucgt.service.impl.DmGTBusinessTypeLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmGTBusinessTypeLocalService
 * @see vn.gt.dao.danhmucgt.service.base.DmGTBusinessTypeLocalServiceBaseImpl
 * @see vn.gt.dao.danhmucgt.service.impl.DmGTBusinessTypeLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmucgt.service.impl.DmGTBusinessTypeLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class DmGTBusinessTypeLocalServiceUtil {
public DmGTBusinessTypeLocalServiceUtil(DmGTBusinessTypeLocalServiceImpl service) {
DmGTBusinessTypeLocalServiceUtil._service = service;
}
public static DmGTBusinessTypeLocalServiceImpl getService() {
return _service;
}
private static DmGTBusinessTypeLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmucgt.service.impl.DmGTBusinessTypeLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dm g t business type to the database. Also notifies the appropriate model listeners.
	*
	* @param dmGTBusinessType the dm g t business type
	* @return the dm g t business type that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmGtBusinessType addDmGTBusinessType(
		com.fds.nsw.nghiepvu.model.DmGtBusinessType dmGTBusinessType)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmGTBusinessType(dmGTBusinessType);
	}

	/**
	* Creates a new dm g t business type with the primary key. Does not add the dm g t business type to the database.
	*
	* @param id the primary key for the new dm g t business type
	* @return the new dm g t business type
	*/
	public static com.fds.nsw.nghiepvu.model.DmGtBusinessType createDmGTBusinessType(
		long id) {
		return getService().createDmGTBusinessType(id);
	}

	/**
	* Deletes the dm g t business type with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the dm g t business type
	* @throws PortalException if a dm g t business type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmGTBusinessType(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmGTBusinessType(id);
	}

	/**
	* Deletes the dm g t business type from the database. Also notifies the appropriate model listeners.
	*
	* @param dmGTBusinessType the dm g t business type
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmGTBusinessType(
		com.fds.nsw.nghiepvu.model.DmGtBusinessType dmGTBusinessType)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmGTBusinessType(dmGTBusinessType);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.DmGtBusinessType fetchDmGTBusinessType(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmGTBusinessType(id);
	}

	/**
	* Returns the dm g t business type with the primary key.
	*
	* @param id the primary key of the dm g t business type
	* @return the dm g t business type
	* @throws PortalException if a dm g t business type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmGtBusinessType getDmGTBusinessType(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmGTBusinessType(id);
	}

	

	/**
	* Returns a range of all the dm g t business types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of dm g t business types
	* @param end the upper bound of the range of dm g t business types (not inclusive)
	* @return the range of dm g t business types
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmGtBusinessType> getDmGTBusinessTypes(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmGTBusinessTypes(start, end);
	}

	/**
	* Returns the number of dm g t business types.
	*
	* @return the number of dm g t business types
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmGTBusinessTypesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmGTBusinessTypesCount();
	}

	/**
	* Updates the dm g t business type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmGTBusinessType the dm g t business type
	* @return the dm g t business type that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmGtBusinessType updateDmGTBusinessType(
		com.fds.nsw.nghiepvu.model.DmGtBusinessType dmGTBusinessType)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmGTBusinessType(dmGTBusinessType);
	}

	/**
	* Updates the dm g t business type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmGTBusinessType the dm g t business type
	* @param merge whether to merge the dm g t business type with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm g t business type that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmGtBusinessType updateDmGTBusinessType(
		com.fds.nsw.nghiepvu.model.DmGtBusinessType dmGTBusinessType,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmGTBusinessType(dmGTBusinessType, merge);
	}

	

	

	public static int countByBusinessTypeCode(int businessTypeCode) {
		return getService().countByBusinessTypeCode(businessTypeCode);
	}

	public static com.fds.nsw.nghiepvu.model.DmGtBusinessType getByBusinessTypeCode(
		int businessTypeCode) {
		return getService().getByBusinessTypeCode(businessTypeCode);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}