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
 * The utility for the dm port harbour local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmPortHarbourLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmPortHarbourLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmPortHarbourLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmPortHarbourLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmPortHarbourLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class DmPortHarbourLocalServiceUtil {
public DmPortHarbourLocalServiceUtil(DmPortHarbourLocalServiceImpl service) {
DmPortHarbourLocalServiceUtil._service = service;
}
public static DmPortHarbourLocalServiceImpl getService() {
return _service;
}
private static DmPortHarbourLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmPortHarbourLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dm port harbour to the database. Also notifies the appropriate model listeners.
	*
	* @param dmPortHarbour the dm port harbour
	* @return the dm port harbour that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmPortHarbour addDmPortHarbour(
		com.fds.nsw.nghiepvu.model.DmPortHarbour dmPortHarbour)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmPortHarbour(dmPortHarbour);
	}

	/**
	* Creates a new dm port harbour with the primary key. Does not add the dm port harbour to the database.
	*
	* @param id the primary key for the new dm port harbour
	* @return the new dm port harbour
	*/
	public static com.fds.nsw.nghiepvu.model.DmPortHarbour createDmPortHarbour(
		int id) {
		return getService().createDmPortHarbour(id);
	}

	/**
	* Deletes the dm port harbour with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the dm port harbour
	* @throws PortalException if a dm port harbour with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmPortHarbour(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmPortHarbour(id);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmPortHarbour> findPortHarbours(
			java.lang.String maritimeCode, java.lang.String portRegion,
			java.lang.String portHarbourName, java.lang.String isDelete,
			java.lang.String portHarbourCodeGroup, int start, int end) {
		return getService()
				.findPortHarbours(maritimeCode, portRegion, portHarbourName,
						isDelete, portHarbourCodeGroup, start, end);
	}

	public static long countPortHarbours(java.lang.String maritimeCode,
										 java.lang.String portRegion, java.lang.String portHarbourName,
										 java.lang.String isDelete, java.lang.String portHarbourCodeGroup) {
		return getService()
				.countPortHarbours(maritimeCode, portRegion,
						portHarbourName, isDelete, portHarbourCodeGroup);
	}

	public static long getMaxSequenceNo(java.lang.String maritimeCode,
										java.lang.String portRegionCode) {
		return getService().getMaxSequenceNo(maritimeCode, portRegionCode);
	}


	/**
	* Deletes the dm port harbour from the database. Also notifies the appropriate model listeners.
	*
	* @param dmPortHarbour the dm port harbour
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmPortHarbour(
		com.fds.nsw.nghiepvu.model.DmPortHarbour dmPortHarbour)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmPortHarbour(dmPortHarbour);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.DmPortHarbour fetchDmPortHarbour(
		int id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmPortHarbour(id);
	}

	/**
	* Returns the dm port harbour with the primary key.
	*
	* @param id the primary key of the dm port harbour
	* @return the dm port harbour
	* @throws PortalException if a dm port harbour with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmPortHarbour getDmPortHarbour(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmPortHarbour(id);
	}

	

	/**
	* Returns a range of all the dm port harbours.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of dm port harbours
	* @param end the upper bound of the range of dm port harbours (not inclusive)
	* @return the range of dm port harbours
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmPortHarbour> getDmPortHarbours(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmPortHarbours(start, end);
	}

	/**
	* Returns the number of dm port harbours.
	*
	* @return the number of dm port harbours
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmPortHarboursCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmPortHarboursCount();
	}

	/**
	* Updates the dm port harbour in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmPortHarbour the dm port harbour
	* @return the dm port harbour that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmPortHarbour updateDmPortHarbour(
		com.fds.nsw.nghiepvu.model.DmPortHarbour dmPortHarbour)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmPortHarbour(dmPortHarbour);
	}

	/**
	* Updates the dm port harbour in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmPortHarbour the dm port harbour
	* @param merge whether to merge the dm port harbour with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm port harbour that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmPortHarbour updateDmPortHarbour(
		com.fds.nsw.nghiepvu.model.DmPortHarbour dmPortHarbour, boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmPortHarbour(dmPortHarbour, merge);
	}

	

	

	public static com.fds.nsw.nghiepvu.model.DmPortHarbour getByPortHarbourCode(
		java.lang.String portHarbourCode) {
		return getService().getByPortHarbourCode(portHarbourCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmPortHarbour> findByPortRegionCode(
		java.lang.String portRegionCode) {
		return getService().findByPortRegionCode(portRegionCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmPortHarbour> findByPortRegion(
		java.lang.String portRegion) {
		return getService().findByPortRegion(portRegion);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmPortHarbour> findDanhSachDmPortHarbour(
		java.lang.String maritimeCode, java.lang.String portRegion,
		java.lang.String portHarbourName, int start, int end) {
		return getService()
				   .findDanhSachDmPortHarbour(maritimeCode, portRegion,
			portHarbourName, start, end);
	}

	public static int countDanhSachDmPortHarbour(
		java.lang.String maritimeCode, java.lang.String portRegion,
		java.lang.String portHarbourName) {
		return getService()
				   .countDanhSachDmPortHarbour(maritimeCode, portRegion,
			portHarbourName);
	}

	public static com.fds.nsw.nghiepvu.model.DmPortHarbour getFirstPortHarbour() {
		return getService().getFirstPortHarbour();
	}

	public static com.fds.nsw.nghiepvu.model.DmPortHarbour getLastPortHarbour() {
		return getService().getLastPortHarbour();
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}