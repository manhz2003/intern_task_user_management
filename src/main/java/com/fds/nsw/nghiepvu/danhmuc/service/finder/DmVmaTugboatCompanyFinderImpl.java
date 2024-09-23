package com.fds.nsw.nghiepvu.danhmuc.service.finder;

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
import com.fds.nsw.nghiepvu.model.DmVmaTugboatCompany;
@Service
@Slf4j
public class DmVmaTugboatCompanyFinderImpl extends

		BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmVmaTugboatCompany> queryFactory;

	public List<DmVmaTugboatCompany> findVmaTugboatCompanies(
			String maritimeCode, String tugboatCompanyName,
			String companyAddress, String contactEmail, String telNo,
			String isDelete, String tugboatCompanyCodeGroup, int start, int end)
			throws SystemException {
		
		try {
			
			StringBuilder query = new StringBuilder();
			String sql = "SELECT * FROM dm_vma_tugboat_company WHERE 1=1 ";

			if (Validator.isNotNull(maritimeCode)
					&& maritimeCode.trim().length() > 0
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				query.append(" AND maritimeCode= :maritimeCode ");
			}
			if (Validator.isNotNull(tugboatCompanyName)
					&& tugboatCompanyName.trim().length() > 0) {
				query.append(" AND tugboatCompanyName like '%"
						+ tugboatCompanyName.trim() + "%' ");
			}
			if (Validator.isNotNull(companyAddress)
					&& companyAddress.trim().length() > 0) {
				query.append(" AND companyAddress like '%"
						+ companyAddress.trim() + "%' ");
			}
			if (Validator.isNotNull(contactEmail)
					&& contactEmail.trim().length() > 0) {
				query.append(" AND contactEmail like '%" + contactEmail.trim()
						+ "%' ");
			}
			if (Validator.isNotNull(telNo) && telNo.trim().length() > 0) {
				query.append(" AND telNo like '%" + telNo.trim() + "%' ");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.equals("")) {
				query.append("AND isDelete= :isDelete");
			}
			if (Validator.isNotNull(tugboatCompanyCodeGroup)
					&& !tugboatCompanyCodeGroup.equals("")) {
				tugboatCompanyCodeGroup = tugboatCompanyCodeGroup.replace(",",
						"', '");
				query.append("AND TugboatCompanyCode IN ('"
						+ tugboatCompanyCodeGroup + "')");
			}
			query.append(" ORDER BY modifiedDate DESC");

			sql = sql + query.toString();

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).firstResult(start).maxResults(end - start).entityClass(DmVmaTugboatCompany.class).build();
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

			return (List<DmVmaTugboatCompany>)  queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public long countVmaTugboatCompanies(String maritimeCode,
			String tugboatCompanyName, String companyAddress,
			String contactEmail, String telNo, String isDelete,
			String tugboatCompanyCodeGroup) throws SystemException { long count = 0; try {
			
			StringBuilder query = new StringBuilder();
			String sql = "SELECT count(*) AS total FROM dm_vma_tugboat_company WHERE 1=1 ";

			if (Validator.isNotNull(maritimeCode)
					&& maritimeCode.trim().length() > 0
					&& (!maritimeCode.equalsIgnoreCase("---"))) {
				query.append(" AND maritimeCode= :maritimeCode ");
			}
			if (Validator.isNotNull(tugboatCompanyName)
					&& tugboatCompanyName.trim().length() > 0) {
				query.append(" AND tugboatCompanyName like '%"
						+ tugboatCompanyName.trim() + "%' ");
			}
			if (Validator.isNotNull(companyAddress)
					&& companyAddress.trim().length() > 0) {
				query.append(" AND companyAddress like '%"
						+ companyAddress.trim() + "%' ");
			}
			if (Validator.isNotNull(contactEmail)
					&& contactEmail.trim().length() > 0) {
				query.append(" AND contactEmail like '%" + contactEmail.trim()
						+ "%' ");
			}
			if (Validator.isNotNull(telNo) && telNo.trim().length() > 0) {
				query.append(" AND telNo like '%" + telNo.trim() + "%' ");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.equals("")) {
				query.append("AND isDelete= :isDelete");
			}
			if (Validator.isNotNull(tugboatCompanyCodeGroup)
					&& !tugboatCompanyCodeGroup.equals("")) {
				tugboatCompanyCodeGroup = tugboatCompanyCodeGroup.replace(",",
						"', '");
				query.append("AND TugboatCompanyCode IN ('"
						+ tugboatCompanyCodeGroup + "')");
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

			
			count = (Long) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return count;
	}

	
}
