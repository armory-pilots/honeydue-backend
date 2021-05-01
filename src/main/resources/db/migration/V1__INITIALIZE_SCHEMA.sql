CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE users
(
    "id"            serial8 PRIMARY KEY,
    "nickname"      text NOT NULL,
    "email_address" text NOT NULL,
    "last_name"     varchar(255),
    "first_name"    varchar(255),
    "password"      bytea NOT NULL
);

CREATE TABLE chores
(
    "id"          serial8,
    "user_id"     int8         NOT NULL,
    "title"       varchar(255) NOT NULL,
    "description" text         NOT NULL,
    "due_date"    date         NOT NULL DEFAULT CURRENT_DATE,
    PRIMARY KEY ("id"),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

INSERT INTO users ( nickname, email_address, last_name, first_name, "password" )
VALUES
( 'jediknight', 'skywalker@honeydue.cloud', 'skywalker', 'luke', pgp_sym_encrypt ( 'P@ssw0rd', 'wandering-firefly-4506' ) );

INSERT INTO users ( nickname, email_address, last_name, first_name, "password" )
VALUES
( 'ladyjedi', 'leia@honeydue.cloud', 'leia', 'princess', pgp_sym_encrypt ( 'P@ssw0rd', 'wandering-firefly-4506' ) );

INSERT INTO users ( nickname, email_address, last_name, first_name, "password" )
VALUES
( 'darthvader', 'vader@honeydue.cloud', 'darth', 'vader', pgp_sym_encrypt ( 'P@ssw0rd', 'wandering-firefly-4506' ) );