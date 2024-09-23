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
 * The utility for the dm history maritime local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmHistoryMaritimeLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmHistoryMaritimeLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmHistoryMaritimeLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmHistoryMaritimeLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmHistoryMaritimeLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class DmHistoryMaritimeLocalServiceUtil {
public DmHistoryMaritimeLocalServiceUtil(DmHistoryMaritimeLocalServiceImpl service) {
DmHistoryMaritimeLocalServiceUtil._service = service;
}
public static DmHistoryMaritimeLocalServiceImpl getService() {
return _service;
}
private static DmHistoryMaritimeLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmHistoryMaritimeLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dm history maritime to the database. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryMaritime the dm history maritime
	* @return the dm history maritime that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryMaritime addDmHistoryMaritime(
		com.fds.nsw.nghiepvu.model.DmHistoryMaritime dmHistoryMaritime)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmHistoryMaritime(dmHistoryMaritime);
	}


	public static com.fds.nsw.nghiepvu.model.DmHistoryMaritime createDmHistoryMaritime(
		int id) {
		return getService().createDmHistoryMaritime(id);
	}

	/**
	* Deletes the dm history maritime with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the dm history maritime
	* @throws PortalException if a dm history maritime with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmHistoryMaritime(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmHistoryMaritime(id);
	}

	/**
	* Deletes the dm history maritime from the database. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryMaritime the dm history maritime
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmHistoryMaritime(
		com.fds.nsw.nghiepvu.model.DmHistoryMaritime dmHistoryMaritime)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmHistoryMaritime(dmHistoryMaritime);
	}


	public static com.fds.nsw.nghiepvu.model.DmHistoryMaritime fetchDmHistoryMaritime(
		int id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmHistoryMaritime(id);
	}

	/**
	* Returns the dm history maritime with the primary key.
	*
	* @param id the primary key of the dm history maritime
	* @return the dm history maritime
	* @throws PortalException if a dm history maritime with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryMaritime getDmHistoryMaritime(
		int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryMaritime(id);
	}

	

	/**
	* Returns a range of all the dm history maritimes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of dm history maritimes
	* @param end the upper bound of the range of dm history maritimes (not inclusive)
	* @return the range of dm history maritimes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmHistoryMaritime> getDmHistoryMaritimes(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryMaritimes(start, end);
	}

	/**
	* Returns the number of dm history maritimes.
	*
	* @return the number of dm history maritimes
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmHistoryMaritimesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryMaritimesCount();
	}

	/**
	* Updates the dm history maritime in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryMaritime the dm history maritime
	* @return the dm history maritime that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryMaritime updateDmHistoryMaritime(
		com.fds.nsw.nghiepvu.model.DmHistoryMaritime dmHistoryMaritime)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmHistoryMaritime(dmHistoryMaritime);
	}

	/**
	* Updates the dm history maritime in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryMaritime the dm history maritime
	* @param merge whether to merge the dm history maritime with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm history maritime that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryMaritime updateDmHistoryMaritime(
		com.fds.nsw.nghiepvu.model.DmHistoryMaritime dmHistoryMaritime,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmHistoryMaritime(dmHistoryMaritime, merge);
	}

	

	

	public static com.fds.nsw.nghiepvu.model.DmHistoryMaritime getByMaritimeCode(
		java.lang.String maritimeCode) {
		return getService().getByMaritimeCode(maritimeCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmHistoryMaritime> findAllDmHistoryMaritimeOrderAsc() {
		return getService().findAllDmHistoryMaritimeOrderAsc();
	}

	public static com.fds.nsw.nghiepvu.model.DmHistoryMaritime getByMaritimeCodeAndSyncVersion(
		java.lang.String maritimeCode, java.lang.String syncVersion) {
		return getService()
				   .getByMaritimeCodeAndSyncVersion(maritimeCode, syncVersion);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}