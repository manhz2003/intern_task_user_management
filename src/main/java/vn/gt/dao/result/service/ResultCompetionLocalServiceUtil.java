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

package vn.gt.dao.result.service;






/**
 * The utility for the result competion local service. This utility wraps {@link vn.gt.dao.result.service.impl.ResultCompetionLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see ResultCompetionLocalService
 * @see vn.gt.dao.result.service.base.ResultCompetionLocalServiceBaseImpl
 * @see vn.gt.dao.result.service.impl.ResultCompetionLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.result.service.impl.ResultCompetionLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class ResultCompetionLocalServiceUtil {
public ResultCompetionLocalServiceUtil(ResultCompetionLocalServiceImpl service) {
ResultCompetionLocalServiceUtil._service = service;
}
public static ResultCompetionLocalServiceImpl getService() {
return _service;
}
private static ResultCompetionLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.result.service.impl.ResultCompetionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the result competion to the database. Also notifies the appropriate model listeners.
	*
	* @param resultCompetion the result competion
	* @return the result competion that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.ResultCompletion addResultCompetion(
		com.fds.nsw.nghiepvu.model.ResultCompletion resultCompetion)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addResultCompetion(resultCompetion);
	}

	/**
	* Creates a new result competion with the primary key. Does not add the result competion to the database.
	*
	* @param id the primary key for the new result competion
	* @return the new result competion
	*/
	public static com.fds.nsw.nghiepvu.model.ResultCompletion createResultCompetion(
		long id) {
		return getService().createResultCompetion(id);
	}

	/**
	* Deletes the result competion with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the result competion
	* @throws PortalException if a result competion with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteResultCompetion(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteResultCompetion(id);
	}

	/**
	* Deletes the result competion from the database. Also notifies the appropriate model listeners.
	*
	* @param resultCompetion the result competion
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteResultCompetion(
		com.fds.nsw.nghiepvu.model.ResultCompletion resultCompetion)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteResultCompetion(resultCompetion);
	}

	


	


	

	


	public static com.fds.nsw.nghiepvu.model.ResultCompletion fetchResultCompetion(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchResultCompetion(id);
	}

	/**
	* Returns the result competion with the primary key.
	*
	* @param id the primary key of the result competion
	* @return the result competion
	* @throws PortalException if a result competion with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.ResultCompletion getResultCompetion(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getResultCompetion(id);
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
	public static java.util.List<com.fds.nsw.nghiepvu.model.ResultCompletion> getResultCompetions(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getResultCompetions(start, end);
	}

	/**
	* Returns the number of result competions.
	*
	* @return the number of result competions
	* @throws SystemException if a system exception occurred
	*/
	public static int getResultCompetionsCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getResultCompetionsCount();
	}

	/**
	* Updates the result competion in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param resultCompetion the result competion
	* @return the result competion that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.ResultCompletion updateResultCompetion(
		com.fds.nsw.nghiepvu.model.ResultCompletion resultCompetion)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateResultCompetion(resultCompetion);
	}

	/**
	* Updates the result competion in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param resultCompetion the result competion
	* @param merge whether to merge the result competion with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the result competion that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.ResultCompletion updateResultCompetion(
		com.fds.nsw.nghiepvu.model.ResultCompletion resultCompetion, boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateResultCompetion(resultCompetion, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/


	public static com.fds.nsw.nghiepvu.model.ResultCompletion findByDocumentNameAndDocumentYear(
		long documentName, int documentYear) {
		return getService()
				   .findByDocumentNameAndDocumentYear(documentName, documentYear);
	}

	public static void clearService() {
		_service = null;
	}


	
}