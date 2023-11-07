create database animals;

\c animals

create table animals (
    id serial primary key,
    name varchar(50),
    weight integer
);

insert into animals (name, weight) VALUES ('lion', '400');
insert into animals (name, weight) VALUES ('bear', '500');
insert into animals (name, weight) VALUES ('wolf', '300');

begin transaction isolation level serializable;

select sum(weight) from animals;

update animals set weight = 350 where name = 'lion';

select sum(weight) from animals;

update animals set weight = 250 where name = 'wolf';

commit;