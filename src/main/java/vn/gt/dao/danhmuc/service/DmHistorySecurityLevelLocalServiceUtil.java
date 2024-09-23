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
 * The utility for the dm history security level local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmHistorySecurityLevelLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmHistorySecurityLevelLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmHistorySecurityLevelLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmHistorySecurityLevelLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmHistorySecurityLevelLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class DmHistorySecurityLevelLocalServiceUtil {
public DmHistorySecurityLevelLocalServiceUtil(DmHistorySecurityLevelLocalServiceImpl service) {
DmHistorySecurityLevelLocalServiceUtil._service = service;
}
public static DmHistorySecurityLevelLocalServiceImpl getService() {
return _service;
}
private static DmHistorySecurityLevelLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmHistorySecurityLevelLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dm history security level to the database. Also notifies the appropriate model listeners.
	*
	* @param dmHistorySecurityLevel the dm history security level
	* @return the dm history security level that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistorySecurityLevel addDmHistorySecurityLevel(
		com.fds.nsw.nghiepvu.model.DmHistorySecurityLevel dmHistorySecurityLevel)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmHistorySecurityLevel(dmHistorySecurityLevel);
	}

	/**
	* Creates a new dm history security level with the primary key. Does not add the dm history security level to the database.
	*
	* @param id the primary key for the new dm history security level
	* @return the new dm history security level
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistorySecurityLevel createDmHistorySecurityLevel(
		int id) {
		return getService().createDmHistorySecurityLevel(id);
	}

	/**
	* Deletes the dm history security level with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the dm history security level
	* @throws PortalException if a dm history security level with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmHistorySecurityLevel(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmHistorySecurityLevel(id);
	}

	/**
	* Deletes the dm history security level from the database. Also notifies the appropriate model listeners.
	*
	* @param dmHistorySecurityLevel the dm history security level
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmHistorySecurityLevel(
		com.fds.nsw.nghiepvu.model.DmHistorySecurityLevel dmHistorySecurityLevel)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmHistorySecurityLevel(dmHistorySecurityLevel);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.DmHistorySecurityLevel fetchDmHistorySecurityLevel(
		int id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmHistorySecurityLevel(id);
	}

	/**
	* Returns the dm history security level with the primary key.
	*
	* @param id the primary key of the dm history security level
	* @return the dm history security level
	* @throws PortalException if a dm history security level with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistorySecurityLevel getDmHistorySecurityLevel(
		int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistorySecurityLevel(id);
	}

	

	/**
	* Returns a range of all the dm history security levels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of dm history security levels
	* @param end the upper bound of the range of dm history security levels (not inclusive)
	* @return the range of dm history security levels
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmHistorySecurityLevel> getDmHistorySecurityLevels(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistorySecurityLevels(start, end);
	}

	/**
	* Returns the number of dm history security levels.
	*
	* @return the number of dm history security levels
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmHistorySecurityLevelsCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistorySecurityLevelsCount();
	}

	/**
	* Updates the dm history security level in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmHistorySecurityLevel the dm history security level
	* @return the dm history security level that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistorySecurityLevel updateDmHistorySecurityLevel(
		com.fds.nsw.nghiepvu.model.DmHistorySecurityLevel dmHistorySecurityLevel)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmHistorySecurityLevel(dmHistorySecurityLevel);
	}

	/**
	* Updates the dm history security level in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmHistorySecurityLevel the dm history security level
	* @param merge whether to merge the dm history security level with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm history security level that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistorySecurityLevel updateDmHistorySecurityLevel(
		com.fds.nsw.nghiepvu.model.DmHistorySecurityLevel dmHistorySecurityLevel,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .updateDmHistorySecurityLevel(dmHistorySecurityLevel, merge);
	}

	

	

	public static com.fds.nsw.nghiepvu.model.DmHistorySecurityLevel getBySecurityLevelCodeAndSyncVersion(
		java.lang.String securityLevelCode, java.lang.String syncVersion) {
		return getService()
				   .getBySecurityLevelCodeAndSyncVersion(securityLevelCode,
			syncVersion);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}