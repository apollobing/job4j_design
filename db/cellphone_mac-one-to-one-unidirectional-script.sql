create table cellphone(
    id serial primary key,
    brand varchar(255),
    model varchar(255)
);

create table mac(
    id serial primary key,
    address varchar(255),
    cellphone_id int references cellphone(id) unique
);

insert into cellphone(brand, model) values ('Google', 'Pixel 7');
insert into mac(address, cellphone_id) VALUES ('F0:98:9D:1C:93:F6', 1);

select * from mac;

select * from cellphone where id in (select cellphone_id from mac);

insert into mac(address, cellphone_id) VALUES ('F1:38:3D:14:93:F6', 1);