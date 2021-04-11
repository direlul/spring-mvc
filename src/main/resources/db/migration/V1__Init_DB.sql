create sequence hibernate_sequence start 1 increment 1;

create table messages (
    id int8 not null,
    filename varchar(255),
    tag varchar(255),
    text varchar(255),
    user_id int8,
    primary key (id)
);

create table user_roles (
    user_id int8 not null,
    roles varchar(255)
);
create table users (
    id int8 not null,
    activation_code varchar(255),
    email varchar(255),
    is_active boolean not null,
    password varchar(255),
    username varchar(255),
    primary key (id)
);
alter table if exists messages
    add constraint messages_users_fk
    foreign key (user_id) references users;
alter table if exists user_roles
    add constraint users_roles_fk
    foreign key (user_id) references users;