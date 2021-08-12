create table item(
    id bigint not null identity(1,1),
    uuid varchar(250) not null,
    stock int not null,
    name varchar(250),
    cost float,
    primary key(id)
);

create unique index UK_uuid on item(uuid);

insert into item(uuid, stock, name) values (NEWID(), 100, 'item test 1', 200), (NEWID(), 50, 'item test 2', 100);

create table shoppingCart(
    id bigint not null identity(1,1),
    uuid varchar(250) not null,
    uuid_user varchar(250) not null,
    activeStatus bit not null,
    primary key(id)
);

create unique index UK_shoppingCart_uuid on shoppingCart(uuid);

create table item_in_shoppingCart(
    id_shoppingCart bigint not null,
    amount int not null,
    uuid_item varchar(250) not null
);

create index IxID_shoppingCart on item_in_shoppingCart(id_shoppingCart);

create table checkout(
    id bigint not null identity(1,1),
    uuid varchar(250) not null,
    shoppingCart_uuid varchar(250) not null,
    total_cost float,
    primary key(id)
);

create unique index UK_checkout_uuid on checkout(uuid);
create unique index UK_shoppingCart on checkout(shoppingCart_uuid);