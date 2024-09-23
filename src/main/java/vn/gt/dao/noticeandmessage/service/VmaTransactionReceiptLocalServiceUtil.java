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
 * The utility for the vma transaction receipt local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.VmaTransactionReceiptLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see VmaTransactionReceiptLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.VmaTransactionReceiptLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.VmaTransactionReceiptLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.VmaTransactionReceiptLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class VmaTransactionReceiptLocalServiceUtil {
    public VmaTransactionReceiptLocalServiceUtil(VmaTransactionReceiptLocalServiceImpl service) {
        VmaTransactionReceiptLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.VmaTransactionReceiptLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the vma transaction receipt to the database. Also notifies the appropriate model listeners.
     *
     * @param vmaTransactionReceipt the vma transaction receipt
     * @return the vma transaction receipt that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionReceipt addVmaTransactionReceipt(
            com.fds.nsw.nghiepvu.model.VmaTransactionReceipt vmaTransactionReceipt)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addVmaTransactionReceipt(vmaTransactionReceipt);
    }

    /**
     * Creates a new vma transaction receipt with the primary key. Does not add the vma transaction receipt to the database.
     *
     * @param id the primary key for the new vma transaction receipt
     * @return the new vma transaction receipt
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionReceipt createVmaTransactionReceipt(
            long id) {
        return getService().createVmaTransactionReceipt(id);
    }

    /**
     * Deletes the vma transaction receipt with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the vma transaction receipt
     * @return the vma transaction receipt that was removed
     * @throws PortalException if a vma transaction receipt with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionReceipt deleteVmaTransactionReceipt(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaTransactionReceipt(id);
    }

    /**
     * Deletes the vma transaction receipt from the database. Also notifies the appropriate model listeners.
     *
     * @param vmaTransactionReceipt the vma transaction receipt
     * @return the vma transaction receipt that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionReceipt deleteVmaTransactionReceipt(
            com.fds.nsw.nghiepvu.model.VmaTransactionReceipt vmaTransactionReceipt)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaTransactionReceipt(vmaTransactionReceipt);
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
   

    public static com.fds.nsw.nghiepvu.model.VmaTransactionReceipt fetchVmaTransactionReceipt(
            long id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchVmaTransactionReceipt(id);
    }

    /**
     * Returns the vma transaction receipt with the primary key.
     *
     * @param id the primary key of the vma transaction receipt
     * @return the vma transaction receipt
     * @throws PortalException if a vma transaction receipt with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionReceipt getVmaTransactionReceipt(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaTransactionReceipt(id);
    }

    

    /**
     * Returns a range of all the vma transaction receipts.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of vma transaction receipts
     * @param end the upper bound of the range of vma transaction receipts (not inclusive)
     * @return the range of vma transaction receipts
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaTransactionReceipt> getVmaTransactionReceipts(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaTransactionReceipts(start, end);
    }

    /**
     * Returns the number of vma transaction receipts.
     *
     * @return the number of vma transaction receipts
     * @throws SystemException if a system exception occurred
     */
    public static int getVmaTransactionReceiptsCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaTransactionReceiptsCount();
    }

    /**
     * Updates the vma transaction receipt in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaTransactionReceipt the vma transaction receipt
     * @return the vma transaction receipt that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionReceipt updateVmaTransactionReceipt(
            com.fds.nsw.nghiepvu.model.VmaTransactionReceipt vmaTransactionReceipt)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaTransactionReceipt(vmaTransactionReceipt);
    }

    /**
     * Updates the vma transaction receipt in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaTransactionReceipt the vma transaction receipt
     * @param merge whether to merge the vma transaction receipt with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the vma transaction receipt that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionReceipt updateVmaTransactionReceipt(
            com.fds.nsw.nghiepvu.model.VmaTransactionReceipt vmaTransactionReceipt,
            boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .updateVmaTransactionReceipt(vmaTransactionReceipt, merge);
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
   

    

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaTransactionReceipt> findByMaritimeCode(
            java.lang.String maritimeCode) {
        return getService().findByMaritimeCode(maritimeCode);
    }

    public static void clearService() {
        _service = null;
    }

    public static VmaTransactionReceiptLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(VmaTransactionReceiptLocalServiceImpl service) {
    }

    private static VmaTransactionReceiptLocalServiceImpl _service;
}
