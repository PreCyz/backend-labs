DROP TABLE IF EXISTS company;

CREATE SEQUENCE HIBERNATE_SEQUENCE;

CREATE TABLE company (
                         id INT DEFAULT HIBERNATE_SEQUENCE.nextval PRIMARY KEY,
                         name VARCHAR(250) NOT NULL,
                         start_date TIMESTAMP NOT NULL,
                         board_members INT DEFAULT NULL
);
