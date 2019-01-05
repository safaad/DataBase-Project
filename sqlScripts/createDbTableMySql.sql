/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     12/25/2018 5:42:02 PM                        */
/*==============================================================*/

use UnivDB;

/*==============================================================*/
/* Table: ATTEMPTS                                              */
/*==============================================================*/
create table ATTEMPTS
(
   SID                  varchar(5) not null  comment '',
   CID                  varchar(5) not null  comment '',
   primary key (SID, CID)
);

/*==============================================================*/
/* Table: COURSE                                                */
/*==============================================================*/
create table COURSE
(
   CID                  varchar(5) not null  comment '',
   TID                  varchar(5) not null  comment '',
   CNAME                varchar(256)  comment '',
   NB_CREDITS           int not null  comment '',
   primary key (CID)
);

/*==============================================================*/
/* Table: EXAM                                                  */
/*==============================================================*/
create table EXAM
(
   XID                  varchar(5) not null  comment '',
   XLABEL               varchar(256) not null  comment '',
   XDATE                datetime not null  comment '',
   MARK_ON              int not null  comment '',
   primary key (XID)
);

/*==============================================================*/
/* Table: MARKREGISTER                                          */
/*==============================================================*/
create table MARKREGISTER
(
   SID                  varchar(5) not null  comment '',
   XID                  varchar(5) not null  comment '',
   CID                  varchar(5) not null  comment '',
   MARK                 decimal not null  comment '',
   primary key (SID, XID, CID)
);

/*==============================================================*/
/* Table: REQUIRES                                              */
/*==============================================================*/
create table REQUIRES
(
   CID                  varchar(5) not null  comment '',
   COU_CID              varchar(5) not null  comment '',
   primary key (CID, COU_CID)
);

/*==============================================================*/
/* Table: STUDENT                                               */
/*==============================================================*/
create table STUDENT
(
   SID                  varchar(5) not null  comment '',
   PASS                 varchar(256)  comment '',
   SNAME                varchar(256) not null  comment '',
   BDATE                datetime not null  comment '',
   PHONE                varchar(8) not null  comment '',
   ADDRESS              varchar(256) not null  comment '',
   primary key (SID)
);

/*==============================================================*/
/* Table: TEACHER                                               */
/*==============================================================*/
create table TEACHER
(
   TID                  varchar(5) not null  comment '',
   PASS                 varchar(256)  comment '',
   TNAME                varchar(256) not null  comment '',
   BDATE                datetime not null  comment '',
   PHONE                varchar(8) not null  comment '',
   ADDRESS              varchar(256) not null  comment '',
   SPECIALITY           varchar(256) not null  comment '',
   primary key (TID)
);
alter table student alter pass set default '1234';
alter table teacher alter pass set default '1234';
alter table student  auto_increment = 20000;
alter table teacher auto_increment=10000;


