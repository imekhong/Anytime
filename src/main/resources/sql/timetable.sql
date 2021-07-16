create table timetable(
	item_no number default 1,
	id varchar2(50) not null,
	semester varchar2(10) not null,
	subject varchar2(30) not null,
	day varchar2(10) not null,
	start_time number not null,
	start_minute number not null,
	end_time number not null,
	end_minute number not null
);

create table timetable_setting(
  id varchar2(50) PRIMARY KEY,
  main varchar2(10) DEFAULT 'sem1',
  sem1 varchar2(20) DEFAULT 'public',
  sem2 varchar2(20) DEFAULT 'public',
  sem3 varchar2(20) DEFAULT 'public',
  sem4 varchar2(20) DEFAULT 'public',
  sem5 varchar2(20) DEFAULT 'public',
  sem6 varchar2(20) DEFAULT 'public'
);