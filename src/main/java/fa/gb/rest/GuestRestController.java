package fa.gb.rest;

import fa.gb.pojo.guest.Guest;
import fa.gb.pojo.guest.GuestInput;
import fa.gb.rest.request.guest.ActivateRequest;
import fa.gb.rest.request.guest.CreateRequest;
import fa.gb.rest.response.guest.DeleteResponse;
import fa.gb.rest.response.guest.UpdateResponse;
import fa.gb.rest.strings.GuestRestControllerStrings;
import fa.gb.service.GuestService;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import fa.gb.rest.request.guest.UpdateRequest;
import fa.gb.rest.response.guest.ActivateResponse;
import fa.gb.rest.response.guest.CreateResponse;

import java.util.List;
import java.util.UUID;

@RestController
public class GuestRestController {

	private static final Logger logger = LogManager.getLogger(GuestRestController.class);
	private static final String typeValue = "guest";

	@Autowired
	GuestService guestService;

	@CrossOrigin("*")
	@RequestMapping(value = GuestRestControllerStrings.CREATE_URL_VALUE,
			method = RequestMethod.POST)
	@ApiOperation(value = GuestRestControllerStrings.CREATE_DESCRIPTION_VALUE)
	public ResponseEntity<CreateResponse> create(@RequestBody CreateRequest createRequest) {
		String responseMessage = "";

		// Check to make sure that the request body is not null
		if(createRequest == null) {
			responseMessage = "The request body is incorrect and therefore being read as null.";
			logger.error(responseMessage);
			CreateResponse createResponse = new CreateResponse(false, "", responseMessage);
			return new ResponseEntity<CreateResponse>(createResponse, HttpStatus.BAD_REQUEST);
		}

		// Retrieve the input from the request
		GuestInput guestInput = createRequest.getGuestInput();
		if(guestInput == null) {
			responseMessage = "The " + typeValue + " that is trying to be created, has an incorrect structure.";
			logger.error(responseMessage);
			CreateResponse createResponse = new CreateResponse(false, "", responseMessage);
			return new ResponseEntity<CreateResponse>(createResponse, HttpStatus.BAD_REQUEST);
		}

		// Validate the request
		UUID id = UUID.randomUUID();
		Guest guest = guestInput.ValidateAndConvert(id);
		if(guest == null) {
			responseMessage = "The " + typeValue + " that is trying to be created has invalid properties.";
			logger.error(responseMessage);
			CreateResponse createResponse = new CreateResponse(false, "", responseMessage);
			return new ResponseEntity<CreateResponse>(createResponse, HttpStatus.BAD_REQUEST);
		}

		// Check to make sure the guest does not already exists
		boolean exists = guestService.doesGuestExist(id, guest);
		if(exists) {
			responseMessage = "The " + typeValue + " that is trying to be created already exists.";
			logger.debug(responseMessage);
			CreateResponse createResponse = new CreateResponse(false, "", responseMessage);
			return new ResponseEntity<CreateResponse>(createResponse, HttpStatus.OK);
		}

		// If everything is ok, create the guest
		guestService.create(id, guest);

		// Generate the response and return
		responseMessage = "The " + typeValue + " was successfully created.";
		logger.debug(responseMessage);
		CreateResponse createResponse = new CreateResponse(true, responseMessage, "");
		return new ResponseEntity<CreateResponse>(createResponse, HttpStatus.CREATED);
	}

	@CrossOrigin("*")
	@RequestMapping(value = GuestRestControllerStrings.READ_BY_ID_URL_VALUE,
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = GuestRestControllerStrings.READ_BY_ID_DESCRIPTION_VALUE)
	public ResponseEntity<Guest> readById(@PathVariable(GuestRestControllerStrings.PARAMETER_GUEST_ID) UUID id) {
		String responseMessage = "";

		// Check to make sure that the request is not null
		if(id == null) {
			responseMessage = "The request parameter is incorrect.";
			logger.error(responseMessage);
			return new ResponseEntity<Guest>(HttpStatus.BAD_REQUEST);
		}

		// Check to make sure the guest exists for the Id specified
		Guest currentGuest = guestService.readById(id);
		if(currentGuest == null) {
			responseMessage = "The " + typeValue + " that is trying to be read, does not exist.";
			logger.error(responseMessage);
			return new ResponseEntity<Guest>(HttpStatus.NOT_FOUND);
		}

		// Generate the response and return
		responseMessage = "The " + typeValue + " was successfully read.";
		logger.debug(responseMessage);
		return new ResponseEntity<Guest>(currentGuest, HttpStatus.OK);
	}

	@CrossOrigin("*")
	@RequestMapping(value = GuestRestControllerStrings.READ_ALL_URL_VALUE,
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = GuestRestControllerStrings.READ_ALL_DESCRIPTION_VALUE)
	public ResponseEntity<List<Guest>> readAll() {
		String responseMessage = "";

		List<Guest> guests = guestService.readAll();
		if(guests == null) {
			responseMessage = "There was an error trying to retrieve all " + typeValue + "s.";
			logger.error(responseMessage);
			return new ResponseEntity<List<Guest>>(HttpStatus.NOT_FOUND);
		}

		// Generate the response and return
		responseMessage = "All " + typeValue + "s were successfully read.";
		logger.debug(responseMessage);
		return new ResponseEntity<List<Guest>>(guests, HttpStatus.OK);
	}

	@CrossOrigin("*")
	@RequestMapping(value = GuestRestControllerStrings.READ_ALL_ACTIVE_URL_VALUE,
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = GuestRestControllerStrings.READ_ALL_ACTIVE_DESCRIPTION_VALUE)
	public ResponseEntity<List<Guest>> readAllActive(@PathVariable(GuestRestControllerStrings.PARAMETER_ACTIVE_ID) boolean active) {
		String responseMessage = "";

		List<Guest> guests = guestService.readAllActive(active);
		if(guests == null) {
			responseMessage = "There was an error trying to retrieve all active " + typeValue + "s.";
			logger.error(responseMessage);
			return new ResponseEntity<List<Guest>>(HttpStatus.NOT_FOUND);
		}

		// Generate the response and return
		responseMessage = "All active " + typeValue + "s were successfully read.";
		logger.debug(responseMessage);
		return new ResponseEntity<List<Guest>>(guests, HttpStatus.OK);
	}

	@CrossOrigin("*")
	@RequestMapping(value = GuestRestControllerStrings.UPDATE_BY_ID_URL_VALUE,
			method = RequestMethod.PUT)
	@ApiOperation(value = GuestRestControllerStrings.UPDATE_BY_ID_DESCRIPTION_VALUE)
	public ResponseEntity<UpdateResponse> update(@PathVariable(GuestRestControllerStrings.PARAMETER_GUEST_ID) UUID id,
												 @RequestBody UpdateRequest updateRequest) {
		String responseMessage = "";

		// Check to make sure that the request is not null
		if(id == null) {
			responseMessage = "The request parameter is incorrect.";
			logger.error(responseMessage);
			UpdateResponse updateResponse = new UpdateResponse(false, "", responseMessage);
			return new ResponseEntity<UpdateResponse>(updateResponse, HttpStatus.BAD_REQUEST);
		}

		// Check to make sure that the request body is not null
		if(updateRequest == null) {
			responseMessage = "The request body is incorrect.";
			logger.error(responseMessage);
			UpdateResponse updateResponse = new UpdateResponse(false, "", responseMessage);
			return new ResponseEntity<UpdateResponse>(updateResponse, HttpStatus.BAD_REQUEST);
		}

		// Retrieve the input from the request
		GuestInput guestInput = updateRequest.getInput();
		if(guestInput == null) {
			responseMessage = "The " + typeValue + " that is trying to be updated, has an incorrect request structure.";
			logger.error(responseMessage);
			UpdateResponse updateResponse = new UpdateResponse(false, "", responseMessage);
			return new ResponseEntity<UpdateResponse>(updateResponse, HttpStatus.BAD_REQUEST);
		}

		// Validate the request
		Guest guest = guestInput.ValidateAndConvert(id);
		if(guest == null) {
			responseMessage = "The " + typeValue + " that is trying to be updated, has invalid properties.";
			logger.error(responseMessage);
			UpdateResponse updateResponse = new UpdateResponse(false, "", responseMessage);
			return new ResponseEntity<UpdateResponse>(updateResponse, HttpStatus.BAD_REQUEST);
		}

		// Check to make sure the guest exists for the Id specified
		Guest currentGuest = guestService.readById(id);
		if(currentGuest == null) {
			responseMessage = "The " + typeValue + " that is trying to be updated, does not exist.";
			logger.error(responseMessage);
			UpdateResponse updateResponse = new UpdateResponse(false, "", responseMessage);
			return new ResponseEntity<UpdateResponse>(updateResponse, HttpStatus.NOT_FOUND);
		}

		// If everything is ok, update the guest
		guestService.update(guest);

		// Generate the response and return
		responseMessage = "The " + typeValue + "was successfully updated.";
		logger.debug(responseMessage);
		UpdateResponse updateResponse = new UpdateResponse(true, responseMessage, "");
		return new ResponseEntity<UpdateResponse>(updateResponse, HttpStatus.OK);
	}

	@CrossOrigin("*")
	@RequestMapping(value = GuestRestControllerStrings.ACTIVATE_URL_VALUE,
			method = RequestMethod.PUT)
	@ApiOperation(value = GuestRestControllerStrings.ACTIVATE_DESCRIPTION_VALUE)
	public ResponseEntity<ActivateResponse> activate(@PathVariable(GuestRestControllerStrings.PARAMETER_GUEST_ID) UUID id,
													 @RequestBody ActivateRequest activateRequest) {
		String responseMessage = "";

		// Check to make sure that the request is not null
		if(id == null) {
			responseMessage = "The request parameter is incorrect.";
			logger.error(responseMessage);
			ActivateResponse activateResponse = new ActivateResponse(false, "", responseMessage);
			return new ResponseEntity<ActivateResponse>(activateResponse, HttpStatus.BAD_REQUEST);
		}

		// Check to make sure that the request body is not null
		if(activateRequest == null) {
			responseMessage = "The request body is incorrect.";
			logger.error(responseMessage);
			ActivateResponse activateResponse = new ActivateResponse(false, "", responseMessage);
			return new ResponseEntity<ActivateResponse>(activateResponse, HttpStatus.BAD_REQUEST);
		}

		// Retrieve the activation value from the request
		boolean activate = activateRequest.getActivate();

		// Check to make sure the guest exists for the Id specified
		String activatedText = activate ? "activated" : "deactivated";
		Guest currentGuest = guestService.readById(id);
		if(currentGuest == null) {
			responseMessage = "The " + typeValue + " that is trying to be " + activatedText + ", does not exist.";
			logger.error(responseMessage);
			ActivateResponse activateResponse = new ActivateResponse(false, "", responseMessage);
			return new ResponseEntity<ActivateResponse>(activateResponse, HttpStatus.NOT_FOUND);
		}

		// If everything is ok, activate the guest
		guestService.activate(id, activateRequest.getActivate());

		// Generate the response and return
		responseMessage = "The " + typeValue + " was successfully " + activatedText + ".";
		logger.debug(responseMessage);
		ActivateResponse activateResponse = new ActivateResponse(true, responseMessage, "");
		return new ResponseEntity<ActivateResponse>(activateResponse, HttpStatus.OK);
	}

	@CrossOrigin("*")
	@RequestMapping(value = GuestRestControllerStrings.DELETE_BY_ID_URL_VALUE,
			method = RequestMethod.DELETE)
	@ApiOperation(value = GuestRestControllerStrings.DELETE_BY_ID_DESCRIPTION_VALUE)
	public ResponseEntity<DeleteResponse> deleteById(@PathVariable(GuestRestControllerStrings.PARAMETER_GUEST_ID) UUID id) {
		String responseMessage = "";

		// Check to make sure that the request is not null
		if(id == null) {
			responseMessage = "The request parameter is incorrect.";
			logger.error(responseMessage);
			DeleteResponse deleteResponse = new DeleteResponse(false, "", responseMessage);
			return new ResponseEntity<DeleteResponse>(deleteResponse, HttpStatus.BAD_REQUEST);
		}

		// Check to make sure the guest exists for the Id specified
		Guest currentGuest = guestService.readById(id);
		if(currentGuest == null) {
			responseMessage = "The " + typeValue + " that is trying to be deleted, does not exist.";
			logger.error(responseMessage);
			DeleteResponse deleteResponse = new DeleteResponse(false, "", responseMessage);
			return new ResponseEntity<DeleteResponse>(deleteResponse, HttpStatus.NOT_FOUND);
		}

		// If everything is ok, delete the guest
		guestService.deleteById(id);

		//Generate the response and return
		responseMessage = "The " + typeValue + " was successfully deleted.";
		logger.debug(responseMessage);
		DeleteResponse deleteResponse = new DeleteResponse(true, responseMessage, "", id);
		return new ResponseEntity<DeleteResponse>(deleteResponse, HttpStatus.OK);
	}

	@CrossOrigin("*")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = GuestRestControllerStrings.DELETE_ALL_URL_VALUE,
			method = RequestMethod.DELETE)
	@ApiOperation(value = GuestRestControllerStrings.DELETE_ALL_DESCRIPTION_VALUE)
	public ResponseEntity<DeleteResponse> deleteAll() {
		String responseMessage = "";

		// If everything is ok, delete all guest
		guestService.deleteAll();

		//Generate the response and return
		responseMessage = "All " + typeValue + "s were successfully deleted.";
		logger.debug(responseMessage);
		DeleteResponse deleteResponse = new DeleteResponse(true, responseMessage, "");
		return new ResponseEntity<DeleteResponse>(deleteResponse, HttpStatus.OK);
	}
}
