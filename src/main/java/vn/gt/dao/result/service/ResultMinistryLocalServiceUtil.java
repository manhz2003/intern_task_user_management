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
 * The utility for the result ministry local service. This utility wraps {@link vn.gt.dao.result.service.impl.ResultMinistryLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see ResultMinistryLocalService
 * @see vn.gt.dao.result.service.base.ResultMinistryLocalServiceBaseImpl
 * @see vn.gt.dao.result.service.impl.ResultMinistryLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.result.service.impl.ResultMinistryLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class ResultMinistryLocalServiceUtil {
public ResultMinistryLocalServiceUtil(ResultMinistryLocalServiceImpl service) {
ResultMinistryLocalServiceUtil._service = service;
}
public static ResultMinistryLocalServiceImpl getService() {
return _service;
}
private static ResultMinistryLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.result.service.impl.ResultMinistryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the result ministry to the database. Also notifies the appropriate model listeners.
	*
	* @param resultMinistry the result ministry
	* @return the result ministry that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.ResultMinistry addResultMinistry(
		com.fds.nsw.nghiepvu.model.ResultMinistry resultMinistry)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addResultMinistry(resultMinistry);
	}

	/**
	* Creates a new result ministry with the primary key. Does not add the result ministry to the database.
	*
	* @param id the primary key for the new result ministry
	* @return the new result ministry
	*/
	public static com.fds.nsw.nghiepvu.model.ResultMinistry createResultMinistry(
		long id) {
		return getService().createResultMinistry(id);
	}

	/**
	* Deletes the result ministry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the result ministry
	* @throws PortalException if a result ministry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteResultMinistry(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteResultMinistry(id);
	}

	/**
	* Deletes the result ministry from the database. Also notifies the appropriate model listeners.
	*
	* @param resultMinistry the result ministry
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteResultMinistry(
		com.fds.nsw.nghiepvu.model.ResultMinistry resultMinistry)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteResultMinistry(resultMinistry);
	}

	

	

	

	


	public static com.fds.nsw.nghiepvu.model.ResultMinistry fetchResultMinistry(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchResultMinistry(id);
	}

	/**
	* Returns the result ministry with the primary key.
	*
	* @param id the primary key of the result ministry
	* @return the result ministry
	* @throws PortalException if a result ministry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.ResultMinistry getResultMinistry(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getResultMinistry(id);
	}


	/**
	* Returns a range of all the result ministries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of result ministries
	* @param end the upper bound of the range of result ministries (not inclusive)
	* @return the range of result ministries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.ResultMinistry> getResultMinistries(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getResultMinistries(start, end);
	}

	/**
	* Returns the number of result ministries.
	*
	* @return the number of result ministries
	* @throws SystemException if a system exception occurred
	*/
	public static int getResultMinistriesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getResultMinistriesCount();
	}

	/**
	* Updates the result ministry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param resultMinistry the result ministry
	* @return the result ministry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.ResultMinistry updateResultMinistry(
		com.fds.nsw.nghiepvu.model.ResultMinistry resultMinistry)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateResultMinistry(resultMinistry);
	}

	/**
	* Updates the result ministry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param resultMinistry the result ministry
	* @param merge whether to merge the result ministry with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the result ministry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.ResultMinistry updateResultMinistry(
		com.fds.nsw.nghiepvu.model.ResultMinistry resultMinistry, boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateResultMinistry(resultMinistry, merge);
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

	/**
	* Returns the result ministry where documentName = &#63; and documentYear = &#63; and ministryCode = &#63; and businessTypeCode = &#63; or throws
	* a {@link vn.gt.dao.result.NoSuchResultMinistryException} if it could not be found.
	*
	* @param documentName
	the document name
	* @param documentYear
	the document year
	* @param ministryCode
	the ministry code
	* @param businessTypeCode
	the business type code
	* @return the matching result ministry
	* @throws vn.gt.dao.result.NoSuchResultMinistryException
	if a matching result ministry could not be found
	* @throws SystemException
	if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.ResultMinistry> findByDocumentNameAnddocumentYearMinistryCodeBusinessTypeCode(
		int documentName, int documentYear, java.lang.String ministryCode,
		int businessTypeCode) {
		return getService()
				   .findByDocumentNameAnddocumentYearMinistryCodeBusinessTypeCode(documentName,
			documentYear, ministryCode, businessTypeCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.ResultMinistry> findDistinctMinistryCode(
		long documentName, int documentYear) {
		return getService().findDistinctMinistryCode(documentName, documentYear);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.ResultMinistry> findDocumentNameAndDocumentYeahAndMinistryAndBusinessTypeCode(
		long documentName, int documentYear, java.lang.String ministryCode,
		java.lang.String businessTypeCode) {
		return getService()
				   .findDocumentNameAndDocumentYeahAndMinistryAndBusinessTypeCode(documentName,
			documentYear, ministryCode, businessTypeCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.ResultMinistry> findDocNameAndDocYearAndMinistryCode(
		int documentName, int documentYear, java.lang.String ministryCode) {
		return getService()
				   .findDocNameAndDocYearAndMinistryCode(documentName,
			documentYear, ministryCode);
	}

	public static com.fds.nsw.nghiepvu.model.ResultMinistry findDocumentNameAndDocumentYeahAndMinistryAndBusinessTypeCodePhanHoi(
		long documentName, int documentYear, java.lang.String ministryCode,
		java.lang.String lstBusinessTypeCode) {
		return getService()
				   .findDocumentNameAndDocumentYeahAndMinistryAndBusinessTypeCodePhanHoi(documentName,
			documentYear, ministryCode, lstBusinessTypeCode);
	}

	public static void clearService() {
		_service = null;
	}

	
}