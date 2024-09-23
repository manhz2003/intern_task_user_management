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
import com.fds.nsw.nghiepvu.model.VmaAdministrativeViolation;
@Service
@Slf4j
public class VmaAdministrativeViolationFinderImpl extends

		BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaAdministrativeViolation> queryFactory;

	

	public List<VmaAdministrativeViolation> findVmaAdministrativeViolations(
			String portofAuthority, String violationDate,
			String notViolationDate, int start, int end) throws SystemException {
		
		try {
			
			StringBuilder query = new StringBuilder();

			query.append("SELECT * FROM vma_administrative_violation WHERE 1 = 1 ");
			if (Validator.isNotNull(portofAuthority)
					&& !portofAuthority.isEmpty()) {
				query.append(" AND PortofAuthority= :portofAuthority");
			}
			if (Validator.isNotNull(violationDate) && !violationDate.isEmpty()) {
				query.append(" AND YEAR(ViolationDate) = ? ");
			} else if (Validator.isNotNull(notViolationDate)
					&& !notViolationDate.isEmpty()) {
				query.append(" AND YEAR(ViolationDate) NOT IN ("
						+ notViolationDate + ") ");
			}
			query.append(" ORDER BY ViolationDate DESC");

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).firstResult(start).maxResults(end - start).entityClass(VmaAdministrativeViolation.class).build();
			

			
			if (Validator.isNotNull(portofAuthority)
					&& !portofAuthority.isEmpty()) {
				builder.appendNamedParameterMap("portofAuthority",portofAuthority);
			}
			if (Validator.isNotNull(violationDate) && !violationDate.isEmpty()) {
				builder.appendNamedParameterMap("violationDate",violationDate);
			}

			log.info(query.toString());

			return (List<VmaAdministrativeViolation>)  queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public long countVmaAdministrativeViolation(String portofAuthority,
			String violationDate, String notViolationDate) throws SystemException { long count = 0; try {
			
			StringBuilder query = new StringBuilder();

			query.append("SELECT count(*) AS total FROM vma_administrative_violation WHERE 1 = 1 ");
			if (Validator.isNotNull(portofAuthority)
					&& !portofAuthority.isEmpty()) {
				query.append(" AND PortofAuthority= :portofAuthority");
			}
			if (Validator.isNotNull(violationDate) && !violationDate.isEmpty()) {
				query.append(" AND YEAR(ViolationDate) = ? ");
			} else if (Validator.isNotNull(notViolationDate)
					&& !notViolationDate.isEmpty()) {
				query.append(" AND YEAR(ViolationDate) NOT IN ("
						+ notViolationDate + ") ");
			}

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Long.class).build();
			

			
			if (Validator.isNotNull(portofAuthority)
					&& !portofAuthority.isEmpty()) {
				builder.appendNamedParameterMap("portofAuthority",portofAuthority);
			}
			if (Validator.isNotNull(violationDate) && !violationDate.isEmpty()) {
				builder.appendNamedParameterMap("violationDate",violationDate);
			}

			
			count = (Long) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return count;
	}
}
