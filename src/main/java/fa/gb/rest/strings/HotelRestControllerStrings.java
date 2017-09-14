package fa.gb.rest.strings;

public final class HotelRestControllerStrings {

	// Instantiation is not allowed
	private HotelRestControllerStrings() {}

	// Strings for end-points
	public static final String URL_KEY_HOTEL = "hotel";
	public static final String URL_KEY_ACTIVE = "active";
	public static final String PARAMETER_HOTEL_ID = "id";
	public static final String PARAMETER_ACTIVE_ID = "active";

	public static final String CREATE_URL_VALUE = "/" + URL_KEY_HOTEL;
	public static final String READ_BY_ID_URL_VALUE = "/" + URL_KEY_HOTEL + "/{" + PARAMETER_HOTEL_ID + "}";
	public static final String READ_ALL_URL_VALUE = "/" + URL_KEY_HOTEL;
	public static final String READ_ALL_ACTIVE_URL_VALUE = "/" + URL_KEY_HOTEL + "/" + URL_KEY_ACTIVE + "/{" + PARAMETER_ACTIVE_ID + "}";
	public static final String UPDATE_BY_ID_URL_VALUE = "/" + URL_KEY_HOTEL + "/{" + PARAMETER_HOTEL_ID + "}";
	public static final String ACTIVATE_URL_VALUE = "/" + URL_KEY_HOTEL + "/activate/{" + PARAMETER_HOTEL_ID + "}";
	public static final String DELETE_BY_ID_URL_VALUE = "/" + URL_KEY_HOTEL + "/{" + PARAMETER_HOTEL_ID + "}";
	public static final String DELETE_ALL_URL_VALUE = "/" + URL_KEY_HOTEL;

	public static final String CREATE_DESCRIPTION_VALUE = "Create a new hotel";
	public static final String READ_BY_ID_DESCRIPTION_VALUE = "Retrieve the information for a specific hotel based on their ID";
	public static final String READ_ALL_DESCRIPTION_VALUE = "Retrieve the information for all hotels";
	public static final String READ_ALL_ACTIVE_DESCRIPTION_VALUE = "Retrieve the information for all hotels based on whether they are active or not";
	public static final String UPDATE_BY_ID_DESCRIPTION_VALUE = "Update the information for a specific hotel based on their ID";
	public static final String ACTIVATE_DESCRIPTION_VALUE = "Activate a specific hotel based on their ID";
	public static final String DELETE_BY_ID_DESCRIPTION_VALUE = "Delete a specific hotel on their ID";
	public static final String DELETE_ALL_DESCRIPTION_VALUE = "Delete all hotels";
}
