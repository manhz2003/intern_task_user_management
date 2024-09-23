/**
 * 
 */
package com.fds.nsw.nghiepvu.danhmucgt.service.finder;

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
import com.fds.nsw.nghiepvu.model.DmCertificate;
@Service
@Slf4j
public class DmCertificateFinderImpl extends
 BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmCertificate> queryFactory;
	

	public List<DmCertificate> findDmCertificates(String certificateName,
			int start, int end) throws SystemException {

		

		try {
			

			StringBuilder query = new StringBuilder();

			query.append("SELECT * FROM dm_certificate");
			query.append(" WHERE 1=1 ");

			if (Validator.isNotNull(certificateName)
					&& !certificateName.isEmpty()) {
				query.append(" AND CertificateName LIKE '%" + certificateName
						+ "%'");
			}

			query.append(" ORDER BY LENGTH( dm_certificate.CertificateOrder ), dm_certificate.CertificateOrder ASC");

			log.info("=========select dm_certificate >>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).firstResult(start).maxResults(end - start).entityClass(DmCertificate.class).build();
			

			return (List<DmCertificate>)  queryFactory.getResultList(builder);

		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public long countVmaCertificates(String certificateName) throws SystemException { long count = 0; try {
			

			StringBuilder query = new StringBuilder();

			query.append("SELECT count(*) AS total FROM dm_certificate");
			query.append(" WHERE 1=1 ");

			if (Validator.isNotNull(certificateName)
					&& !certificateName.isEmpty()) {
				query.append(" AND CertificateName LIKE '%" + certificateName
						+ "%'");
			}

			log.info("=========count dm_certificate >>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Long.class).build();

			/*  */

			
			count = (Long) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return count;
	}

}
