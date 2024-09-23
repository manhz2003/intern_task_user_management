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
 * The utility for the dm history port wharf local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmHistoryPortWharfLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmHistoryPortWharfLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmHistoryPortWharfLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmHistoryPortWharfLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmHistoryPortWharfLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class DmHistoryPortWharfLocalServiceUtil {
public DmHistoryPortWharfLocalServiceUtil(DmHistoryPortWharfLocalServiceImpl service) {
DmHistoryPortWharfLocalServiceUtil._service = service;
}
public static DmHistoryPortWharfLocalServiceImpl getService() {
return _service;
}
private static DmHistoryPortWharfLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmHistoryPortWharfLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dm history port wharf to the database. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryPortWharf the dm history port wharf
	* @return the dm history port wharf that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryPortWharf addDmHistoryPortWharf(
		com.fds.nsw.nghiepvu.model.DmHistoryPortWharf dmHistoryPortWharf)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmHistoryPortWharf(dmHistoryPortWharf);
	}

	/**
	* Creates a new dm history port wharf with the primary key. Does not add the dm history port wharf to the database.
	*
	* @param id the primary key for the new dm history port wharf
	* @return the new dm history port wharf
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryPortWharf createDmHistoryPortWharf(
		int id) {
		return getService().createDmHistoryPortWharf(id);
	}

	/**
	* Deletes the dm history port wharf with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the dm history port wharf
	* @throws PortalException if a dm history port wharf with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmHistoryPortWharf(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmHistoryPortWharf(id);
	}

	/**
	* Deletes the dm history port wharf from the database. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryPortWharf the dm history port wharf
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmHistoryPortWharf(
		com.fds.nsw.nghiepvu.model.DmHistoryPortWharf dmHistoryPortWharf)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmHistoryPortWharf(dmHistoryPortWharf);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.DmHistoryPortWharf fetchDmHistoryPortWharf(
		int id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmHistoryPortWharf(id);
	}

	/**
	* Returns the dm history port wharf with the primary key.
	*
	* @param id the primary key of the dm history port wharf
	* @return the dm history port wharf
	* @throws PortalException if a dm history port wharf with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryPortWharf getDmHistoryPortWharf(
		int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryPortWharf(id);
	}

	

	/**
	* Returns a range of all the dm history port wharfs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of dm history port wharfs
	* @param end the upper bound of the range of dm history port wharfs (not inclusive)
	* @return the range of dm history port wharfs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmHistoryPortWharf> getDmHistoryPortWharfs(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryPortWharfs(start, end);
	}

	/**
	* Returns the number of dm history port wharfs.
	*
	* @return the number of dm history port wharfs
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmHistoryPortWharfsCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryPortWharfsCount();
	}

	/**
	* Updates the dm history port wharf in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryPortWharf the dm history port wharf
	* @return the dm history port wharf that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryPortWharf updateDmHistoryPortWharf(
		com.fds.nsw.nghiepvu.model.DmHistoryPortWharf dmHistoryPortWharf)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmHistoryPortWharf(dmHistoryPortWharf);
	}

	/**
	* Updates the dm history port wharf in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryPortWharf the dm history port wharf
	* @param merge whether to merge the dm history port wharf with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm history port wharf that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryPortWharf updateDmHistoryPortWharf(
		com.fds.nsw.nghiepvu.model.DmHistoryPortWharf dmHistoryPortWharf,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmHistoryPortWharf(dmHistoryPortWharf, merge);
	}

	

	

	public static com.fds.nsw.nghiepvu.model.DmHistoryPortWharf findByPortWharfCodeAndSyncVersion(
		java.lang.String portWharfCode, java.lang.String syncVersion) {
		return getService()
				   .findByPortWharfCodeAndSyncVersion(portWharfCode, syncVersion);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}