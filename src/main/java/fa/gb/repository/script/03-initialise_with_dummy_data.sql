/*
Start with fresh database
*/

DELETE FROM fa_services."Booking";
DELETE FROM fa_services."Hotel";
DELETE FROM fa_services."Guest";

/*
Dummy insert data for Guest table
*/
INSERT INTO fa_services."Guest" (id, name, email_address, cell_number, created_date) SELECT '00000000-0000-0000-0000-000000000001', 'Default Guest', 'default@fa.com', '0861234567', now()::timestamp WHERE NOT EXISTS (SELECT id FROM fa_services."Guest" WHERE id = '00000000-0000-0000-0000-000000000001');
INSERT INTO fa_services."Guest" (id, name, email_address, cell_number, created_date) SELECT '00000000-0000-0000-0000-000000000002', 'Test Guest 2', 'testuser2@fa.com', '0861234568', now()::timestamp WHERE NOT EXISTS (SELECT id FROM fa_services."Guest" WHERE id = '00000000-0000-0000-0000-000000000002');
INSERT INTO fa_services."Guest" (id, name, email_address, cell_number, created_date) SELECT '00000000-0000-0000-0000-000000000003', 'Test Guest 3', 'testuser3@fa.com', '0861234569', now()::timestamp WHERE NOT EXISTS (SELECT id FROM fa_services."Guest" WHERE id = '00000000-0000-0000-0000-000000000003');

/*
Dummy insert data for Hotel table
*/
INSERT INTO fa_services."Hotel" (id, name, address, number_of_rooms, rate, created_date) SELECT '00000000-0000-0000-0000-000000000001', 'Default Hotel', 'Default Hotel Address', '50', '100', now()::timestamp WHERE NOT EXISTS (SELECT id FROM fa_services."Hotel" WHERE id = '00000000-0000-0000-0000-000000000001');
INSERT INTO fa_services."Hotel" (id, name, address, number_of_rooms, rate, created_date) SELECT '00000000-0000-0000-0000-000000000002', 'Test Hotel 2', 'Address 2', '100', '255', now()::timestamp WHERE NOT EXISTS (SELECT id FROM fa_services."Hotel" WHERE id = '00000000-0000-0000-0000-000000000002');
INSERT INTO fa_services."Hotel" (id, name, address, number_of_rooms, rate, created_date) SELECT '00000000-0000-0000-0000-000000000003', 'Test Hotel 3', 'Address 3', '75', '150',now()::timestamp WHERE NOT EXISTS (SELECT id FROM fa_services."Hotel" WHERE id = '00000000-0000-0000-0000-000000000003');

/*
Dummy insert data for Booking table
*/
/*
INSERT INTO fa_services."Booking" (id, guest_id, hotel_id, rate, reservation_start_date, reservation_end_date, created_date) SELECT '00000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000001', (SELECT rate FROM fa_services."Hotel" WHERE id = '00000000-0000-0000-0000-000000000001'), now() + interval '1 day', now() + interval '2 day', now()::timestamp WHERE NOT EXISTS (SELECT id FROM fa_services."Booking" WHERE id = '00000000-0000-0000-0000-000000000001');
INSERT INTO fa_services."Booking" (id, guest_id, hotel_id, rate, reservation_start_date, reservation_end_date, created_date) SELECT '00000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000001', (SELECT rate FROM fa_services."Hotel" WHERE id = '00000000-0000-0000-0000-000000000001'), now() + interval '2 day', now() + interval '3 day', now()::timestamp WHERE NOT EXISTS (SELECT id FROM fa_services."Booking" WHERE id = '00000000-0000-0000-0000-000000000002');
INSERT INTO fa_services."Booking" (id, guest_id, hotel_id, rate, reservation_start_date, reservation_end_date, created_date) SELECT '00000000-0000-0000-0000-000000000003', '00000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000001', (SELECT rate FROM fa_services."Hotel" WHERE id = '00000000-0000-0000-0000-000000000001'), now() + interval '3 day', now() + interval '4 day', now()::timestamp WHERE NOT EXISTS (SELECT id FROM fa_services."Booking" WHERE id = '00000000-0000-0000-0000-000000000003');
INSERT INTO fa_services."Booking" (id, guest_id, hotel_id, rate, reservation_start_date, reservation_end_date, created_date) SELECT '00000000-0000-0000-0000-000000000004', '00000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000002', (SELECT rate FROM fa_services."Hotel" WHERE id = '00000000-0000-0000-0000-000000000002'), now() + interval '4 day', now() + interval '5 day', now()::timestamp WHERE NOT EXISTS (SELECT id FROM fa_services."Booking" WHERE id = '00000000-0000-0000-0000-000000000004');
INSERT INTO fa_services."Booking" (id, guest_id, hotel_id, rate, reservation_start_date, reservation_end_date, created_date) SELECT '00000000-0000-0000-0000-000000000005', '00000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000001', (SELECT rate FROM fa_services."Hotel" WHERE id = '00000000-0000-0000-0000-000000000001'), now() + interval '1 day', now() + interval '2 day', now()::timestamp WHERE NOT EXISTS (SELECT id FROM fa_services."Booking" WHERE id = '00000000-0000-0000-0000-000000000005');
INSERT INTO fa_services."Booking" (id, guest_id, hotel_id, rate, reservation_start_date, reservation_end_date, created_date) SELECT '00000000-0000-0000-0000-000000000006', '00000000-0000-0000-0000-000000000002', '00000000-0000-0000-0000-000000000001', (SELECT rate FROM fa_services."Hotel" WHERE id = '00000000-0000-0000-0000-000000000001'), now() + interval '2 day', now() + interval '3 day', now()::timestamp WHERE NOT EXISTS (SELECT id FROM fa_services."Booking" WHERE id = '00000000-0000-0000-0000-000000000006');
INSERT INTO fa_services."Booking" (id, guest_id, hotel_id, rate, reservation_start_date, reservation_end_date, created_date) SELECT '00000000-0000-0000-0000-000000000007', '00000000-0000-0000-0000-000000000003', '00000000-0000-0000-0000-000000000003', (SELECT rate FROM fa_services."Hotel" WHERE id = '00000000-0000-0000-0000-000000000003'), now() + interval '1 day', now() + interval '2 day', now()::timestamp WHERE NOT EXISTS (SELECT id FROM fa_services."Booking" WHERE id = '00000000-0000-0000-0000-000000000007');
*/
