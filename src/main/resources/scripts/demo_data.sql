insert into "pizza" (id, name, price)
values (1, 'Pepperoni', '670'),
       (2, 'Black burger', '750'),
       (3, 'Margarita', '500'),
       (4, 'Pesto', '720'),
       (5, 'Neptune', '870'),
       (6, 'Meat Royal', '860'),
       (7, 'Malevich', '950'),
       (8, 'Chicken pizza', '670'),
       (9, 'Mushroom pizza', '730'),
       (10, 'President', '740');

insert into "customer" (id, name, phone, email)
values (1, 'Анастасия', '+71234567890', 'anastasia@ya.ru'),
       (2, 'Алексей', '+70987654321', 'aleksei@gmail.com'),
       (3, 'Александр', '+79998887777', 'aleksandr@yandex.ru'),
       (4, 'Анна', '+78887776666', 'anna@gmail.com'),
       (5, 'Павел', '+76665554444', 'pavel@yandex.ru'),
       (6, 'Юлия', '+75554443322', 'julia@ya.ru');

insert into public.delivery (id, type, cost, minimal_cart_for_free_delivery)
values (1, 'courier', 200, 1500),
       (2, 'self-pickup', 0, 0);

insert into "order" (id, customer_id, delivery_address, delivery_cost, created_timestamp, phone, comment)
values (1, 1, 'б-р Мира, 7-12', 200, now() - interval '2 day', '+71234567890', 'Позвонить от подъезда'),
       (2, 2, 'б-р Мира, 7-20', 200, now() - interval '3 day', '+70987654321',
        'Чаевые не даю, не плюйте в пиццу, пожалуйста'),
       (3, 5, 'б-р Мира, 7-12', 200, now() - interval '1 day', '+76665554444', 'Позвонить от подъезда'),
       (4, 3, 'пл. Ленина, 4-42', 200, now() - interval '1 day', '+79998887777', null),
       (5, 6, 'пл. Минина, 1-15', 200, now() - interval '2 day', '+75554443322', null),
       (6, 4, 'ул. Котова, 12-2', 200, now() - interval '3 day', '+78887776666', null),
       (7, 2, 'б-р Мира, 7-12', 200, now() - interval '3 day', '+70987654321', 'Позвонить от подъезда'),
       (8, 6, 'ул. Нестерова, 9', 200, now() - interval '4 day', '+75554443322', null),
       (9, 5, 'ул. Родионова, 15-70', 200, now() - interval '3 day', '+76665554444', null),
       (10, 1, 'ул. Иванова, 14-5', 200, now() - interval '2 day', '+71234567890', 'Уйду гулять - звоните за 15 мин.'),
       (11, 6, 'пр-т Ленина, 17-82', 200, now() - interval '1 day', '+75554443322', null),
       (12, 2, 'пр-т Ленина, 14-49', 200, now() - interval '4 day', '+70987654321', null);

insert into public.order_position (id, order_id, pizza_id, quantity, price)
values (1, 1, 1, 2, 670),
       (2, 1, 2, 1, 750),
       (3, 1, 3, 1, 500),
       (4, 3, 3, 2, 500),
       (5, 4, 4, 1, 720),
       (6, 5, 5, 2, 870),
       (7, 6, 6, 1, 860),
       (8, 7, 1, 3, 670),
       (9, 8, 2, 2, 750),
       (10, 9, 3, 4, 500),
       (11, 10, 1, 2, 670),
       (12, 11, 1, 2, 670),
       (13, 12, 1, 2, 670),
       (14, 1, 4, 1, 720),
       (15, 2, 9, 1, 730),
       (16, 3, 8, 1, 670),
       (17, 4, 8, 1, 670),
       (18, 5, 9, 1, 730),
       (19, 6, 8, 1, 670),
       (20, 7, 9, 1, 730);

insert into public.role (id, title, description)
values (1, 'MANAGER', 'To manage'),
       (2, 'BOSS', 'To control');

insert into public.users (id, login, password, name, role_id)
values (1, 'manager', '$2a$12$jP7WI5umnmh4ZjtAbGpNseHZcyPOIGAZ0WTFhR8wP8U.EONu2DBqS', 'Ivan', 1),
       (2, 'director', '$2a$12$QAO5eEYa3xCG3SwVZ61FKOGroZ5l3XCXXAIG8hMqfQRnDGroXBPbm', 'Stepan', 2);

select pg_catalog.setval('pizza_id_seq', 10, true);
select pg_catalog.setval('order_id_seq', 12, true);
select pg_catalog.setval('order_position_id_seq', 20, true);
select pg_catalog.setval('role_id_seq', 2, true);
select pg_catalog.setval('users_id_seq', 2, true);
select pg_catalog.setval('customer_id_seq', 6, true);
select pg_catalog.setval('delivery_id_seq', 2, true);
