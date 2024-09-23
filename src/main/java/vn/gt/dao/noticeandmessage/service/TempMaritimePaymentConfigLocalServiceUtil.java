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

package vn.gt.dao.noticeandmessage.service;






/**
 * The utility for the temp maritime payment config local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.TempMaritimePaymentConfigLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see TempMaritimePaymentConfigLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.TempMaritimePaymentConfigLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.TempMaritimePaymentConfigLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.TempMaritimePaymentConfigLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class TempMaritimePaymentConfigLocalServiceUtil {
public TempMaritimePaymentConfigLocalServiceUtil(TempMaritimePaymentConfigLocalServiceImpl service) {
TempMaritimePaymentConfigLocalServiceUtil._service = service;
}
public static TempMaritimePaymentConfigLocalServiceImpl getService() {
return _service;
}
private static TempMaritimePaymentConfigLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.TempMaritimePaymentConfigLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the temp maritime payment config to the database. Also notifies the appropriate model listeners.
	*
	* @param tempMaritimePaymentConfig the temp maritime payment config
	* @return the temp maritime payment config that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempMaritimePaymentConfig addTempMaritimePaymentConfig(
		com.fds.nsw.nghiepvu.model.TempMaritimePaymentConfig tempMaritimePaymentConfig)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .addTempMaritimePaymentConfig(tempMaritimePaymentConfig);
	}

	/**
	* Creates a new temp maritime payment config with the primary key. Does not add the temp maritime payment config to the database.
	*
	* @param id the primary key for the new temp maritime payment config
	* @return the new temp maritime payment config
	*/
	public static com.fds.nsw.nghiepvu.model.TempMaritimePaymentConfig createTempMaritimePaymentConfig(
		long id) {
		return getService().createTempMaritimePaymentConfig(id);
	}

	/**
	* Deletes the temp maritime payment config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the temp maritime payment config
	* @throws PortalException if a temp maritime payment config with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempMaritimePaymentConfig(long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempMaritimePaymentConfig(id);
	}

	/**
	* Deletes the temp maritime payment config from the database. Also notifies the appropriate model listeners.
	*
	* @param tempMaritimePaymentConfig the temp maritime payment config
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteTempMaritimePaymentConfig(
		com.fds.nsw.nghiepvu.model.TempMaritimePaymentConfig tempMaritimePaymentConfig)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteTempMaritimePaymentConfig(tempMaritimePaymentConfig);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.TempMaritimePaymentConfig fetchTempMaritimePaymentConfig(
		long id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchTempMaritimePaymentConfig(id);
	}

	/**
	* Returns the temp maritime payment config with the primary key.
	*
	* @param id the primary key of the temp maritime payment config
	* @return the temp maritime payment config
	* @throws PortalException if a temp maritime payment config with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempMaritimePaymentConfig getTempMaritimePaymentConfig(
		long id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempMaritimePaymentConfig(id);
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
	public static java.util.List<com.fds.nsw.nghiepvu.model.TempMaritimePaymentConfig> getTempMaritimePaymentConfigs(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempMaritimePaymentConfigs(start, end);
	}

	/**
	* Returns the number of temp maritime payment configs.
	*
	* @return the number of temp maritime payment configs
	* @throws SystemException if a system exception occurred
	*/
	public static int getTempMaritimePaymentConfigsCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getTempMaritimePaymentConfigsCount();
	}

	/**
	* Updates the temp maritime payment config in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempMaritimePaymentConfig the temp maritime payment config
	* @return the temp maritime payment config that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempMaritimePaymentConfig updateTempMaritimePaymentConfig(
		com.fds.nsw.nghiepvu.model.TempMaritimePaymentConfig tempMaritimePaymentConfig)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .updateTempMaritimePaymentConfig(tempMaritimePaymentConfig);
	}

	/**
	* Updates the temp maritime payment config in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param tempMaritimePaymentConfig the temp maritime payment config
	* @param merge whether to merge the temp maritime payment config with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the temp maritime payment config that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.TempMaritimePaymentConfig updateTempMaritimePaymentConfig(
		com.fds.nsw.nghiepvu.model.TempMaritimePaymentConfig tempMaritimePaymentConfig,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				   .updateTempMaritimePaymentConfig(tempMaritimePaymentConfig,
			merge);
	}

	

	

	public static com.fds.nsw.nghiepvu.model.TempMaritimePaymentConfig getPaymentConfig(
		java.lang.String maritimeCode) {
		return getService().getPaymentConfig(maritimeCode);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}