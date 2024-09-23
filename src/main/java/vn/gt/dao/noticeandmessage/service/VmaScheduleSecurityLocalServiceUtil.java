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
 * The utility for the vma schedule security local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.VmaScheduleSecurityLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see VmaScheduleSecurityLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.VmaScheduleSecurityLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.VmaScheduleSecurityLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.VmaScheduleSecurityLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class VmaScheduleSecurityLocalServiceUtil {
    public VmaScheduleSecurityLocalServiceUtil(VmaScheduleSecurityLocalServiceImpl service) {
        VmaScheduleSecurityLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.VmaScheduleSecurityLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the vma schedule security to the database. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleSecurity the vma schedule security
     * @return the vma schedule security that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleSecurity addVmaScheduleSecurity(
            com.fds.nsw.nghiepvu.model.VmaScheduleSecurity vmaScheduleSecurity)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addVmaScheduleSecurity(vmaScheduleSecurity);
    }

    /**
     * Creates a new vma schedule security with the primary key. Does not add the vma schedule security to the database.
     *
     * @param id the primary key for the new vma schedule security
     * @return the new vma schedule security
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleSecurity createVmaScheduleSecurity(
            long id) {
        return getService().createVmaScheduleSecurity(id);
    }

    /**
     * Deletes the vma schedule security with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the vma schedule security
     * @return the vma schedule security that was removed
     * @throws PortalException if a vma schedule security with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleSecurity deleteVmaScheduleSecurity(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaScheduleSecurity(id);
    }

    /**
     * Deletes the vma schedule security from the database. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleSecurity the vma schedule security
     * @return the vma schedule security that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleSecurity deleteVmaScheduleSecurity(
            com.fds.nsw.nghiepvu.model.VmaScheduleSecurity vmaScheduleSecurity)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaScheduleSecurity(vmaScheduleSecurity);
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
   

    public static com.fds.nsw.nghiepvu.model.VmaScheduleSecurity fetchVmaScheduleSecurity(
            long id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchVmaScheduleSecurity(id);
    }

    /**
     * Returns the vma schedule security with the primary key.
     *
     * @param id the primary key of the vma schedule security
     * @return the vma schedule security
     * @throws PortalException if a vma schedule security with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleSecurity getVmaScheduleSecurity(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaScheduleSecurity(id);
    }

    

    /**
     * Returns a range of all the vma schedule securities.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of vma schedule securities
     * @param end the upper bound of the range of vma schedule securities (not inclusive)
     * @return the range of vma schedule securities
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaScheduleSecurity> getVmaScheduleSecurities(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaScheduleSecurities(start, end);
    }

    /**
     * Returns the number of vma schedule securities.
     *
     * @return the number of vma schedule securities
     * @throws SystemException if a system exception occurred
     */
    public static int getVmaScheduleSecuritiesCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaScheduleSecuritiesCount();
    }

    /**
     * Updates the vma schedule security in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleSecurity the vma schedule security
     * @return the vma schedule security that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleSecurity updateVmaScheduleSecurity(
            com.fds.nsw.nghiepvu.model.VmaScheduleSecurity vmaScheduleSecurity)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaScheduleSecurity(vmaScheduleSecurity);
    }

    /**
     * Updates the vma schedule security in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleSecurity the vma schedule security
     * @param merge whether to merge the vma schedule security with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the vma schedule security that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleSecurity updateVmaScheduleSecurity(
            com.fds.nsw.nghiepvu.model.VmaScheduleSecurity vmaScheduleSecurity,
            boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaScheduleSecurity(vmaScheduleSecurity, merge);
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
   

    

    public static com.fds.nsw.nghiepvu.model.VmaScheduleSecurity delete(
            long id)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaScheduleSecurityException {
        return getService().delete(id);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaScheduleSecurity> findByItineraryNo(
            java.lang.String itineraryNo)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().findByItineraryNo(itineraryNo);
    }

    public static int countByItineraryNo(java.lang.String itineraryNo)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().countByItineraryNo(itineraryNo);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaScheduleSecurity> findByItineraryNo_NoticeShipType(
            java.lang.String itineraryNo, int noticeShipType)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findByItineraryNo_NoticeShipType(itineraryNo, noticeShipType);
    }

    public static int countByItineraryNo_NoticeShipType(
            java.lang.String itineraryNo, int noticeShipType)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .countByItineraryNo_NoticeShipType(itineraryNo,
                        noticeShipType);
    }

    public static org.json.JSONObject findVmaScheduleSecurity(
            java.lang.String itineraryNo, java.lang.String portofAuthority,
            java.lang.String nameOfShip, java.lang.Long documentName,
            java.lang.Integer documentYear, java.lang.Integer noticeShipType,
            java.lang.String securityOfficeCode,
            java.lang.String securityCompanyName,
            java.lang.String securityOfficialNo, java.lang.String securityDate,
            java.lang.String securityPlace, java.lang.String securityReason,
            java.lang.Integer evacuated, java.lang.String evacuateOfficialCode,
            java.lang.String evacuateCompanyName,
            java.lang.String evacuateOfficialNo, java.lang.String evacuateDate,
            java.lang.String evacuateReason, int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findVmaScheduleSecurity(itineraryNo, portofAuthority,
                        nameOfShip, documentName, documentYear, noticeShipType,
                        securityOfficeCode, securityCompanyName, securityOfficialNo,
                        securityDate, securityPlace, securityReason, evacuated,
                        evacuateOfficialCode, evacuateCompanyName, evacuateOfficialNo,
                        evacuateDate, evacuateReason, start, end);
    }

    public static org.json.JSONObject findVmaScheduleSecurity(
            java.lang.String searchQuery, java.lang.String countQuery, int start,
            int end)
            throws com.fds.nsw.kernel.exception.SystemException,
            org.json.JSONException {
        return getService()
                .findVmaScheduleSecurity(searchQuery, countQuery, start, end);
    }

    public static long countVmaScheduleSecurity(java.lang.String itineraryNo,
                                                java.lang.String portofAuthority, java.lang.String nameOfShip,
                                                java.lang.Long documentName, java.lang.Integer documentYear,
                                                java.lang.Integer noticeShipType, java.lang.String securityOfficeCode,
                                                java.lang.String securityCompanyName,
                                                java.lang.String securityOfficialNo, java.lang.String securityDate,
                                                java.lang.String securityPlace, java.lang.String securityReason,
                                                java.lang.Integer evacuated, java.lang.String evacuateOfficialCode,
                                                java.lang.String evacuateCompanyName,
                                                java.lang.String evacuateOfficialNo, java.lang.String evacuateDate,
                                                java.lang.String evacuateReason)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .countVmaScheduleSecurity(itineraryNo, portofAuthority,
                        nameOfShip, documentName, documentYear, noticeShipType,
                        securityOfficeCode, securityCompanyName, securityOfficialNo,
                        securityDate, securityPlace, securityReason, evacuated,
                        evacuateOfficialCode, evacuateCompanyName, evacuateOfficialNo,
                        evacuateDate, evacuateReason);
    }

    public static long countVmaScheduleSecurity(java.lang.String sql)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().countVmaScheduleSecurity(sql);
    }

    public static void clearService() {
        _service = null;
    }

    public static VmaScheduleSecurityLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(VmaScheduleSecurityLocalServiceImpl service) {
    }

    private static VmaScheduleSecurityLocalServiceImpl _service;
}
