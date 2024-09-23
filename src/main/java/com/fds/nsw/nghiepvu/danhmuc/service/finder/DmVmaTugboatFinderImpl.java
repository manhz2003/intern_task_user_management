package com.fds.nsw.nghiepvu.danhmuc.service.finder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.*;

import com.fds.flex.common.utility.string.StringPool;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
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
import com.fds.nsw.nghiepvu.model.DmVmaTugboat;
@Service
@Slf4j
public class DmVmaTugboatFinderImpl extends
 BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmVmaTugboat> queryFactory;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<Object[]> queryFactory2;


	public List<DmVmaTugboat> findVmaTugboats(String maritimeCode,
			String shipName, double power1, double power2,
			String tugboatCompanyCode, String isDelete, String shipCodeGroup,
			int start, int end) throws SystemException {
		
		try {
			
			StringBuilder query = new StringBuilder();
			String sql = "SELECT * FROM dm_vma_tugboat WHERE 1=1 ";

			if (Validator.isNotNull(maritimeCode)
					&& maritimeCode.trim().length() > 0
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				query.append(" AND maritimeCode= :maritimeCode ");
			}
			if (Validator.isNotNull(shipName) && shipName.trim().length() > 0) {
				query.append(" AND shipName like '%" + shipName.trim() + "%' ");
			}
			if (Validator.isNotNull(tugboatCompanyCode)
					&& tugboatCompanyCode.trim().length() > 0) {
				query.append(" AND tugboatCompanyCode= :tugboatCompanyCode ");
			}
			if (Validator.isNotNull(power1) && power1 != 0
					&& Validator.isNotNull(power2) && power1 != 0) {
				query.append(" AND power BETWEEN " + power1 + " AND " + power2);
			}
			if (Validator.isNotNull(isDelete) && !isDelete.equals("")) {
				query.append(" AND isDelete= :isDelete");
			}
			if (Validator.isNotNull(shipCodeGroup) && !shipCodeGroup.equals("")) {
				shipCodeGroup = shipCodeGroup.replace(",", "', '");
				query.append(" AND ShipCode IN ('" + shipCodeGroup + "')");
			}
			query.append(" ORDER BY modifiedDate DESC");

			sql = sql + query.toString();

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).firstResult(start).maxResults(end - start).entityClass(DmVmaTugboat.class).build();
			log.info("=========find DANH SACH ===" + sql);
			

			if (Validator.isNotNull(maritimeCode)
					&& maritimeCode.trim().length() > 0
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				builder.appendNamedParameterMap("maritimeCode",maritimeCode.trim());
			}
			if (Validator.isNotNull(tugboatCompanyCode)
					& !tugboatCompanyCode.equals("")) {
				builder.appendNamedParameterMap("tugboatCompanyCode",tugboatCompanyCode);
			}
			if (Validator.isNotNull(isDelete) && !isDelete.equals("")) {
				int isDeleteTemp = Integer.valueOf(isDelete);
				builder.appendNamedParameterMap("isDeleteTemp",isDeleteTemp);
			}

			return (List<DmVmaTugboat>)  queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public long countVmaTugboats(String maritimeCode, String shipName,
			double power1, double power2, String tugboatCompanyCode,
			String isDelete, String shipCodeGroup) throws SystemException { long count = 0; try {
			
			StringBuilder query = new StringBuilder();
			String sql = "SELECT count(*) AS total FROM dm_vma_tugboat WHERE 1=1 ";

			if (Validator.isNotNull(maritimeCode)
					&& maritimeCode.trim().length() > 0
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				query.append(" AND maritimeCode= :maritimeCode ");
			}
			if (Validator.isNotNull(shipName) && shipName.trim().length() > 0) {
				query.append(" AND shipName like '%" + shipName.trim() + "%' ");
			}
			if (Validator.isNotNull(tugboatCompanyCode)
					&& tugboatCompanyCode.trim().length() > 0) {
				query.append(" AND tugboatCompanyCode= :tugboatCompanyCode ");
			}
			if (Validator.isNotNull(power1) && power1 != 0
					&& Validator.isNotNull(power2) && power1 != 0) {
				query.append(" AND power BETWEEN " + power1 + " AND " + power2);
			}
			if (Validator.isNotNull(isDelete) && !isDelete.equals("")) {
				query.append(" AND isDelete= :isDelete");
			}
			if (Validator.isNotNull(shipCodeGroup) && !shipCodeGroup.equals("")) {
				shipCodeGroup = shipCodeGroup.replace(",", "', '");
				query.append(" AND ShipCode IN ('" + shipCodeGroup + "')");
			}

			sql = sql + query.toString();

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(Long.class).build();
			

			if (Validator.isNotNull(maritimeCode)
					&& maritimeCode.trim().length() > 0
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				builder.appendNamedParameterMap("maritimeCode",maritimeCode.trim());
			}
			if (Validator.isNotNull(tugboatCompanyCode)
					& !tugboatCompanyCode.equals("")) {
				builder.appendNamedParameterMap("tugboatCompanyCode",tugboatCompanyCode);
			}
			if (Validator.isNotNull(isDelete) && !isDelete.equals("")) {
				int isDeleteTemp = Integer.valueOf(isDelete);
				builder.appendNamedParameterMap("isDeleteTemp",isDeleteTemp);
			}

			
			count = (Long) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return count;
	}

	public JSONObject getModelMau24_1T(String maritimeCode, String shipCode,
			String startDate, String endDate) throws SystemException {

		JSONObject result = JSONFactoryUtil.createJSONObject();
		
		try {
			

			String query = "select DISTINCT TugboatCompanyCode, TugboatCompanyName, ShipCode, ShipName, Power, GT, DWT from dm_vma_tugboat WHERE dm_vma_tugboat.MaritimeCode = '"
					+ maritimeCode
					+ "' and dm_vma_tugboat.MarkedAsDelete = 0 and ShipCode = '"
					+ shipCode + "';";

			log.info("=========select bao cao mau_24_1t>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Object.class).build();
Iterator<Object[]> itr = queryFactory2.getResultList(builder).iterator();

			if (itr.hasNext()) {
				while (itr.hasNext()) {
					Object[] objects = itr.next();

					String tugboatCompanyCode = String
							.valueOf(objects[0] != null ? objects[0]
									: StringPool.BLANK);
					String tugboatCompanyName = String
							.valueOf(objects[1] != null ? objects[1]
									: StringPool.BLANK);
					shipCode = String.valueOf(objects[2] != null ? objects[2]
							: StringPool.BLANK);
					String shipName = String
							.valueOf(objects[3] != null ? objects[3]
									: StringPool.BLANK);
					double power = Double.valueOf(String
							.valueOf(objects[4] != null ? objects[4] : -1));
					double gt = Double.valueOf(String
							.valueOf(objects[5] != null ? objects[5] : -1));
					double dwt = Double.valueOf(String
							.valueOf(objects[6] != null ? objects[6] : -1));

					result.put("tugboatCompanyCode", tugboatCompanyCode);
					result.put("tugboatCompanyName", tugboatCompanyName);
					result.put("shipCode", shipCode);
					result.put("shipName", shipName);
					result.put("power", power >= 0 ? power : null);
					result.put("gt", gt >= 0 ? gt : null);
					result.put("dwt", dwt >= 0 ? dwt : null);
				}
			}

			return result;
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public JSONArray getModelMau36T(String maritimeCode,
			String pilotCompanyCode, String startDate, String endDate)
			throws SystemException {

		JSONArray array = JSONFactoryUtil.createJSONArray();
		
		try {
			

			String query = "Select DISTINCT MaritimeCode, TugboatCompanyCode, TugboatCompanyName, ShipCode, ShipName, Power, GT, DWT, Remarks from dm_vma_tugboat WHERE dm_vma_tugboat.MaritimeCode = '"
					+ maritimeCode
					+ "' and dm_vma_tugboat.MarkedAsDelete = 0 and ShipCode in (Select DISTINCT ShipCode from vma_schedule_tugboat_list WHERE PortofAuthority = '"
					+ maritimeCode
					+ "' and ModifiedDate >= '"
					+ startDate
					+ "' and ModifiedDate <= '"
					+ endDate
					+ "') ORDER BY CONVERT(dm_vma_tugboat.TugboatCompanyName  USING utf8) COLLATE utf8_polish_ci asc, CONVERT(dm_vma_tugboat.ShipName  USING utf8) COLLATE utf8_polish_ci asc";

			log.info("=========select bao cao mau_36t>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Object.class).build();
Iterator<Object[]> itr = queryFactory2.getResultList(builder).iterator();

			if (itr.hasNext()) {
				while (itr.hasNext()) {
					Object[] objects = itr.next();
					JSONObject obj = JSONFactoryUtil.createJSONObject();

					maritimeCode = String
							.valueOf(objects[0] != null ? objects[0]
									: StringPool.BLANK);
					String tugboatCompanyCode = String
							.valueOf(objects[1] != null ? objects[1]
									: StringPool.BLANK);
					String tugboatCompanyName = String
							.valueOf(objects[2] != null ? objects[2]
									: StringPool.BLANK);
					String shipCode = String
							.valueOf(objects[3] != null ? objects[3]
									: StringPool.BLANK);
					String shipName = String
							.valueOf(objects[4] != null ? objects[4]
									: StringPool.BLANK);
					double power = Double.valueOf(String
							.valueOf(objects[5] != null ? objects[5] : -1));
					double gt = Double.valueOf(String
							.valueOf(objects[6] != null ? objects[6] : -1));
					double dwt = Double.valueOf(String
							.valueOf(objects[7] != null ? objects[7] : -1));
					String remarks = String
							.valueOf(objects[8] != null ? objects[8]
									: StringPool.BLANK);

					obj.put("maritimeCode", maritimeCode);
					obj.put("tugboatCompanyCode", tugboatCompanyCode);
					obj.put("tugboatCompanyName", tugboatCompanyName);
					obj.put("shipCode", shipCode);
					obj.put("shipName", shipName);
					obj.put("power", power >= 0 ? power : null);
					obj.put("gt", gt >= 0 ? gt : null);
					obj.put("dwt", dwt >= 0 ? dwt : null);
					obj.put("remarks", remarks);

					array.put(obj);
				}
			}

			return array;
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	
}
