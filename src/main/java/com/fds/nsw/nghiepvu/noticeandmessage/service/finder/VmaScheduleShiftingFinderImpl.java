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

import java.util.*;

import com.fds.nsw.nghiepvu.model.DmShipAgency;
import com.fds.nsw.nghiepvu.model.DmVmaPilotCompany;
import vn.gt.dao.danhmuc.service.*;
import vn.gt.portlet.phuongtien.VMAUtils;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import com.fds.flex.common.ultility.GetterUtil;
import com.fds.flex.common.utility.string.StringPool;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.kernel.dao.orm.BasePersistence;
import com.fds.nsw.kernel.dao.orm.QueryUtil;
import com.fds.nsw.kernel.dao.orm.jpa.QueryBuilder;
import com.fds.nsw.kernel.dao.orm.jpa.QueryFactory;
import com.fds.nsw.kernel.exception.SystemException;
import com.fds.nsw.nghiepvu.model.IssueShiftingOrder;
import com.fds.nsw.nghiepvu.util.ConvertUtil;
import com.fds.nsw.nghiepvu.util.FormatData;

import lombok.extern.slf4j.Slf4j;
import com.fds.nsw.nghiepvu.model.VmaScheduleShifting;
@Service
@Slf4j
public class VmaScheduleShiftingFinderImpl extends

		BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaScheduleShifting> queryFactory;

	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<Object[]> queryFactory2;


	public JSONArray findVmaItinerary_VmaScheduleShifting(String itineraryNo,
			String portofAuthority, String shiftingDate, String requestState,
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
			query.append("A2.ShownDraftxF,");
			query.append("A2.ShownDraftxA,");
			query.append("A2.UnitGRT,");
			query.append("A2.UnitNT,");
			query.append("A2.DWTUnit,");
			query.append("A2.LOAUNIT,");
			query.append("A2.UnitShownDraftxF,");
			query.append("A2.UnitShownDraftxA,");
			query.append("A2.From_,");
			query.append("A2.To_,");
			query.append("A2.ShiftingDate,");
			query.append("A2.NameOfShipownersAgents,");
			query.append("A2.TaxCodeOfShipownersAgents,");
			query.append("A2.ChanelName,");
			query.append("A2.ChanelCodeList,");
			query.append("A2.ID,");

			query.append("A2.AnchoringPortHarbourCode,");
			query.append("A2.AnchoringPortWharfCode,");
			query.append("A2.ShiftingPortHarbourCode,");
			query.append("A2.ShiftingPortWharfCode,");
			query.append("A2.ReasonToShift,");
			// query.append("A2.IssueDate,");
			query.append("A2.ItineraryNo,");
			query.append("vv.Tau_lai,");
			query.append("vvv.Hoa_tieu,");
			query.append("vvv.PilotCompanyCode,");
			query.append("vv.Tau_lai_viet_tat,");
			query.append("vvv.Hoa_tieu_viet_tat,");
			query.append("A21.ShipAgencyCode,");
			query.append("A21.ShipAgencyName,");
			query.append("A21.MaxDraft,");			
			query.append("A21.initFrom,");
			query.append("CONCAT(");
			query.append("(Select Case ISNULL(PortHarbourShortName)  when 0 then PortHarbourShortName else PortHarbourNameVN end as Harbour from dm_port_harbour where PortHarbourCode = A2.AnchoringPortHarbourCode),");
			query.append("', ',");
			query.append("(Select Case ISNULL(PortWharfShortName)  when 0 then PortWharfShortName else PortWharfNameVN end as Wharf  from dm_port_wharf where PortWharfCode = A2.AnchoringPortWharfCode)) ");
			query.append("AS shortInitFrom, ");
			query.append("A1.CargoDescription ");
			
			
			query.append(" FROM vma_itinerary AS A1 INNER JOIN vma_schedule_shifting AS A2 ON A1.ItineraryNo = A2.ItineraryNo");
			String noticeShipType = "4,5";			
			query.append(" INNER JOIN vma_itinerary_schedule AS A21 ON A2.ItineraryNo = A21.ItineraryNo and A2.itineraryScheduleId = A21.ID "); 
			query.append(" LEFT JOIN (select v1.ItineraryNo, v1.itineraryScheduleId , GROUP_CONCAT('<br>',v2.ShipName) as Tau_lai," 
					+ " Case ISNULL(GROUP_CONCAT('<br>', Case ISNULL(v3.TugboatShortName)  when 0 then (Case LENGTH(TRIM(v3.TugboatShortName))  when 0 then v2.ShipName else v3.TugboatShortName end) else v2.ShipName end )) " 
					+ " when 0 then GROUP_CONCAT('<br>', Case ISNULL(v3.TugboatShortName)  when 0 then (Case LENGTH(TRIM(v3.TugboatShortName))  when 0 then v2.ShipName else v3.TugboatShortName end) else v2.ShipName end ) "
					+ " else GROUP_CONCAT('<br>', v2.ShipName) end AS Tau_lai_viet_tat " 
					+ " from vma_schedule_tugboat as v1 LEFT JOIN vma_schedule_tugboat_list as v2 on v1.ItineraryNo = v2.ItineraryNo and v1.sequenceNo = v2.sequenceNo "
					+ " LEFT JOIN dm_vma_tugboat AS v3 ON v3.ShipCode = v2.ShipCode and v3.ShipName like v2.ShipName where NoticeShipType IN ("
					+ noticeShipType
					+ ") GROUP BY v1.ItineraryNo, v1.itineraryScheduleId) as vv on A2.ItineraryNo = vv.ItineraryNo and vv.itineraryScheduleId = A2.ID and vv.Tau_lai is not null");
			query.append(" LEFT JOIN (select v1.ItineraryNo, v1.itineraryScheduleId , GROUP_CONCAT('<br>',v2.PilotName) as Hoa_tieu, "
					+ " Case ISNULL(GROUP_CONCAT('<br>', Case ISNULL(v3.PilotShortName)  when 0 then (Case LENGTH(TRIM(v3.PilotShortName))  when 0 then v2.PilotName else v3.PilotShortName end) else v2.PilotName end )) " 
					+ " when 0 then GROUP_CONCAT('<br>', Case ISNULL(v3.PilotShortName)  when 0 then (Case LENGTH(TRIM(v3.PilotShortName))  when 0 then v2.PilotName else v3.PilotShortName end) else v2.PilotName end )" 
					+ " else GROUP_CONCAT('<br>', v2.PilotName) end AS Hoa_tieu_viet_tat, "
					+ " v2.PilotCompanyCode from (Select * from vma_schedule_pilot_list ORDER BY itineraryScheduleId, PilotCategoryCode ASC) AS v2 LEFT JOIN vma_schedule_pilot as v1 on v1.ItineraryNo = v2.ItineraryNo and v1.sequenceNo = v2.sequenceNo "
					+ " LEFT JOIN dm_vma_pilot AS v3 ON v3.PilotCode = v2.PilotCode where NoticeShipType IN ("
					+ noticeShipType
					+ ") GROUP BY v1.ItineraryNo, v1.itineraryScheduleId) as vvv on A2.ItineraryNo = vvv.ItineraryNo and vvv.itineraryScheduleId = A2.ID and vvv.Hoa_tieu is not null");

			query.append(" WHERE 1=1 ");

			query.append(generateCondition(itineraryNo, portofAuthority,
					shiftingDate, requestState));

			query.append(" ORDER BY A2.ShiftingDate ASC");

			log.info("=========select vma_schedule_shifting>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).firstResult(start).maxResults(end - start).entityClass(Object.class).build();

			Iterator<Object[]> itr = queryFactory2.getResultList(builder).iterator();

			if (itr.hasNext()) {
				while (itr.hasNext()) {
					Object[] objects = itr.next();

					String nameOfShip = String.valueOf(objects[0]);
					String flagStateOfShip = String.valueOf(objects[1]);
					String imoNumber = String.valueOf(objects[2]);
					String callSign = String.valueOf(objects[3]);
					String vrCode = String.valueOf(objects[4]);
					String registryNumber = String.valueOf(objects[5]);

					double gt = GetterUtil.getDouble(objects[6]);
					double nt = GetterUtil.getDouble(objects[7]);
					double dwt = GetterUtil.getDouble(objects[8]);
					double loa = GetterUtil.getDouble(objects[9]);
					double shownDraftxF = GetterUtil.getDouble(objects[10]);
					double shownDraftxA = GetterUtil.getDouble(objects[11]);
					
					String unitGrt = String.valueOf(objects[12]);
					String unitNt = String.valueOf(objects[13]);
					String unitDwt = String.valueOf(objects[14]);
					String unitLoa = String.valueOf(objects[15]);
					String unitShownDraftxF = String.valueOf(objects[16]);
					String unitShownDraftxA = String.valueOf(objects[17]);

					String form = String.valueOf(objects[18]);
					String to = String.valueOf(objects[19]);

					Date datetimeOfShiftingDate = (Date) objects[20];

					shiftingDate = StringPool.BLANK;

					if (datetimeOfShiftingDate != null) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(datetimeOfShiftingDate);

						int day = calendar.get(Calendar.DATE);

						int month = calendar.get(Calendar.MONTH) + 1;

						int year = calendar.get(Calendar.YEAR);

						int hours = calendar.get(Calendar.HOUR_OF_DAY);

						int minute = calendar.get(Calendar.MINUTE);

						shiftingDate = (day < 10 ? "0" + day : day)
								+ StringPool.SLASH
								+ (month < 10 ? "0" + month : month)
								+ StringPool.SLASH + year + StringPool.SPACE
								+ (hours > 10 ? hours : "0" + hours)
								+ StringPool.COLON
								+ (minute > 10 ? minute : "0" + minute);
					}

					String nameOfShipownersAgents = String.valueOf(objects[21]);
					String taxCodeOfShipownersAgents = String
							.valueOf(objects[22]);
					String chanelName = String.valueOf(objects[23]);
					String chanelCodeList = String.valueOf(objects[24]);
					long vmaScheduleShiftingId = GetterUtil
							.getLong(objects[25]);

					String anchoringPortHarbourCode = String
							.valueOf(objects[26]);
					String anchoringPortWharfCode = String.valueOf(objects[27]);
					String shiftingPortHarbourCode = String
							.valueOf(objects[28]);
					String shiftingPortWharfCode = String.valueOf(objects[29]);
					String reasonToShift = String.valueOf(objects[30]);
					itineraryNo = String.valueOf(objects[31]);
					
					String tugboatList = String.valueOf(objects[32]);
					String pilotList = String.valueOf(objects[33]);
					String pilotCompanyCode = String.valueOf(objects[34]);
					String shortTugboatList = String.valueOf(objects[35]);
					String shortPilotList = String.valueOf(objects[36]);
					
					String shipAgencyShortName = StringPool.BLANK;
					String shipAgencyCode = String.valueOf(objects[37]);
					String shipAgencyName = String.valueOf(objects[38]);					
					
					double maxDraft = GetterUtil.getDouble(objects[39]);		
					String initFrom = String.valueOf(objects[40]);
					String shortInitFrom = String.valueOf(objects[41]);
					String cargoDescription = String.valueOf(objects[42]);
					
					
					JSONObject object = JSONFactoryUtil.createJSONObject();

					object.put("anchoringPortHarbourCode",
							anchoringPortHarbourCode);
					object.put("anchoringPortWharfCode", anchoringPortWharfCode);
					object.put("shiftingPortHarbourCode",
							shiftingPortHarbourCode);
					object.put("shiftingPortWharfCode", shiftingPortWharfCode);
					object.put("reasonToShift", reasonToShift);

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
					object.put("maxDraft", maxDraft);
					object.put("shownDraftxF", shownDraftxF);
					object.put("shownDraftxA", shownDraftxA);
					object.put("unitLoa", unitLoa);
					object.put("unitShownDraftxF", unitShownDraftxF);
					object.put("unitShownDraftxA", unitShownDraftxA);
					object.put("unitGrt", unitGrt);
					object.put("unitNt", unitNt);
					object.put("unitDwt", unitDwt);
					object.put("from", form);
					object.put("to", to);
					object.put("shiftingDate", shiftingDate);
					object.put("nameOfShipownersAgents", nameOfShipownersAgents);
					object.put("taxCodeOfShipownersAgents",
							taxCodeOfShipownersAgents);
					object.put("chanelName", chanelName);
					object.put("chanelCodeListName", chanelName);
					object.put("chanelCodeList", chanelCodeList);
					object.put("vmaScheduleShiftingId", vmaScheduleShiftingId);
					object.put("itineraryNo", itineraryNo);
					object.put("cargoDescription", cargoDescription);
					try {
						if (Validator.isNotNull(cargoDescription)){
							object.put(
									"cargoName",
									DmDataItemLocalServiceUtil
											.findByDataGroupIdAndCode0(124,
													cargoDescription).getName());
						} else {
							object.put(
									"cargoName",StringPool.BLANK);
						}
						
					} catch (Exception e) {
						// nothing to do
					}
					try {
						object.put(
								"shiftingPortHarbourName",
								DmPortHarbourLocalServiceUtil
										.getByPortHarbourCode(
												shiftingPortHarbourCode)
										.getPortHarbourNameVN());
					} catch (Exception e) {
						// nothing to do
					}
					try {
						object.put(
								"shiftingPortWharfName",
								DmPortWharfLocalServiceUtil.getByPortWharfCode(
										shiftingPortWharfCode)
										.getPortWharfNameVN());
					} catch (Exception e) {
						//nothing to do
					}
					
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
					
					object.put("tugBoatNames", Validator.isNotNull(tugboatList) ? tugboatList : "");
					object.put("pilotNames", Validator.isNotNull(pilotList) ? pilotList : "");
					
					object.put("shipAgencyName", Validator.isNotNull(shipAgencyName) ? shipAgencyName: "");					
					object.put("shipAgencyShortName", Validator.isNotNull(shipAgencyShortName) ? shipAgencyShortName : (Validator.isNotNull(shipAgencyName) ? shipAgencyName : ""));

					data.put(object);

				}
			}

		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}

		return data;
	}

	public long countVmaItinerary_VmaScheduleShifting(String itineraryNo,
			String portofAuthority, String shiftingDate, String requestState) throws SystemException { long count = 0; try {
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT count(*) AS total FROM vma_itinerary AS A1 INNER JOIN vma_schedule_shifting AS A2 ON A1.ItineraryNo = A2.ItineraryNo WHERE 1 = 1");

			query.append(generateCondition(itineraryNo, portofAuthority,
					shiftingDate, requestState));

			log.info("=========count vma_itinerary_schedule>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Long.class).build();

			/*  */

			
			count = (Long) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return count;
	}

	private String generateCondition(String itineraryNo,
			String portofAuthority, String shiftingDate, String requestState) {
		StringBuilder condition = new StringBuilder();

		if (Validator.isNotNull(itineraryNo)) {
			condition.append(" AND A2.ItineraryNo = \"" + itineraryNo + "\"");
		}

		if (Validator.isNotNull(portofAuthority)) {
			condition.append(" AND A2.PortofAuthority = \"" + portofAuthority
					+ "\"");
		}

		if (Validator.isNotNull(shiftingDate)) {
			Date date = null;

			try {
				date = FormatData.formatDDMMYYYY.parse(shiftingDate);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				String strDate = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE);

				condition.append(" AND A2.ShiftingDate BETWEEN '" + strDate
						+ " 00:00:00'");

				condition.append(" AND '" + strDate + " 23:59:59'");
			}
		}

		if (Validator.isNotNull(requestState) && !requestState.isEmpty()) {
			condition.append(" AND A2.RequestState IN (" + requestState + ")");
		}

		return condition.toString();
	}

}
