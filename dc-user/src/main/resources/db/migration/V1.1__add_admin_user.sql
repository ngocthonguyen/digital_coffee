INSERT INTO dc_user
(id, email, first_name, last_name, "password", username)
VALUES(0, 'admin@digitalcoffee.com', 'Admin', 'Admin', '$2a$10$9RxZUIXF43DVt9iHTQ16heIhwlNSv/aMnrw/XjtGy6n3W2auB951S', 'admin');

INSERT INTO dc_user_role
(id, "role", user_id)
VALUES(0, 'ADMIN', 0);