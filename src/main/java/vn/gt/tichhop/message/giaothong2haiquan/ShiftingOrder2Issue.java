package vn.gt.tichhop.message.giaothong2haiquan;

import java.util.UUID;

import com.fds.nsw.nghiepvu.model.IssueShiftingOrder;
import com.fds.nsw.nghiepvu.model.IssueShiftingOrder;
import vn.gt.dao.noticeandmessage.service.IssueShiftingOrderLocalServiceUtil;
import vn.gt.tichhop.message.TrangThaiBanKhaiNhapCanh;
import vn.gt.utils.FormatData;
import vn.nsw.Header;
import vn.nsw.model.ShiftingOrder;




public class ShiftingOrder2Issue {
	
	/*
	
	public boolean insert2ShiftingOrder(ShiftingOrder shiftingOrder, Header header) throws Exception {
		try {
			
			IssueShiftingOrder issue = new IssueShiftingOrder();
			
			issue.setRequestCode(UUID.randomUUID().toString());
			issue.setNumberShiftingOrder(shiftingOrder.getNumberShiftingOrder());
			issue.setDocumentName(FormatData.convertToLong(shiftingOrder.getDocumentName()));
			issue.setDocumentYear(FormatData.convertToInt(shiftingOrder.getDocumentYear()));
			issue.setPortofAuthority(shiftingOrder.getPortofAuthority());
			issue.setNameOfShip(shiftingOrder.getNameOfShip());
			issue.setFlagStateOfShip(shiftingOrder.getFlagStateOfShip());
			issue.setPortHarbourCode(shiftingOrder.getPortHarbourCode());
			issue.setRepresentative(shiftingOrder.getRepresentative());
			issue.setAnchoringPortCode(shiftingOrder.getAnchoringPortCode());
			issue.setAnchoringPortWharfCode(shiftingOrder.getAnchoringPortWharfCode());
			
			//issue.setShiftingPortWharfCode(shiftingOrder.getShiftingPortWharfCode());
			issue.setShiftingDate(FormatData.parseStringToDate(shiftingOrder.getShiftingDate()));
			
			issue.setReasonToShift(shiftingOrder.getReasonToShift());
			issue.setIssueDate(FormatData.parseStringToDate(shiftingOrder.getIssueDate()));
			issue.setDirectorOfMaritime(shiftingOrder.getDirectorOfMaritimeAdministration());
			issue.setCertificateNo(shiftingOrder.getCertificateNo());
			issue.setRequestState(TrangThaiBanKhaiNhapCanh.KHAI_MOI);
			
			IssueShiftingOrderLocalServiceUtil.addIssueShiftingOrder(issue);
			
			return true;
		} catch (Exception e) {
			log.error(e.getMessage());
		} 
		return false;
	}*/
}
