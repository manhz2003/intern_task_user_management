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
 * The utility for the temp cargo items local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.TempCargoItemsLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see TempCargoItemsLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.TempCargoItemsLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.TempCargoItemsLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.TempCargoItemsLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class TempCargoItemsLocalServiceUtil {
public TempCargoItemsLocalServiceUtil(TempCargoItemsLocalServiceImpl service) {
TempCargoItemsLocalServiceUtil._service = service;
}
public static TempCargoItemsLocalServiceImpl getService() {
return _service;
}
private static TempCargoItemsLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.TempCargoItemsLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the temp cargo items to the database. Also notifies the appropriate model listeners.
	*
	* @param tempCargoItems the temp cargo items
	* @return the temp cargo items that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempCargoItems addTempCargoItems(
		com.fds.nsw.nghiepvu.model.TempCargoItems tempCargoItems)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addTempCargoItems(tempCargoItems);
	}

	/**
	* Creates a new temp cargo items with the primary key. Does not add the temp cargo items to the database.
	*
	* @param id the primary key for the new temp cargo items
	* @return the new temp cargo items
	*/
	public static com.fds.nsw.nghiepvu.model.TempCargoItems createTempCargoItems(
		long id) {
		return getService().createTempCargoItems(id);
	}

	/**
	* Deletes the temp cargo items with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the temp cargo items
	* @throws PortalException if a temp cargo items with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempCargoItems(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempCargoItems(id);
	}

	/**
	* Deletes the temp cargo items from the database. Also notifies the appropriate model listeners.
	*
	* @param tempCargoItems the temp cargo items
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempCargoItems(
		com.fds.nsw.nghiepvu.model.TempCargoItems tempCargoItems)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempCargoItems(tempCargoItems);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.TempCargoItems fetchTempCargoItems(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchTempCargoItems(id);
	}

	/**
	* Returns the temp cargo items with the primary key.
	*
	* @param id the primary key of the temp cargo items
	* @return the temp cargo items
	* @throws PortalException if a temp cargo items with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempCargoItems getTempCargoItems(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempCargoItems(id);
	}

	

	/**
	* Returns a range of all the temp cargo itemses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of temp cargo itemses
	* @param end the upper bound of the range of temp cargo itemses (not inclusive)
	* @return the range of temp cargo itemses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.TempCargoItems> getTempCargoItemses(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempCargoItemses(start, end);
	}

	/**
	* Returns the number of temp cargo itemses.
	*
	* @return the number of temp cargo itemses
	* @throws SystemException if a system exception occurred
	*/
	public static int getTempCargoItemsesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempCargoItemsesCount();
	}

	/**
	* Updates the temp cargo items in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempCargoItems the temp cargo items
	* @return the temp cargo items that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempCargoItems updateTempCargoItems(
		com.fds.nsw.nghiepvu.model.TempCargoItems tempCargoItems)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateTempCargoItems(tempCargoItems);
	}

	/**
	* Updates the temp cargo items in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempCargoItems the temp cargo items
	* @param merge whether to merge the temp cargo items with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the temp cargo items that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempCargoItems updateTempCargoItems(
		com.fds.nsw.nghiepvu.model.TempCargoItems tempCargoItems,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateTempCargoItems(tempCargoItems, merge);
	}

	

	

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempCargoItems> findByRequestCode(
		java.lang.String requestCode) {
		return getService().findByRequestCode(requestCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempCargoItems> findBydocumentNameAnddocumentYearAndRequestCode(
		long documentName, int documentYear, java.lang.String requestCode) {
		return getService()
				   .findBydocumentNameAnddocumentYearAndRequestCode(documentName,
			documentYear, requestCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempCargoItems> findBydocumentNameAnddocumentYear(
		long documentName, int documentYear) {
		return getService()
				   .findBydocumentNameAnddocumentYear(documentName, documentYear);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}