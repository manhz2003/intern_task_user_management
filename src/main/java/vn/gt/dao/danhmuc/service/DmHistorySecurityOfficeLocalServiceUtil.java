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

import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmHistorySecurityLevelLocalServiceImpl;
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmHistorySecurityOfficeLocalServiceImpl;

/**
 * The utility for the dm history security office local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmHistorySecurityOfficeLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmHistorySecurityOfficeLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmHistorySecurityOfficeLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmHistorySecurityOfficeLocalServiceImpl
 * @generated
 */
public class DmHistorySecurityOfficeLocalServiceUtil {

    public DmHistorySecurityOfficeLocalServiceUtil(DmHistorySecurityOfficeLocalServiceImpl service) {
        DmHistorySecurityOfficeLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmHistorySecurityOfficeLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the dm history security office to the database. Also notifies the appropriate model listeners.
     *
     * @param dmHistorySecurityOffice the dm history security office
     * @return the dm history security office that was added
     * @throws SystemException if a system exception occurred
     */

    /**
     * Creates a new dm history security office with the primary key. Does not add the dm history security office to the database.
     *
     * @param id the primary key for the new dm history security office
     * @return the new dm history security office
     */

    /**
     * Deletes the dm history security office with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the dm history security office
     * @return the dm history security office that was removed
     * @throws PortalException if a dm history security office with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */


    /**
     * Deletes the dm history security office from the database. Also notifies the appropriate model listeners.
     *
     * @param dmHistorySecurityOffice the dm history security office
     * @return the dm history security office that was removed
     * @throws SystemException if a system exception occurred
     */


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
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
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
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
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



    /**
     * Returns a range of all the dm history security offices.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of dm history security offices
     * @param end the upper bound of the range of dm history security offices (not inclusive)
     * @return the range of dm history security offices
     * @throws SystemException if a system exception occurred
     */


    /**
     * Returns the number of dm history security offices.
     *
     * @return the number of dm history security offices
     * @throws SystemException if a system exception occurred
     */


    /**
     * Updates the dm history security office in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param dmHistorySecurityOffice the dm history security office
     * @return the dm history security office that was updated
     * @throws SystemException if a system exception occurred
     */


    /**
     * Updates the dm history security office in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param dmHistorySecurityOffice the dm history security office
     * @param merge whether to merge the dm history security office with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the dm history security office that was updated
     * @throws SystemException if a system exception occurred
     */

    /**
     * Returns the Spring bean ID for this bean.
     *
     * @return the Spring bean ID for this bean
     */

    /**
     * Sets the Spring bean ID for this bean.
     *
     * @param beanIdentifier the Spring bean ID for this bean
     */



    public static com.fds.nsw.nghiepvu.model.DmHistorySecurityOffice fetchBySecurityOfficeCode_SyncVersion(
            java.lang.String securityOfficeCode, java.lang.String syncVersion) {
        return getService()
                .fetchBySecurityOfficeCode_SyncVersion(securityOfficeCode,
                        syncVersion);
    }

    public static com.fds.nsw.nghiepvu.model.DmHistorySecurityOffice updateHistorySecurityOffice(
            java.lang.String fromMaritimeCode, java.lang.String securityOfficeCode,
            java.lang.String companyName, java.lang.String companyAddress,
            java.lang.String contactEmail, java.lang.String faxNo,
            java.lang.String remarks, java.lang.String maritimeCode,
            java.lang.String telNo, java.lang.String syncVersion)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchDmHistorySecurityOfficeException {
        return getService()
                .updateHistorySecurityOffice(fromMaritimeCode,
                        securityOfficeCode, companyName, companyAddress, contactEmail,
                        faxNo, remarks, maritimeCode, telNo, syncVersion);
    }

    public static com.fds.nsw.nghiepvu.model.DmHistorySecurityOffice deleteHistorySecurityOffice(
            java.lang.String fromMaritimeCode, java.lang.String securityOfficeCode,
            java.lang.String syncVersion)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchDmHistorySecurityOfficeException {
        return getService()
                .deleteHistorySecurityOffice(fromMaritimeCode,
                        securityOfficeCode, syncVersion);
    }

    public static void clearService() {
        _service = null;
    }

    public static DmHistorySecurityOfficeLocalServiceImpl getService() {
       
        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(DmHistorySecurityOfficeLocalServiceImpl service) {
    }

    private static DmHistorySecurityOfficeLocalServiceImpl _service;
}
