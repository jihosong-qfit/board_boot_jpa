# 개발환경
1. IDE: IntelliJ IDEA Community
2. Spring Boot 3.2.7
3. JDK 17
4. mysql
5. Spring Data JPA
6. Thymeleaf

# 회원 주요기능
1. 회원가입
2. 로그인
3. 회원목록 조회
4. 회원조회
5. 회원정보 수정
6. 회원삭제
7. 로그아웃

# 회원 추가기능
1. ajax를 활용한 이메일(아이디) 중복체크하기
2. 아이디, 패스워드 유효성 검사
3. spring security 적용
4. jwt
5. oauth

# 게시판 주요기능
1. 글쓰기(/board/save)
2. 글목록(/board/)
3. 글조회(/board/{id})
4. 글수정(/board/update/{id})
   - 상세화면에서 수정 버튼 클릭
   - 서버에서 해당 게시글의 정보를 가지고 수정 화면 출력
   - 제목, 내용을 수정 입력 받아서 서버로 요청
   - 수정 처리
5. 글삭제(/board/delete/{id})
6. 페이징처리(/board/paging)
   - /board/paging?page=2
   - /board/paging/2
7. 파일(이미지) 첨부하기
- 단일 파일 첨부
- 다중 파일 첨부
- 파일 첨부와 관련하여 추가될 부분들
    - save.html
    - BoardDTO
    - BoardService.save()
    - BoardEntity
    - BoardFileEntity, BoardFileRepository 추가
    - detail.html
8. 댓글 처리하기
    - 글 상세 페이지에서 댓글 입력 (ajax)
    - 상세조회할 때 기존에 작성된 댓글목록이 보임
    - 댓글을 입력하면 기존 댓글 목록에 새로 작성한 댓글 추가
    - 댓글용 테이블도 필요
9. 추가기능: 조회수 중복방지, id/pw 유효성 검증, 검색, 대댓글, 좋아요, 알람설정, 실시간 채팅(상담), spring security, jwt, 화면 등

## mysql DataBase 계정 생성 및 권한 부여
create database db_board;
create user user_jihosong@localhost identified by '비밀번호';
grant all privileges on db_board.* to user_jihosong@localhost;