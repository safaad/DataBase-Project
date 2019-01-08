use dbproject;

delimiter $$
create procedure certificateStudent(IN stdid int)
begin
select NOW();
select sid , sname from dbproject.student where sid=stdid;
set @crs_list="";
call certificatecursor(stdid,@crs_list);
select crs_list;
end $$
delimiter;

delimiter //
create procedure certificatecursor(IN stdid int,inout crs_list varchar(10000))
begin
DECLARE finished INTEGER DEFAULT 0;
declare cid int;declare sid int;declare xid int;declare mark decimal;
declare crs_cursor cursor for select cid , xid , mark from dbproject.markregister where sid = stdid ;
DECLARE CONTINUE HANDLER 
FOR NOT FOUND SET finished = 1;
open crs_cursor;
get_courses:LOOP
fetch crs_cursor into cid , xid, mark;
if finished=1 then
leave get_courses;
end if;
# build list
set crs_list=concat(cid,"\t",xid,"\t",mark,"\n",crs_list);
end loop get_courses;
close crs_cursor;
end //
delimiter ;


call certificateStudent(20006);


 