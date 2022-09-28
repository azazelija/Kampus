---- liquibase formatted sql
--/*
--    Внешние ключи:  fk_products_type_id - ссылка тип товара
--*/
---- changeset ${user.name}:create_products_fk.sql logicalFilePath:db/changelog/fk/products-fk.sql runOnChange:true splitStatements:true endDelimiter:/
--do
--$$
--    begin
--        if not exists(select 1 from pg_constraint where conname = 'fk_products_type_id') then
--            alter table products
--            add constraint fk_products_type_id foreign key (type_id) references types (id);
--            raise notice 'Table constraint has been added';
--        else
--            raise notice 'Table constraint already exists';
--        end if;
--    end
--$$ /