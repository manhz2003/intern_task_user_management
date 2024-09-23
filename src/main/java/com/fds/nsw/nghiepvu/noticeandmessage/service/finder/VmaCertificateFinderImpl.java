package com.fds.nsw.nghiepvu.noticeandmessage.service.finder;

import java.util.*;

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
import com.fds.nsw.nghiepvu.model.VmaCertificate;
@Service
@Slf4j
public class VmaCertificateFinderImpl extends

		BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaCertificate> queryFactory;

	

	public List<VmaCertificate> findVmaCertificates(String nameOfShip,
			String certificateExpiredDate, String certificateIssueDate,
			String approvalName, String isExamined, String examinationDate,
			String imoNumber, String callSign, String registryNumber,
			int start, int end) throws SystemException {

		
		try {
			

			StringBuilder query = new StringBuilder();

			// query.append("SELECT dm_certificate.ID AS dmCertificateId, dm_certificate.CertificateCode, dm_certificate.CertificateName, vma_certificate.CertificateIssueDate, vma_certificate.CertificateExpiredDate, vma_certificate.ExaminationDate, vma_certificate.IsExamined, vma_certificate.NameOfShip, vma_certificate.ID AS vmaCertificateId, vma_certificate.IMONumber, vma_certificate.CallSign, vma_certificate.RegistryNumber FROM dm_certificate LEFT JOIN vma_certificate ON dm_certificate.CertificateCode = vma_certificate.CertificateCode WHERE 1 = 1 ");

			query.append("SELECT * FROM vma_certificate WHERE 1 = 1 ");
			if (Validator.isNotNull(nameOfShip) && !nameOfShip.isEmpty()) {
				query.append(" AND vma_certificate.NameOfShip LIKE '%"
						+ nameOfShip + "%'");
			}
			if (Validator.isNotNull(certificateExpiredDate)
					&& !certificateExpiredDate.isEmpty()) {
				Date date = null;

				try {
					date = FormatData.formatDDMMYYYY
							.parse(certificateExpiredDate);
				} catch (Exception e) {
					log.error(e.getMessage());
				}

				if (date != null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					String strDate = calendar.get(Calendar.YEAR) + "-"
							+ (calendar.get(Calendar.MONTH) + 1) + "-"
							+ calendar.get(Calendar.DATE);

					query.append(" AND (vma_certificate.CertificateExpiredDate BETWEEN '"
							+ strDate + " 00:00:00'");

					query.append(" AND '" + strDate + " 23:59:59' ) )");
				}
			}
			if (Validator.isNotNull(certificateIssueDate)
					&& !certificateIssueDate.isEmpty()) {
				Date date = null;

				try {
					date = FormatData.formatDDMMYYYY
							.parse(certificateIssueDate);
				} catch (Exception e) {
					log.error(e.getMessage());
				}

				if (date != null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					String strDate = calendar.get(Calendar.YEAR) + "-"
							+ (calendar.get(Calendar.MONTH) + 1) + "-"
							+ calendar.get(Calendar.DATE);

					query.append(" AND (vma_certificate.CertificateIssueDate BETWEEN '"
							+ strDate + " 00:00:00'");

					query.append(" AND '" + strDate + " 23:59:59' ) )");
				}
			}
			if (Validator.isNotNull(approvalName) && !approvalName.isEmpty()) {
				query.append(" AND vma_certificate.ApprovalName LIKE '%"
						+ approvalName + "%'");
			}
			if (Validator.isNotNull(isExamined) && !isExamined.isEmpty()) {
				query.append(" AND vma_certificate.IsExamined = " + isExamined);
			}
			if (Validator.isNotNull(examinationDate)
					&& !examinationDate.isEmpty()) {
				Date date = null;

				try {
					date = FormatData.formatDDMMYYYY.parse(examinationDate);
				} catch (Exception e) {
					log.error(e.getMessage());
				}

				if (date != null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					String strDate = calendar.get(Calendar.YEAR) + "-"
							+ (calendar.get(Calendar.MONTH) + 1) + "-"
							+ calendar.get(Calendar.DATE);

					query.append(" AND (vma_certificate.ExaminationDate BETWEEN '"
							+ strDate + " 00:00:00'");

					query.append(" AND '" + strDate + " 23:59:59' ) )");
				}
			}
			
			if(Validator.isNotNull(imoNumber)){
				query.append(" AND ImoNumber = '" + imoNumber + "'");
			}
			
			if(Validator.isNotNull(callSign)){
				query.append(" AND CallSign = '" + callSign + "'");
			}
			
			if(Validator.isNotNull(registryNumber)){
				query.append(" AND RegistryNumber = '" + registryNumber + "'");
			}

			log.info("=========select vma_certificate >>>>>>>>>>>>>>>>>>>>>>>>>>>========"
					+ query.toString());

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(query.toString()).firstResult(start).maxResults(end - start).entityClass(VmaCertificate.class).build();
			

			return (List<VmaCertificate>)  queryFactory.getResultList(builder);
		} catch (Exception e) {
			throw new SystemException(e);
		} finally {
			
		}
	}

	public long countVmaCertificates(String nameOfShip,
			String certificateExpiredDate, String certificateIssueDate,
			String approvalName, String isExamined, String examinationDate,
			String imoNumber, String callSign, String registryNumber) throws SystemException { long count = 0; try {
			

			StringBuilder query = new StringBuilder();

			query.append("SELECT count(dm_certificate.id) as total FROM dm_certificate LEFT JOIN vma_certificate ON dm_certificate.CertificateCode = vma_certificate.CertificateCode WHERE 1 = 1 ");

			if (Validator.isNotNull(nameOfShip) && !nameOfShip.isEmpty()) {
				query.append(" AND vma_certificate.NameOfShip LIKE '%"
						+ nameOfShip + "%'");
			}
			if (Validator.isNotNull(certificateExpiredDate)
					&& !certificateExpiredDate.isEmpty()) {
				Date date = null;

				try {
					date = FormatData.formatDDMMYYYY
							.parse(certificateExpiredDate);
				} catch (Exception e) {
					log.error(e.getMessage());
				}

				if (date != null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					String strDate = calendar.get(Calendar.YEAR) + "-"
							+ (calendar.get(Calendar.MONTH) + 1) + "-"
							+ calendar.get(Calendar.DATE);

					query.append(" AND (vma_certificate.CertificateExpiredDate BETWEEN '"
							+ strDate + " 00:00:00'");

					query.append(" AND '" + strDate + " 23:59:59' ) )");
				}
			}
			if (Validator.isNotNull(certificateIssueDate)
					&& !certificateIssueDate.isEmpty()) {
				Date date = null;

				try {
					date = FormatData.formatDDMMYYYY
							.parse(certificateIssueDate);
				} catch (Exception e) {
					log.error(e.getMessage());
				}

				if (date != null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					String strDate = calendar.get(Calendar.YEAR) + "-"
							+ (calendar.get(Calendar.MONTH) + 1) + "-"
							+ calendar.get(Calendar.DATE);

					query.append(" AND (vma_certificate.CertificateIssueDate BETWEEN '"
							+ strDate + " 00:00:00'");

					query.append(" AND '" + strDate + " 23:59:59' ) )");
				}
			}
			if (Validator.isNotNull(approvalName) && !approvalName.isEmpty()) {
				query.append(" AND vma_certificate.ApprovalName LIKE '%"
						+ approvalName + "%'");
			}
			if (Validator.isNotNull(isExamined) && !isExamined.isEmpty()) {
				query.append(" AND vma_certificate.IsExamined = " + isExamined);
			}
			if (Validator.isNotNull(examinationDate)
					&& !examinationDate.isEmpty()) {
				Date date = null;

				try {
					date = FormatData.formatDDMMYYYY.parse(examinationDate);
				} catch (Exception e) {
					log.error(e.getMessage());
				}

				if (date != null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					String strDate = calendar.get(Calendar.YEAR) + "-"
							+ (calendar.get(Calendar.MONTH) + 1) + "-"
							+ calendar.get(Calendar.DATE);

					query.append(" AND (vma_certificate.ExaminationDate BETWEEN '"
							+ strDate + " 00:00:00'");

					query.append(" AND '" + strDate + " 23:59:59' ) )");
				}
			}
			
			if(Validator.isNotNull(imoNumber)){
				query.append(" AND ImoNumber = '" + imoNumber + "'");
			}
			
			if(Validator.isNotNull(callSign)){
				query.append(" AND CallSign = '" + callSign + "'");
			}
			
			if(Validator.isNotNull(registryNumber)){
				query.append(" AND RegistryNumber = '" + registryNumber + "'");
			}

			log.info("=========count vma_certificate >>>>>>>>>>>>>>>>>>>>>>>>>>>========"
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
