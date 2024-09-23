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
 * The utility for the vma vnpt service config local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.VmaVnptServiceConfigLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see VmaVnptServiceConfigLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.VmaVnptServiceConfigLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.VmaVnptServiceConfigLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.VmaVnptServiceConfigLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class VmaVnptServiceConfigLocalServiceUtil {
    public VmaVnptServiceConfigLocalServiceUtil(VmaVnptServiceConfigLocalServiceImpl service) {
        VmaVnptServiceConfigLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.VmaVnptServiceConfigLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the vma vnpt service config to the database. Also notifies the appropriate model listeners.
     *
     * @param vmaVnptServiceConfig the vma vnpt service config
     * @return the vma vnpt service config that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaVnptServiceConfig addVmaVnptServiceConfig(
            com.fds.nsw.nghiepvu.model.VmaVnptServiceConfig vmaVnptServiceConfig)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addVmaVnptServiceConfig(vmaVnptServiceConfig);
    }

    /**
     * Creates a new vma vnpt service config with the primary key. Does not add the vma vnpt service config to the database.
     *
     * @param id the primary key for the new vma vnpt service config
     * @return the new vma vnpt service config
     */
    public static com.fds.nsw.nghiepvu.model.VmaVnptServiceConfig createVmaVnptServiceConfig(
            long id) {
        return getService().createVmaVnptServiceConfig(id);
    }

    /**
     * Deletes the vma vnpt service config with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the vma vnpt service config
     * @return the vma vnpt service config that was removed
     * @throws PortalException if a vma vnpt service config with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaVnptServiceConfig deleteVmaVnptServiceConfig(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaVnptServiceConfig(id);
    }

    /**
     * Deletes the vma vnpt service config from the database. Also notifies the appropriate model listeners.
     *
     * @param vmaVnptServiceConfig the vma vnpt service config
     * @return the vma vnpt service config that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaVnptServiceConfig deleteVmaVnptServiceConfig(
            com.fds.nsw.nghiepvu.model.VmaVnptServiceConfig vmaVnptServiceConfig)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaVnptServiceConfig(vmaVnptServiceConfig);
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
   

    public static com.fds.nsw.nghiepvu.model.VmaVnptServiceConfig fetchVmaVnptServiceConfig(
            long id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchVmaVnptServiceConfig(id);
    }

    /**
     * Returns the vma vnpt service config with the primary key.
     *
     * @param id the primary key of the vma vnpt service config
     * @return the vma vnpt service config
     * @throws PortalException if a vma vnpt service config with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaVnptServiceConfig getVmaVnptServiceConfig(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaVnptServiceConfig(id);
    }

    

    /**
     * Returns a range of all the vma vnpt service configs.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of vma vnpt service configs
     * @param end the upper bound of the range of vma vnpt service configs (not inclusive)
     * @return the range of vma vnpt service configs
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaVnptServiceConfig> getVmaVnptServiceConfigs(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaVnptServiceConfigs(start, end);
    }

    /**
     * Returns the number of vma vnpt service configs.
     *
     * @return the number of vma vnpt service configs
     * @throws SystemException if a system exception occurred
     */
    public static int getVmaVnptServiceConfigsCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaVnptServiceConfigsCount();
    }

    /**
     * Updates the vma vnpt service config in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaVnptServiceConfig the vma vnpt service config
     * @return the vma vnpt service config that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaVnptServiceConfig updateVmaVnptServiceConfig(
            com.fds.nsw.nghiepvu.model.VmaVnptServiceConfig vmaVnptServiceConfig)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaVnptServiceConfig(vmaVnptServiceConfig);
    }

    /**
     * Updates the vma vnpt service config in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaVnptServiceConfig the vma vnpt service config
     * @param merge whether to merge the vma vnpt service config with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the vma vnpt service config that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaVnptServiceConfig updateVmaVnptServiceConfig(
            com.fds.nsw.nghiepvu.model.VmaVnptServiceConfig vmaVnptServiceConfig,
            boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .updateVmaVnptServiceConfig(vmaVnptServiceConfig, merge);
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
   

    

    public static com.fds.nsw.nghiepvu.model.VmaVnptServiceConfig findByMaritimeCode(
            java.lang.String maritimeCode) {
        return getService().findByMaritimeCode(maritimeCode);
    }

    public static com.fds.nsw.nghiepvu.model.VmaVnptServiceConfig findByTestMode_MaritimeCode(
            java.lang.String maritimeCode, int testMode) {
        return getService().findByTestMode_MaritimeCode(maritimeCode, testMode);
    }

    public static com.fds.nsw.nghiepvu.model.VmaVnptServiceConfig findByTestMode(
            int testMode) {
        return getService().findByTestMode(testMode);
    }

    public static void clearService() {
        _service = null;
    }

    public static VmaVnptServiceConfigLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(VmaVnptServiceConfigLocalServiceImpl service) {
    }

    private static VmaVnptServiceConfigLocalServiceImpl _service;
}
