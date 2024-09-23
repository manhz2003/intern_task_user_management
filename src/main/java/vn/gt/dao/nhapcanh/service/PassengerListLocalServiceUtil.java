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
// * The utility for the passenger list local service. This utility wraps {@link vn.gt.dao.nhapcanh.service.impl.PassengerListLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
// *
// * <p>
// * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
// * </p>
// *
// * @author win_64
// * @see PassengerListLocalService
// * @see vn.gt.dao.nhapcanh.service.base.PassengerListLocalServiceBaseImpl
// * @see vn.gt.dao.nhapcanh.service.impl.PassengerListLocalServiceImpl
// * @generated
// */
//import org.springframework.stereotype.Component; @Component public class PassengerListLocalServiceUtil {
//public PassengerListLocalServiceUtil(PassengerListLocalServiceImpl service) {
//PassengerListLocalServiceUtil._service = service;
//}
//public static PassengerListLocalServiceImpl getService() {
//return _service;
//}
//private static PassengerListLocalServiceImpl _service;
//	/*
//	 * NOTE FOR DEVELOPERS:
//	 *
//	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.nhapcanh.service.impl.PassengerListLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
//	 */
//
//	/**
//	* Adds the passenger list to the database. Also notifies the appropriate model listeners.
//	*
//	* @param passengerList the passenger list
//	* @return the passenger list that was added
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.PassengerList addPassengerList(
//		com.fds.nsw.nghiepvu.model.PassengerList passengerList)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().addPassengerList(passengerList);
//	}
//
//	/**
//	* Creates a new passenger list with the primary key. Does not add the passenger list to the database.
//	*
//	* @param id the primary key for the new passenger list
//	* @return the new passenger list
//	*/
//	public static com.fds.nsw.nghiepvu.model.PassengerList createPassengerList(
//		long id) {
//		return getService().createPassengerList(id);
//	}
//
//	/**
//	* Deletes the passenger list with the primary key from the database. Also notifies the appropriate model listeners.
//	*
//	* @param id the primary key of the passenger list
//	* @throws PortalException if a passenger list with the primary key could not be found
//	* @throws SystemException if a system exception occurred
//	*/
//	public static void deletePassengerList(long id)
//		throws com.fds.nsw.kernel.exception.PortalException,
//			com.fds.nsw.kernel.exception.SystemException {
//		getService().deletePassengerList(id);
//	}
//
//	/**
//	* Deletes the passenger list from the database. Also notifies the appropriate model listeners.
//	*
//	* @param passengerList the passenger list
//	* @throws SystemException if a system exception occurred
//	*/
//	public static void deletePassengerList(
//		com.fds.nsw.nghiepvu.model.PassengerList passengerList)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		getService().deletePassengerList(passengerList);
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
//	public static com.fds.nsw.nghiepvu.model.PassengerList fetchPassengerList(
//		long id) throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().fetchPassengerList(id);
//	}
//
//	/**
//	* Returns the passenger list with the primary key.
//	*
//	* @param id the primary key of the passenger list
//	* @return the passenger list
//	* @throws PortalException if a passenger list with the primary key could not be found
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.PassengerList getPassengerList(
//		long id)
//		throws com.fds.nsw.kernel.exception.PortalException,
//			com.fds.nsw.kernel.exception.SystemException {
//		return getService().getPassengerList(id);
//	}
//
//
//
//	/**
//	* Returns a range of all the passenger lists.
//	*
//	* <p>
//	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
//	* </p>
//	*
//	* @param start the lower bound of the range of passenger lists
//	* @param end the upper bound of the range of passenger lists (not inclusive)
//	* @return the range of passenger lists
//	* @throws SystemException if a system exception occurred
//	*/
//	public static java.util.List<com.fds.nsw.nghiepvu.model.PassengerList> getPassengerLists(
//		int start, int end)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().getPassengerLists(start, end);
//	}
//
//	/**
//	* Returns the number of passenger lists.
//	*
//	* @return the number of passenger lists
//	* @throws SystemException if a system exception occurred
//	*/
//	public static int getPassengerListsCount()
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().getPassengerListsCount();
//	}
//
//	/**
//	* Updates the passenger list in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
//	*
//	* @param passengerList the passenger list
//	* @return the passenger list that was updated
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.PassengerList updatePassengerList(
//		com.fds.nsw.nghiepvu.model.PassengerList passengerList)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().updatePassengerList(passengerList);
//	}
//
//	/**
//	* Updates the passenger list in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
//	*
//	* @param passengerList the passenger list
//	* @param merge whether to merge the passenger list with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
//	* @return the passenger list that was updated
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.PassengerList updatePassengerList(
//		com.fds.nsw.nghiepvu.model.PassengerList passengerList, boolean merge)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().updatePassengerList(passengerList, merge);
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