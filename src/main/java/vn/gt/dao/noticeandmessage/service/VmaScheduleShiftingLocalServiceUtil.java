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
 * The utility for the vma schedule shifting local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.VmaScheduleShiftingLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see VmaScheduleShiftingLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.VmaScheduleShiftingLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.VmaScheduleShiftingLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.VmaScheduleShiftingLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class VmaScheduleShiftingLocalServiceUtil {
    public VmaScheduleShiftingLocalServiceUtil(VmaScheduleShiftingLocalServiceImpl service) {
        VmaScheduleShiftingLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.VmaScheduleShiftingLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the vma schedule shifting to the database. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleShifting the vma schedule shifting
     * @return the vma schedule shifting that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleShifting addVmaScheduleShifting(
            com.fds.nsw.nghiepvu.model.VmaScheduleShifting vmaScheduleShifting)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addVmaScheduleShifting(vmaScheduleShifting);
    }

    /**
     * Creates a new vma schedule shifting with the primary key. Does not add the vma schedule shifting to the database.
     *
     * @param id the primary key for the new vma schedule shifting
     * @return the new vma schedule shifting
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleShifting createVmaScheduleShifting(
            long id) {
        return getService().createVmaScheduleShifting(id);
    }

    /**
     * Deletes the vma schedule shifting with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the vma schedule shifting
     * @return the vma schedule shifting that was removed
     * @throws PortalException if a vma schedule shifting with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleShifting deleteVmaScheduleShifting(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaScheduleShifting(id);
    }

    /**
     * Deletes the vma schedule shifting from the database. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleShifting the vma schedule shifting
     * @return the vma schedule shifting that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleShifting deleteVmaScheduleShifting(
            com.fds.nsw.nghiepvu.model.VmaScheduleShifting vmaScheduleShifting)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaScheduleShifting(vmaScheduleShifting);
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
   

    public static com.fds.nsw.nghiepvu.model.VmaScheduleShifting fetchVmaScheduleShifting(
            long id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchVmaScheduleShifting(id);
    }

    /**
     * Returns the vma schedule shifting with the primary key.
     *
     * @param id the primary key of the vma schedule shifting
     * @return the vma schedule shifting
     * @throws PortalException if a vma schedule shifting with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleShifting getVmaScheduleShifting(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaScheduleShifting(id);
    }

    

    /**
     * Returns a range of all the vma schedule shiftings.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of vma schedule shiftings
     * @param end the upper bound of the range of vma schedule shiftings (not inclusive)
     * @return the range of vma schedule shiftings
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaScheduleShifting> getVmaScheduleShiftings(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaScheduleShiftings(start, end);
    }

    /**
     * Returns the number of vma schedule shiftings.
     *
     * @return the number of vma schedule shiftings
     * @throws SystemException if a system exception occurred
     */
    public static int getVmaScheduleShiftingsCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaScheduleShiftingsCount();
    }

    /**
     * Updates the vma schedule shifting in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleShifting the vma schedule shifting
     * @return the vma schedule shifting that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleShifting updateVmaScheduleShifting(
            com.fds.nsw.nghiepvu.model.VmaScheduleShifting vmaScheduleShifting)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaScheduleShifting(vmaScheduleShifting);
    }

    /**
     * Updates the vma schedule shifting in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleShifting the vma schedule shifting
     * @param merge whether to merge the vma schedule shifting with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the vma schedule shifting that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleShifting updateVmaScheduleShifting(
            com.fds.nsw.nghiepvu.model.VmaScheduleShifting vmaScheduleShifting,
            boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaScheduleShifting(vmaScheduleShifting, merge);
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
   

    

    public static com.fds.nsw.nghiepvu.model.VmaScheduleShifting delete(
            long id)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaScheduleShiftingException {
        return getService().delete(id);
    }

    public static org.json.JSONObject updateVmaScheduleShifting_VmaItinerary(
            com.fds.nsw.nghiepvu.model.VmaScheduleShifting vmaScheduleShifting,
            com.fds.nsw.nghiepvu.model.VmaItinerary vmaItinerary)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .updateVmaScheduleShifting_VmaItinerary(vmaScheduleShifting,
                        vmaItinerary);
    }

    public static org.json.JSONObject updateVmaScheduleShifting_VmaItinerary(
            java.lang.Integer markedAsArrival, java.lang.Integer markedAsDeparture,
            java.lang.Integer markedAsTransmit, java.lang.Integer markedAsVoyage,
            double crewNumber, double passengerNumber,
            com.fds.nsw.nghiepvu.model.VmaScheduleShifting vmaScheduleShifting,
            com.fds.nsw.nghiepvu.model.VmaItinerary vmaItinerary)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .updateVmaScheduleShifting_VmaItinerary(markedAsArrival,
                        markedAsDeparture, markedAsTransmit, markedAsVoyage, crewNumber,
                        passengerNumber, vmaScheduleShifting, vmaItinerary);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaScheduleShifting> findByItineraryNo(
            java.lang.String itineraryNo)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().findByItineraryNo(itineraryNo);
    }

    public static int countByItineraryNo(java.lang.String itineraryNo)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().countByItineraryNo(itineraryNo);
    }

    public static com.fds.nsw.nghiepvu.model.VmaScheduleShifting findByItineraryNo_CertificateNo(
            java.lang.String itineraryNo, java.lang.String certificateNo)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaScheduleShiftingException {
        return getService()
                .findByItineraryNo_CertificateNo(itineraryNo, certificateNo);
    }

    public static int countByItineraryNo_CertificateNo(
            java.lang.String itineraryNo, java.lang.String certificateNo)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .countByItineraryNo_CertificateNo(itineraryNo, certificateNo);
    }

    public static com.fds.nsw.nghiepvu.model.VmaScheduleShifting findByItineraryNo_VersionNo(
            java.lang.String itineraryNo, java.lang.String versionNo)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaScheduleShiftingException {
        return getService().findByItineraryNo_VersionNo(itineraryNo, versionNo);
    }

    public static int countByItineraryNo_VersionNo(
            java.lang.String itineraryNo, java.lang.String versionNo)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().countByItineraryNo_VersionNo(itineraryNo, versionNo);
    }

    public static org.json.JSONObject findVmaItinerary_VmaScheduleShifting(
            java.lang.String itineraryNo, java.lang.String portofAuthority,
            java.lang.String shiftingDate, java.lang.String requestState,
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findVmaItinerary_VmaScheduleShifting(itineraryNo,
                        portofAuthority, shiftingDate, requestState, start, end);
    }

    public static long countVmaItinerary_VmaScheduleShifting(
            java.lang.String itineraryNo, java.lang.String portofAuthority,
            java.lang.String shiftingDate, java.lang.String requestState)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .countVmaItinerary_VmaScheduleShifting(itineraryNo,
                        portofAuthority, shiftingDate, requestState);
    }

    public static void clearService() {
        _service = null;
    }

    public static VmaScheduleShiftingLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(VmaScheduleShiftingLocalServiceImpl service) {
    }

    private static VmaScheduleShiftingLocalServiceImpl _service;
}
