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
import com.fds.nsw.nghiepvu.model.ViewHoanThanhThuTuc;
@Service
@Slf4j
public class ViewHoanThanhThuTucFinderImpl extends
 BasePersistence {
@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<ViewHoanThanhThuTuc> queryFactory;
	
	
	
	
	public List<ViewHoanThanhThuTuc> findByKetQuaHoanThanhThuTuc(String maritimeCode, String dateFrom, String dateTo) {
		
		Date date = null;
		try {
			
			StringBuilder query = new StringBuilder();
			
			if (Validator.isNotNull(dateFrom) && dateFrom.trim().length() > 0) {
				date = FormatData.parseDateShort3StringToDate(dateFrom.trim());
				dateFrom = FormatData.parseDateToTring(date);				
			}
			if (Validator.isNotNull(dateTo) && dateTo.trim().length() > 0) {
				date = FormatData.parseDateShort3StringToDate(dateTo.trim());
				dateTo = FormatData.parseDateToTring(date);				
			}
			
			query.append(" Select CityCode as CVHH,            ");
			query.append(" MaritimeOrder,           ");
			query.append(" (Case NC IS NULL when true then 0 else NC end) as NC_KYSO,           ");
			query.append(" (Case XC IS NULL when true then 0 else XC end) as XC_KYSO,           ");						
			query.append(" (Case QC IS NULL when true then 0 else QC end) as QC_KYSO,           ");
			query.append(" (Case VC IS NULL when true then 0 else VC end) as VC_KYSO,           ");
			query.append(" (Case RC IS NULL when true then 0 else RC end) as RC_KYSO,           ");
			query.append(" (Case CCV IS NULL when true then 0 else CCV end) as CCV_KYSO,           ");
			query.append(" (Case CCR IS NULL when true then 0 else CCR end) as CCR_KYSO,           ");
			query.append(" (Case VCDK IS NULL when true then 0 else VCDK end) as VCDK_KYSO,           ");
			query.append(" (Case RCDK IS NULL when true then 0 else RCDK end) as RCDK_KYSO,           ");
			query.append(" (Case NCDK IS NULL when true then 0 else NCDK end) as NCDK_KYSO,           ");
			query.append(" (Case XCDK IS NULL when true then 0 else XCDK end) as XCDK_KYSO,           ");
			query.append(" (Case VCTND IS NULL when true then 0 else VCTND end) as VCTND_KYSO,           ");
			query.append(" (Case RCTND IS NULL when true then 0 else RCTND end) as RCTND_KYSO,           ");
			query.append(" (Case NCPTTND IS NULL when true then 0 else NCPTTND end) as NCPTTND_KYSO,           ");
			query.append(" (Case XCPTTND IS NULL when true then 0 else XCPTTND end) as XCPTTND_KYSO,           ");
			query.append(" (Case NC_DUYET IS NULL when true then 0 else NC_DUYET end) as NC_DUYET,           ");
			query.append(" (Case XC_DUYET IS NULL when true then 0 else XC_DUYET end) as XC_DUYET,           ");			
			query.append(" (Case QC_DUYET IS NULL when true then 0 else QC_DUYET end) as QC_DUYET,           ");
			query.append(" (Case VC_DUYET IS NULL when true then 0 else VC_DUYET end) as VC_DUYET,           ");
			query.append(" (Case RC_DUYET IS NULL when true then 0 else RC_DUYET end) as RC_DUYET,           ");
			query.append(" (Case CCV_DUYET IS NULL when true then 0 else CCV_DUYET end) as CCV_DUYET,           ");
			query.append(" (Case CCR_DUYET IS NULL when true then 0 else CCR_DUYET end) as CCR_DUYET,           ");
			query.append(" (Case VCDK_DUYET IS NULL when true then 0 else VCDK_DUYET end) as VCDK_DUYET,           ");
			query.append(" (Case RCDK_DUYET IS NULL when true then 0 else RCDK_DUYET end) as RCDK_DUYET,           ");
			query.append(" (Case NCDK_DUYET IS NULL when true then 0 else NCDK_DUYET end) as NCDK_DUYET,           ");
			query.append(" (Case XCDK_DUYET IS NULL when true then 0 else XCDK_DUYET end) as XCDK_DUYET,           ");
			query.append(" (Case NCPTTND_DUYET IS NULL when true then 0 else NCPTTND_DUYET end) as NCPTTND_DUYET,           ");
			query.append(" (Case XCPTTND_DUYET IS NULL when true then 0 else XCPTTND_DUYET end) as XCPTTND_DUYET,           ");
			query.append(" (Case VCTND_DUYET IS NULL when true then 0 else VCTND_DUYET end) as VCTND_DUYET,           ");
			query.append(" (Case RCTND_DUYET IS NULL when true then 0 else RCTND_DUYET end) as RCTND_DUYET from            ");
			query.append(" (           ");
			query.append("            ");
			query.append(" Select CityCode,            ");
			query.append("    MaritimeOrder,        ");
			query.append("    HosoNhapCanh.total as NC ,         ");
			query.append("    HosoXuatCanh.total as XC ,        ");
			query.append("    HosoQuaCanh.total as QC ,        ");
			query.append("    HosoTauVaoCang.total as VC,         ");
			query.append("    HosoTauRoiCang.total as RC,        ");
			query.append("    HosoChuyenCangVao.total as CCV,         ");
			query.append("    HosoChuyenCangRoi.total as CCR,         ");
			query.append("    HosoVaoCangDauKhi.total as VCDK,        ");
			query.append("    HosoRoiCangDauKhi.total as RCDK,        ");
			query.append("    HosoNhapCanhDauKhi.total as NCDK,        ");
			query.append("    HosoXuatCanhDauKhi.total as XCDK,        ");
			query.append("    HosoVaoCangTND.total as VCTND,        ");
			query.append("    HosoRoiCangTND.total as RCTND,        ");
			query.append("    HosoNhapCanhVNCPC.total as NCPTTND,        ");
			query.append("    HosoXuatCanhVNCPC.total as XCPTTND,        ");
			query.append("    HosoNhapCanh_DUYET.total as NC_DUYET ,         ");
			query.append("    HosoXuatCanh_DUYET.total as XC_DUYET,        ");
			query.append("    HosoQuaCanh_DUYET.total as QC_DUYET,        ");
			query.append("    HosoTauVaoCang_DUYET.total as VC_DUYET,         ");
			query.append("    HosoTauRoiCang_DUYET.total as RC_DUYET,        ");
			query.append("    HosoChuyenCangVao_DUYET.total as CCV_DUYET,         ");
			query.append("    HosoChuyenCangRoi_DUYET.total as CCR_DUYET,         ");
			query.append("    HosoVaoCangDauKhi_DUYET.total as VCDK_DUYET,        ");
			query.append("    HosoRoiCangDauKhi_DUYET.total as RCDK_DUYET,        ");
			query.append("    HosoNhapCanhDauKhi_DUYET.total as NCDK_DUYET,        ");
			query.append("    HosoXuatCanhDauKhi_DUYET.total as XCDK_DUYET,        ");
			query.append("    HosoNhapCanhVNCPC_DUYET.total as NCPTTND_DUYET,        ");
			query.append("    HosoXuatCanhVNCPC_DUYET.total as XCPTTND_DUYET,        ");
			query.append("    HosoVaoCangTND_DUYET.total as VCTND_DUYET,        ");
			query.append("    HosoRoiCangTND_DUYET.total as RCTND_DUYET        ");
			query.append(" from dm_maritime            ");
			query.append(" LEFT JOIN            ");
			query.append(" (           ");
			query.append(" select temp.MaritimeCode, DocumentTypeCode, count(temp.DocumentName) as total from temp_document temp            ");
			query.append("    inner join issue_shifting_order  iss         ");
			query.append("    on iss.DocumentName = temp.DocumentName        ");
			query.append("    and iss.DocumentYear  = temp.DocumentYear         ");
			query.append("    and   iss.StampStatus = 2 and iss.AttachedFile > 0        ");
			query.append(" and iss.requestState in (4)           ");
			query.append(" and temp.documentStatusCode in (19)           ");
			query.append("    and iss.VersionNo = (SELECT MAX(iss2.VersionNo)         ");
			query.append("      FROM issue_shifting_order iss2       ");
			query.append("       WHERE iss.DocumentName = iss2.DocumentName)      ");
			if (Validator.isNotNull(dateFrom) && dateFrom.trim().length() > 0) {				
				query.append(" and temp.ShipDateFrom >= concat(date('" + dateFrom + "'), ' 00:00:00') ");
			}
			if (Validator.isNotNull(dateTo) && dateTo.trim().length() > 0) {				
				query.append(" and temp.ShipDateFrom <= concat(date('" + dateTo + "'), ' 23:59:59') ");
			}			
			if (maritimeCode != null) {
				query.append(" and temp.MaritimeCode = " + maritimeCode);
			}
			query.append(" WHERE 1=1           ");
			query.append(" and temp.DocumentTypeCode in ('NC')           ");
			query.append(" Group by MaritimeCode, DocumentTypeCode order by LENGTH(MaritimeCode), MaritimeCode           ");
			query.append(" ) HosoNhapCanh           ");
			query.append(" ON HosoNhapCanh.MaritimeCode = dm_maritime.MaritimeCode           ");
			query.append("            ");
			
			query.append(" LEFT JOIN            ");
			query.append(" (           ");
			query.append(" select temp.MaritimeCode, DocumentTypeCode, count(temp.DocumentName) as total from temp_document temp            ");
			query.append("    inner join issue_shifting_order  iss         ");
			query.append("    on iss.DocumentName = temp.DocumentName        ");
			query.append("    and iss.DocumentYear  = temp.DocumentYear         ");
			query.append("    and   iss.StampStatus = 2 and iss.AttachedFile > 0        ");
			query.append(" and iss.requestState in (4)           ");
			query.append(" and temp.documentStatusCode in (19)           ");
			query.append("    and iss.VersionNo = (SELECT MAX(iss2.VersionNo)         ");
			query.append("      FROM issue_shifting_order iss2       ");
			query.append("       WHERE iss.DocumentName = iss2.DocumentName)      ");
			if (Validator.isNotNull(dateFrom) && dateFrom.trim().length() > 0) {				
				query.append(" and temp.ShipDateFrom >= concat(date('" + dateFrom + "'), ' 00:00:00') ");
			}
			if (Validator.isNotNull(dateTo) && dateTo.trim().length() > 0) {				
				query.append(" and temp.ShipDateFrom <= concat(date('" + dateTo + "'), ' 23:59:59') ");
			}		
			if (maritimeCode != null) {
				query.append(" and temp.MaritimeCode = " + maritimeCode);
			}
			query.append(" WHERE 1=1           ");
			query.append(" and temp.DocumentTypeCode in ('4')           ");
			query.append(" Group by MaritimeCode, DocumentTypeCode order by LENGTH(MaritimeCode), MaritimeCode           ");
			query.append(" ) HosoTauVaoCang           ");
			query.append(" ON HosoTauVaoCang.MaritimeCode = dm_maritime.MaritimeCode           ");
			query.append("            ");
			
			query.append(" LEFT JOIN            ");
			query.append(" (           ");
			query.append(" select temp.MaritimeCode, DocumentTypeCode, count(temp.DocumentName) as total from temp_document temp            ");
			query.append("    inner join issue_shifting_order  iss         ");
			query.append("    on iss.DocumentName = temp.DocumentName        ");
			query.append("    and iss.DocumentYear  = temp.DocumentYear         ");
			query.append("    and   iss.StampStatus = 2 and iss.AttachedFile > 0        ");
			query.append(" and iss.requestState in (4)           ");
			query.append(" and temp.documentStatusCode in (19)           ");
			query.append("    and iss.VersionNo = (SELECT MAX(iss2.VersionNo)         ");
			query.append("      FROM issue_shifting_order iss2       ");
			query.append("       WHERE iss.DocumentName = iss2.DocumentName)      ");
			if (Validator.isNotNull(dateFrom) && dateFrom.trim().length() > 0) {				
				query.append(" and temp.ShipDateFrom >= concat(date('" + dateFrom + "'), ' 00:00:00') ");
			}
			if (Validator.isNotNull(dateTo) && dateTo.trim().length() > 0) {				
				query.append(" and temp.ShipDateFrom <= concat(date('" + dateTo + "'), ' 23:59:59') ");
			}			
			if (maritimeCode != null) {
				query.append(" and temp.MaritimeCode = " + maritimeCode);
			}
			query.append(" WHERE 1=1           ");
			query.append(" and temp.DocumentTypeCode in ('12')           ");
			query.append(" Group by MaritimeCode, DocumentTypeCode order by LENGTH(MaritimeCode), MaritimeCode           ");
			query.append(" ) HosoChuyenCangVao           ");
			query.append(" ON HosoChuyenCangVao.MaritimeCode = dm_maritime.MaritimeCode           ");
			query.append("            ");
			
			query.append(" LEFT JOIN            ");
			query.append(" (           ");
			query.append(" select temp.MaritimeCode, DocumentTypeCode, count(temp.DocumentName) as total from temp_document temp            ");
			query.append("    inner join issue_shifting_order  iss         ");
			query.append("    on iss.DocumentName = temp.DocumentName        ");
			query.append("    and iss.DocumentYear  = temp.DocumentYear         ");
			query.append("    and   iss.StampStatus = 2 and iss.AttachedFile > 0        ");
			query.append(" and iss.requestState in (4)           ");
			query.append(" and temp.documentStatusCode in (19)           ");
			query.append("    and iss.VersionNo = (SELECT MAX(iss2.VersionNo)         ");
			query.append("      FROM issue_shifting_order iss2       ");
			query.append("       WHERE iss.DocumentName = iss2.DocumentName)      ");
			if (Validator.isNotNull(dateFrom) && dateFrom.trim().length() > 0) {				
				query.append(" and temp.ShipDateFrom >= concat(date('" + dateFrom + "'), ' 00:00:00') ");
			}
			if (Validator.isNotNull(dateTo) && dateTo.trim().length() > 0) {				
				query.append(" and temp.ShipDateFrom <= concat(date('" + dateTo + "'), ' 23:59:59') ");
			}
			if (maritimeCode != null) {
				query.append(" and temp.MaritimeCode = " + maritimeCode);
			}
			query.append(" WHERE 1=1           ");
			query.append(" and temp.DocumentTypeCode in ('10')           ");
			query.append(" Group by MaritimeCode, DocumentTypeCode order by LENGTH(MaritimeCode), MaritimeCode           ");
			query.append(" ) HosoVaoCangDauKhi           ");
			query.append(" ON HosoVaoCangDauKhi.MaritimeCode = dm_maritime.MaritimeCode           ");
			query.append("            ");
			
			query.append(" LEFT JOIN            ");
			query.append(" (           ");
			query.append(" select temp.MaritimeCode, DocumentTypeCode, count(temp.DocumentName) as total from temp_document temp            ");
			query.append("    inner join issue_shifting_order  iss         ");
			query.append("    on iss.DocumentName = temp.DocumentName        ");
			query.append("    and iss.DocumentYear  = temp.DocumentYear         ");
			query.append("    and   iss.StampStatus = 2 and iss.AttachedFile > 0        ");
			query.append(" and iss.requestState in (4)           ");
			query.append(" and temp.documentStatusCode in (19)           ");
			query.append("    and iss.VersionNo = (SELECT MAX(iss2.VersionNo)         ");
			query.append("      FROM issue_shifting_order iss2       ");
			query.append("       WHERE iss.DocumentName = iss2.DocumentName)      ");
			if (Validator.isNotNull(dateFrom) && dateFrom.trim().length() > 0) {				
				query.append(" and temp.ShipDateFrom >= concat(date('" + dateFrom + "'), ' 00:00:00') ");
			}
			if (Validator.isNotNull(dateTo) && dateTo.trim().length() > 0) {				
				query.append(" and temp.ShipDateFrom <= concat(date('" + dateTo + "'), ' 23:59:59') ");
			}
			if (maritimeCode != null) {
				query.append(" and temp.MaritimeCode = " + maritimeCode);
			}
			query.append(" WHERE 1=1           ");
			query.append(" and temp.DocumentTypeCode in ('8')           ");
			query.append(" Group by MaritimeCode, DocumentTypeCode order by LENGTH(MaritimeCode), MaritimeCode           ");
			query.append(" ) HosoNhapCanhDauKhi           ");
			query.append(" ON HosoNhapCanhDauKhi.MaritimeCode = dm_maritime.MaritimeCode           ");
			query.append("            ");
			
			query.append(" LEFT JOIN            ");
			query.append(" (           ");
			query.append(" select temp.MaritimeCode, DocumentTypeCode, count(temp.DocumentName) as total from temp_document temp            ");
			query.append("    inner join issue_shifting_order  iss         ");
			query.append("    on iss.DocumentName = temp.DocumentName        ");
			query.append("    and iss.DocumentYear  = temp.DocumentYear         ");
			query.append("    and   iss.StampStatus = 2 and iss.AttachedFile > 0        ");
			query.append(" and iss.requestState in (4)           ");
			query.append(" and temp.documentStatusCode in (19)           ");
			query.append("    and iss.VersionNo = (SELECT MAX(iss2.VersionNo)         ");
			query.append("      FROM issue_shifting_order iss2       ");
			query.append("       WHERE iss.DocumentName = iss2.DocumentName)      ");
			if (Validator.isNotNull(dateFrom) && dateFrom.trim().length() > 0) {				
				query.append(" and temp.ShipDateFrom >= concat(date('" + dateFrom + "'), ' 00:00:00') ");
			}
			if (Validator.isNotNull(dateTo) && dateTo.trim().length() > 0) {				
				query.append(" and temp.ShipDateFrom <= concat(date('" + dateTo + "'), ' 23:59:59') ");
			}
			if (maritimeCode != null) {
				query.append(" and temp.MaritimeCode = " + maritimeCode);
			}
			query.append(" WHERE 1=1           ");
			query.append(" and temp.DocumentTypeCode in ('16')           ");
			query.append(" Group by MaritimeCode, DocumentTypeCode order by LENGTH(MaritimeCode), MaritimeCode           ");
			query.append(" ) HosoVaoCangTND           ");
			query.append(" ON HosoVaoCangTND.MaritimeCode = dm_maritime.MaritimeCode           ");
			query.append("            ");
			
			query.append(" LEFT JOIN            ");
			query.append(" (           ");
			query.append(" select temp.MaritimeCode, DocumentTypeCode, count(temp.DocumentName) as total from temp_document temp            ");
			query.append("    inner join issue_port_clearance  iss         ");
			query.append("    on iss.DocumentName = temp.DocumentName        ");
			query.append("    and iss.DocumentYear  = temp.DocumentYear         ");
			query.append("    and   iss.StampStatus = 2 and iss.AttachedFile > 0        ");
			query.append(" and iss.requestState in (4)           ");
			query.append(" and temp.documentStatusCode in (19)           ");
			query.append("    and iss.VersionNo = (SELECT MAX(iss2.VersionNo)         ");
			query.append("      FROM issue_port_clearance iss2       ");
			query.append("       WHERE iss.DocumentName = iss2.DocumentName)      ");
			if (Validator.isNotNull(dateFrom) && dateFrom.trim().length() > 0) {				
				query.append(" and iss.SignDate >= concat(date('" + dateFrom + "'), ' 00:00:00') ");
			}
			if (Validator.isNotNull(dateTo) && dateTo.trim().length() > 0) {				
				query.append(" and iss.SignDate <= concat(date('" + dateTo + "'), ' 23:59:59') ");
			}
			if (maritimeCode != null) {
				query.append(" and temp.MaritimeCode = " + maritimeCode);
			}
			query.append(" WHERE 1=1           ");
			query.append(" and temp.DocumentTypeCode in ('XC')           ");
			query.append(" Group by MaritimeCode, DocumentTypeCode order by LENGTH(MaritimeCode), MaritimeCode           ");
			query.append(" ) HosoXuatCanh           ");
			query.append(" ON HosoXuatCanh.MaritimeCode = dm_maritime.MaritimeCode           ");
			query.append("            ");
			
			query.append(" LEFT JOIN            ");
			query.append(" (           ");
			query.append(" select temp.MaritimeCode, DocumentTypeCode, count(temp.DocumentName) as total from temp_document temp            ");
			query.append("    inner join issue_port_clearance  iss         ");
			query.append("    on iss.DocumentName = temp.DocumentName        ");
			query.append("    and iss.DocumentYear  = temp.DocumentYear         ");
			query.append("    and   iss.StampStatus = 2 and iss.AttachedFile > 0        ");
			query.append(" and iss.requestState in (4)           ");
			query.append(" and temp.documentStatusCode in (19)           ");
			query.append("    and iss.VersionNo = (SELECT MAX(iss2.VersionNo)         ");
			query.append("      FROM issue_port_clearance iss2       ");
			query.append("       WHERE iss.DocumentName = iss2.DocumentName)      ");
			if (Validator.isNotNull(dateFrom) && dateFrom.trim().length() > 0) {				
				query.append(" and iss.SignDate >= concat(date('" + dateFrom + "'), ' 00:00:00') ");
			}
			if (Validator.isNotNull(dateTo) && dateTo.trim().length() > 0) {				
				query.append(" and iss.SignDate <= concat(date('" + dateTo + "'), ' 23:59:59') ");
			}
			if (maritimeCode != null) {
				query.append(" and temp.MaritimeCode = " + maritimeCode);
			}
			query.append(" WHERE 1=1           ");
			query.append(" and temp.DocumentTypeCode in ('5')           ");
			query.append(" Group by MaritimeCode, DocumentTypeCode order by LENGTH(MaritimeCode), MaritimeCode           ");
			query.append(" ) HosoTauRoiCang           ");
			query.append(" ON HosoTauRoiCang.MaritimeCode = dm_maritime.MaritimeCode           ");
			query.append("            ");
			
			query.append(" LEFT JOIN            ");
			query.append(" (           ");
			query.append(" select temp.MaritimeCode, DocumentTypeCode, count(temp.DocumentName) as total from temp_document temp            ");
			query.append("    inner join issue_port_clearance  iss         ");
			query.append("    on iss.DocumentName = temp.DocumentName        ");
			query.append("    and iss.DocumentYear  = temp.DocumentYear         ");
			query.append("    and   iss.StampStatus = 2 and iss.AttachedFile > 0        ");
			query.append(" and iss.requestState in (4)           ");
			query.append(" and temp.documentStatusCode in (19)           ");
			query.append("    and iss.VersionNo = (SELECT MAX(iss2.VersionNo)         ");
			query.append("      FROM issue_port_clearance iss2       ");
			query.append("       WHERE iss.DocumentName = iss2.DocumentName)      ");
			if (Validator.isNotNull(dateFrom) && dateFrom.trim().length() > 0) {				
				query.append(" and iss.SignDate >= concat(date('" + dateFrom + "'), ' 00:00:00') ");
			}
			if (Validator.isNotNull(dateTo) && dateTo.trim().length() > 0) {				
				query.append(" and iss.SignDate <= concat(date('" + dateTo + "'), ' 23:59:59') ");
			}
			if (maritimeCode != null) {
				query.append(" and temp.MaritimeCode = " + maritimeCode);
			}
			query.append(" WHERE 1=1           ");
			query.append(" and temp.DocumentTypeCode in ('13')           ");
			query.append(" Group by MaritimeCode, DocumentTypeCode order by LENGTH(MaritimeCode), MaritimeCode           ");
			query.append(" ) HosoChuyenCangRoi           ");
			query.append(" ON HosoChuyenCangRoi.MaritimeCode = dm_maritime.MaritimeCode           ");
			query.append("            ");
			
			query.append(" LEFT JOIN            ");
			query.append(" (           ");
			query.append(" select temp.MaritimeCode, DocumentTypeCode, count(temp.DocumentName) as total from temp_document temp            ");
			query.append("    inner join issue_port_clearance  iss         ");
			query.append("    on iss.DocumentName = temp.DocumentName        ");
			query.append("    and iss.DocumentYear  = temp.DocumentYear         ");
			query.append("    and   iss.StampStatus = 2 and iss.AttachedFile > 0        ");
			query.append(" and iss.requestState in (4)           ");
			query.append(" and temp.documentStatusCode in (19)           ");
			query.append("    and iss.VersionNo = (SELECT MAX(iss2.VersionNo)         ");
			query.append("      FROM issue_port_clearance iss2       ");
			query.append("       WHERE iss.DocumentName = iss2.DocumentName)      ");
			if (Validator.isNotNull(dateFrom) && dateFrom.trim().length() > 0) {				
				query.append(" and iss.SignDate >= concat(date('" + dateFrom + "'), ' 00:00:00') ");
			}
			if (Validator.isNotNull(dateTo) && dateTo.trim().length() > 0) {				
				query.append(" and iss.SignDate <= concat(date('" + dateTo + "'), ' 23:59:59') ");
			}
			if (maritimeCode != null) {
				query.append(" and temp.MaritimeCode = " + maritimeCode);
			}
			query.append(" WHERE 1=1           ");
			query.append(" and temp.DocumentTypeCode in ('11')           ");
			query.append(" Group by MaritimeCode, DocumentTypeCode order by LENGTH(MaritimeCode), MaritimeCode           ");
			query.append(" ) HosoRoiCangDauKhi           ");
			query.append(" ON HosoRoiCangDauKhi.MaritimeCode = dm_maritime.MaritimeCode           ");
			query.append("            ");
			
			query.append(" LEFT JOIN            ");
			query.append(" (           ");
			query.append(" select temp.MaritimeCode, DocumentTypeCode, count(temp.DocumentName) as total from temp_document temp            ");
			query.append("    inner join issue_port_clearance  iss         ");
			query.append("    on iss.DocumentName = temp.DocumentName        ");
			query.append("    and iss.DocumentYear  = temp.DocumentYear         ");
			query.append("    and   iss.StampStatus = 2 and iss.AttachedFile > 0        ");
			query.append(" and iss.requestState in (4)           ");
			query.append(" and temp.documentStatusCode in (19)           ");
			query.append("    and iss.VersionNo = (SELECT MAX(iss2.VersionNo)         ");
			query.append("      FROM issue_port_clearance iss2       ");
			query.append("       WHERE iss.DocumentName = iss2.DocumentName)      ");
			if (Validator.isNotNull(dateFrom) && dateFrom.trim().length() > 0) {				
				query.append(" and iss.SignDate >= concat(date('" + dateFrom + "'), ' 00:00:00') ");
			}
			if (Validator.isNotNull(dateTo) && dateTo.trim().length() > 0) {				
				query.append(" and iss.SignDate <= concat(date('" + dateTo + "'), ' 23:59:59') ");
			}
			if (maritimeCode != null) {
				query.append(" and temp.MaritimeCode = " + maritimeCode);
			}
			query.append(" WHERE 1=1           ");
			query.append(" and temp.DocumentTypeCode in ('9')           ");
			query.append(" Group by MaritimeCode, DocumentTypeCode order by LENGTH(MaritimeCode), MaritimeCode           ");
			query.append(" ) HosoXuatCanhDauKhi           ");
			query.append(" ON HosoXuatCanhDauKhi.MaritimeCode = dm_maritime.MaritimeCode           ");
			query.append("            ");
			
			query.append(" LEFT JOIN            ");
			query.append(" (           ");
			query.append(" select temp.MaritimeCode, DocumentTypeCode, count(temp.DocumentName) as total from temp_document temp            ");
			query.append("    inner join issue_port_clearance  iss         ");
			query.append("    on iss.DocumentName = temp.DocumentName        ");
			query.append("    and iss.DocumentYear  = temp.DocumentYear         ");
			query.append("    and   iss.StampStatus = 2 and iss.AttachedFile > 0        ");
			query.append(" and iss.requestState in (4)           ");
			query.append(" and temp.documentStatusCode in (19)           ");
			query.append("    and iss.VersionNo = (SELECT MAX(iss2.VersionNo)         ");
			query.append("      FROM issue_port_clearance iss2       ");
			query.append("       WHERE iss.DocumentName = iss2.DocumentName)      ");
			if (Validator.isNotNull(dateFrom) && dateFrom.trim().length() > 0) {				
				query.append(" and iss.SignDate >= concat(date('" + dateFrom + "'), ' 00:00:00') ");
			}
			if (Validator.isNotNull(dateTo) && dateTo.trim().length() > 0) {				
				query.append(" and iss.SignDate <= concat(date('" + dateTo + "'), ' 23:59:59') ");
			}
			if (maritimeCode != null) {
				query.append(" and temp.MaritimeCode = " + maritimeCode);
			}
			query.append(" WHERE 1=1           ");
			query.append(" and temp.DocumentTypeCode in ('17')           ");
			query.append(" Group by MaritimeCode, DocumentTypeCode order by LENGTH(MaritimeCode), MaritimeCode           ");
			query.append(" ) HosoRoiCangTND           ");
			query.append(" ON HosoRoiCangTND.MaritimeCode = dm_maritime.MaritimeCode           ");
			query.append("            ");
			
			
			query.append(" LEFT JOIN            ");
			query.append(" (           ");
			query.append(" select temp.MaritimeCode, DocumentTypeCode, count(temp.DocumentName) as total from temp_document temp            ");
			query.append("    inner join issue_port_clearance  iss         ");
			query.append("    on iss.DocumentName = temp.DocumentName        ");
			query.append("    and iss.DocumentYear  = temp.DocumentYear         ");
			query.append("    and   iss.StampStatus = 2 and iss.AttachedFile > 0        ");
			query.append(" and iss.requestState in (4)           ");
			query.append(" and temp.documentStatusCode in (19)           ");
			query.append("    and iss.VersionNo = (SELECT MAX(iss2.VersionNo)         ");
			query.append("      FROM issue_port_clearance iss2       ");
			query.append("       WHERE iss.DocumentName = iss2.DocumentName)      ");
			if (Validator.isNotNull(dateFrom) && dateFrom.trim().length() > 0) {				
				query.append(" and temp.ShipDateFrom >= concat(date('" + dateFrom + "'), ' 00:00:00') ");
			}
			if (Validator.isNotNull(dateTo) && dateTo.trim().length() > 0) {				
				query.append(" and temp.ShipDateFrom <= concat(date('" + dateTo + "'), ' 23:59:59') ");
			}
			if (maritimeCode != null) {
				query.append(" and temp.MaritimeCode = " + maritimeCode);
			}
			query.append(" WHERE 1=1           ");
			query.append(" and temp.DocumentTypeCode in ('14')           ");
			query.append(" Group by MaritimeCode, DocumentTypeCode order by LENGTH(MaritimeCode), MaritimeCode           ");
			query.append(" ) HosoNhapCanhVNCPC           ");
			query.append(" ON HosoNhapCanhVNCPC.MaritimeCode = dm_maritime.MaritimeCode           ");
			query.append("            ");
						
			query.append(" LEFT JOIN            ");
			query.append(" (           ");
			query.append(" select temp.MaritimeCode, DocumentTypeCode, count(temp.DocumentName) as total from temp_document temp            ");
			query.append("    inner join issue_port_clearance  iss         ");
			query.append("    on iss.DocumentName = temp.DocumentName        ");
			query.append("    and iss.DocumentYear  = temp.DocumentYear         ");
			query.append("    and   iss.StampStatus = 2 and iss.AttachedFile > 0        ");
			query.append(" and iss.requestState in (4)           ");
			query.append(" and temp.documentStatusCode in (19)           ");
			query.append("    and iss.VersionNo = (SELECT MAX(iss2.VersionNo)         ");
			query.append("      FROM issue_port_clearance iss2       ");
			query.append("       WHERE iss.DocumentName = iss2.DocumentName)      ");
			if (Validator.isNotNull(dateFrom) && dateFrom.trim().length() > 0) {				
				query.append(" and iss.SignDate >= concat(date('" + dateFrom + "'), ' 00:00:00') ");
			}
			if (Validator.isNotNull(dateTo) && dateTo.trim().length() > 0) {				
				query.append(" and iss.SignDate <= concat(date('" + dateTo + "'), ' 23:59:59') ");
			}
			if (maritimeCode != null) {
				query.append(" and temp.MaritimeCode = " + maritimeCode);
			}
			query.append(" WHERE 1=1           ");
			query.append(" and temp.DocumentTypeCode in ('15')           ");
			query.append(" Group by MaritimeCode, DocumentTypeCode order by LENGTH(MaritimeCode), MaritimeCode           ");
			query.append(" ) HosoXuatCanhVNCPC           ");
			query.append(" ON HosoXuatCanhVNCPC.MaritimeCode = dm_maritime.MaritimeCode           ");
			query.append("            ");
			
			query.append(" LEFT JOIN            ");
			query.append(" (           ");
			query.append(" select temp.MaritimeCode, DocumentTypeCode, count(temp.DocumentName) as total from temp_document temp            ");
			query.append("    inner join issue_permission_for_transit  iss         ");
			query.append("    on iss.DocumentName = temp.DocumentName        ");
			query.append("    and iss.DocumentYear  = temp.DocumentYear         ");
			query.append("    and   iss.StampStatus = 2 and iss.AttachedFile > 0        ");
			query.append(" and iss.requestState in (4)           ");
			query.append(" and temp.documentStatusCode in (19)           ");
			query.append("    and iss.VersionNo = (SELECT MAX(iss2.VersionNo)         ");
			query.append("      FROM issue_permission_for_transit iss2       ");
			query.append("       WHERE iss.DocumentName = iss2.DocumentName)      ");
			if (Validator.isNotNull(dateFrom) && dateFrom.trim().length() > 0) {				
				query.append(" and iss.SignDate >= concat(date('" + dateFrom + "'), ' 00:00:00') ");
			}
			if (Validator.isNotNull(dateTo) && dateTo.trim().length() > 0) {				
				query.append(" and iss.SignDate <= concat(date('" + dateTo + "'), ' 23:59:59') ");
			}
			if (maritimeCode != null) {
				query.append(" and temp.MaritimeCode = " + maritimeCode);
			}
			query.append(" WHERE 1=1           ");
			query.append(" and temp.DocumentTypeCode in ('QC')           ");
			query.append(" Group by MaritimeCode, DocumentTypeCode order by LENGTH(MaritimeCode), MaritimeCode           ");
			query.append(" ) HosoQuaCanh           ");
			query.append(" ON HosoQuaCanh.MaritimeCode = dm_maritime.MaritimeCode           ");
			query.append("            ");
			
			
			
			//// Ho so PHE DUYET HOAN THANH (khong ky so)
			
			
			query.append(" LEFT JOIN            ");
			query.append(" (           ");
			query.append(" select temp.MaritimeCode, DocumentTypeCode, count(temp.DocumentName) as total from temp_document temp            ");
			query.append("    inner join issue_shifting_order  iss         ");
			query.append("    on iss.DocumentName = temp.DocumentName        ");
			query.append("    and iss.DocumentYear  = temp.DocumentYear         ");
			query.append("    and   iss.StampStatus <> 2         ");
			query.append(" and iss.requestState in (4)           ");
			query.append(" and temp.documentStatusCode in (19)           ");
			query.append("    and iss.VersionNo = (SELECT MAX(iss2.VersionNo)         ");
			query.append("      FROM issue_shifting_order iss2       ");
			query.append("       WHERE iss.DocumentName = iss2.DocumentName)      ");
			if (Validator.isNotNull(dateFrom) && dateFrom.trim().length() > 0) {				
				query.append(" and iss.ApprovalDate >= concat(date('" + dateFrom + "'), ' 00:00:00') ");
			}
			if (Validator.isNotNull(dateTo) && dateTo.trim().length() > 0) {				
				query.append(" and iss.ApprovalDate <= concat(date('" + dateTo + "'), ' 23:59:59') ");
			}
			if (maritimeCode != null) {
				query.append(" and temp.MaritimeCode = " + maritimeCode);
			}
			query.append(" WHERE 1=1           ");			
			query.append(" and temp.DocumentTypeCode in ('NC')           ");
			query.append(" Group by MaritimeCode, DocumentTypeCode order by LENGTH(MaritimeCode), MaritimeCode           ");
			query.append(" ) HosoNhapCanh_DUYET           ");
			query.append(" ON HosoNhapCanh_DUYET.MaritimeCode = dm_maritime.MaritimeCode           ");
			query.append("            ");
			query.append(" LEFT JOIN            ");
			query.append(" (           ");
			query.append(" select temp.MaritimeCode, DocumentTypeCode, count(temp.DocumentName) as total from temp_document temp            ");
			query.append("    inner join issue_shifting_order  iss         ");
			query.append("    on iss.DocumentName = temp.DocumentName        ");
			query.append("    and iss.DocumentYear  = temp.DocumentYear         ");
			query.append("    and   iss.StampStatus <> 2        ");
			query.append(" and iss.requestState in (4)           ");
			query.append(" and temp.documentStatusCode in (19)           ");
			query.append("    and iss.VersionNo = (SELECT MAX(iss2.VersionNo)         ");
			query.append("      FROM issue_shifting_order iss2       ");
			query.append("       WHERE iss.DocumentName = iss2.DocumentName)      ");
			if (Validator.isNotNull(dateFrom) && dateFrom.trim().length() > 0) {				
				query.append(" and iss.ApprovalDate >= concat(date('" + dateFrom + "'), ' 00:00:00') ");
			}
			if (Validator.isNotNull(dateTo) && dateTo.trim().length() > 0) {				
				query.append(" and iss.ApprovalDate <= concat(date('" + dateTo + "'), ' 23:59:59') ");
			}
			if (maritimeCode != null) {
				query.append(" and temp.MaritimeCode = " + maritimeCode);
			}
			query.append(" WHERE 1=1           ");
			query.append(" and temp.DocumentTypeCode in ('4')           ");
			query.append(" Group by MaritimeCode, DocumentTypeCode order by LENGTH(MaritimeCode), MaritimeCode           ");
			query.append(" ) HosoTauVaoCang_DUYET           ");
			query.append(" ON HosoTauVaoCang_DUYET.MaritimeCode = dm_maritime.MaritimeCode           ");
			query.append("            ");
			query.append(" LEFT JOIN            ");
			query.append(" (           ");
			query.append(" select temp.MaritimeCode, DocumentTypeCode, count(temp.DocumentName) as total from temp_document temp            ");
			query.append("    inner join issue_shifting_order  iss         ");
			query.append("    on iss.DocumentName = temp.DocumentName        ");
			query.append("    and iss.DocumentYear  = temp.DocumentYear         ");
			query.append("    and   iss.StampStatus <> 2        ");
			query.append(" and iss.requestState in (4)           ");
			query.append(" and temp.documentStatusCode in (19)           ");
			query.append("    and iss.VersionNo = (SELECT MAX(iss2.VersionNo)         ");
			query.append("      FROM issue_shifting_order iss2       ");
			query.append("       WHERE iss.DocumentName = iss2.DocumentName)      ");
			if (Validator.isNotNull(dateFrom) && dateFrom.trim().length() > 0) {				
				query.append(" and iss.ApprovalDate >= concat(date('" + dateFrom + "'), ' 00:00:00') ");
			}
			if (Validator.isNotNull(dateTo) && dateTo.trim().length() > 0) {				
				query.append(" and iss.ApprovalDate <= concat(date('" + dateTo + "'), ' 23:59:59') ");
			}
			if (maritimeCode != null) {
				query.append(" and temp.MaritimeCode = " + maritimeCode);
			}
			query.append(" WHERE 1=1           ");
			query.append(" and temp.DocumentTypeCode in ('12')           ");
			query.append(" Group by MaritimeCode, DocumentTypeCode order by LENGTH(MaritimeCode), MaritimeCode           ");
			query.append(" ) HosoChuyenCangVao_DUYET           ");
			query.append(" ON HosoChuyenCangVao_DUYET.MaritimeCode = dm_maritime.MaritimeCode           ");
			query.append("            ");
			query.append(" LEFT JOIN            ");
			query.append(" (           ");
			query.append(" select temp.MaritimeCode, DocumentTypeCode, count(temp.DocumentName) as total from temp_document temp            ");
			query.append("    inner join issue_shifting_order  iss         ");
			query.append("    on iss.DocumentName = temp.DocumentName        ");
			query.append("    and iss.DocumentYear  = temp.DocumentYear         ");
			query.append("    and   iss.StampStatus <> 2        ");
			query.append(" and iss.requestState in (4)           ");
			query.append(" and temp.documentStatusCode in (19)           ");
			query.append("    and iss.VersionNo = (SELECT MAX(iss2.VersionNo)         ");
			query.append("      FROM issue_shifting_order iss2       ");
			query.append("       WHERE iss.DocumentName = iss2.DocumentName)      ");
			if (Validator.isNotNull(dateFrom) && dateFrom.trim().length() > 0) {				
				query.append(" and iss.ApprovalDate >= concat(date('" + dateFrom + "'), ' 00:00:00') ");
			}
			if (Validator.isNotNull(dateTo) && dateTo.trim().length() > 0) {				
				query.append(" and iss.ApprovalDate <= concat(date('" + dateTo + "'), ' 23:59:59') ");
			}
			if (maritimeCode != null) {
				query.append(" and temp.MaritimeCode = " + maritimeCode);
			}
			query.append(" WHERE 1=1           ");
			query.append(" and temp.DocumentTypeCode in ('10')           ");
			query.append(" Group by MaritimeCode, DocumentTypeCode order by LENGTH(MaritimeCode), MaritimeCode           ");
			query.append(" ) HosoVaoCangDauKhi_DUYET           ");
			query.append(" ON HosoVaoCangDauKhi_DUYET.MaritimeCode = dm_maritime.MaritimeCode           ");
			query.append("            ");
			query.append(" LEFT JOIN            ");
			query.append(" (           ");
			query.append(" select temp.MaritimeCode, DocumentTypeCode, count(temp.DocumentName) as total from temp_document temp            ");
			query.append("    inner join issue_shifting_order  iss         ");
			query.append("    on iss.DocumentName = temp.DocumentName        ");
			query.append("    and iss.DocumentYear  = temp.DocumentYear         ");
			query.append("    and   iss.StampStatus <> 2        ");
			query.append(" and iss.requestState in (4)           ");
			query.append(" and temp.documentStatusCode in (19)           ");
			query.append("    and iss.VersionNo = (SELECT MAX(iss2.VersionNo)         ");
			query.append("      FROM issue_shifting_order iss2       ");
			query.append("       WHERE iss.DocumentName = iss2.DocumentName)      ");
			if (Validator.isNotNull(dateFrom) && dateFrom.trim().length() > 0) {				
				query.append(" and iss.ApprovalDate >= concat(date('" + dateFrom + "'), ' 00:00:00') ");
			}
			if (Validator.isNotNull(dateTo) && dateTo.trim().length() > 0) {				
				query.append(" and iss.ApprovalDate <= concat(date('" + dateTo + "'), ' 23:59:59') ");
			}
			if (maritimeCode != null) {
				query.append(" and temp.MaritimeCode = " + maritimeCode);
			}
			query.append(" WHERE 1=1           ");
			query.append(" and temp.DocumentTypeCode in ('8')           ");
			query.append(" Group by MaritimeCode, DocumentTypeCode order by LENGTH(MaritimeCode), MaritimeCode           ");
			query.append(" ) HosoNhapCanhDauKhi_DUYET           ");
			query.append(" ON HosoNhapCanhDauKhi_DUYET.MaritimeCode = dm_maritime.MaritimeCode           ");
			query.append("            ");
			query.append(" LEFT JOIN            ");
			query.append(" (           ");
			query.append(" select temp.MaritimeCode, DocumentTypeCode, count(temp.DocumentName) as total from temp_document temp            ");
			query.append("    inner join issue_shifting_order  iss         ");
			query.append("    on iss.DocumentName = temp.DocumentName        ");
			query.append("    and iss.DocumentYear  = temp.DocumentYear         ");
			query.append("    and   iss.StampStatus <> 2        ");
			query.append(" and iss.requestState in (4)           ");
			query.append(" and temp.documentStatusCode in (19)           ");
			query.append("    and iss.VersionNo = (SELECT MAX(iss2.VersionNo)         ");
			query.append("      FROM issue_shifting_order iss2       ");
			query.append("       WHERE iss.DocumentName = iss2.DocumentName)      ");
			if (Validator.isNotNull(dateFrom) && dateFrom.trim().length() > 0) {				
				query.append(" and iss.ApprovalDate >= concat(date('" + dateFrom + "'), ' 00:00:00') ");
			}
			if (Validator.isNotNull(dateTo) && dateTo.trim().length() > 0) {				
				query.append(" and iss.ApprovalDate <= concat(date('" + dateTo + "'), ' 23:59:59') ");
			}
			if (maritimeCode != null) {
				query.append(" and temp.MaritimeCode = " + maritimeCode);
			}
			query.append(" WHERE 1=1           ");
			query.append(" and temp.DocumentTypeCode in ('16')           ");
			query.append(" Group by MaritimeCode, DocumentTypeCode order by LENGTH(MaritimeCode), MaritimeCode           ");
			query.append(" ) HosoVaoCangTND_DUYET           ");
			query.append(" ON HosoVaoCangTND_DUYET.MaritimeCode = dm_maritime.MaritimeCode           ");
			query.append("            ");
			query.append(" LEFT JOIN            ");
			query.append(" (           ");
			query.append(" select temp.MaritimeCode, DocumentTypeCode, count(temp.DocumentName) as total from temp_document temp            ");
			query.append("    inner join issue_port_clearance  iss         ");
			query.append("    on iss.DocumentName = temp.DocumentName        ");
			query.append("    and iss.DocumentYear  = temp.DocumentYear         ");
			query.append("    and   iss.StampStatus <> 2        ");
			query.append(" and iss.requestState in (4)           ");
			query.append(" and temp.documentStatusCode in (19)           ");
			query.append("    and iss.VersionNo = (SELECT MAX(iss2.VersionNo)         ");
			query.append("      FROM issue_port_clearance iss2       ");
			query.append("       WHERE iss.DocumentName = iss2.DocumentName)      ");
			if (Validator.isNotNull(dateFrom) && dateFrom.trim().length() > 0) {				
				query.append(" and iss.ApprovalDate >= concat(date('" + dateFrom + "'), ' 00:00:00') ");
			}
			if (Validator.isNotNull(dateTo) && dateTo.trim().length() > 0) {				
				query.append(" and iss.ApprovalDate <= concat(date('" + dateTo + "'), ' 23:59:59') ");
			}
			if (maritimeCode != null) {
				query.append(" and temp.MaritimeCode = " + maritimeCode);
			}
			query.append(" WHERE 1=1           ");
			query.append(" and temp.DocumentTypeCode in ('XC')           ");
			query.append(" Group by MaritimeCode, DocumentTypeCode order by LENGTH(MaritimeCode), MaritimeCode           ");
			query.append(" ) HosoXuatCanh_DUYET           ");
			query.append(" ON HosoXuatCanh_DUYET.MaritimeCode = dm_maritime.MaritimeCode           ");
			query.append("            ");
			query.append(" LEFT JOIN            ");
			query.append(" (           ");
			query.append(" select temp.MaritimeCode, DocumentTypeCode, count(temp.DocumentName) as total from temp_document temp            ");
			query.append("    inner join issue_port_clearance  iss         ");
			query.append("    on iss.DocumentName = temp.DocumentName        ");
			query.append("    and iss.DocumentYear  = temp.DocumentYear         ");
			query.append("    and   iss.StampStatus <> 2        ");
			query.append(" and iss.requestState in (4)           ");
			query.append(" and temp.documentStatusCode in (19)           ");
			query.append("    and iss.VersionNo = (SELECT MAX(iss2.VersionNo)         ");
			query.append("      FROM issue_port_clearance iss2       ");
			query.append("       WHERE iss.DocumentName = iss2.DocumentName)      ");
			if (Validator.isNotNull(dateFrom) && dateFrom.trim().length() > 0) {				
				query.append(" and iss.ApprovalDate >= concat(date('" + dateFrom + "'), ' 00:00:00') ");
			}
			if (Validator.isNotNull(dateTo) && dateTo.trim().length() > 0) {				
				query.append(" and iss.ApprovalDate <= concat(date('" + dateTo + "'), ' 23:59:59') ");
			}
			if (maritimeCode != null) {
				query.append(" and temp.MaritimeCode = " + maritimeCode);
			}
			query.append(" WHERE 1=1           ");
			query.append(" and temp.DocumentTypeCode in ('5')           ");
			query.append(" Group by MaritimeCode, DocumentTypeCode order by LENGTH(MaritimeCode), MaritimeCode           ");
			query.append(" ) HosoTauRoiCang_DUYET           ");
			query.append(" ON HosoTauRoiCang_DUYET.MaritimeCode = dm_maritime.MaritimeCode           ");
			query.append("            ");
			query.append(" LEFT JOIN            ");
			query.append(" (           ");
			query.append(" select temp.MaritimeCode, DocumentTypeCode, count(temp.DocumentName) as total from temp_document temp            ");
			query.append("    inner join issue_port_clearance  iss         ");
			query.append("    on iss.DocumentName = temp.DocumentName        ");
			query.append("    and iss.DocumentYear  = temp.DocumentYear         ");
			query.append("    and   iss.StampStatus <> 2        ");
			query.append(" and iss.requestState in (4)           ");
			query.append(" and temp.documentStatusCode in (19)           ");
			query.append("    and iss.VersionNo = (SELECT MAX(iss2.VersionNo)         ");
			query.append("      FROM issue_port_clearance iss2       ");
			query.append("       WHERE iss.DocumentName = iss2.DocumentName)      ");
			if (Validator.isNotNull(dateFrom) && dateFrom.trim().length() > 0) {				
				query.append(" and iss.ApprovalDate >= concat(date('" + dateFrom + "'), ' 00:00:00') ");
			}
			if (Validator.isNotNull(dateTo) && dateTo.trim().length() > 0) {				
				query.append(" and iss.ApprovalDate <= concat(date('" + dateTo + "'), ' 23:59:59') ");
			}
			if (maritimeCode != null) {
				query.append(" and temp.MaritimeCode = " + maritimeCode);
			}
			query.append(" WHERE 1=1           ");
			query.append(" and temp.DocumentTypeCode in ('13')           ");
			query.append(" Group by MaritimeCode, DocumentTypeCode order by LENGTH(MaritimeCode), MaritimeCode           ");
			query.append(" ) HosoChuyenCangRoi_DUYET           ");
			query.append(" ON HosoChuyenCangRoi_DUYET.MaritimeCode = dm_maritime.MaritimeCode           ");
			query.append("            ");
			query.append(" LEFT JOIN            ");
			query.append(" (           ");
			query.append(" select temp.MaritimeCode, DocumentTypeCode, count(temp.DocumentName) as total from temp_document temp            ");
			query.append("    inner join issue_port_clearance  iss         ");
			query.append("    on iss.DocumentName = temp.DocumentName        ");
			query.append("    and iss.DocumentYear  = temp.DocumentYear         ");
			query.append("    and   iss.StampStatus <> 2        ");
			query.append(" and iss.requestState in (4)           ");
			query.append(" and temp.documentStatusCode in (19)           ");
			query.append("    and iss.VersionNo = (SELECT MAX(iss2.VersionNo)         ");
			query.append("      FROM issue_port_clearance iss2       ");
			query.append("       WHERE iss.DocumentName = iss2.DocumentName)      ");
			if (Validator.isNotNull(dateFrom) && dateFrom.trim().length() > 0) {				
				query.append(" and iss.ApprovalDate >= concat(date('" + dateFrom + "'), ' 00:00:00') ");
			}
			if (Validator.isNotNull(dateTo) && dateTo.trim().length() > 0) {				
				query.append(" and iss.ApprovalDate <= concat(date('" + dateTo + "'), ' 23:59:59') ");
			}
			if (maritimeCode != null) {
				query.append(" and temp.MaritimeCode = " + maritimeCode);
			}
			query.append(" WHERE 1=1           ");
			query.append(" and temp.DocumentTypeCode in ('11')           ");
			query.append(" Group by MaritimeCode, DocumentTypeCode order by LENGTH(MaritimeCode), MaritimeCode           ");
			query.append(" ) HosoRoiCangDauKhi_DUYET           ");
			query.append(" ON HosoRoiCangDauKhi_DUYET.MaritimeCode = dm_maritime.MaritimeCode           ");
			query.append("            ");
			query.append(" LEFT JOIN            ");
			query.append(" (           ");
			query.append(" select temp.MaritimeCode, DocumentTypeCode, count(temp.DocumentName) as total from temp_document temp            ");
			query.append("    inner join issue_port_clearance  iss         ");
			query.append("    on iss.DocumentName = temp.DocumentName        ");
			query.append("    and iss.DocumentYear  = temp.DocumentYear         ");
			query.append("    and   iss.StampStatus <> 2        ");
			query.append(" and iss.requestState in (4)           ");
			query.append(" and temp.documentStatusCode in (19)           ");
			query.append("    and iss.VersionNo = (SELECT MAX(iss2.VersionNo)         ");
			query.append("      FROM issue_port_clearance iss2       ");
			query.append("       WHERE iss.DocumentName = iss2.DocumentName)      ");
			if (Validator.isNotNull(dateFrom) && dateFrom.trim().length() > 0) {				
				query.append(" and iss.ApprovalDate >= concat(date('" + dateFrom + "'), ' 00:00:00') ");
			}
			if (Validator.isNotNull(dateTo) && dateTo.trim().length() > 0) {				
				query.append(" and iss.ApprovalDate <= concat(date('" + dateTo + "'), ' 23:59:59') ");
			}
			if (maritimeCode != null) {
				query.append(" and temp.MaritimeCode = " + maritimeCode);
			}
			query.append(" WHERE 1=1           ");
			query.append(" and temp.DocumentTypeCode in ('9')           ");
			query.append(" Group by MaritimeCode, DocumentTypeCode order by LENGTH(MaritimeCode), MaritimeCode           ");
			query.append(" ) HosoXuatCanhDauKhi_DUYET           ");
			query.append(" ON HosoXuatCanhDauKhi_DUYET.MaritimeCode = dm_maritime.MaritimeCode           ");
			query.append("            ");
			
			query.append(" LEFT JOIN            ");
			query.append(" (           ");
			query.append(" select temp.MaritimeCode, DocumentTypeCode, count(temp.DocumentName) as total from temp_document temp            ");
			query.append("    inner join issue_port_clearance  iss         ");
			query.append("    on iss.DocumentName = temp.DocumentName        ");
			query.append("    and iss.DocumentYear  = temp.DocumentYear         ");
			query.append("    and   iss.StampStatus <> 2        ");
			query.append(" and iss.requestState in (4)           ");
			query.append(" and temp.documentStatusCode in (19)           ");
			query.append("    and iss.VersionNo = (SELECT MAX(iss2.VersionNo)         ");
			query.append("      FROM issue_port_clearance iss2       ");
			query.append("       WHERE iss.DocumentName = iss2.DocumentName)      ");
			if (Validator.isNotNull(dateFrom) && dateFrom.trim().length() > 0) {				
				query.append(" and iss.ApprovalDate >= concat(date('" + dateFrom + "'), ' 00:00:00') ");
			}
			if (Validator.isNotNull(dateTo) && dateTo.trim().length() > 0) {				
				query.append(" and iss.ApprovalDate <= concat(date('" + dateTo + "'), ' 23:59:59') ");
			}
			if (maritimeCode != null) {
				query.append(" and temp.MaritimeCode = " + maritimeCode);
			}
			query.append(" WHERE 1=1           ");
			query.append(" and temp.DocumentTypeCode in ('17')           ");
			query.append(" Group by MaritimeCode, DocumentTypeCode order by LENGTH(MaritimeCode), MaritimeCode           ");
			query.append(" ) HosoRoiCangTND_DUYET           ");
			query.append(" ON HosoRoiCangTND_DUYET.MaritimeCode = dm_maritime.MaritimeCode           ");
			query.append("            ");
			
			query.append(" LEFT JOIN            ");
			query.append(" (           ");
			query.append(" select temp.MaritimeCode, DocumentTypeCode, count(temp.DocumentName) as total from temp_document temp            ");
			query.append("    inner join issue_port_clearance  iss         ");
			query.append("    on iss.DocumentName = temp.DocumentName        ");
			query.append("    and iss.DocumentYear  = temp.DocumentYear         ");
			query.append("    and   iss.StampStatus <> 2        ");
			query.append(" and iss.requestState in (4)           ");
			query.append(" and temp.documentStatusCode in (19)           ");
			query.append("    and iss.VersionNo = (SELECT MAX(iss2.VersionNo)         ");
			query.append("      FROM issue_port_clearance iss2       ");
			query.append("       WHERE iss.DocumentName = iss2.DocumentName)      ");
			if (Validator.isNotNull(dateFrom) && dateFrom.trim().length() > 0) {				
				query.append(" and iss.ApprovalDate >= concat(date('" + dateFrom + "'), ' 00:00:00') ");
			}
			if (Validator.isNotNull(dateTo) && dateTo.trim().length() > 0) {				
				query.append(" and iss.ApprovalDate <= concat(date('" + dateTo + "'), ' 23:59:59') ");
			}
			if (maritimeCode != null) {
				query.append(" and temp.MaritimeCode = " + maritimeCode);
			}
			query.append(" WHERE 1=1           ");
			query.append(" and temp.DocumentTypeCode in ('14')           ");
			query.append(" Group by MaritimeCode, DocumentTypeCode order by LENGTH(MaritimeCode), MaritimeCode           ");
			query.append(" ) HosoNhapCanhVNCPC_DUYET           ");
			query.append(" ON HosoNhapCanhVNCPC_DUYET.MaritimeCode = dm_maritime.MaritimeCode           ");
			query.append("            ");
			
			query.append(" LEFT JOIN            ");
			query.append(" (           ");
			query.append(" select temp.MaritimeCode, DocumentTypeCode, count(temp.DocumentName) as total from temp_document temp            ");
			query.append("    inner join issue_port_clearance  iss         ");
			query.append("    on iss.DocumentName = temp.DocumentName        ");
			query.append("    and iss.DocumentYear  = temp.DocumentYear         ");
			query.append("    and   iss.StampStatus <> 2        ");
			query.append(" and iss.requestState in (4)           ");
			query.append(" and temp.documentStatusCode in (19)           ");
			query.append("    and iss.VersionNo = (SELECT MAX(iss2.VersionNo)         ");
			query.append("      FROM issue_port_clearance iss2       ");
			query.append("       WHERE iss.DocumentName = iss2.DocumentName)      ");
			if (Validator.isNotNull(dateFrom) && dateFrom.trim().length() > 0) {				
				query.append(" and iss.ApprovalDate >= concat(date('" + dateFrom + "'), ' 00:00:00') ");
			}
			if (Validator.isNotNull(dateTo) && dateTo.trim().length() > 0) {				
				query.append(" and iss.ApprovalDate <= concat(date('" + dateTo + "'), ' 23:59:59') ");
			}
			if (maritimeCode != null) {
				query.append(" and temp.MaritimeCode = " + maritimeCode);
			}
			query.append(" WHERE 1=1           ");
			query.append(" and temp.DocumentTypeCode in ('15')           ");
			query.append(" Group by MaritimeCode, DocumentTypeCode order by LENGTH(MaritimeCode), MaritimeCode           ");
			query.append(" ) HosoXuatCanhVNCPC_DUYET           ");
			query.append(" ON HosoXuatCanhVNCPC_DUYET.MaritimeCode = dm_maritime.MaritimeCode           ");
			query.append("            ");
			
			query.append(" LEFT JOIN            ");
			query.append(" (           ");
			query.append(" select temp.MaritimeCode, DocumentTypeCode, count(temp.DocumentName) as total from temp_document temp            ");
			query.append("    inner join issue_permission_for_transit  iss         ");
			query.append("    on iss.DocumentName = temp.DocumentName        ");
			query.append("    and iss.DocumentYear  = temp.DocumentYear         ");
			query.append("    and   iss.StampStatus <> 2        ");
			query.append(" and iss.requestState in (4)           ");
			query.append(" and temp.documentStatusCode in (19)           ");
			query.append("    and iss.VersionNo = (SELECT MAX(iss2.VersionNo)         ");
			query.append("      FROM issue_permission_for_transit iss2       ");
			query.append("       WHERE iss.DocumentName = iss2.DocumentName)      ");
			if (Validator.isNotNull(dateFrom) && dateFrom.trim().length() > 0) {				
				query.append(" and iss.ApprovalDate >= concat(date('" + dateFrom + "'), ' 00:00:00') ");
			}
			if (Validator.isNotNull(dateTo) && dateTo.trim().length() > 0) {				
				query.append(" and iss.ApprovalDate <= concat(date('" + dateTo + "'), ' 23:59:59') ");
			}
			if (maritimeCode != null) {
				query.append(" and temp.MaritimeCode = " + maritimeCode);
			}
			query.append(" WHERE 1=1           ");
			query.append(" and temp.DocumentTypeCode in ('QC')           ");
			query.append(" Group by MaritimeCode, DocumentTypeCode order by LENGTH(MaritimeCode), MaritimeCode           ");
			query.append(" ) HosoQuaCanh_DUYET           ");
			query.append(" ON HosoQuaCanh_DUYET.MaritimeCode = dm_maritime.MaritimeCode           ");
			query.append("            ");
			if (maritimeCode != null) {
				query.append(" WHERE dm_maritime.MaritimeCode = " + maritimeCode);
			}
			query.append(" order by dm_maritime.MaritimeOrder           ");
			query.append(" ) solieuthongke           ");

			
			
			
			String sql = query.toString();
			log.info("=========findByKetQuaHoanThanhThuTuc========" + sql);
			
			QueryBuilder builder = QueryBuilder.builder().sqlQuery(true).queryString(sql).entityClass(ViewHoanThanhThuTuc.class).build();
			

			
			// execute the query and return a list from the db
			return (List<ViewHoanThanhThuTuc>) queryFactory.getResultList(builder);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return new ArrayList<ViewHoanThanhThuTuc>();
	}
	
	
}
