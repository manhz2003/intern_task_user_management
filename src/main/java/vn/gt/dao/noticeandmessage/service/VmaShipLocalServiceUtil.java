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
 * The utility for the vma ship local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.VmaShipLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see VmaShipLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.VmaShipLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.VmaShipLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.VmaShipLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class VmaShipLocalServiceUtil {
    public VmaShipLocalServiceUtil(VmaShipLocalServiceImpl service) {
        VmaShipLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.VmaShipLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the vma ship to the database. Also notifies the appropriate model listeners.
     *
     * @param vmaShip the vma ship
     * @return the vma ship that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaShip addVmaShip(
            com.fds.nsw.nghiepvu.model.VmaShip vmaShip)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addVmaShip(vmaShip);
    }

    /**
     * Creates a new vma ship with the primary key. Does not add the vma ship to the database.
     *
     * @param id the primary key for the new vma ship
     * @return the new vma ship
     */
    public static com.fds.nsw.nghiepvu.model.VmaShip createVmaShip(
            long id) {
        return getService().createVmaShip(id);
    }

    /**
     * Deletes the vma ship with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the vma ship
     * @return the vma ship that was removed
     * @throws PortalException if a vma ship with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaShip deleteVmaShip(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaShip(id);
    }

    /**
     * Deletes the vma ship from the database. Also notifies the appropriate model listeners.
     *
     * @param vmaShip the vma ship
     * @return the vma ship that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaShip deleteVmaShip(
            com.fds.nsw.nghiepvu.model.VmaShip vmaShip)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaShip(vmaShip);
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
   

    public static com.fds.nsw.nghiepvu.model.VmaShip fetchVmaShip(long id)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchVmaShip(id);
    }

    /**
     * Returns the vma ship with the primary key.
     *
     * @param id the primary key of the vma ship
     * @return the vma ship
     * @throws PortalException if a vma ship with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaShip getVmaShip(long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaShip(id);
    }

    

    /**
     * Returns a range of all the vma ships.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of vma ships
     * @param end the upper bound of the range of vma ships (not inclusive)
     * @return the range of vma ships
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaShip> getVmaShips(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaShips(start, end);
    }

    /**
     * Returns the number of vma ships.
     *
     * @return the number of vma ships
     * @throws SystemException if a system exception occurred
     */
    public static int getVmaShipsCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaShipsCount();
    }

    /**
     * Updates the vma ship in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaShip the vma ship
     * @return the vma ship that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaShip updateVmaShip(
            com.fds.nsw.nghiepvu.model.VmaShip vmaShip)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaShip(vmaShip);
    }

    /**
     * Updates the vma ship in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaShip the vma ship
     * @param merge whether to merge the vma ship with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the vma ship that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaShip updateVmaShip(
            com.fds.nsw.nghiepvu.model.VmaShip vmaShip, boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaShip(vmaShip, merge);
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
   

    

    public static com.fds.nsw.nghiepvu.model.VmaShip updayeVmaShip(
            com.fds.nsw.nghiepvu.model.VmaShip vmaShip)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updayeVmaShip(vmaShip);
    }

    public static com.fds.nsw.nghiepvu.model.VmaShip updateVmaShipDeleteStatus(
            long id, int value)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaShipException {
        return getService().updateVmaShipDeleteStatus(id, value);
    }

    public static org.json.JSONObject findVmaShip(
            java.lang.String shipName, java.lang.String shipBoat,
            java.lang.String shipAgencyCode, java.lang.String flagStateOfShip,
            java.lang.String callSign, java.lang.String shipOwnerCode,
            java.lang.String shipOperatorCode, java.lang.String shipTypeMT,
            java.lang.String shipTypeCode, double gt, double dwt, double nt,
            double loa, double breadth, double shownDraftxA,
            double clearanceHeight, double shownDraftxF, double maxDraft,
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException,
            org.json.JSONException {
        return getService()
                .findVmaShip(shipName, shipBoat, shipAgencyCode,
                        flagStateOfShip, callSign, shipOwnerCode, shipOperatorCode,
                        shipTypeMT, shipTypeCode, gt, dwt, nt, loa, breadth, shownDraftxA,
                        clearanceHeight, shownDraftxF, maxDraft, start, end);
    }

    public static org.json.JSONObject findVmaShip(
            java.lang.String searchQuery, java.lang.String countQuery, int start,
            int end)
            throws com.fds.nsw.kernel.exception.SystemException,
            org.json.JSONException {
        return getService().findVmaShip(searchQuery, countQuery, start, end);
    }

    public static org.json.JSONObject findVmaShip(
            java.lang.String shipName, java.lang.String shipBoat,
            java.lang.String shipTypeMT, java.lang.String shipTypeCode,
            java.lang.String tugBoatName, double gt, double dwt, double loa,
            java.lang.String masterCertificateNo, double breadth,
            java.lang.String chiefCertificateNo, java.lang.String vrCode,
            double power, java.lang.String shipOwnerCode,
            java.lang.String nameOfMaster, java.lang.String masterCertificateClass,
            java.lang.String chiefOfEngineer,
            java.lang.String chiefEngineerCertificateClass, int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException,
            org.json.JSONException {
        return getService()
                .findVmaShip(shipName, shipBoat, shipTypeMT, shipTypeCode,
                        tugBoatName, gt, dwt, loa, masterCertificateNo, breadth,
                        chiefCertificateNo, vrCode, power, shipOwnerCode, nameOfMaster,
                        masterCertificateClass, chiefOfEngineer,
                        chiefEngineerCertificateClass, start, end);
    }

    public static long countVmaShip(java.lang.String shipName,
                                    java.lang.String shipBoat, java.lang.String shipAgencyCode,
                                    java.lang.String flagStateOfShip, java.lang.String callSign,
                                    java.lang.String shipOwnerCode, java.lang.String shipOperatorCode,
                                    java.lang.String shipTypeMT, java.lang.String shipTypeCode, double gt,
                                    double dwt, double nt, double loa, double breadth, double shownDraftxA,
                                    double clearanceHeight, double shownDraftxF, double maxDraft)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .countVmaShip(shipName, shipBoat, shipAgencyCode,
                        flagStateOfShip, callSign, shipOwnerCode, shipOperatorCode,
                        shipTypeMT, shipTypeCode, gt, dwt, nt, loa, breadth, shownDraftxA,
                        clearanceHeight, shownDraftxF, maxDraft);
    }

    public static long countVmaShip(java.lang.String shipName,
                                    java.lang.String shipBoat, java.lang.String shipTypeMT,
                                    java.lang.String shipTypeCode, java.lang.String tugBoatName, double gt,
                                    double dwt, double loa, java.lang.String masterCertificateNo,
                                    double breadth, java.lang.String chiefCertificateNo,
                                    java.lang.String vrCode, double power, java.lang.String shipOwnerCode,
                                    java.lang.String nameOfMaster, java.lang.String masterCertificateClass,
                                    java.lang.String chiefOfEngineer,
                                    java.lang.String chiefEngineerCertificateClass)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .countVmaShip(shipName, shipBoat, shipTypeMT, shipTypeCode,
                        tugBoatName, gt, dwt, loa, masterCertificateNo, breadth,
                        chiefCertificateNo, vrCode, power, shipOwnerCode, nameOfMaster,
                        masterCertificateClass, chiefOfEngineer,
                        chiefEngineerCertificateClass);
    }

    public static long countVmaShip(java.lang.String sql)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().countVmaShip(sql);
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

    public static com.fds.nsw.nghiepvu.model.VmaShip findByCallSign(
            java.lang.String callSign)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaShipException {
        return getService().findByCallSign(callSign);
    }

    public static com.fds.nsw.nghiepvu.model.VmaShip fetchByCallSign(
            java.lang.String callSign)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchByCallSign(callSign);
    }

    public static com.fds.nsw.nghiepvu.model.VmaShip findByIMONumber(
            java.lang.String imoNumber)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaShipException {
        return getService().findByIMONumber(imoNumber);
    }

    public static com.fds.nsw.nghiepvu.model.VmaShip fetchByIMONumber(
            java.lang.String imoNumber)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchByIMONumber(imoNumber);
    }

    public static com.fds.nsw.nghiepvu.model.VmaShip findByIMONumber_CallSign(
            java.lang.String imoNumber, java.lang.String callSign)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaShipException {
        return getService().findByIMONumber_CallSign(imoNumber, callSign);
    }

    public static com.fds.nsw.nghiepvu.model.VmaShip fetchByIMONumber_CallSign(
            java.lang.String imoNumber, java.lang.String callSign)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchByIMONumber_CallSign(imoNumber, callSign);
    }

    public static com.fds.nsw.nghiepvu.model.VmaShip findByRegistryNumber(
            java.lang.String registryNumber)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaShipException {
        return getService().findByRegistryNumber(registryNumber);
    }

    public static com.fds.nsw.nghiepvu.model.VmaShip fetchByRegistryNumber(
            java.lang.String registryNumber)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaShipException {
        return getService().fetchByRegistryNumber(registryNumber);
    }

    public static com.fds.nsw.nghiepvu.model.VmaShip findByVRCode(
            java.lang.String vrCode)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaShipException {
        return getService().findByVRCode(vrCode);
    }

    public static com.fds.nsw.nghiepvu.model.VmaShip fetchByVRCode(
            java.lang.String vrCode)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchByVRCode(vrCode);
    }

    public static com.fds.nsw.nghiepvu.model.VmaShip findByVRCode_RegistryNumber(
            java.lang.String imoNumber, java.lang.String registryNumber)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaShipException {
        return getService()
                .findByVRCode_RegistryNumber(imoNumber, registryNumber);
    }

    public static com.fds.nsw.nghiepvu.model.VmaShip fetchByVRCode_RegistryNumber(
            java.lang.String imoNumber, java.lang.String registryNumber)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .fetchByVRCode_RegistryNumber(imoNumber, registryNumber);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaShip> findAll(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().findAll(start, end);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaShip> findAll(
            int start, int end,
            com.fds.nsw.kernel.util.OrderByComparator orderByComparator)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().findAll(start, end, orderByComparator);
    }

    public static com.fds.nsw.nghiepvu.model.VmaShip fetchByIMONumber_CallSign_RegistryNumber(
            java.lang.String imoNumber, java.lang.String callSign,
            java.lang.String registryNumber) {
        return getService()
                .fetchByIMONumber_CallSign_RegistryNumber(imoNumber,
                        callSign, registryNumber);
    }

    public static void clearService() {
        _service = null;
    }

    public static VmaShipLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(VmaShipLocalServiceImpl service) {
    }

    private static VmaShipLocalServiceImpl _service;
}
