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
 * The utility for the dm sync category local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmSyncCategoryLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmSyncCategoryLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmSyncCategoryLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmSyncCategoryLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmSyncCategoryLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class DmSyncCategoryLocalServiceUtil {
public DmSyncCategoryLocalServiceUtil(DmSyncCategoryLocalServiceImpl service) {
DmSyncCategoryLocalServiceUtil._service = service;
}
public static DmSyncCategoryLocalServiceImpl getService() {
return _service;
}
private static DmSyncCategoryLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmSyncCategoryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dm sync category to the database. Also notifies the appropriate model listeners.
	*
	* @param dmSyncCategory the dm sync category
	* @return the dm sync category that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmSyncCategory addDmSyncCategory(
		com.fds.nsw.nghiepvu.model.DmSyncCategory dmSyncCategory)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmSyncCategory(dmSyncCategory);
	}

	/**
	* Creates a new dm sync category with the primary key. Does not add the dm sync category to the database.
	*
	* @param id the primary key for the new dm sync category
	* @return the new dm sync category
	*/
	public static com.fds.nsw.nghiepvu.model.DmSyncCategory createDmSyncCategory(
		long id) {
		return getService().createDmSyncCategory(id);
	}

	/**
	* Deletes the dm sync category with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the dm sync category
	* @throws PortalException if a dm sync category with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmSyncCategory(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmSyncCategory(id);
	}

	public static org.json.JSONArray findDmSyncCategorys(
			java.lang.String categoryIdGroup, int start, int end) {
		return getService().findDmSyncCategorys(categoryIdGroup, start, end);
	}

	public static long countDmSyncCategorys(java.lang.String categoryIdGroup) {
		return getService().countDmSyncCategorys(categoryIdGroup);
	}


	/**
	* Deletes the dm sync category from the database. Also notifies the appropriate model listeners.
	*
	* @param dmSyncCategory the dm sync category
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmSyncCategory(
		com.fds.nsw.nghiepvu.model.DmSyncCategory dmSyncCategory)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmSyncCategory(dmSyncCategory);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.DmSyncCategory fetchDmSyncCategory(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmSyncCategory(id);
	}

	/**
	* Returns the dm sync category with the primary key.
	*
	* @param id the primary key of the dm sync category
	* @return the dm sync category
	* @throws PortalException if a dm sync category with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmSyncCategory getDmSyncCategory(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmSyncCategory(id);
	}

	

	/**
	* Returns a range of all the dm sync categories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of dm sync categories
	* @param end the upper bound of the range of dm sync categories (not inclusive)
	* @return the range of dm sync categories
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmSyncCategory> getDmSyncCategories(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmSyncCategories(start, end);
	}

	/**
	* Returns the number of dm sync categories.
	*
	* @return the number of dm sync categories
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmSyncCategoriesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmSyncCategoriesCount();
	}

	/**
	* Updates the dm sync category in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmSyncCategory the dm sync category
	* @return the dm sync category that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmSyncCategory updateDmSyncCategory(
		com.fds.nsw.nghiepvu.model.DmSyncCategory dmSyncCategory)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmSyncCategory(dmSyncCategory);
	}

	/**
	* Updates the dm sync category in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmSyncCategory the dm sync category
	* @param merge whether to merge the dm sync category with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm sync category that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmSyncCategory updateDmSyncCategory(
		com.fds.nsw.nghiepvu.model.DmSyncCategory dmSyncCategory, boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmSyncCategory(dmSyncCategory, merge);
	}

	

	

	public static void clearService() {
		_service = null;
	}

	

	

	
}