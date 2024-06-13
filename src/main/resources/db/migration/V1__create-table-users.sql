CREATE TABLE users(

    id UUID,
    username VARCHAR(100) UNIQUE,
    email VARCHAR(100) UNIQUE,
    password VARCHAR(255),
    user_active BOOLEAN,

    primary key(id)
)