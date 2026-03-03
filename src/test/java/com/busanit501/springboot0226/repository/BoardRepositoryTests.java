package com.busanit501.springboot0226.repository;

import com.busanit501.springboot0226.domain.Board;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testInsert() {
        // 더미 데이터 100개를 임의로 작성, 하드코딩. , 반목문을 이용해서, 추가 할 예정.
        IntStream.rangeClosed(1,100).forEach(i -> {
            // 더미 엔티티 객체 생성, 빌드 패턴이용.
            Board board = Board.builder()
                    .title("더미 제목...title" + i)
                    .content("더미 내용...content" + i)
                    .writer("더미 사용자...user" + (i % 10))
                    .build();
            // boardRepository 의 기능들 중에서, save 라는 메서드를 이용해서, 실제 디비에 저장.
            Board result = boardRepository.save(board);
            log.info("결과 확인: " + result.getBno());
        });
    }

    @Test
    public void testSelect() {
        Long bno = 100L;
        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow();
        log.info("board 확인 : " +board );
    }

    @Test
    public void testUpdate() {
        Long bno = 100L;
        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow();
        // 수정 메서드를 호출해서, 변경 후, 저장 및 수정.
        board.change("수정 테스트 제목", "수정 테스트 내용");
        boardRepository.save(board);
    }

    @Test
    public void testDelete() {
        Long bno = 100L;
        boardRepository.deleteById(bno);
    }

    @Test
    public void testPaging() {
        // 1 페이지, 정렬 내림차순,
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        Page<Board> result = boardRepository.findAll(pageable);
        // result 결과에는 다양한 페이징 준비물이 들어있다.
        log.info("전체 갯수 result.getTotalElements() : " + result.getTotalElements());
        log.info("전체 페이지 result.getTotalPages() : " + result.getTotalPages());
        log.info("조회 페이지 번호  result.getNumber() :  " + result.getNumber());
        log.info("조회 페이지 크기 result.getSize() :  " + result.getSize());

        // 페이징 처리가 된 10개의 데이터 목록도 있음.
        List<Board> todoList = result.getContent();
        log.info("페이징 처리가 된 10개 데이터 확인 result.getContent() : " + todoList);
    }
}
