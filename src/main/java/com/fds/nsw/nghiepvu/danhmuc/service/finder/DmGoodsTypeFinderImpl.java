package com.fds.nsw.nghiepvu.danhmuc.service.finder;

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
import com.fds.nsw.nghiepvu.model.DmGoodsType;
@Service
@Slf4j
public class DmGoodsTypeFinderImpl extends
 BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmGoodsType> queryFactory;
	@Qualifier("blQueryFactory")
	QueryFactory<Long> queryFactory3;
	

	public List<DmGoodsType> findGoodsType(String goodsTypeNameVN,
			String isDelete, String goodsTypeCodeGroup, int start, int end) throws SystemException {
		
		try {
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT * FROM dm_goods_type WHERE 1 = 1");

			if (Validator.isNotNull(goodsTypeNameVN)
					&& !goodsTypeNameVN.isEmpty()) {
				query.append(" AND GoodsTypeNameVN LIKE ?");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.isEmpty()) {
				query.append(" AND isDelete= :isDelete");
			}
			if (Validator.isNotNull(goodsTypeCodeGroup)
					&& !goodsTypeCodeGroup.isEmpty()) {
				goodsTypeCodeGroup = goodsTypeCodeGroup.replace(",", "', '");
				query.append(" AND GoodsTypeCode IN ('" + goodsTypeCodeGroup
						+ "')");
			}
			query.append(" ORDER BY GoodsTypeCode");

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).firstResult(start).maxResults(end - start).entityClass(DmGoodsType.class).build();
			
			

			if (Validator.isNotNull(goodsTypeNameVN)
					&& !goodsTypeNameVN.isEmpty()) {
				builder.appendNamedParameterMap("goodsTypeNameVN","%" + goodsTypeNameVN + "%");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.isEmpty()) {
				builder.appendNamedParameterMap("isDelete",Integer.valueOf(isDelete));
			}

			return (List<DmGoodsType>)  queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException();
		} finally {
			
		}
	}

	public long countGoodsType(String goodsTypeNameVN, String isDelete,
			String goodsTypeCodeGroup) throws SystemException { try {
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT count(*) AS total FROM dm_goods_type WHERE 1 = 1");

			if (Validator.isNotNull(goodsTypeNameVN)
					&& !goodsTypeNameVN.isEmpty()) {
				query.append(" AND GoodsTypeNameVN LIKE ?");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.isEmpty()) {
				query.append(" AND isDelete= :isDelete");
			}
			if (Validator.isNotNull(goodsTypeCodeGroup)
					&& !goodsTypeCodeGroup.isEmpty()) {
				goodsTypeCodeGroup = goodsTypeCodeGroup.replace(",", "', '");
				query.append(" AND GoodsTypeCode IN ('" + goodsTypeCodeGroup
						+ "')");
			}

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Long.class).build();
			

			if (Validator.isNotNull(goodsTypeNameVN)
					&& !goodsTypeNameVN.isEmpty()) {
				builder.appendNamedParameterMap("goodsTypeNameVN","%" + goodsTypeNameVN + "%");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.isEmpty()) {
				builder.appendNamedParameterMap("isDelete",Integer.valueOf(isDelete));
			}

			Iterator<Long> itr = queryFactory3.getResultList(builder).iterator();

			if (itr.hasNext()) {
				Long count = itr.next();
				if (count != null) {
					return count.intValue();
				}
			}

			return 0;
		} catch (Exception e) {
			throw new SystemException();
		} finally {
			
		}
	}
}
