package fa.gb.rest;

import fa.gb.pojo.booking.BookingDAO;
import fa.gb.pojo.booking.BookingInput;
import fa.gb.pojo.guest.Guest;
import fa.gb.pojo.hotel.Hotel;
import fa.gb.rest.request.booking.CreateMultipleRequest;
import fa.gb.rest.request.booking.UpdateRequest;
import fa.gb.rest.response.booking.ActivateResponse;
import fa.gb.rest.response.booking.CreateResponse;
import fa.gb.rest.response.booking.DeleteResponse;
import fa.gb.rest.response.booking.UpdateResponse;
import fa.gb.rest.strings.BookingRestControllerStrings;
import fa.gb.service.GuestService;
import fa.gb.util.DateUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import fa.gb.pojo.booking.Booking;
import fa.gb.rest.request.booking.ActivateRequest;
import fa.gb.rest.request.booking.CreateRequest;
import fa.gb.service.BookingService;
import fa.gb.service.HotelService;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class BookingRestController {

	private static final Logger logger = LogManager.getLogger(BookingRestController.class);
	private static final String typeValue = "booking";

	@Autowired
	BookingService bookingService;

	@Autowired
	GuestService guestService;

	@Autowired
	HotelService hotelService;

	@CrossOrigin("*")
	@RequestMapping(value = BookingRestControllerStrings.CREATE_URL_VALUE,
			method = RequestMethod.POST)
	@ApiOperation(value = BookingRestControllerStrings.CREATE_DESCRIPTION_VALUE)
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
		BookingInput bookingInput = createRequest.getInput();
		if(bookingInput == null) {
			responseMessage = "The " + typeValue + " that is trying to be created, has an incorrect structure.";
			logger.error(responseMessage);
			CreateResponse createResponse = new CreateResponse(false, "", responseMessage);
			return new ResponseEntity<CreateResponse>(createResponse, HttpStatus.BAD_REQUEST);
		}

		// Validate the request
		UUID id = UUID.randomUUID();
		Booking booking = bookingInput.ValidateAndConvert(id);
		if(booking == null) {
			responseMessage = "The " + typeValue + " that is trying to be created has invalid properties.";
			logger.error(responseMessage);
			CreateResponse createResponse = new CreateResponse(false, "", responseMessage);
			return new ResponseEntity<CreateResponse>(createResponse, HttpStatus.BAD_REQUEST);
		}

		// Retrieve the guest id
		Guest guest = guestService.readByEmailAddress(booking.getGuestEmailAddress());
		if(guest == null) {
			responseMessage = "There is no guest associated with the " + typeValue + " that is trying to be created.";
			logger.error(responseMessage);
			CreateResponse createResponse = new CreateResponse(false, "", responseMessage);
			return new ResponseEntity<CreateResponse>(createResponse, HttpStatus.BAD_REQUEST);
		}

		// Retrieve the hotel id
		Hotel hotel = hotelService.readByName(booking.getHotelName());
		if(hotel == null) {
			responseMessage = "There is no hotel associated with the " + typeValue + " that is trying to be created.";
			logger.error(responseMessage);
			CreateResponse createResponse = new CreateResponse(false, "", responseMessage);
			return new ResponseEntity<CreateResponse>(createResponse, HttpStatus.BAD_REQUEST);
		}

		// Validate the request
		BookingDAO bookingDAO = booking.ConvertToDAO(id, guest.getId(), hotel.getId());
		if(bookingDAO == null) {
			responseMessage = "The " + typeValue + " that is trying to be created has invalid properties.";
			logger.error(responseMessage);
			CreateResponse createResponse = new CreateResponse(false, "", responseMessage);
			return new ResponseEntity<CreateResponse>(createResponse, HttpStatus.BAD_REQUEST);
		}

		// Check to make sure the booking does not already exists
		boolean exists = bookingService.doesBookingExist(id, bookingDAO);
		if(exists) {
			responseMessage = "The " + typeValue + " that is trying to be created already exists.";
			logger.debug(responseMessage);
			CreateResponse createResponse = new CreateResponse(false, "", responseMessage);
			return new ResponseEntity<CreateResponse>(createResponse, HttpStatus.OK);
		}

		// If everything is ok, create the booking
		bookingService.create(id, bookingDAO);

		// Generate the response and return
		responseMessage = "The " + typeValue + " was successfully created.";
		logger.debug(responseMessage);
		CreateResponse createResponse = new CreateResponse(true, responseMessage, "");
		return new ResponseEntity<CreateResponse>(createResponse, HttpStatus.CREATED);
	}

	@CrossOrigin("*")
	@RequestMapping(value = BookingRestControllerStrings.CREATE_MULTIPLE_URL_VALUE,
			method = RequestMethod.POST)
	@ApiOperation(value = BookingRestControllerStrings.CREATE_MULTIPLE_DESCRIPTION_VALUE)
	public ResponseEntity<CreateResponse> createMultiple(@RequestBody CreateMultipleRequest createMultipleRequest) {
		String responseMessage = "";

		// Check to make sure that the request body is not null
		if(createMultipleRequest == null) {
			responseMessage = "The request body is incorrect and therefore being read as null.";
			logger.error(responseMessage);
			CreateResponse createResponse = new CreateResponse(false, "", responseMessage);
			return new ResponseEntity<CreateResponse>(createResponse, HttpStatus.BAD_REQUEST);
		}

		// Retrieve the input from the request
		List<BookingInput> bookingInputs = createMultipleRequest.getInput();
		if(bookingInputs == null) {
			responseMessage = "The " + typeValue + " that is trying to be created, has an incorrect structure.";
			logger.error(responseMessage);
			CreateResponse createResponse = new CreateResponse(false, "", responseMessage);
			return new ResponseEntity<CreateResponse>(createResponse, HttpStatus.OK);
		}

		// Loop through all the booking inputs
		for(BookingInput bookingInput : bookingInputs) {
			// Validate the request
			UUID id = UUID.randomUUID();
			Booking booking = bookingInput.ValidateAndConvert(id);
			if(booking == null) {
				responseMessage = "The " + typeValue + " that is trying to be created has invalid properties.";
				logger.error(responseMessage);
				CreateResponse createResponse = new CreateResponse(false, "", responseMessage);
				return new ResponseEntity<CreateResponse>(createResponse, HttpStatus.OK);
			}

			// Retrieve the guest id
			Guest guest = guestService.readByEmailAddress(booking.getGuestEmailAddress());
			if(guest == null) {
				responseMessage = "There is no guest associated with the " + typeValue + " that is trying to be created.";
				logger.error(responseMessage);
				CreateResponse createResponse = new CreateResponse(false, "", responseMessage);
				return new ResponseEntity<CreateResponse>(createResponse, HttpStatus.OK);
			}

			// Retrieve the hotel id
			Hotel hotel = hotelService.readByName(booking.getHotelName());
			if(hotel == null) {
				responseMessage = "There is no hotel associated with the " + typeValue + " that is trying to be created.";
				logger.error(responseMessage);
				CreateResponse createResponse = new CreateResponse(false, "", responseMessage);
				return new ResponseEntity<CreateResponse>(createResponse, HttpStatus.OK);
			}

			// Validate the request
			BookingDAO bookingDAO = booking.ConvertToDAO(id, guest.getId(), hotel.getId());
			if(bookingDAO == null) {
				responseMessage = "The " + typeValue + " that is trying to be created has invalid properties.";
				logger.error(responseMessage);
				CreateResponse createResponse = new CreateResponse(false, "", responseMessage);
				return new ResponseEntity<CreateResponse>(createResponse, HttpStatus.BAD_REQUEST);
			}

			// Check to see if this is a block booking (i.e. reservation end data is more than one day after start date)
			final Date reservationStartDate = bookingDAO.getReservationStartDate();
			final Date reservationEndDate = bookingDAO.getReservationEndDate();
			int daysDifference = DateUtil.getDaysDifference(reservationStartDate, reservationEndDate);
			if(daysDifference > 1) {
				// Loop through and create single bookings for each of the days after the start date
				for(int i = 0; i < daysDifference; i++) {
					UUID singleBookingId = UUID.randomUUID();
					BookingDAO singleBookingDAO = new BookingDAO(singleBookingId,
							DateUtil.addDays(reservationStartDate, i),
							DateUtil.addDays(reservationStartDate, i + 1),
							bookingDAO.getRate(),
							bookingDAO.getGuestId(),
							bookingDAO.getHotelId());

					// Check to make sure the booking doesn't already exist
					boolean exists = bookingService.doesBookingExist(singleBookingId, singleBookingDAO);
					if(exists) {
						// If this booking already exists for the guest and the hotel for that night, just continue to next
						continue;
					}

					// If everything is ok, create the booking
					bookingService.create(singleBookingId, singleBookingDAO);
				}
			} else {
				// If the end date is not more than one day after the start day, just create the single booking if it doesn't already exist
				boolean exists = bookingService.doesBookingExist(id, bookingDAO);
				if(exists) {
					// If this booking already exists for the guest and the hotel for that night, just continue to next
					continue;
				}

				// If everything is ok, create the booking
				bookingService.create(id, bookingDAO);
			}
		}

		// Generate the response and return
		responseMessage = "The " + typeValue + "s were successfully created.";
		logger.debug(responseMessage);
		CreateResponse createResponse = new CreateResponse(true, responseMessage, "");
		return new ResponseEntity<CreateResponse>(createResponse, HttpStatus.CREATED);
	}

	@CrossOrigin("*")
	@RequestMapping(value = BookingRestControllerStrings.READ_BY_ID_URL_VALUE,
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = BookingRestControllerStrings.READ_BY_ID_DESCRIPTION_VALUE)
	public ResponseEntity<Booking> readById(@PathVariable(BookingRestControllerStrings.PARAMETER_BOOKING_ID) UUID id) {
		String responseMessage = "";

		// Check to make sure that the request is not null
		if(id == null) {
			responseMessage = "The request parameter is incorrect.";
			logger.error(responseMessage);
			return new ResponseEntity<Booking>(HttpStatus.BAD_REQUEST);
		}

		// Check to make sure the booking exists for the Id specified
		BookingDAO bookingDAO = bookingService.readById(id);
		if(bookingDAO == null) {
			responseMessage = "The " + typeValue + " that is trying to be read, does not exist.";
			logger.error(responseMessage);
			return new ResponseEntity<Booking>(HttpStatus.NOT_FOUND);
		}

		// Convert DAO object to return object
		Guest guest = guestService.readById(bookingDAO.getGuestId());
		Hotel hotel = hotelService.readById(bookingDAO.getHotelId());
		Booking booking = bookingDAO.ConvertToRest(id, guest.getEmailAddress(), hotel.getName());

		// Generate the response and return
		responseMessage = "The " + typeValue + " was successfully read.";
		logger.debug(responseMessage);
		return new ResponseEntity<Booking>(booking, HttpStatus.OK);
	}

	@CrossOrigin("*")
	@RequestMapping(value = BookingRestControllerStrings.READ_ALL_URL_VALUE,
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = BookingRestControllerStrings.READ_ALL_DESCRIPTION_VALUE)
	public ResponseEntity<List<Booking>> readAll() {
		String responseMessage = "";

		List<Booking> bookings = bookingService.readAllGuestHotelBooking();
		if(bookings == null) {
			responseMessage = "There was an error trying to retrieve all " + typeValue + "s.";
			logger.error(responseMessage);
			return new ResponseEntity<List<Booking>>(HttpStatus.NOT_FOUND);
		}

		// Generate the response and return
		responseMessage = "All " + typeValue + "s were successfully read.";
		logger.debug(responseMessage);
		return new ResponseEntity<List<Booking>>(bookings, HttpStatus.OK);
	}

	@CrossOrigin("*")
	@RequestMapping(value = BookingRestControllerStrings.READ_ALL_ACTIVE_URL_VALUE,
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = BookingRestControllerStrings.READ_ALL_ACTIVE_DESCRIPTION_VALUE)
	public ResponseEntity<List<Booking>> readAllActive(@PathVariable(BookingRestControllerStrings.PARAMETER_ACTIVE_ID) boolean active) {
		throw new UnsupportedOperationException();

		/*
		List<Booking> bookings = bookingService.readAllActive(active);
		if(bookings == null) {

		}

		return new ResponseEntity<List<Booking>>(bookings, HttpStatus.OK);
		*/
	}

	@CrossOrigin("*")
	@RequestMapping(value = BookingRestControllerStrings.UPDATE_BY_ID_URL_VALUE,
			method = RequestMethod.PUT)
	@ApiOperation(value = BookingRestControllerStrings.UPDATE_BY_ID_DESCRIPTION_VALUE)
	public ResponseEntity<UpdateResponse> update(@PathVariable(BookingRestControllerStrings.PARAMETER_BOOKING_ID) UUID id,
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

		// Check to make sure the booking exists for the Id specified
		BookingDAO currentBookingDAO = bookingService.readById(id);
		if(currentBookingDAO == null) {
			responseMessage = "The " + typeValue + " that is trying to be updated, does not exist.";
			logger.error(responseMessage);
			UpdateResponse updateResponse = new UpdateResponse(false, "", responseMessage);
			return new ResponseEntity<UpdateResponse>(updateResponse, HttpStatus.NOT_FOUND);
		}

		// Retrieve the input from the request
		BookingInput bookingInput = updateRequest.getInput();
		if(bookingInput == null) {
			responseMessage = "The " + typeValue + " that is trying to be updated, has an incorrect request structure.";
			logger.error(responseMessage);
			UpdateResponse updateResponse = new UpdateResponse(false, "", responseMessage);
			return new ResponseEntity<UpdateResponse>(updateResponse, HttpStatus.BAD_REQUEST);
		}

		// Validate the request
		Booking booking = bookingInput.ValidateAndConvert(id);
		if(booking == null) {
			responseMessage = "The " + typeValue + " that is trying to be updated, has invalid properties.";
			logger.error(responseMessage);
			UpdateResponse updateResponse = new UpdateResponse(false, "", responseMessage);
			return new ResponseEntity<UpdateResponse>(updateResponse, HttpStatus.BAD_REQUEST);
		}

		// Retrieve the guest id
		Guest guest = guestService.readByEmailAddress(booking.getGuestEmailAddress());
		if(guest == null) {
			responseMessage = "There is no guest associated with the " + typeValue + " that is trying to be created.";
			logger.error(responseMessage);
			UpdateResponse updateResponse = new UpdateResponse(false, "", responseMessage);
			return new ResponseEntity<UpdateResponse>(updateResponse, HttpStatus.BAD_REQUEST);
		}

		// Retrieve the hotel id
		Hotel hotel = hotelService.readByName(booking.getHotelName());
		if(hotel == null) {
			responseMessage = "There is no hotel associated with the " + typeValue + " that is trying to be created.";
			logger.error(responseMessage);
			UpdateResponse updateResponse = new UpdateResponse(false, "", responseMessage);
			return new ResponseEntity<UpdateResponse>(updateResponse, HttpStatus.BAD_REQUEST);
		}

		// Validate the request
		BookingDAO bookingDAO = booking.ConvertToDAO(id, guest.getId(), hotel.getId());
		if(bookingDAO == null) {
			responseMessage = "The " + typeValue + " that is trying to be created has invalid properties.";
			logger.error(responseMessage);
			UpdateResponse updateResponse = new UpdateResponse(false, "", responseMessage);
			return new ResponseEntity<UpdateResponse>(updateResponse, HttpStatus.BAD_REQUEST);
		}

		// If everything is ok, update the booking
		bookingService.update(bookingDAO);

		// Generate the response and return
		responseMessage = "The " + typeValue + "was successfully updated.";
		logger.debug(responseMessage);
		UpdateResponse updateResponse = new UpdateResponse(true, responseMessage, "");
		return new ResponseEntity<UpdateResponse>(updateResponse, HttpStatus.OK);
	}

	@CrossOrigin("*")
	@RequestMapping(value = BookingRestControllerStrings.ACTIVATE_URL_VALUE,
			method = RequestMethod.PUT)
	@ApiOperation(value = BookingRestControllerStrings.ACTIVATE_DESCRIPTION_VALUE)
	public ResponseEntity<ActivateResponse> activate(@PathVariable(BookingRestControllerStrings.PARAMETER_BOOKING_ID) UUID id,
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

		// Check to make sure the booking exists for the Id specified
		String activatedText = activate ? "activated" : "deactivated";
		BookingDAO currentBookingDAO = bookingService.readById(id);
		if(currentBookingDAO == null) {
			responseMessage = "The " + typeValue + " that is trying to be " + activatedText + ", does not exist.";
			logger.error(responseMessage);
			ActivateResponse activateResponse = new ActivateResponse(false, "", responseMessage);
			return new ResponseEntity<ActivateResponse>(activateResponse, HttpStatus.NOT_FOUND);
		}

		// If everything is ok, activate the booking
		bookingService.activate(id, activateRequest.getActivate());

		// Generate the response and return
		responseMessage = "The " + typeValue + " was successfully " + activatedText + ".";
		logger.debug(responseMessage);
		ActivateResponse activateResponse = new ActivateResponse(true, responseMessage, "");
		return new ResponseEntity<ActivateResponse>(activateResponse, HttpStatus.OK);
	}

	@CrossOrigin("*")
	@RequestMapping(value = BookingRestControllerStrings.DELETE_BY_ID_URL_VALUE,
			method = RequestMethod.DELETE)
	@ApiOperation(value = BookingRestControllerStrings.DELETE_BY_ID_DESCRIPTION_VALUE)
	public ResponseEntity<DeleteResponse> deleteById(@PathVariable(BookingRestControllerStrings.PARAMETER_BOOKING_ID) UUID id) {
		String responseMessage = "";

		// Check to make sure that the request is not null
		if(id == null) {
			responseMessage = "The request parameter is incorrect.";
			logger.error(responseMessage);
			DeleteResponse deleteResponse = new DeleteResponse(false, "", responseMessage);
			return new ResponseEntity<DeleteResponse>(deleteResponse, HttpStatus.BAD_REQUEST);
		}

		// Check to make sure the booking exists for the Id specified
		BookingDAO currentBookingDAO = bookingService.readById(id);
		if(currentBookingDAO == null) {
			responseMessage = "The " + typeValue + " that is trying to be deleted, does not exist.";
			logger.error(responseMessage);
			DeleteResponse deleteResponse = new DeleteResponse(false, "", responseMessage);
			return new ResponseEntity<DeleteResponse>(deleteResponse, HttpStatus.NOT_FOUND);
		}

		// If everything is ok, delete the booking
		bookingService.deleteById(id);

		//Generate the response and return
		responseMessage = "The " + typeValue + " was successfully deleted.";
		logger.debug(responseMessage);
		DeleteResponse deleteResponse = new DeleteResponse(true, responseMessage, "", id);
		return new ResponseEntity<DeleteResponse>(deleteResponse, HttpStatus.OK);
	}

	@CrossOrigin("*")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = BookingRestControllerStrings.DELETE_ALL_URL_VALUE,
			method = RequestMethod.DELETE)
	@ApiOperation(value = BookingRestControllerStrings.DELETE_ALL_DESCRIPTION_VALUE)
	public ResponseEntity<DeleteResponse> deleteAll() {
		String responseMessage = "";

		// If everything is ok, delete all bookings
		bookingService.deleteAll();

		//Generate the response and return
		responseMessage = "All " + typeValue + "s were successfully deleted.";
		logger.debug(responseMessage);
		DeleteResponse deleteResponse = new DeleteResponse(true, responseMessage, "");
		return new ResponseEntity<DeleteResponse>(deleteResponse, HttpStatus.OK);
	}
}
