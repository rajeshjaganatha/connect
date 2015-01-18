package com.thoughtfocus.leave.formbean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;



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
