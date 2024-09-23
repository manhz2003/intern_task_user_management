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
 * The utility for the vma schedule shipyard local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.VmaScheduleShipyardLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see VmaScheduleShipyardLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.VmaScheduleShipyardLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.VmaScheduleShipyardLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.VmaScheduleShipyardLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class VmaScheduleShipyardLocalServiceUtil {
    public VmaScheduleShipyardLocalServiceUtil(VmaScheduleShipyardLocalServiceImpl service) {
        VmaScheduleShipyardLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.VmaScheduleShipyardLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the vma schedule shipyard to the database. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleShipyard the vma schedule shipyard
     * @return the vma schedule shipyard that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleShipyard addVmaScheduleShipyard(
            com.fds.nsw.nghiepvu.model.VmaScheduleShipyard vmaScheduleShipyard)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addVmaScheduleShipyard(vmaScheduleShipyard);
    }

    /**
     * Creates a new vma schedule shipyard with the primary key. Does not add the vma schedule shipyard to the database.
     *
     * @param id the primary key for the new vma schedule shipyard
     * @return the new vma schedule shipyard
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleShipyard createVmaScheduleShipyard(
            long id) {
        return getService().createVmaScheduleShipyard(id);
    }

    /**
     * Deletes the vma schedule shipyard with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the vma schedule shipyard
     * @return the vma schedule shipyard that was removed
     * @throws PortalException if a vma schedule shipyard with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleShipyard deleteVmaScheduleShipyard(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaScheduleShipyard(id);
    }

    /**
     * Deletes the vma schedule shipyard from the database. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleShipyard the vma schedule shipyard
     * @return the vma schedule shipyard that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleShipyard deleteVmaScheduleShipyard(
            com.fds.nsw.nghiepvu.model.VmaScheduleShipyard vmaScheduleShipyard)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaScheduleShipyard(vmaScheduleShipyard);
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
   

    public static com.fds.nsw.nghiepvu.model.VmaScheduleShipyard fetchVmaScheduleShipyard(
            long id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchVmaScheduleShipyard(id);
    }

    /**
     * Returns the vma schedule shipyard with the primary key.
     *
     * @param id the primary key of the vma schedule shipyard
     * @return the vma schedule shipyard
     * @throws PortalException if a vma schedule shipyard with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleShipyard getVmaScheduleShipyard(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaScheduleShipyard(id);
    }

    

    /**
     * Returns a range of all the vma schedule shipyards.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of vma schedule shipyards
     * @param end the upper bound of the range of vma schedule shipyards (not inclusive)
     * @return the range of vma schedule shipyards
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaScheduleShipyard> getVmaScheduleShipyards(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaScheduleShipyards(start, end);
    }

    /**
     * Returns the number of vma schedule shipyards.
     *
     * @return the number of vma schedule shipyards
     * @throws SystemException if a system exception occurred
     */
    public static int getVmaScheduleShipyardsCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaScheduleShipyardsCount();
    }

    /**
     * Updates the vma schedule shipyard in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleShipyard the vma schedule shipyard
     * @return the vma schedule shipyard that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleShipyard updateVmaScheduleShipyard(
            com.fds.nsw.nghiepvu.model.VmaScheduleShipyard vmaScheduleShipyard)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaScheduleShipyard(vmaScheduleShipyard);
    }

    /**
     * Updates the vma schedule shipyard in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleShipyard the vma schedule shipyard
     * @param merge whether to merge the vma schedule shipyard with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the vma schedule shipyard that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleShipyard updateVmaScheduleShipyard(
            com.fds.nsw.nghiepvu.model.VmaScheduleShipyard vmaScheduleShipyard,
            boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaScheduleShipyard(vmaScheduleShipyard, merge);
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
   

    

    public static com.fds.nsw.nghiepvu.model.VmaScheduleShipyard delete(
            long id)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaScheduleShipyardException {
        return getService().delete(id);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaScheduleShipyard> findByItineraryNo(
            java.lang.String itineraryNo)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().findByItineraryNo(itineraryNo);
    }

    public static int countByItineraryNo(java.lang.String itineraryNo)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().countByItineraryNo(itineraryNo);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaScheduleShipyard> findByItineraryNo_NoticeShipType(
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

    public static org.json.JSONObject findVmaScheduleShipyard(
            java.lang.String itineraryNo, java.lang.String portofAuthority,
            java.lang.String nameOfShip, java.lang.Long documentName,
            java.lang.Integer documentYear, java.lang.Integer noticeShipType,
            java.lang.String shipYardCode, java.lang.String shipYardCompanyName,
            java.lang.String shipYardOfficialNo, java.lang.String repairingFrom,
            java.lang.String repairingTo, java.lang.String repairingPlace,
            java.lang.String repairingReason, java.lang.Integer repaired,
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findVmaScheduleShipyard(itineraryNo, portofAuthority,
                        nameOfShip, documentName, documentYear, noticeShipType,
                        shipYardCode, shipYardCompanyName, shipYardOfficialNo,
                        repairingFrom, repairingTo, repairingPlace, repairingReason,
                        repaired, start, end);
    }

    public static org.json.JSONObject findVmaScheduleShipyard(
            java.lang.String searchQuery, java.lang.String countQuery, int start,
            int end)
            throws com.fds.nsw.kernel.exception.SystemException,
            org.json.JSONException {
        return getService()
                .findVmaScheduleShipyard(searchQuery, countQuery, start, end);
    }

    public static long countVmaScheduleShipyard(java.lang.String itineraryNo,
                                                java.lang.String portofAuthority, java.lang.String nameOfShip,
                                                java.lang.Long documentName, java.lang.Integer documentYear,
                                                java.lang.Integer noticeShipType, java.lang.String shipYardCode,
                                                java.lang.String shipYardCompanyName,
                                                java.lang.String shipYardOfficialNo, java.lang.String repairingFrom,
                                                java.lang.String repairingTo, java.lang.String repairingPlace,
                                                java.lang.String repairingReason, java.lang.Integer repaired)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .countVmaScheduleShipyard(itineraryNo, portofAuthority,
                        nameOfShip, documentName, documentYear, noticeShipType,
                        shipYardCode, shipYardCompanyName, shipYardOfficialNo,
                        repairingFrom, repairingTo, repairingPlace, repairingReason,
                        repaired);
    }

    public static long countVmaScheduleShipyard(java.lang.String sql)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().countVmaScheduleShipyard(sql);
    }

    public static void clearService() {
        _service = null;
    }

    public static VmaScheduleShipyardLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(VmaScheduleShipyardLocalServiceImpl service) {
    }

    private static VmaScheduleShipyardLocalServiceImpl _service;
}
