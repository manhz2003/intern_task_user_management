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

package com.fds.nsw.nghiepvu.danhmuc.service.impl;

import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.nghiepvu.danhmuc.service.finder.DmShipAgencyFinderImpl;import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;

import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmShipAgencyPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import lombok.extern.slf4j.Slf4j;
import vn.gt.dao.danhmuc.service.DmHistoryShipAgencyLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmShipAgencyLocalServiceUtil;

@Slf4j @Service






/**
 * The implementation of the dm ship agency local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link vn.gt.dao.danhmuc.service.DmShipAgencyLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.danhmuc.service.base.DmShipAgencyLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.DmShipAgencyLocalServiceUtil
 */
public class DmShipAgencyLocalServiceImpl
	{ @Autowired
	DmShipAgencyPersistenceImpl persistence;
		
		@Autowired
		DmShipAgencyFinderImpl finder;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link vn.gt.dao.danhmuc.service.DmShipAgencyLocalServiceUtil} to access the dm ship agency local service.
	 */

		public List<DmShipAgency> findShipAgencys(String shipAgencyNameVN,
												  String addressVN, String fax, String taxCode, String isDelete,
												  String shipAgencyCodeGroup, int start, int end) {
			try {
				return finder.findShipAgencys(shipAgencyNameVN,
						addressVN, fax, taxCode, isDelete, shipAgencyCodeGroup,
						start, end);
			} catch (Exception e) {
				log.error(e.getMessage());
				return null;
			}
		}

		public long countShipAgencys(String shipAgencyNameVN, String addressVN,
									 String fax, String taxCode, String isDelete,
									 String shipAgencyCodeGroup) {
			try {
				return finder.countShipAgencys(shipAgencyNameVN,
						addressVN, fax, taxCode, isDelete, shipAgencyCodeGroup);
			} catch (Exception e) {
				log.error(e.getMessage());
				return 0;
			}
		}

		public DmShipAgency fetchByShipAgencyCode(String shipAgencyCode)
				throws SystemException {
			return persistence.fetchByF_shipAgencyCode(shipAgencyCode);
		}

		//update by TrungNT -> add param shipAgencyShortName
		public DmShipAgency updateDmShipAgency(String shipAgencyCode,
											   String shipAgencyName, String shipAgencyNameVN, String taxCode,
											   String stateCode, String citycode, String address,
											   String addressVN, String phone, String fax, String email,
											   String description, String representative1,
											   String representativeTitle1, String representative2,
											   String representativeTitle2, String contacter, String position,
											   String contactTell, String syncVersion, String shipAgencyShortName) throws SystemException {

			DmShipAgency dmShipAgency = persistence
					.fetchByF_shipAgencyCode(shipAgencyCode);

			if (dmShipAgency == null) {
				dmShipAgency = new DmShipAgency();
				dmShipAgency.setShipAgencyCode(shipAgencyCode);


				DmHistoryShipAgency dmHistoryShipAgency = new DmHistoryShipAgency();
				dmHistoryShipAgency.setShipAgencyCode(shipAgencyCode);
				dmHistoryShipAgency.setShipAgencyName(shipAgencyNameVN);
				dmHistoryShipAgency.setShipAgencyNameVN(shipAgencyNameVN);
				dmHistoryShipAgency.setTaxCode(shipAgencyCode);
				dmHistoryShipAgency.setAddress(address);
				dmHistoryShipAgency.setAddressVN(addressVN);
				dmHistoryShipAgency.setPhone(phone);
				dmHistoryShipAgency.setFax(fax);
				dmHistoryShipAgency.setEmail(email);

				dmHistoryShipAgency.setDescription(description);
				dmHistoryShipAgency.setRepresentative1(representative1);
				dmHistoryShipAgency.setRepresentativeTitle1(representativeTitle1);
				dmHistoryShipAgency.setRepresentative2(representative2);
				dmHistoryShipAgency.setRepresentativeTitle2(representativeTitle2);
				dmHistoryShipAgency.setContacter(contacter);
				dmHistoryShipAgency.setPosition(position);
				dmHistoryShipAgency.setContactTell(contactTell);
				dmHistoryShipAgency.setShipAgencyShortName(shipAgencyShortName);

				dmHistoryShipAgency.setIsDelete(0);
				dmHistoryShipAgency.setMarkedAsDelete(0);
				dmHistoryShipAgency.setModifiedDate(new Date());
				dmHistoryShipAgency.setRequestedDate(new Date());
				dmHistoryShipAgency.setSyncVersion("1");

				DmHistoryShipAgencyLocalServiceUtil
						.addDmHistoryShipAgency(dmHistoryShipAgency);

			}

			Date now = new Date();

			//Khong cho sua MST
			//dmShipAgency.setShipAgencyCode(shipAgencyCode);
			dmShipAgency.setShipAgencyName(shipAgencyNameVN);
			dmShipAgency.setShipAgencyNameVN(shipAgencyNameVN);
			dmShipAgency.setTaxCode(taxCode);
			dmShipAgency.setStateCode(stateCode);
			dmShipAgency.setCityCode(citycode);
			dmShipAgency.setAddress(address);
			dmShipAgency.setAddressVN(addressVN);
			dmShipAgency.setPhone(phone);
			dmShipAgency.setFax(fax);
			dmShipAgency.setEmail(email);
			dmShipAgency.setDescription(description);
			dmShipAgency.setRepresentative1(representative1);
			dmShipAgency.setRepresentativeTitle1(representativeTitle1);
			dmShipAgency.setRepresentative2(representative2);
			dmShipAgency.setRepresentativeTitle2(representativeTitle2);
			dmShipAgency.setContacter(contacter);
			dmShipAgency.setPosition(position);
			dmShipAgency.setContactTell(contactTell);
			dmShipAgency.setSyncVersion(syncVersion);
			dmShipAgency.setIsDelete(0);
			dmShipAgency.setMarkedAsDelete(0);
			dmShipAgency.setModifiedDate(now);
			dmShipAgency.setRequestedDate(now);
			dmShipAgency.setShipAgencyShortName(shipAgencyShortName);

			if (dmShipAgency.getId() == 0 || Validator.isNull(dmShipAgency.getId())) {
				DmShipAgencyLocalServiceUtil.addDmShipAgency(dmShipAgency);
			} else {
				dmShipAgency = persistence.updateImpl(dmShipAgency, false);
			}

			return dmShipAgency;
		}

		public DmShipAgency deleteDmShipAgency(String shipAgencyCode,
											   String syncVersion) throws SystemException {
			DmShipAgency dmShipAgency = persistence
					.fetchByF_shipAgencyCode(shipAgencyCode);

			Date now = new Date();

			dmShipAgency.setSyncVersion(syncVersion);
			dmShipAgency.setIsDelete(1);
			dmShipAgency.setMarkedAsDelete(1);
			dmShipAgency.setModifiedDate(now);
			dmShipAgency.setRequestedDate(now);

			dmShipAgency = persistence.updateImpl(dmShipAgency, true);

			return dmShipAgency;
		}

		public JSONArray getModelMau58T(String query) throws SystemException {
			return finder.getModelMau58T(query);
		}

		public JSONArray getModelMau58TauDich(String query) throws SystemException {
			return finder.getModelMau58TauDich(query);
		}

		public JSONArray getModelMau59(String query) throws SystemException {
			return finder.getModelMau59(query);
		}


		public DmShipAgency getByShipAgencyCode(String shipAgencyCode) {
		try {
			List<DmShipAgency> dmShipAgencyCodes = persistence.findByShipAgencyCode(shipAgencyCode);
			if (dmShipAgencyCodes != null && dmShipAgencyCodes.size() > 0) { return dmShipAgencyCodes.get(0); }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

		/*
		 * NOTE FOR DEVELOPERS:
		 *
		 * Never modify or reference this class directly. Always use {@link vn.gt.dao.danhmuc.service.DmShipAgencyLocalServiceUtil} to access the dm ship agency local service.
		 */

		/**
		 * Adds the dm ship agency to the database. Also notifies the appropriate model listeners.
		 *
		 * @param dmShipAgency the dm ship agency
		 * @return the dm ship agency that was added
		 * @throws SystemException if a system exception occurred
		 */
		public DmShipAgency addDmShipAgency(DmShipAgency dmShipAgency)
				throws SystemException {

			dmShipAgency = persistence.updateImpl(dmShipAgency, false);





			return dmShipAgency;
		}

		/**
		 * Creates a new dm ship agency with the primary key. Does not add the dm ship agency to the database.
		 *
		 * @param id the primary key for the new dm ship agency
		 * @return the new dm ship agency
		 */
		public DmShipAgency createDmShipAgency(int id) {
			return persistence.create(id);
		}

		/**
		 * Deletes the dm ship agency with the primary key from the database. Also notifies the appropriate model listeners.
		 *
		 * @param id the primary key of the dm ship agency
		 * @throws PortalException if a dm ship agency with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteDmShipAgency(int id)
				throws PortalException, SystemException {
			DmShipAgency dmShipAgency = persistence.remove(id);




		}

		/**
		 * Deletes the dm ship agency from the database. Also notifies the appropriate model listeners.
		 *
		 * @param dmShipAgency the dm ship agency
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteDmShipAgency(DmShipAgency dmShipAgency)
				throws SystemException {
			persistence.remove(dmShipAgency);




		}













		public DmShipAgency fetchDmShipAgency(int id) throws SystemException {
			return persistence.fetchByPrimaryKey(id);
		}

		/**
		 * Returns the dm ship agency with the primary key.
		 *
		 * @param id the primary key of the dm ship agency
		 * @return the dm ship agency
		 * @throws PortalException if a dm ship agency with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public DmShipAgency getDmShipAgency(int id)
				throws PortalException, SystemException {
			return persistence.findByPrimaryKey(id);
		}


		/**
		 * Returns a range of all the dm ship agencies.
		 *
		 * <p>
		 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
		 * </p>
		 *
		 * @param start the lower bound of the range of dm ship agencies
		 * @param end the upper bound of the range of dm ship agencies (not inclusive)
		 * @return the range of dm ship agencies
		 * @throws SystemException if a system exception occurred
		 */
		public List<DmShipAgency> getDmShipAgencies(int start, int end)
				throws SystemException {
			return persistence.findAll(start, end);
		}

		/**
		 * Returns the number of dm ship agencies.
		 *
		 * @return the number of dm ship agencies
		 * @throws SystemException if a system exception occurred
		 */
		public int getDmShipAgenciesCount() throws SystemException {
			return persistence.countAll();
		}

		/**
		 * Updates the dm ship agency in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param dmShipAgency the dm ship agency
		 * @return the dm ship agency that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public DmShipAgency updateDmShipAgency(DmShipAgency dmShipAgency)
				throws SystemException {
			return updateDmShipAgency(dmShipAgency, true);
		}

		/**
		 * Updates the dm ship agency in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param dmShipAgency the dm ship agency
		 * @param merge whether to merge the dm ship agency with the current session. See  for an explanation.
		 * @return the dm ship agency that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public DmShipAgency updateDmShipAgency(DmShipAgency dmShipAgency,
											   boolean merge) throws SystemException {

			dmShipAgency = persistence.updateImpl(dmShipAgency, merge);





			return dmShipAgency;
		}
}