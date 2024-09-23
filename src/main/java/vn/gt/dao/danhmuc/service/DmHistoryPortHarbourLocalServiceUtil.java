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
 * The utility for the dm history port harbour local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmHistoryPortHarbourLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmHistoryPortHarbourLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmHistoryPortHarbourLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmHistoryPortHarbourLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmHistoryPortHarbourLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class DmHistoryPortHarbourLocalServiceUtil {
public DmHistoryPortHarbourLocalServiceUtil(DmHistoryPortHarbourLocalServiceImpl service) {
DmHistoryPortHarbourLocalServiceUtil._service = service;
}
public static DmHistoryPortHarbourLocalServiceImpl getService() {
return _service;
}
private static DmHistoryPortHarbourLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmHistoryPortHarbourLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dm history port harbour to the database. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryPortHarbour the dm history port harbour
	* @return the dm history port harbour that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryPortHarbour addDmHistoryPortHarbour(
		com.fds.nsw.nghiepvu.model.DmHistoryPortHarbour dmHistoryPortHarbour)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmHistoryPortHarbour(dmHistoryPortHarbour);
	}

	/**
	* Creates a new dm history port harbour with the primary key. Does not add the dm history port harbour to the database.
	*
	* @param id the primary key for the new dm history port harbour
	* @return the new dm history port harbour
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryPortHarbour createDmHistoryPortHarbour(
		int id) {
		return getService().createDmHistoryPortHarbour(id);
	}

	/**
	* Deletes the dm history port harbour with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the dm history port harbour
	* @throws PortalException if a dm history port harbour with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmHistoryPortHarbour(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmHistoryPortHarbour(id);
	}

	/**
	* Deletes the dm history port harbour from the database. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryPortHarbour the dm history port harbour
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmHistoryPortHarbour(
		com.fds.nsw.nghiepvu.model.DmHistoryPortHarbour dmHistoryPortHarbour)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmHistoryPortHarbour(dmHistoryPortHarbour);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.DmHistoryPortHarbour fetchDmHistoryPortHarbour(
		int id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmHistoryPortHarbour(id);
	}

	/**
	* Returns the dm history port harbour with the primary key.
	*
	* @param id the primary key of the dm history port harbour
	* @return the dm history port harbour
	* @throws PortalException if a dm history port harbour with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryPortHarbour getDmHistoryPortHarbour(
		int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryPortHarbour(id);
	}

	

	/**
	* Returns a range of all the dm history port harbours.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of dm history port harbours
	* @param end the upper bound of the range of dm history port harbours (not inclusive)
	* @return the range of dm history port harbours
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmHistoryPortHarbour> getDmHistoryPortHarbours(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryPortHarbours(start, end);
	}

	/**
	* Returns the number of dm history port harbours.
	*
	* @return the number of dm history port harbours
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmHistoryPortHarboursCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryPortHarboursCount();
	}

	/**
	* Updates the dm history port harbour in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryPortHarbour the dm history port harbour
	* @return the dm history port harbour that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryPortHarbour updateDmHistoryPortHarbour(
		com.fds.nsw.nghiepvu.model.DmHistoryPortHarbour dmHistoryPortHarbour)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmHistoryPortHarbour(dmHistoryPortHarbour);
	}

	/**
	* Updates the dm history port harbour in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryPortHarbour the dm history port harbour
	* @param merge whether to merge the dm history port harbour with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm history port harbour that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryPortHarbour updateDmHistoryPortHarbour(
		com.fds.nsw.nghiepvu.model.DmHistoryPortHarbour dmHistoryPortHarbour,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .updateDmHistoryPortHarbour(dmHistoryPortHarbour, merge);
	}

	

	

	public static com.fds.nsw.nghiepvu.model.DmHistoryPortHarbour findByPortHarbourCodeAndSyncVersion(
		java.lang.String portHarbourCode, java.lang.String syncVersion) {
		return getService()
				   .findByPortHarbourCodeAndSyncVersion(portHarbourCode,
			syncVersion);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmHistoryPortHarbour> findByPortRegionCode(
		java.lang.String portRegionCode) {
		return getService().findByPortRegionCode(portRegionCode);
	}

	public static void clearService() {
		_service = null;
	}

	

	


}