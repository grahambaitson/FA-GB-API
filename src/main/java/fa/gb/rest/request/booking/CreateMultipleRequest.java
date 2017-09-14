package fa.gb.rest.request.booking;

import fa.gb.pojo.booking.BookingInput;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel
public class CreateMultipleRequest {

    private final List<BookingInput> input;

    public CreateMultipleRequest() {
        this.input = null;
    }

    public CreateMultipleRequest(List<BookingInput> input) {
        this.input = input;
    }

    public List<BookingInput> getInput() {
        return this.input;
    }

}
