CREATE TABLE client (
    id IDENTITY PRIMARY KEY,
    name VARCHAR(200) NOT NULL CHECK ( LENGTH (name) >= 3 ),
    deleted INTEGER
);

CREATE TABLE planet (
    id VARCHAR(50) PRIMARY KEY CHECK ( id !~ '[a-z \W]+' ),
    name VARCHAR(500) NOT NULL CHECK ( LENGTH (name) >= 1 ),
    deleted INTEGER
);

CREATE TABLE ticket (
    id IDENTITY PRIMARY KEY,
    created_at TIMESTAMP WITH TIME ZONE,
    client_id BIGINT REFERENCES client(id),
    from_planet_id VARCHAR(50) REFERENCES planet(id),
    to_planet_id VARCHAR(50) REFERENCES planet(id)
);