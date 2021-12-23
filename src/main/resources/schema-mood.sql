CREATE IF NOT EXISTS DATABASE mood ENCODING 'UTF-8';

insert into roles (id, title) values (1, 'ROLE_ADMIN');

insert into roles (id, title) values (2, 'ROLE_EDITOR');

insert into roles (id, title) values (3, 'ROLE_MODERATOR');

insert into roles (id, title) values (4, 'ROLE_USER');

insert into localisation (id, latitude, longitude) values(1, '48.789', '2.789');

insert into categories (id, title, description) values (1, 'beer', 'ambiance posée');

insert into users (id, name, firstname, birthdate,email, password, phone, image_id, localisation_id, mood_id, role_id) values (1, 'toto', 'titi', '2001-01-08', 'toto@gmail.com', '1234', '0625252525', null,1, 1, 1);

INSERT INTO images (id, data64, data_image64, data_name, mine_type) VALUES
(1, , , 'default_image.png', 'image/png');

