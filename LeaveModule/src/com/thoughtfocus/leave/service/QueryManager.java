package com.thoughtfocus.leave.service;

import java.util.List;

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

/**
 * The service interface for bookmark
 * @author manaswita.mishra
 *
 */
public interface QueryManager {
	
	/**
	 * fire the query
	 * @param tagFilter
	 * @return
	 */
	public List<LeaveType> searchBookmarks() throws Exception;

	public User validateUser(QueryBean queryBean) throws Exception;
	
	public LeaveSummary applyLeave(LeaveBean leaveBean, User user) throws Exception;
	
	public List<HolidayList> holidayList() throws Exception;
	
	public List<LeaveSummary> leaveSummary(User user) throws Exception;

	public List<Projects> projectList() throws Exception;
	
	public List<Tasks> taskList() throws Exception;

	public TaskSummary addTask(TaskBean taskBean, User user) throws Exception;

	public List<TaskSummary> taskSummary(User user);


}
