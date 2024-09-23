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
import com.fds.nsw.nghiepvu.model.DmCargoOnBoard;
@Service
@Slf4j
public class DmCargoOnBoardFinderImpl extends

		BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmCargoOnBoard> queryFactory;

	public List<DmCargoOnBoard> findCargoOnBoards(String goodsTypeNameVN,
			String isDelete, String goodsTypeCodeGroup, int start, int end)
			throws SystemException {
		
		try {
			
			StringBuilder query = new StringBuilder();

			query.append("SELECT * FROM dm_cargo_on_board WHERE 1 = 1 ");

			if (Validator.isNotNull(goodsTypeNameVN)
					&& !goodsTypeNameVN.isEmpty()) {
				query.append(" AND GoodsTypeNameVN LIKE :goodsTypeNameVN");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.isEmpty()) {
				query.append(" AND IsDelete = :isDelete");
			}
			if (Validator.isNotNull(goodsTypeCodeGroup)
					&& !goodsTypeCodeGroup.isEmpty()) {
				goodsTypeCodeGroup = goodsTypeCodeGroup.replace(",", "', '");
				query.append(" AND GoodsTypeCode IN ('" + goodsTypeCodeGroup
						+ "')");
			}

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).firstResult(start).maxResults(end - start).entityClass(DmCargoOnBoard.class).build();
			

			if (Validator.isNotNull(goodsTypeNameVN)
					&& !goodsTypeNameVN.isEmpty()) {
				builder.appendNamedParameterMap("goodsTypeNameVN","%" + goodsTypeNameVN + "%");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.isEmpty()) {
				builder.appendNamedParameterMap("isDelete",Integer.valueOf(isDelete));
			}

			return (List<DmCargoOnBoard>)  queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public long countCargoOnBoards(String goodsTypeNameVN, String isDelete,
			String goodsTypeCodeGroup) throws SystemException {
		long count = 0;
		try {
			
			StringBuilder query = new StringBuilder();

			query.append("SELECT count(*) AS total FROM dm_cargo_on_board WHERE 1 = 1 ");

			if (Validator.isNotNull(goodsTypeNameVN)
					&& !goodsTypeNameVN.isEmpty()) {
				query.append(" AND GoodsTypeNameVN LIKE :goodsTypeNameVN");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.isEmpty()) {
				query.append(" AND IsDelete = :isDelete");
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

			
			count = (Long) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return count;
	}
}
