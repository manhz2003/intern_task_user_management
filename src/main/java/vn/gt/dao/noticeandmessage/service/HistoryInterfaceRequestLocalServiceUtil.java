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
 * The utility for the history interface request local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.HistoryInterfaceRequestLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see HistoryInterfaceRequestLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.HistoryInterfaceRequestLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.HistoryInterfaceRequestLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.HistoryInterfaceRequestLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class HistoryInterfaceRequestLocalServiceUtil {
public HistoryInterfaceRequestLocalServiceUtil(HistoryInterfaceRequestLocalServiceImpl service) {
HistoryInterfaceRequestLocalServiceUtil._service = service;
}
public static HistoryInterfaceRequestLocalServiceImpl getService() {
return _service;
}
private static HistoryInterfaceRequestLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.HistoryInterfaceRequestLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the history interface request to the database. Also notifies the appropriate model listeners.
	*
	* @param historyInterfaceRequest the history interface request
	* @return the history interface request that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.HistoryInterfaceRequest addHistoryInterfaceRequest(
		com.fds.nsw.nghiepvu.model.HistoryInterfaceRequest historyInterfaceRequest)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addHistoryInterfaceRequest(historyInterfaceRequest);
	}

	/**
	* Creates a new history interface request with the primary key. Does not add the history interface request to the database.
	*
	* @param id the primary key for the new history interface request
	* @return the new history interface request
	*/
	public static com.fds.nsw.nghiepvu.model.HistoryInterfaceRequest createHistoryInterfaceRequest(
		long id) {
		return getService().createHistoryInterfaceRequest(id);
	}

	/**
	* Deletes the history interface request with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the history interface request
	* @throws PortalException if a history interface request with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteHistoryInterfaceRequest(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteHistoryInterfaceRequest(id);
	}

	/**
	* Deletes the history interface request from the database. Also notifies the appropriate model listeners.
	*
	* @param historyInterfaceRequest the history interface request
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteHistoryInterfaceRequest(
		com.fds.nsw.nghiepvu.model.HistoryInterfaceRequest historyInterfaceRequest)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteHistoryInterfaceRequest(historyInterfaceRequest);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.HistoryInterfaceRequest fetchHistoryInterfaceRequest(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchHistoryInterfaceRequest(id);
	}

	/**
	* Returns the history interface request with the primary key.
	*
	* @param id the primary key of the history interface request
	* @return the history interface request
	* @throws PortalException if a history interface request with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.HistoryInterfaceRequest getHistoryInterfaceRequest(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getHistoryInterfaceRequest(id);
	}

	

	/**
	* Returns a range of all the history interface requests.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of history interface requests
	* @param end the upper bound of the range of history interface requests (not inclusive)
	* @return the range of history interface requests
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.HistoryInterfaceRequest> getHistoryInterfaceRequests(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getHistoryInterfaceRequests(start, end);
	}

	/**
	* Returns the number of history interface requests.
	*
	* @return the number of history interface requests
	* @throws SystemException if a system exception occurred
	*/
	public static int getHistoryInterfaceRequestsCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getHistoryInterfaceRequestsCount();
	}

	/**
	* Updates the history interface request in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param historyInterfaceRequest the history interface request
	* @return the history interface request that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.HistoryInterfaceRequest updateHistoryInterfaceRequest(
		com.fds.nsw.nghiepvu.model.HistoryInterfaceRequest historyInterfaceRequest)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .updateHistoryInterfaceRequest(historyInterfaceRequest);
	}

	/**
	* Updates the history interface request in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param historyInterfaceRequest the history interface request
	* @param merge whether to merge the history interface request with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the history interface request that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.HistoryInterfaceRequest updateHistoryInterfaceRequest(
		com.fds.nsw.nghiepvu.model.HistoryInterfaceRequest historyInterfaceRequest,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .updateHistoryInterfaceRequest(historyInterfaceRequest, merge);
	}

	

	

	public static com.fds.nsw.nghiepvu.model.HistoryInterfaceRequest findByRequestCode(
		java.lang.String requestCode) {
		return getService().findByRequestCode(requestCode);
	}

	public static com.fds.nsw.nghiepvu.model.HistoryInterfaceRequest findHistoryInterfaceRequestByRequestCode(
		java.lang.String requestCode) {
		return getService().findHistoryInterfaceRequestByRequestCode(requestCode);
	}

	public static int updateHistoryInterfaceRequest(java.lang.String sql) {
		return getService().updateHistoryInterfaceRequest(sql);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}