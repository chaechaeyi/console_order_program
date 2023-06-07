package kr.co._29cm.homework.domainn;

import java.time.LocalDateTime;

/**
 * 상품 정보
 */
public class Item {
    private Long id; // 상품id
    private String name; // 상품명
    private int price; // 판매가
    private int quantity; // 수량
    private LocalDateTime regDate; // 등록일
}
