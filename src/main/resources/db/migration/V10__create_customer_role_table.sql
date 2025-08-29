CREATE TABLE customer_role (
                               customer_id INT NOT NULL,
                               role_id INT NOT NULL,
                               PRIMARY KEY (customer_id, role_id),
                               FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE CASCADE,
                               FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);