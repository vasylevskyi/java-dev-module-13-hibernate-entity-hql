INSERT INTO client (name, deleted) VALUES
    ('John Doe', 0),
    ('Alex Newman', 0),
    ('Nill Harrington', 0),
    ('Helen Saw', 0),
    ('Jonathan Right', 0),
    ('Karen Bishop', 0),
    ('Ben Aflek', 0),
    ('Lisa Larson', 0),
    ('Alan Jones', 0),
    ('Alisa York', 0);

INSERT INTO planet VALUES
    ('VEN2', 'Venus', 0),
    ('MARS4', 'Mars', 0),
    ('PLUTO9', 'Pluton', 0),
    ('JUP5', 'Jupiter', 0),
    ('SAT6', 'Saturn', 0);

INSERT INTO ticket VALUES
    (1, '2023-02-01 01:00:00+01:00', 1, 'VEN2', 'SAT6'),
    (2, '2023-10-15 18:34:00+02:00', 2, 'MARS4', 'PLUTO9'),
    (3, '2023-03-30 11:11:11+11:00', 3, 'PLUTO9', 'MARS4'),
    (4, '2023-12-31 23:59:59+04:00', 4, 'SAT6', 'JUP5'),
    (5, '2024-01-24 06:24:35-04:00', 5, 'JUP5', 'PLUTO9'),
    (6, '2023-07-21 14:10:15+06:00', 6, 'PLUTO9', 'VEN2'),
    (7, '2023-09-08 10:10:53-06:00', 7, 'VEN2', 'JUP5'),
    (8, '2023-11-11 22:15:04+08:00', 8, 'JUP5', 'SAT6'),
    (9, '2024-02-02 12:38:15+04:00', 9, 'MARS4', 'VEN2'),
    (10, '2023-01-18 03:57:11-08:00', 10, 'SAT6', 'MARS4'),
    (11, '2023-05-25 12:13:14+01:00', 3, 'MARS4', 'JUP5');

ALTER TABLE ticket ALTER COLUMN id RESTART WITH (
    SELECT MAX(id) + 1 FROM ticket);