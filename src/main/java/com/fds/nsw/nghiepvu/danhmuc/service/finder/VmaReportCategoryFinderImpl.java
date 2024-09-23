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
import com.fds.nsw.nghiepvu.model.VmaReportCategory;
@Service
@Slf4j
public class VmaReportCategoryFinderImpl extends

		BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaReportCategory> queryFactory;

	

	public List<VmaReportCategory> getReportGroups(String rptGroup,
			String rptLevel, int start, int end) throws SystemException {
		
		try {
			
			StringBuilder query = new StringBuilder();

			query.append("SELECT * FROM vma_report_category WHERE 1 = 1");
			if (Validator.isNotNull(rptGroup) && !rptGroup.isEmpty()) {
				query.append(" AND RptGroup= :rptGroup");
			}
			if (Validator.isNotNull(rptLevel) && !rptLevel.isEmpty()) {
				query.append(" AND RptLevel= :rptLevel");
			}
			query.append(" ORDER BY SequenceNo");

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).firstResult(start).maxResults(end - start).entityClass(VmaReportCategory.class).build();
			

			if (Validator.isNotNull(rptGroup) && !rptGroup.isEmpty()) {
				builder.appendNamedParameterMap("rptGroup",rptGroup);
			}
			if (Validator.isNotNull(rptLevel) && !rptLevel.isEmpty()) {
				builder.appendNamedParameterMap("rptLevel",rptLevel);
			}

			return (List<VmaReportCategory>)  queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public long coungReportGroups(String rptGroup, String rptLevel)
			throws SystemException {
		long count = 0;
		try {
			
			StringBuilder query = new StringBuilder();

			query.append("SELECT count(*) AS total FROM vma_report_category WHERE 1 = 1");
			if (Validator.isNotNull(rptGroup) && !rptGroup.isEmpty()) {
				query.append(" AND RptGroup= :rptGroup");
			}
			if (Validator.isNotNull(rptLevel) && !rptLevel.isEmpty()) {
				query.append(" AND RptLevel= :rptLevel");
			}
			query.append(" ORDER BY SequenceNo");

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Long.class).build();
			

			if (Validator.isNotNull(rptGroup) && !rptGroup.isEmpty()) {
				builder.appendNamedParameterMap("rptGroup",rptGroup);
			}
			if (Validator.isNotNull(rptLevel) && !rptLevel.isEmpty()) {
				builder.appendNamedParameterMap("rptLevel",rptLevel);
			}

			
			count = (Long) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return count;
	}
}
