CREATE TABLE IF NOT EXISTS toy (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    random_number INT
);

INSERT INTO toy (name, random_number) VALUES ('test', 123);
