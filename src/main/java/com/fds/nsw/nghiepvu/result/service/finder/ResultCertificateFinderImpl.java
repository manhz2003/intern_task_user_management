package com.fds.nsw.nghiepvu.result.service.finder;

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
import com.fds.nsw.nghiepvu.model.ResultCertificate;
@Service
@Slf4j
public class ResultCertificateFinderImpl extends

		BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<ResultCertificate> queryFactory;

	public List<ResultCertificate> findResultCertificates(String imoNumber,
			String callSign, int start, int end) throws SystemException {
		
		try {
			
			String sql = "Select DISTINCT result_certificate.* from result_certificate INNER JOIN temp_document ON  result_certificate.DocumentName = temp_document.DocumentName and result_certificate.DocumentYear = temp_document.DocumentYear AND temp_document.IMO = '"
					+ imoNumber
					+ "' and temp_document.CallSign = '"
					+ callSign
					+ "' where result_certificate.DocumentYear in (2019) and (CertificateNo is not null and CertificateIssueDate is not null or CertificateExpiredDate is not null ) GROUP BY CertificateCode ORDER BY CertificateCode";

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).firstResult(start).maxResults(end - start).entityClass(ResultCertificate.class).build();

			return (List<ResultCertificate>)  queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}
}
