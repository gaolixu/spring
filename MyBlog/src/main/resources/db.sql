---create database blog default character set utf8 collate utf8_bin;
---use blog;


SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS category;
create table category
(
id integer primary key auto_increment,
name varchar(64),
display_name varchar(64)
);

insert into category(id,name,display_name) values(1,'firstPage','firstPage');
insert into category(id,name,display_name) values(2,'webPage','JavaWeb Page');
insert into category(id,name,display_name) values(3,'androidPage','Android Page');
insert into category(id,name,display_name) values(4,'rnPage','React Native');
insert into category(id,name,display_name) values(5,'ubuntuPage','Ubuntu');



DROP TABLE IF EXISTS article;

create table article
(
  id  integer auto_increment primary key not null,
  title VARCHAR2(100),
  content     text,
  categoryId integer ,
  summary   text,
  date DATETIME
);

insert into article(title,content,categoryId,summary,date) values('test','testddddddd',1,'test1111','2017-03-10');
insert into article(title,content,categoryId,summary,date) values('test222','test22222',2,'test2222','2017-03-10');


DROP TABLE IF EXISTS comments;
create table comments(
id integer primary key auto_increment,
article_id integer,
comm_ip varchar(64),
comment text
);


DROP TABLE IF EXISTS user;
create table user(
id integer primary key auto_increment,
username varchar(64),
password varchar(64)
);
insert into user(username,password) values('123','123');

--alter table article
--  add constraint article primary key (id);  
-- Create sequence 
--create sequence SEQ_ORDER
--minvalue 1
--maxvalue 999999999999
--start with 1
--increment by 1
--cache 200;
--
--DROP TABLE IF EXISTS `article`;
--CREATE TABLE `article` (
--  `id` integer  COMMENT '����ID',
--  `BUTTON_CODE` varchar(50) default NULL COMMENT '��ť����',
--  `button_icon` varchar(20) default NULL COMMENT '��ťͼ��',
--  `BUTTON_NAME` varchar(50) default NULL COMMENT '��ť����',
--  `BUTTON_STATUS` varchar(2) default NULL COMMENT '��ť״̬',
--  `BUTTON_STYLE` varchar(20) default NULL COMMENT '��ť��ʽ',
--  `EXP` varchar(255) default NULL COMMENT '���ʽ',
--  `FORM_ID` varchar(32) default NULL COMMENT '��ID',
--  `OPT_TYPE` varchar(;20) default NULL COMMENT '��ť����',
--  `order_num` int(11) default NULL COMMENT '����',
--  PRIMARY KEY  (`id`) auto_increment
--) ENGINE=InnoDB DEFAULT CHARSET=utf8;