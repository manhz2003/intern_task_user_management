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
import com.fds.nsw.nghiepvu.model.DmMaritime;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.portlet.baocao.BaoCaoConstant;
import vn.gt.portlet.phuongtien.VMAUtils;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import com.fds.flex.common.ultility.GetterUtil;
import com.fds.flex.common.utility.string.StringPool;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.fds.flex.common.utility.string.StringPool;
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
import com.fds.nsw.nghiepvu.model.VmaTransactionSlip;
@Service
@Slf4j
public class VmaTransactionSlipFinderImpl extends

		BasePersistence {
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaTransactionSlip> queryFactory;

	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<Object[]> queryFactory2;

	public List<VmaTransactionSlip> findVmaTransactionSlip(Class<?> clazz,
														   String className, String sql, int start, int end)
			throws SystemException {

try {


	log.info("=========select vma_transaction_slip>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
			+ sql);
	QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).firstResult(start).maxResults(end - start).entityClass(VmaTransactionSlip.class).build();

	return (List<VmaTransactionSlip>)  queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public long countVmaTransactionSlip(String sql) throws SystemException {
		long count = 0;
try {



	/* QueryPos qPos = QueryPos.getInstance(q); */

	QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(Long.class).build();

	/*  */


	count = (Long) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
return count;
	}

	public JSONArray findDsTBP_DichVu(String itineraryNo, String tugboatCode,
									  String portofAuthority, String imoNumber, String callSign, String registryNumber,
									  String nameOfShip, String shipAgencyCode, String shipAgencyName, String shipOwnerName, String documentaryNo, String documentaryIssued, String paymentDate,String paymentStatus, String currentPaymentStatus, int start, int end) throws SystemException {
		
		JSONArray array = JSONFactoryUtil.createJSONArray();

		try {
			
			StringBuilder query = new StringBuilder();

			String sql = "SELECT distinct vma_transaction_slip.paymentStatus,vma_schedule_tugboat_list.ShipCode,vma_transaction_slip.ShipAgencyName,vma_transaction_slip.NameOfShip,"
					+ "vma_transaction_slip.CallSign,vma_transaction_slip.GT,vma_transaction_slip.DWT,vma_schedule_tugboat_list.Power,vma_transaction_slip.DocumentaryCode,"
					+ "vma_transaction_slip.DocumentaryIssued,vma_transaction_slip.VndTotalAmount,vma_transaction_slip.UsdTotalAmount,vma_transaction_slip.PaymentCategory,vma_transaction_slip.fromDate, vma_transaction_slip.toDate FROM vma_transaction_slip"
					+ " inner join vma_schedule_tugboat_list on vma_transaction_slip.DocumentaryCode = vma_schedule_tugboat_list.DocumentaryCode AND MakePayment = 1"
					+ " WHERE 1=1 ";

			if (Validator.isNotNull(portofAuthority)
					&& portofAuthority.trim().length() > 0) {
				query.append(" AND vma_transaction_slip.PortofAuthority = '"
						+ portofAuthority + "'");
			}

			if (Validator.isNotNull(tugboatCode)
					&& tugboatCode.trim().length() > 0) {
				query.append(" AND vma_schedule_tugboat_list.ShipCode = '"
						+ tugboatCode + "'");
			}


			StringBuilder condition = new StringBuilder();

			if (Validator.isNotNull(itineraryNo)) {
				condition.append(VMAUtils.buildSQLCondition("vma_transaction_slip.ItineraryNo", "'"
						+ itineraryNo + "'", "AND", StringPool.EQUAL));
			}

			if (Validator.isNotNull(documentaryNo)) {
				condition.append(VMAUtils.buildSQLCondition("vma_transaction_slip.DocumentaryNo", "'%"
						+ documentaryNo + "%'", "AND", StringPool.LIKE));
			}

			if (Validator.isNotNull(documentaryIssued)) {

				Date date = null;

				try {
					date = FormatData.formatDDMMYYYY.parse(documentaryIssued);
				} catch (Exception e) {
					log.error(e.getMessage());
				}

				if (date != null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					String strDate = calendar.get(Calendar.YEAR) + "-"
							+ (calendar.get(Calendar.MONTH) + 1) + "-"
							+ calendar.get(Calendar.DATE);

					condition.append(VMAUtils.buildSQLCondition(
							"vma_transaction_slip.DocumentaryIssued", "'" + strDate + " 00:00:00'"
									+ " AND '" + strDate + " 23:59:59'", "AND",
							StringPool.BETWEEN));
				}
			}

			if (Validator.isNotNull(paymentDate)) {
				Date date = null;

				try {
					date = FormatData.formatDDMMYYYY.parse(paymentDate);
				} catch (Exception e) {
					log.error(e.getMessage());
				}

				if (date != null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					String strDate = calendar.get(Calendar.YEAR) + "-"
							+ (calendar.get(Calendar.MONTH) + 1) + "-"
							+ calendar.get(Calendar.DATE);

					condition.append(VMAUtils.buildSQLCondition("vma_transaction_slip.PaymentDate", "'"
							+ strDate + " 00:00:00'" + " AND '" + strDate
							+ " 23:59:59'", "AND", StringPool.BETWEEN));
				}
			}

			if (Validator.isNotNull(paymentStatus)) {
				condition.append(VMAUtils.buildSQLCondition("vma_transaction_slip.paymentStatus", "("
						+ paymentStatus + ")", "AND", "IN"));
			}


			if (Validator.isNotNull(imoNumber)) {
				condition.append(VMAUtils.buildSQLCondition("vma_transaction_slip.imoNumber", "'%"
						+ imoNumber + "%'", "AND", StringPool.LIKE));
			}

			if (Validator.isNotNull(callSign)) {
				condition.append(VMAUtils.buildSQLCondition("vma_transaction_slip.callSign", "'%"
						+ callSign + "%'", "AND", StringPool.LIKE));
			}

			if (Validator.isNotNull(callSign)) {
				condition.append(VMAUtils.buildSQLCondition("vma_transaction_slip.RegistryNumber", "'%"
						+ registryNumber + "%'", "AND", StringPool.LIKE));
			}

			if (Validator.isNotNull(nameOfShip)) {
				condition.append(VMAUtils.buildSQLCondition("vma_transaction_slip.nameOfShip", "'%"
						+ nameOfShip + "%'", "AND", StringPool.LIKE));
			}

			if (Validator.isNotNull(shipAgencyName)) {
				condition.append(VMAUtils.buildSQLCondition("vma_transaction_slip.shipAgencyName", "'%"
						+ shipAgencyName + "%'", "AND", StringPool.LIKE));
			}

			if (Validator.isNotNull(shipOwnerName)) {
				condition.append(VMAUtils.buildSQLCondition("vma_transaction_slip.shipOwnerName", "'%"
						+ shipOwnerName + "%'", "AND", StringPool.LIKE));
			}

			if (Validator.isNotNull(shipAgencyCode)) {
				condition.append(VMAUtils.buildSQLCondition("vma_transaction_slip.shipAgencyCode", "'"
						+ shipAgencyCode + "'", "AND", StringPool.EQUAL));
			}


			if (Validator.isNotNull(currentPaymentStatus) && (!currentPaymentStatus.equals("0")) ) {
				condition.append(VMAUtils.buildSQLCondition("vma_transaction_slip.paymentStatus",  "'"
						+ currentPaymentStatus + "'", "AND", StringPool.EQUAL));
			}

			condition.append(" ORDER BY vma_transaction_slip.DocumentaryIssued DESC ");


			sql = sql + query.toString() + " " + condition.toString();

			log.info("=========select findDsTBP_DichVu >>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ sql);

			QueryBuilder builder =QueryBuilder.builder().sqlQuery(true).queryString(sql)
					.firstResult(start).maxResults(end - start).entityClass(Object.class).build();
			Iterator<Object[]> itr = queryFactory2.getResultList(builder).iterator();

			if (itr.hasNext()) {
				while (itr.hasNext()) {
					Object[] objects = itr.next();

					int ipaymentStatus = GetterUtil.getInteger(objects[0]);
					tugboatCode = String.valueOf(objects[1]);
					String sShipAgencyName = String.valueOf(objects[2]);
					String sNameOfShip = String.valueOf(objects[3]);
					String sCallSign = String.valueOf(objects[4]);
					double gt = GetterUtil.getDouble(objects[5]);
					double dwt = GetterUtil.getDouble(objects[6]);
					double power = GetterUtil.getDouble(objects[7]);
					String documentaryCode = String.valueOf(objects[8]);
					Date dDocumentaryIssued = (Date) objects[9];
					double vndTotalAmount = GetterUtil.getDouble(objects[10]);
					double usdTotalAmount = GetterUtil.getDouble(objects[11]);
					int paymentCategory = GetterUtil.getInteger(objects[12]);
					Date frmDate = (Date) objects[13];
					Date tDate = (Date) objects[14];

					JSONObject object = JSONFactoryUtil.createJSONObject();
					object.put("paymentStatus", ipaymentStatus);
					object.put("tugboatCode", tugboatCode);
					object.put("shipAgencyName", sShipAgencyName);
					object.put("nameOfShip", sNameOfShip);
					object.put("callSign", sCallSign);
					object.put("gt", gt);
					object.put("dwt", dwt);
					object.put("power", power);
					object.put("documentaryCode", documentaryCode);

					String dateDocumentaryIssued = StringPool.BLANK;

					if (dDocumentaryIssued != null) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(dDocumentaryIssued);

						int day = calendar.get(Calendar.DATE);

						int month = calendar.get(Calendar.MONTH) + 1;

						int year = calendar.get(Calendar.YEAR);

						dateDocumentaryIssued = (day < 10 ? "0" + day : day)
								+ StringPool.SLASH
								+ (month < 10 ? "0" + month : month)
								+ StringPool.SLASH + year;
					}
					object.put("documentaryIssued", dateDocumentaryIssued);
					object.put("vndTotalAmount", vndTotalAmount);
					object.put("usdTotalAmount", usdTotalAmount);
					object.put("paymentCategory", paymentCategory);

					String fromDate = StringPool.BLANK;
					String toDate = StringPool.BLANK;
					if (frmDate != null) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(frmDate);

						int day = calendar.get(Calendar.DATE);

						int month = calendar.get(Calendar.MONTH) + 1;

						int year = calendar.get(Calendar.YEAR);

						fromDate = (day < 10 ? "0" + day : day)
								+ StringPool.SLASH
								+ (month < 10 ? "0" + month : month)
								+ StringPool.SLASH + year;
					}
					if (tDate != null) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(tDate);

						int day = calendar.get(Calendar.DATE);

						int month = calendar.get(Calendar.MONTH) + 1;

						int year = calendar.get(Calendar.YEAR);

						toDate = (day < 10 ? "0" + day : day)
								+ StringPool.SLASH
								+ (month < 10 ? "0" + month : month)
								+ StringPool.SLASH + year;
					}
					object.put("fromDate", fromDate);
					object.put("toDate", toDate);

					array.put(object);
				}
			}
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return array;
	}

	public List<VmaTransactionSlip> findDsTBP_ChuyenTuyen(String itineraryNo,
														  String portofAuthority, String imoNumber, String callSign,
														  String registryNumber,
														  String nameOfShip, String shipAgencyCode, String shipAgencyName, String shipOwnerName, String documentaryNo, String documentaryIssued, String paymentDate, String paymentStatus, String currentPaymentStatus,
														  int start, int end) throws SystemException {
		
		try {
			
			StringBuilder query = new StringBuilder();
			String sql = "SELECT * FROM vma_transaction_slip WHERE 1 = 1 ";

			if (Validator.isNotNull(portofAuthority)
					&& portofAuthority.trim().length() > 0) {
				query.append(" AND vma_transaction_slip.PortofAuthority = '"
						+ portofAuthority + "'");
			}


			StringBuilder condition = new StringBuilder();

			if (Validator.isNotNull(itineraryNo)) {
				condition.append(VMAUtils.buildSQLCondition("vma_transaction_slip.ItineraryNo", "'"
						+ itineraryNo + "'", "AND", StringPool.EQUAL));
			}

			if (Validator.isNotNull(documentaryNo)) {
				condition.append(VMAUtils.buildSQLCondition("vma_transaction_slip.DocumentaryNo", "'%"
						+ documentaryNo + "%'", "AND", StringPool.LIKE));
			}

			if (Validator.isNotNull(documentaryIssued)) {

				Date date = null;

				try {
					date = FormatData.formatDDMMYYYY.parse(documentaryIssued);
				} catch (Exception e) {
					log.error(e.getMessage());
				}

				if (date != null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					String strDate = calendar.get(Calendar.YEAR) + "-"
							+ (calendar.get(Calendar.MONTH) + 1) + "-"
							+ calendar.get(Calendar.DATE);

					condition.append(VMAUtils.buildSQLCondition(
							"vma_transaction_slip.DocumentaryIssued", "'" + strDate + " 00:00:00'"
									+ " AND '" + strDate + " 23:59:59'", "AND",
							StringPool.BETWEEN));
				}
			}

			if (Validator.isNotNull(paymentDate)) {
				Date date = null;

				try {
					date = FormatData.formatDDMMYYYY.parse(paymentDate);
				} catch (Exception e) {
					log.error(e.getMessage());
				}

				if (date != null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					String strDate = calendar.get(Calendar.YEAR) + "-"
							+ (calendar.get(Calendar.MONTH) + 1) + "-"
							+ calendar.get(Calendar.DATE);

					condition.append(VMAUtils.buildSQLCondition("vma_transaction_slip.PaymentDate", "'"
							+ strDate + " 00:00:00'" + " AND '" + strDate
							+ " 23:59:59'", "AND", StringPool.BETWEEN));
				}
			}

			if (Validator.isNotNull(paymentStatus)) {
				condition.append(VMAUtils.buildSQLCondition("vma_transaction_slip.paymentStatus", "("
						+ paymentStatus + ")", "AND", "IN"));
			}


			if (Validator.isNotNull(imoNumber)) {
				condition.append(VMAUtils.buildSQLCondition("vma_transaction_slip.imoNumber", "'%"
						+ imoNumber + "%'", "AND", StringPool.LIKE));
			}

			if (Validator.isNotNull(callSign)) {
				condition.append(VMAUtils.buildSQLCondition("vma_transaction_slip.callSign", "'%"
						+ callSign + "%'", "AND", StringPool.LIKE));
			}

			if (Validator.isNotNull(callSign)) {
				condition.append(VMAUtils.buildSQLCondition("vma_transaction_slip.RegistryNumber", "'%"
						+ registryNumber + "%'", "AND", StringPool.LIKE));
			}

			if (Validator.isNotNull(nameOfShip)) {
				condition.append(VMAUtils.buildSQLCondition("vma_transaction_slip.nameOfShip", "'%"
						+ nameOfShip + "%'", "AND", StringPool.LIKE));
			}

			if (Validator.isNotNull(shipAgencyName)) {
				condition.append(VMAUtils.buildSQLCondition("vma_transaction_slip.shipAgencyName", "'%"
						+ shipAgencyName + "%'", "AND", StringPool.LIKE));
			}

			if (Validator.isNotNull(shipOwnerName)) {
				condition.append(VMAUtils.buildSQLCondition("vma_transaction_slip.shipOwnerName", "'%"
						+ shipOwnerName + "%'", "AND", StringPool.LIKE));
			}

			if (Validator.isNotNull(shipAgencyCode)) {
				condition.append(VMAUtils.buildSQLCondition("vma_transaction_slip.shipAgencyCode", "'"
						+ shipAgencyCode + "'", "AND", StringPool.EQUAL));
			}


			if (Validator.isNotNull(currentPaymentStatus) && (!currentPaymentStatus.equals("0")) ) {
				condition.append(VMAUtils.buildSQLCondition("vma_transaction_slip.paymentStatus",  "'"
						+ currentPaymentStatus + "'", "AND", StringPool.EQUAL));
			}

			condition.append(" ORDER BY vma_transaction_slip.DocumentaryIssued DESC ");


			sql = sql + query.toString() + " " + condition.toString();

			log.info("=========select findDsTBP_ChuyenTuyen>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ sql);



			QueryBuilder builder =QueryBuilder.builder().sqlQuery(true).queryString(sql)
					.firstResult(start).maxResults(end - start).entityClass(VmaTransactionSlip.class).build();
			return (List<VmaTransactionSlip>) queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public long countDsTBP(String itineraryNo, String tugboatCode, String imoNumber,
						   String callSign, String registryNumber, String portofAuthority,
						   String nameOfShip, String shipAgencyCode, String shipAgencyName, String shipOwnerName, String documentaryNo, String documentaryIssued, String paymentDate, String paymentStatus, String currentPaymentStatus,
						   int flag) throws SystemException {
		long count = 0;
		String innerJoin = StringPool.BLANK;
		if (flag == 1) {
			innerJoin = " inner join vma_schedule_tugboat_list on vma_transaction_slip.DocumentaryCode = vma_schedule_tugboat_list.DocumentaryCode AND MakePayment = 1";
		}
		try {
			
			StringBuilder query = new StringBuilder();

			String sql = "SELECT count(DISTINCT vma_transaction_slip.id) as total FROM vma_transaction_slip "
					+ innerJoin + " WHERE 1=1 ";

			if (Validator.isNotNull(portofAuthority)
					&& portofAuthority.trim().length() > 0) {
				query.append(" AND vma_transaction_slip.PortofAuthority = '"
						+ portofAuthority + "'");
			}

			if (flag == 1) {
				if (Validator.isNotNull(tugboatCode)
						&& tugboatCode.trim().length() > 0) {
					query.append(" AND vma_schedule_tugboat_list.ShipCode = '"
							+ tugboatCode + "'");
				}
			}

			StringBuilder condition = new StringBuilder();

			if (Validator.isNotNull(itineraryNo)) {
				condition.append(VMAUtils.buildSQLCondition("vma_transaction_slip.ItineraryNo", "'"
						+ itineraryNo + "'", "AND", StringPool.EQUAL));
			}

			if (Validator.isNotNull(documentaryNo)) {
				condition.append(VMAUtils.buildSQLCondition("vma_transaction_slip.DocumentaryNo", "'%"
						+ documentaryNo + "%'", "AND", StringPool.LIKE));
			}

			if (Validator.isNotNull(documentaryIssued)) {

				Date date = null;

				try {
					date = FormatData.formatDDMMYYYY.parse(documentaryIssued);
				} catch (Exception e) {
					log.error(e.getMessage());
				}

				if (date != null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					String strDate = calendar.get(Calendar.YEAR) + "-"
							+ (calendar.get(Calendar.MONTH) + 1) + "-"
							+ calendar.get(Calendar.DATE);

					condition.append(VMAUtils.buildSQLCondition(
							"vma_transaction_slip.DocumentaryIssued", "'" + strDate + " 00:00:00'"
									+ " AND '" + strDate + " 23:59:59'", "AND",
							StringPool.BETWEEN));
				}
			}

			if (Validator.isNotNull(paymentDate)) {
				Date date = null;

				try {
					date = FormatData.formatDDMMYYYY.parse(paymentDate);
				} catch (Exception e) {
					log.error(e.getMessage());
				}

				if (date != null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					String strDate = calendar.get(Calendar.YEAR) + "-"
							+ (calendar.get(Calendar.MONTH) + 1) + "-"
							+ calendar.get(Calendar.DATE);

					condition.append(VMAUtils.buildSQLCondition("vma_transaction_slip.PaymentDate", "'"
							+ strDate + " 00:00:00'" + " AND '" + strDate
							+ " 23:59:59'", "AND", StringPool.BETWEEN));
				}
			}

			if (Validator.isNotNull(paymentStatus)) {
				condition.append(VMAUtils.buildSQLCondition("vma_transaction_slip.paymentStatus", "("
						+ paymentStatus + ")", "AND", "IN"));
			}


			if (Validator.isNotNull(imoNumber)) {
				condition.append(VMAUtils.buildSQLCondition("vma_transaction_slip.imoNumber", "'%"
						+ imoNumber + "%'", "AND", StringPool.LIKE));
			}

			if (Validator.isNotNull(callSign)) {
				condition.append(VMAUtils.buildSQLCondition("vma_transaction_slip.callSign", "'%"
						+ callSign + "%'", "AND", StringPool.LIKE));
			}

			if (Validator.isNotNull(callSign)) {
				condition.append(VMAUtils.buildSQLCondition("vma_transaction_slip.RegistryNumber", "'%"
						+ registryNumber + "%'", "AND", StringPool.LIKE));
			}

			if (Validator.isNotNull(nameOfShip)) {
				condition.append(VMAUtils.buildSQLCondition("vma_transaction_slip.nameOfShip", "'%"
						+ nameOfShip + "%'", "AND", StringPool.LIKE));
			}

			if (Validator.isNotNull(shipAgencyName)) {
				condition.append(VMAUtils.buildSQLCondition("vma_transaction_slip.shipAgencyName", "'%"
						+ shipAgencyName + "%'", "AND", StringPool.LIKE));
			}

			if (Validator.isNotNull(shipOwnerName)) {
				condition.append(VMAUtils.buildSQLCondition("vma_transaction_slip.shipOwnerName", "'%"
						+ shipOwnerName + "%'", "AND", StringPool.LIKE));
			}

			if (Validator.isNotNull(shipAgencyCode)) {
				condition.append(VMAUtils.buildSQLCondition("vma_transaction_slip.shipAgencyCode", "'"
						+ shipAgencyCode + "'", "AND", StringPool.EQUAL));
			}


			if (Validator.isNotNull(currentPaymentStatus) && (!currentPaymentStatus.equals("0")) ) {
				condition.append(VMAUtils.buildSQLCondition("vma_transaction_slip.paymentStatus",  "'"
						+ currentPaymentStatus + "'", "AND", StringPool.EQUAL));
			}




			sql = sql + query.toString() + " " + condition.toString();





			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(Long.class).build();

			/*  */


			count = (Long) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return count;
	}

	public List<VmaTransactionSlip> dsQLCongNo(String nameOfShip,
											   String departmentCode, String paymentName, String month,
											   String paymentStatus, int usdTotalAmount, String vrCode,
											   String shipAgencyName, String imoNumber, String callSign,
											   String registryNumber, String power, String documentaryIssued,
											   String vndTotalAmount, int start, int end) throws SystemException {

		
		try {
			
			StringBuilder query = new StringBuilder();
			String sql = "SELECT vma_transaction_slip.* FROM vma_transaction_slip WHERE 1 = 1 ";

			if (Validator.isNotNull(nameOfShip)
					&& nameOfShip.trim().length() > 0) {
				query.append(" AND vma_transaction_slip.NameOfShip LIKE '%"
						+ nameOfShip + "%'");
			}
			if (Validator.isNotNull(departmentCode)
					&& departmentCode.trim().length() > 0) {
				query.append(" AND vma_transaction_slip.DepartmentCode = "
						+ departmentCode);
			}

			if (Validator.isNotNull(paymentName) && !paymentName.equals("")) {
				query.append(" AND vma_transaction_slip.PaymentName LIKE '%"
						+ paymentName + "%'");
			}

			if (Validator.isNotNull(shipAgencyName)
					&& !shipAgencyName.equals(StringPool.BLANK)) {
				query.append(" AND vma_transaction_slip.ShipAgencyName LIKE '%"
						+ shipAgencyName + "%'");
			}

			if (Validator.isNotNull(imoNumber) && imoNumber.trim().length() > 0) {
				query.append(" AND vma_transaction_slip.ImoNumber = '"
						+ imoNumber + "' ");
			}

			if (Validator.isNotNull(callSign) && callSign.trim().length() > 0) {
				query.append(" AND vma_transaction_slip.CallSign = '"
						+ callSign + "' ");
			}

			if (Validator.isNotNull(registryNumber)
					&& registryNumber.trim().length() > 0) {
				query.append(" AND vma_transaction_slip.RegistryNumber = '"
						+ registryNumber + "' ");
			}

			if (Validator.isNotNull(power) && power.trim().length() > 0) {
				query.append(" AND vma_transaction_slip.Power = " + power);
			}

			if (Validator.isNotNull(documentaryIssued)
					&& !documentaryIssued.equals(StringPool.BLANK)) {
				Date date = FormatData
						.parseDateShort3StringToDate(documentaryIssued);
				String _date = FormatData.parseDateToStringType6(date);

				query.append(" AND vma_transaction_slip.DocumentaryIssued BETWEEN '"
						+ _date + " 00:00:00' AND '" + _date + " 23:59:59'");
			}

			if (Validator.isNotNull(vndTotalAmount)
					&& !vndTotalAmount.equals(StringPool.BLANK)) {
				query.append(" AND vma_transaction_slip.VndTotalAmount = "
						+ vndTotalAmount);
			}

			if (Validator.isNotNull(usdTotalAmount) && usdTotalAmount >= 0) {
				query.append(" AND vma_transaction_slip.UsdTotalAmount = "
						+ usdTotalAmount);
			}

			if (Validator.isNotNull(month) && !month.isEmpty()) {
				Date date = FormatData.parseDateShort3StringToDate(month);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);

				int _month = calendar.get(Calendar.MONTH) + 1;

				int _year = calendar.get(Calendar.YEAR);
				query.append(" AND vma_transaction_slip.PaymentDate BETWEEN '"
						+ _year + "-" + _month + "-1" + " 00:00:00" + "' AND '"
						+ _year + "-" + _month + "-1" + " 23:59:59" + "'");
			}
			if (Validator.isNotNull(paymentStatus)) {
				query.append(" AND vma_transaction_slip.PaymentStatus in ("
						+ paymentStatus + ")");
			}

			if (Validator.isNotNull(vrCode) && !vrCode.isEmpty()) {
				query.append(" AND vma_itinerary.VrCode IN (" + vrCode + ")");
			}

			sql = sql + query.toString();

			log.info("=========select dsQLCongNo>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ sql);




			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).firstResult(start).maxResults(end - start).entityClass(VmaTransactionSlip.class).build();

			return (List<VmaTransactionSlip>)  queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public long countDsQLCongNo(String nameOfShip, String departmentCode,
								String paymentName, String month, String paymentStatus,
								int usdTotalAmount, String vrCode, int start, int end)
			throws SystemException {
		long count = 0;
		
		try {
			
			StringBuilder query = new StringBuilder();
			String sql = "SELECT count(vma_transaction_slip.ID) as total FROM vma_transaction_slip left join vma_itinerary on vma_transaction_slip.itineraryNo = vma_itinerary.itineraryNo  WHERE 1 = 1 ";

			if (Validator.isNotNull(nameOfShip)
					&& nameOfShip.trim().length() > 0) {
				query.append(" AND vma_transaction_slip.NameOfShip LIKE '%"
						+ nameOfShip + "%'");
			}
			if (Validator.isNotNull(departmentCode)
					&& departmentCode.trim().length() > 0) {
				query.append(" AND vma_transaction_slip.DepartmentCode = "
						+ departmentCode);
			}

			if (Validator.isNotNull(paymentName) && !paymentName.equals("")) {
				query.append(" AND vma_transaction_slip.PaymentName LIKE '%"
						+ paymentName + "%'");
			}
			if (Validator.isNotNull(month) && !month.isEmpty()) {
				Date date = FormatData.parseDateShort3StringToDate(month);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);

				int _month = calendar.get(Calendar.MONTH) + 1;

				int _year = calendar.get(Calendar.YEAR);
				query.append(" AND vma_transaction_slip.PaymentDate BETWEEN '"
						+ _year + "-" + _month + "-1" + " 00:00:00" + "' AND '"
						+ _year + "-" + _month + "-1" + " 23:59:59" + "'");
			}
			if (Validator.isNotNull(paymentStatus)) {
				query.append(" AND vma_transaction_slip.PaymentStatus in ("
						+ paymentStatus + ")");
			}

			if (Validator.isNotNull(usdTotalAmount) && usdTotalAmount >= 0) {
				query.append(" AND vma_transaction_slip.UsdTotalAmount = "
						+ usdTotalAmount);
			}

			if (Validator.isNotNull(vrCode) && !vrCode.isEmpty()) {
				query.append(" AND vma_itinerary.VrCode IN (" + vrCode + ")");
			}

			sql = sql + query.toString();

			log.info("=========select count dsQLCongNo>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ sql);




			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).firstResult(start).maxResults(end - start).entityClass(VmaTransactionSlip.class).build();

			/*  */


			count = (Long) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return count;
	}


	public JSONObject findThongKeBienLai81(int reportCode, String paymentName,
										   String portofAuthority, String createDate, String reportUser,
										   String reportPeriod, String reportYear, String reportMonth,
										   String fromDate, String toDate, int start, int end) throws SystemException {

		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray array = JSONFactoryUtil.createJSONArray();
		Boolean HavingObject = false;

		try {
			
			StringBuilder query = new StringBuilder();
			StringBuilder queryLuyKeThang = new StringBuilder();
			StringBuilder queryLuyKeNam = new StringBuilder();

			String sqlCumulativeSum = "Select ";
			sqlCumulativeSum += " SUM(vma_transaction_slip_details.GT),";
			sqlCumulativeSum += " SUM(vma_transaction_slip_details.DWT),";
			sqlCumulativeSum += " SUM(vma_transaction_slip_details.GTConversion),";
			sqlCumulativeSum += " SUM(Case (vma_transaction_slip.UsdTotalAmount > 0) WHEN true THEN vma_transaction_slip_details.GT ELSE '' END ) AS GT_USD,";
			sqlCumulativeSum += " SUM(Case (vma_transaction_slip.VndTotalAmount > 0) WHEN true THEN vma_transaction_slip_details.GT ELSE '' END ) AS GT_VND,";
			sqlCumulativeSum += " SUM(Case (vma_transaction_slip.UsdTotalAmount > 0) WHEN true THEN vma_transaction_slip_details.GTConversion ELSE '' END ) AS GTConversion_USD,";
			sqlCumulativeSum += " SUM(Case (vma_transaction_slip.VndTotalAmount > 0) WHEN true THEN vma_transaction_slip_details.GTConversion ELSE '' END ) AS GTConversion_VND,";

			sqlCumulativeSum += " SUM(Case (vma_transaction_slip_details.InF1313Vnd > 0) WHEN true THEN vma_transaction_slip_details.DWT ELSE";
			sqlCumulativeSum += " Case (vma_transaction_slip_details.OUTF1313Vnd > 0) WHEN true THEN vma_transaction_slip_details.DWT ELSE '0.0' END END ) AS Thuy_noi_dia,";

			// sqlCumulativeSum += " -- InwardTonnageFee 1. Phí trọng tải (Tonnage fee)   - Lượt vào (Inward)";
			sqlCumulativeSum += " SUM(vma_transaction_slip_details.InF1312Usd ) AS TotalUSD_InwardTonnageFee,";
			sqlCumulativeSum += " SUM(Case (vma_transaction_slip_details.InF1313Vnd > 0) ";
			sqlCumulativeSum += " WHEN true then vma_transaction_slip_details.InF1313Vnd ELSE vma_transaction_slip_details.InF1311Vnd END";
			sqlCumulativeSum += " ) AS TotalVND_InwardTonnageFee,		  ";

			// sqlCumulativeSum += " -- OutwardTonnageFee 1. Phí trọng tải (Tonnage fee)   - Lượt rời (Outward)";
			sqlCumulativeSum += " SUM(vma_transaction_slip_details.OutF1312Usd ) AS TotalUSD_OutwardTonnageFee,";
			sqlCumulativeSum += " SUM(Case (vma_transaction_slip_details.OutF1313Vnd > 0) ";
			sqlCumulativeSum += " WHEN true then vma_transaction_slip_details.OutF1313Vnd ELSE vma_transaction_slip_details.OutF1311Vnd END";
			sqlCumulativeSum += " ) AS TotalVND_OutwardTonnageFee,		  ";
			sqlCumulativeSum += " SUM((ROUND((vma_transaction_slip_details.InF1312Usd + vma_transaction_slip_details.OutF1312Usd) * vma_transaction_slip.ExchangeRate, 2) +";
			sqlCumulativeSum += " vma_transaction_slip_details.InF1313Vnd + vma_transaction_slip_details.InF1311Vnd + ";
			sqlCumulativeSum += " vma_transaction_slip_details.OutF1313Vnd + vma_transaction_slip_details.OutF1311Vnd ) ) AS TonnageFeeAmount,";

			// sqlCumulativeSum += " -- OnShipAnchorageFee 2. Phí neo đậu (Anchorage fee)  - Đối với phương tiện (On ship)";
			sqlCumulativeSum += " SUM(vma_transaction_slip_details.InF1322Usd ) AS TotalUSD_OnShipAnchorageFee,";
			sqlCumulativeSum += " SUM(vma_transaction_slip_details.InF1321Vnd ) AS TotalVND_OnShipAnchorageFee,";

			// sqlCumulativeSum += " -- OnCargoAnchorageFee 2. Phí neo đậu (Anchorage fee)  - Đối với hàng hóa (On cargo)";
			sqlCumulativeSum += " SUM(vma_transaction_slip_details.InF1332Usd ) AS TotalUSD_OnCargoAnchorageFee,";
			sqlCumulativeSum += " SUM(vma_transaction_slip_details.InF1331Vnd ) AS TotalVND_OnCargoAnchorageFee,";
			sqlCumulativeSum += " SUM((ROUND((vma_transaction_slip_details.InF1322Usd + vma_transaction_slip_details.InF1332Usd) * vma_transaction_slip.ExchangeRate, 2) +";
			sqlCumulativeSum += " vma_transaction_slip_details.InF1321Vnd + vma_transaction_slip_details.InF1331Vnd ) ) AS AnchorageFeeAmount,";

			// sqlCumulativeSum += " -- InwardSeaProtestFee 5. Lệ phí chứng thực (Kháng nghị hàng hải) (Sea protest fee)   - Lượt vào (Inward)";
			sqlCumulativeSum += " SUM(vma_transaction_slip_details.InF1372Usd ) AS TotalUSD_InwardSeaProtestFee,";
			sqlCumulativeSum += " SUM(Case (vma_transaction_slip_details.InF1373Vnd > 0) ";
			sqlCumulativeSum += " WHEN true then vma_transaction_slip_details.InF1373Vnd ELSE vma_transaction_slip_details.InF1371Vnd END";
			sqlCumulativeSum += " ) AS TotalVND_InwardSeaProtestFee,		  ";

			// sqlCumulativeSum += " -- OutwardSeaProtestFee 5. Lệ phí chứng thực (Kháng nghị hàng hải) (Sea protest fee)   - Lượt rời (Outward)";
			sqlCumulativeSum += " SUM(vma_transaction_slip_details.OutF1372Usd ) AS TotalUSD_OutwardSeaProtestFee,";
			sqlCumulativeSum += " SUM(Case (vma_transaction_slip_details.OutF1373Vnd > 0) ";
			sqlCumulativeSum += " WHEN true then vma_transaction_slip_details.OutF1373Vnd ELSE vma_transaction_slip_details.OutF1371Vnd END";
			sqlCumulativeSum += " ) AS TotalVND_OutwardSeaProtestFee,";
			sqlCumulativeSum += " SUM((ROUND((vma_transaction_slip_details.InF1372Usd + vma_transaction_slip_details.OutF1372Usd) * vma_transaction_slip.ExchangeRate, 2) +";
			sqlCumulativeSum += " vma_transaction_slip_details.InF1373Vnd + vma_transaction_slip_details.InF1371Vnd + ";
			sqlCumulativeSum += " vma_transaction_slip_details.OutF1373Vnd + vma_transaction_slip_details.OutF1371Vnd ) ) AS SeaProtestFeeAmount,";

			// sqlCumulativeSum += " -- InwardNavigationFee 2. Phí bảo đảm hàng hải (NavigationFee)   - Lượt vào (Inward)";
			sqlCumulativeSum += " SUM(vma_transaction_slip_details.InF1352Usd ) AS TotalUSD_InwardNavigationFee,";
			sqlCumulativeSum += " SUM(vma_transaction_slip_details.InF1351Vnd ) AS TotalVND_InwardNavigationFee,";

			// sqlCumulativeSum += " -- OutwardNavigationFee 2. Phí bảo đảm hàng hải (NavigationFee)   - Lượt rời (Outward)";
			sqlCumulativeSum += " SUM(vma_transaction_slip_details.OutF1352Usd ) AS TotalUSD_OutwardNavigationFee,";
			sqlCumulativeSum += " SUM(vma_transaction_slip_details.OutF1351Vnd ) AS TotalVND_OutwardNavigationFee,";
			sqlCumulativeSum += " SUM((ROUND((vma_transaction_slip_details.InF1352Usd + vma_transaction_slip_details.OutF1352Usd) * vma_transaction_slip.ExchangeRate, 2) +";
			sqlCumulativeSum += " vma_transaction_slip_details.InF1351Vnd + vma_transaction_slip_details.OutF1351Vnd ) ) AS NavigationFeeAmount,";


			// sqlCumulativeSum += " -- InwardClearanceFee 3. Lệ phí ra, vào cảng biển (Clearance fee)   - Lượt vào (Inward)";
			sqlCumulativeSum += " SUM(vma_transaction_slip_details.InF1362Usd ) AS TotalUSD_InwardClearanceFee,";
			sqlCumulativeSum += " SUM(Case (vma_transaction_slip_details.InF1363Vnd > 0) ";
			sqlCumulativeSum += " WHEN true then vma_transaction_slip_details.InF1363Vnd ELSE vma_transaction_slip_details.InF1361Vnd END";
			sqlCumulativeSum += " ) AS TotalVND_InwardClearanceFee, ";

			// sqlCumulativeSum += " -- OutwardClearanceFee 3. Lệ phí ra, vào cảng biển (Clearance fee)   - Lượt rời (Outward)";
			sqlCumulativeSum += " SUM(vma_transaction_slip_details.OutF1362Usd ) AS TotalUSD_OutwardClearanceFee, 		";
			sqlCumulativeSum += " SUM(Case (vma_transaction_slip_details.OutF1363Vnd > 0) ";
			sqlCumulativeSum += " WHEN true then vma_transaction_slip_details.OutF1363Vnd ELSE vma_transaction_slip_details.OutF1361Vnd END";
			sqlCumulativeSum += " ) AS TotalVND_OutwardClearanceFee,";
			sqlCumulativeSum += " SUM((ROUND((vma_transaction_slip_details.InF1362Usd + vma_transaction_slip_details.OutF1362Usd) * vma_transaction_slip.ExchangeRate, 2) +";
			sqlCumulativeSum += " vma_transaction_slip_details.InF1363Vnd + vma_transaction_slip_details.InF1361Vnd + ";
			sqlCumulativeSum += " vma_transaction_slip_details.OutF1363Vnd + vma_transaction_slip_details.OutF1361Vnd ) ) AS ClearanceFeeAmount,";
			sqlCumulativeSum += " SUM(vma_transaction_slip.UsdTotalAmount),";
			sqlCumulativeSum += " SUM(vma_transaction_slip.VndTotalAmount),";
			sqlCumulativeSum += " SUM(vma_transaction_slip.PaymentAmount),";


			sqlCumulativeSum += " SUM(Case vma_transaction_slip.PaymentType WHEN 3 THEN 0 WHEN 2 THEN vma_transaction_slip.PaymentAmount WHEN 1 THEN 0 ELSE 0 END ) AS Cash_TotalAmount,";
			sqlCumulativeSum += " SUM((vma_transaction_slip.PaymentAmount - (Case vma_transaction_slip.PaymentType WHEN 3 THEN 0 WHEN 2 THEN vma_transaction_slip.PaymentAmount WHEN 1 THEN 0 ELSE 0 END )) ) AS Transfer_TotalAmount,";
			// 1319- Phí trọng tải tàu thuyền (quy đổi ghi thu), tiền Việt Nam";
			sqlCumulativeSum += " SUM(vma_transaction_slip.F1319Vnd ) AS TotalUSD_TonnageFee,";
			// 1329- Phí neo đậu đối với phương tiện (quy đổi ghi thu), tiền Việt Nam
			sqlCumulativeSum += " SUM(vma_transaction_slip.F1329Vnd ) AS TotalUSDConversionVND_OnShipAnchorageFee,";
			// 1339- Phí neo đậu đối với hàng hóa (quy đổi ghi thu), tiền Việt Nam
			sqlCumulativeSum += " SUM(vma_transaction_slip.F1339Vnd ) AS TotalUSDConversionVND_OnCargoAnchorageFee,";
			// 1359- Phí bảo đảm hàng hải (quy đổi ghi thu), tiền Việt Nam
			sqlCumulativeSum += " SUM(vma_transaction_slip.F1359Vnd ) AS TotalUSDConversionVND_NavigationFee,";
			// 1369- Lệ phí ra vào cảng biển (quy đổi ghi thu), tiền Việt Nam
			sqlCumulativeSum += " SUM(vma_transaction_slip.F1369Vnd ) AS TotalUSDConversionVND_ClearanceFee,";
			// 1379- Lệ phí chứng thực (Kháng nghị hàng hải) (quy đổi ghi thu), tiền Việt Nam
			sqlCumulativeSum += " SUM(vma_transaction_slip.F1379Vnd ) AS TotalUSDConversionVND_SeaProtestFee,";
			// 1309- Phí khác (bổ sung) (quy đổi ghi thu), tiền Việt Nam
			sqlCumulativeSum += " SUM(vma_transaction_slip.F1309Vnd ) AS TotalUSDConversionVND_OtherFee,";
			sqlCumulativeSum += " SUM(vma_transaction_slip.PaymentDifferences),";
			sqlCumulativeSum += " SUM(vma_transaction_slip.PaymentInFigures),";
			sqlCumulativeSum += " SUM(vma_transaction_slip.PaymentRealAmount),";
			sqlCumulativeSum += " SUM(vma_transaction_slip.F1411Vnd),";
			sqlCumulativeSum += " SUM(vma_transaction_slip.F1412Vnd),";
			sqlCumulativeSum += " SUM(vma_transaction_slip.F1413Vnd),";
			sqlCumulativeSum += " SUM(vma_transaction_slip.F1421Vnd),";
			sqlCumulativeSum += " SUM(vma_transaction_slip.F1422Vnd),";
			sqlCumulativeSum += " SUM(vma_transaction_slip.F1423Vnd),";
			sqlCumulativeSum += " SUM(vma_transaction_slip.F1431Vnd),";
			sqlCumulativeSum += " SUM(vma_transaction_slip.F1432Vnd),";
			sqlCumulativeSum += " SUM(vma_transaction_slip.PaidAdvanceAmount),";
			sqlCumulativeSum += " SUM(vma_transaction_slip.F1319Vnd),";
			sqlCumulativeSum += " SUM(vma_transaction_slip.F1311Vnd),";
			sqlCumulativeSum += " SUM(vma_transaction_slip.F1312Usd),";
			sqlCumulativeSum += " SUM(vma_transaction_slip.F1329Vnd),";
			sqlCumulativeSum += " SUM(vma_transaction_slip.F1321Vnd),";
			sqlCumulativeSum += " SUM(vma_transaction_slip.F1322Usd),";
			sqlCumulativeSum += " SUM(vma_transaction_slip.F1339Vnd),";
			sqlCumulativeSum += " SUM(vma_transaction_slip.F1331Vnd),";
			sqlCumulativeSum += " SUM(vma_transaction_slip.F1332Usd),";
			sqlCumulativeSum += " SUM(vma_transaction_slip.F1359Vnd),";
			sqlCumulativeSum += " SUM(vma_transaction_slip.F1351Vnd),";
			sqlCumulativeSum += " SUM(vma_transaction_slip.F1352Usd),";
			sqlCumulativeSum += " SUM(vma_transaction_slip.F1369Vnd),";
			sqlCumulativeSum += " SUM(vma_transaction_slip.F1361Vnd),";
			sqlCumulativeSum += " SUM(vma_transaction_slip.F1362Usd),";
			sqlCumulativeSum += " SUM(vma_transaction_slip.F1379Vnd),";
			sqlCumulativeSum += " SUM(vma_transaction_slip.F1371Vnd),";
			sqlCumulativeSum += " SUM(vma_transaction_slip.F1372Usd),";
			sqlCumulativeSum += " SUM(vma_transaction_slip.F1313Vnd),";
			sqlCumulativeSum += " SUM(vma_transaction_slip.F1363Vnd),";
			sqlCumulativeSum += " SUM(vma_transaction_slip.F1373Vnd),";

			sqlCumulativeSum += " SUM((ROUND((vma_transaction_slip_details.InF1312Usd + vma_transaction_slip_details.OutF1312Usd) * vma_transaction_slip.ExchangeRate, 2) +";
			sqlCumulativeSum += " vma_transaction_slip_details.InF1313Vnd + vma_transaction_slip_details.InF1311Vnd + ";
			sqlCumulativeSum += " vma_transaction_slip_details.OutF1313Vnd + vma_transaction_slip_details.OutF1311Vnd ) +";
			sqlCumulativeSum += " (ROUND((vma_transaction_slip_details.InF1322Usd + vma_transaction_slip_details.InF1332Usd) * vma_transaction_slip.ExchangeRate, 2) +";
			sqlCumulativeSum += " vma_transaction_slip_details.InF1321Vnd + vma_transaction_slip_details.InF1331Vnd ) + ";
			sqlCumulativeSum += " (ROUND((vma_transaction_slip_details.InF1372Usd + vma_transaction_slip_details.OutF1372Usd) * vma_transaction_slip.ExchangeRate, 2) + ";
			sqlCumulativeSum += " vma_transaction_slip_details.InF1373Vnd + vma_transaction_slip_details.InF1371Vnd + ";
			sqlCumulativeSum += " vma_transaction_slip_details.OutF1373Vnd + vma_transaction_slip_details.OutF1371Vnd ) ";
			sqlCumulativeSum += " ) AS PortAuthorityTotalAmount";

			sqlCumulativeSum += " FROM vma_transaction_slip ";
			sqlCumulativeSum += " LEFT JOIN ";
			sqlCumulativeSum += " vma_transaction_slip_details ON vma_transaction_slip_details.ItineraryNo = vma_transaction_slip.ItineraryNo ";
			sqlCumulativeSum += " and vma_transaction_slip_details.DocumentaryCode = vma_transaction_slip.DocumentaryCode ";
			sqlCumulativeSum += " WHERE ( vma_transaction_slip.VndTotalAmount > 0 OR vma_transaction_slip.UsdTotalAmount > 0) ";
			sqlCumulativeSum += " AND vma_transaction_slip.PaymentStatus in (4,9) ";

			if (Validator.isNotNull(paymentName) && !paymentName.equals("")) {
				sqlCumulativeSum += " AND vma_transaction_slip.PaymentName LIKE '%"
						+ paymentName + "%'";
			}


			String sql = "Select ";
			sql += " vma_transaction_slip.ItineraryNo,";
			sql += " vma_transaction_slip.SequenceNo,";
			sql += " vma_transaction_slip.DocumentaryCode,";
			sql += " vma_transaction_slip.PortofAuthority,";

			sql += " vma_transaction_slip.StampSerialNo,";
			sql += " vma_transaction_slip.DocumentaryNo AS Bienlai_giay,";
			sql += " vma_transaction_slip.DocumentaryIssued AS NgayBienlai_giay,";
			sql += " vma_transaction_slip.Reportdate AS NgayBienlaidientu,";
			sql += " SUBSTRING_INDEX(vma_transaction_slip.Reportby, '_', -1) AS Bienlaidientu,";
			sql += " Case vma_transaction_slip.PaymentType WHEN 3 THEN 'KQ' WHEN 2 THEN 'TM' WHEN 1 THEN 'CK' ELSE 'CK' END AS PaymentType,";
			sql += " vma_transaction_slip.PaymentName AS PaymentOrganization,";
			sql += " vma_transaction_slip.NameOfShip,";
			sql += " vma_transaction_slip.FlagStateOfShip,";
			sql += " vma_transaction_slip.IMONumber,";
			sql += " vma_transaction_slip.CallSign,";
			sql += " vma_transaction_slip.RegistryNumber,";
			sql += " vma_transaction_slip.ArrivalPortName,";
			sql += " vma_transaction_slip_details.ShipBoat,";
			sql += " vma_transaction_slip_details.GT,";
			sql += " vma_transaction_slip_details.DWT,";
			sql += " vma_transaction_slip_details.GTPercentage,";
			sql += " vma_transaction_slip_details.GTConversion,";
			sql += " Case (vma_transaction_slip.UsdTotalAmount > 0) WHEN true THEN vma_transaction_slip_details.GT ELSE '' END AS GT_USD,";
			sql += " Case (vma_transaction_slip.VndTotalAmount > 0) WHEN true THEN vma_transaction_slip_details.GT ELSE '' END AS GT_VND,";
			sql += " Case (vma_transaction_slip.UsdTotalAmount > 0) WHEN true THEN vma_transaction_slip_details.GTConversion ELSE '' END AS GTConversion_USD,";
			sql += " Case (vma_transaction_slip.VndTotalAmount > 0) WHEN true THEN vma_transaction_slip_details.GTConversion ELSE '' END AS GTConversion_VND,";

			sql += " Case (vma_transaction_slip_details.InF1313Vnd > 0) WHEN true THEN vma_transaction_slip_details.DWT ELSE";
			sql += " Case (vma_transaction_slip_details.OUTF1313Vnd > 0) WHEN true THEN vma_transaction_slip_details.DWT ELSE '0.0' END END AS Thuy_noi_dia,";

			// sql += " -- InwardTonnageFee 1. Phí trọng tải (Tonnage fee)   - Lượt vào (Inward)";
			sql += " vma_transaction_slip_details.InF1312Usd AS TotalUSD_InwardTonnageFee,";
			sql += " Case (vma_transaction_slip_details.InF1313Vnd > 0) ";
			sql += " WHEN true then vma_transaction_slip_details.InF1313Vnd ELSE vma_transaction_slip_details.InF1311Vnd END";
			sql += " AS TotalVND_InwardTonnageFee,					  ";

			// sql += " -- OutwardTonnageFee 1. Phí trọng tải (Tonnage fee)   - Lượt rời (Outward)";
			sql += " vma_transaction_slip_details.OutF1312Usd AS TotalUSD_OutwardTonnageFee,";
			sql += " Case (vma_transaction_slip_details.OutF1313Vnd > 0) ";
			sql += " WHEN true then vma_transaction_slip_details.OutF1313Vnd ELSE vma_transaction_slip_details.OutF1311Vnd END";
			sql += " AS TotalVND_OutwardTonnageFee,					  ";
			sql += " (ROUND((vma_transaction_slip_details.InF1312Usd + vma_transaction_slip_details.OutF1312Usd) * vma_transaction_slip.ExchangeRate, 2) +";
			sql += " vma_transaction_slip_details.InF1313Vnd + vma_transaction_slip_details.InF1311Vnd + ";
			sql += " vma_transaction_slip_details.OutF1313Vnd + vma_transaction_slip_details.OutF1311Vnd ) AS TonnageFeeAmount,";

			// sql += " -- OnShipAnchorageFee 2. Phí neo đậu (Anchorage fee)  - Đối với phương tiện (On ship)";
			sql += " vma_transaction_slip_details.InF1322Usd AS TotalUSD_OnShipAnchorageFee,";
			sql += " vma_transaction_slip_details.InF1321Vnd AS TotalVND_OnShipAnchorageFee,";

			// sql += " -- OnCargoAnchorageFee 2. Phí neo đậu (Anchorage fee)  - Đối với hàng hóa (On cargo)";
			sql += " vma_transaction_slip_details.InF1332Usd AS TotalUSD_OnCargoAnchorageFee,";
			sql += " vma_transaction_slip_details.InF1331Vnd AS TotalVND_OnCargoAnchorageFee,";
			sql += " (ROUND((vma_transaction_slip_details.InF1322Usd + vma_transaction_slip_details.InF1332Usd) * vma_transaction_slip.ExchangeRate, 2) +";
			sql += " vma_transaction_slip_details.InF1321Vnd + vma_transaction_slip_details.InF1331Vnd ) AS AnchorageFeeAmount,";

			// sql += " -- InwardSeaProtestFee 5. Lệ phí chứng thực (Kháng nghị hàng hải) (Sea protest fee)   - Lượt vào (Inward)";
			sql += " vma_transaction_slip_details.InF1372Usd AS TotalUSD_InwardSeaProtestFee,";
			sql += " Case (vma_transaction_slip_details.InF1373Vnd > 0) ";
			sql += " WHEN true then vma_transaction_slip_details.InF1373Vnd ELSE vma_transaction_slip_details.InF1371Vnd END";
			sql += " AS TotalVND_InwardSeaProtestFee,					  ";

			// sql += " -- OutwardSeaProtestFee 5. Lệ phí chứng thực (Kháng nghị hàng hải) (Sea protest fee)   - Lượt rời (Outward)";
			sql += " vma_transaction_slip_details.OutF1372Usd AS TotalUSD_OutwardSeaProtestFee,";
			sql += " Case (vma_transaction_slip_details.OutF1373Vnd > 0) ";
			sql += " WHEN true then vma_transaction_slip_details.OutF1373Vnd ELSE vma_transaction_slip_details.OutF1371Vnd END";
			sql += " AS TotalVND_OutwardSeaProtestFee,";
			sql += " (ROUND((vma_transaction_slip_details.InF1372Usd + vma_transaction_slip_details.OutF1372Usd) * vma_transaction_slip.ExchangeRate, 2) +";
			sql += " vma_transaction_slip_details.InF1373Vnd + vma_transaction_slip_details.InF1371Vnd + ";
			sql += " vma_transaction_slip_details.OutF1373Vnd + vma_transaction_slip_details.OutF1371Vnd ) AS SeaProtestFeeAmount,";

			// sql += " -- InwardNavigationFee 2. Phí bảo đảm hàng hải (NavigationFee)   - Lượt vào (Inward)";
			sql += " vma_transaction_slip_details.InF1352Usd AS TotalUSD_InwardNavigationFee,";
			sql += " vma_transaction_slip_details.InF1351Vnd AS TotalVND_InwardNavigationFee,";

			// sql += " -- OutwardNavigationFee 2. Phí bảo đảm hàng hải (NavigationFee)   - Lượt rời (Outward)";
			sql += " vma_transaction_slip_details.OutF1352Usd AS TotalUSD_OutwardNavigationFee,";
			sql += " vma_transaction_slip_details.OutF1351Vnd AS TotalVND_OutwardNavigationFee,";
			sql += " (ROUND((vma_transaction_slip_details.InF1352Usd + vma_transaction_slip_details.OutF1352Usd) * vma_transaction_slip.ExchangeRate, 2) +";
			sql += " vma_transaction_slip_details.InF1351Vnd + vma_transaction_slip_details.OutF1351Vnd ) AS NavigationFeeAmount,";


			// sql += " -- InwardClearanceFee 3. Lệ phí ra, vào cảng biển (Clearance fee)   - Lượt vào (Inward)";
			sql += " vma_transaction_slip_details.InF1362Usd AS TotalUSD_InwardClearanceFee,";
			sql += " Case (vma_transaction_slip_details.InF1363Vnd > 0) ";
			sql += " WHEN true then vma_transaction_slip_details.InF1363Vnd ELSE vma_transaction_slip_details.InF1361Vnd END";
			sql += " AS TotalVND_InwardClearanceFee, ";

			// sql += " -- OutwardClearanceFee 3. Lệ phí ra, vào cảng biển (Clearance fee)   - Lượt rời (Outward)";
			sql += " vma_transaction_slip_details.OutF1362Usd AS TotalUSD_OutwardClearanceFee, 					";
			sql += " Case (vma_transaction_slip_details.OutF1363Vnd > 0) ";
			sql += " WHEN true then vma_transaction_slip_details.OutF1363Vnd ELSE vma_transaction_slip_details.OutF1361Vnd END";
			sql += " AS TotalVND_OutwardClearanceFee,";
			sql += " (ROUND((vma_transaction_slip_details.InF1362Usd + vma_transaction_slip_details.OutF1362Usd) * vma_transaction_slip.ExchangeRate, 2) +";
			sql += " vma_transaction_slip_details.InF1363Vnd + vma_transaction_slip_details.InF1361Vnd + ";
			sql += " vma_transaction_slip_details.OutF1363Vnd + vma_transaction_slip_details.OutF1361Vnd ) AS ClearanceFeeAmount,";
			sql += " vma_transaction_slip.UsdTotalAmount,";
			sql += " vma_transaction_slip.VndTotalAmount,";
			sql += " vma_transaction_slip.PaymentAmount,";
			sql += " Case (vma_transaction_slip.UsdTotalAmount > 0)";
			sql += " WHEN true then vma_transaction_slip.ExchangeRate ELSE '0' END AS ExchangeRate,";
			sql += " vma_transaction_slip.PaymentDate,";

			sql += " (ROUND((vma_transaction_slip_details.InF1312Usd + vma_transaction_slip_details.OutF1312Usd) * vma_transaction_slip.ExchangeRate, 2) +";
			sql += " vma_transaction_slip_details.InF1313Vnd + vma_transaction_slip_details.InF1311Vnd + ";
			sql += " vma_transaction_slip_details.OutF1313Vnd + vma_transaction_slip_details.OutF1311Vnd ) +";
			sql += " (ROUND((vma_transaction_slip_details.InF1322Usd + vma_transaction_slip_details.InF1332Usd) * vma_transaction_slip.ExchangeRate, 2) +";
			sql += " vma_transaction_slip_details.InF1321Vnd + vma_transaction_slip_details.InF1331Vnd ) + ";
			sql += " (ROUND((vma_transaction_slip_details.InF1372Usd + vma_transaction_slip_details.OutF1372Usd) * vma_transaction_slip.ExchangeRate, 2) + ";
			sql += " vma_transaction_slip_details.InF1373Vnd + vma_transaction_slip_details.InF1371Vnd + ";
			sql += " vma_transaction_slip_details.OutF1373Vnd + vma_transaction_slip_details.OutF1371Vnd ) ";
			sql += " AS PortAuthorityTotalAmount,";

			sql += " vma_transaction_slip.FinancialAccountant,";
			sql += " vma_transaction_slip.DepartmentCode,";
			sql += " vma_transaction_slip.DepartmentName,";
			sql += " Case vma_transaction_slip.PaymentType WHEN 3 THEN 0 WHEN 2 THEN vma_transaction_slip.PaymentAmount WHEN 1 THEN 0 ELSE 0 END AS Cash_TotalAmount,";
			sql += " (vma_transaction_slip.PaymentAmount - (Case vma_transaction_slip.PaymentType WHEN 3 THEN 0 WHEN 2 THEN vma_transaction_slip.PaymentAmount WHEN 1 THEN 0 ELSE 0 END )) AS Transfer_TotalAmount,";
			sql += " REPLACE(SUBSTRING_INDEX(vma_transaction_slip_details.F1321Remarks, '*', -1), ')','') AS AnchorHourVND,";
			sql += " REPLACE(SUBSTRING_INDEX(vma_transaction_slip_details.F1322Remarks, '*', -1), ')','') AS AnchorHourUSD,";
			sql += " vma_vnptserviceconfig.TaxCode,";
			sql += " vma_vnptserviceconfig.PatternCode,";
			sql += " vma_vnptserviceconfig.SerialCode,";
			sql += " vma_vnptserviceconfig.Remarks as MaritimeNameVN,";
			sql += " vma_itinerary_schedule.CertificateNo,";
			sql += " vma_itinerary_schedule.TimeOfDeparture,";
			sql += " vma_itinerary.TimeOfArrival,";
			sql += " vma_Itinerary.VrCode,";
			sql += " dm_port_harbour.PortHarbourCode,";
			sql += " REPLACE(dm_port_harbour.PortHarbourNameVN, 'Bến cảng ','') as PortHarbourNameVN,";
			sql += " dm_port.PortName,";
			sql += " dm_state.StateName,";
			sql += " vma_transaction_slip.ShipAgencyName,";
			sql += " vma_transaction_slip.MainPurpose,";
			sql += " vma_transaction_slip.NumberItineraryPerMonth,";
			sql += " vma_transaction_slip.TotalAnchorageHours,";

			// 1319- Phí trọng tải tàu thuyền (quy đổi ghi thu), tiền Việt Nam";
			sql += " vma_transaction_slip.F1319Vnd AS TotalUSD_TonnageFee,";
			// 1329- Phí neo đậu đối với phương tiện (quy đổi ghi thu), tiền Việt Nam
			sql += " vma_transaction_slip.F1329Vnd AS TotalUSDConversionVND_OnShipAnchorageFee,";
			// 1339- Phí neo đậu đối với hàng hóa (quy đổi ghi thu), tiền Việt Nam
			sql += " vma_transaction_slip.F1339Vnd AS TotalUSDConversionVND_OnCargoAnchorageFee,";
			// 1359- Phí bảo đảm hàng hải (quy đổi ghi thu), tiền Việt Nam
			sql += " vma_transaction_slip.F1359Vnd AS TotalUSDConversionVND_NavigationFee,";
			// 1369- Lệ phí ra vào cảng biển (quy đổi ghi thu), tiền Việt Nam
			sql += " vma_transaction_slip.F1369Vnd AS TotalUSDConversionVND_ClearanceFee,";
			// 1379- Lệ phí chứng thực (Kháng nghị hàng hải) (quy đổi ghi thu), tiền Việt Nam
			sql += " vma_transaction_slip.F1379Vnd AS TotalUSDConversionVND_SeaProtestFee,";
			// 1309- Phí khác (bổ sung) (quy đổi ghi thu), tiền Việt Nam
			sql += " vma_transaction_slip.F1309Vnd AS TotalUSDConversionVND_OtherFee,";

			sql += " vma_transaction_slip.ApprovalDate,";
			sql += " vma_transaction_slip.FundTransferNo,";
			sql += " vma_transaction_slip.FundTransferDate,";
			sql += " vma_transaction_slip.FundTransferDetails,";
			sql += " vma_transaction_slip.ReceiptNo,";
			sql += " vma_transaction_slip.ReceiptDate,";
			sql += " vma_transaction_slip.ReceiptDetails,";
			sql += " vma_transaction_slip.ReceiptRemark,";
			sql += " vma_transaction_slip.ReceiptBookNo,";
			sql += " vma_transaction_slip.eReceiptNo,";
			sql += " vma_transaction_slip.eReceiptDate,";
			sql += " vma_transaction_slip.eReceiptDetails,";
			sql += " vma_transaction_slip.EmailRecipients,";
			sql += " vma_transaction_slip.PortClearanceCertificateNo,";
			sql += " vma_transaction_slip.ShipOwnerCode,";
			sql += " vma_transaction_slip.HideShipOwnerShipAgency,";
			sql += " vma_transaction_slip.DoCharge,";
			sql += " vma_transaction_slip.HideExchangeRate,";
			sql += " vma_transaction_slip.DocumentaryExchangeRate,";
			sql += " vma_transaction_slip.DocumentaryCurrencyExgDate,";
			sql += " vma_transaction_slip.ExchangeRateDifferences,";
			sql += " vma_transaction_slip.DifferencesExgDate,";
			sql += " vma_transaction_slip.PaymentDifferences,";
			sql += " vma_transaction_slip.PaymentInFigures,";
			sql += " vma_transaction_slip.PaymentRealAmount,";
			sql += " vma_transaction_slip.F1411Vnd,";
			sql += " vma_transaction_slip.F1412Vnd,";
			sql += " vma_transaction_slip.F1413Vnd,";
			sql += " vma_transaction_slip.F1421Vnd,";
			sql += " vma_transaction_slip.F1422Vnd,";
			sql += " vma_transaction_slip.F1423Vnd,";
			sql += " vma_transaction_slip.F1431Vnd,";
			sql += " vma_transaction_slip.F1432Vnd,";
			sql += " vma_transaction_slip.NameOfPortFacility,";
			sql += " vma_transaction_slip.AddressOfPortFacility,";
			sql += " vma_transaction_slip.StatementNumber,";
			sql += " vma_transaction_slip.DateOfStatement,";
			sql += " vma_transaction_slip.StatementIssuedAt,";
			sql += " vma_transaction_slip.StatementValidUntil,";
			sql += " vma_transaction_slip.F1411Remarks,";
			sql += " vma_transaction_slip.F1412Remarks,";
			sql += " vma_transaction_slip.F1413Remarks,";
			sql += " vma_transaction_slip.F1421Remarks,";
			sql += " vma_transaction_slip.F1422Remarks,";
			sql += " vma_transaction_slip.F1423Remarks,";
			sql += " vma_transaction_slip.F1431Remarks,";
			sql += " vma_transaction_slip.F1432Remarks,";

			sql += " vma_transaction_slip.DocumentaryNo,";
			sql += " vma_transaction_slip.DocumentaryIssued,";
			sql += " vma_transaction_slip.DocumentaryKind,";
			sql += " vma_transaction_slip.PaidAdvanceAmount,";
			sql += " vma_transaction_slip.F1319Vnd,";
			sql += " vma_transaction_slip.F1311Vnd,";
			sql += " vma_transaction_slip.F1312Usd,";
			sql += " vma_transaction_slip.F1329Vnd,";
			sql += " vma_transaction_slip.F1321Vnd,";
			sql += " vma_transaction_slip.F1322Usd,";
			sql += " vma_transaction_slip.F1339Vnd,";
			sql += " vma_transaction_slip.F1331Vnd,";
			sql += " vma_transaction_slip.F1332Usd,";
			sql += " vma_transaction_slip.F1359Vnd,";
			sql += " vma_transaction_slip.F1351Vnd,";
			sql += " vma_transaction_slip.F1352Usd,";
			sql += " vma_transaction_slip.F1369Vnd,";
			sql += " vma_transaction_slip.F1361Vnd,";
			sql += " vma_transaction_slip.F1362Usd,";
			sql += " vma_transaction_slip.F1379Vnd,";
			sql += " vma_transaction_slip.F1371Vnd,";
			sql += " vma_transaction_slip.F1372Usd,";
			sql += " vma_transaction_slip.F1313Vnd,";
			sql += " vma_transaction_slip.F1363Vnd,";
			sql += " vma_transaction_slip.F1373Vnd,";

			sql += " vma_transaction_slip.ModifiedDate";

			sql += " FROM vma_transaction_slip ";
			sql += " LEFT JOIN vma_vnptserviceconfig on vma_vnptserviceconfig.MaritimeCode = vma_transaction_slip.PortofAuthority ";
			sql += " LEFT JOIN vma_itinerary ON vma_itinerary.ItineraryNo = vma_transaction_slip.ItineraryNo  ";
			sql += " LEFT JOIN vma_itinerary_schedule ON vma_itinerary_schedule.ItineraryNo = vma_transaction_slip.ItineraryNo and vma_itinerary_schedule.NoticeShipType = 2 ";
			sql += " LEFT JOIN dm_port_harbour ON dm_port_harbour.PortHarbourCode = vma_itinerary_schedule.PortHarbourCode ";
			sql += " LEFT JOIN dm_port ON dm_port.PortCode = vma_itinerary_schedule.ArrivalPortCode ";
			sql += " LEFT JOIN dm_state ON dm_state.StateCode = vma_transaction_slip.FlagStateOfShip ";
			sql += " LEFT JOIN dm_ship_type ON dm_ship_type.ShipTypeCode = vma_itinerary.ShipTypeCode  ";
			sql += " LEFT JOIN dm_vma_ship_type ON dm_vma_ship_type.ShipTypeCode  = vma_itinerary.VmaShipTypeCode ";

			sql += " LEFT JOIN ";
			sql += " vma_transaction_slip_details ON vma_transaction_slip_details.ItineraryNo = vma_transaction_slip.ItineraryNo ";
			sql += " and vma_transaction_slip_details.DocumentaryCode = vma_transaction_slip.DocumentaryCode ";
			sql += " WHERE ( vma_transaction_slip.VndTotalAmount > 0 OR vma_transaction_slip.UsdTotalAmount > 0) ";
			sql += " AND vma_transaction_slip.PaymentStatus in (4,9) ";

			if (Validator.isNotNull(paymentName) && !paymentName.equals("")) {
				sql += " AND vma_transaction_slip.PaymentName LIKE '%"
						+ paymentName + "%'";
			}



			if (reportCode == BaoCaoConstant.BAO_CAO_82){
				log.info("===== BAO_CAO_82 ==============");
				sql += " AND vma_transaction_slip.UsdTotalAmount > 0 ";
				sql += " AND vma_transaction_slip.PaymentType in (1) "; // chuyển khoản

				sqlCumulativeSum += " AND vma_transaction_slip.UsdTotalAmount > 0 ";
				sqlCumulativeSum += " AND vma_transaction_slip.PaymentType in (1) "; // chuyển khoản
			}
			if ((reportCode == BaoCaoConstant.BAO_CAO_82)
					|| (reportCode == BaoCaoConstant.BAO_CAO_83)
					|| (reportCode == BaoCaoConstant.BAO_CAO_84)
					|| (reportCode == BaoCaoConstant.BAO_CAO_85)
					|| (reportCode == BaoCaoConstant.BAO_CAO_86)
					|| (reportCode == BaoCaoConstant.BAO_CAO_87)
					|| (reportCode == BaoCaoConstant.BAO_CAO_88)
					|| (reportCode == BaoCaoConstant.BAO_CAO_89)
					|| (reportCode == BaoCaoConstant.BAO_CAO_90)
					|| (reportCode == BaoCaoConstant.BAO_CAO_91)
					|| (reportCode == BaoCaoConstant.BAO_CAO_92)
					|| (reportCode == BaoCaoConstant.BAO_CAO_93)
					|| (reportCode == BaoCaoConstant.BAO_CAO_94)
					|| (reportCode == BaoCaoConstant.BAO_CAO_95)
					|| (reportCode == BaoCaoConstant.BAO_CAO_96)

			){
				log.info("===== BAO_CAO_82 ==============");
				// sql += " AND vma_transaction_slip.UsdTotalAmount > 0 ";
			}
			String monthCumulative = StringPool.BLANK;
			String yearCumulative = StringPool.BLANK;

			String reportDateFrom = StringPool.BLANK;
			String reportDateTo = StringPool.BLANK;
			if (Validator.isNotNull(fromDate) && Validator.isNotNull(toDate)
					&& !fromDate.isEmpty() && !toDate.isEmpty()) {
				Date f_date = null;
				Date t_date = null;
				try {
					if (fromDate.contains("/") || toDate.contains("/")) {
						f_date = FormatData.formatDDMMYYYY.parse(fromDate);
						t_date = FormatData.formatDDMMYYYY.parse(toDate);
					} else if (fromDate.contains("-") || toDate.contains("-")) {
						f_date = FormatData.formatDateShort2.parse(fromDate);
						t_date = FormatData.formatDateShort2.parse(toDate);
					} else {
						f_date = FormatData.formatDDMMYYY2.parse(fromDate);
						t_date = FormatData.formatDDMMYYY2.parse(toDate);
					}
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

					query.append(" AND vma_transaction_slip.DocumentaryIssued BETWEEN '"
							+ f_strDate + " 00:00:00'");
					query.append(" AND '" + t_strDate + " 23:59:59' ");

					String firstDateOfMonth =  calendar.get(Calendar.YEAR) + "-"
							+ (calendar.get(Calendar.MONTH) + 1) + "-"
							+ 1;
					String firstDateOfYear =  calendar.get(Calendar.YEAR) + "-"
							+ 1 + "-"
							+ 1;

					queryLuyKeThang.append(" AND vma_transaction_slip.DocumentaryIssued BETWEEN '"
							+ firstDateOfMonth + " 00:00:00'" + " AND '" + t_strDate + " 23:59:59' ");


					queryLuyKeNam.append(" AND vma_transaction_slip.DocumentaryIssued BETWEEN '"
							+ firstDateOfYear + " 00:00:00'" + " AND '" + t_strDate + " 23:59:59' ");

					monthCumulative = (calendar.get(Calendar.MONTH) + 1)+"";
					yearCumulative = calendar.get(Calendar.YEAR)+"";

				}
			}

			if (Validator.isNotNull(portofAuthority)
					&& portofAuthority.trim().length() > 0) {
				query.append(" AND vma_transaction_slip.PortofAuthority = '"
						+ portofAuthority + "'");

				queryLuyKeThang.append(" AND vma_transaction_slip.PortofAuthority = '"
						+ portofAuthority + "'");

				queryLuyKeNam.append(" AND vma_transaction_slip.PortofAuthority = '"
						+ portofAuthority + "'");
			}

			query.append(" ORDER BY PaymentOrganization, vma_transaction_slip.ID ");


			String sql2 = sqlCumulativeSum + queryLuyKeThang.toString();
			String sql3 = sqlCumulativeSum + queryLuyKeNam.toString();

			String monthGT = "0.0";
			String monthUsdTotalAmount = "0.0";
			String monthF1312Usd = "0.0";
			String monthF1362Usd = "0.0";
			String monthF1322Usd = "0.0";
			String monthF1332Usd = "0.0";
			String monthF1352Usd = "0.0";
			String monthF1372Usd = "0.0";
			String yearGT = "0.0";
			String yearUsdTotalAmount = "0.0";
			String yearF1312Usd = "0.0";
			String yearF1362Usd = "0.0";
			String yearF1322Usd = "0.0";
			String yearF1332Usd = "0.0";
			String yearF1352Usd = "0.0";
			String yearF1372Usd = "0.0";

			String monthF1319Vnd = "0.0";
			String monthF1311Vnd = "0.0";
			String monthF1369Vnd = "0.0";
			String monthF1361Vnd = "0.0";
			String monthF1329Vnd = "0.0";
			String monthF1321Vnd = "0.0";
			String monthF1339Vnd = "0.0";
			String monthF1331Vnd = "0.0";
			String monthF1359Vnd = "0.0";
			String monthF1351Vnd = "0.0";
			String monthF1379Vnd = "0.0";
			String monthF1371Vnd = "0.0";
			String monthF1313Vnd = "0.0";
			String monthF1363Vnd = "0.0";
			String monthF1373Vnd = "0.0";

			String yearF1319Vnd = "0.0";
			String yearF1311Vnd = "0.0";
			String yearF1369Vnd = "0.0";
			String yearF1361Vnd = "0.0";
			String yearF1329Vnd = "0.0";
			String yearF1321Vnd = "0.0";
			String yearF1339Vnd = "0.0";
			String yearF1331Vnd = "0.0";
			String yearF1359Vnd = "0.0";
			String yearF1351Vnd = "0.0";
			String yearF1379Vnd = "0.0";
			String yearF1371Vnd = "0.0";
			String yearF1313Vnd = "0.0";
			String yearF1363Vnd = "0.0";
			String yearF1373Vnd = "0.0";

			log.info("=========select findThongKeBienLai81 Luy ke thang >>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ sql2);
			log.info("=========select findThongKeBienLai81 Luy ke nam >>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ sql3);
			QueryBuilder builder2 = QueryBuilder.builder().sqlQuery(true).queryString(sql2).firstResult(start).maxResults(end - start).entityClass(Object.class).build();



			Iterator<Object[]> itr2 =queryFactory2.getResultList(builder2).iterator();

			if (itr2.hasNext()) {
				while (itr2.hasNext()) {
					Object[] objects = itr2.next();
					if (Validator.isNotNull(String.valueOf(objects[0]))) {
						String GT = String.valueOf(objects[0]);
						monthGT = GT;
						String DWT = String.valueOf(objects[1]);
						String GTConversion = String.valueOf(objects[2]);
						String GT_USD = String.valueOf(objects[3]);
						String GT_VND = String.valueOf(objects[4]);
						String GTConversion_USD = String.valueOf(objects[5]);
						String GTConversion_VND = String.valueOf(objects[6]);
						String Thuy_noi_dia = String.valueOf(objects[7]);
						String TotalUSD_InwardTonnageFee = String.valueOf(objects[8]);
						String TotalVND_InwardTonnageFee = String.valueOf(objects[9]);
						String TotalUSD_OutwardTonnageFee = String.valueOf(objects[10]);
						String TotalVND_OutwardTonnageFee = String.valueOf(objects[11]);
						String TonnageFeeAmount = String.valueOf(objects[12]);
						String TotalUSD_OnShipAnchorageFee = String.valueOf(objects[13]);
						String TotalVND_OnShipAnchorageFee = String.valueOf(objects[14]);
						String TotalUSD_OnCargoAnchorageFee = String.valueOf(objects[15]);
						String TotalVND_OnCargoAnchorageFee = String.valueOf(objects[16]);
						String AnchorageFeeAmount = String.valueOf(objects[17]);
						String TotalUSD_InwardSeaProtestFee = String.valueOf(objects[18]);
						String TotalVND_InwardSeaProtestFee = String.valueOf(objects[19]);
						String TotalUSD_OutwardSeaProtestFee = String.valueOf(objects[20]);
						String TotalVND_OutwardSeaProtestFee = String.valueOf(objects[21]);
						String SeaProtestFeeAmount = String.valueOf(objects[22]);
						String TotalUSD_InwardNavigationFee = String.valueOf(objects[23]);
						String TotalVND_InwardNavigationFee = String.valueOf(objects[24]);
						String TotalUSD_OutwardNavigationFee = String.valueOf(objects[25]);
						String TotalVND_OutwardNavigationFee = String.valueOf(objects[26]);
						String NavigationFeeAmount = String.valueOf(objects[27]);
						String TotalUSD_InwardClearanceFee = String.valueOf(objects[28]);
						String TotalVND_InwardClearanceFee = String.valueOf(objects[29]);
						String TotalUSD_OutwardClearanceFee = String.valueOf(objects[30]);
						String TotalVND_OutwardClearanceFee = String.valueOf(objects[31]);
						String ClearanceFeeAmount = String.valueOf(objects[32]);
						String UsdTotalAmount = String.valueOf(objects[33]);
						monthUsdTotalAmount = UsdTotalAmount;
						String VndTotalAmount = String.valueOf(objects[34]);
						String PaymentAmount = String.valueOf(objects[35]);
						String Cash_TotalAmount = String.valueOf(objects[36]);
						String Transfer_TotalAmount = String.valueOf(objects[37]);

						String TotalUSD_TonnageFee = String.valueOf(objects[38]);
						String TotalUSDConversionVND_OnShipAnchorageFee = String.valueOf(objects[39]);
						String TotalUSDConversionVND_OnCargoAnchorageFee = String.valueOf(objects[40]);
						String TotalUSDConversionVND_NavigationFee = String.valueOf(objects[41]);
						String TotalUSDConversionVND_ClearanceFee = String.valueOf(objects[42]);
						String TotalUSDConversionVND_SeaProtestFee = String.valueOf(objects[43]);
						String TotalUSDConversionVND_OtherFee = String.valueOf(objects[44]);

						String PaymentDifferences = String.valueOf(objects[45]);
						String PaymentInFigures = String.valueOf(objects[46]);
						String PaymentRealAmount = String.valueOf(objects[47]);
						String F1411Vnd = String.valueOf(objects[48]);
						String F1412Vnd = String.valueOf(objects[49]);
						String F1413Vnd = String.valueOf(objects[50]);
						String F1421Vnd = String.valueOf(objects[51]);
						String F1422Vnd = String.valueOf(objects[52]);
						String F1423Vnd = String.valueOf(objects[53]);
						String F1431Vnd = String.valueOf(objects[54]);
						String F1432Vnd = String.valueOf(objects[55]);

						String PaidAdvanceAmount = String.valueOf(objects[56]);

						String F1319Vnd = String.valueOf(objects[57]);
						monthF1319Vnd = F1319Vnd;
						String F1311Vnd = String.valueOf(objects[58]);
						monthF1311Vnd = F1311Vnd;
						String F1312Usd = String.valueOf(objects[59]);
						monthF1312Usd = F1312Usd ;
						String F1329Vnd = String.valueOf(objects[60]);
						monthF1329Vnd = F1329Vnd;
						String F1321Vnd = String.valueOf(objects[61]);
						monthF1321Vnd = F1321Vnd;
						String F1322Usd = String.valueOf(objects[62]);
						monthF1322Usd = F1322Usd ;
						String F1339Vnd = String.valueOf(objects[63]);
						monthF1339Vnd = F1339Vnd;
						String F1331Vnd = String.valueOf(objects[64]);
						monthF1331Vnd = F1331Vnd;
						String F1332Usd = String.valueOf(objects[65]);
						monthF1332Usd = F1332Usd ;
						String F1359Vnd = String.valueOf(objects[66]);
						monthF1359Vnd = F1359Vnd;
						String F1351Vnd = String.valueOf(objects[67]);
						monthF1351Vnd = F1351Vnd;
						String F1352Usd = String.valueOf(objects[68]);
						monthF1352Usd = F1352Usd ;
						String F1369Vnd = String.valueOf(objects[69]);
						monthF1369Vnd = F1369Vnd;
						String F1361Vnd = String.valueOf(objects[70]);
						monthF1361Vnd = F1361Vnd;
						String F1362Usd = String.valueOf(objects[71]);
						monthF1362Usd = F1362Usd ;
						String F1379Vnd = String.valueOf(objects[72]);
						monthF1379Vnd = F1379Vnd;
						String F1371Vnd = String.valueOf(objects[73]);
						monthF1371Vnd = F1371Vnd;
						String F1372Usd = String.valueOf(objects[74]);
						monthF1372Usd = F1372Usd ;
						String F1313Vnd = String.valueOf(objects[75]);
						monthF1313Vnd = F1313Vnd;
						String F1363Vnd = String.valueOf(objects[76]);
						monthF1363Vnd = F1363Vnd;
						String F1373Vnd = String.valueOf(objects[77]);
						monthF1373Vnd = F1373Vnd;
					}

				}
			}

			QueryBuilder builder3 = QueryBuilder.builder().sqlQuery(true).queryString(sql3).firstResult(start).maxResults(end - start).entityClass(Object.class).build();



			Iterator<Object[]> itr3 =queryFactory2.getResultList(builder3).iterator();

			if (itr3.hasNext()) {
				while (itr3.hasNext()) {
					Object[] objects = itr3.next();
					if (Validator.isNotNull(String.valueOf(objects[0]))) {
						String GT = String.valueOf(objects[0]);
						yearGT = GT;
						String DWT = String.valueOf(objects[1]);
						String GTConversion = String.valueOf(objects[2]);
						String GT_USD = String.valueOf(objects[3]);
						String GT_VND = String.valueOf(objects[4]);
						String GTConversion_USD = String.valueOf(objects[5]);
						String GTConversion_VND = String.valueOf(objects[6]);
						String Thuy_noi_dia = String.valueOf(objects[7]);
						String TotalUSD_InwardTonnageFee = String.valueOf(objects[8]);
						String TotalVND_InwardTonnageFee = String.valueOf(objects[9]);
						String TotalUSD_OutwardTonnageFee = String.valueOf(objects[10]);
						String TotalVND_OutwardTonnageFee = String.valueOf(objects[11]);
						String TonnageFeeAmount = String.valueOf(objects[12]);
						String TotalUSD_OnShipAnchorageFee = String.valueOf(objects[13]);
						String TotalVND_OnShipAnchorageFee = String.valueOf(objects[14]);
						String TotalUSD_OnCargoAnchorageFee = String.valueOf(objects[15]);
						String TotalVND_OnCargoAnchorageFee = String.valueOf(objects[16]);
						String AnchorageFeeAmount = String.valueOf(objects[17]);
						String TotalUSD_InwardSeaProtestFee = String.valueOf(objects[18]);
						String TotalVND_InwardSeaProtestFee = String.valueOf(objects[19]);
						String TotalUSD_OutwardSeaProtestFee = String.valueOf(objects[20]);
						String TotalVND_OutwardSeaProtestFee = String.valueOf(objects[21]);
						String SeaProtestFeeAmount = String.valueOf(objects[22]);
						String TotalUSD_InwardNavigationFee = String.valueOf(objects[23]);
						String TotalVND_InwardNavigationFee = String.valueOf(objects[24]);
						String TotalUSD_OutwardNavigationFee = String.valueOf(objects[25]);
						String TotalVND_OutwardNavigationFee = String.valueOf(objects[26]);
						String NavigationFeeAmount = String.valueOf(objects[27]);
						String TotalUSD_InwardClearanceFee = String.valueOf(objects[28]);
						String TotalVND_InwardClearanceFee = String.valueOf(objects[29]);
						String TotalUSD_OutwardClearanceFee = String.valueOf(objects[30]);
						String TotalVND_OutwardClearanceFee = String.valueOf(objects[31]);
						String ClearanceFeeAmount = String.valueOf(objects[32]);
						String UsdTotalAmount = String.valueOf(objects[33]);
						yearUsdTotalAmount = UsdTotalAmount;
						String VndTotalAmount = String.valueOf(objects[34]);
						String PaymentAmount = String.valueOf(objects[35]);
						String Cash_TotalAmount = String.valueOf(objects[36]);
						String Transfer_TotalAmount = String.valueOf(objects[37]);

						String TotalUSD_TonnageFee = String.valueOf(objects[38]);
						String TotalUSDConversionVND_OnShipAnchorageFee = String.valueOf(objects[39]);
						String TotalUSDConversionVND_OnCargoAnchorageFee = String.valueOf(objects[40]);
						String TotalUSDConversionVND_NavigationFee = String.valueOf(objects[41]);
						String TotalUSDConversionVND_ClearanceFee = String.valueOf(objects[42]);
						String TotalUSDConversionVND_SeaProtestFee = String.valueOf(objects[43]);
						String TotalUSDConversionVND_OtherFee = String.valueOf(objects[44]);

						String PaymentDifferences = String.valueOf(objects[45]);
						String PaymentInFigures = String.valueOf(objects[46]);
						String PaymentRealAmount = String.valueOf(objects[47]);
						String F1411Vnd = String.valueOf(objects[48]);
						String F1412Vnd = String.valueOf(objects[49]);
						String F1413Vnd = String.valueOf(objects[50]);
						String F1421Vnd = String.valueOf(objects[51]);
						String F1422Vnd = String.valueOf(objects[52]);
						String F1423Vnd = String.valueOf(objects[53]);
						String F1431Vnd = String.valueOf(objects[54]);
						String F1432Vnd = String.valueOf(objects[55]);

						String PaidAdvanceAmount = String.valueOf(objects[56]);
						String F1319Vnd = String.valueOf(objects[57]);
						yearF1319Vnd = F1319Vnd;
						String F1311Vnd = String.valueOf(objects[58]);
						yearF1311Vnd = F1311Vnd;
						String F1312Usd = String.valueOf(objects[59]);
						yearF1312Usd = F1312Usd ;
						String F1329Vnd = String.valueOf(objects[60]);
						yearF1329Vnd = F1329Vnd;
						String F1321Vnd = String.valueOf(objects[61]);
						yearF1321Vnd = F1321Vnd;
						String F1322Usd = String.valueOf(objects[62]);
						yearF1322Usd = F1322Usd ;
						String F1339Vnd = String.valueOf(objects[63]);
						yearF1339Vnd = F1339Vnd;
						String F1331Vnd = String.valueOf(objects[64]);
						yearF1331Vnd = F1331Vnd;
						String F1332Usd = String.valueOf(objects[65]);
						yearF1332Usd = F1332Usd ;
						String F1359Vnd = String.valueOf(objects[66]);
						yearF1359Vnd = F1359Vnd;
						String F1351Vnd = String.valueOf(objects[67]);
						yearF1351Vnd = F1351Vnd;
						String F1352Usd = String.valueOf(objects[68]);
						yearF1352Usd = F1352Usd ;
						String F1369Vnd = String.valueOf(objects[69]);
						yearF1369Vnd = F1369Vnd;
						String F1361Vnd = String.valueOf(objects[70]);
						yearF1361Vnd = F1361Vnd;
						String F1362Usd = String.valueOf(objects[71]);
						yearF1362Usd = F1362Usd ;
						String F1379Vnd = String.valueOf(objects[72]);
						yearF1379Vnd = F1379Vnd;
						String F1371Vnd = String.valueOf(objects[73]);
						yearF1371Vnd= F1371Vnd;
						String F1372Usd = String.valueOf(objects[74]);
						yearF1372Usd = F1372Usd ;
						String F1313Vnd = String.valueOf(objects[75]);
						yearF1313Vnd = F1313Vnd;
						String F1363Vnd = String.valueOf(objects[76]);
						yearF1363Vnd = F1363Vnd;
						String F1373Vnd = String.valueOf(objects[77]);
						yearF1373Vnd = F1373Vnd;
					}

				}
			}


			sql = sql + query.toString();

			log.info("=========select findThongKeBienLai81 >>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ sql);


			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).firstResult(start).maxResults(end - start).entityClass(Object.class).build();



			Iterator<Object[]> itr =queryFactory2.getResultList(builder).iterator();
			


			if (itr.hasNext()) {
				while (itr.hasNext()) {
					Object[] objects = itr.next();

					String ItineraryNo = String.valueOf(objects[0]);
					String SequenceNo = String.valueOf(objects[1]);
					String DocumentaryCode = String.valueOf(objects[2]);
					String PortofAuthority = String.valueOf(objects[3]);
					String StampSerialNo = String.valueOf(objects[4]);
					String Bienlai_giay = String.valueOf(objects[5]);
					String NgayBienlai_giay = String.valueOf(objects[6]);
					String NgayBienlaidientu = String.valueOf(objects[7]);
					String Bienlaidientu = String.valueOf(objects[8]);
					String PaymentType = String.valueOf(objects[9]);
					String PaymentName = String.valueOf(objects[10]);
					String NameOfShip = String.valueOf(objects[11]);
					String FlagStateOfShip = String.valueOf(objects[12]);
					String IMONumber = String.valueOf(objects[13]);
					String CallSign = String.valueOf(objects[14]);
					String RegistryNumber = String.valueOf(objects[15]);
					String ArrivalPortName = String.valueOf(objects[16]);
					String ShipBoat = String.valueOf(objects[17]);
					String GT = String.valueOf(objects[18]);
					String DWT = String.valueOf(objects[19]);
					String GTPercentage = String.valueOf(objects[20]);
					String GTConversion = String.valueOf(objects[21]);
					String GT_USD = String.valueOf(objects[22]);
					String GT_VND = String.valueOf(objects[23]);
					String GTConversion_USD = String.valueOf(objects[24]);
					String GTConversion_VND = String.valueOf(objects[25]);
					String Thuy_noi_dia = String.valueOf(objects[26]);
					String TotalUSD_InwardTonnageFee = String.valueOf(objects[27]);
					String TotalVND_InwardTonnageFee = String.valueOf(objects[28]);
					String TotalUSD_OutwardTonnageFee = String.valueOf(objects[29]);
					String TotalVND_OutwardTonnageFee = String.valueOf(objects[30]);
					String TonnageFeeAmount = String.valueOf(objects[31]);
					String TotalUSD_OnShipAnchorageFee = String.valueOf(objects[32]);
					String TotalVND_OnShipAnchorageFee = String.valueOf(objects[33]);
					String TotalUSD_OnCargoAnchorageFee = String.valueOf(objects[34]);
					String TotalVND_OnCargoAnchorageFee = String.valueOf(objects[35]);
					String AnchorageFeeAmount = String.valueOf(objects[36]);
					String TotalUSD_InwardSeaProtestFee = String.valueOf(objects[37]);
					String TotalVND_InwardSeaProtestFee = String.valueOf(objects[38]);
					String TotalUSD_OutwardSeaProtestFee = String.valueOf(objects[39]);
					String TotalVND_OutwardSeaProtestFee = String.valueOf(objects[40]);
					String SeaProtestFeeAmount = String.valueOf(objects[41]);
					String TotalUSD_InwardNavigationFee = String.valueOf(objects[42]);
					String TotalVND_InwardNavigationFee = String.valueOf(objects[43]);
					String TotalUSD_OutwardNavigationFee = String.valueOf(objects[44]);
					String TotalVND_OutwardNavigationFee = String.valueOf(objects[45]);
					String NavigationFeeAmount = String.valueOf(objects[46]);
					String TotalUSD_InwardClearanceFee = String.valueOf(objects[47]);
					String TotalVND_InwardClearanceFee = String.valueOf(objects[48]);
					String TotalUSD_OutwardClearanceFee = String.valueOf(objects[49]);
					String TotalVND_OutwardClearanceFee = String.valueOf(objects[50]);
					String ClearanceFeeAmount = String.valueOf(objects[51]);
					String UsdTotalAmount = String.valueOf(objects[52]);
					String VndTotalAmount = String.valueOf(objects[53]);
					String PaymentAmount = String.valueOf(objects[54]);
					String ExchangeRate = String.valueOf(objects[55]);
					String PaymentDate = String.valueOf(objects[56]);
					String PortAuthorityTotalAmount = String.valueOf(objects[57]);
					String FinancialAccountant = String.valueOf(objects[58]);
					String DepartmentCode = String.valueOf(objects[59]);
					String DepartmentName = String.valueOf(objects[60]);
					String Cash_TotalAmount = String.valueOf(objects[61]);
					String Transfer_TotalAmount = String.valueOf(objects[62]);
					String AnchorHourVND = String.valueOf(objects[63]);
					String AnchorHourUSD = String.valueOf(objects[64]);
					String TaxCode = String.valueOf(objects[65]);
					String PatternCode = String.valueOf(objects[66]);
					String SerialCode = String.valueOf(objects[67]);
					String MaritimeNameVN = String.valueOf(objects[68]);
					String CertificateNo = String.valueOf(objects[69]);
					String TimeOfDeparture = String.valueOf(objects[70]);
					String TimeOfArrival = String.valueOf(objects[71]);
					String VrCode = String.valueOf(objects[72]);
					String PortHarbourCode = String.valueOf(objects[73]);
					String PortHarbourNameVN = String.valueOf(objects[74]);
					String PortName = String.valueOf(objects[75]);
					String StateName = String.valueOf(objects[76]);
					String ShipAgencyName = String.valueOf(objects[77]);
					String MainPurpose = String.valueOf(objects[78]);
					String NumberItineraryPerMonth = String.valueOf(objects[79]);
					String TotalAnchorageHours = String.valueOf(objects[80]);

					String TotalUSD_TonnageFee = String.valueOf(objects[81]);
					String TotalUSDConversionVND_OnShipAnchorageFee = String.valueOf(objects[82]);
					String TotalUSDConversionVND_OnCargoAnchorageFee = String.valueOf(objects[83]);
					String TotalUSDConversionVND_NavigationFee = String.valueOf(objects[84]);
					String TotalUSDConversionVND_ClearanceFee = String.valueOf(objects[85]);
					String TotalUSDConversionVND_SeaProtestFee = String.valueOf(objects[86]);
					String TotalUSDConversionVND_OtherFee = String.valueOf(objects[87]);
					String ApprovalDate = String.valueOf(objects[88]);
					String FundTransferNo = String.valueOf(objects[89]);
					String FundTransferDate = String.valueOf(objects[90]);
					String FundTransferDetails = String.valueOf(objects[91]);
					String ReceiptNo = String.valueOf(objects[92]);
					String ReceiptDate = String.valueOf(objects[93]);
					String ReceiptDetails = String.valueOf(objects[94]);
					String ReceiptRemark = String.valueOf(objects[95]);
					String ReceiptBookNo = String.valueOf(objects[96]);
					String eReceiptNo = String.valueOf(objects[97]);
					String eReceiptDate = String.valueOf(objects[98]);
					String eReceiptDetails = String.valueOf(objects[99]);
					String EmailRecipients = String.valueOf(objects[100]);
					String PortClearanceCertificateNo = String.valueOf(objects[101]);
					String ShipOwnerCode = String.valueOf(objects[102]);
					String HideShipOwnerShipAgency = String.valueOf(objects[103]);
					String DoCharge = String.valueOf(objects[104]);
					String HideExchangeRate = String.valueOf(objects[105]);
					String DocumentaryExchangeRate = String.valueOf(objects[106]);
					String DocumentaryCurrencyExgDate = String.valueOf(objects[107]);
					String ExchangeRateDifferences = String.valueOf(objects[108]);
					String DifferencesExgDate = String.valueOf(objects[109]);
					String PaymentDifferences = String.valueOf(objects[110]);
					String PaymentInFigures = String.valueOf(objects[111]);
					String PaymentRealAmount = String.valueOf(objects[112]);
					String F1411Vnd = String.valueOf(objects[113]);
					String F1412Vnd = String.valueOf(objects[114]);
					String F1413Vnd = String.valueOf(objects[115]);
					String F1421Vnd = String.valueOf(objects[116]);
					String F1422Vnd = String.valueOf(objects[117]);
					String F1423Vnd = String.valueOf(objects[118]);
					String F1431Vnd = String.valueOf(objects[119]);
					String F1432Vnd = String.valueOf(objects[120]);
					String NameOfPortFacility = String.valueOf(objects[121]);
					String AddressOfPortFacility = String.valueOf(objects[122]);
					String StatementNumber = String.valueOf(objects[123]);
					String DateOfStatement = String.valueOf(objects[124]);
					String StatementIssuedAt = String.valueOf(objects[125]);
					String StatementValidUntil = String.valueOf(objects[126]);
					String F1411Remarks = String.valueOf(objects[127]);
					String F1412Remarks = String.valueOf(objects[128]);
					String F1413Remarks = String.valueOf(objects[129]);
					String F1421Remarks = String.valueOf(objects[130]);
					String F1422Remarks = String.valueOf(objects[131]);
					String F1423Remarks = String.valueOf(objects[132]);
					String F1431Remarks = String.valueOf(objects[133]);
					String F1432Remarks = String.valueOf(objects[134]);

					String DocumentaryNo = String.valueOf(objects[135]);
					String DocumentaryIssued = String.valueOf(objects[136]);
					String DocumentaryKind = String.valueOf(objects[137]);
					String PaidAdvanceAmount = String.valueOf(objects[138]);
					String F1319Vnd = String.valueOf(objects[139]);
					String F1311Vnd = String.valueOf(objects[140]);
					String F1312Usd = String.valueOf(objects[141]);
					String F1329Vnd = String.valueOf(objects[142]);
					String F1321Vnd = String.valueOf(objects[143]);
					String F1322Usd = String.valueOf(objects[144]);
					String F1339Vnd = String.valueOf(objects[145]);
					String F1331Vnd = String.valueOf(objects[146]);
					String F1332Usd = String.valueOf(objects[147]);
					String F1359Vnd = String.valueOf(objects[148]);
					String F1351Vnd = String.valueOf(objects[149]);
					String F1352Usd = String.valueOf(objects[150]);
					String F1369Vnd = String.valueOf(objects[151]);
					String F1361Vnd = String.valueOf(objects[152]);
					String F1362Usd = String.valueOf(objects[153]);
					String F1379Vnd = String.valueOf(objects[154]);
					String F1371Vnd = String.valueOf(objects[155]);
					String F1372Usd = String.valueOf(objects[156]);
					String F1313Vnd = String.valueOf(objects[157]);
					String F1363Vnd = String.valueOf(objects[158]);
					String F1373Vnd = String.valueOf(objects[159]);


					String ModifiedDate = String.valueOf(objects[160]);



					JSONObject object = JSONFactoryUtil.createJSONObject();


					object.put("ItineraryNo", ItineraryNo);
					object.put("SequenceNo", SequenceNo);
					object.put("DocumentaryCode", DocumentaryCode);
					object.put("PortofAuthority", PortofAuthority);
					object.put("StampSerialNo", StampSerialNo);
					object.put("Bienlai_giay", Bienlai_giay);
					object.put("NgayBienlai_giay", PrintingDate(NgayBienlai_giay, StringPool.SLASH));
					object.put("NgayBienlaidientu", PrintingDate(NgayBienlaidientu, StringPool.SLASH));
					object.put("Bienlaidientu", Bienlaidientu);
					object.put("PaymentType", PaymentType);
					object.put("PaymentName", PaymentName);
					object.put("NameOfShip", NameOfShip);
					object.put("FlagStateOfShip", FlagStateOfShip);
					object.put("IMONumber", IMONumber);
					object.put("CallSign", CallSign);
					object.put("RegistryNumber", RegistryNumber);
					object.put("ArrivalPortName", ArrivalPortName);
					object.put("ShipBoat", ShipBoat);
					object.put("GT", GT);
					object.put("DWT", DWT);
					object.put("GTPercentage", GTPercentage);
					object.put("GTConversion", GTConversion);
					object.put("GT_USD", GT_USD);
					object.put("GT_VND", GT_VND);
					object.put("GTConversion_USD", GTConversion_USD);
					object.put("GTConversion_VND", GTConversion_VND);
					object.put("Thuy_noi_dia", Thuy_noi_dia);
					object.put("TotalUSD_InwardTonnageFee", TotalUSD_InwardTonnageFee);
					object.put("TotalVND_InwardTonnageFee", TotalVND_InwardTonnageFee);
					object.put("TotalUSD_OutwardTonnageFee", TotalUSD_OutwardTonnageFee);
					object.put("TotalVND_OutwardTonnageFee", TotalVND_OutwardTonnageFee);
					object.put("TonnageFeeAmount", TonnageFeeAmount);
					object.put("TotalUSD_OnShipAnchorageFee", TotalUSD_OnShipAnchorageFee);
					object.put("TotalVND_OnShipAnchorageFee", TotalVND_OnShipAnchorageFee);
					object.put("TotalUSD_OnCargoAnchorageFee", TotalUSD_OnCargoAnchorageFee);
					object.put("TotalVND_OnCargoAnchorageFee", TotalVND_OnCargoAnchorageFee);
					object.put("AnchorageFeeAmount", AnchorageFeeAmount);
					object.put("TotalUSD_InwardSeaProtestFee", TotalUSD_InwardSeaProtestFee);
					object.put("TotalVND_InwardSeaProtestFee", TotalVND_InwardSeaProtestFee);
					object.put("TotalUSD_OutwardSeaProtestFee", TotalUSD_OutwardSeaProtestFee);
					object.put("TotalVND_OutwardSeaProtestFee", TotalVND_OutwardSeaProtestFee);
					object.put("SeaProtestFeeAmount", SeaProtestFeeAmount);
					object.put("TotalUSD_InwardNavigationFee", TotalUSD_InwardNavigationFee);
					object.put("TotalVND_InwardNavigationFee", TotalVND_InwardNavigationFee);
					object.put("TotalUSD_OutwardNavigationFee", TotalUSD_OutwardNavigationFee);
					object.put("TotalVND_OutwardNavigationFee", TotalVND_OutwardNavigationFee);
					object.put("NavigationFeeAmount", NavigationFeeAmount);
					object.put("TotalUSD_InwardClearanceFee", TotalUSD_InwardClearanceFee);
					object.put("TotalVND_InwardClearanceFee", TotalVND_InwardClearanceFee);
					object.put("TotalUSD_OutwardClearanceFee", TotalUSD_OutwardClearanceFee);
					object.put("TotalVND_OutwardClearanceFee", TotalVND_OutwardClearanceFee);
					object.put("ClearanceFeeAmount", ClearanceFeeAmount);
					object.put("UsdTotalAmount", UsdTotalAmount);
					object.put("VndTotalAmount", VndTotalAmount);
					object.put("PaymentAmount", PaymentAmount);
					object.put("ExchangeRate", ExchangeRate);
					object.put("PaymentDate", PrintingDate(PaymentDate, StringPool.SLASH));
					object.put("PortAuthorityTotalAmount", PortAuthorityTotalAmount);
					object.put("FinancialAccountant", FinancialAccountant);
					object.put("DepartmentCode", DepartmentCode);
					object.put("DepartmentName", DepartmentName);
					object.put("Cash_TotalAmount", Cash_TotalAmount);
					object.put("Transfer_TotalAmount", Transfer_TotalAmount);
					object.put("AnchorHourVND", AnchorHourVND);
					object.put("AnchorHourUSD", AnchorHourUSD);
					object.put("TaxCode", TaxCode);
					object.put("PatternCode", PatternCode);
					object.put("SerialCode", SerialCode);
					object.put("MaritimeNameVN", MaritimeNameVN);
					object.put("CertificateNo", CertificateNo);
					object.put("TimeOfDeparture", PrintingDate(TimeOfDeparture, StringPool.SLASH));
					object.put("TimeOfArrival", PrintingDate(TimeOfArrival, StringPool.SLASH));
					object.put("VrCode", VrCode);
					object.put("PortHarbourCode", PortHarbourCode);
					object.put("PortHarbourNameVN", PortHarbourNameVN);
					object.put("PortName", PortName);
					object.put("StateName", StateName);
					object.put("ShipAgencyName", ShipAgencyName);
					object.put("MainPurpose", MainPurpose);
					object.put("NumberItineraryPerMonth", NumberItineraryPerMonth);
					object.put("TotalAnchorageHours", TotalAnchorageHours);

					object.put("TotalUSD_TonnageFee",  TotalUSD_TonnageFee);
					object.put("TotalUSDConversionVND_OnShipAnchorageFee",  TotalUSDConversionVND_OnShipAnchorageFee);
					object.put("TotalUSDConversionVND_OnCargoAnchorageFee",  TotalUSDConversionVND_OnCargoAnchorageFee);
					object.put("TotalUSDConversionVND_NavigationFee",  TotalUSDConversionVND_NavigationFee);
					object.put("TotalUSDConversionVND_ClearanceFee",  TotalUSDConversionVND_ClearanceFee);
					object.put("TotalUSDConversionVND_SeaProtestFee",  TotalUSDConversionVND_SeaProtestFee);
					object.put("TotalUSDConversionVND_OtherFee",  TotalUSDConversionVND_OtherFee);
					object.put("ApprovalDate",  PrintingDate(ApprovalDate, StringPool.SLASH));
					object.put("FundTransferNo",  FundTransferNo);
					object.put("FundTransferDate",  PrintingDate(FundTransferDate, StringPool.SLASH));
					object.put("FundTransferDetails",  FundTransferDetails);
					object.put("ReceiptNo",  ReceiptNo);
					object.put("ReceiptDate",  PrintingDate(ReceiptDate, StringPool.SLASH));
					object.put("ReceiptDetails",  ReceiptDetails);
					object.put("ReceiptRemark",  ReceiptRemark);
					object.put("ReceiptBookNo",  ReceiptBookNo);
					object.put("eReceiptNo",  eReceiptNo);
					object.put("eReceiptDate",  PrintingDate(eReceiptDate, StringPool.SLASH));
					object.put("eReceiptDetails",  eReceiptDetails);
					object.put("EmailRecipients",  EmailRecipients);
					object.put("PortClearanceCertificateNo",  PortClearanceCertificateNo);
					object.put("ShipOwnerCode",  ShipOwnerCode);
					object.put("HideShipOwnerShipAgency",  HideShipOwnerShipAgency);
					object.put("DoCharge",  DoCharge);
					object.put("HideExchangeRate",  HideExchangeRate);
					object.put("DocumentaryExchangeRate",  DocumentaryExchangeRate);
					object.put("DocumentaryCurrencyExgDate",  PrintingDate(DocumentaryCurrencyExgDate, StringPool.SLASH));
					object.put("ExchangeRateDifferences",  ExchangeRateDifferences);
					object.put("DifferencesExgDate",  PrintingDate(DifferencesExgDate, StringPool.SLASH));
					object.put("PaymentDifferences",  PaymentDifferences);
					object.put("PaymentInFigures",  PaymentInFigures);
					object.put("PaymentRealAmount",  PaymentRealAmount);
					object.put("F1411Vnd",  F1411Vnd);
					object.put("F1412Vnd",  F1412Vnd);
					object.put("F1413Vnd",  F1413Vnd);
					object.put("F1421Vnd",  F1421Vnd);
					object.put("F1422Vnd",  F1422Vnd);
					object.put("F1423Vnd",  F1423Vnd);
					object.put("F1431Vnd",  F1431Vnd);
					object.put("F1432Vnd",  F1432Vnd);
					object.put("NameOfPortFacility",  NameOfPortFacility);
					object.put("AddressOfPortFacility",  AddressOfPortFacility);
					object.put("StatementNumber",  StatementNumber);
					object.put("DateOfStatement",  PrintingDate(DateOfStatement, StringPool.SLASH));
					object.put("StatementIssuedAt",  StatementIssuedAt);
					object.put("StatementValidUntil",  PrintingDate(StatementValidUntil, StringPool.SLASH));
					object.put("F1411Remarks",  F1411Remarks);
					object.put("F1412Remarks",  F1412Remarks);
					object.put("F1413Remarks",  F1413Remarks);
					object.put("F1421Remarks",  F1421Remarks);
					object.put("F1422Remarks",  F1422Remarks);
					object.put("F1423Remarks",  F1423Remarks);
					object.put("F1431Remarks",  F1431Remarks);
					object.put("F1432Remarks",  F1432Remarks);
					object.put("F1432Remarks",  F1432Remarks);
					object.put("SecurityFee",  0);

					object.put("DocumentaryNo",  DocumentaryNo);
					object.put("DocumentaryIssued",  PrintingDate(DocumentaryIssued, StringPool.SLASH));
					object.put("DocumentaryKind",  DocumentaryKind);
					object.put("PaidAdvanceAmount",  PaidAdvanceAmount);
					object.put("F1319Vnd",  F1319Vnd);
					object.put("F1311Vnd",  F1311Vnd);
					object.put("F1312Usd",  F1312Usd);
					object.put("F1329Vnd",  F1329Vnd);
					object.put("F1321Vnd",  F1321Vnd);
					object.put("F1322Usd",  F1322Usd);
					object.put("F1339Vnd",  F1339Vnd);
					object.put("F1331Vnd",  F1331Vnd);
					object.put("F1332Usd",  F1332Usd);
					object.put("F1359Vnd",  F1359Vnd);
					object.put("F1351Vnd",  F1351Vnd);
					object.put("F1352Usd",  F1352Usd);
					object.put("F1369Vnd",  F1369Vnd);
					object.put("F1361Vnd",  F1361Vnd);
					object.put("F1362Usd",  F1362Usd);
					object.put("F1379Vnd",  F1379Vnd);
					object.put("F1371Vnd",  F1371Vnd);
					object.put("F1372Usd",  F1372Usd);
					object.put("F1313Vnd",  F1313Vnd);
					object.put("F1363Vnd",  F1363Vnd);
					object.put("F1373Vnd",  F1373Vnd);

					if (Validator.isNotNull(ApprovalDate)) {
						try {
							Date date = FormatData.formatDateShort
									.parse(ApprovalDate);
							object.put("ApprovalDateDDMMYYYY", FormatData.formatDDMMYYYY
									.format(date));
						} catch (ParseException e) {
							log.error(e.getMessage());
						}
					}

					if (Validator.isNotNull(PaymentDate)) {
						try {
							Date date = FormatData.formatDateShort
									.parse(PaymentDate);
							object.put("PaymentDateDDMMYYYY", FormatData.formatDDMMYYYY
									.format(date));
						} catch (ParseException e) {
							log.error(e.getMessage());
						}
					}

					if (Validator.isNotNull(DocumentaryIssued)) {
						try {
							Date date = FormatData.formatDateShort
									.parse(DocumentaryIssued);
							object.put("DocumentaryIssuedDDMMYYYY", FormatData.formatDDMMYYYY
									.format(date));
						} catch (ParseException e) {
							log.error(e.getMessage());
						}
					}

					if (Validator.isNotNull(TimeOfDeparture)) {
						try {
							Date date = FormatData.formatDateShort
									.parse(TimeOfDeparture);
							object.put("DepartureDateDDMMYYYY", FormatData.formatDDMMYYYY
									.format(date));
						} catch (ParseException e) {
							log.error(e.getMessage());
						}
					}

					object.put("ArrivalDateDDMMYYYY", PrintingDate(TimeOfArrival, StringPool.SLASH));
					object.put("FundTransferDateDDMMYYYY", PrintingDate(FundTransferDate, StringPool.SLASH));
					object.put("ReceiptDateDDMMYYYY", PrintingDate(ReceiptDate, StringPool.SLASH));
					object.put("eReceiptDateDDMMYYYY", PrintingDate(eReceiptDate, StringPool.SLASH));
					object.put("DocumentaryCurrencyExgDateDDMMYYYY", PrintingDate(DocumentaryCurrencyExgDate, StringPool.SLASH));
					object.put("DifferencesExgDateDDMMYYYY", PrintingDate(DifferencesExgDate, StringPool.SLASH));
					object.put("ModifiedDate", PrintingDate(ModifiedDate, StringPool.SLASH));

					Date frmDate = (Date) objects[160];
					Date tDate = (Date) objects[160];

					fromDate = StringPool.BLANK;
					toDate = StringPool.BLANK;
					if (frmDate != null) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(frmDate);

						int day = calendar.get(Calendar.DATE);

						int month = calendar.get(Calendar.MONTH) + 1;

						int year = calendar.get(Calendar.YEAR);

						fromDate = (day < 10 ? "0" + day : day)
								+ StringPool.SLASH
								+ (month < 10 ? "0" + month : month)
								+ StringPool.SLASH + year;
					}
					if (tDate != null) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(tDate);

						int day = calendar.get(Calendar.DATE);

						int month = calendar.get(Calendar.MONTH) + 1;

						int year = calendar.get(Calendar.YEAR);

						toDate = (day < 10 ? "0" + day : day)
								+ StringPool.SLASH
								+ (month < 10 ? "0" + month : month)
								+ StringPool.SLASH + year;
					}
					object.put("fromDateService", fromDate);
					object.put("toDateService", toDate);


					if ((reportCode == BaoCaoConstant.BAO_CAO_81)
							|| (reportCode == BaoCaoConstant.BAO_CAO_82)
							|| (reportCode == BaoCaoConstant.BAO_CAO_83)
							|| (reportCode == BaoCaoConstant.BAO_CAO_84)
							|| (reportCode == BaoCaoConstant.BAO_CAO_85)
							|| (reportCode == BaoCaoConstant.BAO_CAO_86)
							|| (reportCode == BaoCaoConstant.BAO_CAO_87)
							|| (reportCode == BaoCaoConstant.BAO_CAO_88)
							|| (reportCode == BaoCaoConstant.BAO_CAO_89)
							|| (reportCode == BaoCaoConstant.BAO_CAO_90)
							|| (reportCode == BaoCaoConstant.BAO_CAO_91)
							|| (reportCode == BaoCaoConstant.BAO_CAO_92)
							|| (reportCode == BaoCaoConstant.BAO_CAO_93)
							|| (reportCode == BaoCaoConstant.BAO_CAO_94)
							|| (reportCode == BaoCaoConstant.BAO_CAO_95)
							|| (reportCode == BaoCaoConstant.BAO_CAO_96)

					){

						object.put("monthGT", Validator.isNull(monthGT) ? "0.0" : monthGT);
						object.put("monthUsdTotalAmount", Validator.isNull(monthUsdTotalAmount) ? "0.0" : monthUsdTotalAmount);
						object.put("monthF1312Usd", Validator.isNull(monthF1312Usd) ? "0.0" : monthF1312Usd);
						object.put("monthF1362Usd", Validator.isNull(monthF1362Usd) ? "0.0" : monthF1362Usd);
						object.put("monthF1322Usd", Validator.isNull(monthF1322Usd) ? "0.0" : monthF1322Usd);
						object.put("monthF1332Usd", Validator.isNull(monthF1332Usd) ? "0.0" : monthF1332Usd);
						object.put("monthF1352Usd", Validator.isNull(monthF1352Usd) ? "0.0" : monthF1352Usd);
						object.put("monthF1372Usd", Validator.isNull(monthF1372Usd) ? "0.0" : monthF1372Usd);

						object.put("monthF1319Vnd", Validator.isNull(monthF1319Vnd) ? "0.0" : monthF1319Vnd);
						object.put("monthF1311Vnd", Validator.isNull(monthF1311Vnd) ? "0.0" : monthF1311Vnd);
						object.put("monthF1369Vnd", Validator.isNull(monthF1369Vnd) ? "0.0" : monthF1369Vnd);
						object.put("monthF1361Vnd", Validator.isNull(monthF1361Vnd) ? "0.0" : monthF1361Vnd);
						object.put("monthF1329Vnd", Validator.isNull(monthF1329Vnd) ? "0.0" : monthF1329Vnd);
						object.put("monthF1321Vnd", Validator.isNull(monthF1321Vnd) ? "0.0" : monthF1321Vnd);
						object.put("monthF1339Vnd", Validator.isNull(monthF1339Vnd) ? "0.0" : monthF1339Vnd);
						object.put("monthF1331Vnd", Validator.isNull(monthF1331Vnd) ? "0.0" : monthF1331Vnd);
						object.put("monthF1359Vnd", Validator.isNull(monthF1359Vnd) ? "0.0" : monthF1359Vnd);
						object.put("monthF1351Vnd", Validator.isNull(monthF1351Vnd) ? "0.0" : monthF1351Vnd);
						object.put("monthF1379Vnd", Validator.isNull(monthF1379Vnd) ? "0.0" : monthF1379Vnd);
						object.put("monthF1371Vnd", Validator.isNull(monthF1371Vnd) ? "0.0" : monthF1371Vnd);
						object.put("monthF1313Vnd", Validator.isNull(monthF1313Vnd) ? "0.0" : monthF1313Vnd);
						object.put("monthF1363Vnd", Validator.isNull(monthF1363Vnd) ? "0.0" : monthF1363Vnd);
						object.put("monthF1373Vnd", Validator.isNull(monthF1373Vnd) ? "0.0" : monthF1373Vnd);

						object.put("yearGT", Validator.isNull(yearGT) ? "0.0" : yearGT);
						object.put("yearUsdTotalAmount", Validator.isNull(yearUsdTotalAmount) ? "0.0" : yearUsdTotalAmount);
						object.put("yearF1312Usd", Validator.isNull(yearF1312Usd) ? "0.0" : yearF1312Usd);
						object.put("yearF1362Usd", Validator.isNull(yearF1362Usd) ? "0.0" : yearF1362Usd);
						object.put("yearF1322Usd", Validator.isNull(yearF1322Usd) ? "0.0" : yearF1322Usd);
						object.put("yearF1332Usd", Validator.isNull(yearF1332Usd) ? "0.0" : yearF1332Usd);
						object.put("yearF1352Usd", Validator.isNull(yearF1352Usd) ? "0.0" : yearF1352Usd);
						object.put("yearF1372Usd", Validator.isNull(yearF1372Usd) ? "0.0" : yearF1372Usd);

						object.put("yearF1319Vnd", Validator.isNull(yearF1319Vnd) ? "0.0" : yearF1319Vnd);
						object.put("yearF1311Vnd", Validator.isNull(yearF1311Vnd) ? "0.0" : yearF1311Vnd);
						object.put("yearF1369Vnd", Validator.isNull(yearF1369Vnd) ? "0.0" : yearF1369Vnd);
						object.put("yearF1361Vnd", Validator.isNull(yearF1361Vnd) ? "0.0" : yearF1361Vnd);
						object.put("yearF1329Vnd", Validator.isNull(yearF1329Vnd) ? "0.0" : yearF1329Vnd);
						object.put("yearF1321Vnd", Validator.isNull(yearF1321Vnd) ? "0.0" : yearF1321Vnd);
						object.put("yearF1339Vnd", Validator.isNull(yearF1339Vnd) ? "0.0" : yearF1339Vnd);
						object.put("yearF1331Vnd", Validator.isNull(yearF1331Vnd) ? "0.0" : yearF1331Vnd);
						object.put("yearF1359Vnd", Validator.isNull(yearF1359Vnd) ? "0.0" : yearF1359Vnd);
						object.put("yearF1351Vnd", Validator.isNull(yearF1351Vnd) ? "0.0" : yearF1351Vnd);
						object.put("yearF1379Vnd", Validator.isNull(yearF1379Vnd) ? "0.0" : yearF1379Vnd);
						object.put("yearF1371Vnd", Validator.isNull(yearF1371Vnd) ? "0.0" : yearF1371Vnd);
						object.put("yearF1313Vnd", Validator.isNull(yearF1313Vnd) ? "0.0" : yearF1313Vnd);
						object.put("yearF1363Vnd", Validator.isNull(yearF1363Vnd) ? "0.0" : yearF1363Vnd);
						object.put("yearF1373Vnd", Validator.isNull(yearF1373Vnd) ? "0.0" : yearF1373Vnd);

						object.put("monthCumulative", monthCumulative);
						object.put("yearCumulative", yearCumulative);
					}
					if (Validator.isNotNull(String.valueOf(objects[0]))) {
						array.put(object);

						HavingObject = true;
					}
				}
				result.put("reportDetails", array);

			}
			if (HavingObject != true) {
				JSONObject object = JSONFactoryUtil.createJSONObject();
				if ((reportCode == BaoCaoConstant.BAO_CAO_81)
						|| (reportCode == BaoCaoConstant.BAO_CAO_82)
						|| (reportCode == BaoCaoConstant.BAO_CAO_83)
						|| (reportCode == BaoCaoConstant.BAO_CAO_84)
						|| (reportCode == BaoCaoConstant.BAO_CAO_85)
						|| (reportCode == BaoCaoConstant.BAO_CAO_86)
						|| (reportCode == BaoCaoConstant.BAO_CAO_87)
						|| (reportCode == BaoCaoConstant.BAO_CAO_88)
						|| (reportCode == BaoCaoConstant.BAO_CAO_89)
						|| (reportCode == BaoCaoConstant.BAO_CAO_90)
						|| (reportCode == BaoCaoConstant.BAO_CAO_91)
						|| (reportCode == BaoCaoConstant.BAO_CAO_92)
						|| (reportCode == BaoCaoConstant.BAO_CAO_93)
						|| (reportCode == BaoCaoConstant.BAO_CAO_94)
						|| (reportCode == BaoCaoConstant.BAO_CAO_95)
						|| (reportCode == BaoCaoConstant.BAO_CAO_96)

				){

					object.put("monthGT", monthGT);
					object.put("monthUsdTotalAmount", monthUsdTotalAmount);
					object.put("monthF1312Usd", monthF1312Usd);
					object.put("monthF1362Usd", monthF1362Usd);
					object.put("monthF1322Usd", monthF1322Usd);
					object.put("monthF1332Usd", monthF1332Usd);
					object.put("monthF1352Usd", monthF1352Usd);
					object.put("monthF1372Usd", monthF1372Usd);

					object.put("monthF1319Vnd", Validator.isNull(monthF1319Vnd) ? "0.0" : monthF1319Vnd);
					object.put("monthF1311Vnd", Validator.isNull(monthF1311Vnd) ? "0.0" : monthF1311Vnd);
					object.put("monthF1369Vnd", Validator.isNull(monthF1369Vnd) ? "0.0" : monthF1369Vnd);
					object.put("monthF1361Vnd", Validator.isNull(monthF1361Vnd) ? "0.0" : monthF1361Vnd);
					object.put("monthF1329Vnd", Validator.isNull(monthF1329Vnd) ? "0.0" : monthF1329Vnd);
					object.put("monthF1321Vnd", Validator.isNull(monthF1321Vnd) ? "0.0" : monthF1321Vnd);
					object.put("monthF1339Vnd", Validator.isNull(monthF1339Vnd) ? "0.0" : monthF1339Vnd);
					object.put("monthF1331Vnd", Validator.isNull(monthF1331Vnd) ? "0.0" : monthF1331Vnd);
					object.put("monthF1359Vnd", Validator.isNull(monthF1359Vnd) ? "0.0" : monthF1359Vnd);
					object.put("monthF1351Vnd", Validator.isNull(monthF1351Vnd) ? "0.0" : monthF1351Vnd);
					object.put("monthF1379Vnd", Validator.isNull(monthF1379Vnd) ? "0.0" : monthF1379Vnd);
					object.put("monthF1371Vnd", Validator.isNull(monthF1371Vnd) ? "0.0" : monthF1371Vnd);
					object.put("monthF1313Vnd", Validator.isNull(monthF1313Vnd) ? "0.0" : monthF1313Vnd);
					object.put("monthF1363Vnd", Validator.isNull(monthF1363Vnd) ? "0.0" : monthF1363Vnd);
					object.put("monthF1373Vnd", Validator.isNull(monthF1373Vnd) ? "0.0" : monthF1373Vnd);

					object.put("yearGT", yearGT);
					object.put("yearUsdTotalAmount", yearUsdTotalAmount);
					object.put("yearF1312Usd", yearF1312Usd);
					object.put("yearF1362Usd", yearF1362Usd);
					object.put("yearF1322Usd", yearF1322Usd);
					object.put("yearF1332Usd", yearF1332Usd);
					object.put("yearF1352Usd", yearF1352Usd);
					object.put("yearF1372Usd", yearF1372Usd);

					object.put("yearF1319Vnd", Validator.isNull(yearF1319Vnd) ? "0.0" : yearF1319Vnd);
					object.put("yearF1311Vnd", Validator.isNull(yearF1311Vnd) ? "0.0" : yearF1311Vnd);
					object.put("yearF1369Vnd", Validator.isNull(yearF1369Vnd) ? "0.0" : yearF1369Vnd);
					object.put("yearF1361Vnd", Validator.isNull(yearF1361Vnd) ? "0.0" : yearF1361Vnd);
					object.put("yearF1329Vnd", Validator.isNull(yearF1329Vnd) ? "0.0" : yearF1329Vnd);
					object.put("yearF1321Vnd", Validator.isNull(yearF1321Vnd) ? "0.0" : yearF1321Vnd);
					object.put("yearF1339Vnd", Validator.isNull(yearF1339Vnd) ? "0.0" : yearF1339Vnd);
					object.put("yearF1331Vnd", Validator.isNull(yearF1331Vnd) ? "0.0" : yearF1331Vnd);
					object.put("yearF1359Vnd", Validator.isNull(yearF1359Vnd) ? "0.0" : yearF1359Vnd);
					object.put("yearF1351Vnd", Validator.isNull(yearF1351Vnd) ? "0.0" : yearF1351Vnd);
					object.put("yearF1379Vnd", Validator.isNull(yearF1379Vnd) ? "0.0" : yearF1379Vnd);
					object.put("yearF1371Vnd", Validator.isNull(yearF1371Vnd) ? "0.0" : yearF1371Vnd);
					object.put("yearF1313Vnd", Validator.isNull(yearF1313Vnd) ? "0.0" : yearF1313Vnd);
					object.put("yearF1363Vnd", Validator.isNull(yearF1363Vnd) ? "0.0" : yearF1363Vnd);
					object.put("yearF1373Vnd", Validator.isNull(yearF1373Vnd) ? "0.0" : yearF1373Vnd);

					object.put("monthCumulative", monthCumulative);
					object.put("yearCumulative", yearCumulative);

				}
				array.put(object);
				result.put("reportDetails", array);
			}



			try {
				Date dFromDate = FormatData.formatDDMMYYYY.parse(createDate);
				String sCreateDate = vn.gt.tichhop.report.ReportFunction.parserDateToString5(dFromDate);
				result.put("createDate", sCreateDate);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}



			result.put("fromDate", reportDateFrom);
			result.put("toDate", reportDateTo);

			DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(portofAuthority);
			String maritimeNameVN = dmMaritime.getMaritimeNameVN();
			String maritimeName = dmMaritime.getMaritimeName();
			String directorOfMaritime = dmMaritime.getCityCode();
			String maritimeAddress = dmMaritime.getAddressVN();
			String signTitle = StringPool.BLANK;
			String signName = StringPool.BLANK;
			result.put("maritimeNameVN", maritimeNameVN);
			result.put("maritimeName", maritimeName);
			result.put("directorOfMaritime", directorOfMaritime);
			result.put("maritimeAddress", maritimeAddress);
			result.put("portofAuthority", portofAuthority);
			result.put("signTitle", signTitle);
			result.put("signName", signName);
			result.put("reportUser", reportUser);
			result.put("reportMadeBy", reportUser);


		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return result;
	}










	public JSONObject findThongKeBienLai82(String portofAuthority, String reportUser,
										   String reportPeriod, String reportYear, String reportMonth,
										   String fromDate, String toDate, int start, int end) throws SystemException {

		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray array = JSONFactoryUtil.createJSONArray();

		try {
			
			StringBuilder query = new StringBuilder();



			query.append(" ) as thongke Where 1=1 ");

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




			result.put("fromDate", fromDate);
			result.put("toDate", toDate);



			DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(portofAuthority);
			String maritimeNameVN = dmMaritime.getMaritimeNameVN();
			String maritimeName = dmMaritime.getMaritimeName();
			String directorOfMaritime = dmMaritime.getCityCode();
			String maritimeAddressVN = dmMaritime.getAddressVN();
			String signTitle = StringPool.BLANK;
			String signName = StringPool.BLANK;
			result.put("maritimeNameVN", maritimeNameVN);
			result.put("maritimeName", maritimeName);
			result.put("directorOfMaritime", directorOfMaritime);
			result.put("maritimeAddressVN", maritimeAddressVN);
			result.put("portofAuthority", portofAuthority);
			result.put("signTitle", signTitle);
			result.put("signName", signName);
			result.put("reportUser", reportUser);


		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return result;
	}

	public JSONObject findThongKeBienLai83(String portofAuthority, String reportUser,
										   String reportPeriod, String reportYear, String reportMonth,
										   String fromDate, String toDate, int start, int end) throws SystemException {

		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray array = JSONFactoryUtil.createJSONArray();

		try {
			
			StringBuilder query = new StringBuilder();



			query.append(" ) as thongke Where 1=1 ");

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




			result.put("fromDate", fromDate);
			result.put("toDate", toDate);



			DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(portofAuthority);
			String maritimeNameVN = dmMaritime.getMaritimeNameVN();
			String maritimeName = dmMaritime.getMaritimeName();
			String directorOfMaritime = dmMaritime.getCityCode();
			String maritimeAddressVN = dmMaritime.getAddressVN();
			String signTitle = StringPool.BLANK;
			String signName = StringPool.BLANK;
			result.put("maritimeNameVN", maritimeNameVN);
			result.put("maritimeName", maritimeName);
			result.put("directorOfMaritime", directorOfMaritime);
			result.put("maritimeAddressVN", maritimeAddressVN);
			result.put("portofAuthority", portofAuthority);
			result.put("signTitle", signTitle);
			result.put("signName", signName);
			result.put("reportUser", reportUser);


		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return result;
	}

	public JSONObject findThongKeBienLai84(String portofAuthority, String reportUser,
										   String reportPeriod, String reportYear, String reportMonth,
										   String fromDate, String toDate, int start, int end) throws SystemException {

		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray array = JSONFactoryUtil.createJSONArray();

		try {
			
			StringBuilder query = new StringBuilder();



			query.append(" ) as thongke Where 1=1 ");

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




			result.put("fromDate", fromDate);
			result.put("toDate", toDate);



			DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(portofAuthority);
			String maritimeNameVN = dmMaritime.getMaritimeNameVN();
			String maritimeName = dmMaritime.getMaritimeName();
			String directorOfMaritime = dmMaritime.getCityCode();
			String maritimeAddressVN = dmMaritime.getAddressVN();
			String signTitle = StringPool.BLANK;
			String signName = StringPool.BLANK;
			result.put("maritimeNameVN", maritimeNameVN);
			result.put("maritimeName", maritimeName);
			result.put("directorOfMaritime", directorOfMaritime);
			result.put("maritimeAddressVN", maritimeAddressVN);
			result.put("portofAuthority", portofAuthority);
			result.put("signTitle", signTitle);
			result.put("signName", signName);
			result.put("reportUser", reportUser);


		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return result;
	}

	public JSONObject findThongKeBienLai85(String portofAuthority, String reportUser,
										   String reportPeriod, String reportYear, String reportMonth,
										   String fromDate, String toDate, int start, int end) throws SystemException {

		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray array = JSONFactoryUtil.createJSONArray();

		try {
			
			StringBuilder query = new StringBuilder();



			query.append(" ) as thongke Where 1=1 ");

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




			result.put("fromDate", fromDate);
			result.put("toDate", toDate);



			DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(portofAuthority);
			String maritimeNameVN = dmMaritime.getMaritimeNameVN();
			String maritimeName = dmMaritime.getMaritimeName();
			String directorOfMaritime = dmMaritime.getCityCode();
			String maritimeAddressVN = dmMaritime.getAddressVN();
			String signTitle = StringPool.BLANK;
			String signName = StringPool.BLANK;
			result.put("maritimeNameVN", maritimeNameVN);
			result.put("maritimeName", maritimeName);
			result.put("directorOfMaritime", directorOfMaritime);
			result.put("maritimeAddressVN", maritimeAddressVN);
			result.put("portofAuthority", portofAuthority);
			result.put("signTitle", signTitle);
			result.put("signName", signName);
			result.put("reportUser", reportUser);


		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return result;
	}

	public JSONObject findThongKeBienLai86(String portofAuthority, String reportUser,
										   String reportPeriod, String reportYear, String reportMonth,
										   String fromDate, String toDate, int start, int end) throws SystemException {

		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray array = JSONFactoryUtil.createJSONArray();

		try {
			
			StringBuilder query = new StringBuilder();



			query.append(" ) as thongke Where 1=1 ");

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




			result.put("fromDate", fromDate);
			result.put("toDate", toDate);



			DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(portofAuthority);
			String maritimeNameVN = dmMaritime.getMaritimeNameVN();
			String maritimeName = dmMaritime.getMaritimeName();
			String directorOfMaritime = dmMaritime.getCityCode();
			String maritimeAddressVN = dmMaritime.getAddressVN();
			String signTitle = StringPool.BLANK;
			String signName = StringPool.BLANK;
			result.put("maritimeNameVN", maritimeNameVN);
			result.put("maritimeName", maritimeName);
			result.put("directorOfMaritime", directorOfMaritime);
			result.put("maritimeAddressVN", maritimeAddressVN);
			result.put("portofAuthority", portofAuthority);
			result.put("signTitle", signTitle);
			result.put("signName", signName);
			result.put("reportUser", reportUser);


		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return result;
	}

	public JSONObject findThongKeBienLai87(String portofAuthority, String reportUser,
										   String reportPeriod, String reportYear, String reportMonth,
										   String fromDate, String toDate, int start, int end) throws SystemException {

		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray array = JSONFactoryUtil.createJSONArray();

		try {
			
			StringBuilder query = new StringBuilder();



			query.append(" ) as thongke Where 1=1 ");

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




			result.put("fromDate", fromDate);
			result.put("toDate", toDate);



			DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(portofAuthority);
			String maritimeNameVN = dmMaritime.getMaritimeNameVN();
			String maritimeName = dmMaritime.getMaritimeName();
			String directorOfMaritime = dmMaritime.getCityCode();
			String maritimeAddressVN = dmMaritime.getAddressVN();
			String signTitle = StringPool.BLANK;
			String signName = StringPool.BLANK;
			result.put("maritimeNameVN", maritimeNameVN);
			result.put("maritimeName", maritimeName);
			result.put("directorOfMaritime", directorOfMaritime);
			result.put("maritimeAddressVN", maritimeAddressVN);
			result.put("portofAuthority", portofAuthority);
			result.put("signTitle", signTitle);
			result.put("signName", signName);
			result.put("reportUser", reportUser);


		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return result;
	}

	public JSONObject findThongKeBienLai88(String portofAuthority, String reportUser,
										   String reportPeriod, String reportYear, String reportMonth,
										   String fromDate, String toDate, int start, int end) throws SystemException {

		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray array = JSONFactoryUtil.createJSONArray();

		try {
			
			StringBuilder query = new StringBuilder();



			query.append(" ) as thongke Where 1=1 ");

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




			result.put("fromDate", fromDate);
			result.put("toDate", toDate);



			DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(portofAuthority);
			String maritimeNameVN = dmMaritime.getMaritimeNameVN();
			String maritimeName = dmMaritime.getMaritimeName();
			String directorOfMaritime = dmMaritime.getCityCode();
			String maritimeAddressVN = dmMaritime.getAddressVN();
			String signTitle = StringPool.BLANK;
			String signName = StringPool.BLANK;
			result.put("maritimeNameVN", maritimeNameVN);
			result.put("maritimeName", maritimeName);
			result.put("directorOfMaritime", directorOfMaritime);
			result.put("maritimeAddressVN", maritimeAddressVN);
			result.put("portofAuthority", portofAuthority);
			result.put("signTitle", signTitle);
			result.put("signName", signName);
			result.put("reportUser", reportUser);


		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return result;
	}

	public JSONObject findThongKeBienLai89(String portofAuthority, String reportUser,
										   String reportPeriod, String reportYear, String reportMonth,
										   String fromDate, String toDate, int start, int end) throws SystemException {

		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray array = JSONFactoryUtil.createJSONArray();

		try {
			
			StringBuilder query = new StringBuilder();



			query.append(" ) as thongke Where 1=1 ");

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




			result.put("fromDate", fromDate);
			result.put("toDate", toDate);



			DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(portofAuthority);
			String maritimeNameVN = dmMaritime.getMaritimeNameVN();
			String maritimeName = dmMaritime.getMaritimeName();
			String directorOfMaritime = dmMaritime.getCityCode();
			String maritimeAddressVN = dmMaritime.getAddressVN();
			String signTitle = StringPool.BLANK;
			String signName = StringPool.BLANK;
			result.put("maritimeNameVN", maritimeNameVN);
			result.put("maritimeName", maritimeName);
			result.put("directorOfMaritime", directorOfMaritime);
			result.put("maritimeAddressVN", maritimeAddressVN);
			result.put("portofAuthority", portofAuthority);
			result.put("signTitle", signTitle);
			result.put("signName", signName);
			result.put("reportUser", reportUser);


		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return result;
	}

	public JSONObject findThongKeBienLai90(String portofAuthority, String reportUser,
										   String reportPeriod, String reportYear, String reportMonth,
										   String fromDate, String toDate, int start, int end) throws SystemException {

		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray array = JSONFactoryUtil.createJSONArray();

		try {
			
			StringBuilder query = new StringBuilder();



			query.append(" ) as thongke Where 1=1 ");

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




			result.put("fromDate", fromDate);
			result.put("toDate", toDate);



			DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(portofAuthority);
			String maritimeNameVN = dmMaritime.getMaritimeNameVN();
			String maritimeName = dmMaritime.getMaritimeName();
			String directorOfMaritime = dmMaritime.getCityCode();
			String maritimeAddressVN = dmMaritime.getAddressVN();
			String signTitle = StringPool.BLANK;
			String signName = StringPool.BLANK;
			result.put("maritimeNameVN", maritimeNameVN);
			result.put("maritimeName", maritimeName);
			result.put("directorOfMaritime", directorOfMaritime);
			result.put("maritimeAddressVN", maritimeAddressVN);
			result.put("portofAuthority", portofAuthority);
			result.put("signTitle", signTitle);
			result.put("signName", signName);
			result.put("reportUser", reportUser);


		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return result;
	}

	public JSONObject findThongKeBienLai91(String portofAuthority, String reportUser,
										   String reportPeriod, String reportYear, String reportMonth,
										   String fromDate, String toDate, int start, int end) throws SystemException {

		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray array = JSONFactoryUtil.createJSONArray();

		try {
			
			StringBuilder query = new StringBuilder();



			query.append(" ) as thongke Where 1=1 ");

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




			result.put("fromDate", fromDate);
			result.put("toDate", toDate);



			DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(portofAuthority);
			String maritimeNameVN = dmMaritime.getMaritimeNameVN();
			String maritimeName = dmMaritime.getMaritimeName();
			String directorOfMaritime = dmMaritime.getCityCode();
			String maritimeAddressVN = dmMaritime.getAddressVN();
			String signTitle = StringPool.BLANK;
			String signName = StringPool.BLANK;
			result.put("maritimeNameVN", maritimeNameVN);
			result.put("maritimeName", maritimeName);
			result.put("directorOfMaritime", directorOfMaritime);
			result.put("maritimeAddressVN", maritimeAddressVN);
			result.put("portofAuthority", portofAuthority);
			result.put("signTitle", signTitle);
			result.put("signName", signName);
			result.put("reportUser", reportUser);


		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return result;
	}

	public JSONObject findThongKeBienLai92(String portofAuthority, String reportUser,
										   String reportPeriod, String reportYear, String reportMonth,
										   String fromDate, String toDate, int start, int end) throws SystemException {

		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray array = JSONFactoryUtil.createJSONArray();

		try {
			
			StringBuilder query = new StringBuilder();



			query.append(" ) as thongke Where 1=1 ");

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




			result.put("fromDate", fromDate);
			result.put("toDate", toDate);



			DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(portofAuthority);
			String maritimeNameVN = dmMaritime.getMaritimeNameVN();
			String maritimeName = dmMaritime.getMaritimeName();
			String directorOfMaritime = dmMaritime.getCityCode();
			String maritimeAddressVN = dmMaritime.getAddressVN();
			String signTitle = StringPool.BLANK;
			String signName = StringPool.BLANK;
			result.put("maritimeNameVN", maritimeNameVN);
			result.put("maritimeName", maritimeName);
			result.put("directorOfMaritime", directorOfMaritime);
			result.put("maritimeAddressVN", maritimeAddressVN);
			result.put("portofAuthority", portofAuthority);
			result.put("signTitle", signTitle);
			result.put("signName", signName);
			result.put("reportUser", reportUser);


		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return result;
	}

	public JSONObject findThongKeBienLai93(String portofAuthority, String reportUser,
										   String reportPeriod, String reportYear, String reportMonth,
										   String fromDate, String toDate, int start, int end) throws SystemException {

		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray array = JSONFactoryUtil.createJSONArray();

		try {
			
			StringBuilder query = new StringBuilder();



			query.append(" ) as thongke Where 1=1 ");

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




			result.put("fromDate", fromDate);
			result.put("toDate", toDate);



			DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(portofAuthority);
			String maritimeNameVN = dmMaritime.getMaritimeNameVN();
			String maritimeName = dmMaritime.getMaritimeName();
			String directorOfMaritime = dmMaritime.getCityCode();
			String maritimeAddressVN = dmMaritime.getAddressVN();
			String signTitle = StringPool.BLANK;
			String signName = StringPool.BLANK;
			result.put("maritimeNameVN", maritimeNameVN);
			result.put("maritimeName", maritimeName);
			result.put("directorOfMaritime", directorOfMaritime);
			result.put("maritimeAddressVN", maritimeAddressVN);
			result.put("portofAuthority", portofAuthority);
			result.put("signTitle", signTitle);
			result.put("signName", signName);
			result.put("reportUser", reportUser);


		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return result;
	}

	public JSONObject findThongKeBienLai94(String portofAuthority, String reportUser,
										   String reportPeriod, String reportYear, String reportMonth,
										   String fromDate, String toDate, int start, int end) throws SystemException {

		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray array = JSONFactoryUtil.createJSONArray();

		try {
			
			StringBuilder query = new StringBuilder();



			query.append(" ) as thongke Where 1=1 ");

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




			result.put("fromDate", fromDate);
			result.put("toDate", toDate);



			DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(portofAuthority);
			String maritimeNameVN = dmMaritime.getMaritimeNameVN();
			String maritimeName = dmMaritime.getMaritimeName();
			String directorOfMaritime = dmMaritime.getCityCode();
			String maritimeAddressVN = dmMaritime.getAddressVN();
			String signTitle = StringPool.BLANK;
			String signName = StringPool.BLANK;
			result.put("maritimeNameVN", maritimeNameVN);
			result.put("maritimeName", maritimeName);
			result.put("directorOfMaritime", directorOfMaritime);
			result.put("maritimeAddressVN", maritimeAddressVN);
			result.put("portofAuthority", portofAuthority);
			result.put("signTitle", signTitle);
			result.put("signName", signName);
			result.put("reportUser", reportUser);


		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return result;
	}

	public JSONObject findThongKeBienLai95(String portofAuthority, String reportUser,
										   String reportPeriod, String reportYear, String reportMonth,
										   String fromDate, String toDate, int start, int end) throws SystemException {

		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray array = JSONFactoryUtil.createJSONArray();

		try {
			
			StringBuilder query = new StringBuilder();



			query.append(" ) as thongke Where 1=1 ");

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




			result.put("fromDate", fromDate);
			result.put("toDate", toDate);



			DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(portofAuthority);
			String maritimeNameVN = dmMaritime.getMaritimeNameVN();
			String maritimeName = dmMaritime.getMaritimeName();
			String directorOfMaritime = dmMaritime.getCityCode();
			String maritimeAddressVN = dmMaritime.getAddressVN();
			String signTitle = StringPool.BLANK;
			String signName = StringPool.BLANK;
			result.put("maritimeNameVN", maritimeNameVN);
			result.put("maritimeName", maritimeName);
			result.put("directorOfMaritime", directorOfMaritime);
			result.put("maritimeAddressVN", maritimeAddressVN);
			result.put("portofAuthority", portofAuthority);
			result.put("signTitle", signTitle);
			result.put("signName", signName);
			result.put("reportUser", reportUser);


		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return result;
	}

	public JSONObject findThongKeBienLai96(String portofAuthority, String reportUser,
										   String reportPeriod, String reportYear, String reportMonth,
										   String fromDate, String toDate, int start, int end) throws SystemException {

		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray array = JSONFactoryUtil.createJSONArray();

		try {
			
			StringBuilder query = new StringBuilder();



			query.append(" ) as thongke Where 1=1 ");

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




			result.put("fromDate", fromDate);
			result.put("toDate", toDate);



			DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(portofAuthority);
			String maritimeNameVN = dmMaritime.getMaritimeNameVN();
			String maritimeName = dmMaritime.getMaritimeName();
			String directorOfMaritime = dmMaritime.getCityCode();
			String maritimeAddressVN = dmMaritime.getAddressVN();
			String signTitle = StringPool.BLANK;
			String signName = StringPool.BLANK;
			result.put("maritimeNameVN", maritimeNameVN);
			result.put("maritimeName", maritimeName);
			result.put("directorOfMaritime", directorOfMaritime);
			result.put("maritimeAddressVN", maritimeAddressVN);
			result.put("portofAuthority", portofAuthority);
			result.put("signTitle", signTitle);
			result.put("signName", signName);
			result.put("reportUser", reportUser);


		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return result;
	}

	public JSONObject findThongKeBienLai97(String portofAuthority, String reportUser,
										   String reportPeriod, String reportYear, String reportMonth,
										   String fromDate, String toDate, int start, int end) throws SystemException {

		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray array = JSONFactoryUtil.createJSONArray();

		try {
			
			StringBuilder query = new StringBuilder();



			query.append(" ) as thongke Where 1=1 ");

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




			result.put("fromDate", fromDate);
			result.put("toDate", toDate);



			DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(portofAuthority);
			String maritimeNameVN = dmMaritime.getMaritimeNameVN();
			String maritimeName = dmMaritime.getMaritimeName();
			String directorOfMaritime = dmMaritime.getCityCode();
			String maritimeAddressVN = dmMaritime.getAddressVN();
			String signTitle = StringPool.BLANK;
			String signName = StringPool.BLANK;
			result.put("maritimeNameVN", maritimeNameVN);
			result.put("maritimeName", maritimeName);
			result.put("directorOfMaritime", directorOfMaritime);
			result.put("maritimeAddressVN", maritimeAddressVN);
			result.put("portofAuthority", portofAuthority);
			result.put("signTitle", signTitle);
			result.put("signName", signName);
			result.put("reportUser", reportUser);


		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return result;
	}

	public JSONObject findThongKeBienLai98(String portofAuthority, String reportUser,
										   String reportPeriod, String reportYear, String reportMonth,
										   String fromDate, String toDate, int start, int end) throws SystemException {

		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray array = JSONFactoryUtil.createJSONArray();

		try {
			
			StringBuilder query = new StringBuilder();



			query.append(" ) as thongke Where 1=1 ");

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




			result.put("fromDate", fromDate);
			result.put("toDate", toDate);



			DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(portofAuthority);
			String maritimeNameVN = dmMaritime.getMaritimeNameVN();
			String maritimeName = dmMaritime.getMaritimeName();
			String directorOfMaritime = dmMaritime.getCityCode();
			String maritimeAddressVN = dmMaritime.getAddressVN();
			String signTitle = StringPool.BLANK;
			String signName = StringPool.BLANK;
			result.put("maritimeNameVN", maritimeNameVN);
			result.put("maritimeName", maritimeName);
			result.put("directorOfMaritime", directorOfMaritime);
			result.put("maritimeAddressVN", maritimeAddressVN);
			result.put("portofAuthority", portofAuthority);
			result.put("signTitle", signTitle);
			result.put("signName", signName);
			result.put("reportUser", reportUser);


		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return result;
	}


	public JSONObject findThongKeBienLai99(String portofAuthority, String reportUser,
										   String reportPeriod, String reportYear, String reportMonth,
										   String fromDate, String toDate, int start, int end) throws SystemException {

		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray array = JSONFactoryUtil.createJSONArray();

		try {
			
			StringBuilder query = new StringBuilder();



			query.append(" ) as thongke Where 1=1 ");

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




			result.put("fromDate", fromDate);
			result.put("toDate", toDate);



			DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(portofAuthority);
			String maritimeNameVN = dmMaritime.getMaritimeNameVN();
			String maritimeName = dmMaritime.getMaritimeName();
			String directorOfMaritime = dmMaritime.getCityCode();
			String maritimeAddressVN = dmMaritime.getAddressVN();
			String signTitle = StringPool.BLANK;
			String signName = StringPool.BLANK;
			result.put("maritimeNameVN", maritimeNameVN);
			result.put("maritimeName", maritimeName);
			result.put("directorOfMaritime", directorOfMaritime);
			result.put("maritimeAddressVN", maritimeAddressVN);
			result.put("portofAuthority", portofAuthority);
			result.put("signTitle", signTitle);
			result.put("signName", signName);
			result.put("reportUser", reportUser);


		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return result;
	}

	public JSONObject findThongKeBienLai100(String portofAuthority, String reportUser,
											String reportPeriod, String reportYear, String reportMonth,
											String fromDate, String toDate, int start, int end) throws SystemException {

		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray array = JSONFactoryUtil.createJSONArray();

		try {
			
			StringBuilder query = new StringBuilder();



			query.append(" ) as thongke Where 1=1 ");

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




			result.put("fromDate", fromDate);
			result.put("toDate", toDate);



			DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(portofAuthority);
			String maritimeNameVN = dmMaritime.getMaritimeNameVN();
			String maritimeName = dmMaritime.getMaritimeName();
			String directorOfMaritime = dmMaritime.getCityCode();
			String maritimeAddressVN = dmMaritime.getAddressVN();
			String signTitle = StringPool.BLANK;
			String signName = StringPool.BLANK;
			result.put("maritimeNameVN", maritimeNameVN);
			result.put("maritimeName", maritimeName);
			result.put("directorOfMaritime", directorOfMaritime);
			result.put("maritimeAddressVN", maritimeAddressVN);
			result.put("portofAuthority", portofAuthority);
			result.put("signTitle", signTitle);
			result.put("signName", signName);
			result.put("reportUser", reportUser);


		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return result;
	}

	public JSONObject findThongKeBienLai101(String portofAuthority, String reportUser,
											String reportPeriod, String reportYear, String reportMonth,
											String fromDate, String toDate, int start, int end) throws SystemException {

		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray array = JSONFactoryUtil.createJSONArray();

		try {
			
			StringBuilder query = new StringBuilder();



			query.append(" ) as thongke Where 1=1 ");

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




			result.put("fromDate", fromDate);
			result.put("toDate", toDate);



			DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(portofAuthority);
			String maritimeNameVN = dmMaritime.getMaritimeNameVN();
			String maritimeName = dmMaritime.getMaritimeName();
			String directorOfMaritime = dmMaritime.getCityCode();
			String maritimeAddressVN = dmMaritime.getAddressVN();
			String signTitle = StringPool.BLANK;
			String signName = StringPool.BLANK;
			result.put("maritimeNameVN", maritimeNameVN);
			result.put("maritimeName", maritimeName);
			result.put("directorOfMaritime", directorOfMaritime);
			result.put("maritimeAddressVN", maritimeAddressVN);
			result.put("portofAuthority", portofAuthority);
			result.put("signTitle", signTitle);
			result.put("signName", signName);
			result.put("reportUser", reportUser);


		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return result;
	}

	public JSONObject findThongKeBienLai102(String portofAuthority, String reportUser,
											String reportPeriod, String reportYear, String reportMonth,
											String fromDate, String toDate, int start, int end) throws SystemException {

		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray array = JSONFactoryUtil.createJSONArray();

		try {
			
			StringBuilder query = new StringBuilder();



			query.append(" ) as thongke Where 1=1 ");

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




			result.put("fromDate", fromDate);
			result.put("toDate", toDate);



			DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(portofAuthority);
			String maritimeNameVN = dmMaritime.getMaritimeNameVN();
			String maritimeName = dmMaritime.getMaritimeName();
			String directorOfMaritime = dmMaritime.getCityCode();
			String maritimeAddressVN = dmMaritime.getAddressVN();
			String signTitle = StringPool.BLANK;
			String signName = StringPool.BLANK;
			result.put("maritimeNameVN", maritimeNameVN);
			result.put("maritimeName", maritimeName);
			result.put("directorOfMaritime", directorOfMaritime);
			result.put("maritimeAddressVN", maritimeAddressVN);
			result.put("portofAuthority", portofAuthority);
			result.put("signTitle", signTitle);
			result.put("signName", signName);
			result.put("reportUser", reportUser);


		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return result;
	}

	public JSONObject findThongKeBienLai103(String portofAuthority, String reportUser,
											String reportPeriod, String reportYear, String reportMonth,
											String fromDate, String toDate, int start, int end) throws SystemException {

		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray array = JSONFactoryUtil.createJSONArray();

		try {
			
			StringBuilder query = new StringBuilder();



			query.append(" ) as thongke Where 1=1 ");

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




			result.put("fromDate", fromDate);
			result.put("toDate", toDate);



			DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(portofAuthority);
			String maritimeNameVN = dmMaritime.getMaritimeNameVN();
			String maritimeName = dmMaritime.getMaritimeName();
			String directorOfMaritime = dmMaritime.getCityCode();
			String maritimeAddressVN = dmMaritime.getAddressVN();
			String signTitle = StringPool.BLANK;
			String signName = StringPool.BLANK;
			result.put("maritimeNameVN", maritimeNameVN);
			result.put("maritimeName", maritimeName);
			result.put("directorOfMaritime", directorOfMaritime);
			result.put("maritimeAddressVN", maritimeAddressVN);
			result.put("portofAuthority", portofAuthority);
			result.put("signTitle", signTitle);
			result.put("signName", signName);
			result.put("reportUser", reportUser);


		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return result;
	}

	public JSONObject findThongKeBienLai104(String portofAuthority, String createDate, String reportUser,
											String reportPeriod, String reportYear, String reportMonth,
											String fromDate, String toDate, int start, int end) throws SystemException {

		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray array = JSONFactoryUtil.createJSONArray();

		try {
			
			StringBuilder query = new StringBuilder();



			query.append(" ) as thongke Where 1=1 ");

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




			result.put("fromDate", fromDate);
			result.put("toDate", toDate);



			DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(portofAuthority);
			String maritimeNameVN = dmMaritime.getMaritimeNameVN();
			String maritimeName = dmMaritime.getMaritimeName();
			String directorOfMaritime = dmMaritime.getCityCode();
			String maritimeAddressVN = dmMaritime.getAddressVN();
			String signTitle = StringPool.BLANK;
			String signName = StringPool.BLANK;
			result.put("maritimeNameVN", maritimeNameVN);
			result.put("maritimeName", maritimeName);
			result.put("directorOfMaritime", directorOfMaritime);
			result.put("maritimeAddressVN", maritimeAddressVN);
			result.put("portofAuthority", portofAuthority);
			result.put("signTitle", signTitle);
			result.put("signName", signName);
			result.put("reportUser", reportUser);


		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return result;
	}

	public JSONObject findThongKeBienLai105(String portofAuthority, String createDate, String reportUser,
											String reportPeriod, String reportYear, String reportMonth,
											String fromDate, String toDate, int start, int end) throws SystemException {

		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray array = JSONFactoryUtil.createJSONArray();

		try {
			
			StringBuilder query = new StringBuilder();


			query.append("SELECT iss.id, iss.CertificateNo,iss.NameOfShip, temp.Imo,iss.CallSign, iss.GT, iss.DWT, iss.SignDate, iss.DocumentName, iss.VersionNo, iss.ValidUntil, iss.IssueDate, iss.ApprovalDate, iss.CancelDate, iss.TimeOfDeparture, temp.CreatedDate, temp.PortCode  FROM issue_port_clearance iss ");
			query.append(" inner join temp_document temp ");
			query.append(" on iss.DocumentName = temp.DocumentName ");
			query.append(" and iss.DocumentYear = temp.DocumentYear ");
			query.append(" INNER JOIN (Select Max(VersionNo) as MaxVerionNo, DocumentName, DocumentYear from issue_port_clearance group by DocumentName, DocumentYear) AS MAXVersion ");
			query.append(" ON MAXVersion.DocumentName = iss.DocumentName  ");
			query.append(" AND MAXVersion.DocumentYear = iss.DocumentYear  ");
			query.append(" AND MAXVersion.MaxVerionNo = iss.VersionNo ");
			query.append(" where 1=1  ");

			// Giay phep ky so
			query.append(" AND iss.StampStatus = 2 AND iss.AttachedFile > 0 AND iss.RequestState <> 6 " );

			// cang vu hang hai
			if (Validator.isNotNull(portofAuthority) && portofAuthority.trim().length() > 0) {
				query.append(" AND iss.PortofAuthority = '"
						+ portofAuthority + "'");
			}


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
						query.append(" AND (iss.SignDate  BETWEEN '"
								+ f_strDate + " " + lastFiveDigitsFromDate + ":00'");
						query.append(" AND '" + t_strDate + " " + lastFiveDigitsToDate + ":00' OR iss.SignDate IS NULL) ");
					} else {

						query.append(" AND (iss.SignDate BETWEEN '"
								+ f_strDate + " 00:00:00'");
						query.append(" AND '" + t_strDate + " 23:59:59' OR iss.SignDate IS NULL) ");
					}

				}
			}

			query.append(" ORDER BY iss.id ASC");



			String sql = query.toString();

			log.info("=========select findThongKeBienLai105 >>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ sql);



			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).firstResult(start).maxResults(end - start).entityClass(Object.class).build();



			Iterator<Object[]> itr =queryFactory2.getResultList(builder).iterator();

			if (itr.hasNext()) {
				while (itr.hasNext()) {
					Object[] objects = itr.next();

					String SequenceNo = String.valueOf(objects[0]);
					String CertificateNo = String.valueOf(objects[1]);
					String NameOfShip = String.valueOf(objects[2]);
					String Imo = String.valueOf(objects[3]);
					String CallSign = String.valueOf(objects[4]);
					String GT = String.valueOf(objects[5]);
					String DWT = String.valueOf(objects[6]);
					String SignDate = String.valueOf(objects[7]);
					String DocumentName = String.valueOf(objects[8]);

					String VersionNo = String.valueOf(objects[9]);
					String ValidUntil = String.valueOf(objects[10]);
					String IssueDate = String.valueOf(objects[11]);
					String ApprovalDate = String.valueOf(objects[12]);
					String CancelDate = String.valueOf(objects[13]);
					String TimeOfDeparture = String.valueOf(objects[14]);
					String CreatedDate = String.valueOf(objects[15]);
					String PortCode = String.valueOf(objects[16]);

					JSONObject object = JSONFactoryUtil.createJSONObject();


					//object.put("ItineraryNo", ItineraryNo);
					object.put("SequenceNo", SequenceNo);
					object.put("CertificateNo", CertificateNo);
					object.put("NameOfShip", NameOfShip);
					object.put("Imo", Imo);
					object.put("CallSign", CallSign);
					object.put("GT", GT);
					object.put("DWT", DWT);
					object.put("DocumentName", DocumentName);
					object.put("VersionNo", VersionNo);



					try {
						Date dSignDate = FormatData.formatDateShort.parse(SignDate);
						//String sSignDate = vn.gt.tichhop.report.ReportFunction.parseDateToString(dSignDate);
						String sSignDate = FormatData.parseDateToTringType4(dSignDate);
						object.put("SignDate", sSignDate);
					} catch (ParseException e) {
						log.error(e.getMessage());
					}

					try {
						Date dCancelDate = FormatData.formatDateShort.parse(CancelDate);
						//String sCancelDate = vn.gt.tichhop.report.ReportFunction.parseDateToString(dCancelDate);
						String sCancelDate = FormatData.parseDateToTringType4(dCancelDate);
						object.put("CancelDate", sCancelDate);
					} catch (ParseException e) {
						log.error(e.getMessage());
					}

					try {
						Date dApprovalDate = FormatData.formatDateShort.parse(ApprovalDate);
						//String sApprovalDate = vn.gt.tichhop.report.ReportFunction.parseDateToString(dApprovalDate);
						String sApprovalDate = FormatData.parseDateToTringType4(dApprovalDate);
						object.put("ApprovalDate", sApprovalDate);
					} catch (ParseException e) {
						log.error(e.getMessage());
					}

					try {
						Date dIssueDate = FormatData.formatDateShort.parse(IssueDate);
						//String sIssueDate = vn.gt.tichhop.report.ReportFunction.parseDateToString(dIssueDate);
						String sIssueDate = FormatData.parseDateToTringType4(dIssueDate);
						object.put("IssueDate", sIssueDate);
					} catch (ParseException e) {
						log.error(e.getMessage());
					}

					try {
						Date dValidUntil = FormatData.formatDateShort.parse(ValidUntil);
						//String sValidUntil = vn.gt.tichhop.report.ReportFunction.parseDateToString(dValidUntil);
						String sValidUntil = FormatData.parseDateToTringType4(dValidUntil);
						object.put("ValidUntil", sValidUntil);
					} catch (ParseException e) {
						log.error(e.getMessage());
					}
					try {
						Date dTimeOfDeparture = FormatData.formatDateShort.parse(TimeOfDeparture);
						//String sTimeOfDeparture = vn.gt.tichhop.report.ReportFunction.parseDateToString(dTimeOfDeparture);
						String sTimeOfDeparture = FormatData.parseDateToTringType4(dTimeOfDeparture);
						object.put("TimeOfDeparture", sTimeOfDeparture);
					} catch (ParseException e) {
						log.error(e.getMessage());
					}

					String PortName = vn.gt.utils.GetNameFunction.getPortName(FormatData.formatDateShort.parse(CreatedDate), PortCode);
					object.put("PortName", PortName);

					if (Validator.isNotNull(String.valueOf(objects[0]))) {
						array.put(object);
					}

				}
				result.put("reportDetails", array);
			}
			try {
				Date dFromDate = FormatData.formatDDMMYYYY.parse(createDate);
				String sCreateDate = vn.gt.tichhop.report.ReportFunction.parserDateToString5(dFromDate);
				result.put("createDate", sCreateDate);
			} catch (ParseException e) {
				log.error(e.getMessage());
			}

			result.put("fromDate", fromDate);
			result.put("toDate", toDate);



			DmMaritime dmMaritime = DmMaritimeLocalServiceUtil.getByMaritimeCode(portofAuthority);
			String maritimeNameVN = dmMaritime.getMaritimeNameVN();
			String maritimeName = dmMaritime.getMaritimeName();
			String directorOfMaritime = dmMaritime.getCityCode();
			String maritimeAddressVN = dmMaritime.getAddressVN();
			String signTitle = StringPool.BLANK;
			String signName = StringPool.BLANK;
			result.put("maritimeNameVN", maritimeNameVN);
			result.put("maritimeName", maritimeName);
			result.put("directorOfMaritime", directorOfMaritime);
			result.put("maritimeAddressVN", maritimeAddressVN);
			result.put("portofAuthority", portofAuthority);
			result.put("signTitle", signTitle);
			result.put("signName", signName);
			result.put("reportUser", reportUser);


		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return result;
	}

	public String PrintingDate(String fromDate, String slashOrDash) {
		String reportDateFrom = StringPool.BLANK;
		String reportDateTo = StringPool.BLANK;
		if (Validator.isNotNull(fromDate)) {
			Date f_date = null;

			try {
				if (fromDate.contains("/")) {
					f_date = FormatData.formatDDMMYYYY.parse(fromDate);

				} else if (fromDate.contains("-")) {
					f_date = FormatData.formatDateShort.parse(fromDate);

				} else {
					f_date = FormatData.formatDDMMYYY2.parse(fromDate);

				}
			} catch (Exception e) {
				log.error(e.getMessage());
			}

			if (f_date != null ) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(f_date);

				reportDateFrom =
						calendar.get(Calendar.DATE) + StringPool.SLASH + (calendar.get(Calendar.MONTH) + 1) + StringPool.SLASH
								+ calendar.get(Calendar.YEAR);

				reportDateTo =
						calendar.get(Calendar.DATE) + StringPool.DASH + (calendar.get(Calendar.MONTH) + 1) + StringPool.DASH
								+ calendar.get(Calendar.YEAR);
				if (slashOrDash.contains(StringPool.DASH)){
					return reportDateTo;
				} else if (slashOrDash.contains(StringPool.DASH)){
					return reportDateFrom;
				} else {
					return reportDateFrom;
				}
			} else {
				return StringPool.BLANK;
			}

		} else {
			return StringPool.BLANK;
		}

	}
}
