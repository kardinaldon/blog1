drop table if exists comment;
drop table if exists account;
drop table if exists article;
drop table if exists category;
drop sequence if exists account_seq;
drop sequence if exists comment_seq;
drop index if exists comment_idx;
drop index if exists comment_idx1;
drop index if exists article_idx;

create table category (
    id integer not null unique,
    name varchar(20),
    url varchar(255) unique,
    articles integer not null default(0),
    primary key (id)
);

create table article (
    id integer not null unique,
    title varchar(255) not null,
    url varchar(255) not null,
    logo varchar(255) not null,
    description varchar(255) not null,
    content text not null,
    id_category integer not null,
    created timestamp default (now()),
    views bigint not null default (0),
    comments integer not null default (0),

    primary key (id),
    foreign key (id_category) references category(id) on delete restrict on update cascade
);

create table account (
    id integer not null unique,
    email varchar(100) not null unique,
    name varchar(100) not null,
    avatar varchar(255),
    created timestamp default (now()),

    primary key (id)
);

create table comment (
    id bigint not null unique,
    id_account bigint not null,
    id_article bigint not null,
    content text not null,
    created timestamp default (now()),

    primary key (id),
    foreign key (id_account) references account(id) on delete restrict on update cascade,
    foreign key (id_article) references article(id) on delete restrict on update cascade
);

create sequence account_seq
    increment 1
    start 1
    minvalue 1
    cache 1;

create sequence comment_seq
    increment 1
    start 1
    minvalue 1
    cache 1;

create index comment_idx
    on comment using btree
        (id_article asc nulls last)
    tablespace pg_default;

create index comment_idx1
    on comment using btree
        (id_account asc nulls last)
    tablespace pg_default;

create index article_idx
    on article using btree
        (id_category asc nulls last)
    tablespace pg_default;


