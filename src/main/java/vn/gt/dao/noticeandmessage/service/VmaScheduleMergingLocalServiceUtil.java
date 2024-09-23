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
 * The utility for the vma schedule merging local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.VmaScheduleMergingLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see VmaScheduleMergingLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.VmaScheduleMergingLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.VmaScheduleMergingLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.VmaScheduleMergingLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class VmaScheduleMergingLocalServiceUtil {
    public VmaScheduleMergingLocalServiceUtil(VmaScheduleMergingLocalServiceImpl service) {
        VmaScheduleMergingLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.VmaScheduleMergingLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the vma schedule merging to the database. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleMerging the vma schedule merging
     * @return the vma schedule merging that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleMerging addVmaScheduleMerging(
            com.fds.nsw.nghiepvu.model.VmaScheduleMerging vmaScheduleMerging)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addVmaScheduleMerging(vmaScheduleMerging);
    }

    /**
     * Creates a new vma schedule merging with the primary key. Does not add the vma schedule merging to the database.
     *
     * @param id the primary key for the new vma schedule merging
     * @return the new vma schedule merging
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleMerging createVmaScheduleMerging(
            long id) {
        return getService().createVmaScheduleMerging(id);
    }

    /**
     * Deletes the vma schedule merging with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the vma schedule merging
     * @return the vma schedule merging that was removed
     * @throws PortalException if a vma schedule merging with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleMerging deleteVmaScheduleMerging(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaScheduleMerging(id);
    }

    /**
     * Deletes the vma schedule merging from the database. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleMerging the vma schedule merging
     * @return the vma schedule merging that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleMerging deleteVmaScheduleMerging(
            com.fds.nsw.nghiepvu.model.VmaScheduleMerging vmaScheduleMerging)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaScheduleMerging(vmaScheduleMerging);
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
   

    public static com.fds.nsw.nghiepvu.model.VmaScheduleMerging fetchVmaScheduleMerging(
            long id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchVmaScheduleMerging(id);
    }

    /**
     * Returns the vma schedule merging with the primary key.
     *
     * @param id the primary key of the vma schedule merging
     * @return the vma schedule merging
     * @throws PortalException if a vma schedule merging with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleMerging getVmaScheduleMerging(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaScheduleMerging(id);
    }

    

    /**
     * Returns a range of all the vma schedule mergings.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of vma schedule mergings
     * @param end the upper bound of the range of vma schedule mergings (not inclusive)
     * @return the range of vma schedule mergings
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaScheduleMerging> getVmaScheduleMergings(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaScheduleMergings(start, end);
    }

    /**
     * Returns the number of vma schedule mergings.
     *
     * @return the number of vma schedule mergings
     * @throws SystemException if a system exception occurred
     */
    public static int getVmaScheduleMergingsCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaScheduleMergingsCount();
    }

    /**
     * Updates the vma schedule merging in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleMerging the vma schedule merging
     * @return the vma schedule merging that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleMerging updateVmaScheduleMerging(
            com.fds.nsw.nghiepvu.model.VmaScheduleMerging vmaScheduleMerging)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaScheduleMerging(vmaScheduleMerging);
    }

    /**
     * Updates the vma schedule merging in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleMerging the vma schedule merging
     * @param merge whether to merge the vma schedule merging with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the vma schedule merging that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleMerging updateVmaScheduleMerging(
            com.fds.nsw.nghiepvu.model.VmaScheduleMerging vmaScheduleMerging,
            boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaScheduleMerging(vmaScheduleMerging, merge);
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
   

    

    public static com.fds.nsw.nghiepvu.model.VmaScheduleMerging delete(
            long id)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaScheduleMergingException {
        return getService().delete(id);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaScheduleMerging> findByItineraryNo(
            java.lang.String itineraryNo)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().findByItineraryNo(itineraryNo);
    }

    public static int countByItineraryNo(java.lang.String itineraryNo)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().countByItineraryNo(itineraryNo);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaScheduleMerging> findByItineraryNo_NoticeShipType(
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

    public static org.json.JSONObject findVmaScheduleMerging(
            java.lang.String itineraryNo, java.lang.String portofAuthority,
            java.lang.Long documentName, java.lang.Integer documentYear,
            java.lang.Integer noticeShipType, java.lang.String shipOwnerCode,
            java.lang.String shipOwnersName, java.lang.String nameOfShip,
            java.lang.String flagStateOfShip, java.lang.String imoNumber,
            java.lang.String callSign, java.lang.String vrCode,
            java.lang.String registryNumber, java.lang.String shipOperatorCode,
            java.lang.String shipOperatorName, java.lang.String shipAgencyCode,
            java.lang.String shipAgencyName, java.lang.Double gt,
            java.lang.Double nt, java.lang.Double shownDraftxF,
            java.lang.Double shownDraftxA, java.lang.Double loa,
            java.lang.Double dwt, long itineraryScheduleId, int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findVmaScheduleMerging(itineraryNo, portofAuthority,
                        documentName, documentYear, noticeShipType, shipOwnerCode,
                        shipOwnersName, nameOfShip, flagStateOfShip, imoNumber, callSign,
                        vrCode, registryNumber, shipOperatorCode, shipOperatorName,
                        shipAgencyCode, shipAgencyName, gt, nt, shownDraftxF, shownDraftxA,
                        loa, dwt, itineraryScheduleId, start, end);
    }

    public static long countVmaScheduleMerging(java.lang.String itineraryNo,
                                               java.lang.String portofAuthority, java.lang.Long documentName,
                                               java.lang.Integer documentYear, java.lang.Integer noticeShipType,
                                               java.lang.String shipOwnerCode, java.lang.String shipOwnersName,
                                               java.lang.String nameOfShip, java.lang.String flagStateOfShip,
                                               java.lang.String imoNumber, java.lang.String callSign,
                                               java.lang.String vrCode, java.lang.String registryNumber,
                                               java.lang.String shipOperatorCode, java.lang.String shipOperatorName,
                                               java.lang.String shipAgencyCode, java.lang.String shipAgencyName,
                                               java.lang.Double gt, java.lang.Double nt,
                                               java.lang.Double shownDraftxF, java.lang.Double shownDraftxA,
                                               java.lang.Double loa, java.lang.Double dwt, long itineraryScheduleId)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .countVmaScheduleMerging(itineraryNo, portofAuthority,
                        documentName, documentYear, noticeShipType, shipOwnerCode,
                        shipOwnersName, nameOfShip, flagStateOfShip, imoNumber, callSign,
                        vrCode, registryNumber, shipOperatorCode, shipOperatorName,
                        shipAgencyCode, shipAgencyName, gt, nt, shownDraftxF, shownDraftxA,
                        loa, dwt, itineraryScheduleId);
    }

    public static org.json.JSONObject findVmaScheduleMerging(
            java.lang.String searchQuery, java.lang.String countQuery, int start,
            int end)
            throws com.fds.nsw.kernel.exception.SystemException,
            org.json.JSONException {
        return getService()
                .findVmaScheduleMerging(searchQuery, countQuery, start, end);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaScheduleMerging> fetchByItineraryScheduleId(
            long itineraryScheduleId, int start, int end) {
        return getService()
                .fetchByItineraryScheduleId(itineraryScheduleId, start, end);
    }

    public static org.json.JSONObject updateVmaScheduleMergings(
            java.util.List<com.fds.nsw.nghiepvu.model.VmaScheduleMerging> vmaScheduleMergings,
            long itineraryScheduleId) {
        return getService()
                .updateVmaScheduleMergings(vmaScheduleMergings,
                        itineraryScheduleId);
    }

    public static void clearService() {
        _service = null;
    }

    public static VmaScheduleMergingLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(VmaScheduleMergingLocalServiceImpl service) {
    }

    private static VmaScheduleMergingLocalServiceImpl _service;
}
