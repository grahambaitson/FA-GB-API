package fa.gb.pojo.booking;

import fa.gb.pojo.BasePojo;

import java.util.Date;
import java.util.UUID;

public class Booking extends BasePojo {

    private Date reservationStartDate;
    private Date reservationEndDate;
    private final double rate;
    private final String guestEmailAddress;
    private final String hotelName;
    private String reservationColour;
    private String reservationBorderColour;

    public Booking() {
        super();
        this.reservationStartDate = null;
        this.reservationEndDate = null;
        this.rate = 0;
        this.hotelName = null;
        this.guestEmailAddress = null;
        this.reservationColour = null;
        this.reservationBorderColour = null;
    }

    public Booking(UUID id, Date reservationStartDate, Date reservationEndDate,
                    double rate, String guestEmailAddress, String hotelName) {
        super(id);
        this.reservationStartDate = reservationStartDate;
        this.reservationEndDate = reservationEndDate;
        this.rate = rate;
        this.guestEmailAddress = guestEmailAddress;
        this.hotelName = hotelName;
    }

    public Booking(UUID id, Date reservationStartDate, Date reservationEndDate,
                             double rate, String guestEmailAddress, String hotelName,
                             Date createdDate) {
        super(id, createdDate);
        this.reservationStartDate = reservationStartDate;
        this.reservationEndDate = reservationEndDate;
        this.rate = rate;
        this.hotelName = hotelName;
        this.guestEmailAddress = guestEmailAddress;
    }

    public Booking(UUID id, Date reservationStartDate, Date reservationEndDate,
                    double rate, String guestEmailAddress, String hotelName,
                     Date createdDate, Date modifiedDate, Date deletedDate) {
        super(id, createdDate, modifiedDate, deletedDate);
        this.reservationStartDate = reservationStartDate;
        this.reservationEndDate = reservationEndDate;
        this.rate = rate;
        this.guestEmailAddress = guestEmailAddress;
        this.hotelName = hotelName;
    }

    public Date getReservationStartDate() {
        return reservationStartDate;
    }

    public void setReservationStartDate(Date reservationStartDate) {
        this.reservationStartDate = reservationStartDate;
    }

    public Date getReservationEndDate() {
        return reservationEndDate;
    }

    public void setReservationEndDate(Date reservationEndDate) {
        this.reservationEndDate = reservationEndDate;
    }

    public double getRate() {
        return rate;
    }

    public String getGuestEmailAddress() {
        return guestEmailAddress;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getReservationColour() { return reservationColour; }

    public void setReservationColour(String reservationColour) {
        this.reservationColour = reservationColour;
    }

    public String getReservationBorderColour() { return reservationBorderColour; }

    public void setReservationBorderColour(String reservationBorderColour) {
        this.reservationBorderColour = reservationBorderColour;
    }

    public BookingDAO ConvertToDAO(UUID id, UUID guestId, UUID hotelId) {
        return new BookingDAO(id,
                this.getReservationStartDate(),
                this.getReservationEndDate(),
                this.getRate(),
                guestId,
                hotelId);
    }
}
