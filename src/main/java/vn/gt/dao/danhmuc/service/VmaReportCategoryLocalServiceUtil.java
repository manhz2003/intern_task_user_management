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
 * The utility for the vma report category local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.VmaReportCategoryLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see VmaReportCategoryLocalService
 * @see vn.gt.dao.danhmuc.service.base.VmaReportCategoryLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.VmaReportCategoryLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.VmaReportCategoryLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class VmaReportCategoryLocalServiceUtil {
    public VmaReportCategoryLocalServiceUtil(VmaReportCategoryLocalServiceImpl service) {
        VmaReportCategoryLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.VmaReportCategoryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the vma report category to the database. Also notifies the appropriate model listeners.
     *
     * @param vmaReportCategory the vma report category
     * @return the vma report category that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaReportCategory addVmaReportCategory(
            com.fds.nsw.nghiepvu.model.VmaReportCategory vmaReportCategory)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addVmaReportCategory(vmaReportCategory);
    }

    /**
     * Creates a new vma report category with the primary key. Does not add the vma report category to the database.
     *
     * @param id the primary key for the new vma report category
     * @return the new vma report category
     */
    public static com.fds.nsw.nghiepvu.model.VmaReportCategory createVmaReportCategory(
            long id) {
        return getService().createVmaReportCategory(id);
    }

    /**
     * Deletes the vma report category with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the vma report category
     * @return the vma report category that was removed
     * @throws PortalException if a vma report category with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaReportCategory deleteVmaReportCategory(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaReportCategory(id);
    }

    /**
     * Deletes the vma report category from the database. Also notifies the appropriate model listeners.
     *
     * @param vmaReportCategory the vma report category
     * @return the vma report category that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaReportCategory deleteVmaReportCategory(
            com.fds.nsw.nghiepvu.model.VmaReportCategory vmaReportCategory)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaReportCategory(vmaReportCategory);
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
   

    public static com.fds.nsw.nghiepvu.model.VmaReportCategory fetchVmaReportCategory(
            long id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchVmaReportCategory(id);
    }

    /**
     * Returns the vma report category with the primary key.
     *
     * @param id the primary key of the vma report category
     * @return the vma report category
     * @throws PortalException if a vma report category with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaReportCategory getVmaReportCategory(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaReportCategory(id);
    }

    

    /**
     * Returns a range of all the vma report categories.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of vma report categories
     * @param end the upper bound of the range of vma report categories (not inclusive)
     * @return the range of vma report categories
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaReportCategory> getVmaReportCategories(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaReportCategories(start, end);
    }

    /**
     * Returns the number of vma report categories.
     *
     * @return the number of vma report categories
     * @throws SystemException if a system exception occurred
     */
    public static int getVmaReportCategoriesCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaReportCategoriesCount();
    }

    /**
     * Updates the vma report category in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaReportCategory the vma report category
     * @return the vma report category that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaReportCategory updateVmaReportCategory(
            com.fds.nsw.nghiepvu.model.VmaReportCategory vmaReportCategory)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaReportCategory(vmaReportCategory);
    }

    /**
     * Updates the vma report category in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaReportCategory the vma report category
     * @param merge whether to merge the vma report category with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the vma report category that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaReportCategory updateVmaReportCategory(
            com.fds.nsw.nghiepvu.model.VmaReportCategory vmaReportCategory,
            boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaReportCategory(vmaReportCategory, merge);
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
   

    

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaReportCategory> getReportGroups(
            java.lang.String rptGroup, java.lang.String rptLevel, int start, int end) {
        return getService().getReportGroups(rptGroup, rptLevel, start, end);
    }

    public static long countReportGroups(java.lang.String rptGroup,
                                         java.lang.String rptLevel) {
        return getService().countReportGroups(rptGroup, rptLevel);
    }

    public static void clearService() {
        _service = null;
    }

    public static VmaReportCategoryLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(VmaReportCategoryLocalServiceImpl service) {
    }

    private static VmaReportCategoryLocalServiceImpl _service;
}
