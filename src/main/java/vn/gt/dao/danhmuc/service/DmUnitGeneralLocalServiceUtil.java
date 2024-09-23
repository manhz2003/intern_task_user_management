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
 * The utility for the dm unit general local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmUnitGeneralLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmUnitGeneralLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmUnitGeneralLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmUnitGeneralLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmUnitGeneralLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class DmUnitGeneralLocalServiceUtil {
public DmUnitGeneralLocalServiceUtil(DmUnitGeneralLocalServiceImpl service) {
DmUnitGeneralLocalServiceUtil._service = service;
}
public static DmUnitGeneralLocalServiceImpl getService() {
return _service;
}
private static DmUnitGeneralLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmUnitGeneralLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dm unit general to the database. Also notifies the appropriate model listeners.
	*
	* @param dmUnitGeneral the dm unit general
	* @return the dm unit general that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmUnitGeneral addDmUnitGeneral(
		com.fds.nsw.nghiepvu.model.DmUnitGeneral dmUnitGeneral)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmUnitGeneral(dmUnitGeneral);
	}

	/**
	* Creates a new dm unit general with the primary key. Does not add the dm unit general to the database.
	*
	* @param id the primary key for the new dm unit general
	* @return the new dm unit general
	*/
	public static com.fds.nsw.nghiepvu.model.DmUnitGeneral createDmUnitGeneral(
		int id) {
		return getService().createDmUnitGeneral(id);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmUnitGeneral> findByUnitName(
			java.lang.String unitName, int start, int end) {
		return getService().findByUnitName(unitName, start, end);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmUnitGeneral> findUnitGenerals(
			java.lang.String unitName, java.lang.String isDelete,
			java.lang.String unitCodeGroup, int start, int end) {
		return getService()
				.findUnitGenerals(unitName, isDelete, unitCodeGroup, start,
						end);
	}

	public static long countUnitGenerals(java.lang.String unitName,
										 java.lang.String isDelete, java.lang.String unitCodeGroup) {
		return getService().countUnitGenerals(unitName, isDelete, unitCodeGroup);
	}


	/**
	* Deletes the dm unit general with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the dm unit general
	* @throws PortalException if a dm unit general with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmUnitGeneral(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmUnitGeneral(id);
	}

	/**
	* Deletes the dm unit general from the database. Also notifies the appropriate model listeners.
	*
	* @param dmUnitGeneral the dm unit general
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmUnitGeneral(
		com.fds.nsw.nghiepvu.model.DmUnitGeneral dmUnitGeneral)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmUnitGeneral(dmUnitGeneral);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.DmUnitGeneral fetchDmUnitGeneral(
		int id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmUnitGeneral(id);
	}

	/**
	* Returns the dm unit general with the primary key.
	*
	* @param id the primary key of the dm unit general
	* @return the dm unit general
	* @throws PortalException if a dm unit general with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmUnitGeneral getDmUnitGeneral(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmUnitGeneral(id);
	}

	

	/**
	* Returns a range of all the dm unit generals.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of dm unit generals
	* @param end the upper bound of the range of dm unit generals (not inclusive)
	* @return the range of dm unit generals
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmUnitGeneral> getDmUnitGenerals(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmUnitGenerals(start, end);
	}

	/**
	* Returns the number of dm unit generals.
	*
	* @return the number of dm unit generals
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmUnitGeneralsCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmUnitGeneralsCount();
	}

	/**
	* Updates the dm unit general in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmUnitGeneral the dm unit general
	* @return the dm unit general that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmUnitGeneral updateDmUnitGeneral(
		com.fds.nsw.nghiepvu.model.DmUnitGeneral dmUnitGeneral)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmUnitGeneral(dmUnitGeneral);
	}

	/**
	* Updates the dm unit general in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmUnitGeneral the dm unit general
	* @param merge whether to merge the dm unit general with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm unit general that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmUnitGeneral updateDmUnitGeneral(
		com.fds.nsw.nghiepvu.model.DmUnitGeneral dmUnitGeneral, boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmUnitGeneral(dmUnitGeneral, merge);
	}

	

	

	public static com.fds.nsw.nghiepvu.model.DmUnitGeneral getByUnitCode(
		java.lang.String unitCode) {
		return getService().getByUnitCode(unitCode);
	}

	public static com.fds.nsw.nghiepvu.model.DmUnitGeneral getByUnitCodeAndSyncVersion(
		java.lang.String unitCode, java.lang.String syncVersion) {
		return getService().getByUnitCodeAndSyncVersion(unitCode, syncVersion);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}