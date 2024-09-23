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
// * The utility for the document local service. This utility wraps {@link vn.gt.dao.nhapcanh.service.impl.DocumentLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
// *
// * <p>
// * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
// * </p>
// *
// * @author win_64
// * @see DocumentLocalService
// * @see vn.gt.dao.nhapcanh.service.base.DocumentLocalServiceBaseImpl
// * @see vn.gt.dao.nhapcanh.service.impl.DocumentLocalServiceImpl
// * @generated
// */
//import org.springframework.stereotype.Component; @Component public class DocumentLocalServiceUtil {
//public DocumentLocalServiceUtil(DocumentLocalServiceImpl service) {
//DocumentLocalServiceUtil._service = service;
//}
//public static DocumentLocalServiceImpl getService() {
//return _service;
//}
//private static DocumentLocalServiceImpl _service;
//	/*
//	 * NOTE FOR DEVELOPERS:
//	 *
//	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.nhapcanh.service.impl.DocumentLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
//	 */
//
//	/**
//	* Adds the document to the database. Also notifies the appropriate model listeners.
//	*
//	* @param document the document
//	* @return the document that was added
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.Document addDocument(
//		com.fds.nsw.nghiepvu.model.Document document)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().addDocument(document);
//	}
//
//	/**
//	* Creates a new document with the primary key. Does not add the document to the database.
//	*
//	* @param id the primary key for the new document
//	* @return the new document
//	*/
//	public static com.fds.nsw.nghiepvu.model.Document createDocument(long id) {
//		return getService().createDocument(id);
//	}
//
//	/**
//	* Deletes the document with the primary key from the database. Also notifies the appropriate model listeners.
//	*
//	* @param id the primary key of the document
//	* @throws PortalException if a document with the primary key could not be found
//	* @throws SystemException if a system exception occurred
//	*/
//	public static void deleteDocument(long id)
//		throws com.fds.nsw.kernel.exception.PortalException,
//			com.fds.nsw.kernel.exception.SystemException {
//		getService().deleteDocument(id);
//	}
//
//	/**
//	* Deletes the document from the database. Also notifies the appropriate model listeners.
//	*
//	* @param document the document
//	* @throws SystemException if a system exception occurred
//	*/
//	public static void deleteDocument(
//		com.fds.nsw.nghiepvu.model.Document document)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		getService().deleteDocument(document);
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
//	public static com.fds.nsw.nghiepvu.model.Document fetchDocument(long id)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().fetchDocument(id);
//	}
//
//	/**
//	* Returns the document with the primary key.
//	*
//	* @param id the primary key of the document
//	* @return the document
//	* @throws PortalException if a document with the primary key could not be found
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.Document getDocument(long id)
//		throws com.fds.nsw.kernel.exception.PortalException,
//			com.fds.nsw.kernel.exception.SystemException {
//		return getService().getDocument(id);
//	}
//
//
//
//	/**
//	* Returns a range of all the documents.
//	*
//	* <p>
//	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
//	* </p>
//	*
//	* @param start the lower bound of the range of documents
//	* @param end the upper bound of the range of documents (not inclusive)
//	* @return the range of documents
//	* @throws SystemException if a system exception occurred
//	*/
//	public static java.util.List<com.fds.nsw.nghiepvu.model.Document> getDocuments(
//		int start, int end)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().getDocuments(start, end);
//	}
//
//	/**
//	* Returns the number of documents.
//	*
//	* @return the number of documents
//	* @throws SystemException if a system exception occurred
//	*/
//	public static int getDocumentsCount()
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().getDocumentsCount();
//	}
//
//	/**
//	* Updates the document in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
//	*
//	* @param document the document
//	* @return the document that was updated
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.Document updateDocument(
//		com.fds.nsw.nghiepvu.model.Document document)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().updateDocument(document);
//	}
//
//	/**
//	* Updates the document in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
//	*
//	* @param document the document
//	* @param merge whether to merge the document with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
//	* @return the document that was updated
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.Document updateDocument(
//		com.fds.nsw.nghiepvu.model.Document document, boolean merge)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().updateDocument(document, merge);
//	}
//
//
//
//
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