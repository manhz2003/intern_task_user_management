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
import com.fds.nsw.nghiepvu.model.VmaShip;
@Service
@Slf4j
public class VmaShipFinderImpl extends
 BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaShip> queryFactory;

	

	public List<VmaShip> findVmaShip(String shipName, String shipBoat,
			String shipAgencyCode, String flagStateOfShip, String callSign,
			String shipOwnerCode, String shipOperatorCode, String shipTypeMT,
			String shipTypeCode, double gt, double dwt, double nt, double loa,
			double breadth, double shownDraftxA, double clearanceHeight,
			double shownDraftxF, double maxDraft, int start, int end)
			throws SystemException {

		

		try {
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT * FROM vma_ship WHERE 1 = 1");

			query.append(generateCondition1(shipName, shipBoat, shipAgencyCode,
					flagStateOfShip, callSign, shipOwnerCode, shipOperatorCode,
					shipTypeMT, shipTypeCode, gt, dwt, nt, loa, breadth,
					shownDraftxA, clearanceHeight, shownDraftxF, maxDraft));

			query.append(" ORDER BY ShipName ASC");

			log.info("=========select vms_ship>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString() + " | start = " + start + " | " + end);
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).firstResult(start).maxResults(end - start).entityClass(VmaShip.class).build();

			/*  */

			return (List<VmaShip>)  queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public List<VmaShip> findVmaShip(String shipName, String shipBoat,
			String shipTypeMT, String shipTypeCode, String tugBoatName,
			double gt, double dwt, double loa, String masterCertificateNo,
			double breadth, String chiefCertificateNo, String vrCode,
			double power, String shipOwnerCode, String nameOfMaster,
			String masterCertificateClass, String chiefOfEngineer,
			String chiefEngineerCertificateClass, int start, int end)
			throws SystemException {

		

		try {
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT * FROM vma_ship WHERE 1 = 1");

			query.append(generateCondition2(shipName, shipBoat, shipTypeMT,
					shipTypeCode, tugBoatName, gt, dwt, loa,
					masterCertificateNo, breadth, chiefCertificateNo, vrCode,
					power, shipOwnerCode, nameOfMaster, masterCertificateClass,
					chiefOfEngineer, chiefEngineerCertificateClass));

			query.append(" ORDER BY ShipName ASC");

			log.info("=========select vms_ship>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).firstResult(start).maxResults(end - start).entityClass(VmaShip.class).build();

			/*  */

			return (List<VmaShip>)  queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public long countVmaShip(String shipName, String shipBoat,
			String shipAgencyCode, String flagStateOfShip, String callSign,
			String shipOwnerCode, String shipOperatorCode, String shipTypeMT,
			String shipTypeCode, double gt, double dwt, double nt, double loa,
			double breadth, double shownDraftxA, double clearanceHeight,
			double shownDraftxF, double maxDraft) throws SystemException { long count = 0; try {
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT count(*) AS total FROM vma_ship WHERE 1 = 1");

			query.append(generateCondition1(shipName, shipBoat, shipAgencyCode,
					flagStateOfShip, callSign, shipOwnerCode, shipOperatorCode,
					shipTypeMT, shipTypeCode, gt, dwt, nt, loa, breadth,
					shownDraftxA, clearanceHeight, shownDraftxF, maxDraft));

			log.info("=========count vms_ship>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
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

	public long countVmaShip(String shipName, String shipBoat,
			String shipTypeMT, String shipTypeCode, String tugBoatName,
			double gt, double dwt, double loa, String masterCertificateNo,
			double breadth, String chiefCertificateNo, String vrCode,
			double power, String shipOwnerCode, String nameOfMaster,
			String masterCertificateClass, String chiefOfEngineer,
			String chiefEngineerCertificateClass) throws SystemException { long count = 0; try {
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT count(*) AS total FROM vma_ship WHERE 1 = 1");

			query.append(generateCondition2(shipName, shipBoat, shipTypeMT,
					shipTypeCode, tugBoatName, gt, dwt, loa,
					masterCertificateNo, breadth, chiefCertificateNo, vrCode,
					power, shipOwnerCode, nameOfMaster, masterCertificateClass,
					chiefOfEngineer, chiefEngineerCertificateClass));

			log.info("=========count vms_ship>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
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

	private String generateCondition1(String shipName, String shipBoat,
			String shipAgencyCode, String flagStateOfShip, String callSign,
			String shipOwnerCode, String shipOperatorCode, String shipTypeMT,
			String shipTypeCode, double gt, double dwt, double nt, double loa,
			double breadth, double shownDraftxA, double clearanceHeight,
			double shownDraftxF, double maxDraft) {
		StringBuilder condition = new StringBuilder();

		if (Validator.isNotNull(shipName)) {
			condition.append(" AND ShipName LIKE \"%" + shipName + "%\"");
		}

		if (Validator.isNotNull(shipBoat)) {
			condition.append(" AND ShipBoat = \"" + shipBoat + "\"");
		}

		if (Validator.isNotNull(shipAgencyCode)) {
			condition
					.append(" AND ShipAgencyCode = \"" + shipAgencyCode + "\"");
		}

		if (Validator.isNotNull(flagStateOfShip)) {
			condition.append(" AND FlagStateOfShip = \"" + flagStateOfShip
					+ "\"");
		}

		if (Validator.isNotNull(callSign)) {
			condition.append(" AND CallSign LIKE \"%" + callSign + "%\"");
		}

		if (Validator.isNotNull(shipOwnerCode)) {
			condition.append(" AND ShipOwnerCode = \"" + shipOwnerCode + "\"");
		}

		if (Validator.isNotNull(shipOperatorCode)) {
			condition.append(" AND ShipOperatorCode = \"" + shipOperatorCode
					+ "\"");
		}

		if (Validator.isNotNull(shipTypeMT)) {
			condition.append(" AND ShipTypeMT LIKE \"%" + shipTypeMT + "%\"");
		}

		if (Validator.isNotNull(shipTypeCode)) {
			condition.append(" AND ShipTypeCode LIKE \"%" + shipTypeCode
					+ "%\"");
		}

		if (gt >= 0) {
			condition.append(" AND GT = \"" + gt + "\"");
		}

		if (dwt >= 0) {
			condition.append(" AND DWT = \"" + dwt + "\"");
		}

		if (loa >= 0) {
			condition.append(" AND LOA = \"" + loa + "\"");
		}

		if (nt >= 0) {
			condition.append(" AND NT = \"" + nt + "\"");
		}

		if (breadth >= 0) {
			condition.append(" AND Breadth = \"" + breadth + "\"");
		}

		if (shownDraftxA >= 0) {
			condition.append(" AND ShownDraftxA = \"" + shownDraftxA + "\"");
		}

		if (clearanceHeight >= 0) {
			condition.append(" AND ClearanceHeight = \"" + clearanceHeight
					+ "\"");
		}

		if (shownDraftxF >= 0) {
			condition.append(" AND ShownDraftxF = \"" + shownDraftxF + "\"");
		}

		if (maxDraft >= 0) {
			condition.append(" AND MaxDraft = \"" + maxDraft + "\"");
		}

		return condition.toString();
	}

	private String generateCondition2(String shipName, String shipBoat,
			String shipTypeMT, String shipTypeCode, String tugBoatName,
			double gt, double dwt, double loa, String masterCertificateNo,
			double breadth, String chiefCertificateNo, String vrCode,
			double power, String shipOwnerCode, String nameOfMaster,
			String masterCertificateClass, String chiefOfEngineer,
			String chiefEngineerCertificateClass) {
		StringBuilder condition = new StringBuilder();

		if (Validator.isNotNull(shipName)) {
			condition.append(" AND ShipName LIKE \"%" + shipName + "%\"");
		}

		if (Validator.isNotNull(shipBoat)) {
			condition.append(" AND ShipBoat = \"" + shipBoat + "\"");
		}

		if (Validator.isNotNull(shipTypeMT)) {
			condition.append(" AND ShipTypeMT LIKE \"%" + shipTypeMT + "%\"");
		}

		if (Validator.isNotNull(shipTypeCode)) {
			condition.append(" AND ShipTypeCode LIKE \"%" + shipTypeCode
					+ "%\"");
		}

		if (Validator.isNotNull(tugBoatName)) {
			condition.append(" AND TugBoatName LIKE \"%" + tugBoatName + "%\"");
		}

		if (gt >= 0) {
			condition.append(" AND GT = \"" + gt + "\"");
		}

		if (dwt >= 0) {
			condition.append(" AND DWT = \"" + dwt + "\"");
		}

		if (loa >= 0) {
			condition.append(" AND LOA = \"" + loa + "\"");
		}

		if (breadth >= 0) {
			condition.append(" AND Breadth = \"" + breadth + "\"");
		}

		if (power >= 0) {
			condition.append(" AND Power = \"" + power + "\"");
		}

		if (Validator.isNotNull(shipOwnerCode)) {
			condition.append(" AND ShipOwnerCode = \"" + shipOwnerCode + "\"");
		}

		if (Validator.isNotNull(masterCertificateClass)
				|| Validator.isNotNull(masterCertificateNo)
				|| Validator.isNotNull(nameOfMaster)) {
			condition.append(" AND ( ");

			String orCondition = "";

			if (Validator.isNotNull(masterCertificateClass)) {
				condition.append(orCondition + "NameOfMaster LIKE \"%"
						+ masterCertificateClass + "%\"");
				orCondition = " OR ";
			}

			if (Validator.isNotNull(masterCertificateNo)) {
				condition.append(orCondition + "NameOfMaster LIKE \"%"
						+ masterCertificateNo + "%\"");
				orCondition = " OR ";
			}

			if (Validator.isNotNull(nameOfMaster)) {
				condition.append(orCondition + "NameOfMaster LIKE \"%"
						+ nameOfMaster + "%\"");
				orCondition = " OR ";
			}

			condition.append(" ) ");
		}

		if (Validator.isNotNull(chiefOfEngineer)
				|| Validator.isNotNull(chiefCertificateNo)
				|| Validator.isNotNull(chiefEngineerCertificateClass)) {
			condition.append(" AND ( ");

			String orCondition = "";

			if (Validator.isNotNull(chiefOfEngineer)) {
				condition.append(orCondition + "ChiefOfEngineer LIKE \"%"
						+ chiefOfEngineer + "%\"");
				orCondition = " OR ";
			}

			if (Validator.isNotNull(chiefCertificateNo)) {
				condition.append(orCondition + "ChiefOfEngineer LIKE \"%"
						+ chiefCertificateNo + "%\"");
				orCondition = " OR ";
			}

			if (Validator.isNotNull(chiefEngineerCertificateClass)) {
				condition.append(orCondition + "ChiefOfEngineer LIKE \"%"
						+ chiefEngineerCertificateClass + "%\"");
				orCondition = " OR ";
			}

			condition.append(" ) ");
		}

		return condition.toString();
	}

	public List<VmaShip> findVmaShip(Class<?> clazz, String className,
			String sql, int start, int end) throws SystemException {

		

		try {
			

			log.info("=========select vms_ship>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ sql);
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).firstResult(start).maxResults(end - start).entityClass(VmaShip.class).build();


			/*  */

			return (List<VmaShip>)  queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public long countVmaShip(String sql) throws SystemException { long count = 0; try {
			

			log.info("=========count vms_ship>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
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
