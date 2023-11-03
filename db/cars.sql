create table car_bodies(
    id serial primary key,
    name varchar(255)
);

create table car_engines(
    id serial primary key,
    name varchar(255)
);

create table car_transmissions(
    id serial primary key,
    name varchar(255)
);

create table cars(
    id serial primary key,
    name varchar(255),
    body_id int references car_bodies(id),
    engine_id int references car_engines(id),
    transmission_id int references car_transmissions(id)
);

insert into car_bodies(name) values ('Hatchback');
insert into car_bodies(name) values ('Crossover');
insert into car_bodies(name) values ('SUV');
insert into car_bodies(name) values ('Cabriolet');
insert into car_bodies(name) values ('Sedan');
insert into car_engines(name) values ('V2');
insert into car_engines(name) values ('V4');
insert into car_engines(name) values ('V6');
insert into car_engines(name) values ('V8');
insert into car_engines(name) values ('V12');
insert into car_transmissions(name) values ('iMT');
insert into car_transmissions(name) values ('AMT');
insert into car_transmissions(name) values ('CVT');
insert into car_transmissions(name) values ('Torque Converter');
insert into car_transmissions(name) values ('DCT');
insert into cars(name, body_id, engine_id, transmission_id) values ('BMW', 1, 1, 1);
insert into cars(name, body_id, engine_id, transmission_id) values ('Audi', 2, 2, 2);
insert into cars(name, body_id, engine_id, transmission_id) values ('Nissan', 3, 3, 3);
insert into cars(name, body_id) values ('Toyota', 4);

select c.id, c.name Brand, cb.name Body, ce.name Engine, ct.name Transmission from cars c
left join car_bodies cb on cb.id = c.body_id
left join car_engines ce on ce.id = c.engine_id
left join car_transmissions ct on ct.id = c.transmission_id;

select cb.id, cb.name Body from car_bodies cb
left join cars c on cb.id = c.body_id where c.body_id is null;

select ce.id, ce.name Engine from car_engines ce
left join cars c on ce.id = c.engine_id where c.engine_id is null;

select ct.id, ct.name Transmission from car_transmissions ct
left join cars c on ct.id = c.engine_id where c.engine_id is null;