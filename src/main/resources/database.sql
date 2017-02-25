CREATE DATABASE  IF NOT EXISTS `cars`;
USE `cars`;

CREATE TABLE `car` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `wheel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `car_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_car_id_wheel` FOREIGN KEY (`car_id`) REFERENCES `car` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tyres` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL ,
  `size` int(11) NOT NULL,
  `wheel_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_wheel_id_tyres` FOREIGN KEY (`wheel_id`) REFERENCES `wheel` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `engine` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `engine_capacity` int(11) NOT NULL,
  `car_id` int(11) NOT NULL ,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_car_id_engine` FOREIGN KEY (`car_id`) REFERENCES `car` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


