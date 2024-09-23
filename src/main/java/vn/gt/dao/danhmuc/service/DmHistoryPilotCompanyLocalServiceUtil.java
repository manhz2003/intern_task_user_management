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

import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmHistoryPilotCompanyLocalServiceImpl;

public class DmHistoryPilotCompanyLocalServiceUtil {
    public DmHistoryPilotCompanyLocalServiceUtil(DmHistoryPilotCompanyLocalServiceImpl service) {
        DmHistoryPilotCompanyLocalServiceUtil._service = service;
    }

    /**
     * Adds the dm history pilot company to the database. Also notifies the appropriate model listeners.
     *
     * @param dmHistoryPilotCompany the dm history pilot company
     * @return the dm history pilot company that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryPilotCompany addDmHistoryPilotCompany(
            com.fds.nsw.nghiepvu.model.DmHistoryPilotCompany dmHistoryPilotCompany)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addDmHistoryPilotCompany(dmHistoryPilotCompany);
    }

    /**
     * Creates a new dm history pilot company with the primary key. Does not add the dm history pilot company to the database.
     *
     * @param Id the primary key for the new dm history pilot company
     * @return the new dm history pilot company
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryPilotCompany createDmHistoryPilotCompany(
            long Id) {
        return getService().createDmHistoryPilotCompany(Id);
    }

    /**
     * Deletes the dm history pilot company with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param Id the primary key of the dm history pilot company
     * @return the dm history pilot company that was removed
     * @throws PortalException if a dm history pilot company with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryPilotCompany deleteDmHistoryPilotCompany(
            long Id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteDmHistoryPilotCompany(Id);
    }

    /**
     * Deletes the dm history pilot company from the database. Also notifies the appropriate model listeners.
     *
     * @param dmHistoryPilotCompany the dm history pilot company
     * @return the dm history pilot company that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryPilotCompany deleteDmHistoryPilotCompany(
            com.fds.nsw.nghiepvu.model.DmHistoryPilotCompany dmHistoryPilotCompany)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteDmHistoryPilotCompany(dmHistoryPilotCompany);
    }

    public static com.fds.nsw.nghiepvu.model.DmHistoryPilotCompany fetchDmHistoryPilotCompany(
            long Id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchDmHistoryPilotCompany(Id);
    }

    /**
     * Returns the dm history pilot company with the primary key.
     *
     * @param Id the primary key of the dm history pilot company
     * @return the dm history pilot company
     * @throws PortalException if a dm history pilot company with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryPilotCompany getDmHistoryPilotCompany(
            long Id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmHistoryPilotCompany(Id);
    }

    /**
     * Returns a range of all the dm history pilot companies.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of dm history pilot companies
     * @param end the upper bound of the range of dm history pilot companies (not inclusive)
     * @return the range of dm history pilot companies
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.DmHistoryPilotCompany> getDmHistoryPilotCompanies(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmHistoryPilotCompanies(start, end);
    }

    /**
     * Returns the number of dm history pilot companies.
     *
     * @return the number of dm history pilot companies
     * @throws SystemException if a system exception occurred
     */
    public static int getDmHistoryPilotCompaniesCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmHistoryPilotCompaniesCount();
    }

    /**
     * Updates the dm history pilot company in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param dmHistoryPilotCompany the dm history pilot company
     * @return the dm history pilot company that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryPilotCompany updateDmHistoryPilotCompany(
            com.fds.nsw.nghiepvu.model.DmHistoryPilotCompany dmHistoryPilotCompany)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateDmHistoryPilotCompany(dmHistoryPilotCompany);
    }

    /**
     * Updates the dm history pilot company in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param dmHistoryPilotCompany the dm history pilot company
     * @param merge whether to merge the dm history pilot company with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the dm history pilot company that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryPilotCompany updateDmHistoryPilotCompany(
            com.fds.nsw.nghiepvu.model.DmHistoryPilotCompany dmHistoryPilotCompany,
            boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .updateDmHistoryPilotCompany(dmHistoryPilotCompany, merge);
    }


    public static com.fds.nsw.nghiepvu.model.DmHistoryPilotCompany fetchByPilotCompanyCode_SyncVersion(
            java.lang.String pilotCompanyCode, java.lang.String syncVersion) {
        return getService()
                .fetchByPilotCompanyCode_SyncVersion(pilotCompanyCode,
                        syncVersion);
    }

    public static com.fds.nsw.nghiepvu.model.DmHistoryPilotCompany updateHistoryPilotCompany(
            java.lang.String fromMaritimeCode, java.lang.String maritimeCode,
            java.lang.String pilotCompanyCode, java.lang.String pilotCompanyName,
            java.lang.String telNo, java.lang.String faxNo,
            java.lang.String contactEmail, java.lang.String companyAddress,
            java.lang.String remarks, java.lang.String syncVersion,
            java.lang.String companyShortName)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchDmHistoryPilotCompanyException {
        return getService()
                .updateHistoryPilotCompany(fromMaritimeCode, maritimeCode,
                        pilotCompanyCode, pilotCompanyName, telNo, faxNo, contactEmail,
                        companyAddress, remarks, syncVersion, companyShortName);
    }

    public static com.fds.nsw.nghiepvu.model.DmHistoryPilotCompany deleteHistoryPilotCompany(
            java.lang.String fromMaritimeCode, java.lang.String pilotCompanyCode,
            java.lang.String syncVersion)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchDmHistoryPilotCompanyException {
        return getService()
                .deleteHistoryPilotCompany(fromMaritimeCode,
                        pilotCompanyCode, syncVersion);
    }

    public static void clearService() {
        _service = null;
    }

    public static DmHistoryPilotCompanyLocalServiceImpl getService() {


        return _service;
    }


    private static DmHistoryPilotCompanyLocalServiceImpl _service;
}
