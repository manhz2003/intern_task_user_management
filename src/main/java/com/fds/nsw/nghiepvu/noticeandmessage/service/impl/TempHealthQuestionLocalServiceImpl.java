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
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.TempHealthQuestionPersistenceImpl;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;import lombok.extern.slf4j.Slf4j;@Slf4j @Service






/**
 * The implementation of the temp health question local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link vn.gt.dao.noticeandmessage.service.TempHealthQuestionLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.noticeandmessage.service.base.TempHealthQuestionLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.TempHealthQuestionLocalServiceUtil
 */
public class TempHealthQuestionLocalServiceImpl
	{ @Autowired
	TempHealthQuestionPersistenceImpl persistence;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link vn.gt.dao.noticeandmessage.service.TempHealthQuestionLocalServiceUtil} to access the temp health question local service.
	 */
	
	public List<TempHealthQuestion> findByRequestCode(String requestCode) {
		try {
			return persistence.findByRequestCode(requestCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

		/*
		 * NOTE FOR DEVELOPERS:
		 *
		 * Never modify or reference this class directly. Always use {@link vn.gt.dao.noticeandmessage.service.TempHealthQuestionLocalServiceUtil} to access the temp health question local service.
		 */

		/**
		 * Adds the temp health question to the database. Also notifies the appropriate model listeners.
		 *
		 * @param tempHealthQuestion the temp health question
		 * @return the temp health question that was added
		 * @throws SystemException if a system exception occurred
		 */
		public TempHealthQuestion addTempHealthQuestion(
				TempHealthQuestion tempHealthQuestion) throws SystemException {

			tempHealthQuestion = persistence.updateImpl(tempHealthQuestion,
					false);





			return tempHealthQuestion;
		}

		/**
		 * Creates a new temp health question with the primary key. Does not add the temp health question to the database.
		 *
		 * @param id the primary key for the new temp health question
		 * @return the new temp health question
		 */
		public TempHealthQuestion createTempHealthQuestion(long id) {
			return persistence.create(id);
		}

		/**
		 * Deletes the temp health question with the primary key from the database. Also notifies the appropriate model listeners.
		 *
		 * @param id the primary key of the temp health question
		 * @throws PortalException if a temp health question with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteTempHealthQuestion(long id)
				throws PortalException, SystemException {
			TempHealthQuestion tempHealthQuestion = persistence.remove(id);




		}

		/**
		 * Deletes the temp health question from the database. Also notifies the appropriate model listeners.
		 *
		 * @param tempHealthQuestion the temp health question
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteTempHealthQuestion(TempHealthQuestion tempHealthQuestion)
				throws SystemException {
			persistence.remove(tempHealthQuestion);




		}













		public TempHealthQuestion fetchTempHealthQuestion(long id)
				throws SystemException {
			return persistence.fetchByPrimaryKey(id);
		}

		/**
		 * Returns the temp health question with the primary key.
		 *
		 * @param id the primary key of the temp health question
		 * @return the temp health question
		 * @throws PortalException if a temp health question with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public TempHealthQuestion getTempHealthQuestion(long id)
				throws PortalException, SystemException {
			return persistence.findByPrimaryKey(id);
		}



		/**
		 * Returns a range of all the temp health questions.
		 *
		 * <p>
		 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
		 * </p>
		 *
		 * @param start the lower bound of the range of temp health questions
		 * @param end the upper bound of the range of temp health questions (not inclusive)
		 * @return the range of temp health questions
		 * @throws SystemException if a system exception occurred
		 */
		public List<TempHealthQuestion> getTempHealthQuestions(int start, int end)
				throws SystemException {
			return persistence.findAll(start, end);
		}

		/**
		 * Returns the number of temp health questions.
		 *
		 * @return the number of temp health questions
		 * @throws SystemException if a system exception occurred
		 */
		public int getTempHealthQuestionsCount() throws SystemException {
			return persistence.countAll();
		}

		/**
		 * Updates the temp health question in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param tempHealthQuestion the temp health question
		 * @return the temp health question that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public TempHealthQuestion updateTempHealthQuestion(
				TempHealthQuestion tempHealthQuestion) throws SystemException {
			return updateTempHealthQuestion(tempHealthQuestion, true);
		}

		/**
		 * Updates the temp health question in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param tempHealthQuestion the temp health question
		 * @param merge whether to merge the temp health question with the current session. See  for an explanation.
		 * @return the temp health question that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public TempHealthQuestion updateTempHealthQuestion(
				TempHealthQuestion tempHealthQuestion, boolean merge)
				throws SystemException {

			tempHealthQuestion = persistence.updateImpl(tempHealthQuestion,
					merge);





			return tempHealthQuestion;
		}
}