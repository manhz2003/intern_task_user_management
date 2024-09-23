package vn.gt.portlet.thongtintau;

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
public class ThongTinTauUtils
 {

	public static String getUrlFlag(String flagMenu) {
		if (flagMenu.equalsIgnoreCase(ThongTinTauAction.GIAY_PHEP_ROI_CANG)) {
			return "giay-phep-roi-cang";
		} else if (flagMenu.equalsIgnoreCase(ThongTinTauAction.GIAY_PHEP_QUA_CANH)) {
			return "giay-phep-qua-canh";
		} else if (flagMenu.equalsIgnoreCase(ThongTinTauAction.KE_HOACH_DIEU_DONG_TAU)) {
			return "ke-hoach-dieu-dong-tau";
		} else if (flagMenu.equalsIgnoreCase(ThongTinTauAction.THONG_TIN_TAU)) {
			return "thong-tin-tau";
		}
		return "";
	}

}
