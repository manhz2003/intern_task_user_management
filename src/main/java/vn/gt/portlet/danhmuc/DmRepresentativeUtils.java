package vn.gt.portlet.danhmuc;

import java.util.List;

import com.fds.nsw.nghiepvu.model.DmRepresentative;
import vn.gt.dao.danhmuc.service.DmMaritimeLocalServiceUtil;
import vn.gt.dao.danhmuc.service.DmRepresentativeLocalServiceUtil;

import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class DmRepresentativeUtils {

	public static JSONObject getDetailRepresentative(String representativeCode) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		DmRepresentative dmRepresentative = DmRepresentativeLocalServiceUtil
				.getByRepCode(representativeCode);
		result.put("id", dmRepresentative.getId());
		result.put("repCode", dmRepresentative.getRepCode());
		result.put("repLevel", dmRepresentative.getRepLevel());
		result.put("repName", dmRepresentative.getRepName());
		result.put("repNameVN", dmRepresentative.getRepNameVN());
		result.put("modifiedDate", dmRepresentative.getModifiedDate());
		result.put("isDelete", dmRepresentative.getIsDelete());
		return result;
	}

	public static JSONObject getRepresentatives(String repNameVN, int repLevel,
			String maritimeCode, String isDelete, String repCodeGroup,
			int start, int end) {
		JSONArray array = JSONFactoryUtil.createJSONArray();
		JSONObject result = JSONFactoryUtil.createJSONObject();
		long total = 0;
		List<DmRepresentative> dmRepresentatives = DmRepresentativeLocalServiceUtil
				.findDmRepresentatives(maritimeCode, repNameVN, repLevel,
						isDelete, repCodeGroup, start, end);
		total = DmRepresentativeLocalServiceUtil.countDmRepresentatives(
				maritimeCode, repNameVN, repLevel, isDelete, repCodeGroup);
		for (DmRepresentative dmRepresentative : dmRepresentatives) {
			JSONObject obj = JSONFactoryUtil.createJSONObject();

			obj.put("id", dmRepresentative.getId());
			obj.put("repCode", dmRepresentative.getRepCode());
			obj.put("repLevel", dmRepresentative.getRepLevel());
			obj.put("repName", dmRepresentative.getRepName());
			obj.put("repNameVN", dmRepresentative.getRepNameVN());
			obj.put("maritimeNameVN", DmMaritimeLocalServiceUtil
					.getByMaritimeCode(dmRepresentative.getMaritimeCode())
					.getMaritimeNameVN());
			obj.put("isDelete", dmRepresentative.getIsDelete());
			array.put(obj);
		}
		result.put("data", array);
		result.put("total", total);

		return result;
	}
}
