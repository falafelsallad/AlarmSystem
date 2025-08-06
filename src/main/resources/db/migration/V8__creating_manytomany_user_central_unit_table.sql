-- CREATE TABLE MANY-TO-MANY USER AND CENTRAL UNIT
CREATE TABLE app_user_central_unit (
    app_user_id INT NOT NULL,
    central_unit_id INT NOT NULL,
    PRIMARY KEY (app_user_id, central_unit_id),
    FOREIGN KEY (app_user_id) REFERENCES app_user(id) ON DELETE CASCADE,
    FOREIGN KEY (central_unit_id) REFERENCES central_unit(id) ON DELETE CASCADE

);