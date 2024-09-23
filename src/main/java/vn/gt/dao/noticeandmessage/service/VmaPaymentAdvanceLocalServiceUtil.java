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
 * The utility for the vma payment advance local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.VmaPaymentAdvanceLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see VmaPaymentAdvanceLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.VmaPaymentAdvanceLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.VmaPaymentAdvanceLocalServiceImpl
 * @generated
 */
import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.VmaPaymentAdvanceLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class VmaPaymentAdvanceLocalServiceUtil {
    public VmaPaymentAdvanceLocalServiceUtil(VmaPaymentAdvanceLocalServiceImpl service) {
        VmaPaymentAdvanceLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.VmaPaymentAdvanceLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the vma payment advance to the database. Also notifies the appropriate model listeners.
     *
     * @param vmaPaymentAdvance the vma payment advance
     * @return the vma payment advance that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaPaymentAdvance addVmaPaymentAdvance(
            com.fds.nsw.nghiepvu.model.VmaPaymentAdvance vmaPaymentAdvance)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addVmaPaymentAdvance(vmaPaymentAdvance);
    }

    /**
     * Creates a new vma payment advance with the primary key. Does not add the vma payment advance to the database.
     *
     * @param id the primary key for the new vma payment advance
     * @return the new vma payment advance
     */
    public static com.fds.nsw.nghiepvu.model.VmaPaymentAdvance createVmaPaymentAdvance(
            long id) {
        return getService().createVmaPaymentAdvance(id);
    }

    /**
     * Deletes the vma payment advance with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the vma payment advance
     * @return the vma payment advance that was removed
     * @throws PortalException if a vma payment advance with the primary key could not be found
     * @throws SystemException if a system exception occurred
     * @throws com.fds.nsw.nghiepvu.service.exception.NoSuchVmaPaymentAdvanceException
     */
    public static com.fds.nsw.nghiepvu.model.VmaPaymentAdvance deleteVmaPaymentAdvance(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaPaymentAdvanceException {
        return getService().deleteVmaPaymentAdvance(id);
    }

    /**
     * Deletes the vma payment advance from the database. Also notifies the appropriate model listeners.
     *
     * @param vmaPaymentAdvance the vma payment advance
     * @return the vma payment advance that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaPaymentAdvance deleteVmaPaymentAdvance(
            com.fds.nsw.nghiepvu.model.VmaPaymentAdvance vmaPaymentAdvance)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaPaymentAdvance(vmaPaymentAdvance);
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
   

    public static com.fds.nsw.nghiepvu.model.VmaPaymentAdvance fetchVmaPaymentAdvance(
            long id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchVmaPaymentAdvance(id);
    }

    /**
     * Returns the vma payment advance with the primary key.
     *
     * @param id the primary key of the vma payment advance
     * @return the vma payment advance
     * @throws PortalException if a vma payment advance with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaPaymentAdvance getVmaPaymentAdvance(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaPaymentAdvance(id);
    }

    

    /**
     * Returns a range of all the vma payment advances.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of vma payment advances
     * @param end the upper bound of the range of vma payment advances (not inclusive)
     * @return the range of vma payment advances
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaPaymentAdvance> getVmaPaymentAdvances(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaPaymentAdvances(start, end);
    }

    /**
     * Returns the number of vma payment advances.
     *
     * @return the number of vma payment advances
     * @throws SystemException if a system exception occurred
     */
    public static int getVmaPaymentAdvancesCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaPaymentAdvancesCount();
    }

    /**
     * Updates the vma payment advance in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaPaymentAdvance the vma payment advance
     * @return the vma payment advance that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaPaymentAdvance updateVmaPaymentAdvance(
            com.fds.nsw.nghiepvu.model.VmaPaymentAdvance vmaPaymentAdvance)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaPaymentAdvance(vmaPaymentAdvance);
    }

    /**
     * Updates the vma payment advance in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaPaymentAdvance the vma payment advance
     * @param merge whether to merge the vma payment advance with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the vma payment advance that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaPaymentAdvance updateVmaPaymentAdvance(
            com.fds.nsw.nghiepvu.model.VmaPaymentAdvance vmaPaymentAdvance,
            boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaPaymentAdvance(vmaPaymentAdvance, merge);
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
   

    

    public static com.fds.nsw.nghiepvu.model.VmaPaymentAdvance addVmaPaymentAdvance_updateVmaTransactionBalance(
            com.fds.nsw.nghiepvu.model.VmaPaymentAdvance vmaPaymentAdvance,
            com.fds.nsw.nghiepvu.model.VmaTransactionBalance vmaTransactionBalance)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .addVmaPaymentAdvance_updateVmaTransactionBalance(vmaPaymentAdvance,
                        vmaTransactionBalance);
    }


    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaPaymentAdvance> findAll()
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().findAll();
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaPaymentAdvance> findAll(
            int start, int end)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().findAll(start, end);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaPaymentAdvance> findAll(
            int start, int end,
            com.fds.nsw.kernel.util.OrderByComparator orderByComparator)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().findAll(start, end, orderByComparator);
    }

    public static int countByshipAgencyCode(java.lang.String shipAgencyCode)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().countByshipAgencyCode(shipAgencyCode);
    }

    public static int countByshipAgencyCode_paymentType(
            java.lang.String shipAgencyCode, int paymentType)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .countByshipAgencyCode_paymentType(shipAgencyCode,
                        paymentType);
    }

    public static int countBytransactionTypeCode(
            java.lang.String transactionTypeCode)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().countBytransactionTypeCode(transactionTypeCode);
    }

    public static int countByshipAgencyCode_transactionTypeCode(
            java.lang.String shipAgencyCode, java.lang.String transactionTypeCode)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .countByshipAgencyCode_transactionTypeCode(shipAgencyCode,
                        transactionTypeCode);
    }

    public static int countByportofAuthority(java.lang.String portofAuthority)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().countByportofAuthority(portofAuthority);
    }

    public static boolean rutquy(
            com.fds.nsw.nghiepvu.model.VmaPaymentAdvance vmaPaymentAdvance,
            java.lang.String itineraryNo, java.lang.String documentaryCode,
            java.lang.String portofAuthority, java.lang.String transactionTypeCode,
            java.lang.String shipAgencyCode, java.lang.String departmentCode)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .rutquy(vmaPaymentAdvance, itineraryNo, documentaryCode,
                        portofAuthority, transactionTypeCode, shipAgencyCode, departmentCode);
    }

    public static boolean xacnhanthanhtoan(
            com.fds.nsw.nghiepvu.model.VmaPaymentAdvance vmaPaymentAdvance,
            java.lang.String itineraryNo, java.lang.String documentaryCode,
            java.lang.String portofAuthority, java.lang.String transactionTypeCode,
            java.lang.String shipAgencyCode, java.lang.String departmentCode)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .xacnhanthanhtoan(vmaPaymentAdvance, itineraryNo,
                        documentaryCode, portofAuthority, transactionTypeCode,
                        shipAgencyCode, departmentCode);
    }

    public static boolean noptien(
            com.fds.nsw.nghiepvu.model.VmaPaymentAdvance vmaPaymentAdvance,
            java.lang.String itineraryNo, java.lang.String documentaryCode,
            java.lang.String portofAuthority, java.lang.String transactionTypeCode,
            java.lang.String shipAgencyCode, java.lang.String departmentCode)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .noptien(vmaPaymentAdvance, itineraryNo, documentaryCode,
                        portofAuthority, transactionTypeCode, shipAgencyCode, departmentCode);
    }

    public static boolean huytinhphi(java.lang.String itineraryNo,
                                     java.lang.String documentaryCode, java.lang.String portofAuthority,
                                     java.lang.String transactionTypeCode, java.lang.String shipAgencyCode,
                                     java.lang.String departmentCode)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .huytinhphi(itineraryNo, documentaryCode, portofAuthority,
                        transactionTypeCode, shipAgencyCode, departmentCode);
    }

    public static boolean xacnhantinhphi(java.lang.String itineraryNo,
                                         java.lang.String documentaryCode, java.lang.String portofAuthority,
                                         java.lang.String transactionTypeCode, java.lang.String shipAgencyCode,
                                         java.lang.String departmentCode)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .xacnhantinhphi(itineraryNo, documentaryCode,
                        portofAuthority, transactionTypeCode, shipAgencyCode, departmentCode);
    }

    public static void clearService() {
        _service = null;
    }

    public static VmaPaymentAdvanceLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(VmaPaymentAdvanceLocalServiceImpl service) {
    }

    private static VmaPaymentAdvanceLocalServiceImpl _service;
}
