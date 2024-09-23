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
// * The utility for the dm enterrise local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmEnterriseLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
// *
// * <p>
// * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
// * </p>
// *
// * @author win_64
// * @see DmEnterriseLocalService
// * @see vn.gt.dao.danhmuc.service.base.DmEnterriseLocalServiceBaseImpl
// * @see vn.gt.dao.danhmuc.service.impl.DmEnterriseLocalServiceImpl
// * @generated
// */
//import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmEnterriseLocalServiceImpl;
//import org.springframework.stereotype.Component; @Component public class DmEnterriseLocalServiceUtil {
//public DmEnterriseLocalServiceUtil(DmEnterriseLocalServiceImpl service) {
//DmEnterriseLocalServiceUtil._service = service;
//}
//public static DmEnterriseLocalServiceImpl getService() {
//return _service;
//}
//private static DmEnterriseLocalServiceImpl _service;
//	/*
//	 * NOTE FOR DEVELOPERS:
//	 *
//	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmEnterriseLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
//	 */
//
//	/**
//	* Adds the dm enterrise to the database. Also notifies the appropriate model listeners.
//	*
//	* @param dmEnterrise the dm enterrise
//	* @return the dm enterrise that was added
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.DmEnterrise addDmEnterrise(
//		com.fds.nsw.nghiepvu.model.DmEnterrise dmEnterrise)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().addDmEnterrise(dmEnterrise);
//	}
//
//	/**
//	* Creates a new dm enterrise with the primary key. Does not add the dm enterrise to the database.
//	*
//	* @param id the primary key for the new dm enterrise
//	* @return the new dm enterrise
//	*/
//	public static com.fds.nsw.nghiepvu.model.DmEnterrise createDmEnterrise(int id) {
//		return getService().createDmEnterrise(id);
//	}
//
//	/**
//	* Deletes the dm enterrise with the primary key from the database. Also notifies the appropriate model listeners.
//	*
//	* @param id the primary key of the dm enterrise
//	* @throws PortalException if a dm enterrise with the primary key could not be found
//	* @throws SystemException if a system exception occurred
//	*/
//	public static void deleteDmEnterrise(int id)
//		throws com.fds.nsw.kernel.exception.PortalException,
//			com.fds.nsw.kernel.exception.SystemException {
//		getService().deleteDmEnterrise(id);
//	}
//
//	/**
//	* Deletes the dm enterrise from the database. Also notifies the appropriate model listeners.
//	*
//	* @param dmEnterrise the dm enterrise
//	* @throws SystemException if a system exception occurred
//	*/
//	public static void deleteDmEnterrise(
//		com.fds.nsw.nghiepvu.model.DmEnterrise dmEnterrise)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		getService().deleteDmEnterrise(dmEnterrise);
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
//	public static com.fds.nsw.nghiepvu.model.DmEnterrise fetchDmEnterrise(int id)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().fetchDmEnterrise(id);
//	}
//
//	/**
//	* Returns the dm enterrise with the primary key.
//	*
//	* @param id the primary key of the dm enterrise
//	* @return the dm enterrise
//	* @throws PortalException if a dm enterrise with the primary key could not be found
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.DmEnterrise getDmEnterrise(int id)
//		throws com.fds.nsw.kernel.exception.PortalException,
//			com.fds.nsw.kernel.exception.SystemException {
//		return getService().getDmEnterrise(id);
//	}
//
//
//
//	/**
//	* Returns a range of all the dm enterrises.
//	*
//	* <p>
//	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
//	* </p>
//	*
//	* @param start the lower bound of the range of dm enterrises
//	* @param end the upper bound of the range of dm enterrises (not inclusive)
//	* @return the range of dm enterrises
//	* @throws SystemException if a system exception occurred
//	*/
//	public static java.util.List<com.fds.nsw.nghiepvu.model.DmEnterrise> getDmEnterrises(
//		int start, int end)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().getDmEnterrises(start, end);
//	}
//
//	/**
//	* Returns the number of dm enterrises.
//	*
//	* @return the number of dm enterrises
//	* @throws SystemException if a system exception occurred
//	*/
//	public static int getDmEnterrisesCount()
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().getDmEnterrisesCount();
//	}
//
//	/**
//	* Updates the dm enterrise in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
//	*
//	* @param dmEnterrise the dm enterrise
//	* @return the dm enterrise that was updated
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.DmEnterrise updateDmEnterrise(
//		com.fds.nsw.nghiepvu.model.DmEnterrise dmEnterrise)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().updateDmEnterrise(dmEnterrise);
//	}
//
//	/**
//	* Updates the dm enterrise in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
//	*
//	* @param dmEnterrise the dm enterrise
//	* @param merge whether to merge the dm enterrise with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
//	* @return the dm enterrise that was updated
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.DmEnterrise updateDmEnterrise(
//		com.fds.nsw.nghiepvu.model.DmEnterrise dmEnterrise, boolean merge)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().updateDmEnterrise(dmEnterrise, merge);
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