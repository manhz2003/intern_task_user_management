package vn.gt.portlet.danhmuc;

import java.util.List;

import com.fds.nsw.nghiepvu.model.DmGoodsType;
import vn.gt.dao.danhmuc.service.DmGoodsTypeLocalServiceUtil;

import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class DmGoodTypeUtils {


	public static JSONObject getGoodsTypes(String goodsTypeNameVN,
			String isDelete, String goodsTypeCodeGroup, int start, int end) {
		JSONArray array = JSONFactoryUtil.createJSONArray();
		JSONObject result = JSONFactoryUtil.createJSONObject();
		long total = 0;
		List<DmGoodsType> dmGoodsTypes = DmGoodsTypeLocalServiceUtil
				.findGoodsType(goodsTypeNameVN, isDelete, goodsTypeCodeGroup,
						start, end);
		total = DmGoodsTypeLocalServiceUtil.countGoodsType(goodsTypeNameVN,
				isDelete, goodsTypeCodeGroup);
		for (DmGoodsType dmGoodsType : dmGoodsTypes) {
			JSONObject obj = JSONFactoryUtil.createJSONObject();

			obj.put("id", dmGoodsType.getId());
			obj.put("goodsTypeCode", dmGoodsType.getGoodsTypeCode());
			obj.put("goodsTypeName", dmGoodsType.getGoodsTypeName());
			obj.put("goodsTypeNameVN", dmGoodsType.getGoodsTypeNameVN());
			obj.put("isDelete", dmGoodsType.getIsDelete());

			array.put(obj);
		}
		result.put("data", array);
		result.put("total", total);

		return result;
	}

	public static JSONObject getDetailGoodsType(String goodsTypeCode) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		DmGoodsType dmGoodsType = DmGoodsTypeLocalServiceUtil
				.getByGoodsTypeCode(goodsTypeCode);
		result.put("id", dmGoodsType.getId());
		result.put("goodsTypeCode", dmGoodsType.getGoodsTypeCode());
		result.put("goodsTypeNameVN", dmGoodsType.getGoodsTypeNameVN());
		result.put("goodsTypeName", dmGoodsType.getGoodsTypeName());
		result.put("modifiedDate", dmGoodsType.getModifiedDate());
		result.put("isDelete", dmGoodsType.getIsDelete());
		return result;
	}
}
