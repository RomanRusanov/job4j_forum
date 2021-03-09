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
