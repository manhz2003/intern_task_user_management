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
 * The utility for the temp ship security port items local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.TempShipSecurityPortItemsLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see TempShipSecurityPortItemsLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.TempShipSecurityPortItemsLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.TempShipSecurityPortItemsLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.TempShipSecurityPortItemsLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class TempShipSecurityPortItemsLocalServiceUtil {
public TempShipSecurityPortItemsLocalServiceUtil(TempShipSecurityPortItemsLocalServiceImpl service) {
TempShipSecurityPortItemsLocalServiceUtil._service = service;
}
public static TempShipSecurityPortItemsLocalServiceImpl getService() {
return _service;
}
private static TempShipSecurityPortItemsLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.TempShipSecurityPortItemsLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the temp ship security port items to the database. Also notifies the appropriate model listeners.
	*
	* @param tempShipSecurityPortItems the temp ship security port items
	* @return the temp ship security port items that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempShipSecurityPortItems addTempShipSecurityPortItems(
		com.fds.nsw.nghiepvu.model.TempShipSecurityPortItems tempShipSecurityPortItems)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .addTempShipSecurityPortItems(tempShipSecurityPortItems);
	}

	/**
	* Creates a new temp ship security port items with the primary key. Does not add the temp ship security port items to the database.
	*
	* @param id the primary key for the new temp ship security port items
	* @return the new temp ship security port items
	*/
	public static com.fds.nsw.nghiepvu.model.TempShipSecurityPortItems createTempShipSecurityPortItems(
		long id) {
		return getService().createTempShipSecurityPortItems(id);
	}

	/**
	* Deletes the temp ship security port items with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the temp ship security port items
	* @throws PortalException if a temp ship security port items with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempShipSecurityPortItems(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempShipSecurityPortItems(id);
	}

	/**
	* Deletes the temp ship security port items from the database. Also notifies the appropriate model listeners.
	*
	* @param tempShipSecurityPortItems the temp ship security port items
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempShipSecurityPortItems(
		com.fds.nsw.nghiepvu.model.TempShipSecurityPortItems tempShipSecurityPortItems)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempShipSecurityPortItems(tempShipSecurityPortItems);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.TempShipSecurityPortItems fetchTempShipSecurityPortItems(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchTempShipSecurityPortItems(id);
	}

	/**
	* Returns the temp ship security port items with the primary key.
	*
	* @param id the primary key of the temp ship security port items
	* @return the temp ship security port items
	* @throws PortalException if a temp ship security port items with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempShipSecurityPortItems getTempShipSecurityPortItems(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempShipSecurityPortItems(id);
	}

	

	/**
	* Returns a range of all the temp ship security port itemses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of temp ship security port itemses
	* @param end the upper bound of the range of temp ship security port itemses (not inclusive)
	* @return the range of temp ship security port itemses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.TempShipSecurityPortItems> getTempShipSecurityPortItemses(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempShipSecurityPortItemses(start, end);
	}

	/**
	* Returns the number of temp ship security port itemses.
	*
	* @return the number of temp ship security port itemses
	* @throws SystemException if a system exception occurred
	*/
	public static int getTempShipSecurityPortItemsesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempShipSecurityPortItemsesCount();
	}

	/**
	* Updates the temp ship security port items in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempShipSecurityPortItems the temp ship security port items
	* @return the temp ship security port items that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempShipSecurityPortItems updateTempShipSecurityPortItems(
		com.fds.nsw.nghiepvu.model.TempShipSecurityPortItems tempShipSecurityPortItems)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .updateTempShipSecurityPortItems(tempShipSecurityPortItems);
	}

	/**
	* Updates the temp ship security port items in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempShipSecurityPortItems the temp ship security port items
	* @param merge whether to merge the temp ship security port items with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the temp ship security port items that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempShipSecurityPortItems updateTempShipSecurityPortItems(
		com.fds.nsw.nghiepvu.model.TempShipSecurityPortItems tempShipSecurityPortItems,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .updateTempShipSecurityPortItems(tempShipSecurityPortItems,
			merge);
	}

	

	

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempShipSecurityPortItems> findByRequestCode(
		java.lang.String requestCode)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().findByRequestCode(requestCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempShipSecurityPortItems> findByRequestCodeOrderByDateArrivalASC(
		java.lang.String requestCode)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().findByRequestCodeOrderByDateArrivalASC(requestCode);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}