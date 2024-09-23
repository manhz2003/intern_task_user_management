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
 * The utility for the dm ship agency local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmShipAgencyLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmShipAgencyLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmShipAgencyLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmShipAgencyLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmShipAgencyLocalServiceImpl;
import org.springframework.stereotype.Component; @Component public class DmShipAgencyLocalServiceUtil {
public DmShipAgencyLocalServiceUtil(DmShipAgencyLocalServiceImpl service) {
DmShipAgencyLocalServiceUtil._service = service;
}
public static DmShipAgencyLocalServiceImpl getService() {
return _service;
}
private static DmShipAgencyLocalServiceImpl _service;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmShipAgencyLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dm ship agency to the database. Also notifies the appropriate model listeners.
	*
	* @param dmShipAgency the dm ship agency
	* @return the dm ship agency that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmShipAgency addDmShipAgency(
		com.fds.nsw.nghiepvu.model.DmShipAgency dmShipAgency)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().addDmShipAgency(dmShipAgency);
	}

	/**
	* Creates a new dm ship agency with the primary key. Does not add the dm ship agency to the database.
	*
	* @param id the primary key for the new dm ship agency
	* @return the new dm ship agency
	*/
	public static com.fds.nsw.nghiepvu.model.DmShipAgency createDmShipAgency(
		int id) {
		return getService().createDmShipAgency(id);
	}

	/**
	* Deletes the dm ship agency with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the dm ship agency
	* @throws PortalException if a dm ship agency with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmShipAgency(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmShipAgency(id);
	}

	public static java.util.List<com.fds.nsw.nghiepvu.model.DmShipAgency> findShipAgencys(
			java.lang.String shipAgencyNameVN, java.lang.String addressVN,
			java.lang.String fax, java.lang.String taxCode,
			java.lang.String isDelete, java.lang.String shipAgencyCodeGroup,
			int start, int end) {
		return getService()
				.findShipAgencys(shipAgencyNameVN, addressVN, fax, taxCode,
						isDelete, shipAgencyCodeGroup, start, end);
	}

	public static long countShipAgencys(java.lang.String shipAgencyNameVN,
										java.lang.String addressVN, java.lang.String fax,
										java.lang.String taxCode, java.lang.String isDelete,
										java.lang.String shipAgencyCodeGroup) {
		return getService()
				.countShipAgencys(shipAgencyNameVN, addressVN, fax, taxCode,
						isDelete, shipAgencyCodeGroup);
	}

	public static com.fds.nsw.nghiepvu.model.DmShipAgency fetchByShipAgencyCode(
			java.lang.String shipAgencyCode)
			throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchByShipAgencyCode(shipAgencyCode);
	}

	public static com.fds.nsw.nghiepvu.model.DmShipAgency updateDmShipAgency(
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
				.updateDmShipAgency(shipAgencyCode, shipAgencyName,
						shipAgencyNameVN, taxCode, stateCode, citycode, address, addressVN,
						phone, fax, email, description, representative1,
						representativeTitle1, representative2, representativeTitle2,
						contacter, position, contactTell, syncVersion, shipAgencyShortName);
	}

	public static com.fds.nsw.nghiepvu.model.DmShipAgency deleteDmShipAgency(
			java.lang.String shipAgencyCode, java.lang.String syncVersion)
			throws com.fds.nsw.kernel.exception.SystemException {
		return getService().deleteDmShipAgency(shipAgencyCode, syncVersion);
	}

	public static org.json.JSONArray getModelMau58T(
			java.lang.String query)
			throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getModelMau58T(query);
	}

	public static org.json.JSONArray getModelMau58TauDich(
			java.lang.String query)
			throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getModelMau58TauDich(query);
	}

	public static org.json.JSONArray getModelMau59(
			java.lang.String query)
			throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getModelMau59(query);
	}


	/**
	* Deletes the dm ship agency from the database. Also notifies the appropriate model listeners.
	*
	* @param dmShipAgency the dm ship agency
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteDmShipAgency(
		com.fds.nsw.nghiepvu.model.DmShipAgency dmShipAgency)
		throws com.fds.nsw.kernel.exception.SystemException {
		getService().deleteDmShipAgency(dmShipAgency);
	}

	public static com.fds.nsw.nghiepvu.model.DmShipAgency fetchDmShipAgency(int id)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().fetchDmShipAgency(id);
	}

	/**
	* Returns the dm ship agency with the primary key.
	*
	* @param id the primary key of the dm ship agency
	* @return the dm ship agency
	* @throws PortalException if a dm ship agency with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmShipAgency getDmShipAgency(int id)
		throws com.fds.nsw.kernel.exception.PortalException,
			com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmShipAgency(id);
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
	public static java.util.List<com.fds.nsw.nghiepvu.model.DmShipAgency> getDmShipAgencies(
		int start, int end)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmShipAgencies(start, end);
	}

	/**
	* Returns the number of dm ship agencies.
	*
	* @return the number of dm ship agencies
	* @throws SystemException if a system exception occurred
	*/
	public static int getDmShipAgenciesCount()
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().getDmShipAgenciesCount();
	}

	/**
	* Updates the dm ship agency in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmShipAgency the dm ship agency
	* @return the dm ship agency that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmShipAgency updateDmShipAgency(
		com.fds.nsw.nghiepvu.model.DmShipAgency dmShipAgency)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmShipAgency(dmShipAgency);
	}

	/**
	* Updates the dm ship agency in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dmShipAgency the dm ship agency
	* @param merge whether to merge the dm ship agency with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the dm ship agency that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.fds.nsw.nghiepvu.model.DmShipAgency updateDmShipAgency(
		com.fds.nsw.nghiepvu.model.DmShipAgency dmShipAgency, boolean merge)
		throws com.fds.nsw.kernel.exception.SystemException {
		return getService().updateDmShipAgency(dmShipAgency, merge);
	}

	

	

	public static com.fds.nsw.nghiepvu.model.DmShipAgency getByShipAgencyCode(
		java.lang.String shipAgencyCode) {
		return getService().getByShipAgencyCode(shipAgencyCode);
	}

	public static void clearService() {
		_service = null;
	}

	

	

	
}