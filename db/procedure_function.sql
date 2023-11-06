create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 8, 115);
insert into products (name, producer, count, price) VALUES ('product_2', 'producer_2', 0, 50);
insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 15, 269);
insert into products (name, producer, count, price) VALUES ('product_4', 'producer_4', 3, 182);
insert into products (name, producer, count, price) VALUES ('product_5', 'producer_5', 64, 256);

create or replace procedure delete_data(p_count boolean, p_id integer)
language 'plpgsql'
as $$
    BEGIN
        if p_count = true then
            delete from products where count = 0;
        end if;
        if p_id > 0 then
            delete from products where id = p_id;
        end if;
    END;
$$;

call delete_data(true, 0);
call delete_data(false, 3);
select * from products;

create or replace function f_delete_data(p_name varchar(50), p_producer varchar(50))
returns integer
language 'plpgsql'
as
$$
    declare
        result integer;
    begin
        if p_name is not null THEN
            select into result id from products where name = p_name;
            delete from products where name = p_name;
        end if;
        if p_producer is not null THEN
            delete from products where producer = p_producer;
            select into result avg(price) from products;
        end if;
        return result;
    end;
$$;

select f_delete_data('product_1', null);
select f_delete_data(null, 'producer_5');
select * from products;