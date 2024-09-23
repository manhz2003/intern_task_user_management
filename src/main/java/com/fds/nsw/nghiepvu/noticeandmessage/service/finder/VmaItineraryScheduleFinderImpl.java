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

package com.fds.nsw.nghiepvu.noticeandmessage.service.finder;
import com.fds.flex.common.ultility.GetterUtil;
import com.fds.flex.common.utility.string.StringPool;

import com.fds.nsw.nghiepvu.model.*;

import com.fds.nsw.nghiepvu.modelImpl.TempDebitNoteModelImpl;
import jakarta.persistence.EntityManager;
import vn.gt.dao.danhmuc.service.*;
import vn.gt.dao.noticeandmessage.service.VmaScheduleMergingLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaScheduleTugboatListLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaTransactionSlipLocalServiceUtil;
import vn.gt.dao.result.service.TempDebitNoteLocalServiceUtil;
import vn.gt.portlet.danhmuc.DanhMucUtils;
import vn.gt.portlet.phuongtien.VMAUtils;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.kernel.dao.orm.BasePersistence;
import com.fds.nsw.kernel.dao.orm.QueryUtil;
import com.fds.nsw.kernel.dao.orm.jpa.QueryBuilder;
import com.fds.nsw.kernel.dao.orm.jpa.QueryFactory;
import com.fds.nsw.kernel.exception.SystemException;
import com.fds.nsw.nghiepvu.util.ConvertUtil;
import com.fds.nsw.nghiepvu.util.FormatData;

import lombok.extern.slf4j.Slf4j;
import vn.gt.tichhop.report.ReportsBusinessUtils;

@Service
@Slf4j
public class VmaItineraryScheduleFinderImpl extends BasePersistence {
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaItinerarySchedule> queryFactory;

//	EntityManager em = queryFactory.getEntityManager();

	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<Object[]> queryFactory2;

	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<Long> queryFactory3;

	public JSONArray findVmaItinerary_VmaItinerarySchedule(String itineraryNo,
			String maritimeCode, String shipPosition, String markedAsArrival,
			String markedAsDeparture, String noticeShipType, String shipBoat,
			String portRegionCode, String portHarbourCode, String portWharfCode, 
			String cargoType, String vrCode, String routeLevelCode, String chanelCodeList, 
			String timeOfArrival, String timeOfDeparture, String nameOfShip, 
			String flagStateOfShip, String callSign, String registryNumber,
			double gt, double dwt, double loa, double maxDraft, double shownDraftxF, double shownDraftxA,
			int start, int end) throws SystemException {

		

		JSONArray data = JSONFactoryUtil.createJSONArray();

		try {
			

			StringBuilder query = new StringBuilder();

			query.append("SELECT ");
			query.append("A1.NameOfShip,");
			query.append("A1.FlagStateOfShip,");
			query.append("A1.IMONumber,");
			query.append("A1.CallSign,");
			query.append("A1.VRCode,");
			query.append("A1.RegistryNumber,");

			query.append("A2.GT,");
			query.append("A2.NT,");
			query.append("A2.DWT,");
			query.append("A2.LOA,");
			query.append("A2.Breadth,");
			query.append("A2.ClearanceHeight,");
			query.append("A2.Power,");
			query.append("A2.MaxDraft,");
			query.append("A2.ShownDraftxF,");
			query.append("A2.ShownDraftxA,");
			query.append("A2.UnitLOA,");
			query.append("A2.UnitBreadth,");
			query.append("A2.UnitClearanceHeight,");
			query.append("A2.UnitShownDraftxF,");
			query.append("A2.UnitShownDraftxA,");
			query.append("A2.UnitGRT,");
			query.append("A2.UnitNT,");
			query.append("A2.UnitDWT,");
			query.append("A2.UnitPower,");
			query.append("A2.UnitMaxDraft,");
			query.append("A2.TimeOfDeparture,");
			query.append("A2.ShipAgencyName,");
			query.append("A2.ShipAgencyCode,");
			query.append("A2.ChanelName,");
			query.append("A2.ChanelCodeList,");
			query.append("A2.ID,");
			query.append("A2.ItineraryNo,");
			query.append("A2.NoticeShipType,");
			query.append("A2.TimeOfArrival,");
			query.append("A2.RouteLevelCode,");
			query.append("A2.ArrivalPortCode,");
			query.append("A2.InitFrom,");
			query.append("A2.PortRegionCode,");
			query.append("A2.PortHarbourCode,");
			query.append("A2.PortWharfCode,");
			
			query.append("vv.Tau_lai,");
			query.append("vvv.Hoa_tieu,");
			query.append("vvv.PilotCompanyCode,");
			query.append("vv.Tau_lai_viet_tat,");
			query.append("vvv.Hoa_tieu_viet_tat,");
			query.append("A2.initFrom AS shortInitFrom, ");
			query.append("A2.ChanelName AS chanelName");



			query.append(" FROM vma_itinerary AS A1 INNER JOIN vma_itinerary_schedule AS A2 ON A1.ItineraryNo = A2.ItineraryNo");

			query.append(" LEFT JOIN (select v1.ItineraryNo, v1.itineraryScheduleId , GROUP_CONCAT('<br>',v2.ShipName) as Tau_lai, "
					+ " Case ISNULL(GROUP_CONCAT('<br>', Case ISNULL(v3.TugboatShortName)  when 0 then (Case LENGTH(TRIM(v3.TugboatShortName))  when 0 then v2.ShipName else v3.TugboatShortName end) else v2.ShipName end )) " 
					+ " when 0 then GROUP_CONCAT('<br>', Case ISNULL(v3.TugboatShortName)  when 0 then (Case LENGTH(TRIM(v3.TugboatShortName))  when 0 then v2.ShipName else v3.TugboatShortName end) else v2.ShipName end ) "
					+ " else GROUP_CONCAT('<br>', v2.ShipName) end AS Tau_lai_viet_tat " 
					+ " from vma_schedule_tugboat as v1 LEFT JOIN vma_schedule_tugboat_list as v2 on v1.ItineraryNo = v2.ItineraryNo and v1.sequenceNo = v2.sequenceNo "
					+ " LEFT JOIN dm_vma_tugboat AS v3 ON v3.ShipCode = v2.ShipCode and v3.ShipName like v2.ShipName where NoticeShipType IN ("
					+ noticeShipType
					+ ") GROUP BY v1.ItineraryNo, v1.itineraryScheduleId) as vv on A2.ItineraryNo = vv.ItineraryNo and (vv.itineraryScheduleId = A2.ID and A2.NoticeShipType in (1,2)) and vv.Tau_lai is not null");
			query.append(" LEFT JOIN (select v1.ItineraryNo, v1.itineraryScheduleId , GROUP_CONCAT('<br>',v2.PilotName) as Hoa_tieu, "
					+ " Case ISNULL(GROUP_CONCAT('<br>', Case ISNULL(v3.PilotShortName)  when 0 then (Case LENGTH(TRIM(v3.PilotShortName))  when 0 then v2.PilotName else v3.PilotShortName end) else v2.PilotName end )) " 
					+ " when 0 then GROUP_CONCAT('<br>', Case ISNULL(v3.PilotShortName)  when 0 then (Case LENGTH(TRIM(v3.PilotShortName))  when 0 then v2.PilotName else v3.PilotShortName end) else v2.PilotName end )" 
					+ " else GROUP_CONCAT('<br>', v2.PilotName) end AS Hoa_tieu_viet_tat, "		
					+ " v2.PilotCompanyCode from (Select * from vma_schedule_pilot_list ORDER BY itineraryScheduleId, PilotCategoryCode ASC) AS v2 LEFT JOIN vma_schedule_pilot as v1 on v1.ItineraryNo = v2.ItineraryNo and v1.sequenceNo = v2.sequenceNo "
					+ " LEFT JOIN dm_vma_pilot AS v3 ON v3.PilotCode = v2.PilotCode where NoticeShipType IN ("
					+ noticeShipType
					+ ") GROUP BY v1.ItineraryNo, v1.itineraryScheduleId) as vvv on A2.ItineraryNo = vvv.ItineraryNo and (vvv.itineraryScheduleId = A2.ID and A2.NoticeShipType in (1,2)) and vvv.Hoa_tieu is not null");
			
			query.append(" WHERE 1=1 ");

			query.append(generateCondition3(itineraryNo,
					maritimeCode, shipPosition, markedAsArrival,
					markedAsDeparture, noticeShipType, shipBoat,
					portRegionCode, portHarbourCode, portWharfCode, cargoType, vrCode, routeLevelCode, chanelCodeList, 
					timeOfArrival, timeOfDeparture, nameOfShip, flagStateOfShip, callSign, registryNumber,
					gt, dwt, loa, maxDraft, shownDraftxF, shownDraftxA));
			

			query.append(" ORDER BY  A2.TimeOfArrival DESC, A2.TimeOfDeparture DESC ");
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Object.class).build();
			Iterator<Object[]> itr =queryFactory2.getResultList(builder).iterator();

			if (itr.hasNext()) {
				while (itr.hasNext()) {
					Object[] objects = itr.next();

					nameOfShip = String.valueOf(objects[0]);
					flagStateOfShip = String.valueOf(objects[1]);
					String imoNumber = String.valueOf(objects[2]);
					callSign = String.valueOf(objects[3]);
					vrCode = String.valueOf(objects[4]);
					registryNumber = String.valueOf(objects[5]);

					gt = GetterUtil.getDouble(objects[6]);
					double nt = GetterUtil.getDouble(objects[7]);
					dwt = GetterUtil.getDouble(objects[8]);
					loa = GetterUtil.getDouble(objects[9]);
					double breadth = GetterUtil.getDouble(objects[10]);
					double clearanceHeight = GetterUtil.getDouble(objects[11]);
					double power = GetterUtil.getDouble(objects[12]);
					maxDraft = GetterUtil.getDouble(objects[13]);
					shownDraftxF = GetterUtil.getDouble(objects[14]);
					shownDraftxA = GetterUtil.getDouble(objects[15]);
					String unitLoa = String.valueOf(objects[16]);
					String unitBreadth = String.valueOf(objects[17]);
					String unitClearanceHeight = String.valueOf(objects[18]);
					String unitShownDraftxF = String.valueOf(objects[19]);
					String unitShownDraftxA = String.valueOf(objects[20]);
					String unitGrt = String.valueOf(objects[21]);
					String unitNt = String.valueOf(objects[22]);
					String unitDwt = String.valueOf(objects[23]);
					String unitPower = String.valueOf(objects[24]);
					String unitMaxDraft = String.valueOf(objects[25]);

					Date datetimeOfDeparture = (Date) objects[26];

					timeOfDeparture = StringPool.BLANK;

					if (datetimeOfDeparture != null) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(datetimeOfDeparture);

						int day = calendar.get(Calendar.DATE);

						int month = calendar.get(Calendar.MONTH) + 1;

						int year = calendar.get(Calendar.YEAR);

						timeOfDeparture = (day < 10 ? "0" + day : day)
								+ StringPool.SLASH
								+ (month < 10 ? "0" + month : month)
								+ StringPool.SLASH + year;
					}

					String shipAgencyShortName = StringPool.BLANK;
					String shipAgencyName = String.valueOf(objects[27]);
					String shipAgencyCode = String.valueOf(objects[28]);
					String chanelName = String.valueOf(objects[29]);
					chanelCodeList = String.valueOf(objects[30]);

					long vmaItineraryScheduleId = GetterUtil
							.getLong(objects[31]);

					itineraryNo = String.valueOf(objects[32]);
					int _noticeShipType = GetterUtil.getInteger(objects[33]);

					Date datetimeOfArrival = (Date) objects[34];

					timeOfArrival = StringPool.BLANK;

					if (datetimeOfArrival != null) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(datetimeOfArrival);

						int day = calendar.get(Calendar.DATE);

						int month = calendar.get(Calendar.MONTH) + 1;

						int year = calendar.get(Calendar.YEAR);

						timeOfArrival = (day < 10 ? "0" + day : day)
								+ StringPool.SLASH
								+ (month < 10 ? "0" + month : month)
								+ StringPool.SLASH + year;
					}
					routeLevelCode = String.valueOf(objects[35]);
					String arrivalPortCode = String.valueOf(objects[36]);
					String InitFrom = String.valueOf(objects[37]);
					portRegionCode = String.valueOf(objects[38]);
					portHarbourCode = String.valueOf(objects[39]);
					portWharfCode = String.valueOf(objects[40]);

					String tugboatList = String.valueOf(objects[41]);
					String pilotList = String.valueOf(objects[42]);
					String pilotCompanyCode = String.valueOf(objects[43]);
					String shortTugboatList = String.valueOf(objects[44]);
					String shortPilotList = String.valueOf(objects[45]);
					String shortInitFrom = String.valueOf(objects[46]);

					//String portName = StringPool.BLANK;
					String PortRegionNameVN = StringPool.BLANK;
					String PortHarbourNameVN = StringPool.BLANK;
					String PortWharfNameVN = StringPool.BLANK;
					String anchoringPortWharfName = StringPool.BLANK;
					/*try {
						DmPort dmPort = DmPortLocalServiceUtil.getByPortCode(arrivalPortCode);
						if (Validator.isNotNull(dmPort)) {
							portName = dmPort.getPortName();
							anchoringPortWharfName += portName + ", ";
						}
					} catch (Exception e) {
						log.info("arrivalPortCode***********");
					} */
					try {
						DmPortRegion dmPortRegion = DmPortRegionLocalServiceUtil
								.getByPortRegionCode(portRegionCode);
						if (Validator.isNotNull(dmPortRegion)) {
							PortRegionNameVN = dmPortRegion.getPortRegionNameVN();
							anchoringPortWharfName += PortRegionNameVN + ", ";
						}
					} catch (Exception e) {
						log.info("portRegionCode***********");
					}
					try {
						DmPortHarbour dmPortHarbour = DmPortHarbourLocalServiceUtil.getByPortHarbourCode(portHarbourCode);
						if (Validator.isNotNull(dmPortHarbour)) {
							PortHarbourNameVN = dmPortHarbour.getPortHarbourNameVN();
							anchoringPortWharfName += PortHarbourNameVN + ", ";
						}

					} catch (Exception e) {
						log.info("portHarbourCode***********");
					}
					try {
						DmPortWharf dmPortWharf = DmPortWharfLocalServiceUtil
								.getByPortWharfCode(portWharfCode);
						if (dmPortWharf != null && Validator.isNotNull(dmPortWharf.getPortWharfNameVN())) {
							PortWharfNameVN = dmPortWharf.getPortWharfNameVN();
							anchoringPortWharfName += PortWharfNameVN;
						}
					} catch (Exception e) {
						log.info("portWharfCode***********");
					}

					JSONObject object = JSONFactoryUtil.createJSONObject();

					object.put("nameOfShip", nameOfShip);
					object.put("flagStateOfShip", flagStateOfShip);
					try {
						object.put(
								"flagStateOfShipName",
								DmStateLocalServiceUtil.getByStateCode(
										flagStateOfShip).getStateName());
					} catch (Exception e) {
						// nothing to do
					}
					object.put("imoNumber", imoNumber);
					object.put("callSign", callSign);
					object.put("vrCode", vrCode);
					object.put("registryNumber", registryNumber);

					object.put("gt", gt);
					object.put("nt", nt);
					object.put("dwt", dwt);
					object.put("loa", loa);
					object.put("breadth", breadth);
					object.put("clearanceHeight", clearanceHeight);
					object.put("power", power);
					object.put("maxDraft", maxDraft);
					object.put("shownDraftxF", shownDraftxF);
					object.put("shownDraftxA", shownDraftxA);
					object.put("unitLoa", unitLoa);
					object.put("unitBreadth", unitBreadth);
					object.put("unitClearanceHeight", unitClearanceHeight);
					object.put("unitShownDraftxF", unitShownDraftxF);
					object.put("unitShownDraftxA", unitShownDraftxA);
					object.put("unitGrt", unitGrt);
					object.put("unitNt", unitNt);
					object.put("unitDwt", unitDwt);
					object.put("unitPower", unitPower);
					object.put("unitMaxDraft", unitMaxDraft);
					if (datetimeOfDeparture != null) {
						object.put("timeOfDeparture", FormatData.formatDateShort3
							.format(datetimeOfDeparture));
					} else {
						object.put("timeOfDeparture", "");
					}
					if (datetimeOfArrival != null) {
						object.put("timeOfArrival", FormatData.formatDateShort3
							.format(datetimeOfArrival));
					} else {
						object.put("timeOfArrival", "");
					}
					object.put("shipAgencyName", shipAgencyName);
					object.put("shipAgencyCode", shipAgencyCode);
					object.put("chanelName", chanelName);
					object.put("chanelCodeListName", chanelName);
					object.put("chanelCodeList", chanelCodeList);
					object.put("vmaItineraryScheduleId", vmaItineraryScheduleId);

					object.put("itineraryNo", itineraryNo);
					object.put("noticeShipType", _noticeShipType);
					object.put("routeLevelCode", routeLevelCode);
					object.put("arrivalPortCode", arrivalPortCode);
					object.put("initFrom", InitFrom);
					object.put("shortInitFrom", Validator.isNotNull(shortInitFrom) ? shortInitFrom : "");
					object.put("portRegionCode", portRegionCode);
					object.put("portHarbourCode", portHarbourCode);
					object.put("portWharfCode", portWharfCode);
					object.put("anchoringPortWharfName", anchoringPortWharfName);

					object.put("tugboatList", Validator.isNotNull(tugboatList) ? tugboatList : "");
					object.put("shortTugboatList", Validator.isNotNull(shortTugboatList) ? shortTugboatList : "");

					String companyShortName = StringPool.BLANK;
					String pilotCompanyName = StringPool.BLANK;
					if (Validator.isNotNull(pilotCompanyCode)) {
						DmVmaPilotCompany dmVmaPilotCompany = DmVmaPilotCompanyLocalServiceUtil
								.fetchByPilotCompanyCode(pilotCompanyCode);
						if (dmVmaPilotCompany != null) {
							if (Validator.isNotNull(dmVmaPilotCompany.getCompanyShortName())) {
								companyShortName = " -"
										+ dmVmaPilotCompany.getCompanyShortName();
							}
							pilotCompanyName = " -"
									+ dmVmaPilotCompany.getPilotCompanyName();
						}
						if (Validator.isNotNull(companyShortName)) {
							pilotList += companyShortName;
							shortPilotList += companyShortName;
						} else if (Validator.isNotNull(pilotCompanyName)) {
							pilotList += pilotCompanyName;
							shortPilotList += pilotCompanyName;
						}
					}
					if (Validator.isNotNull(shipAgencyCode)) {
						DmShipAgency objshipAgency =  DmShipAgencyLocalServiceUtil.fetchByShipAgencyCode(shipAgencyCode);
						if ((Validator.isNotNull(objshipAgency) && Validator.isNotNull(objshipAgency.getShipAgencyShortName()))) {
							shipAgencyShortName = objshipAgency.getShipAgencyShortName();
						}
					}

					object.put("pilotList", Validator.isNotNull(pilotList) ? pilotList : "");
					object.put("shortPilotList", Validator.isNotNull(shortPilotList) ? shortPilotList : "");
					object.put("tugBoatNames", Validator.isNotNull(tugboatList) ? tugboatList : "");
					object.put("pilotNames", Validator.isNotNull(pilotList) ? pilotList : "");

					data.put(object);

				}
			}

		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
		}

		return data;
	}

	public JSONArray findVmaItinerary_VmaItinerarySchedule_VmaScheduleShifting(
			String itineraryNo, String maritimeCode, String shipPosition,
			String markedAsArrival, String markedAsDeparture,
			String noticeShipType, String shipBoat, String timeOfArrival,
			String portRegionCode, String certificateNo, String requestState,
			boolean export, String fromDate, String toDate, int start, int end)
			throws SystemException {


		JSONArray data = JSONFactoryUtil.createJSONArray();

		boolean status1 = false, status2 = false;
		if (noticeShipType.contains("1") || noticeShipType.contains("2")) {
			status1 = true;
		}
		if (noticeShipType.contains("4") || noticeShipType.contains("5")) {
			status2 = true;
		}
		try {

			StringBuilder query = new StringBuilder();

			if (status1 == true) {
				query.append("SELECT distinct ");
				query.append("A2.NameOfShip,");
				query.append("A2.GT,");
				query.append("A2.DWT,");
				query.append("A2.LOA,");
				query.append("A2.ShownDraftxF,");
				query.append("A2.ShownDraftxA,");
				query.append("A2.TimeOfDeparture,");
				query.append("A2.TimeOfArrival,");
				query.append("A2.TimeOfApproval,");
				query.append("A2.ShipAgencyName,");
				query.append("A2.shipAgencyCode,");
				query.append("A2.ID,");
				query.append("A2.ItineraryNo,");
				query.append("A3.PortRegionName,");
				query.append("A3.PortRegionCode,");
				query.append("A4.PortHarbourNameVN,");
				query.append("A4.PortHarbourCode,");
				query.append("A5.PortWharfNameVN,");
				query.append("A5.PortWharfCode,");
				query.append("vv.Tau_lai,");
				query.append("vvv.Hoa_tieu,");
				query.append("A2.MaritimeRemarks,");
				query.append("A2.NoticeShipType,");
				query.append("A2.CertificateNo,");
				query.append("A2.initFrom,");
				query.append("(SELECT id from vma_schedule_shifting WHERE noticeshiptype = 4 AND itineraryScheduleId = A2.ID) AS vmaScheduleShiftingId,");
				query.append("vvv.PilotCompanyCode,");
				query.append("A5.PortWharfShortName,");
				query.append("A4.PortHarbourShortName,");
				query.append("A3.PortRegionShortName,");
				query.append("A3.SequenceNo AS A3SequenceNo,");
				query.append("vv.Tau_lai_viet_tat,");
				query.append("vvv.Hoa_tieu_viet_tat,");
				query.append("A2.initFrom AS shortInitFrom ");

				query.append(" FROM vma_itinerary AS A1 INNER JOIN vma_itinerary_schedule AS A2 ON A1.ItineraryNo = A2.ItineraryNo LEFT JOIN dm_port_harbour AS A4 ON A2.PortHarbourCode = A4.PortHarbourCode");
				query.append(" LEFT JOIN dm_port_region AS A3 ON A4.PortRegion = A3.PortRegionCode LEFT JOIN dm_port_wharf AS A5 ON A2.PortWharfCode = A5.PortWharfCode");
				query.append(" LEFT JOIN (select v1.ItineraryNo, v1.itineraryScheduleId , GROUP_CONCAT('<br>',v2.ShipName) as Tau_lai, "
						+ " Case ISNULL(GROUP_CONCAT('<br>', Case ISNULL(v3.TugboatShortName)  when 0 then (Case LENGTH(TRIM(v3.TugboatShortName))  when 0 then v2.ShipName else v3.TugboatShortName end) else v2.ShipName end )) "
						+ " when 0 then GROUP_CONCAT('<br>', Case ISNULL(v3.TugboatShortName)  when 0 then (Case LENGTH(TRIM(v3.TugboatShortName))  when 0 then v2.ShipName else v3.TugboatShortName end) else v2.ShipName end ) "
						+ " else GROUP_CONCAT('<br>', v2.ShipName) end AS Tau_lai_viet_tat "
						+ " from vma_schedule_tugboat as v1 LEFT JOIN vma_schedule_tugboat_list as v2 on v1.ItineraryNo = v2.ItineraryNo and v1.sequenceNo = v2.sequenceNo "
						+ " LEFT JOIN dm_vma_tugboat AS v3 ON v3.ShipCode = v2.ShipCode and v3.ShipName like v2.ShipName where NoticeShipType IN ("
						+ noticeShipType
						+ ") GROUP BY v1.ItineraryNo, v1.itineraryScheduleId) as vv on A2.ItineraryNo = vv.ItineraryNo and (vv.itineraryScheduleId = A2.ID and A2.NoticeShipType in (1,2)) and vv.Tau_lai is not null");
				query.append(" LEFT JOIN (select v1.ItineraryNo, v1.itineraryScheduleId , GROUP_CONCAT('<br>',v2.PilotName) as Hoa_tieu, "
						+ " Case ISNULL(GROUP_CONCAT('<br>', Case ISNULL(v3.PilotShortName)  when 0 then (Case LENGTH(TRIM(v3.PilotShortName))  when 0 then v2.PilotName else v3.PilotShortName end) else v2.PilotName end )) "
						+ " when 0 then GROUP_CONCAT('<br>', Case ISNULL(v3.PilotShortName)  when 0 then (Case LENGTH(TRIM(v3.PilotShortName))  when 0 then v2.PilotName else v3.PilotShortName end) else v2.PilotName end )"
						+ " else GROUP_CONCAT('<br>', v2.PilotName) end AS Hoa_tieu_viet_tat, "
						+ " v2.PilotCompanyCode from (Select * from vma_schedule_pilot_list ORDER BY itineraryScheduleId, PilotCategoryCode ASC) AS v2 LEFT JOIN vma_schedule_pilot as v1 on v1.ItineraryNo = v2.ItineraryNo and v1.sequenceNo = v2.sequenceNo "
						+ " LEFT JOIN dm_vma_pilot AS v3 ON v3.PilotCode = v2.PilotCode where NoticeShipType IN ("
						+ noticeShipType
						+ ") GROUP BY v1.ItineraryNo, v1.itineraryScheduleId) as vvv on A2.ItineraryNo = vvv.ItineraryNo and (vvv.itineraryScheduleId = A2.ID and A2.NoticeShipType in (1,2)) and vvv.Hoa_tieu is not null");

				query.append(" WHERE 1=1 ");
				query.append(" and NoticeShipType IN (1, 2) ");
				if (export) {
					query.append(generateCondition_exportExcel_1(itineraryNo,
							maritimeCode, shipPosition, markedAsArrival,
							markedAsDeparture, noticeShipType, shipBoat,
							portRegionCode, null, null, fromDate, toDate));
				} else {
					query.append(generateCondition1(itineraryNo, maritimeCode,
							shipPosition, markedAsArrival, markedAsDeparture,
							noticeShipType, shipBoat, timeOfArrival,
							portRegionCode, null, null));
				}
			}
			if (status2 == true) {
				if (status1 == true) {
					query.append(" UNION ");
				}
				query.append("SELECT distinct ");
				query.append("A2.NameOfShip,");
				query.append("A2.GT,");
				query.append("A2.DWT,");
				query.append("A2.LOA,");
				query.append("A2.ShownDraftxF,");
				query.append("A2.ShownDraftxA,");
				query.append("A2.TimeOfDeparture,");
				query.append("A2.TimeOfArrival,");
				query.append("A2.TimeOfApproval,");
				query.append("A2.ShipAgencyName,");
				query.append("A2.shipAgencyCode,");
				query.append("A2.ID,");
				query.append("A2.ItineraryNo,");
				query.append("A3.PortRegionName,");
				query.append("A3.PortRegionCode,");
				query.append("A4.PortHarbourNameVN,");
				query.append("A4.PortHarbourCode,");
				query.append("A5.PortWharfNameVN,");
				query.append("A5.PortWharfCode,");
				query.append("vv.Tau_lai,");
				query.append("vvv.Hoa_tieu,");
				//query.append("A2.MaritimeRemarks,");
				query.append("A1.Remarks AS MaritimeRemarks,");
				query.append("A2.NoticeShipType,");
				query.append("A2.CertificateNo,");
				query.append("A2.initFrom,");
				query.append("(SELECT id from vma_schedule_shifting WHERE noticeshiptype = 4 AND itineraryScheduleId = A2.ID) AS vmaScheduleShiftingId,");
				query.append("vvv.PilotCompanyCode,");
				query.append("A5.PortWharfShortName,");
				query.append("A4.PortHarbourShortName,");
				query.append("A3.PortRegionShortName,");
				query.append("A3.SequenceNo AS A3SequenceNo,");
				query.append("vv.Tau_lai_viet_tat,");
				query.append("vvv.Hoa_tieu_viet_tat,");
				query.append("CONCAT(");
				query.append("(Select Case ISNULL(PortHarbourShortName)  when 0 then PortHarbourShortName else PortHarbourNameVN end as Harbour from dm_port_harbour where PortHarbourCode = A1.AnchoringPortHarbourCode),");
				query.append("', ',");
				query.append("(Select Case ISNULL(PortWharfShortName)  when 0 then PortWharfShortName else PortWharfNameVN end as Wharf  from dm_port_wharf where PortWharfCode = A1.AnchoringPortWharfCode)) ");
				query.append("AS shortInitFrom, ");
				query.append("A1.ChanelName AS chanelName");


				query.append(" FROM vma_schedule_shifting AS A1 INNER JOIN vma_itinerary_schedule AS A2 ON A1.ItineraryNo = A2.ItineraryNo and A1.itineraryScheduleId = A2.ID LEFT JOIN dm_port_harbour AS A4 ON A2.PortHarbourCode = A4.PortHarbourCode");
				query.append(" LEFT JOIN dm_port_region AS A3 ON A4.PortRegion = A3.PortRegionCode LEFT JOIN dm_port_wharf AS A5 ON A2.PortWharfCode = A5.PortWharfCode");
				query.append(" LEFT JOIN (select v1.ItineraryNo, v1.itineraryScheduleId , GROUP_CONCAT('<br>',v2.ShipName) as Tau_lai,"
						+ " Case ISNULL(GROUP_CONCAT('<br>', Case ISNULL(v3.TugboatShortName)  when 0 then (Case LENGTH(TRIM(v3.TugboatShortName))  when 0 then v2.ShipName else v3.TugboatShortName end) else v2.ShipName end )) "
						+ " when 0 then GROUP_CONCAT('<br>', Case ISNULL(v3.TugboatShortName)  when 0 then (Case LENGTH(TRIM(v3.TugboatShortName))  when 0 then v2.ShipName else v3.TugboatShortName end) else v2.ShipName end ) "
						+ " else GROUP_CONCAT('<br>', v2.ShipName) end AS Tau_lai_viet_tat "
						+ " from vma_schedule_tugboat as v1 LEFT JOIN vma_schedule_tugboat_list as v2 on v1.ItineraryNo = v2.ItineraryNo and v1.sequenceNo = v2.sequenceNo "
						+ " LEFT JOIN dm_vma_tugboat AS v3 ON v3.ShipCode = v2.ShipCode and v3.ShipName like v2.ShipName where NoticeShipType IN ("
						+ noticeShipType
						+ ") GROUP BY v1.ItineraryNo, v1.itineraryScheduleId) as vv on A2.ItineraryNo = vv.ItineraryNo and vv.itineraryScheduleId = A1.ID and vv.Tau_lai is not null");
				query.append(" LEFT JOIN (select v1.ItineraryNo, v1.itineraryScheduleId , GROUP_CONCAT('<br>',v2.PilotName) as Hoa_tieu, "
						+ " Case ISNULL(GROUP_CONCAT('<br>', Case ISNULL(v3.PilotShortName)  when 0 then (Case LENGTH(TRIM(v3.PilotShortName))  when 0 then v2.PilotName else v3.PilotShortName end) else v2.PilotName end )) "
						+ " when 0 then GROUP_CONCAT('<br>', Case ISNULL(v3.PilotShortName)  when 0 then (Case LENGTH(TRIM(v3.PilotShortName))  when 0 then v2.PilotName else v3.PilotShortName end) else v2.PilotName end )"
						+ " else GROUP_CONCAT('<br>', v2.PilotName) end AS Hoa_tieu_viet_tat, "
						+ " v2.PilotCompanyCode from (Select * from vma_schedule_pilot_list ORDER BY itineraryScheduleId, PilotCategoryCode ASC) AS v2 LEFT JOIN vma_schedule_pilot as v1 on v1.ItineraryNo = v2.ItineraryNo and v1.sequenceNo = v2.sequenceNo "
						+ " LEFT JOIN dm_vma_pilot AS v3 ON v3.PilotCode = v2.PilotCode where NoticeShipType IN ("
						+ noticeShipType
						+ ") GROUP BY v1.ItineraryNo, v1.itineraryScheduleId) as vvv on A2.ItineraryNo = vvv.ItineraryNo and vvv.itineraryScheduleId = A1.ID and vvv.Hoa_tieu is not null");

				query.append(" WHERE 1=1 ");

				if (export) {
					query.append(generateCondition_exportExcel_2(itineraryNo,
							certificateNo, noticeShipType, requestState,
							maritimeCode, portRegionCode, fromDate, toDate));
				} else {
					query.append(generateCondition2(itineraryNo, certificateNo,
							"4", requestState, maritimeCode, portRegionCode, timeOfArrival));
				}
			}
			query.append(" ORDER BY  A3SequenceNo ASC, Case (TimeOfArrival IS NOT NULL) when true then TimeOfArrival else TimeOfDeparture end ASC");

			log.info("=========select vma_itinerary_schedule_vma_itinerary_vma_schedule_shifting======== "
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true)
					.queryString(query.toString()).entityClass(Object.class).build();

			Iterator<Object[]> itr =queryFactory2.getResultList(builder).iterator();

			if (itr.hasNext()) {
				while (itr.hasNext()) {
					Object[] objects = itr.next();

					String nameOfShip = String.valueOf(objects[0]);
					double gt = GetterUtil.getDouble(objects[1]);
					double dwt = GetterUtil.getDouble(objects[2]);
					double loa = GetterUtil.getDouble(objects[3]);
					double shownDraftxF = GetterUtil.getDouble(objects[4]);
					double shownDraftxA = GetterUtil.getDouble(objects[5]);
					Date datetimeOfDeparture = (Date) objects[6];

					String timeOfDeparture = StringPool.BLANK;
					String timeHHMMOfDeparture = StringPool.BLANK;
					if (datetimeOfDeparture != null) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(datetimeOfDeparture);

						int day = calendar.get(Calendar.DATE);

						int month = calendar.get(Calendar.MONTH) + 1;

						int year = calendar.get(Calendar.YEAR);

						int hour = calendar.get(Calendar.HOUR_OF_DAY);

						int minute = calendar.get(Calendar.MINUTE);

						timeOfDeparture = (day < 10 ? "0" + day : day)
								+ StringPool.SLASH
								+ (month < 10 ? "0" + month : month)
								+ StringPool.SLASH + year + " "
								+ (hour < 10 ? "0" + hour : hour) + ":"
								+ (minute < 10 ? "0" + minute : minute);
						timeHHMMOfDeparture = (hour < 10 ? "0" + hour : hour) + ":"
								+ (minute < 10 ? "0" + minute : minute);
					}

					Date datetimeOfArrival = (Date) objects[7];

					timeOfArrival = StringPool.BLANK;
					String timeHHMMOfArrival = StringPool.BLANK;

					if (datetimeOfArrival != null) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(datetimeOfArrival);

						int day = calendar.get(Calendar.DATE);

						int month = calendar.get(Calendar.MONTH) + 1;

						int year = calendar.get(Calendar.YEAR);

						int hour = calendar.get(Calendar.HOUR_OF_DAY);

						int minute = calendar.get(Calendar.MINUTE);

						timeOfArrival = (day < 10 ? "0" + day : day)
								+ StringPool.SLASH
								+ (month < 10 ? "0" + month : month)
								+ StringPool.SLASH + year + " "
								+ (hour < 10 ? "0" + hour : hour) + ":"
								+ (minute < 10 ? "0" + minute : minute);
						timeHHMMOfArrival = (hour < 10 ? "0" + hour : hour) + ":"
								+ (minute < 10 ? "0" + minute : minute);
					}

					Date datetimeOfApproval = (Date) objects[8];

					String timeOfApproval = StringPool.BLANK;

					if (datetimeOfApproval != null) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(datetimeOfApproval);

						int day = calendar.get(Calendar.DATE);

						int month = calendar.get(Calendar.MONTH) + 1;

						int year = calendar.get(Calendar.YEAR);

						timeOfApproval = (day < 10 ? "0" + day : day)
								+ StringPool.SLASH
								+ (month < 10 ? "0" + month : month)
								+ StringPool.SLASH + year;
					}
					String shipAgencyShortName = StringPool.BLANK;
					String shipAgencyName = String.valueOf(objects[9]);
					String shipAgencyCode = String.valueOf(objects[10]);

					long vmaItineraryScheduleId = GetterUtil
							.getLong(objects[11]);

					itineraryNo = String.valueOf(objects[12]);

					String portRegionName = String.valueOf(objects[13]);
					portRegionCode = String.valueOf(objects[14]);

					String portHarbourNameVN = String.valueOf(objects[15]);
					String portHarbourCode = String.valueOf(objects[16]);

					String portWharfNameVN = String.valueOf(objects[17]);
					String portWharfCode = String.valueOf(objects[18]);

					String tugboatList = String.valueOf(objects[19]);
					String pilotList = String.valueOf(objects[20]);
					String maritimeRemarks = String.valueOf(objects[21]);
					noticeShipType = String.valueOf(objects[22]);
					certificateNo = String.valueOf(objects[23]);
					String initFrom = String.valueOf(objects[24]);
					String vmaScheduleShiftingId = StringPool.BLANK;
					try {
						vmaScheduleShiftingId = String.valueOf(objects[25]);
					} catch (Exception e) {
						// nothing to do
					}
					String pilotCompanyCode = String.valueOf(objects[26]);

					String portWharfShortName = Validator.isNotNull(String.valueOf(objects[27])) ? String.valueOf(objects[27]) :  portWharfNameVN;
					String portHarbourShortName = Validator.isNotNull(String.valueOf(objects[28])) ? String.valueOf(objects[28]) : portHarbourNameVN;
					String portRegionShortName = Validator.isNotNull(String.valueOf(objects[29])) ? String.valueOf(objects[29]) : portRegionName;

					String shortTugboatList = String.valueOf(objects[31]);
					String shortPilotList = String.valueOf(objects[32]);
					String shortInitFrom = String.valueOf(objects[33]);
					String chanelName = String.valueOf(objects[34]);
					// SonVH sua ten tau = tau keo+ ds xa lan
					try {
						String xalan = StringPool.BLANK;
						List<VmaScheduleMerging> lstVmaScheduleMerging = VmaScheduleMergingLocalServiceUtil
								.findByItineraryNo_NoticeShipType(itineraryNo,
										Integer.parseInt(noticeShipType));
						if (lstVmaScheduleMerging != null
								&& lstVmaScheduleMerging.size() > 0) {
							int i = 0;
							for (VmaScheduleMerging vmaScheduleMerging : lstVmaScheduleMerging) {
								if (vmaScheduleMerging.getItineraryScheduleId() == vmaItineraryScheduleId) {
									i = i + 1;
									if (i == 1) {
										xalan = xalan + " ("
												+ vmaScheduleMerging.getShipLashName();
									} else if (i <= lstVmaScheduleMerging.size()) {
										xalan = xalan + " + "
												+ vmaScheduleMerging.getShipLashName();
									}
								}
							}
							if (i > 0) {
								xalan = xalan + ")";
							}
							nameOfShip = nameOfShip + xalan;
						}
					} catch (Exception e) {

					}
					JSONObject object = JSONFactoryUtil.createJSONObject();

					object.put("nameOfShip", nameOfShip);
					object.put("gt", gt);
					object.put("dwt", dwt);
					object.put("loa", loa);
					object.put("maxDraft", shownDraftxF + "/" + shownDraftxA);
					object.put("shownDraftxA", shownDraftxA);
					object.put("shownDraftxF", shownDraftxF);
					object.put("timeOfArrival", timeOfArrival);
					object.put("timeOfApproval", timeOfApproval);
					object.put("timeOfDeparture", timeOfDeparture);
					object.put("vmaItineraryScheduleId", vmaItineraryScheduleId);
					object.put("itineraryNo", itineraryNo);
					object.put("portRegionName", portRegionName);
					object.put("portHarbourName", portHarbourNameVN);
					object.put("portHarbourNameVN", portHarbourNameVN);
					object.put("portWharfName", portWharfNameVN);
					object.put("portWharfNameVN", portWharfNameVN);
					object.put("portWharfShortName", portWharfShortName);
					object.put("portHarbourShortName", portHarbourShortName);
					object.put("portRegionShortName", portRegionShortName);
					object.put("tugboatList", Validator.isNotNull(tugboatList) ? tugboatList : "");
					object.put("shortTugboatList", Validator.isNotNull(shortTugboatList) ? shortTugboatList : "");

					String companyShortName = StringPool.BLANK;
					String pilotCompanyName = StringPool.BLANK;
					if (Validator.isNotNull(pilotCompanyCode)) {
						DmVmaPilotCompany dmVmaPilotCompany = DmVmaPilotCompanyLocalServiceUtil
								.fetchByPilotCompanyCode(pilotCompanyCode);
						if (dmVmaPilotCompany != null) {
							if (Validator.isNotNull(dmVmaPilotCompany.getCompanyShortName())) {
								companyShortName = " -"
										+ dmVmaPilotCompany.getCompanyShortName();
							}
							pilotCompanyName = " -"
									+ dmVmaPilotCompany.getPilotCompanyName();
						}
						if (Validator.isNotNull(companyShortName)) {
							pilotList += companyShortName;
							shortPilotList += companyShortName;
						} else if (Validator.isNotNull(pilotCompanyName)) {
							pilotList += pilotCompanyName;
							shortPilotList += pilotCompanyName;
						}
					}
					if (Validator.isNotNull(shipAgencyCode)) {
						DmShipAgency objshipAgency =  DmShipAgencyLocalServiceUtil.fetchByShipAgencyCode(shipAgencyCode);
						if ((Validator.isNotNull(objshipAgency) && Validator.isNotNull(objshipAgency.getShipAgencyShortName()))) {
							shipAgencyShortName = objshipAgency.getShipAgencyShortName();
						}
					}
					object.put("pilotList", Validator.isNotNull(pilotList) ? pilotList : "");
					object.put("shortPilotList", Validator.isNotNull(shortPilotList) ? shortPilotList : "");
					object.put("initFrom", Validator.isNotNull(initFrom) ? initFrom : "");
					object.put("shortInitFrom", Validator.isNotNull(shortInitFrom) ? shortInitFrom : "");
					object.put("chanelName", Validator.isNotNull(chanelName) ? chanelName : "");
					object.put("shipAgencyName", Validator.isNotNull(shipAgencyName) ? shipAgencyName: "");
					object.put("shipAgencyShortName", Validator.isNotNull(shipAgencyShortName) ? shipAgencyShortName : (Validator.isNotNull(shipAgencyName) ? shipAgencyName : ""));
					object.put("maritimeRemarks", maritimeRemarks);
					object.put("shipAgencyCode", shipAgencyCode);
					object.put("portRegionCode", portRegionCode);
					object.put("portHarbourCode", portHarbourCode);
					object.put("portWharfCode", portWharfCode);
					object.put("noticeShipType", noticeShipType);
					object.put("certificateNo", certificateNo);

					object.put("vmaScheduleShiftingId", vmaScheduleShiftingId);
					object.put("timeHHMMOfArrival", timeHHMMOfArrival);
					object.put("timeHHMMOfDeparture", timeHHMMOfDeparture);
					if (vmaItineraryScheduleId > 0 ) {
						data.put(object);
					}


				}
			}

		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
		}

		return data;
	}

	public long countVmaItinerary_VmaItinerarySchedule(String itineraryNo,
			String maritimeCode, String shipPosition, String markedAsArrival,
			String markedAsDeparture, String noticeShipType, String shipBoat,
			String portRegionCode, String portHarbourCode, String portWharfCode,
			String cargoType, String vrCode, String routeLevelCode, String chanelCodeList,
			String timeOfArrival, String timeOfDeparture, String nameOfShip,
			String flagStateOfShip, String callSign, String registryNumber,
			double gt, double dwt, double loa,
			double maxDraft, double shownDraftxF, double shownDraftxA) throws SystemException {


		try {
			StringBuilder query = new StringBuilder();
			query.append("SELECT count(*) AS total FROM vma_itinerary AS A1 INNER JOIN vma_itinerary_schedule AS A2 ON A1.ItineraryNo = A2.ItineraryNo WHERE 1 = 1");

			query.append(generateCondition3(itineraryNo,
					maritimeCode, shipPosition, markedAsArrival,
					markedAsDeparture, noticeShipType, shipBoat,
					portRegionCode, portHarbourCode, portWharfCode, cargoType, vrCode, routeLevelCode, chanelCodeList,
					timeOfArrival, timeOfDeparture, nameOfShip, flagStateOfShip, callSign, registryNumber,
					gt, dwt, loa, maxDraft, shownDraftxF, shownDraftxA));

			log.info("=========count vma_itinerary_schedule>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Long.class).build();

			

			Iterator<Long> itr = queryFactory3.getResultList(builder).iterator();

			if (itr.hasNext()) {
				Long count = itr.next();
				if (count != null) {
					return count.intValue();
				}
			}
			return 0;
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
		}
	}

	private String generateCondition1(String itineraryNo, String maritimeCode,
			String shipPosition, String markedAsArrival,
			String markedAsDeparture, String noticeShipType, String shipBoat,
			String timeOfArrival, String portRegionCode, String vrCode,
			String routeLevelCode) {
		StringBuilder condition = new StringBuilder();

		if (Validator.isNotNull(routeLevelCode)) {
			condition.append(" AND A2.RouteLevelCode = \'" + routeLevelCode
					+ "\'");
		}

		if (Validator.isNotNull(maritimeCode)) {
			condition.append(" AND A1.MaritimeCode = \'" + maritimeCode + "\'");
		}

		if (Validator.isNotNull(vrCode)) {
			condition.append(" AND A1.VrCode = \'" + vrCode + "\'");
		}

		if (Validator.isNotNull(markedAsArrival) && !markedAsArrival.isEmpty()
				&& Validator.isNotNull(markedAsDeparture)
				&& !markedAsDeparture.isEmpty()) {
			condition.append(" AND ( ( A1.MarkedAsArrival IN ("
					+ markedAsArrival + ") ");

			if (Validator.isNotNull(noticeShipType)
					&& !noticeShipType.isEmpty()) {
				condition.append(" AND A2.NoticeShipType IN (" + noticeShipType
						+ ") ");
			}

			if (Validator.isNotNull(timeOfArrival)) {
				Date date = null;

				try {
					date = FormatData.formatDDMMYYYY.parse(timeOfArrival);
				} catch (Exception e) {
					log.error(e.getMessage());
				}

				if (date != null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					String strDate = calendar.get(Calendar.YEAR) + "-"
							+ (calendar.get(Calendar.MONTH) + 1) + "-"
							+ calendar.get(Calendar.DATE);

					condition.append(" AND (A2.TimeOfArrival BETWEEN '"
							+ strDate + " 00:00:00'");

					condition.append(" AND '" + strDate + " 23:59:59' ) )");
				}
			}

			condition.append(" OR ( A1.MarkedAsDeparture IN ("
					+ markedAsDeparture + ")");

			condition.append(" AND A2.NoticeShipType IN (" + noticeShipType
					+ ")  ");

			if (Validator.isNotNull(timeOfArrival)) {
				Date date = null;

				try {
					date = FormatData.formatDDMMYYYY.parse(timeOfArrival);
				} catch (Exception e) {
					log.error(e.getMessage());
				}

				if (date != null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					String strDate = calendar.get(Calendar.YEAR) + "-"
							+ (calendar.get(Calendar.MONTH) + 1) + "-"
							+ calendar.get(Calendar.DATE);

					condition.append(" AND A2.TimeOfDeparture BETWEEN '"
							+ strDate + " 00:00:00'");

					condition.append(" AND '" + strDate + " 23:59:59') )");
				}
			}
		} else {
			if (Validator.isNotNull(markedAsArrival)
					&& !markedAsArrival.isEmpty()) {
				condition.append(" AND A1.MarkedAsArrival IN ("
						+ markedAsArrival + ")");

				if (Validator.isNotNull(noticeShipType)
						&& !noticeShipType.isEmpty()) {
					condition.append(" AND A2.NoticeShipType IN ("
							+ noticeShipType + ")");
				}

				if (Validator.isNotNull(timeOfArrival)) {
					Date date = null;

					try {
						date = FormatData.formatDDMMYYYY.parse(timeOfArrival);
					} catch (Exception e) {
						log.error(e.getMessage());
					}

					if (date != null) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(date);
						String strDate = calendar.get(Calendar.YEAR) + "-"
								+ (calendar.get(Calendar.MONTH) + 1) + "-"
								+ calendar.get(Calendar.DATE);

						condition.append(" AND (A2.TimeOfArrival BETWEEN '"
								+ strDate + " 00:00:00'");

						condition.append(" AND '" + strDate + " 23:59:59' )");
					}
				}
			}

			if (Validator.isNotNull(markedAsDeparture)
					&& !markedAsDeparture.isEmpty()) {
				condition.append(" AND A1.MarkedAsDeparture IN ("
						+ markedAsDeparture + ")");

				if (Validator.isNotNull(noticeShipType)
						&& !noticeShipType.isEmpty()) {
					condition.append(" AND A2.NoticeShipType IN ("
							+ noticeShipType + ")");
				}

				if (Validator.isNotNull(timeOfArrival)) {
					Date date = null;

					try {
						date = FormatData.formatDDMMYYYY.parse(timeOfArrival);
					} catch (Exception e) {
						log.error(e.getMessage());
					}

					if (date != null) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(date);
						String strDate = calendar.get(Calendar.YEAR) + "-"
								+ (calendar.get(Calendar.MONTH) + 1) + "-"
								+ calendar.get(Calendar.DATE);

						condition.append(" AND A2.TimeOfDeparture BETWEEN '"
								+ strDate + " 00:00:00'");

						condition.append(" AND '" + strDate + " 23:59:59'");
					}
				}
			}

		}
		/*
		 * if (Validator.isNotNull(noticeShipType) && !noticeShipType.isEmpty())
		 * { condition.append(" AND A2.NoticeShipType IN (" + noticeShipType +
		 * ")"); }
		 */

		if (Validator.isNotNull(shipBoat)) {
			condition.append(" AND A2.ShipBoat = \"" + shipBoat + "\"");
		}

		// Edit by Dungnv
		/*
		 * if (Validator.isNotNull(timeOfArrival)) { Date date = null;
		 *
		 * try { date = FormatData.formatDDMMYYYY.parse(timeOfArrival); } catch
		 * (Exception e) { log.error(e.getMessage()); }
		 *
		 * if (date != null) { Calendar calendar = Calendar.getInstance();
		 * calendar.setTime(date); String strDate = calendar.get(Calendar.YEAR)
		 * + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" +
		 * calendar.get(Calendar.DATE);
		 *
		 * condition.append(" AND (A2.TimeOfArrival BETWEEN '" + strDate +
		 * " 00:00:00'");
		 *
		 * condition.append(" AND '" + strDate + " 23:59:59'");
		 *
		 * condition.append(" OR A2.TimeOfDeparture BETWEEN '" + strDate +
		 * " 00:00:00'");
		 *
		 * condition.append(" AND '" + strDate + " 23:59:59') "); } }
		 */

		if (Validator.isNotNull(itineraryNo)) {
			condition.append(" AND A2.ItineraryNo = \"" + itineraryNo + "\"");
		}

		if (Validator.isNotNull(portRegionCode) && !portRegionCode.isEmpty()) {
			condition.append(" AND A2.PortRegionCode = " + portRegionCode + "");
		}

		return condition.toString();
	}

	private String generateCondition2(String itineraryNo, String certificateNo,
			String noticeShipType, String requestState, String maritimeCode, String portRegionCode,
			String timeOfArrival) {
		StringBuilder condition = new StringBuilder();

		condition.append(" AND A2.NoticeShipType IN (" + noticeShipType + ")");

		if (Validator.isNotNull(requestState)) {
			condition.append(" AND A1.RequestState IN (" + requestState + ")");
		}

		if (Validator.isNotNull(itineraryNo)) {
			condition.append(" AND A2.ItineraryNo = \"" + itineraryNo + "\"");
		}

		if (Validator.isNotNull(certificateNo)) {
			condition.append(" AND A1.certificateNo = \"" + certificateNo
					+ "\"");
		}

		if (Validator.isNotNull(maritimeCode)) {
			condition.append(" AND A1.PortofAuthority = \'" + maritimeCode + "\'");
		}

		if (Validator.isNotNull(portRegionCode) && !portRegionCode.isEmpty()) {
			condition.append(" AND A2.PortRegionCode = " + portRegionCode + "");
		}

		if (Validator.isNotNull(timeOfArrival)) {
			Date date = null;

			try {
				date = FormatData.formatDDMMYYYY.parse(timeOfArrival);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				String strDate = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE);

				condition.append(" AND A2.TimeOfDeparture BETWEEN '" + strDate
						+ " 00:00:00'");

				condition.append(" AND '" + strDate + " 23:59:59'");
			}
		}

		return condition.toString();
	}

	public List<VmaItinerarySchedule> findVmaItinerarySchedule(Class<?> clazz,
			String className, String sql, int start, int end)
			throws SystemException {


		try {
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true)
					.queryString(sql).firstResult(start).maxResults(end - start).entityClass(VmaItinerarySchedule.class).build();


			/* QueryPos qPos = QueryPos.getInstance(q); */

			return (List<VmaItinerarySchedule>) queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
		}
	}

	public long countVmaItinerarySchedule(String sql) throws SystemException {

		try {

			log.info("=========count vms_itinerary_schedule>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ sql);


			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true)
					.queryString(sql).entityClass(Long.class).build();

			Iterator<Long> itr = queryFactory3.getResultList(builder).iterator();

			if (itr.hasNext()) {
				Long count = itr.next();
				if (count != null) {
					return count.intValue();
				}
			}
			return 0;
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	private String generateCondition_exportExcel_1(String itineraryNo,
			String maritimeCode, String shipPosition, String markedAsArrival,
			String markedAsDeparture, String noticeShipType, String shipBoat,
			String portRegionCode, String vrCode, String routeLevelCode,
			String fromDate, String toDate) {
		StringBuilder condition = new StringBuilder();

		if (Validator.isNotNull(routeLevelCode)) {
			condition.append(" AND A2.RouteLevelCode = \'" + routeLevelCode
					+ "\'");
		}

		if (Validator.isNotNull(maritimeCode)) {
			condition.append(" AND A1.MaritimeCode = \'" + maritimeCode + "\'");
		}

		if (Validator.isNotNull(vrCode)) {
			condition.append(" AND A1.VrCode = \'" + vrCode + "\'");
		}

		if (Validator.isNotNull(markedAsArrival) && !markedAsArrival.isEmpty()
				&& Validator.isNotNull(markedAsDeparture)
				&& !markedAsDeparture.isEmpty()) {
			condition.append(" AND ( ( A1.MarkedAsArrival IN ("
					+ markedAsArrival + ") ");

			if (Validator.isNotNull(noticeShipType)
					&& !noticeShipType.isEmpty()) {
				condition.append(" AND A2.NoticeShipType IN (" + noticeShipType
						+ ") ");
			}

			if (Validator.isNotNull(fromDate) && Validator.isNotNull(toDate)
					&& !fromDate.isEmpty() && !toDate.isEmpty()) {
				Date f_date = null;
				Date t_date = null;
				try {
					f_date = FormatData.formatDDMMYYYY.parse(fromDate);
					t_date = FormatData.formatDDMMYYYY.parse(toDate);
				} catch (Exception e) {
					log.error(e.getMessage());
				}
				
				

				if (f_date != null && t_date != null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(f_date);
					String f_strDate = calendar.get(Calendar.YEAR) + "-"
							+ (calendar.get(Calendar.MONTH) + 1) + "-"
							+ calendar.get(Calendar.DATE);
					calendar.setTime(t_date);
					String t_strDate = calendar.get(Calendar.YEAR) + "-"
							+ (calendar.get(Calendar.MONTH) + 1) + "-"
							+ calendar.get(Calendar.DATE);

					
					if (fromDate.length() <= 16 && toDate.length() <= 16) {
						String lastFiveDigitsFromDate = fromDate.substring(fromDate.length() - 5 );
						String lastFiveDigitsToDate = toDate.substring(toDate.length() - 5 );
						condition.append(" AND A2.TimeOfArrival BETWEEN '"
								+ f_strDate + " " + lastFiveDigitsFromDate + ":00'");
						condition.append(" AND '" + t_strDate + " " + lastFiveDigitsToDate + ":00' ) ");
					} else {
						condition.append(" AND A2.TimeOfArrival BETWEEN '"
								+ f_strDate + " 00:00:00'");

						condition.append(" AND '" + t_strDate + " 23:59:59' ) ");

					}
									}
			}

			condition.append(" OR ( A1.MarkedAsDeparture IN ("
					+ markedAsDeparture + ")");

			condition.append(" AND A2.NoticeShipType IN (" + noticeShipType
					+ ")  ");

			if (Validator.isNotNull(fromDate) && Validator.isNotNull(toDate)
					&& !fromDate.isEmpty() && !toDate.isEmpty()) {
				Date f_date = null;
				Date t_date = null;
				try {
					f_date = FormatData.formatDDMMYYYY.parse(fromDate);
					t_date = FormatData.formatDDMMYYYY.parse(toDate);
				} catch (Exception e) {
					log.error(e.getMessage());
				}

				if (f_date != null && t_date != null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(f_date);
					String f_strDate = calendar.get(Calendar.YEAR) + "-"
							+ (calendar.get(Calendar.MONTH) + 1) + "-"
							+ calendar.get(Calendar.DATE);
					calendar.setTime(t_date);
					String t_strDate = calendar.get(Calendar.YEAR) + "-"
							+ (calendar.get(Calendar.MONTH) + 1) + "-"
							+ calendar.get(Calendar.DATE);

					if (fromDate.length() <= 16 && toDate.length() <= 16) {
						String lastFiveDigitsFromDate = fromDate.substring(fromDate.length() - 5 );
						String lastFiveDigitsToDate = toDate.substring(toDate.length() - 5 );
						condition.append(" AND A2.TimeOfDeparture BETWEEN '"
								+ f_strDate + " " + lastFiveDigitsFromDate + ":00'");
						condition.append(" AND '" + t_strDate + " " + lastFiveDigitsToDate + ":00' )) ");
					} else {
						condition.append(" AND A2.TimeOfDeparture BETWEEN '"
								+ f_strDate + " 00:00:00'");

						condition.append(" AND '" + t_strDate + " 23:59:59' )) ");
					}
					
				}
			}
		} else {
			if (Validator.isNotNull(markedAsArrival)
					&& !markedAsArrival.isEmpty()) {
				condition.append(" AND A1.MarkedAsArrival IN ("
						+ markedAsArrival + ")");

				if (Validator.isNotNull(noticeShipType)
						&& !noticeShipType.isEmpty()) {
					condition.append(" AND A2.NoticeShipType IN ("
							+ noticeShipType + ")");
				}

				if (Validator.isNotNull(fromDate)
						&& Validator.isNotNull(toDate) && !fromDate.isEmpty()
						&& !toDate.isEmpty()) {
					Date f_date = null;
					Date t_date = null;
					try {
						f_date = FormatData.formatDDMMYYYY.parse(fromDate);
						t_date = FormatData.formatDDMMYYYY.parse(toDate);
					} catch (Exception e) {
						log.error(e.getMessage());
					}

					if (f_date != null && t_date != null) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(f_date);
						String f_strDate = calendar.get(Calendar.YEAR) + "-"
								+ (calendar.get(Calendar.MONTH) + 1) + "-"
								+ calendar.get(Calendar.DATE);
						calendar.setTime(t_date);
						String t_strDate = calendar.get(Calendar.YEAR) + "-"
								+ (calendar.get(Calendar.MONTH) + 1) + "-"
								+ calendar.get(Calendar.DATE);

						if (fromDate.length() <= 16 && toDate.length() <= 16) {
							String lastFiveDigitsFromDate = fromDate.substring(fromDate.length() - 5 );
							String lastFiveDigitsToDate = toDate.substring(toDate.length() - 5 );
							condition.append(" AND A2.TimeOfArrival BETWEEN '"
									+ f_strDate + " " + lastFiveDigitsFromDate + ":00'");
							condition.append(" AND '" + t_strDate + " " + lastFiveDigitsToDate + ":00' ");
						} else {
							condition.append(" AND A2.TimeOfArrival BETWEEN '"
									+ f_strDate + " 00:00:00'");

							condition.append(" AND '" + t_strDate
									+ " 23:59:59' ");

						}
					}
				}
			}

			if (Validator.isNotNull(markedAsDeparture)
					&& !markedAsDeparture.isEmpty()) {
				condition.append(" AND A1.MarkedAsDeparture IN ("
						+ markedAsDeparture + ")");

				if (Validator.isNotNull(noticeShipType)
						&& !noticeShipType.isEmpty()) {
					condition.append(" AND A2.NoticeShipType IN ("
							+ noticeShipType + ")");
				}

				if (Validator.isNotNull(fromDate)
						&& Validator.isNotNull(toDate) && !fromDate.isEmpty()
						&& !toDate.isEmpty()) {
					Date f_date = null;
					Date t_date = null;
					try {
						f_date = FormatData.formatDDMMYYYY.parse(fromDate);
						t_date = FormatData.formatDDMMYYYY.parse(toDate);
					} catch (Exception e) {
						log.error(e.getMessage());
					}

					if (f_date != null && t_date != null) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(f_date);
						String f_strDate = calendar.get(Calendar.YEAR) + "-"
								+ (calendar.get(Calendar.MONTH) + 1) + "-"
								+ calendar.get(Calendar.DATE);
						calendar.setTime(t_date);
						String t_strDate = calendar.get(Calendar.YEAR) + "-"
								+ (calendar.get(Calendar.MONTH) + 1) + "-"
								+ calendar.get(Calendar.DATE);

						if (fromDate.length() <= 16 && toDate.length() <= 16) {
							String lastFiveDigitsFromDate = fromDate.substring(fromDate.length() - 5 );
							String lastFiveDigitsToDate = toDate.substring(toDate.length() - 5 );
							condition.append(" AND (A2.TimeOfDeparture BETWEEN '"
									+ f_strDate + " " + lastFiveDigitsFromDate + ":00'");
							condition.append(" AND '" + t_strDate + " " + lastFiveDigitsToDate + ":00' ) ");
						} else {
							condition.append(" AND (A2.TimeOfDeparture BETWEEN '"
									+ f_strDate + " 00:00:00'");
	
							condition.append(" AND '" + t_strDate
									+ " 23:59:59' ) ");
						}
					}
				}
			}

		}

		if (Validator.isNotNull(shipBoat)) {
			condition.append(" AND A2.ShipBoat = \"" + shipBoat + "\"");
		}

		if (Validator.isNotNull(itineraryNo)) {
			condition.append(" AND A2.ItineraryNo = \"" + itineraryNo + "\"");
		}

		if (Validator.isNotNull(portRegionCode) && !portRegionCode.isEmpty()) {
			condition.append(" AND A2.PortRegionCode = " + portRegionCode + "");
		}

		return condition.toString();
	}

	private String generateCondition_exportExcel_2(String itineraryNo,
			String certificateNo, String noticeShipType, String requestState,
			String maritimeCode, String portRegionCode, String fromDate, String toDate) {
		StringBuilder condition = new StringBuilder();

		condition.append(" AND A2.NoticeShipType IN (" + noticeShipType + ")");

		if (Validator.isNotNull(requestState)) {
			condition.append(" AND A1.RequestState IN (" + requestState + ")");
		}

		if (Validator.isNotNull(itineraryNo)) {
			condition.append(" AND A2.ItineraryNo = \"" + itineraryNo + "\"");
		}

		if (Validator.isNotNull(certificateNo)) {
			condition.append(" AND A1.certificateNo = \"" + certificateNo
					+ "\"");
		}
		
		if (Validator.isNotNull(maritimeCode)) {
			condition.append(" AND A1.PortofAuthority = \'" + maritimeCode + "\'");
		}


		if (Validator.isNotNull(portRegionCode) && !portRegionCode.isEmpty()) {
			condition.append(" AND A2.PortRegionCode = " + portRegionCode + "");
		}

		if (Validator.isNotNull(fromDate) && Validator.isNotNull(toDate)
				&& !fromDate.isEmpty() && !toDate.isEmpty()) {
			Date f_date = null;
			Date t_date = null;
			try {
				f_date = FormatData.formatDDMMYYYY.parse(fromDate);
				t_date = FormatData.formatDDMMYYYY.parse(toDate);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (f_date != null && t_date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(f_date);
				String f_strDate = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE);
				calendar.setTime(t_date);
				String t_strDate = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE);

				if (fromDate.length() <= 16 && toDate.length() <= 16) {
					String lastFiveDigitsFromDate = fromDate.substring(fromDate.length() - 5 );
					String lastFiveDigitsToDate = toDate.substring(toDate.length() - 5 );
					condition.append(" AND (A2.TimeOfDeparture BETWEEN '"
							+ f_strDate + " " + lastFiveDigitsFromDate + ":00'");
					condition.append(" AND '" + t_strDate + " " + lastFiveDigitsToDate + ":00' ) ");
				} else {
					condition.append(" AND (A2.TimeOfDeparture BETWEEN '"
							+ f_strDate + " 00:00:00'");
	
					condition.append(" AND '" + t_strDate + " 23:59:59' ) ");
				}
			}
		}
		return condition.toString();
	}
	
	private String generateCondition3(String itineraryNo, String maritimeCode,
			String shipPosition, String markedAsArrival,
			String markedAsDeparture, String noticeShipType, String shipBoat,
			String portRegionCode, String portHarbourCode, String portWharfCode, 
			String cargoType, String vrCode, String routeLevelCode, String chanelCodeList, 
			String timeOfArrival, String timeOfDeparture, String nameOfShip, 
			String flagStateOfShip, String callSign, String registryNumber,
			double gt, double dwt, double loa, 
			double maxDraft, double shownDraftxF, double shownDraftxA	) {
		StringBuilder condition = new StringBuilder();

		if (Validator.isNotNull(itineraryNo)) {
			condition.append(" AND A1.ItineraryNo = \'" + itineraryNo + "\'");
		}

		if (Validator.isNotNull(maritimeCode)) {
			condition.append(" AND A1.MaritimeCode = \'" + maritimeCode + "\'");
		}

		if (Validator.isNotNull(shipPosition)) {
			condition.append(" AND A1.ShipPosition = \'" + shipPosition + "\'");
		}
		

		if (Validator.isNotNull(markedAsArrival) && !markedAsArrival.isEmpty()
				&& Validator.isNotNull(markedAsDeparture)
				&& !markedAsDeparture.isEmpty()) {
			condition.append(" AND ( ( A1.MarkedAsArrival IN ("
					+ markedAsArrival + ") ");

			if (Validator.isNotNull(noticeShipType)
					&& !noticeShipType.isEmpty()) {
				condition.append(" AND A2.NoticeShipType IN (" + noticeShipType
						+ ") ");
			}

			if (Validator.isNotNull(timeOfArrival)) {
				Date date = null;

				try {
					date = FormatData.formatDDMMYYYY.parse(timeOfArrival);
				} catch (Exception e) {
					log.error(e.getMessage());
				}

				if (date != null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					String strDate = calendar.get(Calendar.YEAR) + "-"
							+ (calendar.get(Calendar.MONTH) + 1) + "-"
							+ calendar.get(Calendar.DATE);

					condition.append(" AND (A2.TimeOfArrival BETWEEN '"
							+ strDate + " 00:00:00'");

					condition.append(" AND '" + strDate + " 23:59:59' ) )");
				}
			}

			condition.append(" OR ( A1.MarkedAsDeparture IN ("
					+ markedAsDeparture + ")");

			condition.append(" AND A2.NoticeShipType IN (" + noticeShipType
					+ ")  ");

			if (Validator.isNotNull(timeOfDeparture)) {
				Date date = null;

				try {
					date = FormatData.formatDDMMYYYY.parse(timeOfDeparture);
				} catch (Exception e) {
					log.error(e.getMessage());
				}

				if (date != null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					String strDate = calendar.get(Calendar.YEAR) + "-"
							+ (calendar.get(Calendar.MONTH) + 1) + "-"
							+ calendar.get(Calendar.DATE);

					condition.append(" AND A2.TimeOfDeparture BETWEEN '"
							+ strDate + " 00:00:00'");

					condition.append(" AND '" + strDate + " 23:59:59') )");
				}
			}
		} else {
			if (Validator.isNotNull(markedAsArrival)
					&& !markedAsArrival.isEmpty()) {
				condition.append(" AND A1.MarkedAsArrival IN ("
						+ markedAsArrival + ")");

				if (Validator.isNotNull(noticeShipType)
						&& !noticeShipType.isEmpty()) {
					condition.append(" AND A2.NoticeShipType IN ("
							+ noticeShipType + ")");
				}

				if (Validator.isNotNull(timeOfArrival)) {
					Date date = null;

					try {
						date = FormatData.formatDDMMYYYY.parse(timeOfArrival);
					} catch (Exception e) {
						log.error(e.getMessage());
					}

					if (date != null) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(date);
						String strDate = calendar.get(Calendar.YEAR) + "-"
								+ (calendar.get(Calendar.MONTH) + 1) + "-"
								+ calendar.get(Calendar.DATE);

						condition.append(" AND (A2.TimeOfArrival BETWEEN '"
								+ strDate + " 00:00:00'");

						condition.append(" AND '" + strDate + " 23:59:59' )");
					}
				}
			}

			if (Validator.isNotNull(markedAsDeparture)
					&& !markedAsDeparture.isEmpty()) {
				condition.append(" AND A1.MarkedAsDeparture IN ("
						+ markedAsDeparture + ")");

				if (Validator.isNotNull(noticeShipType)
						&& !noticeShipType.isEmpty()) {
					condition.append(" AND A2.NoticeShipType IN ("
							+ noticeShipType + ")");
				}

				if (Validator.isNotNull(timeOfDeparture)) {
					Date date = null;

					try {
						date = FormatData.formatDDMMYYYY.parse(timeOfDeparture);
					} catch (Exception e) {
						log.error(e.getMessage());
					}

					if (date != null) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(date);
						String strDate = calendar.get(Calendar.YEAR) + "-"
								+ (calendar.get(Calendar.MONTH) + 1) + "-"
								+ calendar.get(Calendar.DATE);

						condition.append(" AND A2.TimeOfDeparture BETWEEN '"
								+ strDate + " 00:00:00'");

						condition.append(" AND '" + strDate + " 23:59:59'");
					}
				}
			}

		}

		if (Validator.isNotNull(shipBoat)) {
			condition.append(" AND A2.ShipBoat = \'" + shipBoat + "\'");
		}

		if (Validator.isNotNull(itineraryNo)) {
			condition.append(" AND A2.ItineraryNo = \"" + itineraryNo + "\"");
		}

		if (Validator.isNotNull(portRegionCode) && !portRegionCode.isEmpty()) {
			condition.append(" AND A2.PortRegionCode = " + portRegionCode + "");
		}

		if (Validator.isNotNull(portHarbourCode) && !portHarbourCode.isEmpty()) {
			condition.append(" AND A2.PortHarbourCode = " + portHarbourCode + "");
		}
		
		if (Validator.isNotNull(portWharfCode) && !portWharfCode.isEmpty()) {
			condition.append(" AND A2.PortWharfCode = " + portWharfCode + "");
		}
		

		if (Validator.isNotNull(vrCode)) {
			condition.append(" AND A1.VrCode = \'" + vrCode + "\'");
		}
		
		if (Validator.isNotNull(routeLevelCode)) {
			condition.append(" AND A2.RouteLevelCode = \'" + routeLevelCode + "\'");
		}
		
		if (Validator.isNotNull(cargoType)) {
			condition.append(" AND A2.CargoType = \'" + cargoType + "\'");
		}
		
		if (Validator.isNotNull(chanelCodeList)
				&& !chanelCodeList.isEmpty()) {
			condition.append(" AND A2.ChanelCodeList IN ("
					+ chanelCodeList + ")");
		}
		
		if (Validator.isNotNull(nameOfShip)) {
			condition.append(" AND A2.NameOfShip like \'%" + nameOfShip + "%\'");
		}
		if (Validator.isNotNull(flagStateOfShip)) {
			condition.append(" AND A1.FlagStateOfShip = \'" + flagStateOfShip + "\'");
		}
		if (Validator.isNotNull(callSign)) {
			condition.append(" AND A1.CallSign like \'%" + callSign + "%\'");
		}
		if (Validator.isNotNull(registryNumber)) {
			condition.append(" AND A1.registryNumber like \'%" + registryNumber + "%\'");
		}
		
		if (Validator.isNotNull(gt) && gt > 0) {
			condition.append(" AND A2.GT <= " + gt);
		}
		if (Validator.isNotNull(dwt) && dwt > 0) {
			condition.append(" AND A2.DWT <= " + dwt);
		}
		if (Validator.isNotNull(loa) && loa > 0) {
			condition.append(" AND A2.LOA <= " + loa);
		}
		if (Validator.isNotNull(maxDraft) && maxDraft > 0) {
			condition.append(" AND A2.maxDraft <= " + maxDraft);
		}
		if (Validator.isNotNull(shownDraftxF) && shownDraftxF > 0) {
			condition.append(" AND A2.shownDraftxF <= " + shownDraftxF);
		}
		if (Validator.isNotNull(shownDraftxA) && shownDraftxA > 0) {
			condition.append(" AND A2.shownDraftxA <= " + shownDraftxA);
		}
		
		return condition.toString();
	}

	public JSONObject findSoLuotLaiDat_VmaItinerarySchedule_VmaScheduleShifting(
			String maritimeCode, String tugboatCompanyCode, String shipCode,  String reportUser,
			String reportPeriod, String reportYear, String reportMonth,
			String fromDate, String toDate, int start, int end)
			throws SystemException {

		

		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray data = JSONFactoryUtil.createJSONArray();
		
		try {
			

			StringBuilder query = new StringBuilder();
	
						
				query.append("Select distinct ");
				query.append("ItineraryNo, ");
				query.append("NameOfShip, ");
				query.append("NoticeShipType, ");
				query.append("TimeOfArrival, ");
				query.append("TimeOfDeparture, ");
				query.append("Tau_lai, ");
				query.append("TugboatCompanyCode, ");
				query.append("Tau_lai_viet_tat, ");
				query.append("TugDateFrom, ");
				query.append("TugDateTo, ");
				query.append("ShipCode, ");
				query.append("ShipName, ");
				query.append("TugboatCompanyName, ");				
				query.append("TugMode, ");
				query.append("TugboatShortName, ");
				query.append("Displacement, ");
				query.append("TugFinishAt, ");
				query.append("GT, ");
				query.append("DWT, ");
				query.append("LOA, ");
				query.append("ShownDraftxF, ");
				query.append("ShownDraftxA, ");
			query.append("PortWharfShortName, ");
			query.append("PortHarbourShortName, ");
			query.append("PortRegionShortName, ");
			query.append("PortRegionName, ");
			query.append("PortRegionCode, ");
			query.append("PortHarbourNameVN, ");
			query.append("PortHarbourCode, ");
			query.append("PortWharfNameVN, ");

			query.append("PortWharfCode, ");
				query.append("InitFrom, ");
			query.append("shortInitFrom, ");
			query.append("itineraryScheduleId ");

			query.append(" from ( ");
				
			
						 
				query.append(" (Select  vv.itineraryScheduleId, A2.ItineraryNo, A2.NameOfShip, ");
				query.append(" A2.NoticeShipType, A2.TimeOfArrival, A2.TimeOfDeparture, vv.Tau_lai, vv.TugboatCompanyCode, vv.Tau_lai_viet_tat, ");
			query.append(" vv.TugDateFrom, vv.TugDateTo, A0.ShipCode, A0.ShipName, A0.TugboatCompanyName, vv.TugMode, A0.TugboatShortName, A0.Displacement,  ");
				query.append(" CASE NOTICESHIPTYPE WHEN 4 THEN TimeOfDeparture WHEN 2 THEN TimeOfDeparture ELSE TimeOfArrival  END AS TugFinishAt, ");
				query.append("A2.GT,");
				query.append("A2.DWT,");
				query.append("A2.LOA,");
				query.append("A2.ShownDraftxF,");
				query.append("A2.ShownDraftxA,");
				query.append("A5.PortWharfShortName,");
				query.append("A4.PortHarbourShortName,");
				query.append("A3.PortRegionShortName,");				
				query.append("A3.PortRegionName,");
				query.append("A3.PortRegionCode,");
				query.append("A4.PortHarbourNameVN,");
				query.append("A4.PortHarbourCode,");
				query.append("A5.PortWharfNameVN,");
				query.append("A5.PortWharfCode, ");
				query.append(" A2.InitFrom,");
				query.append(" A2.initFrom AS shortInitFrom ");
				
				query.append(" from vma_itinerary_schedule as A2 ");
				query.append(" LEFT JOIN dm_port_harbour AS A4 ON A2.PortHarbourCode = A4.PortHarbourCode");
				query.append(" LEFT JOIN dm_port_region AS A3 ON A4.PortRegion = A3.PortRegionCode LEFT JOIN dm_port_wharf AS A5 ON A2.PortWharfCode = A5.PortWharfCode");
			query.append(" LEFT JOIN (select v1.ItineraryNo, v1.itineraryScheduleId , GROUP_CONCAT('<br>',v2.TugboatCompanyCode) as Danh_sach_congty_tau_lai, GROUP_CONCAT('<br>',v2.ShipCode) as Danh_sach_tau_lai, GROUP_CONCAT('<br>',v2.ShipName) as Tau_lai, ");
			query.append(" Case ISNULL(GROUP_CONCAT('<br>', Case ISNULL(v3.TugboatShortName)  when 0 then (Case LENGTH(TRIM(v3.TugboatShortName))  when 0 then v2.ShipName else v3.TugboatShortName end) else v2.ShipName end )) ");
				query.append(" when 0 then GROUP_CONCAT('<br>', Case ISNULL(v3.TugboatShortName)  when 0 then (Case LENGTH(TRIM(v3.TugboatShortName))  when 0 then v2.ShipName else v3.TugboatShortName end) else v2.ShipName end )  ");
				query.append(" else GROUP_CONCAT('<br>', v2.ShipName) end AS Tau_lai_viet_tat, v1.TugDateFrom, v1.TugDateTo, v2.ShipCode, v2.ShipName,  ");
				query.append(" v2.TugboatCompanyCode, v2.TugboatCompanyName, v2.TugboatShortName, v2.TugMode, v2.GT, v2.Power, v2.UnitPower ");
				query.append(" from vma_schedule_tugboat as v1 LEFT JOIN vma_schedule_tugboat_list as v2 on v1.ItineraryNo = v2.ItineraryNo and v1.sequenceNo = v2.sequenceNo ");
			if (Validator.isNotNull(tugboatCompanyCode)) {
				query.append(" and v2.TugboatCompanyCode = '" + tugboatCompanyCode + "'");
			}
			query.append(" LEFT JOIN dm_vma_tugboat AS v3 ON v3.ShipCode = v2.ShipCode and v3.ShipName like v2.ShipName where v1.NoticeShipType IN (1, 2) ");

			query.append(" GROUP BY v1.ItineraryNo, v1.itineraryScheduleId) as vv on A2.ItineraryNo = vv.ItineraryNo and (vv.itineraryScheduleId = A2.ID and A2.NoticeShipType in (1,2)) and vv.Tau_lai is not null ");
			query.append(" LEFT JOIN dm_vma_tugboat A0 ON A0.ID < 0 ");
			if (Validator.isNotNull(shipCode)) {
				query.append(" OR A0.ShipCode = '" + shipCode + "'");
			}

			query.append(" where 1=1 ");
				query.append(" and A2.NoticeShipType IN (1, 2) ");
							
				if (Validator.isNotNull(tugboatCompanyCode)) {
					query.append(" and LOCATE('" + tugboatCompanyCode + "'" + ", vv.Danh_sach_congty_tau_lai) > 0 ");
				}
				if (Validator.isNotNull(shipCode)) {
					query.append(" and LOCATE('" + shipCode + "'" + ", vv.Danh_sach_tau_lai) > 0 ");
				} 
				query.append(" ORDER BY vv.itineraryScheduleId ");
				query.append(" )			 ");

				query.append(" UNION ( ");

			query.append(" Select A1.itineraryScheduleId, A2.ItineraryNo, A2.NameOfShip, ");
				
				query.append(" A2.NoticeShipType, A2.TimeOfArrival, A2.TimeOfDeparture, vv.Tau_lai, vv.TugboatCompanyCode, vv.Tau_lai_viet_tat, ");
			query.append(" vv.TugDateFrom, vv.TugDateTo, A0.ShipCode, A0.ShipName, vv.TugboatCompanyName, vv.TugMode, A0.TugboatShortName, A0.Displacement, ");
				query.append(" CASE NOTICESHIPTYPE WHEN 4 THEN TimeOfDeparture WHEN 2 THEN TimeOfDeparture ELSE TimeOfArrival  END AS TugFinishAt, ");
				query.append("A2.GT,");
				query.append("A2.DWT,");
				query.append("A2.LOA,");
				query.append("A2.ShownDraftxF,");
				query.append("A2.ShownDraftxA,");
				query.append("A5.PortWharfShortName,");
				query.append("A4.PortHarbourShortName,");
				query.append("A3.PortRegionShortName,");				
				query.append("A3.PortRegionName,");
				query.append("A3.PortRegionCode,");
				query.append("A4.PortHarbourNameVN,");
				query.append("A4.PortHarbourCode,");
				query.append("A5.PortWharfNameVN,");
				query.append("A5.PortWharfCode,");
				query.append(" A2.InitFrom,");
				query.append(" A2.initFrom AS shortInitFrom ");
				
				query.append(" FROM vma_schedule_shifting AS A1  ");
				query.append(" INNER JOIN vma_itinerary_schedule AS A2 ON A1.ItineraryNo = A2.ItineraryNo and A1.itineraryScheduleId = A2.ID ");
				query.append(" LEFT JOIN dm_port_harbour AS A4 ON A2.PortHarbourCode = A4.PortHarbourCode");
				query.append(" LEFT JOIN dm_port_region AS A3 ON A4.PortRegion = A3.PortRegionCode LEFT JOIN dm_port_wharf AS A5 ON A2.PortWharfCode = A5.PortWharfCode");
			query.append(" LEFT JOIN (select v1.ItineraryNo, v1.itineraryScheduleId , GROUP_CONCAT('<br>',v2.TugboatCompanyCode) as Danh_sach_congty_tau_lai, GROUP_CONCAT('<br>',v2.ShipCode) as Danh_sach_tau_lai, GROUP_CONCAT('<br>',v2.ShipName) as Tau_lai,  ");
				query.append(" Case ISNULL(GROUP_CONCAT('<br>', Case ISNULL(v3.TugboatShortName)  when 0 then (Case LENGTH(TRIM(v3.TugboatShortName))  when 0 then v2.ShipName else v3.TugboatShortName end) else v2.ShipName end )) "); 
				query.append(" when 0 then GROUP_CONCAT('<br>', Case ISNULL(v3.TugboatShortName)  when 0 then (Case LENGTH(TRIM(v3.TugboatShortName))  when 0 then v2.ShipName else v3.TugboatShortName end) else v2.ShipName end )  ");
				query.append(" else GROUP_CONCAT('<br>', v2.ShipName) end AS Tau_lai_viet_tat, v1.TugDateFrom, v1.TugDateTo, v2.ShipCode, v2.ShipName,  ");
			query.append(" v3.TugboatCompanyCode, v3.TugboatCompanyName, v3.TugboatShortName, v2.TugMode, v2.GT, v2.Power, v2.UnitPower ");
			query.append(" from vma_schedule_tugboat as v1 LEFT JOIN vma_schedule_tugboat_list as v2 on v1.ItineraryNo = v2.ItineraryNo and v1.sequenceNo = v2.sequenceNo ");
			if (Validator.isNotNull(tugboatCompanyCode)) {
				query.append(" and v2.TugboatCompanyCode = '" + tugboatCompanyCode + "'");
			}
			query.append(" LEFT JOIN dm_vma_tugboat AS v3 ON v3.ShipCode = v2.ShipCode and v3.ShipName like v2.ShipName where v1.NoticeShipType IN (4) ");

			query.append(" GROUP BY v1.ItineraryNo, v1.itineraryScheduleId) as vv on A2.ItineraryNo = vv.ItineraryNo and vv.itineraryScheduleId = A1.ID and vv.Tau_lai is not null ");
			query.append(" LEFT JOIN dm_vma_tugboat A0 ON A0.ID < 0 ");
			if (Validator.isNotNull(shipCode)) {
				query.append(" OR A0.ShipCode = '" + shipCode + "'");
			}

			query.append(" where 1=1 ");
								
				if (Validator.isNotNull(tugboatCompanyCode)) {
					query.append(" and LOCATE('" + tugboatCompanyCode + "'" + ", vv.Danh_sach_congty_tau_lai) > 0 ");
				}
				if (Validator.isNotNull(shipCode)) {
					query.append(" and LOCATE('" + shipCode + "'" + ", vv.Danh_sach_tau_lai) > 0 ");
				}
								
				query.append(" ORDER BY vv.itineraryScheduleId ");
				query.append(" ) ");
				query.append(" ) as LuotLaiDat Where 1=1 "); 
			
				String reportDateFrom = StringPool.BLANK;
				String reportDateTo = StringPool.BLANK;
				if (Validator.isNotNull(fromDate) && Validator.isNotNull(toDate)
						&& !fromDate.isEmpty() && !toDate.isEmpty()) {
					Date f_date = null;
					Date t_date = null;
					try {
						f_date = FormatData.formatDateShort.parse(fromDate);
						t_date = FormatData.formatDateShort.parse(toDate);
					} catch (Exception e) {
						log.error(e.getMessage());
					}

					if (f_date != null && t_date != null) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(f_date);
						String f_strDate = calendar.get(Calendar.YEAR) + "-"
								+ (calendar.get(Calendar.MONTH) + 1) + "-"
								+ calendar.get(Calendar.DATE);
						
						reportDateFrom = 						
								calendar.get(Calendar.DATE) + StringPool.SLASH + (calendar.get(Calendar.MONTH) + 1) + StringPool.SLASH
								+ calendar.get(Calendar.YEAR);
						
						calendar.setTime(t_date);
						String t_strDate = calendar.get(Calendar.YEAR) + "-"
								+ (calendar.get(Calendar.MONTH) + 1) + "-"
								+ calendar.get(Calendar.DATE);

						reportDateTo = 						
								calendar.get(Calendar.DATE) + StringPool.SLASH + (calendar.get(Calendar.MONTH) + 1) + StringPool.SLASH
								+ calendar.get(Calendar.YEAR);
						
						if (fromDate.length() <= 16 && toDate.length() <= 16) {
							String lastFiveDigitsFromDate = fromDate.substring(fromDate.length() - 5 );
							String lastFiveDigitsToDate = toDate.substring(toDate.length() - 5 );
							query.append(" AND TugFinishAt BETWEEN '"
									+ f_strDate + " " + lastFiveDigitsFromDate + ":00'");
							query.append(" AND '" + t_strDate + " " + lastFiveDigitsToDate + ":00' ");
						} else {
							query.append(" AND TugFinishAt BETWEEN '"
									+ f_strDate + " 00:00:00'");
							query.append(" AND '" + t_strDate + " 23:59:59' ");
						}
						
					}
				}
			query.append(" ORDER BY TugFinishAt ASC, TugboatCompanyCode ASC, ShipCode ASC ");
			log.info("=========findSoLuotLaiDat_DmVmaTugboat_VmaItinerarySchedule_VmaScheduleShifting======== "
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString())
					.firstResult(start).maxResults(end - start).entityClass(Object.class).build();

			Iterator<Object[]> itr =queryFactory2.getResultList(builder).iterator();
			int CountTug = 0;
			
			if (itr.hasNext()) {
				while (itr.hasNext()) {
					Object[] objects = itr.next();

					String itineraryNo = String.valueOf(objects[0]);

					String nameOfShip = String.valueOf(objects[1]);
					String noticeShipType = String.valueOf(objects[2]);

					// String TimeOfArrival = String.valueOf(objects[3]);
					// String TimeOfDeparture = String.valueOf(objects[4]);
					
					String tau_lai = String.valueOf(objects[5]);
					tugboatCompanyCode = String.valueOf(objects[6]);

					String tau_lai_viet_tat = String.valueOf(objects[7]);
					String tugDateFrom = String.valueOf(objects[8]);

					String tugDateTo = String.valueOf(objects[9]);
					shipCode = String.valueOf(objects[10]);
					String shipName = String.valueOf(objects[11]);
					String tugboatCompanyName = String.valueOf(objects[12]);
					String tugMode = String.valueOf(objects[13]);
					String tugboatShortName = String.valueOf(objects[14]);
					String displacement = String.valueOf(objects[15]);
					// String TugFinishAt = String.valueOf(objects[16]);
					
					double gt = GetterUtil.getDouble(objects[17]);
					double dwt = GetterUtil.getDouble(objects[18]);
					double loa = GetterUtil.getDouble(objects[19]);
					double shownDraftxF = GetterUtil.getDouble(objects[20]);
					double shownDraftxA = GetterUtil.getDouble(objects[21]);
					
					String portRegionName = String.valueOf(objects[25]);
					String portHarbourNameVN = String.valueOf(objects[27]);
					String portWharfNameVN = String.valueOf(objects[29]);
					String initFrom = String.valueOf(objects[31]);
					String shortInitFrom = String.valueOf(objects[32]);
					String portWharfShortName = Validator.isNotNull(String.valueOf(objects[22])) ? String.valueOf(objects[22]) :  portWharfNameVN;
					String portHarbourShortName = Validator.isNotNull(String.valueOf(objects[23])) ? String.valueOf(objects[23]) : portHarbourNameVN;
					String portRegionShortName = Validator.isNotNull(String.valueOf(objects[24])) ? String.valueOf(objects[24]) : portRegionName;
										
					
					Date datetimeOfTugFinish = (Date) objects[16];

					String timeOfTugFinish = StringPool.BLANK;
					String timeHHMMOfTugFinish = StringPool.BLANK;
					String dayOfTugFinish = StringPool.BLANK;
					if (datetimeOfTugFinish != null) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(datetimeOfTugFinish);

						int day = calendar.get(Calendar.DATE);

						int month = calendar.get(Calendar.MONTH) + 1;

						int year = calendar.get(Calendar.YEAR);

						int hour = calendar.get(Calendar.HOUR_OF_DAY);

						int minute = calendar.get(Calendar.MINUTE);

						timeOfTugFinish = (day < 10 ? "0" + day : day)
								+ StringPool.SLASH
								+ (month < 10 ? "0" + month : month)
								+ StringPool.SLASH + year + " "
								+ (hour < 10 ? "0" + hour : hour) + ":"
								+ (minute < 10 ? "0" + minute : minute);
						timeHHMMOfTugFinish = (hour < 10 ? "0" + hour : hour) + ":"
								+ (minute < 10 ? "0" + minute : minute);
						dayOfTugFinish = (day < 10 ? "0" + day : day)
								+ StringPool.SLASH
								+ (month < 10 ? "0" + month : month)
								+ StringPool.SLASH + year;
					}
					
					Date datetimeOfDeparture = (Date) objects[4];

					String timeOfDeparture = StringPool.BLANK;
					String timeHHMMOfDeparture = StringPool.BLANK;
					String dayOfDeparture = StringPool.BLANK;
					if (datetimeOfDeparture != null) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(datetimeOfDeparture);

						int day = calendar.get(Calendar.DATE);

						int month = calendar.get(Calendar.MONTH) + 1;

						int year = calendar.get(Calendar.YEAR);

						int hour = calendar.get(Calendar.HOUR_OF_DAY);

						int minute = calendar.get(Calendar.MINUTE);

						timeOfDeparture = (day < 10 ? "0" + day : day)
								+ StringPool.SLASH
								+ (month < 10 ? "0" + month : month)
								+ StringPool.SLASH + year + " "
								+ (hour < 10 ? "0" + hour : hour) + ":"
								+ (minute < 10 ? "0" + minute : minute);
						timeHHMMOfDeparture = (hour < 10 ? "0" + hour : hour) + ":"
								+ (minute < 10 ? "0" + minute : minute);
						dayOfDeparture = (day < 10 ? "0" + day : day)
								+ StringPool.SLASH
								+ (month < 10 ? "0" + month : month)
								+ StringPool.SLASH + year;
					}

					Date datetimeOfArrival = (Date) objects[3];

					String timeOfArrival = StringPool.BLANK;
					String timeHHMMOfArrival = StringPool.BLANK;
					String dayOfArrival = StringPool.BLANK;

					if (datetimeOfArrival != null) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(datetimeOfArrival);

						int day = calendar.get(Calendar.DATE);

						int month = calendar.get(Calendar.MONTH) + 1;

						int year = calendar.get(Calendar.YEAR);

						int hour = calendar.get(Calendar.HOUR_OF_DAY);

						int minute = calendar.get(Calendar.MINUTE);

						timeOfArrival = (day < 10 ? "0" + day : day)
								+ StringPool.SLASH
								+ (month < 10 ? "0" + month : month)
								+ StringPool.SLASH + year + " "
								+ (hour < 10 ? "0" + hour : hour) + ":"
								+ (minute < 10 ? "0" + minute : minute);
						timeHHMMOfArrival = (hour < 10 ? "0" + hour : hour) + ":"
								+ (minute < 10 ? "0" + minute : minute);
						dayOfArrival = (day < 10 ? "0" + day : day)
								+ StringPool.SLASH
								+ (month < 10 ? "0" + month : month)
								+ StringPool.SLASH + year ;
					}
					Date datetimeOfTugDateFrom = (Date) objects[8];			
					if (datetimeOfTugDateFrom != null) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(datetimeOfTugDateFrom);

						int day = calendar.get(Calendar.DATE);

						int month = calendar.get(Calendar.MONTH) + 1;

						int year = calendar.get(Calendar.YEAR);

						int hour = calendar.get(Calendar.HOUR_OF_DAY);

						int minute = calendar.get(Calendar.MINUTE);

						tugDateFrom = (day < 10 ? "0" + day : day)
								+ StringPool.SLASH
								+ (month < 10 ? "0" + month : month)
								+ StringPool.SLASH + year + " "
								+ (hour < 10 ? "0" + hour : hour) + ":"
										+ (minute < 10 ? "0" + minute : minute);
					}
					Date datetimeOfTugDateTo = (Date) objects[9];
					if (datetimeOfTugDateTo != null) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(datetimeOfTugDateTo);

						int day = calendar.get(Calendar.DATE);

						int month = calendar.get(Calendar.MONTH) + 1;

						int year = calendar.get(Calendar.YEAR);

						int hour = calendar.get(Calendar.HOUR_OF_DAY);

						int minute = calendar.get(Calendar.MINUTE);

						tugDateTo = (day < 10 ? "0" + day : day)
								+ StringPool.SLASH
								+ (month < 10 ? "0" + month : month)
								+ StringPool.SLASH + year + " "
								+ (hour < 10 ? "0" + hour : hour) + ":"
								+ (minute < 10 ? "0" + minute : minute); 
								
					}
					JSONObject object = JSONFactoryUtil.createJSONObject();

					object.put("itineraryNo", itineraryNo);
					object.put("nameOfShip", nameOfShip);
					object.put("gt", gt);
					object.put("dwt", dwt);
					object.put("loa", loa);
					object.put("maxDraft", shownDraftxF + "/" + shownDraftxA);
					object.put("shownDraftxA", shownDraftxA);
					object.put("shownDraftxF", shownDraftxF);
					object.put("noticeShipType", noticeShipType);
					object.put("tau_lai", tau_lai);
					object.put("tugboatCompanyCode", tugboatCompanyCode);
					object.put("tau_lai_viet_tat", tau_lai_viet_tat );					
					object.put("timeOfArrival", timeOfArrival);
					object.put("dayOfArrival", dayOfArrival);
					object.put("timeHHMMOfArrival", timeHHMMOfArrival);
					object.put("timeOfDeparture", timeOfDeparture);
					object.put("dayOfDeparture", dayOfDeparture);
					object.put("timeHHMMOfDeparture", timeHHMMOfDeparture);
					object.put("timeOfTugFinish", timeOfTugFinish);
					object.put("dayOfTugFinish", dayOfTugFinish);
					object.put("timeHHMMOfTugFinish", timeHHMMOfTugFinish);
					object.put("tugDateFrom", tugDateFrom );
					object.put("tugDateTo", tugDateTo );
					object.put("shipCode", shipCode );
					object.put("shipName", shipName );
					object.put("tugboatCompanyName", tugboatCompanyName);					
					object.put("tugMode", tugMode );
					object.put("tugboatShortName", tugboatShortName );					
					object.put("displacement", displacement );
												
					object.put("portWharfShortName", portWharfShortName);
					object.put("portHarbourShortName", portHarbourShortName);
					object.put("portRegionShortName", portRegionShortName);
					object.put("initFrom", Validator.isNotNull(initFrom) ? initFrom : "");
					object.put("shortInitFrom", Validator.isNotNull(shortInitFrom) ? shortInitFrom : "");
					
					data.put(object);
					CountTug += 1;
					
				}
			}
			
			result.put("reportDetails", data);

			String companyShortName = StringPool.BLANK;
			String tugboatCompanyName = StringPool.BLANK;
			if (Validator.isNotNull(tugboatCompanyCode)) {
				DmVmaTugboatCompany dmVmaTugboatCompany = DmVmaTugboatCompanyLocalServiceUtil
						.fetchByTugboatCompanyCode(tugboatCompanyCode);
				if (dmVmaTugboatCompany != null) {
					if (Validator.isNotNull(dmVmaTugboatCompany.getCompanyShortName())) {
						companyShortName = dmVmaTugboatCompany.getCompanyShortName();
					}							
					tugboatCompanyName = dmVmaTugboatCompany.getTugboatCompanyName();
				}
			}
			
			String tugboatShortName = StringPool.BLANK;
			String nameOfShip = StringPool.BLANK;
			String power = StringPool.BLANK;
			String unitPower = StringPool.BLANK;
			String loa = StringPool.BLANK;
			
			String gt = StringPool.BLANK;
			String nt = StringPool.BLANK;
			String dwt = StringPool.BLANK;
			String tugboatExpiredDate = StringPool.BLANK;
			
			if (Validator.isNotNull(shipCode)) {
				DmVmaTugboat dmVmaTugboat = DmVmaTugboatLocalServiceUtil
						.fetchByShipCode(shipCode);
				if (dmVmaTugboat != null) {
					if (Validator.isNotNull(dmVmaTugboat.getTugboatShortName())) {
						tugboatShortName = dmVmaTugboat.getTugboatShortName();
					}							
					nameOfShip = dmVmaTugboat.getShipName();					
					power = String.valueOf(dmVmaTugboat.getPower());
					unitPower = dmVmaTugboat.getUnitPower();
					loa = String.valueOf(dmVmaTugboat.getLoa());
					gt = String.valueOf(dmVmaTugboat.getGt());
					nt = String.valueOf(dmVmaTugboat.getNt());
					dwt = String.valueOf(dmVmaTugboat.getDwt());
					
					
										
					
					Date datetimeOfTugboatExpiredDate = (dmVmaTugboat.getTugboatExpiredDate() !=null ? dmVmaTugboat.getTugboatExpiredDate() : new Date());			
					if (dmVmaTugboat.getTugboatExpiredDate() != null) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(datetimeOfTugboatExpiredDate);

						int day = calendar.get(Calendar.DATE);

						int month = calendar.get(Calendar.MONTH) + 1;

						int year = calendar.get(Calendar.YEAR);

						int hour = calendar.get(Calendar.HOUR_OF_DAY);

						int minute = calendar.get(Calendar.MINUTE);

						tugboatExpiredDate = (day < 10 ? "0" + day : day)
								+ StringPool.SLASH
								+ (month < 10 ? "0" + month : month)
								+ StringPool.SLASH + year ;
					}
					result.put("tugboatShortName", tugboatShortName);
					result.put("nameOfShip", nameOfShip);
					result.put("power", power);
					result.put("unitPower", unitPower);
					
					result.put("loa", loa);
					result.put("gt", gt);
					result.put("nt", nt);
					result.put("dwt", dwt);
					result.put("tugboatExpiredDate", tugboatExpiredDate);
				}
				
			}
			String maritimeNameVN = DmMaritimeLocalServiceUtil.getByMaritimeCode(maritimeCode).getMaritimeNameVN();			
			result.put("maritimeNameVN", maritimeNameVN);
						
			result.put("companyShortName", companyShortName);
			result.put("tugboatCompanyName", tugboatCompanyName);
			result.put("countTug", CountTug);
			
			result.put("maritimeCode", maritimeCode);
			result.put("reportPeriod", reportPeriod);
			result.put("reportYear", reportYear);
			result.put("reportMonth", reportMonth);
			result.put("fromDate", reportDateFrom);
			result.put("toDate", reportDateTo);
			result.put("reportUser", reportUser);
			
			
			
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}

		return result;
	}

	public JSONObject findSoLuotHoaTieuDanTau_VmaItinerarySchedule_VmaScheduleShifting(
			String maritimeCode, String pilotCompanyCode, String pilotCode, String pilotTurn, String reportUser, 
			String reportPeriod, String reportYear, String reportMonth,
			String fromDate, String toDate, int start, int end)
			throws SystemException {

		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray data = JSONFactoryUtil.createJSONArray();
		
		try {
			

			StringBuilder query = new StringBuilder();
						
			query.append("Select distinct ");
			query.append("ItineraryNo, ");
			query.append("NameOfShip, ");
			query.append("NoticeShipType, ");
			query.append("TimeOfArrival, ");
			query.append("TimeOfDeparture, ");
			query.append("Hoa_tieu, ");
			query.append("PilotCompanyCode, ");
			query.append("Hoa_tieu_viet_tat, ");
			query.append("PilotCode, ");
			query.append("PilotName, ");
			query.append("PilotCompanyName, ");
			query.append("PilotCertificateNo, ");
			query.append("PilotCertificateDate, ");
			query.append("PilotExpiredDate, ");
			query.append("PilotNo, ");
			query.append("PilotFinishAt, ");
			query.append("GT, ");
			query.append("DWT, ");
			query.append("LOA, ");
			query.append("ShownDraftxF, ");
			query.append("ShownDraftxA, ");
			query.append("PortWharfShortName,");
			query.append("PortHarbourShortName,");
			query.append("PortRegionShortName,");			
			query.append("PortRegionName,");
			query.append("PortRegionCode,");
			query.append("PortHarbourNameVN,");
			query.append("PortHarbourCode,");
			query.append("PortWharfNameVN,");
			query.append("PortWharfCode,");
			query.append("InitFrom, ");
			query.append("shortInitFrom, ");
			query.append("itineraryScheduleId ");

			query.append(" from ( ");
			query.append(" (Select vvv.itineraryScheduleId, A2.ItineraryNo, A2.NameOfShip, ");
			query.append(" A2.NoticeShipType, A2.TimeOfArrival, A2.TimeOfDeparture, vvv.Hoa_tieu, vvv.PilotCompanyCode, vvv.Hoa_tieu_viet_tat, ");
			query.append(" A0.PilotCode, A0.PilotName, vvv.PilotCompanyName, A0.PilotCertificateNo, A0.PilotCertificateDate, A0.PilotExpiredDate, A0.PilotNo, ");
			query.append(" CASE NOTICESHIPTYPE WHEN 4 THEN TimeOfDeparture WHEN 2 THEN TimeOfDeparture ELSE TimeOfArrival  END AS PilotFinishAt, ");
			query.append("A2.GT,");
			query.append("A2.DWT,");
			query.append("A2.LOA,");
			query.append("A2.ShownDraftxF,");
			query.append("A2.ShownDraftxA,");
			query.append("A5.PortWharfShortName,");
			query.append("A4.PortHarbourShortName,");
			query.append("A3.PortRegionShortName,");			
			query.append("A3.PortRegionName,");
			query.append("A3.PortRegionCode,");
			query.append("A4.PortHarbourNameVN,");
			query.append("A4.PortHarbourCode,");
			query.append("A5.PortWharfNameVN,");
			query.append("A5.PortWharfCode,");
			query.append(" A2.InitFrom,");
			query.append(" A2.initFrom AS shortInitFrom ");
			query.append(" from vma_itinerary_schedule as A2 ");
			query.append(" LEFT JOIN dm_port_harbour AS A4 ON A2.PortHarbourCode = A4.PortHarbourCode");
			query.append(" LEFT JOIN dm_port_region AS A3 ON A4.PortRegion = A3.PortRegionCode LEFT JOIN dm_port_wharf AS A5 ON A2.PortWharfCode = A5.PortWharfCode");
			query.append(" LEFT JOIN (select v1.ItineraryNo, v1.itineraryScheduleId , GROUP_CONCAT('<br>',v2.PilotCompanyCode) as Danh_sach_congty_hoatieu, GROUP_CONCAT('<br>',v2.PilotCode) as Danh_sach_hoatieu, GROUP_CONCAT('<br>',v2.PilotName) as Hoa_tieu, ");
			query.append(" Case ISNULL(GROUP_CONCAT('<br>', Case ISNULL(v3.PilotShortName)  when 0 then (Case LENGTH(TRIM(v3.PilotShortName))  when 0 then v2.PilotName else v3.PilotShortName end) else v2.PilotName end )) "); 
			query.append(" when 0 then GROUP_CONCAT('<br>', Case ISNULL(v3.PilotShortName)  when 0 then (Case LENGTH(TRIM(v3.PilotShortName))  when 0 then v2.PilotName else v3.PilotShortName end) else v2.PilotName end ) ");
			query.append(" else GROUP_CONCAT('<br>', v2.PilotName) end AS Hoa_tieu_viet_tat,  ");
			query.append(" v2.PilotCompanyCode, v2.PilotCompanyName, v2.PilotCode from (Select * from vma_schedule_pilot_list ORDER BY itineraryScheduleId, PilotCategoryCode ASC) AS v2 LEFT JOIN vma_schedule_pilot as v1 on v1.ItineraryNo = v2.ItineraryNo and v1.sequenceNo = v2.sequenceNo ");
			if (Validator.isNotNull(pilotCompanyCode)) {
				query.append(" and v2.PilotCompanyCode = '" + pilotCompanyCode + "'");
			}
			query.append(" LEFT JOIN dm_vma_pilot AS v3 ON v3.PilotCode = v2.PilotCode where v1.NoticeShipType IN (1,2) ");

			query.append(" GROUP BY v1.ItineraryNo, v1.itineraryScheduleId) as vvv on A2.ItineraryNo = vvv.ItineraryNo and (vvv.itineraryScheduleId = A2.ID and A2.NoticeShipType in (1,2)) and vvv.Hoa_tieu is not null ");
			query.append(" LEFT JOIN dm_vma_pilot AS A0 ON A0.ID < 0  ");
			if (Validator.isNotNull(pilotCode)) {
				query.append(" OR A0.PilotCode = '" + pilotCode + "'");
			}


			query.append(" where 1=1 ");
			query.append(" and A2.NoticeShipType IN (1, 2) ");
			
			if (Validator.isNotNull(pilotCompanyCode)) {
				query.append(" and LOCATE('" + pilotCompanyCode + "'" + ", vvv.Danh_sach_congty_hoatieu) > 0 ");
			}
			if (Validator.isNotNull(pilotCode)) {
				query.append(" and LOCATE('" + pilotCode + "'" + ", vvv.Danh_sach_hoatieu) > 0 ");
			}
					 
			query.append(" ORDER BY vvv.itineraryScheduleId ");
			query.append(" ) ");

			query.append(" UNION ( ");
			query.append(" Select A1.itineraryScheduleId, A2.ItineraryNo, A2.NameOfShip, ");
			query.append(" A2.NoticeShipType, A2.TimeOfArrival, A2.TimeOfDeparture, vvv.Hoa_tieu, vvv.PilotCompanyCode, vvv.Hoa_tieu_viet_tat, ");
			query.append(" A0.PilotCode, A0.PilotName, vvv.PilotCompanyName, A0.PilotCertificateNo, A0.PilotCertificateDate, A0.PilotExpiredDate, A0.PilotNo, ");
			query.append(" CASE NOTICESHIPTYPE WHEN 4 THEN TimeOfDeparture WHEN 2 THEN TimeOfDeparture ELSE TimeOfArrival  END AS PilotFinishAt, ");
			query.append("A2.GT,");
			query.append("A2.DWT,");
			query.append("A2.LOA,");
			query.append("A2.ShownDraftxF,");
			query.append("A2.ShownDraftxA,");
			query.append("A5.PortWharfShortName,");
			query.append("A4.PortHarbourShortName,");
			query.append("A3.PortRegionShortName,");			
			query.append("A3.PortRegionName,");
			query.append("A3.PortRegionCode,");
			query.append("A4.PortHarbourNameVN,");
			query.append("A4.PortHarbourCode,");
			query.append("A5.PortWharfNameVN,");
			query.append("A5.PortWharfCode,");
			query.append(" A2.InitFrom, ");
			query.append("CONCAT(");
			query.append("(Select Case ISNULL(PortHarbourShortName)  when 0 then PortHarbourShortName else PortHarbourNameVN end as Harbour from dm_port_harbour where PortHarbourCode = A1.AnchoringPortHarbourCode),");
			query.append("', ',");
			query.append("(Select Case ISNULL(PortWharfShortName)  when 0 then PortWharfShortName else PortWharfNameVN end as Wharf  from dm_port_wharf where PortWharfCode = A1.AnchoringPortWharfCode)) ");
			query.append("AS shortInitFrom ");			
			query.append(" FROM vma_schedule_shifting AS A1  ");
			query.append(" INNER JOIN vma_itinerary_schedule AS A2 ON A1.ItineraryNo = A2.ItineraryNo and A1.itineraryScheduleId = A2.ID "); 
			query.append(" LEFT JOIN dm_port_harbour AS A4 ON A2.PortHarbourCode = A4.PortHarbourCode");
			query.append(" LEFT JOIN dm_port_region AS A3 ON A4.PortRegion = A3.PortRegionCode LEFT JOIN dm_port_wharf AS A5 ON A2.PortWharfCode = A5.PortWharfCode");
			query.append(" LEFT JOIN (select v1.ItineraryNo, v1.itineraryScheduleId , GROUP_CONCAT('<br>',v2.PilotCompanyCode) as Danh_sach_congty_hoatieu, GROUP_CONCAT('<br>',v2.PilotCode) as Danh_sach_hoatieu, GROUP_CONCAT('<br>',v2.PilotName) as Hoa_tieu,  ");
			query.append(" Case ISNULL(GROUP_CONCAT('<br>', Case ISNULL(v3.PilotShortName)  when 0 then (Case LENGTH(TRIM(v3.PilotShortName))  when 0 then v2.PilotName else v3.PilotShortName end) else v2.PilotName end )) "); 
			query.append(" when 0 then GROUP_CONCAT('<br>', Case ISNULL(v3.PilotShortName)  when 0 then (Case LENGTH(TRIM(v3.PilotShortName))  when 0 then v2.PilotName else v3.PilotShortName end) else v2.PilotName end ) ");
			query.append(" else GROUP_CONCAT('<br>', v2.PilotName) end AS Hoa_tieu_viet_tat,  ");
			query.append(" v2.PilotCompanyCode,  v2.PilotCompanyName, v2.PilotCode from (Select * from vma_schedule_pilot_list ORDER BY itineraryScheduleId, PilotCategoryCode ASC) AS v2 LEFT JOIN vma_schedule_pilot as v1 on v1.ItineraryNo = v2.ItineraryNo and v1.sequenceNo = v2.sequenceNo ");
			if (Validator.isNotNull(pilotCompanyCode)) {
				query.append(" and v2.PilotCompanyCode = '" + pilotCompanyCode + "'");
			}

			query.append(" LEFT JOIN dm_vma_pilot AS v3 ON v3.PilotCode = v2.PilotCode where NoticeShipType IN (4) ");
											
			query.append(" GROUP BY v1.ItineraryNo, v1.itineraryScheduleId) as vvv on A2.ItineraryNo = vvv.ItineraryNo and vvv.itineraryScheduleId = A1.ID and vvv.Hoa_tieu is not null ");
			query.append(" LEFT JOIN dm_vma_pilot AS A0 ON A0.ID < 0  ");
			if (Validator.isNotNull(pilotCode)) {
				query.append(" OR A0.PilotCode = '" + pilotCode + "'");
			}

			query.append(" where 1=1 ");
			if (Validator.isNotNull(pilotCompanyCode)) {
				query.append(" and LOCATE('" + pilotCompanyCode + "'" + ", vvv.Danh_sach_congty_hoatieu) > 0 ");
			}
			if (Validator.isNotNull(pilotCode)) {
				query.append(" and LOCATE('" + pilotCode + "'" + ", vvv.Danh_sach_hoatieu) > 0 ");
			}
			 
			query.append(" ORDER BY vvv.itineraryScheduleId ");
			query.append(" ) ");
			query.append(" ) as LuotDanTau  WHERE 1=1 ");
			
		
			String reportDateFrom = StringPool.BLANK;
			String reportDateTo = StringPool.BLANK;
			if (Validator.isNotNull(fromDate) && Validator.isNotNull(toDate)
					&& !fromDate.isEmpty() && !toDate.isEmpty()) {
				Date f_date = null;
				Date t_date = null;
				try {
					f_date = FormatData.formatDateShort.parse(fromDate);
					t_date = FormatData.formatDateShort.parse(toDate);
				} catch (Exception e) {
					log.error(e.getMessage());
				}

				if (f_date != null && t_date != null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(f_date);
					String f_strDate = calendar.get(Calendar.YEAR) + "-"
							+ (calendar.get(Calendar.MONTH) + 1) + "-"
							+ calendar.get(Calendar.DATE);
					
					reportDateFrom = 						
							calendar.get(Calendar.DATE) + StringPool.SLASH + (calendar.get(Calendar.MONTH) + 1) + StringPool.SLASH
							+ calendar.get(Calendar.YEAR);
					
					calendar.setTime(t_date);
					String t_strDate = calendar.get(Calendar.YEAR) + "-"
							+ (calendar.get(Calendar.MONTH) + 1) + "-"
							+ calendar.get(Calendar.DATE);
					
					reportDateTo = 						
							calendar.get(Calendar.DATE) + StringPool.SLASH + (calendar.get(Calendar.MONTH) + 1) + StringPool.SLASH
							+ calendar.get(Calendar.YEAR);
					
					if (fromDate.length() <= 16 && toDate.length() <= 16) {
						String lastFiveDigitsFromDate = fromDate.substring(fromDate.length() - 5 );
						String lastFiveDigitsToDate = toDate.substring(toDate.length() - 5 );
						query.append(" AND PilotFinishAt BETWEEN '"
								+ f_strDate + " " + lastFiveDigitsFromDate + ":00'");
						query.append(" AND '" + t_strDate + " " + lastFiveDigitsToDate + ":00'  ");
					} else {
						query.append(" AND PilotFinishAt BETWEEN '"
								+ f_strDate + " 00:00:00'");
						query.append(" AND '" + t_strDate + " 23:59:59'  ");
					}
					
				}
			}

			query.append(" ORDER BY PilotFinishAt ASC, PilotCompanyCode ASC, PilotCode ASC  ");
			log.info("=========findSoLuotDanTau_DmVmaPilot_VmaItinerarySchedule_VmaScheduleShifting======== "
					+ query.toString());
						
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString())
					.firstResult(start).maxResults(end - start).entityClass(Object.class).build();

			

			Iterator<Object[]> itr =queryFactory2.getResultList(builder).iterator();
			int CountTug = 0;
			if (itr.hasNext()) {
				while (itr.hasNext()) {
					Object[] objects = itr.next();

					String itineraryNo = String.valueOf(objects[0]);

					String nameOfShip = String.valueOf(objects[1]);
					String noticeShipType = String.valueOf(objects[2]);

					// String TimeOfArrival = String.valueOf(objects[3]);
					// String TimeOfDeparture = String.valueOf(objects[4]);
					
					String hoa_tieu = String.valueOf(objects[5]);
					pilotCompanyCode = String.valueOf(objects[6]);

					String hoa_tieu_viet_tat = String.valueOf(objects[7]);
					pilotCode = String.valueOf(objects[8]);

					String pilotName = String.valueOf(objects[9]);
					String pilotCompanyName = String.valueOf(objects[10]);
					String pilotCertificateNo = String.valueOf(objects[11]);
					String pilotCertificateDate = String.valueOf(objects[12]);
					String pilotExpiredDate = String.valueOf(objects[13]);
					String pilotNo = String.valueOf(objects[14]);
					// String PilotFinishAt = String.valueOf(objects[15]);
					
					double gt = GetterUtil.getDouble(objects[16]);
					double dwt = GetterUtil.getDouble(objects[17]);
					double loa = GetterUtil.getDouble(objects[18]);
					double shownDraftxF = GetterUtil.getDouble(objects[19]);
					double shownDraftxA = GetterUtil.getDouble(objects[20]);
					
					String portRegionName = String.valueOf(objects[24]);
					String portHarbourNameVN = String.valueOf(objects[26]);
					String portWharfNameVN = String.valueOf(objects[28]);
					String initFrom = String.valueOf(objects[30]);
					String shortInitFrom = String.valueOf(objects[31]);
					
					Date datetimeOfPilotFinish = (Date) objects[15];

					String timeOfPilotFinish = StringPool.BLANK;
					String timeHHMMOfPilotFinish = StringPool.BLANK;
					String dayOfPilotFinish = StringPool.BLANK;
					if (datetimeOfPilotFinish != null) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(datetimeOfPilotFinish);

						int day = calendar.get(Calendar.DATE);

						int month = calendar.get(Calendar.MONTH) + 1;

						int year = calendar.get(Calendar.YEAR);

						int hour = calendar.get(Calendar.HOUR_OF_DAY);

						int minute = calendar.get(Calendar.MINUTE);

						timeOfPilotFinish = (day < 10 ? "0" + day : day)
								+ StringPool.SLASH
								+ (month < 10 ? "0" + month : month)
								+ StringPool.SLASH + year + " "
								+ (hour < 10 ? "0" + hour : hour) + ":"
								+ (minute < 10 ? "0" + minute : minute);
						timeHHMMOfPilotFinish = (hour < 10 ? "0" + hour : hour) + ":"
								+ (minute < 10 ? "0" + minute : minute);
						dayOfPilotFinish = (day < 10 ? "0" + day : day)
								+ StringPool.SLASH
								+ (month < 10 ? "0" + month : month)
								+ StringPool.SLASH + year;
					}
					
					Date datetimeOfDeparture = (Date) objects[4];

					String timeOfDeparture = StringPool.BLANK;
					String timeHHMMOfDeparture = StringPool.BLANK;
					String dayOfDeparture = StringPool.BLANK;
					if (datetimeOfDeparture != null) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(datetimeOfDeparture);

						int day = calendar.get(Calendar.DATE);

						int month = calendar.get(Calendar.MONTH) + 1;

						int year = calendar.get(Calendar.YEAR);

						int hour = calendar.get(Calendar.HOUR_OF_DAY);

						int minute = calendar.get(Calendar.MINUTE);

						timeOfDeparture = (day < 10 ? "0" + day : day)
								+ StringPool.SLASH
								+ (month < 10 ? "0" + month : month)
								+ StringPool.SLASH + year + " "
								+ (hour < 10 ? "0" + hour : hour) + ":"
								+ (minute < 10 ? "0" + minute : minute);
						timeHHMMOfDeparture = (hour < 10 ? "0" + hour : hour) + ":"
								+ (minute < 10 ? "0" + minute : minute);
						dayOfDeparture = (day < 10 ? "0" + day : day)
								+ StringPool.SLASH
								+ (month < 10 ? "0" + month : month)
								+ StringPool.SLASH + year;
					}

					Date datetimeOfArrival = (Date) objects[3];

					String timeOfArrival = StringPool.BLANK;
					String timeHHMMOfArrival = StringPool.BLANK;
					String dayOfArrival = StringPool.BLANK;

					if (datetimeOfArrival != null) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(datetimeOfArrival);

						int day = calendar.get(Calendar.DATE);

						int month = calendar.get(Calendar.MONTH) + 1;

						int year = calendar.get(Calendar.YEAR);

						int hour = calendar.get(Calendar.HOUR_OF_DAY);

						int minute = calendar.get(Calendar.MINUTE);

						timeOfArrival = (day < 10 ? "0" + day : day)
								+ StringPool.SLASH
								+ (month < 10 ? "0" + month : month)
								+ StringPool.SLASH + year + " "
								+ (hour < 10 ? "0" + hour : hour) + ":"
								+ (minute < 10 ? "0" + minute : minute);
						timeHHMMOfArrival = (hour < 10 ? "0" + hour : hour) + ":"
								+ (minute < 10 ? "0" + minute : minute);
						dayOfArrival = (day < 10 ? "0" + day : day)
								+ StringPool.SLASH
								+ (month < 10 ? "0" + month : month)
								+ StringPool.SLASH + year ;
					}

					
					String portWharfShortName = Validator.isNotNull(String.valueOf(objects[21])) ? String.valueOf(objects[21]) :  portWharfNameVN;
					String portHarbourShortName = Validator.isNotNull(String.valueOf(objects[22])) ? String.valueOf(objects[22]) : portHarbourNameVN;
					String portRegionShortName = Validator.isNotNull(String.valueOf(objects[23])) ? String.valueOf(objects[23]) : portRegionName;
					
					JSONObject object = JSONFactoryUtil.createJSONObject();

					object.put("itineraryNo", itineraryNo);
					object.put("nameOfShip", nameOfShip);
					object.put("gt", gt);
					object.put("dwt", dwt);
					object.put("loa", loa);
					object.put("maxDraft", shownDraftxF + "/" + shownDraftxA);
					object.put("shownDraftxA", shownDraftxA);
					object.put("shownDraftxF", shownDraftxF);
					object.put("noticeShipType", noticeShipType);
					object.put("hoa_tieu", hoa_tieu);
					object.put("pilotCompanyCode", pilotCompanyCode);
					object.put("hoa_tieu_viet_tat", hoa_tieu_viet_tat );
					object.put("pilotCode", pilotCode );
					object.put("pilotName", pilotName );
					object.put("timeOfArrival", timeOfArrival);
					object.put("dayOfArrival", dayOfArrival);
					object.put("timeHHMMOfArrival", timeHHMMOfArrival);
					object.put("timeOfDeparture", timeOfDeparture);
					object.put("dayOfDeparture", dayOfDeparture);
					object.put("timeHHMMOfDeparture", timeHHMMOfDeparture);
					object.put("timeOfPilotFinish", timeOfPilotFinish);
					object.put("dayOfPilotFinish", dayOfPilotFinish);
					object.put("timeHHMMOfPilotFinish", timeHHMMOfPilotFinish);
					object.put("pilotCompanyName", pilotCompanyName );
					object.put("pilotCertificateNo", pilotCertificateNo );
					object.put("pilotNo", pilotNo);
					object.put("pilotCertificateDate", pilotCertificateDate );
					object.put("pilotExpiredDate", pilotExpiredDate );
					
					object.put("portWharfShortName", portWharfShortName);
					object.put("portHarbourShortName", portHarbourShortName);
					object.put("portRegionShortName", portRegionShortName);
					
					String pilotList = String.valueOf(objects[5]);
					String shortPilotList = String.valueOf(objects[7]);
					
					String companyShortName = StringPool.BLANK;
					
					if (Validator.isNotNull(pilotCompanyCode)) {
						DmVmaPilotCompany dmVmaPilotCompany = DmVmaPilotCompanyLocalServiceUtil
								.fetchByPilotCompanyCode(pilotCompanyCode);
						if (dmVmaPilotCompany != null) {
							if (Validator.isNotNull(dmVmaPilotCompany.getCompanyShortName())) {
								companyShortName = " -"
										+ dmVmaPilotCompany.getCompanyShortName();
							}							
							pilotCompanyName = " -"
									+ dmVmaPilotCompany.getPilotCompanyName();
						}
						if (Validator.isNotNull(companyShortName)) {
							pilotList += companyShortName;
							shortPilotList += companyShortName;
						} else if (Validator.isNotNull(pilotCompanyName)) {
							pilotList += pilotCompanyName;
							shortPilotList += pilotCompanyName;
						}
					}
					/*// Chưa dùng đến
					if (Validator.isNotNull(shipAgencyCode)) {
						DmShipAgency objshipAgency =  DmShipAgencyLocalServiceUtil.fetchByShipAgencyCode(shipAgencyCode);
						if ((Validator.isNotNull(objshipAgency) && Validator.isNotNull(objshipAgency.getShipAgencyShortName()))) {
							shipAgencyShortName = objshipAgency.getShipAgencyShortName();
						}
					}*/
					object.put("pilotList", Validator.isNotNull(pilotList) ? pilotList : "");					
					object.put("shortPilotList", Validator.isNotNull(shortPilotList) ? shortPilotList : "");
					object.put("initFrom", Validator.isNotNull(initFrom) ? initFrom : "");
					object.put("shortInitFrom", Validator.isNotNull(shortInitFrom) ? shortInitFrom : "");
					
					data.put(object);
					CountTug += 1;
					
				}
			}

			result.put("reportDetails", data);
						
			String companyShortName = StringPool.BLANK;
			String pilotCompanyName = StringPool.BLANK;
			if (Validator.isNotNull(pilotCompanyCode)) {
				DmVmaPilotCompany dmVmaPilotCompany = DmVmaPilotCompanyLocalServiceUtil
						.fetchByPilotCompanyCode(pilotCompanyCode);
				if (dmVmaPilotCompany != null) {
					if (Validator.isNotNull(dmVmaPilotCompany.getCompanyShortName())) {
						companyShortName = dmVmaPilotCompany.getCompanyShortName();
					}							
					pilotCompanyName = dmVmaPilotCompany.getPilotCompanyName();
				}
			}
			
			String pilotShortName = StringPool.BLANK;
			String pilotName = StringPool.BLANK;
			String pilotCertificateNo = StringPool.BLANK;
			String pilotCertificateDate = StringPool.BLANK;
			String pilotExpiredDate = StringPool.BLANK;
			String pilotNo = StringPool.BLANK;
			String pilotCategoryCode = StringPool.BLANK;
			String pilotCategoryName = StringPool.BLANK;
			
			if (Validator.isNotNull(pilotCode)) {
				DmVmaPilot dmVmaPilot = DmVmaPilotLocalServiceUtil
						.fetchbyPilotCode(pilotCode);
				if (dmVmaPilot != null) {
					if (Validator.isNotNull(dmVmaPilot.getPilotShortName())) {
						pilotShortName = dmVmaPilot.getPilotShortName();
					}							
					pilotName = dmVmaPilot.getPilotName();
					pilotNo = dmVmaPilot.getPilotNo();
					pilotCertificateNo = dmVmaPilot.getPilotCertificateNo();
					pilotCategoryCode = dmVmaPilot.getPilotCategoryCode();
					
					if (dmVmaPilot.getPilotCategoryCode() != null) {
						pilotCategoryName = DmVmaPilotCategoryLocalServiceUtil.fetchByPilotCategoryCode(pilotCategoryCode).getPilotCategoryName();
					}
					
					Date datetimeOfPilotCertificateDate = (dmVmaPilot.getPilotCertificateDate() !=null ? dmVmaPilot.getPilotCertificateDate() : new Date());			
					if (dmVmaPilot.getPilotCertificateDate() != null) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(datetimeOfPilotCertificateDate);

						int day = calendar.get(Calendar.DATE);

						int month = calendar.get(Calendar.MONTH) + 1;

						int year = calendar.get(Calendar.YEAR);

						int hour = calendar.get(Calendar.HOUR_OF_DAY);

						int minute = calendar.get(Calendar.MINUTE);

						pilotCertificateDate = (day < 10 ? "0" + day : day)
								+ StringPool.SLASH
								+ (month < 10 ? "0" + month : month)
								+ StringPool.SLASH + year ;
					}
					
					Date datetimeOfPilotExpiredDate = (dmVmaPilot.getPilotExpiredDate() !=null ? dmVmaPilot.getPilotExpiredDate() : new Date());			
					if (dmVmaPilot.getPilotExpiredDate() != null) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(datetimeOfPilotExpiredDate);

						int day = calendar.get(Calendar.DATE);

						int month = calendar.get(Calendar.MONTH) + 1;

						int year = calendar.get(Calendar.YEAR);

						int hour = calendar.get(Calendar.HOUR_OF_DAY);

						int minute = calendar.get(Calendar.MINUTE);

						pilotExpiredDate = (day < 10 ? "0" + day : day)
								+ StringPool.SLASH
								+ (month < 10 ? "0" + month : month)
								+ StringPool.SLASH + year ;
					}
					result.put("pilotShortName", pilotShortName);
					result.put("pilotName", pilotName);
					result.put("pilotNo", pilotNo);
					result.put("pilotCategoryName", pilotCategoryName);
					result.put("pilotCertificateNo", pilotCertificateNo);
					result.put("pilotCertificateDate", pilotCertificateDate);
					result.put("pilotExpiredDate", pilotExpiredDate);
				}
				
			}
			String maritimeNameVN = DmMaritimeLocalServiceUtil.getByMaritimeCode(maritimeCode).getMaritimeNameVN();			
			result.put("maritimeNameVN", maritimeNameVN);
						
			result.put("companyShortName", companyShortName);
			result.put("pilotCompanyName", pilotCompanyName);
			result.put("countTug", CountTug);
			
			result.put("maritimeCode", maritimeCode);
			result.put("reportPeriod", reportPeriod);
			result.put("reportYear", reportYear);
			result.put("reportMonth", reportMonth);
			result.put("fromDate", reportDateFrom);
			result.put("toDate", reportDateTo);
			result.put("reportUser", reportUser);

		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}

		return result;
	}


	public JSONObject findThongKeTauLaiDat(
			String maritimeCode, String tugboatCompanyCode, String shipCode,  String reportUser,
			String reportPeriod, String fromDate, String toDate, int xemBK, int xemBK_Thutuc )
			throws SystemException {



		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray data = JSONFactoryUtil.createJSONArray();

		try {

			StringBuilder query = new StringBuilder();


			query.append("Select distinct ");
			query.append("ItineraryNo, ");
			query.append("itineraryScheduleId, ");
			query.append("Danh_sach_congty_tau_lai, ");
			query.append("Danh_sach_tau_lai, ");
			query.append("Tau_lai, ");
			query.append("Tau_lai_viet_tat, ");
			query.append("TugDateFrom, ");
			query.append("TugDateTo, ");
			query.append("ShipCode, ");
			query.append("ShipName, ");
			query.append("TugboatCompanyCode, ");
			query.append("TugboatCompanyName, ");
			query.append("TugboatShortName, ");
			query.append("TugMode, ");
			query.append("GT, ");
			query.append("Power, ");
			query.append("UnitPower, ");
			query.append("countTugDays ");

			query.append(" from ( ");


			query.append(" (select v1.ItineraryNo, A1.itineraryScheduleId , GROUP_CONCAT('<br>',v2.TugboatCompanyCode) as Danh_sach_congty_tau_lai, GROUP_CONCAT('<br>',v2.ShipCode) as Danh_sach_tau_lai, GROUP_CONCAT('<br>',v2.ShipName) as Tau_lai,   Case ISNULL(GROUP_CONCAT('<br>', Case ISNULL(v3.TugboatShortName)  when 0 then (Case LENGTH(TRIM(v3.TugboatShortName))  when 0 then v2.ShipName else v3.TugboatShortName end) else v2.ShipName end ))  when 0 then GROUP_CONCAT('<br>', Case ISNULL(v3.TugboatShortName)  when 0 then (Case LENGTH(TRIM(v3.TugboatShortName))  when 0 then v2.ShipName else v3.TugboatShortName end) else v2.ShipName end )   else GROUP_CONCAT('<br>', v2.ShipName) end AS Tau_lai_viet_tat, v1.TugDateFrom, v1.TugDateTo, v2.ShipCode, v2.ShipName,   v2.TugboatCompanyCode, v2.TugboatCompanyName, v2.TugboatShortName, v2.TugMode, v2.GT, v2.Power, v2.UnitPower, DATEDIFF(v1.TugDateTo, v1.TugDateFrom)+1 as countTugDays  from vma_schedule_tugboat as v1 ");
			query.append(" INNER JOIN vma_schedule_tugboat_list as v2 on v1.ItineraryNo = v2.ItineraryNo and v1.sequenceNo = v2.sequenceNo AND v1.NoticeShipType IN (4)   ");
			if (Validator.isNotNull(tugboatCompanyCode)) {
				query.append(" and v2.TugboatCompanyCode = '" + tugboatCompanyCode + "'");
			}

			if (Validator.isNotNull(shipCode)) {
				query.append(" AND v2.ShipCode = '" + shipCode + "'");
			}

			query.append(" INNER JOIN vma_schedule_shifting As A1  ON A1.ItineraryNo = v1.ItineraryNo  and v1.itineraryScheduleId = A1.ID ");
			query.append(" LEFT JOIN dm_vma_tugboat AS v3 ON v3.ShipCode = v2.ShipCode and v3.ShipName like v2.ShipName where v1.NoticeShipType IN (4) ");
			query.append(" GROUP BY v1.ItineraryNo, v1.itineraryScheduleId,  v2.ShipCode ");
			query.append(" ) ");
			query.append(" UNION ( ");
			query.append(" select v1.ItineraryNo, v1.itineraryScheduleId , GROUP_CONCAT('<br>',v2.TugboatCompanyCode) as Danh_sach_congty_tau_lai, GROUP_CONCAT('<br>',v2.ShipCode) as Danh_sach_tau_lai, GROUP_CONCAT('<br>',v2.ShipName) as Tau_lai,   Case ISNULL(GROUP_CONCAT('<br>', Case ISNULL(v3.TugboatShortName)  when 0 then (Case LENGTH(TRIM(v3.TugboatShortName))  when 0 then v2.ShipName else v3.TugboatShortName end) else v2.ShipName end ))  when 0 then GROUP_CONCAT('<br>', Case ISNULL(v3.TugboatShortName)  when 0 then (Case LENGTH(TRIM(v3.TugboatShortName))  when 0 then v2.ShipName else v3.TugboatShortName end) else v2.ShipName end )   else GROUP_CONCAT('<br>', v2.ShipName) end AS Tau_lai_viet_tat, v1.TugDateFrom, v1.TugDateTo, v2.ShipCode, v2.ShipName,   v2.TugboatCompanyCode, v2.TugboatCompanyName, v2.TugboatShortName, v2.TugMode, v2.GT, v2.Power, v2.UnitPower, DATEDIFF(v1.TugDateTo, v1.TugDateFrom)+1 as countTugDays  from vma_schedule_tugboat as v1 ");
			query.append(" INNER JOIN vma_schedule_tugboat_list as v2 on v1.ItineraryNo = v2.ItineraryNo and v1.sequenceNo = v2.sequenceNo AND v1.NoticeShipType IN (1,2)   ");
			if (Validator.isNotNull(tugboatCompanyCode)) {
				query.append(" and v2.TugboatCompanyCode = '" + tugboatCompanyCode + "'");
			}

			if (Validator.isNotNull(shipCode)) {
				query.append(" AND v2.ShipCode = '" + shipCode + "'");
			}
			query.append(" INNER JOIN vma_itinerary_schedule As A2  ON A2.ItineraryNo = v1.ItineraryNo  and v1.itineraryScheduleId = A2.ID ");
			query.append(" LEFT JOIN dm_vma_tugboat AS v3 ON v3.ShipCode = v2.ShipCode and v3.ShipName like v2.ShipName where v1.NoticeShipType IN (1,2) ");
			query.append(" GROUP BY v1.ItineraryNo, v1.itineraryScheduleId,  v2.ShipCode ");
			query.append(" ) ) as LuotLaiDat Where 1=1  ");


			String fromNgayTrongNam = StringPool.BLANK;
			String toNgayTrongNam = StringPool.BLANK;

			String reportDateFrom = StringPool.BLANK;
			String reportDateTo = StringPool.BLANK;

			long timeStampFromDate = 0, timeStampToDate = 0;


			if (Validator.isNotNull(fromDate) && Validator.isNotNull(toDate)
					&& !fromDate.isEmpty() && !toDate.isEmpty()) {
				Date f_date = null;
				Date t_date = null;
				try {
					f_date = FormatData.formatDateShort3.parse(fromDate);
					t_date = FormatData.formatDateShort3.parse(toDate);

				} catch (Exception e) {
					log.error(e.getMessage());
				}

				if (f_date != null && t_date != null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(f_date);
					String f_strDate = calendar.get(Calendar.YEAR) + "-"
							+ (calendar.get(Calendar.MONTH) + 1) + "-"
							+ calendar.get(Calendar.DATE);

					reportDateFrom =
							calendar.get(Calendar.DATE) + StringPool.SLASH + (calendar.get(Calendar.MONTH) + 1) + StringPool.SLASH
									+ calendar.get(Calendar.YEAR);

					calendar.setTime(t_date);
					String t_strDate = calendar.get(Calendar.YEAR) + "-"
							+ (calendar.get(Calendar.MONTH) + 1) + "-"
							+ calendar.get(Calendar.DATE);

					reportDateTo =
							calendar.get(Calendar.DATE) + StringPool.SLASH + (calendar.get(Calendar.MONTH) + 1) + StringPool.SLASH
									+ calendar.get(Calendar.YEAR);

					if (fromDate.length() <= 16 && toDate.length() <= 16) {
						String lastFiveDigitsFromDate = fromDate.substring(fromDate.length() - 5 );
						String lastFiveDigitsToDate = toDate.substring(toDate.length() - 5 );
						query.append(" AND LuotLaiDat.TugDateTo BETWEEN '"
								+ f_strDate + " " + lastFiveDigitsFromDate + ":00'");
						query.append(" AND '" + t_strDate + " " + lastFiveDigitsToDate + ":00' ");

						timeStampFromDate = FormatData.formatDateShort.parse(f_strDate + " " + lastFiveDigitsFromDate + ":00'").getTime();
						timeStampToDate = FormatData.formatDateShort.parse(t_strDate + " " + lastFiveDigitsToDate + ":00' ").getTime();

					} else {
						query.append(" AND LuotLaiDat.TugDateTo BETWEEN '"
								+ f_strDate + " 00:00:00'");
						query.append(" AND '" + t_strDate + " 23:59:59' ");

						timeStampFromDate = FormatData.formatDateShort.parse(f_strDate + " 00:00:00'").getTime();
						timeStampToDate = FormatData.formatDateShort.parse(t_strDate + " 23:59:59' ").getTime();
					}

					fromNgayTrongNam = calendar.get(Calendar.YEAR) + "-" +"01" + "-" + "01";
					toNgayTrongNam = calendar.get(Calendar.YEAR) + "-" +"12" + "-" + "31";
				}
			}

			query.append(" ORDER BY LuotLaiDat.TugDateFrom, LuotLaiDat.itineraryScheduleId ");
			log.info("=========findThongKeTauLaiDat======== "
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true)
					.queryString(query.toString()).entityClass(Object.class).build();

			Iterator<Object[]> itr =queryFactory2.getResultList(builder).iterator();
			int CountTug = 0;

			if (itr.hasNext()) {
				while (itr.hasNext()) {
					Object[] objects = itr.next();


					String itineraryNo = String.valueOf(objects[0]);
					String itineraryScheduleId = String.valueOf(objects[1]);

					String Danh_sach_congty_tau_lai = String.valueOf(objects[2]);

					String Danh_sach_tau_lai = String.valueOf(objects[3]);
					String tau_lai = String.valueOf(objects[4]);
					String tau_lai_viet_tat = String.valueOf(objects[5]);

					shipCode = String.valueOf(objects[8]);
					String shipName = String.valueOf(objects[9]);

					tugboatCompanyCode = String.valueOf(objects[10]);
					String tugboatCompanyName = String.valueOf(objects[11]);

					String tugDateFrom = String.valueOf(objects[6]);
					String tugDateTo = String.valueOf(objects[7]);
					String tugboatShortName = String.valueOf(objects[12]);
					String tugMode = String.valueOf(objects[13]);

					double gt = GetterUtil.getDouble(objects[14]);
					double Power = GetterUtil.getDouble(objects[15]);
					String UnitPower = String.valueOf(objects[16]);

					String countTugDays = String.valueOf(objects[17]);
					long ngayKhongTinhPhi = countThongKeTauLaiDat_NgayKhongTinhPhi(itineraryScheduleId, maritimeCode, tugboatCompanyCode, shipCode,  fromDate, toDate, fromNgayTrongNam, toNgayTrongNam);
					if (ngayKhongTinhPhi > 0) {
						long ngayTinhPhi = GetterUtil.getLong(objects[17]) - ngayKhongTinhPhi;
						countTugDays = String.valueOf(ngayTinhPhi);
						CountTug += ngayTinhPhi;
					} else {
						long ngayTinhPhi = GetterUtil.getLong(objects[17]);
						CountTug += ngayTinhPhi;
					}


					Date datetimeOfTugDateFrom = (Date) objects[6];
					if (datetimeOfTugDateFrom != null) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(datetimeOfTugDateFrom);

						int day = calendar.get(Calendar.DATE);

						int month = calendar.get(Calendar.MONTH) + 1;

						int year = calendar.get(Calendar.YEAR);

						int hour = calendar.get(Calendar.HOUR_OF_DAY);

						int minute = calendar.get(Calendar.MINUTE);

						tugDateFrom = (day < 10 ? "0" + day : day)
								+ StringPool.SLASH
								+ (month < 10 ? "0" + month : month)
								+ StringPool.SLASH + year + " "
								+ (hour < 10 ? "0" + hour : hour) + ":"
								+ (minute < 10 ? "0" + minute : minute);
					}

					Date datetimeOfTugDateTo = (Date) objects[7];
					if (datetimeOfTugDateTo != null) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(datetimeOfTugDateTo);

						int day = calendar.get(Calendar.DATE);

						int month = calendar.get(Calendar.MONTH) + 1;

						int year = calendar.get(Calendar.YEAR);

						int hour = calendar.get(Calendar.HOUR_OF_DAY);

						int minute = calendar.get(Calendar.MINUTE);

						tugDateTo = (day < 10 ? "0" + day : day)
								+ StringPool.SLASH
								+ (month < 10 ? "0" + month : month)
								+ StringPool.SLASH + year + " "
								+ (hour < 10 ? "0" + hour : hour) + ":"
								+ (minute < 10 ? "0" + minute : minute);

					}

					String getDocumentaryCode = "";
					String getMakePayment = "";

					JSONObject object = JSONFactoryUtil.createJSONObject();

					object.put("itineraryNo", itineraryNo);
					object.put("itineraryScheduleId", itineraryScheduleId);
					object.put("gt", gt);
					object.put("Power", Power);
					object.put("UnitPower", UnitPower);
					object.put("countTugDays", countTugDays);

					object.put("tau_lai", tau_lai);
					object.put("tau_lai_viet_tat", tau_lai_viet_tat );
					object.put("Danh_sach_congty_tau_lai", Danh_sach_congty_tau_lai);
					object.put("Danh_sach_tau_lai", Danh_sach_tau_lai );

					object.put("tugDate", tugDateFrom );
					object.put("tugDateFrom", tugDateFrom );
					object.put("tugDateTo", tugDateTo );
					object.put("shipCode", shipCode );
					object.put("shipName", shipName );
					object.put("tugboatCompanyCode", tugboatCompanyCode);
					object.put("tugboatCompanyName", tugboatCompanyName);
					object.put("tugMode", tugMode );
					object.put("tugboatShortName", tugboatShortName );

					object.put("documentaryCode", getDocumentaryCode);
					object.put("makePayment", getMakePayment);

					data.put(object);


				}
			}

			result.put("vma_schedule_tugboat_list", data);

			String companyShortName = StringPool.BLANK;
			String tugboatCompanyName = StringPool.BLANK;
			if (Validator.isNotNull(tugboatCompanyCode)) {
				DmVmaTugboatCompany dmVmaTugboatCompany = DmVmaTugboatCompanyLocalServiceUtil
						.fetchByTugboatCompanyCode(tugboatCompanyCode);
				if (dmVmaTugboatCompany != null) {
					if (Validator.isNotNull(dmVmaTugboatCompany.getCompanyShortName())) {
						companyShortName = dmVmaTugboatCompany.getCompanyShortName();
					}
					tugboatCompanyName = dmVmaTugboatCompany.getTugboatCompanyName();
				}
			}

			String tugboatShortName = StringPool.BLANK;
			String nameOfShip = StringPool.BLANK;
			String power = StringPool.BLANK;
			String unitPower = StringPool.BLANK;
			String loa = StringPool.BLANK;

			String gt = StringPool.BLANK;
			String nt = StringPool.BLANK;
			String dwt = StringPool.BLANK;
			String tugboatExpiredDate = StringPool.BLANK;
			double gtconversion = 0.0;

			if (Validator.isNotNull(shipCode)) {
				DmVmaTugboat dmVmaTugboat = DmVmaTugboatLocalServiceUtil
						.fetchByShipCode(shipCode);
				if (dmVmaTugboat != null) {
					if (Validator.isNotNull(dmVmaTugboat.getTugboatShortName())) {
						tugboatShortName = dmVmaTugboat.getTugboatShortName();
					}
					nameOfShip = dmVmaTugboat.getShipName();
					power = String.valueOf(dmVmaTugboat.getPower());
					unitPower = dmVmaTugboat.getUnitPower();
					loa = String.valueOf(dmVmaTugboat.getLoa());
					gt = String.valueOf(dmVmaTugboat.getGt());
					nt = String.valueOf(dmVmaTugboat.getNt());
					dwt = String.valueOf(dmVmaTugboat.getDwt());

					if (dmVmaTugboat.getGt().doubleValue() > 0) {
						gtconversion = dmVmaTugboat.getGt().doubleValue();
					} else if (dmVmaTugboat.getDwt().doubleValue() > 0) {
						gtconversion = dmVmaTugboat.getDwt().doubleValue() / 1.5;
					} else if (dmVmaTugboat.getPower() > 0) {
						gtconversion = dmVmaTugboat.getPower() * 0.5;
					} else {
						gtconversion = 0.0;
					}


					Date datetimeOfTugboatExpiredDate = (dmVmaTugboat.getTugboatExpiredDate() !=null ? dmVmaTugboat.getTugboatExpiredDate() : new Date());
					if (dmVmaTugboat.getTugboatExpiredDate() != null) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(datetimeOfTugboatExpiredDate);

						int day = calendar.get(Calendar.DATE);

						int month = calendar.get(Calendar.MONTH) + 1;

						int year = calendar.get(Calendar.YEAR);

						int hour = calendar.get(Calendar.HOUR_OF_DAY);

						int minute = calendar.get(Calendar.MINUTE);

						tugboatExpiredDate = (day < 10 ? "0" + day : day)
								+ StringPool.SLASH
								+ (month < 10 ? "0" + month : month)
								+ StringPool.SLASH + year ;
					}
					result.put("tugboatShortName", tugboatShortName);
					result.put("nameOfShip", nameOfShip);
					result.put("power", power);
					result.put("unitPower", unitPower);

					result.put("loa", loa);
					result.put("gt", gt);
					result.put("nt", nt);
					result.put("dwt", dwt);
					result.put("tugboatExpiredDate", tugboatExpiredDate);
				}

			}
			String maritimeNameVN = DmMaritimeLocalServiceUtil.getByMaritimeCode(maritimeCode).getMaritimeNameVN();
			result.put("maritimeNameVN", maritimeNameVN);

			result.put("companyShortName", companyShortName);
			result.put("tugboatCompanyName", tugboatCompanyName);
			result.put("countTug", CountTug);

			Date date = new Date();
			Calendar cal = Calendar.getInstance(TimeZone.getDefault());
			cal.setTime(date);
			int reportYear = cal.get(Calendar.YEAR);
			int reportMonth = cal.get(Calendar.MONTH);

			result.put("maritimeCode", maritimeCode);
			result.put("reportPeriod", reportPeriod);
			result.put("reportYear", reportYear);
			result.put("reportMonth", reportMonth);
			result.put("fromDate", reportDateFrom);
			result.put("toDate", reportDateTo);
			result.put("reportUser", reportUser);

			int XBTPTDV = 0;
			int XBK = 1;
			int XEM_BANG_KE_THU_TUC = 0;
			int XEM_BANG_KE_KE_TOAN = 1;


			if (xemBK == XBTPTDV) {

				List<VmaScheduleTugboatList> vmaScheduleTugboatLists = new ArrayList<VmaScheduleTugboatList>();
				if (xemBK == XBTPTDV) {
					vmaScheduleTugboatLists = VmaScheduleTugboatListLocalServiceUtil
							.getByShipCode_MakePayment(shipCode, 0);
				} else if (xemBK == XBK) {
					if (xemBK_Thutuc == XEM_BANG_KE_THU_TUC) {
						vmaScheduleTugboatLists = VmaScheduleTugboatListLocalServiceUtil
								.getByShipCode_MakePayment(shipCode,
										XEM_BANG_KE_THU_TUC);
					} else if (xemBK_Thutuc == XEM_BANG_KE_KE_TOAN) {
						vmaScheduleTugboatLists = VmaScheduleTugboatListLocalServiceUtil
								.getByShipCode_MakePayment(shipCode,
										XEM_BANG_KE_KE_TOAN);
					}
				}


				double totalPayment = 0;
				totalPayment = gtconversion	* 100 * CountTug;

				// Add to TempDebitnote
				TempDebitnote tempDebitNote = new TempDebitnote();

				Timestamp timestamp = new Timestamp(
						System.currentTimeMillis());
				String debitnotenumber = String
						.valueOf(timestamp.getTime());

				tempDebitNote.setItineraryNo("KEYTAUDICHVU");
				tempDebitNote.setDocumentName(0L);
				tempDebitNote.setDocumentYear(reportYear);
				tempDebitNote.setFromdate(FormatData.formatDateShort.parse(fromDate));
				tempDebitNote.setTodate(FormatData.formatDateShort.parse(toDate));
				String reportby =reportUser;
				String division = reportby;
				tempDebitNote.setDivision(division);
				tempDebitNote.setReportby(reportby);
				tempDebitNote.setReportdate(new Date());
				tempDebitNote.setCorporationid(maritimeCode);
				tempDebitNote.setMariTimeCode(String.valueOf(DmMaritimeLocalServiceUtil.getByMaritimeCode(maritimeCode).getId()));
				tempDebitNote.setDocumentTypeCode("---");
				tempDebitNote.setCurrency("VND");
				tempDebitNote.setDescription("");
				tempDebitNote.setDebitnotenumber(debitnotenumber);

				tempDebitNote.setOrganization(maritimeNameVN);
				tempDebitNote.setTotalpayment(BigDecimal.valueOf(totalPayment));

				tempDebitNote = TempDebitNoteLocalServiceUtil
						.addTempDebitNote(tempDebitNote);
				try {
					result.put("tempDebitNote", VMAUtils.object2Json(
							tempDebitNote, TempDebitnote.class));

				} catch (Exception e) {

				}

				int debitnoteId = tempDebitNote.getId().intValue();

				// Add to VmaTransactionSlip
				VmaTransactionSlip vmaTransactionSlip = new VmaTransactionSlip();
				vmaTransactionSlip.setItineraryNo("KEYTAUDICHVU");
				long sequenceNo = ReportsBusinessUtils
						.taoGiaTriThamSo("KEYTAUDICHVU");
				vmaTransactionSlip.setSequenceNo(Integer.valueOf(String
						.valueOf(sequenceNo)));
				vmaTransactionSlip.setFromdate(FormatData.formatDateShort.parse(fromDate));
				vmaTransactionSlip.setTodate(FormatData.formatDateShort.parse(toDate));
				vmaTransactionSlip.setDebitnoteid(debitnoteId);
				vmaTransactionSlip.setDocumentaryCode(debitnotenumber);
				vmaTransactionSlip.setNameOfShip(vmaScheduleTugboatLists
						.get(0).getShipName());
				vmaTransactionSlip.setGt(vmaScheduleTugboatLists.get(0)
						.getGt());
				vmaTransactionSlip.setShipOwnerName(vmaScheduleTugboatLists
						.get(0).getTugboatCompanyName());
				vmaTransactionSlip
						.setShipOperationName(vmaScheduleTugboatLists
								.get(0).getTugboatCompanyName());
				vmaTransactionSlip.setPaymentAmount(totalPayment);
				vmaTransactionSlip.setVndTotalAmount(totalPayment);
				vmaTransactionSlip.setF1311Vnd(totalPayment);
				vmaTransactionSlip.setF1311Remarks(DanhMucUtils
						.encodeUTF8("100 * GT * Số ngày thực tế"));
				vmaTransactionSlip.setPaymentStatus(1);
				vmaTransactionSlip
						.setPortofAuthority(maritimeCode);
				vmaTransactionSlip.setAmountInWordsVnd(DanhMucUtils
						.convert(totalPayment, 1));


				vmaTransactionSlip = VmaTransactionSlipLocalServiceUtil
						.addVmaTransactionSlip(vmaTransactionSlip);
				try {
					result.put("vmaTransactionSlip", VMAUtils.object2Json(
							vmaTransactionSlip,
							VmaTransactionSlip.class));
				} catch (Exception e) {

				}

				// update makePayment for VmaScheduleTugboatList
				/*for (VmaScheduleTugboatList vmaScheduleTugboatList : vmaScheduleTugboatLists) {
					vmaScheduleTugboatList.setMakePayment(1);
					vmaScheduleTugboatList
							.setDocumentaryCode(debitnotenumber);

					VmaScheduleTugboatList tugboatList = VmaScheduleTugboatListLocalServiceUtil
							.updateVmaScheduleTugboatList(vmaScheduleTugboatList);


				}
				for (VmaScheduleTugboatList vmaScheduleTugboatList : vmaScheduleTugboatLists) {
					VmaScheduleTugboat vmaScheduleTugboat = VmaScheduleTugboatLocalServiceUtil
							.getByItineraryNo_SequenceNo(
									vmaScheduleTugboatList.getItineraryNo(),
									vmaScheduleTugboatList.getSequenceNo());
					if (vmaScheduleTugboat != null) {
						long tugDateTo = 0;
						try {
							tugDateTo = vmaScheduleTugboat.getTugDateTo()
									.getTime();
						} catch (Exception e) {
							// nothing to do
						}
						if (tugDateTo >= timeStampFromDate
								&& tugDateTo <= timeStampToDate) {
							totalPayment += vmaScheduleTugboatList.getGt()
									* 100 * arrayDay.keySet().size();
						}
					}
				}
				*
				*/

			}

		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
		}

		return result;

	}


	public long countThongKeTauLaiDat_NgayKhongTinhPhi(String itineraryScheduleId, String maritimeCode, String tugboatCompanyCode, String shipCode,
													   String fromDate, String toDate, String fromNgayTrongNam, String toNgayTrongNam) throws SystemException {


		try {
			StringBuilder query = new StringBuilder();



// Tinh số ngày dich vu theo thời gian thực
			query.append(" Select COUNT(days_lookup.NgayTrongNam) as total from  ");
			query.append(" (  ");
			query.append(" Select * from (  ");
			query.append(" (  ");
			query.append(" select v1.ItineraryNo, A1.itineraryScheduleId , GROUP_CONCAT('<br>',v2.TugboatCompanyCode) as Danh_sach_congty_tau_lai, GROUP_CONCAT('<br>',v2.ShipCode) as Danh_sach_tau_lai, GROUP_CONCAT('<br>',v2.ShipName) as Tau_lai,   v1.TugDateFrom, v1.TugDateTo from vma_schedule_tugboat as v1 ");
			query.append(" INNER JOIN vma_schedule_tugboat_list as v2 on v1.ItineraryNo = v2.ItineraryNo and v1.sequenceNo = v2.sequenceNo AND v1.NoticeShipType IN (4)    ");
			if (Validator.isNotNull(tugboatCompanyCode)) {
				query.append(" and v2.TugboatCompanyCode = '" + tugboatCompanyCode + "'");
			}

			if (Validator.isNotNull(shipCode)) {
				query.append(" AND v2.ShipCode = '" + shipCode + "'");
			}
			query.append(" INNER JOIN vma_schedule_shifting As A1  ON A1.ItineraryNo = v1.ItineraryNo  and v1.itineraryScheduleId = A1.ID ");
			query.append(" where v1.NoticeShipType IN (4)    ");
			query.append(" GROUP BY v1.ItineraryNo, v1.itineraryScheduleId,  v2.ShipCode ");
			query.append(" ) ");
			query.append(" UNION ( ");
			query.append(" select v1.ItineraryNo, v1.itineraryScheduleId , GROUP_CONCAT('<br>',v2.TugboatCompanyCode) as Danh_sach_congty_tau_lai, GROUP_CONCAT('<br>',v2.ShipCode) as Danh_sach_tau_lai, GROUP_CONCAT('<br>',v2.ShipName) as Tau_lai,  v1.TugDateFrom, v1.TugDateTo  from vma_schedule_tugboat as v1 ");
			query.append(" INNER JOIN vma_schedule_tugboat_list as v2 on v1.ItineraryNo = v2.ItineraryNo and v1.sequenceNo = v2.sequenceNo AND v1.NoticeShipType IN (1,2)  ");
			if (Validator.isNotNull(tugboatCompanyCode)) {
				query.append(" and v2.TugboatCompanyCode = '" + tugboatCompanyCode + "'");
			}

			if (Validator.isNotNull(shipCode)) {
				query.append(" AND v2.ShipCode = '" + shipCode + "'");
			}
			query.append(" INNER JOIN vma_itinerary_schedule As A2  ON A2.ItineraryNo = v1.ItineraryNo  and v1.itineraryScheduleId = A2.ID ");
			query.append(" where v1.NoticeShipType IN (1,2)  ");
			query.append(" GROUP BY v1.ItineraryNo, v1.itineraryScheduleId, v2.ShipCode ");
			query.append(" ) ) as LuotLaiDatKhongTinhPhi, ");
			query.append(" ( ");
			query.append(" select NgayTrongNam from ( ");
			query.append(" select @maxDate - interval (a.a+(10*b.a)+(100*c.a)+(1000*d.a)) day NgayTrongNam from ");
			query.append(" (select 0 as a union all select 1 union all select 2 union all select 3 ");
			query.append(" union all select 4 union all select 5 union all select 6 union all ");
			query.append(" select 7 union all select 8 union all select 9) a, ");
			query.append(" (select 0 as a union all select 1 union all select 2 union all select 3 ");
			query.append(" union all select 4 union all select 5 union all select 6 union all ");
			query.append(" select 7 union all select 8 union all select 9) b,  ");
			query.append(" (select 0 as a union all select 1 union all select 2 union all select 3 ");
			query.append(" union all select 4 union all select 5 union all select 6 union all ");
			query.append(" select 7 union all select 8 union all select 9) c,  ");
			query.append(" (select 0 as a union all select 1 union all select 2 union all select 3 ");
			query.append(" union all select 4 union all select 5 union all select 6 union all ");
			query.append(" select 7 union all select 8 union all select 9) d,  ");
			//query.append(" (select @minDate\\:='" + fromNgayTrongNam+ "', @maxDate\\:='"+ toNgayTrongNam + "') e ");

			query.append(queryFactory.getEntityManager().createNativeQuery(" (select @minDate\\:='" + fromNgayTrongNam+ "', @maxDate\\:='"+ toNgayTrongNam + "') e ").toString());

			query.append(" ) f ");
			query.append(" where NgayTrongNam between @minDate and @maxDate ORDER BY NgayTrongNam ASC ");
			query.append(" ) TrongKy ");



			query.append(" WHERE itineraryScheduleId = " + itineraryScheduleId + " and DATE(TugDateFrom) <= NgayTrongNam and DATE(TugDateTo) >= NgayTrongNam ");
			// query.append(" AND TugDateTo BETWEEN '2021-08-01 00:00:00' AND '2021-08-31 23:59:59' )   ");

			String reportDateFrom = StringPool.BLANK;
			String reportDateTo = StringPool.BLANK;
			if (Validator.isNotNull(fromDate) && Validator.isNotNull(toDate)
					&& !fromDate.isEmpty() && !toDate.isEmpty()) {
				Date f_date = null;
				Date t_date = null;
				try {
					f_date = FormatData.formatDateShort3.parse(fromDate);
					t_date = FormatData.formatDateShort3.parse(toDate);
				} catch (Exception e) {
					log.error(e.getMessage());
				}

				if (f_date != null && t_date != null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(f_date);
					String f_strDate = calendar.get(Calendar.YEAR) + "-"
							+ (calendar.get(Calendar.MONTH) + 1) + "-"
							+ calendar.get(Calendar.DATE);

					reportDateFrom =
							calendar.get(Calendar.DATE) + StringPool.SLASH + (calendar.get(Calendar.MONTH) + 1) + StringPool.SLASH
									+ calendar.get(Calendar.YEAR);

					calendar.setTime(t_date);
					String t_strDate = calendar.get(Calendar.YEAR) + "-"
							+ (calendar.get(Calendar.MONTH) + 1) + "-"
							+ calendar.get(Calendar.DATE);

					reportDateTo =
							calendar.get(Calendar.DATE) + StringPool.SLASH + (calendar.get(Calendar.MONTH) + 1) + StringPool.SLASH
									+ calendar.get(Calendar.YEAR);

					if (fromDate.length() <= 16 && toDate.length() <= 16) {
						String lastFiveDigitsFromDate = fromDate.substring(fromDate.length() - 5 );
						String lastFiveDigitsToDate = toDate.substring(toDate.length() - 5 );
						query.append(" AND TugDateTo BETWEEN '"
								+ f_strDate + " " + lastFiveDigitsFromDate + ":00'");
						query.append(" AND '" + t_strDate + " " + lastFiveDigitsToDate + ":00' ");
					} else {
						query.append(" AND TugDateTo BETWEEN '"
								+ f_strDate + " 00:00:00'");
						query.append(" AND '" + t_strDate + " 23:59:59' ");
					}


				}
			}
			query.append(" ) days_lookup ");
			query.append(" INNER JOIN  ");
			query.append(" ( ");
			query.append(" Select * from ( ");
			query.append(" ( ");
			query.append(" select v1.ItineraryNo, A1.itineraryScheduleId , GROUP_CONCAT('<br>',v2.TugboatCompanyCode) as Danh_sach_congty_tau_lai, GROUP_CONCAT('<br>',v2.ShipCode) as Danh_sach_tau_lai, GROUP_CONCAT('<br>',v2.ShipName) as Tau_lai,   v1.TugDateFrom, v1.TugDateTo from vma_schedule_tugboat as v1 ");
			query.append(" INNER JOIN vma_schedule_tugboat_list as v2 on v1.ItineraryNo = v2.ItineraryNo and v1.sequenceNo = v2.sequenceNo AND v1.NoticeShipType IN (4)    ");
			if (Validator.isNotNull(tugboatCompanyCode)) {
				query.append(" and v2.TugboatCompanyCode = '" + tugboatCompanyCode + "'");
			}

			if (Validator.isNotNull(shipCode)) {
				query.append(" AND v2.ShipCode = '" + shipCode + "'");
			}
			query.append(" INNER JOIN vma_schedule_shifting As A1  ON A1.ItineraryNo = v1.ItineraryNo  and v1.itineraryScheduleId = A1.ID ");
			query.append(" where v1.NoticeShipType IN (4)    ");
			query.append(" GROUP BY v1.ItineraryNo, v1.itineraryScheduleId,  v2.ShipCode ");
			query.append(" ) ");
			query.append(" UNION ( ");
			query.append(" select v1.ItineraryNo, v1.itineraryScheduleId , GROUP_CONCAT('<br>',v2.TugboatCompanyCode) as Danh_sach_congty_tau_lai, GROUP_CONCAT('<br>',v2.ShipCode) as Danh_sach_tau_lai, GROUP_CONCAT('<br>',v2.ShipName) as Tau_lai,  v1.TugDateFrom, v1.TugDateTo  from vma_schedule_tugboat as v1 ");
			query.append(" INNER JOIN vma_schedule_tugboat_list as v2 on v1.ItineraryNo = v2.ItineraryNo and v1.sequenceNo = v2.sequenceNo AND v1.NoticeShipType IN (1,2)     ");
			if (Validator.isNotNull(tugboatCompanyCode)) {
				query.append(" and v2.TugboatCompanyCode = '" + tugboatCompanyCode + "'");
			}

			if (Validator.isNotNull(shipCode)) {
				query.append(" AND v2.ShipCode = '" + shipCode + "'");
			}
			query.append(" INNER JOIN vma_itinerary_schedule As A2  ON A2.ItineraryNo = v1.ItineraryNo  and v1.itineraryScheduleId = A2.ID ");
			query.append(" where v1.NoticeShipType IN (1,2)  ");
			query.append(" GROUP BY v1.ItineraryNo, v1.itineraryScheduleId, v2.ShipCode ");
			query.append(" ) ) as LuotLaiDatKhongTinhPhi, ");
			query.append(" ( ");
			query.append(" select NgayTrongNam from ( ");
			query.append(" select @maxDate - interval (a.a+(10*b.a)+(100*c.a)+(1000*d.a)) day NgayTrongNam from ");
			query.append(" (select 0 as a union all select 1 union all select 2 union all select 3 ");
			query.append(" union all select 4 union all select 5 union all select 6 union all ");
			query.append(" select 7 union all select 8 union all select 9) a,  ");
			query.append(" (select 0 as a union all select 1 union all select 2 union all select 3 ");
			query.append(" union all select 4 union all select 5 union all select 6 union all ");
			query.append(" select 7 union all select 8 union all select 9) b,  ");
			query.append(" (select 0 as a union all select 1 union all select 2 union all select 3 ");
			query.append(" union all select 4 union all select 5 union all select 6 union all ");
			query.append(" select 7 union all select 8 union all select 9) c,  ");
			query.append(" (select 0 as a union all select 1 union all select 2 union all select 3 ");
			query.append(" union all select 4 union all select 5 union all select 6 union all ");
			query.append(" select 7 union all select 8 union all select 9) d,  ");
			//query.append(" (select @minDate\\:='" + fromNgayTrongNam+ "', @maxDate\\:='"+ toNgayTrongNam + "') e ");
			query.append(queryFactory.getEntityManager().createNativeQuery(" (select @minDate\\:='" + fromNgayTrongNam+ "', @maxDate\\:='"+ toNgayTrongNam + "') e ").toString());

			query.append(" ) f ");
			query.append(" where NgayTrongNam between @minDate and @maxDate ORDER BY NgayTrongNam ASC ");
			query.append(" ) TrongKy ");



			query.append(" WHERE itineraryScheduleId < " + itineraryScheduleId + " and DATE(TugDateFrom) <= NgayTrongNam and DATE(TugDateTo) >= NgayTrongNam ");
			// query.append(" AND TugDateTo BETWEEN '2021-08-01 00:00:00' AND '2021-08-31 23:59:59'  ");
			reportDateFrom = StringPool.BLANK;
			reportDateTo = StringPool.BLANK;
			if (Validator.isNotNull(fromDate) && Validator.isNotNull(toDate)
					&& !fromDate.isEmpty() && !toDate.isEmpty()) {
				Date f_date = null;
				Date t_date = null;
				try {
					f_date = FormatData.formatDateShort3.parse(fromDate);
					t_date = FormatData.formatDateShort3.parse(toDate);
				} catch (Exception e) {
					log.error(e.getMessage());
				}

				if (f_date != null && t_date != null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(f_date);
					String f_strDate = calendar.get(Calendar.YEAR) + "-"
							+ (calendar.get(Calendar.MONTH) + 1) + "-"
							+ calendar.get(Calendar.DATE);

					reportDateFrom =
							calendar.get(Calendar.DATE) + StringPool.SLASH + (calendar.get(Calendar.MONTH) + 1) + StringPool.SLASH
									+ calendar.get(Calendar.YEAR);

					calendar.setTime(t_date);
					String t_strDate = calendar.get(Calendar.YEAR) + "-"
							+ (calendar.get(Calendar.MONTH) + 1) + "-"
							+ calendar.get(Calendar.DATE);

					reportDateTo =
							calendar.get(Calendar.DATE) + StringPool.SLASH + (calendar.get(Calendar.MONTH) + 1) + StringPool.SLASH
									+ calendar.get(Calendar.YEAR);

					if (fromDate.length() <= 16 && toDate.length() <= 16) {
						String lastFiveDigitsFromDate = fromDate.substring(fromDate.length() - 5 );
						String lastFiveDigitsToDate = toDate.substring(toDate.length() - 5 );
						query.append(" AND TugDateTo BETWEEN '"
								+ f_strDate + " " + lastFiveDigitsFromDate + ":00'");
						query.append(" AND '" + t_strDate + " " + lastFiveDigitsToDate + ":00' ");
					} else {
						query.append(" AND TugDateTo BETWEEN '"
								+ f_strDate + " 00:00:00'");
						query.append(" AND '" + t_strDate + " 23:59:59' ");
					}


				}
			}


			query.append(" ) days_occupied  ");

			query.append(" ON days_lookup.NgayTrongNam = days_occupied.NgayTrongNam ");


			log.info("=========countThongKeTauLaiDat_NgayKhongTinhPhi>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Long.class).build();


			Iterator<Long> itr = queryFactory3.getResultList(builder).iterator();


			if (itr.hasNext()) {
				Long count = itr.next();
				if (count != null) {
					return count.intValue();
				}
			}
			return 0;
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
		}
	}


}
