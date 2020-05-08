/********************************************/
/*Internet Market DB creation script for PostgreSQL*/
/******************************************/
DROP database if exists internet_market_local;
CREATE DATABASE if not exists internet_market_local CHARACTER SET UTF8 COLLATE utf8_unicode_ci;

use internet_market_local;

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

/*****************************/
/*Creates the table of Users*/
/*****************************/
CREATE TABLE if not exists `users` (
`id` INT NOT NULL auto_increment,
`name` VARCHAR(100) NOT NULL,
`password` VARCHAR(300) NOT NULL,
`surname` VARCHAR(100) NOT NULL,
`email` VARCHAR(100) NOT NULL,
PRIMARY KEY (`id`));

/*****************************/
/*Creates the table of Role*/
/*****************************/
CREATE TABLE if not exists `user_role` (
`user_id` INT NOT NULL,
`roles` VARCHAR(50) NOT NULL);

/*****************************/
/*Creates the table of Orders */
/*****************************/
CREATE TABLE if not exists `orders` (
`id` INT NOT NULL auto_increment,
`status` BOOLEAN NOT NULL,
`data` VARCHAR (30),
`user_id` INT NOT NULL,
PRIMARY KEY (`id`));

/*****************************/
/*Creates the table of Orders info and relation between Products and Orders*/
/*****************************/
CREATE TABLE if not exists `order_info` (
`order_id` INT NOT NULL,
`product_id` INT NOT NULL,
`quantity` INT NOT NULL,
FOREIGN KEY(order_id)REFERENCES orders(id) ON DELETE CASCADE,
FOREIGN KEY(product_id)REFERENCES product(id) ON DELETE CASCADE,
PRIMARY KEY (order_id, product_id));

/*****************************/
/*Creates test products*/
/*****************************/
insert into product_group(id,group_name) values(1,"Masks");
insert into product_group(id,group_name) values(2,"Laptop");
insert into product(id,name,price,description,product_group_id) values(1,"Трехслойная защитная маска Sterilis многоразовая темно-синяя/розовая (2000992398541)",70,"Трехслойная универсальная многоразовая маска с вставкой внутреннего слоя пенополиуретана, предназначена для защиты дыхательных путей от пыли и разных загрязнений. Чаше всего она применяется в медицине и в косметологии.",1);
insert into product(id,name,price,description,product_group_id) values(2,"Test Laptop",10000,"Test Laptop",2);
insert into product(id,name,price,description,product_group_id) values(3,"Test Laptop 2",35666,"Test Laptop №2",2);

/*****************************/
/*Creates test users*/
/*****************************/
insert into users(id,name,password,surname,email) values(1,"admin","$2a$10$RVF9alUMkr5lj4t/IK0hHuzuiEUHMFEupfSbK6G/QEoyELsu.u.8C","surname","admin@gmail.com");
insert into user_role(user_id,roles) values(1,"ADMIN");
insert into users(id,name,password,surname,email) values(2,"testUser1","$2a$10$XuzcCav7k1pOQLL/T5el7.H1Hc2p22I8nJ.lxTaIzKKT/wjUraGpu","surname1","test_user1@gmail.com");
insert into user_role(user_id,roles) values(2,"USER");
insert into users(id,name,password,surname,email) values(3,"testUser2","$2a$10$cTvc5hnF2FphLIbyVVaGiOcGgdl9Uo5tYdpkMO5.CRqBiO8so5fwO","surname2","test_user2@gmail.com");
insert into user_role(user_id,roles) values(3,"USER");

/*****************************/
/*Creates test orders*/
/*****************************/
insert into orders(id,status,data,user_id) values(1,true,null,2);
insert into orders(id,status,data,user_id) values(2,false,"08.05.2020",2);
insert into orders(id,status,data,user_id) values(3,true,null,3);

insert into order_info(order_id,product_id,quantity) value(1,2,10);
insert into order_info(order_id,product_id,quantity) value(1,3,10);
insert into order_info(order_id,product_id,quantity) value(2,1,100);
