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
 * The utility for the vma schedule xline local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.VmaScheduleXlineLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see VmaScheduleXlineLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.VmaScheduleXlineLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.VmaScheduleXlineLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.VmaScheduleXlineLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class VmaScheduleXlineLocalServiceUtil {
    public VmaScheduleXlineLocalServiceUtil(VmaScheduleXlineLocalServiceImpl service) {
        VmaScheduleXlineLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.VmaScheduleXlineLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the vma schedule xline to the database. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleXline the vma schedule xline
     * @return the vma schedule xline that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleXline addVmaScheduleXline(
            com.fds.nsw.nghiepvu.model.VmaScheduleXline vmaScheduleXline)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addVmaScheduleXline(vmaScheduleXline);
    }

    /**
     * Creates a new vma schedule xline with the primary key. Does not add the vma schedule xline to the database.
     *
     * @param id the primary key for the new vma schedule xline
     * @return the new vma schedule xline
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleXline createVmaScheduleXline(
            long id) {
        return getService().createVmaScheduleXline(id);
    }

    /**
     * Deletes the vma schedule xline with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the vma schedule xline
     * @return the vma schedule xline that was removed
     * @throws PortalException if a vma schedule xline with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleXline deleteVmaScheduleXline(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaScheduleXline(id);
    }

    /**
     * Deletes the vma schedule xline from the database. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleXline the vma schedule xline
     * @return the vma schedule xline that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleXline deleteVmaScheduleXline(
            com.fds.nsw.nghiepvu.model.VmaScheduleXline vmaScheduleXline)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaScheduleXline(vmaScheduleXline);
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
   

    public static com.fds.nsw.nghiepvu.model.VmaScheduleXline fetchVmaScheduleXline(
            long id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchVmaScheduleXline(id);
    }

    /**
     * Returns the vma schedule xline with the primary key.
     *
     * @param id the primary key of the vma schedule xline
     * @return the vma schedule xline
     * @throws PortalException if a vma schedule xline with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleXline getVmaScheduleXline(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaScheduleXline(id);
    }

    

    /**
     * Returns a range of all the vma schedule xlines.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of vma schedule xlines
     * @param end the upper bound of the range of vma schedule xlines (not inclusive)
     * @return the range of vma schedule xlines
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaScheduleXline> getVmaScheduleXlines(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaScheduleXlines(start, end);
    }

    /**
     * Returns the number of vma schedule xlines.
     *
     * @return the number of vma schedule xlines
     * @throws SystemException if a system exception occurred
     */
    public static int getVmaScheduleXlinesCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaScheduleXlinesCount();
    }

    /**
     * Updates the vma schedule xline in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleXline the vma schedule xline
     * @return the vma schedule xline that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleXline updateVmaScheduleXline(
            com.fds.nsw.nghiepvu.model.VmaScheduleXline vmaScheduleXline)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaScheduleXline(vmaScheduleXline);
    }

    /**
     * Updates the vma schedule xline in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleXline the vma schedule xline
     * @param merge whether to merge the vma schedule xline with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the vma schedule xline that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleXline updateVmaScheduleXline(
            com.fds.nsw.nghiepvu.model.VmaScheduleXline vmaScheduleXline,
            boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaScheduleXline(vmaScheduleXline, merge);
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
   

    

    public static com.fds.nsw.nghiepvu.model.VmaScheduleXline delete(
            long id)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaScheduleXlineException {
        return getService().delete(id);
    }

    public static org.json.JSONObject findVmaScheduleXline(
            java.lang.String portofAuthority, java.lang.String shipOperatorCode,
            java.lang.String companyName, java.lang.String routeDescription,
            java.lang.Integer scheduleYear, java.lang.Integer scheduleMonth,
            java.lang.Integer voyageNumber, int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findVmaScheduleXline(portofAuthority, shipOperatorCode,
                        companyName, routeDescription, scheduleYear, scheduleMonth,
                        voyageNumber, start, end);
    }

    public static org.json.JSONObject findVmaScheduleXline(
            java.lang.String searchQuery, java.lang.String countQuery, int start,
            int end)
            throws com.fds.nsw.kernel.exception.SystemException,
            org.json.JSONException {
        return getService()
                .findVmaScheduleXline(searchQuery, countQuery, start, end);
    }

    public static long countVmaScheduleXline(java.lang.String portofAuthority,
                                             java.lang.String shipOperatorCode, java.lang.String companyName,
                                             java.lang.String routeDescription, java.lang.Integer scheduleYear,
                                             java.lang.Integer scheduleMonth, java.lang.Integer voyageNumber)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .countVmaScheduleXline(portofAuthority, shipOperatorCode,
                        companyName, routeDescription, scheduleYear, scheduleMonth,
                        voyageNumber);
    }

    public static long countVmaScheduleXline(java.lang.String sql)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().countVmaScheduleXline(sql);
    }

    public static void clearService() {
        _service = null;
    }

    public static VmaScheduleXlineLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(VmaScheduleXlineLocalServiceImpl service) {
    }

    private static VmaScheduleXlineLocalServiceImpl _service;
}
