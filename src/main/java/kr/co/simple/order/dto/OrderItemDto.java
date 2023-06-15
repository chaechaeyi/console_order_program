package kr.co.simple.order.dto;

public record OrderItemDto(
        Long itemId,
        int orderQuantity
) {
    public static OrderItemDto of( Long itemId, int orderQuantity) {
        return new OrderItemDto(itemId, orderQuantity);
    }
}
