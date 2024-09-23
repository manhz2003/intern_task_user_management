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
 * The utility for the vma transaction department local service. This utility wraps {@link vn.gt.dao.danhmuc.service.impl.VmaTransactionDepartmentLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see VmaTransactionDepartmentLocalService
 * @see vn.gt.dao.danhmuc.service.base.VmaTransactionDepartmentLocalServiceBaseImpl
 * @see vn.gt.dao.danhmuc.service.impl.VmaTransactionDepartmentLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.danhmuc.service.impl.VmaTransactionDepartmentLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class VmaTransactionDepartmentLocalServiceUtil {
    public VmaTransactionDepartmentLocalServiceUtil(VmaTransactionDepartmentLocalServiceImpl service) {
        VmaTransactionDepartmentLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.danhmuc.service.impl.VmaTransactionDepartmentLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the vma transaction department to the database. Also notifies the appropriate model listeners.
     *
     * @param vmaTransactionDepartment the vma transaction department
     * @return the vma transaction department that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionDepartment addVmaTransactionDepartment(
            com.fds.nsw.nghiepvu.model.VmaTransactionDepartment vmaTransactionDepartment)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addVmaTransactionDepartment(vmaTransactionDepartment);
    }

    /**
     * Creates a new vma transaction department with the primary key. Does not add the vma transaction department to the database.
     *
     * @param id the primary key for the new vma transaction department
     * @return the new vma transaction department
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionDepartment createVmaTransactionDepartment(
            long id) {
        return getService().createVmaTransactionDepartment(id);
    }

    /**
     * Deletes the vma transaction department with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the vma transaction department
     * @return the vma transaction department that was removed
     * @throws PortalException if a vma transaction department with the primary key could not be found
     * @throws SystemException if a system exception occurred
     * @throws com.fds.nsw.nghiepvu.service.exception.NoSuchVmaTransactionDepartmentException
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionDepartment deleteVmaTransactionDepartment(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaTransactionDepartmentException {
        return getService().deleteVmaTransactionDepartment(id);
    }

    /**
     * Deletes the vma transaction department from the database. Also notifies the appropriate model listeners.
     *
     * @param vmaTransactionDepartment the vma transaction department
     * @return the vma transaction department that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionDepartment deleteVmaTransactionDepartment(
            com.fds.nsw.nghiepvu.model.VmaTransactionDepartment vmaTransactionDepartment)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .deleteVmaTransactionDepartment(vmaTransactionDepartment);
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
   

    public static com.fds.nsw.nghiepvu.model.VmaTransactionDepartment fetchVmaTransactionDepartment(
            long id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchVmaTransactionDepartment(id);
    }

    /**
     * Returns the vma transaction department with the primary key.
     *
     * @param id the primary key of the vma transaction department
     * @return the vma transaction department
     * @throws PortalException if a vma transaction department with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionDepartment getVmaTransactionDepartment(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaTransactionDepartment(id);
    }

    

    /**
     * Returns a range of all the vma transaction departments.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of vma transaction departments
     * @param end the upper bound of the range of vma transaction departments (not inclusive)
     * @return the range of vma transaction departments
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaTransactionDepartment> getVmaTransactionDepartments(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaTransactionDepartments(start, end);
    }

    /**
     * Returns the number of vma transaction departments.
     *
     * @return the number of vma transaction departments
     * @throws SystemException if a system exception occurred
     */
    public static int getVmaTransactionDepartmentsCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaTransactionDepartmentsCount();
    }

    /**
     * Updates the vma transaction department in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaTransactionDepartment the vma transaction department
     * @return the vma transaction department that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionDepartment updateVmaTransactionDepartment(
            com.fds.nsw.nghiepvu.model.VmaTransactionDepartment vmaTransactionDepartment)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .updateVmaTransactionDepartment(vmaTransactionDepartment);
    }

    /**
     * Updates the vma transaction department in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaTransactionDepartment the vma transaction department
     * @param merge whether to merge the vma transaction department with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the vma transaction department that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionDepartment updateVmaTransactionDepartment(
            com.fds.nsw.nghiepvu.model.VmaTransactionDepartment vmaTransactionDepartment,
            boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .updateVmaTransactionDepartment(vmaTransactionDepartment,
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
   

    

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaTransactionDepartment> findByF_portOfAuthority(
            java.lang.String portOfAuthority)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().findByF_portOfAuthority(portOfAuthority);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaTransactionDepartment> findVmaTransactionDepartmentByDepartmentCodes(
            java.lang.String portofAuthority,
            java.util.List<java.lang.String> departmentCodes) {
        return getService()
                .findVmaTransactionDepartmentByDepartmentCodes(portofAuthority,
                        departmentCodes);
    }

    public static java.util.List<java.lang.String> checkExistDepartmentCode(
            java.lang.String portofAuthority) {
        return getService().checkExistDepartmentCode(portofAuthority);
    }

    public static java.util.List<java.lang.String[]> getDepartmentInfo(
            java.lang.String portofAuthority) {
        return getService().getDepartmentInfo(portofAuthority);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaTransactionDepartment> findVmaTransactionDepartments(
            java.lang.String portOfAuthority, java.lang.String departmentName,
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findVmaTransactionDepartments(portOfAuthority,
                        departmentName, start, end);
    }

    public static long coutVmaTransactionDepartments(
            java.lang.String portOfAuthority, java.lang.String departmentName)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .coutVmaTransactionDepartments(portOfAuthority,
                        departmentName);
    }

    public static com.fds.nsw.nghiepvu.model.VmaTransactionDepartment fetchVmaTransactionDepartment(
            java.lang.String departmentCode) {
        return getService().fetchVmaTransactionDepartment(departmentCode);
    }

    public static com.fds.nsw.nghiepvu.model.VmaTransactionDepartment updateVmaTransactionDepartment(
            java.lang.String portOfAuthority, java.lang.String departmentCode,
            java.lang.String departmentName, int sequenceNo,
            java.lang.String transactionTypeVND, java.lang.String transactionTypeUSD)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .updateVmaTransactionDepartment(portOfAuthority,
                        departmentCode, departmentName, sequenceNo, transactionTypeVND,
                        transactionTypeUSD);
    }


    public static void clearService() {
        _service = null;
    }

    public static VmaTransactionDepartmentLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(VmaTransactionDepartmentLocalServiceImpl service) {
    }

    private static VmaTransactionDepartmentLocalServiceImpl _service;
}
