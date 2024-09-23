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

import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmHistoryPilotCategoryLocalServiceImpl;
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmHistoryPilotLocalServiceImpl;

public class DmHistoryPilotLocalServiceUtil {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmHistoryPilotLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */
    public DmHistoryPilotLocalServiceUtil(DmHistoryPilotLocalServiceImpl service) {
        DmHistoryPilotLocalServiceUtil._service = service;
    }

    /**
     * Adds the dm history pilot to the database. Also notifies the appropriate model listeners.
     *
     * @param dmHistoryPilot the dm history pilot
     * @return the dm history pilot that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryPilot addDmHistoryPilot(
            com.fds.nsw.nghiepvu.model.DmHistoryPilot dmHistoryPilot)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addDmHistoryPilot(dmHistoryPilot);
    }

    /**
     * Creates a new dm history pilot with the primary key. Does not add the dm history pilot to the database.
     *
     * @param Id the primary key for the new dm history pilot
     * @return the new dm history pilot
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryPilot createDmHistoryPilot(
            long Id) {
        return getService().createDmHistoryPilot(Id);
    }

    /**
     * Deletes the dm history pilot with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param Id the primary key of the dm history pilot
     * @return the dm history pilot that was removed
     * @throws PortalException if a dm history pilot with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryPilot deleteDmHistoryPilot(
            long Id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteDmHistoryPilot(Id);
    }

    /**
     * Deletes the dm history pilot from the database. Also notifies the appropriate model listeners.
     *
     * @param dmHistoryPilot the dm history pilot
     * @return the dm history pilot that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryPilot deleteDmHistoryPilot(
            com.fds.nsw.nghiepvu.model.DmHistoryPilot dmHistoryPilot)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteDmHistoryPilot(dmHistoryPilot);
    }


    public static com.fds.nsw.nghiepvu.model.DmHistoryPilot fetchDmHistoryPilot(
            long Id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchDmHistoryPilot(Id);
    }

    /**
     * Returns the dm history pilot with the primary key.
     *
     * @param Id the primary key of the dm history pilot
     * @return the dm history pilot
     * @throws PortalException if a dm history pilot with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryPilot getDmHistoryPilot(
            long Id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmHistoryPilot(Id);
    }

    /**
     * Returns a range of all the dm history pilots.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of dm history pilots
     * @param end the upper bound of the range of dm history pilots (not inclusive)
     * @return the range of dm history pilots
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.DmHistoryPilot> getDmHistoryPilots(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmHistoryPilots(start, end);
    }

    /**
     * Returns the number of dm history pilots.
     *
     * @return the number of dm history pilots
     * @throws SystemException if a system exception occurred
     */
    public static int getDmHistoryPilotsCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmHistoryPilotsCount();
    }

    /**
     * Updates the dm history pilot in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param dmHistoryPilot the dm history pilot
     * @return the dm history pilot that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryPilot updateDmHistoryPilot(
            com.fds.nsw.nghiepvu.model.DmHistoryPilot dmHistoryPilot)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateDmHistoryPilot(dmHistoryPilot);
    }

    /**
     * Updates the dm history pilot in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param dmHistoryPilot the dm history pilot
     * @param merge whether to merge the dm history pilot with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the dm history pilot that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryPilot updateDmHistoryPilot(
            com.fds.nsw.nghiepvu.model.DmHistoryPilot dmHistoryPilot, boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateDmHistoryPilot(dmHistoryPilot, merge);
    }


    public static com.fds.nsw.nghiepvu.model.DmHistoryPilot fetchByPilotCode_SyncVersion(
            java.lang.String pilotCode, java.lang.String syncVersion) {
        return getService().fetchByPilotCode_SyncVersion(pilotCode, syncVersion);
    }

    public static com.fds.nsw.nghiepvu.model.DmHistoryPilot updateHistoryPilot(
            java.lang.String fromMaritimeCode, java.lang.String maritimeCode,
            java.lang.String pilotCompanyCode, java.lang.String pilotCompanyName,
            java.lang.String pilotBOD, java.lang.String pilotNo,
            java.lang.String pilotCode, java.lang.String pilotName,
            java.lang.String pilotCertificateNo,
            java.lang.String pilotCategoryCode,
            java.util.Date pilotCertificateDate, java.lang.String remarks,
            java.lang.String syncVersion, java.lang.String pilotShortName,
            java.util.Date pilotExpiredDate)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchDmHistoryPilotException {
        return getService()
                .updateHistoryPilot(fromMaritimeCode, maritimeCode,
                        pilotCompanyCode, pilotCompanyName, pilotBOD, pilotNo, pilotCode,
                        pilotName, pilotCertificateNo, pilotCategoryCode,
                        pilotCertificateDate, remarks, syncVersion, pilotShortName,
                        pilotExpiredDate);
    }

    public static com.fds.nsw.nghiepvu.model.DmHistoryPilot deleteHistoryPilot(
            java.lang.String fromMaritimeCode, java.lang.String pilotCode,
            java.lang.String syncVersion)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchDmHistoryPilotException {
        return getService()
                .deleteHistoryPilot(fromMaritimeCode, pilotCode, syncVersion);
    }

    public static void clearService() {
        _service = null;
    }

    public static DmHistoryPilotLocalServiceImpl getService() {

        return _service;
    }


    private static DmHistoryPilotLocalServiceImpl _service;
}
