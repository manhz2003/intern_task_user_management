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
 * The utility for the dm ministry local service. This utility wraps {@link vn.gt.dao.danhmucgt.service.impl.DmMinistryLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmMinistryLocalService
 * @see vn.gt.dao.danhmucgt.service.base.DmMinistryLocalServiceBaseImpl
 * @see vn.gt.dao.danhmucgt.service.impl.DmMinistryLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmucgt.service.impl.DmMinistryLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class DmMinistryLocalServiceUtil {
public DmMinistryLocalServiceUtil(DmMinistryLocalServiceImpl service) {
DmMinistryLocalServiceUtil._service = service;
}
public static DmMinistryLocalServiceImpl getService() {
return _service;
}
private static DmMinistryLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmucgt.service.impl.DmMinistryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dm ministry to the database. Also notifies the appropriate model listeners.
	*
	* @param dmMinistry the dm ministry
	* @return the dm ministry that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmMinistry addDmMinistry(
		com.fds.nsw.nghiepvu.model.DmMinistry dmMinistry)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmMinistry(dmMinistry);
	}

	/**
	* Creates a new dm ministry with the primary key. Does not add the dm ministry to the database.
	*
	* @param id the primary key for the new dm ministry
	* @return the new dm ministry
	*/
	public static com.fds.nsw.nghiepvu.model.DmMinistry createDmMinistry(long id) {
		return getService().createDmMinistry(id);
	}

	/**
	* Deletes the dm ministry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the dm ministry
	* @throws PortalException if a dm ministry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmMinistry(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmMinistry(id);
	}

	/**
	* Deletes the dm ministry from the database. Also notifies the appropriate model listeners.
	*
	* @param dmMinistry the dm ministry
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmMinistry(
		com.fds.nsw.nghiepvu.model.DmMinistry dmMinistry)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmMinistry(dmMinistry);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.DmMinistry fetchDmMinistry(long id)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmMinistry(id);
	}

	/**
	* Returns the dm ministry with the primary key.
	*
	* @param id the primary key of the dm ministry
	* @return the dm ministry
	* @throws PortalException if a dm ministry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmMinistry getDmMinistry(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmMinistry(id);
	}

	

	/**
	* Returns a range of all the dm ministries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of dm ministries
	* @param end the upper bound of the range of dm ministries (not inclusive)
	* @return the range of dm ministries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmMinistry> getDmMinistries(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmMinistries(start, end);
	}

	/**
	* Returns the number of dm ministries.
	*
	* @return the number of dm ministries
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmMinistriesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmMinistriesCount();
	}

	/**
	* Updates the dm ministry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmMinistry the dm ministry
	* @return the dm ministry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmMinistry updateDmMinistry(
		com.fds.nsw.nghiepvu.model.DmMinistry dmMinistry)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmMinistry(dmMinistry);
	}

	/**
	* Updates the dm ministry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmMinistry the dm ministry
	* @param merge whether to merge the dm ministry with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm ministry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmMinistry updateDmMinistry(
		com.fds.nsw.nghiepvu.model.DmMinistry dmMinistry, boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmMinistry(dmMinistry, merge);
	}

	

	

	public static com.fds.nsw.nghiepvu.model.DmMinistry findByMinistryCode(
		java.lang.String ministryCode) {
		return getService().findByMinistryCode(ministryCode);
	}

	/**
	* Returns the number of dm ministries where ministryCode = &#63;.
	*
	* @param ministryCode the ministry code
	* @return the number of matching dm ministries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByMinistryCode(java.lang.String ministryCode) {
		return getService().countByMinistryCode(ministryCode);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}