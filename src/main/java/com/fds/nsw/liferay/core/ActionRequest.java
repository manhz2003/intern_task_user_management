package com.fds.nsw.liferay.core;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.*;

@Getter
@Setter
public class ActionRequest extends HttpServletRequestWrapper {
	public static final  String ACTION_NAME = "javax.portlet.action";
	public ActionRequest(HttpServletRequest request) {
		super(request);
	}

	public PortletSession portletSession;

	@Getter
	@Setter
	public static class PortletSession {

		public PortletContext portletContext;

	}

	public static class PortletContext {
		public String realPath;

		//todo add default realPath
		public String getRealPath(String slash) {
			return null;
		}

	}
  
}
