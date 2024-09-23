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
 * The utility for the dm vma pilot category local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.DmVmaPilotCategoryLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see DmVmaPilotCategoryLocalService
 * @see vn.gt.dao.danhmuc.service.base.DmVmaPilotCategoryLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.DmVmaPilotCategoryLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.DmVmaPilotCategoryLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class DmVmaPilotCategoryLocalServiceUtil {
    public DmVmaPilotCategoryLocalServiceUtil(DmVmaPilotCategoryLocalServiceImpl service) {
        DmVmaPilotCategoryLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.DmVmaPilotCategoryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the dm vma pilot category to the database. Also notifies the appropriate model listeners.
     *
     * @param dmVmaPilotCategory the dm vma pilot category
     * @return the dm vma pilot category that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaPilotCategory addDmVmaPilotCategory(
            com.fds.nsw.nghiepvu.model.DmVmaPilotCategory dmVmaPilotCategory)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addDmVmaPilotCategory(dmVmaPilotCategory);
    }

    /**
     * Creates a new dm vma pilot category with the primary key. Does not add the dm vma pilot category to the database.
     *
     * @param Id the primary key for the new dm vma pilot category
     * @return the new dm vma pilot category
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaPilotCategory createDmVmaPilotCategory(
            long Id) {
        return getService().createDmVmaPilotCategory(Id);
    }

    /**
     * Deletes the dm vma pilot category with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param Id the primary key of the dm vma pilot category
     * @return the dm vma pilot category that was removed
     * @throws PortalException if a dm vma pilot category with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaPilotCategory deleteDmVmaPilotCategory(
            long Id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteDmVmaPilotCategory(Id);
    }

    /**
     * Deletes the dm vma pilot category from the database. Also notifies the appropriate model listeners.
     *
     * @param dmVmaPilotCategory the dm vma pilot category
     * @return the dm vma pilot category that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaPilotCategory deleteDmVmaPilotCategory(
            com.fds.nsw.nghiepvu.model.DmVmaPilotCategory dmVmaPilotCategory)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteDmVmaPilotCategory(dmVmaPilotCategory);
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
   

    public static com.fds.nsw.nghiepvu.model.DmVmaPilotCategory fetchDmVmaPilotCategory(
            long Id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchDmVmaPilotCategory(Id);
    }

    /**
     * Returns the dm vma pilot category with the primary key.
     *
     * @param Id the primary key of the dm vma pilot category
     * @return the dm vma pilot category
     * @throws PortalException if a dm vma pilot category with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaPilotCategory getDmVmaPilotCategory(
            long Id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmVmaPilotCategory(Id);
    }

    

    /**
     * Returns a range of all the dm vma pilot categories.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of dm vma pilot categories
     * @param end the upper bound of the range of dm vma pilot categories (not inclusive)
     * @return the range of dm vma pilot categories
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.DmVmaPilotCategory> getDmVmaPilotCategories(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmVmaPilotCategories(start, end);
    }

    /**
     * Returns the number of dm vma pilot categories.
     *
     * @return the number of dm vma pilot categories
     * @throws SystemException if a system exception occurred
     */
    public static int getDmVmaPilotCategoriesCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getDmVmaPilotCategoriesCount();
    }

    /**
     * Updates the dm vma pilot category in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param dmVmaPilotCategory the dm vma pilot category
     * @return the dm vma pilot category that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaPilotCategory updateDmVmaPilotCategory(
            com.fds.nsw.nghiepvu.model.DmVmaPilotCategory dmVmaPilotCategory)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateDmVmaPilotCategory(dmVmaPilotCategory);
    }

    /**
     * Updates the dm vma pilot category in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param dmVmaPilotCategory the dm vma pilot category
     * @param merge whether to merge the dm vma pilot category with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the dm vma pilot category that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.DmVmaPilotCategory updateDmVmaPilotCategory(
            com.fds.nsw.nghiepvu.model.DmVmaPilotCategory dmVmaPilotCategory,
            boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateDmVmaPilotCategory(dmVmaPilotCategory, merge);
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
   

    

    public static com.fds.nsw.nghiepvu.model.DmVmaPilotCategory fetchByPilotCategoryCode(
            java.lang.String pilotCategoryCode) {
        return getService().fetchByPilotCategoryCode(pilotCategoryCode);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.DmVmaPilotCategory> findByPilotCategoryName(
            java.lang.String pilotCategoryName, int start, int end) {
        return getService()
                .findByPilotCategoryName(pilotCategoryName, start, end);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.DmVmaPilotCategory> findPilotCategoryies(
            java.lang.String pilotCategoryName, java.lang.String isDelete,
            java.lang.String pilotCategoryCodeGroup, int start, int end) {
        return getService()
                .findPilotCategoryies(pilotCategoryName, isDelete,
                        pilotCategoryCodeGroup, start, end);
    }

    public static long countPilotCategoryies(
            java.lang.String pilotCategoryName, java.lang.String isDelete,
            java.lang.String pilotCategoryCodeGroup) {
        return getService()
                .countPilotCategoryies(pilotCategoryName, isDelete,
                        pilotCategoryCodeGroup);
    }

    public static com.fds.nsw.nghiepvu.model.DmVmaPilotCategory updateVmaPilotCategory(
            java.lang.String fromMaritimeCode, java.lang.String pilotCategoryCode,
            java.lang.String pilotCategoryName, java.lang.String maxLength,
            java.lang.String safeTime, java.lang.String remarks,
            java.lang.String grossTonage, java.lang.String syncVersion)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchDmVmaPilotCategoryException {
        return getService()
                .updateVmaPilotCategory(fromMaritimeCode, pilotCategoryCode,
                        pilotCategoryName, maxLength, safeTime, remarks, grossTonage,
                        syncVersion);
    }

    public static com.fds.nsw.nghiepvu.model.DmVmaPilotCategory deleteVmaPilotCategory(
            java.lang.String fromMaritimeCode, java.lang.String pilotCategoryCode,
            java.lang.String syncVersion)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchDmVmaPilotCategoryException {
        return getService()
                .deleteVmaPilotCategory(fromMaritimeCode, pilotCategoryCode,
                        syncVersion);
    }

    public static void clearService() {
        _service = null;
    }

    public static DmVmaPilotCategoryLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(DmVmaPilotCategoryLocalServiceImpl service) {
    }

    private static DmVmaPilotCategoryLocalServiceImpl _service;
}
