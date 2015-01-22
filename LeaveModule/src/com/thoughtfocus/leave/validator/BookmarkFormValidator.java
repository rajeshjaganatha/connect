package com.thoughtfocus.leave.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Validator class to handle bookmark validations
 * @author manaswita.mishra
 *
 */
@Component("bookmarkFormValidator")
public class BookmarkFormValidator implements Validator
{
	/*@Override
	public boolean supports(Class<?> clazz)
	{
		return Bookmark.class.isAssignableFrom(clazz);
	}*/

	
	/**
	 * Add bookmark validations
	 * @param model
	 * @param errors
	 */
	@Override
	public void validate(Object model, Errors errors)
	{
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName","required.userName", "User Name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password","required.password", "Password is required.");
					
	}
	
	
	


	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}
}
