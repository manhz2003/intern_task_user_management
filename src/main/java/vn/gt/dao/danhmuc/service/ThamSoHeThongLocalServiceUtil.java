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
// * The utility for the tham so he thong local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.ThamSoHeThongLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
// *
// * <p>
// * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
// * </p>
// *
// * @author win_64
// * @see ThamSoHeThongLocalService
// * @see vn.gt.dao.danhmuc.service.base.ThamSoHeThongLocalServiceBaseImpl
// * @see vn.gt.dao.danhmuc.service.impl.ThamSoHeThongLocalServiceImpl
// * @generated
// */
//import org.springframework.stereotype.Component; @Component public class ThamSoHeThongLocalServiceUtil {
//public ThamSoHeThongLocalServiceUtil(ThamSoHeThongLocalServiceImpl service) {
//ThamSoHeThongLocalServiceUtil._service = service;
//}
//public static ThamSoHeThongLocalServiceImpl getService() {
//return _service;
//}
//private static ThamSoHeThongLocalServiceImpl _service;
//	/*
//	 * NOTE FOR DEVELOPERS:
//	 *
//	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.ThamSoHeThongLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
//	 */
//
//	/**
//	* Adds the tham so he thong to the database. Also notifies the appropriate model listeners.
//	*
//	* @param thamSoHeThong the tham so he thong
//	* @return the tham so he thong that was added
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.ThamSoHeThong addThamSoHeThong(
//		com.fds.nsw.nghiepvu.model.ThamSoHeThong thamSoHeThong)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().addThamSoHeThong(thamSoHeThong);
//	}
//
//	/**
//	* Creates a new tham so he thong with the primary key. Does not add the tham so he thong to the database.
//	*
//	* @param id the primary key for the new tham so he thong
//	* @return the new tham so he thong
//	*/
//	public static com.fds.nsw.nghiepvu.model.ThamSoHeThong createThamSoHeThong(
//		long id) {
//		return getService().createThamSoHeThong(id);
//	}
//
//	/**
//	* Deletes the tham so he thong with the primary key from the database. Also notifies the appropriate model listeners.
//	*
//	* @param id the primary key of the tham so he thong
//	* @throws PortalException if a tham so he thong with the primary key could not be found
//	* @throws SystemException if a system exception occurred
//	*/
//	public static void deleteThamSoHeThong(long id)
//		throws com.fds.nsw.kernel.exception.PortalException,
//			com.fds.nsw.kernel.exception.SystemException {
//		getService().deleteThamSoHeThong(id);
//	}
//
//	/**
//	* Deletes the tham so he thong from the database. Also notifies the appropriate model listeners.
//	*
//	* @param thamSoHeThong the tham so he thong
//	* @throws SystemException if a system exception occurred
//	*/
//	public static void deleteThamSoHeThong(
//		com.fds.nsw.nghiepvu.model.ThamSoHeThong thamSoHeThong)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		getService().deleteThamSoHeThong(thamSoHeThong);
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
//	public static com.fds.nsw.nghiepvu.model.ThamSoHeThong fetchThamSoHeThong(
//		long id) throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().fetchThamSoHeThong(id);
//	}
//
//	/**
//	* Returns the tham so he thong with the primary key.
//	*
//	* @param id the primary key of the tham so he thong
//	* @return the tham so he thong
//	* @throws PortalException if a tham so he thong with the primary key could not be found
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.ThamSoHeThong getThamSoHeThong(
//		long id)
//		throws com.fds.nsw.kernel.exception.PortalException,
//			com.fds.nsw.kernel.exception.SystemException {
//		return getService().getThamSoHeThong(id);
//	}
//
//
//
//	/**
//	* Returns a range of all the tham so he thongs.
//	*
//	* <p>
//	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
//	* </p>
//	*
//	* @param start the lower bound of the range of tham so he thongs
//	* @param end the upper bound of the range of tham so he thongs (not inclusive)
//	* @return the range of tham so he thongs
//	* @throws SystemException if a system exception occurred
//	*/
//	public static java.util.List<com.fds.nsw.nghiepvu.model.ThamSoHeThong> getThamSoHeThongs(
//		int start, int end)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().getThamSoHeThongs(start, end);
//	}
//
//	/**
//	* Returns the number of tham so he thongs.
//	*
//	* @return the number of tham so he thongs
//	* @throws SystemException if a system exception occurred
//	*/
//	public static int getThamSoHeThongsCount()
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().getThamSoHeThongsCount();
//	}
//
//	/**
//	* Updates the tham so he thong in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
//	*
//	* @param thamSoHeThong the tham so he thong
//	* @return the tham so he thong that was updated
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.ThamSoHeThong updateThamSoHeThong(
//		com.fds.nsw.nghiepvu.model.ThamSoHeThong thamSoHeThong)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().updateThamSoHeThong(thamSoHeThong);
//	}
//
//	/**
//	* Updates the tham so he thong in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
//	*
//	* @param thamSoHeThong the tham so he thong
//	* @param merge whether to merge the tham so he thong with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
//	* @return the tham so he thong that was updated
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.ThamSoHeThong updateThamSoHeThong(
//		com.fds.nsw.nghiepvu.model.ThamSoHeThong thamSoHeThong, boolean merge)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().updateThamSoHeThong(thamSoHeThong, merge);
//	}
//
//
//
//
//
//	public static com.fds.nsw.nghiepvu.model.ThamSoHeThong findByKeyData(
//		java.lang.String keyData) {
//		return getService().findByKeyData(keyData);
//	}
//
//	public static com.fds.nsw.nghiepvu.model.ThamSoHeThong findByKeyDataAndDescription(
//		java.lang.String keyData, java.lang.String description) {
//		return getService().findByKeyDataAndDescription(keyData, description);
//	}
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