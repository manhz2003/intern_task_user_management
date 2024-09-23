package com.fds.nsw.properties;

import java.util.Map;

/**
 * @author vietdd
 */
public class PropKey {

   

	public static Map<String, Object> keyMap;

    public static Map<String, Object> getKeyMap() {
        return keyMap;
    }

    public static void setKeyMap(Map<String, Object> keyMap) {
        PropKey.keyMap = keyMap;
    }

}
