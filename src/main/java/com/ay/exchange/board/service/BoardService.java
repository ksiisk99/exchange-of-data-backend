package com.ay.exchange.board.service;

import com.ay.exchange.board.dto.CategoryDto;
import com.ay.exchange.board.dto.request.DeleteRequest;
import com.ay.exchange.board.dto.request.WriteRequest;
import com.ay.exchange.board.entity.Board;
import com.ay.exchange.board.entity.BoardContent;
import com.ay.exchange.board.entity.DesiredBoard;
import com.ay.exchange.board.entity.vo.*;
import com.ay.exchange.board.repository.BoardContentRepository;
import com.ay.exchange.board.repository.BoardRepository;
import com.ay.exchange.board.repository.DesiredBoardRepository;
import com.ay.exchange.user.exception.InvalidUserRoleException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final DesiredBoardRepository desiredBoardRepository;
    private final BoardContentRepository boardContentRepository;

    public void writeBoard(WriteRequest writeRequest) {
        BoardCategory boardCategory=BoardCategory.builder()
                .largeCategory(getLargeCategory(writeRequest.getCategoryDto().getLargeCategory()))
                .mediumCategory(getMediumCategory(writeRequest.getCategoryDto().getMediumCategory()))
                .smallCategory(getSmallCategory(writeRequest.getCategoryDto().getSmallCategory()))
                .fileType(getFileType(writeRequest.getCategoryDto().getFileType()))
                .gradeType(getGradeType(writeRequest.getCategoryDto().getGradeType()))
                .subjectName(writeRequest.getCategoryDto().getSubjectName())
                .professorName(writeRequest.getCategoryDto().getProfessorName())
                .build();

        Board board=Board.builder()
                .title(writeRequest.getTitle())
                .writer(writeRequest.getWriter())
                .numberOfFilePages(writeRequest.getNumberOfFilePages())
                .numberOfSuccessfulExchanges(0)
                .approval(false)
                .views(1L)
                .category(boardCategory)
                .build();
        boardRepository.save(board);

        BoardContent boardContent= BoardContent.builder()
                .content(writeRequest.getContent())
                .filePath("PATH")
                .board(board)
                .build();
        boardContentRepository.save(boardContent);

        for(CategoryDto dto:writeRequest.getDesiredBoards()){
            BoardCategory desiredCategory=BoardCategory.builder()
                    .largeCategory(getLargeCategory(dto.getLargeCategory()))
                    .mediumCategory(getMediumCategory(dto.getMediumCategory()))
                    .smallCategory(getSmallCategory(dto.getSmallCategory()))
                    .fileType(getFileType(dto.getFileType()))
                    .gradeType(getGradeType(dto.getGradeType()))
                    .subjectName(dto.getSubjectName())
                    .professorName(dto.getProfessorName())
                    .build();

            desiredBoardRepository.save(DesiredBoard.builder()
                    .category(desiredCategory)
                    .board(board)
                    .boardContent(boardContent)
                    .build());
        }
    }

    public void deleteBoard(String token, DeleteRequest deleteRequest) {
        boardContentRepository.deleteById(deleteRequest.getBoardContentId());
//        if(isAuthorized(token)){
//            boardContentRepository.deleteByBoardId(deleteRequest.getBoardId());
//        }else{
//            throw new InvalidUserRoleException();
//        }
    }

//    private boolean isAuthorized(String token) { //삭제기능에서 이 메소드가 무조건 사용됨 중복코드 나중에 리팩토링
//        return jwtTokenProvider.getEmail(token).equals(token);
//    }

    private GradeType getGradeType(int gradeType) {
        switch (gradeType){
            case 0:
                return GradeType.Freshman;
            case 1:
                return GradeType.Sophomore;
            case 2:
                return GradeType.Junior;
            default:
                return GradeType.Senior;
        }
    }

    private FileType getFileType(int fileType) {
        switch(fileType){
            case 0:
                return FileType.중간고사;
            case 1:
                return FileType.기말고사;
            case 2:
                return FileType.과제;
            default:
                return FileType.요약;
        }
    }

    private SmallCategory getSmallCategory(int smallCategory) {
        switch(smallCategory){
            case 0:
                return SmallCategory.식품영양학과;
            case 1:
                return SmallCategory.컴퓨터공학과;
            case 2:
                return SmallCategory.정보전기전자공학과;
            case 3:
                return SmallCategory.통계데이터사이언스학과;
            case 4:
                return SmallCategory.소프트웨어학과;
            case 5:
                return SmallCategory.도시정보공학과;
            case 6:
                return SmallCategory.환경에너지공학과;
            case 7:
                return SmallCategory.AI융합학과;
            case 8:
                return SmallCategory.글로벌경영학과;
            case 9:
                return SmallCategory.행정학과;
            case 10:
                return SmallCategory.관광경영학과;
            case 11:
                return SmallCategory.공연예술학과;
            case 12:
                return SmallCategory.음악학과;
            case 13:
                return SmallCategory.디지털미디어디자인학과;
            case 14:
                return SmallCategory.화장품발명디자인학과;
            case 15:
                return SmallCategory.뷰티메디컬디자인학과;
            case 16:
                return SmallCategory.국어국문학과;
            case 17:
                return SmallCategory.영미언어문화학과;
            case 18:
                return SmallCategory.러시아언어문화학과;
            case 19:
                return SmallCategory.중국언어문화학과;
            case 20:
                return SmallCategory.유아교육과;
            case 21:
                return SmallCategory.신학과;
            case 22:
                return SmallCategory.기독교교육과;
            case 23:
                return SmallCategory.문제해결;
            case 24:
                return SmallCategory.융합실무;
            case 25:
                return SmallCategory.의사소통;
            case 26:
                return SmallCategory.인성양성;
            case 27:
                return SmallCategory.글로벌;
            case 28:
                return SmallCategory.리더십;
            default:
                return SmallCategory.기타;
        }
    }

    private MediumCategory getMediumCategory(int mediumCategory) {
        switch(mediumCategory){
            case 0:
                return MediumCategory.창의융합대학;
            case 1:
                return MediumCategory.사회과학대학;
            case 2:
                return MediumCategory.예술체육대학;
            case 3:
                return MediumCategory.인문대학;
            case 4:
                return MediumCategory.신학대학;
            case 5:
                return MediumCategory.아리교양대학;
            case 6:
                return MediumCategory.논문;
            default:
                return MediumCategory.자격증;
        }
    }

    private LargeCategory getLargeCategory(int largeCategory) {
        switch(largeCategory){
            case 0:
                return LargeCategory.Major;
            case 1:
                return LargeCategory.GE;
            default:
                return LargeCategory.Etc;
        }
    }
}
