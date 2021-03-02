create table discussions
(
    id          bigserial primary key,
    description varchar(255),
    created     timestamp without time zone not null default now()
);

create table posts
(
    id      bigserial primary key,
    name    varchar(255),
    created timestamp without time zone not null default now()
);

create table posts_discussions
(
    post_id       bigint references posts (id),
    discussion_id bigint references discussions (id),
    primary key (post_id, discussion_id)
);

create table authorities
(
    id        serial primary key,
    authority varchar(50) not null unique
);

create table users
(
    id           serial primary key,
    username     varchar(50)  not null unique,
    password     varchar(100) not null,
    enabled      boolean default true,
    authority_id int          not null references authorities (id)
);

insert into authorities (authority) values ('role_admin'),('role_user');

insert into users (username, password, enabled, authority_id)
values ('user', '$2a$10$xjFKOGq8dHM2tt3pHZTgQOaEjMp5iNGed5q5uEKmYKx0e2ZNUMEy.', true,
        (select id from authorities where authority = 'role_admin')
        );

insert into posts (name)
values ('о чем этот форум?'),
       ('правила форума.');
