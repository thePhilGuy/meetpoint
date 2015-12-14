# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table session (
  id                        bigint not null,
  name                      varchar(255),
  host_id                   bigint,
  meet_type                 varchar(255),
  constraint pk_session primary key (id))
;

create table user (
  id                        bigint not null,
  name                      varchar(255),
  facebook_id               varchar(255),
  user_access_token         varchar(255),
  is_logged_in              boolean,
  friends                   varchar(255),
  latitude                  double,
  longitude                 double,
  constraint pk_user primary key (id))
;


create table session_user_joined (
  session_id_joined              bigint not null,
  user_id_joined                 bigint not null,
  constraint pk_session_user_joined primary key (session_id_joined, user_id_joined))
;

create table session_user_unjoined (
  session_id_unjoined            bigint not null,
  user_id_unjoined               bigint not null,
  constraint pk_session_user_unjoined primary key (session_id_unjoined, user_id_unjoined))
;
create sequence session_seq;

create sequence user_seq;




alter table session_user_joined add constraint fk_session_user_joined_sessio_01 foreign key (session_id_joined) references session (id) on delete restrict on update restrict;

alter table session_user_joined add constraint fk_session_user_joined_user_02 foreign key (user_id_joined) references user (id) on delete restrict on update restrict;

alter table session_user_unjoined add constraint fk_session_user_unjoined_sess_01 foreign key (session_id_unjoined) references session (id) on delete restrict on update restrict;

alter table session_user_unjoined add constraint fk_session_user_unjoined_user_02 foreign key (user_id_unjoined) references user (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists session;

drop table if exists session_user_joined;

drop table if exists session_user_unjoined;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists session_seq;

drop sequence if exists user_seq;

