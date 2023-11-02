create table type(
    id serial primary key,
    name varchar(255)
);

create table product(
    id serial primary key,
    name varchar(255),
    type_id int references type(id),
    expired_date date,
    price float
);

insert into type(name) values ('Сыр');
insert into type(name) values ('Молоко');
insert into type(name) values ('Мороженое');
insert into type(name) values ('Яблоки');
insert into type(name) values ('Колбаса');

insert into product(name, type_id, expired_date, price)
values ('Сыр творожный', 1, '2023-12-03', 129.99);
insert into product(name, type_id, expired_date, price)
values ('Сыр Маасдам', 1, '2023-12-05', 719.99);
insert into product(name, type_id, expired_date, price)
values ('Сыр плавленный', 1, '2023-12-06', 69.99);
insert into product(name, type_id, expired_date, price)
values ('Сыр Гауда', 1, '2023-06-11', 509.99);

insert into product(name, type_id, expired_date, price)
values ('Молоко Простоквашино', 2, '2023-12-04', 89.99);
insert into product(name, type_id, expired_date, price)
values ('Молоко Домик в деревне', 2, '2023-12-07', 79.99);
insert into product(name, type_id, expired_date, price)
values ('Молоко Растишка', 2, '2023-12-08', 69.99);

insert into product(name, type_id, expired_date, price)
values ('Мороженое Коровка', 3, '2023-12-09', 59.99);
insert into product(name, type_id, expired_date, price)
values ('Мороженое Натурпломбир', 3, '2023-12-10', 49.99);
insert into product(name, type_id, expired_date, price)
values ('Мороженое 48 копеек', 3, '2023-12-11', 79.99);

insert into product(name, type_id, expired_date, price)
values ('Яблоки Голден', 4, '2024-02-23', 129.99);
insert into product(name, type_id, expired_date, price)
values ('Яблоки Гренни', 4, '2024-02-24', 109.99);
insert into product(name, type_id, expired_date, price)
values ('Яблоки сезонные', 4, '2024-02-25', 119.99);

insert into product(name, type_id, expired_date, price)
values ('Колбаса вареная', 5, '2023-11-01', 79.99);
insert into product(name, type_id, expired_date, price)
values ('Колбаса копченая', 5, '2023-09-27', 139.99);
insert into product(name, type_id, expired_date, price)
values ('Колбаса докторская', 5, '2023-12-16', 159.99);

select t.name Тип, p.name Продукт
from type t
join product p on t.id = p.type_id
where t.name = 'Сыр';

select * from product where name like '%Мороженое%';

select * from product p where current_date > p.expired_date;

select t.name Тип, max(p.price) "Самый дорогой продукт"
from type t
join product p on t.id = p.type_id
group by t.name;

select t.name Тип, count(*) Количество
from type t
join product p on t.id = p.type_id
group by t.name;

select t.name Тип, p.name Продукт
from type t
join product p on t.id = p.type_id
where t.name = 'Сыр' or t.name = 'Молоко'
order by t.name asc;

select t.name Тип, count(*) Количество
from type t
join product p on t.id = p.type_id
group by t.name
having count(*) < 4;

select p.name Продукт, t.name Тип
from product p
join type t on p.type_id = t.id
order by t.name asc;