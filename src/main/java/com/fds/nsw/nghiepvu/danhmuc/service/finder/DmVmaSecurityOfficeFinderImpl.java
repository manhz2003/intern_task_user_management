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
import com.fds.nsw.nghiepvu.model.DmVmaSecurityOffice;
@Service
@Slf4j
public class DmVmaSecurityOfficeFinderImpl extends

		BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmVmaSecurityOffice> queryFactory;
	public List<DmVmaSecurityOffice> findVmaSecurityOffices(
			String maritimeCode, String companyName, String companyAddress,
			String contactEmail, String telNo, String isDelete,
			String securityOfficeCodeGroup, int start, int end)
			throws SystemException {

		
		try {
			
			StringBuilder query = new StringBuilder();
			String sql = "SELECT * FROM dm_vma_security_office WHERE 1=1 ";

			if (Validator.isNotNull(maritimeCode) && !maritimeCode.isEmpty()
					&& !maritimeCode.equals("---")) {
				query.append(" AND maritimeCode= :maritimeCode ");
			}
			if (Validator.isNotNull(companyName) && !companyName.equals("")) {
				query.append(" AND companyName like '%" + companyName + "%'");
			}
			if (Validator.isNotNull(companyAddress)
					&& !companyAddress.equals("")) {
				query.append(" AND companyAddress like '%" + companyAddress
						+ "%'");
			}
			if (Validator.isNotNull(contactEmail) && !contactEmail.equals("")) {
				query.append(" AND contactEmail like '%" + contactEmail + "%'");
			}
			if (Validator.isNotNull(telNo) && !telNo.equals("")) {
				query.append(" AND telNo like '%" + telNo + "%'");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.equals("")) {
				query.append(" AND isDelete= :isDelete");
			}
			if (Validator.isNotNull(securityOfficeCodeGroup)
					&& !securityOfficeCodeGroup.equals("")) {
				securityOfficeCodeGroup = securityOfficeCodeGroup.replace(",",
						"', '");
				query.append(" AND SecurityOfficeCode IN ('"
						+ securityOfficeCodeGroup + "')");
			}
			query.append(" ORDER BY modifiedDate DESC");

			sql = sql + query.toString();

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).firstResult(start).maxResults(end - start).entityClass(DmVmaSecurityOffice.class).build();
			log.info("=========find DANH SACH ===" + sql);
			

			if (Validator.isNotNull(maritimeCode) && !maritimeCode.isEmpty()
					&& !maritimeCode.equals("---")) {
				builder.appendNamedParameterMap("maritimeCode",maritimeCode);
			}
			if (Validator.isNotNull(isDelete) && !isDelete.equals("")) {
				int isDeleteTemp = Integer.valueOf(isDelete);
				builder.appendNamedParameterMap("isDeleteTemp",isDeleteTemp);
			}
			return (List<DmVmaSecurityOffice>)  queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}
	
	public long countVmaSecurityOffices(
			String maritimeCode, String companyName, String companyAddress,
			String contactEmail, String telNo, String isDelete,
			String securityOfficeCodeGroup) throws SystemException { long count = 0; try {
			
			StringBuilder query = new StringBuilder();
			String sql = "SELECT count(*) AS total FROM dm_vma_security_office WHERE 1=1 ";

			if (Validator.isNotNull(maritimeCode) && !maritimeCode.isEmpty()
					&& !maritimeCode.equals("---")) {
				query.append(" AND maritimeCode= :maritimeCode ");
			}
			if (Validator.isNotNull(companyName) && !companyName.equals("")) {
				query.append(" AND companyName like '%" + companyName + "%'");
			}
			if (Validator.isNotNull(companyAddress)
					&& !companyAddress.equals("")) {
				query.append(" AND companyAddress like '%" + companyAddress
						+ "%'");
			}
			if (Validator.isNotNull(contactEmail) && !contactEmail.equals("")) {
				query.append(" AND contactEmail like '%" + contactEmail + "%'");
			}
			if (Validator.isNotNull(telNo) && !telNo.equals("")) {
				query.append(" AND telNo like '%" + telNo + "%'");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.equals("")) {
				query.append(" AND isDelete= :isDelete");
			}
			if (Validator.isNotNull(securityOfficeCodeGroup)
					&& !securityOfficeCodeGroup.equals("")) {
				securityOfficeCodeGroup = securityOfficeCodeGroup.replace(",",
						"', '");
				query.append(" AND SecurityOfficeCode IN ('"
						+ securityOfficeCodeGroup + "')");
			}
			sql = sql + query.toString();

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(Long.class).build();
			

			if (Validator.isNotNull(maritimeCode) && !maritimeCode.isEmpty()
					&& !maritimeCode.equals("---")) {
				builder.appendNamedParameterMap("maritimeCode",maritimeCode);
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
