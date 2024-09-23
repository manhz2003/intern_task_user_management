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

package vn.gt.dao.noticeandmessage.service;






/**
 * The utility for the temp dangerous goods items local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.TempDangerousGoodsItemsLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see TempDangerousGoodsItemsLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.TempDangerousGoodsItemsLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.TempDangerousGoodsItemsLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.TempDangerousGoodsItemsLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class TempDangerousGoodsItemsLocalServiceUtil {
public TempDangerousGoodsItemsLocalServiceUtil(TempDangerousGoodsItemsLocalServiceImpl service) {
TempDangerousGoodsItemsLocalServiceUtil._service = service;
}
public static TempDangerousGoodsItemsLocalServiceImpl getService() {
return _service;
}
private static TempDangerousGoodsItemsLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.TempDangerousGoodsItemsLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the temp dangerous goods items to the database. Also notifies the appropriate model listeners.
	*
	* @param tempDangerousGoodsItems the temp dangerous goods items
	* @return the temp dangerous goods items that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempDangerousGoodsItems addTempDangerousGoodsItems(
		com.fds.nsw.nghiepvu.model.TempDangerousGoodsItems tempDangerousGoodsItems)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addTempDangerousGoodsItems(tempDangerousGoodsItems);
	}

	/**
	* Creates a new temp dangerous goods items with the primary key. Does not add the temp dangerous goods items to the database.
	*
	* @param id the primary key for the new temp dangerous goods items
	* @return the new temp dangerous goods items
	*/
	public static com.fds.nsw.nghiepvu.model.TempDangerousGoodsItems createTempDangerousGoodsItems(
		long id) {
		return getService().createTempDangerousGoodsItems(id);
	}

	/**
	* Deletes the temp dangerous goods items with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the temp dangerous goods items
	* @throws PortalException if a temp dangerous goods items with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempDangerousGoodsItems(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempDangerousGoodsItems(id);
	}

	/**
	* Deletes the temp dangerous goods items from the database. Also notifies the appropriate model listeners.
	*
	* @param tempDangerousGoodsItems the temp dangerous goods items
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempDangerousGoodsItems(
		com.fds.nsw.nghiepvu.model.TempDangerousGoodsItems tempDangerousGoodsItems)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempDangerousGoodsItems(tempDangerousGoodsItems);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.TempDangerousGoodsItems fetchTempDangerousGoodsItems(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchTempDangerousGoodsItems(id);
	}

	/**
	* Returns the temp dangerous goods items with the primary key.
	*
	* @param id the primary key of the temp dangerous goods items
	* @return the temp dangerous goods items
	* @throws PortalException if a temp dangerous goods items with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempDangerousGoodsItems getTempDangerousGoodsItems(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempDangerousGoodsItems(id);
	}

	

	/**
	* Returns a range of all the temp dangerous goods itemses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of temp dangerous goods itemses
	* @param end the upper bound of the range of temp dangerous goods itemses (not inclusive)
	* @return the range of temp dangerous goods itemses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.TempDangerousGoodsItems> getTempDangerousGoodsItemses(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempDangerousGoodsItemses(start, end);
	}

	/**
	* Returns the number of temp dangerous goods itemses.
	*
	* @return the number of temp dangerous goods itemses
	* @throws SystemException if a system exception occurred
	*/
	public static int getTempDangerousGoodsItemsesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempDangerousGoodsItemsesCount();
	}

	/**
	* Updates the temp dangerous goods items in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempDangerousGoodsItems the temp dangerous goods items
	* @return the temp dangerous goods items that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempDangerousGoodsItems updateTempDangerousGoodsItems(
		com.fds.nsw.nghiepvu.model.TempDangerousGoodsItems tempDangerousGoodsItems)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .updateTempDangerousGoodsItems(tempDangerousGoodsItems);
	}

	/**
	* Updates the temp dangerous goods items in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempDangerousGoodsItems the temp dangerous goods items
	* @param merge whether to merge the temp dangerous goods items with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the temp dangerous goods items that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempDangerousGoodsItems updateTempDangerousGoodsItems(
		com.fds.nsw.nghiepvu.model.TempDangerousGoodsItems tempDangerousGoodsItems,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .updateTempDangerousGoodsItems(tempDangerousGoodsItems, merge);
	}

	

	

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempDangerousGoodsItems> findByRequestCode(
		java.lang.String requestCode)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().findByRequestCode(requestCode);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}