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
 * The utility for the result declaration local service. This utility wraps {@link vn.gt.dao.result.service.impl.ResultDeclarationLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see ResultDeclarationLocalService
 * @see vn.gt.dao.result.service.base.ResultDeclarationLocalServiceBaseImpl
 * @see vn.gt.dao.result.service.impl.ResultDeclarationLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.result.service.impl.ResultDeclarationLocalServiceImpl;
import org.springframework.stereotype.Component; @Component   public class ResultDeclarationLocalServiceUtil {
	public ResultDeclarationLocalServiceUtil(ResultDeclarationLocalServiceImpl service) {
		ResultDeclarationLocalServiceUtil._service = service;
	}
	public static ResultDeclarationLocalServiceImpl getService() {
		return _service;
	}
	private static ResultDeclarationLocalServiceImpl _service;

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.result.service.impl.ResultDeclarationLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the result declaration to the database. Also notifies the appropriate model listeners.
	 *
	 * @param resultDeclaration the result declaration
	 * @return the result declaration that was added
	 * @throws SystemException if a system exception occurred
	 */
	public static com.fds.nsw.nghiepvu.model.ResultDeclaration addResultDeclaration(
			com.fds.nsw.nghiepvu.model.ResultDeclaration resultDeclaration)
			throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addResultDeclaration(resultDeclaration);
	}

	/**
	 * Creates a new result declaration with the primary key. Does not add the result declaration to the database.
	 *
	 * @param id the primary key for the new result declaration
	 * @return the new result declaration
	 */
	public static com.fds.nsw.nghiepvu.model.ResultDeclaration createResultDeclaration(
			long id) {
		return getService().createResultDeclaration(id);
	}

	/**
	 * Deletes the result declaration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the result declaration
	 * @throws PortalException if a result declaration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public static void deleteResultDeclaration(long id)
			throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteResultDeclaration(id);
	}

	/**
	 * Deletes the result declaration from the database. Also notifies the appropriate model listeners.
	 *
	 * @param resultDeclaration the result declaration
	 * @throws SystemException if a system exception occurred
	 */
	public static void deleteResultDeclaration(
			com.fds.nsw.nghiepvu.model.ResultDeclaration resultDeclaration)
			throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteResultDeclaration(resultDeclaration);
	}

	


	

	

	


	public static com.fds.nsw.nghiepvu.model.ResultDeclaration fetchResultDeclaration(
			long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchResultDeclaration(id);
	}

	/**
	 * Returns the result declaration with the primary key.
	 *
	 * @param id the primary key of the result declaration
	 * @return the result declaration
	 * @throws PortalException if a result declaration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public static com.fds.nsw.nghiepvu.model.ResultDeclaration getResultDeclaration(
			long id)
			throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getResultDeclaration(id);
	}



	/**
	 * Returns a range of all the result declarations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of result declarations
	 * @param end the upper bound of the range of result declarations (not inclusive)
	 * @return the range of result declarations
	 * @throws SystemException if a system exception occurred
	 */
	public static java.util.List<com.fds.nsw.nghiepvu.model.ResultDeclaration> getResultDeclarations(
			int start, int end)
			throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getResultDeclarations(start, end);
	}

	/**
	 * Returns the number of result declarations.
	 *
	 * @return the number of result declarations
	 * @throws SystemException if a system exception occurred
	 */
	public static int getResultDeclarationsCount()
			throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getResultDeclarationsCount();
	}

	/**
	 * Updates the result declaration in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param resultDeclaration the result declaration
	 * @return the result declaration that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public static com.fds.nsw.nghiepvu.model.ResultDeclaration updateResultDeclaration(
			com.fds.nsw.nghiepvu.model.ResultDeclaration resultDeclaration)
			throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateResultDeclaration(resultDeclaration);
	}

	/**
	 * Updates the result declaration in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param resultDeclaration the result declaration
	 * @param merge whether to merge the result declaration with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	 * @return the result declaration that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public static com.fds.nsw.nghiepvu.model.ResultDeclaration updateResultDeclaration(
			com.fds.nsw.nghiepvu.model.ResultDeclaration resultDeclaration,
			boolean merge)
			throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateResultDeclaration(resultDeclaration, merge);
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


	public static java.util.List<com.fds.nsw.nghiepvu.model.ResultDeclaration> findResultDeclarationByBusinessTypeCode(
			int businessTypeCode) {
		return getService()
				.findResultDeclarationByBusinessTypeCode(businessTypeCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.ResultDeclaration> findByDocumentNameAndBusinessTypeCodeAndDocumentYear(
			int businessTypeCode, long documentName, int documentYear) {
		return getService()
				.findByDocumentNameAndBusinessTypeCodeAndDocumentYear(businessTypeCode,
						documentName, documentYear);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.ResultDeclaration> DocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode(
			int businessTypeCode, long documentName, int documentYear,
			java.lang.String requestCode) {
		return getService()
				.DocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode(businessTypeCode,
						documentName, documentYear, requestCode);
	}

	public static com.fds.nsw.nghiepvu.model.ResultDeclaration findbyDocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode(
			int businessTypeCode, long documentName, int documentYear,
			java.lang.String requestCode) {
		return getService()
				.findbyDocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode(businessTypeCode,
						documentName, documentYear, requestCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.ResultDeclaration> FindByDocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode(
			int businessTypeCode, long documentName, int documentYear,
			java.lang.String requestCode) {
		return getService()
				.FindByDocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode(businessTypeCode,
						documentName, documentYear, requestCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.ResultDeclaration> findByDocumentNameAndDocumentYear(
			long documentName, int documentYear) {
		return getService()
				.findByDocumentNameAndDocumentYear(documentName, documentYear);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.ResultDeclaration> findByDocumentNameAndDocumentYearOrderByBusinessOrder(
			long documentName, int documentYear) {
		return getService()
				.findByDocumentNameAndDocumentYearOrderByBusinessOrder(documentName,
						documentYear);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.ResultDeclaration> findResultDeclarationByDocumentNameAndDocumentYearNcQcReport(
			long documentName, int documentYear) {
		return getService()
				.findResultDeclarationByDocumentNameAndDocumentYearNcQcReport(documentName,
						documentYear);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.ResultDeclaration> findResultDeclarationByDocumentNameAndDocumentYearXcReport(
			long documentName, int documentYear) {
		return getService()
				.findResultDeclarationByDocumentNameAndDocumentYearXcReport(documentName,
						documentYear);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.ResultDeclaration> findResultDeclarationByDocumentNameAndDocumentYearNXDoiChieu(
			long documentName, int documentYear) {
		return getService()
				.findResultDeclarationByDocumentNameAndDocumentYearNXDoiChieu(documentName,
						documentYear);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.ResultDeclaration> findResultDeclarationByDocumentNameAndDocumentYearQCDoiChieu(
			long documentName, int documentYear) {
		return getService()
				.findResultDeclarationByDocumentNameAndDocumentYearQCDoiChieu(documentName,
						documentYear);
	}

	public static int countByRequestCode(java.lang.String requestCode) {
		return getService().countByRequestCode(requestCode);
	}

	public static com.fds.nsw.nghiepvu.model.ResultDeclaration findByRequestCode(
			java.lang.String requestCode) {
		return getService().findByRequestCode(requestCode);
	}

	public static int countByDocumentNameAndBusinessTypeCodeAndDocumentYear(
			int businessTypeCode, long documentName, int documentYear) {
		return getService()
				.countByDocumentNameAndBusinessTypeCodeAndDocumentYear(businessTypeCode,
						documentName, documentYear);
	}

	public static int countByDocumentNameAndDocumentYear(long documentName,
														 int documentYear) {
		return getService()
				.countByDocumentNameAndDocumentYear(documentName,
						documentYear);
	}

	public static int countByRequestCodeAndBusinessTypeCode(
			java.lang.String requestCode, int businessTypeCode) {
		return getService()
				.countByRequestCodeAndBusinessTypeCode(requestCode,
						businessTypeCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.ResultDeclaration> findByRequestCodeAndBusinessTypeCode(
			java.lang.String requestCode, int businessTypeCode) {
		return getService()
				.findByRequestCodeAndBusinessTypeCode(requestCode,
						businessTypeCode);
	}

	public static void clearService() {
		_service = null;
	}

}