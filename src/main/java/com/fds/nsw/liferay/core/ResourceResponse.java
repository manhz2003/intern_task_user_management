package com.fds.nsw.liferay.core;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

public class ResourceResponse extends HttpServletRequestWrapper {

	public ResourceResponse(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}

	public ResourceURL createResourceURL(String action) {
		ResourceURL resourceURL = new ResourceURL();
		resourceURL.setResourceID(this, action);

		return resourceURL;
	}
  
}
