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
 * The utility for the vma transaction slip details local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.VmaTransactionSlipDetailsLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see VmaTransactionSlipDetailsLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.VmaTransactionSlipDetailsLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.VmaTransactionSlipDetailsLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.VmaTransactionSlipDetailsLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class VmaTransactionSlipDetailsLocalServiceUtil {
    public VmaTransactionSlipDetailsLocalServiceUtil(VmaTransactionSlipDetailsLocalServiceImpl service) {
        VmaTransactionSlipDetailsLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.VmaTransactionSlipDetailsLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the vma transaction slip details to the database. Also notifies the appropriate model listeners.
     *
     * @param vmaTransactionSlipDetails the vma transaction slip details
     * @return the vma transaction slip details that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionSlipDetails addVmaTransactionSlipDetails(
            com.fds.nsw.nghiepvu.model.VmaTransactionSlipDetails vmaTransactionSlipDetails)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .addVmaTransactionSlipDetails(vmaTransactionSlipDetails);
    }

    /**
     * Creates a new vma transaction slip details with the primary key. Does not add the vma transaction slip details to the database.
     *
     * @param id the primary key for the new vma transaction slip details
     * @return the new vma transaction slip details
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionSlipDetails createVmaTransactionSlipDetails(
            long id) {
        return getService().createVmaTransactionSlipDetails(id);
    }

    /**
     * Deletes the vma transaction slip details with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the vma transaction slip details
     * @return the vma transaction slip details that was removed
     * @throws PortalException if a vma transaction slip details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     * @throws com.fds.nsw.nghiepvu.service.exception.NoSuchVmaTransactionSlipDetailsException
     */


    /**
     * Deletes the vma transaction slip details from the database. Also notifies the appropriate model listeners.
     *
     * @param vmaTransactionSlipDetails the vma transaction slip details
     * @return the vma transaction slip details that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionSlipDetails deleteVmaTransactionSlipDetails(
            com.fds.nsw.nghiepvu.model.VmaTransactionSlipDetails vmaTransactionSlipDetails)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .deleteVmaTransactionSlipDetails(vmaTransactionSlipDetails);
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
   

    public static com.fds.nsw.nghiepvu.model.VmaTransactionSlipDetails fetchVmaTransactionSlipDetails(
            long id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchVmaTransactionSlipDetails(id);
    }

    /**
     * Returns the vma transaction slip details with the primary key.
     *
     * @param id the primary key of the vma transaction slip details
     * @return the vma transaction slip details
     * @throws PortalException if a vma transaction slip details with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionSlipDetails getVmaTransactionSlipDetails(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaTransactionSlipDetails(id);
    }

    

    /**
     * Returns a range of all the vma transaction slip detailses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of vma transaction slip detailses
     * @param end the upper bound of the range of vma transaction slip detailses (not inclusive)
     * @return the range of vma transaction slip detailses
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaTransactionSlipDetails> getVmaTransactionSlipDetailses(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaTransactionSlipDetailses(start, end);
    }

    /**
     * Returns the number of vma transaction slip detailses.
     *
     * @return the number of vma transaction slip detailses
     * @throws SystemException if a system exception occurred
     */
    public static int getVmaTransactionSlipDetailsesCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaTransactionSlipDetailsesCount();
    }

    /**
     * Updates the vma transaction slip details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaTransactionSlipDetails the vma transaction slip details
     * @return the vma transaction slip details that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionSlipDetails updateVmaTransactionSlipDetails(
            com.fds.nsw.nghiepvu.model.VmaTransactionSlipDetails vmaTransactionSlipDetails)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .updateVmaTransactionSlipDetails(vmaTransactionSlipDetails);
    }

    /**
     * Updates the vma transaction slip details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaTransactionSlipDetails the vma transaction slip details
     * @param merge whether to merge the vma transaction slip details with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the vma transaction slip details that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionSlipDetails updateVmaTransactionSlipDetails(
            com.fds.nsw.nghiepvu.model.VmaTransactionSlipDetails vmaTransactionSlipDetails,
            boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .updateVmaTransactionSlipDetails(vmaTransactionSlipDetails,
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
   

    

    public static void deleteVmaTransactionSlipDetails(long id)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaTransactionSlipDetailsException {
        getService().deleteVmaTransactionSlipDetails(id);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaTransactionSlipDetails> findAll()
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().findAll();
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaTransactionSlipDetails> findAll(
            int start, int end)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().findAll(start, end);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaTransactionSlipDetails> findAll(
            int start, int end,
            com.fds.nsw.kernel.util.OrderByComparator orderByComparator)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().findAll(start, end, orderByComparator);
    }

    public static int countByItineraryNo(java.lang.String itineraryNo)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().countByItineraryNo(itineraryNo);
    }

    public static com.fds.nsw.nghiepvu.model.VmaTransactionSlipDetails fetchByItineraryNo_DocumentaryCode(
            java.lang.String itineraryNo, java.lang.String documentaryCode)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .fetchByItineraryNo_DocumentaryCode(itineraryNo,
                        documentaryCode);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaTransactionSlipDetails> findByItineraryNo(
            java.lang.String itineraryNo)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().findByItineraryNo(itineraryNo);
    }

    public static void clearService() {
        _service = null;
    }

    public static VmaTransactionSlipDetailsLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(VmaTransactionSlipDetailsLocalServiceImpl service) {
    }

    private static VmaTransactionSlipDetailsLocalServiceImpl _service;
}
