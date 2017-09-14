/* Create the fa_services schema and tables */
DROP SCHEMA IF EXISTS fa_services CASCADE;
CREATE SCHEMA fa_services;

CREATE TABLE fa_services."Guest"
(
  id                uuid                      	PRIMARY KEY,
  name              text                        NOT NULL,
  email_address     text                        NOT NULL UNIQUE,
  cell_number       text                        DEFAULT '',
  created_date      timestamp with time zone    NOT NULL,
  modified_date     timestamp with time zone    ,
  deleted_date      timestamp with time zone
);

CREATE TABLE fa_services."Hotel"
(
  id                uuid                      	PRIMARY KEY,
  name              text                        NOT NULL,
  address           text                        NOT NULL,
  number_of_rooms   integer                     DEFAULT 0,
  rate              decimal                     NOT NULL,
  created_date      timestamp with time zone    NOT NULL,
  modified_date     timestamp with time zone    ,
  deleted_date      timestamp with time zone
);

CREATE TABLE fa_services."Booking"
(
  id                      uuid                        PRIMARY KEY,
  guest_id                uuid                        NOT NULL,
  hotel_id                uuid                        NOT NULL,
  rate                    decimal                     NOT NULL,
  reservation_start_date  date                        NOT NULL,
  reservation_end_date    date                        NOT NULL,
  created_date            timestamp with time zone    NOT NULL,
  modified_date           timestamp with time zone    ,
  deleted_date            timestamp with time zone    ,

  CONSTRAINT "Provision_fkey_guestId_Guest_id" FOREIGN KEY (guest_id)
      REFERENCES fa_services."Guest" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT "Provision_fkey_hotelId_Hotel_id" FOREIGN KEY (hotel_id)
      REFERENCES fa_services."Hotel" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- View: fa_services."GuestHotelBooking"
-- DROP VIEW fa_services."GuestHotelBooking";

CREATE OR REPLACE VIEW fa_services."GuestHotelBooking" AS
 SELECT b.id,
    b.reservation_start_date,
    b.reservation_end_date,
    b.rate AS booking_rate,
    h.name AS hotel_name,
    h.address AS hotel_address,
    h.rate AS hotel_rate,
    g.name AS guest_name,
    g.email_address AS guest_email_address,
    b.created_date,
    b.modified_date,
    b.deleted_date AS deleted_date
   FROM fa_services."Booking" b,
    fa_services."Guest" g,
    fa_services."Hotel" h
  WHERE b.guest_id = g.id AND b.hotel_id = h.id
  ORDER BY b.id;

ALTER TABLE fa_services."GuestHotelBooking"
  OWNER TO postgres;
