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
 * The utility for the dm history goods type local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmHistoryGoodsTypeLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmHistoryGoodsTypeLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmHistoryGoodsTypeLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmHistoryGoodsTypeLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmHistoryGoodsTypeLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class DmHistoryGoodsTypeLocalServiceUtil {
public DmHistoryGoodsTypeLocalServiceUtil(DmHistoryGoodsTypeLocalServiceImpl service) {
DmHistoryGoodsTypeLocalServiceUtil._service = service;
}
public static DmHistoryGoodsTypeLocalServiceImpl getService() {
return _service;
}
private static DmHistoryGoodsTypeLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmHistoryGoodsTypeLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dm history goods type to the database. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryGoodsType the dm history goods type
	* @return the dm history goods type that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryGoodsType addDmHistoryGoodsType(
		com.fds.nsw.nghiepvu.model.DmHistoryGoodsType dmHistoryGoodsType)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmHistoryGoodsType(dmHistoryGoodsType);
	}

	/**
	* Creates a new dm history goods type with the primary key. Does not add the dm history goods type to the database.
	*
	* @param id the primary key for the new dm history goods type
	* @return the new dm history goods type
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryGoodsType createDmHistoryGoodsType(
		int id) {
		return getService().createDmHistoryGoodsType(id);
	}

	/**
	* Deletes the dm history goods type with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the dm history goods type
	* @throws PortalException if a dm history goods type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmHistoryGoodsType(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmHistoryGoodsType(id);
	}

	/**
	* Deletes the dm history goods type from the database. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryGoodsType the dm history goods type
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmHistoryGoodsType(
		com.fds.nsw.nghiepvu.model.DmHistoryGoodsType dmHistoryGoodsType)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmHistoryGoodsType(dmHistoryGoodsType);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.DmHistoryGoodsType fetchDmHistoryGoodsType(
		int id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmHistoryGoodsType(id);
	}

	/**
	* Returns the dm history goods type with the primary key.
	*
	* @param id the primary key of the dm history goods type
	* @return the dm history goods type
	* @throws PortalException if a dm history goods type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryGoodsType getDmHistoryGoodsType(
		int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryGoodsType(id);
	}

	

	/**
	* Returns a range of all the dm history goods types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of dm history goods types
	* @param end the upper bound of the range of dm history goods types (not inclusive)
	* @return the range of dm history goods types
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmHistoryGoodsType> getDmHistoryGoodsTypes(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryGoodsTypes(start, end);
	}

	/**
	* Returns the number of dm history goods types.
	*
	* @return the number of dm history goods types
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmHistoryGoodsTypesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryGoodsTypesCount();
	}

	/**
	* Updates the dm history goods type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryGoodsType the dm history goods type
	* @return the dm history goods type that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryGoodsType updateDmHistoryGoodsType(
		com.fds.nsw.nghiepvu.model.DmHistoryGoodsType dmHistoryGoodsType)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmHistoryGoodsType(dmHistoryGoodsType);
	}

	/**
	* Updates the dm history goods type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryGoodsType the dm history goods type
	* @param merge whether to merge the dm history goods type with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm history goods type that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryGoodsType updateDmHistoryGoodsType(
		com.fds.nsw.nghiepvu.model.DmHistoryGoodsType dmHistoryGoodsType,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmHistoryGoodsType(dmHistoryGoodsType, merge);
	}

	

	

	public static com.fds.nsw.nghiepvu.model.DmHistoryGoodsType findByGoodsTypeCodeAndSyncVersion(
		java.lang.String goodsTypeCode, java.lang.String syncVersion) {
		return getService()
				   .findByGoodsTypeCodeAndSyncVersion(goodsTypeCode, syncVersion);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}