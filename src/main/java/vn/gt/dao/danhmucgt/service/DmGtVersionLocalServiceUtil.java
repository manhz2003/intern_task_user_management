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

package vn.gt.dao.danhmucgt.service;






/**
 * The utility for the dm gt version local service. This utility wraps {@link vn.gt.dao.danhmucgt.service.impl.DmGtVersionLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmGtVersionLocalService
 * @see vn.gt.dao.danhmucgt.service.base.DmGtVersionLocalServiceBaseImpl
 * @see vn.gt.dao.danhmucgt.service.impl.DmGtVersionLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmucgt.service.impl.DmGtVersionLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class DmGtVersionLocalServiceUtil {
public DmGtVersionLocalServiceUtil(DmGtVersionLocalServiceImpl service) {
DmGtVersionLocalServiceUtil._service = service;
}
public static DmGtVersionLocalServiceImpl getService() {
return _service;
}
private static DmGtVersionLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmucgt.service.impl.DmGtVersionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dm gt version to the database. Also notifies the appropriate model listeners.
	*
	* @param dmGtVersion the dm gt version
	* @return the dm gt version that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmGtVersion addDmGtVersion(
		com.fds.nsw.nghiepvu.model.DmGtVersion dmGtVersion)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmGtVersion(dmGtVersion);
	}

	/**
	* Creates a new dm gt version with the primary key. Does not add the dm gt version to the database.
	*
	* @param id the primary key for the new dm gt version
	* @return the new dm gt version
	*/
	public static com.fds.nsw.nghiepvu.model.DmGtVersion createDmGtVersion(
		int id) {
		return getService().createDmGtVersion(id);
	}

	/**
	* Deletes the dm gt version with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the dm gt version
	* @throws PortalException if a dm gt version with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmGtVersion(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmGtVersion(id);
	}

	/**
	* Deletes the dm gt version from the database. Also notifies the appropriate model listeners.
	*
	* @param dmGtVersion the dm gt version
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmGtVersion(
		com.fds.nsw.nghiepvu.model.DmGtVersion dmGtVersion)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmGtVersion(dmGtVersion);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.DmGtVersion fetchDmGtVersion(int id)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmGtVersion(id);
	}

	/**
	* Returns the dm gt version with the primary key.
	*
	* @param id the primary key of the dm gt version
	* @return the dm gt version
	* @throws PortalException if a dm gt version with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmGtVersion getDmGtVersion(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmGtVersion(id);
	}

	

	/**
	* Returns a range of all the dm gt versions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of dm gt versions
	* @param end the upper bound of the range of dm gt versions (not inclusive)
	* @return the range of dm gt versions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmGtVersion> getDmGtVersions(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmGtVersions(start, end);
	}

	/**
	* Returns the number of dm gt versions.
	*
	* @return the number of dm gt versions
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmGtVersionsCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmGtVersionsCount();
	}

	/**
	* Updates the dm gt version in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmGtVersion the dm gt version
	* @return the dm gt version that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmGtVersion updateDmGtVersion(
		com.fds.nsw.nghiepvu.model.DmGtVersion dmGtVersion)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmGtVersion(dmGtVersion);
	}

	/**
	* Updates the dm gt version in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmGtVersion the dm gt version
	* @param merge whether to merge the dm gt version with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm gt version that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmGtVersion updateDmGtVersion(
		com.fds.nsw.nghiepvu.model.DmGtVersion dmGtVersion, boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmGtVersion(dmGtVersion, merge);
	}

	

	

	public static void clearService() {
		_service = null;
	}

	

	

	
}