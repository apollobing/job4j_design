create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('iPhone', 50000), ('iPad', 40000), ('Macbook', 100000);
insert into devices(name, price) values ('Nokia 3310', 700), ('MiPad', 15000), ('Asus PC', 23400);
insert into devices(name, price) values ('Pixel 7', 42300), ('Buds', 5500), ('GoPro 2', 4900);
insert into devices(name, price) values ('Headphones', 1000), ('Watch', 1500), ('Razer v3', 2000);
insert into people(name) values ('Bob'), ('Lesly'), ('Tom'), ('Kate');
insert into devices_people(device_id, people_id) values (1, 2), (2, 1), (3, 3), (10, 4);
insert into devices_people(device_id, people_id) values (4, 3), (5, 1), (6, 2), (11, 4);
insert into devices_people(device_id, people_id) values (7, 2), (8, 3), (9, 1), (12, 4);

select avg(price) "AVG Price" from devices;

select p.name Name, avg(d.price) "AVG Price"
from devices_people as dp
join people p
on dp.people_id = p.id
join devices d
on dp.device_id = d.id
group by p.name
order by avg(d.price) desc;

select p.name Name, avg(d.price) "AVG Price"
from devices_people as dp
join people p
on dp.people_id = p.id
join devices d
on dp.device_id = d.id
group by p.name
having avg(d.price) > 5000;