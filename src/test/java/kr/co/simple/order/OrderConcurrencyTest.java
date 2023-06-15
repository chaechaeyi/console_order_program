package kr.co.simple.order;

import kr.co.simple.order.domain.Order;
import kr.co.simple.order.dto.OrderItemDto;
import kr.co.simple.order.exception.SoldOutException;
import kr.co.simple.order.repository.OrderRepository;
import kr.co.simple.order.service.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ActiveProfiles("test")
@DisplayName("주문 동시성 테스트")
@SpringBootTest
class OrderConcurrencyTest {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;

    @Test
    @DisplayName("multi thread로 주문 동시성 테스트")
    void givenTestData_whenCreateOrder_thenCreateOrderCheck() throws InterruptedException {
        // given
        // test data : 628066, '무설탕 프로틴 초콜릿 틴볼스', 12900, 8
        int orderQuantity = 2; // 주문 수량
        int testThreadPoolSize = 10; // 테스트 thread

        List<OrderItemDto> orderItems = new ArrayList<>();
        orderItems.add(OrderItemDto.of(Long.valueOf(628066), orderQuantity));

        // when
        ExecutorService executorService = Executors.newFixedThreadPool(testThreadPoolSize);
        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i = 1; i <= testThreadPoolSize; i++) {
            executorService.execute(() -> {
                try {
                    orderService.createOrder(orderItems);
                }catch(SoldOutException e){
                    System.out.print(e);
                }catch (Exception e){
                    System.out.print(e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();

        //then
        // todo 
        // isolation = Isolation.SERIALIZABLE 를 사용하여 처리 해 둔 상태 입니다.
        // db level lock의 경우  dead lock이 발생하기 때문에 redis 를 사용해서 동시성 제어 하는 방향으로 프로젝트 업그레이드 예정입니다.
        List<Order> orders = orderRepository.findAll();
        assertNotEquals(orders.size(), testThreadPoolSize);

    }
}
