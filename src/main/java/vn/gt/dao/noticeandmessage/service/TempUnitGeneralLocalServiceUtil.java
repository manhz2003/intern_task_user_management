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
//package vn.gt.dao.noticeandmessage.service;
//
//
//
//
//
//
///**
// * The utility for the temp unit general local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.TempUnitGeneralLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
// *
// * <p>
// * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
// * </p>
// *
// * @author win_64
// * @see TempUnitGeneralLocalService
// * @see vn.gt.dao.noticeandmessage.service.base.TempUnitGeneralLocalServiceBaseImpl
// * @see vn.gt.dao.noticeandmessage.service.impl.TempUnitGeneralLocalServiceImpl
// * @generated
// */
//import org.springframework.stereotype.Component; @Component public class TempUnitGeneralLocalServiceUtil {
//public TempUnitGeneralLocalServiceUtil(TempUnitGeneralLocalServiceImpl service) {
//TempUnitGeneralLocalServiceUtil._service = service;
//}
//public static TempUnitGeneralLocalServiceImpl getService() {
//return _service;
//}
//private static TempUnitGeneralLocalServiceImpl _service;
//	/*
//	 * NOTE FOR DEVELOPERS:
//	 *
//	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.TempUnitGeneralLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
//	 */
//
//	/**
//	* Adds the temp unit general to the database. Also notifies the appropriate model listeners.
//	*
//	* @param tempUnitGeneral the temp unit general
//	* @return the temp unit general that was added
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.TempUnitGeneral addTempUnitGeneral(
//		com.fds.nsw.nghiepvu.model.TempUnitGeneral tempUnitGeneral)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().addTempUnitGeneral(tempUnitGeneral);
//	}
//
//	/**
//	* Creates a new temp unit general with the primary key. Does not add the temp unit general to the database.
//	*
//	* @param id the primary key for the new temp unit general
//	* @return the new temp unit general
//	*/
//	public static com.fds.nsw.nghiepvu.model.TempUnitGeneral createTempUnitGeneral(
//		long id) {
//		return getService().createTempUnitGeneral(id);
//	}
//
//	/**
//	* Deletes the temp unit general with the primary key from the database. Also notifies the appropriate model listeners.
//	*
//	* @param id the primary key of the temp unit general
//	* @throws PortalException if a temp unit general with the primary key could not be found
//	* @throws SystemException if a system exception occurred
//	*/
//	public static void deleteTempUnitGeneral(long id)
//		throws com.fds.nsw.kernel.exception.PortalException,
//			com.fds.nsw.kernel.exception.SystemException {
//		getService().deleteTempUnitGeneral(id);
//	}
//
//	/**
//	* Deletes the temp unit general from the database. Also notifies the appropriate model listeners.
//	*
//	* @param tempUnitGeneral the temp unit general
//	* @throws SystemException if a system exception occurred
//	*/
//	public static void deleteTempUnitGeneral(
//		com.fds.nsw.nghiepvu.model.TempUnitGeneral tempUnitGeneral)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		getService().deleteTempUnitGeneral(tempUnitGeneral);
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
//	public static com.fds.nsw.nghiepvu.model.TempUnitGeneral fetchTempUnitGeneral(
//		long id) throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().fetchTempUnitGeneral(id);
//	}
//
//	/**
//	* Returns the temp unit general with the primary key.
//	*
//	* @param id the primary key of the temp unit general
//	* @return the temp unit general
//	* @throws PortalException if a temp unit general with the primary key could not be found
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.TempUnitGeneral getTempUnitGeneral(
//		long id)
//		throws com.fds.nsw.kernel.exception.PortalException,
//			com.fds.nsw.kernel.exception.SystemException {
//		return getService().getTempUnitGeneral(id);
//	}
//
//
//
//	/**
//	* Returns a range of all the temp unit generals.
//	*
//	* <p>
//	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
//	* </p>
//	*
//	* @param start the lower bound of the range of temp unit generals
//	* @param end the upper bound of the range of temp unit generals (not inclusive)
//	* @return the range of temp unit generals
//	* @throws SystemException if a system exception occurred
//	*/
//	public static java.util.List<com.fds.nsw.nghiepvu.model.TempUnitGeneral> getTempUnitGenerals(
//		int start, int end)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().getTempUnitGenerals(start, end);
//	}
//
//	/**
//	* Returns the number of temp unit generals.
//	*
//	* @return the number of temp unit generals
//	* @throws SystemException if a system exception occurred
//	*/
//	public static int getTempUnitGeneralsCount()
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().getTempUnitGeneralsCount();
//	}
//
//	/**
//	* Updates the temp unit general in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
//	*
//	* @param tempUnitGeneral the temp unit general
//	* @return the temp unit general that was updated
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.TempUnitGeneral updateTempUnitGeneral(
//		com.fds.nsw.nghiepvu.model.TempUnitGeneral tempUnitGeneral)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().updateTempUnitGeneral(tempUnitGeneral);
//	}
//
//	/**
//	* Updates the temp unit general in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
//	*
//	* @param tempUnitGeneral the temp unit general
//	* @param merge whether to merge the temp unit general with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
//	* @return the temp unit general that was updated
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.TempUnitGeneral updateTempUnitGeneral(
//		com.fds.nsw.nghiepvu.model.TempUnitGeneral tempUnitGeneral,
//		boolean merge)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().updateTempUnitGeneral(tempUnitGeneral, merge);
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