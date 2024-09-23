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
 * The utility for the vma transaction function local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.VmaTransactionFunctionLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see VmaTransactionFunctionLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.VmaTransactionFunctionLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.VmaTransactionFunctionLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.VmaTransactionFunctionLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class VmaTransactionFunctionLocalServiceUtil {
    public VmaTransactionFunctionLocalServiceUtil(VmaTransactionFunctionLocalServiceImpl service) {
        VmaTransactionFunctionLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.VmaTransactionFunctionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the vma transaction function to the database. Also notifies the appropriate model listeners.
     *
     * @param vmaTransactionFunction the vma transaction function
     * @return the vma transaction function that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionFunction addVmaTransactionFunction(
            com.fds.nsw.nghiepvu.model.VmaTransactionFunction vmaTransactionFunction)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addVmaTransactionFunction(vmaTransactionFunction);
    }

    /**
     * Creates a new vma transaction function with the primary key. Does not add the vma transaction function to the database.
     *
     * @param id the primary key for the new vma transaction function
     * @return the new vma transaction function
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionFunction createVmaTransactionFunction(
            long id) {
        return getService().createVmaTransactionFunction(id);
    }

    /**
     * Deletes the vma transaction function with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the vma transaction function
     * @return the vma transaction function that was removed
     * @throws PortalException if a vma transaction function with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionFunction deleteVmaTransactionFunction(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaTransactionFunction(id);
    }

    /**
     * Deletes the vma transaction function from the database. Also notifies the appropriate model listeners.
     *
     * @param vmaTransactionFunction the vma transaction function
     * @return the vma transaction function that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionFunction deleteVmaTransactionFunction(
            com.fds.nsw.nghiepvu.model.VmaTransactionFunction vmaTransactionFunction)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaTransactionFunction(vmaTransactionFunction);
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
   

    public static com.fds.nsw.nghiepvu.model.VmaTransactionFunction fetchVmaTransactionFunction(
            long id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchVmaTransactionFunction(id);
    }

    /**
     * Returns the vma transaction function with the primary key.
     *
     * @param id the primary key of the vma transaction function
     * @return the vma transaction function
     * @throws PortalException if a vma transaction function with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionFunction getVmaTransactionFunction(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaTransactionFunction(id);
    }

    

    /**
     * Returns a range of all the vma transaction functions.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of vma transaction functions
     * @param end the upper bound of the range of vma transaction functions (not inclusive)
     * @return the range of vma transaction functions
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaTransactionFunction> getVmaTransactionFunctions(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaTransactionFunctions(start, end);
    }

    /**
     * Returns the number of vma transaction functions.
     *
     * @return the number of vma transaction functions
     * @throws SystemException if a system exception occurred
     */
    public static int getVmaTransactionFunctionsCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaTransactionFunctionsCount();
    }

    /**
     * Updates the vma transaction function in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaTransactionFunction the vma transaction function
     * @return the vma transaction function that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionFunction updateVmaTransactionFunction(
            com.fds.nsw.nghiepvu.model.VmaTransactionFunction vmaTransactionFunction)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaTransactionFunction(vmaTransactionFunction);
    }

    /**
     * Updates the vma transaction function in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaTransactionFunction the vma transaction function
     * @param merge whether to merge the vma transaction function with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the vma transaction function that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionFunction updateVmaTransactionFunction(
            com.fds.nsw.nghiepvu.model.VmaTransactionFunction vmaTransactionFunction,
            boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .updateVmaTransactionFunction(vmaTransactionFunction, merge);
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
   

    

    public static com.fds.nsw.nghiepvu.model.VmaTransactionFunction delete(
            long id)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaTransactionFunctionException {
        return getService().delete(id);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaTransactionFunction> findByPortofAuthority(
            java.lang.String portofAuthority)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().findByPortofAuthority(portofAuthority);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaTransactionFunction> findByPortofAuthority(
            java.lang.String portofAuthority, int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().findByPortofAuthority(portofAuthority, start, end);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaTransactionFunction> findByPortofAuthority(
            java.lang.String portofAuthority, int start, int end,
            com.fds.nsw.kernel.util.OrderByComparator orderByComparator)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findByPortofAuthority(portofAuthority, start, end,
                        orderByComparator);
    }

    public static int countByPortofAuthority(java.lang.String portofAuthority)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().countByPortofAuthority(portofAuthority);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaTransactionFunction> findVmaTransactionFunctionByCustomSQL(
            java.lang.String sql, int start, int end) {
        return getService()
                .findVmaTransactionFunctionByCustomSQL(sql, start, end);
    }

    public static org.json.JSONObject findVmaTransactionFunction(
            java.lang.String searchQuery, java.lang.String countQuery, int start,
            int end)
            throws com.fds.nsw.kernel.exception.SystemException,
            org.json.JSONException {
        return getService()
                .findVmaTransactionFunction(searchQuery, countQuery, start,
                        end);
    }

    public static long countVmaTransactionFunction(java.lang.String sql)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().countVmaTransactionFunction(sql);
    }

    public static com.fds.nsw.nghiepvu.model.VmaTransactionFunction findByPortofAuthority_transactionTypeCode(
            java.lang.String portofAuthority, java.lang.String transactionTypeCode)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findByPortofAuthority_transactionTypeCode(portofAuthority,
                        transactionTypeCode);
    }

    public static void clearService() {
        _service = null;
    }

    public static VmaTransactionFunctionLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(VmaTransactionFunctionLocalServiceImpl service) {
    }

    private static VmaTransactionFunctionLocalServiceImpl _service;
}
