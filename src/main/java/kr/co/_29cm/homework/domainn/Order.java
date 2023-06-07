package kr.co._29cm.homework.domainn;

import java.time.LocalDateTime;

/**
 * 주문 정보
 */
public class Order {
    private Long id; // 주문id
    private Long itemId; // 상품id
    private String status; // 주문상태
    private int totalPrice; // 총가격
    private int orderCnt; // 주문수량
    private LocalDateTime orderDate; // 주문일시
}
