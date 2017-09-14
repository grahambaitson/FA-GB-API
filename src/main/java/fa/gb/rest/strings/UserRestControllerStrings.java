package fa.gb.rest.strings;

public final class UserRestControllerStrings {

	// Instantiation is not allowed
	private UserRestControllerStrings() {}

	// Strings for end-points	
	public static final String USER_LOGIN_URL = "/user/login";
	
	public static final String USER_LOGIN_DESCRIPTION = "OAuth2PasswordGrant login method for authentication";
	
	public static final String PROBLEM_READING_LOGIN_FORM_VALUES = "Problem reading login form values.";
	public static final String GRANT_TYPE_PASSWORD_EXPECTED = "Incorrect grant type (grant_type = 'password' expected).";
	public static final String INVALID_USERNAME_OR_PASSWORD = "Invalid username or password.";
		
	public static final String PASSWORD = "password";
	public static final String USERNAME = "username";
	public static final String GRANT_TYPE = "grant_type";
}
