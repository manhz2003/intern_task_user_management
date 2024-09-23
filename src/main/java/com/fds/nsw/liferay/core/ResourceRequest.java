package com.fds.nsw.liferay.core;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class ResourceRequest extends HttpServletRequestWrapper {
	public ResourceRequest(HttpServletRequest request) {
		super(request);
	}

	public ActionRequest.PortletSession portletSession;

	@Getter
	@Setter
	public static class PortletSession {

		public ActionRequest.PortletContext portletContext;

	}

	public static class PortletContext {
		public String realPath;

		//todo add default realPath
		public String getRealPath(String slash) {
			return null;
		}

	}

  
}
