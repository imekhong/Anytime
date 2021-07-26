create table scores(
  item_no number default 1,
  id varchar2(50) not null,
  semester varchar2(10) not null,
  type varchar2(30) not null,
  subject varchar2(30) not null,
  score number not null
);