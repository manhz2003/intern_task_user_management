///**
// * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
// *
// * This library is free software; you can redistribute it and/or modify it under
// * the terms of the GNU Lesser General Public License as published by the Free
// * Software Foundation; either version 2.1 of the License, or (at your option)
// * any later version.
// *
// * This library is distributed in the hope that it will be useful, but WITHOUT
// * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
// * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
// * details.
// */
//
//package vn.gt.dao.noticeandmessage.service;
//
//
//
//
//
//
///**
// * The utility for the issue acceptance for transit local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.IssueAcceptanceForTransitLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
// *
// * <p>
// * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
// * </p>
// *
// * @author win_64
// * @see IssueAcceptanceForTransitLocalService
// * @see vn.gt.dao.noticeandmessage.service.base.IssueAcceptanceForTransitLocalServiceBaseImpl
// * @see vn.gt.dao.noticeandmessage.service.impl.IssueAcceptanceForTransitLocalServiceImpl
// * @generated
// */
//import org.springframework.stereotype.Component; @Component public class IssueAcceptanceForTransitLocalServiceUtil {
//public IssueAcceptanceForTransitLocalServiceUtil(IssueAcceptanceForTransitLocalServiceImpl service) {
//IssueAcceptanceForTransitLocalServiceUtil._service = service;
//}
//public static IssueAcceptanceForTransitLocalServiceImpl getService() {
//return _service;
//}
//private static IssueAcceptanceForTransitLocalServiceImpl _service;
//	/*
//	 * NOTE FOR DEVELOPERS:
//	 *
//	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.IssueAcceptanceForTransitLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
//	 */
//
//	/**
//	* Adds the issue acceptance for transit to the database. Also notifies the appropriate model listeners.
//	*
//	* @param issueAcceptanceForTransit the issue acceptance for transit
//	* @return the issue acceptance for transit that was added
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.IssueAcceptanceForTransit addIssueAcceptanceForTransit(
//		com.fds.nsw.nghiepvu.model.IssueAcceptanceForTransit issueAcceptanceForTransit)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService()
//				   .addIssueAcceptanceForTransit(issueAcceptanceForTransit);
//	}
//
//	/**
//	* Creates a new issue acceptance for transit with the primary key. Does not add the issue acceptance for transit to the database.
//	*
//	* @param id the primary key for the new issue acceptance for transit
//	* @return the new issue acceptance for transit
//	*/
//	public static com.fds.nsw.nghiepvu.model.IssueAcceptanceForTransit createIssueAcceptanceForTransit(
//		long id) {
//		return getService().createIssueAcceptanceForTransit(id);
//	}
//
//	/**
//	* Deletes the issue acceptance for transit with the primary key from the database. Also notifies the appropriate model listeners.
//	*
//	* @param id the primary key of the issue acceptance for transit
//	* @throws PortalException if a issue acceptance for transit with the primary key could not be found
//	* @throws SystemException if a system exception occurred
//	*/
//	public static void deleteIssueAcceptanceForTransit(long id)
//		throws com.fds.nsw.kernel.exception.PortalException,
//			com.fds.nsw.kernel.exception.SystemException {
//		getService().deleteIssueAcceptanceForTransit(id);
//	}
//
//	/**
//	* Deletes the issue acceptance for transit from the database. Also notifies the appropriate model listeners.
//	*
//	* @param issueAcceptanceForTransit the issue acceptance for transit
//	* @throws SystemException if a system exception occurred
//	*/
//	public static void deleteIssueAcceptanceForTransit(
//		com.fds.nsw.nghiepvu.model.IssueAcceptanceForTransit issueAcceptanceForTransit)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		getService().deleteIssueAcceptanceForTransit(issueAcceptanceForTransit);
//	}
//
//	/**
//	* Performs a dynamic query on the database and returns the matching rows.
//	*
//	* @param dynamicQuery the dynamic query
//	* @return the matching rows
//	* @throws SystemException if a system exception occurred
//	*/
//	
//	public static java.util.List dynamicQuery(
//		com.fds.nsw.kernel.dao.orm.DynamicQuery dynamicQuery)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().dynamicQuery(dynamicQuery);
//	}
//
//	/**
//	* Performs a dynamic query on the database and returns a range of the matching rows.
//	*
//	* <p>
//	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
//	* </p>
//	*
//	* @param dynamicQuery the dynamic query
//	* @param start the lower bound of the range of model instances
//	* @param end the upper bound of the range of model instances (not inclusive)
//	* @return the range of matching rows
//	* @throws SystemException if a system exception occurred
//	*/
//	
//	public static java.util.List dynamicQuery(
//		com.fds.nsw.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
//		int end) throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().dynamicQuery(dynamicQuery, start, end);
//	}
//
//	/**
//	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
//	*
//	* <p>
//	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
//	* </p>
//	*
//	* @param dynamicQuery the dynamic query
//	* @param start the lower bound of the range of model instances
//	* @param end the upper bound of the range of model instances (not inclusive)
//	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
//	* @return the ordered range of matching rows
//	* @throws SystemException if a system exception occurred
//	*/
//	
//	public static java.util.List dynamicQuery(
//		com.fds.nsw.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
//		int end,
//		com.fds.nsw.kernel.util.OrderByComparator orderByComparator)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService()
//				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
//	}
//
//	/**
//	* Returns the number of rows that match the dynamic query.
//	*
//	* @param dynamicQuery the dynamic query
//	* @return the number of rows that match the dynamic query
//	* @throws SystemException if a system exception occurred
//	*/
//	public static long dynamicQueryCount(
//		com.fds.nsw.kernel.dao.orm.DynamicQuery dynamicQuery)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().dynamicQueryCount(dynamicQuery);
//	}
//
//	public static com.fds.nsw.nghiepvu.model.IssueAcceptanceForTransit fetchIssueAcceptanceForTransit(
//		long id) throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().fetchIssueAcceptanceForTransit(id);
//	}
//
//	/**
//	* Returns the issue acceptance for transit with the primary key.
//	*
//	* @param id the primary key of the issue acceptance for transit
//	* @return the issue acceptance for transit
//	* @throws PortalException if a issue acceptance for transit with the primary key could not be found
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.IssueAcceptanceForTransit getIssueAcceptanceForTransit(
//		long id)
//		throws com.fds.nsw.kernel.exception.PortalException,
//			com.fds.nsw.kernel.exception.SystemException {
//		return getService().getIssueAcceptanceForTransit(id);
//	}
//
//	public static com.liferay.portal.model.PersistedModel getPersistedModel(
//		java.io.Serializable primaryKeyObj)
//		throws com.fds.nsw.kernel.exception.PortalException,
//			com.fds.nsw.kernel.exception.SystemException {
//		return getService().getPersistedModel(primaryKeyObj);
//	}
//
//	/**
//	* Returns a range of all the issue acceptance for transits.
//	*
//	* <p>
//	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
//	* </p>
//	*
//	* @param start the lower bound of the range of issue acceptance for transits
//	* @param end the upper bound of the range of issue acceptance for transits (not inclusive)
//	* @return the range of issue acceptance for transits
//	* @throws SystemException if a system exception occurred
//	*/
//	public static java.util.List<com.fds.nsw.nghiepvu.model.IssueAcceptanceForTransit> getIssueAcceptanceForTransits(
//		int start, int end)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().getIssueAcceptanceForTransits(start, end);
//	}
//
//	/**
//	* Returns the number of issue acceptance for transits.
//	*
//	* @return the number of issue acceptance for transits
//	* @throws SystemException if a system exception occurred
//	*/
//	public static int getIssueAcceptanceForTransitsCount()
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().getIssueAcceptanceForTransitsCount();
//	}
//
//	/**
//	* Updates the issue acceptance for transit in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
//	*
//	* @param issueAcceptanceForTransit the issue acceptance for transit
//	* @return the issue acceptance for transit that was updated
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.IssueAcceptanceForTransit updateIssueAcceptanceForTransit(
//		com.fds.nsw.nghiepvu.model.IssueAcceptanceForTransit issueAcceptanceForTransit)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService()
//				   .updateIssueAcceptanceForTransit(issueAcceptanceForTransit);
//	}
//
//	/**
//	* Updates the issue acceptance for transit in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
//	*
//	* @param issueAcceptanceForTransit the issue acceptance for transit
//	* @param merge whether to merge the issue acceptance for transit with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
//	* @return the issue acceptance for transit that was updated
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.IssueAcceptanceForTransit updateIssueAcceptanceForTransit(
//		com.fds.nsw.nghiepvu.model.IssueAcceptanceForTransit issueAcceptanceForTransit,
//		boolean merge)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService()
//				   .updateIssueAcceptanceForTransit(issueAcceptanceForTransit,
//			merge);
//	}
//
//	/**
//	* Returns the Spring bean ID for this bean.
//	*
//	* @return the Spring bean ID for this bean
//	*/
//	public static java.lang.String getBeanIdentifier() {
//		return getService().getBeanIdentifier();
//	}
//
//	/**
//	* Sets the Spring bean ID for this bean.
//	*
//	* @param beanIdentifier the Spring bean ID for this bean
//	*/
//	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
//		getService().setBeanIdentifier(beanIdentifier);
//	}
//
//	public static com.fds.nsw.nghiepvu.model.IssueAcceptanceForTransit findIssueAcceptanceForTransitByDocumentYearAndDocumentYear(
//		long documentName, int documentYear) {
//		return getService()
//				   .findIssueAcceptanceForTransitByDocumentYearAndDocumentYear(documentName,
//			documentYear);
//	}
//
//	public static void clearService() {
//		_service = null;
//	}
//
//	public static IssueAcceptanceForTransitLocalServiceImpl getService() {
//		if (_service == null) {
//			Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
//					IssueAcceptanceForTransitLocalService.class.getName());
//			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
//					"portletClassLoader");
//
//			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
//					IssueAcceptanceForTransitLocalService.class.getName(),
//					portletClassLoader);
//
//			_service = new IssueAcceptanceForTransitLocalServiceClp(classLoaderProxy);
//
//			ClpSerializer.setClassLoader(portletClassLoader);
//
//			ReferenceRegistry.registerReference(IssueAcceptanceForTransitLocalServiceUtil.class,
//				"_service");
//			MethodCache.remove(IssueAcceptanceForTransitLocalService.class);
//		}
//
//		return _service;
//	}
//
//	public void setService(IssueAcceptanceForTransitLocalServiceImpl service) {
//		MethodCache.remove(IssueAcceptanceForTransitLocalService.class);
//
//		_service = service;
//
//		ReferenceRegistry.registerReference(IssueAcceptanceForTransitLocalServiceUtil.class,
//			"_service");
//		MethodCache.remove(IssueAcceptanceForTransitLocalService.class);
//	}
//
//
//}