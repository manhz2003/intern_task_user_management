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
 * The utility for the vma transaction slip local service. This utility wraps {@link vn.gt.dao.noticeandmessage.service.impl.VmaTransactionSlipLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see VmaTransactionSlipLocalService
 * @see vn.gt.dao.noticeandmessage.service.base.VmaTransactionSlipLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.impl.VmaTransactionSlipLocalServiceImpl
 * @generated
 */
import com.fds.nsw.nghiepvu.noticeandmessage.service.impl.VmaTransactionSlipLocalServiceImpl;
import org.springframework.stereotype.Component; @Component
public class VmaTransactionSlipLocalServiceUtil {
    public VmaTransactionSlipLocalServiceUtil(VmaTransactionSlipLocalServiceImpl service) {
        VmaTransactionSlipLocalServiceUtil._service = service;
    }
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link vn.gt.dao.noticeandmessage.service.impl.VmaTransactionSlipLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * Adds the vma transaction slip to the database. Also notifies the appropriate model listeners.
     *
     * @param vmaTransactionSlip the vma transaction slip
     * @return the vma transaction slip that was added
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionSlip addVmaTransactionSlip(
            com.fds.nsw.nghiepvu.model.VmaTransactionSlip vmaTransactionSlip)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().addVmaTransactionSlip(vmaTransactionSlip);
    }

    /**
     * Creates a new vma transaction slip with the primary key. Does not add the vma transaction slip to the database.
     *
     * @param id the primary key for the new vma transaction slip
     * @return the new vma transaction slip
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionSlip createVmaTransactionSlip(
            long id) {
        return getService().createVmaTransactionSlip(id);
    }

    /**
     * Deletes the vma transaction slip with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the vma transaction slip
     * @return the vma transaction slip that was removed
     * @throws PortalException if a vma transaction slip with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionSlip deleteVmaTransactionSlip(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaTransactionSlip(id);
    }

    /**
     * Deletes the vma transaction slip from the database. Also notifies the appropriate model listeners.
     *
     * @param vmaTransactionSlip the vma transaction slip
     * @return the vma transaction slip that was removed
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionSlip deleteVmaTransactionSlip(
            com.fds.nsw.nghiepvu.model.VmaTransactionSlip vmaTransactionSlip)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().deleteVmaTransactionSlip(vmaTransactionSlip);
    }

    public static vn.nsw.model.ResponseTransactionList findVmaTransactionSlip_TichHopBienLai(
            java.lang.String searchQuery, int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException,
            org.json.JSONException {
        return getService()
                .findVmaTransactionSlip_TichHopBienLai(searchQuery, start,
                        end);
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
   

    public static com.fds.nsw.nghiepvu.model.VmaTransactionSlip fetchVmaTransactionSlip(
            long id) throws com.fds.nsw.kernel.exception.SystemException {
        return getService().fetchVmaTransactionSlip(id);
    }

    /**
     * Returns the vma transaction slip with the primary key.
     *
     * @param id the primary key of the vma transaction slip
     * @return the vma transaction slip
     * @throws PortalException if a vma transaction slip with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionSlip getVmaTransactionSlip(
            long id)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaTransactionSlip(id);
    }

    

    /**
     * Returns a range of all the vma transaction slips.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of vma transaction slips
     * @param end the upper bound of the range of vma transaction slips (not inclusive)
     * @return the range of vma transaction slips
     * @throws SystemException if a system exception occurred
     */
    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaTransactionSlip> getVmaTransactionSlips(
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaTransactionSlips(start, end);
    }

    /**
     * Returns the number of vma transaction slips.
     *
     * @return the number of vma transaction slips
     * @throws SystemException if a system exception occurred
     */
    public static int getVmaTransactionSlipsCount()
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().getVmaTransactionSlipsCount();
    }

    /**
     * Updates the vma transaction slip in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaTransactionSlip the vma transaction slip
     * @return the vma transaction slip that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionSlip updateVmaTransactionSlip(
            com.fds.nsw.nghiepvu.model.VmaTransactionSlip vmaTransactionSlip)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaTransactionSlip(vmaTransactionSlip);
    }

    /**
     * Updates the vma transaction slip in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param vmaTransactionSlip the vma transaction slip
     * @param merge whether to merge the vma transaction slip with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.fds.nsw.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the vma transaction slip that was updated
     * @throws SystemException if a system exception occurred
     */
    public static com.fds.nsw.nghiepvu.model.VmaTransactionSlip updateVmaTransactionSlip(
            com.fds.nsw.nghiepvu.model.VmaTransactionSlip vmaTransactionSlip,
            boolean merge)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().updateVmaTransactionSlip(vmaTransactionSlip, merge);
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
   

    

    public static org.json.JSONObject addVmaTransactionSlip_VmaTransactionSlipDetail(
            com.fds.nsw.nghiepvu.model.VmaTransactionSlip vmaTransactionSlip,
            com.fds.nsw.nghiepvu.model.VmaTransactionSlipDetails vmaTransactionSlipDetails)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .addVmaTransactionSlip_VmaTransactionSlipDetail(vmaTransactionSlip,
                        vmaTransactionSlipDetails);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaTransactionSlip> findByItineraryNo_PaymentStatus(
            java.lang.String itineraryNo, int paymentStatus)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findByItineraryNo_PaymentStatus(itineraryNo, paymentStatus);
    }

    public static org.json.JSONObject updateVmaTransactionSlip_VmaTransactionSlipDetail(
            com.fds.nsw.nghiepvu.model.VmaTransactionSlip vmaTransactionSlip,
            com.fds.nsw.nghiepvu.model.VmaTransactionSlipDetails vmaTransactionSlipDetails)
            throws com.fds.nsw.kernel.exception.PortalException,
            com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .updateVmaTransactionSlip_VmaTransactionSlipDetail(vmaTransactionSlip,
                        vmaTransactionSlipDetails);
    }

    public static com.fds.nsw.nghiepvu.model.VmaTransactionSlip delete(
            long id)
            throws com.fds.nsw.kernel.exception.SystemException,
            com.fds.nsw.nghiepvu.service.exception.NoSuchVmaTransactionSlipException {
        return getService().delete(id);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaTransactionSlip> findByItineraryNo(
            java.lang.String itineraryNo)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().findByItineraryNo(itineraryNo);
    }

    public static int countByItineraryNo(java.lang.String itineraryNo)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().countByItineraryNo(itineraryNo);
    }

    public static com.fds.nsw.nghiepvu.model.VmaTransactionSlip fetchByitineraryNo_sequenceNo(
            java.lang.String itineraryNo, int sequenceNo) {
        return getService()
                .fetchByitineraryNo_sequenceNo(itineraryNo, sequenceNo);
    }

    public static com.fds.nsw.nghiepvu.model.VmaTransactionSlip fetchByitineraryNo_noticeShipType(
            java.lang.String itineraryNo, int noticeShipType) {
        return getService()
                .fetchByitineraryNo_noticeShipType(itineraryNo,
                        noticeShipType);
    }

    public static com.fds.nsw.nghiepvu.model.VmaTransactionSlip fetchByitineraryNo_documentaryCode(
            java.lang.String itineraryNo, java.lang.String documentaryCode) {
        return getService()
                .fetchByitineraryNo_documentaryCode(itineraryNo,
                        documentaryCode);
    }

    public static com.fds.nsw.nghiepvu.model.VmaTransactionSlip updateVmaTransactionSlip(
            com.fds.nsw.nghiepvu.model.VmaTransactionSlip vmaTransactionSlip,
            java.lang.String maritimeCode, double conversionValue,
            java.lang.Integer makePayment, java.lang.Integer forArrival,
            java.lang.Integer forDeparture, java.lang.String purposeCode,
            java.lang.String shipTypeCode, java.lang.String portHarbourCode,
            java.lang.Double anchorageHour, java.lang.Double seat,
            java.lang.Double voyageNumber, double anchorageDurationTime)
            throws com.fds.nsw.kernel.exception.SystemException,
            org.json.JSONException {
        return getService()
                .updateVmaTransactionSlip(vmaTransactionSlip, maritimeCode,
                        conversionValue, makePayment, forArrival, forDeparture,
                        purposeCode, shipTypeCode, portHarbourCode, anchorageHour, seat,
                        voyageNumber, anchorageDurationTime);
    }

    public static org.json.JSONObject findVmaTransactionSlip(
            java.lang.String searchQuery, java.lang.String countQuery, int start,
            int end)
            throws com.fds.nsw.kernel.exception.SystemException,
            org.json.JSONException {
        return getService()
                .findVmaTransactionSlip(searchQuery, countQuery, start, end);
    }

    public static long countVmaTransactionSlip(java.lang.String sql)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService().countVmaTransactionSlip(sql);
    }

    public static java.lang.String genDocumenttaryCode(
            java.lang.String itineraryNo, java.lang.String debitnoteNumber) {
        return getService().genDocumenttaryCode(itineraryNo, debitnoteNumber);
    }

    public static long countDsTBP(java.lang.String itineraryNo,
                                  java.lang.String tugboatCode, java.lang.String imo,
                                  java.lang.String callSign, java.lang.String registryNumber,
                                  java.lang.String portofAuthority, java.lang.String nameOfShip,
                                  java.lang.String shipAgencyCode, java.lang.String shipAgencyName,
                                  java.lang.String shipOwnerName, java.lang.String documentaryNo,
                                  java.lang.String documentaryIssued, java.lang.String paymentDate,
                                  java.lang.String paymentStatus, java.lang.String currentPaymentStatus,
                                  int flag) throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .countDsTBP(itineraryNo, tugboatCode, imo, callSign,
                        registryNumber, portofAuthority, nameOfShip, shipAgencyCode,
                        shipAgencyName, shipOwnerName, documentaryNo, documentaryIssued,
                        paymentDate, paymentStatus, currentPaymentStatus, flag);
    }


    public static org.json.JSONArray findDsTBP(
            java.lang.String itineraryNo, java.lang.String tugboatCode,
            java.lang.String portofAuthority, java.lang.String imoNumber,
            java.lang.String callSign, java.lang.String registryNumber,
            java.lang.String nameOfShip, java.lang.String shipAgencyCode,
            java.lang.String shipAgencyName, java.lang.String shipOwnerName,
            java.lang.String documentaryNo, java.lang.String documentaryIssued,
            java.lang.String paymentDate, java.lang.String paymentStatus,
            java.lang.String currentPaymentStatus, int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findDsTBP(itineraryNo, tugboatCode, portofAuthority,
                        imoNumber, callSign, registryNumber, nameOfShip, shipAgencyCode,
                        shipAgencyName, shipOwnerName, documentaryNo, documentaryIssued,
                        paymentDate, paymentStatus, currentPaymentStatus, start, end);
    }


    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaTransactionSlip> findDsTBP_ChuyenTuyen(
            java.lang.String itineraryNo, java.lang.String portofAuthority,
            java.lang.String imo, java.lang.String callSign,
            java.lang.String registryNumber, java.lang.String nameOfShip,
            java.lang.String shipAgencyCode, java.lang.String shipAgencyName,
            java.lang.String shipOwnerName, java.lang.String documentaryNo,
            java.lang.String documentaryIssued, java.lang.String paymentDate,
            java.lang.String paymentStatus, java.lang.String currentPaymentStatus,
            int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findDsTBP_ChuyenTuyen(itineraryNo, portofAuthority, imo,
                        callSign, registryNumber, nameOfShip, shipAgencyCode,
                        shipAgencyName, shipOwnerName, documentaryNo, documentaryIssued,
                        paymentDate, paymentStatus, currentPaymentStatus, start, end);
    }


    public static com.fds.nsw.nghiepvu.model.VmaTransactionSlip getByItineraryNo_Debitnoteid(
            java.lang.String itineraryNo, int debitnoteid) {
        return getService()
                .getByItineraryNo_Debitnoteid(itineraryNo, debitnoteid);
    }

    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaTransactionSlip> findByF_Debitnoteid(
            int debitnoteid, int start, int end) {
        return getService().findByF_Debitnoteid(debitnoteid, start, end);
    }

    public static long countByF_Debitnoteid(int debitnoteid) {
        return getService().countByF_Debitnoteid(debitnoteid);
    }

    public static long countDsQLCongNo(java.lang.String nameOfShip,
                                       java.lang.String departmentCode, java.lang.String paymentName,
                                       java.lang.String month, java.lang.String paymentStatus,
                                       int usdTotalAmount, java.lang.String vrCode, int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .countDsQLCongNo(nameOfShip, departmentCode, paymentName,
                        month, paymentStatus, usdTotalAmount, vrCode, start, end);
    }


    public static java.util.List<com.fds.nsw.nghiepvu.model.VmaTransactionSlip> dsQLCongNo(
            java.lang.String nameOfShip, java.lang.String departmentCode,
            java.lang.String paymentName, java.lang.String month,
            java.lang.String paymentStatus, int usdTotalAmount,
            java.lang.String vrCode, java.lang.String shipAgencyName,
            java.lang.String imoNumber, java.lang.String callSign,
            java.lang.String registryNumber, java.lang.String power,
            java.lang.String documentaryIssued, java.lang.String vndTotalAmount,
            int start, int end)

    throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .dsQLCongNo(nameOfShip, departmentCode, paymentName, month,
                        paymentStatus, usdTotalAmount, vrCode, shipAgencyName, imoNumber,
                        callSign, registryNumber, power, documentaryIssued, vndTotalAmount,
                        start, end)
                ;
    }

    public static org.json.JSONObject findThongKeBienLai81(
            int reportCode, java.lang.String paymentName,
            java.lang.String portofAuthority, java.lang.String createDate,
            java.lang.String reportUser, java.lang.String reportPeriod,
            java.lang.String reportYear, java.lang.String reportMonth,
            java.lang.String dateFrom, java.lang.String dateTo, int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findThongKeBienLai81(reportCode, paymentName,
                        portofAuthority, createDate, reportUser, reportPeriod, reportYear,
                        reportMonth, dateFrom, dateTo, start, end);
    }

    public static org.json.JSONObject findThongKeBienLai82(
            java.lang.String portofAuthority, java.lang.String reportUser,
            java.lang.String reportPeriod, java.lang.String reportYear,
            java.lang.String reportMonth, java.lang.String dateFrom,
            java.lang.String dateTo, int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findThongKeBienLai82(portofAuthority, reportUser,
                        reportPeriod, reportYear, reportMonth, dateFrom, dateTo, start, end);
    }

    public static org.json.JSONObject findThongKeBienLai83(
            java.lang.String portofAuthority, java.lang.String reportUser,
            java.lang.String reportPeriod, java.lang.String reportYear,
            java.lang.String reportMonth, java.lang.String dateFrom,
            java.lang.String dateTo, int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findThongKeBienLai83(portofAuthority, reportUser,
                        reportPeriod, reportYear, reportMonth, dateFrom, dateTo, start, end);
    }

    public static org.json.JSONObject findThongKeBienLai84(
            java.lang.String portofAuthority, java.lang.String reportUser,
            java.lang.String reportPeriod, java.lang.String reportYear,
            java.lang.String reportMonth, java.lang.String dateFrom,
            java.lang.String dateTo, int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findThongKeBienLai84(portofAuthority, reportUser,
                        reportPeriod, reportYear, reportMonth, dateFrom, dateTo, start, end);
    }

    public static org.json.JSONObject findThongKeBienLai85(
            java.lang.String portofAuthority, java.lang.String reportUser,
            java.lang.String reportPeriod, java.lang.String reportYear,
            java.lang.String reportMonth, java.lang.String dateFrom,
            java.lang.String dateTo, int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findThongKeBienLai85(portofAuthority, reportUser,
                        reportPeriod, reportYear, reportMonth, dateFrom, dateTo, start, end);
    }

    public static org.json.JSONObject findThongKeBienLai86(
            java.lang.String portofAuthority, java.lang.String reportUser,
            java.lang.String reportPeriod, java.lang.String reportYear,
            java.lang.String reportMonth, java.lang.String dateFrom,
            java.lang.String dateTo, int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findThongKeBienLai86(portofAuthority, reportUser,
                        reportPeriod, reportYear, reportMonth, dateFrom, dateTo, start, end);
    }

    public static org.json.JSONObject findThongKeBienLai87(
            java.lang.String portofAuthority, java.lang.String reportUser,
            java.lang.String reportPeriod, java.lang.String reportYear,
            java.lang.String reportMonth, java.lang.String dateFrom,
            java.lang.String dateTo, int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findThongKeBienLai87(portofAuthority, reportUser,
                        reportPeriod, reportYear, reportMonth, dateFrom, dateTo, start, end);
    }

    public static org.json.JSONObject findThongKeBienLai88(
            java.lang.String portofAuthority, java.lang.String reportUser,
            java.lang.String reportPeriod, java.lang.String reportYear,
            java.lang.String reportMonth, java.lang.String dateFrom,
            java.lang.String dateTo, int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findThongKeBienLai88(portofAuthority, reportUser,
                        reportPeriod, reportYear, reportMonth, dateFrom, dateTo, start, end);
    }

    public static org.json.JSONObject findThongKeBienLai89(
            java.lang.String portofAuthority, java.lang.String reportUser,
            java.lang.String reportPeriod, java.lang.String reportYear,
            java.lang.String reportMonth, java.lang.String dateFrom,
            java.lang.String dateTo, int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findThongKeBienLai89(portofAuthority, reportUser,
                        reportPeriod, reportYear, reportMonth, dateFrom, dateTo, start, end);
    }

    public static org.json.JSONObject findThongKeBienLai90(
            java.lang.String portofAuthority, java.lang.String reportUser,
            java.lang.String reportPeriod, java.lang.String reportYear,
            java.lang.String reportMonth, java.lang.String dateFrom,
            java.lang.String dateTo, int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findThongKeBienLai90(portofAuthority, reportUser,
                        reportPeriod, reportYear, reportMonth, dateFrom, dateTo, start, end);
    }

    public static org.json.JSONObject findThongKeBienLai91(
            java.lang.String portofAuthority, java.lang.String reportUser,
            java.lang.String reportPeriod, java.lang.String reportYear,
            java.lang.String reportMonth, java.lang.String dateFrom,
            java.lang.String dateTo, int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findThongKeBienLai91(portofAuthority, reportUser,
                        reportPeriod, reportYear, reportMonth, dateFrom, dateTo, start, end);
    }

    public static org.json.JSONObject findThongKeBienLai92(
            java.lang.String portofAuthority, java.lang.String reportUser,
            java.lang.String reportPeriod, java.lang.String reportYear,
            java.lang.String reportMonth, java.lang.String dateFrom,
            java.lang.String dateTo, int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findThongKeBienLai92(portofAuthority, reportUser,
                        reportPeriod, reportYear, reportMonth, dateFrom, dateTo, start, end);
    }

    public static org.json.JSONObject findThongKeBienLai93(
            java.lang.String portofAuthority, java.lang.String reportUser,
            java.lang.String reportPeriod, java.lang.String reportYear,
            java.lang.String reportMonth, java.lang.String dateFrom,
            java.lang.String dateTo, int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findThongKeBienLai93(portofAuthority, reportUser,
                        reportPeriod, reportYear, reportMonth, dateFrom, dateTo, start, end);
    }

    public static org.json.JSONObject findThongKeBienLai94(
            java.lang.String portofAuthority, java.lang.String reportUser,
            java.lang.String reportPeriod, java.lang.String reportYear,
            java.lang.String reportMonth, java.lang.String dateFrom,
            java.lang.String dateTo, int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findThongKeBienLai94(portofAuthority, reportUser,
                        reportPeriod, reportYear, reportMonth, dateFrom, dateTo, start, end);
    }

    public static org.json.JSONObject findThongKeBienLai95(
            java.lang.String portofAuthority, java.lang.String reportUser,
            java.lang.String reportPeriod, java.lang.String reportYear,
            java.lang.String reportMonth, java.lang.String dateFrom,
            java.lang.String dateTo, int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findThongKeBienLai95(portofAuthority, reportUser,
                        reportPeriod, reportYear, reportMonth, dateFrom, dateTo, start, end);
    }

    public static org.json.JSONObject findThongKeBienLai96(
            java.lang.String portofAuthority, java.lang.String reportUser,
            java.lang.String reportPeriod, java.lang.String reportYear,
            java.lang.String reportMonth, java.lang.String dateFrom,
            java.lang.String dateTo, int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findThongKeBienLai96(portofAuthority, reportUser,
                        reportPeriod, reportYear, reportMonth, dateFrom, dateTo, start, end);
    }

    public static org.json.JSONObject findThongKeBienLai97(
            java.lang.String portofAuthority, java.lang.String reportUser,
            java.lang.String reportPeriod, java.lang.String reportYear,
            java.lang.String reportMonth, java.lang.String dateFrom,
            java.lang.String dateTo, int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findThongKeBienLai97(portofAuthority, reportUser,
                        reportPeriod, reportYear, reportMonth, dateFrom, dateTo, start, end);
    }

    public static org.json.JSONObject findThongKeBienLai98(
            java.lang.String portofAuthority, java.lang.String reportUser,
            java.lang.String reportPeriod, java.lang.String reportYear,
            java.lang.String reportMonth, java.lang.String dateFrom,
            java.lang.String dateTo, int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findThongKeBienLai98(portofAuthority, reportUser,
                        reportPeriod, reportYear, reportMonth, dateFrom, dateTo, start, end);
    }

    public static org.json.JSONObject findThongKeBienLai99(
            java.lang.String portofAuthority, java.lang.String reportUser,
            java.lang.String reportPeriod, java.lang.String reportYear,
            java.lang.String reportMonth, java.lang.String dateFrom,
            java.lang.String dateTo, int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findThongKeBienLai99(portofAuthority, reportUser,
                        reportPeriod, reportYear, reportMonth, dateFrom, dateTo, start, end);
    }

    public static org.json.JSONObject findThongKeBienLai100(
            java.lang.String portofAuthority, java.lang.String reportUser,
            java.lang.String reportPeriod, java.lang.String reportYear,
            java.lang.String reportMonth, java.lang.String dateFrom,
            java.lang.String dateTo, int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findThongKeBienLai100(portofAuthority, reportUser,
                        reportPeriod, reportYear, reportMonth, dateFrom, dateTo, start, end);
    }

    public static org.json.JSONObject findThongKeBienLai101(
            java.lang.String portofAuthority, java.lang.String reportUser,
            java.lang.String reportPeriod, java.lang.String reportYear,
            java.lang.String reportMonth, java.lang.String dateFrom,
            java.lang.String dateTo, int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findThongKeBienLai101(portofAuthority, reportUser,
                        reportPeriod, reportYear, reportMonth, dateFrom, dateTo, start, end);
    }

    public static org.json.JSONObject findThongKeBienLai102(
            java.lang.String portofAuthority, java.lang.String reportUser,
            java.lang.String reportPeriod, java.lang.String reportYear,
            java.lang.String reportMonth, java.lang.String dateFrom,
            java.lang.String dateTo, int start, int end)
            throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findThongKeBienLai102(portofAuthority, reportUser,
                        reportPeriod, reportYear, reportMonth, dateFrom, dateTo, start, end);
    }

    public static org.json.JSONObject findThongKeBienLai103(
            java.lang.String portofAuthority, java.lang.String createDate,
            java.lang.String reportUser, java.lang.String reportPeriod,
            java.lang.String reportYear, java.lang.String reportMonth,
            java.lang.String dateFrom, java.lang.String dateTo, int start, int end)

    throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findThongKeBienLai103(portofAuthority, reportUser,
                        reportPeriod, reportYear, reportMonth, dateFrom, dateTo, start, end);
    }

    public static org.json.JSONObject findThongKeBienLai104(
            java.lang.String portofAuthority, java.lang.String createDate,
            java.lang.String reportUser, java.lang.String reportPeriod,
            java.lang.String reportYear, java.lang.String reportMonth,
            java.lang.String dateFrom, java.lang.String dateTo, int start, int end)

    throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findThongKeBienLai104(portofAuthority, createDate,
                        reportUser, reportPeriod, reportYear, reportMonth, dateFrom,
                        dateTo, start, end)
                ;
    }

    public static org.json.JSONObject findThongKeBienLai105(
            java.lang.String portofAuthority, java.lang.String createDate,
            java.lang.String reportUser, java.lang.String reportPeriod,
            java.lang.String reportYear, java.lang.String reportMonth,
            java.lang.String dateFrom, java.lang.String dateTo, int start, int end)

    throws com.fds.nsw.kernel.exception.SystemException {
        return getService()
                .findThongKeBienLai105(portofAuthority, createDate,
                        reportUser, reportPeriod, reportYear, reportMonth, dateFrom,
                        dateTo, start, end)
                ;
    }

    public static void clearService() {
        _service = null;
    }

    public static VmaTransactionSlipLocalServiceImpl getService() {
        if (_service == null) {        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(VmaTransactionSlipLocalServiceImpl service) {
    }

    private static VmaTransactionSlipLocalServiceImpl _service;
}
