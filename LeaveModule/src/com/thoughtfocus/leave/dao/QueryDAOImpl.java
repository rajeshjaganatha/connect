package com.thoughtfocus.leave.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

@Repository
@Transactional
@Component("queryDAO")
public class QueryDAOImpl implements QueryDAO
{
	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * Get All Bookmarks
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<LeaveType> searchBookmarks(){
		List<LeaveType> leaveObjectList =new ArrayList<LeaveType>();
			Session session=sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(LeaveType.class, "leavetype");
			criteria.setFetchMode("leavetype.leavesummary", FetchMode.JOIN);
			criteria.createAlias("leavetype.leavesummary", "LS");
			ProjectionList check = Projections.projectionList()
					.add(Projections.groupProperty("leaveTypeId"),"leaveTypeId")
					.add(Projections.groupProperty("leaveType"),"leaveType")
					.add(Projections.groupProperty("allotment"),"allotment")
					.add(Projections.sum("LS.leaveAvailed"),"availed");
			criteria.setProjection(check);
			List<Object> list = criteria.list();
			
			for (int i = 0; i < list.size(); i++) {
				Object[] o = (Object[]) list.get(i);
				LeaveType lt = new LeaveType();
					lt.setLeaveTypeId((Integer) o[0]);
					lt.setLeaveType((String) o[1]);
					lt.setAllotment((Integer) o[2]);
					lt.setAvailed(Integer.valueOf(o[3].toString()));
					leaveObjectList.add(lt);
			}
		return leaveObjectList;
}
	
	@SuppressWarnings("unchecked")
	public List<HolidayList>  holidayList(){
		List<HolidayList> holidayObjectList =new ArrayList<HolidayList>();
			Session session=sessionFactory.getCurrentSession();
			Criteria crit =session.createCriteria(HolidayList.class);
			holidayObjectList=(List<HolidayList>)crit.list();
		return holidayObjectList;
}

	@SuppressWarnings("unchecked")
	public List<LeaveSummary>  leaveSummary(User user){
		
		List<LeaveSummary> leavesummary =new ArrayList<LeaveSummary>();
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(LeaveSummary.class);
		criteria.setFetchMode("LeaveType", FetchMode.JOIN);
		leavesummary = criteria.list();
        return leavesummary;
		
}
	@Override
	public User validateUser(QueryBean queryBean) throws Exception{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("userName", queryBean.getUserName()));
		criteria.add(Restrictions.eq("password", queryBean.getPassword()));
		User user=(User) criteria.uniqueResult();
		return user;
	}
	
	@Override
	public LeaveSummary applyLeave(LeaveBean leaveBean, User user) throws Exception{
		Session session=sessionFactory.getCurrentSession();
		int count = checkexistingleave(stringtoDate(leaveBean.getFromdate()), stringtoDate(leaveBean.getTodate()), user);
		if (count==0) {
			LeaveSummary addleave = new LeaveSummary();
			addleave.setUserId(user.getUserId());
			addleave.setLeaveTypeId(leaveBean.getLeaveTypeId());
			addleave.setLeaveAvailed(leaveBean.getLeaveAvailed());
			addleave.setFromDate(stringtoDate(leaveBean.getFromdate()));	
			addleave.setToDate(stringtoDate(leaveBean.getTodate()));
			session.save(addleave);
			return addleave;	
		} else {
		return null;	
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public int checkexistingleave(Date fromDate, Date toDate, User user) {
		List<LeaveSummary> list =new ArrayList<LeaveSummary>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(LeaveSummary.class);
		criteria.add(Restrictions.ge("toDate", fromDate));
		criteria.add(Restrictions.le("fromDate", toDate));
		criteria.add(Restrictions.eq("userId", user.getUserId()));
		list = (List<LeaveSummary>) criteria.list();
		int count = list.size();
		return count;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Projects>  projectList(){
		List<Projects> projectList =new ArrayList<Projects>();
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Projects.class);
		projectList = criteria.list();
        return projectList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Tasks>  taskList(){
		List<Tasks> taskList =new ArrayList<Tasks>();
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Tasks.class);
		taskList = criteria.list();
        return taskList;
	}
	
	@Override
	public TaskSummary addTask(TaskBean taskBean, User user) throws Exception{
		Session session=sessionFactory.getCurrentSession();
		int count = checkexistingtask( taskBean.getProjectId(), taskBean.getTaskId(), stringtoDate(taskBean.getDate()),  user);
		if (count==0) {
			TaskSummary addTask = new TaskSummary();
			addTask.setUserId(user.getUserId());
			addTask.setProjectId(taskBean.getProjectId());
			addTask.setTaskId(taskBean.getTaskId());
			addTask.setTaskDate(stringtoDate(taskBean.getDate()));
			addTask.setHours(Double.valueOf(taskBean.getHours()));
			addTask.setTaskDesc(taskBean.getTaskDesc());
			session.save(addTask);
			return addTask;	
		} else {
		return null;	
		}
	}

	@SuppressWarnings("unchecked")
	public int checkexistingtask(int projectId, int taskId, Date date, User user) {
		List<TaskSummary> list =new ArrayList<TaskSummary>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TaskSummary.class);
		criteria.add(Restrictions.eq("userId", user.getUserId()));
		criteria.add(Restrictions.eq("projectId",projectId));
		criteria.add(Restrictions.eq("taskId", taskId));
		criteria.add(Restrictions.eq("taskDate", date));
		list = (List<TaskSummary>) criteria.list();
		int count = list.size();
		return count;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<TaskSummary>  taskSummary(User user){
		List<TaskSummary> taskSummary =new ArrayList<TaskSummary>();
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(TaskSummary.class);
		criteria.setFetchMode("Projects", FetchMode.JOIN);
		taskSummary = (List<TaskSummary>) criteria.list();
        return taskSummary;
	}

	public Date stringtoDate(String dateasString) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = formatter.parse(dateasString);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		return date;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
}
