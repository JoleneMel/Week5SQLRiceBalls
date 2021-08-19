create database if not exists riceballs;

use riceballs;

drop table if exists extraIngredients; 
drop table if exists riceballs; 

-- creating the riceballs first 
-- maybe add enums for sizes of rice ball???
create table riceballs (
id int(10) not null auto_increment, 
name varchar(50) not null, 
primary key(id)
);

create table extraIngredients (
id int(10) not null auto_increment, 
ingredient varchar(25) not null, 
riceball_id int (10) not null, 
primary key(id),
foreign key(riceball_id) references riceballs(id)
);

-- insert into riceballs(name) values("Cool Riceball");