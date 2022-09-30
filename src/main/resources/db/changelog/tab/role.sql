-- liquibase formatted sql
/*
 Описание:                Роли
 Первичные ключи:         inline
 Уникальные ключи:        pk_role_name
*/
-- changeset ${user.name}:create_role_tab.sql logicalFilePath:db/changelog/tab/role.sql runOnChange:true splitStatements:true
create table if not exists role
(
    role_name   varchar(200)
        constraint pk_role_name primary key
);


-- changeset ${user.name}:comment_on_columns_role.sql logicalFilePath:db/changelog/tab/role.sql runOnChange:true splitStatements:true
comment on column role.role_name is 'Роль пользователя';