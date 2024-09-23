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
//package vn.gt.dao.nhapcanh.service;
//
//
//
//
//
//
///**
// * The utility for the response bo local service. This utility wraps {@link vn.gt.dao.nhapcanh.service.impl.ResponseBoLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
// *
// * <p>
// * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
// * </p>
// *
// * @author win_64
// * @see ResponseBoLocalService
// * @see vn.gt.dao.nhapcanh.service.base.ResponseBoLocalServiceBaseImpl
// * @see vn.gt.dao.nhapcanh.service.impl.ResponseBoLocalServiceImpl
// * @generated
// */
//import org.springframework.stereotype.Component; @Component public class ResponseBoLocalServiceUtil {
//public ResponseBoLocalServiceUtil(ResponseBoLocalServiceImpl service) {
//ResponseBoLocalServiceUtil._service = service;
//}
//public static ResponseBoLocalServiceImpl getService() {
//return _service;
//}
//private static ResponseBoLocalServiceImpl _service;
//	/*
//	 * NOTE FOR DEVELOPERS:
//	 *
//	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.nhapcanh.service.impl.ResponseBoLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
//	 */
//
//	/**
//	* Adds the response bo to the database. Also notifies the appropriate model listeners.
//	*
//	* @param responseBo the response bo
//	* @return the response bo that was added
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.ResponseBo addResponseBo(
//		com.fds.nsw.nghiepvu.model.ResponseBo responseBo)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().addResponseBo(responseBo);
//	}
//
//	/**
//	* Creates a new response bo with the primary key. Does not add the response bo to the database.
//	*
//	* @param id the primary key for the new response bo
//	* @return the new response bo
//	*/
//	public static com.fds.nsw.nghiepvu.model.ResponseBo createResponseBo(long id) {
//		return getService().createResponseBo(id);
//	}
//
//	/**
//	* Deletes the response bo with the primary key from the database. Also notifies the appropriate model listeners.
//	*
//	* @param id the primary key of the response bo
//	* @throws PortalException if a response bo with the primary key could not be found
//	* @throws SystemException if a system exception occurred
//	*/
//	public static void deleteResponseBo(long id)
//		throws com.fds.nsw.kernel.exception.PortalException,
//			com.fds.nsw.kernel.exception.SystemException {
//		getService().deleteResponseBo(id);
//	}
//
//	/**
//	* Deletes the response bo from the database. Also notifies the appropriate model listeners.
//	*
//	* @param responseBo the response bo
//	* @throws SystemException if a system exception occurred
//	*/
//	public static void deleteResponseBo(
//		com.fds.nsw.nghiepvu.model.ResponseBo responseBo)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		getService().deleteResponseBo(responseBo);
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
//	public static com.fds.nsw.nghiepvu.model.ResponseBo fetchResponseBo(long id)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().fetchResponseBo(id);
//	}
//
//	/**
//	* Returns the response bo with the primary key.
//	*
//	* @param id the primary key of the response bo
//	* @return the response bo
//	* @throws PortalException if a response bo with the primary key could not be found
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.ResponseBo getResponseBo(long id)
//		throws com.fds.nsw.kernel.exception.PortalException,
//			com.fds.nsw.kernel.exception.SystemException {
//		return getService().getResponseBo(id);
//	}
//
//
//
//	/**
//	* Returns a range of all the response bos.
//	*
//	* <p>
//	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
//	* </p>
//	*
//	* @param start the lower bound of the range of response bos
//	* @param end the upper bound of the range of response bos (not inclusive)
//	* @return the range of response bos
//	* @throws SystemException if a system exception occurred
//	*/
//	public static java.util.List<com.fds.nsw.nghiepvu.model.ResponseBo> getResponseBos(
//		int start, int end)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().getResponseBos(start, end);
//	}
//
//	/**
//	* Returns the number of response bos.
//	*
//	* @return the number of response bos
//	* @throws SystemException if a system exception occurred
//	*/
//	public static int getResponseBosCount()
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().getResponseBosCount();
//	}
//
//	/**
//	* Updates the response bo in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
//	*
//	* @param responseBo the response bo
//	* @return the response bo that was updated
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.ResponseBo updateResponseBo(
//		com.fds.nsw.nghiepvu.model.ResponseBo responseBo)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().updateResponseBo(responseBo);
//	}
//
//	/**
//	* Updates the response bo in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
//	*
//	* @param responseBo the response bo
//	* @param merge whether to merge the response bo with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
//	* @return the response bo that was updated
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.ResponseBo updateResponseBo(
//		com.fds.nsw.nghiepvu.model.ResponseBo responseBo, boolean merge)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().updateResponseBo(responseBo, merge);
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
//
//
//
//
//}