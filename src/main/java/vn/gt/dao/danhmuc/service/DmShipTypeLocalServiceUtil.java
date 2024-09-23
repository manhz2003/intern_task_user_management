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
 * The utility for the dm ship type local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmShipTypeLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmShipTypeLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmShipTypeLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmShipTypeLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmShipTypeLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class DmShipTypeLocalServiceUtil {
public DmShipTypeLocalServiceUtil(DmShipTypeLocalServiceImpl service) {
DmShipTypeLocalServiceUtil._service = service;
}
public static DmShipTypeLocalServiceImpl getService() {
return _service;
}
private static DmShipTypeLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmShipTypeLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dm ship type to the database. Also notifies the appropriate model listeners.
	*
	* @param dmShipType the dm ship type
	* @return the dm ship type that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmShipType addDmShipType(
		com.fds.nsw.nghiepvu.model.DmShipType dmShipType)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmShipType(dmShipType);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmShipType> findByShipTypeNameVN(
			java.lang.String shipTypeNameVN, int start, int end) {
		return getService().findByShipTypeNameVN(shipTypeNameVN, start, end);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmShipType> findShipTypes(
			java.lang.String shipTypeNameVN, java.lang.String isDelete,
			java.lang.String shipTypeCodeGroup, int start, int end) {
		return getService()
				.findShipTypes(shipTypeNameVN, isDelete, shipTypeCodeGroup,
						start, end);
	}

	public static long countShipTypes(java.lang.String shipTypeNameVN,
									  java.lang.String isDelete, java.lang.String shipTypeCodeGroup) {
		return getService()
				.countShipTypes(shipTypeNameVN, isDelete, shipTypeCodeGroup);
	}


	/**
	* Creates a new dm ship type with the primary key. Does not add the dm ship type to the database.
	*
	* @param id the primary key for the new dm ship type
	* @return the new dm ship type
	*/
	public static com.fds.nsw.nghiepvu.model.DmShipType createDmShipType(int id) {
		return getService().createDmShipType(id);
	}

	/**
	* Deletes the dm ship type with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the dm ship type
	* @throws PortalException if a dm ship type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmShipType(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmShipType(id);
	}

	/**
	* Deletes the dm ship type from the database. Also notifies the appropriate model listeners.
	*
	* @param dmShipType the dm ship type
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmShipType(
		com.fds.nsw.nghiepvu.model.DmShipType dmShipType)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmShipType(dmShipType);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.DmShipType fetchDmShipType(int id)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmShipType(id);
	}

	/**
	* Returns the dm ship type with the primary key.
	*
	* @param id the primary key of the dm ship type
	* @return the dm ship type
	* @throws PortalException if a dm ship type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmShipType getDmShipType(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmShipType(id);
	}

	

	/**
	* Returns a range of all the dm ship types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of dm ship types
	* @param end the upper bound of the range of dm ship types (not inclusive)
	* @return the range of dm ship types
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmShipType> getDmShipTypes(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmShipTypes(start, end);
	}

	/**
	* Returns the number of dm ship types.
	*
	* @return the number of dm ship types
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmShipTypesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmShipTypesCount();
	}

	/**
	* Updates the dm ship type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmShipType the dm ship type
	* @return the dm ship type that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmShipType updateDmShipType(
		com.fds.nsw.nghiepvu.model.DmShipType dmShipType)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmShipType(dmShipType);
	}

	/**
	* Updates the dm ship type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmShipType the dm ship type
	* @param merge whether to merge the dm ship type with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm ship type that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmShipType updateDmShipType(
		com.fds.nsw.nghiepvu.model.DmShipType dmShipType, boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmShipType(dmShipType, merge);
	}

	

	

	public static com.fds.nsw.nghiepvu.model.DmShipType getByShipTypeCode(
		java.lang.String shipTypeCode) {
		return getService().getByShipTypeCode(shipTypeCode);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}