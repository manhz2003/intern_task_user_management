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
 * The utility for the vma schedule testing local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.VmaScheduleTestingLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see VmaScheduleTestingLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.VmaScheduleTestingLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.VmaScheduleTestingLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.VmaScheduleTestingLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class VmaScheduleTestingLocalServiceUtil {
    public VmaScheduleTestingLocalServiceUtil(VmaScheduleTestingLocalServiceImpl service) {
        VmaScheduleTestingLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.VmaScheduleTestingLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the vma schedule testing to the database. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleTesting the vma schedule testing
     * @return the vma schedule testing that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleTesting addVmaScheduleTesting(
            com.fds.nsw.nghiepvu.model.VmaScheduleTesting vmaScheduleTesting)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addVmaScheduleTesting(vmaScheduleTesting);
    }

    /**
     * Creates a new vma schedule testing with the primary key. Does not add the vma schedule testing to the database.
     *
     * @param id the primary key for the new vma schedule testing
     * @return the new vma schedule testing
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleTesting createVmaScheduleTesting(
            long id) {
        return getService().createVmaScheduleTesting(id);
    }

    /**
     * Deletes the vma schedule testing with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the vma schedule testing
     * @return the vma schedule testing that was removed
     * @throws PortalException if a vma schedule testing with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleTesting deleteVmaScheduleTesting(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaScheduleTesting(id);
    }

    /**
     * Deletes the vma schedule testing from the database. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleTesting the vma schedule testing
     * @return the vma schedule testing that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleTesting deleteVmaScheduleTesting(
            com.fds.nsw.nghiepvu.model.VmaScheduleTesting vmaScheduleTesting)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaScheduleTesting(vmaScheduleTesting);
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
   

    public static com.fds.nsw.nghiepvu.model.VmaScheduleTesting fetchVmaScheduleTesting(
            long id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchVmaScheduleTesting(id);
    }

    /**
     * Returns the vma schedule testing with the primary key.
     *
     * @param id the primary key of the vma schedule testing
     * @return the vma schedule testing
     * @throws PortalException if a vma schedule testing with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleTesting getVmaScheduleTesting(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaScheduleTesting(id);
    }

    

    /**
     * Returns a range of all the vma schedule testings.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of vma schedule testings
     * @param end the upper bound of the range of vma schedule testings (not inclusive)
     * @return the range of vma schedule testings
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaScheduleTesting> getVmaScheduleTestings(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaScheduleTestings(start, end);
    }

    /**
     * Returns the number of vma schedule testings.
     *
     * @return the number of vma schedule testings
     * @throws SystemException if a system exception occurred
     */
    public static int getVmaScheduleTestingsCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaScheduleTestingsCount();
    }

    /**
     * Updates the vma schedule testing in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleTesting the vma schedule testing
     * @return the vma schedule testing that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleTesting updateVmaScheduleTesting(
            com.fds.nsw.nghiepvu.model.VmaScheduleTesting vmaScheduleTesting)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaScheduleTesting(vmaScheduleTesting);
    }

    /**
     * Updates the vma schedule testing in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleTesting the vma schedule testing
     * @param merge whether to merge the vma schedule testing with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the vma schedule testing that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleTesting updateVmaScheduleTesting(
            com.fds.nsw.nghiepvu.model.VmaScheduleTesting vmaScheduleTesting,
            boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaScheduleTesting(vmaScheduleTesting, merge);
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
   

    

    public static com.fds.nsw.nghiepvu.model.VmaScheduleTesting delete(
            long id)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaScheduleTestingException {
        return getService().delete(id);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaScheduleTesting> findByItineraryNo(
            java.lang.String itineraryNo)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().findByItineraryNo(itineraryNo);
    }

    public static int countByItineraryNo(java.lang.String itineraryNo)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().countByItineraryNo(itineraryNo);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaScheduleTesting> findByItineraryNo_NoticeShipType(
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

    public static org.json.JSONObject findVmaScheduleTesting(
            java.lang.String itineraryNo, java.lang.String portofAuthority,
            java.lang.Long documentName, java.lang.Integer documentYear,
            java.lang.Integer noticeShipType, java.lang.String testingFrom,
            java.lang.String testingTo, java.lang.String nameOfShip,
            java.lang.String flagStateOfShip, java.lang.String imoNumber,
            java.lang.String callSign, java.lang.String vrCode,
            java.lang.String registryNumber, java.lang.Double shownDraftxF,
            java.lang.Double shownDraftxA, java.lang.Double loa,
            java.lang.Double dwt, int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findVmaScheduleTesting(itineraryNo, portofAuthority,
                        documentName, documentYear, noticeShipType, testingFrom, testingTo,
                        nameOfShip, flagStateOfShip, imoNumber, callSign, vrCode,
                        registryNumber, shownDraftxF, shownDraftxA, loa, dwt, start, end);
    }

    public static org.json.JSONObject findVmaScheduleTesting(
            java.lang.String searchQuery, java.lang.String countQuery, int start,
            int end)
            throws com.fds.nsw.kernel.exception.SystemException,
            org.json.JSONException {
        return getService()
                .findVmaScheduleTesting(searchQuery, countQuery, start, end);
    }

    public static long countVmaScheduleTesting(java.lang.String itineraryNo,
                                               java.lang.String portofAuthority, java.lang.Long documentName,
                                               java.lang.Integer documentYear, java.lang.Integer noticeShipType,
                                               java.lang.String testingFrom, java.lang.String testingTo,
                                               java.lang.String nameOfShip, java.lang.String flagStateOfShip,
                                               java.lang.String imoNumber, java.lang.String callSign,
                                               java.lang.String vrCode, java.lang.String registryNumber,
                                               java.lang.Double shownDraftxF, java.lang.Double shownDraftxA,
                                               java.lang.Double loa, java.lang.Double dwt)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .countVmaScheduleTesting(itineraryNo, portofAuthority,
                        documentName, documentYear, noticeShipType, testingFrom, testingTo,
                        nameOfShip, flagStateOfShip, imoNumber, callSign, vrCode,
                        registryNumber, shownDraftxF, shownDraftxA, loa, dwt);
    }

    public static long countVmaScheduleTesting(java.lang.String sql)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().countVmaScheduleTesting(sql);
    }

    public static com.fds.nsw.nghiepvu.model.VmaScheduleTesting fetchByitineraryNo_noticeShipType_certificateNo(
            java.lang.String itineraryNo, int noticeShipType,
            java.lang.String certificateNo) {
        return getService()
                .fetchByitineraryNo_noticeShipType_certificateNo(itineraryNo,
                        noticeShipType, certificateNo);
    }

    public static void clearService() {
        _service = null;
    }

    public static VmaScheduleTestingLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(VmaScheduleTestingLocalServiceImpl service) {
    }

    private static VmaScheduleTestingLocalServiceImpl _service;
}
