/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     12/25/2018 5:42:37 PM                        */
/*==============================================================*/

use UnivDB;

create trigger TIB_ATTEMPTS before insert
on ATTEMPTS for each row
begin
end;


create trigger TUB_ATTEMPTS before update
on ATTEMPTS for each row
begin
end;


create trigger TDB_EXAM before delete
on EXAM for each row
begin
end;


create trigger TUB_EXAM before update
on EXAM for each row
begin
end;


create trigger TIB_MARKREGISTER before insert
on MARKREGISTER for each row
begin
end;


create trigger TUB_MARKREGISTER before update
on MARKREGISTER for each row
begin
end;


create trigger TIB_REQUIRES before insert
on REQUIRES for each row
begin
end;


create trigger TUB_REQUIRES before update
on REQUIRES for each row
begin
end;


create trigger TDB_TEACHER before delete
on TEACHER for each row
begin
end;


create trigger TUB_TEACHER before update
on TEACHER for each row
begin
end;

