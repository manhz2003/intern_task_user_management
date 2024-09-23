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
 * The utility for the vma itinerary protest local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.VmaItineraryProtestLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see VmaItineraryProtestLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.VmaItineraryProtestLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.VmaItineraryProtestLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.VmaItineraryProtestLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class VmaItineraryProtestLocalServiceUtil {
    public VmaItineraryProtestLocalServiceUtil(VmaItineraryProtestLocalServiceImpl service) {
        VmaItineraryProtestLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.VmaItineraryProtestLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the vma itinerary protest to the database. Also notifies the appropriate model listeners.
     *
     * @param vmaItineraryProtest the vma itinerary protest
     * @return the vma itinerary protest that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaItineraryProtest addVmaItineraryProtest(
            com.fds.nsw.nghiepvu.model.VmaItineraryProtest vmaItineraryProtest)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addVmaItineraryProtest(vmaItineraryProtest);
    }

    /**
     * Creates a new vma itinerary protest with the primary key. Does not add the vma itinerary protest to the database.
     *
     * @param id the primary key for the new vma itinerary protest
     * @return the new vma itinerary protest
     */
    public static com.fds.nsw.nghiepvu.model.VmaItineraryProtest createVmaItineraryProtest(
            long id) {
        return getService().createVmaItineraryProtest(id);
    }

    /**
     * Deletes the vma itinerary protest with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the vma itinerary protest
     * @return the vma itinerary protest that was removed
     * @throws PortalException if a vma itinerary protest with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaItineraryProtest deleteVmaItineraryProtest(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaItineraryProtest(id);
    }

    /**
     * Deletes the vma itinerary protest from the database. Also notifies the appropriate model listeners.
     *
     * @param vmaItineraryProtest the vma itinerary protest
     * @return the vma itinerary protest that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaItineraryProtest deleteVmaItineraryProtest(
            com.fds.nsw.nghiepvu.model.VmaItineraryProtest vmaItineraryProtest)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaItineraryProtest(vmaItineraryProtest);
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
   

    public static com.fds.nsw.nghiepvu.model.VmaItineraryProtest fetchVmaItineraryProtest(
            long id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchVmaItineraryProtest(id);
    }

    /**
     * Returns the vma itinerary protest with the primary key.
     *
     * @param id the primary key of the vma itinerary protest
     * @return the vma itinerary protest
     * @throws PortalException if a vma itinerary protest with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaItineraryProtest getVmaItineraryProtest(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaItineraryProtest(id);
    }

    

    /**
     * Returns a range of all the vma itinerary protests.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of vma itinerary protests
     * @param end the upper bound of the range of vma itinerary protests (not inclusive)
     * @return the range of vma itinerary protests
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaItineraryProtest> getVmaItineraryProtests(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaItineraryProtests(start, end);
    }

    /**
     * Returns the number of vma itinerary protests.
     *
     * @return the number of vma itinerary protests
     * @throws SystemException if a system exception occurred
     */
    public static int getVmaItineraryProtestsCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaItineraryProtestsCount();
    }

    /**
     * Updates the vma itinerary protest in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaItineraryProtest the vma itinerary protest
     * @return the vma itinerary protest that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaItineraryProtest updateVmaItineraryProtest(
            com.fds.nsw.nghiepvu.model.VmaItineraryProtest vmaItineraryProtest)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaItineraryProtest(vmaItineraryProtest);
    }

    /**
     * Updates the vma itinerary protest in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaItineraryProtest the vma itinerary protest
     * @param merge whether to merge the vma itinerary protest with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the vma itinerary protest that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaItineraryProtest updateVmaItineraryProtest(
            com.fds.nsw.nghiepvu.model.VmaItineraryProtest vmaItineraryProtest,
            boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaItineraryProtest(vmaItineraryProtest, merge);
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
   

    

    public static com.fds.nsw.nghiepvu.model.VmaItineraryProtest delete(
            long id)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaItineraryProtestException {
        return getService().delete(id);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaItineraryProtest> findByItineraryNo(
            java.lang.String itineraryNo)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().findByItineraryNo(itineraryNo);
    }

    public static int countByItineraryNo(java.lang.String itineraryNo)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().countByItineraryNo(itineraryNo);
    }

    public static com.fds.nsw.nghiepvu.model.VmaItineraryProtest findByItineraryNo_NoticeShipType_SequenceNo(
            java.lang.String itineraryNo, int noticeShipType, int sequenceNo)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaItineraryProtestException {
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

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaItineraryProtest> findByItineraryNo_NoticeShipType(
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

    public static org.json.JSONObject findVmaItineraryProtest(
            java.lang.String itineraryNo, java.lang.String portofAuthority,
            java.lang.String nameOfShip, java.lang.Long documentName,
            java.lang.Integer documentYear, java.lang.Integer noticeShipType,
            java.lang.String shipOperatorCode, java.lang.String shipAgencyCode,
            java.lang.String protestDate, java.lang.String protestRemarks,
            java.lang.Integer makePayment, int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findVmaItineraryProtest(itineraryNo, portofAuthority,
                        nameOfShip, documentName, documentYear, noticeShipType,
                        shipOperatorCode, shipAgencyCode, protestDate, protestRemarks,
                        makePayment, start, end);
    }

    public static org.json.JSONObject findVmaItineraryProtest(
            java.lang.String searchQuery, java.lang.String countQuery, int start,
            int end)
            throws com.fds.nsw.kernel.exception.SystemException,
            org.json.JSONException {
        return getService()
                .findVmaItineraryProtest(searchQuery, countQuery, start, end);
    }

    public static long countVmaItineraryProtest(java.lang.String itineraryNo,
                                                java.lang.String portofAuthority, java.lang.String nameOfShip,
                                                java.lang.Long documentName, java.lang.Integer documentYear,
                                                java.lang.Integer noticeShipType, java.lang.String shipOperatorCode,
                                                java.lang.String shipAgencyCode, java.lang.String protestDate,
                                                java.lang.String protestRemarks, java.lang.Integer makePayment)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .countVmaItineraryProtest(itineraryNo, portofAuthority,
                        nameOfShip, documentName, documentYear, noticeShipType,
                        shipOperatorCode, shipAgencyCode, protestDate, protestRemarks,
                        makePayment);
    }

    public static long countVmaItineraryProtest(java.lang.String sql)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().countVmaItineraryProtest(sql);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaItineraryProtest> findByItineraryNo_documentaryCode(
            java.lang.String itineraryNo, java.lang.String documentaryCode) {
        return getService()
                .findByItineraryNo_documentaryCode(itineraryNo,
                        documentaryCode);
    }

    public static void clearService() {
        _service = null;
    }

    public static VmaItineraryProtestLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(VmaItineraryProtestLocalServiceImpl service) {
    }

    private static VmaItineraryProtestLocalServiceImpl _service;
}
