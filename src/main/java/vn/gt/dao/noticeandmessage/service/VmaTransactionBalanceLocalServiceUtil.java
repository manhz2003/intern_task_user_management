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
 * The utility for the vma transaction balance local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.VmaTransactionBalanceLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see VmaTransactionBalanceLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.VmaTransactionBalanceLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.VmaTransactionBalanceLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.VmaTransactionBalanceLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class VmaTransactionBalanceLocalServiceUtil {
    public VmaTransactionBalanceLocalServiceUtil(VmaTransactionBalanceLocalServiceImpl service) {
        VmaTransactionBalanceLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.VmaTransactionBalanceLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the vma transaction balance to the database. Also notifies the appropriate model listeners.
     *
     * @param vmaTransactionBalance the vma transaction balance
     * @return the vma transaction balance that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionBalance addVmaTransactionBalance(
            com.fds.nsw.nghiepvu.model.VmaTransactionBalance vmaTransactionBalance)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addVmaTransactionBalance(vmaTransactionBalance);
    }

    /**
     * Creates a new vma transaction balance with the primary key. Does not add the vma transaction balance to the database.
     *
     * @param id the primary key for the new vma transaction balance
     * @return the new vma transaction balance
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionBalance createVmaTransactionBalance(
            long id) {
        return getService().createVmaTransactionBalance(id);
    }

    /**
     * Deletes the vma transaction balance with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the vma transaction balance
     * @return the vma transaction balance that was removed
     * @throws PortalException if a vma transaction balance with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionBalance deleteVmaTransactionBalance(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaTransactionBalance(id);
    }

    /**
     * Deletes the vma transaction balance from the database. Also notifies the appropriate model listeners.
     *
     * @param vmaTransactionBalance the vma transaction balance
     * @return the vma transaction balance that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionBalance deleteVmaTransactionBalance(
            com.fds.nsw.nghiepvu.model.VmaTransactionBalance vmaTransactionBalance)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaTransactionBalance(vmaTransactionBalance);
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
   

    public static com.fds.nsw.nghiepvu.model.VmaTransactionBalance fetchVmaTransactionBalance(
            long id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchVmaTransactionBalance(id);
    }

    /**
     * Returns the vma transaction balance with the primary key.
     *
     * @param id the primary key of the vma transaction balance
     * @return the vma transaction balance
     * @throws PortalException if a vma transaction balance with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionBalance getVmaTransactionBalance(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaTransactionBalance(id);
    }

    

    /**
     * Returns a range of all the vma transaction balances.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of vma transaction balances
     * @param end the upper bound of the range of vma transaction balances (not inclusive)
     * @return the range of vma transaction balances
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaTransactionBalance> getVmaTransactionBalances(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaTransactionBalances(start, end);
    }

    /**
     * Returns the number of vma transaction balances.
     *
     * @return the number of vma transaction balances
     * @throws SystemException if a system exception occurred
     */
    public static int getVmaTransactionBalancesCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaTransactionBalancesCount();
    }

    /**
     * Updates the vma transaction balance in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaTransactionBalance the vma transaction balance
     * @return the vma transaction balance that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionBalance updateVmaTransactionBalance(
            com.fds.nsw.nghiepvu.model.VmaTransactionBalance vmaTransactionBalance)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaTransactionBalance(vmaTransactionBalance);
    }

    /**
     * Updates the vma transaction balance in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaTransactionBalance the vma transaction balance
     * @param merge whether to merge the vma transaction balance with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the vma transaction balance that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionBalance updateVmaTransactionBalance(
            com.fds.nsw.nghiepvu.model.VmaTransactionBalance vmaTransactionBalance,
            boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .updateVmaTransactionBalance(vmaTransactionBalance, merge);
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
   

    

    public static void autoInitVmaTransactionBalance(
            java.lang.String portofAuthority, java.lang.String shipAgencyCode,
            java.lang.String shipAgencyName)
            throws com.fds.nsw.kernel.exception.SystemException {
        getService()
                .autoInitVmaTransactionBalance(portofAuthority, shipAgencyCode,
                        shipAgencyName);
    }

    public static com.fds.nsw.nghiepvu.model.VmaTransactionBalance delete(
            long id)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaTransactionBalanceException {
        return getService().delete(id);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaTransactionBalance> findByPortofAuthority(
            java.lang.String portofAuthority)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().findByPortofAuthority(portofAuthority);
    }

    public static int countByPortofAuthority(java.lang.String portofAuthority)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().countByPortofAuthority(portofAuthority);
    }

    public static com.fds.nsw.nghiepvu.model.VmaTransactionBalance findByportofAuthority_transactionTypeCode(
            java.lang.String portofAuthority, java.lang.String transactionTypeCode) {
        return getService()
                .findByportofAuthority_transactionTypeCode(portofAuthority,
                        transactionTypeCode);
    }

    public static com.fds.nsw.nghiepvu.model.VmaTransactionBalance findByportofAuthority_shipAgencyCode_transactionTypeCode(
            java.lang.String portofAuthority, java.lang.String shipAgencyCode,
            java.lang.String transactionTypeCode) {
        return getService()
                .findByportofAuthority_shipAgencyCode_transactionTypeCode(portofAuthority,
                        shipAgencyCode, transactionTypeCode);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaTransactionBalance> findByportofAuthority_shipAgencyCode(
            java.lang.String portofAuthority, java.lang.String shipAgencyCode) {
        return getService()
                .findByportofAuthority_shipAgencyCode(portofAuthority,
                        shipAgencyCode);
    }

    public static void clearService() {
        _service = null;
    }

    public static VmaTransactionBalanceLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(VmaTransactionBalanceLocalServiceImpl service) {
    }

    private static VmaTransactionBalanceLocalServiceImpl _service;
}
