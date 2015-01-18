package com.thoughtfocus.leave.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.thoughtfocus.leave.constants.QueryConstants;
import com.thoughtfocus.leave.domain.User;

public class RequestInitializeInterceptor extends HandlerInterceptorAdapter{
	
	private static final Logger logger = Logger.getLogger(RequestInitializeInterceptor.class); 

	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler)
		    throws Exception {
			String uri = request.getRequestURI();
			
			/*Authenticate user before sending the request to the controller*/
		    User sessUser=(User) request.getSession().getAttribute(QueryConstants.LOGGED_IN_USER);
		    if(!uri.endsWith("validateUser.do") && 
		    		!uri.endsWith("logout.do") && 
		    		!uri.endsWith("applyLeave.do") &&
		    		!uri.endsWith("userLogin.do") ){
			    if(null != sessUser){
			    	logger.debug("my name is :"+sessUser.getUserName());
			    }else{
			    	logger.debug("user logged out...");
			    	response.sendRedirect("userLogin.do");
		            return false;
	
			    }
		    }
			return true;
		}
	 
	/*	//after the handler is executed
		public void postHandle(
			HttpServletRequest request, HttpServletResponse response, 
			Object handler, ModelAndView modelAndView)
			throws Exception {
			System.out.println("*****************************************posthandle");
		}
		
		public void afterCompletion(HttpServletRequest request, 
				HttpServletResponse response, Object handler)
			    throws Exception {
			System.out.println("********************************************afterCompletion");
		}*/
}
