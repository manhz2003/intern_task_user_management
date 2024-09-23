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

package vn.gt.dao.common.service;






/**
 * The utility for the log message validation local service. This utility wraps {@link vn.gt.dao.common.service.impl.LogMessageValidationLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see LogMessageValidationLocalService
 * @see vn.gt.dao.common.service.base.LogMessageValidationLocalServiceBaseImpl
 * @see vn.gt.dao.common.service.impl.LogMessageValidationLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.common.service.impl.LogMessageValidationLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class LogMessageValidationLocalServiceUtil {
public LogMessageValidationLocalServiceUtil(LogMessageValidationLocalServiceImpl service) {
LogMessageValidationLocalServiceUtil._service = service;
}
public static LogMessageValidationLocalServiceImpl getService() {
return _service;
}
private static LogMessageValidationLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.common.service.impl.LogMessageValidationLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the log message validation to the database. Also notifies the appropriate model listeners.
	*
	* @param logMessageValidation the log message validation
	* @return the log message validation that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.LogMessageValidation addLogMessageValidation(
		com.fds.nsw.nghiepvu.model.LogMessageValidation logMessageValidation)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addLogMessageValidation(logMessageValidation);
	}

	/**
	* Creates a new log message validation with the primary key. Does not add the log message validation to the database.
	*
	* @param id the primary key for the new log message validation
	* @return the new log message validation
	*/
	public static com.fds.nsw.nghiepvu.model.LogMessageValidation createLogMessageValidation(
		long id) {
		return getService().createLogMessageValidation(id);
	}

	/**
	* Deletes the log message validation with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the log message validation
	* @throws PortalException if a log message validation with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteLogMessageValidation(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteLogMessageValidation(id);
	}

	/**
	* Deletes the log message validation from the database. Also notifies the appropriate model listeners.
	*
	* @param logMessageValidation the log message validation
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteLogMessageValidation(
		com.fds.nsw.nghiepvu.model.LogMessageValidation logMessageValidation)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteLogMessageValidation(logMessageValidation);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.LogMessageValidation fetchLogMessageValidation(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchLogMessageValidation(id);
	}

	/**
	* Returns the log message validation with the primary key.
	*
	* @param id the primary key of the log message validation
	* @return the log message validation
	* @throws PortalException if a log message validation with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.LogMessageValidation getLogMessageValidation(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getLogMessageValidation(id);
	}

	

	/**
	* Returns a range of all the log message validations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of log message validations
	* @param end the upper bound of the range of log message validations (not inclusive)
	* @return the range of log message validations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.LogMessageValidation> getLogMessageValidations(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getLogMessageValidations(start, end);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
												java.lang.String[] parameterTypes, java.lang.Object[] arguments)
			throws java.lang.Throwable {
		return null;
	}


	/**
	* Returns the number of log message validations.
	*
	* @return the number of log message validations
	* @throws SystemException if a system exception occurred
	*/
	public static int getLogMessageValidationsCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getLogMessageValidationsCount();
	}

	/**
	* Updates the log message validation in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param logMessageValidation the log message validation
	* @return the log message validation that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.LogMessageValidation updateLogMessageValidation(
		com.fds.nsw.nghiepvu.model.LogMessageValidation logMessageValidation)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateLogMessageValidation(logMessageValidation);
	}

	/**
	* Updates the log message validation in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param logMessageValidation the log message validation
	* @param merge whether to merge the log message validation with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the log message validation that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.LogMessageValidation updateLogMessageValidation(
		com.fds.nsw.nghiepvu.model.LogMessageValidation logMessageValidation,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .updateLogMessageValidation(logMessageValidation, merge);
	}

	

	

	public static java.util.List<com.fds.nsw.nghiepvu.model.LogMessageValidation> findByDocumentNameDocumentYear(
		long documentName, int documentYear) {
		return getService()
				   .findByDocumentNameDocumentYear(documentName, documentYear);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}