insert into ANYTIMEUSER VALUES(77,sysdate,'admin@admin.com','admin','1234','admin','admin','20','77~77',2,'7777','adminSchool','true',sysdate);

create table ANYTIMEUSER(
id Number(4,2),
createdate Date default sysdate,
email varchar2(100) PRIMARY KEY,
auth varchar2(30),
Password varchar2(300),
rule varchar2(30),
username varchar2(100),
age varchar2(30),
agegroup varchar2(30),
grade number(4,2),
enteryear varchar2(50),
SCHOOL varchar2(300),
permissions varchar2(30), -- 사용권한
EXPIRATION_DATE date default sysdate
);

CREATE SEQUENCE Autoadd
  START WITH 1
  INCREMENT BY 1
  MAXVALUE 10000
  MINVALUE 1
  NOCYCLE;
drop sequence Autoadd;

select * from anyTimeUser;
drop table anyTimeUser;
insert into anyTimeUser values(Autoadd.NEXTVAL,sysdate,'aa','kakao','1234','user','hi','12','30~54',1,'22','가좌중학교',sysdate + (INTERVAL '3' YEAR));
delete from anyTimeUser where email = 'dpound@naver.com';

commit;
-------------------------- 밑으로는 bookshop sql 문

create table ANYTIMEBOARD(
BOARDID NUMBER(4,0) PRIMARY KEY,
BOOKTITLE VARCHAR2(200 BYTE),
BOOKISBN VARCHAR2(100 BYTE),
WRITER VARCHAR2(100 BYTE),
SCHOOL VARCHAR2(300 BYTE),
UNDERLINE VARCHAR2(50 BYTE),
HANDWRITE VARCHAR2(50 BYTE),
COVER VARCHAR2(50 BYTE),
NAMEWRITE VARCHAR2(50 BYTE),
PAGE VARCHAR2(50 BYTE),
MEANSOFTRANSACTION VARCHAR2(50 BYTE),
PRICE NUMBER,
Sales_status varchar2(50),
createdate Date default sysdate,

CONSTRAINT fk_bookId FOREIGN KEY(WRITER)
         REFERENCES ANYTIMEUSER(email) ON DELETE CASCADE

);


CREATE SEQUENCE AutoBookShopAdd
  START WITH 1
  INCREMENT BY 1
  MAXVALUE 10000
  MINVALUE 1
  NOCYCLE;

create table photoTable(
id NUMBER(4,0) PRIMARY KEY,
photo1 clob,
photo2 clob,
photo3 clob,
photo4 clob,
photo5 clob,
CONSTRAINT fk_photoID FOREIGN KEY(id)
         REFERENCES ANYTIMEBOARD(BOARDID) ON DELETE CASCADE
);


select id,* from photoTable;
drop sequence AutoBookShopAdd;

-- 페이징위한 select문
select B.* from(select rownum rn, A.* from(select * from ANYTIMEBOARD order by BOARDID desc)A)B 
		 where rn between 0 and 5;

select *from ANYTIMEBOARD;
select * from photoTable;
select *from ANYTIMEUSER;

-- 위에서 부터 순서대로
drop table photoTable;
drop table ANYTIMEBOARD;
drop table ANYTIMEUSER;



commit;


