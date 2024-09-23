//todo Message listener
///**
// * @author DTT-007
// *
// */
//package vn.gt.tichhop.schedule;
//
//import java.util.List;
//
//import com.fds.nsw.nghiepvu.model.TempDocument;
//import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
//import vn.gt.tichhop.message.MessageType;
//
//import com.fds.nsw.kernel.exception.SystemException;
//
//
//import com.liferay.portal.kernel.messaging.Message;
//import com.liferay.portal.kernel.messaging.MessageListener;
//import com.liferay.portal.kernel.messaging.MessageListenerException;
//import com.fds.flex.common.ultility.Validator;
//
///**Tu dong tu choi tiep nhan ho so ben KE HOACH*/
////public class SchedulerTuChoiTiepNhan implements MessageListener {
//public class SchedulerTuChoiTiepNhan {
//
//
//
//	//@Override
//	public void receive(Message message) throws MessageListenerException {
//		/* SOME CODE TO BE RUN IN SCHEDULER AT INTERVAL */
//		try {
//			List<TempDocument> lstDocument = TempDocumentLocalServiceUtil.findKeHoachYCBS();
//			if (Validator.isNotNull(lstDocument) && lstDocument.size() > 0) {
//				log.info("======TuChoiTiepNhan===KeHoachYCBS===Thuc hien Business");
//				//ACTION_TYPE = 9 = YEU CAU BO XUNG
//				//Moi lan gui yeu cau bo xung, co mot message RequestDirection OUT, IN
//				//BusinessType -FunctionType
//				//99 			27
//				//99 			22
//
//				//Thong tin gui yeu cau bo xung
//				for (TempDocument document : lstDocument) {
//					if (document.getDocumentTypeCode().equalsIgnoreCase(MessageType.LOAT_THU_TUC_NHAP_CANH)) {
//
//					} else if (document.getDocumentTypeCode().equalsIgnoreCase(MessageType.LOAT_THU_TUC_XUAT_CANH)) {
//
//					} else if (document.getDocumentTypeCode().equalsIgnoreCase(MessageType.LOAT_THU_TUC_QUA_CANH)) {
//
//					}
//				}
//			} else {
//				log.info("======TuChoiTiepNhan===KeHoachYCBS===KHONG TON TAI HO SO THEO DIEU KIEN");
//			}
//		} catch (SystemException e) {
//			e.printStackTrace();
//		}
//
//	}
//}
//
//
///**
//"0 0 12 * * ? *"            Fire at 12pm (noon) every day
//"0 15 10 ? * * *"           Fire at 10:15am every day
//"0 10,44 14 ? 3 WED *"      Fire at 2:10pm and at 2:44pm every Wednesday in the month of March.
//"0 15 10 ? * MON-FRI *"     Fire at 10:15am every Monday, Tuesday, Wednesday, Thursday and Friday
//"50 * * ? * * *"
// */