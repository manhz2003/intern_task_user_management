package com.fds.nsw.liferay.core;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;

@Getter
public class ResourceURL {
    public String resourceID;

    public void setResourceID(HttpServletRequest request, String action) {
        StringBuffer requestURL = request.getRequestURL();
        String result = requestURL.toString();
        if(!result.contains("https")) {
            result = result.replaceAll("http", "https");
        }
        this.resourceID = result + "?p_p_id=" + action + "&p_p_lifecycle=2&p_p_state=normal&p_p_mode=view&p_p_cacheability=cacheLevelPage";
    }

    public void setResourceID(String resourceId) {
        this.resourceID += "&p_p_resource_id=" + resourceId;
    }

    public String toString() {
        return this.getResourceID();
    }

}
