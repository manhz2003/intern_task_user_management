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

import com.fds.nsw.nghiepvu.danhmucgt.service.persistence.DmGtReportCategoryPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;import lombok.extern.slf4j.Slf4j;@Slf4j @Service



/**
 * The implementation of the dm gt report category local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link vn.gt.dao.danhmucgt.service.DmGtReportCategoryLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.danhmucgt.service.base.DmGtReportCategoryLocalServiceBaseImpl
 * @see vn.gt.dao.danhmucgt.service.DmGtReportCategoryLocalServiceUtil
 */
public class DmGtReportCategoryLocalServiceImpl
	{ @Autowired
	DmGtReportCategoryPersistenceImpl persistence;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link vn.gt.dao.danhmucgt.service.DmGtReportCategoryLocalServiceUtil} to access the dm gt report category local service.
	 */

		/*
		 * NOTE FOR DEVELOPERS:
		 *
		 * Never modify or reference this class directly. Always use {@link vn.gt.dao.danhmucgt.service.DmGtReportCategoryLocalServiceUtil} to access the dm gt report category local service.
		 */

		/**
		 * Adds the dm gt report category to the database. Also notifies the appropriate model listeners.
		 *
		 * @param dmGtReportCategory the dm gt report category
		 * @return the dm gt report category that was added
		 * @throws SystemException if a system exception occurred
		 */
		public DmGtReportCategory addDmGtReportCategory(
				DmGtReportCategory dmGtReportCategory) throws SystemException {

			dmGtReportCategory = persistence.updateImpl(dmGtReportCategory,
					false);





			return dmGtReportCategory;
		}

		public DmGtReportCategory findByOrganizationCode(String category) {
			try {
				return persistence.fetchByOrganizationCode(
						category, 0);
			} catch (SystemException e) {
				log.error(e.getMessage());
			}

			return null;
		}

		public List<DmGtReportCategory> findByIsDelete(int isDelete, int start,
													   int end) {
			try {
				return persistence.findByF_isDelete(isDelete,
						start, end);
			} catch (SystemException e) {
				log.error(e.getMessage());
			}

			return null;
		}


		/**
		 * Creates a new dm gt report category with the primary key. Does not add the dm gt report category to the database.
		 *
		 * @param id the primary key for the new dm gt report category
		 * @return the new dm gt report category
		 */
		public DmGtReportCategory createDmGtReportCategory(long id) {
			return persistence.create(id);
		}

		/**
		 * Deletes the dm gt report category with the primary key from the database. Also notifies the appropriate model listeners.
		 *
		 * @param id the primary key of the dm gt report category
		 * @throws PortalException if a dm gt report category with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteDmGtReportCategory(long id)
				throws PortalException, SystemException {
			DmGtReportCategory dmGtReportCategory = persistence.remove(id);




		}

		/**
		 * Deletes the dm gt report category from the database. Also notifies the appropriate model listeners.
		 *
		 * @param dmGtReportCategory the dm gt report category
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteDmGtReportCategory(DmGtReportCategory dmGtReportCategory)
				throws SystemException {
			persistence.remove(dmGtReportCategory);




		}













		public DmGtReportCategory fetchDmGtReportCategory(long id)
				throws SystemException {
			return persistence.fetchByPrimaryKey(id);
		}

		/**
		 * Returns the dm gt report category with the primary key.
		 *
		 * @param id the primary key of the dm gt report category
		 * @return the dm gt report category
		 * @throws PortalException if a dm gt report category with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public DmGtReportCategory getDmGtReportCategory(long id)
				throws PortalException, SystemException {
			return persistence.findByPrimaryKey(id);
		}


		/**
		 * Returns a range of all the dm gt report categories.
		 *
		 * <p>
		 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
		 * </p>
		 *
		 * @param start the lower bound of the range of dm gt report categories
		 * @param end the upper bound of the range of dm gt report categories (not inclusive)
		 * @return the range of dm gt report categories
		 * @throws SystemException if a system exception occurred
		 */
		public List<DmGtReportCategory> getDmGtReportCategories(int start, int end)
				throws SystemException {
			return persistence.findAll(start, end);
		}

		/**
		 * Returns the number of dm gt report categories.
		 *
		 * @return the number of dm gt report categories
		 * @throws SystemException if a system exception occurred
		 */
		public int getDmGtReportCategoriesCount() throws SystemException {
			return persistence.countAll();
		}

		/**
		 * Updates the dm gt report category in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param dmGtReportCategory the dm gt report category
		 * @return the dm gt report category that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public DmGtReportCategory updateDmGtReportCategory(
				DmGtReportCategory dmGtReportCategory) throws SystemException {
			return updateDmGtReportCategory(dmGtReportCategory, true);
		}

		/**
		 * Updates the dm gt report category in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param dmGtReportCategory the dm gt report category
		 * @param merge whether to merge the dm gt report category with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
		 * @return the dm gt report category that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public DmGtReportCategory updateDmGtReportCategory(
				DmGtReportCategory dmGtReportCategory, boolean merge)
				throws SystemException {

			dmGtReportCategory = persistence.updateImpl(dmGtReportCategory,
					merge);





			return dmGtReportCategory;
		}
}