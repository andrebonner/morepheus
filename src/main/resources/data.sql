-- Flights
INSERT INTO flights (number, arrival_date, departure_date, origin, destination, seats) VALUES('A-123','2021-07-26','2021-07-25','Vancouer','Bettam',150);
INSERT INTO flights (number, arrival_date, departure_date, origin, destination, seats) VALUES('A-234','2021-07-26','2021-07-25','Bettam','Vancouer',150);
INSERT INTO flights (number, arrival_date, departure_date, origin, destination, seats) VALUES('A-345','2021-07-27','2021-07-26','Franklin','Vancouer',150);
INSERT INTO flights (number, arrival_date, departure_date, origin, destination, seats) VALUES('A-456','2021-07-27','2021-07-26','Vancouer','Franklin',150);

-- Hotels
INSERT INTO hotels (name, location, status) VALUES('Beckford Hotel','17 Kings way','NEW');
INSERT INTO hotels (name, location, status) VALUES('Richomd Hotel','37 Wayne Place','NEW');
INSERT INTO hotels (name, location, status) VALUES('Jakes Hotel','90 Greeks Place','PENDING');

-- Hotel Rooms
INSERT INTO hotel_rooms (name, location, status, hotel_id) VALUES('Room Best A','Left Corner','INSPECTED',1);
INSERT INTO hotel_rooms (name, location, status, hotel_id) VALUES('Room Best B','Left Corner Upper','READY',1);
INSERT INTO hotel_rooms (name, location, status, hotel_id) VALUES('Room Best C','Left Corner Middle','READY',1);
INSERT INTO hotel_rooms (name, location, status, hotel_id) VALUES('Room Rich A','Upper Corner','INSPECTED',2);
INSERT INTO hotel_rooms (name, location, status, hotel_id) VALUES('Room Rich B','Uppler Corner Left','READY',2);
INSERT INTO hotel_rooms (name, location, status, hotel_id) VALUES('Room Jack A','Left Corner Middle','READY',3);
INSERT INTO hotel_rooms (name, location, status, hotel_id) VALUES('Room Jack B','Left Corner Middle','READY',3);

-- Reservations
INSERT INTO reservations (hotel_room_id, user_id, check_in_time, check_out_time) VALUES (1,2,'2021-09-22 19:10:25','2021-09-29 19:10:22');
INSERT INTO reservations (hotel_room_id, user_id, check_in_time, check_out_time) VALUES (2,2,'2021-09-22 19:10:25','2021-09-29 19:10:22');
INSERT INTO reservations (hotel_room_id, user_id, check_in_time, check_out_time) VALUES (2,1,'2021-09-22 19:10:25','2021-09-29 19:10:22');
INSERT INTO reservations (hotel_room_id, user_id, check_in_time, check_out_time) VALUES (1,3,'2021-09-22 19:10:25','2021-09-29 19:10:22');
INSERT INTO reservations (hotel_room_id, user_id, check_in_time, check_out_time) VALUES (3,2,'2021-09-22 19:10:25','2021-09-29 19:10:22');
INSERT INTO reservations (hotel_room_id, user_id, check_in_time, check_out_time) VALUES (2,1,'2021-09-22 19:10:25','2021-09-29 19:10:22');
INSERT INTO reservations (hotel_room_id, user_id, check_in_time, check_out_time) VALUES (1,3,'2021-09-22 19:10:25','2021-09-29 19:10:22');
INSERT INTO reservations (hotel_room_id, user_id, check_in_time, check_out_time) VALUES (3,3,'2021-09-22 19:10:25','2021-09-29 19:10:22');
INSERT INTO reservations (hotel_room_id, user_id, check_in_time, check_out_time) VALUES (1,1,'2021-09-22 19:10:25','2021-09-29 19:10:22');
INSERT INTO reservations (hotel_room_id, user_id, check_in_time, check_out_time) VALUES (3,1,'2021-09-22 19:10:25','2021-09-29 19:10:22');

-- Users
--INSERT INTO users ( is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, password, role, username) VALUES ( true, true, true, true, 'password', 'REG_USER', 'andre');
--INSERT INTO users ( is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, password, role, username) VALUES ( true, true, true, true, 'password', 'ADMIN', 'admin');