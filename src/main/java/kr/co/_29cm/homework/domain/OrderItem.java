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
    @Column(name = "order_item_id")
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
    private int price; // 상품 가격
    @Setter
    @Column(nullable = false)
    private int orderQuantity; // 상품 주문수량

    public OrderItem() {
    }

    private OrderItem(Item item, int price, int orderQuantity) {
        this.item = item;
        this.price = price;
        this.orderQuantity = orderQuantity;
    }

    public static OrderItem createOrderItem(Item item, int orderQuantity) {
        item.reduceItemStockQuantity(orderQuantity);
        return new OrderItem(item, item.getPrice(), orderQuantity);
    }

}
