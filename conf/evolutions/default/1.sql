# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table session (
  id                        bigint not null,
  name                      varchar(255),
  host_id                   bigint,
  user_id                   bigint,
  constraint pk_session primary key (id))
;

create table user (
  id                        bigint not null,
  name                      varchar(255),
  facebook_id               varchar(255),
  is_logged_in              boolean,
  constraint pk_user primary key (id))
;

create sequence session_seq;

create sequence user_seq;

alter table session add constraint fk_session_user_1 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_session_user_1 on session (user_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists session;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists session_seq;

drop sequence if exists user_seq;

