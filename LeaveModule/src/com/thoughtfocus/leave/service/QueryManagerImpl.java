package com.thoughtfocus.leave.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thoughtfocus.leave.dao.QueryDAO;
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

@Service("queryManager")
public class QueryManagerImpl implements QueryManager{
	
	@Autowired
	private QueryDAO queryDAO;
	
	/**
	 * Fire the query
	 * @param tagFilter
	 * @return
	 */
	@Override
	public List<LeaveType> searchBookmarks() throws HibernateException,Exception{
		List<LeaveType> result = new ArrayList<LeaveType>();
		result=queryDAO.searchBookmarks();
		return result;
	}
	
	@Override
	public List<HolidayList> holidayList() throws HibernateException,Exception{
		List<HolidayList> holidaylist = new ArrayList<HolidayList>();
		holidaylist=queryDAO.holidayList();
		return holidaylist;
	}
	
	@Override
	public User validateUser(QueryBean queryBean) throws Exception{
		User user=queryDAO.validateUser(queryBean);
		return user;
	}
	
	@Override
	public LeaveSummary applyLeave (LeaveBean leaveBean, User user) throws Exception{
		LeaveSummary addLeave=queryDAO.applyLeave(leaveBean, user);
		return addLeave;
	}

	@Override
	public List<LeaveSummary> leaveSummary(User user) throws Exception {
		List<LeaveSummary> leavesummary = new ArrayList<LeaveSummary>();
		leavesummary=queryDAO.leaveSummary(user);
		return leavesummary;		
	}

	@Override
	public List<Projects> projectList() throws Exception {
		List<Projects> projectList = new ArrayList<Projects>();
		projectList=queryDAO.projectList();
		return projectList;		
	}
	
	@Override
	public List<Tasks> taskList() throws Exception {
		List<Tasks> taskList = new ArrayList<Tasks>();
		taskList=queryDAO.taskList();
		return taskList;		
	}

	@Override
	public TaskSummary addTask(TaskBean taskBean, User user) throws Exception {
		TaskSummary addTask=queryDAO.addTask(taskBean, user);
		return addTask;
	}

	@Override
	public List<TaskSummary> taskSummary(User user) {
		List<TaskSummary> taskSummary = new ArrayList<TaskSummary>();
		taskSummary=queryDAO.taskSummary(user);
		return taskSummary;		
	}
}
