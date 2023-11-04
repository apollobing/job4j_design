create table departments(
    id serial primary key,
    name varchar(255)
);

create table employees(
    id serial primary key,
    name varchar(255),
    departments_id int references departments(id)
);

insert into departments(name) values ('IT');
insert into departments(name) values ('HR');
insert into departments(name) values ('Security');
insert into departments(name) values ('Finance');
insert into departments(name) values ('Marketing');
insert into departments(name) values ('Research');

insert into employees(name, departments_id) values ('John', 1);
insert into employees(name, departments_id) values ('Bob', 1);
insert into employees(name, departments_id) values ('Samanta', 2);
insert into employees(name, departments_id) values ('Lily', 2);
insert into employees(name, departments_id) values ('Tom', 3);
insert into employees(name, departments_id) values ('Lisa', 3);
insert into employees(name, departments_id) values ('Helen', 4);
insert into employees(name, departments_id) values ('Tim', 4);
insert into employees(name, departments_id) values ('Victor', 5);
insert into employees(name, departments_id) values ('Roberta', 5);

select * from departments d left join employees e on d.id = e.departments_id;
select * from departments d right join employees e on d.id = e.departments_id;
select * from departments d full join employees e on d.id = e.departments_id;
select * from departments d left join employees e on d.id = e.departments_id
union
select * from departments d right join employees e on d.id = e.departments_id;
select * from departments cross join employees;

select * from departments d left join employees e on d.id = e.departments_id where e.id is null;

select * from departments d left join employees e on d.id = e.departments_id;
select d.id, d.name, e.id, e.name, e.departments_id
from employees e right join departments d on d.id = e.departments_id;
select * from employees e left join departments d on d.id = e.departments_id;
select e.id, e.name, e.departments_id, d.id, d.name
from departments d right join employees e on d.id = e.departments_id;

create table teens(
    id serial primary key,
    name varchar(255),
    gender char
);

insert into teens(name, gender) values ('John', 'm');
insert into teens(name, gender) values ('Bob', 'm');
insert into teens(name, gender) values ('Samanta', 'f');
insert into teens(name, gender) values ('Lily', 'f');
insert into teens(name, gender) values ('Tom', 'm');
insert into teens(name, gender) values ('Lisa', 'f');
insert into teens(name, gender) values ('Helen', 'f');
insert into teens(name, gender) values ('Tim', 'm');
insert into teens(name, gender) values ('Victor', 'm');
insert into teens(name, gender) values ('Roberta', 'f');

select t1.name Male, t2.name Female from teens t1
cross join teens t2
where t1.gender > t2.gender;