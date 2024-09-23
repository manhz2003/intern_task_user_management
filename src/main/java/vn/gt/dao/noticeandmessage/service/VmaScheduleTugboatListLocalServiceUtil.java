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
 * The utility for the vma schedule tugboat list local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.VmaScheduleTugboatListLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see VmaScheduleTugboatListLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.VmaScheduleTugboatListLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.VmaScheduleTugboatListLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.VmaScheduleTugboatListLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class VmaScheduleTugboatListLocalServiceUtil {
    public VmaScheduleTugboatListLocalServiceUtil(VmaScheduleTugboatListLocalServiceImpl service) {
        VmaScheduleTugboatListLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.VmaScheduleTugboatListLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the vma schedule tugboat list to the database. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleTugboatList the vma schedule tugboat list
     * @return the vma schedule tugboat list that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleTugboatList addVmaScheduleTugboatList(
            com.fds.nsw.nghiepvu.model.VmaScheduleTugboatList vmaScheduleTugboatList)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addVmaScheduleTugboatList(vmaScheduleTugboatList);
    }

    /**
     * Creates a new vma schedule tugboat list with the primary key. Does not add the vma schedule tugboat list to the database.
     *
     * @param id the primary key for the new vma schedule tugboat list
     * @return the new vma schedule tugboat list
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleTugboatList createVmaScheduleTugboatList(
            long id) {
        return getService().createVmaScheduleTugboatList(id);
    }

    /**
     * Deletes the vma schedule tugboat list with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the vma schedule tugboat list
     * @return the vma schedule tugboat list that was removed
     * @throws PortalException if a vma schedule tugboat list with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleTugboatList deleteVmaScheduleTugboatList(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaScheduleTugboatList(id);
    }

    /**
     * Deletes the vma schedule tugboat list from the database. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleTugboatList the vma schedule tugboat list
     * @return the vma schedule tugboat list that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleTugboatList deleteVmaScheduleTugboatList(
            com.fds.nsw.nghiepvu.model.VmaScheduleTugboatList vmaScheduleTugboatList)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaScheduleTugboatList(vmaScheduleTugboatList);
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
   

    public static com.fds.nsw.nghiepvu.model.VmaScheduleTugboatList fetchVmaScheduleTugboatList(
            long id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchVmaScheduleTugboatList(id);
    }

    /**
     * Returns the vma schedule tugboat list with the primary key.
     *
     * @param id the primary key of the vma schedule tugboat list
     * @return the vma schedule tugboat list
     * @throws PortalException if a vma schedule tugboat list with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleTugboatList getVmaScheduleTugboatList(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaScheduleTugboatList(id);
    }

    

    /**
     * Returns a range of all the vma schedule tugboat lists.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of vma schedule tugboat lists
     * @param end the upper bound of the range of vma schedule tugboat lists (not inclusive)
     * @return the range of vma schedule tugboat lists
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaScheduleTugboatList> getVmaScheduleTugboatLists(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaScheduleTugboatLists(start, end);
    }

    /**
     * Returns the number of vma schedule tugboat lists.
     *
     * @return the number of vma schedule tugboat lists
     * @throws SystemException if a system exception occurred
     */
    public static int getVmaScheduleTugboatListsCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaScheduleTugboatListsCount();
    }

    /**
     * Updates the vma schedule tugboat list in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleTugboatList the vma schedule tugboat list
     * @return the vma schedule tugboat list that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleTugboatList updateVmaScheduleTugboatList(
            com.fds.nsw.nghiepvu.model.VmaScheduleTugboatList vmaScheduleTugboatList)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaScheduleTugboatList(vmaScheduleTugboatList);
    }

    /**
     * Updates the vma schedule tugboat list in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaScheduleTugboatList the vma schedule tugboat list
     * @param merge whether to merge the vma schedule tugboat list with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the vma schedule tugboat list that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaScheduleTugboatList updateVmaScheduleTugboatList(
            com.fds.nsw.nghiepvu.model.VmaScheduleTugboatList vmaScheduleTugboatList,
            boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .updateVmaScheduleTugboatList(vmaScheduleTugboatList, merge);
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
   

    

    public static com.fds.nsw.nghiepvu.model.VmaScheduleTugboatList delete(
            long id)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaScheduleTugboatListException {
        return getService().delete(id);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaScheduleTugboatList> findByItineraryNo(
            java.lang.String itineraryNo)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().findByItineraryNo(itineraryNo);
    }

    public static int countByItineraryNo(java.lang.String itineraryNo)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().countByItineraryNo(itineraryNo);
    }

    public static com.fds.nsw.nghiepvu.model.VmaScheduleTugboatList findByItineraryNo_SequenceNo_ShipCode(
            java.lang.String itineraryNo, int sequenceNo, java.lang.String shipCode)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaScheduleTugboatListException {
        return getService()
                .findByItineraryNo_SequenceNo_ShipCode(itineraryNo,
                        sequenceNo, shipCode);
    }

    public static int countByItineraryNo_SequenceNo_ShipCode(
            java.lang.String itineraryNo, int sequenceNo, java.lang.String shipCode)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .countByItineraryNo_SequenceNo_ShipCode(itineraryNo,
                        sequenceNo, shipCode);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaScheduleTugboatList> findByItineraryNo_SequenceNo(
            java.lang.String itineraryNo, int sequenceNo)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().findByItineraryNo_SequenceNo(itineraryNo, sequenceNo);
    }

    public static int countByItineraryNo_SequenceNo(
            java.lang.String itineraryNo, int sequenceNo)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .countByItineraryNo_SequenceNo(itineraryNo, sequenceNo);
    }

    public static org.json.JSONObject findScheduleTugboatList(
            java.util.LinkedHashMap<java.lang.String, java.lang.Class<?>> colMap,
            java.lang.String searchQuery, java.lang.String countQuery, int start,
            int end)
            throws com.fds.nsw.kernel.exception.SystemException,
            org.json.JSONException {
        return getService()
                .findScheduleTugboatList(colMap, searchQuery, countQuery,
                        start, end);
    }

    public static long countVmaScheduleTugboatList(java.lang.String sql)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().countVmaScheduleTugboatList(sql);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaScheduleTugboatList> getByShipCode_MakePayment(
            java.lang.String shipCode, int makePayment) {
        return getService().getByShipCode_MakePayment(shipCode, makePayment);
    }

    public static void clearService() {
        _service = null;
    }

    public static VmaScheduleTugboatListLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(VmaScheduleTugboatListLocalServiceImpl service) {
    }

    private static VmaScheduleTugboatListLocalServiceImpl _service;
}
