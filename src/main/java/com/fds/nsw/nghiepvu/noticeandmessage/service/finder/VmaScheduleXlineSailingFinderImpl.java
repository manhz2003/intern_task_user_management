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
import com.fds.nsw.nghiepvu.model.VmaScheduleXlineSailing;
@Service
@Slf4j
public class VmaScheduleXlineSailingFinderImpl extends

		BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaScheduleXlineSailing> queryFactory;

	

	public List<VmaScheduleXlineSailing> findVmaScheduleXlineSailings(
			String portofAuthority, String nameOfShip, String imoNumber,
			String callSign, String registryNumber, String voyageNo,
			String stateCode, String provinceCode, String maritimePortCode,
			String portGoingToStateName, String portGoingToCode, int start,
			int end) throws SystemException {
		

		try {

			

			StringBuilder query = new StringBuilder();

			query.append("SELECT * FROM vma_schedule_xline_sailing WHERE 1 = 1 ");

			query.append(generateCondition(portofAuthority, nameOfShip,
					imoNumber, callSign, registryNumber, voyageNo, stateCode,
					provinceCode, maritimePortCode, portGoingToStateName,
					portGoingToCode));

			log.info("=========select vma_schedule_xline_sailing>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).firstResult(start).maxResults(end - start).entityClass(VmaScheduleXlineSailing.class).build();

			List<VmaScheduleXlineSailing> vmaScheduleXlineSailings = (List<VmaScheduleXlineSailing>) queryFactory.getResultList(builder);

			return vmaScheduleXlineSailings;
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public long countVmaScheduleXlineSailings(String portofAuthority,
			String nameOfShip, String imoNumber, String callSign,
			String registryNumber, String voyageNo, String stateCode,
			String provinceCode, String maritimePortCode,
			String portGoingToStateName, String portGoingToCode) throws SystemException { long count = 0; try {

			

			StringBuilder query = new StringBuilder();

			query.append("SELECT COUNT(*) AS total FROM vma_schedule_xline_sailing WHERE 1 = 1 ");

			query.append(generateCondition(portofAuthority, nameOfShip,
					imoNumber, callSign, registryNumber, voyageNo, stateCode,
					provinceCode, maritimePortCode, portGoingToStateName,
					portGoingToCode));

			log.info("=========select count vma_schedule_xline_sailing>>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Long.class).build();

			
			count = (Long) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return count;
	}

	private String generateCondition(String portofAuthority, String nameOfShip,
			String imoNumber, String callSign, String registryNumber,
			String voyageNo, String stateCode, String provinceCode,
			String maritimePortCode, String portGoingToStateName,
			String portGoingToCode) {
		StringBuilder condition = new StringBuilder();

		if (portofAuthority != null && !portofAuthority.isEmpty()) {
			condition.append(" AND PortofAuthority =" + portofAuthority);
		}

		if (nameOfShip != null && !nameOfShip.isEmpty()) {
			condition.append(" AND nameOfShip LIKE '%" + nameOfShip + "%'");
		}

		if (imoNumber != null && !imoNumber.isEmpty()) {
			condition.append(" AND imoNumber = '" + imoNumber + "'");
		}

		if (callSign != null && !callSign.isEmpty()) {
			condition.append(" AND callSign = '" + callSign + "'");
		}

		if (registryNumber != null && !registryNumber.isEmpty()) {
			condition.append(" AND registryNumber = '" + registryNumber + "'");
		}

		if (voyageNo != null && !voyageNo.isEmpty()) {
			condition.append(" AND voyageNo = '" + voyageNo + "'");
		}

		if (stateCode != null && !stateCode.isEmpty()) {
			condition.append(" AND stateCode = '" + stateCode + "'");
		}

		if (provinceCode != null && !provinceCode.isEmpty()) {
			condition.append(" AND provinceCode = '" + provinceCode + "'");
		}

		if (maritimePortCode != null && !maritimePortCode.isEmpty()) {
			condition.append(" AND maritimePortCode = '" + maritimePortCode + "'");
		}

		if (portGoingToStateName != null && !portGoingToStateName.isEmpty()) {
			condition.append(" AND portGoingToStateName LIKE '%"
					+ portGoingToStateName + "%'");
		}

		if (portGoingToCode != null && !portGoingToCode.isEmpty()) {
			condition.append(" AND portGoingToCode = '" + portGoingToCode + "'");
		}

		return condition.toString();
	}
}
