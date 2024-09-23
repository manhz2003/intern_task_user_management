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
 * The utility for the dm history vma ship type local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmHistoryVmaShipTypeLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmHistoryVmaShipTypeLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmHistoryVmaShipTypeLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmHistoryVmaShipTypeLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmHistoryVmaShipTypeLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class DmHistoryVmaShipTypeLocalServiceUtil {
    public DmHistoryVmaShipTypeLocalServiceUtil(DmHistoryVmaShipTypeLocalServiceImpl service) {
        DmHistoryVmaShipTypeLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmHistoryVmaShipTypeLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the dm history vma ship type to the database. Also notifies the appropriate model listeners.
     *
     * @param dmHistoryVmaShipType the dm history vma ship type
     * @return the dm history vma ship type that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryVmaShipType addDmHistoryVmaShipType(
            com.fds.nsw.nghiepvu.model.DmHistoryVmaShipType dmHistoryVmaShipType)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addDmHistoryVmaShipType(dmHistoryVmaShipType);
    }

    /**
     * Creates a new dm history vma ship type with the primary key. Does not add the dm history vma ship type to the database.
     *
     * @param id the primary key for the new dm history vma ship type
     * @return the new dm history vma ship type
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryVmaShipType createDmHistoryVmaShipType(
            long id) {
        return getService().createDmHistoryVmaShipType(id);
    }

    /**
     * Deletes the dm history vma ship type with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the dm history vma ship type
     * @return the dm history vma ship type that was removed
     * @throws PortalException if a dm history vma ship type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryVmaShipType deleteDmHistoryVmaShipType(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteDmHistoryVmaShipType(id);
    }

    /**
     * Deletes the dm history vma ship type from the database. Also notifies the appropriate model listeners.
     *
     * @param dmHistoryVmaShipType the dm history vma ship type
     * @return the dm history vma ship type that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryVmaShipType deleteDmHistoryVmaShipType(
            com.fds.nsw.nghiepvu.model.DmHistoryVmaShipType dmHistoryVmaShipType)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteDmHistoryVmaShipType(dmHistoryVmaShipType);
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
   

    public static com.fds.nsw.nghiepvu.model.DmHistoryVmaShipType fetchDmHistoryVmaShipType(
            long id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchDmHistoryVmaShipType(id);
    }

    /**
     * Returns the dm history vma ship type with the primary key.
     *
     * @param id the primary key of the dm history vma ship type
     * @return the dm history vma ship type
     * @throws PortalException if a dm history vma ship type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryVmaShipType getDmHistoryVmaShipType(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmHistoryVmaShipType(id);
    }

    

    /**
     * Returns a range of all the dm history vma ship types.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of dm history vma ship types
     * @param end the upper bound of the range of dm history vma ship types (not inclusive)
     * @return the range of dm history vma ship types
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.DmHistoryVmaShipType> getDmHistoryVmaShipTypes(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmHistoryVmaShipTypes(start, end);
    }

    /**
     * Returns the number of dm history vma ship types.
     *
     * @return the number of dm history vma ship types
     * @throws SystemException if a system exception occurred
     */
    public static int getDmHistoryVmaShipTypesCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmHistoryVmaShipTypesCount();
    }

    /**
     * Updates the dm history vma ship type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param dmHistoryVmaShipType the dm history vma ship type
     * @return the dm history vma ship type that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryVmaShipType updateDmHistoryVmaShipType(
            com.fds.nsw.nghiepvu.model.DmHistoryVmaShipType dmHistoryVmaShipType)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateDmHistoryVmaShipType(dmHistoryVmaShipType);
    }

    /**
     * Updates the dm history vma ship type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param dmHistoryVmaShipType the dm history vma ship type
     * @param merge whether to merge the dm history vma ship type with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the dm history vma ship type that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmHistoryVmaShipType updateDmHistoryVmaShipType(
            com.fds.nsw.nghiepvu.model.DmHistoryVmaShipType dmHistoryVmaShipType,
            boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .updateDmHistoryVmaShipType(dmHistoryVmaShipType, merge);
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
   

    

    public static com.fds.nsw.nghiepvu.model.DmHistoryVmaShipType fetchByShiptypeCode_SyncVersion(
            java.lang.String shipTypeCode, java.lang.String syncVersion) {
        return getService()
                .fetchByShiptypeCode_SyncVersion(shipTypeCode, syncVersion);
    }

    public static com.fds.nsw.nghiepvu.model.DmHistoryVmaShipType updateHistoryVmaShipType(
            java.lang.String fromMaritimeCode, java.lang.String shipTypeCode,
            java.lang.String shipTypeName, int applyBoat, int applyShip,
            java.lang.String remarks, java.lang.String shipTypeRef,
            java.lang.String syncVersion)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchDmHistoryVmaShipTypeException {
        return getService()
                .updateHistoryVmaShipType(fromMaritimeCode, shipTypeCode,
                        shipTypeName, applyBoat, applyShip, remarks, shipTypeRef,
                        syncVersion);
    }

    public static com.fds.nsw.nghiepvu.model.DmHistoryVmaShipType deleteHistoryVmaShipType(
            java.lang.String fromMaritimeCode, java.lang.String shipTypeCode,
            java.lang.String syncVersion)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchDmHistoryVmaShipTypeException {
        return getService()
                .deleteHistoryVmaShipType(fromMaritimeCode, shipTypeCode,
                        syncVersion);
    }

    public static void clearService() {
        _service = null;
    }

    public static DmHistoryVmaShipTypeLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(DmHistoryVmaShipTypeLocalServiceImpl service) {
    }

    private static DmHistoryVmaShipTypeLocalServiceImpl _service;
}
