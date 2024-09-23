/**
 *
 */
package vn.gt.tichhop.message;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.fds.nsw.nghiepvu.model.TempDocument;
import com.fds.nsw.nghiepvu.model.TempDocument;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.ResultCompletion;

import vn.gt.dao.result.service.ResultCompetionLocalServiceUtil;
import vn.gt.utils.FormatData;
import vn.nsw.Header;
import vn.nsw.model.XacNhanThongQuan;




/**
 * @author win_64
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
public class LenhThongQuanUtils
 {



	public String nhanLenhThongQuan(Header header, String dataInput, String data) {
		BusinessUtils businessUtils = new BusinessUtils();
		try {

			// Insert history.
			businessUtils.insertHistory(header, dataInput, BusinessUtils.HqmcToBoGiaoThong, TrangThaiHoSo.HISTORY_REQUEST_STATE_MOI_YEU_CAU, null);

			List<Object> liObjects = MessageFactory.converXMLMessagesContentToObject(data);
			// Insert RESULT_COMPLETION table.
			List<TempDocument> litempDocuments = TempDocumentLocalServiceUtil.findTemDocumentByDocumentNameAndDocumentYear(header.getSubject()
					.getReference(), header.getSubject().getDocumentYear());
			TempDocument tempDocument = null;
			if (litempDocuments != null && litempDocuments.size() > 0) {
				tempDocument = litempDocuments.get(0);
			}

			ResultCompletion resultCompetion = ResultCompetionLocalServiceUtil.findByDocumentNameAndDocumentYear(header.getSubject().getReference(),
					header.getSubject().getDocumentYear());
			if (resultCompetion == null) {
				if (tempDocument == null) {
					tempDocument = new TempDocument();
				}
				resultCompetion = new ResultCompletion();

				resultCompetion.setRequestCode(header.getReference().getMessageId());

				resultCompetion.setRequestState(tempDocument.getRequestState());
				resultCompetion.setDocumentName(tempDocument.getDocumentName());
				resultCompetion.setDocumentYear(tempDocument.getDocumentYear());
				resultCompetion.setNameOfShip(tempDocument.getShipName());
				resultCompetion.setFlagStateOfShip(tempDocument.getStateCode());
				resultCompetion.setNameOfMaster(tempDocument.getShipCaptain());
				resultCompetion.setGrossTonnage(BigDecimal.valueOf(tempDocument.getGrt()));
				resultCompetion.setPortOfArrivalCode(tempDocument.getPortCode());
				resultCompetion.setMaritimeCode(tempDocument.getMaritimeCode());

				for (Object object : liObjects) {
					if (object instanceof XacNhanThongQuan) {
						XacNhanThongQuan xacNhanThongQuan = XacNhanThongQuan.class.cast(object);
						resultCompetion.setResponseTimeHQ(FormatData.parseDateShort3StringToDate(xacNhanThongQuan.getAcceptDate()));
						resultCompetion.setResponseStatusHQ(LenhThongQuanUtils.getResponseStatusHQ(xacNhanThongQuan.getFlag()));
						resultCompetion.setResponseHQ(xacNhanThongQuan.getNotification());
					}
				}
				ResultCompetionLocalServiceUtil.addResultCompetion(resultCompetion);
				log.debug("=====Add new ResultCompletion with DocumentName:" + tempDocument.getDocumentName() + ":DocumentYear::"
						+ tempDocument.getDocumentYear());
			} else {
				for (Object object : liObjects) {
					if (object instanceof XacNhanThongQuan) {
						XacNhanThongQuan xacNhanThongQuan = XacNhanThongQuan.class.cast(object);
						resultCompetion.setResponseTimeHQ(FormatData.parseDateShort3StringToDate(xacNhanThongQuan.getAcceptDate()));
						resultCompetion.setResponseStatusHQ(LenhThongQuanUtils.getResponseStatusHQ(xacNhanThongQuan.getFlag()));
						resultCompetion.setResponseHQ(xacNhanThongQuan.getNotification());
					}
				}
				ResultCompetionLocalServiceUtil.updateResultCompetion(resultCompetion);
				log.debug("=====Update ResultCompletion with DocumentName:" + resultCompetion.getDocumentName() + ":DocumentYear::"
						+ resultCompetion.getDocumentYear());
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
		businessUtils.insertHistory(header, xmlResult, BusinessUtils.BoGiaoThongToHqmc, TrangThaiHoSo.DA_GHI_NHO_YEU_CAU, UUID.randomUUID()
				.toString());

		return xmlResult;
	}

	private static int getResponseStatusHQ(String flag) {
		if (flag.equalsIgnoreCase(MessageType.FLAG_XANH)) {
			return MessageType.DA_PHE_DUYET;
		} else if (flag.equalsIgnoreCase(MessageType.FLAG_DO)) {
			return MessageType.TU_CHOI;
		}
		return 0;
	}

}
