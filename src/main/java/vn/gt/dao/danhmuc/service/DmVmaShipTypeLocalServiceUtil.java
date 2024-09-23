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
 * The utility for the dm vma ship type local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmVmaShipTypeLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmVmaShipTypeLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmVmaShipTypeLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmVmaShipTypeLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmVmaShipTypeLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class DmVmaShipTypeLocalServiceUtil {
    public DmVmaShipTypeLocalServiceUtil(DmVmaShipTypeLocalServiceImpl service) {
        DmVmaShipTypeLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmVmaShipTypeLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the dm vma ship type to the database. Also notifies the appropriate model listeners.
     *
     * @param dmVmaShipType the dm vma ship type
     * @return the dm vma ship type that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaShipType addDmVmaShipType(
            com.fds.nsw.nghiepvu.model.DmVmaShipType dmVmaShipType)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addDmVmaShipType(dmVmaShipType);
    }

    /**
     * Creates a new dm vma ship type with the primary key. Does not add the dm vma ship type to the database.
     *
     * @param id the primary key for the new dm vma ship type
     * @return the new dm vma ship type
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaShipType createDmVmaShipType(
            long id) {
        return getService().createDmVmaShipType(id);
    }

    /**
     * Deletes the dm vma ship type with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the dm vma ship type
     * @return the dm vma ship type that was removed
     * @throws PortalException if a dm vma ship type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaShipType deleteDmVmaShipType(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteDmVmaShipType(id);
    }

    /**
     * Deletes the dm vma ship type from the database. Also notifies the appropriate model listeners.
     *
     * @param dmVmaShipType the dm vma ship type
     * @return the dm vma ship type that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaShipType deleteDmVmaShipType(
            com.fds.nsw.nghiepvu.model.DmVmaShipType dmVmaShipType)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteDmVmaShipType(dmVmaShipType);
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
   

    public static com.fds.nsw.nghiepvu.model.DmVmaShipType fetchDmVmaShipType(
            long id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchDmVmaShipType(id);
    }

    /**
     * Returns the dm vma ship type with the primary key.
     *
     * @param id the primary key of the dm vma ship type
     * @return the dm vma ship type
     * @throws PortalException if a dm vma ship type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaShipType getDmVmaShipType(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmVmaShipType(id);
    }

    

    /**
     * Returns a range of all the dm vma ship types.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of dm vma ship types
     * @param end the upper bound of the range of dm vma ship types (not inclusive)
     * @return the range of dm vma ship types
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.DmVmaShipType> getDmVmaShipTypes(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmVmaShipTypes(start, end);
    }

    /**
     * Returns the number of dm vma ship types.
     *
     * @return the number of dm vma ship types
     * @throws SystemException if a system exception occurred
     */
    public static int getDmVmaShipTypesCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmVmaShipTypesCount();
    }

    /**
     * Updates the dm vma ship type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param dmVmaShipType the dm vma ship type
     * @return the dm vma ship type that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaShipType updateDmVmaShipType(
            com.fds.nsw.nghiepvu.model.DmVmaShipType dmVmaShipType)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateDmVmaShipType(dmVmaShipType);
    }

    /**
     * Updates the dm vma ship type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param dmVmaShipType the dm vma ship type
     * @param merge whether to merge the dm vma ship type with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the dm vma ship type that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaShipType updateDmVmaShipType(
            com.fds.nsw.nghiepvu.model.DmVmaShipType dmVmaShipType, boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateDmVmaShipType(dmVmaShipType, merge);
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
   

    

    public static com.fds.nsw.nghiepvu.model.DmVmaShipType fetchByShipTypeCode(
            java.lang.String shipTypeCode) {
        return getService().fetchByShipTypeCode(shipTypeCode);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.DmVmaShipType> findVmaShipTypes(
            java.lang.String shipTypeName, java.lang.String applyShip,
            java.lang.String applyBoat, java.lang.String isDelete,
            java.lang.String shipTypeCodeGroup, int start, int end) {
        return getService()
                .findVmaShipTypes(shipTypeName, applyShip, applyBoat,
                        isDelete, shipTypeCodeGroup, start, end);
    }

    public static long countVmaShipTypes(java.lang.String shipTypeName,
                                         java.lang.String applyShip, java.lang.String applyBoat,
                                         java.lang.String isDelete, java.lang.String shipTypeCodeGroup) {
        return getService()
                .countVmaShipTypes(shipTypeName, applyShip, applyBoat,
                        isDelete, shipTypeCodeGroup);
    }

    public static com.fds.nsw.nghiepvu.model.DmVmaShipType updateVmaShipType(
            java.lang.String fromMaritimeCode, java.lang.String shipTypeCode,
            java.lang.String shipTypeName, int applyBoat, int applyShip,
            java.lang.String remarks, java.lang.String shipTypeRef,
            java.lang.String syncVersion)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .updateVmaShipType(fromMaritimeCode, shipTypeCode,
                        shipTypeName, applyBoat, applyShip, remarks, shipTypeRef,
                        syncVersion);
    }

    public static com.fds.nsw.nghiepvu.model.DmVmaShipType deleteVmaShipType(
            java.lang.String fromMaritimeCode, java.lang.String shipTypeCode,
            java.lang.String syncVersion)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchDmVmaShipTypeException {
        return getService()
                .deleteVmaShipType(fromMaritimeCode, shipTypeCode,
                        syncVersion);
    }

    public static void clearService() {
        _service = null;
    }

    public static DmVmaShipTypeLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(DmVmaShipTypeLocalServiceImpl service) {
    }

    private static DmVmaShipTypeLocalServiceImpl _service;
}
