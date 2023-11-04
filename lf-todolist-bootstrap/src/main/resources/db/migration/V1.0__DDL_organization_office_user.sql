CREATE TABLE LF_USER
(
    uuid              BINARY(16) NOT NULL,
    username          VARCHAR(25) NULL,
    password          VARCHAR(100) NULL,
    first_name        VARCHAR(50) NULL,
    last_name         VARCHAR(50) NULL,
    date_of_birth     DATE NULL,
    national_id       VARCHAR(25) NULL,
    gender            VARCHAR(6) NULL,
    country_code      VARCHAR(10) NULL,
    mobile            VARCHAR(20) NULL,
    email             VARCHAR(50) NULL,
    address_1         VARCHAR(50) NULL,
    address_2         VARCHAR(50) NULL,
    address_3         VARCHAR(50) NULL,
    locked            TINYINT NULL,
    last_login        DATETIME NULL,
    number_of_retries INT NULL,
    role              VARCHAR(50) NULL,
    updated_by        VARCHAR(50) NULL,
    updated_at        DATETIME NULL,
    created_by        VARCHAR(50) NULL,
    created_at        DATETIME NULL,
    PRIMARY KEY (uuid)
);

CREATE TABLE LF_ROLE
(
    uuid              BINARY(16) NOT NULL,
    description       VARCHAR(100) NULL,
    role              VARCHAR(100) NOT NULL,
    updated_by        VARCHAR(50) NULL,
    updated_at        DATETIME NULL,
    created_by        VARCHAR(50) NULL,
    created_at        DATETIME NULL,
    PRIMARY KEY (uuid)
);

CREATE TABLE LF_TODO
(
    uuid              BINARY(16) NOT NULL,
    user_uuid         BINARY(16) NOT NULL,
    title             VARCHAR(100) NULL,
    goal              VARCHAR(100) NOT NULL,
    start_date        DATETIME NOT NULL,
    end_date          DATETIME NOT NULL,
    is_active         TINYINT NOT NULL DEFAULT 1,
    updated_by        VARCHAR(50) NULL,
    updated_at        DATETIME NULL,
    created_by        VARCHAR(50) NULL,
    created_at        DATETIME NULL,
    PRIMARY KEY (uuid),
    FOREIGN KEY (user_uuid) REFERENCES LF_USER (uuid) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE LF_TODO_ITEM
(
    uuid                BINARY(16) NOT NULL,
    todo_uuid           BINARY(16) NULL,
    item_name           VARCHAR(100) NOT NULL,
    updated_by        VARCHAR(50) NULL,
    updated_at        DATETIME NULL,
    created_by        VARCHAR(50) NULL,
    created_at        DATETIME NULL,
    PRIMARY KEY (uuid),
    FOREIGN KEY (todo_uuid) REFERENCES LF_TODO (uuid) ON DELETE CASCADE ON UPDATE CASCADE
);