package com.ay.exchange.board.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CategoryDto {
    @Schema(description = "전공(0), 교양(1), 기타(2)")
    private int largeCategory;

    @Schema(description = "신학대학(0), 인문대학(1), 예술체육대학(2), 사회과학대학(3), 창의융합대학(4), " +
            "인성양성(5), 리더십(6), 융합실무(7), 문제해결(8), 글로벌(9), 의사소통(10),논문(11), 자격증(12)")
    private int mediumCategory;

    @Schema(description = "신학과(0), 기독교교육과(1), 국어국문학과(2), 영미언어문화학과(3), " +
            "러시아언어문화학과(4), 중국언어문화학과(5), 유아교육과(6), 공연예술학과(7), 음악학과(8), " +
            "디지털미디어디자인학과(9), 화장품발명디자인학과(10), 뷰티메디컬디자인학과(11), " +
            "글로벌경영학과(12), 행정학과(13), 관광경영학과(14), 식품영양학과(15), " +
            "컴퓨터공학과(16), 정보전기전자공학과(17), 통계데이터사이언스학과(18), 소프트웨어학과(19), " +
            "도시정보공학과(20), 환경에너지공학과(21), AI융합학과(22)")
    private int smallCategory;

    @Schema(description = "중간고사(0), 기말고사(1), 과제(2), 요약(3)")
    private int fileType;

    @Schema(description = "Freshman(0), Sophomore(1), Junior(2), Senior(3)")
    private int gradeType;

    @Schema(description = "전공 또는 교양 선택 시 과목명 입력")
    private String subjectName;

    @Schema(description = "전공 또는 교양 선택 시 교수명 입력")
    private String professorName;
}
