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
 * The utility for the dm goods local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmGoodsLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmGoodsLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmGoodsLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmGoodsLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmGoodsLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class DmGoodsLocalServiceUtil {
public DmGoodsLocalServiceUtil(DmGoodsLocalServiceImpl service) {
DmGoodsLocalServiceUtil._service = service;
}
public static DmGoodsLocalServiceImpl getService() {
return _service;
}
private static DmGoodsLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmGoodsLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dm goods to the database. Also notifies the appropriate model listeners.
	*
	* @param dmGoods the dm goods
	* @return the dm goods that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmGoods addDmGoods(
		com.fds.nsw.nghiepvu.model.DmGoods dmGoods)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmGoods(dmGoods);
	}

	/**
	* Creates a new dm goods with the primary key. Does not add the dm goods to the database.
	*
	* @param id the primary key for the new dm goods
	* @return the new dm goods
	*/
	public static com.fds.nsw.nghiepvu.model.DmGoods createDmGoods(int id) {
		return getService().createDmGoods(id);
	}

	/**
	* Deletes the dm goods with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the dm goods
	* @throws PortalException if a dm goods with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmGoods(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmGoods(id);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmGoods> findByGoodsItemName(
			java.lang.String goodsItemName, int start, int end) {
		return getService().findByGoodsItemName(goodsItemName, start, end);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmGoods> findGoods(
			java.lang.String goodsItemName, java.lang.String isDelete,
			java.lang.String goodsItemCodeGroup, int start, int end) {
		return getService()
				.findGoods(goodsItemName, isDelete, goodsItemCodeGroup,
						start, end);
	}

	public static long countGoods(java.lang.String goodsItemName,
								  java.lang.String isDelete, java.lang.String goodsItemCodeGroup) {
		return getService()
				.countGoods(goodsItemName, isDelete, goodsItemCodeGroup);
	}


	/**
	* Deletes the dm goods from the database. Also notifies the appropriate model listeners.
	*
	* @param dmGoods the dm goods
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmGoods(com.fds.nsw.nghiepvu.model.DmGoods dmGoods)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmGoods(dmGoods);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.DmGoods fetchDmGoods(int id)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmGoods(id);
	}

	/**
	* Returns the dm goods with the primary key.
	*
	* @param id the primary key of the dm goods
	* @return the dm goods
	* @throws PortalException if a dm goods with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmGoods getDmGoods(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmGoods(id);
	}

	

	/**
	* Returns a range of all the dm goodses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of dm goodses
	* @param end the upper bound of the range of dm goodses (not inclusive)
	* @return the range of dm goodses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmGoods> getDmGoodses(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmGoodses(start, end);
	}

	/**
	* Returns the number of dm goodses.
	*
	* @return the number of dm goodses
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmGoodsesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmGoodsesCount();
	}

	/**
	* Updates the dm goods in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmGoods the dm goods
	* @return the dm goods that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmGoods updateDmGoods(
		com.fds.nsw.nghiepvu.model.DmGoods dmGoods)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmGoods(dmGoods);
	}

	/**
	* Updates the dm goods in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmGoods the dm goods
	* @param merge whether to merge the dm goods with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm goods that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmGoods updateDmGoods(
		com.fds.nsw.nghiepvu.model.DmGoods dmGoods, boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmGoods(dmGoods, merge);
	}

	

	

	public static com.fds.nsw.nghiepvu.model.DmGoods getByGoodsItemCode(
		java.lang.String goodsItemCode) {
		return getService().getByGoodsItemCode(goodsItemCode);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}