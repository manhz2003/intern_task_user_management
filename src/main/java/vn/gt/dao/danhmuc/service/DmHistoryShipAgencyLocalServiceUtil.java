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
 * The utility for the dm history ship agency local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmHistoryShipAgencyLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmHistoryShipAgencyLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmHistoryShipAgencyLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmHistoryShipAgencyLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmHistoryShipAgencyLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class DmHistoryShipAgencyLocalServiceUtil {
public DmHistoryShipAgencyLocalServiceUtil(DmHistoryShipAgencyLocalServiceImpl service) {
DmHistoryShipAgencyLocalServiceUtil._service = service;
}
public static DmHistoryShipAgencyLocalServiceImpl getService() {
return _service;
}
private static DmHistoryShipAgencyLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmHistoryShipAgencyLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dm history ship agency to the database. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryShipAgency the dm history ship agency
	* @return the dm history ship agency that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryShipAgency addDmHistoryShipAgency(
		com.fds.nsw.nghiepvu.model.DmHistoryShipAgency dmHistoryShipAgency)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmHistoryShipAgency(dmHistoryShipAgency);
	}

	/**
	* Creates a new dm history ship agency with the primary key. Does not add the dm history ship agency to the database.
	*
	* @param id the primary key for the new dm history ship agency
	* @return the new dm history ship agency
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryShipAgency createDmHistoryShipAgency(
		int id) {
		return getService().createDmHistoryShipAgency(id);
	}

	/**
	* Deletes the dm history ship agency with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the dm history ship agency
	* @throws PortalException if a dm history ship agency with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmHistoryShipAgency(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmHistoryShipAgency(id);
	}

	public static com.fds.nsw.nghiepvu.model.DmHistoryShipAgency updateDmHistoryShipAgency(
			java.lang.String shipAgencyCode, java.lang.String shipAgencyName,
			java.lang.String shipAgencyNameVN, java.lang.String taxCode,
			java.lang.String stateCode, java.lang.String citycode,
			java.lang.String address, java.lang.String addressVN,
			java.lang.String phone, java.lang.String fax, java.lang.String email,
			java.lang.String description, java.lang.String representative1,
			java.lang.String representativeTitle1,
			java.lang.String representative2,
			java.lang.String representativeTitle2, java.lang.String contacter,
			java.lang.String position, java.lang.String contactTell,
			java.lang.String syncVersion, java.lang.String shipAgencyShortName)
			throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				.updateDmHistoryShipAgency(shipAgencyCode, shipAgencyName,
						shipAgencyNameVN, taxCode, stateCode, citycode, address, addressVN,
						phone, fax, email, description, representative1,
						representativeTitle1, representative2, representativeTitle2,
						contacter, position, contactTell, syncVersion, shipAgencyShortName);
	}

	public static com.fds.nsw.nghiepvu.model.DmHistoryShipAgency deleteDmHistoryShipAgency(
			java.lang.String shipAgencyCode, java.lang.String syncVersion)
			throws com.fds.nsw.kernel.exception.SystemException {
		return getService()
				.deleteDmHistoryShipAgency(shipAgencyCode, syncVersion);
	}


	/**
	* Deletes the dm history ship agency from the database. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryShipAgency the dm history ship agency
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmHistoryShipAgency(
		com.fds.nsw.nghiepvu.model.DmHistoryShipAgency dmHistoryShipAgency)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmHistoryShipAgency(dmHistoryShipAgency);
	}

	
	

	

	

	

	public static com.fds.nsw.nghiepvu.model.DmHistoryShipAgency fetchDmHistoryShipAgency(
		int id) throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmHistoryShipAgency(id);
	}

	/**
	* Returns the dm history ship agency with the primary key.
	*
	* @param id the primary key of the dm history ship agency
	* @return the dm history ship agency
	* @throws PortalException if a dm history ship agency with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryShipAgency getDmHistoryShipAgency(
		int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryShipAgency(id);
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
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmHistoryShipAgency> getDmHistoryShipAgencies(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryShipAgencies(start, end);
	}

	/**
	* Returns the number of dm history ship agencies.
	*
	* @return the number of dm history ship agencies
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmHistoryShipAgenciesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmHistoryShipAgenciesCount();
	}

	/**
	* Updates the dm history ship agency in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryShipAgency the dm history ship agency
	* @return the dm history ship agency that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryShipAgency updateDmHistoryShipAgency(
		com.fds.nsw.nghiepvu.model.DmHistoryShipAgency dmHistoryShipAgency)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmHistoryShipAgency(dmHistoryShipAgency);
	}

	/**
	* Updates the dm history ship agency in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmHistoryShipAgency the dm history ship agency
	* @param merge whether to merge the dm history ship agency with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm history ship agency that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmHistoryShipAgency updateDmHistoryShipAgency(
		com.fds.nsw.nghiepvu.model.DmHistoryShipAgency dmHistoryShipAgency,
		boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmHistoryShipAgency(dmHistoryShipAgency, merge);
	}

	

	

	public static com.fds.nsw.nghiepvu.model.DmHistoryShipAgency getByShipAgencyCode(
		java.lang.String shipAgencyCode) {
		return getService().getByShipAgencyCode(shipAgencyCode);
	}

	public static com.fds.nsw.nghiepvu.model.DmHistoryShipAgency findByPurposeCodeAndSyncVersion(
		java.lang.String shipAgencyCode, java.lang.String syncVersion) {
		return getService()
				   .findByPurposeCodeAndSyncVersion(shipAgencyCode, syncVersion);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}