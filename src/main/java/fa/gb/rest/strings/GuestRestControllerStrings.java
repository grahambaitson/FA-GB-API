package fa.gb.rest.strings;

public final class GuestRestControllerStrings {

	// Instantiation is not allowed
	private GuestRestControllerStrings() {}

	// Strings for end-points
	public static final String URL_KEY_GUEST = "guest";
	public static final String URL_KEY_ACTIVE = "active";
	public static final String PARAMETER_GUEST_ID = "id";
	public static final String PARAMETER_ACTIVE_ID = "active";

	public static final String CREATE_URL_VALUE = "/" + URL_KEY_GUEST;
	public static final String READ_BY_ID_URL_VALUE = "/" + URL_KEY_GUEST + "/{" + PARAMETER_GUEST_ID + "}";
	public static final String READ_ALL_URL_VALUE = "/" + URL_KEY_GUEST;
	public static final String READ_ALL_ACTIVE_URL_VALUE = "/" + URL_KEY_GUEST + "/" + URL_KEY_ACTIVE + "/{" + PARAMETER_ACTIVE_ID + "}";
	public static final String UPDATE_BY_ID_URL_VALUE = "/" + URL_KEY_GUEST + "/{" + PARAMETER_GUEST_ID + "}";
	public static final String ACTIVATE_URL_VALUE = "/" + URL_KEY_GUEST + "/activate/{" + PARAMETER_GUEST_ID + "}";
	public static final String DELETE_BY_ID_URL_VALUE = "/" + URL_KEY_GUEST + "/{" + PARAMETER_GUEST_ID + "}";
	public static final String DELETE_ALL_URL_VALUE = "/" + URL_KEY_GUEST;

	public static final String CREATE_DESCRIPTION_VALUE = "Create a new guest";
	public static final String READ_BY_ID_DESCRIPTION_VALUE = "Retrieve the information for a specific guest based on their ID";
	public static final String READ_ALL_DESCRIPTION_VALUE = "Retrieve the information for all guests";
	public static final String READ_ALL_ACTIVE_DESCRIPTION_VALUE = "Retrieve the information for all guests based on whether they are active or not";
	public static final String UPDATE_BY_ID_DESCRIPTION_VALUE = "Update the information for a specific guest based on their ID";
	public static final String ACTIVATE_DESCRIPTION_VALUE = "Activate a specific guest based on their ID";
	public static final String DELETE_BY_ID_DESCRIPTION_VALUE = "Delete a specific guest on their ID";
	public static final String DELETE_ALL_DESCRIPTION_VALUE = "Delete all guests";
}
