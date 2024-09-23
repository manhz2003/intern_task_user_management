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

import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmArrivalPurposeLocalServiceImpl;
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmHistoryShipOwnerLocalServiceImpl;


/**
 * The utility for the dm history ship owner local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmHistoryShipOwnerLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmHistoryShipOwnerLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmHistoryShipOwnerLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmHistoryShipOwnerLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmHistoryShipOwnerLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class DmHistoryShipOwnerLocalServiceUtil {

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmHistoryShipOwnerLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the dm history ship owner to the database. Also notifies the appropriate model listeners.
     *
     * @param dmHistoryShipOwner the dm history ship owner
     * @return the dm history ship owner that was added
     * @throws SystemException if a system exception occurred
     */

    public DmHistoryShipOwnerLocalServiceUtil(DmHistoryShipOwnerLocalServiceImpl service) {
        DmHistoryShipOwnerLocalServiceUtil._service = service;
    }

    public static com.fds.nsw.nghiepvu.model.DmHistoryShipOwner addDmHistoryShipOwner(
            com.fds.nsw.nghiepvu.model.DmHistoryShipOwner dmHistoryShipOwner)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addDmHistoryShipOwner(dmHistoryShipOwner);
    }

    /**
     * Creates a new dm history ship owner with the primary key. Does not add the dm history ship owner to the database.
     *
     * @param Id the primary key for the new dm history ship owner
     * @return the new dm history ship owner
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryShipOwner createDmHistoryShipOwner(
            long Id) {
        return getService().createDmHistoryShipOwner(Id);
    }

    /**
     * Deletes the dm history ship owner with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param Id the primary key of the dm history ship owner
     * @return the dm history ship owner that was removed
     * @throws PortalException if a dm history ship owner with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryShipOwner deleteDmHistoryShipOwner(
            long Id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteDmHistoryShipOwner(Id);
    }

    /**
     * Deletes the dm history ship owner from the database. Also notifies the appropriate model listeners.
     *
     * @param dmHistoryShipOwner the dm history ship owner
     * @return the dm history ship owner that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryShipOwner deleteDmHistoryShipOwner(
            com.fds.nsw.nghiepvu.model.DmHistoryShipOwner dmHistoryShipOwner)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteDmHistoryShipOwner(dmHistoryShipOwner);
    }

    public static com.fds.nsw.nghiepvu.model.DmHistoryShipOwner fetchDmHistoryShipOwner(
            long Id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchDmHistoryShipOwner(Id);
    }

    /**
     * Returns the dm history ship owner with the primary key.
     *
     * @param Id the primary key of the dm history ship owner
     * @return the dm history ship owner
     * @throws PortalException if a dm history ship owner with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryShipOwner getDmHistoryShipOwner(
            long Id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmHistoryShipOwner(Id);
    }

    

    /**
     * Returns a range of all the dm history ship owners.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of dm history ship owners
     * @param end the upper bound of the range of dm history ship owners (not inclusive)
     * @return the range of dm history ship owners
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.DmHistoryShipOwner> getDmHistoryShipOwners(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmHistoryShipOwners(start, end);
    }

    /**
     * Returns the number of dm history ship owners.
     *
     * @return the number of dm history ship owners
     * @throws SystemException if a system exception occurred
     */
    public static int getDmHistoryShipOwnersCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmHistoryShipOwnersCount();
    }

    /**
     * Updates the dm history ship owner in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param dmHistoryShipOwner the dm history ship owner
     * @return the dm history ship owner that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryShipOwner updateDmHistoryShipOwner(
            com.fds.nsw.nghiepvu.model.DmHistoryShipOwner dmHistoryShipOwner)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateDmHistoryShipOwner(dmHistoryShipOwner);
    }

    /**
     * Updates the dm history ship owner in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param dmHistoryShipOwner the dm history ship owner
     * @param merge whether to merge the dm history ship owner with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the dm history ship owner that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryShipOwner updateDmHistoryShipOwner(
            com.fds.nsw.nghiepvu.model.DmHistoryShipOwner dmHistoryShipOwner,
            boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateDmHistoryShipOwner(dmHistoryShipOwner, merge);
    }


    public static com.fds.nsw.nghiepvu.model.DmHistoryShipOwner fetchByshipOwnerCode_syncVersion(
            java.lang.String shipOwnerCode, java.lang.String syncVersion) {
        return getService()
                .fetchByshipOwnerCode_syncVersion(shipOwnerCode, syncVersion);
    }

    public static com.fds.nsw.nghiepvu.model.DmHistoryShipOwner updateHistoryShipOwner(
            java.lang.String fromMaritimeCode, java.lang.String maritimeCode,
            java.lang.String shipOwnerCode, java.lang.String taxCode,
            java.lang.String companyName, java.lang.String companyAddress,
            java.lang.String contactEmail, java.lang.String telNo,
            java.lang.String faxNo, int isShipOwner, int isShipOperator,
            java.lang.String remarks, java.lang.String syncVersion,
            java.lang.String companyShortName, int isOther)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .updateHistoryShipOwner(fromMaritimeCode, maritimeCode,
                        shipOwnerCode, taxCode, companyName, companyAddress, contactEmail,
                        telNo, faxNo, isShipOwner, isShipOperator, remarks, syncVersion,
                        companyShortName, isOther);
    }

    public static com.fds.nsw.nghiepvu.model.DmHistoryShipOwner deleteHistoryShipOwner(
            java.lang.String fromMaritimeCode, java.lang.String shipOwnerCode,
            java.lang.String syncVersion)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .deleteHistoryShipOwner(fromMaritimeCode, shipOwnerCode,
                        syncVersion);
    }

    public static void clearService() {
        _service = null;
    }

    public static DmHistoryShipOwnerLocalServiceImpl getService() {

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(DmHistoryShipOwnerLocalServiceImpl service) {
    }

    private static DmHistoryShipOwnerLocalServiceImpl _service;
}
