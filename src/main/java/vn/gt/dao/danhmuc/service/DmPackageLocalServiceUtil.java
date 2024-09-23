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
 * The utility for the dm package local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmPackageLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmPackageLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmPackageLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmPackageLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmPackageLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class DmPackageLocalServiceUtil {
public DmPackageLocalServiceUtil(DmPackageLocalServiceImpl service) {
DmPackageLocalServiceUtil._service = service;
}
public static DmPackageLocalServiceImpl getService() {
return _service;
}
private static DmPackageLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmPackageLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dm package to the database. Also notifies the appropriate model listeners.
	*
	* @param dmPackage the dm package
	* @return the dm package that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmPackage addDmPackage(
		com.fds.nsw.nghiepvu.model.DmPackage dmPackage)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmPackage(dmPackage);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmPackage> findByPackage(
			java.lang.String packageNameVN, int start, int end) {
		return getService().findByPackage(packageNameVN, start, end);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmPackage> findPackages(
			java.lang.String packageCode, java.lang.String packageNameVN,
			java.lang.String isDelete, int start, int end) {
		return getService()
				.findPackages(packageCode, packageNameVN, isDelete, start,
						end);
	}

	public static long countPackages(java.lang.String packageCode,
									 java.lang.String packageNameVN, java.lang.String isDelete) {
		return getService().countPackages(packageCode, packageNameVN, isDelete);
	}


	/**
	* Creates a new dm package with the primary key. Does not add the dm package to the database.
	*
	* @param id the primary key for the new dm package
	* @return the new dm package
	*/
	public static com.fds.nsw.nghiepvu.model.DmPackage createDmPackage(int id) {
		return getService().createDmPackage(id);
	}

	/**
	* Deletes the dm package with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the dm package
	* @throws PortalException if a dm package with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmPackage(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmPackage(id);
	}

	/**
	* Deletes the dm package from the database. Also notifies the appropriate model listeners.
	*
	* @param dmPackage the dm package
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmPackage(
		com.fds.nsw.nghiepvu.model.DmPackage dmPackage)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmPackage(dmPackage);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.DmPackage fetchDmPackage(int id)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmPackage(id);
	}

	/**
	* Returns the dm package with the primary key.
	*
	* @param id the primary key of the dm package
	* @return the dm package
	* @throws PortalException if a dm package with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmPackage getDmPackage(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmPackage(id);
	}

	

	/**
	* Returns a range of all the dm packages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of dm packages
	* @param end the upper bound of the range of dm packages (not inclusive)
	* @return the range of dm packages
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmPackage> getDmPackages(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmPackages(start, end);
	}

	/**
	* Returns the number of dm packages.
	*
	* @return the number of dm packages
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmPackagesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmPackagesCount();
	}

	/**
	* Updates the dm package in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmPackage the dm package
	* @return the dm package that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmPackage updateDmPackage(
		com.fds.nsw.nghiepvu.model.DmPackage dmPackage)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmPackage(dmPackage);
	}

	/**
	* Updates the dm package in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmPackage the dm package
	* @param merge whether to merge the dm package with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm package that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmPackage updateDmPackage(
		com.fds.nsw.nghiepvu.model.DmPackage dmPackage, boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmPackage(dmPackage, merge);
	}

	

	

	public static com.fds.nsw.nghiepvu.model.DmPackage getByPackageCode(
		java.lang.String packageCode) {
		return getService().getByPackageCode(packageCode);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}