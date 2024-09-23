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
 * The utility for the temp goods items local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.TempGoodsItemsLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see TempGoodsItemsLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.TempGoodsItemsLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.TempGoodsItemsLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.TempGoodsItemsLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class TempGoodsItemsLocalServiceUtil {
public TempGoodsItemsLocalServiceUtil(TempGoodsItemsLocalServiceImpl service) {
TempGoodsItemsLocalServiceUtil._service = service;
}
public static TempGoodsItemsLocalServiceImpl getService() {
return _service;
}
private static TempGoodsItemsLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.TempGoodsItemsLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the temp goods items to the database. Also notifies the appropriate model listeners.
	*
	* @param tempGoodsItems the temp goods items
	* @return the temp goods items that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempGoodsItems addTempGoodsItems(
		com.fds.nsw.nghiepvu.model.TempGoodsItems tempGoodsItems)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addTempGoodsItems(tempGoodsItems);
	}

	/**
	* Creates a new temp goods items with the primary key. Does not add the temp goods items to the database.
	*
	* @param id the primary key for the new temp goods items
	* @return the new temp goods items
	*/
	public static com.fds.nsw.nghiepvu.model.TempGoodsItems createTempGoodsItems(
		long id) {
		return getService().createTempGoodsItems(id);
	}

	/**
	* Deletes the temp goods items with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the temp goods items
	* @throws PortalException if a temp goods items with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempGoodsItems(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempGoodsItems(id);
	}

	/**
	* Deletes the temp goods items from the database. Also notifies the appropriate model listeners.
	*
	* @param tempGoodsItems the temp goods items
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempGoodsItems(
		com.fds.nsw.nghiepvu.model.TempGoodsItems tempGoodsItems)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempGoodsItems(tempGoodsItems);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.TempGoodsItems fetchTempGoodsItems(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchTempGoodsItems(id);
	}

	/**
	* Returns the temp goods items with the primary key.
	*
	* @param id the primary key of the temp goods items
	* @return the temp goods items
	* @throws PortalException if a temp goods items with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempGoodsItems getTempGoodsItems(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempGoodsItems(id);
	}

	

	/**
	* Returns a range of all the temp goods itemses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of temp goods itemses
	* @param end the upper bound of the range of temp goods itemses (not inclusive)
	* @return the range of temp goods itemses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.TempGoodsItems> getTempGoodsItemses(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempGoodsItemses(start, end);
	}

	/**
	* Returns the number of temp goods itemses.
	*
	* @return the number of temp goods itemses
	* @throws SystemException if a system exception occurred
	*/
	public static int getTempGoodsItemsesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempGoodsItemsesCount();
	}

	/**
	* Updates the temp goods items in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempGoodsItems the temp goods items
	* @return the temp goods items that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempGoodsItems updateTempGoodsItems(
		com.fds.nsw.nghiepvu.model.TempGoodsItems tempGoodsItems)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateTempGoodsItems(tempGoodsItems);
	}

	/**
	* Updates the temp goods items in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempGoodsItems the temp goods items
	* @param merge whether to merge the temp goods items with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the temp goods items that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempGoodsItems updateTempGoodsItems(
		com.fds.nsw.nghiepvu.model.TempGoodsItems tempGoodsItems,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateTempGoodsItems(tempGoodsItems, merge);
	}

	

	

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempGoodsItems> findByRequestCode(
		java.lang.String requestCode)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().findByRequestCode(requestCode);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}