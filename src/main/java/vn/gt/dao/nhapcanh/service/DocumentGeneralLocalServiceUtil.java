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
//package vn.gt.dao.nhapcanh.service;
//
//
//
//
//
//
///**
// * The utility for the document general local service. This utility wraps {@link vn.gt.dao.nhapcanh.service.impl.DocumentGeneralLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
// *
// * <p>
// * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
// * </p>
// *
// * @author win_64
// * @see DocumentGeneralLocalService
// * @see vn.gt.dao.nhapcanh.service.base.DocumentGeneralLocalServiceBaseImpl
// * @see vn.gt.dao.nhapcanh.service.impl.DocumentGeneralLocalServiceImpl
// * @generated
// */
//import org.springframework.stereotype.Component; @Component public class DocumentGeneralLocalServiceUtil {
//public DocumentGeneralLocalServiceUtil(DocumentGeneralLocalServiceImpl service) {
//DocumentGeneralLocalServiceUtil._service = service;
//}
//public static DocumentGeneralLocalServiceImpl getService() {
//return _service;
//}
//private static DocumentGeneralLocalServiceImpl _service;
//	/*
//	 * NOTE FOR DEVELOPERS:
//	 *
//	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.nhapcanh.service.impl.DocumentGeneralLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
//	 */
//
//	/**
//	* Adds the document general to the database. Also notifies the appropriate model listeners.
//	*
//	* @param documentGeneral the document general
//	* @return the document general that was added
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.DocumentGeneral addDocumentGeneral(
//		com.fds.nsw.nghiepvu.model.DocumentGeneral documentGeneral)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().addDocumentGeneral(documentGeneral);
//	}
//
//	/**
//	* Creates a new document general with the primary key. Does not add the document general to the database.
//	*
//	* @param id the primary key for the new document general
//	* @return the new document general
//	*/
//	public static com.fds.nsw.nghiepvu.model.DocumentGeneral createDocumentGeneral(
//		long id) {
//		return getService().createDocumentGeneral(id);
//	}
//
//	/**
//	* Deletes the document general with the primary key from the database. Also notifies the appropriate model listeners.
//	*
//	* @param id the primary key of the document general
//	* @throws PortalException if a document general with the primary key could not be found
//	* @throws SystemException if a system exception occurred
//	*/
//	public static void deleteDocumentGeneral(long id)
//		throws com.fds.nsw.kernel.exception.PortalException,
//			com.fds.nsw.kernel.exception.SystemException {
//		getService().deleteDocumentGeneral(id);
//	}
//
//	/**
//	* Deletes the document general from the database. Also notifies the appropriate model listeners.
//	*
//	* @param documentGeneral the document general
//	* @throws SystemException if a system exception occurred
//	*/
//	public static void deleteDocumentGeneral(
//		com.fds.nsw.nghiepvu.model.DocumentGeneral documentGeneral)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		getService().deleteDocumentGeneral(documentGeneral);
//	}
//
//
//
//
//
//
//
//
//
//
//	public static com.fds.nsw.nghiepvu.model.DocumentGeneral fetchDocumentGeneral(
//		long id) throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().fetchDocumentGeneral(id);
//	}
//
//	/**
//	* Returns the document general with the primary key.
//	*
//	* @param id the primary key of the document general
//	* @return the document general
//	* @throws PortalException if a document general with the primary key could not be found
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.DocumentGeneral getDocumentGeneral(
//		long id)
//		throws com.fds.nsw.kernel.exception.PortalException,
//			com.fds.nsw.kernel.exception.SystemException {
//		return getService().getDocumentGeneral(id);
//	}
//
//
//
//	/**
//	* Returns a range of all the document generals.
//	*
//	* <p>
//	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
//	* </p>
//	*
//	* @param start the lower bound of the range of document generals
//	* @param end the upper bound of the range of document generals (not inclusive)
//	* @return the range of document generals
//	* @throws SystemException if a system exception occurred
//	*/
//	public static java.util.List<com.fds.nsw.nghiepvu.model.DocumentGeneral> getDocumentGenerals(
//		int start, int end)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().getDocumentGenerals(start, end);
//	}
//
//	/**
//	* Returns the number of document generals.
//	*
//	* @return the number of document generals
//	* @throws SystemException if a system exception occurred
//	*/
//	public static int getDocumentGeneralsCount()
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().getDocumentGeneralsCount();
//	}
//
//	/**
//	* Updates the document general in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
//	*
//	* @param documentGeneral the document general
//	* @return the document general that was updated
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.DocumentGeneral updateDocumentGeneral(
//		com.fds.nsw.nghiepvu.model.DocumentGeneral documentGeneral)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().updateDocumentGeneral(documentGeneral);
//	}
//
//	/**
//	* Updates the document general in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
//	*
//	* @param documentGeneral the document general
//	* @param merge whether to merge the document general with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
//	* @return the document general that was updated
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.DocumentGeneral updateDocumentGeneral(
//		com.fds.nsw.nghiepvu.model.DocumentGeneral documentGeneral, boolean merge)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().updateDocumentGeneral(documentGeneral, merge);
//	}
//
//
//
//
//
//	public static java.util.List<com.fds.nsw.nghiepvu.model.DocumentGeneral> findAll(
//		int start, int end) {
//		return getService().findAll(start, end);
//	}
//
//	/**
//	* Returns the number of document generals.
//	*
//	* @return the number of document generals
//	* @throws SystemException if a system exception occurred
//	*/
//	public static int countAll() {
//		return getService().countAll();
//	}
//
//	public static void clearService() {
//		_service = null;
//	}
//
//
//
//
//
//
//}