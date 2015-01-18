package com.thoughtfocus.leave.formbean;

import java.io.Serializable;
import java.math.BigDecimal;



public class LeaveTypeSummaryBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private BigDecimal leaveTypeId;
	private String leaveType;
	private int allotment;
	private int available;
	

	public BigDecimal getLeaveTypeId() {
		return leaveTypeId;
	}

	public void setLeaveTypeId(BigDecimal leaveTypeId) {
		this.leaveTypeId = leaveTypeId;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public int getAllotment() {
		return allotment;
	}

	public void setAllotment(int allotment) {
		this.allotment = allotment;
	}
	
	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

}
