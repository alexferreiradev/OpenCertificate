create table certificate (
    ID int not null primary key
    fileName varchar(255) not null,
    fileExtension varchar(255) not null,
    uuid varchar(255) not null
    participantId int not null
);

alter table certificate add foreign key (participant_id) references (id);
