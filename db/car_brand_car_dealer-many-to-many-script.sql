create table car_brand(
     id serial primary key,
     name varchar(255)
 );
 
 create table car_dealer(
     id serial primary key,
     name varchar(255)
 );
 
 create table brand_dealer(
     id serial primary key,
     car_brand_id int references car_brand(id),
     car_dealer_id int references car_dealer(id)
 );

insert into car_brand(name) values ('BMW');
insert into car_brand(name) values ('Audi');
insert into car_brand(name) values ('Volvo');

insert into car_dealer(name) values ('Wester cars');
insert into car_dealer(name) values ('Bod & Tom');
insert into car_dealer(name) values ('Spirit');

insert into brand_dealer(car_brand_id, car_dealer_id) values (1, 1);
insert into brand_dealer(car_brand_id, car_dealer_id) values (1, 2);
insert into brand_dealer(car_brand_id, car_dealer_id) values (1, 3);
insert into brand_dealer(car_brand_id, car_dealer_id) values (2, 1);
insert into brand_dealer(car_brand_id, car_dealer_id) values (2, 2);
insert into brand_dealer(car_brand_id, car_dealer_id) values (3, 3);

select * from car_dealer where id in (select car_brand_id from brand_dealer);