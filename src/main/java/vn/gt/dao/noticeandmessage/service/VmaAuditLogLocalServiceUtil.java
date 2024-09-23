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
 * The utility for the vma audit log local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.VmaAuditLogLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see VmaAuditLogLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.VmaAuditLogLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.VmaAuditLogLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.VmaAuditLogLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class VmaAuditLogLocalServiceUtil {
    public VmaAuditLogLocalServiceUtil(VmaAuditLogLocalServiceImpl service) {
        VmaAuditLogLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.VmaAuditLogLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the vma audit log to the database. Also notifies the appropriate model listeners.
     *
     * @param vmaAuditLog the vma audit log
     * @return the vma audit log that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaAuditLog addVmaAuditLog(
            com.fds.nsw.nghiepvu.model.VmaAuditLog vmaAuditLog)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addVmaAuditLog(vmaAuditLog);
    }

    /**
     * Creates a new vma audit log with the primary key. Does not add the vma audit log to the database.
     *
     * @param id the primary key for the new vma audit log
     * @return the new vma audit log
     */
    public static com.fds.nsw.nghiepvu.model.VmaAuditLog createVmaAuditLog(
            long id) {
        return getService().createVmaAuditLog(id);
    }

    /**
     * Deletes the vma audit log with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the vma audit log
     * @return the vma audit log that was removed
     * @throws PortalException if a vma audit log with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaAuditLog deleteVmaAuditLog(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaAuditLog(id);
    }

    /**
     * Deletes the vma audit log from the database. Also notifies the appropriate model listeners.
     *
     * @param vmaAuditLog the vma audit log
     * @return the vma audit log that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaAuditLog deleteVmaAuditLog(
            com.fds.nsw.nghiepvu.model.VmaAuditLog vmaAuditLog)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaAuditLog(vmaAuditLog);
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
   

    public static com.fds.nsw.nghiepvu.model.VmaAuditLog fetchVmaAuditLog(
            long id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchVmaAuditLog(id);
    }

    /**
     * Returns the vma audit log with the primary key.
     *
     * @param id the primary key of the vma audit log
     * @return the vma audit log
     * @throws PortalException if a vma audit log with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaAuditLog getVmaAuditLog(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaAuditLog(id);
    }

    

    /**
     * Returns a range of all the vma audit logs.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of vma audit logs
     * @param end the upper bound of the range of vma audit logs (not inclusive)
     * @return the range of vma audit logs
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaAuditLog> getVmaAuditLogs(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaAuditLogs(start, end);
    }

    /**
     * Returns the number of vma audit logs.
     *
     * @return the number of vma audit logs
     * @throws SystemException if a system exception occurred
     */
    public static int getVmaAuditLogsCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaAuditLogsCount();
    }

    /**
     * Updates the vma audit log in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaAuditLog the vma audit log
     * @return the vma audit log that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaAuditLog updateVmaAuditLog(
            com.fds.nsw.nghiepvu.model.VmaAuditLog vmaAuditLog)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaAuditLog(vmaAuditLog);
    }

    /**
     * Updates the vma audit log in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaAuditLog the vma audit log
     * @param merge whether to merge the vma audit log with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the vma audit log that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaAuditLog updateVmaAuditLog(
            com.fds.nsw.nghiepvu.model.VmaAuditLog vmaAuditLog, boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaAuditLog(vmaAuditLog, merge);
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
   

    

    public static com.fds.nsw.nghiepvu.model.VmaAuditLog addVmaAuditLog(
            long userId, java.lang.String modifyUser, java.lang.String actionName,
            java.lang.String tableName, java.lang.String keyCode,
            java.lang.String remarks)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .addVmaAuditLog(userId, modifyUser, actionName, tableName,
                        keyCode, remarks);
    }

    public static void clearService() {
        _service = null;
    }

    public static VmaAuditLogLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(VmaAuditLogLocalServiceImpl service) {
    }

    private static VmaAuditLogLocalServiceImpl _service;
}
