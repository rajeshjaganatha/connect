package com.thoughtfocus.leave.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.thoughtfocus.leave.constants.QueryConstants;
import com.thoughtfocus.leave.domain.HolidayList;
import com.thoughtfocus.leave.domain.LeaveSummary;
import com.thoughtfocus.leave.domain.LeaveType;
import com.thoughtfocus.leave.domain.User;
import com.thoughtfocus.leave.formbean.LeaveBean;
import com.thoughtfocus.leave.formbean.QueryBean;
import com.thoughtfocus.leave.service.QueryManager;
import com.thoughtfocus.leave.validator.BookmarkFormValidator;

/**
 * The bookmark controller for capturing all bookmark actions
 * @author manaswita.mishra
 *
 */
@Controller
public class QueryController {
	
	@Autowired
	private QueryManager queryManager;
	
	@Autowired
	private BookmarkFormValidator bookmarkFormValidator;
	
	private static final Logger logger = Logger.getLogger(QueryController.class); 

	/**
	 * Show all bookmarks
	 * @param map
	 * @param session
	 * @return
	 */
	/**
     * clear the query
     * @param queryBean
     * @return
     */
    /**
     * 
     * @param queryBean
     * @return
     */
    @RequestMapping(value="/userLogin", method=RequestMethod.GET)
	public ModelAndView userLogin(@ModelAttribute("queryBean")QueryBean queryBean){

		return new ModelAndView("UserLogin", "queryBean", queryBean);
	}
    
    /**
     * 
     * @param queryBean
     * @param result
     * @param session
     * @param req
     * @param model
     * @return
     */
    @RequestMapping("/validateUser")
	public String validateUser(@ModelAttribute("queryBean") @Valid QueryBean queryBean,final BindingResult result,Map<String, Object> map,HttpSession session,HttpServletRequest req,Model model){
		
			bookmarkFormValidator.validate(queryBean, result);
			List<LeaveType> queryResult = null;
			
			if (result.hasErrors()) {
				logger.info("Validation errors logging in");
				return "UserLogin";
			}
			try {
				User user=queryManager.validateUser(queryBean);
				if(null!=user){
					session.setAttribute(QueryConstants.LOGGED_IN_USER, user);
					logger.debug(queryBean.getUserName()+" just logged in");
					System.out.println("User logged in");
					queryResult = queryManager.searchBookmarks();

				}else{
					model.addAttribute(QueryConstants.USER_ERROR_MSG, QueryConstants.ERROR_MSG_LOGIN_FAILED);
					logger.debug(queryBean.getUserName()+" could not get in");
					return "UserLogin";
				}
		
			}catch (HibernateException e){
				model.addAttribute(QueryConstants.USER_ERROR_MSG, QueryConstants.ERROR_MSG_LOGIN_FAILED_1);
				logger.debug(queryBean.getUserName()+" could not get in");
				logger.error(e.getStackTrace());
				return "UserLogin";
			}catch (Exception e) {
				model.addAttribute(QueryConstants.USER_ERROR_MSG, QueryConstants.ERROR_MSG_UNEXPECTED);
				logger.debug(queryBean.getUserName()+" could not get in");
				logger.error(e.getMessage());
				return "UserLogin";
			}
			map.put("queryResults", queryResult);

			return "QueryResults";

	}
    
    /**
	 * Logout user
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public ModelAndView logout(@ModelAttribute("queryBean")QueryBean queryBean,HttpServletRequest request)
	{
		request.getSession().invalidate();
		return new ModelAndView("UserLogin", "queryBean", queryBean);
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value="/applyLeave", method=RequestMethod.POST)
	public String applyLeave(@ModelAttribute("leaveBean") @Valid LeaveBean leaveBean,final BindingResult result,Map<String, Object> map,HttpSession session,HttpServletRequest req,Model model){
		
		List<LeaveType> queryResult = null;
		
		if (result.hasErrors()) {
			logger.info("Validation errors applying leave");
			try {
				queryResult = queryManager.searchBookmarks();
				} catch (Exception e) {
				e.printStackTrace();
			}
			map.put("queryResults", queryResult);
			return "QueryResults";
		}
		try {

			User user=(User) session.getAttribute(QueryConstants.LOGGED_IN_USER);
			LeaveSummary applyleave =  queryManager.applyLeave(leaveBean,user);
			/*int applyleave =  queryManager.applyLeave(leaveBean,user);
			if (applyleave != 0) {
				System.out.println(applyleave);
			}*/
			} catch (Exception e) {
			e.printStackTrace();
			}
				
		try {
			queryResult = queryManager.searchBookmarks();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("queryResults", queryResult);
		return "QueryResults";
		
	}
	
	@RequestMapping(value="/holidaylist", method=RequestMethod.GET)
	public String holidaylist(@ModelAttribute("queryBean") @Valid QueryBean queryBean,final BindingResult result,Map<String, Object> map,HttpSession session,HttpServletRequest req,Model model){
		
		List<HolidayList> holidaylist = null;
		try {
			holidaylist = queryManager.holidayList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		map.put("holidayList", holidaylist);
		return "HolidayList";
		
	}
	

}
