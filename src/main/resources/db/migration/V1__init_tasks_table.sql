DROP TABLE IF EXISTS tasks;

CREATE TABLE tasks
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(255) NOT NULL,
    completed   BIT          NOT NULL DEFAULT 0
);
