package com.thoughtfocus.leave.dao;

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
 * The dao interface for bookmark
 * @author manaswita.mishra
 *
 */
public interface QueryDAO
{
	
	/**
	 * fire the query
	 * @param tagFilter
	 * @return
	 */
	public List<LeaveType> searchBookmarks();
	
	public List<HolidayList> holidayList();
	
	public List<LeaveSummary> leaveSummary(User user);

	public User validateUser(QueryBean queryBean) throws Exception;
	
	public LeaveSummary applyLeave (LeaveBean leaveBean, User user) throws Exception;
	
	public List<Projects> projectList();
	
	public List<Tasks> taskList();

	public TaskSummary addTask(TaskBean taskBean, User user) throws Exception;

	public List<TaskSummary> taskSummary(User user);
	
	
}
