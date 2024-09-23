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
import java.util.Date;
import java.util.List;

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
import com.fds.nsw.nghiepvu.model.VmaScheduleTransfer;
@Service
@Slf4j
public class VmaScheduleTransferFinderImpl extends

		BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaScheduleTransfer> queryFactory;

	/*
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
			query.append("A1.CargoDescription");

			query.append(" FROM vma_itinerary AS A1 INNER JOIN vma_schedule_shifting AS A2 ON A1.ItineraryNo = A2.ItineraryNo");

			query.append(" WHERE 1=1 ");

			query.append(generateCondition(itineraryNo, portofAuthority,
					shiftingDate, requestState));

			query.append(" ORDER BY A2.ShiftingDate ASC");

			log.info("=========select vma_schedule_shifting>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).firstResult(start).maxResults(end - start).

			

			Iterator<Object[]> itr = (Iterator<Object[]>) QueryUtil.list(q,
					getDialect(), start, end).iterator();

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
					String cargoDescription = String.valueOf(objects[32]);

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
					object.put("imoNumber", imoNumber);
					object.put("callSign", callSign);
					object.put("vrCode", vrCode);
					object.put("registryNumber", registryNumber);

					object.put("gt", gt);
					object.put("nt", nt);
					object.put("dwt", dwt);
					object.put("loa", loa);

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

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).firstResult(start).maxResults(end - start).entityClass(XXX.class).build();

			  

			
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

*/}
