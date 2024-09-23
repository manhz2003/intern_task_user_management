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
 * The utility for the dm vma ship owner local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmVmaShipOwnerLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmVmaShipOwnerLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmVmaShipOwnerLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmVmaShipOwnerLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmVmaShipOwnerLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class DmVmaShipOwnerLocalServiceUtil {
    public DmVmaShipOwnerLocalServiceUtil(DmVmaShipOwnerLocalServiceImpl service) {
        DmVmaShipOwnerLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmVmaShipOwnerLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the dm vma ship owner to the database. Also notifies the appropriate model listeners.
     *
     * @param dmVmaShipOwner the dm vma ship owner
     * @return the dm vma ship owner that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaShipOwner addDmVmaShipOwner(
            com.fds.nsw.nghiepvu.model.DmVmaShipOwner dmVmaShipOwner)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addDmVmaShipOwner(dmVmaShipOwner);
    }

    /**
     * Creates a new dm vma ship owner with the primary key. Does not add the dm vma ship owner to the database.
     *
     * @param Id the primary key for the new dm vma ship owner
     * @return the new dm vma ship owner
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaShipOwner createDmVmaShipOwner(
            long Id) {
        return getService().createDmVmaShipOwner(Id);
    }

    /**
     * Deletes the dm vma ship owner with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param Id the primary key of the dm vma ship owner
     * @return the dm vma ship owner that was removed
     * @throws PortalException if a dm vma ship owner with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaShipOwner deleteDmVmaShipOwner(
            long Id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteDmVmaShipOwner(Id);
    }

    /**
     * Deletes the dm vma ship owner from the database. Also notifies the appropriate model listeners.
     *
     * @param dmVmaShipOwner the dm vma ship owner
     * @return the dm vma ship owner that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaShipOwner deleteDmVmaShipOwner(
            com.fds.nsw.nghiepvu.model.DmVmaShipOwner dmVmaShipOwner)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteDmVmaShipOwner(dmVmaShipOwner);
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
   

    public static com.fds.nsw.nghiepvu.model.DmVmaShipOwner fetchDmVmaShipOwner(
            long Id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchDmVmaShipOwner(Id);
    }

    /**
     * Returns the dm vma ship owner with the primary key.
     *
     * @param Id the primary key of the dm vma ship owner
     * @return the dm vma ship owner
     * @throws PortalException if a dm vma ship owner with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaShipOwner getDmVmaShipOwner(
            long Id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmVmaShipOwner(Id);
    }

    

    /**
     * Returns a range of all the dm vma ship owners.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of dm vma ship owners
     * @param end the upper bound of the range of dm vma ship owners (not inclusive)
     * @return the range of dm vma ship owners
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.DmVmaShipOwner> getDmVmaShipOwners(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmVmaShipOwners(start, end);
    }

    /**
     * Returns the number of dm vma ship owners.
     *
     * @return the number of dm vma ship owners
     * @throws SystemException if a system exception occurred
     */
    public static int getDmVmaShipOwnersCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmVmaShipOwnersCount();
    }

    /**
     * Updates the dm vma ship owner in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param dmVmaShipOwner the dm vma ship owner
     * @return the dm vma ship owner that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaShipOwner updateDmVmaShipOwner(
            com.fds.nsw.nghiepvu.model.DmVmaShipOwner dmVmaShipOwner)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateDmVmaShipOwner(dmVmaShipOwner);
    }

    /**
     * Updates the dm vma ship owner in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param dmVmaShipOwner the dm vma ship owner
     * @param merge whether to merge the dm vma ship owner with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the dm vma ship owner that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaShipOwner updateDmVmaShipOwner(
            com.fds.nsw.nghiepvu.model.DmVmaShipOwner dmVmaShipOwner, boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateDmVmaShipOwner(dmVmaShipOwner, merge);
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
   

    

    public static java.util.List<com.fds.nsw.nghiepvu.model.DmVmaShipOwner> findByMaritimeCode(
            java.lang.String maritimeCode, int start, int end) {
        return getService().findByMaritimeCode(maritimeCode, start, end);
    }

    public static com.fds.nsw.nghiepvu.model.DmVmaShipOwner fetchByShipOwnerCode(
            java.lang.String shipOwnerCode) {
        return getService().fetchByShipOwnerCode(shipOwnerCode);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.DmVmaShipOwner> findVmaShipOwners(
            java.lang.String maritimeCode, java.lang.String taxCode,
            java.lang.String companyName, java.lang.String companyAddress,
            java.lang.String contactEmail, java.lang.String telNo,
            java.lang.String isShipOwner, java.lang.String isShipOperator,
            java.lang.String isDelete, java.lang.String shipOwnerCodeGroup,
            int isOther, int start, int end) {
        return getService()
                .findVmaShipOwners(maritimeCode, taxCode, companyName,
                        companyAddress, contactEmail, telNo, isShipOwner, isShipOperator,
                        isDelete, shipOwnerCodeGroup, isOther, start, end);
    }

    public static long countVmaShipOwners(java.lang.String maritimeCode,
                                          java.lang.String taxCode, java.lang.String companyName,
                                          java.lang.String companyAddress, java.lang.String contactEmail,
                                          java.lang.String telNo, java.lang.String isShipOwner,
                                          java.lang.String isShipOperator, java.lang.String isDelete,
                                          java.lang.String shipOwnerCodeGroup, int isOther) {
        return getService()
                .countVmaShipOwners(maritimeCode, taxCode, companyName,
                        companyAddress, contactEmail, telNo, isShipOwner, isShipOperator,
                        isDelete, shipOwnerCodeGroup, isOther);
    }

    public static com.fds.nsw.nghiepvu.model.DmVmaShipOwner fetchByF_taxCode(
            java.lang.String taxCode) {
        return getService().fetchByF_taxCode(taxCode);
    }

    public static com.fds.nsw.nghiepvu.model.DmVmaShipOwner updateVmaShipOwner(
            java.lang.String fromMaritimeCode, java.lang.String maritimeCode,
            java.lang.String shipOwnerCode, java.lang.String taxCode,
            java.lang.String companyName, java.lang.String companyAddress,
            java.lang.String contactEmail, java.lang.String telNo,
            java.lang.String faxNo, int isShipOwner, int isShipOperator,
            java.lang.String remarks, java.lang.String syncVersion,
            java.lang.String companyShortName, int isOther)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchDmVmaShipOwnerException {
        return getService()
                .updateVmaShipOwner(fromMaritimeCode, maritimeCode,
                        shipOwnerCode, taxCode, companyName, companyAddress, contactEmail,
                        telNo, faxNo, isShipOwner, isShipOperator, remarks, syncVersion,
                        companyShortName, isOther);
    }

    public static com.fds.nsw.nghiepvu.model.DmVmaShipOwner deleteVmaShipOwner(
            java.lang.String fromMaritimeCode, java.lang.String shipOwnerCode,
            java.lang.String syncVersion)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchDmVmaShipOwnerException {
        return getService()
                .deleteVmaShipOwner(fromMaritimeCode, shipOwnerCode,
                        syncVersion);
    }

    public static void clearService() {
        _service = null;
    }

    public static DmVmaShipOwnerLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(DmVmaShipOwnerLocalServiceImpl service) {
    }

    private static DmVmaShipOwnerLocalServiceImpl _service;
}
