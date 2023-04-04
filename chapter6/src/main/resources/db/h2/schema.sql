drop table if exists employee;

create table employee (
    id serial not null,
    name varchar not null,
    primary key (id)
);