create table if not exists _user(
    id int generated always as identity primary key
    , email VARCHAR(256)
    , login varchar(256)
    , name varchar(512)
    , birthday DATE
);
create table if not exists mpa
(
    id int primary key
    , name varchar(256)
);
create table if not exists film
(
    id int generated always as identity primary key
    , name varchar(256)
    , description varchar(200)
    , release_date DATE
    , duration int
    , rate int
    , mpa int
    , foreign key (mpa) references mpa(id)
);
create table if not exists user_friend
(
    user_id int
    , related_user_id int
    , primary key (user_id, related_user_id)
    , foreign key (user_id) references _user(id)
    , foreign key (related_user_id) references  _user(id)
);
create table if not exists user_like_film
(
    user_id int
    , film_id int
    , primary key (user_id, film_id)
    , foreign key (user_id) references _user(id)
    , foreign key (film_id) references film(id)
);
create table if not exists genre
(
    id int primary key
    , name varchar(256)
);
create table if not exists film_genre
(
    film_id int
    , genre_id int
    , primary key (film_id, genre_id)
    , foreign key (film_id) references film(id)
    , foreign key (genre_id) references genre(id)
);

