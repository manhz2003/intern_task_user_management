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
 * The utility for the vma schedule transfer local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.VmaScheduleTransferLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see VmaScheduleTransferLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.VmaScheduleTransferLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.VmaScheduleTransferLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.VmaScheduleTransferLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class VmaScheduleTransferLocalServiceUtil {
    public VmaScheduleTransferLocalServiceUtil(VmaScheduleTransferLocalServiceImpl service) {
        VmaScheduleTransferLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.VmaScheduleTransferLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the vma schedule transfer to the database. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleTransfer the vma schedule transfer
     * @return the vma schedule transfer that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleTransfer addVmaScheduleTransfer(
            com.fds.nsw.nghiepvu.model.VmaScheduleTransfer vmaScheduleTransfer)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addVmaScheduleTransfer(vmaScheduleTransfer);
    }

    /**
     * Creates a new vma schedule transfer with the primary key. Does not add the vma schedule transfer to the database.
     *
     * @param id the primary key for the new vma schedule transfer
     * @return the new vma schedule transfer
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleTransfer createVmaScheduleTransfer(
            long id) {
        return getService().createVmaScheduleTransfer(id);
    }

    /**
     * Deletes the vma schedule transfer with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the vma schedule transfer
     * @return the vma schedule transfer that was removed
     * @throws PortalException if a vma schedule transfer with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleTransfer deleteVmaScheduleTransfer(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaScheduleTransfer(id);
    }

    /**
     * Deletes the vma schedule transfer from the database. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleTransfer the vma schedule transfer
     * @return the vma schedule transfer that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleTransfer deleteVmaScheduleTransfer(
            com.fds.nsw.nghiepvu.model.VmaScheduleTransfer vmaScheduleTransfer)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaScheduleTransfer(vmaScheduleTransfer);
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
   

    public static com.fds.nsw.nghiepvu.model.VmaScheduleTransfer fetchVmaScheduleTransfer(
            long id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchVmaScheduleTransfer(id);
    }

    /**
     * Returns the vma schedule transfer with the primary key.
     *
     * @param id the primary key of the vma schedule transfer
     * @return the vma schedule transfer
     * @throws PortalException if a vma schedule transfer with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleTransfer getVmaScheduleTransfer(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaScheduleTransfer(id);
    }

    

    /**
     * Returns a range of all the vma schedule transfers.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of vma schedule transfers
     * @param end the upper bound of the range of vma schedule transfers (not inclusive)
     * @return the range of vma schedule transfers
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaScheduleTransfer> getVmaScheduleTransfers(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaScheduleTransfers(start, end);
    }

    /**
     * Returns the number of vma schedule transfers.
     *
     * @return the number of vma schedule transfers
     * @throws SystemException if a system exception occurred
     */
    public static int getVmaScheduleTransfersCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaScheduleTransfersCount();
    }

    /**
     * Updates the vma schedule transfer in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleTransfer the vma schedule transfer
     * @return the vma schedule transfer that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleTransfer updateVmaScheduleTransfer(
            com.fds.nsw.nghiepvu.model.VmaScheduleTransfer vmaScheduleTransfer)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaScheduleTransfer(vmaScheduleTransfer);
    }

    /**
     * Updates the vma schedule transfer in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleTransfer the vma schedule transfer
     * @param merge whether to merge the vma schedule transfer with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the vma schedule transfer that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleTransfer updateVmaScheduleTransfer(
            com.fds.nsw.nghiepvu.model.VmaScheduleTransfer vmaScheduleTransfer,
            boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaScheduleTransfer(vmaScheduleTransfer, merge);
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
   

    

    public static com.fds.nsw.nghiepvu.model.VmaScheduleTransfer updayeVmaScheduleTransfer(
            com.fds.nsw.nghiepvu.model.VmaScheduleTransfer vmaScheduleTransfer)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updayeVmaScheduleTransfer(vmaScheduleTransfer);
    }

    public static int countByCallSign(java.lang.String callSign)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().countByCallSign(callSign);
    }

    public static int countByIMONumber(java.lang.String imoNumber)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().countByIMONumber(imoNumber);
    }

    public static int countByIMONumber_CallSign(java.lang.String imoNumber,
                                                java.lang.String callSign)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().countByIMONumber_CallSign(imoNumber, callSign);
    }

    public static int countByRegistryNumber(java.lang.String registryNumber)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().countByRegistryNumber(registryNumber);
    }

    public static int countByVRCode(java.lang.String vrCode)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().countByVRCode(vrCode);
    }

    public static int countByVRCode_RegistryNumber(java.lang.String imoNumber,
                                                   java.lang.String registryNumber)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .countByVRCode_RegistryNumber(imoNumber, registryNumber);
    }

    public static com.fds.nsw.nghiepvu.model.VmaScheduleTransfer findByCallSign(
            java.lang.String callSign)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaScheduleTransferException {
        return getService().findByCallSign(callSign);
    }

    public static com.fds.nsw.nghiepvu.model.VmaScheduleTransfer fetchByCallSign(
            java.lang.String callSign)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchByCallSign(callSign);
    }

    public static com.fds.nsw.nghiepvu.model.VmaScheduleTransfer findByIMONumber(
            java.lang.String imoNumber)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaScheduleTransferException {
        return getService().findByIMONumber(imoNumber);
    }

    public static com.fds.nsw.nghiepvu.model.VmaScheduleTransfer fetchByIMONumber(
            java.lang.String imoNumber)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchByIMONumber(imoNumber);
    }

    public static com.fds.nsw.nghiepvu.model.VmaScheduleTransfer findByIMONumber_CallSign(
            java.lang.String imoNumber, java.lang.String callSign)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaScheduleTransferException {
        return getService().findByIMONumber_CallSign(imoNumber, callSign);
    }

    public static com.fds.nsw.nghiepvu.model.VmaScheduleTransfer fetchByIMONumber_CallSign(
            java.lang.String imoNumber, java.lang.String callSign)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchByIMONumber_CallSign(imoNumber, callSign);
    }

    public static com.fds.nsw.nghiepvu.model.VmaScheduleTransfer findByRegistryNumber(
            java.lang.String registryNumber)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaScheduleTransferException {
        return getService().findByRegistryNumber(registryNumber);
    }

    public static com.fds.nsw.nghiepvu.model.VmaScheduleTransfer fetchByRegistryNumber(
            java.lang.String registryNumber)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaScheduleTransferException {
        return getService().fetchByRegistryNumber(registryNumber);
    }

    public static com.fds.nsw.nghiepvu.model.VmaScheduleTransfer findByVRCode(
            java.lang.String vrCode)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaScheduleTransferException {
        return getService().findByVRCode(vrCode);
    }

    public static com.fds.nsw.nghiepvu.model.VmaScheduleTransfer fetchByVRCode(
            java.lang.String vrCode)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchByVRCode(vrCode);
    }

    public static com.fds.nsw.nghiepvu.model.VmaScheduleTransfer findByVRCode_RegistryNumber(
            java.lang.String imoNumber, java.lang.String registryNumber)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaScheduleTransferException {
        return getService()
                .findByVRCode_RegistryNumber(imoNumber, registryNumber);
    }

    public static com.fds.nsw.nghiepvu.model.VmaScheduleTransfer fetchByVRCode_RegistryNumber(
            java.lang.String imoNumber, java.lang.String registryNumber)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .fetchByVRCode_RegistryNumber(imoNumber, registryNumber);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaScheduleTransfer> findAll(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().findAll(start, end);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaScheduleTransfer> findAll(
            int start, int end,
            com.fds.nsw.kernel.util.OrderByComparator orderByComparator)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().findAll(start, end, orderByComparator);
    }

    public static com.fds.nsw.nghiepvu.model.VmaScheduleTransfer fetchByIMONumber_CallSign_RegistryNumber(
            java.lang.String imoNumber, java.lang.String callSign,
            java.lang.String registryNumber) {
        return getService()
                .fetchByIMONumber_CallSign_RegistryNumber(imoNumber,
                        callSign, registryNumber);
    }

    public static com.fds.nsw.nghiepvu.model.VmaScheduleTransfer findByItineraryScheduleId(
            long itineraryScheduleId) {
        return getService().findByItineraryScheduleId(itineraryScheduleId);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaScheduleTransfer> findByItineraryNo_NoticeShipType(
            java.lang.String itineraryNo, int noticeShipType) {
        return getService()
                .findByItineraryNo_NoticeShipType(itineraryNo, noticeShipType);
    }

    public static void clearService() {
        _service = null;
    }

    public static VmaScheduleTransferLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(VmaScheduleTransferLocalServiceImpl service) {
    }

    private static VmaScheduleTransferLocalServiceImpl _service;
}
