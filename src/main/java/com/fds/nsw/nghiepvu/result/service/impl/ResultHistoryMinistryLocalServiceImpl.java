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

package com.fds.nsw.nghiepvu.result.service.impl;import java.util.ArrayList; import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.result.service.finder.ResultHistoryMinistryFinderImpl;
import com.fds.nsw.nghiepvu.result.service.persistence.ResultHistoryMinistryPersistenceImpl;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;import lombok.extern.slf4j.Slf4j;@Slf4j @Service






/**
 * The implementation of the result history ministry local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link vn.gt.dao.result.service.ResultHistoryMinistryLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.result.service.base.ResultHistoryMinistryLocalServiceBaseImpl
 * @see vn.gt.dao.result.service.ResultHistoryMinistryLocalServiceUtil
 */
public class ResultHistoryMinistryLocalServiceImpl
	{ @Autowired
	ResultHistoryMinistryPersistenceImpl persistence;@Autowired
	ResultHistoryMinistryFinderImpl finder;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link vn.gt.dao.result.service.ResultHistoryMinistryLocalServiceUtil} to access the result history ministry local service.
	 */
	
	public ResultHistoryMinistry findByRequestCode(
			java.lang.String requestCode){
		try{
			return persistence.findByRequestCode(requestCode);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	/**
	* Returns all the result history ministries where documentName = &#63; and documentYear = &#63;.
	*
	* @param documentName the document name
	* @param documentYear the document year
	* @return the matching result history ministries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<ResultHistoryMinistry> findByDocumentNameAnddocumentYear(
		int documentName, int documentYear){
		try{
			return persistence.findByDocumentNameAnddocumentYear(documentName,documentYear);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	* Returns all the result history ministries where documentName = &#63; and documentYear = &#63;.
	*
	* @param documentName the document name
	* @param documentYear the document year
	* @return the matching result history ministries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<ResultHistoryMinistry> findByDocumentNameAnddocumentYearAndMinistryCode(
		long documentName, int documentYear, String ministryCode){
		try{
			return persistence.findByDocumentNameAnddocumentYearAndMinistryCode(documentName, documentYear, ministryCode);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	* Returns all the result history ministries where ministryCode = &#63;.
	*
	* @param ministryCode the ministry code
	* @return the matching result history ministries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<ResultHistoryMinistry> findByMinistryCode(
		java.lang.String ministryCode){
		try{
			return persistence.findByMinistryCode(ministryCode);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ResultHistoryMinistry> findDistinctMinistryCode(long documentName, int documentYear){
		try {
			return finder.findDistinctMinistryCode(documentName, documentYear);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
//	public List<ResultHistoryMinistry> findLeftJoinMinistryCode(long documentName, int documentYear) {
//		try {
//			return finder.findLeftJoinMinistryCode(documentName, documentYear);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//		
//	}
	
	public ResultHistoryMinistry findDocumentNameAndDocumentYeahAndMinistryAndBusinessTypeCode(
			long documentName, int documentYear, String ministryCode,String businessTypeCode){
		try {
			return finder.findDocumentNameAndDocumentYeahAndMinistryAndBusinessTypeCode(documentName,documentYear,ministryCode,businessTypeCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
				return null;
		
	}


		/*
		 * NOTE FOR DEVELOPERS:
		 *
		 * Never modify or reference this class directly. Always use {@link vn.gt.dao.result.service.ResultHistoryMinistryLocalServiceUtil} to access the result history ministry local service.
		 */

		/**
		 * Adds the result history ministry to the database. Also notifies the appropriate model listeners.
		 *
		 * @param resultHistoryMinistry the result history ministry
		 * @return the result history ministry that was added
		 * @throws SystemException if a system exception occurred
		 */
		public ResultHistoryMinistry addResultHistoryMinistry(
				ResultHistoryMinistry resultHistoryMinistry) throws SystemException {

			resultHistoryMinistry = persistence.updateImpl(resultHistoryMinistry,
					false);





			return resultHistoryMinistry;
		}

		/**
		 * Creates a new result history ministry with the primary key. Does not add the result history ministry to the database.
		 *
		 * @param id the primary key for the new result history ministry
		 * @return the new result history ministry
		 */
		public ResultHistoryMinistry createResultHistoryMinistry(long id) {
			return persistence.create(id);
		}

		/**
		 * Deletes the result history ministry with the primary key from the database. Also notifies the appropriate model listeners.
		 *
		 * @param id the primary key of the result history ministry
		 * @throws PortalException if a result history ministry with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteResultHistoryMinistry(long id)
				throws PortalException, SystemException {
			ResultHistoryMinistry resultHistoryMinistry = persistence.remove(id);




		}

		/**
		 * Deletes the result history ministry from the database. Also notifies the appropriate model listeners.
		 *
		 * @param resultHistoryMinistry the result history ministry
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteResultHistoryMinistry(
				ResultHistoryMinistry resultHistoryMinistry) throws SystemException {
			persistence.remove(resultHistoryMinistry);




		}













		public ResultHistoryMinistry fetchResultHistoryMinistry(long id)
				throws SystemException {
			return persistence.fetchByPrimaryKey(id);
		}

		/**
		 * Returns the result history ministry with the primary key.
		 *
		 * @param id the primary key of the result history ministry
		 * @return the result history ministry
		 * @throws PortalException if a result history ministry with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public ResultHistoryMinistry getResultHistoryMinistry(long id)
				throws PortalException, SystemException {
			return persistence.findByPrimaryKey(id);
		}



		/**
		 * Returns a range of all the result history ministries.
		 *
		 * <p>
		 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
		 * </p>
		 *
		 * @param start the lower bound of the range of result history ministries
		 * @param end the upper bound of the range of result history ministries (not inclusive)
		 * @return the range of result history ministries
		 * @throws SystemException if a system exception occurred
		 */
		public List<ResultHistoryMinistry> getResultHistoryMinistries(int start,
																	  int end) throws SystemException {
			return persistence.findAll(start, end);
		}

		/**
		 * Returns the number of result history ministries.
		 *
		 * @return the number of result history ministries
		 * @throws SystemException if a system exception occurred
		 */
		public int getResultHistoryMinistriesCount() throws SystemException {
			return persistence.countAll();
		}

		/**
		 * Updates the result history ministry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param resultHistoryMinistry the result history ministry
		 * @return the result history ministry that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public ResultHistoryMinistry updateResultHistoryMinistry(
				ResultHistoryMinistry resultHistoryMinistry) throws SystemException {
			return updateResultHistoryMinistry(resultHistoryMinistry, true);
		}

		/**
		 * Updates the result history ministry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param resultHistoryMinistry the result history ministry
		 * @param merge whether to merge the result history ministry with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
		 * @return the result history ministry that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public ResultHistoryMinistry updateResultHistoryMinistry(
				ResultHistoryMinistry resultHistoryMinistry, boolean merge)
				throws SystemException {

			resultHistoryMinistry = persistence.updateImpl(resultHistoryMinistry,
					merge);





			return resultHistoryMinistry;
		}
}