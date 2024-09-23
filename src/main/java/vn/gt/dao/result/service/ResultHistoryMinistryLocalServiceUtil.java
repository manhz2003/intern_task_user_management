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
 * The utility for the result history ministry local service. This utility wraps {@link vn.gt.dao.result.service.impl.ResultHistoryMinistryLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see ResultHistoryMinistryLocalService
 * @see vn.gt.dao.result.service.base.ResultHistoryMinistryLocalServiceBaseImpl
 * @see vn.gt.dao.result.service.impl.ResultHistoryMinistryLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.result.service.impl.ResultHistoryMinistryLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class ResultHistoryMinistryLocalServiceUtil {
public ResultHistoryMinistryLocalServiceUtil(ResultHistoryMinistryLocalServiceImpl service) {
ResultHistoryMinistryLocalServiceUtil._service = service;
}
public static ResultHistoryMinistryLocalServiceImpl getService() {
return _service;
}
private static ResultHistoryMinistryLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.result.service.impl.ResultHistoryMinistryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the result history ministry to the database. Also notifies the appropriate model listeners.
	*
	* @param resultHistoryMinistry the result history ministry
	* @return the result history ministry that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.ResultHistoryMinistry addResultHistoryMinistry(
		com.fds.nsw.nghiepvu.model.ResultHistoryMinistry resultHistoryMinistry)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addResultHistoryMinistry(resultHistoryMinistry);
	}

	/**
	* Creates a new result history ministry with the primary key. Does not add the result history ministry to the database.
	*
	* @param id the primary key for the new result history ministry
	* @return the new result history ministry
	*/
	public static com.fds.nsw.nghiepvu.model.ResultHistoryMinistry createResultHistoryMinistry(
		long id) {
		return getService().createResultHistoryMinistry(id);
	}

	/**
	* Deletes the result history ministry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the result history ministry
	* @throws PortalException if a result history ministry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteResultHistoryMinistry(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteResultHistoryMinistry(id);
	}

	/**
	* Deletes the result history ministry from the database. Also notifies the appropriate model listeners.
	*
	* @param resultHistoryMinistry the result history ministry
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteResultHistoryMinistry(
		com.fds.nsw.nghiepvu.model.ResultHistoryMinistry resultHistoryMinistry)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteResultHistoryMinistry(resultHistoryMinistry);
	}

	

	

	


	


	public static com.fds.nsw.nghiepvu.model.ResultHistoryMinistry fetchResultHistoryMinistry(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchResultHistoryMinistry(id);
	}

	/**
	* Returns the result history ministry with the primary key.
	*
	* @param id the primary key of the result history ministry
	* @return the result history ministry
	* @throws PortalException if a result history ministry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.ResultHistoryMinistry getResultHistoryMinistry(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getResultHistoryMinistry(id);
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
	public static java.util.List<com.fds.nsw.nghiepvu.model.ResultHistoryMinistry> getResultHistoryMinistries(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getResultHistoryMinistries(start, end);
	}

	/**
	* Returns the number of result history ministries.
	*
	* @return the number of result history ministries
	* @throws SystemException if a system exception occurred
	*/
	public static int getResultHistoryMinistriesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getResultHistoryMinistriesCount();
	}

	/**
	* Updates the result history ministry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param resultHistoryMinistry the result history ministry
	* @return the result history ministry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.ResultHistoryMinistry updateResultHistoryMinistry(
		com.fds.nsw.nghiepvu.model.ResultHistoryMinistry resultHistoryMinistry)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateResultHistoryMinistry(resultHistoryMinistry);
	}

	/**
	* Updates the result history ministry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param resultHistoryMinistry the result history ministry
	* @param merge whether to merge the result history ministry with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the result history ministry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.ResultHistoryMinistry updateResultHistoryMinistry(
		com.fds.nsw.nghiepvu.model.ResultHistoryMinistry resultHistoryMinistry,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .updateResultHistoryMinistry(resultHistoryMinistry, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/


	public static com.fds.nsw.nghiepvu.model.ResultHistoryMinistry findByRequestCode(
		java.lang.String requestCode) {
		return getService().findByRequestCode(requestCode);
	}

	/**
	* Returns all the result history ministries where documentName = &#63; and documentYear = &#63;.
	*
	* @param documentName the document name
	* @param documentYear the document year
	* @return the matching result history ministries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.ResultHistoryMinistry> findByDocumentNameAnddocumentYear(
		int documentName, int documentYear) {
		return getService()
				   .findByDocumentNameAnddocumentYear(documentName, documentYear);
	}

	/**
	* Returns all the result history ministries where documentName = &#63; and documentYear = &#63;.
	*
	* @param documentName the document name
	* @param documentYear the document year
	* @return the matching result history ministries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.ResultHistoryMinistry> findByDocumentNameAnddocumentYearAndMinistryCode(
		long documentName, int documentYear, java.lang.String ministryCode) {
		return getService()
				   .findByDocumentNameAnddocumentYearAndMinistryCode(documentName,
			documentYear, ministryCode);
	}

	/**
	* Returns all the result history ministries where ministryCode = &#63;.
	*
	* @param ministryCode the ministry code
	* @return the matching result history ministries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.ResultHistoryMinistry> findByMinistryCode(
		java.lang.String ministryCode) {
		return getService().findByMinistryCode(ministryCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.ResultHistoryMinistry> findDistinctMinistryCode(
		long documentName, int documentYear) {
		return getService().findDistinctMinistryCode(documentName, documentYear);
	}

	public static com.fds.nsw.nghiepvu.model.ResultHistoryMinistry findDocumentNameAndDocumentYeahAndMinistryAndBusinessTypeCode(
		long documentName, int documentYear, java.lang.String ministryCode,
		java.lang.String businessTypeCode) {
		return getService()
				   .findDocumentNameAndDocumentYeahAndMinistryAndBusinessTypeCode(documentName,
			documentYear, ministryCode, businessTypeCode);
	}

	public static void clearService() {
		_service = null;
	}

	
}