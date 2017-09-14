package fa.gb.pojo.booking;

import fa.gb.pojo.BasePojo;

import java.util.Date;
import java.util.UUID;

public class BookingDAO extends BasePojo {

    private final Date reservationStartDate;
    private Date reservationEndDate;
    private final double rate;
    private final UUID guestId;
    private final UUID hotelId;

    public BookingDAO() {
        super();
        this.reservationStartDate = null;
        this.reservationEndDate = null;
        this.rate = 0;
        this.guestId = null;
        this.hotelId = null;
    }

    public BookingDAO(UUID id, Date reservationStartDate,
                      Date reservationEndDate, double rate, UUID guestId,
                      UUID hotelId) {
        super(id);
        this.reservationStartDate = reservationStartDate;
        this.reservationEndDate = reservationEndDate;
        this.rate = rate;
        this.guestId = guestId;
        this.hotelId = hotelId;
    }

    public BookingDAO(UUID id, Date reservationEndDate, Date reservationStartDate,
                      double rate, UUID guestId, UUID hotelId, Date createdDate) {
        super(id, createdDate);
        this.reservationStartDate = reservationStartDate;
        this.reservationEndDate = reservationEndDate;
        this.rate = rate;
        this.guestId = guestId;
        this.hotelId = hotelId;
    }

    public BookingDAO(UUID id, Date reservationStartDate,
                      Date reservationEndDate, double rate, UUID guestId,
                      UUID hotelId, Date createdDate, Date modifiedDate, Date deletedDate) {
        super(id, createdDate, modifiedDate, deletedDate);
        this.reservationStartDate = reservationStartDate;
        this.reservationEndDate = reservationEndDate;
        this.rate = rate;
        this.guestId = guestId;
        this.hotelId = hotelId;
    }

    public Date getReservationStartDate() {
        return this.reservationStartDate;
    }

    public Date getReservationEndDate() {
        return this.reservationEndDate;
    }

    public void setReservationEndDate(Date reservationEndDate) {
        this.reservationEndDate = reservationEndDate;
    }

    public double getRate() {
        return this.rate;
    }

    public UUID getGuestId() {
        return this.guestId;
    }

    public UUID getHotelId() {
        return this.hotelId;
    }

    public Booking ConvertToRest(UUID id, String guestEmailAddress, String hotelName) {
        return new Booking(id, this.getReservationStartDate(), this.getReservationEndDate(),
                this.getRate(), guestEmailAddress, hotelName);
    }
}
