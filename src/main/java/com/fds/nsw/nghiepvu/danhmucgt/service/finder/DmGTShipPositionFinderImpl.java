package com.fds.nsw.nghiepvu.danhmucgt.service.finder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.fds.nsw.nghiepvu.model.DmGtShipPosition;
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
@Service
@Slf4j
public class DmGTShipPositionFinderImpl extends
 BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmGtShipPosition> queryFactory;

	public List<DmGtShipPosition> findByRoleAndThuTuc(String positionCode) throws SystemException {
		
		try {
			
			StringBuilder query = new StringBuilder();
			
			query.append("SELECT * FROM dm_gt_ship_position WHERE 1=1 ");
			
			if (Validator.isNotNull(positionCode) && positionCode.trim().length() > 0 ) {
				query.append("AND PositionCode IN ("+positionCode+")");
			}
			
			String sql = query.toString();
			//System.out.println("=========findByRoleAndThuTuc========" + sql);
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(DmGtShipPosition.class).build();
						// execute the query and return a list from the db
			return (List<DmGtShipPosition>) queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}
}
