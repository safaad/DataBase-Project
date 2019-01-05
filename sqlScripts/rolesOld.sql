
create user 'admin'@'localhost' identified by 'admin';
grant all privileges on univdb.* to 'admin'@'localhost';


create role teacher,student;
grant insert , update on univdb.exam to teacher;
grant insert ,update on univdb.markregister to teacher;
grant select ,show view on  univdb.* to teacher;


grant insert,update on univdb.attempts to student;
grant select ,show view on univdb.student to student;
grant select ,show view on univdb.markregister to student;
grant select,show view on univdb.course to student;


delimiter //
create procedure permission_std (IN stdid varchar(5))
begin

create user stdid@'localhost';
grant all on student to stdid@'localhost';

end//
delimiter ;


delimiter //
create procedure permission_tch (IN tid varchar(5))
begin

create user tid@'localhost';
grant all on teacher to tid@'localhost';

end//
delimiter ;
