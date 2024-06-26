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
7. 파일(이미지) 첨부하기
- 단일 파일 첨부
- 다중 파일 첨부
