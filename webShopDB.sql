/*
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
*/
/*
DROP SCHEMA IF EXISTS `webshop` ;
DROP TABLE IF EXISTS `webshop`.`role` ;
DROP TABLE IF EXISTS `webshop`.`shopping_cart` ;
DROP TABLE IF EXISTS `webshop`.`account` ;
DROP TABLE IF EXISTS `webshop`.`account_detail` ;
DROP TABLE IF EXISTS `webshop`.`status` ;
DROP TABLE IF EXISTS `webshop`.`account_order` ;
DROP TABLE IF EXISTS `webshop`.`category` ;
DROP TABLE IF EXISTS `webshop`.`product` ;
DROP TABLE IF EXISTS `webshop`.`account_order_has_product` ;
DROP TABLE IF EXISTS `webshop`.`product_detail` ;
DROP TABLE IF EXISTS `webshop`.`shopping_cart_has_product` ;
*/
/*
	###########################################################################
							DROP OLD & CREATE A NEW DATABASE
	###########################################################################
*/

DROP SCHEMA `webshop`;
CREATE SCHEMA `webshop` DEFAULT CHARACTER SET utf8;
USE `webshop`;
/*
CREATE SCHEMA IF NOT EXISTS `webshop` DEFAULT CHARACTER SET utf8 ;
*/
CREATE TABLE IF NOT EXISTS `webshop`.`role`
(
    `id`   INT                    NOT NULL AUTO_INCREMENT,
    `name` ENUM ('ADMIN', 'USER') NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `webshop`.`shopping_cart`
(
    `id`          INT       NOT NULL AUTO_INCREMENT,
    `last_update` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  DEFAULT CHARACTER SET = UTF8;

CREATE TABLE IF NOT EXISTS `webshop`.`account`
(
    `id`               INT          NOT NULL AUTO_INCREMENT,
    `email`            VARCHAR(100) NOT NULL,
    `password`         VARCHAR(100) NOT NULL,
    `create_time`      TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `role_id`          INT          NOT NULL,
    `shopping_cart_id` INT          NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_account_role1_idx` (`role_id` ASC) VISIBLE,
    INDEX `fk_account_shopping_cart1_idx` (`shopping_cart_id` ASC) VISIBLE,
    CONSTRAINT `fk_account_role1`
        FOREIGN KEY (`role_id`)
            REFERENCES `webshop`.`role` (`id`),
    CONSTRAINT `fk_account_shopping_cart1`
        FOREIGN KEY (`shopping_cart_id`)
            REFERENCES `webshop`.`shopping_cart` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `webshop`.`account_detail`
(
    `id`            INT          NOT NULL,
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
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `webshop`.`status`
(
    `id`   INT                                                                   NOT NULL AUTO_INCREMENT,
    `name` ENUM ('IN_PROGRESS', 'DELIVERY', 'CANCELED', 'DELIVERED', 'FINISHED') NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE
) ENGINE = InnoDB
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
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `webshop`.`category`
(
    `id`             INT           NOT NULL AUTO_INCREMENT,
    `name`           VARCHAR(300)  NOT NULL,
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
) ENGINE = InnoDB
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
) ENGINE = InnoDB
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
) ENGINE = InnoDB
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
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


/*
	###########################################################################
								INSERTION OF DATA
	###########################################################################
*/

insert into webshop.role
    (role.name)
    value
    ('ADMIN'),('USER');

insert into webshop.status
    (status.name)
    value
    ('IN_PROGRESS'),('DELIVERY'),('CANCELED'),('DELIVERED'),('FINISHED');

insert into webshop.shopping_cart
    (shopping_cart.last_update)
    value
    (CURRENT_TIMESTAMP),(CURRENT_TIMESTAMP);

insert into webshop.account
(account.email, account.password, account.role_id, account.shopping_cart_id)
values ('root@gmail.com', 'root', 1, 1),
       ('user@gmail.com', 'user', 2, 1);


/*
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
*/