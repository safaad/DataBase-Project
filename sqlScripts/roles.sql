create user 'admin'@'localhost' identified by 'admin';
grant all privileges on DBProject.* to 'admin'@'localhost';

create user 'student'@'localhost' identified by 'student';
create user 'teacher'@'localhost' identified by 'teacher';

grant insert , update on DBProject.exam to 'teacher'@'localhost';
grant insert ,update on DBProject.markregister to 'teacher'@'localhost';
grant select ,show view on  DBProject.* to 'teacher'@'localhost';

grant insert,update on DBProject.attempts to 'student'@'localhost';
grant select ,show view on DBProject.student to 'student'@'localhost';
grant update on DBProject.student to 'student'@'localhost';
grant select ,show view on DBProject.markregister to 'student'@'localhost';
grant select,show view on DBProject.course to 'student'@'localhost';
grant select,show view on DBProject.REQUIRES to 'student'@'localhost';
grant select,show view on DBProject.ATTEMPTS to 'student'@'localhost';
grant select,show view on DBProject.TEACHER to 'student'@'localhost';



