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
 * The utility for the vma schedule tugboat local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.VmaScheduleTugboatLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see VmaScheduleTugboatLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.VmaScheduleTugboatLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.VmaScheduleTugboatLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.VmaScheduleTugboatLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class VmaScheduleTugboatLocalServiceUtil {
    public VmaScheduleTugboatLocalServiceUtil(VmaScheduleTugboatLocalServiceImpl service) {
        VmaScheduleTugboatLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.VmaScheduleTugboatLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the vma schedule tugboat to the database. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleTugboat the vma schedule tugboat
     * @return the vma schedule tugboat that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleTugboat addVmaScheduleTugboat(
            com.fds.nsw.nghiepvu.model.VmaScheduleTugboat vmaScheduleTugboat)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addVmaScheduleTugboat(vmaScheduleTugboat);
    }

    /**
     * Creates a new vma schedule tugboat with the primary key. Does not add the vma schedule tugboat to the database.
     *
     * @param id the primary key for the new vma schedule tugboat
     * @return the new vma schedule tugboat
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleTugboat createVmaScheduleTugboat(
            long id) {
        return getService().createVmaScheduleTugboat(id);
    }

    /**
     * Deletes the vma schedule tugboat with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the vma schedule tugboat
     * @return the vma schedule tugboat that was removed
     * @throws PortalException if a vma schedule tugboat with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleTugboat deleteVmaScheduleTugboat(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaScheduleTugboat(id);
    }

    /**
     * Deletes the vma schedule tugboat from the database. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleTugboat the vma schedule tugboat
     * @return the vma schedule tugboat that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleTugboat deleteVmaScheduleTugboat(
            com.fds.nsw.nghiepvu.model.VmaScheduleTugboat vmaScheduleTugboat)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaScheduleTugboat(vmaScheduleTugboat);
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
   

    public static com.fds.nsw.nghiepvu.model.VmaScheduleTugboat fetchVmaScheduleTugboat(
            long id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchVmaScheduleTugboat(id);
    }

    /**
     * Returns the vma schedule tugboat with the primary key.
     *
     * @param id the primary key of the vma schedule tugboat
     * @return the vma schedule tugboat
     * @throws PortalException if a vma schedule tugboat with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleTugboat getVmaScheduleTugboat(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaScheduleTugboat(id);
    }

    

    /**
     * Returns a range of all the vma schedule tugboats.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of vma schedule tugboats
     * @param end the upper bound of the range of vma schedule tugboats (not inclusive)
     * @return the range of vma schedule tugboats
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaScheduleTugboat> getVmaScheduleTugboats(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaScheduleTugboats(start, end);
    }

    /**
     * Returns the number of vma schedule tugboats.
     *
     * @return the number of vma schedule tugboats
     * @throws SystemException if a system exception occurred
     */
    public static int getVmaScheduleTugboatsCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaScheduleTugboatsCount();
    }

    /**
     * Updates the vma schedule tugboat in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleTugboat the vma schedule tugboat
     * @return the vma schedule tugboat that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleTugboat updateVmaScheduleTugboat(
            com.fds.nsw.nghiepvu.model.VmaScheduleTugboat vmaScheduleTugboat)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaScheduleTugboat(vmaScheduleTugboat);
    }

    /**
     * Updates the vma schedule tugboat in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleTugboat the vma schedule tugboat
     * @param merge whether to merge the vma schedule tugboat with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the vma schedule tugboat that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleTugboat updateVmaScheduleTugboat(
            com.fds.nsw.nghiepvu.model.VmaScheduleTugboat vmaScheduleTugboat,
            boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaScheduleTugboat(vmaScheduleTugboat, merge);
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
   

    

    public static org.json.JSONObject addVmaScheduleTugboat_VmaScheduleTugboatLists(
            com.fds.nsw.nghiepvu.model.VmaScheduleTugboat vmaScheduleTugboat,
            java.util.List<com.fds.nsw.nghiepvu.model.VmaScheduleTugboatList> vmaScheduleTugboatLists)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .addVmaScheduleTugboat_VmaScheduleTugboatLists(vmaScheduleTugboat,
                        vmaScheduleTugboatLists);
    }

    public static org.json.JSONObject updateVmaScheduleTugboat_VmaScheduleTugboatLists(
            com.fds.nsw.nghiepvu.model.VmaScheduleTugboat vmaScheduleTugboat,
            java.util.List<com.fds.nsw.nghiepvu.model.VmaScheduleTugboatList> vmaScheduleTugboatLists)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .updateVmaScheduleTugboat_VmaScheduleTugboatLists(vmaScheduleTugboat,
                        vmaScheduleTugboatLists);
    }

    public static com.fds.nsw.nghiepvu.model.VmaScheduleTugboat delete(
            long id)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaScheduleTugboatException {
        return getService().delete(id);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaScheduleTugboat> findByItineraryNo(
            java.lang.String itineraryNo)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().findByItineraryNo(itineraryNo);
    }

    public static int countByItineraryNo(java.lang.String itineraryNo)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().countByItineraryNo(itineraryNo);
    }

    public static com.fds.nsw.nghiepvu.model.VmaScheduleTugboat findByItineraryNo_NoticeShipType_CertificateNo(
            java.lang.String itineraryNo, int noticeShipType,
            java.lang.String certificateNo)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaScheduleTugboatException {
        return getService()
                .findByItineraryNo_NoticeShipType_CertificateNo(itineraryNo,
                        noticeShipType, certificateNo);
    }

    public static int countByItineraryNo_NoticeShipType_CertificateNo(
            java.lang.String itineraryNo, int noticeShipType,
            java.lang.String certificateNo)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .countByItineraryNo_NoticeShipType_CertificateNo(itineraryNo,
                        noticeShipType, certificateNo);
    }

    public static com.fds.nsw.nghiepvu.model.VmaScheduleTugboat findByItineraryNo_NoticeShipType_SequenceNo(
            java.lang.String itineraryNo, int noticeShipType, int sequenceNo)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaScheduleTugboatException {
        return getService()
                .findByItineraryNo_NoticeShipType_SequenceNo(itineraryNo,
                        noticeShipType, sequenceNo);
    }

    public static int countByItineraryNo_NoticeShipType_SequenceNo(
            java.lang.String itineraryNo, int noticeShipType, int sequenceNo)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .countByItineraryNo_NoticeShipType_SequenceNo(itineraryNo,
                        noticeShipType, sequenceNo);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaScheduleTugboat> findByItineraryNo_NoticeShipType(
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

    public static org.json.JSONObject findScheduleTugboat(
            java.lang.String itineraryNo, java.lang.String nameOfShip,
            java.lang.Integer noticeShipType,
            java.lang.String anchoringPortHarbourCode,
            java.lang.String anchoringPortWharfCode,
            java.lang.String shiftingPortRegionCode,
            java.lang.String shiftingPortHarbourCode,
            java.lang.String shiftingPortWharfCode, java.lang.String tugDateFrom,
            java.lang.String tugDateTo, int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findScheduleTugboat(itineraryNo, nameOfShip,
                        noticeShipType, anchoringPortHarbourCode, anchoringPortWharfCode,
                        shiftingPortRegionCode, shiftingPortHarbourCode,
                        shiftingPortWharfCode, tugDateFrom, tugDateTo, start, end);
    }

    public static org.json.JSONObject findScheduleTugboat(
            java.lang.String searchQuery, java.lang.String countQuery, int start,
            int end)
            throws com.fds.nsw.kernel.exception.SystemException,
            org.json.JSONException {
        return getService()
                .findScheduleTugboat(searchQuery, countQuery, start, end);
    }

    public static long countVmaScheduleTugboat(java.lang.String itineraryNo,
                                               java.lang.String nameOfShip, java.lang.Integer noticeShipType,
                                               java.lang.String anchoringPortHarbourCode,
                                               java.lang.String anchoringPortWharfCode,
                                               java.lang.String shiftingPortRegionCode,
                                               java.lang.String shiftingPortHarbourCode,
                                               java.lang.String shiftingPortWharfCode, java.lang.String tugDateFrom,
                                               java.lang.String tugDateTo)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .countVmaScheduleTugboat(itineraryNo, nameOfShip,
                        noticeShipType, anchoringPortHarbourCode, anchoringPortWharfCode,
                        shiftingPortRegionCode, shiftingPortHarbourCode,
                        shiftingPortWharfCode, tugDateFrom, tugDateTo);
    }

    public static long countVmaScheduleTugboat(java.lang.String sql)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().countVmaScheduleTugboat(sql);
    }

    public static org.json.JSONArray getModelMau24_2T(
            java.lang.String maritimeCode, java.lang.String shipCode,
            java.lang.String startDate, java.lang.String endDate)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .getModelMau24_2T(maritimeCode, shipCode, startDate, endDate);
    }

    public static org.json.JSONArray getModelMau25T(
            java.lang.String maritimeCode, java.lang.String tugboatCompanyCode,
            java.lang.String startDate, java.lang.String endDate)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .getModelMau25T(maritimeCode, tugboatCompanyCode, startDate,
                        endDate);
    }

    public static void deleteVmaScheduleTugboat_VmaScheduleTugboatList(long id)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaScheduleTugboatException {
        getService().deleteVmaScheduleTugboat_VmaScheduleTugboatList(id);
    }

    public static com.fds.nsw.nghiepvu.model.VmaScheduleTugboat getByItineraryNo_SequenceNo(
            java.lang.String itineraryNo, int sequenceNo) {
        return getService().getByItineraryNo_SequenceNo(itineraryNo, sequenceNo);
    }

    public static void clearService() {
        _service = null;
    }

    public static VmaScheduleTugboatLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(VmaScheduleTugboatLocalServiceImpl service) {
    }

    private static VmaScheduleTugboatLocalServiceImpl _service;
}
