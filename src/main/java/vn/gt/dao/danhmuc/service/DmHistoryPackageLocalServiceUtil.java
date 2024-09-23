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
 * The utility for the dm history package local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmHistoryPackageLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmHistoryPackageLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmHistoryPackageLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmHistoryPackageLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmHistoryPackageLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class DmHistoryPackageLocalServiceUtil {
public DmHistoryPackageLocalServiceUtil(DmHistoryPackageLocalServiceImpl service) {
DmHistoryPackageLocalServiceUtil._service = service;
}
public static DmHistoryPackageLocalServiceImpl getService() {
return _service;
}
private static DmHistoryPackageLocalServiceImpl _service;

	public static com.fds.nsw.nghiepvu.model.DmHistoryPackage addDmHistoryPackage(
		com.fds.nsw.nghiepvu.model.DmHistoryPackage dmHistoryPackage)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmHistoryPackage(dmHistoryPackage);
	}

	/**
	* Creates a new dm history package with the primary key. Does not add the dm history package to the database.
	*
	* @param id the primary key for the new dm history package
	* @return the new dm history package
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryPackage createDmHistoryPackage(
		int id) {
		return getService().createDmHistoryPackage(id);
	}

	/**
	* Deletes the dm history package with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the dm history package
	* @throws PortalException if a dm history package with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmHistoryPackage(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmHistoryPackage(id);
	}

	/**
	* Deletes the dm history package from the database. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryPackage the dm history package
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmHistoryPackage(
		com.fds.nsw.nghiepvu.model.DmHistoryPackage dmHistoryPackage)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmHistoryPackage(dmHistoryPackage);
	}

	public static com.fds.nsw.nghiepvu.model.DmHistoryPackage fetchDmHistoryPackage(
		int id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmHistoryPackage(id);
	}

	/**
	* Returns the dm history package with the primary key.
	*
	* @param id the primary key of the dm history package
	* @return the dm history package
	* @throws PortalException if a dm history package with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryPackage getDmHistoryPackage(
		int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryPackage(id);
	}

	

	/**
	* Returns a range of all the dm history packages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of dm history packages
	* @param end the upper bound of the range of dm history packages (not inclusive)
	* @return the range of dm history packages
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmHistoryPackage> getDmHistoryPackages(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryPackages(start, end);
	}

	/**
	* Returns the number of dm history packages.
	*
	* @return the number of dm history packages
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmHistoryPackagesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryPackagesCount();
	}

	/**
	* Updates the dm history package in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryPackage the dm history package
	* @return the dm history package that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryPackage updateDmHistoryPackage(
		com.fds.nsw.nghiepvu.model.DmHistoryPackage dmHistoryPackage)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmHistoryPackage(dmHistoryPackage);
	}

	/**
	* Updates the dm history package in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryPackage the dm history package
	* @param merge whether to merge the dm history package with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm history package that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryPackage updateDmHistoryPackage(
		com.fds.nsw.nghiepvu.model.DmHistoryPackage dmHistoryPackage, boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmHistoryPackage(dmHistoryPackage, merge);
	}

	

	

	public static com.fds.nsw.nghiepvu.model.DmHistoryPackage getHistoryPackageByPackageCodeAndSyncVersion(
		java.lang.String packageCode, java.lang.String syncVersion) {
		return getService()
				   .getHistoryPackageByPackageCodeAndSyncVersion(packageCode,
			syncVersion);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}