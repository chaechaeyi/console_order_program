package kr.co._29cm.homework.domainn;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

/**
 * 상품 정보
 */
@Getter
@ToString
@Table
@Entity
public class Item extends AuditingFields {
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

    protected Item() {
    }

    private Item(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public static Item of(String name, int price, int quantity) {
        return new Item(name, price, quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item item)) return false;
        return id != null && id.equals(item.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
