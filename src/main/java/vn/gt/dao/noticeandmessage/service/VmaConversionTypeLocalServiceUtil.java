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
 * The utility for the vma conversion type local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.VmaConversionTypeLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see VmaConversionTypeLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.VmaConversionTypeLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.VmaConversionTypeLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.VmaConversionTypeLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class VmaConversionTypeLocalServiceUtil {
    public VmaConversionTypeLocalServiceUtil(VmaConversionTypeLocalServiceImpl service) {
        VmaConversionTypeLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.VmaConversionTypeLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the vma conversion type to the database. Also notifies the appropriate model listeners.
     *
     * @param vmaConversionType the vma conversion type
     * @return the vma conversion type that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaConversionType addVmaConversionType(
            com.fds.nsw.nghiepvu.model.VmaConversionType vmaConversionType)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addVmaConversionType(vmaConversionType);
    }

    /**
     * Creates a new vma conversion type with the primary key. Does not add the vma conversion type to the database.
     *
     * @param id the primary key for the new vma conversion type
     * @return the new vma conversion type
     */
    public static com.fds.nsw.nghiepvu.model.VmaConversionType createVmaConversionType(
            long id) {
        return getService().createVmaConversionType(id);
    }

    /**
     * Deletes the vma conversion type with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the vma conversion type
     * @return the vma conversion type that was removed
     * @throws PortalException if a vma conversion type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaConversionType deleteVmaConversionType(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaConversionType(id);
    }

    /**
     * Deletes the vma conversion type from the database. Also notifies the appropriate model listeners.
     *
     * @param vmaConversionType the vma conversion type
     * @return the vma conversion type that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaConversionType deleteVmaConversionType(
            com.fds.nsw.nghiepvu.model.VmaConversionType vmaConversionType)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaConversionType(vmaConversionType);
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
   

    public static com.fds.nsw.nghiepvu.model.VmaConversionType fetchVmaConversionType(
            long id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchVmaConversionType(id);
    }

    /**
     * Returns the vma conversion type with the primary key.
     *
     * @param id the primary key of the vma conversion type
     * @return the vma conversion type
     * @throws PortalException if a vma conversion type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaConversionType getVmaConversionType(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaConversionType(id);
    }

    

    /**
     * Returns a range of all the vma conversion types.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of vma conversion types
     * @param end the upper bound of the range of vma conversion types (not inclusive)
     * @return the range of vma conversion types
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaConversionType> getVmaConversionTypes(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaConversionTypes(start, end);
    }

    /**
     * Returns the number of vma conversion types.
     *
     * @return the number of vma conversion types
     * @throws SystemException if a system exception occurred
     */
    public static int getVmaConversionTypesCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaConversionTypesCount();
    }

    /**
     * Updates the vma conversion type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaConversionType the vma conversion type
     * @return the vma conversion type that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaConversionType updateVmaConversionType(
            com.fds.nsw.nghiepvu.model.VmaConversionType vmaConversionType)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaConversionType(vmaConversionType);
    }

    /**
     * Updates the vma conversion type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaConversionType the vma conversion type
     * @param merge whether to merge the vma conversion type with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the vma conversion type that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaConversionType updateVmaConversionType(
            com.fds.nsw.nghiepvu.model.VmaConversionType vmaConversionType,
            boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaConversionType(vmaConversionType, merge);
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
   

    

    public static int countAll() {
        return getService().countAll();
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaConversionType> findAll() {
        return getService().findAll();
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaConversionType> findAll(
            int start, int end) {
        return getService().findAll(start, end);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaConversionType> findAll(
            int start, int end,
            com.fds.nsw.kernel.util.OrderByComparator orderByComparator) {
        return getService().findAll(start, end, orderByComparator);
    }

    public static org.json.JSONObject findVmaConversionType(
            java.lang.String searchQuery, java.lang.String countQuery, int start,
            int end)
            throws com.fds.nsw.kernel.exception.SystemException,
            org.json.JSONException {
        return getService()
                .findVmaConversionType(searchQuery, countQuery, start, end);
    }

    public static long countVmaConversionType(java.lang.String sql)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().countVmaConversionType(sql);
    }

    public static void clearService() {
        _service = null;
    }

    public static VmaConversionTypeLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(VmaConversionTypeLocalServiceImpl service) {
    }

    private static VmaConversionTypeLocalServiceImpl _service;
}
