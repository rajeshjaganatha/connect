package com.thoughtfocus.leave.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The User domain object to represent one user
 * @author manaswita.mishra
 */

@Entity
@Table(name="T_LEAVE_SUMMARY")
public class LeaveSummary implements Serializable
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
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

	private static final long serialVersionUID = 1L;



}
