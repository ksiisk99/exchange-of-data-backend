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
                return FileType.????????????;
            case 1:
                return FileType.????????????;
            case 2:
                return FileType.??????;
            case 3:
                return FileType.??????;
            default:
                return null;
        }
    }

    private DepartmentType getDepartmentType(int departmentType) {
        switch (departmentType) {
            case 0:
                return DepartmentType.?????????;
            case 1:
                return DepartmentType.??????????????????;
            case 2:
                return DepartmentType.??????????????????;
            case 3:
                return DepartmentType.????????????????????????;
            case 4:
                return DepartmentType.???????????????????????????;
            case 5:
                return DepartmentType.????????????????????????;
            case 6:
                return DepartmentType.???????????????;
            case 7:
                return DepartmentType.??????????????????;
            case 8:
                return DepartmentType.????????????;
            case 9:
                return DepartmentType.?????????????????????????????????;
            case 10:
                return DepartmentType.??????????????????????????????;
            case 11:
                return DepartmentType.??????????????????????????????;
            case 12:
                return DepartmentType.?????????????????????;
            case 13:
                return DepartmentType.????????????;
            case 14:
                return DepartmentType.??????????????????;
            case 15:
                return DepartmentType.??????????????????;
            case 16:
                return DepartmentType.??????????????????;
            case 17:
                return DepartmentType.???????????????????????????;
            case 18:
                return DepartmentType.?????????????????????????????????;
            case 19:
                return DepartmentType.?????????????????????;
            case 20:
                return DepartmentType.?????????????????????;
            case 21:
                return DepartmentType.????????????????????????;
            case 22:
                return DepartmentType.AI????????????;
            default:
                return null;
        }
    }

    private Category getCategory(int category) {
        switch (category) {
            case 0:
                return Category.????????????;
            case 1:
                return Category.????????????;
            case 2:
                return Category.??????????????????;
            case 3:
                return Category.??????????????????;
            case 4:
                return Category.??????????????????;
            case 5:
                return Category.????????????;
            case 6:
                return Category.?????????;
            case 7:
                return Category.????????????;
            case 8:
                return Category.????????????;
            case 9:
                return Category.?????????;
            case 10:
                return Category.????????????;
            case 11:
                return Category.??????;
            case 12:
                return Category.?????????;
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
                false //?????? approval true??? ???????????????
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
                .filter(d->(d>=0 && d<=22)) //[???????????? ????????????] ????????? ???????????? ????????? ????????? ??? ??????
                .map(d-> getDepartmentType(d).name())
                .collect(Collectors.toList());
    }

}
