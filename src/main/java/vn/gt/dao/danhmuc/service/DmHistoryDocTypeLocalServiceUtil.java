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
 * The utility for the dm history doc type local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmHistoryDocTypeLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmHistoryDocTypeLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmHistoryDocTypeLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmHistoryDocTypeLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmHistoryDocTypeLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class DmHistoryDocTypeLocalServiceUtil {
public DmHistoryDocTypeLocalServiceUtil(DmHistoryDocTypeLocalServiceImpl service) {
DmHistoryDocTypeLocalServiceUtil._service = service;
}
public static DmHistoryDocTypeLocalServiceImpl getService() {
return _service;
}
private static DmHistoryDocTypeLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmHistoryDocTypeLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dm history doc type to the database. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryDocType the dm history doc type
	* @return the dm history doc type that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryDocType addDmHistoryDocType(
		com.fds.nsw.nghiepvu.model.DmHistoryDocType dmHistoryDocType)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmHistoryDocType(dmHistoryDocType);
	}

	/**
	* Creates a new dm history doc type with the primary key. Does not add the dm history doc type to the database.
	*
	* @param id the primary key for the new dm history doc type
	* @return the new dm history doc type
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryDocType createDmHistoryDocType(
		int id) {
		return getService().createDmHistoryDocType(id);
	}

	/**
	* Deletes the dm history doc type with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the dm history doc type
	* @throws PortalException if a dm history doc type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmHistoryDocType(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmHistoryDocType(id);
	}

	/**
	* Deletes the dm history doc type from the database. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryDocType the dm history doc type
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmHistoryDocType(
		com.fds.nsw.nghiepvu.model.DmHistoryDocType dmHistoryDocType)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmHistoryDocType(dmHistoryDocType);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.DmHistoryDocType fetchDmHistoryDocType(
		int id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmHistoryDocType(id);
	}

	/**
	* Returns the dm history doc type with the primary key.
	*
	* @param id the primary key of the dm history doc type
	* @return the dm history doc type
	* @throws PortalException if a dm history doc type with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryDocType getDmHistoryDocType(
		int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryDocType(id);
	}

	

	/**
	* Returns a range of all the dm history doc types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of dm history doc types
	* @param end the upper bound of the range of dm history doc types (not inclusive)
	* @return the range of dm history doc types
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmHistoryDocType> getDmHistoryDocTypes(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryDocTypes(start, end);
	}

	/**
	* Returns the number of dm history doc types.
	*
	* @return the number of dm history doc types
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmHistoryDocTypesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryDocTypesCount();
	}

	/**
	* Updates the dm history doc type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryDocType the dm history doc type
	* @return the dm history doc type that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryDocType updateDmHistoryDocType(
		com.fds.nsw.nghiepvu.model.DmHistoryDocType dmHistoryDocType)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmHistoryDocType(dmHistoryDocType);
	}

	/**
	* Updates the dm history doc type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryDocType the dm history doc type
	* @param merge whether to merge the dm history doc type with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm history doc type that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryDocType updateDmHistoryDocType(
		com.fds.nsw.nghiepvu.model.DmHistoryDocType dmHistoryDocType, boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmHistoryDocType(dmHistoryDocType, merge);
	}

	

	

	public static com.fds.nsw.nghiepvu.model.DmHistoryDocType findByDocumentType(
		java.lang.String documentType) {
		return getService().findByDocumentType(documentType);
	}

	public static com.fds.nsw.nghiepvu.model.DmHistoryDocType findByDocumentTypeAndSyncVersion(
		java.lang.String documentType, java.lang.String syncVersion) {
		return getService()
				   .findByDocumentTypeAndSyncVersion(documentType, syncVersion);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}