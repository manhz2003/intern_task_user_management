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
 * The utility for the dm vma security office local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmVmaSecurityOfficeLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmVmaSecurityOfficeLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmVmaSecurityOfficeLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmVmaSecurityOfficeLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmVmaSecurityOfficeLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class DmVmaSecurityOfficeLocalServiceUtil {
    public DmVmaSecurityOfficeLocalServiceUtil(DmVmaSecurityOfficeLocalServiceImpl service) {
        DmVmaSecurityOfficeLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmVmaSecurityOfficeLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the dm vma security office to the database. Also notifies the appropriate model listeners.
     *
     * @param dmVmaSecurityOffice the dm vma security office
     * @return the dm vma security office that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaSecurityOffice addDmVmaSecurityOffice(
            com.fds.nsw.nghiepvu.model.DmVmaSecurityOffice dmVmaSecurityOffice)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addDmVmaSecurityOffice(dmVmaSecurityOffice);
    }

    /**
     * Creates a new dm vma security office with the primary key. Does not add the dm vma security office to the database.
     *
     * @param id the primary key for the new dm vma security office
     * @return the new dm vma security office
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaSecurityOffice createDmVmaSecurityOffice(
            long id) {
        return getService().createDmVmaSecurityOffice(id);
    }

    /**
     * Deletes the dm vma security office with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the dm vma security office
     * @return the dm vma security office that was removed
     * @throws PortalException if a dm vma security office with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaSecurityOffice deleteDmVmaSecurityOffice(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteDmVmaSecurityOffice(id);
    }

    /**
     * Deletes the dm vma security office from the database. Also notifies the appropriate model listeners.
     *
     * @param dmVmaSecurityOffice the dm vma security office
     * @return the dm vma security office that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaSecurityOffice deleteDmVmaSecurityOffice(
            com.fds.nsw.nghiepvu.model.DmVmaSecurityOffice dmVmaSecurityOffice)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteDmVmaSecurityOffice(dmVmaSecurityOffice);
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
   

    public static com.fds.nsw.nghiepvu.model.DmVmaSecurityOffice fetchDmVmaSecurityOffice(
            long id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchDmVmaSecurityOffice(id);
    }

    /**
     * Returns the dm vma security office with the primary key.
     *
     * @param id the primary key of the dm vma security office
     * @return the dm vma security office
     * @throws PortalException if a dm vma security office with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaSecurityOffice getDmVmaSecurityOffice(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmVmaSecurityOffice(id);
    }

    

    /**
     * Returns a range of all the dm vma security offices.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of dm vma security offices
     * @param end the upper bound of the range of dm vma security offices (not inclusive)
     * @return the range of dm vma security offices
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.DmVmaSecurityOffice> getDmVmaSecurityOffices(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmVmaSecurityOffices(start, end);
    }

    /**
     * Returns the number of dm vma security offices.
     *
     * @return the number of dm vma security offices
     * @throws SystemException if a system exception occurred
     */
    public static int getDmVmaSecurityOfficesCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmVmaSecurityOfficesCount();
    }

    /**
     * Updates the dm vma security office in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param dmVmaSecurityOffice the dm vma security office
     * @return the dm vma security office that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaSecurityOffice updateDmVmaSecurityOffice(
            com.fds.nsw.nghiepvu.model.DmVmaSecurityOffice dmVmaSecurityOffice)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateDmVmaSecurityOffice(dmVmaSecurityOffice);
    }

    /**
     * Updates the dm vma security office in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param dmVmaSecurityOffice the dm vma security office
     * @param merge whether to merge the dm vma security office with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the dm vma security office that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaSecurityOffice updateDmVmaSecurityOffice(
            com.fds.nsw.nghiepvu.model.DmVmaSecurityOffice dmVmaSecurityOffice,
            boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateDmVmaSecurityOffice(dmVmaSecurityOffice, merge);
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
   

    

    public static com.fds.nsw.nghiepvu.model.DmVmaSecurityOffice fetchBySecurityOfficeCode(
            java.lang.String securityOfficeCode) {
        return getService().fetchBySecurityOfficeCode(securityOfficeCode);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.DmVmaSecurityOffice> findVmaSecurityOffice(
            java.lang.String maritimeCode, java.lang.String companyName,
            java.lang.String companyAddress, java.lang.String contactEmail,
            java.lang.String telNo, java.lang.String isDelete,
            java.lang.String securityOfficeCodeGroup, int start, int end) {
        return getService()
                .findVmaSecurityOffice(maritimeCode, companyName,
                        companyAddress, contactEmail, telNo, isDelete,
                        securityOfficeCodeGroup, start, end);
    }

    public static long countVmaSecurityOffice(java.lang.String maritimeCode,
                                              java.lang.String companyName, java.lang.String companyAddress,
                                              java.lang.String contactEmail, java.lang.String telNo,
                                              java.lang.String isDelete, java.lang.String securityOfficeCodeGroup) {
        return getService()
                .countVmaSecurityOffice(maritimeCode, companyName,
                        companyAddress, contactEmail, telNo, isDelete,
                        securityOfficeCodeGroup);
    }

    public static com.fds.nsw.nghiepvu.model.DmVmaSecurityOffice updateVmaSecurityOffice(
            java.lang.String fromMaritimeCode, java.lang.String securityOfficeCode,
            java.lang.String companyName, java.lang.String companyAddress,
            java.lang.String contactEmail, java.lang.String faxNo,
            java.lang.String remarks, java.lang.String maritimeCode,
            java.lang.String telNo, java.lang.String syncVersion)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchDmVmaSecurityOfficeException {
        return getService()
                .updateVmaSecurityOffice(fromMaritimeCode,
                        securityOfficeCode, companyName, companyAddress, contactEmail,
                        faxNo, remarks, maritimeCode, telNo, syncVersion);
    }

    public static com.fds.nsw.nghiepvu.model.DmVmaSecurityOffice deleteVmaSecurityOffice(
            java.lang.String fromMaritimeCode, java.lang.String securityOfficeCode,
            java.lang.String syncVersion)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchDmVmaSecurityOfficeException {
        return getService()
                .deleteVmaSecurityOffice(fromMaritimeCode,
                        securityOfficeCode, syncVersion);
    }

    public static void clearService() {
        _service = null;
    }

    public static DmVmaSecurityOfficeLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(DmVmaSecurityOfficeLocalServiceImpl service) {
    }

    private static DmVmaSecurityOfficeLocalServiceImpl _service;
}
