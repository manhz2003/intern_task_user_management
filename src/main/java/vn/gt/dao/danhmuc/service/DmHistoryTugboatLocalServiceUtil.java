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

import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmHistoryTugboatLocalServiceImpl;


/**
 * The utility for the dm history tugboat local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmHistoryTugboatLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmHistoryTugboatLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmHistoryTugboatLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmHistoryTugboatLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmHistoryTugboatLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class DmHistoryTugboatLocalServiceUtil {
    public DmHistoryTugboatLocalServiceUtil(DmHistoryTugboatLocalServiceImpl service) {
        DmHistoryTugboatLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmHistoryTugboatLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the dm history tugboat to the database. Also notifies the appropriate model listeners.
     *
     * @param dmHistoryTugboat the dm history tugboat
     * @return the dm history tugboat that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryTugboat addDmHistoryTugboat(
            com.fds.nsw.nghiepvu.model.DmHistoryTugboat dmHistoryTugboat)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addDmHistoryTugboat(dmHistoryTugboat);
    }

    /**
     * Creates a new dm history tugboat with the primary key. Does not add the dm history tugboat to the database.
     *
     * @param Id the primary key for the new dm history tugboat
     * @return the new dm history tugboat
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryTugboat createDmHistoryTugboat(
            long Id) {
        return getService().createDmHistoryTugboat(Id);
    }

    /**
     * Deletes the dm history tugboat with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param Id the primary key of the dm history tugboat
     * @return the dm history tugboat that was removed
     * @throws PortalException if a dm history tugboat with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryTugboat deleteDmHistoryTugboat(
            long Id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteDmHistoryTugboat(Id);
    }

    /**
     * Deletes the dm history tugboat from the database. Also notifies the appropriate model listeners.
     *
     * @param dmHistoryTugboat the dm history tugboat
     * @return the dm history tugboat that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryTugboat deleteDmHistoryTugboat(
            com.fds.nsw.nghiepvu.model.DmHistoryTugboat dmHistoryTugboat)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteDmHistoryTugboat(dmHistoryTugboat);
    }


    /**
     * Performs a dynamic query on the database and returns the matching rows.
     *
     * @param dynamicQuery the dynamic query
     * @return the matching rows
     * @throws SystemException if a system exception occurred
     */

    /**
     * Performs a dynamic query on the database and returns a range of the matching rows.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param dynamicQuery the dynamic query
     * @param start the lower bound of the range of model instances
     * @param end the upper bound of the range of model instances (not inclusive)
     * @return the range of matching rows
     * @throws SystemException if a system exception occurred
     */


    /**
     * Performs a dynamic query on the database and returns an ordered range of the matching rows.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param dynamicQuery the dynamic query
     * @param start the lower bound of the range of model instances
     * @param end the upper bound of the range of model instances (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching rows
     * @throws SystemException if a system exception occurred
     */


    /**
     * Returns the number of rows that match the dynamic query.
     *
     * @param dynamicQuery the dynamic query
     * @return the number of rows that match the dynamic query
     * @throws SystemException if a system exception occurred
     */


    public static com.fds.nsw.nghiepvu.model.DmHistoryTugboat fetchDmHistoryTugboat(
            long Id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchDmHistoryTugboat(Id);
    }

    /**
     * Returns the dm history tugboat with the primary key.
     *
     * @param Id the primary key of the dm history tugboat
     * @return the dm history tugboat
     * @throws PortalException if a dm history tugboat with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryTugboat getDmHistoryTugboat(
            long Id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmHistoryTugboat(Id);
    }



    /**
     * Returns a range of all the dm history tugboats.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of dm history tugboats
     * @param end the upper bound of the range of dm history tugboats (not inclusive)
     * @return the range of dm history tugboats
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.DmHistoryTugboat> getDmHistoryTugboats(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmHistoryTugboats(start, end);
    }

    /**
     * Returns the number of dm history tugboats.
     *
     * @return the number of dm history tugboats
     * @throws SystemException if a system exception occurred
     */
    public static int getDmHistoryTugboatsCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmHistoryTugboatsCount();
    }

    /**
     * Updates the dm history tugboat in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param dmHistoryTugboat the dm history tugboat
     * @return the dm history tugboat that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryTugboat updateDmHistoryTugboat(
            com.fds.nsw.nghiepvu.model.DmHistoryTugboat dmHistoryTugboat)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateDmHistoryTugboat(dmHistoryTugboat);
    }

    /**
     * Updates the dm history tugboat in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param dmHistoryTugboat the dm history tugboat
     * @param merge whether to merge the dm history tugboat with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the dm history tugboat that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryTugboat updateDmHistoryTugboat(
            com.fds.nsw.nghiepvu.model.DmHistoryTugboat dmHistoryTugboat, boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateDmHistoryTugboat(dmHistoryTugboat, merge);
    }

    /**
     * Returns the Spring bean ID for this bean.
     *
     * @return the Spring bean ID for this bean
     */

    public static com.fds.nsw.nghiepvu.model.DmHistoryTugboat fetchByShipCode_SyncVersion(
            java.lang.String shipCode, java.lang.String syncVersion) {
        return getService().fetchByShipCode_SyncVersion(shipCode, syncVersion);
    }

    public static com.fds.nsw.nghiepvu.model.DmHistoryTugboat updateHistoryTugboat(
            java.lang.String fromMaritimeCode, java.lang.String maritimeCode,
            java.lang.String tugboatCompanyCode,
            java.lang.String tugboatCompanyName, java.lang.String shipCode,
            java.lang.String shipName, double power, double loa, double breadth,
            double clearanceHeight, double displacement, java.lang.String remarks,
            java.lang.String unitPower, double vndUnitPrice, double usdUnitPrice,
            int gt, int nt, int dwt, java.lang.String unitGRT,
            java.lang.String unitNT, java.lang.String unitDWT,
            java.lang.String syncVersion, java.lang.String tugboatShortName,
            java.util.Date tugboatExpiredDate)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchDmHistoryTugboatException {
        return getService()
                .updateHistoryTugboat(fromMaritimeCode, maritimeCode,
                        tugboatCompanyCode, tugboatCompanyName, shipCode, shipName, power,
                        loa, breadth, clearanceHeight, displacement, remarks, unitPower,
                        vndUnitPrice, usdUnitPrice, gt, nt, dwt, unitGRT, unitNT, unitDWT,
                        syncVersion, tugboatShortName, tugboatExpiredDate);
    }

    public static com.fds.nsw.nghiepvu.model.DmHistoryTugboat deleteHistoryTugboat(
            java.lang.String fromMaritimeCode, java.lang.String shipCode,
            java.lang.String syncVersion)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchDmHistoryTugboatException {
        return getService()
                .deleteHistoryTugboat(fromMaritimeCode, shipCode, syncVersion);
    }

    public static void clearService() {
        _service = null;
    }

    public static DmHistoryTugboatLocalServiceImpl getService() {

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(DmHistoryTugboatLocalServiceImpl service) {
    }

    private static DmHistoryTugboatLocalServiceImpl _service;
}
