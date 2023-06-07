package kr.co._29cm.homework.domainn;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 주문 정보
 */
@Getter
@ToString
@Table
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 주문id

    @Setter @ManyToOne(optional = false) private Items items; // 상품id
    @Setter @Column(nullable = false, length = 50) private String status; // 주문상태
    @Setter @Column(nullable = false) private int totalPrice; // 총가격
    @Setter @Column(nullable = false) private int orderCnt; // 주문수량

    @CreatedDate @Column(nullable = false) private LocalDateTime orderRegAt; // 주문일시
    @CreatedBy @Column(nullable = false, length = 100) private String orderRegBy; // 주문자
    @LastModifiedDate @Column(nullable = false) private LocalDateTime orderModAt; // 주문수정일
    @LastModifiedBy @Column(nullable = false, length = 100) private String orderModBy; // 주문수정자

    public Orders() {}

    private Orders(Items items, String status, int totalPrice, int orderCnt) {
        this.items = items;
        this.status = status;
        this.totalPrice = totalPrice;
        this.orderCnt = orderCnt;
    }

    public static Orders of(Items items, String status, int totalPrice, int orderCnt){
        return new Orders(items, status, totalPrice, orderCnt);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Orders orders)) return false;
        return id != null && id.equals(orders.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
