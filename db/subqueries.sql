CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);

insert into customers (first_name, last_name, age, country) VALUES ('Bob', 'Johnes', 46, 'UK');
insert into customers (first_name, last_name, age, country) VALUES ('Lisa', 'Bloom', 24, 'Germany');
insert into customers (first_name, last_name, age, country) VALUES ('Mellisa', 'Harts', 33, 'USA');
insert into customers (first_name, last_name, age, country) VALUES ('Matt', 'Rasty', 37, 'Ireland');
insert into customers (first_name, last_name, age, country) VALUES ('Huan', 'Peres', 24, 'Spain');

select * from customers where age = (select min(age) from customers);

CREATE TABLE orders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);

insert into orders (amount, customer_id) VALUES (5, 2);
insert into orders (amount, customer_id) VALUES (17, 4);
insert into orders (amount, customer_id) VALUES (8, 5);

select * from customers where customers.id NOT IN (select customer_id from orders);