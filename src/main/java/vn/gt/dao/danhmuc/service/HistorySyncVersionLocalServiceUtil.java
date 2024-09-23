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
 * The utility for the history sync version local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.HistorySyncVersionLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see HistorySyncVersionLocalService
 * @see vn.gt.dao.danhmuc.service.base.HistorySyncVersionLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.HistorySyncVersionLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.HistorySyncVersionLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class HistorySyncVersionLocalServiceUtil {
public HistorySyncVersionLocalServiceUtil(HistorySyncVersionLocalServiceImpl service) {
HistorySyncVersionLocalServiceUtil._service = service;
}
public static HistorySyncVersionLocalServiceImpl getService() {
return _service;
}
private static HistorySyncVersionLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.HistorySyncVersionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the history sync version to the database. Also notifies the appropriate model listeners.
	*
	* @param historySyncVersion the history sync version
	* @return the history sync version that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.HistorySyncVersion addHistorySyncVersion(
		com.fds.nsw.nghiepvu.model.HistorySyncVersion historySyncVersion)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addHistorySyncVersion(historySyncVersion);
	}

	/**
	* Creates a new history sync version with the primary key. Does not add the history sync version to the database.
	*
	* @param id the primary key for the new history sync version
	* @return the new history sync version
	*/
	public static com.fds.nsw.nghiepvu.model.HistorySyncVersion createHistorySyncVersion(
		long id) {
		return getService().createHistorySyncVersion(id);
	}

	/**
	* Deletes the history sync version with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the history sync version
	* @throws PortalException if a history sync version with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteHistorySyncVersion(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteHistorySyncVersion(id);
	}

	/**
	* Deletes the history sync version from the database. Also notifies the appropriate model listeners.
	*
	* @param historySyncVersion the history sync version
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteHistorySyncVersion(
		com.fds.nsw.nghiepvu.model.HistorySyncVersion historySyncVersion)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteHistorySyncVersion(historySyncVersion);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.HistorySyncVersion fetchHistorySyncVersion(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchHistorySyncVersion(id);
	}

	/**
	* Returns the history sync version with the primary key.
	*
	* @param id the primary key of the history sync version
	* @return the history sync version
	* @throws PortalException if a history sync version with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.HistorySyncVersion getHistorySyncVersion(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getHistorySyncVersion(id);
	}

	

	/**
	* Returns a range of all the history sync versions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of history sync versions
	* @param end the upper bound of the range of history sync versions (not inclusive)
	* @return the range of history sync versions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.HistorySyncVersion> getHistorySyncVersions(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getHistorySyncVersions(start, end);
	}

	/**
	* Returns the number of history sync versions.
	*
	* @return the number of history sync versions
	* @throws SystemException if a system exception occurred
	*/
	public static int getHistorySyncVersionsCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getHistorySyncVersionsCount();
	}

	/**
	* Updates the history sync version in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param historySyncVersion the history sync version
	* @return the history sync version that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.HistorySyncVersion updateHistorySyncVersion(
		com.fds.nsw.nghiepvu.model.HistorySyncVersion historySyncVersion)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateHistorySyncVersion(historySyncVersion);
	}

	/**
	* Updates the history sync version in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param historySyncVersion the history sync version
	* @param merge whether to merge the history sync version with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the history sync version that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.HistorySyncVersion updateHistorySyncVersion(
		com.fds.nsw.nghiepvu.model.HistorySyncVersion historySyncVersion,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateHistorySyncVersion(historySyncVersion, merge);
	}

	

	

	public static java.lang.String getsyncVersion(
		java.util.Date requestedDate, java.lang.String categoryID)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getsyncVersion(requestedDate, categoryID);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}