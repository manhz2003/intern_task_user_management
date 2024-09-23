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
 * The utility for the vma schedule pilot local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.VmaSchedulePilotLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see VmaSchedulePilotLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.VmaSchedulePilotLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.VmaSchedulePilotLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.VmaSchedulePilotLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class VmaSchedulePilotLocalServiceUtil {
    public VmaSchedulePilotLocalServiceUtil(VmaSchedulePilotLocalServiceImpl service) {
        VmaSchedulePilotLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.VmaSchedulePilotLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the vma schedule pilot to the database. Also notifies the appropriate model listeners.
     *
     * @param vmaSchedulePilot the vma schedule pilot
     * @return the vma schedule pilot that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaSchedulePilot addVmaSchedulePilot(
            com.fds.nsw.nghiepvu.model.VmaSchedulePilot vmaSchedulePilot)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addVmaSchedulePilot(vmaSchedulePilot);
    }

    /**
     * Creates a new vma schedule pilot with the primary key. Does not add the vma schedule pilot to the database.
     *
     * @param id the primary key for the new vma schedule pilot
     * @return the new vma schedule pilot
     */
    public static com.fds.nsw.nghiepvu.model.VmaSchedulePilot createVmaSchedulePilot(
            long id) {
        return getService().createVmaSchedulePilot(id);
    }

    /**
     * Deletes the vma schedule pilot with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the vma schedule pilot
     * @return the vma schedule pilot that was removed
     * @throws PortalException if a vma schedule pilot with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaSchedulePilot deleteVmaSchedulePilot(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaSchedulePilot(id);
    }

    /**
     * Deletes the vma schedule pilot from the database. Also notifies the appropriate model listeners.
     *
     * @param vmaSchedulePilot the vma schedule pilot
     * @return the vma schedule pilot that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaSchedulePilot deleteVmaSchedulePilot(
            com.fds.nsw.nghiepvu.model.VmaSchedulePilot vmaSchedulePilot)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaSchedulePilot(vmaSchedulePilot);
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
   

    public static com.fds.nsw.nghiepvu.model.VmaSchedulePilot fetchVmaSchedulePilot(
            long id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchVmaSchedulePilot(id);
    }

    /**
     * Returns the vma schedule pilot with the primary key.
     *
     * @param id the primary key of the vma schedule pilot
     * @return the vma schedule pilot
     * @throws PortalException if a vma schedule pilot with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaSchedulePilot getVmaSchedulePilot(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaSchedulePilot(id);
    }

    

    /**
     * Returns a range of all the vma schedule pilots.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of vma schedule pilots
     * @param end the upper bound of the range of vma schedule pilots (not inclusive)
     * @return the range of vma schedule pilots
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaSchedulePilot> getVmaSchedulePilots(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaSchedulePilots(start, end);
    }

    /**
     * Returns the number of vma schedule pilots.
     *
     * @return the number of vma schedule pilots
     * @throws SystemException if a system exception occurred
     */
    public static int getVmaSchedulePilotsCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaSchedulePilotsCount();
    }

    /**
     * Updates the vma schedule pilot in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaSchedulePilot the vma schedule pilot
     * @return the vma schedule pilot that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaSchedulePilot updateVmaSchedulePilot(
            com.fds.nsw.nghiepvu.model.VmaSchedulePilot vmaSchedulePilot)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaSchedulePilot(vmaSchedulePilot);
    }

    /**
     * Updates the vma schedule pilot in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaSchedulePilot the vma schedule pilot
     * @param merge whether to merge the vma schedule pilot with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the vma schedule pilot that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaSchedulePilot updateVmaSchedulePilot(
            com.fds.nsw.nghiepvu.model.VmaSchedulePilot vmaSchedulePilot,
            boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaSchedulePilot(vmaSchedulePilot, merge);
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
   

    

    public static org.json.JSONObject addVmaSchedulePilot_VmaSchedulePilotLists(
            com.fds.nsw.nghiepvu.model.VmaSchedulePilot vmaSchedulePilot,
            java.util.List<com.fds.nsw.nghiepvu.model.VmaSchedulePilotList> vmaSchedulePilotLists)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .addVmaSchedulePilot_VmaSchedulePilotLists(vmaSchedulePilot,
                        vmaSchedulePilotLists);
    }

    public static org.json.JSONObject updateVmaSchedulePilot_VmaSchedulePilotLists(
            com.fds.nsw.nghiepvu.model.VmaSchedulePilot vmaSchedulePilot,
            java.util.List<com.fds.nsw.nghiepvu.model.VmaSchedulePilotList> vmaSchedulePilotLists)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .updateVmaSchedulePilot_VmaSchedulePilotLists(vmaSchedulePilot,
                        vmaSchedulePilotLists);
    }

    public static com.fds.nsw.nghiepvu.model.VmaSchedulePilot delete(
            long id)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaSchedulePilotException {
        return getService().delete(id);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaSchedulePilot> findByItineraryNo(
            java.lang.String itineraryNo)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().findByItineraryNo(itineraryNo);
    }

    public static int countByItineraryNo(java.lang.String itineraryNo)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().countByItineraryNo(itineraryNo);
    }

    public static com.fds.nsw.nghiepvu.model.VmaSchedulePilot findByItineraryNo_NoticeShipType_SequenceNo(
            java.lang.String itineraryNo, int noticeShipType, int sequenceNo)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaSchedulePilotException {
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

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaSchedulePilot> findByItineraryNo_NoticeShipType(
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

    public static org.json.JSONObject findVmaSchedulePilot(
            java.lang.String itineraryNo, java.lang.String portofAuthority,
            java.lang.String nameOfShip, java.lang.String certificateNo,
            java.lang.Integer noticeShipType,
            java.lang.String anchoringPortHarbourCode,
            java.lang.String anchoringPortWharfCode,
            java.lang.String shiftingPortRegionCode,
            java.lang.String shiftingPortHarbourCode,
            java.lang.String shiftingPortWharfCode, java.lang.String pilotDateFrom,
            java.lang.String pilotDateTo, int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findVmaSchedulePilot(itineraryNo, portofAuthority,
                        nameOfShip, certificateNo, noticeShipType,
                        anchoringPortHarbourCode, anchoringPortWharfCode,
                        shiftingPortRegionCode, shiftingPortHarbourCode,
                        shiftingPortWharfCode, pilotDateFrom, pilotDateTo, start, end);
    }

    public static org.json.JSONObject findVmaSchedulePilot(
            java.lang.String searchQuery, java.lang.String countQuery, int start,
            int end)
            throws com.fds.nsw.kernel.exception.SystemException,
            org.json.JSONException {
        return getService()
                .findVmaSchedulePilot(searchQuery, countQuery, start, end);
    }

    public static long countVmaSchedulePilot(java.lang.String itineraryNo,
                                             java.lang.String portofAuthority, java.lang.String nameOfShip,
                                             java.lang.String certificateNo, java.lang.Integer noticeShipType,
                                             java.lang.String anchoringPortHarbourCode,
                                             java.lang.String anchoringPortWharfCode,
                                             java.lang.String shiftingPortRegionCode,
                                             java.lang.String shiftingPortHarbourCode,
                                             java.lang.String shiftingPortWharfCode, java.lang.String pilotDateFrom,
                                             java.lang.String pilotDateTo)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .countVmaSchedulePilot(itineraryNo, portofAuthority,
                        nameOfShip, certificateNo, noticeShipType,
                        anchoringPortHarbourCode, anchoringPortWharfCode,
                        shiftingPortRegionCode, shiftingPortHarbourCode,
                        shiftingPortWharfCode, pilotDateFrom, pilotDateTo);
    }

    public static long countVmaSchedulePilot(java.lang.String sql)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().countVmaSchedulePilot(sql);
    }

    public static com.fds.nsw.nghiepvu.model.VmaSchedulePilot findByItineraryNo_NoticeShipType_CertificateNo(
            java.lang.String itineraryNo, int noticeShipType,
            java.lang.String certificateNo)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaSchedulePilotException {
        return getService()
                .findByItineraryNo_NoticeShipType_CertificateNo(itineraryNo,
                        noticeShipType, certificateNo);
    }

    public static org.json.JSONArray getModelMau26_2T(
            java.lang.String maritimeCode, java.lang.String pilotCode,
            java.lang.String startDate, java.lang.String endDate)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .getModelMau26_2T(maritimeCode, pilotCode, startDate, endDate);
    }

    public static org.json.JSONArray getModelMau27T(
            java.lang.String maritimeCode, java.lang.String pilotCompanyCode,
            java.lang.String startDate, java.lang.String endDate)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .getModelMau27T(maritimeCode, pilotCompanyCode, startDate,
                        endDate);
    }

    public static void clearService() {
        _service = null;
    }

    public static VmaSchedulePilotLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(VmaSchedulePilotLocalServiceImpl service) {
    }

    private static VmaSchedulePilotLocalServiceImpl _service;
}
