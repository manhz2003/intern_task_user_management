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
 * The utility for the vma service port local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.VmaServicePortLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see VmaServicePortLocalService
 * @see vn.gt.dao.danhmuc.service.base.VmaServicePortLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.VmaServicePortLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.VmaServicePortLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class VmaServicePortLocalServiceUtil {
    public VmaServicePortLocalServiceUtil(VmaServicePortLocalServiceImpl service) {
        VmaServicePortLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.VmaServicePortLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the vma service port to the database. Also notifies the appropriate model listeners.
     *
     * @param vmaServicePort the vma service port
     * @return the vma service port that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaServicePort addVmaServicePort(
            com.fds.nsw.nghiepvu.model.VmaServicePort vmaServicePort)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addVmaServicePort(vmaServicePort);
    }

    /**
     * Creates a new vma service port with the primary key. Does not add the vma service port to the database.
     *
     * @param id the primary key for the new vma service port
     * @return the new vma service port
     */
    public static com.fds.nsw.nghiepvu.model.VmaServicePort createVmaServicePort(
            long id) {
        return getService().createVmaServicePort(id);
    }

    /**
     * Deletes the vma service port with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the vma service port
     * @return the vma service port that was removed
     * @throws PortalException if a vma service port with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaServicePort deleteVmaServicePort(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaServicePort(id);
    }

    /**
     * Deletes the vma service port from the database. Also notifies the appropriate model listeners.
     *
     * @param vmaServicePort the vma service port
     * @return the vma service port that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaServicePort deleteVmaServicePort(
            com.fds.nsw.nghiepvu.model.VmaServicePort vmaServicePort)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaServicePort(vmaServicePort);
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
   

    public static com.fds.nsw.nghiepvu.model.VmaServicePort fetchVmaServicePort(
            long id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchVmaServicePort(id);
    }

    /**
     * Returns the vma service port with the primary key.
     *
     * @param id the primary key of the vma service port
     * @return the vma service port
     * @throws PortalException if a vma service port with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaServicePort getVmaServicePort(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaServicePort(id);
    }

    

    /**
     * Returns a range of all the vma service ports.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of vma service ports
     * @param end the upper bound of the range of vma service ports (not inclusive)
     * @return the range of vma service ports
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaServicePort> getVmaServicePorts(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaServicePorts(start, end);
    }

    /**
     * Returns the number of vma service ports.
     *
     * @return the number of vma service ports
     * @throws SystemException if a system exception occurred
     */
    public static int getVmaServicePortsCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaServicePortsCount();
    }

    /**
     * Updates the vma service port in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaServicePort the vma service port
     * @return the vma service port that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaServicePort updateVmaServicePort(
            com.fds.nsw.nghiepvu.model.VmaServicePort vmaServicePort)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaServicePort(vmaServicePort);
    }

    /**
     * Updates the vma service port in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaServicePort the vma service port
     * @param merge whether to merge the vma service port with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the vma service port that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaServicePort updateVmaServicePort(
            com.fds.nsw.nghiepvu.model.VmaServicePort vmaServicePort, boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaServicePort(vmaServicePort, merge);
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
   

    

    public static long countVmaServicePort()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().countVmaServicePort();
    }

    public static void clearService() {
        _service = null;
    }

    public static VmaServicePortLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(VmaServicePortLocalServiceImpl service) {
    }

    private static VmaServicePortLocalServiceImpl _service;
}
