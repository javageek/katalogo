connect 'jdbc:derby://localhost:1527/myDB;user=katalog;password=katalog';
create table foo (id integer, name varchar(20));
insert into foo values (1, 'derby');
insert into foo values (2, 'derby plug-ins');
disconnect;
exit;