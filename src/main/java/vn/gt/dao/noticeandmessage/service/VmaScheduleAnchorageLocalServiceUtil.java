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
 * The utility for the vma schedule anchorage local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.VmaScheduleAnchorageLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see VmaScheduleAnchorageLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.VmaScheduleAnchorageLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.VmaScheduleAnchorageLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.VmaScheduleAnchorageLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class VmaScheduleAnchorageLocalServiceUtil {
    public VmaScheduleAnchorageLocalServiceUtil(VmaScheduleAnchorageLocalServiceImpl service) {
        VmaScheduleAnchorageLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.VmaScheduleAnchorageLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the vma schedule anchorage to the database. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleAnchorage the vma schedule anchorage
     * @return the vma schedule anchorage that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleAnchorage addVmaScheduleAnchorage(
            com.fds.nsw.nghiepvu.model.VmaScheduleAnchorage vmaScheduleAnchorage)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addVmaScheduleAnchorage(vmaScheduleAnchorage);
    }

    /**
     * Creates a new vma schedule anchorage with the primary key. Does not add the vma schedule anchorage to the database.
     *
     * @param id the primary key for the new vma schedule anchorage
     * @return the new vma schedule anchorage
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleAnchorage createVmaScheduleAnchorage(
            long id) {
        return getService().createVmaScheduleAnchorage(id);
    }

    /**
     * Deletes the vma schedule anchorage with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the vma schedule anchorage
     * @return the vma schedule anchorage that was removed
     * @throws PortalException if a vma schedule anchorage with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleAnchorage deleteVmaScheduleAnchorage(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaScheduleAnchorage(id);
    }

    /**
     * Deletes the vma schedule anchorage from the database. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleAnchorage the vma schedule anchorage
     * @return the vma schedule anchorage that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleAnchorage deleteVmaScheduleAnchorage(
            com.fds.nsw.nghiepvu.model.VmaScheduleAnchorage vmaScheduleAnchorage)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaScheduleAnchorage(vmaScheduleAnchorage);
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
   

    public static com.fds.nsw.nghiepvu.model.VmaScheduleAnchorage fetchVmaScheduleAnchorage(
            long id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchVmaScheduleAnchorage(id);
    }

    /**
     * Returns the vma schedule anchorage with the primary key.
     *
     * @param id the primary key of the vma schedule anchorage
     * @return the vma schedule anchorage
     * @throws PortalException if a vma schedule anchorage with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleAnchorage getVmaScheduleAnchorage(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaScheduleAnchorage(id);
    }

    

    /**
     * Returns a range of all the vma schedule anchorages.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of vma schedule anchorages
     * @param end the upper bound of the range of vma schedule anchorages (not inclusive)
     * @return the range of vma schedule anchorages
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaScheduleAnchorage> getVmaScheduleAnchorages(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaScheduleAnchorages(start, end);
    }

    /**
     * Returns the number of vma schedule anchorages.
     *
     * @return the number of vma schedule anchorages
     * @throws SystemException if a system exception occurred
     */
    public static int getVmaScheduleAnchoragesCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaScheduleAnchoragesCount();
    }

    /**
     * Updates the vma schedule anchorage in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleAnchorage the vma schedule anchorage
     * @return the vma schedule anchorage that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleAnchorage updateVmaScheduleAnchorage(
            com.fds.nsw.nghiepvu.model.VmaScheduleAnchorage vmaScheduleAnchorage)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaScheduleAnchorage(vmaScheduleAnchorage);
    }

    /**
     * Updates the vma schedule anchorage in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleAnchorage the vma schedule anchorage
     * @param merge whether to merge the vma schedule anchorage with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the vma schedule anchorage that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleAnchorage updateVmaScheduleAnchorage(
            com.fds.nsw.nghiepvu.model.VmaScheduleAnchorage vmaScheduleAnchorage,
            boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .updateVmaScheduleAnchorage(vmaScheduleAnchorage, merge);
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
   

    

    public static com.fds.nsw.nghiepvu.model.VmaScheduleAnchorage delete(
            long id)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaScheduleAnchorageException {
        return getService().delete(id);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaScheduleAnchorage> findByItineraryNo(
            java.lang.String itineraryNo)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().findByItineraryNo(itineraryNo);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaScheduleAnchorage> findByItineraryNo_MakePayment(
            java.lang.String itineraryNo, int makePayment)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findByItineraryNo_MakePayment(itineraryNo, makePayment);
    }

    public static int countByItineraryNo(java.lang.String itineraryNo)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().countByItineraryNo(itineraryNo);
    }

    public static com.fds.nsw.nghiepvu.model.VmaScheduleAnchorage findByItineraryNo_SequenceNo(
            java.lang.String itineraryNo, int sequenceNo)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaScheduleAnchorageException {
        return getService().findByItineraryNo_SequenceNo(itineraryNo, sequenceNo);
    }

    public static int countByItineraryNo_SequenceNo(
            java.lang.String itineraryNo, int sequenceNo)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .countByItineraryNo_SequenceNo(itineraryNo, sequenceNo);
    }

    public static int countByitineraryNo_makePayment(
            java.lang.String itineraryNo, int makePayment)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .countByitineraryNo_makePayment(itineraryNo, makePayment);
    }

    public static org.json.JSONObject updateVmaScheduleAnchorage_VmaItinerary(
            com.fds.nsw.nghiepvu.model.VmaScheduleAnchorage vmaScheduleAnchorage,
            com.fds.nsw.nghiepvu.model.VmaItinerary vmaItinerary)
            throws com.fds.nsw.kernel.exception.SystemException,
            org.json.JSONException {
        return getService()
                .updateVmaScheduleAnchorage_VmaItinerary(vmaScheduleAnchorage,
                        vmaItinerary);
    }

    public static org.json.JSONObject findVmaItinerary_VmaScheduleAnchorage(
            java.lang.String itineraryNo, java.lang.String nameOfShip,
            java.lang.String purposeCode, java.lang.String anchoringDateFrom,
            java.lang.String anchoringDateTo,
            java.lang.String anchoringPortRegionCode,
            java.lang.String anchoringPortHarbourCode,
            java.lang.String anchoringPortWharfCode,
            java.lang.String shipOwnerCode, java.lang.String shipOperatorCode,
            java.lang.String shipAgencyCode, java.lang.Integer makePayment,
            java.lang.Integer anchorageSupplement,
            java.lang.String documentaryCode, java.lang.Long itineraryScheduleId,
            java.lang.Integer shipPosition, java.lang.String flag,
            java.lang.String flag2, java.lang.String imoNumber,
            java.lang.String callSign, java.lang.String registryNumber,
            java.lang.String timeOfArrival, int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findVmaItinerary_VmaScheduleAnchorage(itineraryNo,
                        nameOfShip, purposeCode, anchoringDateFrom, anchoringDateTo,
                        anchoringPortRegionCode, anchoringPortHarbourCode,
                        anchoringPortWharfCode, shipOwnerCode, shipOperatorCode,
                        shipAgencyCode, makePayment, anchorageSupplement, documentaryCode,
                        itineraryScheduleId, shipPosition, flag, flag2, imoNumber,
                        callSign, registryNumber, timeOfArrival, start, end);
    }

    public static long countVmaItinerary_VmaScheduleAnchorage(
            java.lang.String itineraryNo, java.lang.String nameOfShip,
            java.lang.String purposeCode, java.lang.String anchoringDateFrom,
            java.lang.String anchoringDateTo,
            java.lang.String anchoringPortRegionCode,
            java.lang.String anchoringPortHarbourCode,
            java.lang.String anchoringPortWharfCode,
            java.lang.String shipOwnerCode, java.lang.String shipOperatorCode,
            java.lang.String shipAgencyCode, java.lang.Integer makePayment,
            java.lang.Integer anchorageSupplement,
            java.lang.String documentaryCode, java.lang.Long itineraryScheduleId,
            java.lang.Integer shipPosition, java.lang.String flag,
            java.lang.String imoNumber, java.lang.String callSign,
            java.lang.String registryNumber, java.lang.String timeOfArrival)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .countVmaItinerary_VmaScheduleAnchorage(itineraryNo,
                        nameOfShip, purposeCode, anchoringDateFrom, anchoringDateTo,
                        anchoringPortRegionCode, anchoringPortHarbourCode,
                        anchoringPortWharfCode, shipOwnerCode, shipOperatorCode,
                        shipAgencyCode, makePayment, anchorageSupplement, documentaryCode,
                        itineraryScheduleId, shipPosition, flag, imoNumber, callSign,
                        registryNumber, timeOfArrival);
    }

    public static com.fds.nsw.nghiepvu.model.VmaScheduleAnchorage fetchByanchorageSupplement(
            long anchorageSupplement) {
        return getService().fetchByanchorageSupplement(anchorageSupplement);
    }

    public static com.fds.nsw.nghiepvu.model.VmaScheduleAnchorage fetchByitineraryScheduleId(
            long itineraryScheduleId) {
        return getService().fetchByitineraryScheduleId(itineraryScheduleId);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaScheduleAnchorage> findByitineraryNo_makePayment_anchorageSupplement(
            java.lang.String itineraryNo, int makePayment, int anchorageSupplement) {
        return getService()
                .findByitineraryNo_makePayment_anchorageSupplement(itineraryNo,
                        makePayment, anchorageSupplement);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaScheduleAnchorage> findByitineraryNo_noticeShipType_makePayment(
            java.lang.String itineraryNo, int noticeShipType, int makePayment) {
        return getService()
                .findByitineraryNo_noticeShipType_makePayment(itineraryNo,
                        noticeShipType, makePayment);
    }

    public static org.json.JSONArray getVmaScheduleAnchorageDuration(
            java.lang.String itineraryNo, int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .getVmaScheduleAnchorageDuration(itineraryNo, start, end);
    }

    public static com.fds.nsw.nghiepvu.model.VmaScheduleAnchorage findByitineraryScheduleId(
            long itineraryScheduleId) {
        return getService().findByitineraryScheduleId(itineraryScheduleId);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaScheduleAnchorage> findByItineraryNo_documentaryCode(
            java.lang.String itineraryNo, java.lang.String documentaryCode) {
        return getService()
                .findByItineraryNo_documentaryCode(itineraryNo,
                        documentaryCode);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaScheduleAnchorage> findByItineraryNo_noticeShipType(
            java.lang.String itineraryNo, int noticeShipType) {
        return getService()
                .findByItineraryNo_noticeShipType(itineraryNo, noticeShipType);
    }

    public static void clearService() {
        _service = null;
    }

    public static VmaScheduleAnchorageLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(VmaScheduleAnchorageLocalServiceImpl service) {
    }

    private static VmaScheduleAnchorageLocalServiceImpl _service;
}
