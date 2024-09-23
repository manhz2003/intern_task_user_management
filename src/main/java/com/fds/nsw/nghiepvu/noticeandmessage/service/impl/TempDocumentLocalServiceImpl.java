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

package com.fds.nsw.nghiepvu.noticeandmessage.service.impl;

import java.io.Serializable;
import vn.gt.portlet.phuongtien.VMAUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.fds.nsw.nghiepvu.baocaothongke.model.FlowChartDataByDate;
import com.fds.nsw.nghiepvu.model.*;
import com.fds.nsw.nghiepvu.noticeandmessage.service.finder.TempDocumentFinderImpl;
import com.fds.nsw.nghiepvu.noticeandmessage.service.persistence.TempDocumentPersistenceImpl;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.kernel.exception.PortalException;
import com.fds.nsw.kernel.exception.SystemException;

import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

/**
 * The implementation of the temp document local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link vn.gt.dao.noticeandmessage.service.TempDocumentLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author win_64
 * @see vn.gt.dao.noticeandmessage.service.base.TempDocumentLocalServiceBaseImpl
 * @see vn.gt.dao.noticeandmessage.service.tempDocumentLocalService
 */
public class TempDocumentLocalServiceImpl {
	@Autowired
	TempDocumentPersistenceImpl persistence;
	@Autowired
	TempDocumentFinderImpl finder;

	

	/*
	 * NOTE FOR DEVELOPERS:
	 * 
	 * Never reference this interface directly. Always use {@link
	 * vn.gt.dao.noticeandmessage.service.tempDocumentLocalService} to access
	 * the temp document local service.
	 */

	public TempDocument getTemDocumentByRequestCode(String requestCode) {
		try {
			List<TempDocument> documents = persistence.findByRequestCode(requestCode);
			if (documents != null && documents.size() > 0) {
				return documents.get(0);
			}
		} catch (Exception e) {
			log.equals(e);
		}
		return null;
	}

	public TempDocument getByDocumentNameAndDocumentYear(long documentName, int documentYear) throws Exception {
		return finder.getByDocumentNameAndDocumentYear(documentName, documentYear);
	}

	public String getCallSignByDocumentNameAndDocumentYear(long documentName, int documentYear) {
		try {
			return finder.getCallSignByDocumentNameAndDocumentYear(documentName, documentYear);
		} catch (Exception e) {
			log.equals(e);
		}
		return null;
	}

	public List<TempDocument> findTemDocumentByDocumentNameAndDocumentYear(long documentName, int documentYear) {
		try {
			return finder.findTemDocumentByDocumentNameAndDocumentYear(documentName, documentYear);
		} catch (Exception es) {

			es.printStackTrace();log.error(es.getMessage());
		}
		return new ArrayList<TempDocument>();
	}

	public List<TempDocument> findTempDocumentArivalByMaritimeCodeAndDateFromAndDateTo(String maritimeCode,
			String dateFrom, String dateTo, String documentTypeCode, String requestState, String documentStatusCode) {
		try {
			return finder.findTempDocumentArivalByMaritimeCodeAndDateFromAndDateTo(maritimeCode, dateFrom,
					dateTo, documentTypeCode, requestState, documentStatusCode);
		} catch (Exception es) {

			es.printStackTrace();log.error(es.getMessage());
		}
		return null;
	}

	public List<TempDocument> findTempDocumentLeaveByMaritimeCodeAndDateFromAndDateTo(String maritimeCode,
			String dateFrom, String dateTo, String documentTypeCode, String requestState, String documentStatusCode) {
		try {
			return finder.findTempDocumentLeaveByMaritimeCodeAndDateFromAndDateTo(maritimeCode, dateFrom,
					dateTo, documentTypeCode, requestState, documentStatusCode);
		} catch (Exception es) {

			es.printStackTrace();log.error(es.getMessage());
		}
		return null;
	}

	public long countTempDocumentForStatisticsReport(String maritimeCode, String dateFrom, String dateTo,
			String documentTypeCode, String requestState, String documentStatusCode) {
		try {
			return finder.countTempDocumentForStatisticsReport(maritimeCode, dateFrom, dateTo,
					documentTypeCode, requestState, documentStatusCode);
		} catch (Exception es) {

			es.printStackTrace();log.error(es.getMessage());
		}
		return 0;
	}

	public long countTempDocumentCompletionForStatisticsReport(String maritimeCode, String dateFrom, String dateTo,
			String documentTypeCode, String requestState, String documentStatusCode) {
		try {
			return finder.countTempDocumentCompletionForStatisticsReport(maritimeCode, dateFrom, dateTo,
					documentTypeCode, requestState, documentStatusCode);
		} catch (Exception es) {

			es.printStackTrace();log.error(es.getMessage());
		}
		return 0;
	}

	public List<FlowChartDataByDate> countTempDocumentForStatisticsByDate(String maritimeCode, String dateFrom,
			String dateTo, String documentTypeCode, String requestState, String documentStatusCode) {
		List<FlowChartDataByDate> result = null;
		try {
			result = finder.countTempDocumentForStatisticsByDate(maritimeCode, dateFrom, dateTo,
					documentTypeCode, requestState, documentStatusCode);
		} catch (Exception es) {

			es.printStackTrace();log.error(es.getMessage());
		}
		return result;
	}

	public long countTempDocumentJoinIssueShiftingOrderForStatisticsReport(String maritimeCode, String dateFrom,
			String dateTo, String documentTypeCode, String requestState, String issRequestState,
			String documentStatusCode) {
		try {
			return finder.countTempDocumentJoinIssueShiftingOrderForStatisticsReport(maritimeCode, dateFrom,
					dateTo, documentTypeCode, requestState, issRequestState, documentStatusCode);
		} catch (Exception es) {

			es.printStackTrace();log.error(es.getMessage());
		}
		return 0;
	}

	public long countTempDocumentJoinIssuePermissionTransitForStatisticsReport(String maritimeCode, String dateFrom,
			String dateTo, String documentTypeCode, String requestState, String issRequestState,
			String documentStatusCode) {
		try {
			return finder.countTempDocumentJoinIssuePermissionTransitForStatisticsReport(maritimeCode,
					dateFrom, dateTo, documentTypeCode, requestState, issRequestState, documentStatusCode);
		} catch (Exception es) {

			es.printStackTrace();log.error(es.getMessage());
		}
		return 0;
	}

	public long countTempDocumentJoinIssuePortClearanceForStatisticsReport(String maritimeCode, String dateFrom,
			String dateTo, String documentTypeCode, String requestState, String issRequestState,
			String documentStatusCode) {
		try {
			return finder.countTempDocumentJoinIssuePortClearanceForStatisticsReport(maritimeCode, dateFrom,
					dateTo, documentTypeCode, requestState, issRequestState, documentStatusCode);
		} catch (Exception es) {

			es.printStackTrace();log.error(es.getMessage());
		}
		return 0;
	}

	public long countTempDocumentJoinIssueAcceptanceForStatisticsReport(String maritimeCode, String dateFrom,
			String dateTo, String documentTypeCode, String requestState, String issRequestState,
			String documentStatusCode) {
		try {
			return finder.countTempDocumentJoinIssueAcceptanceForStatisticsReport(maritimeCode, dateFrom,
					dateTo, documentTypeCode, requestState, issRequestState, documentStatusCode);
		} catch (Exception es) {

			es.printStackTrace();log.error(es.getMessage());
		}
		return 0;
	}

	public long countTempDocumentSignedIssueShiftingOrderForStatisticsReport(String maritimeCode, String dateFrom,
			String dateTo, String documentTypeCode, String requestState, String issRequestState,
			String documentStatusCode) {
		try {
			return finder.countTempDocumentSignedIssueShiftingOrderForStatisticsReport(maritimeCode,
					dateFrom, dateTo, documentTypeCode, requestState, issRequestState, documentStatusCode);
		} catch (Exception es) {

			es.printStackTrace();log.error(es.getMessage());
		}
		return 0;
	}

	public long countTempDocumentSignedIssuePermissionTransitForStatisticsReport(String maritimeCode, String dateFrom,
			String dateTo, String documentTypeCode, String requestState, String issRequestState,
			String documentStatusCode) {
		try {
			return finder.countTempDocumentSignedIssuePermissionTransitForStatisticsReport(maritimeCode,
					dateFrom, dateTo, documentTypeCode, requestState, issRequestState, documentStatusCode);
		} catch (Exception es) {

			es.printStackTrace();log.error(es.getMessage());
		}
		return 0;
	}

	public long countTempDocumentSignedIssuePortClearanceForStatisticsReport(String maritimeCode, String dateFrom,
			String dateTo, String documentTypeCode, String requestState, String issRequestState,
			String documentStatusCode) {
		try {
			return finder.countTempDocumentSignedIssuePortClearanceForStatisticsReport(maritimeCode,
					dateFrom, dateTo, documentTypeCode, requestState, issRequestState, documentStatusCode);
		} catch (Exception es) {

			es.printStackTrace();log.error(es.getMessage());
		}
		return 0;
	}

	public long countTempDocumentSignedIssueAcceptanceForStatisticsReport(String maritimeCode, String dateFrom,
			String dateTo, String documentTypeCode, String requestState, String issRequestState,
			String documentStatusCode) {
		try {
			return finder.countTempDocumentSignedIssueAcceptanceForStatisticsReport(maritimeCode, dateFrom,
					dateTo, documentTypeCode, requestState, issRequestState, documentStatusCode);
		} catch (Exception es) {

			es.printStackTrace();log.error(es.getMessage());
		}
		return 0;
	}

	public TempDocument findTemDocumentByDocumentNameDocumentYear(long documentName, int documentYear) {
		try {
			return finder.findTemDocumentByDocumentNameDocumentYear(documentName, documentYear);
		} catch (Exception es) {
			es.printStackTrace();log.error(es.getMessage());
		}
		return null;
	}

	public List<TempDocument> findTempDocumentByRequestState(String requestState, String documentTypeCode, int start,
			int end) throws SystemException {
		try {
			return finder.findTempDocumentByRequestState(requestState, documentTypeCode, start, end);
		} catch (Exception es) {

			es.printStackTrace();log.error(es.getMessage());
		}
		return new ArrayList<TempDocument>();
	}

	public int countTempDocumentByRequestState(String requestState, String documentTypeCode) {
		try {
			return finder.countTempDocumentByRequestState(requestState, documentTypeCode);
		} catch (Exception es) {

			es.printStackTrace();log.error(es.getMessage());
		}
		return 0;
	}

	public List<TempDocument> findTempDocumentByDocumentStatusCode(String documentStatusCode, String documentTypeCode,
			int start, int end) throws SystemException {
		try {
			return finder.findTempDocumentByDocumentStatusCode(documentStatusCode, documentTypeCode, start,
					end);
		} catch (Exception es) {

			es.printStackTrace();log.error(es.getMessage());
		}
		return new ArrayList<TempDocument>();
	}

	public int countTempDocumentByDocumentStatusCode(String documentStatusCode, String documentTypeCode) {
		try {
			return finder.countTempDocumentByDocumentStatusCode(documentStatusCode, documentTypeCode);
		} catch (Exception es) {

			es.printStackTrace();log.error(es.getMessage());
		}
		return 0;
	}

	public List<TempDocument> findTempDocumentByKeHoach(String requestState, String documentTypeCode,
			String maritimeCode, String shipName, String positionCode, String portRegionCode, String mabankhai,
			String stateCode, String shipDateFromStart, String shipDateFromEnd, String shipDateToStart,
			String shipDateToEnd, String callSign, String imo, String ngayLamThuTucFrom, String ngayLamThuTucTo,
			int start, int end) {

		try {
			return finder.findTempDocumentByKeHoach(requestState, documentTypeCode, maritimeCode, shipName,
					positionCode, portRegionCode, mabankhai, stateCode, shipDateFromStart, shipDateFromEnd,
					shipDateToStart, shipDateToEnd, callSign, imo, ngayLamThuTucFrom, ngayLamThuTucTo, start, end);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public int countTempDocumentByKeHoach(String requestState, String documentTypeCode, String maritimeCode,
			String shipName, String positionCode, String portRegionCode, String mabankhai, String stateCode,
			String shipDateFromStart, String shipDateFromEnd, String shipDateToStart, String shipDateToEnd,
			String callSign, String imo, String ngayLamThuTucFrom, String ngayLamThuTucTo) {

		try {
			return finder.countTempDocumentByKeHoach(requestState, documentTypeCode, maritimeCode, shipName,
					positionCode, portRegionCode, mabankhai, stateCode, shipDateFromStart, shipDateFromEnd,
					shipDateToStart, shipDateToEnd, callSign, imo, ngayLamThuTucFrom, ngayLamThuTucTo);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return 0;
	}

	public List<TempDocument> findTempDocumentByKeHoach(String requestState, String documentTypeCode,
			String maritimeCode, String shipName, String positionCode, String portRegionCode, String mabankhai,
			String stateCode, String shipDateFromStart, String shipDateFromEnd, String shipDateToStart,
			String shipDateToEnd, String callSign, String imo, String ngayLamThuTucFrom, String ngayLamThuTucTo,
			String nameOfShipownersAgents, String portRegionCodeNext, int start, int end) {

		try {
			return finder.findTempDocumentByKeHoach(requestState, documentTypeCode, maritimeCode, shipName,
					positionCode, portRegionCode, mabankhai, stateCode, shipDateFromStart, shipDateFromEnd,
					shipDateToStart, shipDateToEnd, callSign, imo, ngayLamThuTucFrom, ngayLamThuTucTo,
					nameOfShipownersAgents, portRegionCodeNext, start, end);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public int countTempDocumentByKeHoach(String requestState, String documentTypeCode, String maritimeCode,
			String shipName, String positionCode, String portRegionCode, String mabankhai, String stateCode,
			String shipDateFromStart, String shipDateFromEnd, String shipDateToStart, String shipDateToEnd,
			String callSign, String imo, String ngayLamThuTucFrom, String ngayLamThuTucTo,
			String nameOfShipownersAgents, String portRegionCodeNext) {

		try {
			return finder.countTempDocumentByKeHoach(requestState, documentTypeCode, maritimeCode, shipName,
					positionCode, portRegionCode, mabankhai, stateCode, shipDateFromStart, shipDateFromEnd,
					shipDateToStart, shipDateToEnd, callSign, imo, ngayLamThuTucFrom, ngayLamThuTucTo,
					portRegionCodeNext, portRegionCodeNext);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return 0;
	}

	public List<TempDocument> findAll(int start, int end) {
		try {
			return persistence.findAll(start, end);
		} catch (Exception es) {

			es.printStackTrace();log.error(es.getMessage());
		}
		return new ArrayList<TempDocument>();
	}

	public int countAll() {
		try {
			return persistence.countAll();
		} catch (Exception es) {
			
			es.printStackTrace();log.error(es.getMessage());
		}
		return 0;
	}

	public List<TempDocument> findTempDocumentByThuTuc(String documentTypeCode, String documentName,
			String documentYear, String maLoaiHoSo, String shipName, String shipTypeCode, String stateCode,
			String shipCaptain, String callSign, String userCreated, int start, int end) throws SystemException {
		return finder.findTempDocumentByThuTuc(documentTypeCode, documentName, documentYear, maLoaiHoSo,
				shipName, shipTypeCode, stateCode, shipCaptain, callSign, userCreated, start, end);
	}

	public int countTempDocumentByThuTuc(String documentTypeCode, String documentName, String documentYear,
			String maLoaiHoSo, String shipName, String shipTypeCode, String stateCode, String shipCaptain,
			String callSign, String userCreated) throws SystemException {
		return finder.countTempDocumentByThuTuc(documentTypeCode, documentName, documentYear, maLoaiHoSo,
				shipName, shipTypeCode, stateCode, shipCaptain, callSign, userCreated);
	}

	public List<TempDocument> findDanhSachHoSoRoleThuTuc(String documentTypeCode, String maritimeCode, String portCode,
			String lastPortCode, String shipName, String stateCode, String callSign, String imo, String shipPosition,
			String shipDateFromStart, String shipDateFromEnd, String shipDateToStart, String shipDateToEnd,
			String documentStatusCode, String arrivalShipAgency, String nameArrivalShipAgency,
			String departureShipAgency, String nameDepartureShipAgency, String portRegionCode, String ngayLamThuTucFrom,
			String ngayLamThuTucTo, String maBanKhai, int start, int end, boolean isDTND, boolean isDTNDCam)
			throws SystemException {

		log.info("==nameArrivalShipAgency====" + nameArrivalShipAgency + "==arrivalShipAgency====" + arrivalShipAgency);
		log.info("==nameDepartureShipAgency==" + nameDepartureShipAgency + "==departureShipAgency=="
				+ departureShipAgency);

		// if ((nameArrivalShipAgency.trim().length() > 0 &&
		// arrivalShipAgency.trim().length() == 0) ||
		// (nameDepartureShipAgency.trim().length() > 0 &&
		// departureShipAgency.trim().length() == 0)) {
		// return finder.findDanhSachHoSoRoleThuTucLikeShipAgency(
		// documentTypeCode, maritimeCode, portCode, lastPortCode,
		// shipName, stateCode, callSign, imo, shipPosition,
		// shipDateFromStart, shipDateFromEnd, shipDateToStart, shipDateToEnd,
		// documentStatusCode, arrivalShipAgency, nameArrivalShipAgency,
		// departureShipAgency, nameDepartureShipAgency,
		// portRegionCode, ngayLamThuTucFrom, ngayLamThuTucTo, maBanKhai, start,
		// end);
		// } else {
		return finder.findDanhSachHoSoRoleThuTuc(documentTypeCode, maritimeCode, portCode, lastPortCode,
				shipName, stateCode, callSign, imo, shipPosition, shipDateFromStart, shipDateFromEnd, shipDateToStart,
				shipDateToEnd, documentStatusCode, arrivalShipAgency, nameArrivalShipAgency, departureShipAgency,
				nameDepartureShipAgency, portRegionCode, ngayLamThuTucFrom, ngayLamThuTucTo, maBanKhai, start, end,
				isDTND, isDTNDCam);
		// }
	}

	public int countDanhSachHoSoRoleThuTuc(String documentTypeCode, String maritimeCode, String portCode,
			String lastPortCode, String shipName, String stateCode, String callSign, String imo, String shipPosition,
			String shipDateFromStart, String shipDateFromEnd, String shipDateToStart, String shipDateToEnd,
			String documentStatusCode, String arrivalShipAgency, String nameArrivalShipAgency,
			String departureShipAgency, String nameDepartureShipAgency, String portRegionCode, String ngayLamThuTucFrom,
			String ngayLamThuTucTo, String maBanKhai, boolean isDTND, boolean isDTNDCam) throws SystemException {

		log.info("==nameArrivalShipAgency====" + nameArrivalShipAgency + "==arrivalShipAgency====" + arrivalShipAgency);
		log.info("==nameDepartureShipAgency==" + nameDepartureShipAgency + "==departureShipAgency=="
				+ departureShipAgency);

		// if ((nameArrivalShipAgency.trim().length() > 0 &&
		// arrivalShipAgency.trim().length() == 0) ||
		// (nameDepartureShipAgency.trim().length() > 0 &&
		// departureShipAgency.trim().length() == 0)) {
		//
		// return
		// finder.countDanhSachHoSoRoleThuTucLikeShipAgency(documentTypeCode,
		// maritimeCode, portCode, lastPortCode, shipName, stateCode, callSign,
		// imo, shipPosition, shipDateFromStart, shipDateFromEnd,
		// shipDateToStart, shipDateToEnd, documentStatusCode,
		// arrivalShipAgency, nameArrivalShipAgency, departureShipAgency,
		// nameDepartureShipAgency,
		// portRegionCode, ngayLamThuTucFrom, ngayLamThuTucTo, maBanKhai);
		//
		// } else {
		return finder.countDanhSachHoSoRoleThuTuc(documentTypeCode, maritimeCode, portCode, lastPortCode,
				shipName, stateCode, callSign, imo, shipPosition, shipDateFromStart, shipDateFromEnd, shipDateToStart,
				shipDateToEnd, documentStatusCode, arrivalShipAgency, nameArrivalShipAgency, departureShipAgency,
				nameDepartureShipAgency, portRegionCode, ngayLamThuTucFrom, ngayLamThuTucTo, maBanKhai, isDTND,
				isDTNDCam);
		// }

	}

	public List<TempDocument> findDanhSachHoSoRoleThuTuc(String documentTypeCode, String maritimeCode, String portCode,
			String lastPortCode, String shipName, String stateCode, String callSign, String imo, String shipPosition,
			String shipDateFromStart, String shipDateFromEnd, String shipDateToStart, String shipDateToEnd,
			String documentStatusCode, String arrivalShipAgency, String nameArrivalShipAgency,
			String departureShipAgency, String nameDepartureShipAgency, String portRegionCode, String ngayLamThuTucFrom,
			String ngayLamThuTucTo, String maBanKhai, String nameOfShipownersAgents, String maritimeCodeNext, int start,
			int end, boolean isDTND, boolean isDTNDCam) throws SystemException {

		return finder.findDanhSachHoSoRoleThuTuc(documentTypeCode, maritimeCode, portCode, lastPortCode,
				shipName, stateCode, callSign, imo, shipPosition, shipDateFromStart, shipDateFromEnd, shipDateToStart,
				shipDateToEnd, documentStatusCode, arrivalShipAgency, nameArrivalShipAgency, departureShipAgency,
				nameDepartureShipAgency, portRegionCode, ngayLamThuTucFrom, ngayLamThuTucTo, maBanKhai,
				nameOfShipownersAgents, maritimeCodeNext, start, end, isDTND, isDTNDCam);
	}

	public int countDanhSachHoSoRoleThuTuc(String documentTypeCode, String maritimeCode, String portCode,
			String lastPortCode, String shipName, String stateCode, String callSign, String imo, String shipPosition,
			String shipDateFromStart, String shipDateFromEnd, String shipDateToStart, String shipDateToEnd,
			String documentStatusCode, String arrivalShipAgency, String nameArrivalShipAgency,
			String departureShipAgency, String nameDepartureShipAgency, String portRegionCode, String ngayLamThuTucFrom,
			String ngayLamThuTucTo, String maBanKhai, String nameOfShipownersAgents, String maritimeCodeNext,
			boolean isDTND, boolean isDTNDCam) throws SystemException {

		return finder.countDanhSachHoSoRoleThuTuc(documentTypeCode, maritimeCode, portCode, lastPortCode,
				shipName, stateCode, callSign, imo, shipPosition, shipDateFromStart, shipDateFromEnd, shipDateToStart,
				shipDateToEnd, documentStatusCode, arrivalShipAgency, nameArrivalShipAgency, departureShipAgency,
				nameDepartureShipAgency, portRegionCode, ngayLamThuTucFrom, ngayLamThuTucTo, maBanKhai,
				nameOfShipownersAgents, maritimeCodeNext, isDTND, isDTNDCam);

	}

	public List<TempDocument> findTempDocumentInfo(String documentStatusCode, String maritimeCode, String portCode,
			String shipName, String callSign, String shipDateFrom, String shipDateTo, int start, int end) {
		try {
			return finder.findTempDocumentInfo(documentStatusCode, maritimeCode, portCode, shipName,
					callSign, shipDateFrom, shipDateTo, start, end);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	public int countTempDocumentInfo(String documentStatusCode, String maritimeCode, String portCode, String shipName,
			String callSign, String shipDateFrom, String shipDateTo) throws SystemException {
		try {
			return finder.countTempDocumentInfo(documentStatusCode, maritimeCode, portCode, shipName,
					callSign, shipDateFrom, shipDateTo);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return 0;
	}

	public List<TempDocument> findKeHoachYCBS() throws SystemException {
		return finder.findKeHoachYCBS();
	}

	public TempDocument fetchByF_B_N_Y_TYPE(long documentName, int documentYear, String documentTypeCode)
			throws SystemException {
		return persistence.fetchByF_B_N_Y_TYPE(documentName, documentYear, documentTypeCode);
	}

	public List<TempDocument> findByTempDocumentByShipNameSMSAndDocumentTypeCode(String shipName, String callSign,
			String shipTypeCode, int documentYear, String documentTypeCode) {
		try {
			return persistence.findByTempDocumentByShipNameSMSAndDocumentTypeCode(shipName, callSign,
					shipTypeCode, documentYear, documentTypeCode);
		} catch (Exception e) {
			log.error("error:",e);
		}
		return new ArrayList<TempDocument>();
	}

	public List<TempDocument> findDanhSachHoSoVanThuChoDongDau(String documentTypeCode, String maritimeCode,
			String portCode, String lastPortCode, String shipName, String stateCode, String callSign, String imo,
			String shipPosition, String shipDateFromStart, String shipDateFromEnd, String shipDateToStart,
			String shipDateToEnd, String documentStatusCode, String arrivalShipAgency, String nameArrivalShipAgency,
			String departureShipAgency, String nameDepartureShipAgency, String portRegionCode, String ngayLamThuTucFrom,
			String ngayLamThuTucTo, String maBanKhai, int start, int end) throws SystemException {

		return finder.findDanhSachHoSoVanThuChoDongDau(documentTypeCode, maritimeCode, portCode,
				lastPortCode, shipName, stateCode, callSign, imo, shipPosition, shipDateFromStart, shipDateFromEnd,
				shipDateToStart, shipDateToEnd, documentStatusCode, arrivalShipAgency, nameArrivalShipAgency,
				departureShipAgency, nameDepartureShipAgency, portRegionCode, ngayLamThuTucFrom, ngayLamThuTucTo,
				maBanKhai, start, end);

	}

	public int countDanhSachHoSoVanThuChoDongDau(String documentTypeCode, String maritimeCode, String portCode,
			String lastPortCode, String shipName, String stateCode, String callSign, String imo, String shipPosition,
			String shipDateFromStart, String shipDateFromEnd, String shipDateToStart, String shipDateToEnd,
			String documentStatusCode, String arrivalShipAgency, String nameArrivalShipAgency,
			String departureShipAgency, String nameDepartureShipAgency, String portRegionCode, String ngayLamThuTucFrom,
			String ngayLamThuTucTo, String maBanKhai) throws SystemException {

		return finder.countDanhSachHoSoVanThuChoDongDau(documentTypeCode, maritimeCode, portCode,
				lastPortCode, shipName, stateCode, callSign, imo, shipPosition, shipDateFromStart, shipDateFromEnd,
				shipDateToStart, shipDateToEnd, documentStatusCode, arrivalShipAgency, nameArrivalShipAgency,
				departureShipAgency, nameDepartureShipAgency, portRegionCode, ngayLamThuTucFrom, ngayLamThuTucTo,
				maBanKhai);

	}

	public List<TempDocument> findDanhSachHoSoVanThuChoDongDau(String documentTypeCode, String maritimeCode,
			String portCode, String lastPortCode, String shipName, String stateCode, String callSign, String imo,
			String shipPosition, String shipDateFromStart, String shipDateFromEnd, String shipDateToStart,
			String shipDateToEnd, String documentStatusCode, String arrivalShipAgency, String nameArrivalShipAgency,
			String departureShipAgency, String nameDepartureShipAgency, String portRegionCode, String ngayLamThuTucFrom,
			String ngayLamThuTucTo, String maBanKhai, String nameOfShipownersAgents, String maritimeCodeNext, int start,
			int end) throws SystemException {

		return finder.findDanhSachHoSoVanThuChoDongDau(documentTypeCode, maritimeCode, portCode,
				lastPortCode, shipName, stateCode, callSign, imo, shipPosition, shipDateFromStart, shipDateFromEnd,
				shipDateToStart, shipDateToEnd, documentStatusCode, arrivalShipAgency, nameArrivalShipAgency,
				departureShipAgency, nameDepartureShipAgency, portRegionCode, ngayLamThuTucFrom, ngayLamThuTucTo,
				maBanKhai, nameOfShipownersAgents, maritimeCodeNext, start, end);

	}

	public int countDanhSachHoSoVanThuChoDongDau(String documentTypeCode, String maritimeCode, String portCode,
			String lastPortCode, String shipName, String stateCode, String callSign, String imo, String shipPosition,
			String shipDateFromStart, String shipDateFromEnd, String shipDateToStart, String shipDateToEnd,
			String documentStatusCode, String arrivalShipAgency, String nameArrivalShipAgency,
			String departureShipAgency, String nameDepartureShipAgency, String portRegionCode, String ngayLamThuTucFrom,
			String ngayLamThuTucTo, String maBanKhai, String nameOfShipownersAgents, String maritimeCodeNext)
			throws SystemException {

		return finder.countDanhSachHoSoVanThuChoDongDau(documentTypeCode, maritimeCode, portCode,
				lastPortCode, shipName, stateCode, callSign, imo, shipPosition, shipDateFromStart, shipDateFromEnd,
				shipDateToStart, shipDateToEnd, documentStatusCode, arrivalShipAgency, nameArrivalShipAgency,
				departureShipAgency, nameDepartureShipAgency, portRegionCode, ngayLamThuTucFrom, ngayLamThuTucTo,
				maBanKhai, nameOfShipownersAgents, maritimeCodeNext);

	}

	public List<TempDocument> findDanhSachHoSoLanhDaoChoKySo(String documentTypeCode, String maritimeCode,
			String portCode, String lastPortCode, String shipName, String stateCode, String callSign, String imo,
			String shipPosition, String shipDateFromStart, String shipDateFromEnd, String shipDateToStart,
			String shipDateToEnd, String documentStatusCode, String arrivalShipAgency, String nameArrivalShipAgency,
			String departureShipAgency, String nameDepartureShipAgency, String portRegionCode, String ngayLamThuTucFrom,
			String ngayLamThuTucTo, String maBanKhai, String representative, int start, int end)
			throws SystemException {

		return finder.findDanhSachHoSoLanhDaoChoKySo(documentTypeCode, maritimeCode, portCode, lastPortCode,
				shipName, stateCode, callSign, imo, shipPosition, shipDateFromStart, shipDateFromEnd, shipDateToStart,
				shipDateToEnd, documentStatusCode, arrivalShipAgency, nameArrivalShipAgency, departureShipAgency,
				nameDepartureShipAgency, portRegionCode, ngayLamThuTucFrom, ngayLamThuTucTo, maBanKhai, representative,
				start, end);

	}

	public int countDanhSachHoSoLanhDaoChoKySo(String documentTypeCode, String maritimeCode, String portCode,
			String lastPortCode, String shipName, String stateCode, String callSign, String imo, String shipPosition,
			String shipDateFromStart, String shipDateFromEnd, String shipDateToStart, String shipDateToEnd,
			String documentStatusCode, String arrivalShipAgency, String nameArrivalShipAgency,
			String departureShipAgency, String nameDepartureShipAgency, String portRegionCode, String ngayLamThuTucFrom,
			String ngayLamThuTucTo, String maBanKhai, String representative) throws SystemException {

		return finder.countDanhSachHoSoLanhDaoChoKySo(documentTypeCode, maritimeCode, portCode,
				lastPortCode, shipName, stateCode, callSign, imo, shipPosition, shipDateFromStart, shipDateFromEnd,
				shipDateToStart, shipDateToEnd, documentStatusCode, arrivalShipAgency, nameArrivalShipAgency,
				departureShipAgency, nameDepartureShipAgency, portRegionCode, ngayLamThuTucFrom, ngayLamThuTucTo,
				maBanKhai, representative);

	}

	public List<TempDocument> findDanhSachHoSoLanhDaoChoKySo(String documentTypeCode, String maritimeCode,
			String portCode, String lastPortCode, String shipName, String stateCode, String callSign, String imo,
			String shipPosition, String shipDateFromStart, String shipDateFromEnd, String shipDateToStart,
			String shipDateToEnd, String documentStatusCode, String arrivalShipAgency, String nameArrivalShipAgency,
			String departureShipAgency, String nameDepartureShipAgency, String portRegionCode, String ngayLamThuTucFrom,
			String ngayLamThuTucTo, String maBanKhai, String representative, String nameOfShipownersAgents,
															 String maritimeCodeNext, String daKy, int start, int end) throws SystemException {
		if (Validator.isNotNull(daKy) && daKy.length() > 0) {
			return finder.findDanhSachHoSoLanhDaoDaKySo(
					documentTypeCode, maritimeCode, portCode, lastPortCode,
					shipName, stateCode, callSign, imo, shipPosition,
					shipDateFromStart, shipDateFromEnd, shipDateToStart,
					shipDateToEnd, documentStatusCode, arrivalShipAgency,
					nameArrivalShipAgency, departureShipAgency,
					nameDepartureShipAgency, portRegionCode, ngayLamThuTucFrom,
					ngayLamThuTucTo, maBanKhai, representative,
					nameOfShipownersAgents, maritimeCodeNext, start, end);

		}


		return finder.findDanhSachHoSoLanhDaoChoKySo(
				documentTypeCode, maritimeCode, portCode, lastPortCode,
				shipName, stateCode, callSign, imo, shipPosition,
				shipDateFromStart, shipDateFromEnd, shipDateToStart,
				shipDateToEnd, documentStatusCode, arrivalShipAgency,
				nameArrivalShipAgency, departureShipAgency,
				nameDepartureShipAgency, portRegionCode, ngayLamThuTucFrom,
				ngayLamThuTucTo, maBanKhai, representative,
				nameOfShipownersAgents, maritimeCodeNext, start, end);


	}

	public int countDanhSachHoSoLanhDaoChoKySo(String documentTypeCode, String maritimeCode, String portCode,
			String lastPortCode, String shipName, String stateCode, String callSign, String imo, String shipPosition,
			String shipDateFromStart, String shipDateFromEnd, String shipDateToStart, String shipDateToEnd,
			String documentStatusCode, String arrivalShipAgency, String nameArrivalShipAgency,
			String departureShipAgency, String nameDepartureShipAgency, String portRegionCode, String ngayLamThuTucFrom,
			String ngayLamThuTucTo, String maBanKhai, String representative, String nameOfShipownersAgents,
			String maritimeCodeNext, String daKy) throws SystemException {

		if (Validator.isNotNull(daKy) && daKy.length() > 0) {
			return finder.countDanhSachHoSoLanhDaoDaKySo(
					documentTypeCode, maritimeCode, portCode, lastPortCode,
					shipName, stateCode, callSign, imo, shipPosition,
					shipDateFromStart, shipDateFromEnd, shipDateToStart,
					shipDateToEnd, documentStatusCode, arrivalShipAgency,
					nameArrivalShipAgency, departureShipAgency,
					nameDepartureShipAgency, portRegionCode, ngayLamThuTucFrom,
					ngayLamThuTucTo, maBanKhai, representative,
					nameOfShipownersAgents, maritimeCodeNext);
		}
		return finder.countDanhSachHoSoLanhDaoChoKySo(
				documentTypeCode, maritimeCode, portCode, lastPortCode,
				shipName, stateCode, callSign, imo, shipPosition,
				shipDateFromStart, shipDateFromEnd, shipDateToStart,
				shipDateToEnd, documentStatusCode, arrivalShipAgency,
				nameArrivalShipAgency, departureShipAgency,
				nameDepartureShipAgency, portRegionCode, ngayLamThuTucFrom,
				ngayLamThuTucTo, maBanKhai, representative,
				nameOfShipownersAgents, maritimeCodeNext);


	}

	public long countByF_documentTypeCode_maritimeCode(String documentTypeCode, String maritimeCode) {
		long result = 0;
		try {
			result = persistence.countByF_documentTypeCode_maritimeCode(documentTypeCode, maritimeCode);
		} catch (SystemException e) {
			result = 0;
		}
		return result;
	}

	public long countByF_documentTypeCode_maritimeCodeFinder(String documentTypeCode, String maritimeCode,
			String shipName, String documentName, String callSign) {
		long result = 0;
		try {
			result = finder.counterHoSoAll(documentTypeCode, maritimeCode, shipName, documentName,
					callSign);
		} catch (SystemException e) {
			result = 0;
		}
		return result;
	}

	public List<TempDebitnote> findTempDebitNote(String maritimeCode, String positionCode, String portRegionCode,
			String mabankhai, String stateCode, String imo, String markasdelted, String lastPortCode, int start,
			int end, String shipName, String callSign) throws SystemException {

		return finder.findTempDebitNote(maritimeCode, positionCode, portRegionCode, mabankhai, stateCode,
				imo, markasdelted, lastPortCode, start, end, shipName, callSign);

	}

	public long countTempDebitNote(String maritimeCode, String positionCode, String portRegionCode, String mabankhai,
			String stateCode, String imo, String markasdelted, String lastPortCode, int start, int end, String shipName,
			String callSign) throws SystemException {

		return finder.findTempDebitNote(maritimeCode, positionCode,
				portRegionCode, mabankhai, stateCode, imo, markasdelted,
				lastPortCode, -1, -1, shipName, callSign).size();


	}

	public boolean isValid_ShipAgencyCode_IMO_MaritimeCode(long documentName, int documentYear, String imo,
			String maritimeCode, String ShipAgencyCode) throws SystemException {
		return finder.isValid_ShipAgencyCode_IMO_MaritimeCode(documentName, documentYear, imo, maritimeCode,
				ShipAgencyCode);
	}

	public TempDocument findTempDocumentLeftByIMOandCallSign(
			String maritimeCode, Date DateOfArrival, String callSign,
			String imo, String documentTypeCode, String requestState,
			String documentStatusCode) {
		try {
			return finder.findTempDocumentLeftByIMOandCallSign(
					maritimeCode, DateOfArrival, callSign, imo,
					documentTypeCode, requestState, documentStatusCode);
		} catch (Exception e) {

			log.error(e.getMessage());
		}
		return null;
	}

	public JSONObject findTempDocument(String searchQuery, String countQuery,
									   int start, int end) throws JSONException, SystemException {

		List<TempDocument> tempDocuments = finder
				.findTempDocument(TempDocument.class, "TempDocument",
						searchQuery, start, end);

		long count = finder.countTempDocument(countQuery);

		JSONObject result = JSONFactoryUtil.createJSONObject();

		JSONArray data = JSONFactoryUtil.createJSONArray();

		if (tempDocuments != null) {

			for (TempDocument tempDocument : tempDocuments) {
				JSONObject object = VMAUtils.object2Json(tempDocument,
						TempDocument.class);
				data.put(object);
			}

		}

		result.put("total", count);

		result.put("data", data);

		return result;
	}

	public List<TempDebitnote> findTempDebitNoteTauBien_PTTND(
			String maritimeCode, String positionCode, String portRegionCode,
			String mabankhai, String stateCode, String imo,
			String markasdelted, String lastPortCode, int start, int end,
			String shipName, String callSign, int flag) throws SystemException {
		return finder.findTempDebitNoteTauBien_PTTND(
				maritimeCode, positionCode, portRegionCode, mabankhai,
				stateCode, imo, markasdelted, lastPortCode, start, end,
				shipName, callSign, flag);
	}

	public long countTempDebitNoteTauBien_PTTND(String maritimeCode,
												String positionCode, String portRegionCode, String mabankhai,
												String stateCode, String imo, String markasdelted,
												String lastPortCode, String shipName, String callSign, int flag)
			throws SystemException {
		return finder.findTempDebitNoteTauBien_PTTND(
				maritimeCode, positionCode, portRegionCode, mabankhai,
				stateCode, imo, markasdelted, lastPortCode, -1, -1,
				shipName, callSign, flag).size();
	}


	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link
	 * vn.gt.dao.noticeandmessage.service.tempDocumentLocalService} to access
	 * the temp document local service.
	 */

	/**
	 * Adds the temp document to the database. Also notifies the appropriate model
	 * listeners.
	 *
	 * @param tempDocument the temp document
	 * @return the temp document that was added
	 * @throws SystemException if a system exception occurred
	 */
	public TempDocument addTempDocument(TempDocument tempDocument) throws SystemException {
		

		tempDocument = persistence.updateImpl(tempDocument, false);

		return tempDocument;
	}

	/**
	 * Creates a new temp document with the primary key. Does not add the temp
	 * document to the database.
	 *
	 * @param id the primary key for the new temp document
	 * @return the new temp document
	 */
	public TempDocument createTempDocument(long id) {
		return persistence.create(id);
	}

	/**
	 * Deletes the temp document with the primary key from the database. Also
	 * notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the temp document
	 * @throws PortalException if a temp document with the primary key could not be
	 *                         found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteTempDocument(long id) throws PortalException, SystemException {
		TempDocument tempDocument = persistence.remove(id);

	}

	/**
	 * Deletes the temp document from the database. Also notifies the appropriate
	 * model listeners.
	 *
	 * @param tempDocument the temp document
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteTempDocument(TempDocument tempDocument) throws SystemException {
		persistence.remove(tempDocument);

	}

	public TempDocument fetchTempDocument(long id) throws SystemException {
		return persistence.fetchByPrimaryKey(id);
	}

	/**
	 * Returns the temp document with the primary key.
	 *
	 * @param id the primary key of the temp document
	 * @return the temp document
	 * @throws PortalException if a temp document with the primary key could not be
	 *                         found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDocument getTempDocument(long id) throws PortalException, SystemException {
		return persistence.findByPrimaryKey(id);
	}

	public TempDocument getPersistedModel(Serializable primaryKeyObj) throws PortalException, SystemException {
		return persistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the temp documents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code>
	 * instances. <code>start</code> and <code>end</code> are not primary keys, they
	 * are indexes in the result set. Thus, <code>0</code> refers to the first
	 * result in the set. Setting both <code>start</code> and <code>end</code> to
	 *  will return the
	 * full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of temp documents
	 * @param end   the upper bound of the range of temp documents (not inclusive)
	 * @return the range of temp documents
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDocument> getTempDocuments(int start, int end) throws SystemException {
		return persistence.findAll(start, end);
	}

	/**
	 * Returns the number of temp documents.
	 *
	 * @return the number of temp documents
	 * @throws SystemException if a system exception occurred
	 */
	public int getTempDocumentsCount() throws SystemException {
		return persistence.countAll();
	}

	/**
	 * Updates the temp document in the database or adds it if it does not yet
	 * exist. Also notifies the appropriate model listeners.
	 *
	 * @param tempDocument the temp document
	 * @return the temp document that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public TempDocument updateTempDocument(TempDocument tempDocument) throws SystemException {
		return updateTempDocument(tempDocument, true);
	}

	/**
	 * Updates the temp document in the database or adds it if it does not yet
	 * exist. Also notifies the appropriate model listeners.
	 *
	 * @param tempDocument the temp document
	 * @param merge        whether to merge the temp document with the current
	 *                     session. See
	 *                     
	 *                     for an explanation.
	 * @return the temp document that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public TempDocument updateTempDocument(TempDocument tempDocument, boolean merge) throws SystemException {
		

		tempDocument = persistence.updateImpl(tempDocument, merge);

		return tempDocument;
	}
}