create table departement(
    idDepartement serial primary key,
    nomdepartement varchar(30)
);
create table employe(
    idEmploye serial primary key,
    nomEmploye varchar(30) ,
    dateNaissance date,
    idDepartement int references departement(idDepartement)
);

create table poste(
    idPoste serial primary key,
    nomPoste varchar(30)
);

create table entreprise(
    idEntreprise serial primary key,
    nomEntreprise varchar(30)
);

create table conge(
    idConge serial primary key,
    duree int
);