INSERT INTO `shop_db_1`.`categories` (title) VALUE
('PHONE'), ('LAPTOP'), ('HEADPHONES'), ('MONOBLOCK'), ('CLOCK');
GO

UPDATE `shop_db_1`.`products` SET `category_id` = '1' WHERE (`id` = '1');
GO
UPDATE `shop_db_1`.`products` SET `category_id` = '2' WHERE (`id` = '2');
GO
UPDATE `shop_db_1`.`products` SET `category_id` = '3' WHERE (`id` = '3');
GO
UPDATE `shop_db_1`.`products` SET `category_id` = '5' WHERE (`id` = '4');
GO
UPDATE `shop_db_1`.`products` SET `category_id` = '4' WHERE (`id` = '5');
GO

