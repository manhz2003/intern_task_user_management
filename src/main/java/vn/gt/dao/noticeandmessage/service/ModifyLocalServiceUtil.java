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
// * The utility for the modify local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.ModifyLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
// *
// * <p>
// * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
// * </p>
// *
// * @author win_64
// * @see ModifyLocalService
// * @see vn.gt.dao.noticeandmessage.service.base.ModifyLocalServiceBaseImpl
// * @see vn.gt.dao.noticeandmessage.service.impl.ModifyLocalServiceImpl
// * @generated
// */
//import org.springframework.stereotype.Component; @Component public class ModifyLocalServiceUtil {
//public ModifyLocalServiceUtil(ModifyLocalServiceImpl service) {
//ModifyLocalServiceUtil._service = service;
//}
//public static ModifyLocalServiceImpl getService() {
//return _service;
//}
//private static ModifyLocalServiceImpl _service;
//	/*
//	 * NOTE FOR DEVELOPERS:
//	 *
//	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.ModifyLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
//	 */
//
//	/**
//	* Adds the modify to the database. Also notifies the appropriate model listeners.
//	*
//	* @param modify the modify
//	* @return the modify that was added
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.Modify addModify(
//		com.fds.nsw.nghiepvu.model.Modify modify)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().addModify(modify);
//	}
//
//	/**
//	* Creates a new modify with the primary key. Does not add the modify to the database.
//	*
//	* @param id the primary key for the new modify
//	* @return the new modify
//	*/
//	public static com.fds.nsw.nghiepvu.model.Modify createModify(long id) {
//		return getService().createModify(id);
//	}
//
//	/**
//	* Deletes the modify with the primary key from the database. Also notifies the appropriate model listeners.
//	*
//	* @param id the primary key of the modify
//	* @throws PortalException if a modify with the primary key could not be found
//	* @throws SystemException if a system exception occurred
//	*/
//	public static void deleteModify(long id)
//		throws com.fds.nsw.kernel.exception.PortalException,
//			com.fds.nsw.kernel.exception.SystemException {
//		getService().deleteModify(id);
//	}
//
//	/**
//	* Deletes the modify from the database. Also notifies the appropriate model listeners.
//	*
//	* @param modify the modify
//	* @throws SystemException if a system exception occurred
//	*/
//	public static void deleteModify(
//		com.fds.nsw.nghiepvu.model.Modify modify)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		getService().deleteModify(modify);
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
//	public static com.fds.nsw.nghiepvu.model.Modify fetchModify(long id)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().fetchModify(id);
//	}
//
//	/**
//	* Returns the modify with the primary key.
//	*
//	* @param id the primary key of the modify
//	* @return the modify
//	* @throws PortalException if a modify with the primary key could not be found
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.Modify getModify(long id)
//		throws com.fds.nsw.kernel.exception.PortalException,
//			com.fds.nsw.kernel.exception.SystemException {
//		return getService().getModify(id);
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
//	* Returns a range of all the modifies.
//	*
//	* <p>
//	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
//	* </p>
//	*
//	* @param start the lower bound of the range of modifies
//	* @param end the upper bound of the range of modifies (not inclusive)
//	* @return the range of modifies
//	* @throws SystemException if a system exception occurred
//	*/
//	public static java.util.List<com.fds.nsw.nghiepvu.model.Modify> getModifies(
//		int start, int end)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().getModifies(start, end);
//	}
//
//	/**
//	* Returns the number of modifies.
//	*
//	* @return the number of modifies
//	* @throws SystemException if a system exception occurred
//	*/
//	public static int getModifiesCount()
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().getModifiesCount();
//	}
//
//	/**
//	* Updates the modify in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
//	*
//	* @param modify the modify
//	* @return the modify that was updated
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.Modify updateModify(
//		com.fds.nsw.nghiepvu.model.Modify modify)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().updateModify(modify);
//	}
//
//	/**
//	* Updates the modify in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
//	*
//	* @param modify the modify
//	* @param merge whether to merge the modify with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
//	* @return the modify that was updated
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.Modify updateModify(
//		com.fds.nsw.nghiepvu.model.Modify modify, boolean merge)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().updateModify(modify, merge);
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
//	public static void clearService() {
//		_service = null;
//	}
//
//	public static ModifyLocalServiceImpl getService() {
//		if (_service == null) {
//			Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
//					ModifyLocalService.class.getName());
//			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
//					"portletClassLoader");
//
//			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
//					ModifyLocalService.class.getName(), portletClassLoader);
//
//			_service = new ModifyLocalServiceClp(classLoaderProxy);
//
//			ClpSerializer.setClassLoader(portletClassLoader);
//
//			ReferenceRegistry.registerReference(ModifyLocalServiceUtil.class,
//				"_service");
//			MethodCache.remove(ModifyLocalService.class);
//		}
//
//		return _service;
//	}
//
//	public void setService(ModifyLocalServiceImpl service) {
//		MethodCache.remove(ModifyLocalService.class);
//
//		_service = service;
//
//		ReferenceRegistry.registerReference(ModifyLocalServiceUtil.class,
//			"_service");
//		MethodCache.remove(ModifyLocalService.class);
//	}
//
//
//}