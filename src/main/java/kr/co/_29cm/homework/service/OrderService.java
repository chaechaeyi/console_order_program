package kr.co._29cm.homework.service;

import jakarta.persistence.LockModeType;
import kr.co._29cm.homework.constant.OrderStatus;
import kr.co._29cm.homework.domain.Item;
import kr.co._29cm.homework.domain.Order;
import kr.co._29cm.homework.domain.OrderItem;
import kr.co._29cm.homework.dto.OrderItemDto;
import kr.co._29cm.homework.repository.ItemRepository;
import kr.co._29cm.homework.repository.OrderItemRepository;
import kr.co._29cm.homework.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 주문 service
 */
@Service
@Transactional(isolation = Isolation.SERIALIZABLE)
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    /**
     * 주문 생성
     * @param orderItemDtos
     * @return
     */
    public Long createOrder(List<OrderItemDto> orderItemDtos) {
        List<OrderItem> orderItems = new ArrayList<>();

        for(OrderItemDto orderItemDto : orderItemDtos) {
            Item item = itemRepository.getReferenceById(orderItemDto.itemId());
            OrderItem orderItem = OrderItem.createOrderItem(item, orderItemDto.orderQuantity());
            orderItems.add(orderItem);
        }

        Order order = Order.of(OrderStatus.COMPLETE, orderItems);
        orderRepository.save(order);

        return order.getId();
    }

    @Transactional(readOnly = true)
    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow();
    }

}
