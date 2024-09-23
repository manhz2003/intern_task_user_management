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
 * The utility for the dm vma tugboat company local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmVmaTugboatCompanyLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmVmaTugboatCompanyLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmVmaTugboatCompanyLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmVmaTugboatCompanyLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmVmaTugboatCompanyLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class DmVmaTugboatCompanyLocalServiceUtil {
    public DmVmaTugboatCompanyLocalServiceUtil(DmVmaTugboatCompanyLocalServiceImpl service) {
        DmVmaTugboatCompanyLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmVmaTugboatCompanyLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the dm vma tugboat company to the database. Also notifies the appropriate model listeners.
     *
     * @param dmVmaTugboatCompany the dm vma tugboat company
     * @return the dm vma tugboat company that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaTugboatCompany addDmVmaTugboatCompany(
            com.fds.nsw.nghiepvu.model.DmVmaTugboatCompany dmVmaTugboatCompany)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addDmVmaTugboatCompany(dmVmaTugboatCompany);
    }

    /**
     * Creates a new dm vma tugboat company with the primary key. Does not add the dm vma tugboat company to the database.
     *
     * @param Id the primary key for the new dm vma tugboat company
     * @return the new dm vma tugboat company
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaTugboatCompany createDmVmaTugboatCompany(
            long Id) {
        return getService().createDmVmaTugboatCompany(Id);
    }

    /**
     * Deletes the dm vma tugboat company with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param Id the primary key of the dm vma tugboat company
     * @return the dm vma tugboat company that was removed
     * @throws PortalException if a dm vma tugboat company with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaTugboatCompany deleteDmVmaTugboatCompany(
            long Id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteDmVmaTugboatCompany(Id);
    }

    /**
     * Deletes the dm vma tugboat company from the database. Also notifies the appropriate model listeners.
     *
     * @param dmVmaTugboatCompany the dm vma tugboat company
     * @return the dm vma tugboat company that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaTugboatCompany deleteDmVmaTugboatCompany(
            com.fds.nsw.nghiepvu.model.DmVmaTugboatCompany dmVmaTugboatCompany)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteDmVmaTugboatCompany(dmVmaTugboatCompany);
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
   

    public static com.fds.nsw.nghiepvu.model.DmVmaTugboatCompany fetchDmVmaTugboatCompany(
            long Id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchDmVmaTugboatCompany(Id);
    }

    /**
     * Returns the dm vma tugboat company with the primary key.
     *
     * @param Id the primary key of the dm vma tugboat company
     * @return the dm vma tugboat company
     * @throws PortalException if a dm vma tugboat company with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaTugboatCompany getDmVmaTugboatCompany(
            long Id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmVmaTugboatCompany(Id);
    }

    

    /**
     * Returns a range of all the dm vma tugboat companies.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of dm vma tugboat companies
     * @param end the upper bound of the range of dm vma tugboat companies (not inclusive)
     * @return the range of dm vma tugboat companies
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.DmVmaTugboatCompany> getDmVmaTugboatCompanies(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmVmaTugboatCompanies(start, end);
    }

    /**
     * Returns the number of dm vma tugboat companies.
     *
     * @return the number of dm vma tugboat companies
     * @throws SystemException if a system exception occurred
     */
    public static int getDmVmaTugboatCompaniesCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmVmaTugboatCompaniesCount();
    }

    /**
     * Updates the dm vma tugboat company in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param dmVmaTugboatCompany the dm vma tugboat company
     * @return the dm vma tugboat company that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaTugboatCompany updateDmVmaTugboatCompany(
            com.fds.nsw.nghiepvu.model.DmVmaTugboatCompany dmVmaTugboatCompany)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateDmVmaTugboatCompany(dmVmaTugboatCompany);
    }

    /**
     * Updates the dm vma tugboat company in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param dmVmaTugboatCompany the dm vma tugboat company
     * @param merge whether to merge the dm vma tugboat company with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the dm vma tugboat company that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaTugboatCompany updateDmVmaTugboatCompany(
            com.fds.nsw.nghiepvu.model.DmVmaTugboatCompany dmVmaTugboatCompany,
            boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateDmVmaTugboatCompany(dmVmaTugboatCompany, merge);
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
   

    

    public static java.util.List<com.fds.nsw.nghiepvu.model.DmVmaTugboatCompany> findByMaritimeCode(
            java.lang.String maritimeCode, int start, int end) {
        return getService().findByMaritimeCode(maritimeCode, start, end);
    }

    public static com.fds.nsw.nghiepvu.model.DmVmaTugboatCompany fetchByTugboatCompanyCode(
            java.lang.String tugboatCompanyCode) {
        return getService().fetchByTugboatCompanyCode(tugboatCompanyCode);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.DmVmaTugboatCompany> findVmaTugboatCompanies(
            java.lang.String maritimeCode, java.lang.String tugboatCompanyName,
            java.lang.String companyAddress, java.lang.String contactEmail,
            java.lang.String telNo, java.lang.String isDelete,
            java.lang.String tugboatCompanyCodeGroup, int start, int end) {
        return getService()
                .findVmaTugboatCompanies(maritimeCode, tugboatCompanyName,
                        companyAddress, contactEmail, telNo, isDelete,
                        tugboatCompanyCodeGroup, start, end);
    }

    public static long countVmaTugboatCompanies(java.lang.String maritimeCode,
                                                java.lang.String tugboatCompanyName, java.lang.String companyAddress,
                                                java.lang.String contactEmail, java.lang.String telNo,
                                                java.lang.String isDelete, java.lang.String tugboatCompanyCodeGroup) {
        return getService()
                .countVmaTugboatCompanies(maritimeCode, tugboatCompanyName,
                        companyAddress, contactEmail, telNo, isDelete,
                        tugboatCompanyCodeGroup);
    }

    public static com.fds.nsw.nghiepvu.model.DmVmaTugboatCompany updateVmaTugboatCompany(
            java.lang.String fromMaritimeCode, java.lang.String maritimeCode,
            java.lang.String tugboatCompanyCode,
            java.lang.String tugboatCompanyName, java.lang.String companyAddress,
            java.lang.String contactEmail, java.lang.String telNo,
            java.lang.String faxNo, java.lang.String remarks,
            java.lang.String syncVersion, java.lang.String companyShortName)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchDmVmaTugboatCompanyException {
        return getService()
                .updateVmaTugboatCompany(fromMaritimeCode, maritimeCode,
                        tugboatCompanyCode, tugboatCompanyName, companyAddress,
                        contactEmail, telNo, faxNo, remarks, syncVersion, companyShortName);
    }

    public static com.fds.nsw.nghiepvu.model.DmVmaTugboatCompany deleteVmaTugboatCompany(
            java.lang.String fromMaritimeCode, java.lang.String tugboatCompanyCode,
            java.lang.String syncVersion)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchDmVmaTugboatCompanyException {
        return getService()
                .deleteVmaTugboatCompany(fromMaritimeCode,
                        tugboatCompanyCode, syncVersion);
    }

    public static void clearService() {
        _service = null;
    }

    public static DmVmaTugboatCompanyLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(DmVmaTugboatCompanyLocalServiceImpl service) {
    }

    private static DmVmaTugboatCompanyLocalServiceImpl _service;
}
