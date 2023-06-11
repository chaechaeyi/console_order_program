package kr.co._29cm.homework.domain;

import jakarta.persistence.*;
import kr.co._29cm.homework.constant.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * 주문 정보 etity
 */
@Getter
@ToString
@Table(name = "orders")
@Entity
public class Order extends AuditingFields {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id; // 주문id
    @Setter
    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문상태
    @Setter
    @Column(nullable = false)
    private int totalPrice; // 총가격
    @Setter
    @Column(nullable = false)
    private int deliveryPrice; // 배송비

    @Setter
    @Column(nullable = false)
    private int payPrice; // 배송비

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderItem> orderItems = new ArrayList<>();

    public Order() {
    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
    private Order(OrderStatus status, int totalPrice, int deliveryPrice,  List<OrderItem> orderItems) {
        this.status = status;
        this.totalPrice = totalPrice;
        this.deliveryPrice = deliveryPrice;
        this.payPrice = totalPrice + deliveryPrice;
        for(OrderItem orderItem : orderItems){
            this.addOrderItem(orderItem);
        }
    }
    public static Order of(OrderStatus status, List<OrderItem> orderItems) {
        int totalPrice = orderItems.stream().mapToInt(OrderItem::getPrice).sum();
        return new Order(status, totalPrice, calculateDeleveryPrice(totalPrice), orderItems);
    }

    /**
     * 배송비 계산
     * @param totalPrice
     * @return
     */
    private static int calculateDeleveryPrice(int totalPrice){
        final int MIN_PRICE = 50000;
        final int DELEVERY_PRICE = 2500;
        return totalPrice>=MIN_PRICE?0:DELEVERY_PRICE;
    }
}

