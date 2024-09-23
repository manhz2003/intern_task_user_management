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
 * The utility for the vma certificate local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.VmaCertificateLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see VmaCertificateLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.VmaCertificateLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.VmaCertificateLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.VmaCertificateLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class VmaCertificateLocalServiceUtil {
    public VmaCertificateLocalServiceUtil(VmaCertificateLocalServiceImpl service) {
        VmaCertificateLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.VmaCertificateLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the vma certificate to the database. Also notifies the appropriate model listeners.
     *
     * @param vmaCertificate the vma certificate
     * @return the vma certificate that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaCertificate addVmaCertificate(
            com.fds.nsw.nghiepvu.model.VmaCertificate vmaCertificate)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addVmaCertificate(vmaCertificate);
    }

    /**
     * Creates a new vma certificate with the primary key. Does not add the vma certificate to the database.
     *
     * @param id the primary key for the new vma certificate
     * @return the new vma certificate
     */
    public static com.fds.nsw.nghiepvu.model.VmaCertificate createVmaCertificate(
            int id) {
        return getService().createVmaCertificate(id);
    }

    /**
     * Deletes the vma certificate with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the vma certificate
     * @return the vma certificate that was removed
     * @throws PortalException if a vma certificate with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaCertificate deleteVmaCertificate(
            int id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaCertificate(id);
    }

    /**
     * Deletes the vma certificate from the database. Also notifies the appropriate model listeners.
     *
     * @param vmaCertificate the vma certificate
     * @return the vma certificate that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaCertificate deleteVmaCertificate(
            com.fds.nsw.nghiepvu.model.VmaCertificate vmaCertificate)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaCertificate(vmaCertificate);
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
   

    public static com.fds.nsw.nghiepvu.model.VmaCertificate fetchVmaCertificate(
            int id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchVmaCertificate(id);
    }

    /**
     * Returns the vma certificate with the primary key.
     *
     * @param id the primary key of the vma certificate
     * @return the vma certificate
     * @throws PortalException if a vma certificate with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaCertificate getVmaCertificate(
            int id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaCertificate(id);
    }

    

    /**
     * Returns a range of all the vma certificates.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of vma certificates
     * @param end the upper bound of the range of vma certificates (not inclusive)
     * @return the range of vma certificates
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaCertificate> getVmaCertificates(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaCertificates(start, end);
    }

    /**
     * Returns the number of vma certificates.
     *
     * @return the number of vma certificates
     * @throws SystemException if a system exception occurred
     */
    public static int getVmaCertificatesCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaCertificatesCount();
    }

    /**
     * Updates the vma certificate in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaCertificate the vma certificate
     * @return the vma certificate that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaCertificate updateVmaCertificate(
            com.fds.nsw.nghiepvu.model.VmaCertificate vmaCertificate)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaCertificate(vmaCertificate);
    }

    /**
     * Updates the vma certificate in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaCertificate the vma certificate
     * @param merge whether to merge the vma certificate with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the vma certificate that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaCertificate updateVmaCertificate(
            com.fds.nsw.nghiepvu.model.VmaCertificate vmaCertificate,
            boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaCertificate(vmaCertificate, merge);
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
   

    

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaCertificate> findVmaCertificates(
            java.lang.String nameOfShip, java.lang.String certificateExpiredDate,
            java.lang.String certificateIssueDate, java.lang.String approvalName,
            java.lang.String isExamined, java.lang.String examinationDate,
            java.lang.String imoNumber, java.lang.String callSign,
            java.lang.String registryNumber, int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findVmaCertificates(nameOfShip, certificateExpiredDate,
                        certificateIssueDate, approvalName, isExamined, examinationDate,
                        imoNumber, callSign, registryNumber, start, end);
    }

    public static long countVmaCertificates(java.lang.String nameOfShip,
                                            java.lang.String certificateExpiredDate,
                                            java.lang.String certificateIssueDate, java.lang.String approvalName,
                                            java.lang.String isExamined, java.lang.String examinationDate,
                                            java.lang.String imoNumber, java.lang.String callSign,
                                            java.lang.String registryNumber)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .countVmaCertificates(nameOfShip, certificateExpiredDate,
                        certificateIssueDate, approvalName, isExamined, examinationDate,
                        imoNumber, callSign, registryNumber);
    }

    public static com.fds.nsw.nghiepvu.model.VmaCertificate updateVmaCertificate(
            int id, java.lang.String certificateCode, java.lang.String nameOfShip,
            java.util.Date certificateExpiredDate,
            java.util.Date certificateIssueDate, java.util.Date examinationDate,
            java.lang.String approvalName, int isExamined,
            java.lang.String imoNumber, java.lang.String registryNumber,
            java.lang.String callSign)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .updateVmaCertificate(id, certificateCode, nameOfShip,
                        certificateExpiredDate, certificateIssueDate, examinationDate,
                        approvalName, isExamined, imoNumber, registryNumber, callSign);
    }

    public static void clearService() {
        _service = null;
    }

    public static VmaCertificateLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(VmaCertificateLocalServiceImpl service) {
    }

    private static VmaCertificateLocalServiceImpl _service;
}
