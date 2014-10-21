package com.thoughtfocus.leave.formbean;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.thoughtfocus.leave.domain.LeaveType;

public class BeanUtilsCopy {

	public static void main(String[] args) {

		LeaveType fromBean = new LeaveType();
		LeaveTypeSummaryBean toBean = new LeaveTypeSummaryBean();
		try {
			System.out.println("Copying properties from fromBean to toBean");
			BeanUtils.copyProperties(toBean, fromBean);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		System.out.println(ToStringBuilder.reflectionToString(fromBean));
		System.out.println(ToStringBuilder.reflectionToString(toBean));
	}
}