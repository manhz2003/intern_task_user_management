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

import com.fds.nsw.nghiepvu.danhmuc.service.finder.DmPortWharfFinderImpl;
import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmPortWharfPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;
import vn.gt.portlet.baocao.bc12bt.BC12BTVinalinesModel;
import lombok.extern.slf4j.Slf4j;@Slf4j @Service









/**
 * The implementation of the dm port wharf local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link vn.gt.dao.danhmuc.service.DmPortWharfLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.danhmuc.service.base.DmPortWharfLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.DmPortWharfLocalServiceUtil
 */
public class DmPortWharfLocalServiceImpl { @Autowired
DmPortWharfPersistenceImpl persistence;@Autowired
DmPortWharfFinderImpl finder;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link vn.gt.dao.danhmuc.service.DmPortWharfLocalServiceUtil} to access the dm port wharf local service.
	 */
	public DmPortWharf getByPortWharfCode(String portWharfCode) {
		try {
			List<DmPortWharf> dmPortWharfes = persistence.findByPortWharfCode(portWharfCode);
			if (dmPortWharfes != null && dmPortWharfes.size() > 0) {
				return dmPortWharfes.get(0);
			}
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<DmPortWharf> findByPortRegionCode(String portRegionCode) {
		try {
			return persistence.findByPortRegionCode(portRegionCode);
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * ascOrdesc:
	 * asc = true
	 * desc = false
	 * */
	public List<DmPortWharf> findByPortRegionCodeAndPortHarbourCodeOrNullOrderPortCode(String portRegionCode, String portHarbourCode, boolean ascOrdesc) {
		try {
			return finder.findByPortRegionCodeAndPortHarbourCodeOrNullOrderPortCode(portRegionCode, portHarbourCode, ascOrdesc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<DmPortWharf> findByPortHarbourCodeOrNull(String portHarbourCode, boolean ascOrdesc) {
		try {
			return finder.findByPortHarbourCodeOrNull(portHarbourCode, ascOrdesc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<DmPortWharf> findByPortRegionCodeOrderPortCode(String portRegionCode, boolean ascOrdesc) {
		try {
			return finder.findByPortRegionCodeOrderPortCode(portRegionCode, ascOrdesc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<DmPortWharf> findDanhSachDmPortWharf(String portRegionCode, String portHarbourCode, String PortWharfNameVN, Integer portWharfPayment, boolean ascOrdesc, int start, int end) {
		try {
			return finder.findDanhSachDmPortWharf(portRegionCode, portHarbourCode, PortWharfNameVN, portWharfPayment, ascOrdesc, start, end);
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<DmPortWharf> findPortWharfs(String maritimeCode,
											String portRegionCode, String portHarbourCode,
											String portWharfNameVN, String isDelete, String portWharfCodeGroup,
											int start, int end) {
		try {
			return finder.findPortWharfs(maritimeCode,
					portRegionCode, portHarbourCode, portWharfNameVN, isDelete,
					portWharfCodeGroup, start, end);
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return null;
	}

	public long countPortWharfs(String maritimeCode, String portRegionCode,
								String portHarbourCode, String portWharfNameVN, String isDelete,
								String portWharfCodeGroup) {
		try {
			return finder.countPortWharfs(maritimeCode,
					portRegionCode, portHarbourCode, portWharfNameVN, isDelete,
					portWharfCodeGroup);
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public long getMaxSequenceNo(String portRegionCode, String portHarbourCode) {
		try {
			return finder.getMaxSequenceNo(portRegionCode,
					portHarbourCode);
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<BC12BTVinalinesModel> getModelMau12BTVinalines(
			String maritimeCode, String startDate, String endDate)
			throws SystemException {
		return finder.getModelMau12BTVinalines(maritimeCode,
				startDate, endDate);
	}

	public JSONArray getModelMau60(String maritimeCode, String startDate,
								   String endDate) throws SystemException {
		return finder
				.getModelMau60(maritimeCode, startDate, endDate);
	}

	public JSONArray getModelMau58S(String maritimeCode, String startDate,
									String endDate) throws SystemException {
		return finder.getModelMau58S(maritimeCode, startDate,
				endDate);
	}


	public int countDanhSachDmPortWharf(String portRegionCode, String portHarbourCode, String PortWharfNameVN, Integer portWharfPayment) {
		try {
			return finder.countDanhSachDmPortWharf(portRegionCode, portHarbourCode, PortWharfNameVN, portWharfPayment);
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public List<DmPortWharf> findDongBoDmPortWharf(String MaritimeCode, String portRegionCode, String portHarbourCode, String PortWharfNameVN, boolean ascOrdesc, int start, int end) {
		try {
			return finder.findDongBoDmPortWharf(MaritimeCode, portRegionCode, portHarbourCode, PortWharfNameVN, ascOrdesc, start, end);
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int countDongBoDmPortWharf(String MaritimeCode, String portRegionCode, String portHarbourCode, String PortWharfNameVN) {
		try {
			return finder.countDongBoDmPortWharf(MaritimeCode, portRegionCode, portHarbourCode, PortWharfNameVN);
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public DmPortWharf getFirstPortWharf() {
		try {
			return finder.getFirstPortWharf();
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public DmPortWharf getLastPortWharf() {
		try {
			return finder.getLastPortWharf();
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link vn.gt.dao.danhmuc.service.DmPortWharfLocalServiceUtil} to access the dm port wharf local service.
	 */

	/**
	 * Adds the dm port wharf to the database. Also notifies the appropriate model listeners.
	 *
	 * @param dmPortWharf the dm port wharf
	 * @return the dm port wharf that was added
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortWharf addDmPortWharf(DmPortWharf dmPortWharf)
			throws SystemException {

		dmPortWharf = persistence.updateImpl(dmPortWharf, false);





		return dmPortWharf;
	}

	/**
	 * Creates a new dm port wharf with the primary key. Does not add the dm port wharf to the database.
	 *
	 * @param id the primary key for the new dm port wharf
	 * @return the new dm port wharf
	 */
	public DmPortWharf createDmPortWharf(int id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the dm port wharf with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm port wharf
	 * @throws PortalException if a dm port wharf with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteDmPortWharf(int id)
			throws PortalException, SystemException {
		DmPortWharf dmPortWharf = persistence.remove(id);




	}

	/**
	 * Deletes the dm port wharf from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dmPortWharf the dm port wharf
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteDmPortWharf(DmPortWharf dmPortWharf)
			throws SystemException {
		persistence.remove(dmPortWharf);




	}













	public DmPortWharf fetchDmPortWharf(int id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the dm port wharf with the primary key.
	 *
	 * @param id the primary key of the dm port wharf
	 * @return the dm port wharf
	 * @throws PortalException if a dm port wharf with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortWharf getDmPortWharf(int id)
			throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}


	/**
	 * Returns a range of all the dm port wharfs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm port wharfs
	 * @param end the upper bound of the range of dm port wharfs (not inclusive)
	 * @return the range of dm port wharfs
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPortWharf> getDmPortWharfs(int start, int end)
			throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of dm port wharfs.
	 *
	 * @return the number of dm port wharfs
	 * @throws SystemException if a system exception occurred
	 */
	public int getDmPortWharfsCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the dm port wharf in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param dmPortWharf the dm port wharf
	 * @return the dm port wharf that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortWharf updateDmPortWharf(DmPortWharf dmPortWharf)
			throws SystemException {
		return updateDmPortWharf(dmPortWharf, true);
	}

	/**
	 * Updates the dm port wharf in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param dmPortWharf the dm port wharf
	 * @param merge whether to merge the dm port wharf with the current session. See  for an explanation.
	 * @return the dm port wharf that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortWharf updateDmPortWharf(DmPortWharf dmPortWharf, boolean merge)
			throws SystemException {

		dmPortWharf = persistence.updateImpl(dmPortWharf, merge);





		return dmPortWharf;
	}
}