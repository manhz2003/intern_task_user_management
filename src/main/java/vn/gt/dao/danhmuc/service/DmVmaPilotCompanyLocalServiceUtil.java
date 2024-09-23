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
 * The utility for the dm vma pilot company local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmVmaPilotCompanyLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmVmaPilotCompanyLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmVmaPilotCompanyLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmVmaPilotCompanyLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmVmaPilotCompanyLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class DmVmaPilotCompanyLocalServiceUtil {
    public DmVmaPilotCompanyLocalServiceUtil(DmVmaPilotCompanyLocalServiceImpl service) {
        DmVmaPilotCompanyLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmVmaPilotCompanyLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the dm vma pilot company to the database. Also notifies the appropriate model listeners.
     *
     * @param dmVmaPilotCompany the dm vma pilot company
     * @return the dm vma pilot company that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaPilotCompany addDmVmaPilotCompany(
            com.fds.nsw.nghiepvu.model.DmVmaPilotCompany dmVmaPilotCompany)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addDmVmaPilotCompany(dmVmaPilotCompany);
    }

    /**
     * Creates a new dm vma pilot company with the primary key. Does not add the dm vma pilot company to the database.
     *
     * @param Id the primary key for the new dm vma pilot company
     * @return the new dm vma pilot company
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaPilotCompany createDmVmaPilotCompany(
            long Id) {
        return getService().createDmVmaPilotCompany(Id);
    }

    /**
     * Deletes the dm vma pilot company with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param Id the primary key of the dm vma pilot company
     * @return the dm vma pilot company that was removed
     * @throws PortalException if a dm vma pilot company with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaPilotCompany deleteDmVmaPilotCompany(
            long Id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteDmVmaPilotCompany(Id);
    }

    /**
     * Deletes the dm vma pilot company from the database. Also notifies the appropriate model listeners.
     *
     * @param dmVmaPilotCompany the dm vma pilot company
     * @return the dm vma pilot company that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaPilotCompany deleteDmVmaPilotCompany(
            com.fds.nsw.nghiepvu.model.DmVmaPilotCompany dmVmaPilotCompany)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteDmVmaPilotCompany(dmVmaPilotCompany);
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
   

    public static com.fds.nsw.nghiepvu.model.DmVmaPilotCompany fetchDmVmaPilotCompany(
            long Id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchDmVmaPilotCompany(Id);
    }

    /**
     * Returns the dm vma pilot company with the primary key.
     *
     * @param Id the primary key of the dm vma pilot company
     * @return the dm vma pilot company
     * @throws PortalException if a dm vma pilot company with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaPilotCompany getDmVmaPilotCompany(
            long Id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmVmaPilotCompany(Id);
    }

    

    /**
     * Returns a range of all the dm vma pilot companies.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of dm vma pilot companies
     * @param end the upper bound of the range of dm vma pilot companies (not inclusive)
     * @return the range of dm vma pilot companies
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.DmVmaPilotCompany> getDmVmaPilotCompanies(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmVmaPilotCompanies(start, end);
    }

    /**
     * Returns the number of dm vma pilot companies.
     *
     * @return the number of dm vma pilot companies
     * @throws SystemException if a system exception occurred
     */
    public static int getDmVmaPilotCompaniesCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmVmaPilotCompaniesCount();
    }

    /**
     * Updates the dm vma pilot company in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param dmVmaPilotCompany the dm vma pilot company
     * @return the dm vma pilot company that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaPilotCompany updateDmVmaPilotCompany(
            com.fds.nsw.nghiepvu.model.DmVmaPilotCompany dmVmaPilotCompany)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateDmVmaPilotCompany(dmVmaPilotCompany);
    }

    /**
     * Updates the dm vma pilot company in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param dmVmaPilotCompany the dm vma pilot company
     * @param merge whether to merge the dm vma pilot company with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the dm vma pilot company that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaPilotCompany updateDmVmaPilotCompany(
            com.fds.nsw.nghiepvu.model.DmVmaPilotCompany dmVmaPilotCompany,
            boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateDmVmaPilotCompany(dmVmaPilotCompany, merge);
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
   

    

    public static java.util.List<com.fds.nsw.nghiepvu.model.DmVmaPilotCompany> findByMaritimeCode(
            java.lang.String maritimeCode, int start, int end) {
        return getService().findByMaritimeCode(maritimeCode, start, end);
    }

    public static com.fds.nsw.nghiepvu.model.DmVmaPilotCompany fetchByPilotCompanyCode(
            java.lang.String pilotCompanyCode) {
        return getService().fetchByPilotCompanyCode(pilotCompanyCode);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.DmVmaPilotCompany> findPilotCompanies(
            java.lang.String maritimeCode, java.lang.String pilotCompanyName,
            java.lang.String companyAddress, java.lang.String contactEmail,
            java.lang.String telNo, java.lang.String isDelete,
            java.lang.String pilotCompanyCodeGroup, int start, int end) {
        return getService()
                .findPilotCompanies(maritimeCode, pilotCompanyName,
                        companyAddress, contactEmail, telNo, isDelete,
                        pilotCompanyCodeGroup, start, end);
    }

    public static long countPilotCompanies(java.lang.String maritimeCode,
                                           java.lang.String pilotCompanyName, java.lang.String companyAddress,
                                           java.lang.String contactEmail, java.lang.String telNo,
                                           java.lang.String isDelete, java.lang.String pilotCompanyCodeGroup) {
        return getService()
                .countPilotCompanies(maritimeCode, pilotCompanyName,
                        companyAddress, contactEmail, telNo, isDelete, pilotCompanyCodeGroup);
    }

    public static com.fds.nsw.nghiepvu.model.DmVmaPilotCompany updatePilotCompany(
            java.lang.String fromMaritimeCode, java.lang.String maritimeCode,
            java.lang.String pilotCompanyCode, java.lang.String pilotCompanyName,
            java.lang.String telNo, java.lang.String faxNo,
            java.lang.String contactEmail, java.lang.String companyAddress,
            java.lang.String remarks, java.lang.String syncVersion,
            java.lang.String companyShortName)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchDmVmaPilotCompanyException {
        return getService()
                .updatePilotCompany(fromMaritimeCode, maritimeCode,
                        pilotCompanyCode, pilotCompanyName, telNo, faxNo, contactEmail,
                        companyAddress, remarks, syncVersion, companyShortName);
    }

    public static com.fds.nsw.nghiepvu.model.DmVmaPilotCompany deletePilotCompany(
            java.lang.String fromMaritimeCode, java.lang.String pilotCompanyCode,
            java.lang.String syncVersion)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchDmVmaPilotCompanyException {
        return getService()
                .deletePilotCompany(fromMaritimeCode, pilotCompanyCode,
                        syncVersion);
    }

    public static void clearService() {
        _service = null;
    }

    public static DmVmaPilotCompanyLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(DmVmaPilotCompanyLocalServiceImpl service) {
    }

    private static DmVmaPilotCompanyLocalServiceImpl _service;
}
