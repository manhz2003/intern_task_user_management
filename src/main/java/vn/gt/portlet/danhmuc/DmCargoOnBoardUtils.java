package vn.gt.portlet.danhmuc;

import java.util.List;

import com.fds.nsw.nghiepvu.model.DmCargoOnBoard;
import vn.gt.dao.danhmuc.service.DmCargoOnBoardLocalServiceUtil;

import org.json.JSONArray;
import com.fds.nsw.liferay.core.JSONFactoryUtil;
import org.json.JSONObject;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class DmCargoOnBoardUtils {


	public static JSONObject getCargoOnBoards(String goodsTypeNameVN,
			String isDelete, String goodsTypeCodeGroup, int start, int end) {
		JSONArray array = JSONFactoryUtil.createJSONArray();
		JSONObject result = JSONFactoryUtil.createJSONObject();
		long total = 0;
		List<DmCargoOnBoard> cargoOnBoards = DmCargoOnBoardLocalServiceUtil
				.findCargoOnBoards(goodsTypeNameVN, isDelete,
						goodsTypeCodeGroup, start, end);
		total = DmCargoOnBoardLocalServiceUtil.countCargoOnBoards(
				goodsTypeNameVN, isDelete, goodsTypeCodeGroup);

		for (DmCargoOnBoard dmCargoOnBoard : cargoOnBoards) {
			JSONObject obj = JSONFactoryUtil.createJSONObject();
			obj.put("id", dmCargoOnBoard.getId());
			obj.put("goodsTypeCode", dmCargoOnBoard.getGoodsTypeCode());
			obj.put("goodsTypeName", dmCargoOnBoard.getGoodsTypeName());
			obj.put("goodsTypeNameVN", dmCargoOnBoard.getGoodsTypeNameVN());
			obj.put("isDelete", dmCargoOnBoard.getIsDelete());
			array.put(obj);
		}
		result.put("data", array);
		result.put("total", total);
		return result;
	}

	public static JSONObject getDetailCargoOnBoard(String goodsTypeCode) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		DmCargoOnBoard dmCargoOnBoard = DmCargoOnBoardLocalServiceUtil
				.fetchByGoodsTypeCode(goodsTypeCode);
		result.put("id", dmCargoOnBoard.getId());
		result.put("goodsTypeCode", dmCargoOnBoard.getGoodsTypeCode());
		result.put("goodsTypeName", dmCargoOnBoard.getGoodsTypeName());
		result.put("goodsTypeNameVN", dmCargoOnBoard.getGoodsTypeNameVN());
		result.put("modifiedDate", dmCargoOnBoard.getModifiedDate());
		result.put("isDelete", dmCargoOnBoard.getIsDelete());
		return result;
	}
}
