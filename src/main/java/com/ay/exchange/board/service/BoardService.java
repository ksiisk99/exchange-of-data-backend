package com.ay.exchange.board.service;

import com.ay.exchange.board.dto.query.BoardInfoDto;
import com.ay.exchange.board.dto.request.WriteRequest;
import com.ay.exchange.board.dto.response.BoardResponse;
import com.ay.exchange.board.entity.Board;
import com.ay.exchange.board.entity.BoardContent;
import com.ay.exchange.board.entity.vo.*;
import com.ay.exchange.board.repository.BoardContentRepository;
import com.ay.exchange.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardContentRepository boardContentRepository;
    private final String REGEX="[0-9]+";

    public void writeBoard(WriteRequest writeRequest) {
        BoardCategory boardCategory = BoardCategory.builder()
                .category(getCategory(writeRequest.getCategoryDto().getCategory()))
                .departmentType(getDepartmentType(writeRequest.getCategoryDto().getDepartmentType()))
                .fileType(getFileType(writeRequest.getCategoryDto().getFileType()))
                .gradeType(getGradeType(writeRequest.getCategoryDto().getGradeType()))
                .subjectName(writeRequest.getCategoryDto().getSubjectName())
                .professorName(writeRequest.getCategoryDto().getProfessorName())
                .build();

        Board board = Board.builder()
                .title(writeRequest.getTitle())
                .writer(writeRequest.getWriter())
                .numberOfFilePages(writeRequest.getNumberOfFilePages())
                .numberOfSuccessfulExchanges(0)
                .approval(false)
                .views(1)
                .boardCategory(boardCategory)
                .build();
        boardRepository.save(board);

        BoardContent boardContent = BoardContent.builder()
                .content(writeRequest.getContent())
                .filePath("PATH")
                .board(board)
                .build();
        boardContentRepository.save(boardContent);

    }

    private GradeType getGradeType(int gradeType) {
        switch (gradeType) {
            case 0:
                return GradeType.Freshman;
            case 1:
                return GradeType.Sophomore;
            case 2:
                return GradeType.Junior;
            case 3:
                return GradeType.Senior;
            default:
                return null;
        }
    }

    private FileType getFileType(int fileType) {
        switch (fileType) {
            case 0:
                return FileType.중간고사;
            case 1:
                return FileType.기말고사;
            case 2:
                return FileType.과제;
            case 3:
                return FileType.요약;
            default:
                return null;
        }
    }

    private DepartmentType getDepartmentType(int departmentType) {
        switch (departmentType) {
            case 0:
                return DepartmentType.신학과;
            case 1:
                return DepartmentType.기독교교육과;
            case 2:
                return DepartmentType.국어국문학과;
            case 3:
                return DepartmentType.영미언어문화학과;
            case 4:
                return DepartmentType.러시아언어문화학과;
            case 5:
                return DepartmentType.중국언어문화학과;
            case 6:
                return DepartmentType.유아교육과;
            case 7:
                return DepartmentType.공연예술학과;
            case 8:
                return DepartmentType.음악학과;
            case 9:
                return DepartmentType.디지털미디어디자인학과;
            case 10:
                return DepartmentType.화장품발명디자인학과;
            case 11:
                return DepartmentType.뷰티메디컬디자인학과;
            case 12:
                return DepartmentType.글로벌경영학과;
            case 13:
                return DepartmentType.행정학과;
            case 14:
                return DepartmentType.관광경영학과;
            case 15:
                return DepartmentType.식품영양학과;
            case 16:
                return DepartmentType.컴퓨터공학과;
            case 17:
                return DepartmentType.정보전기전자공학과;
            case 18:
                return DepartmentType.통계데이터사이언스학과;
            case 19:
                return DepartmentType.소프트웨어학과;
            case 20:
                return DepartmentType.도시정보공학과;
            case 21:
                return DepartmentType.환경에너지공학과;
            case 22:
                return DepartmentType.AI융합학과;
            default:
                return null;
        }
    }

    private Category getCategory(int category) {
        switch (category) {
            case 0:
                return Category.신학대학;
            case 1:
                return Category.인문대학;
            case 2:
                return Category.예술체육대학;
            case 3:
                return Category.사회과학대학;
            case 4:
                return Category.창의융합대학;
            case 5:
                return Category.인성양성;
            case 6:
                return Category.리더십;
            case 7:
                return Category.융합실무;
            case 8:
                return Category.문제해결;
            case 9:
                return Category.글로벌;
            case 10:
                return Category.의사소통;
            case 11:
                return Category.논문;
            case 12:
                return Category.자격증;
            default:
                return null;
        }
    }


    public BoardResponse getBoardList(Integer page, int category,
                                      String department, String grade, String type
    ) {
        PageRequest pageRequest = PageRequest.of(page > 0 ? (page - 1) : 1, 2,
                Sort.by(Sort.Direction.DESC, "id"));

        Page<BoardInfoDto> pages = boardRepository.findBoards(
                false //추후 approval true로 변경해야함
                , getCategory(category)
                , pageRequest
                , getSeparateDepartmentConditions(department)
                , getSeparateGradeConditions(grade)
                , getSeparateTypeConditions(type));

//        System.out.println(pages.getTotalPages());
//        System.out.println(pages.getTotalElements());
//        System.out.println(pages.getNumber());

        return new BoardResponse(pages.getTotalPages(), pages.getContent());
    }

    private List<String> getSeparateTypeConditions(String type) {
        return Arrays.stream(type.split(","))
                .filter(t->t.matches(REGEX))
                .map(t->Integer.parseInt(t))
                .filter(t->(t>=0 && t<=3))
                .map(t->getFileType(t).name())
                .collect(Collectors.toList());
    }

    private List<String> getSeparateGradeConditions(String grade) {
        return Arrays.stream(grade.split(","))
                .filter(g->g.matches(REGEX))
                .map(g->Integer.parseInt(g))
                .filter(g->(g>=0 && g<=3))
                .map(g->getGradeType(g).name())
                .collect(Collectors.toList());
    }

    private List<String> getSeparateDepartmentConditions(String department) {
        return Arrays.stream(department.split(","))
                .filter(d->d.matches(REGEX))
                .map(d->Integer.parseInt(d))
                .filter(d->(d>=0 && d<=22)) //[하드코딩 리팩토링] 구현이 바뀔수도 있어서 나중에 할 예정
                .map(d-> getDepartmentType(d).name())
                .collect(Collectors.toList());
    }

}
