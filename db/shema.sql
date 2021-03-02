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
    post_id    bigint references posts (id),
    discussion_id bigint references discussions (id),
    primary key (post_id, discussion_id)
);

create table users
(
    id       bigserial primary key,
    username varchar(255),
    password varchar(255)
);

insert into users (username, password) values ('user', '123'),('admin', '456');
insert into posts (name) values ('О чем этот форум?'), ('Правила форума.');
