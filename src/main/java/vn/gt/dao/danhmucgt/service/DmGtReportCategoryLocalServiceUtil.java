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
 * The utility for the dm gt report category local service. This utility wraps {@link vn.gt.dao.danhmucgt.service.impl.DmGtReportCategoryLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmGtReportCategoryLocalService
 * @see vn.gt.dao.danhmucgt.service.base.DmGtReportCategoryLocalServiceBaseImpl
 * @see vn.gt.dao.danhmucgt.service.impl.DmGtReportCategoryLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmucgt.service.impl.DmGtReportCategoryLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class DmGtReportCategoryLocalServiceUtil {
public DmGtReportCategoryLocalServiceUtil(DmGtReportCategoryLocalServiceImpl service) {
DmGtReportCategoryLocalServiceUtil._service = service;
}
public static DmGtReportCategoryLocalServiceImpl getService() {
return _service;
}
private static DmGtReportCategoryLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmucgt.service.impl.DmGtReportCategoryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	public static com.fds.nsw.nghiepvu.model.DmGtReportCategory findByOrganizationCode(
			java.lang.String category) {
		return getService().findByOrganizationCode(category);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmGtReportCategory> findByIsDelete(
			int isDelete, int start, int end) {
		return getService().findByIsDelete(isDelete, start, end);
	}




	/**
	* Adds the dm gt report category to the database. Also notifies the appropriate model listeners.
	*
	* @param dmGtReportCategory the dm gt report category
	* @return the dm gt report category that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmGtReportCategory addDmGtReportCategory(
		com.fds.nsw.nghiepvu.model.DmGtReportCategory dmGtReportCategory)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmGtReportCategory(dmGtReportCategory);
	}

	/**
	* Creates a new dm gt report category with the primary key. Does not add the dm gt report category to the database.
	*
	* @param id the primary key for the new dm gt report category
	* @return the new dm gt report category
	*/
	public static com.fds.nsw.nghiepvu.model.DmGtReportCategory createDmGtReportCategory(
		long id) {
		return getService().createDmGtReportCategory(id);
	}

	/**
	* Deletes the dm gt report category with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the dm gt report category
	* @throws PortalException if a dm gt report category with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmGtReportCategory(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmGtReportCategory(id);
	}

	/**
	* Deletes the dm gt report category from the database. Also notifies the appropriate model listeners.
	*
	* @param dmGtReportCategory the dm gt report category
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmGtReportCategory(
		com.fds.nsw.nghiepvu.model.DmGtReportCategory dmGtReportCategory)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmGtReportCategory(dmGtReportCategory);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.DmGtReportCategory fetchDmGtReportCategory(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmGtReportCategory(id);
	}

	/**
	* Returns the dm gt report category with the primary key.
	*
	* @param id the primary key of the dm gt report category
	* @return the dm gt report category
	* @throws PortalException if a dm gt report category with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmGtReportCategory getDmGtReportCategory(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmGtReportCategory(id);
	}

	

	/**
	* Returns a range of all the dm gt report categories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of dm gt report categories
	* @param end the upper bound of the range of dm gt report categories (not inclusive)
	* @return the range of dm gt report categories
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmGtReportCategory> getDmGtReportCategories(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmGtReportCategories(start, end);
	}

	/**
	* Returns the number of dm gt report categories.
	*
	* @return the number of dm gt report categories
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmGtReportCategoriesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmGtReportCategoriesCount();
	}

	/**
	* Updates the dm gt report category in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmGtReportCategory the dm gt report category
	* @return the dm gt report category that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmGtReportCategory updateDmGtReportCategory(
		com.fds.nsw.nghiepvu.model.DmGtReportCategory dmGtReportCategory)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmGtReportCategory(dmGtReportCategory);
	}

	/**
	* Updates the dm gt report category in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmGtReportCategory the dm gt report category
	* @param merge whether to merge the dm gt report category with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm gt report category that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmGtReportCategory updateDmGtReportCategory(
		com.fds.nsw.nghiepvu.model.DmGtReportCategory dmGtReportCategory,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmGtReportCategory(dmGtReportCategory, merge);
	}

	

	

	public static void clearService() {
		_service = null;
	}

	

	

	
}