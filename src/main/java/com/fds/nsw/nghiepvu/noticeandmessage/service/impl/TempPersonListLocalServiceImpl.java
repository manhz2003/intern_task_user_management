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

package com.fds.nsw.nghiepvu.noticeandmessage.service.impl;import java.util.ArrayList; import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.TempPersonListPersistenceImpl;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;import lombok.extern.slf4j.Slf4j;@Slf4j @Service









/**
 * The implementation of the temp person list local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link vn.gt.dao.noticeandmessage.service.TempPersonListLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.noticeandmessage.service.base.TempPersonListLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.TempPersonListLocalServiceUtil
 */
public class TempPersonListLocalServiceImpl
	{ @Autowired
	TempPersonListPersistenceImpl persistence;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link vn.gt.dao.noticeandmessage.service.TempPersonListLocalServiceUtil} to access the temp person list local service.
	 */
	public List<TempPersonList> findBydocumentNameAnddocumentYearAndRequestCode(int documentName, int documentYear, String requestCode) {
		try {
			return persistence.findBydocumentNameAnddocumentYearAndRequestCode(documentName, documentYear, requestCode);
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			return new ArrayList<TempPersonList>();
		}
	}


		/*
		 * NOTE FOR DEVELOPERS:
		 *
		 * Never modify or reference this class directly. Always use {@link vn.gt.dao.noticeandmessage.service.TempPersonListLocalServiceUtil} to access the temp person list local service.
		 */

		/**
		 * Adds the temp person list to the database. Also notifies the appropriate model listeners.
		 *
		 * @param tempPersonList the temp person list
		 * @return the temp person list that was added
		 * @throws SystemException if a system exception occurred
		 */
		public TempPersonList addTempPersonList(TempPersonList tempPersonList)
				throws SystemException {

			tempPersonList = persistence.updateImpl(tempPersonList, false);





			return tempPersonList;
		}

		/**
		 * Creates a new temp person list with the primary key. Does not add the temp person list to the database.
		 *
		 * @param id the primary key for the new temp person list
		 * @return the new temp person list
		 */
		public TempPersonList createTempPersonList(long id) {
			return persistence.create(id);
		}

		/**
		 * Deletes the temp person list with the primary key from the database. Also notifies the appropriate model listeners.
		 *
		 * @param id the primary key of the temp person list
		 * @throws PortalException if a temp person list with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteTempPersonList(long id)
				throws PortalException, SystemException {
			TempPersonList tempPersonList = persistence.remove(id);




		}

		/**
		 * Deletes the temp person list from the database. Also notifies the appropriate model listeners.
		 *
		 * @param tempPersonList the temp person list
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteTempPersonList(TempPersonList tempPersonList)
				throws SystemException {
			persistence.remove(tempPersonList);




		}













		public TempPersonList fetchTempPersonList(long id)
				throws SystemException {
			return persistence.fetchByPrimaryKey(id);
		}

		/**
		 * Returns the temp person list with the primary key.
		 *
		 * @param id the primary key of the temp person list
		 * @return the temp person list
		 * @throws PortalException if a temp person list with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public TempPersonList getTempPersonList(long id)
				throws PortalException, SystemException {
			return persistence.findByPrimaryKey(id);
		}


		/**
		 * Returns a range of all the temp person lists.
		 *
		 * <p>
		 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
		 * </p>
		 *
		 * @param start the lower bound of the range of temp person lists
		 * @param end the upper bound of the range of temp person lists (not inclusive)
		 * @return the range of temp person lists
		 * @throws SystemException if a system exception occurred
		 */
		public List<TempPersonList> getTempPersonLists(int start, int end)
				throws SystemException {
			return persistence.findAll(start, end);
		}

		/**
		 * Returns the number of temp person lists.
		 *
		 * @return the number of temp person lists
		 * @throws SystemException if a system exception occurred
		 */
		public int getTempPersonListsCount() throws SystemException {
			return persistence.countAll();
		}

		/**
		 * Updates the temp person list in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param tempPersonList the temp person list
		 * @return the temp person list that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public TempPersonList updateTempPersonList(TempPersonList tempPersonList)
				throws SystemException {
			return updateTempPersonList(tempPersonList, true);
		}

		/**
		 * Updates the temp person list in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param tempPersonList the temp person list
		 * @param merge whether to merge the temp person list with the current session. See  for an explanation.
		 * @return the temp person list that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public TempPersonList updateTempPersonList(TempPersonList tempPersonList,
												   boolean merge) throws SystemException {

			tempPersonList = persistence.updateImpl(tempPersonList, merge);





			return tempPersonList;
		}
}