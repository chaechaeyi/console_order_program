package kr.co._29cm.homework.domain;

import jakarta.persistence.*;
import kr.co._29cm.homework.domain.costant.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 주문 정보 etity
 */
@Getter
@ToString
@Table(name = "orders")
@Entity
public class Order extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    public Order() {
    }

    private Order(OrderStatus status, int totalPrice, int deliveryPrice) {
        this.status = status;
        this.totalPrice = totalPrice;
        this.deliveryPrice = deliveryPrice;
    }

    public static Order of(OrderStatus status, int totalPrice) {
        return new Order(status, totalPrice, calculateDeleveryPrice(totalPrice));
    }
    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Order order)) return false;
        return id != null && id.equals(order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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

