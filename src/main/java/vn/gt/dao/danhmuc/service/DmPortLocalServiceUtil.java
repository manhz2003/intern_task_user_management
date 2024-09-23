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
 * The utility for the dm port local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmPortLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmPortLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmPortLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmPortLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmPortLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class DmPortLocalServiceUtil {
public DmPortLocalServiceUtil(DmPortLocalServiceImpl service) {
DmPortLocalServiceUtil._service = service;
}
public static DmPortLocalServiceImpl getService() {
return _service;
}
private static DmPortLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmPortLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dm port to the database. Also notifies the appropriate model listeners.
	*
	* @param dmPort the dm port
	* @return the dm port that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmPort addDmPort(
		com.fds.nsw.nghiepvu.model.DmPort dmPort)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmPort(dmPort);
	}

	/**
	* Creates a new dm port with the primary key. Does not add the dm port to the database.
	*
	* @param id the primary key for the new dm port
	* @return the new dm port
	*/
	public static com.fds.nsw.nghiepvu.model.DmPort createDmPort(int id) {
		return getService().createDmPort(id);
	}

	/**
	* Deletes the dm port with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the dm port
	* @throws PortalException if a dm port with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmPort(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmPort(id);
	}

	/**
	* Deletes the dm port from the database. Also notifies the appropriate model listeners.
	*
	* @param dmPort the dm port
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmPort(com.fds.nsw.nghiepvu.model.DmPort dmPort)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmPort(dmPort);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmPort> findByPortName(
			java.lang.String portName, int start, int end) {
		return getService().findByPortName(portName, start, end);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmPort> findPorts(
			java.lang.String portName, java.lang.String isDelete,
			java.lang.String portCodeGroup, java.lang.String portType,
			java.lang.String stateCode, int start, int end) {
		return getService()
				.findPorts(portName, isDelete, portCodeGroup, portType,
						stateCode, start, end);
	}

	public static long countPorts(java.lang.String portName,
								  java.lang.String isDelete, java.lang.String portCodeGroup,
								  java.lang.String portType, java.lang.String stateCode) {
		return getService()
				.countPorts(portName, isDelete, portCodeGroup, portType,
						stateCode);
	}

	public static com.fds.nsw.nghiepvu.model.DmPort fetchDmPort(int id)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmPort(id);
	}

	/**
	* Returns the dm port with the primary key.
	*
	* @param id the primary key of the dm port
	* @return the dm port
	* @throws PortalException if a dm port with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmPort getDmPort(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmPort(id);
	}

	

	/**
	* Returns a range of all the dm ports.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of dm ports
	* @param end the upper bound of the range of dm ports (not inclusive)
	* @return the range of dm ports
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmPort> getDmPorts(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmPorts(start, end);
	}

	/**
	* Returns the number of dm ports.
	*
	* @return the number of dm ports
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmPortsCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmPortsCount();
	}

	/**
	* Updates the dm port in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmPort the dm port
	* @return the dm port that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmPort updateDmPort(
		com.fds.nsw.nghiepvu.model.DmPort dmPort)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmPort(dmPort);
	}

	/**
	* Updates the dm port in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmPort the dm port
	* @param merge whether to merge the dm port with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm port that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmPort updateDmPort(
		com.fds.nsw.nghiepvu.model.DmPort dmPort, boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmPort(dmPort, merge);
	}

	

	

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmPort> findAll() {
		return getService().findAll();
	}

	public static com.fds.nsw.nghiepvu.model.DmPort getByPortCode(
		java.lang.String portCode) {
		return getService().getByPortCode(portCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmPort> findByLoCode(
		java.lang.String loCode) {
		return getService().findByLoCode(loCode);
	}

	public static int countByPortCode(java.lang.String portCode) {
		return getService().countByPortCode(portCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmPort> findAll(
		int start, int end) {
		return getService().findAll(start, end);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmPort> findByStateCodeAndCityCode(
		java.lang.String stateCode, java.lang.String citycode) {
		return getService().findByStateCodeAndCityCode(stateCode, citycode);
	}

	public static com.fds.nsw.nghiepvu.model.DmPort fetchByPortCodeLoCode(
		java.lang.String portCode, java.lang.String loCode) {
		return getService().fetchByPortCodeLoCode(portCode, loCode);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}