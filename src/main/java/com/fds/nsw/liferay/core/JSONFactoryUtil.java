package com.fds.nsw.liferay.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;

@Slf4j
public class JSONFactoryUtil {
    private static ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    public static JSONArray createJSONArray() {
        return new JSONArray();
    }

    public static JSONArray createJSONArray(String object) {
        return new JSONArray(object);
    }

    public static JSONObject createJSONObject() {
        return new JSONObject();
    }

    public static JSONObject createJSONObject(Object object) {
        return new JSONObject(object);
    }

    public static String looseSerialize(Object object) {
      try {
          return ow.writeValueAsString(object);
      } catch (Exception e) {
          log.error("Error when looseSerialize", e);
      }
      return "";
    }

    public static String looseSerializeDeep(Object object) {
        //todo looseSerializeDeep
        try {
            return ow.writeValueAsString(object);
        } catch (Exception e) {
            log.error("Error when looseSerialize", e);
        }
        return "";
    }
}
