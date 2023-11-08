DELIMITER //
DROP TRIGGER IF EXISTS AddRegion //
CREATE TRIGGER AddRegion
    BEFORE INSERT
    ON `shipping_company`.`city` FOR EACH ROW
BEGIN
    IF(NOT EXISTS(
            SELECT id FROM `shipping_company`.`region`
            WHERE id = NEW.region_id
        ))
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error! No region with such id';
    END IF;
END //
DELIMITER ;

DELIMITER //
DROP TRIGGER IF EXISTS UpdateRegion //
CREATE TRIGGER UpdateRegion
    BEFORE UPDATE
    ON `shipping_company`.`city` FOR EACH ROW
BEGIN
    IF(NOT EXISTS(
            SELECT id FROM `shipping_company`.`region`
            WHERE id = NEW.region_id
        ))
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error! No region with such id';
    END IF;
END //
DELIMITER ;

DELIMITER //
DROP TRIGGER IF EXISTS UpdateRegionId //
CREATE TRIGGER UpdateRegionId
    BEFORE UPDATE
    ON `shipping_company`.`region` FOR EACH ROW
BEGIN
    IF(EXISTS(
            SELECT region_id FROM `shipping_company`.`city`
            WHERE region_id = OLD.id
        ))
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error! Can`t update row! FK';
    END IF;
END //
DELIMITER ;

DELIMITER //
DROP TRIGGER IF EXISTS DeleteRegionId //
CREATE TRIGGER DeleteRegionId
    BEFORE DELETE
    ON `shipping_company`.`region` FOR EACH ROW
BEGIN
    IF(EXISTS(
            SELECT region_id FROM `shipping_company`.`city`
            WHERE region_id = OLD.id
        ))
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error! Can`t delete row! FK';
    END IF;
END //
DELIMITER ;


DELIMITER //
DROP TRIGGER IF EXISTS CheckPhoneCardinality //
CREATE TRIGGER CheckPhoneCardinality
    BEFORE INSERT
    ON `shipping_company`.`delivery_person` FOR EACH ROW
BEGIN
    IF (LENGTH(NEW.phone) < 10)
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Phone can`t be less 10 sybmols';
    END IF;
END //
DELIMITER ;


DELIMITER //
DROP TRIGGER IF EXISTS only_names_accepted_in_delivery_person_insertion;
CREATE TRIGGER only_names_accepted_in_delivery_person_insertion
    BEFORE INSERT
    ON `shipping_company`.`delivery_person` FOR EACH ROW
BEGIN
    IF NEW.name NOT IN ('Svitlana', 'Petro', 'Olha', 'Taras') THEN
        SIGNAL SQLSTATE '22000'
            SET MESSAGE_TEXT = 'ONLY Svitlana, Petro, Olha, Taras can be delivery persons';
    END IF;
END //
DELIMITER ;

DROP TRIGGER IF EXISTS restrict_modification_of_company_table;
DELIMITER //
CREATE TRIGGER restrict_modification_of_company_table
    BEFORE UPDATE
    ON `shipping_company`.`delivery_person` FOR EACH ROW
BEGIN
    SIGNAL SQLSTATE '22000'
        SET MESSAGE_TEXT = 'You can not update this table.';
END //
DELIMITER ;