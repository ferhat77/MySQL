create  table talebeler(
id char(3) primary key,
isim varchar(50),
veli_isim varchar(50),
yazili_notu int

);
insert into talebeler values(123,'Haluk Bilgin','Hasan',99);
insert into talebeler values(124,'Harun Bil','Ahmet',91);
insert into talebeler values(125,'Halime Bak','Ipek',77);
insert into talebeler values(126,'Hasan  Hasmayan','Mehmet',83);
insert into talebeler values(127,'Hanimiş Beee','Merve',63);
select * from talebeler;