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
 * The utility for the result certificate local service. This utility wraps {@link vn.gt.dao.result.service.impl.ResultCertificateLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see ResultCertificateLocalService
 * @see vn.gt.dao.result.service.base.ResultCertificateLocalServiceBaseImpl
 * @see vn.gt.dao.result.service.impl.ResultCertificateLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.result.service.impl.ResultCertificateLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class ResultCertificateLocalServiceUtil {
public ResultCertificateLocalServiceUtil(ResultCertificateLocalServiceImpl service) {
ResultCertificateLocalServiceUtil._service = service;
}
public static ResultCertificateLocalServiceImpl getService() {
return _service;
}
private static ResultCertificateLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.result.service.impl.ResultCertificateLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the result certificate to the database. Also notifies the appropriate model listeners.
	*
	* @param resultCertificate the result certificate
	* @return the result certificate that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.ResultCertificate addResultCertificate(
		com.fds.nsw.nghiepvu.model.ResultCertificate resultCertificate)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addResultCertificate(resultCertificate);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.ResultCertificate> findResultCertificates(
			java.lang.String imoNumber, java.lang.String callSign, int start,
			int end) throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				.findResultCertificates(imoNumber, callSign, start, end);
	}


	/**
	* Creates a new result certificate with the primary key. Does not add the result certificate to the database.
	*
	* @param id the primary key for the new result certificate
	* @return the new result certificate
	*/
	public static com.fds.nsw.nghiepvu.model.ResultCertificate createResultCertificate(
		long id) {
		return getService().createResultCertificate(id);
	}

	/**
	* Deletes the result certificate with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the result certificate
	* @throws PortalException if a result certificate with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteResultCertificate(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteResultCertificate(id);
	}

	/**
	* Deletes the result certificate from the database. Also notifies the appropriate model listeners.
	*
	* @param resultCertificate the result certificate
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteResultCertificate(
		com.fds.nsw.nghiepvu.model.ResultCertificate resultCertificate)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteResultCertificate(resultCertificate);
	}

	

	

	


	


	public static com.fds.nsw.nghiepvu.model.ResultCertificate fetchResultCertificate(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchResultCertificate(id);
	}

	/**
	* Returns the result certificate with the primary key.
	*
	* @param id the primary key of the result certificate
	* @return the result certificate
	* @throws PortalException if a result certificate with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.ResultCertificate getResultCertificate(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getResultCertificate(id);
	}



	/**
	* Returns a range of all the result certificates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of result certificates
	* @param end the upper bound of the range of result certificates (not inclusive)
	* @return the range of result certificates
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.ResultCertificate> getResultCertificates(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getResultCertificates(start, end);
	}

	/**
	* Returns the number of result certificates.
	*
	* @return the number of result certificates
	* @throws SystemException if a system exception occurred
	*/
	public static int getResultCertificatesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getResultCertificatesCount();
	}

	/**
	* Updates the result certificate in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param resultCertificate the result certificate
	* @return the result certificate that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.ResultCertificate updateResultCertificate(
		com.fds.nsw.nghiepvu.model.ResultCertificate resultCertificate)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateResultCertificate(resultCertificate);
	}

	/**
	* Updates the result certificate in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param resultCertificate the result certificate
	* @param merge whether to merge the result certificate with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the result certificate that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.ResultCertificate updateResultCertificate(
		com.fds.nsw.nghiepvu.model.ResultCertificate resultCertificate,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateResultCertificate(resultCertificate, merge);
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


	public static java.util.List<com.fds.nsw.nghiepvu.model.ResultCertificate> findByDocumentNameAndDocumentYear(
		long documentName, int documentYear) {
		return getService()
				   .findByDocumentNameAndDocumentYear(documentName, documentYear);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.ResultCertificate> findByDocumentNameAndDocumentYearAndMaritimeCode(
		long documentName, int documentYear, java.lang.String maritimeCode) {
		return getService()
				   .findByDocumentNameAndDocumentYearAndMaritimeCode(documentName,
			documentYear, maritimeCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.ResultCertificate> findByDocumentNameAndDocumentYearAndCertificateCode(
		long documentName, int documentYear, java.lang.String certificateCode) {
		return getService()
				   .findByDocumentNameAndDocumentYearAndCertificateCode(documentName,
			documentYear, certificateCode);
	}

	public static com.fds.nsw.nghiepvu.model.ResultCertificate fetchByF_BY4(
		long documentName, int documentYear, java.lang.String certificateCode,
		java.lang.String certificateNo)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .fetchByF_BY4(documentName, documentYear, certificateCode,
			certificateNo);
	}

	public static com.fds.nsw.nghiepvu.model.ResultCertificate findByCrewNameAndCertificateCode(
		long documentName, int documentYear, java.lang.String certificateCode,
		java.lang.String crewName)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .findByCrewNameAndCertificateCode(documentName,
			documentYear, certificateCode, crewName);
	}

	public static void clearService() {
		_service = null;
	}

	
}