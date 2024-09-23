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
import com.fds.nsw.nghiepvu.model.DmPackage;
@Service
@Slf4j
public class DmPackageFinderImpl extends
 BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmPackage> queryFactory;

	

	public List<DmPackage> findPackages(String packageCode,
			String packageNameVN, String isDelete, int start, int end)
			throws SystemException {
		
		try {
			
			StringBuilder query = new StringBuilder();

			query.append("SELECT * FROM dm_package WHERE 1 = 1");
			if (Validator.isNotNull(packageCode) && !packageCode.isEmpty()) {
				packageCode = packageCode.replace(",", "', '");
				query.append(" AND PackageCode IN ('" + packageCode + "')");
			}
			if (Validator.isNotNull(packageNameVN) && !packageNameVN.isEmpty()) {
				query.append(" AND PackageNameVN LIKE ?");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.isEmpty()) {
				query.append(" AND IsDelete= :isDelete");
			}

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).firstResult(start).maxResults(end - start).entityClass(DmPackage.class).build();
			

			log.info("FIND===============: " + query);

			if (Validator.isNotNull(packageNameVN) && !packageNameVN.isEmpty()) {
				builder.appendNamedParameterMap("packageNameVN","%" + packageNameVN + "%");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.isEmpty()) {
				builder.appendNamedParameterMap("isDelete",Integer.valueOf(isDelete));
			}

			return (List<DmPackage>) queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public long countPackages(String packageCode, String packageNameVN,
			String isDelete) throws SystemException {
		long count = 0;
		try {
			
			StringBuilder query = new StringBuilder();

			query.append("SELECT count(*) AS total FROM dm_package WHERE 1 = 1");
			if (Validator.isNotNull(packageCode) && !packageCode.isEmpty()) {
				packageCode = packageCode.replace(",", "', '");
				query.append(" AND PackageCode IN ('" + packageCode + "')");
			}
			if (Validator.isNotNull(packageNameVN) && !packageNameVN.isEmpty()) {
				query.append(" AND PackageNameVN LIKE ?");
			}
			if (Validator.isNotNull(isDelete) && !isDelete.isEmpty()) {
				query.append(" AND IsDelete= :isDelete");
			}

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Long.class).build();
			

			if (Validator.isNotNull(packageNameVN) && !packageNameVN.isEmpty()) {
				builder.appendNamedParameterMap("packageNameVN","%" + packageNameVN + "%");
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
