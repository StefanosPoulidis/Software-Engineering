-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema dbv010
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `dbv010` ;

-- -----------------------------------------------------
-- Schema dbv010
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `dbv010` DEFAULT CHARACTER SET utf8 ;
SHOW WARNINGS;
USE `dbv010` ;

-- -----------------------------------------------------
-- Table `dbv010`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dbv010`.`users` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dbv010`.`users` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `privilege` SMALLINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dbv010`.`frontisthrio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dbv010`.`frontisthrio` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dbv010`.`frontisthrio` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NULL,
  `timestamp` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `phonenumber` VARCHAR(45) NULL,
  `user_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`, `user_id`),
  UNIQUE INDEX `fr_id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  INDEX `fk_frontisthrio_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_frontisthrio_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `dbv010`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dbv010`.`package`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dbv010`.`package` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dbv010`.`package` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `field` ENUM('Humanitarian', 'Sciences', 'Health', 'EconomicsAndInformation') NOT NULL,
  `price` SMALLINT NULL,
  `success_rate` INT NULL,
  `city` VARCHAR(45) NULL,
  `street` VARCHAR(45) NULL,
  `street_number` INT NULL,
  `description` VARCHAR(45) NULL,
  `geopoint` POINT NULL,
  `frontisthrio_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`, `frontisthrio_id`),
  UNIQUE INDEX `pkg_id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_package_frontisthrio1_idx` (`frontisthrio_id` ASC) VISIBLE,
  CONSTRAINT `fk_package_frontisthrio1`
    FOREIGN KEY (`frontisthrio_id`)
    REFERENCES `dbv010`.`frontisthrio` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

SHOW WARNINGS;
USE `dbv010` ;

-- -----------------------------------------------------
-- Placeholder table for view `dbv010`.`UsersFrontisthrioPackageView`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbv010`.`UsersFrontisthrioPackageView` (`usersId` INT, `frontisthrioId` INT, `frontisthrioName` INT, `packageId` INT);
SHOW WARNINGS;

-- -----------------------------------------------------
-- View `dbv010`.`UsersFrontisthrioPackageView`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dbv010`.`UsersFrontisthrioPackageView`;
SHOW WARNINGS;
DROP VIEW IF EXISTS `dbv010`.`UsersFrontisthrioPackageView` ;
SHOW WARNINGS;
USE `dbv010`;
CREATE  OR REPLACE VIEW `UsersFrontisthrioPackageView` AS
SELECT users.id AS usersId, frontisthrio.id AS frontisthrioId, frontisthrio.name AS frontisthrioName, package.id AS packageId
FROM users, frontisthrio, package
WHERE users.id = user_id AND frontisthrio.id = frontisthrio_id;
SHOW WARNINGS;

-- -----------------------------------------------------
-- Data for table `dbv010`.`users`
-- -----------------------------------------------------
START TRANSACTION;
USE `dbv010`;
INSERT INTO `dbv010`.`users` (`id`, `username`, `password`, `email`, `privilege`) VALUES (1, 'admin', 'admin', 'root', 2);
INSERT INTO `dbv010`.`users` (`id`, `username`, `password`, `email`, `privilege`) VALUES (2, 'usr1', 'usr1', 'usr1@gmail.com', 1);
INSERT INTO `dbv010`.`users` (`id`, `username`, `password`, `email`, `privilege`) VALUES (3, 'usr2', 'usr2', 'usr2@gmail.com', 1);
INSERT INTO `dbv010`.`users` (`id`, `username`, `password`, `email`, `privilege`) VALUES (4, 'usr3', 'usr3', 'usr3@gmail.com', 1);
INSERT INTO `dbv010`.`users` (`id`, `username`, `password`, `email`, `privilege`) VALUES (5, 'usr4', 'usr4', 'usr4@gmail.com', 1);
INSERT INTO `dbv010`.`users` (`id`, `username`, `password`, `email`, `privilege`) VALUES (6, 'usr5', 'usr5', 'usr5@gmail.com', 1);
INSERT INTO `dbv010`.`users` (`id`, `username`, `password`, `email`, `privilege`) VALUES (7, 'user', 'user', 'noprivileges', 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `dbv010`.`frontisthrio`
-- -----------------------------------------------------
START TRANSACTION;
USE `dbv010`;
INSERT INTO `dbv010`.`frontisthrio` (`id`, `name`, `description`, `timestamp`, `phonenumber`, `user_id`) VALUES (1, 'Φροντ1', 'Περ1', '2015-07-21 22:42:31', '2106789123', 2);
INSERT INTO `dbv010`.`frontisthrio` (`id`, `name`, `description`, `timestamp`, `phonenumber`, `user_id`) VALUES (2, 'Φροντ2', 'Περ2', '2014-07-21 22:42:31', '2106442390', 3);
INSERT INTO `dbv010`.`frontisthrio` (`id`, `name`, `description`, `timestamp`, `phonenumber`, `user_id`) VALUES (3, 'Φροντ3', 'Περ3', '2010-10-21 22:42:31', '2106830090', 4);
INSERT INTO `dbv010`.`frontisthrio` (`id`, `name`, `description`, `timestamp`, `phonenumber`, `user_id`) VALUES (4, 'Φροντ4', 'Περ4', '2015-07-21 22:42:31', NULL, 5);
INSERT INTO `dbv010`.`frontisthrio` (`id`, `name`, `description`, `timestamp`, `phonenumber`, `user_id`) VALUES (5, 'Φροντ5', 'Περ5', '2015-07-21 22:42:31', '212006767', 6);

COMMIT;


-- -----------------------------------------------------
-- Data for table `dbv010`.`package`
-- -----------------------------------------------------
START TRANSACTION;
USE `dbv010`;
INSERT INTO `dbv010`.`package` (`id`, `field`, `price`, `success_rate`, `city`, `street`, `street_number`, `description`, `geopoint`, `frontisthrio_id`) VALUES (1, 'Health', 200, 56, 'Αθήνα', 'Κυψέλης', 22, 'εεεεεε', ST_GeomFromText(ST_AsText(POINT(37.997166,23.736374)),4326), 1);
INSERT INTO `dbv010`.`package` (`id`, `field`, `price`, `success_rate`, `city`, `street`, `street_number`, `description`, `geopoint`, `frontisthrio_id`) VALUES (2, 'Sciences', 250, 77, 'Αθήνα', 'Κυψέλης', 24, 'οοοοο', ST_GeomFromText(ST_AsText(POINT(37.997325,23.736454)),4326), 1);
INSERT INTO `dbv010`.`package` (`id`, `field`, `price`, `success_rate`, `city`, `street`, `street_number`, `description`, `geopoint`, `frontisthrio_id`) VALUES (3, 'Sciences', 300, 90, 'Αθήνα', 'Δεληγιάννη', 3, 'ααααα', ST_GeomFromText(ST_AsText(POINT(37.989423,23.735487)),4326), 1);
INSERT INTO `dbv010`.`package` (`id`, `field`, `price`, `success_rate`, `city`, `street`, `street_number`, `description`, `geopoint`, `frontisthrio_id`) VALUES (4, 'Health', 300, 89, 'Αθήνα', 'Πανόρμου', 55, 'ιιιιιιιιιιιιιιι', ST_GeomFromText(ST_AsText(POINT(37.991889,23.759708)),4326), 1);
INSERT INTO `dbv010`.`package` (`id`, `field`, `price`, `success_rate`, `city`, `street`, `street_number`, `description`, `geopoint`, `frontisthrio_id`) VALUES (5, 'Humanitarian', 320, 89, 'Αθήνα', 'Σαρανταπόρου', 17, 'εεεεεε', ST_GeomFromText(ST_AsText(POINT(38.013414,23.794079)),4326), 2);
INSERT INTO `dbv010`.`package` (`id`, `field`, `price`, `success_rate`, `city`, `street`, `street_number`, `description`, `geopoint`, `frontisthrio_id`) VALUES (6, 'EconomicsAndInformation', 280, 95, 'Αθήνα', 'Ελικώνος', 88, 'οοοοο', ST_GeomFromText(ST_AsText(POINT(38.009511,23.744426)),4326), 2);
INSERT INTO `dbv010`.`package` (`id`, `field`, `price`, `success_rate`, `city`, `street`, `street_number`, `description`, `geopoint`, `frontisthrio_id`) VALUES (7, 'Humanitarian', 200, 69, 'Λάρισα', 'Μιαούλη', 15, 'ααααα', ST_GeomFromText(ST_AsText(POINT(39.636301,22.422594)),4326), 3);
INSERT INTO `dbv010`.`package` (`id`, `field`, `price`, `success_rate`, `city`, `street`, `street_number`, `description`, `geopoint`, `frontisthrio_id`) VALUES (8, 'Sciences', 450, 77, 'Θεσσαλονίκη', 'Ευρυβιάδου', 10, 'ιιιιιιιιιιιιιιι', ST_GeomFromText(ST_AsText(POINT(40.617633,22.967373)),4326), 4);
INSERT INTO `dbv010`.`package` (`id`, `field`, `price`, `success_rate`, `city`, `street`, `street_number`, `description`, `geopoint`, `frontisthrio_id`) VALUES (9, 'EconomicsAndInformation', 400, 70, 'Θεσσαλονίκη', 'Αγίας Τριάδος', 11, 'εεεεεε', ST_GeomFromText(ST_AsText(POINT(40.618140,22.955286)),4326), 4);
INSERT INTO `dbv010`.`package` (`id`, `field`, `price`, `success_rate`, `city`, `street`, `street_number`, `description`, `geopoint`, `frontisthrio_id`) VALUES (10, 'Sciences', 600, 87, 'Πάτρα', 'Πηγάσου', 33, 'οοοοο', ST_GeomFromText(ST_AsText(POINT(38.220606,21.731634)),4326), 5);
INSERT INTO `dbv010`.`package` (`id`, `field`, `price`, `success_rate`, `city`, `street`, `street_number`, `description`, `geopoint`, `frontisthrio_id`) VALUES (11, 'Humanitarian', 550, 88, 'Πάτρα', 'Πηγάσου', 33, 'ααααα', ST_GeomFromText(ST_AsText(POINT(38.220606,21.731634)),4326), 5);

COMMIT;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
