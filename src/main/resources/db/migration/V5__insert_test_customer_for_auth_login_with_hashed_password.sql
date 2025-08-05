INSERT INTO customer (id, name, ssn, address, email, phone, passwordHash)
SELECT 11, 'Authory Testy', '19900101-1235', '123 Elm St, Stockholm', 'authorytesty@test.com', '0701234568', '9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08'
WHERE NOT EXISTS(SELECT 1 FROM customer WHERE id = 11);
-- password before SHA256 encryption "test"