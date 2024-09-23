package vn.gt.portlet.nguoidung;

import java.io.File;

import com.fds.nsw.liferay.core.ActionRequest;
import com.fds.nsw.liferay.core.ActionResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.gt.dao.common.service.UserSignLocalServiceUtil;



import com.fds.nsw.liferay.core.UploadPortletRequest;
import com.fds.nsw.liferay.core.ParamUtil;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.core.PortalUtil;
import vn.gt.portlet.TransportationMVCAction;

@Slf4j
@Component
public class QuanLyNguoiDungAction extends TransportationMVCAction {
	public void updateUserSign(ActionRequest actionRequest, ActionResponse actionResponse) 
			throws Exception {
		
		UploadPortletRequest uploadRequest = (UploadPortletRequest) actionRequest;
		
		long userId = PortalUtil.getUserId(uploadRequest);
		
		long userSignId = ParamUtil.getLong(uploadRequest, "userSignId");
		long accountId = ParamUtil.getLong(uploadRequest, "accountId");
		
		String maritimeCode = ParamUtil.getString(uploadRequest, "maritimeCode");
		String title = ParamUtil.getString(uploadRequest, "title");
		String representative = ParamUtil.getString(uploadRequest, "representative");

		//todo get data from FormParam
//		String chungThuSoName = uploadRequest.getFileName("fileChungThuSo");
//		File chungThuSoFile = uploadRequest.getFile("fileChungThuSo");
//		boolean deleteFileChungThuSo = ParamUtil.getBoolean(uploadRequest, "fileChungThuSo", false);
//
//		String chuKySoName = uploadRequest.getFileName("fileChuKySo");
//		File chuKySoFile = uploadRequest.getFile("fileChuKySo");
//		boolean deleteFileChuKySo = ParamUtil.getBoolean(uploadRequest, "fileChuKySo", false);
//
//		String conDauName = uploadRequest.getFileName("fileConDau");
//		File conDauFile = uploadRequest.getFile("fileConDau");
//		boolean deleteFileConDau = ParamUtil.getBoolean(uploadRequest, "fileConDau", false);
//
//		String redirectURL = ParamUtil.getString(uploadRequest, "redirectURL");
//		String backURL = ParamUtil.getString(uploadRequest, "backURL");
//
//		boolean result = false;
//		try {
//			UserSignLocalServiceUtil.updateUserSign(userId, userSignId, accountId,
//				maritimeCode, title, representative, chungThuSoName, chungThuSoFile, deleteFileChungThuSo,
//				chuKySoName, chuKySoFile, deleteFileChuKySo, conDauName, conDauFile, deleteFileConDau);
//
//			result = true;
//		} catch(Exception e) {
//			log.error(e.getMessage());
//		}
		
//		if(result && Validator.isNotNull(redirectURL)) {
//			actionResponse.sendRedirect(redirectURL);
//		} else if(!result && Validator.isNotNull(backURL)) {
//			actionResponse.sendRedirect(backURL);
//		}
	}
	
	public static void deleteUserSign(ActionRequest actionRequest, ActionResponse actionResponse) 
			throws Exception {
		
		long userSignId = ParamUtil.getLong(actionRequest, "userSignId");
		String redirectURL = ParamUtil.getString(actionRequest, "redirectURL");
		
		if(userSignId > 0) {
			try {
				UserSignLocalServiceUtil.deleteUserSignById(userSignId);
			} catch(Exception e) {
				log.error(e.getMessage());
			}
			
			if(Validator.isNotNull(redirectURL)) {
			}
		}
	}
	
}
