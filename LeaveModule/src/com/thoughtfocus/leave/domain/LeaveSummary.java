package com.thoughtfocus.leave.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

/**
 * The User domain object to represent one user
 * @author manaswita.mishra
 */

@Entity
@Table(name="T_LEAVE_SUMMARY")
public class LeaveSummary implements Serializable
{
	

	private int leaveSummaryId;
	
	@Column(name="USER_ID")
	private int userId;
	
	@Column(name="LEAVE_TYPE_ID")	
	private int leaveTypeId;
	
	@Column(name="LEAVE_AVAILED")	
	private int leaveAvailed;
	
	@Column(name="FROM_DATE")	
	private Date fromDate;

	@Column(name="TO_DATE")	
	private Date toDate;

	private List<LeaveType> leavetype;
	


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	public int getLeaveSummaryId() {
		return leaveSummaryId;
	}

	public void setLeaveSummaryId(int leaveSummaryId) {
		this.leaveSummaryId = leaveSummaryId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getLeaveTypeId() {
		return leaveTypeId;
	}

	public void setLeaveTypeId(int leaveTypeId) {
		this.leaveTypeId = leaveTypeId;
	}

	public int getLeaveAvailed() {
		return leaveAvailed;
	}

	public void setLeaveAvailed(int leaveAvailed) {
		this.leaveAvailed = leaveAvailed;
	}

	public Date getFromDate() {
		
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "leaveTypeId", insertable = false, updatable = false)
	public List<LeaveType> getLeavetype() {
		return leavetype;
	}

	public void setLeavetype(List<LeaveType> leavetype) {
		this.leavetype = leavetype;
	}

	private static final long serialVersionUID = 1L;



}
