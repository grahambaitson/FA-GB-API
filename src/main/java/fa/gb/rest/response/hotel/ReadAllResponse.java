package fa.gb.rest.response.hotel;

import fa.gb.pojo.hotel.Hotel;
import fa.gb.rest.response.BaseReadAllResponse;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel
public class ReadAllResponse extends BaseReadAllResponse<Hotel> {
	
	public ReadAllResponse(boolean successful, String responseMessage, String errorMessage, List<Hotel> hotels) {
		super(successful, responseMessage, errorMessage, hotels);
	}
	
}

