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

package vn.gt.dao.noticeandmessage.service;






/**
 * The utility for the temp health question local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.TempHealthQuestionLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see TempHealthQuestionLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.TempHealthQuestionLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.TempHealthQuestionLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.TempHealthQuestionLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class TempHealthQuestionLocalServiceUtil {
public TempHealthQuestionLocalServiceUtil(TempHealthQuestionLocalServiceImpl service) {
TempHealthQuestionLocalServiceUtil._service = service;
}
public static TempHealthQuestionLocalServiceImpl getService() {
return _service;
}
private static TempHealthQuestionLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.TempHealthQuestionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the temp health question to the database. Also notifies the appropriate model listeners.
	*
	* @param tempHealthQuestion the temp health question
	* @return the temp health question that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempHealthQuestion addTempHealthQuestion(
		com.fds.nsw.nghiepvu.model.TempHealthQuestion tempHealthQuestion)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addTempHealthQuestion(tempHealthQuestion);
	}

	/**
	* Creates a new temp health question with the primary key. Does not add the temp health question to the database.
	*
	* @param id the primary key for the new temp health question
	* @return the new temp health question
	*/
	public static com.fds.nsw.nghiepvu.model.TempHealthQuestion createTempHealthQuestion(
		long id) {
		return getService().createTempHealthQuestion(id);
	}

	/**
	* Deletes the temp health question with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the temp health question
	* @throws PortalException if a temp health question with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempHealthQuestion(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempHealthQuestion(id);
	}

	/**
	* Deletes the temp health question from the database. Also notifies the appropriate model listeners.
	*
	* @param tempHealthQuestion the temp health question
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempHealthQuestion(
		com.fds.nsw.nghiepvu.model.TempHealthQuestion tempHealthQuestion)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempHealthQuestion(tempHealthQuestion);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.TempHealthQuestion fetchTempHealthQuestion(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchTempHealthQuestion(id);
	}

	/**
	* Returns the temp health question with the primary key.
	*
	* @param id the primary key of the temp health question
	* @return the temp health question
	* @throws PortalException if a temp health question with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempHealthQuestion getTempHealthQuestion(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempHealthQuestion(id);
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
	public static java.util.List<com.fds.nsw.nghiepvu.model.TempHealthQuestion> getTempHealthQuestions(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempHealthQuestions(start, end);
	}

	/**
	* Returns the number of temp health questions.
	*
	* @return the number of temp health questions
	* @throws SystemException if a system exception occurred
	*/
	public static int getTempHealthQuestionsCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempHealthQuestionsCount();
	}

	/**
	* Updates the temp health question in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempHealthQuestion the temp health question
	* @return the temp health question that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempHealthQuestion updateTempHealthQuestion(
		com.fds.nsw.nghiepvu.model.TempHealthQuestion tempHealthQuestion)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateTempHealthQuestion(tempHealthQuestion);
	}

	/**
	* Updates the temp health question in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempHealthQuestion the temp health question
	* @param merge whether to merge the temp health question with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the temp health question that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempHealthQuestion updateTempHealthQuestion(
		com.fds.nsw.nghiepvu.model.TempHealthQuestion tempHealthQuestion,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateTempHealthQuestion(tempHealthQuestion, merge);
	}

	

	

	public static java.util.List<com.fds.nsw.nghiepvu.model.TempHealthQuestion> findByRequestCode(
		java.lang.String requestCode) {
		return getService().findByRequestCode(requestCode);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}