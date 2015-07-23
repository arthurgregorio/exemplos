-- cria DB
CREATE DATABASE `crudjsf` /*!40100 COLLATE 'utf8_general_ci' */;

-- cria o usuario
CREATE USER 'sa_crudjsf'@'localhost' IDENTIFIED BY 'sa_crudjsf';
GRANT USAGE ON *.* TO 'sa_crudjsf'@'localhost';
GRANT SELECT, EXECUTE, SHOW VIEW, ALTER, ALTER ROUTINE, CREATE, CREATE ROUTINE, CREATE TEMPORARY TABLES, CREATE VIEW, DELETE, DROP, EVENT, INDEX, INSERT, REFERENCES, TRIGGER, UPDATE  ON `crudjsf`.* TO 'sa_crudjsf'@'localhost';
FLUSH PRIVILEGES;