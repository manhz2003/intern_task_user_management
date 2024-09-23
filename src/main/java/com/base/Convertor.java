//package com.base;
//
//import com.google.gson.Gson;
//
//public class Convertor {
//	public static String toJson(Object data, RefData refData) {
//		if (refData == null)
//			return "RefData is null";
//		if (!(data instanceof Object))
//			return "Data is not instance of object";
//		Gson gson = new Gson();
//		refData.data = gson.toJson(data);
//		return null;
//	}
//
//	public static String fromJson(String jsonData, Object type, RefData refData) {
//		if (type == null)
//			return "Type is null";
//		if (refData == null)
//			return "RefData is null";
//		if (StringEx.isNullOrWhiteSpace(jsonData))
//			return "Json data is empty";
//		Gson gson = new Gson();
//		try {
//			refData.data = gson.fromJson(jsonData, type.getClass());
//		} catch (Exception ex) {
//			return ex.toString();
//		}
//		return null;
//	}
//}
