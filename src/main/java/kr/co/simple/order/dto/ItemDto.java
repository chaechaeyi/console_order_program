package kr.co.simple.order.dto;

import kr.co.simple.order.domain.Item;

public record ItemDto(
        Long id,
        String name,
        int price,
        int stockQuantity
) {
    public static ItemDto of(Long id, String name, int price, int stockQuantity) {
        return new ItemDto(id, name, price, stockQuantity);
    }

    public static ItemDto from(Item entity) {
        return new ItemDto(
                entity.getId(),
                entity.getName(),
                entity.getPrice(),
                entity.getStockQuantity()
        );
    }

    public Item toEntity(ItemDto itemDto) {
        return Item.of(
                name,
                price,
                stockQuantity
        );
    }
}
