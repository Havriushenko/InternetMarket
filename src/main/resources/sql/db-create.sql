/********************************************/
/*Internet Market DB creation script for PostgreSQL*/
/******************************************/
DROP database if exists internet_market;
CREATE DATABASE if not exists internet_market CHARACTER SET UTF8 COLLATE utf8_unicode_ci;

use internet_market;

/*****************************/
/*Creates the table of Product */
/*****************************/
CREATE TABLE if not exists `product` (
`id` INT NOT NULL auto_increment,
`name` VARCHAR(200) NOT NULL,
`price` DOUBLE NOT NULL,
`description` VARCHAR(500) NOT NULL,
`product_group_id` INT,
PRIMARY KEY (`id`));

/*****************************/
/*Creates the table of Product Group */
/*****************************/
CREATE TABLE if not exists `product_group` (
`id` INT NOT NULL auto_increment,
`group_name` VARCHAR(50) NOT NULL,
PRIMARY KEY (`id`));

insert into product(name,price,description,product_group_id) values("Трехслойная защитная маска Sterilis многоразовая темно-синяя/розовая (2000992398541)",70,"Трехслойная универсальная многоразовая маска с вставкой внутреннего слоя пенополиуретана, предназначена для защиты дыхательных путей от пыли и разных загрязнений. Чаше всего она применяется в медицине и в косметологии.",1);
insert into product_group(group_name) values("Masks");
