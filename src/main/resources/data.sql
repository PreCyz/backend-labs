DROP TABLE IF EXISTS company;

CREATE TABLE company (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  start_date TIMESTAMP NOT NULL,
  board_members INT DEFAULT NULL
);

INSERT INTO company (name, start_date, board_members) VALUES
  ('Aliko', CURRENT_TIMESTAMP , 1),
  ('Bill', CURRENT_TIMESTAMP, 2),
  ('Folrunsho', CURRENT_TIMESTAMP, 3);