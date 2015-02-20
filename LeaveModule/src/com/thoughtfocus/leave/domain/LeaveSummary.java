package com.thoughtfocus.leave.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	
	private int leaveAvailed;

	private Date fromDate;
	
	private Date toDate;
		
	private int userId;
	
	private LeaveType leavetype;
	
	private int leaveTypeId;
	
	
	@Column(name="LEAVE_TYPE_ID")
	public int getLeaveTypeId() {
		return leaveTypeId;
	}

	public void setLeaveTypeId(int leaveTypeId) {
		this.leaveTypeId = leaveTypeId;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	public int getLeaveSummaryId() {
		return leaveSummaryId;
	}

	public void setLeaveSummaryId(int leaveSummaryId) {
		this.leaveSummaryId = leaveSummaryId;
	}

	@Column(name="USER_ID")
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}


	@Column(name="LEAVE_AVAILED")
	public int getLeaveAvailed() {
		return leaveAvailed;
	}

	public void setLeaveAvailed(int leaveAvailed) {
		this.leaveAvailed = leaveAvailed;
	}

	@Column(name="FROM_DATE")
	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	@Column(name="TO_DATE")
	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "LEAVE_TYPE_ID", insertable = false, updatable = false)
	public LeaveType getLeavetype() {
		return leavetype;
	}

	public void setLeavetype(LeaveType leavetype) {
		this.leavetype = leavetype;
	}

	
}
