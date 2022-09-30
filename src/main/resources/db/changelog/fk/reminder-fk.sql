-- liquibase formatted sql
/*
    Внешние ключи:  fk_user_id - ссылка на юзера
*/
-- changeset ${user.name}:create_reminder_fk.sql logicalFilePath:db/changelog/fk/reminder-fk.sql runOnChange:true splitStatements:true endDelimiter:/
do
$$
    begin
        if not exists(select 1 from pg_constraint where conname = 'fk_user_id') then
            alter table reminder
            add constraint fk_user_id foreign key (user_id) references users (id)
            on delete cascade;
            raise notice 'Table constraint has been added';
        else
            raise notice 'Table constraint already exists';
        end if;
    end
$$ /