package com.thoughtfocus.leave.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;



/**
 * The User domain object to represent one user
 * @author manaswita.mishra
 */

@Entity
@Table(name="T_LEAVE_TYPE")
public class LeaveType implements Serializable
{
	private static final long serialVersionUID = 1L;

	
	private int leaveTypeId;
	
		
	private String leaveType;
	
		
	private int allotment;
	
	@Transient
	private int availed;
	
	private List<LeaveSummary> leavesummary;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="LEAVE_TYPE_ID")
	public int getLeaveTypeId() {
		return leaveTypeId;
	}

	public void setLeaveTypeId(int leaveTypeId) {
		this.leaveTypeId = leaveTypeId;
	}

	@Column(name="LEAVE_TYPE")
	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	@Column(name="ALLOTMENT")
	public int getAllotment() {
		return allotment;
	}

	public void setAllotment(int allotment) {
		this.allotment = allotment;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "leavetype")
	public List<LeaveSummary> getLeavesummary() {
		return leavesummary;
	}

	public void setLeavesummary(List<LeaveSummary> leavesummary) {
		this.leavesummary = leavesummary;
	}

	@Transient
	public int getAvailed() {
		return availed;
	}

	public void setAvailed(int availed) {
		this.availed = availed;
	}
	
}
