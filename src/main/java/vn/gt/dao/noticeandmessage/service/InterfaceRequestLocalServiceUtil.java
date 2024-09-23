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
 * The utility for the interface request local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.InterfaceRequestLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see InterfaceRequestLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.InterfaceRequestLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.InterfaceRequestLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.InterfaceRequestLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class InterfaceRequestLocalServiceUtil {
public InterfaceRequestLocalServiceUtil(InterfaceRequestLocalServiceImpl service) {
InterfaceRequestLocalServiceUtil._service = service;
}
public static InterfaceRequestLocalServiceImpl getService() {
return _service;
}
private static InterfaceRequestLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.InterfaceRequestLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the interface request to the database. Also notifies the appropriate model listeners.
	*
	* @param interfaceRequest the interface request
	* @return the interface request that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.InterfaceRequest addInterfaceRequest(
		com.fds.nsw.nghiepvu.model.InterfaceRequest interfaceRequest)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addInterfaceRequest(interfaceRequest);
	}

	/**
	* Creates a new interface request with the primary key. Does not add the interface request to the database.
	*
	* @param id the primary key for the new interface request
	* @return the new interface request
	*/
	public static com.fds.nsw.nghiepvu.model.InterfaceRequest createInterfaceRequest(
		long id) {
		return getService().createInterfaceRequest(id);
	}

	/**
	* Deletes the interface request with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the interface request
	* @throws PortalException if a interface request with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteInterfaceRequest(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteInterfaceRequest(id);
	}

	/**
	* Deletes the interface request from the database. Also notifies the appropriate model listeners.
	*
	* @param interfaceRequest the interface request
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteInterfaceRequest(
		com.fds.nsw.nghiepvu.model.InterfaceRequest interfaceRequest)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteInterfaceRequest(interfaceRequest);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.InterfaceRequest fetchInterfaceRequest(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchInterfaceRequest(id);
	}

	/**
	* Returns the interface request with the primary key.
	*
	* @param id the primary key of the interface request
	* @return the interface request
	* @throws PortalException if a interface request with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.InterfaceRequest getInterfaceRequest(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getInterfaceRequest(id);
	}

	

	/**
	* Returns a range of all the interface requests.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of interface requests
	* @param end the upper bound of the range of interface requests (not inclusive)
	* @return the range of interface requests
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.InterfaceRequest> getInterfaceRequests(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getInterfaceRequests(start, end);
	}

	/**
	* Returns the number of interface requests.
	*
	* @return the number of interface requests
	* @throws SystemException if a system exception occurred
	*/
	public static int getInterfaceRequestsCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getInterfaceRequestsCount();
	}

	/**
	* Updates the interface request in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param interfaceRequest the interface request
	* @return the interface request that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.InterfaceRequest updateInterfaceRequest(
		com.fds.nsw.nghiepvu.model.InterfaceRequest interfaceRequest)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateInterfaceRequest(interfaceRequest);
	}

	/**
	* Updates the interface request in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param interfaceRequest the interface request
	* @param merge whether to merge the interface request with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the interface request that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.InterfaceRequest updateInterfaceRequest(
		com.fds.nsw.nghiepvu.model.InterfaceRequest interfaceRequest,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateInterfaceRequest(interfaceRequest, merge);
	}

	

	

	public static com.fds.nsw.nghiepvu.model.InterfaceRequest findByRequestCode(
		java.lang.String requestCode) {
		return getService().findByRequestCode(requestCode);
	}

	public static com.fds.nsw.nghiepvu.model.InterfaceRequest findInterfaceRequestByRequestCode(
		java.lang.String requestCode) {
		return getService().findInterfaceRequestByRequestCode(requestCode);
	}

	public static java.util.Date getRequestedDateByRequestCode(
		java.lang.String requestCode) {
		return getService().getRequestedDateByRequestCode(requestCode);
	}

	public static java.lang.String getFunctionTypeByRequestCode(
		java.lang.String requestCode) {
		return getService().getFunctionTypeByRequestCode(requestCode);
	}

	public static java.lang.String getRemarksByRequestCode(
		java.lang.String requestCode) {
		return getService().getRemarksByRequestCode(requestCode);
	}

	public static int updateInterfaceRequest(java.lang.String sql) {
		return getService().updateInterfaceRequest(sql);
	}

	public static com.fds.nsw.nghiepvu.model.InterfaceRequest fetchByF_BY_documentNameRef(
		java.lang.String documentNameRef, java.lang.String businessType) {
		return getService()
				   .fetchByF_BY_documentNameRef(documentNameRef, businessType);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}