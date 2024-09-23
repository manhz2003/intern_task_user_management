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
 * The utility for the vma ship certificate local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.VmaShipCertificateLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see VmaShipCertificateLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.VmaShipCertificateLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.VmaShipCertificateLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.VmaShipCertificateLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class VmaShipCertificateLocalServiceUtil {
    public VmaShipCertificateLocalServiceUtil(VmaShipCertificateLocalServiceImpl service) {
        VmaShipCertificateLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.VmaShipCertificateLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the vma ship certificate to the database. Also notifies the appropriate model listeners.
     *
     * @param vmaShipCertificate the vma ship certificate
     * @return the vma ship certificate that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaShipCertificate addVmaShipCertificate(
            com.fds.nsw.nghiepvu.model.VmaShipCertificate vmaShipCertificate)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addVmaShipCertificate(vmaShipCertificate);
    }

    /**
     * Creates a new vma ship certificate with the primary key. Does not add the vma ship certificate to the database.
     *
     * @param id the primary key for the new vma ship certificate
     * @return the new vma ship certificate
     */
    public static com.fds.nsw.nghiepvu.model.VmaShipCertificate createVmaShipCertificate(
            long id) {
        return getService().createVmaShipCertificate(id);
    }

    /**
     * Deletes the vma ship certificate with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the vma ship certificate
     * @return the vma ship certificate that was removed
     * @throws PortalException if a vma ship certificate with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaShipCertificate deleteVmaShipCertificate(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaShipCertificate(id);
    }

    /**
     * Deletes the vma ship certificate from the database. Also notifies the appropriate model listeners.
     *
     * @param vmaShipCertificate the vma ship certificate
     * @return the vma ship certificate that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaShipCertificate deleteVmaShipCertificate(
            com.fds.nsw.nghiepvu.model.VmaShipCertificate vmaShipCertificate)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaShipCertificate(vmaShipCertificate);
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
   

    public static com.fds.nsw.nghiepvu.model.VmaShipCertificate fetchVmaShipCertificate(
            long id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchVmaShipCertificate(id);
    }

    /**
     * Returns the vma ship certificate with the primary key.
     *
     * @param id the primary key of the vma ship certificate
     * @return the vma ship certificate
     * @throws PortalException if a vma ship certificate with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaShipCertificate getVmaShipCertificate(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaShipCertificate(id);
    }

    

    /**
     * Returns a range of all the vma ship certificates.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of vma ship certificates
     * @param end the upper bound of the range of vma ship certificates (not inclusive)
     * @return the range of vma ship certificates
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaShipCertificate> getVmaShipCertificates(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaShipCertificates(start, end);
    }

    /**
     * Returns the number of vma ship certificates.
     *
     * @return the number of vma ship certificates
     * @throws SystemException if a system exception occurred
     */
    public static int getVmaShipCertificatesCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaShipCertificatesCount();
    }

    /**
     * Updates the vma ship certificate in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaShipCertificate the vma ship certificate
     * @return the vma ship certificate that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaShipCertificate updateVmaShipCertificate(
            com.fds.nsw.nghiepvu.model.VmaShipCertificate vmaShipCertificate)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaShipCertificate(vmaShipCertificate);
    }

    /**
     * Updates the vma ship certificate in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaShipCertificate the vma ship certificate
     * @param merge whether to merge the vma ship certificate with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the vma ship certificate that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaShipCertificate updateVmaShipCertificate(
            com.fds.nsw.nghiepvu.model.VmaShipCertificate vmaShipCertificate,
            boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaShipCertificate(vmaShipCertificate, merge);
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
   

    

    public static com.fds.nsw.nghiepvu.model.VmaShipCertificate delete(
            long id)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaShipCertificateException {
        return getService().delete(id);
    }

    public static int countAll()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().countAll();
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

    public static com.fds.nsw.nghiepvu.model.VmaShipCertificate findByCallSign(
            java.lang.String callSign)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaShipCertificateException {
        return getService().findByCallSign(callSign);
    }

    public static com.fds.nsw.nghiepvu.model.VmaShipCertificate fetchByCallSign(
            java.lang.String callSign)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchByCallSign(callSign);
    }

    public static com.fds.nsw.nghiepvu.model.VmaShipCertificate findByIMONumber(
            java.lang.String imoNumber)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaShipCertificateException {
        return getService().findByIMONumber(imoNumber);
    }

    public static com.fds.nsw.nghiepvu.model.VmaShipCertificate fetchByIMONumber(
            java.lang.String imoNumber)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchByIMONumber(imoNumber);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaShipCertificate> findByIMONumber_CallSign(
            java.lang.String imoNumber, java.lang.String callSign)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaShipCertificateException {
        return getService().findByIMONumber_CallSign(imoNumber, callSign);
    }

    public static com.fds.nsw.nghiepvu.model.VmaShipCertificate findByRegistryNumber(
            java.lang.String registryNumber)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaShipCertificateException {
        return getService().findByRegistryNumber(registryNumber);
    }

    public static com.fds.nsw.nghiepvu.model.VmaShipCertificate fetchByRegistryNumber(
            java.lang.String registryNumber)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaShipCertificateException {
        return getService().fetchByRegistryNumber(registryNumber);
    }

    public static com.fds.nsw.nghiepvu.model.VmaShipCertificate findByVRCode(
            java.lang.String vrCode)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaShipCertificateException {
        return getService().findByVRCode(vrCode);
    }

    public static com.fds.nsw.nghiepvu.model.VmaShipCertificate fetchByVRCode(
            java.lang.String vrCode)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchByVRCode(vrCode);
    }

    public static com.fds.nsw.nghiepvu.model.VmaShipCertificate findByVRCode_RegistryNumber(
            java.lang.String imoNumber, java.lang.String registryNumber)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaShipCertificateException {
        return getService()
                .findByVRCode_RegistryNumber(imoNumber, registryNumber);
    }

    public static com.fds.nsw.nghiepvu.model.VmaShipCertificate fetchByVRCode_RegistryNumber(
            java.lang.String imoNumber, java.lang.String registryNumber)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .fetchByVRCode_RegistryNumber(imoNumber, registryNumber);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaShipCertificate> findAll(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().findAll(start, end);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaShipCertificate> findAll(
            int start, int end,
            com.fds.nsw.kernel.util.OrderByComparator orderByComparator)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().findAll(start, end, orderByComparator);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaShipCertificate> findVmaShipCertificate(
            java.lang.String imoNumber, java.lang.String callSign, int start,
            int end) throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findVmaShipCertificate(imoNumber, callSign, start, end);
    }

    public static void clearService() {
        _service = null;
    }

    public static VmaShipCertificateLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(VmaShipCertificateLocalServiceImpl service) {
    }

    private static VmaShipCertificateLocalServiceImpl _service;
}
