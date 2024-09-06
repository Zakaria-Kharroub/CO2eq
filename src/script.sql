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

SELECT users.name,consomations.date_debut,consomations.date_fin
FROM users JOIN consomations ON users.id = consomations.user_id
order by users.name;



-- drop table consomations


