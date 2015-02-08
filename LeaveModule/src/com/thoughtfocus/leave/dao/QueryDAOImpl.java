package com.thoughtfocus.leave.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.thoughtfocus.leave.domain.User;
import com.thoughtfocus.leave.formbean.LeaveBean;
import com.thoughtfocus.leave.formbean.QueryBean;

/**
 * The dao layer for bookmark operations
 * @author manaswita.mishra
 *
 */
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
			Criteria crit = session.createCriteria(LeaveType.class);

					
			leaveObjectList=(List<LeaveType>)crit.list();
			/*Query query = session.createQuery("SELECT DISTINCT LT.leaveTypeId,  LT.leaveType, LT.allotment,"+
			"LT.allotment-SUM(LS.leaveAvailed) as available "+
			"FROM LeaveType LT, LeaveSummary LS WHERE LS.leaveTypeId = LT.leaveTypeId GROUP BY LT.leaveTypeId,LT.leaveType,LT.allotment");
			
			leaveObjectList=(List<LeaveType>)query.list();*/

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
			Criteria crit =session.createCriteria(LeaveSummary.class);
			crit.add(Restrictions.eq("userId", user.getUserId()));
			leavesummary=(List<LeaveSummary>)crit.list();
						
		
		
		
		
		Criteria criteria = session.createCriteria(LeaveSummary.class, "leavesummary");
        criteria.setFetchMode("leavesummary.user", FetchMode.JOIN);
        criteria.createAlias("leavesummary.user", "user"); // inner join by default
 
        ProjectionList columns = Projections.projectionList()
                        .add(Projections.property("user.userName"))
                        .add(Projections.property("leaveAvailed"));
        criteria.setProjection(columns);
 
        List<Object[]> list = criteria.list();
        for(Object[] arr : list){
            System.out.println(Arrays.toString(arr));
        }
		
		
        return leavesummary;
		
}
	@Override
	public User validateUser(QueryBean queryBean) throws Exception{


		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("userName", queryBean.getUserName()));
		//criteria.add(Restrictions.eq("password", CommonUtil.encrypt(queryBean.getPassword())));
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
	
	public int checkexistingleave(Date fromDate, Date toDate, User user) {
		/*SELECT COUNT(USER_ID) 
		  FROM T_LEAVE_SUMMARY WHERE 
		  ('2014-01-15'<=TO_DATE) and ('2014-01-16'>=FROM_DATE) 
		  and USER_ID=1;*/
		List<LeaveSummary> list =new ArrayList<LeaveSummary>();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(LeaveSummary.class);
		criteria.add(Restrictions.ge("toDate", fromDate));
		criteria.add(Restrictions.le("fromDate", toDate));
		criteria.add(Restrictions.eq("userId", user.getUserId()));
		
		list = (List<LeaveSummary>) criteria.list();
		int count = list.size();
		System.out.println(count);
		return count;
		
	}

	public Date stringtoDate(String dateasString) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = formatter.parse(dateasString);
			
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return date;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}
