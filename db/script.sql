create table movies (
    id serial primary key,
    nameOfMovie varchar(255),
    releaseDate date,
    oscar bool
);

select * from movies;

insert into movies(nameOfMovie, releaseDate, oscar) values('Shawshank Redemption', '1994-09-10', 'true');

select * from movies;

update movies set nameOfMovie = 'The Shawshank Redemption';

select * from movies;

delete from movies;

select * from movies;