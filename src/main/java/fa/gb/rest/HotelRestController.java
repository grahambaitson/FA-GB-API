package fa.gb.rest;

import fa.gb.pojo.hotel.Hotel;
import fa.gb.pojo.hotel.HotelInput;
import fa.gb.rest.request.hotel.ActivateRequest;
import fa.gb.rest.request.hotel.CreateRequest;
import fa.gb.rest.request.hotel.UpdateRequest;
import fa.gb.rest.response.hotel.ActivateResponse;
import fa.gb.rest.response.hotel.CreateResponse;
import fa.gb.rest.response.hotel.UpdateResponse;
import fa.gb.rest.strings.HotelRestControllerStrings;
import fa.gb.service.HotelService;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import fa.gb.rest.response.hotel.DeleteResponse;

import java.util.List;
import java.util.UUID;

@RestController
public class HotelRestController {

	private static final Logger logger = LogManager.getLogger(HotelRestController.class);
	private static final String typeValue = "hotel";

	@Autowired
    HotelService hotelService;

	@CrossOrigin("*")
	@RequestMapping(value = HotelRestControllerStrings.CREATE_URL_VALUE,
			method = RequestMethod.POST)
	@ApiOperation(value = HotelRestControllerStrings.CREATE_DESCRIPTION_VALUE)
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
		HotelInput hotelInput = createRequest.getHotelInput();
		if(hotelInput == null) {
			responseMessage = "The " + typeValue + " that is trying to be created, has an incorrect structure.";
			logger.error(responseMessage);
			CreateResponse createResponse = new CreateResponse(false, "", responseMessage);
			return new ResponseEntity<CreateResponse>(createResponse, HttpStatus.BAD_REQUEST);
		}

		// Validate the request
		UUID id = UUID.randomUUID();
		Hotel hotel = hotelInput.ValidateAndConvert(id);
		if(hotel == null) {
			responseMessage = "The " + typeValue + " that is trying to be created has invalid properties.";
			logger.error(responseMessage);
			CreateResponse createResponse = new CreateResponse(false, "", responseMessage);
			return new ResponseEntity<CreateResponse>(createResponse, HttpStatus.BAD_REQUEST);
		}

		// Check to make sure the hotel does not already exists
		boolean exists = hotelService.doesHotelExist(id, hotel);
		if(exists) {
			responseMessage = "The " + typeValue + " that is trying to be created already exists.";
			logger.debug(responseMessage);
			CreateResponse createResponse = new CreateResponse(false, "", responseMessage);
			return new ResponseEntity<CreateResponse>(createResponse, HttpStatus.OK);
		}

		// If everything is ok, create the hotel
		hotelService.create(id, hotel);

		// Generate the response and return
		responseMessage = "The " + typeValue + " was successfully created.";
		logger.debug(responseMessage);
		CreateResponse createResponse = new CreateResponse(true, responseMessage, "");
		return new ResponseEntity<CreateResponse>(createResponse, HttpStatus.CREATED);
	}

	@CrossOrigin("*")
	@RequestMapping(value = HotelRestControllerStrings.READ_BY_ID_URL_VALUE,
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = HotelRestControllerStrings.READ_BY_ID_DESCRIPTION_VALUE)
	public ResponseEntity<Hotel> readById(@PathVariable(HotelRestControllerStrings.PARAMETER_HOTEL_ID) UUID id) {
		String responseMessage = "";

		// Check to make sure that the request is not null
		if(id == null) {
			responseMessage = "The request parameter is incorrect.";
			logger.error(responseMessage);
			return new ResponseEntity<Hotel>(HttpStatus.BAD_REQUEST);
		}

		// Check to make sure the hotel exists for the Id specified
		Hotel currentHotel = hotelService.readById(id);
		if(currentHotel == null) {
			responseMessage = "The " + typeValue + " that is trying to be read, does not exist.";
			logger.error(responseMessage);
			return new ResponseEntity<Hotel>(HttpStatus.NOT_FOUND);
		}

		// Generate the response and return
		responseMessage = "The " + typeValue + " was successfully read.";
		logger.debug(responseMessage);
		return new ResponseEntity<Hotel>(currentHotel, HttpStatus.OK);
	}

	@CrossOrigin("*")
	@RequestMapping(value = HotelRestControllerStrings.READ_ALL_URL_VALUE,
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = HotelRestControllerStrings.READ_ALL_DESCRIPTION_VALUE)
	public ResponseEntity<List<Hotel>> readAll() {
		String responseMessage = "";

		List<Hotel> hotels = hotelService.readAll();
		if(hotels == null) {
			responseMessage = "There was an error trying to retrieve all " + typeValue + "s.";
			logger.error(responseMessage);
			return new ResponseEntity<List<Hotel>>(HttpStatus.NOT_FOUND);
		}

		// Generate the response and return
		responseMessage = "All " + typeValue + "s were successfully read.";
		logger.debug(responseMessage);
		return new ResponseEntity<List<Hotel>>(hotels, HttpStatus.OK);
	}

	@CrossOrigin("*")
	@RequestMapping(value = HotelRestControllerStrings.READ_ALL_ACTIVE_URL_VALUE,
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = HotelRestControllerStrings.READ_ALL_ACTIVE_DESCRIPTION_VALUE)
	public ResponseEntity<List<Hotel>> readAllActive(@PathVariable(HotelRestControllerStrings.PARAMETER_ACTIVE_ID) boolean active) {
		String responseMessage = "";

		List<Hotel> hotels= hotelService.readAllActive(active);
		if(hotels == null) {
			responseMessage = "There was an error trying to retrieve all active " + typeValue + "s.";
			logger.error(responseMessage);
			return new ResponseEntity<List<Hotel>>(HttpStatus.NOT_FOUND);
		}

		// Generate the response and return
		responseMessage = "All active " + typeValue + "s were successfully read.";
		logger.debug(responseMessage);
		return new ResponseEntity<List<Hotel>>(hotels, HttpStatus.OK);
	}

	@CrossOrigin("*")
	@RequestMapping(value = HotelRestControllerStrings.UPDATE_BY_ID_URL_VALUE,
			method = RequestMethod.PUT)
	@ApiOperation(value = HotelRestControllerStrings.UPDATE_BY_ID_DESCRIPTION_VALUE)
	public ResponseEntity<UpdateResponse> update(@PathVariable(HotelRestControllerStrings.PARAMETER_HOTEL_ID) UUID id,
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
		HotelInput hotelInput = updateRequest.getInput();
		if(hotelInput == null) {
			responseMessage = "The " + typeValue + " that is trying to be updated, has an incorrect request structure.";
			logger.error(responseMessage);
			UpdateResponse updateResponse = new UpdateResponse(false, "", responseMessage);
			return new ResponseEntity<UpdateResponse>(updateResponse, HttpStatus.BAD_REQUEST);
		}

		// Validate the request
		Hotel hotel = hotelInput.ValidateAndConvert(id);
		if(hotel == null) {
			responseMessage = "The " + typeValue + " that is trying to be updated, has invalid properties.";
			logger.error(responseMessage);
			UpdateResponse updateResponse = new UpdateResponse(false, "", responseMessage);
			return new ResponseEntity<UpdateResponse>(updateResponse, HttpStatus.BAD_REQUEST);
		}

		// Check to make sure the hotel exists for the Id specified
		Hotel currentHotel = hotelService.readById(id);
		if(currentHotel == null) {
			responseMessage = "The " + typeValue + " that is trying to be updated, does not exist.";
			logger.error(responseMessage);
			UpdateResponse updateResponse = new UpdateResponse(false, "", responseMessage);
			return new ResponseEntity<UpdateResponse>(updateResponse, HttpStatus.NOT_FOUND);
		}

		// If everything is ok, update the hotel
		hotelService.update(hotel);

		// Generate the response and return
		responseMessage = "The " + typeValue + "was successfully updated.";
		logger.debug(responseMessage);
		UpdateResponse updateResponse = new UpdateResponse(true, responseMessage, "");
		return new ResponseEntity<UpdateResponse>(updateResponse, HttpStatus.OK);
	}

	@CrossOrigin("*")
	@RequestMapping(value = HotelRestControllerStrings.ACTIVATE_URL_VALUE,
			method = RequestMethod.PUT)
	@ApiOperation(value = HotelRestControllerStrings.ACTIVATE_DESCRIPTION_VALUE)
	public ResponseEntity<ActivateResponse> activate(@PathVariable(HotelRestControllerStrings.PARAMETER_HOTEL_ID) UUID id,
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

		// Check to make sure the hotel exists for the Id specified
		String activatedText = activate ? "activated" : "deactivated";
		Hotel currentHotel = hotelService.readById(id);
		if(currentHotel == null) {
			responseMessage = "The " + typeValue + " that is trying to be " + activatedText + ", does not exist.";
			logger.error(responseMessage);
			ActivateResponse activateResponse = new ActivateResponse(false, "", responseMessage);
			return new ResponseEntity<ActivateResponse>(activateResponse, HttpStatus.NOT_FOUND);
		}

		// If everything is ok, activate the hotel
		hotelService.activate(id, activateRequest.getActivate());

		// Generate the response and return
		responseMessage = "The " + typeValue + " was successfully " + activatedText + ".";
		logger.debug(responseMessage);
		ActivateResponse activateResponse = new ActivateResponse(true, responseMessage, "");
		return new ResponseEntity<ActivateResponse>(activateResponse, HttpStatus.OK);
	}

	@CrossOrigin("*")
	@RequestMapping(value = HotelRestControllerStrings.DELETE_BY_ID_URL_VALUE,
			method = RequestMethod.DELETE)
	@ApiOperation(value = HotelRestControllerStrings.DELETE_BY_ID_DESCRIPTION_VALUE)
	public ResponseEntity<DeleteResponse> deleteById(@PathVariable(HotelRestControllerStrings.PARAMETER_HOTEL_ID) UUID id) {
		String responseMessage = "";

		// Check to make sure that the request is not null
		if(id == null) {
			responseMessage = "The request parameter is incorrect.";
			logger.error(responseMessage);
			DeleteResponse deleteResponse = new DeleteResponse(false, "", responseMessage);
			return new ResponseEntity<DeleteResponse>(deleteResponse, HttpStatus.BAD_REQUEST);
		}

		// Check to make sure the hotel exists for the Id specified
		Hotel currentHotel = hotelService.readById(id);
		if(currentHotel == null) {
			responseMessage = "The " + typeValue + " that is trying to be deleted, does not exist.";
			logger.error(responseMessage);
			DeleteResponse deleteResponse = new DeleteResponse(false, "", responseMessage);
			return new ResponseEntity<DeleteResponse>(deleteResponse, HttpStatus.NOT_FOUND);
		}

		// If everything is ok, delete the hotel
		hotelService.deleteById(id);

		//Generate the response and return
		responseMessage = "The " + typeValue + " was successfully deleted.";
		logger.debug(responseMessage);
		DeleteResponse deleteResponse = new DeleteResponse(true, responseMessage, "", id);
		return new ResponseEntity<DeleteResponse>(deleteResponse, HttpStatus.OK);
	}

	@CrossOrigin("*")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = HotelRestControllerStrings.DELETE_ALL_URL_VALUE,
			method = RequestMethod.DELETE)
	@ApiOperation(value = HotelRestControllerStrings.DELETE_ALL_DESCRIPTION_VALUE)
	public ResponseEntity<DeleteResponse> deleteAll() {
		String responseMessage = "";

		// If everything is ok, delete all hotel
		hotelService.deleteAll();

		//Generate the response and return
		responseMessage = "All " + typeValue + "s were successfully deleted.";
		logger.debug(responseMessage);
		DeleteResponse deleteResponse = new DeleteResponse(true, responseMessage, "");
		return new ResponseEntity<DeleteResponse>(deleteResponse, HttpStatus.OK);
	}
}
