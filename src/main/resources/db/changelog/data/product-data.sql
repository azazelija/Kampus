-- liquibase formatted sql
-- changeset ${user.name}:insert_iphone_6s.sql logicalFilePath:db/changelog/data/product-data.sql runOnChange:true splitStatements:true endDelimiter:/

insert into types (name) values ('Смартфоны');

do
--$$
--declare type_id int;
--declare name varchar := 'Iphone 6s Rose Gold';
--declare description varchar := 'Iphone 6s Rose Gold 2014';
--declare price decimal(12,2) := 39699.00;
--declare total bigint := 1000;
--declare reserved bigint := 1;
--
--begin
--    select types.id into STRICT type_id
--    from types where types.name = 'Смартфоны';
--    if type_id is not null then
--        insert into products (type_id, name, description, price, total, reserved) values (type_id, name, description, price, total, reserved);
--        raise notice 'Iphone 6s has been added';
--    else
--        raise notice 'Failed to add Iphone 6s';
--    end if;
--end
--$$ /