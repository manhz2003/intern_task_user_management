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

package vn.gt.dao.danhmucgt.service;






/**
 * The utility for the dm g t ship position local service. This utility wraps {@link vn.gt.dao.danhmucgt.service.impl.DmGTShipPositionLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmGTShipPositionLocalService
 * @see vn.gt.dao.danhmucgt.service.base.DmGTShipPositionLocalServiceBaseImpl
 * @see vn.gt.dao.danhmucgt.service.impl.DmGTShipPositionLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmucgt.service.impl.DmGTShipPositionLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class DmGTShipPositionLocalServiceUtil {
public DmGTShipPositionLocalServiceUtil(DmGTShipPositionLocalServiceImpl service) {
DmGTShipPositionLocalServiceUtil._service = service;
}
public static DmGTShipPositionLocalServiceImpl getService() {
return _service;
}
private static DmGTShipPositionLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmucgt.service.impl.DmGTShipPositionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dm g t ship position to the database. Also notifies the appropriate model listeners.
	*
	* @param dmGTShipPosition the dm g t ship position
	* @return the dm g t ship position that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmGtShipPosition addDmGTShipPosition(
		com.fds.nsw.nghiepvu.model.DmGtShipPosition dmGTShipPosition)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmGTShipPosition(dmGTShipPosition);
	}

	/**
	* Creates a new dm g t ship position with the primary key. Does not add the dm g t ship position to the database.
	*
	* @param id the primary key for the new dm g t ship position
	* @return the new dm g t ship position
	*/
	public static com.fds.nsw.nghiepvu.model.DmGtShipPosition createDmGTShipPosition(
		long id) {
		return getService().createDmGTShipPosition(id);
	}

	/**
	* Deletes the dm g t ship position with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the dm g t ship position
	* @throws PortalException if a dm g t ship position with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmGTShipPosition(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmGTShipPosition(id);
	}

	/**
	* Deletes the dm g t ship position from the database. Also notifies the appropriate model listeners.
	*
	* @param dmGTShipPosition the dm g t ship position
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmGTShipPosition(
		com.fds.nsw.nghiepvu.model.DmGtShipPosition dmGTShipPosition)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmGTShipPosition(dmGTShipPosition);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.DmGtShipPosition fetchDmGTShipPosition(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmGTShipPosition(id);
	}

	/**
	* Returns the dm g t ship position with the primary key.
	*
	* @param id the primary key of the dm g t ship position
	* @return the dm g t ship position
	* @throws PortalException if a dm g t ship position with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmGtShipPosition getDmGTShipPosition(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmGTShipPosition(id);
	}

	

	/**
	* Returns a range of all the dm g t ship positions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of dm g t ship positions
	* @param end the upper bound of the range of dm g t ship positions (not inclusive)
	* @return the range of dm g t ship positions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmGtShipPosition> getDmGTShipPositions(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmGTShipPositions(start, end);
	}

	/**
	* Returns the number of dm g t ship positions.
	*
	* @return the number of dm g t ship positions
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmGTShipPositionsCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmGTShipPositionsCount();
	}

	/**
	* Updates the dm g t ship position in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmGTShipPosition the dm g t ship position
	* @return the dm g t ship position that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmGtShipPosition updateDmGTShipPosition(
		com.fds.nsw.nghiepvu.model.DmGtShipPosition dmGTShipPosition)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmGTShipPosition(dmGTShipPosition);
	}

	/**
	* Updates the dm g t ship position in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmGTShipPosition the dm g t ship position
	* @param merge whether to merge the dm g t ship position with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm g t ship position that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmGtShipPosition updateDmGTShipPosition(
		com.fds.nsw.nghiepvu.model.DmGtShipPosition dmGTShipPosition,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmGTShipPosition(dmGTShipPosition, merge);
	}

	

	

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmGtShipPosition> findByPositionCode(
		java.lang.String positionCode) {
		return getService().findByPositionCode(positionCode);
	}

	public static int countByPositionCode(java.lang.String positionCode) {
		return getService().countByPositionCode(positionCode);
	}

	public static com.fds.nsw.nghiepvu.model.DmGtShipPosition getByPositionCode(
		java.lang.String positionCode) {
		return getService().getByPositionCode(positionCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmGtShipPosition> findByRoleAndThuTuc(
		java.lang.String positionCode)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().findByRoleAndThuTuc(positionCode);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}