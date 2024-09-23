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
import com.fds.nsw.nghiepvu.model.HistorySyncVersion;
@Service
@Slf4j
public class HistorySyncVersionFinderImpl extends
 BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<HistorySyncVersion> queryFactory;
	
	
	
	public List<HistorySyncVersion> getsyncVersion(Date requestedDate, String categoryID) throws SystemException {
		
		try {
			
			StringBuilder query = new StringBuilder();
			
			query.append("SELECT * FROM history_sync_version WHERE 1=1 ");
			
			if (Validator.isNotNull(requestedDate) && Validator.isNotNull(FormatData.parseDateToTring(requestedDate))) {
				query.append(" AND requesteddate <=  str_to_date('" + FormatData.parseDateToTring(requestedDate) + "', '%Y-%m-%d %H:%i:%s' )  and  latestvaluedate >= str_to_date('" + FormatData.parseDateToTring(requestedDate) + "', '%Y-%m-%d %H:%i:%s' ) ");
			}
			if (Validator.isNotNull(categoryID) && categoryID.trim().length() > 0) {
				query.append(" AND categoryid='" + categoryID + "' ");
			}
			query.append(" limit 1 ");
			log.debug("==========getsyncVersion========" + query.toString());
			
			String sql = query.toString();
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(HistorySyncVersion.class).build();
			// execute the query and return a list from the db
			return (List<HistorySyncVersion>) queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}
}
