INSERT INTO address(id, cep, city, neighborhood, `number`, state, street) VALUES(1, '89909971', 'Barra Bonita', 'Centro', 310, 'SANTA_CATARINA', 'Rua do Ouvidor');
INSERT INTO user(uuid, about, cpf, email, first_name, gender, last_name, login, member_since, password, phone, profile_image_url, refresh_token, refresh_token_expiration, user_address) VALUES('DJ9CmS8RsGQ8ISZnKDBXQNUTECc2', 'The master account', '84949904035', 'master@carus.com', 'Master', 'OTHER', 'Carus', 'master@carus.com', '2022-11-25', '$2a$10$kcZpLmfATk8dM08.m/AdZeCmNTel63XDX9.Rbx/iLnWFfQd/Cj3GC', '49992508793', 'https://firebasestorage.googleapis.com/v0/b/carus-app-363822.appspot.com/o/Default%20Images%2Fblank-profile.png?alt=media&token=adea62ac-fdb0-4278-907d-721090b9aa80', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXN0ZXJAY2FydXMuY29tIiwiZXhwIjozMzcwMjE1OTUxfQ.x_wx-2lyAGhTc1vXVgbI-6ijPhd81F5Cy3j0W75W22fU-sD4FKtbI_arfL2Twck2jWfuBnRna2hOeWclCHEcTA', '2023-11-25 01:32:55.000', 1);
INSERT INTO cnh(id, birth_date, cnh_number, expiration_date, register_number, rg, state, user_uuid) VALUES(1, '2000-02-02', '3044949953', '2029-08-08', '74875745972', '36993529', 'SANTA_CATARINA', 'DJ9CmS8RsGQ8ISZnKDBXQNUTECc2');

