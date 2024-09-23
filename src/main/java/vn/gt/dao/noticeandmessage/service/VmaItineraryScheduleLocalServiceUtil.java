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
 * The utility for the vma itinerary schedule local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.VmaItineraryScheduleLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see VmaItineraryScheduleLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.VmaItineraryScheduleLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.VmaItineraryScheduleLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.VmaItineraryScheduleLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class VmaItineraryScheduleLocalServiceUtil {
    public VmaItineraryScheduleLocalServiceUtil(VmaItineraryScheduleLocalServiceImpl service) {
        VmaItineraryScheduleLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.VmaItineraryScheduleLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the vma itinerary schedule to the database. Also notifies the appropriate model listeners.
     *
     * @param vmaItinerarySchedule the vma itinerary schedule
     * @return the vma itinerary schedule that was added
     * @throws SystemException if a system exception occurred
     */

    public static org.json.JSONObject findThongKeTauLaiDat(
            java.lang.String maritimeCode, java.lang.String tugboatCompanyCode,
            java.lang.String shipCode, java.lang.String reportUser,
            java.lang.String reportPeriod, java.lang.String fromDate,
            java.lang.String toDate, int xemBK, int xemBK_Thutuc)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findThongKeTauLaiDat(maritimeCode, tugboatCompanyCode,
                        shipCode, reportUser, reportPeriod, fromDate, toDate, xemBK,
                        xemBK_Thutuc);
    }

    public static com.fds.nsw.nghiepvu.model.VmaItinerarySchedule addVmaItinerarySchedule(
            com.fds.nsw.nghiepvu.model.VmaItinerarySchedule vmaItinerarySchedule)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addVmaItinerarySchedule(vmaItinerarySchedule);
    }

    /**
     * Creates a new vma itinerary schedule with the primary key. Does not add the vma itinerary schedule to the database.
     *
     * @param id the primary key for the new vma itinerary schedule
     * @return the new vma itinerary schedule
     */
    public static com.fds.nsw.nghiepvu.model.VmaItinerarySchedule createVmaItinerarySchedule(
            long id) {
        return getService().createVmaItinerarySchedule(id);
    }

    /**
     * Deletes the vma itinerary schedule with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the vma itinerary schedule
     * @return the vma itinerary schedule that was removed
     * @throws PortalException if a vma itinerary schedule with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaItinerarySchedule deleteVmaItinerarySchedule(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaItinerarySchedule(id);
    }

    /**
     * Deletes the vma itinerary schedule from the database. Also notifies the appropriate model listeners.
     *
     * @param vmaItinerarySchedule the vma itinerary schedule
     * @return the vma itinerary schedule that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaItinerarySchedule deleteVmaItinerarySchedule(
            com.fds.nsw.nghiepvu.model.VmaItinerarySchedule vmaItinerarySchedule)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaItinerarySchedule(vmaItinerarySchedule);
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
   

    public static com.fds.nsw.nghiepvu.model.VmaItinerarySchedule fetchVmaItinerarySchedule(
            long id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchVmaItinerarySchedule(id);
    }

    /**
     * Returns the vma itinerary schedule with the primary key.
     *
     * @param id the primary key of the vma itinerary schedule
     * @return the vma itinerary schedule
     * @throws PortalException if a vma itinerary schedule with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaItinerarySchedule getVmaItinerarySchedule(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaItinerarySchedule(id);
    }

    

    /**
     * Returns a range of all the vma itinerary schedules.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of vma itinerary schedules
     * @param end the upper bound of the range of vma itinerary schedules (not inclusive)
     * @return the range of vma itinerary schedules
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaItinerarySchedule> getVmaItinerarySchedules(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaItinerarySchedules(start, end);
    }

    /**
     * Returns the number of vma itinerary schedules.
     *
     * @return the number of vma itinerary schedules
     * @throws SystemException if a system exception occurred
     */
    public static int getVmaItinerarySchedulesCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaItinerarySchedulesCount();
    }

    /**
     * Updates the vma itinerary schedule in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaItinerarySchedule the vma itinerary schedule
     * @return the vma itinerary schedule that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaItinerarySchedule updateVmaItinerarySchedule(
            com.fds.nsw.nghiepvu.model.VmaItinerarySchedule vmaItinerarySchedule)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaItinerarySchedule(vmaItinerarySchedule);
    }

    /**
     * Updates the vma itinerary schedule in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaItinerarySchedule the vma itinerary schedule
     * @param merge whether to merge the vma itinerary schedule with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the vma itinerary schedule that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaItinerarySchedule updateVmaItinerarySchedule(
            com.fds.nsw.nghiepvu.model.VmaItinerarySchedule vmaItinerarySchedule,
            boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .updateVmaItinerarySchedule(vmaItinerarySchedule, merge);
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
   

    

    public static com.fds.nsw.nghiepvu.model.VmaItinerarySchedule delete(
            long id)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaItineraryScheduleException {
        return getService().delete(id);
    }

    public static com.fds.nsw.nghiepvu.model.VmaItinerarySchedule delete(
            com.fds.nsw.nghiepvu.model.VmaItinerarySchedule vmaItinerarySchedule)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().delete(vmaItinerarySchedule);
    }

    public static org.json.JSONObject updateVmaItinerarySchedule_VmaItinerary(
            com.fds.nsw.nghiepvu.model.VmaItinerarySchedule vmaItinerarySchedule,
            com.fds.nsw.nghiepvu.model.VmaItinerary vmaItinerary)
            throws com.fds.nsw.kernel.exception.SystemException,
            org.json.JSONException {
        return getService()
                .updateVmaItinerarySchedule_VmaItinerary(vmaItinerarySchedule,
                        vmaItinerary);
    }

    public static org.json.JSONObject updateVmaItinerarySchedule_VmaItinerary(
            java.lang.Integer markedAsArrival, java.lang.Integer markedAsDeparture,
            java.lang.Integer markedAsTransmit, java.lang.Integer markedAsVoyage,
            com.fds.nsw.nghiepvu.model.VmaItinerarySchedule vmaItinerarySchedule,
            com.fds.nsw.nghiepvu.model.VmaItinerary vmaItinerary)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .updateVmaItinerarySchedule_VmaItinerary(markedAsArrival,
                        markedAsDeparture, markedAsTransmit, markedAsVoyage,
                        vmaItinerarySchedule, vmaItinerary);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaItinerarySchedule> findByItineraryNo(
            java.lang.String itineraryNo)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().findByItineraryNo(itineraryNo);
    }

    public static int countByItineraryNo(java.lang.String itineraryNo)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().countByItineraryNo(itineraryNo);
    }

    public static com.fds.nsw.nghiepvu.model.VmaItinerarySchedule findByItineraryNo_NoticeShipType(
            java.lang.String itineraryNo, int noticeShipType) {
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

    public static org.json.JSONObject findVmaItinerary_VmaItinerarySchedule(
            java.lang.String itineraryNo, java.lang.String maritimeCode,
            java.lang.String shipPosition, java.lang.String markedAsArrival,
            java.lang.String markedAsDeparture, java.lang.String noticeShipType,
            java.lang.String shipBoat, java.lang.String portRegionCode,
            java.lang.String portHarbourCode, java.lang.String portWharfCode,
            java.lang.String cargoType, java.lang.String vrCode,
            java.lang.String routeLevelCode, java.lang.String chanelCodeList,
            java.lang.String timeOfArrival, java.lang.String timeOfDeparture,
            java.lang.String nameOfShip, java.lang.String flagStateOfShip,
            java.lang.String callSign, java.lang.String registryNumber, double gt,
            double dwt, double loa, double maxDraft, double shownDraftxF,
            double shownDraftxA, int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findVmaItinerary_VmaItinerarySchedule(itineraryNo,
                        maritimeCode, shipPosition, markedAsArrival, markedAsDeparture,
                        noticeShipType, shipBoat, portRegionCode, portHarbourCode,
                        portWharfCode, cargoType, vrCode, routeLevelCode, chanelCodeList,
                        timeOfArrival, timeOfDeparture, nameOfShip, flagStateOfShip,
                        callSign, registryNumber, gt, dwt, loa, maxDraft, shownDraftxF,
                        shownDraftxA, start, end);
    }

    public static long countVmaItinerary_VmaItinerarySchedule(
            java.lang.String itineraryNo, java.lang.String maritimeCode,
            java.lang.String shipPosition, java.lang.String markedAsArrival,
            java.lang.String markedAsDeparture, java.lang.String noticeShipType,
            java.lang.String shipBoat, java.lang.String portRegionCode,
            java.lang.String portHarbourCode, java.lang.String portWharfCode,
            java.lang.String cargoType, java.lang.String vrCode,
            java.lang.String routeLevelCode, java.lang.String chanelCodeList,
            java.lang.String timeOfArrival, java.lang.String timeOfDeparture,
            java.lang.String nameOfShip, java.lang.String flagStateOfShip,
            java.lang.String callSign, java.lang.String registryNumber, double gt,
            double dwt, double loa, double maxDraft, double shownDraftxF,
            double shownDraftxA)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .countVmaItinerary_VmaItinerarySchedule(itineraryNo,
                        maritimeCode, shipPosition, markedAsArrival, markedAsDeparture,
                        noticeShipType, shipBoat, portRegionCode, portHarbourCode,
                        portWharfCode, cargoType, vrCode, routeLevelCode, chanelCodeList,
                        timeOfArrival, timeOfDeparture, nameOfShip, flagStateOfShip,
                        callSign, registryNumber, gt, dwt, loa, maxDraft, shownDraftxF,
                        shownDraftxA);
    }

    public static org.json.JSONArray findVmaItinerary_VmaItinerarySchedule_VmaScheduleShifting(
            java.lang.String itineraryNo, java.lang.String maritimeCode,
            java.lang.String shipPosition, java.lang.String markedAsArrival,
            java.lang.String markedAsDeparture, java.lang.String noticeShipType,
            java.lang.String shipBoat, java.lang.String timeOfArrival,
            java.lang.String portRegionCode, java.lang.String certificateNo,
            java.lang.String requestState, boolean export,
            java.lang.String fromDate, java.lang.String toDate, int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findVmaItinerary_VmaItinerarySchedule_VmaScheduleShifting(itineraryNo,
                        maritimeCode, shipPosition, markedAsArrival, markedAsDeparture,
                        noticeShipType, shipBoat, timeOfArrival, portRegionCode,
                        certificateNo, requestState, export, fromDate, toDate, start, end);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaItinerarySchedule> findVmaItinerarySchedule(
            java.lang.String searchQuery, int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException,
            org.json.JSONException {
        return getService().findVmaItinerarySchedule(searchQuery, start, end);
    }

    public static org.json.JSONObject findVmaItinerarySchedule(
            java.lang.String searchQuery, java.lang.String countQuery, int start,
            int end)
            throws com.fds.nsw.kernel.exception.SystemException,
            org.json.JSONException {
        return getService()
                .findVmaItinerarySchedule(searchQuery, countQuery, start, end);
    }

    public static com.fds.nsw.nghiepvu.model.VmaItinerarySchedule findByDocumentName_DocumentYear_NoticeShipType(
            long documentName, int documentYear, int noticeShipType) {
        return getService()
                .findByDocumentName_DocumentYear_NoticeShipType(documentName,
                        documentYear, noticeShipType);
    }

    public static com.fds.nsw.nghiepvu.model.VmaItinerarySchedule findByitineraryNo_certificateNo_noticeShipType(
            java.lang.String itineraryNo, int noticeShipType,
            java.lang.String certificateNo)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaItineraryScheduleException {
        return getService()
                .findByitineraryNo_certificateNo_noticeShipType(itineraryNo,
                        noticeShipType, certificateNo);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaItinerarySchedule> findByDocumentName_DocumentYear(
            long documentName, int documentYear) {
        return getService()
                .findByDocumentName_DocumentYear(documentName, documentYear);
    }

    public static java.lang.String genCertificateNo(long documentName,
                                                    long documentYear, java.lang.String maritimeCode,
                                                    java.lang.String itineraryNo) {
        return getService()
                .genCertificateNo(documentName, documentYear, maritimeCode,
                        itineraryNo);
    }

    public static java.lang.String genDocumentNameVoy(
            java.lang.String maritimeCode) {
        return getService().genDocumentNameVoy(maritimeCode);
    }

    public static org.json.JSONObject findSoLuotLaiDat_VmaItinerarySchedule_VmaScheduleShifting(
            java.lang.String maritimeCode, java.lang.String tugboatCompanyCode,
            java.lang.String shipCode, java.lang.String reportUser,
            java.lang.String reportPeriod, java.lang.String reportYear,
            java.lang.String reportMonth, java.lang.String fromDate,
            java.lang.String toDate, int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findSoLuotLaiDat_VmaItinerarySchedule_VmaScheduleShifting(maritimeCode,
                        tugboatCompanyCode, shipCode, reportUser, reportPeriod, reportYear,
                        reportMonth, fromDate, toDate, start, end);
    }

    public static org.json.JSONObject findSoLuotHoaTieuDanTau_VmaItinerarySchedule_VmaScheduleShifting(
            java.lang.String maritimeCode, java.lang.String pilotCompanyCode,
            java.lang.String pilotCode, java.lang.String pilotTurn,
            java.lang.String reportUser, java.lang.String reportPeriod,
            java.lang.String reportYear, java.lang.String reportMonth,
            java.lang.String fromDate, java.lang.String toDate, int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findSoLuotHoaTieuDanTau_VmaItinerarySchedule_VmaScheduleShifting(maritimeCode,
                        pilotCompanyCode, pilotCode, pilotTurn, reportUser, reportPeriod,
                        reportYear, reportMonth, fromDate, toDate, start, end);
    }

    public static void clearService() {
        _service = null;
    }

    public static VmaItineraryScheduleLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(VmaItineraryScheduleLocalServiceImpl service) {
    }

    private static VmaItineraryScheduleLocalServiceImpl _service;
}
