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

package vn.gt.dao.danhmuc.service;






/**
 * The utility for the dm history port local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmHistoryPortLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmHistoryPortLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmHistoryPortLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmHistoryPortLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmHistoryPortLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class DmHistoryPortLocalServiceUtil {
public DmHistoryPortLocalServiceUtil(DmHistoryPortLocalServiceImpl service) {
DmHistoryPortLocalServiceUtil._service = service;
}
public static DmHistoryPortLocalServiceImpl getService() {
return _service;
}
private static DmHistoryPortLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmHistoryPortLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dm history port to the database. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryPort the dm history port
	* @return the dm history port that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryPort addDmHistoryPort(
		com.fds.nsw.nghiepvu.model.DmHistoryPort dmHistoryPort)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmHistoryPort(dmHistoryPort);
	}

	/**
	* Creates a new dm history port with the primary key. Does not add the dm history port to the database.
	*
	* @param id the primary key for the new dm history port
	* @return the new dm history port
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryPort createDmHistoryPort(
		int id) {
		return getService().createDmHistoryPort(id);
	}

	/**
	* Deletes the dm history port with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the dm history port
	* @throws PortalException if a dm history port with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmHistoryPort(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmHistoryPort(id);
	}

	/**
	* Deletes the dm history port from the database. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryPort the dm history port
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmHistoryPort(
		com.fds.nsw.nghiepvu.model.DmHistoryPort dmHistoryPort)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmHistoryPort(dmHistoryPort);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.DmHistoryPort fetchDmHistoryPort(
		int id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmHistoryPort(id);
	}

	/**
	* Returns the dm history port with the primary key.
	*
	* @param id the primary key of the dm history port
	* @return the dm history port
	* @throws PortalException if a dm history port with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryPort getDmHistoryPort(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryPort(id);
	}

	

	/**
	* Returns a range of all the dm history ports.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of dm history ports
	* @param end the upper bound of the range of dm history ports (not inclusive)
	* @return the range of dm history ports
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmHistoryPort> getDmHistoryPorts(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryPorts(start, end);
	}

	/**
	* Returns the number of dm history ports.
	*
	* @return the number of dm history ports
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmHistoryPortsCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryPortsCount();
	}

	/**
	* Updates the dm history port in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryPort the dm history port
	* @return the dm history port that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryPort updateDmHistoryPort(
		com.fds.nsw.nghiepvu.model.DmHistoryPort dmHistoryPort)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmHistoryPort(dmHistoryPort);
	}

	/**
	* Updates the dm history port in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryPort the dm history port
	* @param merge whether to merge the dm history port with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm history port that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryPort updateDmHistoryPort(
		com.fds.nsw.nghiepvu.model.DmHistoryPort dmHistoryPort, boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmHistoryPort(dmHistoryPort, merge);
	}

	

	

	public static com.fds.nsw.nghiepvu.model.DmHistoryPort findByPortCodeAndSyncVersion(
		java.lang.String portCode, java.lang.String syncVersion) {
		return getService().findByPortCodeAndSyncVersion(portCode, syncVersion);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}