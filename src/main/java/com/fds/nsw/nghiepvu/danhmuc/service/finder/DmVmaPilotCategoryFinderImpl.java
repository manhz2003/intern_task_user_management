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
import com.fds.nsw.nghiepvu.model.DmVmaPilotCategory;
@Service
@Slf4j
public class DmVmaPilotCategoryFinderImpl extends

		BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmVmaPilotCategory> queryFactory;

	public List<DmVmaPilotCategory> findPilotCategoryies(
			String pilotCategoryName, String isDelete,
			String pilotCategoryCodeGroup, int start, int end)
			throws SystemException {
		
		try {
			
			StringBuilder query = new StringBuilder();

			query.append("SELECT * FROM dm_vma_pilot_category WHERE 1 = 1");

			if (Validator.isNotNull(pilotCategoryName)
					&& !pilotCategoryName.isEmpty()) {
				query.append(" AND PilotCategoryName LIKE ?");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.isEmpty()) {
				query.append(" AND IsDelete= :isDelete");
			}
			if (Validator.isNotNull(pilotCategoryCodeGroup)
					&& !pilotCategoryCodeGroup.isEmpty()) {
				pilotCategoryCodeGroup = pilotCategoryCodeGroup.replace(",",
						"', '");
				query.append(" AND PilotCategoryCode IN ('"
						+ pilotCategoryCodeGroup + "')");
			}
			query.append(" ORDER BY ModifiedDate DESC");

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).firstResult(start).maxResults(end - start).entityClass(DmVmaPilotCategory.class).build();
			

			if (Validator.isNotNull(pilotCategoryName)
					&& !pilotCategoryName.isEmpty()) {
				builder.appendNamedParameterMap("pilotCategoryName","%" + pilotCategoryName + "%");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.isEmpty()) {
				builder.appendNamedParameterMap("isDelete",Integer.valueOf(isDelete));
			}

			return (List<DmVmaPilotCategory>)  queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public long countPilotCategoryies(String pilotCategoryName,
			String isDelete, String pilotCategoryCodeGroup) throws SystemException { long count = 0; try {
			
			StringBuilder query = new StringBuilder();

			query.append("SELECT count(*) AS total FROM dm_vma_pilot_category WHERE 1 = 1");

			if (Validator.isNotNull(pilotCategoryName)
					&& !pilotCategoryName.isEmpty()) {
				query.append(" AND PilotCategoryName LIKE ?");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.isEmpty()) {
				query.append(" AND IsDelete= :isDelete");
			}
			if (Validator.isNotNull(pilotCategoryCodeGroup)
					&& !pilotCategoryCodeGroup.isEmpty()) {
				pilotCategoryCodeGroup = pilotCategoryCodeGroup.replace(",",
						"', '");
				query.append(" AND PilotCategoryCode IN ('"
						+ pilotCategoryCodeGroup + "')");
			}

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Long.class).build();
			

			if (Validator.isNotNull(pilotCategoryName)
					&& !pilotCategoryName.isEmpty()) {
				builder.appendNamedParameterMap("pilotCategoryName","%" + pilotCategoryName + "%");
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
