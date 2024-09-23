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
import com.fds.nsw.nghiepvu.model.DmRankRating;
@Service
@Slf4j
public class DmRankRatingFinderImpl extends
 BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmRankRating> queryFactory;
	public List<DmRankRating> findRankRatings(String rankNameVN,
			String rankName, String isDelete, String rankCodeGroup, int start,
			int end) throws SystemException {
		
		try {
			
			StringBuilder query = new StringBuilder();

			query.append("SELECT * FROM dm_rank_rating WHERE 1 = 1 ");

			if (Validator.isNotNull(rankNameVN) && !rankNameVN.isEmpty()) {
				query.append(" AND RankNameVN LIKE ?");
			}
			if (Validator.isNotNull(rankName) && !rankName.isEmpty()) {
				query.append(" AND RankName LIKE ?");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.isEmpty()) {
				query.append(" AND IsDelete= :isDelete");
			}
			if (Validator.isNotNull(rankCodeGroup) && !rankCodeGroup.isEmpty()) {
				rankCodeGroup = rankCodeGroup.replace(",", "', '");
				query.append(" AND RankCode IN ('" + rankCodeGroup + "')");
			}

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).firstResult(start).maxResults(end - start).entityClass(DmRankRating.class).build();
			

			if (Validator.isNotNull(rankNameVN) && !rankNameVN.isEmpty()) {
				builder.appendNamedParameterMap("rankNameVN","%" + rankNameVN + "%");
			}
			if (Validator.isNotNull(rankName) && !rankName.isEmpty()) {
				builder.appendNamedParameterMap("rankName","%" + rankName + "%");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.isEmpty()) {
				builder.appendNamedParameterMap("isDelete",Integer.valueOf(isDelete));
			}

			return (List<DmRankRating>)  queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public long countRankRatings(String rankNameVN, String rankName,
			String isDelete, String rankCodeGroup) throws SystemException { long count = 0; try {
			
			StringBuilder query = new StringBuilder();

			query.append("SELECT count(*) AS total FROM dm_rank_rating WHERE 1 = 1 ");

			if (Validator.isNotNull(rankNameVN) && !rankNameVN.isEmpty()) {
				query.append(" AND RankNameVN LIKE ?");
			}
			if (Validator.isNotNull(rankName) && !rankName.isEmpty()) {
				query.append(" AND RankName LIKE ?");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.isEmpty()) {
				query.append(" AND IsDelete= :isDelete");
			}
			if (Validator.isNotNull(rankCodeGroup) && !rankCodeGroup.isEmpty()) {
				rankCodeGroup = rankCodeGroup.replace(",", "', '");
				query.append(" AND RankCode IN ('" + rankCodeGroup + "')");
			}

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Long.class).build();
			

			if (Validator.isNotNull(rankNameVN) && !rankNameVN.isEmpty()) {
				builder.appendNamedParameterMap("rankNameVN","%" + rankNameVN + "%");
			}
			if (Validator.isNotNull(rankName) && !rankName.isEmpty()) {
				builder.appendNamedParameterMap("rankName","%" + rankName + "%");
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
