package com.fds.nsw.nghiepvu.noticeandmessage.service.finder;

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
import com.fds.nsw.nghiepvu.model.VmaShipCertificate;
@Service
@Slf4j
public class VmaShipCertificateFinderImpl extends

		BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaShipCertificate> queryFactory;

	public List<VmaShipCertificate> findVmaShipCertificate(String imoNumber,
			String callSign, int start, int end) throws SystemException {
		
		try {
			
			StringBuilder query = new StringBuilder();
			String sql = "SELECT * FROM vma_ship_certificate WHERE imoNumber = '"
					+ imoNumber
					+ "' AND callSign = '"
					+ callSign
					+ "' AND (certificateExpiredDate is not null) AND Date(certificateExpiredDate) <= Date(now())";

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).firstResult(start).maxResults(end - start).entityClass(VmaShipCertificate.class).build();

			return (List<VmaShipCertificate>)  queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

}
