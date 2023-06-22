package kr.co.simple.order.service;

import kr.co.simple.order.constant.OrderStatus;
import kr.co.simple.order.domain.Item;
import kr.co.simple.order.domain.Order;
import kr.co.simple.order.domain.OrderItem;
import kr.co.simple.order.dto.OrderItemDto;
import kr.co.simple.order.repository.ItemRepository;
import kr.co.simple.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 주문 service
 */
@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
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
