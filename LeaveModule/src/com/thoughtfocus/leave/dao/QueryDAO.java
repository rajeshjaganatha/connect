package com.thoughtfocus.leave.dao;

import java.util.List;

import com.thoughtfocus.leave.domain.HolidayList;
import com.thoughtfocus.leave.domain.LeaveSummary;
import com.thoughtfocus.leave.domain.LeaveType;
import com.thoughtfocus.leave.domain.User;
import com.thoughtfocus.leave.formbean.LeaveBean;
import com.thoughtfocus.leave.formbean.QueryBean;

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
	
	
	
}
