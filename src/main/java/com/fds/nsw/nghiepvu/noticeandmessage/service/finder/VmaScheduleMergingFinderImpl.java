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

import java.util.ArrayList;
import java.util.Collections;

import java.util.*;

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
import com.fds.nsw.nghiepvu.model.VmaScheduleMerging;
@Service
@Slf4j
public class VmaScheduleMergingFinderImpl extends

		BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaScheduleMerging> queryFactory;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<Object[]> queryFactory2;
	public JSONArray findVmaScheduleMerging(String itineraryNo,
			String portofAuthority, Long documentName, Integer documentYear,
			Integer noticeShipType, String shipOwnerCode,
			String shipOwnersName, String nameOfShip, String flagStateOfShip,
			String imoNumber, String callSign, String vrCode,
			String registryNumber, String shipOperatorCode,
			String shipOperatorName, String shipAgencyCode,
			String shipAgencyName, Double gt, Double nt, Double shownDraftxF,
			Double shownDraftxA, Double loa, Double dwt,
			long itineraryScheduleId, int start, int end)
			throws SystemException {

		

		JSONArray data = JSONFactoryUtil.createJSONArray();

		try {
			

			StringBuilder query = new StringBuilder();

			query.append("SELECT A1.ItineraryNo,");
			query.append("A1.PortofAuthority,");
			query.append("A1.DocumentName,");
			query.append("A1.DocumentYear,");
			query.append("A1.NoticeShipType,");
			query.append("A1.ShipBoat,");
			query.append("A1.ShipOwnerCode,");
			query.append("A1.ShipOwnersName,");
			query.append("A1.ShipOperatorCode,");
			query.append("A1.ShipOperatorName,");
			query.append("A1.ShipAgencyCode,");
			query.append("A1.ShipAgencyName,");
			query.append("A1.ShipAgencyAddress,");
			query.append("A1.ArrivalPortCode,");
			query.append("A1.PortRegionCode,");
			query.append("A1.PortHarbourCode,");
			query.append("A1.PortWharfCode,");
			query.append("A1.GT,");
			query.append("A1.NT,");
			query.append("A1.DWT,");
			query.append("A1.LOA,");			
			query.append("A1.MaxDraft,");
			query.append("A1.ShownDraftxF,");
			query.append("A1.ShownDraftxA,");
			query.append("A1.UnitLOA,");
			query.append("A1.UnitShownDraftxF,");
			query.append("A1.UnitShownDraftxA,");
			query.append("A1.UnitGRT,");
			query.append("A1.UnitNT,");
			query.append("A1.UnitDWT,");
			query.append("A1.UnitMaxDraft,");
			query.append("A1.MergeDateFrom,");
			query.append("A1.MergeDateTo,");
			
			query.append("A1.ItineraryScheduleId,");
			query.append("A1.Breadth,");
			query.append("A1.ClearanceHeight,");
			query.append("A1.Power,");
			query.append("A1.PurposeCode,");
			query.append("A1.PurposeSpecified,");
			query.append("A1.ShipTypeMT,");
			query.append("A1.ShipTypeCode,");
			query.append("A1.ShipLashIMONumber,");
			query.append("A1.ShipLashCallSign,");
			query.append("A1.ShipLashRegistryNumber,");
			query.append("A1.ShipLashVRCode,");
			query.append("A1.ShipLashFlagStateOfShip,");			
			query.append("A1.MakePayment,");
			query.append("A1.DocumentaryCode,");			
			query.append("A1.ShipLashName,");
			query.append("A2.NameOfShip,");
			query.append("A2.FlagStateOfShip,");
			query.append("A2.IMONumber,");
			query.append("A2.CallSign,");
			query.append("A2.VRCode,");
			query.append("A2.RegistryNumber,");
			query.append("A1.ID");
			query.append(" FROM vma_schedule_merging AS A1");
			query.append(" INNER JOIN vma_itinerary AS A2");
			query.append(" ON A1.ItineraryNo = A2.ItineraryNo");

			query.append(" WHERE 1=1 ");

			query.append(generateCondition(itineraryNo, portofAuthority,
					documentName, documentYear, noticeShipType, shipOwnerCode,
					shipOwnersName, nameOfShip, flagStateOfShip, imoNumber,
					callSign, vrCode, registryNumber, shipOperatorCode,
					shipOperatorName, shipAgencyCode, shipAgencyName, gt, nt,
					shownDraftxF, shownDraftxA, loa, dwt, itineraryScheduleId));

			query.append(" ORDER BY MergeDateFrom ASC");

			log.info("=========select vma_schedule_merging INNER JOIN vma_itinerary>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString())
					.firstResult(start).maxResults(end - start).entityClass(Object.class).build();

			Iterator<Object[]> itr =queryFactory2.getResultList(builder).iterator();

			
			if (itr.hasNext()) {
				while (itr.hasNext()) {
					Object[] objects = itr.next();

					itineraryNo = String.valueOf(objects[0]);
					portofAuthority = String.valueOf(objects[1]);
					documentName = GetterUtil.getLong(objects[2]);
					documentYear = GetterUtil.getInteger(objects[3]);
					noticeShipType = GetterUtil.getInteger(objects[4]);
					String shipBoat = String.valueOf(objects[5]);
					shipOwnerCode = String.valueOf(objects[6]);
					shipOwnersName = String.valueOf(objects[7]);
					shipOperatorCode = String.valueOf(objects[8]);
					shipOperatorName = String.valueOf(objects[9]);
					shipAgencyCode = String.valueOf(objects[10]);
					shipAgencyName = String.valueOf(objects[11]);
					String shipAgencyAddress = String.valueOf(objects[12]);

					String arrivalPortCode = String.valueOf(objects[13]);
					String PortRegionCode = String.valueOf(objects[14]);
					String portHarbourCode = String.valueOf(objects[15]);
					String portWharfCode = String.valueOf(objects[16]);
					gt = GetterUtil.getDouble(objects[17]);
					nt = GetterUtil.getDouble(objects[18]);
					dwt = GetterUtil.getDouble(objects[19]);
					loa = GetterUtil.getDouble(objects[20]);
					double maxDraft = GetterUtil.getDouble(objects[21]);
					shownDraftxF = GetterUtil.getDouble(objects[22]);
					shownDraftxA = GetterUtil.getDouble(objects[23]);
					String unitLOA = String.valueOf(objects[24]);
					String unitShownDraftxF = String.valueOf(objects[25]);
					String unitShownDraftxA = String.valueOf(objects[26]);
					String unitGRT = String.valueOf(objects[27]);
					String unitNT = String.valueOf(objects[28]);
					String unitDWT = String.valueOf(objects[29]);
					String unitMaxDraft = String.valueOf(objects[30]);
					String mergeDateFrom = "";
					String mergeDateTo = "";
					try {
						Date mergeDateFromDate = (Date) objects[31];
						Date mergeDateToDate = (Date) objects[32];

						mergeDateFrom = FormatData.formatDateShort3
								.format(mergeDateFromDate);
						mergeDateTo = FormatData.formatDateShort3
								.format(mergeDateToDate);
					} catch (Exception e) {
						mergeDateFrom = "";
						mergeDateTo = "";
					}

					itineraryScheduleId = GetterUtil.getLong(objects[33]);
					double breadth = GetterUtil.getDouble(objects[34]);
					double clearanceHeight = GetterUtil.getDouble(objects[35]);
					double power = GetterUtil.getDouble(objects[36]);
					String purposeCode = String.valueOf(objects[37]);
					String purposeSpecified = String.valueOf(objects[38]);
					String shipTypeMT = String.valueOf(objects[39]);
					String shipTypeCode = String.valueOf(objects[40]);
					String shipLashIMONumber = String.valueOf(objects[41]);
					String shipLashCallSign = String.valueOf(objects[42]);
					String shipLashRegistryNumber = String.valueOf(objects[43]);
					String shipLashVRCode = String.valueOf(objects[44]);
					String shipLashFlagStateOfShip = String.valueOf(objects[45]);
					String makePayment = String.valueOf(objects[46]);
					String documentaryCode = String.valueOf(objects[47]);
					String shipLashName = String.valueOf(objects[48]);

					nameOfShip = String.valueOf(objects[49]);
					flagStateOfShip = String.valueOf(objects[50]);
					imoNumber = String.valueOf(objects[51]);
					callSign = String.valueOf(objects[52]);
					vrCode = String.valueOf(objects[53]);
					registryNumber = String.valueOf(objects[54]);
					long vmaScheduleMergingId = GetterUtil.getLong(objects[55]);

					JSONObject object = JSONFactoryUtil.createJSONObject();

					object.put("itineraryNo", itineraryNo);
					object.put("portofAuthority", portofAuthority);
					object.put("documentName", documentName);
					object.put("documentYear", documentYear);
					object.put("noticeShipType", noticeShipType);
					object.put("shipBoat", shipBoat);
					object.put("shipOwnerCode", shipOwnerCode);
					object.put("shipOwnersName", shipOwnersName);
					object.put("shipOperatorCode", shipOperatorCode);
					object.put("shipOperatorName", shipOperatorName);
					object.put("shipAgencyCode", shipAgencyCode);
					object.put("shipAgencyName", shipAgencyName);
					object.put("shipAgencyAddress", shipAgencyAddress);

					object.put("arrivalPortCode", arrivalPortCode);
					object.put("portRegionCode", PortRegionCode);
					object.put("portHarbourCode", portHarbourCode);
					object.put("portWharfCode", portWharfCode);
					object.put("gt", gt);
					object.put("nt", nt);
					object.put("dwt", dwt);
					object.put("loa", loa);
					object.put("maxDraft", maxDraft);
					object.put("shownDraftxF", shownDraftxF);
					object.put("shownDraftxA", shownDraftxA);
					object.put("unitLOA", unitLOA);
					object.put("unitShownDraftxF", unitShownDraftxF);
					object.put("unitShownDraftxA", unitShownDraftxA);
					object.put("unitGRT", unitGRT);
					object.put("unitNT", unitNT);
					object.put("unitDWT", unitDWT);
					object.put("unitMaxDraft", unitMaxDraft);
					object.put("mergeDateFrom", mergeDateFrom);
					object.put("mergeDateTo", mergeDateTo);

					object.put("itineraryScheduleId", itineraryScheduleId);

					object.put("breadth", breadth);
					object.put("clearanceHeight", clearanceHeight);
					object.put("power", power);
					object.put("purposeCode", purposeCode);
					object.put("purposeSpecified", purposeSpecified);
					object.put("shipTypeMT", shipTypeMT);
					object.put("shipTypeCode", shipTypeCode);
					object.put("shipLashIMONumber", shipLashIMONumber);
					object.put("shipLashCallSign", shipLashCallSign);
					object.put("shipLashRegistryNumber", shipLashRegistryNumber);
					object.put("shipLashVRCode", shipLashVRCode);
					object.put("shipLashFlagStateOfShip", shipLashFlagStateOfShip);
					object.put("makePayment", makePayment);
					object.put("documentaryCode", documentaryCode);
					object.put("shipLashName", shipLashName);

					object.put("nameOfShip", nameOfShip);
					object.put("flagStateOfShip", flagStateOfShip);
					object.put("imoNumber", imoNumber);
					object.put("callSign", callSign);
					object.put("vrCode", vrCode);
					object.put("registryNumber", registryNumber);

					object.put("vmaScheduleMergingId", vmaScheduleMergingId);


					data.put(object);

				}
			}

		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
		}

		return data;
	}

	public long countVmaScheduleMerging(String itineraryNo,
			String portofAuthority, Long documentName, Integer documentYear,
			Integer noticeShipType, String shipOwnerCode,
			String shipOwnersName, String nameOfShip, String flagStateOfShip,
			String imoNumber, String callSign, String vrCode,
			String registryNumber, String shipOperatorCode,
			String shipOperatorName, String shipAgencyCode,
			String shipAgencyName, Double gt, Double nt, Double shownDraftxF,
			Double shownDraftxA, Double loa, Double dwt,
			long itineraryScheduleId) throws SystemException {
		long count = 0;
		try {
			StringBuilder query = new StringBuilder();
			query.append("SELECT count(*) AS total");
			query.append(" FROM vma_schedule_merging AS A1");
			query.append(" INNER JOIN vma_itinerary AS A2");
			query.append(" ON A1.ItineraryNo = A2.ItineraryNo");
			query.append(" WHERE 1=1 ");

			query.append(generateCondition(itineraryNo, portofAuthority,
					documentName, documentYear, noticeShipType, shipOwnerCode,
					shipOwnersName, nameOfShip, flagStateOfShip, imoNumber,
					callSign, vrCode, registryNumber, shipOperatorCode,
					shipOperatorName, shipAgencyCode, shipAgencyName, gt, nt,
					shownDraftxF, shownDraftxA, loa, dwt, itineraryScheduleId));

			log.info("=========count vma_schedule_merging INNER JOIN vma_itinerary>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Long.class).build();

			count = (Long) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
		}
		return count;
	}

	private String generateCondition(String itineraryNo,
			String portofAuthority, Long documentName, Integer documentYear,
			Integer noticeShipType, String shipOwnerCode,
			String shipOwnersName, String nameOfShip, String flagStateOfShip,
			String imoNumber, String callSign, String vrCode,
			String registryNumber, String shipOperatorCode,
			String shipOperatorName, String shipAgencyCode,
			String shipAgencyName, Double gt, Double nt, Double shownDraftxF,
			Double shownDraftxA, Double loa, Double dwt,
			long itineraryScheduleId) {
		StringBuilder condition = new StringBuilder();

		if (Validator.isNotNull(itineraryNo)) {
			condition.append(" AND A1.ItineraryNo = \"" + itineraryNo + "\"");
		}

		if (noticeShipType != null) {
			condition.append(" AND A1.NoticeShipType = " + noticeShipType);
		}

		if (documentYear != null) {
			condition.append(" AND A1.DocumentYear = " + documentYear);
		}

		if (documentName != null) {
			condition.append(" AND A1.DocumentName = " + documentName);
		}

		if (Validator.isNotNull(portofAuthority)) {
			condition.append(" AND A1.PortofAuthority = \"" + portofAuthority
					+ "\"");
		}

		if (Validator.isNotNull(shipOwnerCode)) {
			condition.append(" AND A1.ShipOwnerCode = \"" + shipOwnerCode
					+ "\"");
		}

		if (Validator.isNotNull(shipOwnersName)) {
			condition.append(" AND A1.ShipOwnersName LIKE \"%" + shipOwnersName
					+ "%\"");
		}

		if (Validator.isNotNull(shipOperatorCode)) {
			condition.append(" AND A1.ShipOperatorCode = \"" + registryNumber
					+ "\"");
		}

		if (Validator.isNotNull(shipAgencyCode)) {
			condition.append(" AND A1.ShipAgencyCode = \"" + shipAgencyCode
					+ "\"");
		}

		if (Validator.isNotNull(shipOperatorName)) {
			condition.append(" AND A1.ShipOperatorName LIKE \"%"
					+ shipOperatorName + "%\"");
		}

		if (Validator.isNotNull(shipAgencyName)) {
			condition.append(" AND A1.ShipAgencyName LIKE \"%" + shipAgencyName
					+ "%\"");
		}

		if (gt != null) {
			condition.append(" AND A1.GT = \"" + gt + "\"");
		}

		if (nt != null) {
			condition.append(" AND A1.NT = \"" + nt + "\"");
		}

		if (shownDraftxF != null) {
			condition.append(" AND A1.ShownDraftxF = \"" + shownDraftxF + "\"");
		}

		if (shownDraftxA != null) {
			condition.append(" AND A1.shownDraftxA = \"" + shownDraftxA + "\"");
		}

		if (loa != null) {
			condition.append(" AND A1.LOA = \"" + loa + "\"");
		}

		if (dwt != null) {
			condition.append(" AND A1.DWT = \"" + dwt + "\"");
		}

		if (Validator.isNotNull(nameOfShip)) {
			condition
					.append(" AND A2.NameOfShip LIKE \"%" + nameOfShip + "%\"");
		}

		if (Validator.isNotNull(flagStateOfShip)) {
			condition.append(" AND A2.FlagStateOfShip LIKE \"%" + flagStateOfShip
					+ "%\"");
		}

		if (Validator.isNotNull(callSign)) {
			condition.append(" AND A2.CallSign = \"" + callSign + "\"");
		}

		if (Validator.isNotNull(imoNumber)) {
			condition.append(" AND A2.ImoNumber = \"" + imoNumber + "\"");
		}

		if (Validator.isNotNull(vrCode)) {
			condition.append(" AND A2.VRCode = \"" + vrCode + "\"");
		}

		if (Validator.isNotNull(registryNumber)) {
			condition.append(" AND A2.RegistryNumber = \"" + registryNumber
					+ "\"");
		}

		if (Validator.isNotNull(itineraryScheduleId) && itineraryScheduleId >= 0) {
			condition.append(" AND A1.ItineraryScheduleId = "
					+ itineraryScheduleId);
		}

		return condition.toString();
	}
	
	public List<VmaScheduleMerging> findVmaScheduleMerging(Class<?> clazz,
			String className, String sql, int start, int end)
			throws SystemException {


		try {
			QueryBuilder builder =QueryBuilder.builder().sqlQuery(true).queryString(sql)
					.firstResult(start).maxResults(end - start).entityClass(VmaScheduleMerging.class).build();

			/* QueryPos qPos = QueryPos.getInstance(q); */

			return (List<VmaScheduleMerging>) queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
		}
	}

	public long countVmaScheduleMerging(String sql) throws SystemException {
		long count = 0L;
		try {


			log.info("=========count VmaScheduleMerging>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ sql);

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(Long.class).build();

			/*  */

			
			count = (Long) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return count;
	}

}
