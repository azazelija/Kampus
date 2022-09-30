-- liquibase formatted sql
/*
     Главный пользователь с ролью ADMIN
*/
-- changeset ${user.name}:insert_admin_user.sql logicalFilePath:db/changelog/data/user-data.sql runOnChange:true splitStatements:true endDelimiter:/

do
$$
declare role_name_v2 varchar;
declare username varchar := 'admin';
declare password varchar := '$2a$12$mqJg1RzTkf/MDrt62FdSE.luzjHCsCEOcrBwoKT0cTA0kNsbV/tl6';
declare telegram_id varchar := 'TelegramId';
declare email varchar := 'cifay19019@pahed.com';

begin
    select role.role_name into STRICT role_name_v2
            from role where role.role_name = 'ADMIN';
    if role_name_v2 is not null then
        insert into users (role_name, username, password, telegram_id, email) values (role_name_v2, username, password, telegram_id, email);
        raise notice 'Admin user has been added';
    else
        raise notice 'Failed to add Admin user';
    end if;
end
$$ /

-- changeset ${user.name}:insert_default_user.sql logicalFilePath:db/changelog/data/user-data.sql runOnChange:true splitStatements:true endDelimiter:/

do
$$
declare role_name_v2 varchar;
declare username varchar := 'user';
declare password varchar := '$2a$12$6nZ6m/hP3qmykxaeKIvHau3K1JIZfYMWEMykuGjr3.HH/D1NLCKgK';
declare telegram_id varchar := 'TelegramId';
declare email varchar := 'cifay19019@pahed.com';

begin
    select role.role_name into STRICT role_name_v2
            from role where role.role_name = 'USER';
    if role_name_v2 is not null then
        insert into users (role_name, username, password, telegram_id, email) values (role_name_v2, username, password, telegram_id, email);
        raise notice 'Admin user has been added';
    else
        raise notice 'Failed to add Admin user';
    end if;
end
$$ /