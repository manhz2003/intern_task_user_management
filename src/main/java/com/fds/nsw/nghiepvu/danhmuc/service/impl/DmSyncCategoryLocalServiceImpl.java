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

package com.fds.nsw.nghiepvu.danhmuc.service.impl;import com.fds.nsw.nghiepvu.danhmuc.service.finder.DmSyncCategoryFinderImpl;import java.util.ArrayList; import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;

import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmSyncCategoryPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import lombok.extern.slf4j.Slf4j;@Slf4j @Service



/**
 * The implementation of the dm sync category local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link vn.gt.dao.danhmuc.service.DmSyncCategoryLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.danhmuc.service.base.DmSyncCategoryLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.DmSyncCategoryLocalServiceUtil
 */
public class DmSyncCategoryLocalServiceImpl
	{ @Autowired
	DmSyncCategoryPersistenceImpl persistence;
		@Autowired
		DmSyncCategoryFinderImpl finder;

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link vn.gt.dao.danhmuc.service.DmSyncCategoryLocalServiceUtil} to access the dm sync category local service.
	 */

		/*
		 * NOTE FOR DEVELOPERS:
		 *
		 * Never modify or reference this class directly. Always use {@link vn.gt.dao.danhmuc.service.DmSyncCategoryLocalServiceUtil} to access the dm sync category local service.
		 */

		/**
		 * Adds the dm sync category to the database. Also notifies the appropriate model listeners.
		 *
		 * @param dmSyncCategory the dm sync category
		 * @return the dm sync category that was added
		 * @throws SystemException if a system exception occurred
		 */

		public JSONArray findDmSyncCategorys(String categoryIdGroup, int start,
											 int end) {
			try {
				return finder.findDmSyncCategorys(categoryIdGroup,
						start, end);
			} catch (Exception e) {
				log.error(e.getMessage());
				return null;
			}
		}

		public long countDmSyncCategorys(String categoryIdGroup) {
			try {
				return finder.countDmSyncCategorys(categoryIdGroup);
			} catch (Exception e) {
				log.error(e.getMessage());
				return 0;
			}
		}


		public DmSyncCategory addDmSyncCategory(DmSyncCategory dmSyncCategory)
				throws SystemException {

			dmSyncCategory = persistence.updateImpl(dmSyncCategory, false);





			return dmSyncCategory;
		}

		/**
		 * Creates a new dm sync category with the primary key. Does not add the dm sync category to the database.
		 *
		 * @param id the primary key for the new dm sync category
		 * @return the new dm sync category
		 */
		public DmSyncCategory createDmSyncCategory(long id) {
			return persistence.create(id);
		}

		/**
		 * Deletes the dm sync category with the primary key from the database. Also notifies the appropriate model listeners.
		 *
		 * @param id the primary key of the dm sync category
		 * @throws PortalException if a dm sync category with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteDmSyncCategory(long id)
				throws PortalException, SystemException {
			DmSyncCategory dmSyncCategory = persistence.remove(id);




		}

		/**
		 * Deletes the dm sync category from the database. Also notifies the appropriate model listeners.
		 *
		 * @param dmSyncCategory the dm sync category
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteDmSyncCategory(DmSyncCategory dmSyncCategory)
				throws SystemException {
			persistence.remove(dmSyncCategory);




		}













		public DmSyncCategory fetchDmSyncCategory(long id)
				throws SystemException {
			return persistence.fetchByPrimaryKey(id);
		}

		/**
		 * Returns the dm sync category with the primary key.
		 *
		 * @param id the primary key of the dm sync category
		 * @return the dm sync category
		 * @throws PortalException if a dm sync category with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public DmSyncCategory getDmSyncCategory(long id)
				throws PortalException, SystemException {
			return persistence.findByPrimaryKey(id);
		}


		/**
		 * Returns a range of all the dm sync categories.
		 *
		 * <p>
		 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
		 * </p>
		 *
		 * @param start the lower bound of the range of dm sync categories
		 * @param end the upper bound of the range of dm sync categories (not inclusive)
		 * @return the range of dm sync categories
		 * @throws SystemException if a system exception occurred
		 */
		public List<DmSyncCategory> getDmSyncCategories(int start, int end)
				throws SystemException {
			return persistence.findAll(start, end);
		}

		/**
		 * Returns the number of dm sync categories.
		 *
		 * @return the number of dm sync categories
		 * @throws SystemException if a system exception occurred
		 */
		public int getDmSyncCategoriesCount() throws SystemException {
			return persistence.countAll();
		}

		/**
		 * Updates the dm sync category in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param dmSyncCategory the dm sync category
		 * @return the dm sync category that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public DmSyncCategory updateDmSyncCategory(DmSyncCategory dmSyncCategory)
				throws SystemException {
			return updateDmSyncCategory(dmSyncCategory, true);
		}

		/**
		 * Updates the dm sync category in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param dmSyncCategory the dm sync category
		 * @param merge whether to merge the dm sync category with the current session. See  for an explanation.
		 * @return the dm sync category that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public DmSyncCategory updateDmSyncCategory(DmSyncCategory dmSyncCategory,
												   boolean merge) throws SystemException {

			dmSyncCategory = persistence.updateImpl(dmSyncCategory, merge);





			return dmSyncCategory;
		}
}