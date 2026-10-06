
# 비회원 게시판

게시물 등록, 상세, 수정, 삭제가 가능한 비회원 기반의 게시판

---
## 기술 스택
### 프론트엔드
Thymeleaf

### 백엔드
Spring-Boot(3.5.16)  // 4.x버전부턴 MyBatis를 지원하지 않음

JDK17

### DB
H2 내장 DB

### DB관리
MyBatis(3.0.5)

### 데이터 송수신
REST API

---
## API 명세

View 이동 API (BoardViewController)

| HTTP    | URL                          | 설명                         |
|---------|------------------------------|------------------------------|
| GET     | /board/list?keyword=&page=   | 게시판 목록 조회 페이지 이동 |
| GET     | /board/detail/{id}           | 게시글 조회 페이지 이동      |
| POST    | /board                       | 게시글 등록 페이지 이동      |
| PUT     | /board/{id}                  | 게시글 수정 페이지 이동      |
| DELETE  | /board/{id}                  | 게시글 삭제 페이지 이동      |


REST API (BoardAPIController)

| HTTP    | URL                         | 설명                                       |
|---------|-----------------------------|--------------------------------------------|
| GET     | /api/board?keyword=&page=   | keyword, page 기반 게시판 목록 데이터 조회 |
| GET     | /api/board/detail/{id}      | 게시글 상세 데이터 조회                    |
| POST    | /api/board                  | 게시글 등록                                |
| PUT     | /api/board/{id}             | 게시글 수정                                |
| DELETE  | /api/board/{id}             | 게시글 삭제                                |
---

## 파일 구성

```bash
\---src
+---main
|   +---java
|   |   \---com
|   |       \---example
|   |           \---board
|   |               |   BoardApplication.java
|   |               |
|   |               +---controller
|   |               |       BoardViewController.java
|   |               |       BoardAPIController.java
|   |               |
|   |               +---dto
|   |               |       BoardCreateRequestDTO.java
|   |               |       BoardDetailResponse.java
|   |               |       BoardUpdateRequest.java
|   |               |
|   |               +---mapper
|   |               |       BoardMapper.java
|   |               |
|   |               \---service
|   |                       BoardService.java
|   |                       BoardServiceImple.java
|   |
|   \---resources
|       |   application.yaml
|       |   schema.sql
|       |
|       +---css
|       |       board.css
|       |
|       +---js
|       |       detail.js
|       |       insert.js
|       |       list.js
|       |       update.js
|       |
|       +---mapper
|       |       BoardMapper.xml
|       |
|       \---templates
|               delete.html
|               detail.html
|               insert.html
|               list.html
|               update.html
```

