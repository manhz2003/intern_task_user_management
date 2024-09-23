package vn.gt.portlet.danhmuc;

import java.util.List;

import com.fds.nsw.nghiepvu.model.DmGoods;
import vn.gt.dao.danhmuc.service.DmGoodsLocalServiceUtil;

import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class DmGoodUtils {

	public static JSONObject getGoods(String goodsItemName, String isDelete,
			String goodsItemCodeGroup, int start, int end) {
		JSONArray array = JSONFactoryUtil.createJSONArray();
		JSONObject result = JSONFactoryUtil.createJSONObject();
		long total = 0;
		List<DmGoods> dmGoods = DmGoodsLocalServiceUtil.findGoods(
				goodsItemName, isDelete, goodsItemCodeGroup, start, end);
		total = DmGoodsLocalServiceUtil.countGoods(goodsItemName, isDelete,
				goodsItemCodeGroup);
		for (DmGoods dmGood : dmGoods) {
			JSONObject obj = JSONFactoryUtil.createJSONObject();
			obj.put("id", dmGood.getId());
			obj.put("goodsItemCode", dmGood.getGoodsItemCode());
			obj.put("goodsItemNameVN", dmGood.getGoodsItemNameVN());
			obj.put("goodsItemName", dmGood.getGoodsItemName());
			obj.put("isDelete", dmGood.getIsDelete());
			array.put(obj);
		}
		result.put("data", array);
		result.put("total", total);

		return result;
	}

	public static JSONObject getDetailGoods(String goodsItemCode) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		DmGoods dmGoods = DmGoodsLocalServiceUtil
				.getByGoodsItemCode(goodsItemCode);
		result.put("id", dmGoods.getId());
		result.put("goodsItemCode", dmGoods.getGoodsItemCode());
		result.put("goodsItemNameVN", dmGoods.getGoodsItemNameVN());
		result.put("goodsItemName", dmGoods.getGoodsItemName());
		result.put("modifiedDate", dmGoods.getModifiedDate());
		result.put("isDelete", dmGoods.getIsDelete());
		return result;
	}
}
