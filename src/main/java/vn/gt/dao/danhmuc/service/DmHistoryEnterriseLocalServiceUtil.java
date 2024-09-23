///** class nay ko can sua, co bang nhung ko co data thi lam an gi
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
// * The utility for the dm history enterrise local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmHistoryEnterriseLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
// *
// * <p>
// * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
// * </p>
// *
// * @author win_64
// * @see DmHistoryEnterriseLocalService
// * @see vn.gt.dao.danhmuc.service.base.DmHistoryEnterriseLocalServiceBaseImpl
// * @see vn.gt.dao.danhmuc.service.impl.DmHistoryEnterriseLocalServiceImpl
// * @generated
// */
//import org.springframework.stereotype.Component; @Component public class DmHistoryEnterriseLocalServiceUtil {
//public DmHistoryEnterriseLocalServiceUtil(DmHistoryEnterriseLocalServiceImpl service) {
//DmHistoryEnterriseLocalServiceUtil._service = service;
//}
//public static DmHistoryEnterriseLocalServiceImpl getService() {
//return _service;
//}
//private static DmHistoryEnterriseLocalServiceImpl _service;
//	/*
//	 * NOTE FOR DEVELOPERS:
//	 *
//	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmHistoryEnterriseLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
//	 */
//
//	/**
//	* Adds the dm history enterrise to the database. Also notifies the appropriate model listeners.
//	*
//	* @param dmHistoryEnterrise the dm history enterrise
//	* @return the dm history enterrise that was added
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.DmHistoryEnterrise addDmHistoryEnterrise(
//		com.fds.nsw.nghiepvu.model.DmHistoryEnterrise dmHistoryEnterrise)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().addDmHistoryEnterrise(dmHistoryEnterrise);
//	}
//
//	/**
//	* Creates a new dm history enterrise with the primary key. Does not add the dm history enterrise to the database.
//	*
//	* @param id the primary key for the new dm history enterrise
//	* @return the new dm history enterrise
//	*/
//	public static com.fds.nsw.nghiepvu.model.DmHistoryEnterrise createDmHistoryEnterrise(
//		int id) {
//		return getService().createDmHistoryEnterrise(id);
//	}
//
//	/**
//	* Deletes the dm history enterrise with the primary key from the database. Also notifies the appropriate model listeners.
//	*
//	* @param id the primary key of the dm history enterrise
//	* @throws PortalException if a dm history enterrise with the primary key could not be found
//	* @throws SystemException if a system exception occurred
//	*/
//	public static void deleteDmHistoryEnterrise(int id)
//		throws com.fds.nsw.kernel.exception.PortalException,
//			com.fds.nsw.kernel.exception.SystemException {
//		getService().deleteDmHistoryEnterrise(id);
//	}
//
//	/**
//	* Deletes the dm history enterrise from the database. Also notifies the appropriate model listeners.
//	*
//	* @param dmHistoryEnterrise the dm history enterrise
//	* @throws SystemException if a system exception occurred
//	*/
//	public static void deleteDmHistoryEnterrise(
//		com.fds.nsw.nghiepvu.model.DmHistoryEnterrise dmHistoryEnterrise)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		getService().deleteDmHistoryEnterrise(dmHistoryEnterrise);
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
//	public static com.fds.nsw.nghiepvu.model.DmHistoryEnterrise fetchDmHistoryEnterrise(
//		int id) throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().fetchDmHistoryEnterrise(id);
//	}
//
//	/**
//	* Returns the dm history enterrise with the primary key.
//	*
//	* @param id the primary key of the dm history enterrise
//	* @return the dm history enterrise
//	* @throws PortalException if a dm history enterrise with the primary key could not be found
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.DmHistoryEnterrise getDmHistoryEnterrise(
//		int id)
//		throws com.fds.nsw.kernel.exception.PortalException,
//			com.fds.nsw.kernel.exception.SystemException {
//		return getService().getDmHistoryEnterrise(id);
//	}
//
//
//
//	/**
//	* Returns a range of all the dm history enterrises.
//	*
//	* <p>
//	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
//	* </p>
//	*
//	* @param start the lower bound of the range of dm history enterrises
//	* @param end the upper bound of the range of dm history enterrises (not inclusive)
//	* @return the range of dm history enterrises
//	* @throws SystemException if a system exception occurred
//	*/
//	public static java.util.List<com.fds.nsw.nghiepvu.model.DmHistoryEnterrise> getDmHistoryEnterrises(
//		int start, int end)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().getDmHistoryEnterrises(start, end);
//	}
//
//	/**
//	* Returns the number of dm history enterrises.
//	*
//	* @return the number of dm history enterrises
//	* @throws SystemException if a system exception occurred
//	*/
//	public static int getDmHistoryEnterrisesCount()
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().getDmHistoryEnterrisesCount();
//	}
//
//	/**
//	* Updates the dm history enterrise in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
//	*
//	* @param dmHistoryEnterrise the dm history enterrise
//	* @return the dm history enterrise that was updated
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.DmHistoryEnterrise updateDmHistoryEnterrise(
//		com.fds.nsw.nghiepvu.model.DmHistoryEnterrise dmHistoryEnterrise)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().updateDmHistoryEnterrise(dmHistoryEnterrise);
//	}
//
//	/**
//	* Updates the dm history enterrise in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
//	*
//	* @param dmHistoryEnterrise the dm history enterrise
//	* @param merge whether to merge the dm history enterrise with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
//	* @return the dm history enterrise that was updated
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.DmHistoryEnterrise updateDmHistoryEnterrise(
//		com.fds.nsw.nghiepvu.model.DmHistoryEnterrise dmHistoryEnterrise,
//		boolean merge)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().updateDmHistoryEnterrise(dmHistoryEnterrise, merge);
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