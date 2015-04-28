package com.thoughtfocus.leave.controller;

import java.util.ArrayList;
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
import com.thoughtfocus.leave.domain.Projects;
import com.thoughtfocus.leave.domain.TaskSummary;
import com.thoughtfocus.leave.domain.Tasks;
import com.thoughtfocus.leave.domain.User;
import com.thoughtfocus.leave.formbean.LeaveBean;
import com.thoughtfocus.leave.formbean.QueryBean;
import com.thoughtfocus.leave.formbean.TaskBean;
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
	
	@RequestMapping(value="/applyLeave", method=RequestMethod.POST)
	public String applyLeave(@ModelAttribute("leaveBean") @Valid LeaveBean leaveBean,final BindingResult result,Map<String, Object> map,HttpSession session,HttpServletRequest req,Model model){
		
		bookmarkFormValidator.validateLeave(leaveBean, result);
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

			if (null==applyleave) {
			model.addAttribute(QueryConstants.USER_ERROR_MSG, QueryConstants.ERROR_MSG_APPLY_LEAVE_FAILED);
			}else {
				model.addAttribute(QueryConstants.USER_ERROR_MSG, QueryConstants.ERROR_MSG_APPLY_LEAVE_SUCCESS);
			}
			} catch (Exception e) {
			e.printStackTrace();
			}
				
		try {
			queryResult = queryManager.searchBookmarks();
		} catch (Exception e) {
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
			e.printStackTrace();
		}		
		map.put("holidayList", holidaylist);
		return "HolidayList";
		
	}
	
	@RequestMapping(value="/leavesummary", method=RequestMethod.GET)
	public String leavesummary(@ModelAttribute("queryBean") @Valid QueryBean queryBean,final BindingResult result,Map<String, Object> map,HttpSession session,HttpServletRequest req,Model model){
		User user=(User) session.getAttribute(QueryConstants.LOGGED_IN_USER);
		List<LeaveSummary> leavesummary = null;
		try {
			leavesummary = queryManager.leaveSummary(user);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		map.put("leaveSummary", leavesummary);
		return "LeaveSummary";
		
	}
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home(@ModelAttribute("taskBean") @Valid TaskBean taskBean,final BindingResult result,Map<String, Object> map,HttpSession session,HttpServletRequest req,Model model){
		List<LeaveType> queryResult = null;
		
	
			
			try {
				queryResult = queryManager.searchBookmarks();
				} catch (Exception e) {
				e.printStackTrace();
			}
			map.put("queryResults", queryResult);
			return "QueryResults";
		}
		

	@RequestMapping(value="/timesheet", method=RequestMethod.GET)
	public String timesheet(Map<String, Object> map,HttpSession session,HttpServletRequest req,Model model){
		List<Projects> projectList = null;
		List<Tasks> taskList = null;
		List<TaskSummary> taskSummary = new ArrayList<TaskSummary>();
		User user = (User) session.getAttribute(QueryConstants.LOGGED_IN_USER);
		

			try {
				projectList = queryManager.projectList();
				taskList = queryManager.taskList();
				taskSummary=queryManager.taskSummary(user);
				} catch (Exception e) {
				e.printStackTrace();
			}
			map.put("projectList", projectList);
			map.put("taskList", taskList);
			map.put("taskSummary", taskSummary);
			return "TaskManager";

		
	}
	
	@RequestMapping(value="/addTask", method=RequestMethod.POST)
	public String addTask(@ModelAttribute("taskBean") @Valid TaskBean taskBean,final BindingResult result,Map<String, Object> map,HttpSession session,HttpServletRequest req,Model model){
		System.out.println("hi");
		List<Projects> projectList = null;
		List<Tasks> taskList = null;
		List<TaskSummary> taskSummary = new ArrayList<TaskSummary>();
		User user = null;
		try {
			user=(User) session.getAttribute(QueryConstants.LOGGED_IN_USER);
			TaskSummary addTask =  queryManager.addTask(taskBean,user);
			if (null==addTask) {
			model.addAttribute(QueryConstants.USER_ERROR_MSG, QueryConstants.ERROR_MSG_ADD_TASK_FAILED);
			}else {
				model.addAttribute(QueryConstants.USER_ERROR_MSG, QueryConstants.ERROR_MSG_ADD_TASK_SUCCESS);
			}
			} catch (Exception e) {
			e.printStackTrace();
			}
				
		try {
			projectList = queryManager.projectList();
			taskList = queryManager.taskList();
			taskSummary=queryManager.taskSummary(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		map.put("projectList", projectList);
		map.put("taskList", taskList);
		map.put("taskSummary", taskSummary);
		return "TaskManager";
	}
	
	@RequestMapping(value="/calendar", method=RequestMethod.GET)
	public String calendar(Map<String, Object> map,HttpSession session,HttpServletRequest req,Model model){
		System.out.println("hi");
		List<TaskSummary> taskSummary = new ArrayList<TaskSummary>();
		try{
			User user=(User) session.getAttribute(QueryConstants.LOGGED_IN_USER);
			taskSummary=queryManager.taskSummary(user);
		}catch (Exception e) {
			e.printStackTrace();
		}
		map.put("taskSummary", taskSummary);
		return "calendar";

		
	}
	
	@RequestMapping(value="/calendarMonthPrintView", method=RequestMethod.GET)
	public String calendarMonthPrintView(Map<String, Object> map,HttpSession session,HttpServletRequest req,Model model){
		System.out.println("hi2");
		return "calendarMonthPrintView";

		
	}
}
