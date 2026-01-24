CREATE TABLE houses (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    house_code VARCHAR(50) NOT NULL,
    name VARCHAR(100) NOT NULL,
    location VARCHAR(255),
    deleted BOOLEAN NOT NULL DEFAULT FALSE,
    house_status VARCHAR(30) NOT NULL DEFAULT 'ACTIVE',
    created_by VARCHAR(100),
    created_date TIMESTAMP NOT NULL,
    updated_by VARCHAR(100),
    updated_date TIMESTAMP,
    version BIGINT,

     CONSTRAINT uk_house_name UNIQUE (name),
     CONSTRAINT uk_house_code UNIQUE (house_code)
);

CREATE TABLE rooms (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    -- soft delete
    deleted BOOLEAN NOT NULL DEFAULT FALSE,
    -- auditing
    created_by VARCHAR(255),
    created_date TIMESTAMP NOT NULL,
    updated_by VARCHAR(255),
    updated_date TIMESTAMP,
    -- optimistic locking
    version BIGINT,
    -- room fields
    room_number VARCHAR(100) NOT NULL,
    room_type VARCHAR(50) NOT NULL,
    room_code VARCHAR(100) NOT NULL,
    room_status VARCHAR(50) NOT NULL DEFAULT 'AVAILABLE',
    capacity INTEGER NOT NULL,
    remark TEXT,
    -- relationship
    house_id UUID NOT NULL,
    CONSTRAINT uk_room_code UNIQUE (room_code),
    CONSTRAINT fk_rooms_house
    FOREIGN KEY (house_id)
    REFERENCES houses(id)
);
