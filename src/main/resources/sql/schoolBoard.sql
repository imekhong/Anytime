create table school_board(
 writer_id varchar2(50),
 nickname varchar2(20),
 post_no number PRIMARY KEY,
 section varchar2(2) not null,
 subject varchar2(50) not null,
 content CLOB not null,
 views number default 0,
 write_date varchar2(30) default to_char(sysdate,'yyyy.mm.dd hh24:mi'),
 school varchar2(300) not null
 );
  
create sequence school_board_no_seq
INCREMENT BY 1
START WITH 1;

create table reply_school_board(
  post_no number,
  reply_no number(10) PRIMARY KEY,
  parent_no number(10) default 0,
  depth number(10) default 0,
  writer_id varchar2(50) not null,
  nickname varchar2(50) not null,
  reply_content varchar2(1000) not null,
  write_date varchar2(30) default to_char(sysdate,'yyyy.mm.dd hh24:mi'),
  school varchar2(300) not null,
  constraint fk_school_reply foreign key(post_no) references school_board(post_no) on delete cascade
);

create SEQUENCE school_reply_no_seq
START WITH 1
INCREMENT BY 1;

create table like_school_board(
  post_no number,
  id varchar2(50) not null,
  constraint fk_school_like foreign key(post_no) references school_board(post_no) on delete cascade
);

create table tag_school_board(
  post_no number references school_board(post_no) on delete cascade,
  tag varchar2(20)
);

commit;