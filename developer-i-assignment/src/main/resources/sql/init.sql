/**
 * step 01. 데이터베이스, 계정 생성 및 권한 부여
 * root 계정으로 로그인
 */

create user 'repl_assign_master'@'localhost' identified by 'hello';
create user 'repl_assign_second'@'localhost' identified by 'world';

create database repl_assign_db character set=utf8mb4 collate=utf8mb4_unicode_ci;

grant all privileges on repl_assign_db.* to 'repl_assign_master'@'localhost';
grant select on repl_assign_db.* to 'repl_assign_second'@'localhost';

flush privileges;

/**
 * step 02. 테이블 생성
 * repl_assign_master 계정으로 로그인
 */

-- spring session jdbc
create table repl_assign_session (
    primary_id char(36) not null,
    session_id char(36) not null,
    creation_time bigint not null,
    last_access_time bigint not null,
    max_inactive_interval int not null,
    expiry_time bigint not null,
    principal_name varchar(100),
    constraint spring_session_pk primary key (primary_id)
) engine=innodb row_format=dynamic;

create unique index repl_assign_session_ix1 on repl_assign_session (session_id);
create index repl_assign_session_ix2 on repl_assign_session (expiry_time);
create index repl_assign_session_ix3 on repl_assign_session (principal_name);

create table repl_assign_session_attributes (
    session_primary_id char(36) not null,
    attribute_name varchar(200) not null,
    attribute_bytes blob not null,
    constraint repl_assign_session_attributes_pk primary key (session_primary_id, attribute_name),
    constraint repl_assign_session_attributes_fk foreign key (session_primary_id) references repl_assign_session(primary_id) on delete cascade
) engine=innodb row_format=dynamic;

create table member (
   member_id bigint not null auto_increment,
	email varchar(200) not null,
	gender varchar(255),
	name varchar(20) not null,
	nickname varchar(30) not null,
	password varchar(200) not null,
	phone_number varchar(40) not null,
	primary key (member_id)
) engine=InnoDB;

create table product_order (
   order_id varchar(12) not null,
	payment_date datetime not null,
	product_name varchar(100) not null,
	member_id bigint,
	primary key (order_id)
) engine=InnoDB;

alter table member drop index UK_mbmcqelty0fbrvxp1q58dn57t;
alter table member add constraint UK_mbmcqelty0fbrvxp1q58dn57t unique (email);
alter table product_order add constraint FKhhw52boqi14jonn99dwq64tgi  foreign key (member_id)  references member (member_id);
