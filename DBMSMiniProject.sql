show databases;
show tables;
use LoginApp;

create table register(uid int NOT NULL AUTO_INCREMENT,username varchar(20),phone varchar(10),email varchar(40),primary key(uid,email));
ALTER TABLE register AUTO_INCREMENT=1;
create table login(uid int NOT NULL AUTO_INCREMENT,firstname varchar(20), pass varchar(15),primary key(uid),foreign key(uid) references register(uid));
ALTER TABLE login AUTO_INCREMENT=1;
create table details(uid int NOT NULL AUTO_INCREMENT,firstname varchar(20),lastname varchar(15),email varchar(30),gender varchar(1),primary key(uid,email),foreign key(uid) references register(uid));
ALTER TABLE details AUTO_INCREMENT=1;
create table misc(uid int NOT NULL AUTO_INCREMENT,address varchar(40),dob date,primary key(uid),foreign key(uid) references register(uid));
ALTER TABLE misc AUTO_INCREMENT=1;
create table recipient(rid int not null auto_increment,fromid varchar(40),toid varchar(40),mcount int,primary key(rid));
ALTER TABLE recipient AUTO_INCREMENT=1;
create table messages(rid int not null auto_increment,fromid varchar(40),message varchar(50),subj varchar(20),mcount int,primary key(rid));
ALTER TABLE messages AUTO_INCREMENT=1;
create table attachments(rid int not null auto_increment,fromid varchar(40),attachment varchar(50),toid varchar(30));
ALTER TABLE attachments AUTO_INCREMENT=1;

select * from register;
select * from login;
select * from details;
select * from misc;
select * from recipient;
select * from attachments;
select * from messages;

desc login;
desc details;
desc misc;
desc attachments;
desc recipient;
desc messages;
desc register;


truncate register;
truncate login;
truncate misc;
truncate details;
truncate recipient;
truncate attachments;
truncate messages;


show triggers;
drop trigger recipient_AFTER_UPDATE;

update recipient set mcount = mcount + 1 where fromid = "samarthhalyal@gmail.com" and toid = "samarthhalyal@gmail.com";

set @namelist = "";
call MAILXPROCEDURE(@namelist);
select @namelist;

drop table attachments;
drop table recipient;
drop table messages;
drop table register;
drop table misc;
drop table details;
drop table login;
