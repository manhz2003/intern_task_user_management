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
import com.fds.nsw.nghiepvu.model.DmPassportType;
@Service
@Slf4j
public class DmPassportTypeFinderImpl extends

		BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmPassportType> queryFactory;

	

	public List<DmPassportType> findPassportTypes(String passportTypeNameVN,
			String isDelete, String passportTypeCodeGroup, int start, int end) throws SystemException {
		
		try {
			
			StringBuilder query = new StringBuilder();

			query.append("SELECT * FROM dm_passport_type WHERE 1 = 1");
			if (Validator.isNotNull(passportTypeNameVN)
					&& !passportTypeNameVN.isEmpty()) {
				query.append(" AND PassportTypeNameVN LIKE ?");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.isEmpty()) {
				query.append(" AND IsDelete= :isDelete");
			}
			if (Validator.isNotNull(passportTypeCodeGroup)
					&& !passportTypeCodeGroup.isEmpty()) {
				passportTypeCodeGroup = passportTypeCodeGroup.replace(",",
						"', '");
				query.append(" AND PassportTypeCode IN ('"
						+ passportTypeCodeGroup + "')");
			}
			query.append(" ORDER BY PassportTypeCode");

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).firstResult(start).maxResults(end - start).entityClass(DmPassportType.class).build();
			

			if (Validator.isNotNull(passportTypeNameVN)
					&& !passportTypeNameVN.isEmpty()) {
				builder.appendNamedParameterMap("passportTypeNameVN","%" + passportTypeNameVN + "%");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.isEmpty()) {
				builder.appendNamedParameterMap("isDelete",Integer.valueOf(isDelete));
			}

			return (List<DmPassportType>)  queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public long countPassportTypes(String passportTypeNameVN, String isDelete,
			String passportTypeCodeGroup) throws SystemException {
    long count = 0;

		
		try {
			
			StringBuilder query = new StringBuilder();

			query.append("SELECT count(*) AS total FROM dm_passport_type WHERE 1 = 1");
			if (Validator.isNotNull(passportTypeNameVN)
					&& !passportTypeNameVN.isEmpty()) {
				query.append(" AND PassportTypeNameVN LIKE ?");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.isEmpty()) {
				query.append(" AND IsDelete= :isDelete");
			}
			if (Validator.isNotNull(passportTypeCodeGroup)
					&& !passportTypeCodeGroup.isEmpty()) {
				passportTypeCodeGroup = passportTypeCodeGroup.replace(",",
						"', '");
				query.append(" AND PassportTypeCode IN ('"
						+ passportTypeCodeGroup + "')");
			}

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Long.class).build();
			

			if (Validator.isNotNull(passportTypeNameVN)
					&& !passportTypeNameVN.isEmpty()) {
				builder.appendNamedParameterMap("passportTypeNameVN","%" + passportTypeNameVN + "%");
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
