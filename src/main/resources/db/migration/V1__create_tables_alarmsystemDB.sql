-- Customer table
CREATE TABLE customer (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          ssn VARCHAR(16) NOT NULL UNIQUE,
                          address VARCHAR(255),
                          email VARCHAR(255),
                          phone VARCHAR(50),
                          passwordHash VARCHAR(64),
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- User table
CREATE TABLE app_user (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(255),
                          pinHash VARCHAR(64),
                          customer_id INT,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          FOREIGN KEY (customer_id) REFERENCES customer(id)
);

-- Central unit table
CREATE TABLE central_unit (
                              id SERIAL PRIMARY KEY,
                              serial_number VARCHAR(100) NOT NULL UNIQUE,
                              status VARCHAR(50),
                              installed_at TIMESTAMP,
                              customer_id INT,
                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              FOREIGN KEY (customer_id) REFERENCES customer(id)
);

-- tag table
CREATE TABLE tag (
                     id SERIAL PRIMARY KEY,
                     tag_id_hash VARCHAR(64),
                     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Central Unit - Tag, Many to many
CREATE TABLE central_unit_tag (
                                  central_unit_id INT NOT NULL,
                                  tag_id INT NOT NULL,
                                  PRIMARY KEY (central_unit_id, tag_id),
                                  FOREIGN KEY (central_unit_id) REFERENCES central_unit(id) ON DELETE CASCADE,
                                  FOREIGN KEY (tag_id) REFERENCES tag(id) ON DELETE CASCADE
);