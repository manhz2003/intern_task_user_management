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
 * The utility for the dm history state local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmHistoryStateLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmHistoryStateLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmHistoryStateLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmHistoryStateLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmHistoryStateLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class DmHistoryStateLocalServiceUtil {
public DmHistoryStateLocalServiceUtil(DmHistoryStateLocalServiceImpl service) {
DmHistoryStateLocalServiceUtil._service = service;
}
public static DmHistoryStateLocalServiceImpl getService() {
return _service;
}
private static DmHistoryStateLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmHistoryStateLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dm history state to the database. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryState the dm history state
	* @return the dm history state that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryState addDmHistoryState(
		com.fds.nsw.nghiepvu.model.DmHistoryState dmHistoryState)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmHistoryState(dmHistoryState);
	}

	/**
	* Creates a new dm history state with the primary key. Does not add the dm history state to the database.
	*
	* @param id the primary key for the new dm history state
	* @return the new dm history state
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryState createDmHistoryState(
		int id) {
		return getService().createDmHistoryState(id);
	}

	/**
	* Deletes the dm history state with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the dm history state
	* @throws PortalException if a dm history state with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmHistoryState(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmHistoryState(id);
	}

	/**
	* Deletes the dm history state from the database. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryState the dm history state
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmHistoryState(
		com.fds.nsw.nghiepvu.model.DmHistoryState dmHistoryState)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmHistoryState(dmHistoryState);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.DmHistoryState fetchDmHistoryState(
		int id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmHistoryState(id);
	}

	/**
	* Returns the dm history state with the primary key.
	*
	* @param id the primary key of the dm history state
	* @return the dm history state
	* @throws PortalException if a dm history state with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryState getDmHistoryState(
		int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryState(id);
	}

	

	/**
	* Returns a range of all the dm history states.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of dm history states
	* @param end the upper bound of the range of dm history states (not inclusive)
	* @return the range of dm history states
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmHistoryState> getDmHistoryStates(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryStates(start, end);
	}

	/**
	* Returns the number of dm history states.
	*
	* @return the number of dm history states
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmHistoryStatesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryStatesCount();
	}

	/**
	* Updates the dm history state in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryState the dm history state
	* @return the dm history state that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryState updateDmHistoryState(
		com.fds.nsw.nghiepvu.model.DmHistoryState dmHistoryState)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmHistoryState(dmHistoryState);
	}

	/**
	* Updates the dm history state in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryState the dm history state
	* @param merge whether to merge the dm history state with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm history state that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryState updateDmHistoryState(
		com.fds.nsw.nghiepvu.model.DmHistoryState dmHistoryState, boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmHistoryState(dmHistoryState, merge);
	}

	

	

	public static com.fds.nsw.nghiepvu.model.DmHistoryState getByStateCode(
		java.lang.String stateCode) {
		return getService().getByStateCode(stateCode);
	}

	public static com.fds.nsw.nghiepvu.model.DmHistoryState findByStateCodeAndSyncVersion(
		java.lang.String stateCode, java.lang.String syncVersion) {
		return getService().findByStateCodeAndSyncVersion(stateCode, syncVersion);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}