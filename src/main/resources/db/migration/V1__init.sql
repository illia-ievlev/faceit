create table if not exists roles
(
    id   bigint      not null primary key auto_increment unique,
    name varchar(30) not null unique
);

create table if not exists users
(
    id       bigint primary key not null auto_increment unique,
    username varchar(30)        not null unique,
    password varchar(300)       not null
);

create table if not exists orders
(
    id          bigint primary key not null auto_increment unique,
    user_id     bigint             not null,
    total_price decimal            not null,
    foreign key (user_id) references users (id)
);

create table if not exists cuisine
(
    id   bigint primary key not null auto_increment unique,
    name varchar(300)       not null unique
);

create table if not exists meals
(
    id         bigint primary key                not null auto_increment unique,
    name       varchar(300)                      not null unique,
    price      decimal                           not null,
    cuisine_id bigint                            not null,
    meal_type  enum ('main', 'dessert', 'drink') not null,
    foreign key (cuisine_id) references cuisine (id)
);

create table if not exists ordered_meal
(
    id        bigint primary key not null auto_increment unique,
    order_id  bigint             not null,
    meal_id   bigint             not null,
    add_ice   boolean            not null,
    add_lemon boolean            not null,
    foreign key (order_id) references orders (id),
    foreign key (meal_id) references meals (id)
);

CREATE TABLE if not exists users_roles
(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users (username, password)
values ('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i'),
       ('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i');

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2);

insert into cuisine(name)
values ('polish'),
       ('mexican'),
       ('italian');