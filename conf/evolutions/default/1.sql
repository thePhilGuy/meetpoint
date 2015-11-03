# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table session (
  id                        varchar(255) not null,
  name                      varchar(255),
  constraint pk_session primary key (id))
;

create table user (
  id                        varchar(255) not null,
  name                      varchar(255),
  facebook_id               varchar(255),
  is_logged_in              boolean,
  constraint pk_user primary key (id))
;

create sequence session_seq;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists session;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists session_seq;

drop sequence if exists user_seq;

