package com.fds.nsw.nghiepvu.danhmuc.service.finder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONArray;
import org.json.JSONObject;
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
import com.fds.nsw.nghiepvu.model.IssueShiftingOrder;
import com.fds.nsw.nghiepvu.util.ConvertUtil;
import com.fds.nsw.nghiepvu.util.FormatData;

import lombok.extern.slf4j.Slf4j;
import com.fds.nsw.nghiepvu.model.DmVmaPilot;
@Service
@Slf4j
public class DmVmaPilotFinderImpl extends
 BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmVmaPilot> queryFactory;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<Object[]> queryFactory2;

	public List<DmVmaPilot> findVmaPilots(String maritimeCode,
			String pilotCompanyCode, String pilotCategoryCode,
			String pilotName, String isDelete, String pilotCodeGroup,
			int start, int end) throws SystemException {
		
		try {
			
			StringBuilder query = new StringBuilder();
			String sql = "SELECT * FROM dm_vma_pilot WHERE 1=1 ";

			if (Validator.isNotNull(maritimeCode)
					&& maritimeCode.trim().length() > 0
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				query.append(" AND maritimeCode= :maritimeCode ");
			}
			if (Validator.isNotNull(pilotCompanyCode)
					&& pilotCompanyCode.trim().length() > 0) {
				query.append(" AND pilotCompanyCode= :pilotCompanyCode ");
			}
			if (Validator.isNotNull(pilotCategoryCode)
					&& !pilotCategoryCode.equals("")) {
				query.append(" AND pilotCategoryCode= :pilotCategoryCode ");
			}
			if (Validator.isNotNull(pilotName) && !pilotName.equals("")) {
				query.append(" AND pilotName like '%" + pilotName + "%'");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.equals("")) {
				query.append("AND isDelete= :isDelete");
			}
			if (Validator.isNotNull(pilotCodeGroup)
					&& !pilotCodeGroup.equals("")) {
				pilotCodeGroup = pilotCodeGroup.replace(",", "', '");
				query.append("AND PilotCode IN ('" + pilotCodeGroup + "')");
			}
			query.append(" ORDER BY modifiedDate DESC");

			sql = sql + query.toString();

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).firstResult(start).maxResults(end - start).entityClass(DmVmaPilot.class).build();
			log.info("=========find DANH SACH ===" + sql);
			

			if (Validator.isNotNull(maritimeCode)
					&& maritimeCode.trim().length() > 0
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				builder.appendNamedParameterMap("maritimeCode",maritimeCode.trim());
			}
			if (Validator.isNotNull(isDelete) && !isDelete.equals("")) {
				int isDeleteTemp = Integer.valueOf(isDelete);
				builder.appendNamedParameterMap("isDeleteTemp",isDeleteTemp);
			}
			if (Validator.isNotNull(pilotCompanyCode)
					&& !pilotCompanyCode.equals("")) {
				builder.appendNamedParameterMap("pilotCompanyCode",pilotCompanyCode);
			}
			if (Validator.isNotNull(pilotCategoryCode)
					&& !pilotCategoryCode.equals("")) {
				builder.appendNamedParameterMap("pilotCategoryCode",pilotCategoryCode);
			}
			return (List<DmVmaPilot>)  queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public long countVmaPilots(String maritimeCode, String pilotCompanyCode,
			String pilotCategoryCode, String pilotName, String isDelete,
			String pilotCodeGroup) throws SystemException { long count = 0; try {
			
			StringBuilder query = new StringBuilder();
			String sql = "SELECT count(*) AS total FROM dm_vma_pilot WHERE 1=1 ";

			if (Validator.isNotNull(maritimeCode)
					&& maritimeCode.trim().length() > 0
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				query.append(" AND maritimeCode= :maritimeCode ");
			}
			if (Validator.isNotNull(pilotCompanyCode)
					&& pilotCompanyCode.trim().length() > 0) {
				query.append(" AND pilotCompanyCode= :pilotCompanyCode ");
			}
			if (Validator.isNotNull(pilotCategoryCode)
					&& !pilotCategoryCode.equals("")) {
				query.append(" AND pilotCategoryCode= :pilotCategoryCode ");
			}
			if (Validator.isNotNull(pilotName) && !pilotName.equals("")) {
				query.append(" AND pilotName like '%" + pilotName + "%'");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.equals("")) {
				query.append("AND isDelete= :isDelete");
			}
			if (Validator.isNotNull(pilotCodeGroup)
					&& !pilotCodeGroup.equals("")) {
				pilotCodeGroup = pilotCodeGroup.replace(",", "', '");
				query.append("AND PilotCode IN ('" + pilotCodeGroup + "')");
			}
			sql = sql + query.toString();

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(Long.class).build();
			

			if (Validator.isNotNull(maritimeCode)
					&& maritimeCode.trim().length() > 0
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				builder.appendNamedParameterMap("maritimeCode",maritimeCode.trim());
			}
			if (Validator.isNotNull(isDelete) && !isDelete.equals("")) {
				int isDeleteTemp = Integer.valueOf(isDelete);
				builder.appendNamedParameterMap("isDeleteTemp",isDeleteTemp);
			}
			if (Validator.isNotNull(pilotCompanyCode)
					&& !pilotCompanyCode.equals("")) {
				builder.appendNamedParameterMap("pilotCompanyCode",pilotCompanyCode);
			}
			if (Validator.isNotNull(pilotCategoryCode)
					&& !pilotCategoryCode.equals("")) {
				builder.appendNamedParameterMap("pilotCategoryCode",pilotCategoryCode);
			}
			
			count = (Long) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return count;
	}

	public JSONObject getModelMau26_1T(String maritimeCode, String pilotCode)
			throws SystemException {

		JSONObject result = JSONFactoryUtil.createJSONObject();
		
		try {
			

			String query = "Select DISTINCT PilotCompanyCode, PilotCompanyName, PilotCode, PilotName, PilotBOD, dm_vma_pilot.PilotCategoryCode, dm_vma_pilot_category.PilotCategoryName, PilotCertificateNo, PilotCertificateDate, dm_vma_pilot.Remarks as PilotRemarks from dm_vma_Pilot LEFT JOIN dm_vma_pilot_category on dm_vma_pilot_category.PilotCategoryCode = dm_vma_pilot.PilotCategoryCode WHERE dm_vma_pilot.MaritimeCode = '"
					+ maritimeCode
					+ "'and dm_vma_pilot.MarkedAsDelete = 0 and PilotCode = '"
					+ pilotCode + "'";

			log.info("=========select bao cao mau_26_1t>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Object.class).build();
Iterator<Object[]> itr = queryFactory2.getResultList(builder).iterator();

			if (itr.hasNext()) {
				while (itr.hasNext()) {
					Object[] objects = itr.next();

					String pilotCompanyCode = String
							.valueOf(objects[0] != null ? objects[0]
									: StringPool.BLANK);
					String pilotCompanyName = String
							.valueOf(objects[1] != null ? objects[1]
									: StringPool.BLANK);
					pilotCode = String.valueOf(objects[2] != null ? objects[2]
							: StringPool.BLANK);
					String pilotName = String
							.valueOf(objects[3] != null ? objects[3]
									: StringPool.BLANK);
					String pilotBOD = String
							.valueOf(objects[4] != null ? objects[4]
									: StringPool.BLANK);
					String pilotCategoryCode = String
							.valueOf(objects[5] != null ? objects[5]
									: StringPool.BLANK);
					String pilotCategoryName = String
							.valueOf(objects[6] != null ? objects[6]
									: StringPool.BLANK);
					String pilotCertificateNo = String
							.valueOf(objects[7] != null ? objects[7]
									: StringPool.BLANK);
					String pilotCertificateDate = String
							.valueOf(objects[8] != null ? objects[8]
									: StringPool.BLANK);
					String pilotRemarks = String
							.valueOf(objects[9] != null ? objects[9]
									: StringPool.BLANK);

					result.put("pilotCompanyCode", pilotCompanyCode);
					result.put("pilotCompanyName", pilotCompanyName);
					result.put("pilotCode", pilotCode);
					result.put("pilotBOD", pilotBOD);
					result.put("pilotName", pilotName);
					result.put("pilotCategoryCode", pilotCategoryCode);
					result.put("pilotCategoryName", pilotCategoryName);
					result.put("pilotCertificateNo", pilotCertificateNo);
					result.put("pilotCertificateDate", pilotCertificateDate);
					result.put("pilotRemarks", pilotRemarks);
				}
			}

			return result;
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

}
