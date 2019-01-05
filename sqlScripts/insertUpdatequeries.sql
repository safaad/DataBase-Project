use DBproject;
create view gradeStudents as
select student.sid,sname,course.cid , cname , exam.xid , exam.xlabel,xdate,mark_on,mark
from course,student,exam,markregister as mk
where student.sid=mk.sid and course.cid=mk.cid and exam.xid=mk.xid; 

create view SCT as
select attempts.sid,sname,course.cid,course.tid,tname,cname,nb_credits
from course,teacher,student,attempts
where attempts.sid=student.sid and course.cid=attempts.cid and teacher.tid=course.tid;
use DBproject;
insert into student (sname,bdate,phone,address) values('safaa diab','1999-12-02','76900515','beirut'),('bakir nasim','1998-01-01','70898989','beirut'),('batoul dekmak','1999-01-11','81232323','beirut'),
											('nour sheikh ali','1997-01-01','76666777','baalback'),('amira baltajy','1998-01-02','8098989','jbeil');
                                            
use DBproject;
insert into teacher(tname,bdate,phone,address,speciality) values('mohamad dbouk','1977-01-01','70000000','beirut','database management'),('bassem haidar','1980-01-01','70989898','baalback','network and security'),
											('ihab sbeity','1980-01-01','81233445','nabateye','operating system'),('mohamad hamze','1987-09-07','76566778','beirut','inormatics'),('antoun yaackob','1980-07-06','81232425','jbeil','data structure'),
                                            ('nour shokeir','1998-01-01','70898989','beirut','statistics');
use DBproject;
insert into course(cid,tid,cname,nb_credits) values('3306','10004','database',4),('3304','10005','network',4),('3303','10006','OS',5),('3302','10007','php',4),('2206','10007','ds',5);                                            



use DBproject;
delete from teacher where tid='10002' or  tid = '10003';

use DBproject;
update student  set address = 'bchamoun' where sid = '20002'; 				

use DBProject;
delete from course where cid=5 or cid=6 or cid=7 or cid=12;

