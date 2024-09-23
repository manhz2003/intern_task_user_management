package vn.gt.tichhop.message;

import java.util.Date;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import vn.nsw.Header;
import vn.nsw.Header.Subject;
import vn.nsw.model.XacNhanHuyLenhDieuDong;



@Slf4j
public class XacNhanHuyGiayPhep {



	public String huyGiayPhepRoiCang(Header header, String dataInput, String data) {
		BusinessUtils businessUtils = new BusinessUtils();
		try {

			// Insert history.
			businessUtils.insertHistory(header, dataInput, BusinessUtils.HqmcToBoGiaoThong,
					TrangThaiHoSo.HISTORY_REQUEST_STATE_MOI_YEU_CAU, null);

			Object obj = MessageFactory.convertXmltoObject(MessageType.HUY_GIAY_PHEP_ROI_CANG, data);
			if (obj instanceof XacNhanHuyThuTuc) {


				Subject subject = header.getSubject();
				subject.setType(MessageType.HUY_GIAY_PHEP_ROI_CANG);
				header.setSubject(subject);
				businessUtils.sendMessageHuyGiayPhepRoiCang(header, "Bo Giao Thong Van Tai", "Thu Tuc", header.getFrom().getName(), "System", new Date(), Integer.valueOf(String.valueOf( ((XacNhanHuyLenhDieuDong) obj).getIsApprove())), null);
			}
			// Cap nhat trang thai la tiep nhan voi truong hop da tiep nhan ban khai.
			if (header != null && header.getReference() != null) {
				businessUtils.updateHistory(header.getReference().getMessageId(), TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}

		// Ban tin tra ve dung.
		String xmlResult = businessUtils.createReturnContentAfterParserDataReceiverFromNSW(header);
		businessUtils.insertHistory(header, xmlResult, BusinessUtils.BoGiaoThongToHqmc,
				TrangThaiHoSo.DA_GHI_NHO_YEU_CAU, UUID.randomUUID().toString());

		return xmlResult;
	}


	public String huyGiayPhepQuaCanh(Header header, String dataInput, String data) {
		BusinessUtils businessUtils = new BusinessUtils();
		try {

			// Insert history.
			businessUtils.insertHistory(header, dataInput, BusinessUtils.HqmcToBoGiaoThong,
					TrangThaiHoSo.HISTORY_REQUEST_STATE_MOI_YEU_CAU, null);

			Object obj = MessageFactory.convertXmltoObject(MessageType.HUY_GIAY_PHEP_QUA_CANH, data);
			if (obj instanceof XacNhanHuyLenhDieuDong) {
				Subject subject = header.getSubject();
				subject.setType(MessageType.HUY_GIAY_PHEP_QUA_CANH);
				header.setSubject(subject);
				businessUtils.sendMessageHuyGiayPhepQuaCanh(header, "Bo Giao Thong Van Tai", "Thu Tuc", header.getFrom().getName(), "System", new Date(),  Integer.valueOf(String.valueOf( ((XacNhanHuyLenhDieuDong) obj).getIsApprove())), null);
			}
			// Cap nhat trang thai la tiep nhan voi truong hop da tiep nhan ban khai.
			if (header != null && header.getReference() != null) {
				businessUtils.updateHistory(header.getReference().getMessageId(), TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}

		// Ban tin tra ve dung.
		String xmlResult = businessUtils.createReturnContentAfterParserDataReceiverFromNSW(header);
		businessUtils.insertHistory(header, xmlResult, BusinessUtils.BoGiaoThongToHqmc,
				TrangThaiHoSo.DA_GHI_NHO_YEU_CAU, UUID.randomUUID().toString());

		return xmlResult;
	}


	public String khaiHuyHoSo(Header header, String dataInput, String data) {
		BusinessUtils businessUtils = new BusinessUtils();
		try {

			// Insert history.
			businessUtils.insertHistory(header, dataInput, BusinessUtils.HqmcToBoGiaoThong,
					TrangThaiHoSo.HISTORY_REQUEST_STATE_MOI_YEU_CAU, null);

			Object obj = MessageFactory.convertXmltoObject(MessageType.KHAI_HUY_HO_SO, data);
			if (obj instanceof XacNhanHuyLenhDieuDong) {
				Subject subject = header.getSubject();
				subject.setType(MessageType.KHAI_HUY_HO_SO);
				header.setSubject(subject);
				businessUtils.sendMessageKhaiHuyHoSo(header, "Bo Giao Thong Van Tai", "Thu Tuc", header.getFrom().getName(), "System", new Date(),  Integer.valueOf(String.valueOf( ((XacNhanHuyLenhDieuDong) obj).getIsApprove())), null);
			}
			// Cap nhat trang thai la tiep nhan voi truong hop da tiep nhan ban khai.
			if (header != null && header.getReference() != null) {
				businessUtils.updateHistory(header.getReference().getMessageId(), TrangThaiHoSo.HISTORY_REQUEST_STATE_GHI_NHAN);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}

		// Ban tin tra ve dung.
		String xmlResult = businessUtils.createReturnContentAfterParserDataReceiverFromNSW(header);
		businessUtils.insertHistory(header, xmlResult, BusinessUtils.BoGiaoThongToHqmc,
				TrangThaiHoSo.DA_GHI_NHO_YEU_CAU, UUID.randomUUID().toString());

		return xmlResult;
	}
}
