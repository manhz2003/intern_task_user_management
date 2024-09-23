/**
 *
 */
package vn.gt.tichhop.message;

import java.util.Date;
import java.util.UUID;

import vn.nsw.Header;
import vn.nsw.Header.Subject;
import vn.nsw.model.XacNhanHuyLenhDieuDong;




/**
 * @author win_64
 *
 */
import lombok.extern.slf4j.Slf4j;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import com.fds.nsw.liferay.core.LanguageUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.core.UploadPortletRequest;
@Slf4j
public class HuyLenhDieuDongUtils
 {



	public String huyLenhDieuDongHQMC(Header header, String dataInput, String data) {
		BusinessUtils businessUtils = new BusinessUtils();
		try {

			// Insert history.
			businessUtils.insertHistory(header, dataInput, BusinessUtils.HqmcToBoGiaoThong,
					TrangThaiHoSo.HISTORY_REQUEST_STATE_MOI_YEU_CAU, null);

			Object obj = MessageFactory.convertXmltoObject(MessageType.HUY_LENH_DIEU_DONG, data);
			if (obj instanceof XacNhanHuyLenhDieuDong) {


				Subject subject = header.getSubject();
				subject.setType(MessageType.HUY_LENH_DIEU_DONG);
				header.setSubject(subject);
				businessUtils.sendMessageHuyHoLenhDieuDong(header, "Bo Giao Thong Van Tai", "Ke Hoach", header.getFrom().getName(), "System", new Date(), Integer.parseInt( ((XacNhanHuyLenhDieuDong) obj).getIsApprove()), null);
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
