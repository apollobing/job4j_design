create table owner(
    id serial primary key,
    name varchar(255),
    address varchar(255)
);

create table pet(
    id serial primary key,
    name varchar(255),
    nickname varchar(255),
    owner_id int references owner(id)
);

insert into owner(name, address) values ('John', '4 Bolton str.');
insert into owner(name, address) values ('Bob', '312 Shelter str.');
insert into owner(name, address) values ('Linda', '67 Thompson str.');

insert into pet(name, nickname, owner_id) values ('Dog', 'Fluffy', 1);
insert into pet(name, nickname, owner_id) values ('Parrot', 'Pluto', 2);
insert into pet(name, nickname, owner_id) values ('Cat', 'Lilu', 3);
insert into pet(name, nickname, owner_id) values ('Dog', 'Mailo', 3);

select o.name, o.address, p.name, p.nickname
from owner as o join pet as p on o.id = p.owner_id;

select p.name Pet, p.nickname Name, o.address Home
from pet as p join owner as o on o.id = p.owner_id;

select o.name Owner, p.nickname Dog
from owner o join pet p on o.id = p.owner_id where p.name = 'Dog';