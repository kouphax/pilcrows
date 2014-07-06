# --- !Ups

create table quotes (
  id                  bigint not null auto_increment,
  quote               varchar(max),
  attributed_to       varchar(255),
  year                int(4),
  primary key (id)
);


# --- !Downs

drop table if exists quotes;