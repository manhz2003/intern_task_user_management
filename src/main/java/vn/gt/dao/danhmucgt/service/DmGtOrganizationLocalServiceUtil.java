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
 * The utility for the dm gt organization local service. This utility wraps {@link vn.gt.dao.danhmucgt.service.impl.DmGtOrganizationLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmGtOrganizationLocalService
 * @see vn.gt.dao.danhmucgt.service.base.DmGtOrganizationLocalServiceBaseImpl
 * @see vn.gt.dao.danhmucgt.service.impl.DmGtOrganizationLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmucgt.service.impl.DmGtOrganizationLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class DmGtOrganizationLocalServiceUtil {
public DmGtOrganizationLocalServiceUtil(DmGtOrganizationLocalServiceImpl service) {
DmGtOrganizationLocalServiceUtil._service = service;
}
public static DmGtOrganizationLocalServiceImpl getService() {
return _service;
}
private static DmGtOrganizationLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmucgt.service.impl.DmGtOrganizationLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dm gt organization to the database. Also notifies the appropriate model listeners.
	*
	* @param dmGtOrganization the dm gt organization
	* @return the dm gt organization that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmGtOrganization addDmGtOrganization(
		com.fds.nsw.nghiepvu.model.DmGtOrganization dmGtOrganization)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmGtOrganization(dmGtOrganization);
	}

	/**
	* Creates a new dm gt organization with the primary key. Does not add the dm gt organization to the database.
	*
	* @param id the primary key for the new dm gt organization
	* @return the new dm gt organization
	*/
	public static com.fds.nsw.nghiepvu.model.DmGtOrganization createDmGtOrganization(
		long id) {
		return getService().createDmGtOrganization(id);
	}

	/**
	* Deletes the dm gt organization with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the dm gt organization
	* @throws PortalException if a dm gt organization with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmGtOrganization(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmGtOrganization(id);
	}

	/**
	* Deletes the dm gt organization from the database. Also notifies the appropriate model listeners.
	*
	* @param dmGtOrganization the dm gt organization
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmGtOrganization(
		com.fds.nsw.nghiepvu.model.DmGtOrganization dmGtOrganization)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmGtOrganization(dmGtOrganization);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.DmGtOrganization fetchDmGtOrganization(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmGtOrganization(id);
	}

	/**
	* Returns the dm gt organization with the primary key.
	*
	* @param id the primary key of the dm gt organization
	* @return the dm gt organization
	* @throws PortalException if a dm gt organization with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmGtOrganization getDmGtOrganization(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmGtOrganization(id);
	}

	

	/**
	* Returns a range of all the dm gt organizations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of dm gt organizations
	* @param end the upper bound of the range of dm gt organizations (not inclusive)
	* @return the range of dm gt organizations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmGtOrganization> getDmGtOrganizations(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmGtOrganizations(start, end);
	}

	/**
	* Returns the number of dm gt organizations.
	*
	* @return the number of dm gt organizations
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmGtOrganizationsCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmGtOrganizationsCount();
	}

	/**
	* Updates the dm gt organization in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmGtOrganization the dm gt organization
	* @return the dm gt organization that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmGtOrganization updateDmGtOrganization(
		com.fds.nsw.nghiepvu.model.DmGtOrganization dmGtOrganization)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmGtOrganization(dmGtOrganization);
	}

	/**
	* Updates the dm gt organization in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmGtOrganization the dm gt organization
	* @param merge whether to merge the dm gt organization with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm gt organization that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmGtOrganization updateDmGtOrganization(
		com.fds.nsw.nghiepvu.model.DmGtOrganization dmGtOrganization,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmGtOrganization(dmGtOrganization, merge);
	}

	

	

	public static com.fds.nsw.nghiepvu.model.DmGtOrganization findByOrganizationCode(
		java.lang.String organizationCode) {
		return getService().findByOrganizationCode(organizationCode);
	}

	public static void clearService() {
		_service = null;
	}

	

	


}