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
 * The utility for the vma payment invoice local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.VmaPaymentInvoiceLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see VmaPaymentInvoiceLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.VmaPaymentInvoiceLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.VmaPaymentInvoiceLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.VmaPaymentInvoiceLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class VmaPaymentInvoiceLocalServiceUtil {
    public VmaPaymentInvoiceLocalServiceUtil(VmaPaymentInvoiceLocalServiceImpl service) {
        VmaPaymentInvoiceLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.VmaPaymentInvoiceLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the vma payment invoice to the database. Also notifies the appropriate model listeners.
     *
     * @param vmaPaymentInvoice the vma payment invoice
     * @return the vma payment invoice that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaPaymentInvoice addVmaPaymentInvoice(
            com.fds.nsw.nghiepvu.model.VmaPaymentInvoice vmaPaymentInvoice)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addVmaPaymentInvoice(vmaPaymentInvoice);
    }

    /**
     * Creates a new vma payment invoice with the primary key. Does not add the vma payment invoice to the database.
     *
     * @param id the primary key for the new vma payment invoice
     * @return the new vma payment invoice
     */
    public static com.fds.nsw.nghiepvu.model.VmaPaymentInvoice createVmaPaymentInvoice(
            long id) {
        return getService().createVmaPaymentInvoice(id);
    }

    /**
     * Deletes the vma payment invoice with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the vma payment invoice
     * @return the vma payment invoice that was removed
     * @throws PortalException if a vma payment invoice with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaPaymentInvoice deleteVmaPaymentInvoice(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaPaymentInvoice(id);
    }

    /**
     * Deletes the vma payment invoice from the database. Also notifies the appropriate model listeners.
     *
     * @param vmaPaymentInvoice the vma payment invoice
     * @return the vma payment invoice that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaPaymentInvoice deleteVmaPaymentInvoice(
            com.fds.nsw.nghiepvu.model.VmaPaymentInvoice vmaPaymentInvoice)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaPaymentInvoice(vmaPaymentInvoice);
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
   

    public static com.fds.nsw.nghiepvu.model.VmaPaymentInvoice fetchVmaPaymentInvoice(
            long id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchVmaPaymentInvoice(id);
    }

    /**
     * Returns the vma payment invoice with the primary key.
     *
     * @param id the primary key of the vma payment invoice
     * @return the vma payment invoice
     * @throws PortalException if a vma payment invoice with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaPaymentInvoice getVmaPaymentInvoice(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaPaymentInvoice(id);
    }

    

    /**
     * Returns a range of all the vma payment invoices.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of vma payment invoices
     * @param end the upper bound of the range of vma payment invoices (not inclusive)
     * @return the range of vma payment invoices
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaPaymentInvoice> getVmaPaymentInvoices(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaPaymentInvoices(start, end);
    }

    /**
     * Returns the number of vma payment invoices.
     *
     * @return the number of vma payment invoices
     * @throws SystemException if a system exception occurred
     */
    public static int getVmaPaymentInvoicesCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaPaymentInvoicesCount();
    }

    /**
     * Updates the vma payment invoice in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaPaymentInvoice the vma payment invoice
     * @return the vma payment invoice that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaPaymentInvoice updateVmaPaymentInvoice(
            com.fds.nsw.nghiepvu.model.VmaPaymentInvoice vmaPaymentInvoice)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaPaymentInvoice(vmaPaymentInvoice);
    }

    /**
     * Updates the vma payment invoice in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaPaymentInvoice the vma payment invoice
     * @param merge whether to merge the vma payment invoice with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the vma payment invoice that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaPaymentInvoice updateVmaPaymentInvoice(
            com.fds.nsw.nghiepvu.model.VmaPaymentInvoice vmaPaymentInvoice,
            boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaPaymentInvoice(vmaPaymentInvoice, merge);
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
   

    

    public static com.fds.nsw.nghiepvu.model.VmaPaymentInvoice delete(
            long id)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaPaymentInvoiceException {
        return getService().delete(id);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaPaymentInvoice> findByItineraryNo(
            java.lang.String itineraryNo)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().findByItineraryNo(itineraryNo);
    }

    public static int countByItineraryNo(java.lang.String itineraryNo)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().countByItineraryNo(itineraryNo);
    }

    public static void clearService() {
        _service = null;
    }

    public static VmaPaymentInvoiceLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(VmaPaymentInvoiceLocalServiceImpl service) {
    }

    private static VmaPaymentInvoiceLocalServiceImpl _service;
}
