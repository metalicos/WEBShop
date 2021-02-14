SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

/*
#################################################################
#################################################################
						DB RECREATION
#################################################################
#################################################################
*/


DROP SCHEMA IF EXISTS `webshop`;
CREATE SCHEMA `webshop` DEFAULT CHARACTER SET utf8;
USE `webshop`;

CREATE TABLE IF NOT EXISTS `webshop`.`role`
(
    `id`   INT                    NOT NULL AUTO_INCREMENT,
    `name` ENUM ('ADMIN', 'USER') NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 3
    DEFAULT CHARACTER SET = utf8;


CREATE TABLE IF NOT EXISTS `webshop`.`shopping_cart`
(
    `id`          INT       NOT NULL AUTO_INCREMENT,
    `last_update` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  AUTO_INCREMENT = 3
  DEFAULT CHARACTER SET = UTF8;

CREATE TABLE IF NOT EXISTS `webshop`.`account`
(
    `id`               INT           NOT NULL AUTO_INCREMENT,
    `email`            VARCHAR(255)  NOT NULL,
    `password`         VARCHAR(2000) NOT NULL,
    `create_time`      TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `role_id`          INT           NOT NULL,
    `shopping_cart_id` INT           NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_account_role1_idx` (`role_id` ASC) VISIBLE,
    INDEX `fk_account_shopping_cart1_idx` (`shopping_cart_id` ASC) VISIBLE,
    CONSTRAINT `fk_account_role1`
        FOREIGN KEY (`role_id`)
            REFERENCES `webshop`.`role` (`id`),
    CONSTRAINT `fk_account_shopping_cart1`
        FOREIGN KEY (`shopping_cart_id`)
            REFERENCES `webshop`.`shopping_cart` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 3
    DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `webshop`.`account_detail`
(
    `id`            INT          NOT NULL AUTO_INCREMENT,
    `phone`         VARCHAR(255) NULL DEFAULT NULL,
    `zip_code`      INT          NULL DEFAULT NULL,
    `last_update`   TIMESTAMP    NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `surname_ua`    VARCHAR(300) NULL DEFAULT NULL,
    `first_name_ua` VARCHAR(300) NULL DEFAULT NULL,
    `patronymic_ua` VARCHAR(300) NULL DEFAULT NULL,
    `country_ua`    VARCHAR(300) NULL DEFAULT NULL,
    `city_ua`       VARCHAR(300) NULL DEFAULT NULL,
    `street_ua`     VARCHAR(300) NULL DEFAULT NULL,
    `building_ua`   VARCHAR(300) NULL DEFAULT NULL,
    `flat_ua`       VARCHAR(300) NULL DEFAULT NULL,
    `surname_en`    VARCHAR(300) NULL DEFAULT NULL,
    `first_name_en` VARCHAR(300) NULL DEFAULT NULL,
    `patronymic_en` VARCHAR(300) NULL DEFAULT NULL,
    `country_en`    VARCHAR(300) NULL DEFAULT NULL,
    `city_en`       VARCHAR(300) NULL DEFAULT NULL,
    `street_en`     VARCHAR(300) NULL DEFAULT NULL,
    `building_en`   VARCHAR(300) NULL DEFAULT NULL,
    `flat_en`       VARCHAR(300) NULL DEFAULT NULL,
    `account_photo` LONGBLOB     NULL DEFAULT NULL,
    `account_id`    INT          NOT NULL,
    PRIMARY KEY (`id`, `account_id`),
    INDEX `fk_account_detail_account1_idx` (`account_id` ASC) VISIBLE,
    CONSTRAINT `fk_account_detail_account1`
        FOREIGN KEY (`account_id`)
            REFERENCES `webshop`.`account` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `webshop`.`status`
(
    `id`   INT                                                                   NOT NULL AUTO_INCREMENT,
    `name` ENUM ('IN_PROGRESS', 'DELIVERY', 'CANCELED', 'DELIVERED', 'FINISHED') NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 6
    DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `webshop`.`account_order`
(
    `id`                   INT       NOT NULL,
    `create_time`          TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `account_id`           INT       NULL     DEFAULT NULL,
    `status_id`            INT       NOT NULL,
    `droped_by_account_id` INT       NULL     DEFAULT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_account_order_account_idx` (`account_id` ASC) VISIBLE,
    INDEX `fk_account_order_status1_idx` (`status_id` ASC) VISIBLE,
    INDEX `fk_account_order_account1_idx` (`droped_by_account_id` ASC) VISIBLE,
    CONSTRAINT `fk_account_order_account`
        FOREIGN KEY (`account_id`)
            REFERENCES `webshop`.`account` (`id`)
            ON DELETE SET NULL
            ON UPDATE CASCADE,
    CONSTRAINT `fk_account_order_account1`
        FOREIGN KEY (`droped_by_account_id`)
            REFERENCES `webshop`.`account` (`id`)
            ON DELETE SET NULL
            ON UPDATE CASCADE,
    CONSTRAINT `fk_account_order_status1`
        FOREIGN KEY (`status_id`)
            REFERENCES `webshop`.`status` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `webshop`.`category`
(
    `id`             INT           NOT NULL AUTO_INCREMENT,
    `name_ua`        VARCHAR(300)  NOT NULL,
    `name_en`        VARCHAR(45)   NULL,
    `description_ua` VARCHAR(2000) NULL DEFAULT NULL,
    `description_en` VARCHAR(2000) NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  DEFAULT CHARACTER SET = UTF8;

CREATE TABLE IF NOT EXISTS `webshop`.`product`
(
    `id`             INT            NOT NULL AUTO_INCREMENT,
    `price`          DECIMAL(10, 0) NOT NULL DEFAULT '0',
    `amount`         INT            NOT NULL DEFAULT '0',
    `ordered_amount` INT            NOT NULL DEFAULT '0',
    `category_id`    INT            NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_product_category1_idx` (`category_id` ASC) VISIBLE,
    CONSTRAINT `fk_product_category1`
        FOREIGN KEY (`category_id`)
            REFERENCES `webshop`.`category` (`id`)
            ON DELETE RESTRICT
            ON UPDATE CASCADE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `webshop`.`account_order_has_product`
(
    `account_order_id` INT            NOT NULL,
    `product_id`       INT            NOT NULL,
    `price`            DECIMAL(10, 0) NOT NULL,
    `product_amount`   INT            NOT NULL,
    `create_date`      TIMESTAMP      NULL DEFAULT CURRENT_TIMESTAMP,
    `last_update`      TIMESTAMP      NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`account_order_id`, `product_id`),
    INDEX `fk_account_order_has_product_product1_idx` (`product_id` ASC) VISIBLE,
    INDEX `fk_account_order_has_product_account_order1_idx` (`account_order_id` ASC) VISIBLE,
    CONSTRAINT `fk_account_order_has_product_account_order1`
        FOREIGN KEY (`account_order_id`)
            REFERENCES `webshop`.`account_order` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `fk_account_order_has_product_product1`
        FOREIGN KEY (`product_id`)
            REFERENCES `webshop`.`product` (`id`)
            ON DELETE RESTRICT
            ON UPDATE CASCADE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `webshop`.`product_detail`
(
    `id`         INT          NOT NULL AUTO_INCREMENT,
    `name_ua`    VARCHAR(300) NULL DEFAULT NULL,
    `color_ua`   VARCHAR(300) NULL DEFAULT NULL,
    `size_ua`    VARCHAR(300) NULL DEFAULT NULL,
    `about_ua`   VARCHAR(300) NULL DEFAULT NULL,
    `name_en`    VARCHAR(300) NULL DEFAULT NULL,
    `color_en`   VARCHAR(300) NULL DEFAULT NULL,
    `size_en`    VARCHAR(300) NULL DEFAULT NULL,
    `about_en`   VARCHAR(300) NULL DEFAULT NULL,
    `photo_1`    LONGBLOB     NULL DEFAULT NULL,
    `photo_2`    LONGBLOB     NULL DEFAULT NULL,
    `photo_3`    LONGBLOB     NULL DEFAULT NULL,
    `product_id` INT          NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_product_locale_product1_idx` (`product_id` ASC) VISIBLE,
    CONSTRAINT `fk_product_locale_product1`
        FOREIGN KEY (`product_id`)
            REFERENCES `webshop`.`product` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `webshop`.`shopping_cart_has_product`
(
    `shopping_cart_id` INT NOT NULL,
    `product_id`       INT NOT NULL,
    PRIMARY KEY (`shopping_cart_id`, `product_id`),
    INDEX `fk_shopping_cart_has_product_product1_idx` (`product_id` ASC) VISIBLE,
    INDEX `fk_shopping_cart_has_product_shopping_cart1_idx` (`shopping_cart_id` ASC) VISIBLE,
    CONSTRAINT `fk_shopping_cart_has_product_product1`
        FOREIGN KEY (`product_id`)
            REFERENCES `webshop`.`product` (`id`),
    CONSTRAINT `fk_shopping_cart_has_product_shopping_cart1`
        FOREIGN KEY (`shopping_cart_id`)
            REFERENCES `webshop`.`shopping_cart` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;


/*
#################################################################
#################################################################
						DATA INSERTION
#################################################################
#################################################################
*/


START TRANSACTION;
USE `webshop`;
INSERT INTO `webshop`.`role` (`id`, `name`)
VALUES (1, 'ADMIN');
INSERT INTO `webshop`.`role` (`id`, `name`)
VALUES (2, 'USER');
COMMIT;

START TRANSACTION;
USE `webshop`;
INSERT INTO `webshop`.`shopping_cart` (`id`, `last_update`)
VALUES (1, CURRENT_TIMESTAMP);
INSERT INTO `webshop`.`shopping_cart` (`id`, `last_update`)
VALUES (2, CURRENT_TIMESTAMP);
INSERT INTO `webshop`.`shopping_cart` (`id`, `last_update`)
VALUES (3, CURRENT_TIMESTAMP);
COMMIT;

START TRANSACTION;
USE `webshop`;
INSERT INTO `webshop`.`account_detail`
(`id`, `phone`, `zip_code`, `last_update`, `surname_ua`, `first_name_ua`, `patronymic_ua`,
 `country_ua`, `city_ua`, `street_ua`, `building_ua`, `flat_ua`, `surname_en`, `first_name_en`,
 `patronymic_en`, `country_en`, `city_en`, `street_en`, `building_en`, `flat_en`, `account_photo`, `account_id`)
VALUES (1, '0683567221', 79021, NULL, 'Донецький', 'Остап', 'Євгенович', 'Україна', 'Львів', 'С.Петлюри', '48', '45',
        'Donetskyy', 'Ostap', 'Yevgenovych', 'Ukraine', 'Lviv', 'Symona Petliury', '48', '45', NULL, 1);
INSERT INTO `webshop`.`account_detail`
(`id`, `phone`, `zip_code`, `last_update`, `surname_ua`, `first_name_ua`, `patronymic_ua`,
 `country_ua`, `city_ua`, `street_ua`, `building_ua`, `flat_ua`, `surname_en`, `first_name_en`,
 `patronymic_en`, `country_en`, `city_en`, `street_en`, `building_en`, `flat_en`, `account_photo`, `account_id`)
VALUES (2, '0990852635', 79021, NULL, 'Львівська', 'Ольга', 'Ярославівна', 'Україна', 'Львів', 'С.Петлюри', '22', '11',
        'Lvivska', 'Olga', 'Yaroslavivna', 'Ukraine', 'Lviv', 'Symona Petliury', '22', '11', NULL, 2);
INSERT INTO `webshop`.`account_detail`
(`id`, `phone`, `zip_code`, `last_update`, `surname_ua`, `first_name_ua`, `patronymic_ua`,
 `country_ua`, `city_ua`, `street_ua`, `building_ua`, `flat_ua`, `surname_en`, `first_name_en`,
 `patronymic_en`, `country_en`, `city_en`, `street_en`, `building_en`, `flat_en`, `account_photo`, `account_id`)
VALUES (3, '0681845903', 79023, NULL, 'Режекс', 'Володимир', 'Панасович', 'Україна', 'Львів', 'С.Петлюри', '33', '22',
        'Regex', 'Volodymyr', 'Panasovych', 'Ukraine', 'Lviv', 'Symona Petliury', '33', '22', NULL, 3);
COMMIT;

START TRANSACTION;
USE `webshop`;
INSERT INTO `webshop`.`account`
(`id`, `email`, `password`, `create_time`, `role_id`, `shopping_cart_id`)
VALUES (1, 'root@gmail.com', 'root', DEFAULT, 1, 1);
INSERT INTO `webshop`.`account`
(`id`, `email`, `password`, `create_time`, `role_id`, `shopping_cart_id`)
VALUES (2, 'user@gmail.com', 'user', DEFAULT, 2, 2);
INSERT INTO `webshop`.`account`
(`id`, `email`, `password`, `create_time`, `role_id`, `shopping_cart_id`)
VALUES (3, 'tester@gmail.com', 'tester', DEFAULT, 2, 3);
COMMIT;

START TRANSACTION;
USE `webshop`;
INSERT INTO `webshop`.`status` (`id`, `name`)
VALUES (1, 'IN_PROGRESS');
INSERT INTO `webshop`.`status` (`id`, `name`)
VALUES (2, 'DELIVERY');
INSERT INTO `webshop`.`status` (`id`, `name`)
VALUES (3, 'CANCELED');
INSERT INTO `webshop`.`status` (`id`, `name`)
VALUES (4, 'DELIVERED');
INSERT INTO `webshop`.`status` (`id`, `name`)
VALUES (5, 'FINISHED');
COMMIT;



START TRANSACTION;
USE `webshop`;
INSERT INTO `webshop`.`category` (`id`, `name_ua`, `name_en`, `description_ua`, `description_en`)
VALUES (1, 'Флюси', 'Fluxes', 'Речовини для паяння', 'Liquids for soldering');
INSERT INTO `webshop`.`category` (`id`, `name_ua`, `name_en`, `description_ua`, `description_en`)
VALUES (2, 'Паяльники і паяльні станції', 'Soldering irons and soldering stations', 'Приладдя для паяння',
        'Equipment for soldering');
COMMIT;


START TRANSACTION;
USE `webshop`;
INSERT INTO `webshop`.`product` (`id`, `price`, `amount`, `ordered_amount`, `category_id`)
VALUES (1, 23, 66, 0, 1);
INSERT INTO `webshop`.`product` (`id`, `price`, `amount`, `ordered_amount`, `category_id`)
VALUES (2, 44, 66, 0, 1);
INSERT INTO `webshop`.`product` (`id`, `price`, `amount`, `ordered_amount`, `category_id`)
VALUES (3, 55, 66, 0, 1);
INSERT INTO `webshop`.`product` (`id`, `price`, `amount`, `ordered_amount`, `category_id`)
VALUES (4, 56, 66, 0, 1);
INSERT INTO `webshop`.`product` (`id`, `price`, `amount`, `ordered_amount`, `category_id`)
VALUES (5, 34, 66, 0, 1);
INSERT INTO `webshop`.`product` (`id`, `price`, `amount`, `ordered_amount`, `category_id`)
VALUES (6, 22, 66, 0, 2);
INSERT INTO `webshop`.`product` (`id`, `price`, `amount`, `ordered_amount`, `category_id`)
VALUES (7, 10, 66, 0, 2);
INSERT INTO `webshop`.`product` (`id`, `price`, `amount`, `ordered_amount`, `category_id`)
VALUES (8, 11, 66, 0, 2);
INSERT INTO `webshop`.`product` (`id`, `price`, `amount`, `ordered_amount`, `category_id`)
VALUES (9, 45, 66, 0, 2);
INSERT INTO `webshop`.`product` (`id`, `price`, `amount`, `ordered_amount`, `category_id`)
VALUES (10, 55, 66, 0, 2);
COMMIT;


START TRANSACTION;
USE `webshop`;
INSERT INTO `webshop`.`product_detail`
(`id`, `name_ua`, `color_ua`, `size_ua`, `about_ua`, `name_en`, `color_en`, `size_en`, `about_en`, `photo_1`, `photo_2`,
 `photo_3`, `product_id`)
VALUES (1, 'Флюс F10', 'Жовтий', '10мм*2.2мм*1.4мм', 'Активний флюс', 'Flux F10', 'Yellow', '10mm*2.2mm*1.4mm',
        'Active flux', NULL, NULL, NULL, 1);
INSERT INTO `webshop`.`product_detail`
(`id`, `name_ua`, `color_ua`, `size_ua`, `about_ua`, `name_en`, `color_en`, `size_en`, `about_en`, `photo_1`, `photo_2`,
 `photo_3`, `product_id`)
VALUES (2, 'Флюс F8', 'Коричневий', '10мм*2.2мм*1.4мм', 'Активний флюс', 'Flux F8', 'Brown', '10mm*2.2mm*1.4mm',
        'Active flux', NULL, NULL, NULL, 2);
INSERT INTO `webshop`.`product_detail`
(`id`, `name_ua`, `color_ua`, `size_ua`, `about_ua`, `name_en`, `color_en`, `size_en`, `about_en`, `photo_1`, `photo_2`,
 `photo_3`, `product_id`)
VALUES (3, 'Флюс F5', 'Прозорий', '10мм*2.2мм*1.4мм', 'Активний флюс', 'Flux F5', 'Transparent', '10mm*2.2mm*1.4mm',
        'Active flux', NULL, NULL, NULL, 3);
INSERT INTO `webshop`.`product_detail`
(`id`, `name_ua`, `color_ua`, `size_ua`, `about_ua`, `name_en`, `color_en`, `size_en`, `about_en`, `photo_1`, `photo_2`,
 `photo_3`, `product_id`)
VALUES (4, 'Флюс F1', 'Жовтий', '10мм*2.2мм*1.4мм', 'Активний флюс', 'Flux F1', 'Yellow', '10mm*2.2mm*1.4mm',
        'Active flux', NULL, NULL, NULL, 4);
INSERT INTO `webshop`.`product_detail`
(`id`, `name_ua`, `color_ua`, `size_ua`, `about_ua`, `name_en`, `color_en`, `size_en`, `about_en`, `photo_1`, `photo_2`,
 `photo_3`, `product_id`)
VALUES (5, 'Флюс F3', 'Жовтий', '10мм*2.2мм*1.4мм', 'Активний флюс', 'Flux F3', 'Yellow', '10mm*2.2mm*1.4mm',
        'Active flux', NULL, NULL, NULL, 5);
INSERT INTO `webshop`.`product_detail`
(`id`, `name_ua`, `color_ua`, `size_ua`, `about_ua`, `name_en`, `color_en`, `size_en`, `about_en`, `photo_1`, `photo_2`,
 `photo_3`, `product_id`)
VALUES (6, 'Флюс SB-34', 'Жовтий', '10мм*2.2мм*1.4мм', 'Активний флюс', 'Flux SB-34', 'Yellow', '10mm*2.2mm*1.4mm',
        'Active flux', NULL, NULL, NULL, 6);
INSERT INTO `webshop`.`product_detail`
(`id`, `name_ua`, `color_ua`, `size_ua`, `about_ua`, `name_en`, `color_en`, `size_en`, `about_en`, `photo_1`, `photo_2`,
 `photo_3`, `product_id`)
VALUES (7, 'Паяльна станція F12', 'Чорний', '10мм*22.2мм*33.4мм', 'Паяльна станція містить фен та паяльник',
        'Soldering station F-12', 'Black', '10mm*22.2mm*33.4mm',
        'Soldering station contains heating fan and soldering iron.', NULL, NULL, NULL, 7);
INSERT INTO `webshop`.`product_detail`
(`id`, `name_ua`, `color_ua`, `size_ua`, `about_ua`, `name_en`, `color_en`, `size_en`, `about_en`, `photo_1`, `photo_2`,
 `photo_3`, `product_id`)
VALUES (8, 'Паяльник TS-100', 'Чорний', '10мм*10мм*20мм', 'Зручний електричний паяльник', 'Soldering iron TS-100',
        'Black', '10mm*10mm*20mm', 'Comfortable electrical soldering iron', NULL, NULL, NULL, 8);
INSERT INTO `webshop`.`product_detail`
(`id`, `name_ua`, `color_ua`, `size_ua`, `about_ua`, `name_en`, `color_en`, `size_en`, `about_en`, `photo_1`, `photo_2`,
 `photo_3`, `product_id`)
VALUES (9, 'Паяльник TS-200', 'Чорний', '10мм*10мм*20мм', 'Зручний електричний паяльник', 'Soldering iron TS-200',
        'Black', '10mm*10mm*20mm', 'Comfortable electrical soldering iron', NULL, NULL, NULL, 9);
INSERT INTO `webshop`.`product_detail`
(`id`, `name_ua`, `color_ua`, `size_ua`, `about_ua`, `name_en`, `color_en`, `size_en`, `about_en`, `photo_1`, `photo_2`,
 `photo_3`, `product_id`)
VALUES (10, 'Паяльник TS-300', 'Чорний', '10мм*10мм*20мм', 'Зручний електричний паяльник', 'Soldering iron TS-300',
        'Black', '10mm*10mm*20mm', 'Comfortable electrical soldering iron', NULL, NULL, NULL, 10);
COMMIT;



SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;