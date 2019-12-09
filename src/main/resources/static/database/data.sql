INSERT INTO company_share (comment, capital_size, usreou, amount, total_value, face_value, duty_paid)
VALUES ('comment', 4000, 43543, 3, 3000.00,  100, 420.98);
INSERT INTO company_share (comment, capital_size, usreou, amount, total_value, face_value, duty_paid)
VALUES ('comment2', 5000, 12345, 4, 2456.35,  123, 376.78);
INSERT INTO company_share (comment, capital_size, usreou, amount, total_value, face_value, duty_paid)
VALUES ('comment3', 6000, 545445, 5, 11234.22,  340, 112.43);
INSERT INTO company_share (comment, capital_size, usreou, amount, total_value, face_value, duty_paid)
VALUES ('comment4', 7000, 98765, 6, 9875.45,  200, 460.00);
INSERT INTO company_share (comment, capital_size, usreou, amount, total_value, face_value, duty_paid)
VALUES ('comment5', 8000, 24324, 7, 1245.67,  100, 730.19);


INSERT INTO users (id, login, password) VALUES (1, 'user1', '12345678');
INSERT INTO users (id, login, password) VALUES (2, 'user2', '123gfg678');
INSERT INTO users (id, login, password) VALUES (3, 'user3', 'qwerty123');
INSERT INTO users (id, login, password) VALUES (4, 'user4', 'loraarol');


INSERT INTO roles (user_id, role) VALUES (1, 'USER');
INSERT INTO roles (user_id, role) VALUES (1, 'ADMIN');
INSERT INTO roles (user_id, role) VALUES (2, 'USER');
INSERT INTO roles (user_id, role) VALUES (3, 'USER');
INSERT INTO roles (user_id, role) VALUES (4, 'USER');