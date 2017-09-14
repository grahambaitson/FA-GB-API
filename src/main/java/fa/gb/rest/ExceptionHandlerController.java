package fa.gb.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import fa.gb.rest.response.BaseResponse;

@ControllerAdvice
public class ExceptionHandlerController {
	private static final Logger logger = LogManager.getLogger(ExceptionHandlerController.class);
	
	@ExceptionHandler(CannotGetJdbcConnectionException.class)
	public BaseResponse cannotConnectToDatabase(CannotGetJdbcConnectionException exception) {
		logger.error("Error connecting to database: " + exception.getMessage());
		return new BaseResponse(false, "", "Error connecting to database");
	}
	
}
