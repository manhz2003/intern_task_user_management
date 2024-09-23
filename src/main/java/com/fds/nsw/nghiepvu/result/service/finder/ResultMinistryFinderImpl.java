package com.fds.nsw.nghiepvu.result.service.finder;

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
import com.fds.nsw.nghiepvu.model.ResultMinistry;
@Service
@Slf4j
public class ResultMinistryFinderImpl extends
 BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<ResultMinistry> queryFactory;
	
	
	
	public List<ResultMinistry> findByDocumentNameAnddocumentYearMinistryCodeBusinessTypeCode(int documentName, int documentYear,
			java.lang.String ministryCode, int businessTypeCode) {
		
		log.debug("=========THU TUC SEARCH --shipName========" + documentName);
		
		
		try {
			
			StringBuilder query = new StringBuilder();
			
			String sql = "SELECT * FROM RESULT_MINISTRY WHERE 1=1 ";
			
			if (Validator.isNotNull(ministryCode) && ministryCode.trim().length() > 0) {
				query.append(" AND MinistryCode= :ministryCode");
			}
			
			if (documentName > 0) {
				query.append(" AND  DocumentName=" + documentName);
			}
			
			if (documentYear > 0) {
				query.append(" AND  DocumentYear=" + documentYear);
			}
			
			// Ten tau
			if (businessTypeCode > 0) {
				query.append(" AND BusinessTypeCode = " + businessTypeCode);
			}
			
			query.append(" ORDER BY id DESC");
			
			sql = sql + query.toString();
			
			log.debug("=========THU TUC SEARCH --findTempDocumentByThuTuc========" + sql);
			
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(ResultMinistry.class).build();
			
			
			
			if (Validator.isNotNull(ministryCode) && ministryCode.trim().length() > 0) {
				builder.appendNamedParameterMap("ministryCode",ministryCode);
			}
			
			return (List<ResultMinistry>) queryFactory.getResultList(builder);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return null;
	}
	
	public List<ResultMinistry> findDocumentNameAndDocumentYeahAndMinistryAndBusinessTypeCode(long documentName, int documentYear,
			String ministryCode, String lstBusinessTypeCode) throws SystemException {
		
		try {
			
			
			StringBuilder query = new StringBuilder();
			
			query.append("SELECT * FROM result_ministry ");
			
			if (Validator.isNotNull(documentName) && documentName > 0) {
				query.append(" WHERE DocumentName= :documentName ");
			}
			if (Validator.isNotNull(documentYear) && documentYear > 0) {
				query.append(" AND DocumentYear= :documentYear ");
			}
			if (Validator.isNotNull(ministryCode) && ministryCode.trim().length() > 0) {
				query.append(" and ministryCode = '" + ministryCode + "'");
			}
			if (Validator.isNotNull(lstBusinessTypeCode) && lstBusinessTypeCode.trim().length() > 0) {
				query.append(" and businessTypeCode in (" + lstBusinessTypeCode + ")");
			}
			query.append(" order by id desc");
			
			log.debug("===findDocumentNameAndDocumentYeahAndMinistryAndBusinessTypeCode===" + query.toString());
			
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(ResultMinistry.class).build();
			
			builder.appendNamedParameterMap("documentName",documentName);
			builder.appendNamedParameterMap("documentYear",documentYear);
			
			return (List<ResultMinistry>) queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}
	
	public List<ResultMinistry> findDistinctMinistryCode(long documentName, int documentYear) throws SystemException {
		
		
		List<ResultMinistry> allResultMinistry = null;
		try {
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT DISTINCT MinistryCode FROM result_ministry");
			if (Validator.isNotNull(documentName) && documentName > 0) {
				query.append(" WHERE DocumentName= :documentName ");
			}
			if (Validator.isNotNull(documentYear) && documentYear > 0) {
				query.append(" AND DocumentYear= :documentYear ");
			}
			query.append(" order by id asc");
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(ResultMinistry.class).build();
			
			
			builder.appendNamedParameterMap("documentName",documentName);
			builder.appendNamedParameterMap("documentYear",documentYear);
			
			// execute the query and return a list from the db
			
			allResultMinistry = (List<ResultMinistry>) queryFactory.getResultList(builder);
			
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return allResultMinistry;
	}
	
}
