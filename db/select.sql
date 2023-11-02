create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date)
values ('Barracuda fish', 1800, '1930-02-15');
insert into fauna(name, avg_age, discovery_date)
values ('Angelfish', 1400, '1960-04-07');
insert into fauna(name, avg_age, discovery_date)
values ('Clownfish', 1100, '1970-11-23');
insert into fauna(name, avg_age, discovery_date)
values ('Bear', 10000, '1017-02-15');
insert into fauna(name, avg_age, discovery_date)
values ('Wolf', 14000, '912-02-15');
insert into fauna(name, avg_age, discovery_date)
values ('Worm', 300, null);
insert into fauna(name, avg_age, discovery_date)
values ('Eagle', 22000, null);

select * from fauna where name like '%fish%';
select * from fauna where avg_age >= 10000 and avg_age < 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01' order by discovery_date asc;