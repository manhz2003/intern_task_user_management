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
 * The utility for the vma itinerary remarks local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.VmaItineraryRemarksLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see VmaItineraryRemarksLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.VmaItineraryRemarksLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.VmaItineraryRemarksLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.VmaItineraryRemarksLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class VmaItineraryRemarksLocalServiceUtil {
    public VmaItineraryRemarksLocalServiceUtil(VmaItineraryRemarksLocalServiceImpl service) {
        VmaItineraryRemarksLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.VmaItineraryRemarksLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the vma itinerary remarks to the database. Also notifies the appropriate model listeners.
     *
     * @param vmaItineraryRemarks the vma itinerary remarks
     * @return the vma itinerary remarks that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaItineraryRemark addVmaItineraryRemarks(
            com.fds.nsw.nghiepvu.model.VmaItineraryRemark vmaItineraryRemarks)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addVmaItineraryRemarks(vmaItineraryRemarks);
    }

    /**
     * Creates a new vma itinerary remarks with the primary key. Does not add the vma itinerary remarks to the database.
     *
     * @param id the primary key for the new vma itinerary remarks
     * @return the new vma itinerary remarks
     */
    public static com.fds.nsw.nghiepvu.model.VmaItineraryRemark createVmaItineraryRemarks(
            long id) {
        return getService().createVmaItineraryRemarks(id);
    }

    /**
     * Deletes the vma itinerary remarks with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the vma itinerary remarks
     * @return the vma itinerary remarks that was removed
     * @throws PortalException if a vma itinerary remarks with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaItineraryRemark deleteVmaItineraryRemarks(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaItineraryRemarks(id);
    }

    /**
     * Deletes the vma itinerary remarks from the database. Also notifies the appropriate model listeners.
     *
     * @param vmaItineraryRemarks the vma itinerary remarks
     * @return the vma itinerary remarks that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaItineraryRemark deleteVmaItineraryRemarks(
            com.fds.nsw.nghiepvu.model.VmaItineraryRemark vmaItineraryRemarks)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaItineraryRemarks(vmaItineraryRemarks);
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
   

    public static com.fds.nsw.nghiepvu.model.VmaItineraryRemark fetchVmaItineraryRemarks(
            long id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchVmaItineraryRemarks(id);
    }

    /**
     * Returns the vma itinerary remarks with the primary key.
     *
     * @param id the primary key of the vma itinerary remarks
     * @return the vma itinerary remarks
     * @throws PortalException if a vma itinerary remarks with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaItineraryRemark getVmaItineraryRemarks(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaItineraryRemarks(id);
    }

    

    /**
     * Returns a range of all the vma itinerary remarkses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of vma itinerary remarkses
     * @param end the upper bound of the range of vma itinerary remarkses (not inclusive)
     * @return the range of vma itinerary remarkses
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaItineraryRemark> getVmaItineraryRemarkses(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaItineraryRemarkses(start, end);
    }

    /**
     * Returns the number of vma itinerary remarkses.
     *
     * @return the number of vma itinerary remarkses
     * @throws SystemException if a system exception occurred
     */
    public static int getVmaItineraryRemarksesCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaItineraryRemarksesCount();
    }

    /**
     * Updates the vma itinerary remarks in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaItineraryRemarks the vma itinerary remarks
     * @return the vma itinerary remarks that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaItineraryRemark updateVmaItineraryRemarks(
            com.fds.nsw.nghiepvu.model.VmaItineraryRemark vmaItineraryRemarks)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaItineraryRemarks(vmaItineraryRemarks);
    }

    /**
     * Updates the vma itinerary remarks in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaItineraryRemarks the vma itinerary remarks
     * @param merge whether to merge the vma itinerary remarks with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the vma itinerary remarks that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaItineraryRemark updateVmaItineraryRemarks(
            com.fds.nsw.nghiepvu.model.VmaItineraryRemark vmaItineraryRemarks,
            boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaItineraryRemarks(vmaItineraryRemarks, merge);
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
   

    

    public static com.fds.nsw.nghiepvu.model.VmaItineraryRemark delete(
            long id)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaItineraryRemarksException {
        return getService().delete(id);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaItineraryRemark> findByItineraryNo(
            java.lang.String itineraryNo)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().findByItineraryNo(itineraryNo);
    }

    public static int countByItineraryNo(java.lang.String itineraryNo)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().countByItineraryNo(itineraryNo);
    }

    public static com.fds.nsw.nghiepvu.model.VmaItineraryRemark findByItineraryNo_NoticeShipType_SequenceNo(
            java.lang.String itineraryNo, int noticeShipType, int sequenceNo)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaItineraryRemarksException {
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

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaItineraryRemark> findByItineraryNo_NoticeShipType(
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

    public static org.json.JSONObject findVmaItineraryRemarks(
            java.lang.String itineraryNo, java.lang.String portofAuthority,
            java.lang.String nameOfShip, java.lang.Long documentName,
            java.lang.Integer documentYear, java.lang.Integer noticeShipType,
            java.lang.String requestDate, java.lang.String requestPerson,
            java.lang.String remarks, java.lang.Integer markedAsPending, int start,
            int end) throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findVmaItineraryRemarks(itineraryNo, portofAuthority,
                        nameOfShip, documentName, documentYear, noticeShipType,
                        requestDate, requestPerson, remarks, markedAsPending, start, end);
    }

    public static org.json.JSONObject findVmaItineraryRemarks(
            java.lang.String searchQuery, java.lang.String countQuery, int start,
            int end)
            throws com.fds.nsw.kernel.exception.SystemException,
            org.json.JSONException {
        return getService()
                .findVmaItineraryRemarks(searchQuery, countQuery, start, end);
    }

    public static long countVmaItineraryRemarks(java.lang.String itineraryNo,
                                                java.lang.String portofAuthority, java.lang.String nameOfShip,
                                                java.lang.Long documentName, java.lang.Integer documentYear,
                                                java.lang.Integer noticeShipType, java.lang.String requestDate,
                                                java.lang.String requestPerson, java.lang.String remarks,
                                                java.lang.Integer markedAsPending)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .countVmaItineraryRemarks(itineraryNo, portofAuthority,
                        nameOfShip, documentName, documentYear, noticeShipType,
                        requestDate, requestPerson, remarks, markedAsPending);
    }

    public static long countVmaItineraryRemarks(java.lang.String sql)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().countVmaItineraryRemarks(sql);
    }

    public static void clearService() {
        _service = null;
    }

    public static VmaItineraryRemarksLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(VmaItineraryRemarksLocalServiceImpl service) {
    }

    private static VmaItineraryRemarksLocalServiceImpl _service;
}
