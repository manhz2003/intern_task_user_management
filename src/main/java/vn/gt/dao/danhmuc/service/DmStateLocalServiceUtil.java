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
 * The utility for the dm state local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmStateLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmStateLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmStateLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmStateLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmStateLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class DmStateLocalServiceUtil {
public DmStateLocalServiceUtil(DmStateLocalServiceImpl service) {
DmStateLocalServiceUtil._service = service;
}
public static DmStateLocalServiceImpl getService() {
return _service;
}
private static DmStateLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmStateLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dm state to the database. Also notifies the appropriate model listeners.
	*
	* @param dmState the dm state
	* @return the dm state that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmState addDmState(
		com.fds.nsw.nghiepvu.model.DmState dmState)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmState(dmState);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmState> findByStateName(
			java.lang.String stateName, int start, int end) {
		return getService().findByStateName(stateName, start, end);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmState> findStates(
			java.lang.String stateName, java.lang.String isDelete,
			java.lang.String stateCodeGroup, int start, int end) {
		return getService()
				.findStates(stateName, isDelete, stateCodeGroup, start, end);
	}

	public static long countStates(java.lang.String stateName,
								   java.lang.String isDelete, java.lang.String stateCodeGroup) {
		return getService().countStates(stateName, isDelete, stateCodeGroup);
	}

	public static org.json.JSONArray getModelMau53_54T(
			java.lang.String maritimeCode, java.lang.String nameOfShip,
			java.lang.String imoNumber, java.lang.String registryNumber,
			java.lang.String vrCode, java.lang.String flagStateOfShip,
			java.lang.String from_gt, java.lang.String to_gt,
			java.lang.String from_dwt, java.lang.String to_dwt,
			java.lang.String from_loa, java.lang.String to_loa,
			java.lang.String lastPortCode, java.lang.String nextPortCode,
			java.lang.String arrivalShipAgency,
			java.lang.String departureShipAgency, java.lang.String cargoType,
			java.lang.String cargoCategory, java.lang.String callSign,
			java.lang.String startDate, java.lang.String endDate)
			throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				.getModelMau53_54T(maritimeCode, nameOfShip, imoNumber,
						registryNumber, vrCode, flagStateOfShip, from_gt, to_gt, from_dwt,
						to_dwt, from_loa, to_loa, lastPortCode, nextPortCode,
						arrivalShipAgency, departureShipAgency, cargoType, cargoCategory,
						callSign, startDate, endDate);
	}

	public static org.json.JSONArray getModelMau70_78T(
			java.lang.String maritimeCode, java.lang.String nameOfShip,
			java.lang.String imoNumber, java.lang.String registryNumber,
			java.lang.String vrCode, java.lang.String flagStateOfShip,
			java.lang.String from_gt, java.lang.String to_gt,
			java.lang.String from_dwt, java.lang.String to_dwt,
			java.lang.String from_loa, java.lang.String to_loa,
			java.lang.String lastPortCode, java.lang.String nextPortCode,
			java.lang.String arrivalShipAgency,
			java.lang.String departureShipAgency, java.lang.String cargoType,
			java.lang.String cargoCategory, java.lang.String callSign,
			java.lang.String anchoringPortHarbourCode,
			java.lang.String anchoringPortWharfCode,
			java.lang.String shiftingPortHarbourCode,
			java.lang.String shiftingPortWharfCode, java.lang.String startDate,
			java.lang.String endDate)
			throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				.getModelMau70_78T(maritimeCode, nameOfShip, imoNumber,
						registryNumber, vrCode, flagStateOfShip, from_gt, to_gt, from_dwt,
						to_dwt, from_loa, to_loa, lastPortCode, nextPortCode,
						arrivalShipAgency, departureShipAgency, cargoType, cargoCategory,
						callSign, anchoringPortHarbourCode, anchoringPortWharfCode,
						shiftingPortHarbourCode, shiftingPortWharfCode, startDate, endDate);
	}

	public static org.json.JSONArray getModelMau77T(
			java.lang.String maritimeCode, java.lang.String nameOfShip,
			java.lang.String imoNumber, java.lang.String registryNumber,
			java.lang.String vrCode, java.lang.String flagStateOfShip,
			java.lang.String from_gt, java.lang.String to_gt,
			java.lang.String from_dwt, java.lang.String to_dwt,
			java.lang.String from_loa, java.lang.String to_loa,
			java.lang.String lastPortCode, java.lang.String nextPortCode,
			java.lang.String arrivalShipAgency,
			java.lang.String departureShipAgency, java.lang.String cargoType,
			java.lang.String cargoCategory, java.lang.String callSign,
			java.lang.String anchoringPortHarbourCode,
			java.lang.String anchoringPortWharfCode,
			java.lang.String shiftingPortHarbourCode,
			java.lang.String shiftingPortWharfCode, java.lang.String startDate,
			java.lang.String endDate)
			throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				.getModelMau77T(maritimeCode, nameOfShip, imoNumber,
						registryNumber, vrCode, flagStateOfShip, from_gt, to_gt, from_dwt,
						to_dwt, from_loa, to_loa, lastPortCode, nextPortCode,
						arrivalShipAgency, departureShipAgency, cargoType, cargoCategory,
						callSign, anchoringPortHarbourCode, anchoringPortWharfCode,
						shiftingPortHarbourCode, shiftingPortWharfCode, startDate, endDate);
	}


	/**
	* Creates a new dm state with the primary key. Does not add the dm state to the database.
	*
	* @param id the primary key for the new dm state
	* @return the new dm state
	*/
	public static com.fds.nsw.nghiepvu.model.DmState createDmState(int id) {
		return getService().createDmState(id);
	}

	/**
	* Deletes the dm state with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the dm state
	* @throws PortalException if a dm state with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmState(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmState(id);
	}

	/**
	* Deletes the dm state from the database. Also notifies the appropriate model listeners.
	*
	* @param dmState the dm state
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmState(com.fds.nsw.nghiepvu.model.DmState dmState)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmState(dmState);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.DmState fetchDmState(int id)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmState(id);
	}

	/**
	* Returns the dm state with the primary key.
	*
	* @param id the primary key of the dm state
	* @return the dm state
	* @throws PortalException if a dm state with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmState getDmState(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmState(id);
	}

	

	/**
	* Returns a range of all the dm states.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of dm states
	* @param end the upper bound of the range of dm states (not inclusive)
	* @return the range of dm states
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmState> getDmStates(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmStates(start, end);
	}

	/**
	* Returns the number of dm states.
	*
	* @return the number of dm states
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmStatesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmStatesCount();
	}

	/**
	* Updates the dm state in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmState the dm state
	* @return the dm state that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmState updateDmState(
		com.fds.nsw.nghiepvu.model.DmState dmState)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmState(dmState);
	}

	/**
	* Updates the dm state in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmState the dm state
	* @param merge whether to merge the dm state with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm state that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmState updateDmState(
		com.fds.nsw.nghiepvu.model.DmState dmState, boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmState(dmState, merge);
	}

	

	

	public static com.fds.nsw.nghiepvu.model.DmState getByStateCode(
		java.lang.String stateCode) {
		return getService().getByStateCode(stateCode);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmState> getAllOrderByName()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getAllOrderByName();
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}