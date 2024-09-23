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
// * The utility for the rmdc ship local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.RmdcShipLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
// *
// * <p>
// * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
// * </p>
// *
// * @author win_64
// * @see RmdcShipLocalService
// * @see vn.gt.dao.danhmuc.service.base.RmdcShipLocalServiceBaseImpl
// * @see vn.gt.dao.danhmuc.service.impl.RmdcShipLocalServiceImpl
// * @generated
// */
//import org.springframework.stereotype.Component; @Component public class RmdcShipLocalServiceUtil {
//public RmdcShipLocalServiceUtil(RmdcShipLocalServiceImpl service) {
//RmdcShipLocalServiceUtil._service = service;
//}
//public static RmdcShipLocalServiceImpl getService() {
//return _service;
//}
//private static RmdcShipLocalServiceImpl _service;
//	/*
//	 * NOTE FOR DEVELOPERS:
//	 *
//	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.RmdcShipLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
//	 */
//
//	/**
//	* Adds the rmdc ship to the database. Also notifies the appropriate model listeners.
//	*
//	* @param rmdcShip the rmdc ship
//	* @return the rmdc ship that was added
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.RmdcShip addRmdcShip(
//		com.fds.nsw.nghiepvu.model.RmdcShip rmdcShip)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().addRmdcShip(rmdcShip);
//	}
//
//	/**
//	* Creates a new rmdc ship with the primary key. Does not add the rmdc ship to the database.
//	*
//	* @param id the primary key for the new rmdc ship
//	* @return the new rmdc ship
//	*/
//	public static com.fds.nsw.nghiepvu.model.RmdcShip createRmdcShip(long id) {
//		return getService().createRmdcShip(id);
//	}
//
//	/**
//	* Deletes the rmdc ship with the primary key from the database. Also notifies the appropriate model listeners.
//	*
//	* @param id the primary key of the rmdc ship
//	* @throws PortalException if a rmdc ship with the primary key could not be found
//	* @throws SystemException if a system exception occurred
//	*/
//	public static void deleteRmdcShip(long id)
//		throws com.fds.nsw.kernel.exception.PortalException,
//			com.fds.nsw.kernel.exception.SystemException {
//		getService().deleteRmdcShip(id);
//	}
//
//	/**
//	* Deletes the rmdc ship from the database. Also notifies the appropriate model listeners.
//	*
//	* @param rmdcShip the rmdc ship
//	* @throws SystemException if a system exception occurred
//	*/
//	public static void deleteRmdcShip(com.fds.nsw.nghiepvu.model.RmdcShip rmdcShip)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		getService().deleteRmdcShip(rmdcShip);
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
//	public static com.fds.nsw.nghiepvu.model.RmdcShip fetchRmdcShip(long id)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().fetchRmdcShip(id);
//	}
//
//	/**
//	* Returns the rmdc ship with the primary key.
//	*
//	* @param id the primary key of the rmdc ship
//	* @return the rmdc ship
//	* @throws PortalException if a rmdc ship with the primary key could not be found
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.RmdcShip getRmdcShip(long id)
//		throws com.fds.nsw.kernel.exception.PortalException,
//			com.fds.nsw.kernel.exception.SystemException {
//		return getService().getRmdcShip(id);
//	}
//
//
//
//	/**
//	* Returns a range of all the rmdc ships.
//	*
//	* <p>
//	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
//	* </p>
//	*
//	* @param start the lower bound of the range of rmdc ships
//	* @param end the upper bound of the range of rmdc ships (not inclusive)
//	* @return the range of rmdc ships
//	* @throws SystemException if a system exception occurred
//	*/
//	public static java.util.List<com.fds.nsw.nghiepvu.model.RmdcShip> getRmdcShips(
//		int start, int end)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().getRmdcShips(start, end);
//	}
//
//	/**
//	* Returns the number of rmdc ships.
//	*
//	* @return the number of rmdc ships
//	* @throws SystemException if a system exception occurred
//	*/
//	public static int getRmdcShipsCount()
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().getRmdcShipsCount();
//	}
//
//	/**
//	* Updates the rmdc ship in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
//	*
//	* @param rmdcShip the rmdc ship
//	* @return the rmdc ship that was updated
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.RmdcShip updateRmdcShip(
//		com.fds.nsw.nghiepvu.model.RmdcShip rmdcShip)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().updateRmdcShip(rmdcShip);
//	}
//
//	/**
//	* Updates the rmdc ship in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
//	*
//	* @param rmdcShip the rmdc ship
//	* @param merge whether to merge the rmdc ship with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
//	* @return the rmdc ship that was updated
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.RmdcShip updateRmdcShip(
//		com.fds.nsw.nghiepvu.model.RmdcShip rmdcShip, boolean merge)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().updateRmdcShip(rmdcShip, merge);
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