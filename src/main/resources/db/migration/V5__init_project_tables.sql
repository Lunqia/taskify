CREATE TABLE projects
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(255) NOT NULL
);

CREATE TABLE project_steps
(
    id               BIGINT PRIMARY KEY AUTO_INCREMENT,
    description      VARCHAR(255) NOT NULL,
    days_to_deadline INT          NOT NULL,
    project_id       BIGINT       NOT NULL,
    FOREIGN KEY (project_id) REFERENCES projects (id)
);

ALTER TABLE task_groups
    ADD COLUMN project_id BIGINT NULL;

ALTER TABLE task_groups
    ADD FOREIGN KEY (project_id) REFERENCES projects (id);
