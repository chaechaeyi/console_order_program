package kr.co._29cm.homework.domain;

import jakarta.persistence.*;
import kr.co._29cm.homework.constant.ProgramStringMassage;
import kr.co._29cm.homework.error.ErrorMassage;
import kr.co._29cm.homework.exception.SoldOutException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 상품 정보 entity
 */
@Getter
@ToString
@Table
@Entity
public class Item extends AuditingFields {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id; // 상품id
    @Setter
    @Column(nullable = false, length = 500)
    private String name; // 상품명
    @Setter
    @Column(nullable = false)
    private int price; // 판매가
    @Setter
    @Column(nullable = false)
    private int stockQuantity; // 수량


    protected Item() {
    }

    private Item(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public static Item of(String name, int price, int stockQuantity) {
        return new Item(name, price, stockQuantity);
    }

    //stock 감소
    public void reduceItemStockQuantity(int orderQuantity){
        int restQuantity = this.stockQuantity -orderQuantity;
        if(restQuantity<0){
            throw new SoldOutException(ErrorMassage.SOLD_OUT_EXCETION.getMsg());
        }
        this.stockQuantity = restQuantity;
    }
}
