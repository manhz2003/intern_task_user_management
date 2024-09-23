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

import com.fds.nsw.nghiepvu.danhmuc.service.finder.DmPortRegionFinderImpl;
import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmPortRegionPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import lombok.extern.slf4j.Slf4j;
import vn.gt.portlet.baocao.bc12bt.BC12BTKhuCangModel;
import vn.gt.portlet.baocao.bc15t.BC15TModel;

@Slf4j @Service


/**
 * The implementation of the dm port region local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link vn.gt.dao.danhmuc.service.DmPortRegionLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.danhmuc.service.base.DmPortRegionLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.dmPortRegionLocalService
 */
public class DmPortRegionLocalServiceImpl
	{ @Autowired
	DmPortRegionPersistenceImpl persistence;@Autowired
	DmPortRegionFinderImpl finder;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link vn.gt.dao.danhmuc.service.dmPortRegionLocalService} to access the dm port region local service.
	 */

		public List<DmPortRegion> findPortRegions(String maritimeCode,
												  String portRegionNameVN, String portCode, String isDelete,
												  String portRegionCodeGroup, int start, int end) {
			try {
				return finder.findDmPortRegions(maritimeCode,
						portRegionNameVN, portCode, isDelete, portRegionCodeGroup,
						start, end);
			} catch (Exception e) {
				log.error(e.getMessage());
				return null;
			}
		}

		public long countPortRegions(String maritimeCode, String portRegionNameVN,
									 String portCode, String isDelete, String portRegionCodeGroup) {
			try {
				return finder.countDmPortRegions(maritimeCode,
						portRegionNameVN, portCode, isDelete, portRegionCodeGroup);
			} catch (Exception e) {
				log.error(e.getMessage());
				return 0;
			}
		}

		public long getMaxSequenceNo(String maritimeCode, String portCodeRef) {
			try {
				return finder.getMaxSequenceNo(maritimeCode,
						portCodeRef);
			} catch (Exception e) {
				log.error(e.getMessage());
				return 0;
			}
		}

		public List<BC15TModel> getModelMau15T(String maritimeCode,
											   String startDate, String endDate) throws SystemException {
			return finder.getModelMau15T(maritimeCode, startDate,
					endDate);
		}

		public List<BC12BTKhuCangModel> getModelMau12BTKhuCang(String maritimeCode,
															   String startDate, String endDate) throws SystemException {
			return finder.getModelMau12BTKhuCang(maritimeCode,
					startDate, endDate);
		}


		public DmPortRegion getByPortRegionCode(String portRegionCode) {
		try {
			List<DmPortRegion> dmPortRegiones = persistence.findByPortRegionCode(portRegionCode);
			if (dmPortRegiones != null && dmPortRegiones.size() > 0) { return dmPortRegiones.get(0); }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<DmPortRegion> findPortRegionByPortRegionRef (String portRegionRef) {
		try {
			return persistence.findByPortRegionRef(portRegionRef);
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<DmPortRegion> findPortRegionByPortCodeName (String portCode) {
		try {
			return persistence.findByPortCodeName(portCode);
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<DmPortRegion> findDanhSachDmPortRegion(String maritimeCode, String portRegionName, String portCode, int start, int end) {
		try {
			return finder.findDanhSachDmPortRegion(maritimeCode, portRegionName, portCode, start, end);
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int countDanhSachDmPortRegion(String maritimeCode, String portRegionName, String portCode) {
		try {
			return finder.countDanhSachDmPortRegion(maritimeCode, portRegionName, portCode);
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public DmPortRegion getFirstPortRegion() {
		try {
			return finder.getFirstPortRegion();
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public DmPortRegion getLastPortRegion() {
		try {
			return finder.getLastPortRegion();
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return null;
	}

		/*
		 * NOTE FOR DEVELOPERS:
		 *
		 * Never modify or reference this class directly. Always use {@link vn.gt.dao.danhmuc.service.dmPortRegionLocalService} to access the dm port region local service.
		 */

		/**
		 * Adds the dm port region to the database. Also notifies the appropriate model listeners.
		 *
		 * @param dmPortRegion the dm port region
		 * @return the dm port region that was added
		 * @throws SystemException if a system exception occurred
		 */
		public DmPortRegion addDmPortRegion(DmPortRegion dmPortRegion)
				throws SystemException {

			dmPortRegion = persistence.updateImpl(dmPortRegion, false);





			return dmPortRegion;
		}

		/**
		 * Creates a new dm port region with the primary key. Does not add the dm port region to the database.
		 *
		 * @param id the primary key for the new dm port region
		 * @return the new dm port region
		 */
		public DmPortRegion createDmPortRegion(int id) {
			return persistence.create(id);
		}

		/**
		 * Deletes the dm port region with the primary key from the database. Also notifies the appropriate model listeners.
		 *
		 * @param id the primary key of the dm port region
		 * @throws PortalException if a dm port region with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteDmPortRegion(int id)
				throws PortalException, SystemException {
			DmPortRegion dmPortRegion = persistence.remove(id);




		}

		/**
		 * Deletes the dm port region from the database. Also notifies the appropriate model listeners.
		 *
		 * @param dmPortRegion the dm port region
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteDmPortRegion(DmPortRegion dmPortRegion)
				throws SystemException {
			persistence.remove(dmPortRegion);




		}













		public DmPortRegion fetchDmPortRegion(int id) throws SystemException {
			return persistence.fetchByPrimaryKey(id);
		}

		/**
		 * Returns the dm port region with the primary key.
		 *
		 * @param id the primary key of the dm port region
		 * @return the dm port region
		 * @throws PortalException if a dm port region with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public DmPortRegion getDmPortRegion(int id)
				throws PortalException, SystemException {
			return persistence.findByPrimaryKey(id);
		}

		/**
		 * Returns a range of all the dm port regions.
		 *
		 * <p>
		 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
		 * </p>
		 *
		 * @param start the lower bound of the range of dm port regions
		 * @param end the upper bound of the range of dm port regions (not inclusive)
		 * @return the range of dm port regions
		 * @throws SystemException if a system exception occurred
		 */
		public List<DmPortRegion> getDmPortRegions(int start, int end)
				throws SystemException {
			return persistence.findAll(start, end);
		}

		/**
		 * Returns the number of dm port regions.
		 *
		 * @return the number of dm port regions
		 * @throws SystemException if a system exception occurred
		 */
		public int getDmPortRegionsCount() throws SystemException {
			return persistence.countAll();
		}

		/**
		 * Updates the dm port region in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param dmPortRegion the dm port region
		 * @return the dm port region that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public DmPortRegion updateDmPortRegion(DmPortRegion dmPortRegion)
				throws SystemException {
			return updateDmPortRegion(dmPortRegion, true);
		}

		/**
		 * Updates the dm port region in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param dmPortRegion the dm port region
		 * @param merge whether to merge the dm port region with the current session. See  for an explanation.
		 * @return the dm port region that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public DmPortRegion updateDmPortRegion(DmPortRegion dmPortRegion,
											   boolean merge) throws SystemException {

			dmPortRegion = persistence.updateImpl(dmPortRegion, merge);





			return dmPortRegion;
		}
	
}