package cl.com.nissum.exceptions;

import cl.com.nissum.utils.NissumAppConstants;

public class UserEmailRepeatedException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return NissumAppConstants.USER_EMAIL_EXISTS_ERROR;
	}

}
