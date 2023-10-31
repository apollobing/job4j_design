create table customer(
    id serial primary key,
    name varchar(255)
);

create table orders(
    id serial primary key,
    product varchar(255),
    customer_id int references customer(id)
);

insert into customer(name) values ('John');
insert into orders(product, customer_id) VALUES ('Bread', 1);

select * from orders;

select * from customer where id in (select customer_id from orders);