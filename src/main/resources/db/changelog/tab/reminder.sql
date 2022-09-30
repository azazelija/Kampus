-- liquibase formatted sql
/*
 Описание:                Напоминания
 Первичные ключи:         inline
 Уникальные ключи:        pk_reminder_id
*/
-- changeset ${user.name}:create_reminder_tab.sql logicalFilePath:db/changelog/tab/reminder.sql runOnChange:true splitStatements:true
create table if not exists reminder
(
    id   bigserial
        constraint pk_reminder_id primary key,
    title        varchar(200)   not null,
    description  varchar(1000),
    remind_date  date           not null,
    remind_time  time           not null,
    user_id      bigint         not null
);


-- changeset ${user.name}:comment_on_columns_reminder.sql logicalFilePath:db/changelog/tab/reminder.sql runOnChange:true splitStatements:true
comment on column reminder.title is 'Краткое описание';
comment on column reminder.description is 'Полное описание';
comment on column reminder.remind_date is 'Дата напоминания';
comment on column reminder.remind_time is 'Время напоминания';