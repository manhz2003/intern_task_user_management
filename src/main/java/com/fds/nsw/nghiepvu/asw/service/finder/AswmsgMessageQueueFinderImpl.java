package com.fds.nsw.nghiepvu.asw.service.finder;

import java.util.ArrayList;
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
import com.fds.nsw.nghiepvu.model.AswmsgMessagequeue;
import vn.gt.utils.config.ConfigurationManager;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class AswmsgMessageQueueFinderImpl extends
 BasePersistence {

	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<AswmsgMessagequeue> queryFactory;

	public List<AswmsgMessagequeue> getListMessage2Queue(int count) {

		try {

			// thay doi viec lay message queue tu priority > 0 sang lay theo so lan gui
			int soLanGuiToiDa = ConfigurationManager.getIntProp("solangui_message");
			String filterCondition = " AND validationcode not in ( Select DISTINCT validationcode from ( "
					+ "(Select validationcode from ( Select Count(*) as Total, validationcode from aswmsg_messagequeue where solangui > 0 and function not in (03) GROUP BY validationcode) tmp where total > 10) "
					+ " UNION (Select validationcode from ( Select Count(*) as Total, validationcode from aswmsg_messagequeue where solangui > 1 and function not in (03) GROUP BY validationcode) tmp where total > 4) "
					+ " ) tmp WHERE validationcode not in ('NSW')) ";
			String sql1 = "SELECT aswmsg_messagequeue.* FROM aswmsg_messagequeue where solangui < " + soLanGuiToiDa + " and priority > 0 and validated=-1 order by priority desc, solangui, createdtime, id asc" ;
			String sql = "SELECT aswmsg_messagequeue.* FROM aswmsg_messagequeue where solangui < " + soLanGuiToiDa + filterCondition + " and priority > 0 and validated=-1 order by priority desc, solangui, createdtime, id asc" ;

			log.info("=========findMotCuaPhieuXuLyChinhByHoSoThuTucId===" + sql1);

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql)
					.build();

			return queryFactory.getResultList(builder);
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {

		}

		return new ArrayList<AswmsgMessagequeue>();
	}

	public List<AswmsgMessagequeue> getListMessageQueuePending(String validationCode) {

		try {

			int soLanGuiToiDa = ConfigurationManager.getIntProp("solangui_message");

			StringBuilder sb = new StringBuilder();
			sb.append("SELECT aswmsg_messagequeue.* FROM aswmsg_messagequeue WHERE ");

			if (Validator.isNotNull(validationCode)) {
				sb.append("validationcode IN (");
				sb.append(validationCode);
				sb.append(") AND ");
			}

			sb.append("(solangui >= ");
			sb.append(soLanGuiToiDa);
			sb.append(" OR validated=0) ORDER BY priority desc, solangui, createdtime, id ASC");

			log.info("=========getListMessageQueuePending===" + sb.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sb.toString())
					.firstResult(QueryUtil.ALL_POS).maxResults(QueryUtil.ALL_POS)
					.build();

			return queryFactory.getResultList(builder);
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {

		}

		return new ArrayList<AswmsgMessagequeue>();
	}

}
