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
 * The utility for the vma itinerary local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.VmaItineraryLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see VmaItineraryLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.VmaItineraryLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.VmaItineraryLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.VmaItineraryLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class VmaItineraryLocalServiceUtil {
    public VmaItineraryLocalServiceUtil(VmaItineraryLocalServiceImpl service) {
        VmaItineraryLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.VmaItineraryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the vma itinerary to the database. Also notifies the appropriate model listeners.
     *
     * @param vmaItinerary the vma itinerary
     * @return the vma itinerary that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaItinerary addVmaItinerary(
            com.fds.nsw.nghiepvu.model.VmaItinerary vmaItinerary)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addVmaItinerary(vmaItinerary);
    }

    /**
     * Creates a new vma itinerary with the primary key. Does not add the vma itinerary to the database.
     *
     * @param id the primary key for the new vma itinerary
     * @return the new vma itinerary
     */
    public static com.fds.nsw.nghiepvu.model.VmaItinerary createVmaItinerary(
            long id) {
        return getService().createVmaItinerary(id);
    }

    /**
     * Deletes the vma itinerary with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the vma itinerary
     * @return the vma itinerary that was removed
     * @throws PortalException if a vma itinerary with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaItinerary deleteVmaItinerary(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaItinerary(id);
    }

    /**
     * Deletes the vma itinerary from the database. Also notifies the appropriate model listeners.
     *
     * @param vmaItinerary the vma itinerary
     * @return the vma itinerary that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaItinerary deleteVmaItinerary(
            com.fds.nsw.nghiepvu.model.VmaItinerary vmaItinerary)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaItinerary(vmaItinerary);
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
   

    public static com.fds.nsw.nghiepvu.model.VmaItinerary fetchVmaItinerary(
            long id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchVmaItinerary(id);
    }

    /**
     * Returns the vma itinerary with the primary key.
     *
     * @param id the primary key of the vma itinerary
     * @return the vma itinerary
     * @throws PortalException if a vma itinerary with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaItinerary getVmaItinerary(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaItinerary(id);
    }

    

    /**
     * Returns a range of all the vma itineraries.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of vma itineraries
     * @param end the upper bound of the range of vma itineraries (not inclusive)
     * @return the range of vma itineraries
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaItinerary> getVmaItineraries(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaItineraries(start, end);
    }

    /**
     * Returns the number of vma itineraries.
     *
     * @return the number of vma itineraries
     * @throws SystemException if a system exception occurred
     */
    public static int getVmaItinerariesCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaItinerariesCount();
    }

    /**
     * Updates the vma itinerary in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaItinerary the vma itinerary
     * @return the vma itinerary that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaItinerary updateVmaItinerary(
            com.fds.nsw.nghiepvu.model.VmaItinerary vmaItinerary)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaItinerary(vmaItinerary);
    }

    /**
     * Updates the vma itinerary in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaItinerary the vma itinerary
     * @param merge whether to merge the vma itinerary with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the vma itinerary that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaItinerary updateVmaItinerary(
            com.fds.nsw.nghiepvu.model.VmaItinerary vmaItinerary,
            boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaItinerary(vmaItinerary, merge);
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
   

    

    public static org.json.JSONObject addVmaItinerary_VmaItinerarySchedule(
            com.fds.nsw.nghiepvu.model.VmaItinerary vmaItinerary,
            com.fds.nsw.nghiepvu.model.VmaItinerarySchedule vmaItinerarySchedule)
            throws com.fds.nsw.kernel.exception.SystemException,
            org.json.JSONException {
        return getService()
                .addVmaItinerary_VmaItinerarySchedule(vmaItinerary,
                        vmaItinerarySchedule);
    }

    public static org.json.JSONObject addVmaItinerary_VmaScheduleShifting(
            com.fds.nsw.nghiepvu.model.VmaItinerary vmaItinerary,
            com.fds.nsw.nghiepvu.model.VmaScheduleShifting vmaScheduleShifting)
            throws com.fds.nsw.kernel.exception.SystemException,
            org.json.JSONException {
        return getService()
                .addVmaItinerary_VmaScheduleShifting(vmaItinerary,
                        vmaScheduleShifting);
    }

    public static org.json.JSONObject addVmaItinerary_VmaScheduleAnchorage(
            com.fds.nsw.nghiepvu.model.VmaItinerary vmaItinerary,
            com.fds.nsw.nghiepvu.model.VmaScheduleAnchorage vmaScheduleAnchorage)
            throws com.fds.nsw.kernel.exception.SystemException,
            org.json.JSONException {
        return getService()
                .addVmaItinerary_VmaScheduleAnchorage(vmaItinerary,
                        vmaScheduleAnchorage);
    }

    public static org.json.JSONObject updateVmaItinerary(
            java.lang.String itineraryNo, java.lang.Integer markedAsArrival,
            java.lang.Integer markedAsDeparture,
            java.lang.Integer markedAsTransmit, java.lang.Integer markedAsVoyage) {
        return getService()
                .updateVmaItinerary(itineraryNo, markedAsArrival,
                        markedAsDeparture, markedAsTransmit, markedAsVoyage);
    }

    public static org.json.JSONObject updateVmaItinerary(
            long documentName, int documentYear, java.lang.Integer markedAsArrival,
            java.lang.Integer markedAsDeparture,
            java.lang.Integer markedAsTransmit, java.lang.Integer markedAsVoyage) {
        return getService()
                .updateVmaItinerary(documentName, documentYear,
                        markedAsArrival, markedAsDeparture, markedAsTransmit, markedAsVoyage);
    }

    public static com.fds.nsw.nghiepvu.model.VmaItinerary delete(long id)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaItineraryException {
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

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaItinerary> findByCallSign(
            java.lang.String callSign)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaItineraryException {
        return getService().findByCallSign(callSign);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaItinerary> findByIMONumber(
            java.lang.String imoNumber)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaItineraryException {
        return getService().findByIMONumber(imoNumber);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaItinerary> findByIMONumber_CallSign(
            java.lang.String imoNumber, java.lang.String callSign)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaItineraryException {
        return getService().findByIMONumber_CallSign(imoNumber, callSign);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaItinerary> findByRegistryNumber(
            java.lang.String registryNumber)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaItineraryException {
        return getService().findByRegistryNumber(registryNumber);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaItinerary> findByVRCode(
            java.lang.String vrCode)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaItineraryException {
        return getService().findByVRCode(vrCode);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaItinerary> findByVRCode_RegistryNumber(
            java.lang.String imoNumber, java.lang.String registryNumber)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaItineraryException {
        return getService()
                .findByVRCode_RegistryNumber(imoNumber, registryNumber);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaItinerary> findAll(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().findAll(start, end);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaItinerary> findAll(
            int start, int end,
            com.fds.nsw.kernel.util.OrderByComparator orderByComparator)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().findAll(start, end, orderByComparator);
    }

    public static com.fds.nsw.nghiepvu.model.VmaItinerary fetchByitineraryNo(
            java.lang.String itineraryNo)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchByitineraryNo(itineraryNo);
    }

    public static com.fds.nsw.nghiepvu.model.VmaItinerary findByitineraryNo(
            java.lang.String itineraryNo)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaItineraryException {
        return getService().findByitineraryNo(itineraryNo);
    }

    public static com.fds.nsw.nghiepvu.model.VmaItinerary findVmaItineraryLeftByIMOandCallSign(
            java.lang.String maritimeCode, java.util.Date TimeOfDeparture,
            java.lang.String callSign, java.lang.String imo,
            java.lang.String ShipPosition) {
        return getService()
                .findVmaItineraryLeftByIMOandCallSign(maritimeCode,
                        TimeOfDeparture, callSign, imo, ShipPosition);
    }

    public static com.fds.nsw.nghiepvu.model.VmaItinerary findVmaItineraryByVoyageIMOandCallSign(
            java.lang.String maritimeCode, java.lang.String VoyageNumber,
            java.lang.String callSign, java.lang.String imo,
            java.lang.String ShipPosition) {
        return getService()
                .findVmaItineraryByVoyageIMOandCallSign(maritimeCode,
                        VoyageNumber, callSign, imo, ShipPosition);
    }

    public static java.lang.String getItineraryNoWithRule(
            java.lang.String MaritimeCode) {
        return getService().getItineraryNoWithRule(MaritimeCode);
    }

    public static com.fds.nsw.nghiepvu.model.VmaItinerary fetchBydocumentNameIN_documentYearIN(
            long documentNameIN, int documentYearIN) {
        return getService()
                .fetchBydocumentNameIN_documentYearIN(documentNameIN,
                        documentYearIN);
    }

    public static com.fds.nsw.nghiepvu.model.VmaItinerary fetchBydocumentNameOUT_documentYearOUT(
            long documentNameOUT, int documentYearOUT) {
        return getService()
                .fetchBydocumentNameOUT_documentYearOUT(documentNameOUT,
                        documentYearOUT);
    }

    public static org.json.JSONObject findVmaItinerary(
            java.lang.String searchQuery, java.lang.String countQuery, int start,
            int end)
            throws com.fds.nsw.kernel.exception.SystemException,
            org.json.JSONException {
        return getService().findVmaItinerary(searchQuery, countQuery, start, end);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaItinerary> findVmaItinerary_ChuyenTuyen(
            java.lang.String searchQuery, int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().findVmaItinerary_ChuyenTuyen(searchQuery, start, end);
    }

    public static long countVmaItinerary(java.lang.String countQuery)
            throws com.fds.nsw.kernel.exception.SystemException,
            org.json.JSONException {
        return getService().countVmaItinerary(countQuery);
    }

    public static org.json.JSONObject getModelMau12T(
            java.lang.String maritimeCode, java.lang.String startDate,
            java.lang.String endDate, java.lang.String objName)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .getModelMau12T(maritimeCode, startDate, endDate, objName);
    }

    public static org.json.JSONObject getModelMau14T(
            java.lang.String maritimeCode, java.lang.String startDate,
            java.lang.String endDate, java.lang.String objName)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .getModelMau14T(maritimeCode, startDate, endDate, objName);
    }

    public static vn.gt.portlet.baocao.bc12bt.BC12BTLuotHHModel getModelMau12BTLuotHH(
            java.lang.String maritimeCode, java.lang.String startDate,
            java.lang.String endDate)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .getModelMau12BTLuotHH(maritimeCode, startDate, endDate);
    }

    public static org.json.JSONArray getModelMau11T(
            java.lang.String maritimeCode, java.lang.String startDate,
            java.lang.String endDate)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getModelMau11T(maritimeCode, startDate, endDate);
    }

    public static org.json.JSONArray getModelMau11BT(
            java.lang.String maritimeCode, java.lang.String startDate,
            java.lang.String endDate)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getModelMau11BT(maritimeCode, startDate, endDate);
    }

    public static org.json.JSONObject getModelMau19T(
            java.lang.String maritimeCode, java.lang.String startDate,
            java.lang.String endDate)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getModelMau19T(maritimeCode, startDate, endDate);
    }

    public static org.json.JSONArray getModelMau65T(
            java.lang.String maritimeCode, java.lang.String startDate,
            java.lang.String endDate)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getModelMau65T(maritimeCode, startDate, endDate);
    }

    public static org.json.JSONArray getModelMau66T(
            java.lang.String maritimeCode, java.lang.String startDate,
            java.lang.String endDate)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getModelMau66T(maritimeCode, startDate, endDate);
    }

    public static org.json.JSONObject getModelMau67T(
            java.lang.String maritimeCode, java.lang.String startDate,
            java.lang.String endDate)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getModelMau67T(maritimeCode, startDate, endDate);
    }

    public static org.json.JSONObject getModelMau13T(
            java.lang.String maritimeCode, java.lang.String startDate,
            java.lang.String endDate, java.lang.String objName)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .getModelMau13T(maritimeCode, startDate, endDate, objName);
    }

    public static org.json.JSONObject getModelMau16T(
            java.lang.String maritimeCode, java.lang.String startDate,
            java.lang.String endDate, java.lang.String objName)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .getModelMau16T(maritimeCode, startDate, endDate, objName);
    }

    public static org.json.JSONObject getModelMau20T(
            java.lang.String maritimeCode, java.lang.String startDate,
            java.lang.String endDate)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getModelMau20T(maritimeCode, startDate, endDate);
    }

    public static org.json.JSONObject getModelMau21T(
            java.lang.String maritimeCode, java.lang.String startDate,
            java.lang.String endDate)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getModelMau21T(maritimeCode, startDate, endDate);
    }

    public static org.json.JSONArray getModelMau46_47_48_52_71_72_73_79T(
            java.lang.String query)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getModelMau46_47_48_52_71_72_73_79T(query);
    }

    public static org.json.JSONArray getModelMau49_50_51T(
            java.lang.String maritimeCode, java.lang.String nameOfShip,
            java.lang.String imoNumber, java.lang.String registryNumber,
            java.lang.String vrCode, java.lang.String flagStateOfShip,
            java.lang.String from_gt, java.lang.String to_gt,
            java.lang.String from_dwt, java.lang.String to_dwt,
            java.lang.String from_loa, java.lang.String to_loa,
            java.lang.String lastPortCode, java.lang.String nextPortCode,
            java.lang.String arrivalShipAgency,
            java.lang.String departureShipAgency, java.lang.String cargoType,
            java.lang.String cargoCategory, java.lang.String callSign,
            java.lang.String startDate, java.lang.String endDate)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .getModelMau49_50_51T(maritimeCode, nameOfShip, imoNumber,
                        registryNumber, vrCode, flagStateOfShip, from_gt, to_gt, from_dwt,
                        to_dwt, from_loa, to_loa, lastPortCode, nextPortCode,
                        arrivalShipAgency, departureShipAgency, cargoType, cargoCategory,
                        callSign, startDate, endDate);
    }

    public static org.json.JSONArray getModelMauBC15T(
            java.lang.String maritimeCode, java.lang.String cargoTypeContainer,
            java.lang.String cargoTypeKho, java.lang.String cargoTypeLong,
            java.lang.String portHarbourCode, java.lang.String startDate,
            java.lang.String endDate)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .getModelMauBC15T(maritimeCode, cargoTypeContainer,
                        cargoTypeKho, cargoTypeLong, portHarbourCode, startDate, endDate);
    }

    public static org.json.JSONArray dsNoiChuyen(
            java.lang.String shipPosition, java.lang.String imoNumber,
            java.lang.String callSign, java.lang.String registryNumber,
            java.lang.String nameOfShip, java.lang.String documentNameIN,
            java.lang.String timeOfArrival, int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .dsNoiChuyen(shipPosition, imoNumber, callSign,
                        registryNumber, nameOfShip, documentNameIN, timeOfArrival, start,
                        end);
    }

    public static void clearService() {
        _service = null;
    }

    public static VmaItineraryLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(VmaItineraryLocalServiceImpl service) {
    }

    private static VmaItineraryLocalServiceImpl _service;
}
