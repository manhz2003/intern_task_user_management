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

package com.fds.nsw.nghiepvu.danhmucgt.service.impl;import java.util.ArrayList; import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;

import com.fds.nsw.nghiepvu.danhmucgt.service.persistence.DmGtOrganizationPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;import lombok.extern.slf4j.Slf4j;@Slf4j @Service



/**
 * The implementation of the dm gt organization local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link vn.gt.dao.danhmucgt.service.DmGtOrganizationLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.danhmucgt.service.base.DmGtOrganizationLocalServiceBaseImpl
 * @see vn.gt.dao.danhmucgt.service.DmGtOrganizationLocalServiceUtil
 */
public class DmGtOrganizationLocalServiceImpl
	{ @Autowired
	DmGtOrganizationPersistenceImpl persistence;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link vn.gt.dao.danhmucgt.service.DmGtOrganizationLocalServiceUtil} to access the dm gt organization local service.
	 */
	public DmGtOrganization findByOrganizationCode(
			java.lang.String organizationCode){
		try{
			return persistence.fetchByOrganizationCode(organizationCode);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

		/*
		 * NOTE FOR DEVELOPERS:
		 *
		 * Never modify or reference this class directly. Always use {@link vn.gt.dao.danhmucgt.service.DmGtOrganizationLocalServiceUtil} to access the dm gt organization local service.
		 */

		/**
		 * Adds the dm gt organization to the database. Also notifies the appropriate model listeners.
		 *
		 * @param dmGtOrganization the dm gt organization
		 * @return the dm gt organization that was added
		 * @throws SystemException if a system exception occurred
		 */
		public DmGtOrganization addDmGtOrganization(
				DmGtOrganization dmGtOrganization) throws SystemException {

			dmGtOrganization = persistence.updateImpl(dmGtOrganization,
					false);





			return dmGtOrganization;
		}

		/**
		 * Creates a new dm gt organization with the primary key. Does not add the dm gt organization to the database.
		 *
		 * @param id the primary key for the new dm gt organization
		 * @return the new dm gt organization
		 */
		public DmGtOrganization createDmGtOrganization(long id) {
			return persistence.create(id);
		}

		/**
		 * Deletes the dm gt organization with the primary key from the database. Also notifies the appropriate model listeners.
		 *
		 * @param id the primary key of the dm gt organization
		 * @throws PortalException if a dm gt organization with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteDmGtOrganization(long id)
				throws PortalException, SystemException {
			DmGtOrganization dmGtOrganization = persistence.remove(id);




		}

		/**
		 * Deletes the dm gt organization from the database. Also notifies the appropriate model listeners.
		 *
		 * @param dmGtOrganization the dm gt organization
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteDmGtOrganization(DmGtOrganization dmGtOrganization)
				throws SystemException {
			persistence.remove(dmGtOrganization);




		}













		public DmGtOrganization fetchDmGtOrganization(long id)
				throws SystemException {
			return persistence.fetchByPrimaryKey(id);
		}

		/**
		 * Returns the dm gt organization with the primary key.
		 *
		 * @param id the primary key of the dm gt organization
		 * @return the dm gt organization
		 * @throws PortalException if a dm gt organization with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public DmGtOrganization getDmGtOrganization(long id)
				throws PortalException, SystemException {
			return persistence.findByPrimaryKey(id);
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
		public List<DmGtOrganization> getDmGtOrganizations(int start, int end)
				throws SystemException {
			return persistence.findAll(start, end);
		}

		/**
		 * Returns the number of dm gt organizations.
		 *
		 * @return the number of dm gt organizations
		 * @throws SystemException if a system exception occurred
		 */
		public int getDmGtOrganizationsCount() throws SystemException {
			return persistence.countAll();
		}

		/**
		 * Updates the dm gt organization in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param dmGtOrganization the dm gt organization
		 * @return the dm gt organization that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public DmGtOrganization updateDmGtOrganization(
				DmGtOrganization dmGtOrganization) throws SystemException {
			return updateDmGtOrganization(dmGtOrganization, true);
		}

		/**
		 * Updates the dm gt organization in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param dmGtOrganization the dm gt organization
		 * @param merge whether to merge the dm gt organization with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
		 * @return the dm gt organization that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public DmGtOrganization updateDmGtOrganization(
				DmGtOrganization dmGtOrganization, boolean merge)
				throws SystemException {

			dmGtOrganization = persistence.updateImpl(dmGtOrganization,
					merge);





			return dmGtOrganization;
		}
}