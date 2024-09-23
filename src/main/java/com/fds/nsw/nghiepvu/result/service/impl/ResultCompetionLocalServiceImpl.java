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
import com.fds.nsw.nghiepvu.result.service.persistence.ResultCompletionPersistenceImpl;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;import lombok.extern.slf4j.Slf4j;@Slf4j @Service


/**
 * The implementation of the result competion local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link vn.gt.dao.result.service.ResultCompetionLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.result.service.base.ResultCompetionLocalServiceBaseImpl
 * @see vn.gt.dao.result.service.ResultCompetionLocalServiceUtil
 */
public class ResultCompetionLocalServiceImpl
	{ @Autowired
	ResultCompletionPersistenceImpl persistence;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link vn.gt.dao.result.service.ResultCompetionLocalServiceUtil} to access the result competion local service.
	 */
	public ResultCompletion findByDocumentNameAndDocumentYear(long documentName, int documentYear) {
		try {
			List<ResultCompletion> competions =persistence.findByDocumentNameAndDocumentYear(documentName, documentYear);
			if(competions!=null && competions.size()>0){
				return competions.get(0);
						
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

		/*
		 * NOTE FOR DEVELOPERS:
		 *
		 * Never modify or reference this class directly. Always use {@link vn.gt.dao.result.service.ResultCompetionLocalServiceUtil} to access the result competion local service.
		 */

		/**
		 * Adds the result competion to the database. Also notifies the appropriate model listeners.
		 *
		 * @param resultCompetion the result competion
		 * @return the result competion that was added
		 * @throws SystemException if a system exception occurred
		 */
		public ResultCompletion addResultCompetion(ResultCompletion resultCompetion)
				throws SystemException {

			resultCompetion = persistence.updateImpl(resultCompetion,
					false);





			return resultCompetion;
		}

		/**
		 * Creates a new result competion with the primary key. Does not add the result competion to the database.
		 *
		 * @param id the primary key for the new result competion
		 * @return the new result competion
		 */
		public ResultCompletion createResultCompetion(long id) {
			return persistence.create(id);
		}

		/**
		 * Deletes the result competion with the primary key from the database. Also notifies the appropriate model listeners.
		 *
		 * @param id the primary key of the result competion
		 * @throws PortalException if a result competion with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteResultCompetion(long id)
				throws PortalException, SystemException {
			ResultCompletion resultCompetion = persistence.remove(id);




		}

		/**
		 * Deletes the result competion from the database. Also notifies the appropriate model listeners.
		 *
		 * @param resultCompetion the result competion
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteResultCompetion(ResultCompletion resultCompetion)
				throws SystemException {
			persistence.remove(resultCompetion);




		}













		public ResultCompletion fetchResultCompetion(long id)
				throws SystemException {
			return persistence.fetchByPrimaryKey(id);
		}

		/**
		 * Returns the result competion with the primary key.
		 *
		 * @param id the primary key of the result competion
		 * @return the result competion
		 * @throws PortalException if a result competion with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public ResultCompletion getResultCompetion(long id)
				throws PortalException, SystemException {
			return persistence.findByPrimaryKey(id);
		}

		/**
		 * Returns a range of all the result competions.
		 *
		 * <p>
		 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
		 * </p>
		 *
		 * @param start the lower bound of the range of result competions
		 * @param end the upper bound of the range of result competions (not inclusive)
		 * @return the range of result competions
		 * @throws SystemException if a system exception occurred
		 */
		public List<ResultCompletion> getResultCompetions(int start, int end)
				throws SystemException {
			return persistence.findAll(start, end);
		}

		/**
		 * Returns the number of result competions.
		 *
		 * @return the number of result competions
		 * @throws SystemException if a system exception occurred
		 */
		public int getResultCompetionsCount() throws SystemException {
			return persistence.countAll();
		}

		/**
		 * Updates the result competion in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param resultCompetion the result competion
		 * @return the result competion that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public ResultCompletion updateResultCompetion(
				ResultCompletion resultCompetion) throws SystemException {
			return updateResultCompetion(resultCompetion, true);
		}

		/**
		 * Updates the result competion in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param resultCompetion the result competion
		 * @param merge whether to merge the result competion with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
		 * @return the result competion that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public ResultCompletion updateResultCompetion(
				ResultCompletion resultCompetion, boolean merge)
				throws SystemException {

			resultCompetion = persistence.updateImpl(resultCompetion,
					merge);





			return resultCompetion;
		}
}