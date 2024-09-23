package vn.gt.portlet.danhmuc;

import java.util.List;

import com.fds.nsw.nghiepvu.model.DmRankRating;
import vn.gt.dao.danhmuc.service.DmRankRatingLocalServiceUtil;

import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class DmRankRatingUtils {

	public static JSONObject getRankRatings(String rankNameVN, String rankName,
			String isDelete, String rankCodeGroup, int start, int end) {
		JSONArray array = JSONFactoryUtil.createJSONArray();
		JSONObject result = JSONFactoryUtil.createJSONObject();
		long total = 0;

		List<DmRankRating> dmRankRatings = DmRankRatingLocalServiceUtil
				.findRankRatings(rankNameVN, rankName, isDelete, rankCodeGroup,
						start, end);
		total = DmRankRatingLocalServiceUtil.countRankRatings(rankNameVN,
				rankName, isDelete, rankCodeGroup);

		for (DmRankRating dmRankRating : dmRankRatings) {
			JSONObject obj = JSONFactoryUtil.createJSONObject();

			obj.put("id", dmRankRating.getId());
			obj.put("rankCode", dmRankRating.getRankCode());
			obj.put("rankName", dmRankRating.getRankName());
			obj.put("rankNameVN", dmRankRating.getRankNameVN());
			obj.put("isDelete", dmRankRating.getIsDelete());
			array.put(obj);
		}
		result.put("data", array);
		result.put("total", total);

		return result;
	}

	public static JSONObject getDetailRankRating(String rankCode) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		DmRankRating dmRankRating = DmRankRatingLocalServiceUtil
				.getByRankCode(rankCode);
		result.put("id", dmRankRating.getId());
		result.put("rankCode", dmRankRating.getRankCode());
		result.put("rankName", dmRankRating.getRankName());
		result.put("rankNameVN", dmRankRating.getRankNameVN());
		result.put("modifiedDate", dmRankRating.getModifiedDate());
		result.put("isDelete", dmRankRating.getIsDelete());
		return result;
	}
}
