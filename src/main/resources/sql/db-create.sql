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
PRIMARY KEY (`id`));

/*****************************/
/*Creates the table of Product Group */
/*****************************/
CREATE TABLE if not exists `product_group` (
`id` INT NOT NULL auto_increment,
`group_name` VARCHAR(50) NOT NULL,
PRIMARY KEY (`id`));

/*****************************/
/*Creates the table of Characteristic*/
/*****************************/
CREATE TABLE if not exists `characteristic` (
`id` INT NOT NULL auto_increment,
`weight` double   NOT NULL,
`length` double   NOT NULL,
`width` double   NOT NULL,
`height` double   NOT NULL,
`color` VARCHAR(30) NOT NULL,
PRIMARY KEY (`id`));

/*****************************/
/*Creates the table of Relation Product and Product Group*/
/*****************************/
CREATE TABLE IF NOT EXISTS `product_product_group_relation`(
`id` INT NOT NULL auto_increment,
`product_id` INT NOT NULL,
`product_group_id` INT NOT NULL,
PRIMARY KEY (`id`),
FOREIGN KEY(product_id)REFERENCES product(id) ON DELETE CASCADE,
FOREIGN KEY(product_group_id)REFERENCES product_group(id) ON DELETE CASCADE);

/*****************************/
/*Creates the table of relation Product and Characteristic*/
/*****************************/
CREATE TABLE IF NOT EXISTS `product_characteristic_relation`(
`id` INT NOT NULL auto_increment,
`product_id` INT NOT NULL,
`characteristic_id` INT NOT NULL,
PRIMARY KEY (`id`),
FOREIGN KEY(product_id)REFERENCES product(id) ON DELETE CASCADE,
FOREIGN KEY(characteristic_id)REFERENCES characteristic(id) ON DELETE CASCADE);


insert into product(name,price,description) values("Трехслойная защитная маска Sterilis многоразовая темно-синяя/розовая (2000992398541)",70,"Трехслойная универсальная многоразовая маска с вставкой внутреннего слоя пенополиуретана, предназначена для защиты дыхательных путей от пыли и разных загрязнений. Чаше всего она применяется в медицине и в косметологии.");
insert into product_group(group_name) values("Masks");
insert into characteristic(weight,length,width,height,color) values(1,10,1,5,"black");
insert into product_product_group_relation(product_id,product_group_id) values(1,1);
insert into product_characteristic_relation(product_id,characteristic_id) values(1,1);
