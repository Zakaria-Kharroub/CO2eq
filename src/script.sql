-- CREATE TABLE users (
--         id SERIAL PRIMARY KEY,
--         name VARCHAR(255) NOT NULL,
--         age INT NOT NULL
--
-- );

-- create table consomations(
--     id serial primary key,
--     quantity int not null,
--     date_debut date not null,
--     date_fin date not null,
--     user_id int not null,
--     foreign key (user_id) references users(id)
-- );



-- insert into users(name, age) values('zakaria', 20), ('mohamed', 25), ('ali', 30);

-- select * from users;

-- insert into consomations(quantity,date_debut, date_fin, user_id) values(22,'2021-01-01', '2021-01-10', 1), (30,'2021-02-01', '2021-02-10', 2), (40,'2021-03-01', '2021-03-10', 1);

-- select * from consomations;

-- afficher name de user et leur consomations
-- select users.name, consomations.date_debut, consomations.date_fin from users join consomations on users.id = consomations.user_id;

-- SELECT users.name,consomations.date_debut,consomations.date_fin
-- FROM users JOIN consomations ON users.id = consomations.user_id
-- order by users.name;





/*---------------------- creation de table consomation---------------------------*/
-- create TYPE type_consomations as enum (
--     'Transport',
--     'Alimentation',
--     'Logement'
--     );
-- create table consomations(
--
--                              id serial primary key,
--                              quantity int ,
--                              date_debut date,
--                              date_fin date,
--                              type type_consomations,
--                              user_id int,
--                              foreign key (user_id) references users(id)
--
--
-- )

-- create table transports(
--                            type_vehicule varchar(250),
--                            distancep_parcourue FLOAT
-- )inherits (consomations);
--


-- CREATE TABLE alimentations (
--                               type_alimentation type_aliment,
--                               poids FLOAT
-- )inherits (consomations);
--
-- CREATE TYPE type_aliment AS ENUM ('Legume', 'Viande');


-- create table  longements (
--     type_energie type_energie,
--     consomation_energie FLOAT
--
--
-- )inherits (consomations);
--
-- CREATE TYPE type_energie AS ENUM ('Electricite','Gaz');










------------------------------------------------------------------- new SQL

-- -- CREATE TABLE users (
-- --         id SERIAL PRIMARY KEY,
-- --         name VARCHAR(255) NOT NULL,
-- --         age INT NOT NULL
-- --
-- -- );
-- create table consomations(
--
--                              id serial primary key not null ,
--                              quantity int not null,
--                              date_debut date not null,
--                              date_fin date not null,
--                              type_consomations varchar(50) check ( type_consomations IN ( 'Transport', 'Logement', 'Aimentation')) NOT NULL ,
--                              user_id int not null,
--                              foreign key (user_id) references users(id)
--
--
-- );
-- create table transports(
--                            type_vehicule varchar(250) not null ,
--                            distancep_parcourue FLOAT not null
-- )inherits (consomations);
--
--
--
-- CREATE TABLE alimentations (
--                                type_aliment varchar(250) not null,
--                                poids FLOAT not null
-- )inherits (consomations);
--
--
-- create table  logements (
--     type_energie varchar(250) not null,
--     consomation_energie FLOAT not null
--
--
-- )inherits (consomations);











-- CREATE TABLE transports (
--                             id SERIAL PRIMARY KEY,
--                             quantity INT NOT NULL,
--                             date_debut DATE NOT NULL,
--                             date_fin DATE NOT NULL,
--                             type_consomations VARCHAR(50) NOT NULL, -- Ensure this column is correctly defined
--                             type_vehicule VARCHAR(50),
--                             distancep_parcourue DOUBLE PRECISION
-- );





-- new


-- Table de base Consommation
CREATE TABLE consommations (
                               id SERIAL PRIMARY KEY,
                               quantite DOUBLE PRECISION NOT NULL,
                               date_debut DATE NOT NULL,
                               date_fin DATE NOT NULL,
                               user_id INT NOT NULL REFERENCES users(id),
                               type_consomation VARCHAR(50) NOT NULL
);

-- Table Transport hérite de Consommation
CREATE TABLE transports (
                            type_vehicule VARCHAR(250),
                            distance_parcourue FLOAT
) INHERITS (consommations);

-- Table Logement hérite de Consommation
CREATE TABLE logements (
                           consommation_energie FLOAT,
                           type_energie VARCHAR(50)
) INHERITS (consommations);

-- Table Alimentation hérite de Consommation
CREATE TABLE alimentations (
                               type_aliment VARCHAR(50),
                               poids FLOAT
) INHERITS (consommations);










-- ===================================
-- =============================== final result
-- Table de base Consomation
-- CREATE TABLE consomations (
--                               id SERIAL PRIMARY KEY,
--                               quantite DOUBLE PRECISION NOT NULL,
--                               date_debut DATE NOT NULL,
--                               date_fin DATE NOT NULL,
--                               user_id INT NOT NULL REFERENCES users(id),
--                               type_consomation VARCHAR(50) NOT NULL
-- );
--
-- -- Table Transport hérite de Consomation
-- CREATE TABLE transports (
--                             type_vehicule VARCHAR(250),
--                             distance_parcourue double precision
-- ) INHERITS (consomations);
--
-- -- Table Logement hérite de Consomation
-- CREATE TABLE logements (
--                            type_energie VARCHAR(50),
--                            consommation_energie double precision
--
-- ) INHERITS (consomations);
--
-- -- Table Alimentation hérite de Consomation
-- CREATE TABLE alimentations (
--                                type_aliment VARCHAR(50),
--                                poids double precision
-- ) INHERITS (consomations);





-- =============================================================================
create table users
(
    id   serial
        primary key,
    name varchar(255),
    age  integer
);

alter table users
    owner to "GreenPulse";

create table consommations
(
    id               serial
        primary key,
    quantite         double precision not null,
    date_debut       date             not null,
    date_fin         date             not null,
    user_id          integer          not null
        references users,
    type_consomation varchar(50)      not null
);

alter table consommations
    owner to "GreenPulse";

create table transports
(
    type_vehicule      varchar(250),
    distance_parcourue double precision
)
    inherits (consommations);

alter table transports
    owner to "GreenPulse";

create table logements
(
    consommation_energie double precision,
    type_energie         varchar(50)
)
    inherits (consommations);

alter table logements
    owner to "GreenPulse";

create table alimentations
(
    type_aliment varchar(50),
    poids        double precision
)
    inherits (consommations);

alter table alimentations
    owner to "GreenPulse";














