### Anytime
애니타임 프로젝트
(프로젝트 저장소를 다시 만들었습니다.)
---
## 목적
 - 기본에 충실하여 게시판을 구현함으로써 기본적인 CRUD를 익히자
 - Open API를 사용하여 API를 통해 얻어오는 결과값을 활용하는 법을 익히자
 - 고등학생끼리 소통할 수 있는 익명 커뮤니티를 구현하자 (ex: 에브리타임)
---
## 기획 
 - 자유게시판: 모든 학교의 이용자가 소통
 - 학교게시판: 게시물 주제에 따라 4개로 분류되며, 같은 학교 이용자끼리 소통
 - 시간표: 시간표 입력 관리
 - 성적계산기: 성적 입력 관리, 그래프 확인
 - 책방: 중고책 판매/구매 거래, 쪽지로 대화

<img width="693" alt="홈페이지 구성도" src="https://user-images.githubusercontent.com/78437105/126042748-b390395a-3611-49c3-9644-a1b7ac86bf51.png">

---
## 프로젝트 주요기능
# 회원가입: <span style="color:green;">dpound</span>
 - 카카오계정, 일반회원가입, (+추가: <span style="color:orange;">imekhong</span>)네이버계정
 - 일반회원가입: 이메일인증, (+추가: <span style="color:orange;">imekhong</span>)문자인증
 - 공통: 학교입력(지도에서 검색하여 선택)

# 로그인, 로그아웃: <span style="color:green;">dpound</span>
 - 카카오로그인, 일반로그인, (+추가: <span style="color:orange;">imekhong</span>)네이버로그인

# 게시판: <span style="color:orange;">imekhong</span>
 - 자유게시판, 학교게시판(비밀게시판, 동아리게시판, 학교정보게시판, 진학진로게시판)
 - 글목록, 글읽기(+조회수), 글쓰기(summernote), 글수정, 글삭제
 - 댓글, 대댓글, 수정/삭제 
 - 페이징
 - 좋아요
 - 내가 쓴 글, 내가 쓴 댓글

# 시간표: <span style="color:orange;">imekhong</span>
 - 시간표 추가, 수정, 삭제(UI 시간표 그리기)
 - 시간표 이미지로 저장(html2canvas)

# 성적계산기: <span style="color:orange;">imekhong</span>
 - 성적 입력, 수정, 삭제
 - 성적 분포 그래프(Google Chart)

# 책방(중고거래): <span style="color:green;">dpound</span>
 - 책판매 글 쓰기, 수정, 판매완료(글 삭제)
 - 쪽지(책거래)

# 관리자
 - 회원가입한 회원 접근/사용 승인(승인전 게시판, 책방 기능 사용 불가) : <span style="color:green;">dpound</span>
 - 게시글, 댓글 삭제 권한 : <span style="color:orange;">imekhong</span>
---
## 개발 환경
<img width="621" alt="개발 환경" src="https://user-images.githubusercontent.com/78437105/126042726-f3beef84-cba8-4b82-a364-03ffd0326c24.png">
---
## 사용 기술
<img width="591" alt="사용 기술" src="https://user-images.githubusercontent.com/78437105/126042740-81b8bf59-d962-45be-9f81-0cc1c64bcc25.png">
---
## USECASE
 # 사용자
  - 사용자는 일반, 카카오계정, 네이버계정 중 선택하여 회원가입/로그인할 수 있다.
  - 사용자는 가입 후 관리자의 접근 승인을 받아야 게시물이나 책방의 게시물에 접근 할 수 있다.
  - 사용자는 게시물 리스트를 검색어로 검색하여 원하는 결과만 볼 수 있다.
  - 사용자는 자신이 쓴 글/댓글만 수정/삭제할 수 있다.
 # 관리자
 - 모든 회원의 게시물 접근을 승인하거나 취소할 수 있는 권한이 있다.
   : 기존 에브리타임의 학교인증 절차를 대체함
 - 자유게시판의 모든 게시물을 보고 삭제할 수 있다.
 - 학교게시판은 학교구분 없이 모든 게시물을 보고 삭제할 수 있다.
 - 모든 댓글을 보고 삭제할 수 있다.
---
## 화면 설계
# Main 화면 프로토타입

# 게시판 화면 프로토타입

# 시간표 화면 프로토타입

# 성적계산기 화면 프로토타입

---
## 프로젝트 DB ERD

