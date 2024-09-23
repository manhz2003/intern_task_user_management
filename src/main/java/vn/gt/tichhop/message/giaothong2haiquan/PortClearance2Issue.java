package vn.gt.tichhop.message.giaothong2haiquan;

import java.util.UUID;

import com.fds.nsw.nghiepvu.model.IssuePortClearance;
import com.fds.nsw.nghiepvu.model.IssuePortClearance;
import vn.gt.dao.noticeandmessage.service.IssuePortClearanceLocalServiceUtil;
import vn.gt.tichhop.message.TrangThaiBanKhaiXuatCanh;
import vn.gt.utils.FormatData;
import vn.nsw.Header;
import vn.nsw.model.PortClearance;




public class PortClearance2Issue {
	
	
	
	public boolean insert2PortClearance(PortClearance portClearance, Header header) throws Exception {
		try {
			System.out.println("insert2PortClearance  "+portClearance.getCallSign());
			IssuePortClearance issue = new IssuePortClearance();
			
			issue.setRequestCode(UUID.randomUUID().toString());
			issue.setNumberPortClearance(portClearance.getNumberPortClearance());
			issue.setDocumentName(FormatData.convertToLong(portClearance.getDocumentName()));
			issue.setDocumentYear(FormatData.convertToInt(portClearance.getDocumentYear()));
			issue.setPortofAuthority(portClearance.getPortofAuthority());
			issue.setNameOfShip(portClearance.getNameOfShip());
			issue.setFlagStateOfShip(portClearance.getFlagStateOfShip());
			issue.setNumberOfCrews(FormatData.convertToInt(portClearance.getNumberOfCrews()));
			issue.setNumberOfPassengers(FormatData.convertToInt(portClearance.getNumberOfPassengers()));
			issue.setCallSign(portClearance.getCallSign());
			issue.setNameOfMaster(portClearance.getNameOfMaster());
			issue.setCargo(portClearance.getCargo());
			issue.setVolumeCargo(FormatData.convertToDouble(portClearance.getVolumeCargo()));
			issue.setRepresentative(portClearance.getRepresentative());
			issue.setCargoUnit(portClearance.getCargoUnit());
			issue.setTransitCargo(portClearance.getTransitCargo());

			issue.setVolumeTransitCargo(FormatData.convertToDouble(portClearance.getVolumeTransitCargo()));
			issue.setTransitCargoUnit(portClearance.getTransitCargoUnit());

			issue.setTimeOfDeparture(FormatData.parseStringToDate(portClearance.getTimeOfDeparture()));

			issue.setNextPortOfCallCode(portClearance.getNextPortOfCallCode());

			issue.setValidUntil(FormatData.parseStringToDate(portClearance.getValidUntil()));
			issue.setIssueDate(FormatData.parseStringToDate(portClearance.getIssueDate()));
			issue.setDirectorOfMaritime(portClearance.getDirectorOfMaritimeAdministration());

			issue.setCertificateNo(portClearance.getCertificateNo());
			issue.setGt(FormatData.convertToDouble(portClearance.getGT()));
			issue.setRequestState(TrangThaiBanKhaiXuatCanh.KHAI_MOI);

			IssuePortClearanceLocalServiceUtil.addIssuePortClearance(issue);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
