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

import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;

import com.fds.nsw.nghiepvu.danhmuc.service.finder.DmPortHarbourFinderImpl;
import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmPortHarbourPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import lombok.extern.slf4j.Slf4j;@Slf4j @Service










/**
 * The implementation of the dm port harbour local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link vn.gt.dao.danhmuc.service.DmPortHarbourLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.danhmuc.service.base.DmPortHarbourLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.DmPortHarbourLocalServiceUtil
 */
public class DmPortHarbourLocalServiceImpl { @Autowired
DmPortHarbourPersistenceImpl persistence;@Autowired
DmPortHarbourFinderImpl finder;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link vn.gt.dao.danhmuc.service.DmPortHarbourLocalServiceUtil} to access the dm port harbour local service.
	 */
	public DmPortHarbour getByPortHarbourCode(String portHarbourCode) {
		try {
			List<DmPortHarbour> dmPortHarbours = persistence.findByPortHarbourCode(portHarbourCode);
			if (dmPortHarbours != null && dmPortHarbours.size() > 0) { return dmPortHarbours.get(0); }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<DmPortHarbour> findPortHarbours(String maritimeCode,
												String portRegion, String portHarbourName, String isDelete,
												String portHarbourCodeGroup, int start, int end) {
		try {
			return finder.findPortHarbours(maritimeCode,
					portRegion, portHarbourName, isDelete,
					portHarbourCodeGroup, start, end);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public long countPortHarbours(String maritimeCode, String portRegion,
								  String portHarbourName, String isDelete, String portHarbourCodeGroup) {
		try {
			return finder
					.countPortHarbours(maritimeCode, portRegion,
							portHarbourName, isDelete, portHarbourCodeGroup);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public long getMaxSequenceNo(String maritimeCode, String portRegionCode) {
		try {
			return finder.getMaxSequenceNo(maritimeCode,
					portRegionCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}


	public List<DmPortHarbour> findByPortRegionCode(String portRegionCode) {
		try {
			return persistence.findByPortRegionCode(portRegionCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<DmPortHarbour>();
	}
	
	public List<DmPortHarbour> findByPortRegion(String portRegion) {
		try {
			return persistence.findByPortRegion(portRegion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<DmPortHarbour>();
	}
	
	
	public List<DmPortHarbour> findDanhSachDmPortHarbour(String maritimeCode, String portRegion, String portHarbourName, int start, int end) {
		try {
			return finder.findDanhSachDmPortHarbour(maritimeCode, portRegion, portHarbourName, start, end);
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int countDanhSachDmPortHarbour(String maritimeCode, String portRegion, String portHarbourName) {
		try {
			return finder.countDanhSachDmPortHarbour(maritimeCode, portRegion, portHarbourName);
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public DmPortHarbour getFirstPortHarbour() {
		try {
			return finder.getFirstPortHarbour();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public DmPortHarbour getLastPortHarbour() {
		try {
			return finder.getLastPortHarbour();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link vn.gt.dao.danhmuc.service.DmPortHarbourLocalServiceUtil} to access the dm port harbour local service.
	 */

	/**
	 * Adds the dm port harbour to the database. Also notifies the appropriate model listeners.
	 *
	 * @param dmPortHarbour the dm port harbour
	 * @return the dm port harbour that was added
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortHarbour addDmPortHarbour(DmPortHarbour dmPortHarbour)
			throws SystemException {

		dmPortHarbour = persistence.updateImpl(dmPortHarbour, false);





		return dmPortHarbour;
	}

	/**
	 * Creates a new dm port harbour with the primary key. Does not add the dm port harbour to the database.
	 *
	 * @param id the primary key for the new dm port harbour
	 * @return the new dm port harbour
	 */
	public DmPortHarbour createDmPortHarbour(int id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the dm port harbour with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm port harbour
	 * @throws PortalException if a dm port harbour with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteDmPortHarbour(int id)
			throws PortalException, SystemException {
		DmPortHarbour dmPortHarbour = persistence.remove(id);




	}

	/**
	 * Deletes the dm port harbour from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dmPortHarbour the dm port harbour
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteDmPortHarbour(DmPortHarbour dmPortHarbour)
			throws SystemException {
		persistence.remove(dmPortHarbour);




	}













	public DmPortHarbour fetchDmPortHarbour(int id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the dm port harbour with the primary key.
	 *
	 * @param id the primary key of the dm port harbour
	 * @return the dm port harbour
	 * @throws PortalException if a dm port harbour with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortHarbour getDmPortHarbour(int id)
			throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
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
	public List<DmPortHarbour> getDmPortHarbours(int start, int end)
			throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of dm port harbours.
	 *
	 * @return the number of dm port harbours
	 * @throws SystemException if a system exception occurred
	 */
	public int getDmPortHarboursCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the dm port harbour in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param dmPortHarbour the dm port harbour
	 * @return the dm port harbour that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortHarbour updateDmPortHarbour(DmPortHarbour dmPortHarbour)
			throws SystemException {
		return updateDmPortHarbour(dmPortHarbour, true);
	}

	/**
	 * Updates the dm port harbour in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param dmPortHarbour the dm port harbour
	 * @param merge whether to merge the dm port harbour with the current session. See  for an explanation.
	 * @return the dm port harbour that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortHarbour updateDmPortHarbour(DmPortHarbour dmPortHarbour,
											 boolean merge) throws SystemException {

		dmPortHarbour = persistence.updateImpl(dmPortHarbour, merge);





		return dmPortHarbour;
	}
}