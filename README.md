
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

---

## API 명세


| HTTP    | URL                   | 설명                    |
|---------|-----------------------|-------------------------|
| GET     | /board/list           | 게시판 목록 조회        |
| GET     | /board/list?keyword=  | 게시판 목록 검색 (제목) |
| GET     | /board/detail/{id}    | 게시글 조회             |
| POST    | /board/insert         | 게시글 등록             |
| PUT     | /board/{id}           | 게시글 수정             |
| DELETE  | /board/{id}           | 게시글 삭제             |

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
|   |               |       BoardController.java
|   |               |
|   |               +---dto
|   |               |       BoardDTO.java
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

