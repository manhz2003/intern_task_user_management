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
 * The utility for the vma transaction type local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.VmaTransactionTypeLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see VmaTransactionTypeLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.VmaTransactionTypeLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.VmaTransactionTypeLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.VmaTransactionTypeLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class VmaTransactionTypeLocalServiceUtil {
    public VmaTransactionTypeLocalServiceUtil(VmaTransactionTypeLocalServiceImpl service) {
        VmaTransactionTypeLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.VmaTransactionTypeLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the vma transaction type to the database. Also notifies the appropriate model listeners.
     *
     * @param vmaTransactionType the vma transaction type
     * @return the vma transaction type that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionType addVmaTransactionType(
            com.fds.nsw.nghiepvu.model.VmaTransactionType vmaTransactionType)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addVmaTransactionType(vmaTransactionType);
    }

    /**
     * Creates a new vma transaction type with the primary key. Does not add the vma transaction type to the database.
     *
     * @param id the primary key for the new vma transaction type
     * @return the new vma transaction type
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionType createVmaTransactionType(
            long id) {
        return getService().createVmaTransactionType(id);
    }

    /**
     * Deletes the vma transaction type with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the vma transaction type
     * @return the vma transaction type that was removed
     * @throws PortalException if a vma transaction type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionType deleteVmaTransactionType(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaTransactionType(id);
    }

    /**
     * Deletes the vma transaction type from the database. Also notifies the appropriate model listeners.
     *
     * @param vmaTransactionType the vma transaction type
     * @return the vma transaction type that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionType deleteVmaTransactionType(
            com.fds.nsw.nghiepvu.model.VmaTransactionType vmaTransactionType)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaTransactionType(vmaTransactionType);
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
   

    public static com.fds.nsw.nghiepvu.model.VmaTransactionType fetchVmaTransactionType(
            long id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchVmaTransactionType(id);
    }

    /**
     * Returns the vma transaction type with the primary key.
     *
     * @param id the primary key of the vma transaction type
     * @return the vma transaction type
     * @throws PortalException if a vma transaction type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionType getVmaTransactionType(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaTransactionType(id);
    }

    

    /**
     * Returns a range of all the vma transaction types.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of vma transaction types
     * @param end the upper bound of the range of vma transaction types (not inclusive)
     * @return the range of vma transaction types
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaTransactionType> getVmaTransactionTypes(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaTransactionTypes(start, end);
    }

    /**
     * Returns the number of vma transaction types.
     *
     * @return the number of vma transaction types
     * @throws SystemException if a system exception occurred
     */
    public static int getVmaTransactionTypesCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaTransactionTypesCount();
    }

    /**
     * Updates the vma transaction type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaTransactionType the vma transaction type
     * @return the vma transaction type that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionType updateVmaTransactionType(
            com.fds.nsw.nghiepvu.model.VmaTransactionType vmaTransactionType)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaTransactionType(vmaTransactionType);
    }

    /**
     * Updates the vma transaction type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaTransactionType the vma transaction type
     * @param merge whether to merge the vma transaction type with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the vma transaction type that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionType updateVmaTransactionType(
            com.fds.nsw.nghiepvu.model.VmaTransactionType vmaTransactionType,
            boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaTransactionType(vmaTransactionType, merge);
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
   

    

    public static com.fds.nsw.nghiepvu.model.VmaTransactionType delete(
            long id)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaTransactionTypeException {
        return getService().delete(id);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaTransactionType> findByPortofAuthority(
            java.lang.String portofAuthority)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().findByPortofAuthority(portofAuthority);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaTransactionType> findByPortofAuthority(
            java.lang.String portofAuthority, int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().findByPortofAuthority(portofAuthority, start, end);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaTransactionType> findByPortofAuthority(
            java.lang.String portofAuthority, int start, int end,
            com.fds.nsw.kernel.util.OrderByComparator orderByComparator)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findByPortofAuthority(portofAuthority, start, end,
                        orderByComparator);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaTransactionType> findByPortofAuthority_transactionLevel(
            java.lang.String portofAuthority, int transactionLevel) {
        return getService()
                .findByPortofAuthority_transactionLevel(portofAuthority,
                        transactionLevel);
    }

    public static int countByPortofAuthority(java.lang.String portofAuthority)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().countByPortofAuthority(portofAuthority);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaTransactionType> findVmaTransactionTypeByTransactionTypeCodes(
            java.lang.String portofAuthority,
            java.util.List<java.lang.Integer> transactionTypeCodes) {
        return getService()
                .findVmaTransactionTypeByTransactionTypeCodes(portofAuthority,
                        transactionTypeCodes);
    }

    public static java.util.List<java.lang.Integer> checkExistTransactionType(
            java.lang.String portofAuthority, java.lang.String shipAgencyCode) {
        return getService()
                .checkExistTransactionType(portofAuthority, shipAgencyCode);
    }

    public static org.json.JSONObject findVmaTransactionType(
            java.lang.String searchQuery, java.lang.String countQuery, int start,
            int end)
            throws com.fds.nsw.kernel.exception.SystemException,
            org.json.JSONException {
        return getService()
                .findVmaTransactionType(searchQuery, countQuery, start, end);
    }

    public static long countVmaTransactionType(java.lang.String sql)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().countVmaTransactionType(sql);
    }

    public static void clearService() {
        _service = null;
    }

    public static VmaTransactionTypeLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(VmaTransactionTypeLocalServiceImpl service) {
    }

    private static VmaTransactionTypeLocalServiceImpl _service;
}
