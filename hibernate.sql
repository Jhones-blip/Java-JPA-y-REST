create database hibernate;
use hibernate;

create table vehiculos(
id int primary key auto_increment not null,
numSerie int(10) not null,
marcaVehiculo varchar(50) not null,
placaVehiculo varchar(10) not null,
numSerieMotor varchar(50) not null
);

insert into vehiculos values ('',325415476,'Ferrari','ABC3258','FS8200');//

drop database hibernate;