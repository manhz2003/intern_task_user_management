/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package vn.gt.dao.common.service;






/**
 * The utility for the user port local service. This utility wraps {@link vn.gt.dao.common.service.impl.UserPortLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see UserPortLocalService
 * @see vn.gt.dao.common.service.base.UserPortLocalServiceBaseImpl
 * @see vn.gt.dao.common.service.impl.UserPortLocalServiceImpl
 * @generated
 */
import com.fds.nsw.liferay.core.DynamicQuery;
import com.fds.nsw.nghiepvu.common.service.impl.UserPortLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class UserPortLocalServiceUtil {
public UserPortLocalServiceUtil(UserPortLocalServiceImpl service) {
UserPortLocalServiceUtil._service = service;
}
public static UserPortLocalServiceImpl getService() {
return _service;
}
private static UserPortLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.common.service.impl.UserPortLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the user port to the database. Also notifies the appropriate model listeners.
	*
	* @param userPort the user port
	* @return the user port that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.UserPort addUserPort(
		com.fds.nsw.nghiepvu.model.UserPort userPort)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addUserPort(userPort);
	}

	/**
	* Creates a new user port with the primary key. Does not add the user port to the database.
	*
	* @param id the primary key for the new user port
	* @return the new user port
	*/
	public static com.fds.nsw.nghiepvu.model.UserPort createUserPort(long id) {
		return getService().createUserPort(id);
	}

	/**
	* Deletes the user port with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the user port
	* @throws PortalException if a user port with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteUserPort(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteUserPort(id);
	}

	/**
	* Deletes the user port from the database. Also notifies the appropriate model listeners.
	*
	* @param userPort the user port
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteUserPort(com.fds.nsw.nghiepvu.model.UserPort userPort)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteUserPort(userPort);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.UserPort fetchUserPort(long id)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchUserPort(id);
	}

	/**
	* Returns the user port with the primary key.
	*
	* @param id the primary key of the user port
	* @return the user port
	* @throws PortalException if a user port with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.UserPort getUserPort(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getUserPort(id);
	}

	

	/**
	* Returns a range of all the user ports.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of user ports
	* @param end the upper bound of the range of user ports (not inclusive)
	* @return the range of user ports
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.UserPort> getUserPorts(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getUserPorts(start, end);
	}

	/**
	* Returns the number of user ports.
	*
	* @return the number of user ports
	* @throws SystemException if a system exception occurred
	*/
	public static int getUserPortsCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getUserPortsCount();
	}

	/**
	* Updates the user port in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param userPort the user port
	* @return the user port that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.UserPort updateUserPort(
		com.fds.nsw.nghiepvu.model.UserPort userPort)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateUserPort(userPort);
	}

	/**
	* Updates the user port in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param userPort the user port
	* @param merge whether to merge the user port with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the user port that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.UserPort updateUserPort(
		com.fds.nsw.nghiepvu.model.UserPort userPort, boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateUserPort(userPort, merge);
	}


	public static DynamicQuery dynamicQuery() {
		return null;
	}



	public static java.util.List<com.fds.nsw.nghiepvu.model.UserPort> findByPortCode(
		java.lang.String portCode, int start, int end) {
		return getService().findByPortCode(portCode, start, end);
	}

	public static com.fds.nsw.nghiepvu.model.UserPort findByUserId(long userId) {
		return getService().findByUserId(userId);
	}

	public static int countByPortCode(java.lang.String portHarbourCode) {
		return getService().countByPortCode(portHarbourCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.UserPort> findAll(
		int start, int end) {
		return getService().findAll(start, end);
	}


	public static java.util.List<com.fds.nsw.nghiepvu.model.UserPort> fetchByDepartmentCode(
			java.lang.String departmentCode, int start, int end) {
		return getService().fetchByDepartmentCode(departmentCode, start, end);
	}

	public static long countByDepartmentCode(String departmentCode) {
		return getService().countByDepartmentCode(departmentCode);
	}


	public static void clearService() {
		_service = null;
	}

	

	

	
}