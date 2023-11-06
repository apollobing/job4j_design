create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

insert into products (name, producer, count, price) values ('product_3', 'producer_3', 8, 115);
insert into products (name, producer, count, price) values ('product_1', 'producer_1', 3, 50);
select * from products;

create or replace function add_tax_s()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.13
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger add_tax_s_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure add_tax_s();

insert into products (name, producer, count, price) values ('product_2', 'producer_2', 2, 20);
select * from products;

create or replace function add_tax_r()
    returns trigger as
$$
    BEGIN
        new.price = new.price + new.price * 0.2;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger add_tax_r_trigger
    before insert
    on products
    for each row
    execute procedure add_tax_r();

insert into products (name, producer, count, price) values ('product_4', 'producer_4', 4, 40);
select * from products;

create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create or replace function price_history()
    returns trigger as
$$
    BEGIN
        insert into history_of_price (name, price, date)
        values (new.name, new.price, current_timestamp);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger price_history_trigger
    after insert
    on products
    for each row
    execute procedure price_history();

insert into products (name, producer, count, price) values ('product_5', 'producer_5', 5, 55);
select * from history_of_price;