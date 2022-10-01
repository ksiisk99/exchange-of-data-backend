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
                .largeCategory(getLargeCategory(writeRequest.getCategoryDto().getLargeCategory()))
                .mediumCategory(getMediumCategory(writeRequest.getCategoryDto().getMediumCategory()))
                .smallCategory(getSmallCategory(writeRequest.getCategoryDto().getSmallCategory()))
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

    private SmallCategory getSmallCategory(int smallCategory) {
        switch (smallCategory) {
            case 0:
                return SmallCategory.신학과;
            case 1:
                return SmallCategory.기독교교육과;
            case 2:
                return SmallCategory.국어국문학과;
            case 3:
                return SmallCategory.영미언어문화학과;
            case 4:
                return SmallCategory.러시아언어문화학과;
            case 5:
                return SmallCategory.중국언어문화학과;
            case 6:
                return SmallCategory.유아교육과;
            case 7:
                return SmallCategory.공연예술학과;
            case 8:
                return SmallCategory.음악학과;
            case 9:
                return SmallCategory.디지털미디어디자인학과;
            case 10:
                return SmallCategory.화장품발명디자인학과;
            case 11:
                return SmallCategory.뷰티메디컬디자인학과;
            case 12:
                return SmallCategory.글로벌경영학과;
            case 13:
                return SmallCategory.행정학과;
            case 14:
                return SmallCategory.관광경영학과;
            case 15:
                return SmallCategory.식품영양학과;
            case 16:
                return SmallCategory.컴퓨터공학과;
            case 17:
                return SmallCategory.정보전기전자공학과;
            case 18:
                return SmallCategory.통계데이터사이언스학과;
            case 19:
                return SmallCategory.소프트웨어학과;
            case 20:
                return SmallCategory.도시정보공학과;
            case 21:
                return SmallCategory.환경에너지공학과;
            case 22:
                return SmallCategory.AI융합학과;
            default:
                return null;
        }
    }

    private MediumCategory getMediumCategory(int mediumCategory) {
        switch (mediumCategory) {
            case 0:
                return MediumCategory.신학대학;
            case 1:
                return MediumCategory.인문대학;
            case 2:
                return MediumCategory.예술체육대학;
            case 3:
                return MediumCategory.사회과학대학;
            case 4:
                return MediumCategory.창의융합대학;
            case 5:
                return MediumCategory.인성양성;
            case 6:
                return MediumCategory.리더십;
            case 7:
                return MediumCategory.융합실무;
            case 8:
                return MediumCategory.문제해결;
            case 9:
                return MediumCategory.글로벌;
            case 10:
                return MediumCategory.의사소통;
            case 11:
                return MediumCategory.논문;
            case 12:
                return MediumCategory.자격증;
            default:
                return null;
        }
    }

    private LargeCategory getLargeCategory(int largeCategory) {
        switch (largeCategory) {
            case 0:
                return LargeCategory.Major;
            case 1:
                return LargeCategory.GE;
            case 2:
                return LargeCategory.Etc;
            default:
                return null;
        }
    }

    public BoardResponse getBoardList(Integer page, int mediumCategory,
                                      String department, String grade, String type
    ) {
        PageRequest pageRequest = PageRequest.of(page > 0 ? (page - 1) : 1, 2,
                Sort.by(Sort.Direction.DESC, "id"));

        Page<BoardInfoDto> pages = boardRepository.findBoards(
                false //추후 approval true로 변경해야함
                , getMediumCategory(mediumCategory)
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
                .map(d->getSmallCategory(d).name())
                .collect(Collectors.toList());
    }

}
