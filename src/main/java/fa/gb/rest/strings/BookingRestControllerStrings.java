package fa.gb.rest.strings;

public final class BookingRestControllerStrings {

	// Instantiation is not allowed
	private BookingRestControllerStrings() {}

	// Strings for end-points
	public static final String URL_KEY_BOOKING = "booking";
	public static final String URL_KEY_ACTIVE = "active";
	public static final String PARAMETER_BOOKING_ID = "id";
	public static final String PARAMETER_ACTIVE_ID = "active";

	public static final String CREATE_URL_VALUE = "/" + URL_KEY_BOOKING;
	public static final String CREATE_MULTIPLE_URL_VALUE = "/" + URL_KEY_BOOKING + "/multiple";
	public static final String READ_BY_ID_URL_VALUE = "/" + URL_KEY_BOOKING + "/{" + PARAMETER_BOOKING_ID + "}";
	public static final String READ_ALL_URL_VALUE = "/" + URL_KEY_BOOKING;
	public static final String READ_ALL_ACTIVE_URL_VALUE = "/" + URL_KEY_BOOKING + "/" + URL_KEY_ACTIVE + "/{" + PARAMETER_ACTIVE_ID + "}";
	public static final String UPDATE_BY_ID_URL_VALUE = "/" + URL_KEY_BOOKING + "/{" + PARAMETER_BOOKING_ID + "}";
	public static final String ACTIVATE_URL_VALUE = "/" + URL_KEY_BOOKING + "/activate/{" + PARAMETER_BOOKING_ID + "}";
	public static final String DELETE_BY_ID_URL_VALUE = "/" + URL_KEY_BOOKING + "/{" + PARAMETER_BOOKING_ID + "}";
	public static final String DELETE_ALL_URL_VALUE = "/" + URL_KEY_BOOKING;

	public static final String CREATE_DESCRIPTION_VALUE = "Create a new booking";
	public static final String CREATE_MULTIPLE_DESCRIPTION_VALUE = "Create multiple new bookings";
	public static final String READ_BY_ID_DESCRIPTION_VALUE = "Retrieve the information for a specific booking based on their ID";
	public static final String READ_ALL_DESCRIPTION_VALUE = "Retrieve the information for all bookings";
	public static final String READ_ALL_ACTIVE_DESCRIPTION_VALUE = "Retrieve the information for all bookings based on whether they are active or not";
	public static final String UPDATE_BY_ID_DESCRIPTION_VALUE = "Update the information for a specific booking based on their ID";
	public static final String ACTIVATE_DESCRIPTION_VALUE = "Activate a specific booking based on their ID";
	public static final String DELETE_BY_ID_DESCRIPTION_VALUE = "Delete a specific booking on their ID";
	public static final String DELETE_ALL_DESCRIPTION_VALUE = "Delete all bookings";
}
