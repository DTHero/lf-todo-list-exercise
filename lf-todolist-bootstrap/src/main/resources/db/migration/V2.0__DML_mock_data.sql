SET
@user1_id=RANDOM_UUID();
SET
@user2_id=RANDOM_UUID();

SET
@role1_id=RANDOM_UUID();
SET
@role2_id=RANDOM_UUID();
SET
@role3_id=RANDOM_UUID();

SET
@todo1_id=RANDOM_UUID();
SET
@todo2_id=RANDOM_UUID();
SET
@todo3_id=RANDOM_UUID();
SET
@todo4_id=RANDOM_UUID();

SET
@todo_item1_id=RANDOM_UUID();
SET
@todo_item2_id=RANDOM_UUID();
SET
@todo_item3_id=RANDOM_UUID();
SET
@todo_item4_id=RANDOM_UUID();
SET
@todo_item5_id=RANDOM_UUID();
SET
@todo_item6_id=RANDOM_UUID();

INSERT INTO lf_user (uuid, username, password, first_name, last_name, date_of_birth, national_id, gender, country_code,
                    mobile, email, address_1, address_2, address_3, locked, number_of_retries, role)
VALUES (@user1_id, 'ntthien', '$2y$12$7DegkPosM9hbSDUmBOkNC.7ZpG0I2EtEpFJi1wZrYaKwf05dlA6QK', 'Thien', 'Nguyen', '1996-11-14',
        '12345678900', 'MALE', '84', '966087757', 'thiennguyen.kt@gmail.com', 'Kon Tum', 'Đăk Tô', null, '0', '0', 'ADMIN');
INSERT INTO lf_user (uuid, username, password, first_name, last_name, date_of_birth, national_id, gender, country_code,
                    mobile, email, address_1, address_2, address_3, locked, number_of_retries, role)
VALUES (@user2_id, 'kelly', '$2y$12$7DegkPosM9hbSDUmBOkNC.7ZpG0I2EtEpFJi1wZrYaKwf05dlA6QK', 'Kelly', 'Tran', '1996-11-14',
        '98765432100', 'FEMALE', '84', '999999999', 'kellytran@lifung.com', null, null, null, '0', '0', 'GUEST');

INSERT INTO lf_role (uuid, role, description)
VALUES (@role1_id, 'ADMIN', 'Administrator');
INSERT INTO lf_role (uuid, role, description)
VALUES (@role2_id, 'EMPLOYEE', 'Employee');
INSERT INTO lf_role (uuid, role, description)
VALUES (@role3_id, 'GUEST', 'Guest');

INSERT INTO lf_todo (uuid, user_uuid, title, goal, start_date, end_date, is_active)
VALUES (@todo1_id, @user1_id, 'Todo 1', 'Target goal 1', '2023-11-04', '2023-11-06', 1);
INSERT INTO lf_todo (uuid, user_uuid, title, goal, start_date, end_date, is_active)
VALUES (@todo2_id, @user1_id, 'Todo 2', 'Target goal 2', '2023-11-05', '2023-11-06', 1);
INSERT INTO lf_todo (uuid, user_uuid, title, goal, start_date, end_date, is_active)
VALUES (@todo3_id, @user1_id, 'Todo 3', 'Target goal 3', '2023-11-03', '2023-11-03', 0);
INSERT INTO lf_todo (uuid, user_uuid, title, goal, start_date, end_date, is_active)
VALUES (@todo4_id, @user2_id, 'Todo 4', 'Target goal 4', '2023-11-06', '2023-11-06', 1);

INSERT INTO lf_todo_item (uuid, todo_uuid, item_name)
VALUES (@todo_item1_id, @todo1_id, 'Todo 1 / Item 1');
INSERT INTO lf_todo_item (uuid, todo_uuid, item_name)
VALUES (@todo_item2_id, @todo2_id, 'Todo 2 / Item 1');
INSERT INTO lf_todo_item (uuid, todo_uuid, item_name)
VALUES (@todo_item3_id, @todo2_id, 'Todo 2 / Item 2');
INSERT INTO lf_todo_item (uuid, todo_uuid, item_name)
VALUES (@todo_item4_id, @todo3_id, 'Todo 3 / Item 1');
INSERT INTO lf_todo_item (uuid, todo_uuid, item_name)
VALUES (@todo_item5_id, @todo4_id, 'Todo 4 / Item 1');
INSERT INTO lf_todo_item (uuid, todo_uuid, item_name)
VALUES (@todo_item6_id, @todo4_id, 'Todo 4 / Item 2');
