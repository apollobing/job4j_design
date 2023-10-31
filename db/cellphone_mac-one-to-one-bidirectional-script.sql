create table cellphone(
    id serial primary key,
    brand varchar(255),
    model varchar(255)
);

create table mac(
    id serial primary key,
    address varchar(255)
);

create table cellphone_mac(
    id serial primary key,
    cellphone_id int references cellphone(id) unique,
    mac_id int references mac(id) unique
);

insert into cellphone(brand, model) values ('Google', 'Pixel 7');
insert into mac(address) VALUES ('F0:98:9D:1C:93:F6');
insert into cellphone_mac(cellphone_id, mac_id) values (1, 1);
    

select * from mac;

select * from cellphone where id in (select cellphone_id from cellphone_mac);

insert into cellphone_mac(cellphone_id, mac_id) values (1, 1);