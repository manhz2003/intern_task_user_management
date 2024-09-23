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
import com.fds.nsw.nghiepvu.model.DmVmaShipOwner;
@Service
@Slf4j
public class DmVmaShipOwnerFinderImpl extends

		BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmVmaShipOwner> queryFactory;
	public List<DmVmaShipOwner> findVmaShipOwners(String maritimeCode,
			String taxCode, String companyName, String companyAddress,
			String contactEmail, String telNo, String isShipOwner,
			String isShipOperator, String isDelete, String shipOwnerCodeGroup,
			int isOther, int start, int end) throws SystemException {

		
		try {
			
			StringBuilder query = new StringBuilder();
			String sql = "SELECT * FROM dm_vma_ship_owner WHERE 1=1 ";

			if (Validator.isNotNull(maritimeCode) && !maritimeCode.isEmpty()
					&& !maritimeCode.equals("---")) {
				query.append(" AND maritimeCode= :maritimeCode ");
			}
			if (Validator.isNotNull(taxCode) && !taxCode.equals("")) {
				query.append(" AND taxCode like '%" + taxCode + "%'");
			}
			if (Validator.isNotNull(companyName) && !companyName.equals("")) {
				query.append(" AND companyName like '%" + companyName + "%'");
			}
			if (Validator.isNotNull(companyAddress)
					&& !companyAddress.equals("")) {
				query.append(" AND companyAddress like '%" + companyAddress
						+ "%'");
			}
			if (Validator.isNotNull(contactEmail) && !contactEmail.equals("")) {
				query.append(" AND contactEmail like '%" + contactEmail + "%'");
			}
			if (Validator.isNotNull(telNo) && !telNo.equals("")) {
				query.append(" AND telNo like '%" + telNo + "%'");
			}
			if (Validator.isNotNull(isShipOwner) && !isShipOwner.equals("")) {
				query.append(" AND isShipOwner= :isShipOwner ");
			}
			if (Validator.isNotNull(isShipOperator)
					&& !isShipOperator.equals("")) {
				query.append(" AND isShipOperator= :isShipOperator ");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.equals("")) {
				query.append(" AND isDelete= :isDelete");
			}
			if (Validator.isNotNull(isOther)) {
				query.append(" AND IsOther= :isOther");
			}
			if (Validator.isNotNull(shipOwnerCodeGroup)
					&& !shipOwnerCodeGroup.equals("")) {
				shipOwnerCodeGroup = shipOwnerCodeGroup.replace(",", "', '");
				query.append(" AND ShipOwnerCode IN ('" + shipOwnerCodeGroup
						+ "')");
			}
			query.append(" ORDER BY modifiedDate DESC");

			sql = sql + query.toString();

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).firstResult(start).maxResults(end - start).entityClass(DmVmaShipOwner.class).build();
			log.info("=========find DANH SACH ===" + sql);
			

			if (Validator.isNotNull(maritimeCode) && !maritimeCode.isEmpty()
					&& !maritimeCode.equals("---")) {
				builder.appendNamedParameterMap("maritimeCode",maritimeCode);
			}
			if (Validator.isNotNull(isShipOwner) && !isShipOwner.equals("")) {
				int isShipOwnerTemp = Integer.valueOf(isShipOwner);
				builder.appendNamedParameterMap("isShipOwnerTemp",isShipOwnerTemp);
			}
			if (Validator.isNotNull(isShipOperator)
					&& !isShipOperator.equals("")) {
				int isShipOperatorTemp = Integer.valueOf(isShipOperator);
				builder.appendNamedParameterMap("isShipOperatorTemp",isShipOperatorTemp);
			}
			if (Validator.isNotNull(isDelete) && !isDelete.equals("")) {
				int isDeleteTemp = Integer.valueOf(isDelete);
				builder.appendNamedParameterMap("isDeleteTemp",isDeleteTemp);
			}
			if (Validator.isNotNull(isOther)) {
				builder.appendNamedParameterMap("isOther",isOther);
			}
			return (List<DmVmaShipOwner>)  queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public long countVmaShipOwners(String maritimeCode, String taxCode,
			String companyName, String companyAddress, String contactEmail,
			String telNo, String isShipOwner, String isShipOperator,
			String isDelete, String shipOwnerCodeGroup, int isOther) throws SystemException { long count = 0; try {
			
			StringBuilder query = new StringBuilder();
			String sql = "SELECT count(*) AS total FROM dm_vma_ship_owner WHERE 1=1 ";

			if (Validator.isNotNull(maritimeCode) && !maritimeCode.isEmpty()
					&& !maritimeCode.equals("---")) {
				query.append(" AND maritimeCode= :maritimeCode ");
			}
			if (Validator.isNotNull(taxCode) && !taxCode.equals("")) {
				query.append(" AND taxCode like '%" + taxCode + "%'");
			}
			if (Validator.isNotNull(companyName) && !companyName.equals("")) {
				query.append(" AND companyName like '%" + companyName + "%'");
			}
			if (Validator.isNotNull(companyAddress)
					&& !companyAddress.equals("")) {
				query.append(" AND companyAddress like '%" + companyAddress
						+ "%'");
			}
			if (Validator.isNotNull(contactEmail) && !contactEmail.equals("")) {
				query.append(" AND contactEmail like '%" + contactEmail + "%'");
			}
			if (Validator.isNotNull(telNo) && !telNo.equals("")) {
				query.append(" AND telNo like '%" + telNo + "%'");
			}
			if (Validator.isNotNull(isShipOwner) && !isShipOwner.equals("")) {
				query.append(" AND isShipOwner= :isShipOwner ");
			}
			if (Validator.isNotNull(isShipOperator)
					&& !isShipOperator.equals("")) {
				query.append(" AND isShipOperator= :isShipOperator ");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.equals("")) {
				query.append(" AND isDelete= :isDelete");
			}
			if (Validator.isNotNull(isOther)) {
				query.append(" AND isOther= :isOther");
			}
			if (Validator.isNotNull(shipOwnerCodeGroup)
					&& !shipOwnerCodeGroup.equals("")) {
				shipOwnerCodeGroup = shipOwnerCodeGroup.replace(",", "', '");
				query.append(" AND ShipOwnerCode IN ('" + shipOwnerCodeGroup
						+ "')");
			}
			sql = sql + query.toString();

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(Long.class).build();
			

			if (Validator.isNotNull(maritimeCode) && !maritimeCode.isEmpty()
					&& !maritimeCode.equals("---")) {
				builder.appendNamedParameterMap("maritimeCode",maritimeCode);
			}
			if (Validator.isNotNull(isShipOwner) && !isShipOwner.equals("")) {
				int isShipOwnerTemp = Integer.valueOf(isShipOwner);
				builder.appendNamedParameterMap("isShipOwnerTemp",isShipOwnerTemp);
			}
			if (Validator.isNotNull(isShipOperator)
					&& !isShipOperator.equals("")) {
				int isShipOperatorTemp = Integer.valueOf(isShipOperator);
				builder.appendNamedParameterMap("isShipOperatorTemp",isShipOperatorTemp);
			}
			if (Validator.isNotNull(isDelete) && !isDelete.equals("")) {
				int isDeleteTemp = Integer.valueOf(isDelete);
				builder.appendNamedParameterMap("isDeleteTemp",isDeleteTemp);
			}
			if (Validator.isNotNull(isOther)) {
				builder.appendNamedParameterMap("isOther",isOther);
			}
			
			count = (Long) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return count;
	}

	

}
