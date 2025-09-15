CREATE TABLE sector (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    parent_id INT,
    FOREIGN KEY (parent_id) REFERENCES sector(id)
);

CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(32),
    terms BOOLEAN,
    sector_id INT,
    FOREIGN KEY (sector_id) REFERENCES sector(id)
);