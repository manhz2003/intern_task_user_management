///**
// * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
// *
// * This library is free software; you can redistribute it and/or modify it under
// * the terms of the GNU Lesser General Public License as published by the Free
// * Software Foundation; either version 2.1 of the License, or (at your option)
// * any later version.
// *
// * This library is distributed in the hope that it will be useful, but WITHOUT
// * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
// * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
// * details.
// */
//
//package vn.gt.dao.danhmuc.service;
//
//
//
//
//
//
///**
// * The utility for the dm test n01 request local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmTestN01RequestLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
// *
// * <p>
// * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
// * </p>
// *
// * @author win_64
// * @see DmTestN01RequestLocalService
// * @see vn.gt.dao.danhmuc.service.base.DmTestN01RequestLocalServiceBaseImpl
// * @see vn.gt.dao.danhmuc.service.impl.DmTestN01RequestLocalServiceImpl
// * @generated
// */
//import org.springframework.stereotype.Component; @Component public class DmTestN01RequestLocalServiceUtil {
//public DmTestN01RequestLocalServiceUtil(DmTestN01RequestLocalServiceImpl service) {
//DmTestN01RequestLocalServiceUtil._service = service;
//}
//public static DmTestN01RequestLocalServiceImpl getService() {
//return _service;
//}
//private static DmTestN01RequestLocalServiceImpl _service;
//	/*
//	 * NOTE FOR DEVELOPERS:
//	 *
//	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmTestN01RequestLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
//	 */
//
//	/**
//	* Adds the dm test n01 request to the database. Also notifies the appropriate model listeners.
//	*
//	* @param dmTestN01Request the dm test n01 request
//	* @return the dm test n01 request that was added
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.DmTestN01Request addDmTestN01Request(
//		com.fds.nsw.nghiepvu.model.DmTestN01Request dmTestN01Request)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().addDmTestN01Request(dmTestN01Request);
//	}
//
//	/**
//	* Creates a new dm test n01 request with the primary key. Does not add the dm test n01 request to the database.
//	*
//	* @param requestID the primary key for the new dm test n01 request
//	* @return the new dm test n01 request
//	*/
//	public static com.fds.nsw.nghiepvu.model.DmTestN01Request createDmTestN01Request(
//		int requestID) {
//		return getService().createDmTestN01Request(requestID);
//	}
//
//	/**
//	* Deletes the dm test n01 request with the primary key from the database. Also notifies the appropriate model listeners.
//	*
//	* @param requestID the primary key of the dm test n01 request
//	* @throws PortalException if a dm test n01 request with the primary key could not be found
//	* @throws SystemException if a system exception occurred
//	*/
//	public static void deleteDmTestN01Request(int requestID)
//		throws com.fds.nsw.kernel.exception.PortalException,
//			com.fds.nsw.kernel.exception.SystemException {
//		getService().deleteDmTestN01Request(requestID);
//	}
//
//	/**
//	* Deletes the dm test n01 request from the database. Also notifies the appropriate model listeners.
//	*
//	* @param dmTestN01Request the dm test n01 request
//	* @throws SystemException if a system exception occurred
//	*/
//	public static void deleteDmTestN01Request(
//		com.fds.nsw.nghiepvu.model.DmTestN01Request dmTestN01Request)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		getService().deleteDmTestN01Request(dmTestN01Request);
//	}
//
//
//
//
//
//
//
//
//
//
//	public static com.fds.nsw.nghiepvu.model.DmTestN01Request fetchDmTestN01Request(
//		int requestID)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().fetchDmTestN01Request(requestID);
//	}
//
//	/**
//	* Returns the dm test n01 request with the primary key.
//	*
//	* @param requestID the primary key of the dm test n01 request
//	* @return the dm test n01 request
//	* @throws PortalException if a dm test n01 request with the primary key could not be found
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.DmTestN01Request getDmTestN01Request(
//		int requestID)
//		throws com.fds.nsw.kernel.exception.PortalException,
//			com.fds.nsw.kernel.exception.SystemException {
//		return getService().getDmTestN01Request(requestID);
//	}
//
//
//
//	/**
//	* Returns a range of all the dm test n01 requests.
//	*
//	* <p>
//	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
//	* </p>
//	*
//	* @param start the lower bound of the range of dm test n01 requests
//	* @param end the upper bound of the range of dm test n01 requests (not inclusive)
//	* @return the range of dm test n01 requests
//	* @throws SystemException if a system exception occurred
//	*/
//	public static java.util.List<com.fds.nsw.nghiepvu.model.DmTestN01Request> getDmTestN01Requests(
//		int start, int end)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().getDmTestN01Requests(start, end);
//	}
//
//	/**
//	* Returns the number of dm test n01 requests.
//	*
//	* @return the number of dm test n01 requests
//	* @throws SystemException if a system exception occurred
//	*/
//	public static int getDmTestN01RequestsCount()
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().getDmTestN01RequestsCount();
//	}
//
//	/**
//	* Updates the dm test n01 request in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
//	*
//	* @param dmTestN01Request the dm test n01 request
//	* @return the dm test n01 request that was updated
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.DmTestN01Request updateDmTestN01Request(
//		com.fds.nsw.nghiepvu.model.DmTestN01Request dmTestN01Request)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().updateDmTestN01Request(dmTestN01Request);
//	}
//
//	/**
//	* Updates the dm test n01 request in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
//	*
//	* @param dmTestN01Request the dm test n01 request
//	* @param merge whether to merge the dm test n01 request with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
//	* @return the dm test n01 request that was updated
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.DmTestN01Request updateDmTestN01Request(
//		com.fds.nsw.nghiepvu.model.DmTestN01Request dmTestN01Request, boolean merge)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().updateDmTestN01Request(dmTestN01Request, merge);
//	}
//
//
//
//
//
//	public static void clearService() {
//		_service = null;
//	}
//
//
//}