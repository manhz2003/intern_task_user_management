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
 * The utility for the vma schedule cargolist local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.VmaScheduleCargolistLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see VmaScheduleCargolistLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.VmaScheduleCargolistLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.VmaScheduleCargolistLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.VmaScheduleCargolistLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class VmaScheduleCargolistLocalServiceUtil {
    public VmaScheduleCargolistLocalServiceUtil(VmaScheduleCargolistLocalServiceImpl service) {
        VmaScheduleCargolistLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.VmaScheduleCargolistLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the vma schedule cargolist to the database. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleCargolist the vma schedule cargolist
     * @return the vma schedule cargolist that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleCargolist addVmaScheduleCargolist(
            com.fds.nsw.nghiepvu.model.VmaScheduleCargolist vmaScheduleCargolist)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addVmaScheduleCargolist(vmaScheduleCargolist);
    }

    /**
     * Creates a new vma schedule cargolist with the primary key. Does not add the vma schedule cargolist to the database.
     *
     * @param id the primary key for the new vma schedule cargolist
     * @return the new vma schedule cargolist
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleCargolist createVmaScheduleCargolist(
            long id) {
        return getService().createVmaScheduleCargolist(id);
    }

    /**
     * Deletes the vma schedule cargolist with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the vma schedule cargolist
     * @return the vma schedule cargolist that was removed
     * @throws PortalException if a vma schedule cargolist with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleCargolist deleteVmaScheduleCargolist(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaScheduleCargolist(id);
    }

    /**
     * Deletes the vma schedule cargolist from the database. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleCargolist the vma schedule cargolist
     * @return the vma schedule cargolist that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleCargolist deleteVmaScheduleCargolist(
            com.fds.nsw.nghiepvu.model.VmaScheduleCargolist vmaScheduleCargolist)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaScheduleCargolist(vmaScheduleCargolist);
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
   

    public static com.fds.nsw.nghiepvu.model.VmaScheduleCargolist fetchVmaScheduleCargolist(
            long id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchVmaScheduleCargolist(id);
    }

    /**
     * Returns the vma schedule cargolist with the primary key.
     *
     * @param id the primary key of the vma schedule cargolist
     * @return the vma schedule cargolist
     * @throws PortalException if a vma schedule cargolist with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleCargolist getVmaScheduleCargolist(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaScheduleCargolist(id);
    }

    

    /**
     * Returns a range of all the vma schedule cargolists.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of vma schedule cargolists
     * @param end the upper bound of the range of vma schedule cargolists (not inclusive)
     * @return the range of vma schedule cargolists
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaScheduleCargolist> getVmaScheduleCargolists(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaScheduleCargolists(start, end);
    }

    /**
     * Returns the number of vma schedule cargolists.
     *
     * @return the number of vma schedule cargolists
     * @throws SystemException if a system exception occurred
     */
    public static int getVmaScheduleCargolistsCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaScheduleCargolistsCount();
    }

    /**
     * Updates the vma schedule cargolist in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleCargolist the vma schedule cargolist
     * @return the vma schedule cargolist that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleCargolist updateVmaScheduleCargolist(
            com.fds.nsw.nghiepvu.model.VmaScheduleCargolist vmaScheduleCargolist)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaScheduleCargolist(vmaScheduleCargolist);
    }

    /**
     * Updates the vma schedule cargolist in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleCargolist the vma schedule cargolist
     * @param merge whether to merge the vma schedule cargolist with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the vma schedule cargolist that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleCargolist updateVmaScheduleCargolist(
            com.fds.nsw.nghiepvu.model.VmaScheduleCargolist vmaScheduleCargolist,
            boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .updateVmaScheduleCargolist(vmaScheduleCargolist, merge);
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
   

    

    public static com.fds.nsw.nghiepvu.model.VmaScheduleCargolist delete(
            long id)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaScheduleCargolistException {
        return getService().delete(id);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaScheduleCargolist> findByItineraryNo(
            java.lang.String itineraryNo)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().findByItineraryNo(itineraryNo);
    }

    public static int countByItineraryNo(java.lang.String itineraryNo)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().countByItineraryNo(itineraryNo);
    }

    public static com.fds.nsw.nghiepvu.model.VmaScheduleCargolist findByItineraryNo_NoticeShipType_SequenceNo(
            java.lang.String itineraryNo, int noticeShipType, int sequenceNo)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaScheduleCargolistException {
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

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaScheduleCargolist> findByItineraryNo_NoticeShipType(
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

    public static org.json.JSONObject findVmaScheduleCargolist(
            java.lang.String searchQuery, java.lang.String countQuery, int start,
            int end)
            throws com.fds.nsw.kernel.exception.SystemException,
            org.json.JSONException {
        return getService()
                .findVmaScheduleCargolist(searchQuery, countQuery, start, end);
    }

    public static long countVmaScheduleCargolist(java.lang.String sql)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().countVmaScheduleCargolist(sql);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaScheduleCargolist> findByitineraryScheduleId(
            long itineraryScheduleId) {
        return getService().findByitineraryScheduleId(itineraryScheduleId);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaScheduleCargolist> findByItineraryNo_documentaryCode(
            java.lang.String itineraryNo, java.lang.String documentaryCode) {
        return getService()
                .findByItineraryNo_documentaryCode(itineraryNo,
                        documentaryCode);
    }

    public static com.fds.nsw.nghiepvu.model.VmaScheduleCargolist findByScheduleAnchorageId(
            long scheduleAnchorageId) {
        return getService().findByScheduleAnchorageId(scheduleAnchorageId);
    }

    public static void clearService() {
        _service = null;
    }

    public static VmaScheduleCargolistLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(VmaScheduleCargolistLocalServiceImpl service) {
    }

    private static VmaScheduleCargolistLocalServiceImpl _service;
}
