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
 * The utility for the dm goods type local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmGoodsTypeLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmGoodsTypeLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmGoodsTypeLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmGoodsTypeLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmGoodsTypeLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class DmGoodsTypeLocalServiceUtil {
public DmGoodsTypeLocalServiceUtil(DmGoodsTypeLocalServiceImpl service) {
DmGoodsTypeLocalServiceUtil._service = service;
}
public static DmGoodsTypeLocalServiceImpl getService() {
return _service;
}
private static DmGoodsTypeLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmGoodsTypeLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dm goods type to the database. Also notifies the appropriate model listeners.
	*
	* @param dmGoodsType the dm goods type
	* @return the dm goods type that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmGoodsType addDmGoodsType(
		com.fds.nsw.nghiepvu.model.DmGoodsType dmGoodsType)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmGoodsType(dmGoodsType);
	}

	/**
	* Creates a new dm goods type with the primary key. Does not add the dm goods type to the database.
	*
	* @param id the primary key for the new dm goods type
	* @return the new dm goods type
	*/
	public static com.fds.nsw.nghiepvu.model.DmGoodsType createDmGoodsType(int id) {
		return getService().createDmGoodsType(id);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmGoodsType> FByGoodsTypeNameVN(
			java.lang.String goodsTypeNameVN, int start, int end) {
		return getService().FByGoodsTypeNameVN(goodsTypeNameVN, start, end);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmGoodsType> findGoodsType(
			java.lang.String goodsTypeNameVN, java.lang.String isDelete,
			java.lang.String goodsTypeCodeGroup, int start, int end) {
		return getService()
				.findGoodsType(goodsTypeNameVN, isDelete,
						goodsTypeCodeGroup, start, end);
	}

	public static long countGoodsType(java.lang.String goodsTypeNameVN,
									  java.lang.String isDelete, java.lang.String goodsTypeCodeGroup) {
		return getService()
				.countGoodsType(goodsTypeNameVN, isDelete, goodsTypeCodeGroup);
	}

	/**
	* Deletes the dm goods type with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the dm goods type
	* @throws PortalException if a dm goods type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmGoodsType(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmGoodsType(id);
	}

	/**
	* Deletes the dm goods type from the database. Also notifies the appropriate model listeners.
	*
	* @param dmGoodsType the dm goods type
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmGoodsType(
		com.fds.nsw.nghiepvu.model.DmGoodsType dmGoodsType)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmGoodsType(dmGoodsType);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.DmGoodsType fetchDmGoodsType(int id)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmGoodsType(id);
	}

	/**
	* Returns the dm goods type with the primary key.
	*
	* @param id the primary key of the dm goods type
	* @return the dm goods type
	* @throws PortalException if a dm goods type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmGoodsType getDmGoodsType(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmGoodsType(id);
	}

	

	/**
	* Returns a range of all the dm goods types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of dm goods types
	* @param end the upper bound of the range of dm goods types (not inclusive)
	* @return the range of dm goods types
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmGoodsType> getDmGoodsTypes(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmGoodsTypes(start, end);
	}

	/**
	* Returns the number of dm goods types.
	*
	* @return the number of dm goods types
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmGoodsTypesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmGoodsTypesCount();
	}

	/**
	* Updates the dm goods type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmGoodsType the dm goods type
	* @return the dm goods type that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmGoodsType updateDmGoodsType(
		com.fds.nsw.nghiepvu.model.DmGoodsType dmGoodsType)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmGoodsType(dmGoodsType);
	}

	/**
	* Updates the dm goods type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmGoodsType the dm goods type
	* @param merge whether to merge the dm goods type with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm goods type that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmGoodsType updateDmGoodsType(
		com.fds.nsw.nghiepvu.model.DmGoodsType dmGoodsType, boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmGoodsType(dmGoodsType, merge);
	}

	

	

	public static com.fds.nsw.nghiepvu.model.DmGoodsType getByGoodsTypeCode(
		java.lang.String goodsTypeCode) {
		return getService().getByGoodsTypeCode(goodsTypeCode);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}