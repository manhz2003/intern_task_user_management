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
 * The utility for the temp crew effects items local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.TempCrewEffectsItemsLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see TempCrewEffectsItemsLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.TempCrewEffectsItemsLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.TempCrewEffectsItemsLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.TempCrewEffectsItemsLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class TempCrewEffectsItemsLocalServiceUtil {
public TempCrewEffectsItemsLocalServiceUtil(TempCrewEffectsItemsLocalServiceImpl service) {
TempCrewEffectsItemsLocalServiceUtil._service = service;
}
public static TempCrewEffectsItemsLocalServiceImpl getService() {
return _service;
}
private static TempCrewEffectsItemsLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.TempCrewEffectsItemsLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the temp crew effects items to the database. Also notifies the appropriate model listeners.
	*
	* @param tempCrewEffectsItems the temp crew effects items
	* @return the temp crew effects items that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempCrewEffectsItems addTempCrewEffectsItems(
		com.fds.nsw.nghiepvu.model.TempCrewEffectsItems tempCrewEffectsItems)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addTempCrewEffectsItems(tempCrewEffectsItems);
	}

	/**
	* Creates a new temp crew effects items with the primary key. Does not add the temp crew effects items to the database.
	*
	* @param id the primary key for the new temp crew effects items
	* @return the new temp crew effects items
	*/
	public static com.fds.nsw.nghiepvu.model.TempCrewEffectsItems createTempCrewEffectsItems(
		long id) {
		return getService().createTempCrewEffectsItems(id);
	}

	/**
	* Deletes the temp crew effects items with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the temp crew effects items
	* @throws PortalException if a temp crew effects items with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempCrewEffectsItems(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempCrewEffectsItems(id);
	}

	/**
	* Deletes the temp crew effects items from the database. Also notifies the appropriate model listeners.
	*
	* @param tempCrewEffectsItems the temp crew effects items
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempCrewEffectsItems(
		com.fds.nsw.nghiepvu.model.TempCrewEffectsItems tempCrewEffectsItems)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempCrewEffectsItems(tempCrewEffectsItems);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.TempCrewEffectsItems fetchTempCrewEffectsItems(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchTempCrewEffectsItems(id);
	}

	/**
	* Returns the temp crew effects items with the primary key.
	*
	* @param id the primary key of the temp crew effects items
	* @return the temp crew effects items
	* @throws PortalException if a temp crew effects items with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempCrewEffectsItems getTempCrewEffectsItems(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempCrewEffectsItems(id);
	}

	

	/**
	* Returns a range of all the temp crew effects itemses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of temp crew effects itemses
	* @param end the upper bound of the range of temp crew effects itemses (not inclusive)
	* @return the range of temp crew effects itemses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.TempCrewEffectsItems> getTempCrewEffectsItemses(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempCrewEffectsItemses(start, end);
	}

	/**
	* Returns the number of temp crew effects itemses.
	*
	* @return the number of temp crew effects itemses
	* @throws SystemException if a system exception occurred
	*/
	public static int getTempCrewEffectsItemsesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempCrewEffectsItemsesCount();
	}

	/**
	* Updates the temp crew effects items in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempCrewEffectsItems the temp crew effects items
	* @return the temp crew effects items that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempCrewEffectsItems updateTempCrewEffectsItems(
		com.fds.nsw.nghiepvu.model.TempCrewEffectsItems tempCrewEffectsItems)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateTempCrewEffectsItems(tempCrewEffectsItems);
	}

	/**
	* Updates the temp crew effects items in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempCrewEffectsItems the temp crew effects items
	* @param merge whether to merge the temp crew effects items with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the temp crew effects items that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempCrewEffectsItems updateTempCrewEffectsItems(
		com.fds.nsw.nghiepvu.model.TempCrewEffectsItems tempCrewEffectsItems,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .updateTempCrewEffectsItems(tempCrewEffectsItems, merge);
	}

	

	

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempCrewEffectsItems> findByRequestCode(
		java.lang.String requestCode)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().findByRequestCode(requestCode);
	}

	public static com.fds.nsw.nghiepvu.model.TempCrewEffectsItems findTempCrewEffectsItemsByRequestCode(
		java.lang.String requestCode) {
		return getService().findTempCrewEffectsItemsByRequestCode(requestCode);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}