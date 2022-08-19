-- create table event ();

create table participant
(
    id             int not null primary key,
    name             varchar(255) not null,
    lastName             varchar(255) not null,
    rg           varchar(255) not null,
    cpf          varchar(255) not null,
    hour             varchar(255) not null,
    event_id             int not null
);

create table certificate
(
    id             int          not null primary key,
    file_name      varchar(255) not null,
    file_extension varchar(255) not null,
    uuid           varchar(255) not null,
    participant_id int          not null
);

alter table certificate
    add foreign key (participant_id) references participant(id);
