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
import com.fds.nsw.nghiepvu.model.DmVmaShipType;
@Service
@Slf4j
public class DmVmaShipTypeFinderImpl extends
 BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmVmaShipType> queryFactory;
	public List<DmVmaShipType> findVmaShipTypes(String shipTypeName,
			String applyShip, String applyBoat, String isDelete,
			String shipTypeCodeGroup, int start, int end)
			throws SystemException {

		
		try {
			
			StringBuilder query = new StringBuilder();
			String sql = "SELECT * FROM dm_vma_ship_type WHERE 1=1 ";

			if (Validator.isNotNull(applyShip) && !applyShip.isEmpty()) {
				query.append(" AND applyShip= :applyShip ");
			}
			if (Validator.isNotNull(applyBoat) && !applyBoat.isEmpty()) {
				query.append(" AND applyBoat= :applyBoat ");
			}
			if (Validator.isNotNull(shipTypeName) && !shipTypeName.equals("")) {
				query.append(" AND shipTypeName like '%" + shipTypeName + "%'");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.equals("")) {
				query.append(" AND isDelete= :isDelete");
			}
			if (Validator.isNotNull(shipTypeCodeGroup)
					&& !shipTypeCodeGroup.equals("")) {
				shipTypeCodeGroup = shipTypeCodeGroup.replace(",", "', '");
				query.append(" AND ShipTypeCode IN ('" + shipTypeCodeGroup
						+ ")");
			}
			query.append(" ORDER BY modifiedDate DESC");

			sql = sql + query.toString();

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).firstResult(start).maxResults(end - start).entityClass(DmVmaShipType.class).build();
			log.info("=========find DANH SACH ===" + sql);
			
			
			if (Validator.isNotNull(applyShip) && !applyShip.equals("")) {
				builder.appendNamedParameterMap("applyShip",applyShip);
			}
			if (Validator.isNotNull(applyBoat) && !applyBoat.equals("")) {
				builder.appendNamedParameterMap("applyBoat",applyBoat);
			}
			if (Validator.isNotNull(isDelete) && !isDelete.equals("")) {
				int isDeleteTemp = Integer.valueOf(isDelete);
				builder.appendNamedParameterMap("isDeleteTemp",isDeleteTemp);
			}
			return (List<DmVmaShipType>)  queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public long countVmaShipTypes(String shipTypeName, String applyShip,
			String applyBoat, String isDelete, String shipTypeCodeGroup) throws SystemException { long count = 0; try {
			
			StringBuilder query = new StringBuilder();
			String sql = "SELECT count(*) AS total FROM dm_vma_ship_type WHERE 1=1 ";

			if (Validator.isNotNull(applyShip) && !applyShip.isEmpty()) {
				query.append(" AND applyShip= :applyShip ");
			}
			if (Validator.isNotNull(applyBoat) && !applyBoat.isEmpty()) {
				query.append(" AND applyBoat= :applyBoat ");
			}
			if (Validator.isNotNull(shipTypeName) && !shipTypeName.equals("")) {
				query.append(" AND shipTypeName like '%" + shipTypeName + "%'");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.equals("")) {
				query.append(" AND isDelete= :isDelete");
			}
			if (Validator.isNotNull(shipTypeCodeGroup)
					&& !shipTypeCodeGroup.equals("")) {
				shipTypeCodeGroup = shipTypeCodeGroup.replace(",", "', '");
				query.append(" AND ShipTypeCode IN ('" + shipTypeCodeGroup
						+ ")");
			}
			sql = sql + query.toString();

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(Long.class).build();
			

			if (Validator.isNotNull(isDelete) && !isDelete.equals("")) {
				int isDeleteTemp = Integer.valueOf(isDelete);
				builder.appendNamedParameterMap("isDeleteTemp",isDeleteTemp);
			}
			if (Validator.isNotNull(applyShip) && !applyShip.equals("")) {
				builder.appendNamedParameterMap("applyShip",applyShip);
			}
			if (Validator.isNotNull(applyBoat) && !applyBoat.equals("")) {
				builder.appendNamedParameterMap("applyBoat",applyBoat);
			}

			
			count = (Long) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return count;
	}

	
}
