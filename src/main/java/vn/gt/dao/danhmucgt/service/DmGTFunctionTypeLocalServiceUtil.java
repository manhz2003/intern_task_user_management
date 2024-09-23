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
 * The utility for the dm g t function type local service. This utility wraps {@link vn.gt.dao.danhmucgt.service.impl.DmGTFunctionTypeLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmGTFunctionTypeLocalService
 * @see vn.gt.dao.danhmucgt.service.base.DmGTFunctionTypeLocalServiceBaseImpl
 * @see vn.gt.dao.danhmucgt.service.impl.DmGTFunctionTypeLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmucgt.service.impl.DmGTFunctionTypeLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class DmGTFunctionTypeLocalServiceUtil {
public DmGTFunctionTypeLocalServiceUtil(DmGTFunctionTypeLocalServiceImpl service) {
DmGTFunctionTypeLocalServiceUtil._service = service;
}
public static DmGTFunctionTypeLocalServiceImpl getService() {
return _service;
}
private static DmGTFunctionTypeLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmucgt.service.impl.DmGTFunctionTypeLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dm g t function type to the database. Also notifies the appropriate model listeners.
	*
	* @param dmGTFunctionType the dm g t function type
	* @return the dm g t function type that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmGtFunctionType addDmGTFunctionType(
		com.fds.nsw.nghiepvu.model.DmGtFunctionType dmGTFunctionType)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmGTFunctionType(dmGTFunctionType);
	}

	/**
	* Creates a new dm g t function type with the primary key. Does not add the dm g t function type to the database.
	*
	* @param id the primary key for the new dm g t function type
	* @return the new dm g t function type
	*/
	public static com.fds.nsw.nghiepvu.model.DmGtFunctionType createDmGTFunctionType(
		long id) {
		return getService().createDmGTFunctionType(id);
	}

	/**
	* Deletes the dm g t function type with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the dm g t function type
	* @throws PortalException if a dm g t function type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmGTFunctionType(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmGTFunctionType(id);
	}

	/**
	* Deletes the dm g t function type from the database. Also notifies the appropriate model listeners.
	*
	* @param dmGTFunctionType the dm g t function type
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmGTFunctionType(
		com.fds.nsw.nghiepvu.model.DmGtFunctionType dmGTFunctionType)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmGTFunctionType(dmGTFunctionType);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.DmGtFunctionType fetchDmGTFunctionType(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmGTFunctionType(id);
	}

	/**
	* Returns the dm g t function type with the primary key.
	*
	* @param id the primary key of the dm g t function type
	* @return the dm g t function type
	* @throws PortalException if a dm g t function type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmGtFunctionType getDmGTFunctionType(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmGTFunctionType(id);
	}

	

	/**
	* Returns a range of all the dm g t function types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of dm g t function types
	* @param end the upper bound of the range of dm g t function types (not inclusive)
	* @return the range of dm g t function types
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmGtFunctionType> getDmGTFunctionTypes(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmGTFunctionTypes(start, end);
	}

	/**
	* Returns the number of dm g t function types.
	*
	* @return the number of dm g t function types
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmGTFunctionTypesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmGTFunctionTypesCount();
	}

	/**
	* Updates the dm g t function type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmGTFunctionType the dm g t function type
	* @return the dm g t function type that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmGtFunctionType updateDmGTFunctionType(
		com.fds.nsw.nghiepvu.model.DmGtFunctionType dmGTFunctionType)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmGTFunctionType(dmGTFunctionType);
	}

	/**
	* Updates the dm g t function type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmGTFunctionType the dm g t function type
	* @param merge whether to merge the dm g t function type with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm g t function type that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmGtFunctionType updateDmGTFunctionType(
		com.fds.nsw.nghiepvu.model.DmGtFunctionType dmGTFunctionType,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmGTFunctionType(dmGTFunctionType, merge);
	}

	

	

	public static java.lang.String findNameByFunctionTypeCode(
		java.lang.String functionTypeCode) {
		return getService().findNameByFunctionTypeCode(functionTypeCode);
	}

	public static int countByFunctionTypeCode(java.lang.String functionTypeCode) {
		return getService().countByFunctionTypeCode(functionTypeCode);
	}

	public static void clearService() {
		_service = null;
	}

	

	


}