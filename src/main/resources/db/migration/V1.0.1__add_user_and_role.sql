CREATE TABLE users (
                         id serial NOT NULL primary key,
                         username varchar(15),
                         password varchar(100),
                         email varchar (24),
                         enabled boolean default true
);

CREATE TABLE role (
                           id serial NOT NULL primary key ,
                           name varchar(25)
);

CREATE TABLE user_role (
                           user_id int NOT NULL,
                           role_id int NOT NULL,
                           primary key (user_id, role_id),
                           foreign key (user_id) references users (id),
                           foreign key (role_id) references role (id));

CREATE TABLE product (
                         id serial NOT NULL primary key,
                         title text not null,
                         cost int not null,
                         image text
);

insert into role (name) values ('ROLE_USER'),
                               ('ROLE_ADMIN');


INSERT INTO users (username, password, email)
VALUES
('alex', '$2a$12$.htJwHfX3/JV3H.OOLfAJOvf4rIlroFjafrR3DlR.zWk3UhxN/hVy', 'alex@mail.com');

insert into user_role (user_id, role_id)
values (1, 2);