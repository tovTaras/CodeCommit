USE `shipping_company`;

DROP PROCEDURE IF EXISTS insertTenCarSizes;
DELIMITER //
CREATE PROCEDURE insertTenCarSizes (
    IN new_diameter VARCHAR(40),
    IN new_ingredient_cost_coef FLOAT,
    IN new_name varchar(40),
    IN new_price varchar(40))
BEGIN
    DECLARE max_id INT;
    DECLARE idx INT;
    SELECT MAX(id) + 1 INTO max_id FROM `region`;
    IF max_id IS NULL THEN SET max_id=1;
    END IF;
    SET idx = 1;
    label1: WHILE idx < 11 DO
            INSERT INTO `car_size` (`diameter`, `ingredient_cost_coef`, `name`, `price`) VALUES (CONCAT(new_diameter, idx), new_ingredient_cost_coef*idx, CONCAT(new_name, idx), idx*100);
            SET max_id = max_id + 1;
            SET idx = idx + 1;
            ITERATE label1;
        END WHILE;
END //
DELIMITER ;

DROP PROCEDURE IF EXISTS CityParamInsert;
DELIMITER //
CREATE PROCEDURE CityParamInsert (
    IN new_name VARCHAR(12),
    IN new_region_id INT)
BEGIN
    INSERT INTO `city` (name, region_id) VALUES (new_name, new_region_id);
    SELECT id, name, region_id FROM `city` WHERE name = new_name;
END //
DELIMITER ;

DROP PROCEDURE IF EXISTS CalcAverageCarPrice;
DELIMITER //
CREATE PROCEDURE CalcAverageCarPrice()
BEGIN
    DECLARE label VARCHAR(20);
    SELECT GetAverageCarPrice() AS average_salary;
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS AddCarOrderRelationship //
CREATE PROCEDURE AddCarOrderRelationship(
        IN car_name varchar(45),
    IN address varchar(45))
BEGIN
    DECLARE p_id, o_id INT;
    SELECT id INTO p_id FROM `car` WHERE `name` = car_name;
    SELECT id INTO o_id FROM `order` WHERE adressee = address;
    IF (p_id IS NULL)
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Null value: no such car found';
    END IF;
    IF (o_id IS NULL)
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Null value: no such addressee found';
    END IF;
    INSERT INTO `order_has_car` (order_id, car_id, quantity) VALUES (o_id, p_id, 1);
END //
DELIMITER ;

DELIMITER //
DROP PROCEDURE IF EXISTS CreateTablesWithCursor //
CREATE PROCEDURE CreateTablesWithCursor()
BEGIN
    DECLARE done BOOL DEFAULT false;
    DECLARE surname VARCHAR(50);
    DECLARE my_cursor CURSOR
        FOR SELECT name FROM `delivery_person`;

    DECLARE CONTINUE HANDLER
        FOR NOT FOUND SET done = true;

    OPEN my_cursor;
    my_loop: LOOP
        FETCH my_cursor INTO surname;
        IF (done = true) THEN LEAVE my_loop;
        END IF;
        SET @temp_query = CONCAT('CREATE TABLE IF NOT EXISTS ', surname, DATE_FORMAT(NOW(), "_%Y_%m_%d_%H_%i_%s"), ' (', surname, '1 INT, ', surname, '2 BOOL);');
        PREPARE my_query FROM @temp_query;
        EXECUTE my_query;
        DEALLOCATE PREPARE my_query;
    END LOOP;
    CLOSE my_cursor;
END //
DELIMITER ;