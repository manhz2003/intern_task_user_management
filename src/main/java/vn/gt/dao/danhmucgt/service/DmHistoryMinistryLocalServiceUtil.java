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
 * The utility for the dm history ministry local service. This utility wraps {@link vn.gt.dao.danhmucgt.service.impl.DmHistoryMinistryLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmHistoryMinistryLocalService
 * @see vn.gt.dao.danhmucgt.service.base.DmHistoryMinistryLocalServiceBaseImpl
 * @see vn.gt.dao.danhmucgt.service.impl.DmHistoryMinistryLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmucgt.service.impl.DmHistoryMinistryLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class DmHistoryMinistryLocalServiceUtil {
public DmHistoryMinistryLocalServiceUtil(DmHistoryMinistryLocalServiceImpl service) {
DmHistoryMinistryLocalServiceUtil._service = service;
}
public static DmHistoryMinistryLocalServiceImpl getService() {
return _service;
}
private static DmHistoryMinistryLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmucgt.service.impl.DmHistoryMinistryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dm history ministry to the database. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryMinistry the dm history ministry
	* @return the dm history ministry that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryMinistry addDmHistoryMinistry(
		com.fds.nsw.nghiepvu.model.DmHistoryMinistry dmHistoryMinistry)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmHistoryMinistry(dmHistoryMinistry);
	}

	/**
	* Creates a new dm history ministry with the primary key. Does not add the dm history ministry to the database.
	*
	* @param id the primary key for the new dm history ministry
	* @return the new dm history ministry
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryMinistry createDmHistoryMinistry(
		long id) {
		return getService().createDmHistoryMinistry(id);
	}

	/**
	* Deletes the dm history ministry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the dm history ministry
	* @throws PortalException if a dm history ministry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmHistoryMinistry(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmHistoryMinistry(id);
	}

	/**
	* Deletes the dm history ministry from the database. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryMinistry the dm history ministry
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmHistoryMinistry(
		com.fds.nsw.nghiepvu.model.DmHistoryMinistry dmHistoryMinistry)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmHistoryMinistry(dmHistoryMinistry);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.DmHistoryMinistry fetchDmHistoryMinistry(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmHistoryMinistry(id);
	}

	/**
	* Returns the dm history ministry with the primary key.
	*
	* @param id the primary key of the dm history ministry
	* @return the dm history ministry
	* @throws PortalException if a dm history ministry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryMinistry getDmHistoryMinistry(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryMinistry(id);
	}

	

	/**
	* Returns a range of all the dm history ministries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of dm history ministries
	* @param end the upper bound of the range of dm history ministries (not inclusive)
	* @return the range of dm history ministries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmHistoryMinistry> getDmHistoryMinistries(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryMinistries(start, end);
	}

	/**
	* Returns the number of dm history ministries.
	*
	* @return the number of dm history ministries
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmHistoryMinistriesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryMinistriesCount();
	}

	/**
	* Updates the dm history ministry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryMinistry the dm history ministry
	* @return the dm history ministry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryMinistry updateDmHistoryMinistry(
		com.fds.nsw.nghiepvu.model.DmHistoryMinistry dmHistoryMinistry)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmHistoryMinistry(dmHistoryMinistry);
	}

	/**
	* Updates the dm history ministry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryMinistry the dm history ministry
	* @param merge whether to merge the dm history ministry with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm history ministry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryMinistry updateDmHistoryMinistry(
		com.fds.nsw.nghiepvu.model.DmHistoryMinistry dmHistoryMinistry,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmHistoryMinistry(dmHistoryMinistry, merge);
	}

	

	

	public static void clearService() {
		_service = null;
	}

	

	

	
}