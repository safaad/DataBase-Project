use dbproject;

delimiter $$
create procedure certificateStudent(IN stdid int)
begin
select NOW();
select sid , sname from dbproject.student where sid=stdid;
call certificatecursor(stdid);
end $$
delimiter;

delimiter //
create procedure certificatecursor(IN stdid int)
begin
DECLARE finished INTEGER DEFAULT FALSE;
DECLARE _crs_list TEXT DEFAULT '' ;
DECLARE total_mark decimal default 0;
declare _cid int;declare _sid int;declare _xid int;declare _mark decimal;
declare crs_cursor cursor for select cid , xid , mark from dbproject.markregister where sid = stdid ;
DECLARE CONTINUE HANDLER 
FOR NOT FOUND SET finished = true;
open crs_cursor;
REPEAT
fetch crs_cursor into _cid , _xid, _mark;
if not finished then
# build list
set total_mark = total_mark + _mark;
set _crs_list=concat(_crs_list,'\r\n',_cid,char(10),_xid,char(10),_mark);
end if;
until finished end REPEAT;
close crs_cursor;
set _crs_list=concat(_crs_list,'\r\n','TOTAL',char(10),total_mark);
select _crs_list;
end //
delimiter ;

use dbproject;
call certificateStudent(20006);
