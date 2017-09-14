package fa.gb.rest;

import fa.gb.rest.response.LoginResponse;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import fa.gb.rest.strings.UserRestControllerStrings;

@RestController
public class UserRestController {

	private static final Logger logger = LogManager.getLogger(UserRestController.class);
	
	@CrossOrigin("*")
	@RequestMapping(value = UserRestControllerStrings.USER_LOGIN_URL, 
					method = RequestMethod.POST)
	@ApiOperation(value = UserRestControllerStrings.USER_LOGIN_DESCRIPTION)
	public ResponseEntity<LoginResponse> login(@RequestParam(value=UserRestControllerStrings.GRANT_TYPE) String grantType,
                                               @RequestParam(value=UserRestControllerStrings.USERNAME) String username,
                                               @RequestParam(value=UserRestControllerStrings.PASSWORD) String password) {
		logger.info("In login function");
		String errorMessage = "";
		LoginResponse loginResponse = null;		
		
		if (grantType.equals(UserRestControllerStrings.PASSWORD)) {
			
			logger.info("Password request");
			
			// hardcoded username / password for trial
			if (username.equals("admin") && password.equals("admin")) {
				// send secret token?
				loginResponse = new LoginResponse(true, "successful", "");
				return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.OK); 
			} else {
				errorMessage = UserRestControllerStrings.INVALID_USERNAME_OR_PASSWORD;
			}			
		} else {
			
			errorMessage = UserRestControllerStrings.GRANT_TYPE_PASSWORD_EXPECTED;
		}
		
		loginResponse = new LoginResponse(false, "failed", errorMessage);
		return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.UNAUTHORIZED); 
	}

}
