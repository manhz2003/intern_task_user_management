package com.fds.nsw.liferay.core;

import jakarta.servlet.http.HttpServletRequest;

public class PortletURLFactoryUtil {

    public static PortletURL create(
            HttpServletRequest request, String portletName, long plid,
            String action) {
        StringBuffer requestURL = request.getRequestURL();
        String result = requestURL.toString();
        if(!result.contains("https")) {
            result = result.replaceAll("http", "https");
        }
        PortletURL portletURL = new PortletURL();
        String resourceID = result + "?p_p_id=" + action;
        portletURL.setResourceID(resourceID);

        return portletURL;
    }
}
