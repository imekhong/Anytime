# 시간표 구현 화면

[README로 이동](../../README.md)

## 시간표 화면 구성
- 학기별로 시간표 생성 가능
- 이미지 저장 기능
- 시간표 설정 기능
- 새 수업 추가 기능
- 수업 수정 기능
- 수업 삭제 기능

## 이미지 저장 기능
- html2canvas 사용
- 사용자컴퓨터/다운로드 경로에 애니타임_시간표.png로 시간표 영역만 저장
<img width="941" alt="시간표_이미지저장" src="https://user-images.githubusercontent.com/78437105/126283761-a0047c01-a5a1-4d7b-b175-23fc4feff3d2.png">

## 시간표 설정 기능
- 기본시간표 설정: 상단 시간표 메뉴를 눌렀을때 기본으로 보여주는 학기로 설정
- 시간표 초기화: 해당 학기에 입력된 모든 수업 초기화(삭제)
<img width="894" alt="시간표_설정" src="https://user-images.githubusercontent.com/78437105/126283809-e63c14fb-460f-4d21-afe0-26907ef62421.png">

## 새 수업 추가 기능
- 새 수업 추가: 입력값이 정상적일때 저장됨
  - 모든 항목이 입력되어야함
  - 기존 입력된 시간표와 시간이 중복되지 않아야함
  - 종료시간이 시작시간보다 빠를 수 없음
<img width="939" alt="시간표추가" src="https://user-images.githubusercontent.com/78437105/126283795-a6bef5cd-83dd-4ea5-8c2f-976b6c2d9d51.png">

## 수정, 삭제 기능
- 입력한 수업 수정/삭제하는 기능
<img width="871" alt="시간표삭제" src="https://user-images.githubusercontent.com/78437105/126283815-6bf820a0-8516-4e41-bcb7-9e24dcf204c7.png">
