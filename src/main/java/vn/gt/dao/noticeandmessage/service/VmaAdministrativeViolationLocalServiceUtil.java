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
 * The utility for the vma administrative violation local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.VmaAdministrativeViolationLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see VmaAdministrativeViolationLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.VmaAdministrativeViolationLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.VmaAdministrativeViolationLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.VmaAdministrativeViolationLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class VmaAdministrativeViolationLocalServiceUtil {
    public VmaAdministrativeViolationLocalServiceUtil(VmaAdministrativeViolationLocalServiceImpl service) {
        VmaAdministrativeViolationLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.VmaAdministrativeViolationLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the vma administrative violation to the database. Also notifies the appropriate model listeners.
     *
     * @param vmaAdministrativeViolation the vma administrative violation
     * @return the vma administrative violation that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaAdministrativeViolation addVmaAdministrativeViolation(
            com.fds.nsw.nghiepvu.model.VmaAdministrativeViolation vmaAdministrativeViolation)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .addVmaAdministrativeViolation(vmaAdministrativeViolation);
    }

    /**
     * Creates a new vma administrative violation with the primary key. Does not add the vma administrative violation to the database.
     *
     * @param id the primary key for the new vma administrative violation
     * @return the new vma administrative violation
     */
    public static com.fds.nsw.nghiepvu.model.VmaAdministrativeViolation createVmaAdministrativeViolation(
            long id) {
        return getService().createVmaAdministrativeViolation(id);
    }

    /**
     * Deletes the vma administrative violation with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the vma administrative violation
     * @return the vma administrative violation that was removed
     * @throws PortalException if a vma administrative violation with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaAdministrativeViolation deleteVmaAdministrativeViolation(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaAdministrativeViolation(id);
    }

    /**
     * Deletes the vma administrative violation from the database. Also notifies the appropriate model listeners.
     *
     * @param vmaAdministrativeViolation the vma administrative violation
     * @return the vma administrative violation that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaAdministrativeViolation deleteVmaAdministrativeViolation(
            com.fds.nsw.nghiepvu.model.VmaAdministrativeViolation vmaAdministrativeViolation)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .deleteVmaAdministrativeViolation(vmaAdministrativeViolation);
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
   

    public static com.fds.nsw.nghiepvu.model.VmaAdministrativeViolation fetchVmaAdministrativeViolation(
            long id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchVmaAdministrativeViolation(id);
    }

    /**
     * Returns the vma administrative violation with the primary key.
     *
     * @param id the primary key of the vma administrative violation
     * @return the vma administrative violation
     * @throws PortalException if a vma administrative violation with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaAdministrativeViolation getVmaAdministrativeViolation(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaAdministrativeViolation(id);
    }

    

    /**
     * Returns a range of all the vma administrative violations.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of vma administrative violations
     * @param end the upper bound of the range of vma administrative violations (not inclusive)
     * @return the range of vma administrative violations
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaAdministrativeViolation> getVmaAdministrativeViolations(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaAdministrativeViolations(start, end);
    }

    /**
     * Returns the number of vma administrative violations.
     *
     * @return the number of vma administrative violations
     * @throws SystemException if a system exception occurred
     */
    public static int getVmaAdministrativeViolationsCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaAdministrativeViolationsCount();
    }

    /**
     * Updates the vma administrative violation in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaAdministrativeViolation the vma administrative violation
     * @return the vma administrative violation that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaAdministrativeViolation updateVmaAdministrativeViolation(
            com.fds.nsw.nghiepvu.model.VmaAdministrativeViolation vmaAdministrativeViolation)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .updateVmaAdministrativeViolation(vmaAdministrativeViolation);
    }

    /**
     * Updates the vma administrative violation in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaAdministrativeViolation the vma administrative violation
     * @param merge whether to merge the vma administrative violation with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the vma administrative violation that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaAdministrativeViolation updateVmaAdministrativeViolation(
            com.fds.nsw.nghiepvu.model.VmaAdministrativeViolation vmaAdministrativeViolation,
            boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .updateVmaAdministrativeViolation(vmaAdministrativeViolation,
                        merge);
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
   

    

    public static com.fds.nsw.nghiepvu.model.VmaAdministrativeViolation delete(
            long id)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaAdministrativeViolationException {
        return getService().delete(id);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaAdministrativeViolation> findByPortofAuthority(
            java.lang.String portofAuthority)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().findByPortofAuthority(portofAuthority);
    }

    public static int countByPortofAuthority(java.lang.String portofAuthority)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().countByPortofAuthority(portofAuthority);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaAdministrativeViolation> findVmaAdministrativeViolations(
            java.lang.String portofAuthority, java.lang.String violationDate,
            java.lang.String notViolationDate, int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findVmaAdministrativeViolations(portofAuthority,
                        violationDate, notViolationDate, start, end);
    }

    public static long countVmaAdministrativeViolation(
            java.lang.String portofAuthority, java.lang.String violationDate,
            java.lang.String notViolationDate)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .countVmaAdministrativeViolation(portofAuthority,
                        violationDate, notViolationDate);
    }

    public static void clearService() {
        _service = null;
    }

    public static VmaAdministrativeViolationLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(VmaAdministrativeViolationLocalServiceImpl service) {
    }

    private static VmaAdministrativeViolationLocalServiceImpl _service;
}
