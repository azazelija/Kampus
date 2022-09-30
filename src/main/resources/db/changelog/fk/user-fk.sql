-- liquibase formatted sql
/*
    Внешние ключи:  fk_role_name - ссылка роль
*/
-- changeset ${user.name}:create_user_fk.sql logicalFilePath:db/changelog/fk/user-fk.sql runOnChange:true splitStatements:true endDelimiter:/
do
$$
    begin
        if not exists(select 1 from pg_constraint where conname = 'fk_role_name') then
            alter table users
            add constraint fk_role_name foreign key (role_name) references role (role_name)
            on delete no action;
            raise notice 'Table constraint has been added';
        else
            raise notice 'Table constraint already exists';
        end if;
    end
$$ /