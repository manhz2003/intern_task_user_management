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
 * The utility for the dm vma tugboat local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmVmaTugboatLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmVmaTugboatLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmVmaTugboatLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmVmaTugboatLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmVmaTugboatLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class DmVmaTugboatLocalServiceUtil {
    public DmVmaTugboatLocalServiceUtil(DmVmaTugboatLocalServiceImpl service) {
        DmVmaTugboatLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmVmaTugboatLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the dm vma tugboat to the database. Also notifies the appropriate model listeners.
     *
     * @param dmVmaTugboat the dm vma tugboat
     * @return the dm vma tugboat that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaTugboat addDmVmaTugboat(
            com.fds.nsw.nghiepvu.model.DmVmaTugboat dmVmaTugboat)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addDmVmaTugboat(dmVmaTugboat);
    }

    /**
     * Creates a new dm vma tugboat with the primary key. Does not add the dm vma tugboat to the database.
     *
     * @param Id the primary key for the new dm vma tugboat
     * @return the new dm vma tugboat
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaTugboat createDmVmaTugboat(
            long Id) {
        return getService().createDmVmaTugboat(Id);
    }

    /**
     * Deletes the dm vma tugboat with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param Id the primary key of the dm vma tugboat
     * @return the dm vma tugboat that was removed
     * @throws PortalException if a dm vma tugboat with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaTugboat deleteDmVmaTugboat(
            long Id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteDmVmaTugboat(Id);
    }

    /**
     * Deletes the dm vma tugboat from the database. Also notifies the appropriate model listeners.
     *
     * @param dmVmaTugboat the dm vma tugboat
     * @return the dm vma tugboat that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaTugboat deleteDmVmaTugboat(
            com.fds.nsw.nghiepvu.model.DmVmaTugboat dmVmaTugboat)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteDmVmaTugboat(dmVmaTugboat);
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
   

    public static com.fds.nsw.nghiepvu.model.DmVmaTugboat fetchDmVmaTugboat(
            long Id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchDmVmaTugboat(Id);
    }

    /**
     * Returns the dm vma tugboat with the primary key.
     *
     * @param Id the primary key of the dm vma tugboat
     * @return the dm vma tugboat
     * @throws PortalException if a dm vma tugboat with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaTugboat getDmVmaTugboat(long Id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmVmaTugboat(Id);
    }

    

    /**
     * Returns a range of all the dm vma tugboats.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of dm vma tugboats
     * @param end the upper bound of the range of dm vma tugboats (not inclusive)
     * @return the range of dm vma tugboats
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.DmVmaTugboat> getDmVmaTugboats(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmVmaTugboats(start, end);
    }

    /**
     * Returns the number of dm vma tugboats.
     *
     * @return the number of dm vma tugboats
     * @throws SystemException if a system exception occurred
     */
    public static int getDmVmaTugboatsCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmVmaTugboatsCount();
    }

    /**
     * Updates the dm vma tugboat in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param dmVmaTugboat the dm vma tugboat
     * @return the dm vma tugboat that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaTugboat updateDmVmaTugboat(
            com.fds.nsw.nghiepvu.model.DmVmaTugboat dmVmaTugboat)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateDmVmaTugboat(dmVmaTugboat);
    }

    /**
     * Updates the dm vma tugboat in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param dmVmaTugboat the dm vma tugboat
     * @param merge whether to merge the dm vma tugboat with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the dm vma tugboat that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaTugboat updateDmVmaTugboat(
            com.fds.nsw.nghiepvu.model.DmVmaTugboat dmVmaTugboat, boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateDmVmaTugboat(dmVmaTugboat, merge);
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
   

    

    public static java.util.List<com.fds.nsw.nghiepvu.model.DmVmaTugboat> findByMaritimeCode_tugboatCompanyCode(
            java.lang.String maritimeCode, java.lang.String tugboatCompanyCode,
            int start, int end) {
        return getService()
                .findByMaritimeCode_tugboatCompanyCode(maritimeCode,
                        tugboatCompanyCode, start, end);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.DmVmaTugboat> findByMaritimeCode(
            java.lang.String maritimeCode, int start, int end) {
        return getService().findByMaritimeCode(maritimeCode, start, end);
    }

    public static com.fds.nsw.nghiepvu.model.DmVmaTugboat fetchByShipCode(
            java.lang.String shipCode) {
        return getService().fetchByShipCode(shipCode);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.DmVmaTugboat> findVmaTugboats(
            java.lang.String maritimeCode, java.lang.String tugboatCompanyCode,
            java.lang.String shipName, int power1, int power2,
            java.lang.String isDelete, java.lang.String shipCodeGroup, int start,
            int end) {
        return getService()
                .findVmaTugboats(maritimeCode, tugboatCompanyCode, shipName,
                        power1, power2, isDelete, shipCodeGroup, start, end);
    }

    public static long countVmaTugboats(java.lang.String maritimeCode,
                                        java.lang.String tugboatCompanyCode, java.lang.String shipName,
                                        int power1, int power2, java.lang.String isDelete,
                                        java.lang.String shipCodeGroup) {
        return getService()
                .countVmaTugboats(maritimeCode, tugboatCompanyCode,
                        shipName, power1, power2, isDelete, shipCodeGroup);
    }

    public static com.fds.nsw.nghiepvu.model.DmVmaTugboat updateVmaTugboat(
            java.lang.String fromMaritimeCode, java.lang.String maritimeCode,
            java.lang.String tugboatCompanyCode,
            java.lang.String tugboatCompanyName, java.lang.String shipCode,
            java.lang.String shipName, double power, double loa, double breadth,
            double clearanceHeight, double displacement,
            java.lang.String unitPower, double vndUnitPrice, double usdUnitPrice,
            int gt, int nt, int dwt, java.lang.String unitGRT,
            java.lang.String unitNT, java.lang.String unitDWT,
            java.lang.String remarks, java.lang.String syncVersion,
            java.lang.String tugboatShortName, java.util.Date tugboatExpiredDate)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchDmVmaTugboatException {
        return getService()
                .updateVmaTugboat(fromMaritimeCode, maritimeCode,
                        tugboatCompanyCode, tugboatCompanyName, shipCode, shipName, power,
                        loa, breadth, clearanceHeight, displacement, unitPower,
                        vndUnitPrice, usdUnitPrice, gt, nt, dwt, unitGRT, unitNT, unitDWT,
                        remarks, syncVersion, tugboatShortName, tugboatExpiredDate);
    }

    public static com.fds.nsw.nghiepvu.model.DmVmaTugboat deleteVmaTugboat(
            java.lang.String fromMaritimeCode, java.lang.String shipCode,
            java.lang.String syncVersion)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchDmVmaTugboatException {
        return getService()
                .deleteVmaTugboat(fromMaritimeCode, shipCode, syncVersion);
    }

    public static org.json.JSONObject getModelMau24_1T(
            java.lang.String maritimeCode, java.lang.String shipCode,
            java.lang.String startDate, java.lang.String endDate)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .getModelMau24_1T(maritimeCode, shipCode, startDate, endDate);
    }

    public static org.json.JSONArray getModelMau36T(
            java.lang.String maritimeCode, java.lang.String pilotCompanyCode,
            java.lang.String startDate, java.lang.String endDate)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .getModelMau36T(maritimeCode, pilotCompanyCode, startDate,
                        endDate);
    }

    public static void clearService() {
        _service = null;
    }

    public static DmVmaTugboatLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(DmVmaTugboatLocalServiceImpl service) {
    }

    private static DmVmaTugboatLocalServiceImpl _service;
}
