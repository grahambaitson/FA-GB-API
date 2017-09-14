package fa.gb.pojo.booking;

import com.google.common.base.Strings;
import fa.gb.util.DateUtil;

import java.util.Date;
import java.util.UUID;

public class BookingInput {

    private final Date reservationStartDate;
    private final Date reservationEndDate;
    private final double rate;
    private final String guestEmailAddress;
    private final String hotelName;

    public BookingInput() {
        this.reservationStartDate = null;
        this.reservationEndDate = null;
        this.rate = 0;
        this.guestEmailAddress = null;
        this.hotelName = null;
    }

    public BookingInput(Date reservationStartDate, Date reservationEndDate,
                        double rate, String guestEmailAddress,
                        String hotelName) {
        this.reservationStartDate = reservationStartDate;
        this.reservationEndDate = reservationEndDate;
        this.rate = rate;
        this.guestEmailAddress = guestEmailAddress;
        this.hotelName = hotelName;
    }

    public Date getReservationStartDate() {
        return this.reservationStartDate;
    }

    public Date getReservationEndDate() {
        return this.reservationEndDate;
    }

    public double getRate() {
        return this.rate;
    }

    public String getGuestEmailAddress() {
        return this.guestEmailAddress;
    }

    public String getHotelName() {
        return this.hotelName;
    }

    public Booking ValidateAndConvert(UUID id) {
        boolean valid = this.Validate();
        if(!valid) {
            return null;
        }

        // Adding one day onto the reservation start date if it hasn't been set
        Date endDate = this.getReservationEndDate();
        if(endDate == null) {
            endDate = DateUtil.addDays(this.getReservationStartDate(), 1);
        }

        // If there is no guest specified, use the default one
        String gEmailAddress = this.getGuestEmailAddress();
        if(Strings.isNullOrEmpty(gEmailAddress)) {
            gEmailAddress = "default@fa.com";
        }

        // If there is no hotel specified, use the default one
        String hName = this.getHotelName();
        if(Strings.isNullOrEmpty(hName)) {
            hName = "Default Hotel";
        }

        return new Booking(id,
                this.getReservationStartDate(),
                endDate,
                this.getRate(),
                gEmailAddress,
                hName);
    }

    private boolean Validate() {
        // Only checking the reservation start date here because the guest email address and hotel name
        // are being checked on the rest side
        if(this.getReservationStartDate() == null) {
            return false;
        }

        // Check to make sure if the end date is specified, make sure that it's before the start date
        if(this.getReservationEndDate() != null) {
            if(this.getReservationStartDate().after(this.getReservationEndDate())) {
                return false;
            }
        }

        return true;
    }

}
