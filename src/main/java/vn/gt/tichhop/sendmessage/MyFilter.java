//todo myfilter
//package vn.gt.tichhop.sendmessage;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//
///**
// * Servlet Filter implementation class MyFilter
// */
//@WebFilter("/MyFilter")
//public class MyFilter implements Filter {
//
//	public MyFilter() {
//		// TODO Auto-generated constructor stub
//	}
//
//	/**
//	 * @see Filter#destroy()
//	 */
//	public void destroy() {
//
//	}
//
//	/**
//	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
//	 */
//	public void doFilter(ServletRequest request, ServletResponse response,
//			FilterChain chain) throws IOException, ServletException {
//
//		ThreatSentMessage.init();
//		ThreatReadDB.init();
//		chain.doFilter(request, response);
//	}
//
//	/**
//	 * @see Filter#init(FilterConfig)
//	 */
//	public void init(FilterConfig fConfig) throws ServletException {
//
//	}
//
//}
