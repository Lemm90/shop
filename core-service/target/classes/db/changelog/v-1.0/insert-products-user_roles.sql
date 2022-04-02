INSERT INTO `shop_db_1`.`products` (title, price) VALUE
('IPHONE X', 100), ('MACBOOK', 250), ('AIRPODS', 30), ('APPLE WATCH', 50), ('IMAC', 450);
GO

INSERT INTO `shop_db_1`.`roles` (name)
values
    ('ROLE_USER'),
    ('ROLE_ADMIN');
GO

INSERT INTO `shop_db_1`.`users` (username, password)
values ('bob', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i'),
       ('john', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i');
GO
insert into `shop_db_1`.`users_roles` (user_id, role_id)
values (1, 1),
       (2, 2);
GO
