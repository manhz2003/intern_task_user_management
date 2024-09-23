package com.fds.nsw.nghiepvu.danhmuc.service.finder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import com.fds.nsw.liferay.core.JSONFactoryUtil;


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
import com.fds.nsw.nghiepvu.model.DmSyncCategory;
import org.json.JSONArray;
import org.json.JSONObject;
@Service
@Slf4j
public class DmSyncCategoryFinderImpl extends

		BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmSyncCategory> queryFactory;

	

	public JSONArray findDmSyncCategorys(String categoryIdGroup, int start,
			int end) throws SystemException {
		if (Validator.isNotNull(categoryIdGroup) && !categoryIdGroup.isEmpty()) {
			categoryIdGroup = categoryIdGroup.replace(",", "', '");
		}
		

		JSONArray result = JSONFactoryUtil.createJSONArray();

		try {
			

			StringBuilder query = new StringBuilder();

			query.append("SELECT * FROM dm_sync_category");

			if (Validator.isNotNull(categoryIdGroup)
					&& !categoryIdGroup.isEmpty()) {
				query.append(" WHERE CategoryID IN ('" + categoryIdGroup + "')");
			}

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).firstResult(start).maxResults(end - start).entityClass(DmSyncCategory.class).build();

			log.info("=========select dm_sync_category>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			List<DmSyncCategory> SyncCategorys = (List<DmSyncCategory>) queryFactory.getResultList(builder);

			for (DmSyncCategory dmSyncCategory : SyncCategorys) {
				JSONObject object = JSONFactoryUtil.createJSONObject();

				object.put("categoryId", dmSyncCategory.getCategoryID());
				object.put("categoryDescription",
						dmSyncCategory.getCategoryDescription());

				result.put(object);
			}

			return result;
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public long countDmSyncCategorys(String categoryIdGroup)
			throws SystemException {
		long count = 0;
		if (Validator.isNotNull(categoryIdGroup) && !categoryIdGroup.isEmpty()) {
			categoryIdGroup = categoryIdGroup.replace(",", "', '");
		}

		
		try {
			

			StringBuilder query = new StringBuilder();

			query.append("SELECT count(*) AS total FROM dm_sync_category");

			if (Validator.isNotNull(categoryIdGroup)
					&& !categoryIdGroup.isEmpty()) {
				query.append(" WHERE CategoryID IN ('" + categoryIdGroup + "')");
			}

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Long.class).build();

			
			count = (Long) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return count;
	}
}
