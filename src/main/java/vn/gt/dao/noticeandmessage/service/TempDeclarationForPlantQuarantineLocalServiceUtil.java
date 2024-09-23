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
// * The utility for the temp declaration for plant quarantine local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.TempDeclarationForPlantQuarantineLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
// *
// * <p>
// * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
// * </p>
// *
// * @author win_64
// * @see TempDeclarationForPlantQuarantineLocalService
// * @see vn.gt.dao.noticeandmessage.service.base.TempDeclarationForPlantQuarantineLocalServiceBaseImpl
// * @see vn.gt.dao.noticeandmessage.service.impl.TempDeclarationForPlantQuarantineLocalServiceImpl
// * @generated
// */
//import org.springframework.stereotype.Component; @Component public class TempDeclarationForPlantQuarantineLocalServiceUtil {
//public TempDeclarationForPlantQuarantineLocalServiceUtil(TempDeclarationForPlantQuarantineLocalServiceImpl service) {
//TempDeclarationForPlantQuarantineLocalServiceUtil._service = service;
//}
//public static TempDeclarationForPlantQuarantineLocalServiceImpl getService() {
//return _service;
//}
//private static TempDeclarationForPlantQuarantineLocalServiceImpl _service;
//	/*
//	 * NOTE FOR DEVELOPERS:
//	 *
//	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.TempDeclarationForPlantQuarantineLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
//	 */
//
//	/**
//	* Adds the temp declaration for plant quarantine to the database. Also notifies the appropriate model listeners.
//	*
//	* @param tempDeclarationForPlantQuarantine the temp declaration for plant quarantine
//	* @return the temp declaration for plant quarantine that was added
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.TempDeclarationForPlantQuarantine addTempDeclarationForPlantQuarantine(
//		com.fds.nsw.nghiepvu.model.TempDeclarationForPlantQuarantine tempDeclarationForPlantQuarantine)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService()
//				   .addTempDeclarationForPlantQuarantine(tempDeclarationForPlantQuarantine);
//	}
//
//	/**
//	* Creates a new temp declaration for plant quarantine with the primary key. Does not add the temp declaration for plant quarantine to the database.
//	*
//	* @param id the primary key for the new temp declaration for plant quarantine
//	* @return the new temp declaration for plant quarantine
//	*/
//	public static com.fds.nsw.nghiepvu.model.TempDeclarationForPlantQuarantine createTempDeclarationForPlantQuarantine(
//		long id) {
//		return getService().createTempDeclarationForPlantQuarantine(id);
//	}
//
//	/**
//	* Deletes the temp declaration for plant quarantine with the primary key from the database. Also notifies the appropriate model listeners.
//	*
//	* @param id the primary key of the temp declaration for plant quarantine
//	* @throws PortalException if a temp declaration for plant quarantine with the primary key could not be found
//	* @throws SystemException if a system exception occurred
//	*/
//	public static void deleteTempDeclarationForPlantQuarantine(long id)
//		throws com.fds.nsw.kernel.exception.PortalException,
//			com.fds.nsw.kernel.exception.SystemException {
//		getService().deleteTempDeclarationForPlantQuarantine(id);
//	}
//
//	/**
//	* Deletes the temp declaration for plant quarantine from the database. Also notifies the appropriate model listeners.
//	*
//	* @param tempDeclarationForPlantQuarantine the temp declaration for plant quarantine
//	* @throws SystemException if a system exception occurred
//	*/
//	public static void deleteTempDeclarationForPlantQuarantine(
//		com.fds.nsw.nghiepvu.model.TempDeclarationForPlantQuarantine tempDeclarationForPlantQuarantine)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		getService()
//			.deleteTempDeclarationForPlantQuarantine(tempDeclarationForPlantQuarantine);
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
//	public static com.fds.nsw.nghiepvu.model.TempDeclarationForPlantQuarantine fetchTempDeclarationForPlantQuarantine(
//		long id) throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().fetchTempDeclarationForPlantQuarantine(id);
//	}
//
//	/**
//	* Returns the temp declaration for plant quarantine with the primary key.
//	*
//	* @param id the primary key of the temp declaration for plant quarantine
//	* @return the temp declaration for plant quarantine
//	* @throws PortalException if a temp declaration for plant quarantine with the primary key could not be found
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.TempDeclarationForPlantQuarantine getTempDeclarationForPlantQuarantine(
//		long id)
//		throws com.fds.nsw.kernel.exception.PortalException,
//			com.fds.nsw.kernel.exception.SystemException {
//		return getService().getTempDeclarationForPlantQuarantine(id);
//	}
//
//
//
//	/**
//	* Returns a range of all the temp declaration for plant quarantines.
//	*
//	* <p>
//	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
//	* </p>
//	*
//	* @param start the lower bound of the range of temp declaration for plant quarantines
//	* @param end the upper bound of the range of temp declaration for plant quarantines (not inclusive)
//	* @return the range of temp declaration for plant quarantines
//	* @throws SystemException if a system exception occurred
//	*/
//	public static java.util.List<com.fds.nsw.nghiepvu.model.TempDeclarationForPlantQuarantine> getTempDeclarationForPlantQuarantines(
//		int start, int end)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().getTempDeclarationForPlantQuarantines(start, end);
//	}
//
//	/**
//	* Returns the number of temp declaration for plant quarantines.
//	*
//	* @return the number of temp declaration for plant quarantines
//	* @throws SystemException if a system exception occurred
//	*/
//	public static int getTempDeclarationForPlantQuarantinesCount()
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService().getTempDeclarationForPlantQuarantinesCount();
//	}
//
//	/**
//	* Updates the temp declaration for plant quarantine in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
//	*
//	* @param tempDeclarationForPlantQuarantine the temp declaration for plant quarantine
//	* @return the temp declaration for plant quarantine that was updated
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.TempDeclarationForPlantQuarantine updateTempDeclarationForPlantQuarantine(
//		com.fds.nsw.nghiepvu.model.TempDeclarationForPlantQuarantine tempDeclarationForPlantQuarantine)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService()
//				   .updateTempDeclarationForPlantQuarantine(tempDeclarationForPlantQuarantine);
//	}
//
//	/**
//	* Updates the temp declaration for plant quarantine in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
//	*
//	* @param tempDeclarationForPlantQuarantine the temp declaration for plant quarantine
//	* @param merge whether to merge the temp declaration for plant quarantine with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
//	* @return the temp declaration for plant quarantine that was updated
//	* @throws SystemException if a system exception occurred
//	*/
//	public static com.fds.nsw.nghiepvu.model.TempDeclarationForPlantQuarantine updateTempDeclarationForPlantQuarantine(
//		com.fds.nsw.nghiepvu.model.TempDeclarationForPlantQuarantine tempDeclarationForPlantQuarantine,
//		boolean merge)
//		throws com.fds.nsw.kernel.exception.SystemException {
//		return getService()
//				   .updateTempDeclarationForPlantQuarantine(tempDeclarationForPlantQuarantine,
//			merge);
//	}
//
//
//
//
//
//	/**
//	* Returns the number of temp declaration for plant quarantines where documentName = &#63; and documentYear = &#63;.
//	*
//	* @param documentName the document name
//	* @param documentYear the document year
//	* @return the number of matching temp declaration for plant quarantines
//	* @throws SystemException if a system exception occurred
//	*/
//	public static int countBydocumentNameAnddocumentYear(long documentName,
//		int documentYear) {
//		return getService()
//				   .countBydocumentNameAnddocumentYear(documentName,
//			documentYear);
//	}
//
//	/**
//	* Returns all the temp declaration for plant quarantines where documentName = &#63; and documentYear = &#63;.
//	*
//	* @param documentName the document name
//	* @param documentYear the document year
//	* @return the matching temp declaration for plant quarantines
//	* @throws SystemException if a system exception occurred
//	*/
//	public static java.util.List<com.fds.nsw.nghiepvu.model.TempDeclarationForPlantQuarantine> findBydocumentNameAnddocumentYear(
//		long documentName, int documentYear) {
//		return getService()
//				   .findBydocumentNameAnddocumentYear(documentName, documentYear);
//	}
//
//	public static java.util.List<com.fds.nsw.nghiepvu.model.TempDeclarationForPlantQuarantine> findBydocumentNameAnddocumentYearRequestState(
//		long documentName, int documentYear, int requestState) {
//		return getService()
//				   .findBydocumentNameAnddocumentYearRequestState(documentName,
//			documentYear, requestState);
//	}
//
//	public static void clearService() {
//		_service = null;
//	}
//
//
//
//	public void setService(
//		TempDeclarationForPlantQuarantineLocalService service) {
//		MethodCache.remove(TempDeclarationForPlantQuarantineLocalService.class);
//
//		_service = service;
//
//		ReferenceRegistry.registerReference(TempDeclarationForPlantQuarantineLocalServiceUtil.class,
//			"_service");
//		MethodCache.remove(TempDeclarationForPlantQuarantineLocalService.class);
//	}
//
//
//}