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
import com.fds.nsw.nghiepvu.model.DmGoods;
@Service
@Slf4j
public class DmGoodsFinderImpl extends
 BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmGoods> queryFactory;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<Long> queryFactory3;
	

	public List<DmGoods> findGoods(String goodsItemName, String isDelete,
			String goodsItemCodeGroup, int start, int end) throws SystemException {
		
		try {
			
			StringBuilder query = new StringBuilder();

			query.append("SELECT * FROM dm_goods WHERE 1 = 1");
			if (Validator.isNotNull(goodsItemName) && !goodsItemName.isEmpty()) {
				query.append(" AND GoodsItemName LIKE ?");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.isEmpty()) {
				query.append(" AND IsDelete= :isDelete");
			}
			if (Validator.isNotNull(goodsItemCodeGroup)
					&& !goodsItemCodeGroup.isEmpty()) {
				goodsItemCodeGroup = goodsItemCodeGroup.replace(",", "', '");
				query.append(" AND GoodsItemCode IN ('" + goodsItemCodeGroup
						+ "')");
			}
			query.append(" ORDER BY GoodsItemCode");

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).firstResult(start).maxResults(end - start).entityClass(DmGoods.class).build();
			

			if (Validator.isNotNull(goodsItemName) && !goodsItemName.isEmpty()) {
				builder.appendNamedParameterMap("goodsItemName","%" + goodsItemName + "%");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.isEmpty()) {
				builder.appendNamedParameterMap("isDelete",Integer.valueOf(isDelete));
			}

			return (List<DmGoods>)  queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException();
		} finally {
			
		}
	}

	public long countGoods(String goodsItemName, String isDelete,
			String goodsItemCodeGroup) throws SystemException {  try {
			
			StringBuilder query = new StringBuilder();

			query.append("SELECT count(*) AS total FROM dm_goods WHERE 1 = 1");
			if (Validator.isNotNull(goodsItemName) && !goodsItemName.isEmpty()) {
				query.append(" AND GoodsItemName LIKE ?");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.isEmpty()) {
				query.append(" AND IsDelete= :isDelete");
			}
			if (Validator.isNotNull(goodsItemCodeGroup)
					&& !goodsItemCodeGroup.isEmpty()) {
				goodsItemCodeGroup = goodsItemCodeGroup.replace(",", "', '");
				query.append(" AND GoodsItemCode IN ('" + goodsItemCodeGroup
						+ "')");
			}

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Long.class).build();
			

			if (Validator.isNotNull(goodsItemName) && !goodsItemName.isEmpty()) {
				builder.appendNamedParameterMap("goodsItemName","%" + goodsItemName + "%");
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
