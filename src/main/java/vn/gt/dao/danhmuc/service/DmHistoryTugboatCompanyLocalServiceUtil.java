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

import com.fds.nsw.liferay.service.exception.NoSuchUserException;
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmHistoryTugboatCompanyLocalServiceImpl;
import org.springframework.stereotype.Component;

/**
 * The utility for the dm history tugboat company local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmHistoryTugboatCompanyLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmHistoryTugboatCompanyLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmHistoryTugboatCompanyLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmHistoryTugboatCompanyLocalServiceImpl
 * @generated
 */
@Component
public class DmHistoryTugboatCompanyLocalServiceUtil {

    public DmHistoryTugboatCompanyLocalServiceUtil(DmHistoryTugboatCompanyLocalServiceImpl service) {
        DmHistoryTugboatCompanyLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmHistoryTugboatCompanyLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the dm history tugboat company to the database. Also notifies the appropriate model listeners.
     *
     * @param dmHistoryTugboatCompany the dm history tugboat company
     * @return the dm history tugboat company that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryTugboatCompany addDmHistoryTugboatCompany(
            com.fds.nsw.nghiepvu.model.DmHistoryTugboatCompany dmHistoryTugboatCompany)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addDmHistoryTugboatCompany(dmHistoryTugboatCompany);
    }

    /**
     * Creates a new dm history tugboat company with the primary key. Does not add the dm history tugboat company to the database.
     *
     * @param Id the primary key for the new dm history tugboat company
     * @return the new dm history tugboat company
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryTugboatCompany createDmHistoryTugboatCompany(
            long Id) {
        return getService().createDmHistoryTugboatCompany(Id);
    }

    /**
     * Deletes the dm history tugboat company with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param Id the primary key of the dm history tugboat company
     * @return the dm history tugboat company that was removed
     * @throws PortalException if a dm history tugboat company with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryTugboatCompany deleteDmHistoryTugboatCompany(
            long Id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteDmHistoryTugboatCompany(Id);
    }

    /**
     * Deletes the dm history tugboat company from the database. Also notifies the appropriate model listeners.
     *
     * @param dmHistoryTugboatCompany the dm history tugboat company
     * @return the dm history tugboat company that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryTugboatCompany deleteDmHistoryTugboatCompany(
            com.fds.nsw.nghiepvu.model.DmHistoryTugboatCompany dmHistoryTugboatCompany)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .deleteDmHistoryTugboatCompany(dmHistoryTugboatCompany);
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
   

    public static com.fds.nsw.nghiepvu.model.DmHistoryTugboatCompany fetchDmHistoryTugboatCompany(
            long Id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchDmHistoryTugboatCompany(Id);
    }

    /**
     * Returns the dm history tugboat company with the primary key.
     *
     * @param Id the primary key of the dm history tugboat company
     * @return the dm history tugboat company
     * @throws PortalException if a dm history tugboat company with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryTugboatCompany getDmHistoryTugboatCompany(
            long Id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmHistoryTugboatCompany(Id);
    }

    

    /**
     * Returns a range of all the dm history tugboat companies.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of dm history tugboat companies
     * @param end the upper bound of the range of dm history tugboat companies (not inclusive)
     * @return the range of dm history tugboat companies
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.DmHistoryTugboatCompany> getDmHistoryTugboatCompanies(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmHistoryTugboatCompanies(start, end);
    }

    /**
     * Returns the number of dm history tugboat companies.
     *
     * @return the number of dm history tugboat companies
     * @throws SystemException if a system exception occurred
     */
    public static int getDmHistoryTugboatCompaniesCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmHistoryTugboatCompaniesCount();
    }

    /**
     * Updates the dm history tugboat company in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param dmHistoryTugboatCompany the dm history tugboat company
     * @return the dm history tugboat company that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryTugboatCompany updateDmHistoryTugboatCompany(
            com.fds.nsw.nghiepvu.model.DmHistoryTugboatCompany dmHistoryTugboatCompany)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .updateDmHistoryTugboatCompany(dmHistoryTugboatCompany);
    }

    /**
     * Updates the dm history tugboat company in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param dmHistoryTugboatCompany the dm history tugboat company
     * @param merge whether to merge the dm history tugboat company with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the dm history tugboat company that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryTugboatCompany updateDmHistoryTugboatCompany(
            com.fds.nsw.nghiepvu.model.DmHistoryTugboatCompany dmHistoryTugboatCompany,
            boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .updateDmHistoryTugboatCompany(dmHistoryTugboatCompany, merge);
    }

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
   

    

    public static com.fds.nsw.nghiepvu.model.DmHistoryTugboatCompany fetchByTugboatCompanyCode_SyncVersion(
            java.lang.String tugboatCompanyCode, java.lang.String syncVersion) {
        return getService()
                .fetchByTugboatCompanyCode_SyncVersion(tugboatCompanyCode,
                        syncVersion);
    }

    public static com.fds.nsw.nghiepvu.model.DmHistoryTugboatCompany updateHistoryTugboatCompany(
            java.lang.String fromMaritimeCode, java.lang.String maritimeCode,
            java.lang.String tugboatCompanyCode,
            java.lang.String tugboatCompanyName, java.lang.String companyAddress,
            java.lang.String contactEmail, java.lang.String telNo,
            java.lang.String faxNo, java.lang.String remarks,
            java.lang.String syncVersion, java.lang.String companyShortName)
            throws NoSuchUserException,
            com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchDmHistoryTugboatCompanyException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchDmVmaTugboatCompanyException {
        return getService()
                .updateHistoryTugboatCompany(fromMaritimeCode, maritimeCode,
                        tugboatCompanyCode, tugboatCompanyName, companyAddress,
                        contactEmail, telNo, faxNo, remarks, syncVersion, companyShortName);
    }

    public static com.fds.nsw.nghiepvu.model.DmHistoryTugboatCompany deleteHistoryTugboatCompany(
            java.lang.String fromMaritimeCode, java.lang.String tugboatCompanyCode,
            java.lang.String syncVersion)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchDmHistoryTugboatCompanyException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchDmVmaTugboatCompanyException {
        return getService()
                .deleteHistoryTugboatCompany(fromMaritimeCode,
                        tugboatCompanyCode, syncVersion);
    }

    public static void clearService() {
        _service = null;
    }

    public static DmHistoryTugboatCompanyLocalServiceImpl getService() {
        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(DmHistoryTugboatCompanyLocalServiceImpl service) {
    }

    private static DmHistoryTugboatCompanyLocalServiceImpl _service;
}
