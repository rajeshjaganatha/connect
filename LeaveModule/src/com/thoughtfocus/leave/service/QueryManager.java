package com.thoughtfocus.leave.service;

import java.util.List;

import com.thoughtfocus.leave.domain.HolidayList;
import com.thoughtfocus.leave.domain.LeaveSummary;
import com.thoughtfocus.leave.domain.LeaveType;
import com.thoughtfocus.leave.domain.User;
import com.thoughtfocus.leave.formbean.LeaveBean;
import com.thoughtfocus.leave.formbean.QueryBean;

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
	
	//public int applyLeave(LeaveBean leaveBean, User user) throws Exception;

	public List<HolidayList> holidayList() throws Exception;



}
