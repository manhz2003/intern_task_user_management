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

import com.fds.nsw.nghiepvu.danhmucgt.service.persistence.DmHistoryMinistryPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;import lombok.extern.slf4j.Slf4j;@Slf4j @Service



/**
 * The implementation of the dm history ministry local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link vn.gt.dao.danhmucgt.service.DmHistoryMinistryLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.danhmucgt.service.base.DmHistoryMinistryLocalServiceBaseImpl
 * @see vn.gt.dao.danhmucgt.service.DmHistoryMinistryLocalServiceUtil
 */
public class DmHistoryMinistryLocalServiceImpl
	{ @Autowired
	DmHistoryMinistryPersistenceImpl persistence;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link vn.gt.dao.danhmucgt.service.DmHistoryMinistryLocalServiceUtil} to access the dm history ministry local service.
	 */

		/*
		 * NOTE FOR DEVELOPERS:
		 *
		 * Never modify or reference this class directly. Always use {@link vn.gt.dao.danhmucgt.service.DmHistoryMinistryLocalServiceUtil} to access the dm history ministry local service.
		 */

		/**
		 * Adds the dm history ministry to the database. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryMinistry the dm history ministry
		 * @return the dm history ministry that was added
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryMinistry addDmHistoryMinistry(
				DmHistoryMinistry dmHistoryMinistry) throws SystemException {

			dmHistoryMinistry = persistence.updateImpl(dmHistoryMinistry,
					false);





			return dmHistoryMinistry;
		}

		/**
		 * Creates a new dm history ministry with the primary key. Does not add the dm history ministry to the database.
		 *
		 * @param id the primary key for the new dm history ministry
		 * @return the new dm history ministry
		 */
		public DmHistoryMinistry createDmHistoryMinistry(long id) {
			return persistence.create(id);
		}

		/**
		 * Deletes the dm history ministry with the primary key from the database. Also notifies the appropriate model listeners.
		 *
		 * @param id the primary key of the dm history ministry
		 * @throws PortalException if a dm history ministry with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteDmHistoryMinistry(long id)
				throws PortalException, SystemException {
			DmHistoryMinistry dmHistoryMinistry = persistence.remove(id);




		}

		/**
		 * Deletes the dm history ministry from the database. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryMinistry the dm history ministry
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteDmHistoryMinistry(DmHistoryMinistry dmHistoryMinistry)
				throws SystemException {
			persistence.remove(dmHistoryMinistry);




		}













		public DmHistoryMinistry fetchDmHistoryMinistry(long id)
				throws SystemException {
			return persistence.fetchByPrimaryKey(id);
		}

		/**
		 * Returns the dm history ministry with the primary key.
		 *
		 * @param id the primary key of the dm history ministry
		 * @return the dm history ministry
		 * @throws PortalException if a dm history ministry with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryMinistry getDmHistoryMinistry(long id)
				throws PortalException, SystemException {
			return persistence.findByPrimaryKey(id);
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
		public List<DmHistoryMinistry> getDmHistoryMinistries(int start, int end)
				throws SystemException {
			return persistence.findAll(start, end);
		}

		/**
		 * Returns the number of dm history ministries.
		 *
		 * @return the number of dm history ministries
		 * @throws SystemException if a system exception occurred
		 */
		public int getDmHistoryMinistriesCount() throws SystemException {
			return persistence.countAll();
		}

		/**
		 * Updates the dm history ministry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryMinistry the dm history ministry
		 * @return the dm history ministry that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryMinistry updateDmHistoryMinistry(
				DmHistoryMinistry dmHistoryMinistry) throws SystemException {
			return updateDmHistoryMinistry(dmHistoryMinistry, true);
		}

		/**
		 * Updates the dm history ministry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryMinistry the dm history ministry
		 * @param merge whether to merge the dm history ministry with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
		 * @return the dm history ministry that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryMinistry updateDmHistoryMinistry(
				DmHistoryMinistry dmHistoryMinistry, boolean merge)
				throws SystemException {

			dmHistoryMinistry = persistence.updateImpl(dmHistoryMinistry,
					merge);





			return dmHistoryMinistry;
		}
}