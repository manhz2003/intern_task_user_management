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
// * The utility for the history rmdc ship local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.HistoryRmdcShipLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
// *
// * <p>
// * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
// * </p>
// *
// * @author win_64
// * @see HistoryRmdcShipLocalService
// * @see vn.gt.dao.danhmuc.service.base.HistoryRmdcShipLocalServiceBaseImpl
// * @see vn.gt.dao.danhmuc.service.impl.HistoryRmdcShipLocalServiceImpl
// * @generated
// */
//import org.springframework.stereotype.Component; @Component public class HistoryRmdcShipLocalServiceUtil {
//public HistoryRmdcShipLocalServiceUtil(HistoryRmdcShipLocalServiceImpl service) {
//HistoryRmdcShipLocalServiceUtil._service = service;
//}
//public static HistoryRmdcShipLocalServiceImpl getService() {
//return _service;
//}
//private static HistoryRmdcShipLocalServiceImpl _service;
//	/*
//	 * NOTE FOR DEVELOPERS:
//	 *
//	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.HistoryRmdcShipLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
//	 */
//
//	/**
//	* Adds the history rmdc ship to the database. Also notifies the appropriate model listeners.
//	*
//	* @param historyRmdcShip the history rmdc ship
//	* @return the history rmdc ship that was added
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.HistoryRmdcShip addHistoryRmdcShip(
//		com.fds.nsw.nghiepvu.model.HistoryRmdcShip historyRmdcShip)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().addHistoryRmdcShip(historyRmdcShip);
//	}
//
//	/**
//	* Creates a new history rmdc ship with the primary key. Does not add the history rmdc ship to the database.
//	*
//	* @param id the primary key for the new history rmdc ship
//	* @return the new history rmdc ship
//	*/
//	public static com.fds.nsw.nghiepvu.model.HistoryRmdcShip createHistoryRmdcShip(
//		long id) {
//		return getService().createHistoryRmdcShip(id);
//	}
//
//	/**
//	* Deletes the history rmdc ship with the primary key from the database. Also notifies the appropriate model listeners.
//	*
//	* @param id the primary key of the history rmdc ship
//	* @throws PortalException if a history rmdc ship with the primary key could not be found
//	* @throws SystemException if a system exception occurred
//	*/
//	public static void deleteHistoryRmdcShip(long id)
//		throws com.fds.nsw.kernel.exception.PortalException,
//			com.fds.nsw.kernel.exception.SystemException {
//		getService().deleteHistoryRmdcShip(id);
//	}
//
//	/**
//	* Deletes the history rmdc ship from the database. Also notifies the appropriate model listeners.
//	*
//	* @param historyRmdcShip the history rmdc ship
//	* @throws SystemException if a system exception occurred
//	*/
//	public static void deleteHistoryRmdcShip(
//		com.fds.nsw.nghiepvu.model.HistoryRmdcShip historyRmdcShip)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		getService().deleteHistoryRmdcShip(historyRmdcShip);
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
//	public static com.fds.nsw.nghiepvu.model.HistoryRmdcShip fetchHistoryRmdcShip(
//		long id) throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().fetchHistoryRmdcShip(id);
//	}
//
//	/**
//	* Returns the history rmdc ship with the primary key.
//	*
//	* @param id the primary key of the history rmdc ship
//	* @return the history rmdc ship
//	* @throws PortalException if a history rmdc ship with the primary key could not be found
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.HistoryRmdcShip getHistoryRmdcShip(
//		long id)
//		throws com.fds.nsw.kernel.exception.PortalException,
//			com.fds.nsw.kernel.exception.SystemException {
//		return getService().getHistoryRmdcShip(id);
//	}
//
//
//
//	/**
//	* Returns a range of all the history rmdc ships.
//	*
//	* <p>
//	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
//	* </p>
//	*
//	* @param start the lower bound of the range of history rmdc ships
//	* @param end the upper bound of the range of history rmdc ships (not inclusive)
//	* @return the range of history rmdc ships
//	* @throws SystemException if a system exception occurred
//	*/
//	public static java.util.List<com.fds.nsw.nghiepvu.model.HistoryRmdcShip> getHistoryRmdcShips(
//		int start, int end)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().getHistoryRmdcShips(start, end);
//	}
//
//	/**
//	* Returns the number of history rmdc ships.
//	*
//	* @return the number of history rmdc ships
//	* @throws SystemException if a system exception occurred
//	*/
//	public static int getHistoryRmdcShipsCount()
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().getHistoryRmdcShipsCount();
//	}
//
//	/**
//	* Updates the history rmdc ship in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
//	*
//	* @param historyRmdcShip the history rmdc ship
//	* @return the history rmdc ship that was updated
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.HistoryRmdcShip updateHistoryRmdcShip(
//		com.fds.nsw.nghiepvu.model.HistoryRmdcShip historyRmdcShip)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().updateHistoryRmdcShip(historyRmdcShip);
//	}
//
//	/**
//	* Updates the history rmdc ship in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
//	*
//	* @param historyRmdcShip the history rmdc ship
//	* @param merge whether to merge the history rmdc ship with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
//	* @return the history rmdc ship that was updated
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.HistoryRmdcShip updateHistoryRmdcShip(
//		com.fds.nsw.nghiepvu.model.HistoryRmdcShip historyRmdcShip, boolean merge)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().updateHistoryRmdcShip(historyRmdcShip, merge);
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