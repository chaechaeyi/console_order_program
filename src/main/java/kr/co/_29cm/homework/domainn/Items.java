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
 * 상품 정보
 */
@Getter
@ToString
@Table
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 상품id

    @Setter
    @Column(nullable = false, length = 500)
    private String name; // 상품명
    @Setter
    @Column(nullable = false)
    private int price; // 판매가
    @Setter
    @Column(nullable = false)
    private int quantity; // 수량

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime regAt; // 등록일시
    @CreatedBy
    @Column(nullable = false, length = 100)
    private String regBy; // 등록자
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modAt; // 수정일시
    @LastModifiedBy
    @Column(nullable = false, length = 100)
    private String modBy; // 수정자

    protected Items() {
    }

    private Items(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public static Items of(String name, int price, int quantity) {
        return new Items(name, price, quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Items items)) return false;
        return id != null && id.equals(items.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
