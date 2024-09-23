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
 * The utility for the dm gt route config local service. This utility wraps {@link vn.gt.dao.danhmucgt.service.impl.DmGtRouteConfigLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmGtRouteConfigLocalService
 * @see vn.gt.dao.danhmucgt.service.base.DmGtRouteConfigLocalServiceBaseImpl
 * @see vn.gt.dao.danhmucgt.service.impl.DmGtRouteConfigLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmucgt.service.impl.DmGtRouteConfigLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class DmGtRouteConfigLocalServiceUtil {
public DmGtRouteConfigLocalServiceUtil(DmGtRouteConfigLocalServiceImpl service) {
DmGtRouteConfigLocalServiceUtil._service = service;
}
public static DmGtRouteConfigLocalServiceImpl getService() {
return _service;
}
private static DmGtRouteConfigLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmucgt.service.impl.DmGtRouteConfigLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dm gt route config to the database. Also notifies the appropriate model listeners.
	*
	* @param dmGtRouteConfig the dm gt route config
	* @return the dm gt route config that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmGtRouteConfig addDmGtRouteConfig(
		com.fds.nsw.nghiepvu.model.DmGtRouteConfig dmGtRouteConfig)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmGtRouteConfig(dmGtRouteConfig);
	}

	/**
	* Creates a new dm gt route config with the primary key. Does not add the dm gt route config to the database.
	*
	* @param id the primary key for the new dm gt route config
	* @return the new dm gt route config
	*/
	public static com.fds.nsw.nghiepvu.model.DmGtRouteConfig createDmGtRouteConfig(
		long id) {
		return getService().createDmGtRouteConfig(id);
	}

	/**
	* Deletes the dm gt route config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the dm gt route config
	* @throws PortalException if a dm gt route config with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmGtRouteConfig(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmGtRouteConfig(id);
	}

	/**
	* Deletes the dm gt route config from the database. Also notifies the appropriate model listeners.
	*
	* @param dmGtRouteConfig the dm gt route config
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmGtRouteConfig(
		com.fds.nsw.nghiepvu.model.DmGtRouteConfig dmGtRouteConfig)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmGtRouteConfig(dmGtRouteConfig);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.DmGtRouteConfig fetchDmGtRouteConfig(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmGtRouteConfig(id);
	}

	/**
	* Returns the dm gt route config with the primary key.
	*
	* @param id the primary key of the dm gt route config
	* @return the dm gt route config
	* @throws PortalException if a dm gt route config with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmGtRouteConfig getDmGtRouteConfig(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmGtRouteConfig(id);
	}

	

	/**
	* Returns a range of all the dm gt route configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of dm gt route configs
	* @param end the upper bound of the range of dm gt route configs (not inclusive)
	* @return the range of dm gt route configs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmGtRouteConfig> getDmGtRouteConfigs(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmGtRouteConfigs(start, end);
	}

	/**
	* Returns the number of dm gt route configs.
	*
	* @return the number of dm gt route configs
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmGtRouteConfigsCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmGtRouteConfigsCount();
	}

	/**
	* Updates the dm gt route config in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmGtRouteConfig the dm gt route config
	* @return the dm gt route config that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmGtRouteConfig updateDmGtRouteConfig(
		com.fds.nsw.nghiepvu.model.DmGtRouteConfig dmGtRouteConfig)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmGtRouteConfig(dmGtRouteConfig);
	}

	/**
	* Updates the dm gt route config in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmGtRouteConfig the dm gt route config
	* @param merge whether to merge the dm gt route config with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm gt route config that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmGtRouteConfig updateDmGtRouteConfig(
		com.fds.nsw.nghiepvu.model.DmGtRouteConfig dmGtRouteConfig, boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmGtRouteConfig(dmGtRouteConfig, merge);
	}

	

	

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmGtRouteConfig> findByIsDelete(
		int isDelete) {
		return getService().findByIsDelete(isDelete);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmGtRouteConfig> findByLocCode(
		java.lang.String locCode) {
		return getService().findByLocCode(locCode);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}