insert into authorities (authority) values ('ROLE_ADMIN'),('ROLE_USER');
-- login: user pass: 123
insert into users (username, password, enabled, authority_id)
values ('user', '$2a$10$xjFKOGq8dHM2tt3pHZTgQOaEjMp5iNGed5q5uEKmYKx0e2ZNUMEy.', true,
        (select id from authorities where authority = 'ROLE_ADMIN')
       );

insert into posts (name)
values ('о чем этот форум?'),
       ('правила форума.');