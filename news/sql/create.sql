CREATE TABLE users1
(
    id       BIGINT  NOT NULL GENERATED ALWAYS AS IDENTITY,
    name     VARCHAR NOT NULL,
    password VARCHAR NOT NULL,
    age      INTEGER NOT NULL,
    email    VARCHAR NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE news1
(
    id        BIGINT  NOT NULL,
    title     VARCHAR NOT NULL,
    text      VARCHAR NOT NULL,
    date      VARCHAR NOT NULL,
    views     INTEGER NOT NULL,
    author_id BIGINT  NOT NULL,
    FOREIGN KEY (author_id) REFERENCES users1 (id),
    PRIMARY KEY (id)
);
