package kr.co.simple.order.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 에러 메시지 관리
 */
@Getter
@AllArgsConstructor
public enum ErrorMassage {
    SOLD_OUT_EXCETION("SoldOutException 발생. 주문한 상품량이 재고량보다 큽니다." );

    private String msg;
}
