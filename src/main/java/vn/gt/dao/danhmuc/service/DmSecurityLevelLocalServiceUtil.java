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
 * The utility for the dm security level local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmSecurityLevelLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmSecurityLevelLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmSecurityLevelLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmSecurityLevelLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmSecurityLevelLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class DmSecurityLevelLocalServiceUtil {
public DmSecurityLevelLocalServiceUtil(DmSecurityLevelLocalServiceImpl service) {
DmSecurityLevelLocalServiceUtil._service = service;
}
public static DmSecurityLevelLocalServiceImpl getService() {
return _service;
}
private static DmSecurityLevelLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmSecurityLevelLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dm security level to the database. Also notifies the appropriate model listeners.
	*
	* @param dmSecurityLevel the dm security level
	* @return the dm security level that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmSecurityLevel addDmSecurityLevel(
		com.fds.nsw.nghiepvu.model.DmSecurityLevel dmSecurityLevel)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmSecurityLevel(dmSecurityLevel);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmSecurityLevel> findBySecurityLevel(
			java.lang.String securityLevel, int start, int end) {
		return getService().findBySecurityLevel(securityLevel, start, end);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmSecurityLevel> findBySecurityLevelName(
			java.lang.String securityLevelName, int start, int end) {
		return getService()
				.findBySecurityLevelName(securityLevelName, start, end);
	}


	/**
	* Creates a new dm security level with the primary key. Does not add the dm security level to the database.
	*
	* @param id the primary key for the new dm security level
	* @return the new dm security level
	*/
	public static com.fds.nsw.nghiepvu.model.DmSecurityLevel createDmSecurityLevel(
		int id) {
		return getService().createDmSecurityLevel(id);
	}

	/**
	* Deletes the dm security level with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the dm security level
	* @throws PortalException if a dm security level with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmSecurityLevel(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmSecurityLevel(id);
	}

	/**
	* Deletes the dm security level from the database. Also notifies the appropriate model listeners.
	*
	* @param dmSecurityLevel the dm security level
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmSecurityLevel(
		com.fds.nsw.nghiepvu.model.DmSecurityLevel dmSecurityLevel)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmSecurityLevel(dmSecurityLevel);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.DmSecurityLevel fetchDmSecurityLevel(
		int id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmSecurityLevel(id);
	}

	/**
	* Returns the dm security level with the primary key.
	*
	* @param id the primary key of the dm security level
	* @return the dm security level
	* @throws PortalException if a dm security level with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmSecurityLevel getDmSecurityLevel(
		int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmSecurityLevel(id);
	}

	

	/**
	* Returns a range of all the dm security levels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of dm security levels
	* @param end the upper bound of the range of dm security levels (not inclusive)
	* @return the range of dm security levels
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmSecurityLevel> getDmSecurityLevels(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmSecurityLevels(start, end);
	}

	/**
	* Returns the number of dm security levels.
	*
	* @return the number of dm security levels
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmSecurityLevelsCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmSecurityLevelsCount();
	}

	/**
	* Updates the dm security level in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmSecurityLevel the dm security level
	* @return the dm security level that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmSecurityLevel updateDmSecurityLevel(
		com.fds.nsw.nghiepvu.model.DmSecurityLevel dmSecurityLevel)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmSecurityLevel(dmSecurityLevel);
	}

	/**
	* Updates the dm security level in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmSecurityLevel the dm security level
	* @param merge whether to merge the dm security level with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm security level that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmSecurityLevel updateDmSecurityLevel(
		com.fds.nsw.nghiepvu.model.DmSecurityLevel dmSecurityLevel, boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmSecurityLevel(dmSecurityLevel, merge);
	}

	

	

	public static com.fds.nsw.nghiepvu.model.DmSecurityLevel getBySecurityLevelCode(
		java.lang.String securityLevelCode) {
		return getService().getBySecurityLevelCode(securityLevelCode);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}