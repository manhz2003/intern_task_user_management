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

package vn.gt.dao.noticeandmessage.service;



/**
 * The utility for the vma schedule xline sailing local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.VmaScheduleXlineSailingLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see VmaScheduleXlineSailingLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.VmaScheduleXlineSailingLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.VmaScheduleXlineSailingLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.VmaScheduleXlineSailingLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class VmaScheduleXlineSailingLocalServiceUtil {
    public VmaScheduleXlineSailingLocalServiceUtil(VmaScheduleXlineSailingLocalServiceImpl service) {
        VmaScheduleXlineSailingLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.VmaScheduleXlineSailingLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the vma schedule xline sailing to the database. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleXlineSailing the vma schedule xline sailing
     * @return the vma schedule xline sailing that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleXlineSailing addVmaScheduleXlineSailing(
            com.fds.nsw.nghiepvu.model.VmaScheduleXlineSailing vmaScheduleXlineSailing)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addVmaScheduleXlineSailing(vmaScheduleXlineSailing);
    }

    /**
     * Creates a new vma schedule xline sailing with the primary key. Does not add the vma schedule xline sailing to the database.
     *
     * @param id the primary key for the new vma schedule xline sailing
     * @return the new vma schedule xline sailing
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleXlineSailing createVmaScheduleXlineSailing(
            long id) {
        return getService().createVmaScheduleXlineSailing(id);
    }

    /**
     * Deletes the vma schedule xline sailing with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the vma schedule xline sailing
     * @return the vma schedule xline sailing that was removed
     * @throws PortalException if a vma schedule xline sailing with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleXlineSailing deleteVmaScheduleXlineSailing(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaScheduleXlineSailing(id);
    }

    /**
     * Deletes the vma schedule xline sailing from the database. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleXlineSailing the vma schedule xline sailing
     * @return the vma schedule xline sailing that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleXlineSailing deleteVmaScheduleXlineSailing(
            com.fds.nsw.nghiepvu.model.VmaScheduleXlineSailing vmaScheduleXlineSailing)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .deleteVmaScheduleXlineSailing(vmaScheduleXlineSailing);
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
   

    public static com.fds.nsw.nghiepvu.model.VmaScheduleXlineSailing fetchVmaScheduleXlineSailing(
            long id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchVmaScheduleXlineSailing(id);
    }

    /**
     * Returns the vma schedule xline sailing with the primary key.
     *
     * @param id the primary key of the vma schedule xline sailing
     * @return the vma schedule xline sailing
     * @throws PortalException if a vma schedule xline sailing with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleXlineSailing getVmaScheduleXlineSailing(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaScheduleXlineSailing(id);
    }

    

    /**
     * Returns a range of all the vma schedule xline sailings.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of vma schedule xline sailings
     * @param end the upper bound of the range of vma schedule xline sailings (not inclusive)
     * @return the range of vma schedule xline sailings
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaScheduleXlineSailing> getVmaScheduleXlineSailings(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaScheduleXlineSailings(start, end);
    }

    /**
     * Returns the number of vma schedule xline sailings.
     *
     * @return the number of vma schedule xline sailings
     * @throws SystemException if a system exception occurred
     */
    public static int getVmaScheduleXlineSailingsCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaScheduleXlineSailingsCount();
    }

    /**
     * Updates the vma schedule xline sailing in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleXlineSailing the vma schedule xline sailing
     * @return the vma schedule xline sailing that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleXlineSailing updateVmaScheduleXlineSailing(
            com.fds.nsw.nghiepvu.model.VmaScheduleXlineSailing vmaScheduleXlineSailing)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .updateVmaScheduleXlineSailing(vmaScheduleXlineSailing);
    }

    /**
     * Updates the vma schedule xline sailing in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleXlineSailing the vma schedule xline sailing
     * @param merge whether to merge the vma schedule xline sailing with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the vma schedule xline sailing that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleXlineSailing updateVmaScheduleXlineSailing(
            com.fds.nsw.nghiepvu.model.VmaScheduleXlineSailing vmaScheduleXlineSailing,
            boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .updateVmaScheduleXlineSailing(vmaScheduleXlineSailing, merge);
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
   

    

    public static com.fds.nsw.nghiepvu.model.VmaScheduleXlineSailing delete(
            long id)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaScheduleXlineSailingException {
        return getService().delete(id);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaScheduleXlineSailing> findByshipOperatorCode_scheduleYear_scheduleMonth(
            java.lang.String shipOperatorCode, int scheduleYear, int scheduleMonth)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findByshipOperatorCode_scheduleYear_scheduleMonth(shipOperatorCode,
                        scheduleYear, scheduleMonth);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaScheduleXlineSailing> findVmaScheduleXlineSailings(
            java.lang.String portofAuthority, java.lang.String nameOfShip,
            java.lang.String imoNumber, java.lang.String callSign,
            java.lang.String registryNumber, java.lang.String voyageNo,
            java.lang.String stateCode, java.lang.String provinceCode,
            java.lang.String maritimePortCode,
            java.lang.String portGoingToStateName,
            java.lang.String portGoingToCode, int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findVmaScheduleXlineSailings(portofAuthority, nameOfShip,
                        imoNumber, callSign, registryNumber, voyageNo, stateCode,
                        provinceCode, maritimePortCode, portGoingToStateName,
                        portGoingToCode, start, end);
    }

    public static long countVmaScheduleXlineSailings(
            java.lang.String portofAuthority, java.lang.String nameOfShip,
            java.lang.String imoNumber, java.lang.String callSign,
            java.lang.String registryNumber, java.lang.String voyageNo,
            java.lang.String stateCode, java.lang.String provinceCode,
            java.lang.String maritimePortCode,
            java.lang.String portGoingToStateName, java.lang.String portGoingToCode)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .countVmaScheduleXlineSailings(portofAuthority, nameOfShip,
                        imoNumber, callSign, registryNumber, voyageNo, stateCode,
                        provinceCode, maritimePortCode, portGoingToStateName,
                        portGoingToCode);
    }

    public static void clearService() {
        _service = null;
    }

    public static VmaScheduleXlineSailingLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(VmaScheduleXlineSailingLocalServiceImpl service) {
    }

    private static VmaScheduleXlineSailingLocalServiceImpl _service;
}
