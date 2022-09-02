create sequence hibernate_sequence start 1;

create table event (
    id             SERIAL not null primary key,
    name             varchar(255) not null,
    executor             varchar(255) not null,
    talker_topics           varchar(255) not null,
    date_started          timestamp not null,
    date_ended             timestamp not null
);

create table participant
(
    id             SERIAL not null primary key,
    name             varchar(255) not null,
    last_name             varchar(255) not null,
    rg           varchar(255),
    cpf          varchar(255),
    hour             varchar(255) not null,
    event_id             int not null
);

create table certificate
(
    id             SERIAL          not null primary key,
    file_name      varchar(255) not null,
    file_extension varchar(255) not null,
    uuid           varchar(255) not null,
    participant_id int          not null
);

alter table certificate
    add foreign key (participant_id) references participant(id);
