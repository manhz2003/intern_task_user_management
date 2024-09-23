package vn.gt.portlet.thutuc;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.fds.nsw.liferay.core.ActionRequest;
import jakarta.servlet.http.HttpServletRequest;

import com.fds.nsw.nghiepvu.model.UserPort;
import com.fds.nsw.nghiepvu.model.UserSign;
import vn.gt.dao.common.service.UserPortLocalServiceUtil;
import vn.gt.dao.common.service.UserSignLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.DmDocType;
import com.fds.nsw.nghiepvu.model.DmMaritime;
import com.fds.nsw.nghiepvu.model.DmState;
import vn.gt.dao.danhmuc.service.DmDocTypeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmStateLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.DmGtBusinessType;
import com.fds.nsw.nghiepvu.model.DmGtShipPosition;
import com.fds.nsw.nghiepvu.model.DmGtStatus;
import com.fds.nsw.nghiepvu.model.DmMinistry;
import vn.gt.dao.danhmucgt.service.DmGTBusinessTypeLocalServiceUtil;
import vn.gt.dao.danhmucgt.service.DmGTFunctionTypeLocalServiceUtil;
import vn.gt.dao.danhmucgt.service.DmGTShipPositionLocalServiceUtil;
import vn.gt.dao.danhmucgt.service.DmGtStatusLocalServiceUtil;
import vn.gt.dao.danhmucgt.service.DmMinistryLocalServiceUtil;

import com.fds.nsw.nghiepvu.model.IssueShiftingOrder;
import com.fds.nsw.nghiepvu.model.TempAnimalQuarantine;
import com.fds.nsw.nghiepvu.model.TempCargoDeclaration;
import com.fds.nsw.nghiepvu.model.TempCrewEffectsDeclaration;
import com.fds.nsw.nghiepvu.model.TempCrewList;
import com.fds.nsw.nghiepvu.model.TempDangerousGoodsManifest;
import com.fds.nsw.nghiepvu.model.TempDeclarationOfHealth;
import com.fds.nsw.nghiepvu.model.TempDocument;
import com.fds.nsw.nghiepvu.model.TempGeneralDeclaration;
import com.fds.nsw.nghiepvu.model.TempNoticeShipMessage;
import com.fds.nsw.nghiepvu.model.TempPassengerList;
import com.fds.nsw.nghiepvu.model.TempPlantQuarantine;
import com.fds.nsw.nghiepvu.model.TempShipSecurityMessage;
import com.fds.nsw.nghiepvu.model.TempShipStoresDeclaration;

import vn.gt.dao.noticeandmessage.service.InterfaceRequestLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.IssueShiftingOrderLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempAnimalQuarantineLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempCargoDeclarationLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempCrewEffectsDeclarationLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempCrewListLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDangerousGoodsNanifestLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDeclarationOfHealthLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempDocumentLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempGeneralDeclarationLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempNoTiceShipMessageLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempPassengerListLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempPlantQuarantineLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempShipSecurityMessageLocalServiceUtil;
import vn.gt.dao.noticeandmessage.service.TempShipStoresDeclarationLocalServiceUtil;
import com.fds.nsw.nghiepvu.model.ResultDeclaration;
import com.fds.nsw.nghiepvu.model.ResultMinistry;
import com.fds.nsw.nghiepvu.model.ResultNotification;
import vn.gt.dao.result.service.ResultDeclarationLocalServiceUtil;
import vn.gt.dao.result.service.ResultMinistryLocalServiceUtil;
import vn.gt.dao.result.service.ResultNotificationLocalServiceUtil;
import vn.gt.portlet.Utils;
import vn.gt.portlet.baocao.BaoCaoBussinessUtils;
import vn.gt.portlet.baocao.BaoCaoConstant;
import vn.gt.tichhop.message.MessageType;
import vn.gt.tichhop.message.TrangThaiBanKhaiNhapCanh;
import vn.gt.tichhop.report.ReportFunction;
import vn.gt.tichhop.report.ReportsBusinessUtils;
import vn.gt.utils.CheckBusinessUtil;
import vn.gt.utils.FormatData;
import vn.gt.utils.KeyParams;
import vn.gt.utils.PageType;

import com.fds.nsw.kernel.dao.orm.QueryUtil;
import com.fds.nsw.kernel.exception.SystemException;
import org.json.JSONArray;
import org.json.JSONException;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;
import com.fds.nsw.liferay.core.LanguageUtil;


import com.fds.flex.common.utility.string.StringPool;
import com.fds.flex.common.ultility.Validator;
import com.fds.nsw.liferay.model.Organization;
import com.fds.nsw.liferay.model.User;
import com.fds.nsw.liferay.core.ThemeDisplay;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class JSONProvider {

	

	public static JSONArray getRoleFilterStatus(Locale locale, User user) {
		JSONArray result = JSONFactoryUtil.createJSONArray();
		JSONObject object = JSONFactoryUtil.createJSONObject();

		// get user orgs
		try {
			List<Organization> orgs = user.getOrganizations();
			for (Organization organization : orgs) {
				if (organization.getName().equalsIgnoreCase(LanguageUtil.get(locale, "lanh-dao"))) {
					// start json build ke hoach
					object = JSONFactoryUtil.createJSONObject();
					object.put("action", "folder");
					object.put("action_active", "folder_open");
					object.put("id", -3);
					object.put("type", -3);
					object.put("title", LanguageUtil.get(locale, "cho-ky-so-tt"));
					object.put("active", false);
					object.put("index", 3);
					
					result.put(object);
					// end build ke hoach
					break;
				} else if (organization.getName().equalsIgnoreCase(LanguageUtil.get(locale, "van-thu"))) {
					// start json build ke hoach
					object = JSONFactoryUtil.createJSONObject();
					object.put("action", "folder");
					object.put("action_active", "folder_open");
					object.put("id", -4);
					object.put("type", -4);
					object.put("title", LanguageUtil.get(locale, "cho-dong-dau-tt"));
					object.put("active", false);
					object.put("index", 4);
					
					result.put(object);
					// end build ke hoach
					break;
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return result;
	}

}
