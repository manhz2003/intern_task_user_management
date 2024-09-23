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
// * The utility for the crew list local service. This utility wraps {@link vn.gt.dao.nhapcanh.service.impl.CrewListLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
// *
// * <p>
// * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
// * </p>
// *
// * @author win_64
// * @see CrewListLocalService
// * @see vn.gt.dao.nhapcanh.service.base.CrewListLocalServiceBaseImpl
// * @see vn.gt.dao.nhapcanh.service.impl.CrewListLocalServiceImpl
// * @generated
// */
//import org.springframework.stereotype.Component; @Component public class CrewListLocalServiceUtil {
//public CrewListLocalServiceUtil(CrewListLocalServiceImpl service) {
//CrewListLocalServiceUtil._service = service;
//}
//public static CrewListLocalServiceImpl getService() {
//return _service;
//}
//private static CrewListLocalServiceImpl _service;
//	/*
//	 * NOTE FOR DEVELOPERS:
//	 *
//	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.nhapcanh.service.impl.CrewListLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
//	 */
//
//	/**
//	* Adds the crew list to the database. Also notifies the appropriate model listeners.
//	*
//	* @param crewList the crew list
//	* @return the crew list that was added
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.CrewList addCrewList(
//		com.fds.nsw.nghiepvu.model.CrewList crewList)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().addCrewList(crewList);
//	}
//
//	/**
//	* Creates a new crew list with the primary key. Does not add the crew list to the database.
//	*
//	* @param id the primary key for the new crew list
//	* @return the new crew list
//	*/
//	public static com.fds.nsw.nghiepvu.model.CrewList createCrewList(long id) {
//		return getService().createCrewList(id);
//	}
//
//	/**
//	* Deletes the crew list with the primary key from the database. Also notifies the appropriate model listeners.
//	*
//	* @param id the primary key of the crew list
//	* @throws PortalException if a crew list with the primary key could not be found
//	* @throws SystemException if a system exception occurred
//	*/
//	public static void deleteCrewList(long id)
//		throws com.fds.nsw.kernel.exception.PortalException,
//			com.fds.nsw.kernel.exception.SystemException {
//		getService().deleteCrewList(id);
//	}
//
//	/**
//	* Deletes the crew list from the database. Also notifies the appropriate model listeners.
//	*
//	* @param crewList the crew list
//	* @throws SystemException if a system exception occurred
//	*/
//	public static void deleteCrewList(
//		com.fds.nsw.nghiepvu.model.CrewList crewList)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		getService().deleteCrewList(crewList);
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
//	public static com.fds.nsw.nghiepvu.model.CrewList fetchCrewList(long id)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().fetchCrewList(id);
//	}
//
//	/**
//	* Returns the crew list with the primary key.
//	*
//	* @param id the primary key of the crew list
//	* @return the crew list
//	* @throws PortalException if a crew list with the primary key could not be found
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.CrewList getCrewList(long id)
//		throws com.fds.nsw.kernel.exception.PortalException,
//			com.fds.nsw.kernel.exception.SystemException {
//		return getService().getCrewList(id);
//	}
//
//
//
//	/**
//	* Returns a range of all the crew lists.
//	*
//	* <p>
//	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
//	* </p>
//	*
//	* @param start the lower bound of the range of crew lists
//	* @param end the upper bound of the range of crew lists (not inclusive)
//	* @return the range of crew lists
//	* @throws SystemException if a system exception occurred
//	*/
//	public static java.util.List<com.fds.nsw.nghiepvu.model.CrewList> getCrewLists(
//		int start, int end)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().getCrewLists(start, end);
//	}
//
//	/**
//	* Returns the number of crew lists.
//	*
//	* @return the number of crew lists
//	* @throws SystemException if a system exception occurred
//	*/
//	public static int getCrewListsCount()
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().getCrewListsCount();
//	}
//
//	/**
//	* Updates the crew list in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
//	*
//	* @param crewList the crew list
//	* @return the crew list that was updated
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.CrewList updateCrewList(
//		com.fds.nsw.nghiepvu.model.CrewList crewList)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().updateCrewList(crewList);
//	}
//
//	/**
//	* Updates the crew list in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
//	*
//	* @param crewList the crew list
//	* @param merge whether to merge the crew list with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
//	* @return the crew list that was updated
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.CrewList updateCrewList(
//		com.fds.nsw.nghiepvu.model.CrewList crewList, boolean merge)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().updateCrewList(crewList, merge);
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