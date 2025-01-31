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
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.TempWasteListPersistenceImpl;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;import lombok.extern.slf4j.Slf4j;@Slf4j @Service











/**
 * The implementation of the temp waste list local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link vn.gt.dao.noticeandmessage.service.TempWasteListLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.noticeandmessage.service.base.TempWasteListLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.TempWasteListLocalServiceUtil
 */
public class TempWasteListLocalServiceImpl
	{ @Autowired
	TempWasteListPersistenceImpl persistence;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link vn.gt.dao.noticeandmessage.service.TempWasteListLocalServiceUtil} to access the temp waste list local service.
	 */
	
	public List<TempWasteList> getWasteLists(String requestCode) {
		List<TempWasteList> wasteLists = new ArrayList<TempWasteList>();
		
		try {
			wasteLists = persistence.findByRequestCode(requestCode);
		} catch(SystemException e) {
			log.error(e.getMessage());
		}
		
		return wasteLists;
	}
	
	public List<TempWasteList> getWasteLists(long documentName, int documentYear) {
		List<TempWasteList> wasteLists = new ArrayList<TempWasteList>();
		
		try {
			wasteLists = persistence.findBydocumentNameAnddocumentYear(documentName, documentYear);
		} catch(SystemException e) {
			log.error(e.getMessage());
		}
		
		return wasteLists;
	}
	
	public TempWasteList getWasteList(String requestCode, String typeCode) {
		TempWasteList wasteList = null;
		
		try {
			wasteList = persistence.fetchByRequestCode_TypeCode(requestCode, typeCode);
		} catch(SystemException e) {
			log.error(e.getMessage());
		}
		
		return wasteList;
	}

		/*
		 * NOTE FOR DEVELOPERS:
		 *
		 * Never modify or reference this class directly. Always use {@link vn.gt.dao.noticeandmessage.service.TempWasteListLocalServiceUtil} to access the temp waste list local service.
		 */

		/**
		 * Adds the temp waste list to the database. Also notifies the appropriate model listeners.
		 *
		 * @param tempWasteList the temp waste list
		 * @return the temp waste list that was added
		 * @throws SystemException if a system exception occurred
		 */
		public TempWasteList addTempWasteList(TempWasteList tempWasteList)
				throws SystemException {

			tempWasteList = persistence.updateImpl(tempWasteList, false);





			return tempWasteList;
		}

		/**
		 * Creates a new temp waste list with the primary key. Does not add the temp waste list to the database.
		 *
		 * @param id the primary key for the new temp waste list
		 * @return the new temp waste list
		 */
		public TempWasteList createTempWasteList(long id) {
			return persistence.create(id);
		}

		/**
		 * Deletes the temp waste list with the primary key from the database. Also notifies the appropriate model listeners.
		 *
		 * @param id the primary key of the temp waste list
		 * @throws PortalException if a temp waste list with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteTempWasteList(long id)
				throws PortalException, SystemException {
			TempWasteList tempWasteList = persistence.remove(id);




		}

		/**
		 * Deletes the temp waste list from the database. Also notifies the appropriate model listeners.
		 *
		 * @param tempWasteList the temp waste list
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteTempWasteList(TempWasteList tempWasteList)
				throws SystemException {
			persistence.remove(tempWasteList);




		}













		public TempWasteList fetchTempWasteList(long id) throws SystemException {
			return persistence.fetchByPrimaryKey(id);
		}

		/**
		 * Returns the temp waste list with the primary key.
		 *
		 * @param id the primary key of the temp waste list
		 * @return the temp waste list
		 * @throws PortalException if a temp waste list with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public TempWasteList getTempWasteList(long id)
				throws PortalException, SystemException {
			return persistence.findByPrimaryKey(id);
		}


		/**
		 * Returns a range of all the temp waste lists.
		 *
		 * <p>
		 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
		 * </p>
		 *
		 * @param start the lower bound of the range of temp waste lists
		 * @param end the upper bound of the range of temp waste lists (not inclusive)
		 * @return the range of temp waste lists
		 * @throws SystemException if a system exception occurred
		 */
		public List<TempWasteList> getTempWasteLists(int start, int end)
				throws SystemException {
			return persistence.findAll(start, end);
		}

		/**
		 * Returns the number of temp waste lists.
		 *
		 * @return the number of temp waste lists
		 * @throws SystemException if a system exception occurred
		 */
		public int getTempWasteListsCount() throws SystemException {
			return persistence.countAll();
		}

		/**
		 * Updates the temp waste list in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param tempWasteList the temp waste list
		 * @return the temp waste list that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public TempWasteList updateTempWasteList(TempWasteList tempWasteList)
				throws SystemException {
			return updateTempWasteList(tempWasteList, true);
		}

		/**
		 * Updates the temp waste list in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param tempWasteList the temp waste list
		 * @param merge whether to merge the temp waste list with the current session. See  for an explanation.
		 * @return the temp waste list that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public TempWasteList updateTempWasteList(TempWasteList tempWasteList,
												 boolean merge) throws SystemException {

			tempWasteList = persistence.updateImpl(tempWasteList, merge);





			return tempWasteList;
		}
}