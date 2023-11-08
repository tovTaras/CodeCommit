CREATE DATABASE IF NOT EXISTS shipping_company;
USE shipping_company;

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS region;
DROP TABLE IF EXISTS city;
DROP TABLE IF EXISTS `client`;
DROP TABLE IF EXISTS delivery;
DROP TABLE IF EXISTS delivery_person;
DROP TABLE IF EXISTS `order`;
DROP TABLE IF EXISTS order_has_car;
DROP TABLE IF EXISTS car;
DROP TABLE IF EXISTS car_size;
DROP TABLE IF EXISTS office;
SET FOREIGN_KEY_CHECKS=1;


CREATE TABLE IF NOT EXISTS `shipping_company`.`region` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  CONSTRAINT region_pk PRIMARY KEY (`id`)
)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `shipping_company`.`city` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `region_id` INT NOT NULL,
  CONSTRAINT city_pk PRIMARY KEY (`id`)
)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `shipping_company`.`office` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street_address` VARCHAR(255) NOT NULL,
  `phone` VARCHAR(12) NOT NULL,
  `office_head` VARCHAR(100) NOT NULL,
  `city_id` INT NOT NULL,
  CONSTRAINT office_pk PRIMARY KEY (`id`)
)
ENGINE = InnoDB;



CREATE TABLE IF NOT EXISTS `shipping_company`.`client` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `surname` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `birthday` DATE NULL,
  `gender` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `phone` VARCHAR(45) NOT NULL,
  `city_id` INT NOT NULL,
  CONSTRAINT client_pk PRIMARY KEY (`id`)
)
ENGINE = InnoDB;



CREATE TABLE IF NOT EXISTS `shipping_company`.`car_size` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `diameter` VARCHAR(45) NOT NULL,
  `ingredient_cost_coef` FLOAT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `price` VARCHAR(45) NOT NULL,
  CONSTRAINT car_size_pk PRIMARY KEY (`id`)
)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `shipping_company`.`car` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `car_size_id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  CONSTRAINT car_pk PRIMARY KEY (`id`)
)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `shipping_company`.`delivery_person` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `latitude` FLOAT NOT NULL,
  `longitude` FLOAT NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(12) NOT NULL,
  `adress` VARCHAR(45) NOT NULL,
  CONSTRAINT delivery_person_pk PRIMARY KEY (`id`)
)
ENGINE = InnoDB;



CREATE TABLE IF NOT EXISTS `shipping_company`.`delivery` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `apartmentnumber` VARCHAR(45) NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  `delivery_person_id` INT NOT NULL,
  `delivery_time_aprox` DATE NOT NULL,
  `delivery_time_real` DATE NOT NULL,
  `delivery_payment` DECIMAL(8,2) NOT NULL,
  CONSTRAINT delivery_pk PRIMARY KEY (`id`)
)
ENGINE = InnoDB;



CREATE TABLE IF NOT EXISTS `shipping_company`.`order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `time` TIMESTAMP NOT NULL,
  `client_id` INT NOT NULL,
  `delivery_id` INT NOT NULL,
  `is_delivery` TINYINT NULL,
  `total_price` DECIMAL(8,2) NOT NULL,
  `adressee` VARCHAR(45) NOT NULL,
  `office_id` INT NOT NULL,
  `city_id` INT NOT NULL,
  CONSTRAINT order_pk PRIMARY KEY (`id`, `office_id`)
)
ENGINE = InnoDB;



CREATE TABLE IF NOT EXISTS `shipping_company`.`order_has_car` (
  `order_id` INT NOT NULL,
  `car_id` INT NOT NULL,
  `quantity` INT NOT NULL,
  CONSTRAINT order_has_car_pk PRIMARY KEY (`order_id`, `car_id`)
)
ENGINE = InnoDB;

CREATE UNIQUE INDEX name_index ON delivery_person(`surname`);
CREATE UNIQUE INDEX surname_index ON `client`(`surname`);



ALTER TABLE office ADD CONSTRAINT office_city_id FOREIGN KEY office_city_id (city_id)
    REFERENCES city(id);

ALTER TABLE `client` ADD CONSTRAINT client_city_id FOREIGN KEY client_city_id (city_id)
    REFERENCES city(id);

ALTER TABLE car ADD CONSTRAINT car_car_size_id FOREIGN KEY car_car_size_id (car_size_id)
    REFERENCES car_size(id);

ALTER TABLE city ADD CONSTRAINT city_region_id FOREIGN KEY city_region_id (region_id)
    REFERENCES region(id);

ALTER TABLE order_has_car ADD CONSTRAINT order_has_car_order_id FOREIGN KEY order_has_car_order_id(order_id)
    REFERENCES `order`(id);

ALTER TABLE order_has_car ADD CONSTRAINT order_has_car_car_id FOREIGN KEY order_has_car_car_id(car_id)
    REFERENCES car(id);

ALTER TABLE delivery ADD CONSTRAINT delivery_delivery_person_id FOREIGN KEY delivery_delivery_person_id(delivery_person_id)
    REFERENCES delivery_person(id);


ALTER TABLE `order` ADD CONSTRAINT order_client_id FOREIGN KEY order_client_id(client_id)
    REFERENCES client(id);

ALTER TABLE `order` ADD CONSTRAINT order_delivery_id FOREIGN KEY order_delivery_id(delivery_id)
    REFERENCES delivery(id);


ALTER TABLE `order` ADD CONSTRAINT order_office_id FOREIGN KEY order_office_id(office_id)
    REFERENCES office(id);

ALTER TABLE `order` ADD CONSTRAINT order_city_id FOREIGN KEY order_city_id(city_id)
    REFERENCES city(id);

-- Insert in region

INSERT INTO `shipping_company`.`region` (`id`, `name`) VALUES ('1', 'Europe');
INSERT INTO `shipping_company`.`region` (`id`, `name`) VALUES ('2', 'America');
INSERT INTO `shipping_company`.`region` (`id`, `name`) VALUES ('3', 'Africa');
INSERT INTO `shipping_company`.`region` (`id`, `name`) VALUES ('4', 'Asia');
INSERT INTO `shipping_company`.`region` (`id`, `name`) VALUES ('5', 'Australia');

-- Insert in city

INSERT INTO `shipping_company`.`city` (`id`, `name`, `region_id`) VALUES ('1', 'Ostin',2);
INSERT INTO `shipping_company`.`city` (`id`, `name`, `region_id`) VALUES ('2', 'Berlin',1);
INSERT INTO `shipping_company`.`city` (`id`, `name`, `region_id`) VALUES ('3', 'Mozambik',3);
INSERT INTO `shipping_company`.`city` (`id`, `name`, `region_id`) VALUES ('4', 'Chudova Sudova',1);
INSERT INTO `shipping_company`.`city` (`id`, `name`, `region_id`) VALUES ('5', 'Washington',2);
INSERT INTO `shipping_company`.`city` (`id`, `name`, `region_id`) VALUES ('6', 'Sydney',5);
INSERT INTO `shipping_company`.`city` (`id`, `name`, `region_id`) VALUES ('7', 'Kyoto',4);
INSERT INTO `shipping_company`.`city` (`id`, `name`, `region_id`) VALUES ('8', 'Shang-hai',4);
INSERT INTO `shipping_company`.`city` (`id`, `name`, `region_id`) VALUES ('9', 'Agraba',3);
INSERT INTO `shipping_company`.`city` (`id`, `name`, `region_id`) VALUES ('10', 'Wroclaw',1);



-- Insert in client

INSERT INTO `shipping_company`.`client` (`id`, `surname`, `name`, `birthday`, `gender`, `email`, `phone`, `city_id`) VALUES ('1', 'Petlyovanyi', 'Marian', '2003-10-09', 'male', 'petlyovanyy@gmail.com', '0938107284', '1');
INSERT INTO `shipping_company`.`client` (`id`, `surname`, `name`, `birthday`, `gender`, `email`, `phone`, `city_id`) VALUES ('2', 'Tovartiskyi', 'Taras', '2004-05-28', 'male', 'tovar@gmail.com', '0938207285', '2');
INSERT INTO `shipping_company`.`client` (`id`, `surname`, `name`, `birthday`, `gender`, `email`, `phone`, `city_id`) VALUES ('3', 'Fliorko', 'Taras', '2004-04-05', 'male', 'fliorko@gmail.com', '0938307286', '1');
INSERT INTO `shipping_company`.`client` (`id`, `surname`, `name`, `birthday`, `gender`, `email`, `phone`, `city_id`) VALUES ('4', 'Boretskyi', 'Bohdan', '2001-10-09', 'male', 'bohdan@gmail.com', '0938407287', '4');
INSERT INTO `shipping_company`.`client` (`id`, `surname`, `name`, `birthday`, `gender`, `email`, `phone`, `city_id`) VALUES ('5', 'Alex', 'Sour', '2003-10-09', 'maloe', 'otsis@gmail.com', '0938107588', '1');
INSERT INTO `shipping_company`.`client` (`id`, `surname`, `name`, `birthday`, `gender`, `email`, `phone`, `city_id`) VALUES ('6', 'Merry', 'Veil', '2003-10-09', 'female', 'veilofdiscord@gmail.com', '0638107289', '2');
INSERT INTO `shipping_company`.`client` (`id`, `surname`, `name`, `birthday`, `gender`, `email`, `phone`, `city_id`) VALUES ('7', 'Biggie', 'Mombasa', '2003-10-09', 'male', 'bigandbiggier@gmail.com', '0938107240', '6');
INSERT INTO `shipping_company`.`client` (`id`, `surname`, `name`, `birthday`, `gender`, `email`, `phone`, `city_id`) VALUES ('8', 'Voloshanski', 'Ivan', '2003-10-09', 'male', 'Ivan@gmail.com', '0938103291', '1');
INSERT INTO `shipping_company`.`client` (`id`, `surname`, `name`, `birthday`, `gender`, `email`, `phone`, `city_id`) VALUES ('9', 'Klus', 'Ira', '2003-10-09', 'female', 'Ira@gmail.com', '0938104322', '8');
INSERT INTO `shipping_company`.`client` (`id`, `surname`, `name`, `birthday`, `gender`, `email`, `phone`, `city_id`) VALUES ('10', 'Yatskiv', 'Oleh', '2003-10-09', 'male', 'Oleh@gmail.com', '093407293', '1');



-- Insert in car_size

INSERT INTO `shipping_company`.`car_size` (`id`, `diameter`, `ingredient_cost_coef`, `name`, `price`) VALUES ('1', '10', '1', 'small_wheel', '100');
INSERT INTO `shipping_company`.`car_size` (`id`, `diameter`, `ingredient_cost_coef`, `name`, `price`) VALUES ('2', '20', '2', 'even_smaller_wheel', '200');
INSERT INTO `shipping_company`.`car_size` (`id`, `diameter`, `ingredient_cost_coef`, `name`, `price`) VALUES ('3', '30', '3', 'the_smallest_wheel', '300');
INSERT INTO `shipping_company`.`car_size` (`id`, `diameter`, `ingredient_cost_coef`, `name`, `price`) VALUES ('4', '40', '4', 'medium_wheel', '400');
INSERT INTO `shipping_company`.`car_size` (`id`, `diameter`, `ingredient_cost_coef`, `name`, `price`) VALUES ('5', '50', '5', 'THE_medium_wheel', '500');
INSERT INTO `shipping_company`.`car_size` (`id`, `diameter`, `ingredient_cost_coef`, `name`, `price`) VALUES ('6', '60', '6', 'THAMEDIUMWHEEL', '600');
INSERT INTO `shipping_company`.`car_size` (`id`, `diameter`, `ingredient_cost_coef`, `name`, `price`) VALUES ('7', '70', '7', 'Mediocre', '700');
INSERT INTO `shipping_company`.`car_size` (`id`, `diameter`, `ingredient_cost_coef`, `name`, `price`) VALUES ('8', '80', '8', 'big_wheel', '800');
INSERT INTO `shipping_company`.`car_size` (`id`, `diameter`, `ingredient_cost_coef`, `name`, `price`) VALUES ('9', '90', '9', 'bigger_wheel', '900');
INSERT INTO `shipping_company`.`car_size` (`id`, `diameter`, `ingredient_cost_coef`, `name`, `price`) VALUES ('10', '100', '10', 'the_biggest_wheel', '1000');


-- Insert in car


INSERT INTO `shipping_company`.`car` (`id`, `car_size_id`, `name`) VALUES ('1', '1', 'Michlean');
INSERT INTO `shipping_company`.`car` (`id`, `car_size_id`, `name`) VALUES ('2', '2', 'Kordenia');
INSERT INTO `shipping_company`.`car` (`id`, `car_size_id`, `name`) VALUES ('3', '3', 'Kasparida');
INSERT INTO `shipping_company`.`car` (`id`, `car_size_id`, `name`) VALUES ('4', '4', 'Jokerka');
INSERT INTO `shipping_company`.`car` (`id`, `car_size_id`, `name`) VALUES ('5', '5', 'Yamaha');
INSERT INTO `shipping_company`.`car` (`id`, `car_size_id`, `name`) VALUES ('6', '6', 'Myanma');
INSERT INTO `shipping_company`.`car` (`id`, `car_size_id`, `name`) VALUES ('7', '7', 'Exestensialka');
INSERT INTO `shipping_company`.`car` (`id`, `car_size_id`, `name`) VALUES ('8', '8', 'Formula 1');
INSERT INTO `shipping_company`.`car` (`id`, `car_size_id`, `name`) VALUES ('9', '9', 'formula 2');
INSERT INTO `shipping_company`.`car` (`id`, `car_size_id`, `name`) VALUES ('10', '10', 'E=M*pow(C,2)');

-- Insert delivery_person


INSERT INTO `shipping_company`.`delivery_person` (`id`, `latitude`, `longitude`, `surname`, `name`, `phone`, `adress`) VALUES ('1', '10', '100', 'Bondar', 'Nazarii', '1234567891', 'Lysenka');
INSERT INTO `shipping_company`.`delivery_person` (`id`, `latitude`, `longitude`, `surname`, `name`, `phone`, `adress`) VALUES ('2', '20', '90', 'Holeiko', 'Julia', '1234567892', 'Lysenka');
INSERT INTO `shipping_company`.`delivery_person` (`id`, `latitude`, `longitude`, `surname`, `name`, `phone`, `adress`) VALUES ('3', '30', '80', 'Shvetz', 'Julia', '1234567893', 'Lysenka');
INSERT INTO `shipping_company`.`delivery_person` (`id`, `latitude`, `longitude`, `surname`, `name`, `phone`, `adress`) VALUES ('4', '40', '70', 'Pavlyk', 'Vadym', '1234567894', 'Lychkivska');
INSERT INTO `shipping_company`.`delivery_person` (`id`, `latitude`, `longitude`, `surname`, `name`, `phone`, `adress`) VALUES ('5', '50', '60', 'Salamin', 'Igor', '1234567895', 'Lychkivska');
INSERT INTO `shipping_company`.`delivery_person` (`id`, `latitude`, `longitude`, `surname`, `name`, `phone`, `adress`) VALUES ('6', '60', '50', 'Marchuk', 'Serhii', '1234567896', 'Lychkivska');
INSERT INTO `shipping_company`.`delivery_person` (`id`, `latitude`, `longitude`, `surname`, `name`, `phone`, `adress`) VALUES ('7', '70', '40', 'Topolevskiy', 'Marko', '1234567897', 'Pidvalna');
INSERT INTO `shipping_company`.`delivery_person` (`id`, `latitude`, `longitude`, `surname`, `name`, `phone`, `adress`) VALUES ('8', '80', '30', 'Osadchuk', 'Pavlo', '1234567898', 'Pidvalna');
INSERT INTO `shipping_company`.`delivery_person` (`id`, `latitude`, `longitude`, `surname`, `name`, `phone`, `adress`) VALUES ('9', '90', '20', 'Pashynskyi', 'Vitya', '1234567899', 'Pidvalna');
INSERT INTO `shipping_company`.`delivery_person` (`id`, `latitude`, `longitude`, `surname`, `name`, `phone`, `adress`) VALUES ('10', '100', '10', 'Synyakova', 'Kate', '1234567890', 'Pidvalna');


-- Insert delivery


INSERT INTO `shipping_company`.`delivery` (`id`, `apartmentnumber`, `status`, `delivery_person_id`, `delivery_time_aprox`, `delivery_time_real`, `delivery_payment`) VALUES ('1', '10', 'in progress', '1', '2022-10-15', '2022-10-16', '100');
INSERT INTO `shipping_company`.`delivery` (`id`, `apartmentnumber`, `status`, `delivery_person_id`, `delivery_time_aprox`, `delivery_time_real`, `delivery_payment`) VALUES ('2', '11', 'in progress', '2', '2022-10-15', '2022-10-16', '200');
INSERT INTO `shipping_company`.`delivery` (`id`, `apartmentnumber`, `status`, `delivery_person_id`, `delivery_time_aprox`, `delivery_time_real`, `delivery_payment`) VALUES ('3', '12', 'in progress', '3', '2022-10-15', '2022-10-16', '300');
INSERT INTO `shipping_company`.`delivery` (`id`, `apartmentnumber`, `status`, `delivery_person_id`, `delivery_time_aprox`, `delivery_time_real`, `delivery_payment`) VALUES ('4', '13', 'in progress', '4', '2022-10-15', '2022-10-16', '400');
INSERT INTO `shipping_company`.`delivery` (`id`, `apartmentnumber`, `status`, `delivery_person_id`, `delivery_time_aprox`, `delivery_time_real`, `delivery_payment`) VALUES ('5', '14', 'in progress', '5', '2022-10-15', '2022-10-16', '500');
INSERT INTO `shipping_company`.`delivery` (`id`, `apartmentnumber`, `status`, `delivery_person_id`, `delivery_time_aprox`, `delivery_time_real`, `delivery_payment`) VALUES ('6', '15', 'in progress', '6', '2022-10-15', '2022-10-16', '600');
INSERT INTO `shipping_company`.`delivery` (`id`, `apartmentnumber`, `status`, `delivery_person_id`, `delivery_time_aprox`, `delivery_time_real`, `delivery_payment`) VALUES ('7', '16', 'in progress', '7', '2022-10-15', '2022-10-16', '700');
INSERT INTO `shipping_company`.`delivery` (`id`, `apartmentnumber`, `status`, `delivery_person_id`, `delivery_time_aprox`, `delivery_time_real`, `delivery_payment`) VALUES ('8', '17', 'in progress', '8', '2022-10-15', '2022-10-16', '800');
INSERT INTO `shipping_company`.`delivery` (`id`, `apartmentnumber`, `status`, `delivery_person_id`, `delivery_time_aprox`, `delivery_time_real`, `delivery_payment`) VALUES ('9', '18', 'in progress', '9', '2022-10-15', '2022-10-16', '900');
INSERT INTO `shipping_company`.`delivery` (`id`, `apartmentnumber`, `status`, `delivery_person_id`, `delivery_time_aprox`, `delivery_time_real`, `delivery_payment`) VALUES ('10', '19', 'in progress', '10', '2022-10-15', '2022-10-16', '1000');


-- Insert office


INSERT INTO `shipping_company`.`office` (`id`, `street_address`, `phone`, `office_head`, `city_id`) VALUES ('1', 'Lysenka strrret', '0938107284', 'Marian Petlovanyi', '1');
INSERT INTO `shipping_company`.`office` (`id`, `street_address`, `phone`, `office_head`, `city_id`) VALUES ('2', 'ValueStreet', '0938107284', 'Vadym Buchak', '2');
INSERT INTO `shipping_company`.`office` (`id`, `street_address`, `phone`, `office_head`, `city_id`) VALUES ('3', 'InternalStreet', '0938107284', 'Dionis Pitkov', '3');
INSERT INTO `shipping_company`.`office` (`id`, `street_address`, `phone`, `office_head`, `city_id`) VALUES ('4', 'ProStreet', '0938107284', 'Taras Tovarnitsky', '4');
INSERT INTO `shipping_company`.`office` (`id`, `street_address`, `phone`, `office_head`, `city_id`) VALUES ('5', 'Lysenka', '0938107284', 'Andrew Herasymuk', '5');
INSERT INTO `shipping_company`.`office` (`id`, `street_address`, `phone`, `office_head`, `city_id`) VALUES ('6', 'Avada Kedavra', '0938107284', 'Hart Kevin', '6');
INSERT INTO `shipping_company`.`office` (`id`, `street_address`, `phone`, `office_head`, `city_id`) VALUES ('7', 'Heroyiv UPA', '0938107284', 'Jimmy Carr', '7');
INSERT INTO `shipping_company`.`office` (`id`, `street_address`, `phone`, `office_head`, `city_id`) VALUES ('8', 'Stepana Bandery', '0938107284', 'Olena Zavada', '8');
INSERT INTO `shipping_company`.`office` (`id`, `street_address`, `phone`, `office_head`, `city_id`) VALUES ('9', 'Gorodotska', '0938107284', 'Kerry Light', '9');
INSERT INTO `shipping_company`.`office` (`id`, `street_address`, `phone`, `office_head`, `city_id`) VALUES ('10', 'Independency', '0938107284', 'Ostin Powers', '10');


-- Insert order

INSERT INTO `shipping_company`.`order` (`id`, `time`, `client_id`, `delivery_id`, `is_delivery`, `total_price`, `adressee`, `office_id`, `city_id`) VALUES ('1', '2022-10-14 06:06:06', '1', '1', '1', '100', 'Stepana Bandery', '1', '1');
INSERT INTO `shipping_company`.`order` (`id`, `time`, `client_id`, `delivery_id`, `is_delivery`, `total_price`, `adressee`, `office_id`, `city_id`) VALUES ('2', '2022-10-14 06:06:06', '2', '2', '1', '200', 'Stepana Bandery', '2', '2');
INSERT INTO `shipping_company`.`order` (`id`, `time`, `client_id`, `delivery_id`, `is_delivery`, `total_price`, `adressee`, `office_id`, `city_id`) VALUES ('3', '2022-10-14 06:06:06', '3', '3', '1', '300', 'Stepana Bandery', '3', '3');
INSERT INTO `shipping_company`.`order` (`id`, `time`, `client_id`, `delivery_id`, `is_delivery`, `total_price`, `adressee`, `office_id`, `city_id`) VALUES ('4', '2022-10-14 06:06:06', '4', '4', '1', '400', 'Stepana Bandery', '4', '4');
INSERT INTO `shipping_company`.`order` (`id`, `time`, `client_id`, `delivery_id`, `is_delivery`, `total_price`, `adressee`, `office_id`, `city_id`) VALUES ('5', '2022-10-14 06:06:06', '5', '5', '1', '500', 'Stepana Bandery', '5', '5');
INSERT INTO `shipping_company`.`order` (`id`, `time`, `client_id`, `delivery_id`, `is_delivery`, `total_price`, `adressee`, `office_id`, `city_id`) VALUES ('6', '2022-10-14 06:06:06', '6', '6', '1', '600', 'Stepana Bandery', '6', '6');
INSERT INTO `shipping_company`.`order` (`id`, `time`, `client_id`, `delivery_id`, `is_delivery`, `total_price`, `adressee`, `office_id`, `city_id`) VALUES ('7', '2022-10-14 06:06:06', '7', '7', '1', '700', 'Stepana Bandery', '7', '7');
INSERT INTO `shipping_company`.`order` (`id`, `time`, `client_id`, `delivery_id`, `is_delivery`, `total_price`, `adressee`, `office_id`, `city_id`) VALUES ('8', '2022-10-14 06:06:06', '8', '8', '1', '800', 'Stepana Bandery', '8', '8');
INSERT INTO `shipping_company`.`order` (`id`, `time`, `client_id`, `delivery_id`, `is_delivery`, `total_price`, `adressee`, `office_id`, `city_id`) VALUES ('9', '2022-10-14 06:06:06', '9', '9', '1', '900', 'Stepana Bandery', '9', '9');
INSERT INTO `shipping_company`.`order` (`id`, `time`, `client_id`, `delivery_id`, `is_delivery`, `total_price`, `adressee`, `office_id`, `city_id`) VALUES ('10', '2022-10-14 06:06:06', '10', '10', '1', '1000', 'Lysenka', '10', '10');


-- Insert order_has_car


INSERT INTO `shipping_company`.`order_has_car` (`order_id`, `car_id`, `quantity`) VALUES ('1', '10', '1');
INSERT INTO `shipping_company`.`order_has_car` (`order_id`, `car_id`, `quantity`) VALUES ('2', '9', '1');
INSERT INTO `shipping_company`.`order_has_car` (`order_id`, `car_id`, `quantity`) VALUES ('3', '8', '1');
INSERT INTO `shipping_company`.`order_has_car` (`order_id`, `car_id`, `quantity`) VALUES ('4', '7', '1');
INSERT INTO `shipping_company`.`order_has_car` (`order_id`, `car_id`, `quantity`) VALUES ('5', '6', '1');
INSERT INTO `shipping_company`.`order_has_car` (`order_id`, `car_id`, `quantity`) VALUES ('6', '5', '1');
INSERT INTO `shipping_company`.`order_has_car` (`order_id`, `car_id`, `quantity`) VALUES ('7', '4', '1');
INSERT INTO `shipping_company`.`order_has_car` (`order_id`, `car_id`, `quantity`) VALUES ('8', '3', '1');
INSERT INTO `shipping_company`.`order_has_car` (`order_id`, `car_id`, `quantity`) VALUES ('9', '2', '1');
INSERT INTO `shipping_company`.`order_has_car` (`order_id`, `car_id`, `quantity`) VALUES ('10', '1', '1');




