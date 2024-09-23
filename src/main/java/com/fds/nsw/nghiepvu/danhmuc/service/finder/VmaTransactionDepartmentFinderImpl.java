package com.fds.nsw.nghiepvu.danhmuc.service.finder;

import java.util.*;

import com.fds.flex.common.utility.string.StringUtil;
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
import com.fds.nsw.nghiepvu.model.VmaTransactionDepartment;
@Service
@Slf4j
public class VmaTransactionDepartmentFinderImpl extends

		BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaTransactionDepartment> queryFactory;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<String> queryFactory2;

	@Qualifier("blQueryFactory")
	QueryFactory<Object[]> queryFactory3;

	public List<VmaTransactionDepartment> findVmaTransactionDepartments(
			String portOfAuthority, String departmentName, int start, int end)
			throws SystemException {

		
		try {
			
			StringBuilder query = new StringBuilder();

			query.append("SELECT * FROM vma_transaction_department WHERE 1 = 1 ");
			if (Validator.isNotNull(portOfAuthority)
					&& !portOfAuthority.isEmpty()) {
				query.append(" AND PortOfAuthority =  ?");
			}
			if (Validator.isNotNull(departmentName)
					&& !departmentName.isEmpty()) {
				query.append(" AND DepartmentName LIKE ?");
			}
			query.append(" ORDER BY DepartmentCode");

			log.info("========= select vma_transaction_department: "
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).firstResult(start).maxResults(end - start).entityClass(VmaTransactionDepartment.class).build();
			

			if (Validator.isNotNull(portOfAuthority)
					&& !portOfAuthority.isEmpty()) {
				builder.appendNamedParameterMap("portOfAuthority",portOfAuthority);
			}
			if (Validator.isNotNull(departmentName)
					&& !departmentName.isEmpty()) {
				builder.appendNamedParameterMap("departmentName","%" + departmentName + "%");
			}

			return (List<VmaTransactionDepartment>)  queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public long countVmaTransactionDepartments(String portOfAuthority,
			String departmentName) throws SystemException { long count = 0; try {
			
			StringBuilder query = new StringBuilder();

			query.append("SELECT count(*) AS total FROM vma_transaction_department WHERE 1 = 1 ");
			if (Validator.isNotNull(portOfAuthority)
					&& !portOfAuthority.isEmpty()) {
				query.append(" AND PortOfAuthority =  ?");
			}
			if (Validator.isNotNull(departmentName)
					&& !departmentName.isEmpty()) {
				query.append(" AND DepartmentName LIKE ?");
			}

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).entityClass(Long.class).build();
			

			if (Validator.isNotNull(portOfAuthority)
					&& !portOfAuthority.isEmpty()) {
				builder.appendNamedParameterMap("portOfAuthority",portOfAuthority);
			}
			if (Validator.isNotNull(departmentName)
					&& !departmentName.isEmpty()) {
				builder.appendNamedParameterMap("departmentName","%" + departmentName + "%");
			}

			
			count = (Long) queryFactory.getSingleResult(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
		return count;
	}

	public List<String> checkExistDepartmentCode(String portofAuthority) {
		String sql = "SELECT vma_transaction_department.DepartmentCode FROM vma_transaction_department WHERE vma_transaction_department.DepartmentCode NOT IN (SELECT vma_transaction_balance.DepartmentCode FROM vma_transaction_balance)";

		if (Validator.isNotNull(portofAuthority)) {
			sql += " WHERE portofAuthority = '" + portofAuthority + "'";
		}

		
		List<String> departmentCodes = new ArrayList<String>();

		try {
			



			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(String.class).build();
			Iterator<String> itr = queryFactory2.getResultList(builder).iterator();

			if (itr.hasNext()) {
				while (itr.hasNext()) {
					String departmentCode = itr.next();
					departmentCodes.add(departmentCode);
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			
		}

		return departmentCodes;
	}

	public List<String[]> getDepartmentInfo(String portofAuthority) {
		String sql = "SELECT vma_transaction_department.DepartmentCode, vma_transaction_department.DepartmentName FROM vma_transaction_department";

		if (Validator.isNotNull(portofAuthority)) {
			sql += " WHERE portofAuthority = '" + portofAuthority + "'";
		}

		

		List<String[]> departmentCodes = new ArrayList<String[]>();

		try {


			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(Object.class).build();
			Iterator<Object[]> itr = queryFactory3.getResultList(builder).iterator();

			if (itr.hasNext()) {
				while (itr.hasNext()) {
					Object[] objects = itr.next();
					String departmentCode = String.valueOf(objects[0]);
					String departmentName = String.valueOf(objects[1]);
					departmentCodes.add(new String[] { departmentCode,
							departmentName });
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			
		}

		return departmentCodes;
	}

	public List<VmaTransactionDepartment> findVmaTransactionDepartmentByDepartmentCodes(
			String portofAuthority, List<String> departmentCodes) {

		if (departmentCodes == null || departmentCodes.isEmpty()) {
			return null;
		}

		String sql = "SELECT * from vma_transaction_department WHERE  DepartmentCode IN("
				+ StringUtil.merge(departmentCodes) + ")";
		if (Validator.isNotNull(portofAuthority)) {
			sql += " AND portofAuthority = '" + portofAuthority + "'";
		}

		

		try {
			

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(VmaTransactionDepartment.class).build();

			return (List<VmaTransactionDepartment>) queryFactory.getResultList(builder);

		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		} finally {
			
		}
	}
}
