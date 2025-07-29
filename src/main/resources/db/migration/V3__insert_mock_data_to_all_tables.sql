INSERT INTO customer (id, name, ssn, address, email, phone, passwordHash)
VALUES
    (1, 'Alice Johnson', '19900101-1234', '123 Elm St, Stockholm', 'alice@example.com', '0701234567', 'abc123hash'),
    (2, 'Bob Smith', '19850505-5678', '456 Oak St, Gothenburg', 'bob@example.com', '0732345678', 'def456hash'),
    (3, 'Charlie Brown', '19791212-8765', '789 Pine St, Malmö', 'charlie@example.com', '0763456789', 'ghi789hash'),
    (4, 'Diana Prince', '19930523-4321', '321 Birch St, Uppsala', 'diana@example.com', '0724567890', 'jkl012hash'),
    (5, 'Ethan Hunt', '19881111-1111', '654 Maple St, Lund', 'ethan@example.com', '0795678901', 'mno345hash'),
    (6, 'Fiona Glenanne', '19940808-2222', '987 Spruce St, Örebro', 'fiona@example.com', '0706789012', 'pqr678hash'),
    (7, 'George Miller', '19770303-3333', '135 Cedar St, Västerås', 'george@example.com', '0737890123', 'stu901hash'),
    (8, 'Hanna Eriksson', '19991231-4444', '246 Aspen St, Linköping', 'hanna@example.com', '0768901234', 'vwx234hash'),
    (9, 'Ian Wright', '20010115-5555', '357 Willow St, Helsingborg', 'ian@example.com', '0729012345', 'yza567hash'),
    (10, 'Julia Roberts', '19830620-6666', '468 Redwood St, Jönköping', 'julia@example.com', '0790123456', 'bcd890hash');

INSERT INTO app_user (name, pinHash, customer_id)
VALUES
    ('Alice App', '0ffe1abd1a08215353c233d6e009613e95eec4253832a761af28ff37ac5a150c', 1),
    ('Bob App', 'edee29f882543b956620b26d0ee0e7e950399b1c4222f5de05e06425b4c995e9', 2),
    ('Charlie App', '318aee3fed8c9d040d35a7fc1fa776fb31303833aa2de885354ddf3d44d8fb69', 3),
    ('Diana App', '79f06f8fde333461739f220090a23cb2a79f6d714bee100d0e4b4af249294619', 4),
    ('Ethan App', 'c1f330d0aff31c1c87403f1e4347bcc21aff7c179908723535f2b31723702525', 5),
    ('Fiona App', 'd7697570462f7562b83e81258de0f1e41832e98072e44c36ec8efec46786e24e', 6),
    ('George App', '41c991eb6a66242c0454191244278183ce58cf4a6bcd372f799e4b9cc01886af', 7),
    ('Hanna App', '2926a2731f4b312c08982cacf8061eb14bf65c1a87cc5d70e864e079c6220731', 8),
    ('Ian App', '888df25ae35772424a560c7152a1de794440e0ea5cfee62828333a456a506e05', 9),
    ('Julia App', '9af15b336e6a9619928537df30b2e6a2376569fcf9d7e773eccede65606529a0', 10);

INSERT INTO central_unit (serial_number, status, installed_at, customer_id)
VALUES
    ('CU1001', 'active', '2024-01-10 10:00:00', 1),
    ('CU1002', 'inactive', '2024-02-15 11:30:00', 2),
    ('CU1003', 'active', '2024-03-20 12:00:00', 3),
    ('CU1004', 'maintenance', '2024-04-25 09:45:00', 4),
    ('CU1005', 'active', '2024-05-30 13:15:00', 5),
    ('CU1006', 'inactive', '2024-06-10 14:00:00', 6),
    ('CU1007', 'active', '2024-07-15 15:30:00', 7),
    ('CU1008', 'inactive', '2024-07-20 16:45:00', 8),
    ('CU1009', 'active', '2024-07-25 17:00:00', 9),
    ('CU1010', 'maintenance', '2024-07-28 18:20:00', 10);

INSERT INTO tag (tag_id_hash)
VALUES
    ('0ffe1abd1a08215353c233d6e009613e95eec4253832a761af28ff37ac5a150c'),
    ('edee29f882543b956620b26d0ee0e7e950399b1c4222f5de05e06425b4c995e9'),
    ('318aee3fed8c9d040d35a7fc1fa776fb31303833aa2de885354ddf3d44d8fb69'),
    ('79f06f8fde333461739f220090a23cb2a79f6d714bee100d0e4b4af249294619'),
    ('c1f330d0aff31c1c87403f1e4347bcc21aff7c179908723535f2b31723702525'),
    ('d7697570462f7562b83e81258de0f1e41832e98072e44c36ec8efec46786e24e'),
    ('41c991eb6a66242c0454191244278183ce58cf4a6bcd372f799e4b9cc01886af'),
    ('2926a2731f4b312c08982cacf8061eb14bf65c1a87cc5d70e864e079c6220731'),
    ('888df25ae35772424a560c7152a1de794440e0ea5cfee62828333a456a506e05'),
    ('9af15b336e6a9619928537df30b2e6a2376569fcf9d7e773eccede65606529a0');

INSERT INTO central_unit_tag (central_unit_id, tag_id)
VALUES
    (1, 1),
    (1, 2),
    (2, 3),
    (3, 1),
    (3, 4),
    (4, 5),
    (5, 6),
    (6, 7),
    (7, 8),
    (8, 9),
    (9, 10),
    (10, 1);

