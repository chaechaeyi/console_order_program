package kr.co._29cm.homework.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 주문 메시지 관리
 */
@Getter
@AllArgsConstructor
public enum ProgramStringMassage {
    ORDER_INIT("입력(o[order]: 주문, q[quit]: 종료) : "),
    ITEM_LIST_TITLE("상품정보    상품명             판매가격    재고수"),
    INPUT_ITEM_ID("상품번호 : "),
    INPUT_ITEM_QUANTITY("수량 : "),
    IS_NOT_EXIST_ITEM("존재하지 않는 상품입니다."),
    IS_NOT_EXIST_ORDER_ITEM("주문 대상이 없습니다."),
    IS_WRONG_INPUT("잘못 된 입력 입니다."),
    IS_WRONG_OPERATION("올바른 명령어가 아닙니다."),
    ORDER_FAIL("주문이 실패하였습니다."),
    ORDER_INFO_TITLE("주문내역 : "),
    ORDER_PRICE("주문금액"),
    DELIVERY_PRICE("배송비"),
    PAY_PRICE("지불금액"),
    KOREA_MONEY_UNIT("원"),
    ORDER_COMPLITED("고객님의 주문 감사합니다.");

    private String msg;
}
