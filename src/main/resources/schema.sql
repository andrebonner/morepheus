DROP TABLE IF EXISTS flights;
CREATE TABLE flights (
    id serial NOT NULL,
    number varchar(100) NOT NULL,         -- Flight Number
    arrival_date date NOT NULL,  -- Flight Arrival Date
    departure_date date NOT NULL,  -- Flight Arrival Date
    destination varchar(255) NOT NULL,         -- Flight Destination
    origin varchar(255) NOT NULL,         -- Flight Origin
    seats int4,                           -- Flight Seats
    CONSTRAINT pk_flight_id PRIMARY KEY (id)
);

DROP TABLE IF EXISTS hotels CASCADE;
CREATE TABLE hotels (
    id serial NOT NULL,
    name varchar(100) NOT NULL,         -- Hotel Number
    location varchar(255) NOT NULL,  -- Hotel Location
    status varchar(50) NOT NULL,  -- Hotel Status
    CONSTRAINT pk_hotel_id PRIMARY KEY (id)
);

DROP TABLE IF EXISTS hotel_rooms CASCADE;
CREATE TABLE hotel_rooms (
    id serial NOT NULL,
    name varchar(100) NOT NULL,         -- Hotel Room Number
    location varchar(255) NOT NULL,  -- Hotel Room Location
    status varchar(50) NOT NULL,  -- Hotel Room Status
    hotel_id int4,              -- Hotel Id
    CONSTRAINT fk_hotel_id_room FOREIGN KEY (hotel_id) REFERENCES hotels (id),
    CONSTRAINT pk_hotel_room_id PRIMARY KEY (id)
);

DROP TABLE IF EXISTS reservations;
CREATE TABLE reservations (
    hotel_room_id int4,         -- Reservation Hotel Room Id
    user_id int4,              -- Reservation User Id
    check_in_time TIMESTAMP,  -- Reservation Check In Time
    check_out_time TIMESTAMP,  -- Reservation Check In Time
    CONSTRAINT fk_hotel_room_id FOREIGN KEY (hotel_room_id) REFERENCES hotel_rooms (id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (id)
);

DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users (
    id serial NOT NULL,
    is_account_non_expired boolean NOT NULL,
    is_account_non_locked boolean NOT NULL,
    is_credentials_non_expired boolean NOT NULL,
    is_enabled boolean NOT NULL,
    password varchar(255),
    role varchar(255),
    username varchar(255),
    CONSTRAINT pk_user_id PRIMARY KEY (id)
);