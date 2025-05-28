drop table if exists avenger;

create table avenger (
    id serial not null primary key,
    name varchar(255) not null,
    civilname varchar(255) not null,
    snapped boolean default false
    );

insert into avenger (id, name, civilname, snapped) values (nextval('Avenger_SEQ'), 'Iron Man', 'Tony Stark', false);
insert into avenger (id, name, civilname, snapped) values (nextval('Avenger_SEQ'), 'Captain America', 'Steve Rogers', false);
insert into avenger (id, name, civilname, snapped) values (nextval('Avenger_SEQ'), 'Black Widow', 'Natasha Romanoff', false);
