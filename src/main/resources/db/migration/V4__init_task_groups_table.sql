CREATE TABLE task_groups
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(255) NOT NULL,
    completed   BIT          NOT NULL DEFAULT 0
);

ALTER TABLE tasks
    ADD COLUMN task_group_id BIGINT NULL;

ALTER TABLE tasks
    ADD FOREIGN KEY (task_group_id) REFERENCES task_groups (id);
