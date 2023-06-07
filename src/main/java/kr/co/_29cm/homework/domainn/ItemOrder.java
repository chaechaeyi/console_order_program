package kr.co._29cm.homework.domainn;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

/**
 * 주문 정보
 */
@Getter
@ToString
@Table
@Entity
public class ItemOrder extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 주문id
    @Setter
    @ManyToOne(optional = false)
    private Item item; // 상품id
    @Setter
    @Column(nullable = false, length = 50)
    private String status; // 주문상태
    @Setter
    @Column(nullable = false)
    private int totalPrice; // 총가격
    @Setter
    @Column(nullable = false)
    private int orderCnt; // 주문수량

    public ItemOrder() {
    }

    private ItemOrder(Item item, String status, int totalPrice, int orderCnt) {
        this.item = item;
        this.status = status;
        this.totalPrice = totalPrice;
        this.orderCnt = orderCnt;
    }

    public static ItemOrder of(Item item, String status, int totalPrice, int orderCnt) {
        return new ItemOrder(item, status, totalPrice, orderCnt);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof ItemOrder itemOrder)) return false;
        return id != null && id.equals(itemOrder.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
