package com.qfit.board_boot_jpa.service;

import com.qfit.board_boot_jpa.dto.BoardDTO;
import com.qfit.board_boot_jpa.entity.BoardEntity;
import com.qfit.board_boot_jpa.entity.BoardFileEntity;
import com.qfit.board_boot_jpa.repository.BoardFileRepository;
import com.qfit.board_boot_jpa.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
//DTO -> Entity: Repository로 Entity로 넘겨준다. (Entity Class)
//Entity -> DTO: Controller에서 호출 시에는 DTO로 넘겨준다. (DTO Class)
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardFileRepository boardFileRepository;

    public void save(BoardDTO boardDTO) throws IOException {
        //파일 첨부 여부에 따라 로직 분리
        if (boardDTO.getBoardFile().isEmpty()) {
            //첨부 파일 없음
            BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
            boardRepository.save(boardEntity);
        } else {
            //첨부 파일 있음
            /*
             1. DTO에 담긴 파일을 꺼냄
             2. 파일의 이름을 가져옴
             3. 서버 저장용 이름을 만듦
             //내사진.jpg => 34634674376_내사진.jpg
             4. 저장 경로 설정

             5. 해당 경로에 파일 저장
             6. board_table에 해당 데이터 save 처리
             7. board_file_table에 해당 데이터 save 처리
            * */

            //같은 부모(게시글)을 가진 자식(파일)이 여러개일 수 있으므로 부모 데이터를 먼저 저장한다.
            BoardEntity boardEntity = BoardEntity.toSaveFileEntity(boardDTO); //id값이 없기 때문에 밑에서 다시 가져온다.
            Long savedId = boardRepository.save(boardEntity).getId(); //부모게시글에 대한 pk값, boardId
            BoardEntity board = boardRepository.findById(savedId).get();

            for (MultipartFile boardFile : boardDTO.getBoardFile()) {
//                MultipartFile boardFile = boardDTO.getBoardFile();
                String originalFilename = boardFile.getOriginalFilename();
                String storedFileName = System.currentTimeMillis() + "_" + originalFilename;
                String savePath = "C:/Users/thdwl/study/spring_upload_files/" + storedFileName;
                boardFile.transferTo(new File(savePath));

                BoardFileEntity boardFileEntity = BoardFileEntity.toBoardFileEntity(board, originalFilename, storedFileName);
                boardFileRepository.save(boardFileEntity);
            }
        }//end else
    }

    //데이터베이스의 상태를 변화시키기 위해서 수행하는 작업의 단위
    @Transactional
    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for (BoardEntity boardEntity : boardEntityList) {
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }
        return boardDTOList;
    }

    @Transactional
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    @Transactional
    public BoardDTO findById(Long id) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity);
            return boardDTO;
        } else {
            return null;
        }
    }

    public BoardDTO update(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardDTO);
        boardRepository.save(boardEntity);
        return findById(boardDTO.getId());
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    public Page<BoardDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1; //볼 페이지
        int pageLimit = 3; //한 페이지에 보여줄 글 갯수
        //한 페이지당 3개씩 글을 보여주고 정렬 기준은 entity의 id 기준으로 내림차순 정렬
        //page 위치에 있는 값은 0부터 시작
        Page<BoardEntity> boardEntities = boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));

        System.out.println("boardEntities.getContent() = " + boardEntities.getContent()); //요청 페이지에 해당하는 글
        System.out.println("boardEntities.getTotalElements() = " + boardEntities.getTotalElements()); //전체 글 갯수
        System.out.println("boardEntities.getNumber() = " + boardEntities.getNumber()); //DB로 요청한 페이지 번호
        System.out.println("boardEntities.getTotalPages() = " + boardEntities.getTotalPages()); //전체 페이지 갯수
        System.out.println("boardEntities.getSize() = " + boardEntities.getSize()); //한 페이지에 보여지는 글 갯수
        System.out.println("boardEntities.hasPrevious() = " + boardEntities.hasPrevious()); //이전 페이지 존재 여부
        System.out.println("boardEntities.isFirst() = " + boardEntities.isFirst()); //첫 페이지 여부
        System.out.println("boardEntities.isLast() = " + boardEntities.isLast()); //마지막 페이지 여부

        //entity 객체를 하나씩 꺼내어 DTO객체로 담는다. page 정보를 모두 갖게 되었다. 목록: id, writer, title, hits, createdTime
        Page<BoardDTO> boardDTOS = boardEntities.map(board -> new BoardDTO(board.getId(), board.getBoardWriter(), board.getBoardTitle(), board.getBoardHits(), board.getCreatedTime()));
        return boardDTOS;
    }
}
