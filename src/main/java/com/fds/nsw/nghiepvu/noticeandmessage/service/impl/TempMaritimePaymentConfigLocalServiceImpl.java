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

package com.fds.nsw.nghiepvu.noticeandmessage.service.impl;import java.util.ArrayList; import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;import com.fds.nsw.kernel.exception.PortalException;import org.springframework.stereotype.Service;import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.TempMaritimePaymentConfigPersistenceImpl;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;import lombok.extern.slf4j.Slf4j;@Slf4j @Service






/**
 * The implementation of the temp maritime payment config local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link vn.gt.dao.noticeandmessage.service.TempMaritimePaymentConfigLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.noticeandmessage.service.base.TempMaritimePaymentConfigLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.TempMaritimePaymentConfigLocalServiceUtil
 */
public class TempMaritimePaymentConfigLocalServiceImpl
	{ @Autowired
	TempMaritimePaymentConfigPersistenceImpl persistence;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link vn.gt.dao.noticeandmessage.service.TempMaritimePaymentConfigLocalServiceUtil} to access the temp maritime payment config local service.
	 */
	
	public TempMaritimePaymentConfig getPaymentConfig(String maritimeCode) {
		
		TempMaritimePaymentConfig paymentConfig = null;
		
		try {
			paymentConfig = persistence.fetchByMaritimeCode(maritimeCode);
		} catch(Exception e) {
			
		}
		
		return paymentConfig;
	}

		/*
		 * NOTE FOR DEVELOPERS:
		 *
		 * Never modify or reference this class directly. Always use {@link vn.gt.dao.noticeandmessage.service.TempMaritimePaymentConfigLocalServiceUtil} to access the temp maritime payment config local service.
		 */

		/**
		 * Adds the temp maritime payment config to the database. Also notifies the appropriate model listeners.
		 *
		 * @param tempMaritimePaymentConfig the temp maritime payment config
		 * @return the temp maritime payment config that was added
		 * @throws SystemException if a system exception occurred
		 */
		public TempMaritimePaymentConfig addTempMaritimePaymentConfig(
				TempMaritimePaymentConfig tempMaritimePaymentConfig)
				throws SystemException {

			tempMaritimePaymentConfig = persistence.updateImpl(tempMaritimePaymentConfig,
					false);





			return tempMaritimePaymentConfig;
		}

		/**
		 * Creates a new temp maritime payment config with the primary key. Does not add the temp maritime payment config to the database.
		 *
		 * @param id the primary key for the new temp maritime payment config
		 * @return the new temp maritime payment config
		 */
		public TempMaritimePaymentConfig createTempMaritimePaymentConfig(long id) {
			return persistence.create(id);
		}

		/**
		 * Deletes the temp maritime payment config with the primary key from the database. Also notifies the appropriate model listeners.
		 *
		 * @param id the primary key of the temp maritime payment config
		 * @throws PortalException if a temp maritime payment config with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteTempMaritimePaymentConfig(long id)
				throws PortalException, SystemException {
			TempMaritimePaymentConfig tempMaritimePaymentConfig = persistence.remove(id);




		}

		/**
		 * Deletes the temp maritime payment config from the database. Also notifies the appropriate model listeners.
		 *
		 * @param tempMaritimePaymentConfig the temp maritime payment config
		 * @throws SystemException if a system exception occurred
		 */
		public void deleteTempMaritimePaymentConfig(
				TempMaritimePaymentConfig tempMaritimePaymentConfig)
				throws SystemException {
			persistence.remove(tempMaritimePaymentConfig);




		}













		public TempMaritimePaymentConfig fetchTempMaritimePaymentConfig(long id)
				throws SystemException {
			return persistence.fetchByPrimaryKey(id);
		}

		/**
		 * Returns the temp maritime payment config with the primary key.
		 *
		 * @param id the primary key of the temp maritime payment config
		 * @return the temp maritime payment config
		 * @throws PortalException if a temp maritime payment config with the primary key could not be found
		 * @throws SystemException if a system exception occurred
		 */
		public TempMaritimePaymentConfig getTempMaritimePaymentConfig(long id)
				throws PortalException, SystemException {
			return persistence.findByPrimaryKey(id);
		}


		/**
		 * Returns a range of all the temp maritime payment configs.
		 *
		 * <p>
		 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
		 * </p>
		 *
		 * @param start the lower bound of the range of temp maritime payment configs
		 * @param end the upper bound of the range of temp maritime payment configs (not inclusive)
		 * @return the range of temp maritime payment configs
		 * @throws SystemException if a system exception occurred
		 */
		public List<TempMaritimePaymentConfig> getTempMaritimePaymentConfigs(
				int start, int end) throws SystemException {
			return persistence.findAll(start, end);
		}

		/**
		 * Returns the number of temp maritime payment configs.
		 *
		 * @return the number of temp maritime payment configs
		 * @throws SystemException if a system exception occurred
		 */
		public int getTempMaritimePaymentConfigsCount() throws SystemException {
			return persistence.countAll();
		}

		/**
		 * Updates the temp maritime payment config in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param tempMaritimePaymentConfig the temp maritime payment config
		 * @return the temp maritime payment config that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public TempMaritimePaymentConfig updateTempMaritimePaymentConfig(
				TempMaritimePaymentConfig tempMaritimePaymentConfig)
				throws SystemException {
			return updateTempMaritimePaymentConfig(tempMaritimePaymentConfig, true);
		}

		/**
		 * Updates the temp maritime payment config in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
		 *
		 * @param tempMaritimePaymentConfig the temp maritime payment config
		 * @param merge whether to merge the temp maritime payment config with the current session. See  for an explanation.
		 * @return the temp maritime payment config that was updated
		 * @throws SystemException if a system exception occurred
		 */
		public TempMaritimePaymentConfig updateTempMaritimePaymentConfig(
				TempMaritimePaymentConfig tempMaritimePaymentConfig, boolean merge)
				throws SystemException {

			tempMaritimePaymentConfig = persistence.updateImpl(tempMaritimePaymentConfig,
					merge);





			return tempMaritimePaymentConfig;
		}
}