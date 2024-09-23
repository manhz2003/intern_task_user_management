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

package com.fds.nsw.nghiepvu.danhmuc.service.impl;import java.util.ArrayList; import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;

import com.fds.nsw.nghiepvu.danhmuc.service.finder.DmStateFinderImpl;
import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmStatePersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import lombok.extern.slf4j.Slf4j;@Slf4j @Service








/**
 * The implementation of the dm state local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link vn.gt.dao.danhmuc.service.DmStateLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.danhmuc.service.base.DmStateLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.DmStateLocalServiceUtil
 */
public class DmStateLocalServiceImpl { @Autowired
DmStatePersistenceImpl persistence;@Autowired
DmStateFinderImpl finder;

	public List<DmState> findByStateName(String stateName, int start, int end) {
		try {
			return persistence.findByF_stateNamebyLike(stateName, start,
					end);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<DmState> findStates(String stateName, String isDelete,
									String stateCodeGroup, int start, int end) {
		try {
			return finder.findStates(stateName, isDelete,
					stateCodeGroup, start, end);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public long countStates(String stateName, String isDelete,
							String stateCodeGroup) {
		try {
			return finder.countStates(stateName, isDelete,
					stateCodeGroup);
		} catch (Exception e) {
			log.error(e.getMessage());
			return 0;
		}
	}

	public JSONArray getModelMau53_54T(String maritimeCode, String nameOfShip,
									   String imoNumber, String registryNumber, String vrCode,
									   String flagStateOfShip, String from_gt, String to_gt,
									   String from_dwt, String to_dwt, String from_loa, String to_loa,
									   String lastPortCode, String nextPortCode, String arrivalShipAgency,
									   String departureShipAgency, String cargoType, String cargoCategory,
									   String callSign, String startDate, String endDate)
			throws SystemException {
		return finder.getModelMau53_54T(maritimeCode, nameOfShip,
				imoNumber, registryNumber, vrCode, flagStateOfShip, from_gt,
				to_gt, from_dwt, to_dwt, from_loa, to_loa, lastPortCode,
				nextPortCode, arrivalShipAgency, departureShipAgency,
				cargoType, cargoCategory, callSign, startDate, endDate);
	}

	public JSONArray getModelMau70_78T(String maritimeCode, String nameOfShip,
									   String imoNumber, String registryNumber, String vrCode,
									   String flagStateOfShip, String from_gt, String to_gt,
									   String from_dwt, String to_dwt, String from_loa, String to_loa,
									   String lastPortCode, String nextPortCode, String arrivalShipAgency,
									   String departureShipAgency, String cargoType, String cargoCategory,
									   String callSign, String anchoringPortHarbourCode,
									   String anchoringPortWharfCode, String shiftingPortHarbourCode,
									   String shiftingPortWharfCode, String startDate, String endDate)
			throws SystemException {
		return finder.getModelMau70_78T(maritimeCode, nameOfShip,
				imoNumber, registryNumber, vrCode, flagStateOfShip, from_gt,
				to_gt, from_dwt, to_dwt, from_loa, to_loa, lastPortCode,
				nextPortCode, arrivalShipAgency, departureShipAgency,
				cargoType, cargoCategory, callSign, anchoringPortHarbourCode,
				anchoringPortWharfCode, shiftingPortHarbourCode,
				shiftingPortWharfCode, startDate, endDate);
	}

	public JSONArray getModelMau77T(String maritimeCode, String nameOfShip,
									String imoNumber, String registryNumber, String vrCode,
									String flagStateOfShip, String from_gt, String to_gt,
									String from_dwt, String to_dwt, String from_loa, String to_loa,
									String lastPortCode, String nextPortCode, String arrivalShipAgency,
									String departureShipAgency, String cargoType, String cargoCategory,
									String callSign, String anchoringPortHarbourCode,
									String anchoringPortWharfCode, String shiftingPortHarbourCode,
									String shiftingPortWharfCode, String startDate, String endDate)
			throws SystemException {
		return finder.getModelMau77T(maritimeCode, nameOfShip,
				imoNumber, registryNumber, vrCode, flagStateOfShip, from_gt,
				to_gt, from_dwt, to_dwt, from_loa, to_loa, lastPortCode,
				nextPortCode, arrivalShipAgency, departureShipAgency,
				cargoType, cargoCategory, callSign, anchoringPortHarbourCode,
				anchoringPortWharfCode, shiftingPortHarbourCode,
				shiftingPortWharfCode, startDate, endDate);
	}

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link vn.gt.dao.danhmuc.service.DmStateLocalServiceUtil} to access the dm state local service.
	 */
	public DmState getByStateCode(String stateCode) {
		try {
			List<DmState> dmStates = persistence.findByStateCode(stateCode);
			if (dmStates != null && dmStates.size() > 0) { return dmStates.get(0); }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<DmState> getAllOrderByName() throws SystemException {
		return finder.getAllOrderByName();
	}

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link vn.gt.dao.danhmuc.service.DmStateLocalServiceUtil} to access the dm state local service.
	 */

	/**
	 * Adds the dm state to the database. Also notifies the appropriate model listeners.
	 *
	 * @param dmState the dm state
	 * @return the dm state that was added
	 * @throws SystemException if a system exception occurred
	 */
	public DmState addDmState(DmState dmState) throws SystemException {

		dmState = persistence.updateImpl(dmState, false);





		return dmState;
	}

	/**
	 * Creates a new dm state with the primary key. Does not add the dm state to the database.
	 *
	 * @param id the primary key for the new dm state
	 * @return the new dm state
	 */
	public DmState createDmState(int id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the dm state with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm state
	 * @throws PortalException if a dm state with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteDmState(int id) throws PortalException, SystemException {
		DmState dmState = persistence.remove(id);




	}

	/**
	 * Deletes the dm state from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dmState the dm state
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteDmState(DmState dmState) throws SystemException {
		persistence.remove(dmState);




	}













	public DmState fetchDmState(int id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the dm state with the primary key.
	 *
	 * @param id the primary key of the dm state
	 * @return the dm state
	 * @throws PortalException if a dm state with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmState getDmState(int id) throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
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
	public List<DmState> getDmStates(int start, int end)
			throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of dm states.
	 *
	 * @return the number of dm states
	 * @throws SystemException if a system exception occurred
	 */
	public int getDmStatesCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the dm state in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param dmState the dm state
	 * @return the dm state that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public DmState updateDmState(DmState dmState) throws SystemException {
		return updateDmState(dmState, true);
	}

	/**
	 * Updates the dm state in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param dmState the dm state
	 * @param merge whether to merge the dm state with the current session. See  for an explanation.
	 * @return the dm state that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public DmState updateDmState(DmState dmState, boolean merge)
			throws SystemException {

		dmState = persistence.updateImpl(dmState, merge);





		return dmState;
	}
}