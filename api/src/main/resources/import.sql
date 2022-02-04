INSERT INTO role (id, name) VALUES (1, 'ROLE_ADMIN'), (2, 'ROLE_USER');

INSERT INTO user (id, email, last_login_date, lose, name, username, pass, user_code, win) VALUES (1, 'root@test.com', '2021年01月01日 01時01分', '0', 'root','root', 'password', '331a2a42-d', '0');
INSERT INTO user (id, email, last_login_date, lose, name, username, pass, user_code, win) VALUES (2, 'test1@test.com', '2021年01月01日 01時01分', '0', 'test1','test1', 'password', 'g31a21jh-f', '0');
INSERT INTO user (id, email, last_login_date, lose, name, username, pass, user_code, win) VALUES (3, 'test2@test.com', '2021年01月01日 01時01分', '0', 'test2','test2', 'password', '8h1a2bft-w', '0');
INSERT INTO user (id, email, last_login_date, lose, name, username, pass, user_code, win) VALUES (4, 'test3@test.com', '2021年01月01日 01時01分', '0', 'test3','test3', 'password', '3g1a2kkf-t', '0');
INSERT INTO user (id, email, last_login_date, lose, name, username, pass, user_code, win) VALUES (5, 'test4@test.com', '2021年01月01日 01時01分', '0', 'test4','test4', 'password', 'ac1a2a42-1', '0');

INSERT INTO user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO user_role (user_id, role_id) VALUES (2, 2);
INSERT INTO user_role (user_id, role_id) VALUES (3, 2);
INSERT INTO user_role (user_id, role_id) VALUES (4, 2);
INSERT INTO user_role (user_id, role_id) VALUES (5, 2);