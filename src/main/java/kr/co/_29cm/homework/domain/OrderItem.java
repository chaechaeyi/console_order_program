package kr.co._29cm.homework.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 주문 정보 상세 entity
 */
@Getter
@ToString
@Table
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 주문 상세  id

    @Setter
    @ManyToOne(optional = false)
    @JoinColumn(name="order_id")
    private Order order; // 주문id
    @Setter
    @ManyToOne(optional = false)
    @JoinColumn(name="item_id")
    private Item item; // 상품id
    
    @Setter
    @Column(nullable = false)
    private int price; // 상품 주문 가격

    @Setter
    @Column(nullable = false)
    private int count; // 상품 주문수량

    public OrderItem() {
    }

    private OrderItem(Order order, Item item, int price, int count) {
        this.order = order;
        this.item = item;
        this.price = price;
        this.count = count;
    }

    public static OrderItem of(Order order, Item item, int price, int count) {
        return new OrderItem(order, item, price, count);
    }

}
