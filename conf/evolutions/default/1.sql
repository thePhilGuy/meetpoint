# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table session (
  id                        bigint not null,
  name                      varchar(255),
  host_id                   bigint,
  constraint pk_session primary key (id))
;

create table user (
  id                        bigint not null,
  name                      varchar(255),
  facebook_id               varchar(255),
  user_access_token         varchar(255),
  is_logged_in              boolean,
  constraint pk_user primary key (id))
;


create table session_user (
  session_id                     bigint not null,
  user_id                        bigint not null,
  constraint pk_session_user primary key (session_id, user_id))
;
create sequence session_seq;

create sequence user_seq;




alter table session_user add constraint fk_session_user_session_01 foreign key (session_id) references session (id) on delete restrict on update restrict;

alter table session_user add constraint fk_session_user_user_02 foreign key (user_id) references user (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists session;

drop table if exists session_user;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists session_seq;

drop sequence if exists user_seq;

