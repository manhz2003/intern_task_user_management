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
import com.fds.nsw.nghiepvu.model.DmArrivalPurpose;
@Service
@Slf4j
public class DmArrivalPurposeFinderImpl extends


		BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmArrivalPurpose> queryFactory;



	public List<DmArrivalPurpose> findArrivalPurposes(String purposeName,
			String isDelete, String purposeCodeGroup, int start, int end) throws SystemException {

		
		try {
			
			StringBuilder query = new StringBuilder();

			query.append("SELECT * FROM dm_arrival_purpose WHERE 1 = 1");
			if (Validator.isNotNull(purposeName) && !purposeName.isEmpty()) {
				query.append(" AND PurposeName LIKE :purposeName");
			}
			if (Validator.isNotNull("isDelete") && !isDelete.isEmpty()) {
				query.append(" AND IsDelete = :isDelete");
			}
			if (Validator.isNotNull(purposeCodeGroup)
					&& !purposeCodeGroup.isEmpty()) {
				purposeCodeGroup = purposeCodeGroup.replace(",", "', '");
				query.append(" AND PurposeCode IN ('" + purposeCodeGroup + "')");
			}
			query.append(" ORDER BY PurposeCode");

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).firstResult(start).maxResults(end - start).entityClass(DmArrivalPurpose.class).build();
			

			if (Validator.isNotNull(purposeName) && !purposeName.isEmpty()) {
				builder.appendNamedParameterMap("purposeName","%" + purposeName + "%");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.isEmpty()) {
				builder.appendNamedParameterMap("isDelete",Integer.valueOf(isDelete));
			}

			log.info("=========>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			return (List<DmArrivalPurpose>)  queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public long countArrivalPurposes(String purposeName, String isDelete,
			String purposeCodeGroup) throws SystemException {
		long count = 0;
		try {
			
			StringBuilder query = new StringBuilder();

			query.append("SELECT count(*) AS total FROM dm_arrival_purpose WHERE 1 = 1");
			if (Validator.isNotNull(purposeName) && !purposeName.isEmpty()) {
				query.append(" AND PurposeName LIKE ?");
			}
			if (Validator.isNotNull("isDelete") && !isDelete.isEmpty()) {
				query.append(" AND IsDelete= :isDelete");
			}
			if (Validator.isNotNull(purposeCodeGroup)
					&& !purposeCodeGroup.isEmpty()) {
				purposeCodeGroup = purposeCodeGroup.replace(",", "', '");
				query.append(" AND PurposeCode IN ('" + purposeCodeGroup + "')");
			}

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Long.class).build();
			

			if (Validator.isNotNull(purposeName) && !purposeName.isEmpty()) {
				builder.appendNamedParameterMap("purposeName","%" + purposeName + "%");
			
			}
			if (Validator.isNotNull(isDelete) && !isDelete.isEmpty()) {
				builder.appendNamedParameterMap("isDelete",Integer.valueOf(isDelete));
			}

			
			count = (Long) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return count;
	}

}
