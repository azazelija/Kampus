-- liquibase formatted sql
/*
 Описание:                Пользователи
 Первичные ключи:         inline
 Уникальные ключи:        pk_user_id
*/
-- changeset ${user.name}:create_user_tab.sql logicalFilePath:db/changelog/tab/users.sql runOnChange:true splitStatements:true
create table if not exists users
(
    id          bigserial
        constraint pk_user_id primary key,
    role_name       varchar(50)       not null,
    username        varchar(50)       not null,
    password        varchar(250)      not null,
    telegram_id     varchar(50)       not null,
    email           varchar(50)       not null,

    CONSTRAINT name_unique UNIQUE (username)
);


-- changeset ${user.name}:comment_on_columns_user.sql logicalFilePath:db/changelog/tab/users.sql runOnChange:true splitStatements:true
comment on column users.role_name is 'Роль пользователя';
comment on column users.username is 'Имя';
comment on column users.telegram_id is 'Телеграмм';
comment on column users.email is 'Почта';