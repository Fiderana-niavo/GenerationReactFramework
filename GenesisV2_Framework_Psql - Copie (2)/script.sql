create database vehicules;
\c vehicule

create sequence seqMarque;
create table Marque(
    idMarque varchar(20) default concat('MAR' || nextval('seqMarque')) primary key,
    nomMarque varchar(30),
    dateSortie date
);

insert into Marque(nomMarque,dateSortie) VALUES ('peugot','2024-02-29');

create sequence seqVoiture;
create table voiture(
    idVoiture varchar(20) default concat('VOI' || nextval('seqVoiture')) primary key,
    designation varchar(30),
    idMarque varchar(20) references Marque(idMarque)
);

insert into voiture