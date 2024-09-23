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
 * The utility for the dm history unit general local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmHistoryUnitGeneralLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmHistoryUnitGeneralLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmHistoryUnitGeneralLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmHistoryUnitGeneralLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmHistoryUnitGeneralLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class DmHistoryUnitGeneralLocalServiceUtil {
public DmHistoryUnitGeneralLocalServiceUtil(DmHistoryUnitGeneralLocalServiceImpl service) {
DmHistoryUnitGeneralLocalServiceUtil._service = service;
}
public static DmHistoryUnitGeneralLocalServiceImpl getService() {
return _service;
}
private static DmHistoryUnitGeneralLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmHistoryUnitGeneralLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dm history unit general to the database. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryUnitGeneral the dm history unit general
	* @return the dm history unit general that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryUnitGeneral addDmHistoryUnitGeneral(
		com.fds.nsw.nghiepvu.model.DmHistoryUnitGeneral dmHistoryUnitGeneral)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmHistoryUnitGeneral(dmHistoryUnitGeneral);
	}

	/**
	* Creates a new dm history unit general with the primary key. Does not add the dm history unit general to the database.
	*
	* @param id the primary key for the new dm history unit general
	* @return the new dm history unit general
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryUnitGeneral createDmHistoryUnitGeneral(
		int id) {
		return getService().createDmHistoryUnitGeneral(id);
	}

	/**
	* Deletes the dm history unit general with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the dm history unit general
	* @throws PortalException if a dm history unit general with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmHistoryUnitGeneral(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmHistoryUnitGeneral(id);
	}

	/**
	* Deletes the dm history unit general from the database. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryUnitGeneral the dm history unit general
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmHistoryUnitGeneral(
		com.fds.nsw.nghiepvu.model.DmHistoryUnitGeneral dmHistoryUnitGeneral)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmHistoryUnitGeneral(dmHistoryUnitGeneral);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.DmHistoryUnitGeneral fetchDmHistoryUnitGeneral(
		int id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmHistoryUnitGeneral(id);
	}

	/**
	* Returns the dm history unit general with the primary key.
	*
	* @param id the primary key of the dm history unit general
	* @return the dm history unit general
	* @throws PortalException if a dm history unit general with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryUnitGeneral getDmHistoryUnitGeneral(
		int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryUnitGeneral(id);
	}

	

	/**
	* Returns a range of all the dm history unit generals.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of dm history unit generals
	* @param end the upper bound of the range of dm history unit generals (not inclusive)
	* @return the range of dm history unit generals
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmHistoryUnitGeneral> getDmHistoryUnitGenerals(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryUnitGenerals(start, end);
	}

	/**
	* Returns the number of dm history unit generals.
	*
	* @return the number of dm history unit generals
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmHistoryUnitGeneralsCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryUnitGeneralsCount();
	}

	/**
	* Updates the dm history unit general in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryUnitGeneral the dm history unit general
	* @return the dm history unit general that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryUnitGeneral updateDmHistoryUnitGeneral(
		com.fds.nsw.nghiepvu.model.DmHistoryUnitGeneral dmHistoryUnitGeneral)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmHistoryUnitGeneral(dmHistoryUnitGeneral);
	}

	/**
	* Updates the dm history unit general in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryUnitGeneral the dm history unit general
	* @param merge whether to merge the dm history unit general with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm history unit general that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryUnitGeneral updateDmHistoryUnitGeneral(
		com.fds.nsw.nghiepvu.model.DmHistoryUnitGeneral dmHistoryUnitGeneral,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .updateDmHistoryUnitGeneral(dmHistoryUnitGeneral, merge);
	}

	

	

	public static com.fds.nsw.nghiepvu.model.DmHistoryUnitGeneral getByUnitCodeAndSyncVersion(
		java.lang.String unitCode, java.lang.String syncVersion) {
		return getService().getByUnitCodeAndSyncVersion(unitCode, syncVersion);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}