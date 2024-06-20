create table messages(
    id UUID,
    message TEXT,
    creation_time TIMESTAMP,
    sender_id UUID,
    receiver_id UUID,
    is_seen BOOLEAN,

    PRIMARY KEY(id),
    FOREIGN KEY(sender_id) REFERENCES users(id),
    FOREIGN KEY(receiver_id) REFERENCES users(id)

)