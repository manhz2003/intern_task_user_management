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
 * The utility for the dm history port region local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmHistoryPortRegionLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmHistoryPortRegionLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmHistoryPortRegionLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmHistoryPortRegionLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmHistoryPortRegionLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class DmHistoryPortRegionLocalServiceUtil {
public DmHistoryPortRegionLocalServiceUtil(DmHistoryPortRegionLocalServiceImpl service) {
DmHistoryPortRegionLocalServiceUtil._service = service;
}
public static DmHistoryPortRegionLocalServiceImpl getService() {
return _service;
}
private static DmHistoryPortRegionLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmHistoryPortRegionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dm history port region to the database. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryPortRegion the dm history port region
	* @return the dm history port region that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryPortRegion addDmHistoryPortRegion(
		com.fds.nsw.nghiepvu.model.DmHistoryPortRegion dmHistoryPortRegion)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmHistoryPortRegion(dmHistoryPortRegion);
	}

	/**
	* Creates a new dm history port region with the primary key. Does not add the dm history port region to the database.
	*
	* @param id the primary key for the new dm history port region
	* @return the new dm history port region
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryPortRegion createDmHistoryPortRegion(
		int id) {
		return getService().createDmHistoryPortRegion(id);
	}

	/**
	* Deletes the dm history port region with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the dm history port region
	* @throws PortalException if a dm history port region with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmHistoryPortRegion(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmHistoryPortRegion(id);
	}

	/**
	* Deletes the dm history port region from the database. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryPortRegion the dm history port region
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmHistoryPortRegion(
		com.fds.nsw.nghiepvu.model.DmHistoryPortRegion dmHistoryPortRegion)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmHistoryPortRegion(dmHistoryPortRegion);
	}


	public static com.fds.nsw.nghiepvu.model.DmHistoryPortRegion fetchDmHistoryPortRegion(
		int id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmHistoryPortRegion(id);
	}

	/**
	* Returns the dm history port region with the primary key.
	*
	* @param id the primary key of the dm history port region
	* @return the dm history port region
	* @throws PortalException if a dm history port region with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryPortRegion getDmHistoryPortRegion(
		int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryPortRegion(id);
	}

	

	/**
	* Returns a range of all the dm history port regions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of dm history port regions
	* @param end the upper bound of the range of dm history port regions (not inclusive)
	* @return the range of dm history port regions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmHistoryPortRegion> getDmHistoryPortRegions(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryPortRegions(start, end);
	}

	/**
	* Returns the number of dm history port regions.
	*
	* @return the number of dm history port regions
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmHistoryPortRegionsCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryPortRegionsCount();
	}

	/**
	* Updates the dm history port region in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryPortRegion the dm history port region
	* @return the dm history port region that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryPortRegion updateDmHistoryPortRegion(
		com.fds.nsw.nghiepvu.model.DmHistoryPortRegion dmHistoryPortRegion)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmHistoryPortRegion(dmHistoryPortRegion);
	}

	/**
	* Updates the dm history port region in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryPortRegion the dm history port region
	* @param merge whether to merge the dm history port region with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm history port region that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryPortRegion updateDmHistoryPortRegion(
		com.fds.nsw.nghiepvu.model.DmHistoryPortRegion dmHistoryPortRegion,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmHistoryPortRegion(dmHistoryPortRegion, merge);
	}

	

	

	public static com.fds.nsw.nghiepvu.model.DmHistoryPortRegion findByPortRegionCodeAndSyncVersion(
		java.lang.String portRegionCode, java.lang.String syncVersion) {
		return getService()
				   .findByPortRegionCodeAndSyncVersion(portRegionCode,
			syncVersion);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}