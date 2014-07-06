# --- !Ups

create table quotes (
  id                  bigint not null auto_increment,
  quote               varchar(max),
  attributed_to       varchar(255),
  year                int(4),
  primary key (id)
);

create sequence quotes_seq;

insert into quotes(quote, attributed_to, year) values ('I keep finding baby shoes! What the heck, man, and they''re all lefties!', 'Jake the Dog', 2010);
insert into quotes(quote, attributed_to, year) values ('Dude, suckin’ at something is the first step to being sorta good at something.','Jake the Dog', 2011);
insert into quotes(quote, attributed_to, year) values ('It is often easier to ask for forgiveness than to ask for permission.','Grace Hopper', 1959);

# --- !Downs

drop table if exists quotes;