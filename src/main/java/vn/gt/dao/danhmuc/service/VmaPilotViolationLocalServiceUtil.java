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
 * The utility for the vma pilot violation local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.VmaPilotViolationLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see VmaPilotViolationLocalService
 * @see vn.gt.dao.danhmuc.service.base.VmaPilotViolationLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.VmaPilotViolationLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.VmaPilotViolationLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class VmaPilotViolationLocalServiceUtil {
    public VmaPilotViolationLocalServiceUtil(VmaPilotViolationLocalServiceImpl service) {
        VmaPilotViolationLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.VmaPilotViolationLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the vma pilot violation to the database. Also notifies the appropriate model listeners.
     *
     * @param vmaPilotViolation the vma pilot violation
     * @return the vma pilot violation that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaPilotViolation addVmaPilotViolation(
            com.fds.nsw.nghiepvu.model.VmaPilotViolation vmaPilotViolation)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addVmaPilotViolation(vmaPilotViolation);
    }

    /**
     * Creates a new vma pilot violation with the primary key. Does not add the vma pilot violation to the database.
     *
     * @param id the primary key for the new vma pilot violation
     * @return the new vma pilot violation
     */
    public static com.fds.nsw.nghiepvu.model.VmaPilotViolation createVmaPilotViolation(
            long id) {
        return getService().createVmaPilotViolation(id);
    }

    /**
     * Deletes the vma pilot violation with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the vma pilot violation
     * @return the vma pilot violation that was removed
     * @throws PortalException if a vma pilot violation with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaPilotViolation deleteVmaPilotViolation(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaPilotViolation(id);
    }

    /**
     * Deletes the vma pilot violation from the database. Also notifies the appropriate model listeners.
     *
     * @param vmaPilotViolation the vma pilot violation
     * @return the vma pilot violation that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaPilotViolation deleteVmaPilotViolation(
            com.fds.nsw.nghiepvu.model.VmaPilotViolation vmaPilotViolation)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaPilotViolation(vmaPilotViolation);
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
   

    public static com.fds.nsw.nghiepvu.model.VmaPilotViolation fetchVmaPilotViolation(
            long id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchVmaPilotViolation(id);
    }

    /**
     * Returns the vma pilot violation with the primary key.
     *
     * @param id the primary key of the vma pilot violation
     * @return the vma pilot violation
     * @throws PortalException if a vma pilot violation with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaPilotViolation getVmaPilotViolation(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaPilotViolation(id);
    }

    

    /**
     * Returns a range of all the vma pilot violations.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of vma pilot violations
     * @param end the upper bound of the range of vma pilot violations (not inclusive)
     * @return the range of vma pilot violations
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaPilotViolation> getVmaPilotViolations(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaPilotViolations(start, end);
    }

    /**
     * Returns the number of vma pilot violations.
     *
     * @return the number of vma pilot violations
     * @throws SystemException if a system exception occurred
     */
    public static int getVmaPilotViolationsCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaPilotViolationsCount();
    }

    /**
     * Updates the vma pilot violation in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaPilotViolation the vma pilot violation
     * @return the vma pilot violation that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaPilotViolation updateVmaPilotViolation(
            com.fds.nsw.nghiepvu.model.VmaPilotViolation vmaPilotViolation)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaPilotViolation(vmaPilotViolation);
    }

    /**
     * Updates the vma pilot violation in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaPilotViolation the vma pilot violation
     * @param merge whether to merge the vma pilot violation with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the vma pilot violation that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaPilotViolation updateVmaPilotViolation(
            com.fds.nsw.nghiepvu.model.VmaPilotViolation vmaPilotViolation,
            boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaPilotViolation(vmaPilotViolation, merge);
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
   

    

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaPilotViolation> findByMaritimeCode(
            java.lang.String maritimeCode, int start, int end) {
        return getService().findByMaritimeCode(maritimeCode, start, end);
    }

    public static com.fds.nsw.nghiepvu.model.VmaPilotViolation updateVmaPilotViolation(
            java.lang.String fromMaritimeCode, long id,
            java.lang.String maritimeCode, java.lang.String pilotCode,
            java.util.Date violationDate, java.lang.String violationDescription,
            java.lang.String troubleshooting)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .updateVmaPilotViolation(fromMaritimeCode, id, maritimeCode,
                        pilotCode, violationDate, violationDescription, troubleshooting);
    }

    public static com.fds.nsw.nghiepvu.model.VmaPilotViolation deleteVmaPilotViolation(
            java.lang.String fromMaritimeCode, long id)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaPilotViolationException {
        return getService().deleteVmaPilotViolation(fromMaritimeCode, id);
    }

    public static void clearService() {
        _service = null;
    }

    public static VmaPilotViolationLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(VmaPilotViolationLocalServiceImpl service) {
    }

    private static VmaPilotViolationLocalServiceImpl _service;
}
