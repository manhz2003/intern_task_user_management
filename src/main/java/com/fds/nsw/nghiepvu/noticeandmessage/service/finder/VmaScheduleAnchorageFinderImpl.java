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

import com.fds.nsw.nghiepvu.model.DmPortWharf;
import com.fds.nsw.nghiepvu.model.VmaScheduleCargolist;
import com.fds.nsw.nghiepvu.modelImpl.VmaScheduleCargolistModelImpl;
import vn.gt.dao.danhmuc.service.DmPortHarbourLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortRegionLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmPortWharfLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmStateLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.VmaScheduleCargolistLocalServiceUtil;
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
import com.fds.nsw.nghiepvu.model.VmaScheduleAnchorage;
@Service
@Slf4j
public class VmaScheduleAnchorageFinderImpl extends

		BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaScheduleAnchorage> queryFactory;

	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<Object[]> queryFactory2;

	public JSONArray findVmaItinerary_VmaScheduleAnchorage(String itineraryNo,
			String nameOfShip, String purposeCode, String anchoringDateFrom,
			String anchoringDateTo, String anchoringPortRegionCode,
			String anchoringPortHarbourCode, String anchoringPortWharfCode,
			String shipOwnerCode, String shipOperatorCode,
			String shipAgencyCode, Integer makePayment,
			Integer anchorageSupplement, String documentaryCode,
			Long itineraryScheduleId, Integer shipPosition, String flag,
			String flag2, String imoNumber, String callSign,
			String registryNumber, String timeOfArrival, int start, int end)
			throws SystemException {

		String flagFrom = "vma_schedule_anchorage";
		String flagOrder = StringPool.BLANK;
		String flag2Order = StringPool.BLANK;
		String flagFilter = StringPool.BLANK; //Tau thuyen trong cang
		if (Validator.isNotNull(flag) && flag.length() > 0) {
			flagFrom = "(Select t.* from (Select NameOfShip, MAX(ID) as MaxID from vma_schedule_anchorage WHERE  (NoticeShipType <> 2 OR (NoticeShipType =2 and AnchoringDuration > 0)) and (ISNULL(AnchoringDateFrom)  = 0 OR ISNULL(AnchoringDateTo) = 0) and LENGTH(NameOfShip) > 0 GROUP BY NameOfShip) anchor INNER JOIN vma_schedule_anchorage t ON t.NameOfShip = anchor.NameOfShip and t.ID = anchor.MaxID GROUP BY t.NameOfShip)";
			flagOrder = " A5.SequenceNo ASC, A4.SequenceNo ASC, A3.PortWharfNameVN ASC, TimeOfArrival DESC, nameOfShip ASC, ";
			flagFilter = " AND A2.ID = (SELECT MAX(lastAnchor.ID) FROM vma_schedule_anchorage lastAnchor WHERE A2.ItineraryNo = lastAnchor.ItineraryNo AND (NoticeShipType <> 2 OR (NoticeShipType =2 and AnchoringDuration > 0)) and (ISNULL(AnchoringDateFrom)  = 0 OR ISNULL(AnchoringDateTo) = 0) and LENGTH(NameOfShip) > 0 )";
		}
		if (Validator.isNotNull(flag2) && flag2.length() > 0) {
			flag2Order = " A5.SequenceNo ASC, A4.SequenceNo ASC, TimeOfArrival DESC, ";
		}

		

		JSONArray data = JSONFactoryUtil.createJSONArray();

		try {

			

			StringBuilder query = new StringBuilder();

			query.append("SELECT DISTINCT ");
			query.append("A1.NameOfShip,");
			query.append("A1.FlagStateOfShip,");
			query.append("A1.IMONumber,");
			query.append("A1.CallSign,");
			query.append("A1.VRCode,");
			query.append("A1.RegistryNumber,");
			query.append("A2.AnchoringDateFrom,");
			query.append("A2.PurposeCode,");
			query.append("A2.PurposeSpecified,");
			query.append("A2.AnchoringDateTo,");
			query.append("A2.PortofAuthority,");
			query.append("A2.AnchoringPortRegionCode,");
			query.append("A2.AnchoringPortHarbourCode,");
			query.append("A2.AnchoringPortWharfCode,");
			query.append("A2.NoticeShipType,");
			query.append("A2.PortRegionCode,");
			query.append("A2.PortHarbourCode,");
			query.append("A2.PortWharfCode,");
			query.append("A2.ShipOwnerCode,");
			query.append("A2.ShipOperatorCode,");
			query.append("A2.ShipAgencyCode,");
			query.append("A2.AnchoringDuration,");
			query.append("A2.AnchorageFreeDuration,");
			query.append("A2.ID,");
			query.append("A2.ItineraryNo,");
			query.append("A2.MakePayment,");
			query.append("A2.AnchorageSupplement,");
			query.append("A2.DocumentaryCode,");
			query.append("A2.itineraryScheduleId,");

			query.append("A3.PortWharfName,");
			query.append("A3.PortWharfNameVN,");
			query.append("A3.ManagedVinalines,");
			query.append("A3.PortWharfType,");
			query.append("A3.PortWharfPayment,");
			query.append("A3.DWT as DWTLimit,");
			query.append("A3.LOA as LOALimit,");
			query.append("A3.MaxDraft as MaxDraftLimit,");

			query.append("A4.PortHarbourName,");
			query.append("A4.PortHarbourNameVN,");
			query.append("A4.PortHarbourType,");

			query.append("A5.PortRegionName,");
			query.append("A5.PortRegionNameVN,");
			query.append("A1.DocumentNameIN,");
			query.append("A1.DocumentYearIN,");
			query.append("A1.DocumentNameOUT,");
			query.append("A1.DocumentYearOUT,");

			query.append("A1.DocumentNameVOY,");
			query.append("A1.DocumentYearVOY,");
			query.append("A1.MarkedAsArrival,");
			query.append("A1.TimeOfArrival,");
			query.append("A1.TimeOfDeparture,");
			query.append("A6.ShipAgencyName,");
			query.append("A6.ShipAgencyShortName,");

			query.append("A1.MarkedAsDeparture,");
			query.append("A2.AnchorageDomesticDuration,");
			query.append("A2.AnchorageForeignDuration,");
			query.append("A7.ShipBoat,");
			query.append("A3.PortWharfShortName,");
			query.append("A4.PortHarbourShortName,");
			query.append("A5.PortRegionShortName,");
			query.append(" A8.GT, A8.DWT, A8.LOA, A8.ShownDraftxA, A8.ShownDraftxF, A8.Breadth, A8.ClearanceHeight,");
			query.append(" CASE (A2.NoticeShipType) WHEN 1 THEN 1 WHEN 2 THEN 3 WHEN 4 THEN 2 END as SequenceBy ");

			query.append(" FROM vma_itinerary AS A1 LEFT JOIN " + flagFrom
					+ " AS A2 ON A1.ItineraryNo = A2.ItineraryNo" + flagFilter);

			query.append(" LEFT JOIN vma_itinerary_schedule A8 ON A2.ItineraryNo = A8.ItineraryNo and A2.ItineraryScheduleId = A8.ID");
			
			query.append(" LEFT JOIN dm_port_wharf AS A3 ON A2.AnchoringPortWharfCode = A3.PortWharfCode");

			query.append(" LEFT JOIN dm_port_harbour AS A4 ON A2.AnchoringPortHarbourCode = A4.PortHarbourCode");

			query.append(" LEFT JOIN dm_port_region AS A5 ON A2.AnchoringPortRegionCode = A5.PortRegionCode");

			query.append(" LEFT JOIN dm_ship_agency AS A6 ON A2.ShipAgencyCode = A6.ShipAgencyCode");

			query.append(" LEFT JOIN vma_ship as A7 ON A7.ID = (SELECT MAX(lastShip.ID) FROM vma_ship lastShip WHERE A1.CallSign = lastShip.CallSign and A1.IMONumber = lastShip.IMONumber and A1.RegistryNumber = lastShip.RegistryNumber ) ");

			query.append(" WHERE 1=1 AND A2.ID > 0 ");

			query.append(generateCondition(itineraryNo, nameOfShip,
					purposeCode, anchoringDateFrom, anchoringDateTo,
					anchoringPortRegionCode, anchoringPortHarbourCode,
					anchoringPortWharfCode, shipOwnerCode, shipOperatorCode,
					shipAgencyCode, makePayment, anchorageSupplement,
					documentaryCode, itineraryScheduleId, shipPosition,
					imoNumber, callSign, registryNumber, timeOfArrival));

			query.append(" ORDER BY "
					+ flagOrder
					+ flag2Order
					+ " SequenceBy ASC, CASE(A2.NoticeShipType) WHEN 1 THEN A2.AnchoringDateFrom WHEN 2 THEN A2.AnchoringDateTo  WHEN 4 THEN A2.ID END ASC");

			log.info("=========select vma_schedule_anchorage>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true)
					.queryString(query.toString()).firstResult(start).maxResults(end - start).entityClass(Object.class).build();

			

			Iterator<Object[]> itr =queryFactory2.getResultList(builder).iterator();

			if (itr.hasNext()) {
				while (itr.hasNext()) {
					Object[] objects = itr.next();

					nameOfShip = String.valueOf(objects[0]);
					String flagStateOfShip = String.valueOf(objects[1]);
					imoNumber = String.valueOf(objects[2]);
					callSign = String.valueOf(objects[3]);
					String vrCode = String.valueOf(objects[4]);
					registryNumber = String.valueOf(objects[5]);
					Date fromDate = (Date) objects[6];

					String _anchoringDateFrom = StringPool.BLANK;
					if (fromDate != null) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(fromDate);

						int day = calendar.get(Calendar.DATE);

						int month = calendar.get(Calendar.MONTH) + 1;

						int year = calendar.get(Calendar.YEAR);

						int hour = calendar.get(Calendar.HOUR_OF_DAY);

						int minute = calendar.get(Calendar.MINUTE);

						_anchoringDateFrom = (day < 10 ? "0" + day : day)
								+ StringPool.SLASH
								+ (month < 10 ? "0" + month : month)
								+ StringPool.SLASH + year + StringPool.SPACE
								+ (hour < 10 ? "0" + hour : hour)
								+ StringPool.COLON
								+ (minute < 10 ? "0" + minute : minute);
					}

					purposeCode = String.valueOf(objects[7]);
					String purposeSpecified = String.valueOf(objects[8]);

					Date toDate = (Date) objects[9];

					String _anchoringDateTo = StringPool.BLANK;
					if (toDate != null) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(toDate);

						int day = calendar.get(Calendar.DATE);

						int month = calendar.get(Calendar.MONTH) + 1;

						int year = calendar.get(Calendar.YEAR);

						int hour = calendar.get(Calendar.HOUR_OF_DAY);

						int minute = calendar.get(Calendar.MINUTE);

						_anchoringDateTo = (day < 10 ? "0" + day : day)
								+ StringPool.SLASH
								+ (month < 10 ? "0" + month : month)
								+ StringPool.SLASH + year + StringPool.SPACE
								+ (hour < 10 ? "0" + hour : hour)
								+ StringPool.COLON
								+ (minute < 10 ? "0" + minute : minute);
					}

					String portofAuthority = String.valueOf(objects[10]);
					anchoringPortRegionCode = String.valueOf(objects[11]);
					anchoringPortHarbourCode = String.valueOf(objects[12]);
					anchoringPortWharfCode = String.valueOf(objects[13]);
					int noticeShipType = GetterUtil.getInteger(objects[14]);
					String portRegionCode = String.valueOf(objects[15]);
					String portHarbourCode = String.valueOf(objects[16]);
					String portWharfCode = String.valueOf(objects[17]);
					shipOwnerCode = String.valueOf(objects[18]);
					shipOperatorCode = String.valueOf(objects[19]);
					shipAgencyCode = String.valueOf(objects[20]);
					double anchoringDuration = 0;
					try {
						anchoringDuration = GetterUtil.getDouble(String
								.valueOf(objects[21]));
					} catch (Exception e) {
						// nothing to do
					}
					double anchorageFreeDuration = 0;

					double paymentDuration = anchoringDuration
							- anchorageFreeDuration;

					if (paymentDuration < 0) {
						paymentDuration = 0;
					}

					try {
						anchorageFreeDuration = GetterUtil.getDouble(String
								.valueOf(objects[22]));
					} catch (Exception e) {
						// nothing to do
					}
					long vmaScheduleAnchorageId = GetterUtil
							.getLong(objects[23]);
					itineraryNo = String.valueOf(objects[24]);

					makePayment = GetterUtil.getInteger(objects[25]);
					anchorageSupplement = 0;
					try {
						anchorageSupplement = GetterUtil
								.getInteger(objects[26]);
					} catch (Exception e) {
						// nothing to do
					}
					documentaryCode = String.valueOf(objects[27]);
					itineraryScheduleId = GetterUtil.getLong(objects[28]);

					String portWharfName = String.valueOf(objects[29]);
					String portWharfNameVN = String.valueOf(objects[30]);
					int managedVinalines = GetterUtil.getInteger(objects[31]);
					int portWharfType = GetterUtil.getInteger(objects[32]);
					int portWharfPayment = GetterUtil.getInteger(objects[33]);
					double dwtLimit = GetterUtil.getDouble(objects[34]);
					double loaLimit = GetterUtil.getDouble(objects[35]);
					double maxDraftLimit = GetterUtil.getDouble(objects[36]);

					
					String portHarbourName = String.valueOf(objects[37]);
					String portHarbourNameVN = String.valueOf(objects[38]);
					String portHarbourType = String.valueOf(objects[39]);

					String portRegionName = String.valueOf(objects[40]);
					String portRegionNameVN = String.valueOf(objects[41]);

					String documentNameIN = String.valueOf(objects[42]);
					String documentYearIN = String.valueOf(objects[43]);
					String documentNameOUT = String.valueOf(objects[44]);
					String documentYearOUT = String.valueOf(objects[45]);
					String documentNameVOY = String.valueOf(objects[46]);
					String documentYearVOY = String.valueOf(objects[47]);
					String markedAsArrival = String.valueOf(objects[48]);

					Date _timeOfArrival = (Date) objects[49];
					Date timeOfDeparture = (Date) objects[50];
					String shipAgencyName = String.valueOf(objects[51]);
					String shipAgencyShortName = String.valueOf(objects[52]);

					int markedAsDeparture = GetterUtil.getInteger(objects[53]);
					double anchorageDomesticDuration = GetterUtil
							.getDouble(objects[54]);
					double anchorageForeignDuration = GetterUtil
							.getDouble(objects[55]);
					String shipBoat = String.valueOf(objects[56]);
					String portWharfShortName = Validator.isNotNull(String.valueOf(objects[57])) ? String.valueOf(objects[57]) :  portWharfNameVN;
					String portHarbourShortName = Validator.isNotNull(String.valueOf(objects[58])) ? String.valueOf(objects[58]) : portHarbourNameVN;
					String portRegionShortName = Validator.isNotNull(String.valueOf(objects[59])) ? String.valueOf(objects[59]) : portRegionNameVN;
					
					double gt = GetterUtil.getDouble(objects[60]);
					double dwt = GetterUtil.getDouble(objects[61]);
					double loa = GetterUtil.getDouble(objects[62]);
					double maxDraft = GetterUtil.getDouble(objects[63]);
					double shownDraftxA = GetterUtil.getDouble(objects[63]);
					double shownDraftxF = GetterUtil.getDouble(objects[64]);
					double breadth = GetterUtil.getDouble(objects[65]);
					double clearanceHeight = GetterUtil.getDouble(objects[66]);
					 
					
					
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

					object.put("anchoringDateFrom", _anchoringDateFrom);
					object.put("purposeCode", purposeCode);
					object.put("purposeSpecified", purposeSpecified);
					object.put("anchoringDateTo", _anchoringDateTo);

					object.put("portofAuthority", portofAuthority);
					object.put("anchoringPortRegionCode",
							anchoringPortRegionCode);
					try {
						object.put(
								"portRegionName",
								DmPortRegionLocalServiceUtil
										.getByPortRegionCode(
												anchoringPortRegionCode)
										.getPortRegionName());
					} catch (Exception e) {
						// nothing to do
					}
					object.put("anchoringPortHarbourCode",
							anchoringPortHarbourCode);
					try {
						object.put(
								"portHarbourName",
								DmPortHarbourLocalServiceUtil
										.getByPortHarbourCode(
												anchoringPortHarbourCode)
										.getPortHarbourName());
					} catch (Exception e) {
						// nothing to do
					}
					object.put("anchoringPortWharfCode", anchoringPortWharfCode);
					try {
						object.put("portWharfName", DmPortWharfLocalServiceUtil
								.getByPortWharfCode(anchoringPortWharfCode)
								.getPortWharfName());
					} catch (Exception e) {
						// nothing to do
					}
					try {
						// Canh bao: Tinh phi hay khong 
						if (makePayment == 1) {
							object.put("Verify_MAKEPAYMENT", "Đã ghi phiếu thu");
						} else {
							object.put("Verify_MAKEPAYMENT", "Chưa ghi phiếu thu");
						}
							
						
						DmPortWharf dmPortWharf = DmPortWharfLocalServiceUtil.getByPortWharfCode(anchoringPortWharfCode);
						if (dmPortWharf !=null && dmPortWharf.getPortWharfPayment()==1){
							object.put("Verify_PAYMENT", "Cảng vụ tính phí");
						} else {
							object.put("Verify_PAYMENT", "Không tính phí");
						}
					} catch (Exception e) {
						// nothing to do
						object.put("Verify_PAYMENT", "Không tính phí");
					}
					
					object.put("noticeShipType", noticeShipType);
					object.put("portRegionCode", portRegionCode);
					object.put("portHarbourCode", portHarbourCode);
					object.put("portWharfCode", portWharfCode);
					object.put("shipOwnerCode", shipOwnerCode);
					object.put("shipOperatorCode", shipOperatorCode);
					object.put("shipAgencyCode", shipAgencyCode);
					object.put("anchoringDuration", anchoringDuration);
					object.put("anchorageFreeDuration", anchorageFreeDuration);
					object.put("vmaScheduleAnchorageId", vmaScheduleAnchorageId);

					object.put("itineraryNo", itineraryNo);

					object.put("makePayment", makePayment);
					object.put("anchorageSupplement", anchorageSupplement);
					object.put("documentaryCode", documentaryCode);
					object.put("itineraryScheduleId", itineraryScheduleId);

					object.put("portWharfName", portWharfName);
					object.put("portWharfNameVN", portWharfNameVN);
					object.put("portWharfShortName", portWharfShortName);
					object.put("portHarbourName", portHarbourName);
					object.put("portHarbourNameVN", portHarbourNameVN);
					object.put("portHarbourShortName", portHarbourShortName);
					object.put("portRegionName", portRegionName);
					object.put("portRegionNameVN", portRegionNameVN);
					object.put("portRegionShortName", portRegionShortName);

					object.put("managedVinalines", managedVinalines);
					object.put("portWharfType", portWharfType);
					object.put("portWharfPayment", portWharfPayment);
					
					object.put("gt", gt);
					object.put("dwt", dwt);
					object.put("loa", loa);										
					object.put("maxDraft", maxDraft);
					object.put("shownDraftxA", shownDraftxA);
					object.put("shownDraftxF", shownDraftxF);
					object.put("breadth", breadth);
					object.put("clearanceHeight", clearanceHeight);
					
					
					object.put("dwtLimit", dwtLimit);
					object.put("loaLimit", loaLimit);
					object.put("maxDraftLimit", maxDraftLimit);
					object.put("portHarbourType", portHarbourType);
					object.put("paymentDuration", paymentDuration);

					object.put("documentNameIN", documentNameIN);
					object.put("documentYearIN", documentYearIN);
					object.put("documentNameOUT", documentNameOUT);
					object.put("documentYearOUT", documentYearOUT);
					object.put("documentNameVOY", documentNameVOY);
					object.put("documentYearVOY", documentYearVOY);
					object.put("markedAsArrival", markedAsArrival);
					object.put("shipAgencyShortName", Validator.isNotNull(shipAgencyShortName) ? shipAgencyShortName : "");

					object.put("markedAsDeparture", markedAsDeparture);
					object.put("anchorageDomesticDuration",
							anchorageDomesticDuration);
					object.put("anchorageForeignDuration",
							anchorageForeignDuration);
					object.put("shipBoat", shipBoat);

					if (_timeOfArrival != null) {
						String _tmp = "";
						try {
							_tmp = FormatData.formatDateShort3
									.format(_timeOfArrival);
						} catch (Exception e) {
							// TODO: handle exception
						}
						object.put("timeOfArrival", _tmp);
					} else {
						object.put("timeOfArrival", "");
					}

					if (timeOfDeparture != null) {
						String _tmp = "";
						try {
							_tmp = FormatData.formatDateShort3
									.format(timeOfDeparture);
						} catch (Exception e) {
							// TODO: handle exception
						}
						object.put("timeOfDeparture", _tmp);
					} else {
						object.put("timeOfDeparture", "");
					}

					object.put("shipAgencyName", Validator.isNotNull(shipAgencyName) ? shipAgencyName : "");

					if (itineraryScheduleId > 0) {
						List<VmaScheduleCargolist> cargolists = VmaScheduleCargolistLocalServiceUtil
								.findByitineraryScheduleId(itineraryScheduleId);
						JSONArray arrCargolist = JSONFactoryUtil
								.createJSONArray();
						if (cargolists != null) {
							for (VmaScheduleCargolist vmaScheduleCargolist : cargolists) {
								JSONObject _tmp = VMAUtils.object2Json(
										vmaScheduleCargolist,
										VmaScheduleCargolist.class,
										new String[] { "cargoCategory",
												"cargoType", "cargoCode",
												"portRegionCode",
												"portHarbourCode",
												"portWharfCode", "unit" });
								arrCargolist.put(_tmp);
							}
						}
						object.put("cargolist", arrCargolist);
					}

					if (vmaScheduleAnchorageId > 0) {
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

	public long countVmaItinerary_VmaScheduleAnchorage(String itineraryNo,
			String nameOfShip, String purposeCode, String anchoringDateFrom,
			String anchoringDateTo, String anchoringPortRegionCode,
			String anchoringPortHarbourCode, String anchoringPortWharfCode,
			String shipOwnerCode, String shipOperatorCode,
			String shipAgencyCode, Integer makePayment,
			Integer anchorageSupplement, String documentaryCode,
			Long itineraryScheduleId, Integer shipPosition, String flag,
			String imoNumber, String callSign, String registryNumber,
			String timeOfArrival) throws SystemException {
        long count = 0;
		String flagFrom = "vma_schedule_anchorage";
		String flagFilter = StringPool.BLANK;
		if (Validator.isNotNull(flag) && flag.length() > 0) {
			flagFrom = "(Select t.* from (Select NameOfShip, MAX(ID) as MaxID from vma_schedule_anchorage WHERE  (NoticeShipType <> 2 OR (NoticeShipType =2 and AnchoringDuration > 0)) and (ISNULL(AnchoringDateFrom)  = 0 OR ISNULL(AnchoringDateTo) = 0) and LENGTH(NameOfShip) > 0 GROUP BY NameOfShip) anchor INNER JOIN vma_schedule_anchorage t ON t.NameOfShip = anchor.NameOfShip and t.ID = anchor.MaxID GROUP BY t.NameOfShip)";
			flagFilter = " AND A2.ID = (SELECT MAX(lastAnchor.ID) FROM vma_schedule_anchorage lastAnchor WHERE A2.ItineraryNo = lastAnchor.ItineraryNo AND (NoticeShipType <> 2 OR (NoticeShipType =2 and AnchoringDuration > 0)) and (ISNULL(AnchoringDateFrom)  = 0 OR ISNULL(AnchoringDateTo) = 0) and LENGTH(NameOfShip) > 0)";
		}

		

		try {
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT count(DISTINCT A2.ID) AS total FROM vma_itinerary AS A1 LEFT JOIN "
					+ flagFrom + " AS A2 ON A1.ItineraryNo = A2.ItineraryNo" + flagFilter);
			query.append(" LEFT JOIN vma_itinerary_schedule A8 ON A2.ItineraryNo = A8.ItineraryNo and A2.ItineraryScheduleId = A8.ID");
			query.append(" LEFT JOIN dm_port_wharf AS A3 ON A2.AnchoringPortWharfCode = A3.PortWharfCode");

			query.append(" LEFT JOIN dm_port_harbour AS A4 ON A2.AnchoringPortHarbourCode = A4.PortHarbourCode");

			query.append(" LEFT JOIN dm_port_region AS A5 ON A2.AnchoringPortRegionCode = A5.PortRegionCode");

			query.append(" LEFT JOIN dm_ship_agency AS A6 ON A2.ShipAgencyCode = A6.ShipAgencyCode");

			query.append(" LEFT JOIN vma_ship as A7 ON A7.ID = (SELECT MAX(lastShip.ID) FROM vma_ship lastShip WHERE A1.CallSign = lastShip.CallSign and A1.IMONumber = lastShip.IMONumber and A1.RegistryNumber = lastShip.RegistryNumber ) ");

			query.append(" WHERE 1=1 AND A2.ID > 0 ");

			query.append(generateCondition(itineraryNo, nameOfShip,
					purposeCode, anchoringDateFrom, anchoringDateTo,
					anchoringPortRegionCode, anchoringPortHarbourCode,
					anchoringPortWharfCode, shipOwnerCode, shipOperatorCode,
					shipAgencyCode, makePayment, anchorageSupplement,
					documentaryCode, itineraryScheduleId, shipPosition,
					imoNumber, callSign, registryNumber, timeOfArrival));

			log.info("=========count vma_schedule_anchorage>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
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

	private String generateCondition(String itineraryNo, String nameOfShip,
			String purposeCode, String anchoringDateFrom,
			String anchoringDateTo, String anchoringPortRegionCode,
			String anchoringPortHarbourCode, String anchoringPortWharfCode,
			String shipOwnerCode, String shipOperatorCode,
			String shipAgencyCode, Integer makePayment,
			Integer anchorageSupplement, String documentaryCode,
			Long itineraryScheduleId, Integer shipPosition, String imoNumber,
			String callSign, String registryNumber, String timeOfArrival) {
		StringBuilder condition = new StringBuilder();

		if (shipPosition != null) {
			condition.append(" AND A1.ShipPosition =" + shipPosition);
		}

		if (Validator.isNotNull(nameOfShip)) {
			condition
					.append(" AND A1.NameOfShip LIKE \"%" + nameOfShip + "%\"");
		}

		if (Validator.isNotNull(purposeCode)) {
			condition.append(" AND A2.PurposeCode LIKE \"%" + purposeCode
					+ "%\"");
		}

		if (Validator.isNotNull(anchoringPortRegionCode)) {
			condition.append(" AND A2.AnchoringPortRegionCode = \""
					+ anchoringPortRegionCode + "\"");
		}

		if (Validator.isNotNull(anchoringPortHarbourCode)) {
			condition.append(" AND A2.AnchoringPortHarbourCode = \""
					+ anchoringPortHarbourCode + "\"");
		}

		if (Validator.isNotNull(anchoringPortWharfCode)) {
			condition.append(" AND A2.AnchoringPortWharfCode = \""
					+ anchoringPortWharfCode + "\"");
		}

		if (Validator.isNotNull(shipOwnerCode)) {
			condition.append(" AND A2.ShipOwnerCode = \"" + shipOwnerCode
					+ "\"");
		}

		if (Validator.isNotNull(shipOperatorCode)) {
			condition.append(" AND A2.ShipOperatorCode = \"" + shipOperatorCode
					+ "\"");
		}

		if (Validator.isNotNull(shipAgencyCode)) {
			condition.append(" AND A2.ShipAgencyCode = \"" + shipAgencyCode
					+ "\"");
		}

		if (Validator.isNotNull(anchoringDateFrom)) {
			Date date = null;

			try {
				date = FormatData.formatDDMMYYYY.parse(anchoringDateFrom);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				String strDate = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE);

				condition
						.append(" AND ((AnchoringDateFrom is not null and AnchoringDateFrom >= '"
								+ strDate
								+ " 00:00:00' and AnchoringDateTo is not null and AnchoringDateTo <= '"
								+ strDate
								+ " 23:59:59') OR (AnchoringDateFrom is not null and AnchoringDateFrom >= '"
								+ strDate
								+ " 00:00:00' and AnchoringDateTo is null and (TimeOfDeparture is null OR TimeOfDeparture <= '"
								+ strDate
								+ " 23:59:59')) OR (AnchoringDateFrom is null and ((TimeOfArrival is null and DATE_SUB(AnchoringDateTo,INTERVAL 1 DAY) >= '"
								+ strDate
								+ " 00:00:00') OR TimeOfArrival <= '"
								+ strDate
								+ " 23:59:59') and AnchoringDateTo is not null and AnchoringDateTo <= '"
								+ strDate + " 23:59:59'))");
			}
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

				condition.append(" AND (A1.TimeOfArrival BETWEEN '" + strDate
						+ " 00:00:00" + "' AND '" + strDate + " 23:59:59')");
			}
		}

		if (Validator.isNotNull(imoNumber)
				&& !imoNumber.isEmpty()
				&& Validator.isNotNull(callSign)
				&& !callSign.isEmpty()
				&& (Validator.isNull(registryNumber) || registryNumber
						.isEmpty())) {
			condition.append(" AND A1.IMONumber = '" + imoNumber
					+ "' AND A1.CallSign = '" + callSign + "'");
		} else if (Validator.isNull(imoNumber)
				&& imoNumber.isEmpty()
				&& Validator.isNull(callSign)
				&& callSign.isEmpty()
				&& (Validator.isNotNull(registryNumber) || !registryNumber
						.isEmpty())) {
			condition.append(" AND A1.RegistryNumber = '" + registryNumber
					+ "'");
		}

		if (Validator.isNotNull(anchoringDateTo)) {
			Date date = null;

			try {
				date = FormatData.formatDDMMYYYY.parse(anchoringDateTo);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (date != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				String strDate = calendar.get(Calendar.YEAR) + "-"
						+ (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE);

				condition.append(" AND A2.AnchoringDateTo BETWEEN '" + strDate
						+ " 00:00:00'");

				condition.append(" AND '" + strDate + " 23:59:59'");
			}
		}

		if (Validator.isNotNull(itineraryNo)) {
			condition.append(" AND A2.ItineraryNo = \"" + itineraryNo + "\"");
		}

		if (makePayment != null) {
			condition.append(" AND A2.makePayment = " + makePayment);
		}

		if (anchorageSupplement != null) {
			condition.append(" AND A2.anchorageSupplement = "
					+ anchorageSupplement);
		}

		if (itineraryScheduleId != null) {
			condition.append(" AND A2.itineraryScheduleId = "
					+ itineraryScheduleId);
		}

		if (Validator.isNotNull(documentaryCode)) {
			condition.append(" AND A2.documentaryCode = \"" + documentaryCode
					+ "\"");
		}

		return condition.toString();
	}

	public JSONArray getVmaScheduleAnchorageDuration(String itineraryNo,
			int start, int end) throws SystemException {
		

		JSONArray data = JSONFactoryUtil.createJSONArray();
		try {
			

			String query = "";
			query = "SELECT (SELECT SUM(AnchoringDuration) FROM vma_schedule_anchorage WHERE itineraryNo='"
					+ itineraryNo
					+ "' AND anchoragesupplement = 1) AS bo_sung, "
					+ "(SELECT SUM(AnchoringDuration) FROM vma_schedule_anchorage WHERE itineraryNo='"
					+ itineraryNo
					+ "' AND anchoragesupplement = 0) AS tinh_phi, "
					+ "(SELECT SUM(AnchorageFreeDuration) FROM vma_schedule_anchorage WHERE itineraryNo='"
					+ itineraryNo + "') AS khong_tinh_phi";

			log.info("=========select vma_schedule_anchorage_duration>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query);


			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString())
					.firstResult(start).maxResults(end - start).entityClass(Object.class).build();

			Iterator<Object[]> itr =queryFactory2.getResultList(builder).iterator();

			if (itr.hasNext()) {
				while (itr.hasNext()) {
					Object[] objects = itr.next();
					double anchoringDuration1 = 0;
					double anchoringDuration0 = 0;
					double anchorageFreeDuration = 0;
					try {
						anchoringDuration1 = Double.valueOf(String
								.valueOf(objects[0]));
					} catch (Exception e) {
						// nothing to do
					}
					try {
						anchoringDuration0 = Double.valueOf(String
								.valueOf(objects[1]));
					} catch (Exception e) {
						// nothing to do
					}
					try {
						anchorageFreeDuration = Double.valueOf(String
								.valueOf(objects[2]));
					} catch (Exception e) {
						// nothing to do
					}

					JSONObject object = JSONFactoryUtil.createJSONObject();

					object.put("anchoringDuration1", anchoringDuration1);
					object.put("anchoringDuration0", anchoringDuration0);
					object.put("anchorageFreeDuration", anchorageFreeDuration);
					object.put("itineraryNo", itineraryNo);

					data.put(object);
				}
			}

		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}

		return data;
	}

}
