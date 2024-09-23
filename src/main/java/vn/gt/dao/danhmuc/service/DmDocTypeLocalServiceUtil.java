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

package vn.gt.dao.danhmuc.service;






/**
 * The utility for the dm doc type local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmDocTypeLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmDocTypeLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmDocTypeLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmDocTypeLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmDocTypeLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class DmDocTypeLocalServiceUtil {
public DmDocTypeLocalServiceUtil(DmDocTypeLocalServiceImpl service) {
DmDocTypeLocalServiceUtil._service = service;
}
public static DmDocTypeLocalServiceImpl getService() {
return _service;
}
private static DmDocTypeLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmDocTypeLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dm doc type to the database. Also notifies the appropriate model listeners.
	*
	* @param dmDocType the dm doc type
	* @return the dm doc type that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmDocType addDmDocType(
		com.fds.nsw.nghiepvu.model.DmDocType dmDocType)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmDocType(dmDocType);
	}

	/**
	* Creates a new dm doc type with the primary key. Does not add the dm doc type to the database.
	*
	* @param id the primary key for the new dm doc type
	* @return the new dm doc type
	*/
	public static com.fds.nsw.nghiepvu.model.DmDocType createDmDocType(int id) {
		return getService().createDmDocType(id);
	}

	/**
	* Deletes the dm doc type with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the dm doc type
	* @throws PortalException if a dm doc type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmDocType(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmDocType(id);
	}

	/**
	* Deletes the dm doc type from the database. Also notifies the appropriate model listeners.
	*
	* @param dmDocType the dm doc type
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmDocType(
		com.fds.nsw.nghiepvu.model.DmDocType dmDocType)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmDocType(dmDocType);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmDocType> findByDocumentTypeName(
			java.lang.String documentTypeName, int start, int end) {
		return getService().findByDocumentTypeName(documentTypeName, start, end);
	}

	public static com.fds.nsw.nghiepvu.model.DmDocType findByDocumentType(
			java.lang.String documentType) {
		return getService().findByDocumentType(documentType);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmDocType> findDocTypes(
			java.lang.String documentTypeName, java.lang.String isDelete,
			java.lang.String documentTypeCodeGroup, int start, int end) {
		return getService()
				.findDocTypes(documentTypeName, isDelete,
						documentTypeCodeGroup, start, end);
	}

	public static long countDocTypes(java.lang.String documentTypeName,
									 java.lang.String isDelete, java.lang.String documentTypeCodeGroup) {
		return getService()
				.countDocTypes(documentTypeName, isDelete,
						documentTypeCodeGroup);
	}









	public static com.fds.nsw.nghiepvu.model.DmDocType fetchDmDocType(int id)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmDocType(id);
	}

	/**
	* Returns the dm doc type with the primary key.
	*
	* @param id the primary key of the dm doc type
	* @return the dm doc type
	* @throws PortalException if a dm doc type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmDocType getDmDocType(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmDocType(id);
	}

	

	/**
	* Returns a range of all the dm doc types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of dm doc types
	* @param end the upper bound of the range of dm doc types (not inclusive)
	* @return the range of dm doc types
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmDocType> getDmDocTypes(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmDocTypes(start, end);
	}

	/**
	* Returns the number of dm doc types.
	*
	* @return the number of dm doc types
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmDocTypesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmDocTypesCount();
	}

	/**
	* Updates the dm doc type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmDocType the dm doc type
	* @return the dm doc type that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmDocType updateDmDocType(
		com.fds.nsw.nghiepvu.model.DmDocType dmDocType)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmDocType(dmDocType);
	}

	/**
	* Updates the dm doc type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmDocType the dm doc type
	* @param merge whether to merge the dm doc type with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm doc type that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmDocType updateDmDocType(
		com.fds.nsw.nghiepvu.model.DmDocType dmDocType, boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmDocType(dmDocType, merge);
	}

	

	

	public static com.fds.nsw.nghiepvu.model.DmDocType getByDocumentTypeCode(
		java.lang.String documentTypeCode) {
		return getService().getByDocumentTypeCode(documentTypeCode);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}