SET FOREIGN_KEY_CHECKS=0;

insert into locations(id, formatted, latitude, longitude, city, region, oblast, custom) values('ChIJBUVa4U7P1EAR_kYBF9IxSXY', 'Київ, Україна', '50.4501', '30.5234', 'Київ', 'місто Київ', 'місто Київ', false);
insert into locations(id, formatted, latitude, longitude, city, region, oblast, custom) values('ChIJV5oQCXzdOkcR4ngjARfFI0I', 'Львів, Львівська область, Україна',  '49.839683', '24.029717', 'Львів', 'Львівська область', null, false);


insert into users (email, approved, blocked, first_name, last_name, passport, password, phone, photo_url, rate, user_role) VALUES ('email0@gmail.com', 1, 0, 'Andy', 'Wick', 'ТЧ 938232', '$2a$10$d2HsbmeXA6x6hz7Wm.3RG.4ZFaZYW95i0ieKN.RItNnrt5sVykGr6', '+3809966847', '/resources/users/photos/email0@gmail.com.png', 4, 'ADMIN');
insert into users (email, approved, blocked, first_name, last_name, passport, password, phone, photo_url, rate, user_role) VALUES ('email1@gmail.com', 1, 0, 'Nancy', 'Wick', 'РС 012980', '$2a$10$d2HsbmeXA6x6hz7Wm.3RG.4ZFaZYW95i0ieKN.RItNnrt5sVykGr6', '+3805085894', '/resources/users/photos/email1@gmail.com.png', 5, 'MODERATOR');
insert into users (email, approved, blocked, first_name, last_name, passport, password, phone, photo_url, rate, user_role) VALUES ('email2@gmail.com', 1, 0, 'Mary', 'Shelly', 'КТ 882383', '$2a$10$d2HsbmeXA6x6hz7Wm.3RG.4ZFaZYW95i0ieKN.RItNnrt5sVykGr6', '+3800522314', '/resources/users/photos/email2@gmail.com.png', 3, 'DRIVER');
insert into users (email, approved, blocked, first_name, last_name, passport, password, phone, photo_url, rate, user_role) VALUES ('email3@gmail.com', 1, 0, 'Andy', 'Jefferson', 'ЧШ 825147', '$2a$10$d2HsbmeXA6x6hz7Wm.3RG.4ZFaZYW95i0ieKN.RItNnrt5sVykGr6', '+3800569576', '/resources/users/photos/email3@gmail.com.png', 4, 'DRIVER');
insert into users (email, approved, blocked, first_name, last_name, passport, password, phone, photo_url, rate, user_role) VALUES ('email4@gmail.com', 1, 0, 'Ann', 'Jackson', 'ЖЧ 099523', '$2a$10$d2HsbmeXA6x6hz7Wm.3RG.4ZFaZYW95i0ieKN.RItNnrt5sVykGr6', '+3800342842', '/resources/users/photos/email4@gmail.com.png', 5, 'DRIVER');
insert into users (email, approved, blocked, first_name, last_name, passport, password, phone, photo_url, rate, user_role) VALUES ('email5@gmail.com', 1, 0, 'John', 'Wollowitz', 'ИП 873783', '$2a$10$d2HsbmeXA6x6hz7Wm.3RG.4ZFaZYW95i0ieKN.RItNnrt5sVykGr6', '+3807638284', '/resources/users/photos/email5@gmail.com.png', 4, 'DRIVER');
insert into users (email, approved, blocked, first_name, last_name, passport, password, phone, photo_url, rate, user_role) VALUES ('email6@gmail.com', 1, 0, 'Andy', 'Hatthaway', 'ФЗ 658567', '$2a$10$d2HsbmeXA6x6hz7Wm.3RG.4ZFaZYW95i0ieKN.RItNnrt5sVykGr6', '+3805226116', '/resources/users/photos/email6@gmail.com.png', 5, 'DRIVER');
insert into users (email, approved, blocked, first_name, last_name, passport, password, phone, photo_url, rate, user_role) VALUES ('email7@gmail.com', 1, 0, 'Thomas', 'White', 'ЖВ 547461', '$2a$10$d2HsbmeXA6x6hz7Wm.3RG.4ZFaZYW95i0ieKN.RItNnrt5sVykGr6', '+3803488968', '/resources/users/photos/email7@gmail.com.png', 3, 'DRIVER');
insert into users (email, approved, blocked, first_name, last_name, passport, password, phone, photo_url, rate, user_role) VALUES ('email8@gmail.com', 1, 0, 'Robert', 'Wollowitz', 'ЧЩ 994078', '$2a$10$d2HsbmeXA6x6hz7Wm.3RG.4ZFaZYW95i0ieKN.RItNnrt5sVykGr6', '+3802301126', '/resources/users/photos/email8@gmail.com.png', 4, 'DRIVER');
insert into users (email, approved, blocked, first_name, last_name, passport, password, phone, photo_url, rate, user_role) VALUES ('email9@gmail.com', 1, 0, 'Samuel', 'Jackson', 'ВА 536532', '$2a$10$d2HsbmeXA6x6hz7Wm.3RG.4ZFaZYW95i0ieKN.RItNnrt5sVykGr6', '+3809556342', '/resources/users/photos/email9@gmail.com.png', 5, 'DRIVER');
insert into users (email, approved, blocked, first_name, last_name, passport, password, phone, photo_url, rate, user_role) VALUES ('email16@gmail.com', 1, 0, 'Mary', 'John', 'ВА 136532', '$2a$10$d2HsbmeXA6x6hz7Wm.3RG.4ZFaZYW95i0ieKN.RItNnrt5sVykGr6', '+3809656342', '/resources/users/photos/email16@gmail.com.png', 4, 'DRIVER');

insert into users (email, approved, blocked, first_name, last_name, passport, password, phone, photo_url, rate, user_role) VALUES ('email10@gmail.com', 1, 0, 'Ann', 'Hackson', 'ЖЛ 099523', '$2a$10$d2HsbmeXA6x6hz7Wm.3RG.4ZFaZYW95i0ieKN.RItNnrt5sVykGr6', '+3800342842', '/resources/users/photos/email10@gmail.com.png', 5, 'DRIVER');
insert into users (email, approved, blocked, first_name, last_name, passport, password, phone, photo_url, rate, user_role) VALUES ('email11@gmail.com', 1, 0, 'Dan', 'Wollowitz', 'ИВ 873783', '$2a$10$d2HsbmeXA6x6hz7Wm.3RG.4ZFaZYW95i0ieKN.RItNnrt5sVykGr6', '+3807638284', '/resources/users/photos/email11@gmail.com.png', 4, 'DRIVER');
insert into users (email, approved, blocked, first_name, last_name, passport, password, phone, photo_url, rate, user_role) VALUES ('email12@gmail.com', 1, 0, 'Tom', 'Hatthaway', 'ФР 658567', '$2a$10$d2HsbmeXA6x6hz7Wm.3RG.4ZFaZYW95i0ieKN.RItNnrt5sVykGr6', '+3805226116', '/resources/users/photos/email12@gmail.com.png', 3, 'DRIVER');
insert into users (email, approved, blocked, first_name, last_name, passport, password, phone, photo_url, rate, user_role) VALUES ('email13@gmail.com', 1, 0, 'Sam', 'White', 'ЗВ 547461', '$2a$10$d2HsbmeXA6x6hz7Wm.3RG.4ZFaZYW95i0ieKN.RItNnrt5sVykGr6', '+3803488968', '/resources/users/photos/email13@gmail.com.png', 3, 'DRIVER');
insert into users (email, approved, blocked, first_name, last_name, passport, password, phone, photo_url, rate, user_role) VALUES ('email14@gmail.com', 1, 0, 'Ann', 'Wollowitz', 'КЩ 994078', '$2a$10$d2HsbmeXA6x6hz7Wm.3RG.4ZFaZYW95i0ieKN.RItNnrt5sVykGr6', '+3802301126', '/resources/users/photos/email14@gmail.com.png', 2, 'DRIVER');
insert into users (email, approved, blocked, first_name, last_name, passport, password, phone, photo_url, rate, user_role) VALUES ('email15@gmail.com', 1, 0, 'Doroty', 'Jackson', 'ВЦ 536532', '$2a$10$d2HsbmeXA6x6hz7Wm.3RG.4ZFaZYW95i0ieKN.RItNnrt5sVykGr6', '+3809556342', '/resources/users/photos/email15@gmail.com.png', 3, 'DRIVER');
insert into users (email, approved, blocked, first_name, last_name, passport, password, phone, photo_url, rate, user_role) VALUES ('email17@gmail.com', 1, 0, 'Max', 'Wollowitz', 'ЧЧ 994078', '$2a$10$d2HsbmeXA6x6hz7Wm.3RG.4ZFaZYW95i0ieKN.RItNnrt5sVykGr6', '+3802301126', '/resources/users/photos/email17@gmail.com.png', 4, 'DRIVER');
insert into users (email, approved, blocked, first_name, last_name, passport, password, phone, photo_url, rate, user_role) VALUES ('email18@gmail.com', 1, 0, 'Samuel', 'Daniel', 'ТА 536532', '$2a$10$d2HsbmeXA6x6hz7Wm.3RG.4ZFaZYW95i0ieKN.RItNnrt5sVykGr6', '+3809556342', '/resources/users/photos/email18@gmail.com.png', 4, 'DRIVER');
insert into users (email, approved, blocked, first_name, last_name, passport, password, phone, photo_url, rate, user_role) VALUES ('email19@gmail.com', 1, 0, 'Sam', 'Andry', 'КА 136532', '$2a$10$d2HsbmeXA6x6hz7Wm.3RG.4ZFaZYW95i0ieKN.RItNnrt5sVykGr6', '+3809656342', '/resources/users/photos/email19@gmail.com.png', 4, 'DRIVER');


insert into users (email, approved, blocked, first_name, last_name, passport, password, phone, photo_url, rate, user_role) VALUES ('email21@gmail.com', 1, 0, 'Ann', 'Shelly', 'ГМ 465160', '$2a$10$d2HsbmeXA6x6hz7Wm.3RG.4ZFaZYW95i0ieKN.RItNnrt5sVykGr6', '+3802420498', '/resources/users/photos/email21@gmail.com.png', 5, 'CUSTOMER');
insert into users (email, approved, blocked, first_name, last_name, passport, password, phone, photo_url, rate, user_role) VALUES ('email22@gmail.com', 1, 0, 'Thomas', 'Wollowitz', 'ЗЙ 664936', '$2a$10$d2HsbmeXA6x6hz7Wm.3RG.4ZFaZYW95i0ieKN.RItNnrt5sVykGr6', '+3806615850', '/resources/users/photos/email22@gmail.com.png', 5, 'CUSTOMER');
insert into users (email, approved, blocked, first_name, last_name, passport, password, phone, photo_url, rate, user_role) VALUES ('email23@gmail.com', 1, 0, 'Ann', 'Hatthaway', 'ЦТ 350484', '$2a$10$d2HsbmeXA6x6hz7Wm.3RG.4ZFaZYW95i0ieKN.RItNnrt5sVykGr6', '+3803082695', '/resources/users/photos/email23@gmail.com.png', 4, 'CUSTOMER');
insert into users (email, approved, blocked, first_name, last_name, passport, password, phone, photo_url, rate, user_role) VALUES ('email24@gmail.com', 1, 0, 'Samuel', 'Jackson', 'ВО 781392', '$2a$10$d2HsbmeXA6x6hz7Wm.3RG.4ZFaZYW95i0ieKN.RItNnrt5sVykGr6', '+3804993541', '/resources/users/photos/email24@gmail.com.png', 4, 'CUSTOMER');
insert into users (email, approved, blocked, first_name, last_name, passport, password, phone, photo_url, rate, user_role) VALUES ('email25@gmail.com', 0, 1, 'Ann', 'Shelly', 'НЕ 453327', '$2a$10$d2HsbmeXA6x6hz7Wm.3RG.4ZFaZYW95i0ieKN.RItNnrt5sVykGr6', '+3802436042', '/resources/users/photos/email25@gmail.com.png', 3, 'CUSTOMER');
insert into users (email, approved, blocked, first_name, last_name, passport, password, phone, photo_url, rate, user_role) VALUES ('email26@gmail.com', 1, 0, 'John', 'Shelly', 'ЙН 069135', '$2a$10$d2HsbmeXA6x6hz7Wm.3RG.4ZFaZYW95i0ieKN.RItNnrt5sVykGr6', '+3800632159', '/resources/users/photos/email26@gmail.com.png', 3, 'CUSTOMER');
insert into users (email, approved, blocked, first_name, last_name, passport, password, phone, photo_url, rate, user_role) VALUES ('email27@gmail.com', 1, 0, 'Ann', 'Jefferson', 'ХМ 375924', '$2a$10$d2HsbmeXA6x6hz7Wm.3RG.4ZFaZYW95i0ieKN.RItNnrt5sVykGr6', '+3802154942', '/resources/users/photos/email27@gmail.com.png', 2, 'CUSTOMER');
insert into users (email, approved, blocked, first_name, last_name, passport, password, phone, photo_url, rate, user_role) VALUES ('email28@gmail.com', 0, 1, 'Andy', 'Hatthaway', 'ЛС 432382', '$2a$10$d2HsbmeXA6x6hz7Wm.3RG.4ZFaZYW95i0ieKN.RItNnrt5sVykGr6', '+3802326950', '/resources/users/photos/email28@gmail.com.png', 4, 'CUSTOMER');
insert into users (email, approved, blocked, first_name, last_name, passport, password, phone, photo_url, rate, user_role) VALUES ('email29@gmail.com', 1, 0, 'Samuel', 'Wick', 'НХ 048021', '$2a$10$d2HsbmeXA6x6hz7Wm.3RG.4ZFaZYW95i0ieKN.RItNnrt5sVykGr6', '+3805257202', '/resources/users/photos/email29@gmail.com.png', 1, 'CUSTOMER');
insert into users (email, approved, blocked, first_name, last_name, passport, password, phone, photo_url, rate, user_role) VALUES ('email30@gmail.com', 1, 0, 'Andy', 'Clayborn', 'ХЖ 081313', '$2a$10$d2HsbmeXA6x6hz7Wm.3RG.4ZFaZYW95i0ieKN.RItNnrt5sVykGr6', '+3807493590', '/resources/users/photos/email30@gmail.com.png', 4, 'CUSTOMER');
insert into users (email, approved, blocked, first_name, last_name, passport, password, phone, photo_url, rate, user_role) VALUES ('email31@gmail.com', 1, 0, 'Ann', 'Garcia', 'ЕП 524550', '$2a$10$d2HsbmeXA6x6hz7Wm.3RG.4ZFaZYW95i0ieKN.RItNnrt5sVykGr6', '+3806648954', '/resources/users/photos/email31@gmail.com.png', 5, 'CUSTOMER');
insert into users (email, approved, blocked, first_name, last_name, passport, password, phone, photo_url, rate, user_role) VALUES ('martin@gmail.com', 1, 0, 'Martin', 'Oderky', 'ЕП 524550', '$2a$10$d2HsbmeXA6x6hz7Wm.3RG.4ZFaZYW95i0ieKN.RItNnrt5sVykGr6', '+3806648951', '/resources/users/photos/email31@gmail.com.png', 5, 'CUSTOMER');


insert into cars (car_id, vehicle_back_photourl, vehicle_front_photourl, vehicle_height, vehicle_length, vehicle_width, vehicle_weight, vehicle_name, vehicle_number, vehiclevin, driver_id) VALUES (1, '/resources/cars/photos/0_back_photo.png', '/resources/cars/photos/0_front_photo.png', 1.5500712722022927, 1.9375890902528659, 0.9687945451264329, 119.5101383369773, 'Toyota Corolla', 'GE 8385 QS', '8T3T1E9M3D5M1Q6I1', 'email2@gmail.com');
insert into cars (car_id, vehicle_back_photourl, vehicle_front_photourl, vehicle_height, vehicle_length, vehicle_width, vehicle_weight, vehicle_name, vehicle_number, vehiclevin, driver_id) VALUES (2, '/resources/cars/photos/1_back_photo.png', '/resources/cars/photos/1_front_photo.png', 0.8080390344564858, 1.0100487930706072, 0.5050243965353036, 415.7865796729437, 'Toyota Corolla', 'WE 5793 HJ', '1D8B2N5Q0U0X8C6R5', 'email3@gmail.com');
insert into cars (car_id, vehicle_back_photourl, vehicle_front_photourl, vehicle_height, vehicle_length, vehicle_width, vehicle_weight, vehicle_name, vehicle_number, vehiclevin, driver_id) VALUES (3, '/resources/cars/photos/2_back_photo.png', '/resources/cars/photos/2_front_photo.png', 0.957943440266158, 1.1974293003326975, 0.5987146501663487, 442.88949488521115, 'Honda Accord', 'TI 7964 SS', '6I2Z3Q2W1E5A3G8P0', 'email4@gmail.com');
insert into cars (car_id, vehicle_back_photourl, vehicle_front_photourl, vehicle_height, vehicle_length, vehicle_width, vehicle_weight, vehicle_name, vehicle_number, vehiclevin, driver_id) VALUES  (4, '/resources/cars/photos/3_back_photo.png', '/resources/cars/photos/3_front_photo.png', 1.0201986360234054, 1.2752482950292567, 0.6376241475146284, 284.1200178885863, 'Hyundai Accent', 'YZ 9629 VQ', '7A0T9E1O2M4F0O4W8', 'email5@gmail.com');
insert into cars (car_id, vehicle_back_photourl, vehicle_front_photourl, vehicle_height, vehicle_length, vehicle_width, vehicle_weight, vehicle_name, vehicle_number, vehiclevin, driver_id) VALUES (5, '/resources/cars/photos/4_back_photo.png', '/resources/cars/photos/4_front_photo.png', 1.3182691383724967, 1.6478364229656208, 0.8239182114828104, 247.05648966018066, 'Nissan Micra', 'GJ 2599 IW', '5Y5Y7L8G3X0X8E2B3', 'email6@gmail.com');
insert into cars (car_id, vehicle_back_photourl, vehicle_front_photourl, vehicle_height, vehicle_length, vehicle_width, vehicle_weight, vehicle_name, vehicle_number, vehiclevin, driver_id) VALUES  (6, '/resources/cars/photos/5_back_photo.png', '/resources/cars/photos/5_front_photo.png', 1.5366646124795462, 1.9208307655994328, 0.9604153827997164, 117.42434129855887, 'Hyundai Accent', 'TJ 8243 LH', '2M4M4J5O8Q9N6H7O0', 'email7@gmail.com');
insert into cars (car_id, vehicle_back_photourl, vehicle_front_photourl, vehicle_height, vehicle_length, vehicle_width, vehicle_weight, vehicle_name, vehicle_number, vehiclevin, driver_id) VALUES (7, '/resources/cars/photos/6_back_photo.png', '/resources/cars/photos/6_front_photo.png', 1.5876634510550929, 1.984579313818866, 0.992289656909433, 201.85722973183184, 'Kia Ceed', 'TQ 0435 VH', '4O5S2R7F8V2H2P2M3', 'email8@gmail.com');
insert into cars (car_id, vehicle_back_photourl, vehicle_front_photourl, vehicle_height, vehicle_length, vehicle_width, vehicle_weight, vehicle_name, vehicle_number, vehiclevin, driver_id) VALUES (8, '/resources/cars/photos/7_back_photo.png', '/resources/cars/photos/7_front_photo.png', 1.4351783762566452, 1.7939729703208065, 0.8969864851604032, 360.0958847899743, 'Nissan Micra', 'CG 6444 UG', '6U5A7O3G5K1Z0C0F1', 'email9@gmail.com');
insert into cars (car_id, vehicle_back_photourl, vehicle_front_photourl, vehicle_height, vehicle_length, vehicle_width, vehicle_weight, vehicle_name, vehicle_number, vehiclevin, driver_id) VALUES (9, '/resources/cars/photos/8_back_photo.png', '/resources/cars/photos/8_front_photo.png', 0.9874580090622121, 1.2343225113277652, 0.6171612556638826, 192.502680340209, 'Hyundai Accent', 'GF 6275 RQ', '8D7U2B8Y7D9Y1F1E3', 'email10@gmail.com');
insert into cars (car_id, vehicle_back_photourl, vehicle_front_photourl, vehicle_height, vehicle_length, vehicle_width, vehicle_weight, vehicle_name, vehicle_number, vehiclevin, driver_id) VALUES (10, '/resources/cars/photos/9_back_photo.png', '/resources/cars/photos/9_front_photo.png', 1.3086004034709848, 1.635750504338731, 0.8178752521693655, 373.4775641620177, 'Nissan Micra', 'XJ 1521 NW', '1U2D8R4Y7X9B4Q4Y1', 'email11@gmail.com');
insert into cars (car_id, vehicle_back_photourl, vehicle_front_photourl, vehicle_height, vehicle_length, vehicle_width, vehicle_weight, vehicle_name, vehicle_number, vehiclevin, driver_id) VALUES (11, '/resources/cars/photos/10_back_photo.png', '/resources/cars/photos/10_front_photo.png', 1.3121352199027518, 1.6401690248784397, 0.8200845124392199, 305.44239878314846, 'Scoda Octavia', 'FG 0292 VV', '8U4O6J3Q6U2C2A5N9', 'email12@gmail.com');
insert into cars (car_id, vehicle_back_photourl, vehicle_front_photourl, vehicle_height, vehicle_length, vehicle_width, vehicle_weight, vehicle_name, vehicle_number, vehiclevin, driver_id) VALUES (12, '/resources/cars/photos/11_back_photo.png', '/resources/cars/photos/11_front_photo.png', 0.9882125638331378, 1.2352657047914222, 0.6176328523957111, 311.5183776968188, 'Opel Meriva', 'OP 4013 NP', '1W6K8F5O0G5S5P0I0', 'email13@gmail.com');
insert into cars (car_id, vehicle_back_photourl, vehicle_front_photourl, vehicle_height, vehicle_length, vehicle_width, vehicle_weight, vehicle_name, vehicle_number, vehiclevin, driver_id) VALUES (13, '/resources/cars/photos/12_back_photo.png', '/resources/cars/photos/12_front_photo.png', 1.4792178148406585, 1.849022268550823, 0.9245111342754115, 487.85273070726487, 'Hyundai Accent', 'TZ 0163 HK', '2C5D0C6D9R8G8M1Y2', 'email14@gmail.com');
insert into cars (car_id, vehicle_back_photourl, vehicle_front_photourl, vehicle_height, vehicle_length, vehicle_width, vehicle_weight, vehicle_name, vehicle_number, vehiclevin, driver_id) VALUES (14, '/resources/cars/photos/13_back_photo.png', '/resources/cars/photos/13_front_photo.png', 1.1569132562724835, 1.4461415703406044, 0.7230707851703022, 98.75828870157592, 'Opel Meriva', 'OQ 8674 JE', '7L1T7A6Y9O1D5D2L1', 'email15@gmail.com');
insert into cars (car_id, vehicle_back_photourl, vehicle_front_photourl, vehicle_height, vehicle_length, vehicle_width, vehicle_weight, vehicle_name, vehicle_number, vehiclevin, driver_id) VALUES  (15, '/resources/cars/photos/14_back_photo.png', '/resources/cars/photos/14_front_photo.png', 1.2674880687471415, 1.5843600859339266, 0.7921800429669633, 519.4113604205183, 'Opel Meriva', 'FH 0044 RK', '1I8Q5W5S5V3G0C6H8', 'email16@gmail.com');
insert into cars (car_id, vehicle_back_photourl, vehicle_front_photourl, vehicle_height, vehicle_length, vehicle_width, vehicle_weight, vehicle_name, vehicle_number, vehiclevin, driver_id) VALUES  (16, '/resources/cars/photos/15_back_photo.png', '/resources/cars/photos/15_front_photo.png', 1.0991799287133348, 1.3739749108916683, 0.6869874554458342, 211.81043000031562, 'Nissan Micra', 'MV 4868 RV', '4B0U5O8R9U6L1K4C1', 'email17@gmail.com');
insert into cars (car_id, vehicle_back_photourl, vehicle_front_photourl, vehicle_height, vehicle_length, vehicle_width, vehicle_weight, vehicle_name, vehicle_number, vehiclevin, driver_id) VALUES (17, '/resources/cars/photos/16_back_photo.png', '/resources/cars/photos/16_front_photo.png', 0.9904125184747156, 1.2380156480933944, 0.6190078240466972, 60.03018321310003, 'Scoda Octavia', 'QU 4599 TH', '9N8U9B5Q8L5D5F4W1', 'email18@gmail.com');
insert into cars (car_id, vehicle_back_photourl, vehicle_front_photourl, vehicle_height, vehicle_length, vehicle_width, vehicle_weight, vehicle_name, vehicle_number, vehiclevin, driver_id) VALUES (18, '/resources/cars/photos/17_back_photo.png', '/resources/cars/photos/17_front_photo.png', 1.0346744217266206, 1.2933430271582758, 0.6466715135791379, 90.51255086457314, 'Honda Accord', 'VD 7939 NY', '6W8E4F0K6I6U6I2Q4', 'email19@gmail.com');

insert into orders(id, arrival_date, description, height, length, order_status, price, registration_date, weight, width, customer_user_id, location_from_id, location_to_id) values (1, '2016-12-02 00:00:00', 'Glass', 2.00, 6.00, 'OPEN', NULL, '2016-11-28 00:45:40', 5.00, 0.8, 'martin@gmail.com', 'ChIJV5oQCXzdOkcR4ngjARfFI0I', 'ChIJBUVa4U7P1EAR_kYBF9IxSXY');
insert into orders(id, arrival_date, description, height, length, order_status, price, registration_date, weight, width, customer_user_id, location_from_id, location_to_id) values (2, '2016-12-02 00:00:00', 'Tools', 15.00, 3.00, 'OPEN', NULL, '2016-11-28 00:45:40', 2.00, 4.00, 'martin@gmail.com', 'ChIJV5oQCXzdOkcR4ngjARfFI0I', 'ChIJBUVa4U7P1EAR_kYBF9IxSXY');
insert into orders(id, arrival_date, description, height, length, order_status, price, registration_date, weight, width, customer_user_id, location_from_id, location_to_id) values (3, '2016-12-02 00:00:00', 'Potatoes', 18.00, 2.00, 'OPEN', NULL, '2016-11-28 00:45:40', 5.50, 0.44, 'martin@gmail.com', 'ChIJV5oQCXzdOkcR4ngjARfFI0I', 'ChIJBUVa4U7P1EAR_kYBF9IxSXY');
insert into orders(id, arrival_date, description, height, length, order_status, price, registration_date, weight, width, customer_user_id, location_from_id, location_to_id) values (4, '2016-12-02 00:00:00', 'Clothing', 18.00, 2.00, 'IN_PROGRESS', NULL, '2016-11-28 00:45:40', 5.50, 0.44, 'martin@gmail.com', 'ChIJV5oQCXzdOkcR4ngjARfFI0I', 'ChIJBUVa4U7P1EAR_kYBF9IxSXY');
insert into orders(id, arrival_date, description, height, length, order_status, price, registration_date, weight, width, customer_user_id, location_from_id, location_to_id) values (5, '2016-12-02 00:00:00', 'Stuff', 18.00, 2.00, 'CLOSED', NULL, '2016-11-28 00:45:40', 5.50, 0.44, 'martin@gmail.com', 'ChIJV5oQCXzdOkcR4ngjARfFI0I', 'ChIJBUVa4U7P1EAR_kYBF9IxSXY');
insert into orders(id, arrival_date, description, height, length, order_status, price, registration_date, weight, width, customer_user_id, location_from_id, location_to_id) values (6, '2016-12-02 00:00:00', 'Glass', 18.00, 2.00, 'OPEN', NULL, '2016-11-28 00:45:40', 5.50, 0.44, 'martin@gmail.com', 'ChIJV5oQCXzdOkcR4ngjARfFI0I', 'ChIJBUVa4U7P1EAR_kYBF9IxSXY');
insert into orders(id, arrival_date, description, height, length, order_status, price, registration_date, weight, width, customer_user_id, location_from_id, location_to_id) values (7, '2016-12-02 00:00:00', 'Glass', 18.00, 2.00, 'APPROVED', NULL, '2016-11-28 00:45:40', 5.50, 0.44, 'martin@gmail.com', 'ChIJV5oQCXzdOkcR4ngjARfFI0I', 'ChIJBUVa4U7P1EAR_kYBF9IxSXY');

insert into DELIVERY.ORDERS (arrival_date, description, height, length, order_status, price, registration_date, weight, width, location_from_id, location_to_id, customer_user_id) VALUES ('2016-09-02 21:26:59.842', 'Electronics', 0.694128068479221, 1.388256136958442, 'CLOSED', 50.24857727129156, '2016-09-02 18:33:24.127', 25.714076096942843, 1.1106049095667536, 'ChIJV5oQCXzdOkcR4ngjARfFI0I', 'ChIJBUVa4U7P1EAR_kYBF9IxSXY', 'email21@gmail.com'), ('2016-09-30 09:58:11.998', 'Electronics', 0.5488111180797604, 1.0976222361595207, 'CLOSED', 511.9435956520485, '2016-09-28 10:26:15.907', 15.4321136156497, 0.8780977889276166, 'ChIJV5oQCXzdOkcR4ngjARfFI0I', 'ChIJBUVa4U7P1EAR_kYBF9IxSXY', 'email22@gmail.com'), ('2016-09-05 15:38:44.384', 'Electronics', 0.9349319056392957, 1.8698638112785915, 'CLOSED', 1621.6538243110515, '2016-09-04 06:01:17.492', 43.68882076261527, 1.4958910490228732, 'ChIJV5oQCXzdOkcR4ngjARfFI0I', 'ChIJBUVa4U7P1EAR_kYBF9IxSXY', 'email23@gmail.com'), ('2016-09-22 04:16:43.502', 'Autoparts', 0.5849543547900995, 1.169908709580199, 'CLOSED', 1746.6968201846646, '2016-09-21 00:56:12.71', 37.70061141403006, 0.9359269676641593, 'ChIJV5oQCXzdOkcR4ngjARfFI0I', 'ChIJBUVa4U7P1EAR_kYBF9IxSXY', 'email24@gmail.com'), ('2016-09-16 04:33:28.091', 'Potatoes', 0.9162210524146446, 1.8324421048292892, 'CLOSED', 1818.9360207058853, '2016-09-15 05:57:54.015', 7.429135622771859, 1.4659536838634315, 'ChIJV5oQCXzdOkcR4ngjARfFI0I', 'ChIJBUVa4U7P1EAR_kYBF9IxSXY', 'email25@gmail.com'), ('2016-09-03 22:46:06.619', 'Autoparts', 0.8896145264483422, 1.7792290528966843, 'CLOSED', 1605.331414015639, '2016-09-02 21:17:58.765', 17.698160632166537, 1.4233832423173476, 'ChIJV5oQCXzdOkcR4ngjARfFI0I', 'ChIJBUVa4U7P1EAR_kYBF9IxSXY', 'email26@gmail.com'), ('2016-09-10 20:38:12.631', 'Books', 0.7735040423273954, 1.547008084654791, 'CLOSED', 581.4483264382931, '2016-09-09 21:49:19.819', 36.38338717265137, 1.2376064677238328, 'ChIJV5oQCXzdOkcR4ngjARfFI0I', 'ChIJBUVa4U7P1EAR_kYBF9IxSXY', 'email27@gmail.com'), ('2016-09-19 13:02:48.7', 'Electronics', 0.8372876393858122, 1.6745752787716244, 'CLOSED', 1301.8383589907248, '2016-09-19 05:47:20.965', 10.190865989012753, 1.3396602230172996, 'ChIJV5oQCXzdOkcR4ngjARfFI0I', 'ChIJBUVa4U7P1EAR_kYBF9IxSXY', 'email28@gmail.com'), ('2016-10-02 02:12:54.357', 'Eggs', 0.5112387019471992, 1.0224774038943985, 'CLOSED', 1875.9380539644374, '2016-10-01 11:19:39.503', 19.735863366686413, 0.8179819231155188, 'ChIJV5oQCXzdOkcR4ngjARfFI0I', 'ChIJBUVa4U7P1EAR_kYBF9IxSXY', 'email29@gmail.com'), ('2016-09-24 04:39:52.701', 'Glass', 0.7257595884310084, 1.4515191768620168, 'CLOSED', 1682.2674302458627, '2016-09-22 06:47:50.559', 20.539040080503206, 1.1612153414896136, 'ChIJV5oQCXzdOkcR4ngjARfFI0I', 'ChIJBUVa4U7P1EAR_kYBF9IxSXY', 'email30@gmail.com'), ('2016-09-23 02:54:34.612', 'Autoparts', 0.5427952587679687, 1.0855905175359375, 'CLOSED', 914.2245309778019, '2016-09-21 03:09:18.074', 9.81700890585551, 0.8684724140287501, 'ChIJV5oQCXzdOkcR4ngjARfFI0I', 'ChIJBUVa4U7P1EAR_kYBF9IxSXY', 'email31@gmail.com'), ('2016-09-20 23:35:29.418', 'Painting', 0.6812310109363708, 1.3624620218727417, 'CLOSED', 1209.2350686683242, '2016-09-20 07:14:18.205', 22.462417401793317, 1.0899696174981934, 'ChIJV5oQCXzdOkcR4ngjARfFI0I', 'ChIJBUVa4U7P1EAR_kYBF9IxSXY', 'email21@gmail.com'), ('2016-09-19 07:17:45.925', 'Clothing', 0.5086719640164455, 1.017343928032891, 'CLOSED', 1729.5512185591122, '2016-09-18 11:47:10.3', 20.69191083698349, 0.8138751424263129, 'ChIJV5oQCXzdOkcR4ngjARfFI0I', 'ChIJBUVa4U7P1EAR_kYBF9IxSXY', 'email22@gmail.com'), ('2016-09-23 21:17:12.172', 'Eggs', 0.9020732827919586, 1.8041465655839173, 'CLOSED', 1146.2576279247508, '2016-09-22 19:10:31.011', 48.01308254677667, 1.4433172524671338, 'ChIJV5oQCXzdOkcR4ngjARfFI0I', 'ChIJBUVa4U7P1EAR_kYBF9IxSXY', 'email23@gmail.com'), ('2016-09-28 11:16:45.63', 'Clothing', 0.5423278662926516, 1.0846557325853032, 'CLOSED', 424.21951337932705, '2016-09-26 21:35:28.366', 14.81703381830547, 0.8677245860682427, 'ChIJV5oQCXzdOkcR4ngjARfFI0I', 'ChIJBUVa4U7P1EAR_kYBF9IxSXY', 'email24@gmail.com'), ('2016-09-08 19:57:02.402', 'Tools', 0.7346743601420794, 1.4693487202841589, 'CLOSED', 1699.72954439572, '2016-09-07 02:25:31.982', 28.188945847022595, 1.1754789762273272, 'ChIJV5oQCXzdOkcR4ngjARfFI0I', 'ChIJBUVa4U7P1EAR_kYBF9IxSXY', 'email25@gmail.com'), ('2016-09-24 12:31:46.422', 'Potatoes', 0.7723081160482874, 1.5446162320965748, 'CLOSED', 602.652754257001, '2016-09-24 09:20:42.144', 40.3296441782161, 1.23569298567726, 'ChIJV5oQCXzdOkcR4ngjARfFI0I', 'ChIJBUVa4U7P1EAR_kYBF9IxSXY', 'email26@gmail.com'), ('2016-09-21 01:26:54.913', 'Documents', 0.8222408728088157, 1.6444817456176315, 'CLOSED', 1316.5122219840007, '2016-09-19 07:10:41.836', 16.580109714679224, 1.3155853964941053, 'ChIJV5oQCXzdOkcR4ngjARfFI0I', 'ChIJBUVa4U7P1EAR_kYBF9IxSXY', 'email27@gmail.com'), ('2016-09-07 19:48:24.937', 'Electronics', 0.6507455209727928, 1.3014910419455856, 'CLOSED', 1649.1824386896315, '2016-09-06 05:31:56.202', 48.012629547139504, 1.0411928335564686, 'ChIJV5oQCXzdOkcR4ngjARfFI0I', 'ChIJBUVa4U7P1EAR_kYBF9IxSXY', 'email28@gmail.com'), ('2016-09-08 05:02:48.645', 'Documents', 0.5079157843773199, 1.0158315687546398, 'CLOSED', 651.3470608266778, '2016-09-06 10:26:30.025', 27.65359373471542, 0.8126652550037119, 'ChIJV5oQCXzdOkcR4ngjARfFI0I', 'ChIJBUVa4U7P1EAR_kYBF9IxSXY', 'email29@gmail.com'), ('2016-09-26 06:03:15.615', 'Glass', 0.8424743018432179, 1.6849486036864358, 'CLOSED', 255.9767283606841, '2016-09-24 06:50:35.536', 28.701973958227942, 1.3479588829491487, 'ChIJV5oQCXzdOkcR4ngjARfFI0I', 'ChIJBUVa4U7P1EAR_kYBF9IxSXY', 'email30@gmail.com'), ('2016-09-09 06:50:28.266', 'Eggs', 0.5732613362037329, 1.1465226724074657, 'CLOSED', 769.980469192192, '2016-09-08 18:35:42.851', 9.308193152721747, 0.9172181379259726, 'ChIJV5oQCXzdOkcR4ngjARfFI0I', 'ChIJBUVa4U7P1EAR_kYBF9IxSXY', 'email31@gmail.com'), ('2016-09-28 01:23:31.73', 'Tools', 0.7987179753483777, 1.5974359506967555, 'CLOSED', 211.78901113831253, '2016-09-26 14:32:23.346', 10.364301399999611, 1.2779487605574045, 'ChIJV5oQCXzdOkcR4ngjARfFI0I', 'ChIJBUVa4U7P1EAR_kYBF9IxSXY', 'email21@gmail.com'), ('2016-09-06 20:25:27.271', 'Books', 0.5728464997699043, 1.1456929995398086, 'CLOSED', 1969.850006532611, '2016-09-06 07:51:56.48', 10.902141784036292, 0.9165543996318469, 'ChIJV5oQCXzdOkcR4ngjARfFI0I', 'ChIJBUVa4U7P1EAR_kYBF9IxSXY', 'email22@gmail.com'), ('2016-09-27 09:41:39.111', 'Eggs', 0.5446351341354302, 1.0892702682708604, 'CLOSED', 1623.0345008106551, '2016-09-27 00:25:00.649', 30.053600831084772, 0.8714162146166884, 'ChIJV5oQCXzdOkcR4ngjARfFI0I', 'ChIJBUVa4U7P1EAR_kYBF9IxSXY', 'email23@gmail.com'), ('2016-10-01 15:17:07.259', 'Tools', 0.9858545753103629, 1.9717091506207258, 'CLOSED', 1816.3147668209838, '2016-09-30 22:51:15.637', 25.225103588805773, 1.5773673204965808, 'ChIJV5oQCXzdOkcR4ngjARfFI0I', 'ChIJBUVa4U7P1EAR_kYBF9IxSXY', 'email24@gmail.com'), ('2016-09-15 03:51:15.855', 'Potatoes', 0.8931288545066534, 1.7862577090133067, 'CLOSED', 1527.3171344411116, '2016-09-13 21:41:45.656', 5.792750827663456, 1.4290061672106456, 'ChIJV5oQCXzdOkcR4ngjARfFI0I', 'ChIJBUVa4U7P1EAR_kYBF9IxSXY', 'email25@gmail.com'), ('2016-09-10 18:29:56.55', 'Clothing', 0.9514660559168282, 1.9029321118336564, 'CLOSED', 1739.1420695997733, '2016-09-10 09:14:11.662', 26.678010047712828, 1.5223456894669252, 'ChIJV5oQCXzdOkcR4ngjARfFI0I', 'ChIJBUVa4U7P1EAR_kYBF9IxSXY', 'email26@gmail.com'), ('2016-09-24 20:15:42.118', 'Autoparts', 0.6376446197141914, 1.2752892394283828, 'CLOSED', 861.4294274675271, '2016-09-23 23:25:10.396', 15.246435328311456, 1.0202313915427064, 'ChIJV5oQCXzdOkcR4ngjARfFI0I', 'ChIJBUVa4U7P1EAR_kYBF9IxSXY', 'email27@gmail.com'), ('2016-09-26 10:47:07.186', 'Tools', 0.7211369742433471, 1.4422739484866942, 'CLOSED', 73.73420806003719, '2016-09-25 06:36:10.779', 23.986419968683816, 1.1538191587893554, 'ChIJV5oQCXzdOkcR4ngjARfFI0I', 'ChIJBUVa4U7P1EAR_kYBF9IxSXY', 'email28@gmail.com'), ('2016-09-17 20:58:56.126', 'Documents', 0.8957782373573142, 1.7915564747146284, 'CLOSED', 447.3103124813697, '2016-09-17 12:02:53.256', 48.034356676745965, 1.4332451797717027, 'ChIJV5oQCXzdOkcR4ngjARfFI0I', 'ChIJBUVa4U7P1EAR_kYBF9IxSXY', 'email29@gmail.com'), ('2016-09-09 04:51:31.417', 'Books', 0.9373356638203058, 1.8746713276406115, 'CLOSED', 246.38297089380362, '2016-09-09 04:32:24.708', 31.02443256919899, 1.4997370621124892, 'ChIJV5oQCXzdOkcR4ngjARfFI0I', 'ChIJBUVa4U7P1EAR_kYBF9IxSXY', 'email30@gmail.com'), ('2016-09-02 21:47:38.958', 'Documents', 0.6621594477176078, 1.3243188954352156, 'CLOSED', 1385.0333529168806, '2016-09-01 04:33:52.325', 7.150946206249284, 1.0594551163481725, 'ChIJV5oQCXzdOkcR4ngjARfFI0I', 'ChIJBUVa4U7P1EAR_kYBF9IxSXY', 'email31@gmail.com');

insert into offers (offer_id, approved, car_id, order_id) VALUES (1, false, 1, 1);
insert into offers (offer_id, approved, car_id, order_id) VALUES (2, false, 1, 2);
insert into offers (offer_id, approved, car_id, order_id) VALUES (3, false, 1, 3);
insert into offers (offer_id, approved, car_id, order_id) VALUES (4, false, 3, 2);
insert into offers (offer_id, approved, car_id, order_id) VALUES (5, false, 3, 3);
insert into offers (offer_id, approved, car_id, order_id) VALUES (6, false, 4, 1);
insert into offers (offer_id, approved, car_id, order_id) VALUES (7, false, 4, 3);
insert into offers (offer_id, approved, car_id, order_id) VALUES (8, false, 5, 1);
insert into offers (offer_id, approved, car_id, order_id) VALUES (9, false, 5, 2);
insert into offers (offer_id, approved, car_id, order_id) VALUES (10, false, 5, 3);
insert into offers (offer_id, approved, car_id, order_id) VALUES (11, false, 6, 1);
insert into offers (offer_id, approved, car_id, order_id) VALUES (12, false, 6, 3);
insert into offers (offer_id, approved, car_id, order_id) VALUES (13, false, 7, 1);
insert into offers (offer_id, approved, car_id, order_id) VALUES (14, false, 7, 2);
insert into offers (offer_id, approved, car_id, order_id) VALUES (15, false, 9, 1);
insert into offers (offer_id, approved, car_id, order_id) VALUES (16, false, 9, 2);
insert into offers (offer_id, approved, car_id, order_id) VALUES (17, false, 9, 3);
insert into offers (offer_id, approved, car_id, order_id) VALUES (18, false, 10, 1);
insert into offers (offer_id, approved, car_id, order_id) VALUES (19, false, 10, 2);
insert into offers (offer_id, approved, car_id, order_id) VALUES (20, false, 11, 1);
insert into offers (offer_id, approved, car_id, order_id) VALUES (21, false, 11, 2);
insert into offers (offer_id, approved, car_id, order_id) VALUES (22, false, 11, 3);
insert into offers (offer_id, approved, car_id, order_id) VALUES (23, false, 12, 3);
insert into offers (offer_id, approved, car_id, order_id) VALUES (24, false, 13, 1);
insert into offers (offer_id, approved, car_id, order_id) VALUES (25, true, 2, 4);
insert into offers (offer_id, approved, car_id, order_id) VALUES (26, true, 15, 5);
insert into offers (offer_id, approved, car_id, order_id) VALUES (27, true, 15, 7);

insert into DELIVERY.OFFERS (approved, car_id, order_id) VALUES (true, 1, 34), (true, 14, 8), (true, 10, 18), (true, 11, 13), (true, 17, 29), (true, 2, 38), (true, 17, 17), (true, 3, 31), (true, 10, 30), (true, 14, 14), (true, 12, 35), (true, 10, 16), (true, 16, 27), (true, 4, 37), (true, 8, 21), (true, 3, 36), (true, 18, 40), (true, 9, 23), (true, 4, 20), (true, 2, 24), (true, 4, 19), (true, 17, 33), (true, 17, 9), (true, 15, 25), (true, 14, 10), (true, 8, 39), (true, 13, 11), (true, 4, 15), (true, 3, 26), (true, 9, 32), (true, 16, 28), (true, 6, 22), (true, 15, 12);

insert into DELIVERY.FEEDBACKS (approved, created_on, rate, text, order_id, email) VALUES (1, '2016-09-03 20:59:03.589', 5, 'VERY FAST SHIPPING, I AM VERY HAPPY WITH THIS TRANSPORTER!!!!', 8, 'email21@gmail.com'), (1, '2016-09-30 18:30:24.99', 3, 'item arrived as advertised TRANSPORTER not especially helpful.', 9, 'email22@gmail.com'), (1, '2016-09-05 19:20:48.67', 5, 'VERY FAST SHIPPING, I AM VERY HAPPY WITH THIS TRANSPORTER!!!!', 10, 'email23@gmail.com'), (1, '2016-09-22 07:08:17.092', 1, 'The delivery was not as described.', 11, 'email24@gmail.com'), (1, '2016-09-16 18:03:23.543', 1, 'The delivery was not as described.', 12, 'email25@gmail.com'), (1, '2016-09-04 20:01:55.321', 2, 'Nice JOB, expensive shipping', 13, 'email26@gmail.com'), (1, '2016-09-11 19:37:48.3', 2, 'Nice JOB, expensive shipping', 14, 'email27@gmail.com'), (1, '2016-09-19 22:12:02.399', 5, 'VERY FAST SHIPPING, I AM VERY HAPPY WITH THIS TRANSPORTER!!!!', 15, 'email28@gmail.com'), (1, '2016-10-02 15:22:25.603', 2, 'Nice JOB, expensive shipping', 16, 'email29@gmail.com'), (1, '2016-09-25 02:23:40.643', 2, 'Nice JOB, expensive shipping', 17, 'email30@gmail.com'), (1, '2016-09-23 13:47:37.307', 4, 'A++; very FAST AS USUAL; Received in excellent condition; Great Transporter!', 18, 'email31@gmail.com'), (1, '2016-09-21 20:05:00.353', 3, 'item arrived as advertised TRANSPORTER not especially helpful.', 19, 'email21@gmail.com'), (1, '2016-09-20 02:28:51.389', 1, 'The delivery was not as described.', 20, 'email22@gmail.com'), (1, '2016-09-24 08:50:11.982', 4, 'A++; very FAST AS USUAL; Received in excellent condition; Great Transporter!', 21, 'email23@gmail.com'), (1, '2016-09-28 11:38:18.179', 4, 'A++; very FAST AS USUAL; Received in excellent condition; Great Transporter!', 22, 'email24@gmail.com'), (1, '2016-09-09 15:36:02.76', 5, 'VERY FAST SHIPPING, I AM VERY HAPPY WITH THIS TRANSPORTER!!!!', 23, 'email25@gmail.com'), (1, '2016-09-25 10:22:57.296', 2, 'Nice JOB, expensive shipping', 24, 'email26@gmail.com'), (1, '2016-09-21 11:40:13.014', 5, 'VERY FAST SHIPPING, I AM VERY HAPPY WITH THIS TRANSPORTER!!!!', 25, 'email27@gmail.com'), (1, '2016-09-08 17:10:52.545', 2, 'Nice JOB, expensive shipping', 26, 'email28@gmail.com'), (1, '2016-09-08 21:56:58.996', 4, 'A++; very FAST AS USUAL; Received in excellent condition; Great Transporter!', 27, 'email29@gmail.com'), (1, '2016-09-26 11:22:02.72', 5, 'VERY FAST SHIPPING, I AM VERY HAPPY WITH THIS TRANSPORTER!!!!', 28, 'email30@gmail.com'), (1, '2016-09-10 02:44:22.821', 4, 'A++; very FAST AS USUAL; Received in excellent condition; Great Transporter!', 29, 'email31@gmail.com'), (1, '2016-09-28 16:11:55.098', 2, 'Nice JOB, expensive shipping', 30, 'email21@gmail.com'), (1, '2016-09-07 19:35:49.832', 2, 'Nice JOB, expensive shipping', 31, 'email22@gmail.com'), (1, '2016-09-27 22:29:56.568', 2, 'Nice JOB, expensive shipping', 32, 'email23@gmail.com'), (1, '2016-10-01 23:09:52.148', 4, 'A++; very FAST AS USUAL; Received in excellent condition; Great Transporter!', 33, 'email24@gmail.com'), (1, '2016-09-15 15:07:29.379', 2, 'Nice JOB, expensive shipping', 34, 'email25@gmail.com'), (1, '2016-09-11 14:04:19.938', 2, 'Nice JOB, expensive shipping', 35, 'email26@gmail.com'), (1, '2016-09-25 02:25:33.733', 2, 'Nice JOB, expensive shipping', 36, 'email27@gmail.com'), (1, '2016-09-26 20:26:52.28', 3, 'item arrived as advertised TRANSPORTER not especially helpful.', 37, 'email28@gmail.com'), (1, '2016-09-18 16:07:55.823', 2, 'Nice JOB, expensive shipping', 38, 'email29@gmail.com'), (1, '2016-09-09 10:02:35.882', 3, 'item arrived as advertised TRANSPORTER not especially helpful.', 39, 'email30@gmail.com'), (1, '2016-09-03 20:45:30.725', 4, 'A++; very FAST AS USUAL; Received in excellent condition; Great Transporter!', 40, 'email31@gmail.com');


SET FOREIGN_KEY_CHECKS=1;

CREATE TABLE hour_dimension (hour INT NOT NULL, PRIMARY KEY (hour));

CREATE TABLE day_dimension (day INT NOT NULL, PRIMARY KEY (day));

CREATE TABLE month_dimension (month_id INT NOT NULL, month VARCHAR(10) NULL, PRIMARY KEY (month_id));

INSERT INTO hour_dimension (hour) VALUES (0), (1), (2), (3), (4), (5), (6), (7), (8), (9), (10), (11);
INSERT INTO hour_dimension (hour) VALUES (12), (13), (14), (15), (16), (17), (18), (19), (20), (21), (22), (23);

INSERT INTO day_dimension (day) VALUES (1), (2), (3), (4), (5), (6), (7), (8), (9), (10), (11), (12), (13), (14), (15);
INSERT INTO day_dimension (day) VALUES (16), (17), (18), (19), (20), (21), (22), (23), (24), (25), (26), (27), (28), (29), (30), (31);

INSERT INTO month_dimension (month_id, month) VALUES (1, 'January');
INSERT INTO month_dimension (month_id, month) VALUES (2, 'February');
INSERT INTO month_dimension (month_id, month) VALUES (3, 'March');
INSERT INTO month_dimension (month_id, month) VALUES (4, 'April');
INSERT INTO month_dimension (month_id, month) VALUES (5, 'May');
INSERT INTO month_dimension (month_id, month) VALUES (6, 'June');
INSERT INTO month_dimension (month_id, month) VALUES (7, 'July');
INSERT INTO month_dimension (month_id, month) VALUES (8, 'August ');
INSERT INTO month_dimension (month_id, month) VALUES (9, 'September');
INSERT INTO month_dimension (month_id, month) VALUES (10, 'October');
INSERT INTO month_dimension (month_id, month) VALUES (11, 'November');
INSERT INTO month_dimension (month_id, month) VALUES (12, 'December');

INSERT INTO route_city (visit_date, x, y, order_id) VALUES ('2016-09-11 17:16:27.218', 49.866071, 23.957118, 1);
INSERT INTO route_city (visit_date, x, y, order_id) VALUES ('2016-09-11 17:16:27.218', 49.9602678,24.5536551, 1);
INSERT INTO route_city (visit_date, x, y, order_id) VALUES ('2016-09-11 17:16:27.218', 50.0711054,25.1656323, 1);
INSERT INTO route_city (visit_date, x, y, order_id) VALUES ('2016-09-11 17:16:27.218', 50.379910, 25.707483, 1);
INSERT INTO route_city (visit_date, x, y, order_id) VALUES ('2016-09-11 17:16:27.218', 50.602637, 26.519247, 1);
INSERT INTO route_city (visit_date, x, y, order_id) VALUES ('2016-09-11 17:16:27.218', 50.602061, 27.016033, 1);
INSERT INTO route_city (visit_date, x, y, order_id) VALUES ('2016-09-11 17:16:27.218', 50.615450, 27.251681, 1);
INSERT INTO route_city (visit_date, x, y, order_id) VALUES ('2016-09-11 17:16:27.218', 50.581687, 27.688400, 1);
INSERT INTO route_city (visit_date, x, y, order_id) VALUES ('2016-09-11 17:16:27.218', 50.356229, 28.317024, 1);
INSERT INTO route_city (visit_date, x, y, order_id) VALUES ('2016-09-11 17:16:27.218', 50.389874, 29.491531, 1);
INSERT INTO route_city (visit_date, x, y, order_id) VALUES ('2016-09-11 17:16:27.218', 50.425987, 29.961527, 1);
INSERT INTO route_city (visit_date, x, y, order_id) VALUES ('2016-09-11 17:16:27.218', 50.456188, 30.436237, 1);