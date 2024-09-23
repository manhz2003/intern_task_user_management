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
 * The utility for the dm history goods local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmHistoryGoodsLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmHistoryGoodsLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmHistoryGoodsLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmHistoryGoodsLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmHistoryGoodsLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class DmHistoryGoodsLocalServiceUtil {
public DmHistoryGoodsLocalServiceUtil(DmHistoryGoodsLocalServiceImpl service) {
DmHistoryGoodsLocalServiceUtil._service = service;
}
public static DmHistoryGoodsLocalServiceImpl getService() {
return _service;
}
private static DmHistoryGoodsLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmHistoryGoodsLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dm history goods to the database. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryGoods the dm history goods
	* @return the dm history goods that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryGoods addDmHistoryGoods(
		com.fds.nsw.nghiepvu.model.DmHistoryGoods dmHistoryGoods)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmHistoryGoods(dmHistoryGoods);
	}

	/**
	* Creates a new dm history goods with the primary key. Does not add the dm history goods to the database.
	*
	* @param id the primary key for the new dm history goods
	* @return the new dm history goods
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryGoods createDmHistoryGoods(
		int id) {
		return getService().createDmHistoryGoods(id);
	}


	public static void deleteDmHistoryGoods(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmHistoryGoods(id);
	}

	public static void deleteDmHistoryGoods(
		com.fds.nsw.nghiepvu.model.DmHistoryGoods dmHistoryGoods)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmHistoryGoods(dmHistoryGoods);
	}

	public static com.fds.nsw.nghiepvu.model.DmHistoryGoods fetchDmHistoryGoods(
		int id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmHistoryGoods(id);
	}

	/**
	* Returns the dm history goods with the primary key.
	*
	* @param id the primary key of the dm history goods
	* @return the dm history goods
	* @throws PortalException if a dm history goods with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryGoods getDmHistoryGoods(
		int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryGoods(id);
	}

	

	/**
	* Returns a range of all the dm history goodses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of dm history goodses
	* @param end the upper bound of the range of dm history goodses (not inclusive)
	* @return the range of dm history goodses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmHistoryGoods> getDmHistoryGoodses(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryGoodses(start, end);
	}

	/**
	* Returns the number of dm history goodses.
	*
	* @return the number of dm history goodses
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmHistoryGoodsesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryGoodsesCount();
	}

	/**
	* Updates the dm history goods in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryGoods the dm history goods
	* @return the dm history goods that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryGoods updateDmHistoryGoods(
		com.fds.nsw.nghiepvu.model.DmHistoryGoods dmHistoryGoods)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmHistoryGoods(dmHistoryGoods);
	}

	/**
	* Updates the dm history goods in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryGoods the dm history goods
	* @param merge whether to merge the dm history goods with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm history goods that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryGoods updateDmHistoryGoods(
		com.fds.nsw.nghiepvu.model.DmHistoryGoods dmHistoryGoods, boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmHistoryGoods(dmHistoryGoods, merge);
	}

	

	

	public static com.fds.nsw.nghiepvu.model.DmHistoryGoods findByGoodsItemCodeAndSyncVersion(
		java.lang.String goodsItemCode, java.lang.String syncVersion) {
		return getService()
				   .findByGoodsItemCodeAndSyncVersion(goodsItemCode, syncVersion);
	}

	public static void clearService() {
		_service = null;
	}

	

	


}