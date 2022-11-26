INSERT INTO user(uuid, about, cpf, email, first_name, gender, last_name, login, member_since, password, phone, profile_image_url, refresh_token, refresh_token_expiration, user_address) VALUES('2CQEK2FfvSNWHM1058sQ3U3c0Zq1', 'The master account', NULL, 'master@carus.com', 'Master', NULL, 'Carus', 'master@carus.com', '2022-11-25', '$2a$10$kcZpLmfATk8dM08.m/AdZeCmNTel63XDX9.Rbx/iLnWFfQd/Cj3GC', NULL, NULL, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXN0ZXJAY2FydXMuY29tIiwiZXhwIjozMzcwMjE1OTUxfQ.x_wx-2lyAGhTc1vXVgbI-6ijPhd81F5Cy3j0W75W22fU-sD4FKtbI_arfL2Twck2jWfuBnRna2hOeWclCHEcTA', '2023-11-25 01:32:55.000', null);
INSERT INTO user(uuid, about, cpf, email, first_name, gender, last_name, login, member_since, password, phone, profile_image_url, refresh_token, refresh_token_expiration, user_address) VALUES('ebf85b35-a127-4e9a-860a-1aec6f56d1c1', 'The submaster account', NULL, 'master2@gmail.com', 'Carus', NULL, 'Submaster', 'submaster', '2022-11-06', '$2a$10$Q3z73RSdgmPh7qcp2.8mveN7zncs7k3yT4Y.tKNWlZuoY9yM3arpa', NULL, NULL, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXN0ZXIiLCJleHAiOjMzNjcwNDIxNzB9.dxx5abEhlzJMjGyil-dmQcS2r-O9WiwPK1IG_YgSI8fTIYmDM_34qdwEmgxNRg_KUKZYMO6qNrkEDR283E3YmA', '2023-11-06 15:14:45.000', null);
INSERT INTO user(uuid, about, cpf, email, first_name, gender, last_name, login, member_since, password, phone, profile_image_url, refresh_token, refresh_token_expiration, user_address) VALUES('5lAzWu3NrMhEUr7cpTTMHEfPdKp2', '', NULL, 'igor.ponchielli@gmail.com', 'Igor', NULL, 'Ponchielli', 'igor.ponchielli@gmail.com', '2022-11-25', '$2a$10$8k4dJoEva86SvLt/3xdUFeyQ0UN5dKNnzKKHMnto8/VvlqR1IARWm', NULL, NULL, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJpZ29yLnBvbmNoaWVsbGlAZ21haWwuY29tIiwiZXhwIjozMzcwMjEwNTExfQ.gP0rqVoBYavPZrd2Sw3rsatvfafyKu1blxU2YJ2z7sykZ288H3MUkuAvZQfnhfzUTr5Mu6RU7-CdBXjyAy0n8Q', '2023-11-25 00:47:35.000', null);
INSERT INTO user(uuid, about, cpf, email, first_name, gender, last_name, login, member_since, password, phone, profile_image_url, refresh_token, refresh_token_expiration, user_address) VALUES('JppkekPHgFhC2oS51ucrDtAfKSi2', '', NULL, 'jefmaylon@gmail.com', 'Jefferson ', NULL, 'Zickuhr ', 'jefmaylon@gmail.com', '2022-11-25', '$2a$10$Qk0jprY2EPmg1hcehiF9quJ8ox.YNhfhdUG08Fhxv64aM1r3ZbTkm', NULL, NULL, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqZWZtYXlsb25AZ21haWwuY29tIiwiZXhwIjozMzcwMjE0MTM5fQ.FS2g5Lb0MVyWh2fXOkVjDfHpS_BWfIfMPIqS_3Bx87mX73wrhUecLwhGph7bHDljrYVpWKbqT0vT_01PAjZFFA', '2023-11-25 01:17:49.000', null);
INSERT INTO car(id, address, brand, category, description, doors, fuel, gear_shift, latitude, longitude, model, plate, price, seats, trunk, `year`, user_uuid) VALUES(1, 'Rua Engenheiro Paul Werner, 94 - Itoupava Seca, Blumenau - SC, 89030-100, Brasil', 'Chevrolet', 'HATCHBACK', 'Que carro bacana meus amiguinhos', 4, 'GASOLINE', 'MANUAL', -26.90130932, -49.07831423, 'Astra', 'MGC4509', 34.6, 5, 2, 2002, '2CQEK2FfvSNWHM1058sQ3U3c0Zq1');
INSERT INTO car(id, address, brand, category, description, doors, fuel, gear_shift, latitude, longitude, model, plate, price, seats, trunk, `year`, user_uuid) VALUES(2, 'R. Christiano Karsten, 1197 - Testo Salto, Blumenau - SC, 89074-400, Brasil', 'Chevrolet', 'SEDAN', 'Que carro bacana meus amiguinhos', 4, 'DIESEL', 'MANUAL', -26.85272984, -49.14085597, 'Corsa', 'MGC4529', 34.6, 5, 2, 2004, '2CQEK2FfvSNWHM1058sQ3U3c0Zq1');
INSERT INTO car(id, address, brand, category, description, doors, fuel, gear_shift, latitude, longitude, model, plate, price, seats, trunk, `year`, user_uuid) VALUES(3, 'R. Maria C Saut, 31 - Estados, Indaial - SC, 89130-000, Brasil', 'Hyundai', 'HATCHBACK', 'Que carro bacana meus amiguinhos', 4, 'GASOLINE', 'AUTOMATIC', -26.90853011, -49.23453849, 'HB20', 'GGC4532', 50.6, 5, 2, 2020, '5lAzWu3NrMhEUr7cpTTMHEfPdKp2');
INSERT INTO car(id, address, brand, category, description, doors, fuel, gear_shift, latitude, longitude, model, plate, price, seats, trunk, `year`, user_uuid) VALUES(4, 'Rua Engenheiro Paul Werner, 94 - Itoupava Seca, Blumenau - SC, 89030-100, Brasil', 'Porsche', 'SEDAN', 'Que carro bacana meus amiguinhos', 2, 'GASOLINE', 'AUTOMATIC', -26.90130932, -49.07831423, 'Carrera GT', 'RRC4532', 202.6, 2, 0, 2022, '5lAzWu3NrMhEUr7cpTTMHEfPdKp2');
INSERT INTO car(id, address, brand, category, description, doors, fuel, gear_shift, latitude, longitude, model, plate, price, seats, trunk, `year`, user_uuid) VALUES(5, 'Rua Hermann Weege, 151 - Centro, Pomerode - SC, 89107-000, Brasil', 'Audi', 'WAGON', 'Que carro bacana meus amiguinhos', 4, 'GASOLINE', 'AUTOMATIC', -26.73763330, -49.17444000, 'A4 Avant', 'RRC8932', 47.6, 5, 6, 2022, '5lAzWu3NrMhEUr7cpTTMHEfPdKp2');
INSERT INTO rental(id, address, is_review, latitude, location_date, longitude, price, return_date, status, car_id, user_uuid) VALUES(1, 'Rua Hermann Weege, 151 - Centro, Pomerode - SC, 89107-000, Brasil', 1 , -26.68579, '2019-03-31 22:32:07.000', -49.1567383, 25.00, '2019-03-31 22:32:07.000', 'RENTED', 1, 'JppkekPHgFhC2oS51ucrDtAfKSi2');
INSERT INTO rental(id, address, is_review, latitude, location_date, longitude, price, return_date, status, car_id, user_uuid) VALUES(2, 'Rua Monteiro Lobato, 82 - Tribess, Blumenau - SC, 89057-390, Brasil', 0, -26.68575300, '2022-11-24 22:32:06.000', -49.15675343, 45.00, '2022-11-26 22:32:06.000', 'RESERVED', 3, '5lAzWu3NrMhEUr7cpTTMHEfPdKp2');
INSERT INTO rental(id, address, is_review, latitude, location_date, longitude, price, return_date, status, car_id, user_uuid) VALUES(3, 'R. Sete de Setembro, 945 - Carijós, Indaial - SC, 89130-000, Brasil', 0, -26.88206040, '2022-11-25 22:23:00.000', -49.23964200, 675.00, '2022-11-28 22:23:00.000', 'PENDING', 5, '2CQEK2FfvSNWHM1058sQ3U3c0Zq1');
INSERT INTO rental(id, address, is_review, latitude, location_date, longitude, price, return_date, status, car_id, user_uuid) VALUES(4, 'Rua Galileu Galilei, 79 - Fortaleza, Blumenau - SC, 89055-130, Brasil', 0, -26.68575300, '2022-11-20 22:32:07.000', -49.15675343, 130.00, '2022-11-28 22:32:07.000', 'IN_PROGRESS', 4, 'JppkekPHgFhC2oS51ucrDtAfKSi2');
INSERT INTO rental(id, address, is_review, latitude, location_date, longitude, price, return_date, status, car_id, user_uuid) VALUES(5, 'Rua Antônio Junkes, 86 - Fortaleza, Blumenau - SC, 89056-090, Brasil', 0, -26.68575300, '2022-11-30 22:32:07.000', -49.15675343, 25.00, '2022-12-12 22:32:07.000', 'LATE', 2, '5lAzWu3NrMhEUr7cpTTMHEfPdKp2');
INSERT INTO rental(id, address, is_review, latitude, location_date, longitude, price, return_date, status, car_id, user_uuid) VALUES(6, 'Rua Bruno Morell, 45 - Testo Salto, Blumenau - SC, 89074-580, Brasil', 0, -26.68575300, '2022-11-25 22:32:07.000', -49.15675343, 25.00, '2022-11-25 22:35:07.000', 'REFUSED', 2, '5lAzWu3NrMhEUr7cpTTMHEfPdKp2');
INSERT INTO rate_user(id, date, description, rate, evaluator_user_uuid, rated_user_uuid, rental_id) VALUES(1, '2022-08-08', 'Ótimo carro', 5.00, '5lAzWu3NrMhEUr7cpTTMHEfPdKp2', 'JppkekPHgFhC2oS51ucrDtAfKSi2', 1);
INSERT INTO rate_user(id, date, description, rate, evaluator_user_uuid, rated_user_uuid, rental_id) VALUES(2, '2022-08-08', 'Bom carro', 4.00, 'JppkekPHgFhC2oS51ucrDtAfKSi2', '5lAzWu3NrMhEUr7cpTTMHEfPdKp2', 2);
INSERT INTO rate_car(id, date, description, rate, car_id, user_uuid, rental_id) VALUES(1, '2022-08-08', 'Belo carro', 5.0, 1, 'JppkekPHgFhC2oS51ucrDtAfKSi2', 3);
INSERT INTO rate_car(id, date, description, rate, car_id, user_uuid, rental_id) VALUES(2, '2022-08-08', 'Mais ou menos', 2.0, 1, 'JppkekPHgFhC2oS51ucrDtAfKSi2', 4);
INSERT INTO image(id, url) VALUES(1, 'https://firebasestorage.googleapis.com/v0/b/carus-app-363822.appspot.com/o/demo%2FAstra%202002%2F1-1652576189.jpg?alt=media&token=eccc7476-47d1-42d0-8514-d5f04283280b');
INSERT INTO image(id, url) VALUES(2, 'https://firebasestorage.googleapis.com/v0/b/carus-app-363822.appspot.com/o/demo%2FAstra%202002%2F3-1652576193.jpg?alt=media&token=7f377fa0-9d5b-46e0-96ae-f13b9030efad');
INSERT INTO image(id, url) VALUES(3, 'https://firebasestorage.googleapis.com/v0/b/carus-app-363822.appspot.com/o/demo%2FAstra%202002%2F8-1652576205.jpg?alt=media&token=7ae24a69-0ceb-4604-816c-b9b04f4069fc');
INSERT INTO image(id, url) VALUES(4, 'https://firebasestorage.googleapis.com/v0/b/carus-app-363822.appspot.com/o/demo%2Fcorsa%202004%2Fchevrolet-corsa-1.0-mpfi-classic-sedan-8v-alcool-4p-manual-wmimagem10590905255.jpg?alt=media&token=50ddfbd3-72cd-4d58-8522-b0641ef730be');
INSERT INTO image(id, url) VALUES(5, 'https://firebasestorage.googleapis.com/v0/b/carus-app-363822.appspot.com/o/demo%2Fcorsa%202004%2Fchevrolet-corsa-1.0-mpfi-classic-sedan-8v-alcool-4p-manual-wmimagem10591245946.jpg?alt=media&token=818f72d2-29b1-4baa-acc0-0cf2fcaee199');
INSERT INTO image(id, url) VALUES(6, 'https://firebasestorage.googleapis.com/v0/b/carus-app-363822.appspot.com/o/demo%2FHB20%202020%2Fnovo-hyundai-hb20-1.webp?alt=media&token=7a273d96-4e8e-4f04-b799-f0ac6609a9e5');
INSERT INTO image(id, url) VALUES(7, 'https://firebasestorage.googleapis.com/v0/b/carus-app-363822.appspot.com/o/demo%2FHB20%202020%2Fnovo-hyundai-hb20-2.webp?alt=media&token=258faab4-ebb2-42c8-9f53-d2619060f87a');
INSERT INTO image(id, url) VALUES(8, 'https://firebasestorage.googleapis.com/v0/b/carus-app-363822.appspot.com/o/demo%2FPorsche%20Carrera%20GT%2Fporscheschumacher01.webp?alt=media&token=55a52ce4-292c-4f4f-bff0-247d48d3f5f3');
INSERT INTO image(id, url) VALUES(9, 'https://firebasestorage.googleapis.com/v0/b/carus-app-363822.appspot.com/o/demo%2FPorsche%20Carrera%20GT%2Fporscheschumacher02.webp?alt=media&token=5250bd89-efc5-451e-90ff-1e9de32b15df');
INSERT INTO image(id, url) VALUES(10, 'https://firebasestorage.googleapis.com/v0/b/carus-app-363822.appspot.com/o/demo%2FPorsche%20Carrera%20GT%2Fporscheschumacher04.webp?alt=media&token=ddf4a97e-d1f5-431f-9797-278904fa44ee');
INSERT INTO image(id, url) VALUES(11, 'https://firebasestorage.googleapis.com/v0/b/carus-app-363822.appspot.com/o/demo%2FAudi%20A4%20Avant%2Faudi-a4-2021---brasil.webp?alt=media&token=9b5399fc-430f-48b9-91f4-71fbf40997b7');
INSERT INTO car_image(car_id, image_id) VALUES(1, 1);
INSERT INTO car_image(car_id, image_id) VALUES(1, 2);
INSERT INTO car_image(car_id, image_id) VALUES(1, 3);
INSERT INTO car_image(car_id, image_id) VALUES(2, 4);
INSERT INTO car_image(car_id, image_id) VALUES(2, 5);
INSERT INTO car_image(car_id, image_id) VALUES(3, 6);
INSERT INTO car_image(car_id, image_id) VALUES(3, 7);
INSERT INTO car_image(car_id, image_id) VALUES(4, 8);
INSERT INTO car_image(car_id, image_id) VALUES(4, 9);
INSERT INTO car_image(car_id, image_id) VALUES(4, 10);
INSERT INTO car_image(car_id, image_id) VALUES(5, 11);