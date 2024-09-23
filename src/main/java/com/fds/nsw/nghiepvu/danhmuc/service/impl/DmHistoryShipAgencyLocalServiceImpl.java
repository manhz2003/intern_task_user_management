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

package com.fds.nsw.nghiepvu.danhmuc.service.impl;import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fds.nsw.liferay.core.CounterLocalServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;

import org.springframework.stereotype.Service;

import com.fds.nsw.nghiepvu.danhmuc.service.persistence.DmHistoryShipAgencyPersistenceImpl;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import lombok.extern.slf4j.Slf4j;@Slf4j @Service









/**
 * The implementation of the dm history ship agency local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link vn.gt.dao.danhmuc.service.DmHistoryShipAgencyLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.danhmuc.service.base.DmHistoryShipAgencyLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.DmHistoryShipAgencyLocalServiceUtil
 */
public class DmHistoryShipAgencyLocalServiceImpl
	{ @Autowired
	DmHistoryShipAgencyPersistenceImpl persistence;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link vn.gt.dao.danhmuc.service.DmHistoryShipAgencyLocalServiceUtil} to access the dm history ship agency local service.
	 */
	public DmHistoryShipAgency getByShipAgencyCode(String shipAgencyCode) {
		try {
			List<DmHistoryShipAgency> dmHistoryShipAgencyes = persistence.findByShipAgencyCode(shipAgencyCode);
			if (dmHistoryShipAgencyes != null && dmHistoryShipAgencyes.size() > 0) { return dmHistoryShipAgencyes.get(0); }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public DmHistoryShipAgency findByPurposeCodeAndSyncVersion(String shipAgencyCode, String syncVersion) {
		try {
			return persistence.findByShipAgencyCodeAndSyncVersion(shipAgencyCode, syncVersion);
		} catch (NoSuchDmHistoryShipAgencyException e) {
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return null;
		
	}

		/*
		 * NOTE FOR DEVELOPERS:
		 *
		 * Never modify or reference this class directly. Always use {@link vn.gt.dao.danhmuc.service.DmHistoryShipAgencyLocalServiceUtil} to access the dm history ship agency local service.
		 */

		/**
		 * Adds the dm history ship agency to the database. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryShipAgency the dm history ship agency
		 * @return the dm history ship agency that was added
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryShipAgency addDmHistoryShipAgency(
				DmHistoryShipAgency dmHistoryShipAgency) throws SystemException {

			dmHistoryShipAgency = persistence.updateImpl(dmHistoryShipAgency,
					false);





			return dmHistoryShipAgency;
		}

		/**
		 * Creates a new dm history ship agency with the primary key. Does not add the dm history ship agency to the database.
		 *
		 * @param id the primary key for the new dm history ship agency
		 * @return the new dm history ship agency
		 */
		public DmHistoryShipAgency createDmHistoryShipAgency(int id) {
			return persistence.create(id);
		}

		/**
		 * Deletes the dm history ship agency with the primary key from the database. Also notifies the appropriate model listeners.
		 *
		 * @param id the primary key of the dm history ship agency
		 * @throws PortalException if a dm history ship agency with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteDmHistoryShipAgency(int id)
				throws PortalException, SystemException {
			DmHistoryShipAgency dmHistoryShipAgency = persistence.remove(id);




		}

		/**
		 * Deletes the dm history ship agency from the database. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryShipAgency the dm history ship agency
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteDmHistoryShipAgency(
				DmHistoryShipAgency dmHistoryShipAgency) throws SystemException {
			persistence.remove(dmHistoryShipAgency);




		}













		public DmHistoryShipAgency fetchDmHistoryShipAgency(int id)
				throws SystemException {
			return persistence.fetchByPrimaryKey(id);
		}

		/**
		 * Returns the dm history ship agency with the primary key.
		 *
		 * @param id the primary key of the dm history ship agency
		 * @return the dm history ship agency
		 * @throws PortalException if a dm history ship agency with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryShipAgency getDmHistoryShipAgency(int id)
				throws PortalException, SystemException {
			return persistence.findByPrimaryKey(id);
		}


		/**
		 * Returns a range of all the dm history ship agencies.
		 *
		 * <p>
		 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
		 * </p>
		 *
		 * @param start the lower bound of the range of dm history ship agencies
		 * @param end the upper bound of the range of dm history ship agencies (not inclusive)
		 * @return the range of dm history ship agencies
		 * @throws SystemException if a system exception occurred
		 */
		public List<DmHistoryShipAgency> getDmHistoryShipAgencies(int start, int end)
				throws SystemException {
			return persistence.findAll(start, end);
		}

		/**
		 * Returns the number of dm history ship agencies.
		 *
		 * @return the number of dm history ship agencies
		 * @throws SystemException if a system exception occurred
		 */
		public int getDmHistoryShipAgenciesCount() throws SystemException {
			return persistence.countAll();
		}

		/**
		 * Updates the dm history ship agency in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryShipAgency the dm history ship agency
		 * @return the dm history ship agency that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryShipAgency updateDmHistoryShipAgency(
				DmHistoryShipAgency dmHistoryShipAgency) throws SystemException {
			return updateDmHistoryShipAgency(dmHistoryShipAgency, true);
		}

		/**
		 * Updates the dm history ship agency in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param dmHistoryShipAgency the dm history ship agency
		 * @param merge whether to merge the dm history ship agency with the current session. See  for an explanation.
		 * @return the dm history ship agency that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public DmHistoryShipAgency updateDmHistoryShipAgency(
				DmHistoryShipAgency dmHistoryShipAgency, boolean merge)
				throws SystemException {

			dmHistoryShipAgency = persistence.updateImpl(dmHistoryShipAgency,
					merge);





			return dmHistoryShipAgency;
		}

		public DmHistoryShipAgency updateDmHistoryShipAgency(String shipAgencyCode,
															 String shipAgencyName, String shipAgencyNameVN, String taxCode,
															 String stateCode, String citycode, String address,
															 String addressVN, String phone, String fax, String email,
															 String description, String representative1,
															 String representativeTitle1, String representative2,
															 String representativeTitle2, String contacter, String position,
															 String contactTell, String syncVersion, String shipAgencyShortName) throws SystemException {

			DmHistoryShipAgency dmHistoryShipAgency = persistence
					.fetchByShipAgencyCodeAndSyncVersion(shipAgencyCode,
							syncVersion);

			if (dmHistoryShipAgency == null) {
				int id = (int) CounterLocalServiceUtil
						.increment(DmHistoryShipAgency.class.getName());

				dmHistoryShipAgency = persistence.create(id);
			}

			Date now = new Date();

			dmHistoryShipAgency.setShipAgencyCode(shipAgencyCode);
			dmHistoryShipAgency.setShipAgencyName(shipAgencyNameVN);
			dmHistoryShipAgency.setShipAgencyNameVN(shipAgencyNameVN);
			dmHistoryShipAgency.setTaxCode(taxCode);
			dmHistoryShipAgency.setStateCode(stateCode);
			dmHistoryShipAgency.setCityCode(citycode);
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
			dmHistoryShipAgency.setSyncVersion(syncVersion);
			dmHistoryShipAgency.setIsDelete(0);
			dmHistoryShipAgency.setMarkedAsDelete(0);
			dmHistoryShipAgency.setModifiedDate(now);
			dmHistoryShipAgency.setRequestedDate(now);
			dmHistoryShipAgency.setShipAgencyShortName(shipAgencyShortName);

			dmHistoryShipAgency = persistence.updateImpl(
					dmHistoryShipAgency, true);

			return dmHistoryShipAgency;
		}

		public DmHistoryShipAgency deleteDmHistoryShipAgency(String shipAgencyCode,
															 String syncVersion) throws SystemException {
			DmHistoryShipAgency dmHistoryShipAgency = persistence
					.fetchByShipAgencyCodeAndSyncVersion(shipAgencyCode,
							syncVersion);

			if (dmHistoryShipAgency == null) {
				int id = (int) CounterLocalServiceUtil
						.increment(DmHistoryShipAgency.class.getName());

				dmHistoryShipAgency = persistence.create(id);
			}

			Date now = new Date();

			dmHistoryShipAgency.setSyncVersion(syncVersion);
			dmHistoryShipAgency.setIsDelete(0);
			dmHistoryShipAgency.setMarkedAsDelete(0);
			dmHistoryShipAgency.setModifiedDate(now);
			dmHistoryShipAgency.setRequestedDate(now);

			dmHistoryShipAgency = persistence.updateImpl(
					dmHistoryShipAgency, true);

			return dmHistoryShipAgency;
		}

	}