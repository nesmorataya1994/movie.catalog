INSERT INTO roles (name) VALUES ('ADMIN'), ('USER');

INSERT INTO categories (name) VALUES ('ACTION'), ('ADVENTURE'), ('COMEDY'), ('DRAMA'), ('CRIME'), ('ROMANCE'), ('SCIFI'), ('THRILLER');

INSERT INTO users (firstname, lastname, password, email) VALUES ('admin','admin' '$2a$12$DUd1T/d9.q./NjtJlEYnGu5.Pdd7sOBatylzpCAlRN6azo6LTzzNm', 'admin@gmail.com');

INSERT  INTO users_roles (user_id, role_id) SELECT u.id, r.id FROM users u, roles r WHERE u.firstname = 'admin' AND r.name = 'ADMIN';

-- Insert movies
INSERT INTO movies (name, movie_synopsis, year_of_release, created_at) VALUES ('The Shawshank Redemption', 'Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.', 1994, CURRENT_TIMESTAMP);

INSERT INTO movies (name, movie_synopsis, year_of_release, created_at) VALUES ('Inception', 'A thief who steals corporate secrets through dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.', 2010, CURRENT_TIMESTAMP);

INSERT INTO movies (name, movie_synopsis, year_of_release, created_at) VALUES ('The Dark Knight', 'When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.', 2008, CURRENT_TIMESTAMP);

INSERT INTO movies (name, movie_synopsis, year_of_release, created_at) VALUES ('Pulp Fiction', 'The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.', 1994, CURRENT_TIMESTAMP);

INSERT INTO movies (name, movie_synopsis, year_of_release, created_at) VALUES ('The Matrix', 'A computer programmer discovers that reality as he knows it is a simulation created by machines, and joins a rebellion to break free.', 1999, CURRENT_TIMESTAMP);


INSERT INTO movies_categories (movie_id, category_id) SELECT m.id, c.id FROM movies m, categories c WHERE m.name = 'The Shawshank Redemption' AND c.name IN ('DRAMA');

-- For Inception
INSERT INTO movies_categories (movie_id, category_id) SELECT m.id, c.id FROM movies m, categories c WHERE m.name = 'Inception' AND c.name IN ('SCIFI', 'ACTION', 'THRILLER');

-- For The Dark Knight
INSERT INTO movies_categories (movie_id, category_id) SELECT m.id, c.id FROM movies m, categories c WHERE m.name = 'The Dark Knight' AND c.name IN ('ACTION', 'DRAMA', 'THRILLER');

-- For Pulp Fiction
INSERT INTO movies_categories (movie_id, category_id) SELECT m.id, c.id FROM movies m, categories c WHERE m.name = 'Pulp Fiction' AND c.name IN ('CRIME', 'DRAMA');

-- For The Matrix
INSERT INTO movies_categories (movie_id, category_id) SELECT m.id, c.id FROM movies m, categories c WHERE m.name = 'The Matrix' AND c.name IN ('SCIFI', 'ACTION');

