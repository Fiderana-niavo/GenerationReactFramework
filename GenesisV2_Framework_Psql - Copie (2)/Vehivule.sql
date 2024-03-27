create database vehiculegeneration;
\c vehiculegeneration;

create table marque(
    idMarque serial primary key,
    nomMarque varchar(50)
);
create table voiture(
    idVoiture  serial primary key,
    idMarque int references marque(idMarque),
    nomVoiture varchar(50),
    dataSortie date
);