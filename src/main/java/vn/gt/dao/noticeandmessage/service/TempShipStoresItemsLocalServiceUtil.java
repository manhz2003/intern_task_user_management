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
 * The utility for the temp ship stores items local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.TempShipStoresItemsLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see TempShipStoresItemsLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.TempShipStoresItemsLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.TempShipStoresItemsLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.TempShipStoresItemsLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class TempShipStoresItemsLocalServiceUtil {
public TempShipStoresItemsLocalServiceUtil(TempShipStoresItemsLocalServiceImpl service) {
TempShipStoresItemsLocalServiceUtil._service = service;
}
public static TempShipStoresItemsLocalServiceImpl getService() {
return _service;
}
private static TempShipStoresItemsLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.TempShipStoresItemsLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the temp ship stores items to the database. Also notifies the appropriate model listeners.
	*
	* @param tempShipStoresItems the temp ship stores items
	* @return the temp ship stores items that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempShipStoresItems addTempShipStoresItems(
		com.fds.nsw.nghiepvu.model.TempShipStoresItems tempShipStoresItems)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addTempShipStoresItems(tempShipStoresItems);
	}

	/**
	* Creates a new temp ship stores items with the primary key. Does not add the temp ship stores items to the database.
	*
	* @param id the primary key for the new temp ship stores items
	* @return the new temp ship stores items
	*/
	public static com.fds.nsw.nghiepvu.model.TempShipStoresItems createTempShipStoresItems(
		long id) {
		return getService().createTempShipStoresItems(id);
	}

	/**
	* Deletes the temp ship stores items with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the temp ship stores items
	* @throws PortalException if a temp ship stores items with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempShipStoresItems(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempShipStoresItems(id);
	}

	/**
	* Deletes the temp ship stores items from the database. Also notifies the appropriate model listeners.
	*
	* @param tempShipStoresItems the temp ship stores items
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempShipStoresItems(
		com.fds.nsw.nghiepvu.model.TempShipStoresItems tempShipStoresItems)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempShipStoresItems(tempShipStoresItems);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.TempShipStoresItems fetchTempShipStoresItems(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchTempShipStoresItems(id);
	}

	/**
	* Returns the temp ship stores items with the primary key.
	*
	* @param id the primary key of the temp ship stores items
	* @return the temp ship stores items
	* @throws PortalException if a temp ship stores items with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempShipStoresItems getTempShipStoresItems(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempShipStoresItems(id);
	}

	

	/**
	* Returns a range of all the temp ship stores itemses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of temp ship stores itemses
	* @param end the upper bound of the range of temp ship stores itemses (not inclusive)
	* @return the range of temp ship stores itemses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.TempShipStoresItems> getTempShipStoresItemses(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempShipStoresItemses(start, end);
	}

	/**
	* Returns the number of temp ship stores itemses.
	*
	* @return the number of temp ship stores itemses
	* @throws SystemException if a system exception occurred
	*/
	public static int getTempShipStoresItemsesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempShipStoresItemsesCount();
	}

	/**
	* Updates the temp ship stores items in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempShipStoresItems the temp ship stores items
	* @return the temp ship stores items that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempShipStoresItems updateTempShipStoresItems(
		com.fds.nsw.nghiepvu.model.TempShipStoresItems tempShipStoresItems)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateTempShipStoresItems(tempShipStoresItems);
	}

	/**
	* Updates the temp ship stores items in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempShipStoresItems the temp ship stores items
	* @param merge whether to merge the temp ship stores items with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the temp ship stores items that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempShipStoresItems updateTempShipStoresItems(
		com.fds.nsw.nghiepvu.model.TempShipStoresItems tempShipStoresItems,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateTempShipStoresItems(tempShipStoresItems, merge);
	}

	

	

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempShipStoresItems> findTempShipStoresItemsByRequestCode(
		java.lang.String requestCode) {
		return getService().findTempShipStoresItemsByRequestCode(requestCode);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}