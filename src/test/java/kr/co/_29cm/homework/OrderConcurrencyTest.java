package kr.co._29cm.homework;

import kr.co._29cm.homework.config.JpaConfig;
import kr.co._29cm.homework.domain.Item;
import kr.co._29cm.homework.domain.Order;
import kr.co._29cm.homework.dto.OrderItemDto;
import kr.co._29cm.homework.exception.SoldOutException;
import kr.co._29cm.homework.repository.ItemRepository;
import kr.co._29cm.homework.repository.OrderItemRepository;
import kr.co._29cm.homework.repository.OrderRepository;
import kr.co._29cm.homework.service.ItemService;
import kr.co._29cm.homework.service.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

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
        // todo 퇴사 이후 jpa를 공부해서 이번 과제를 기회로 사용하였는데 이 부분에 대한 고민이 더 필요할 것 같습니다.
        // isolation = Isolation.SERIALIZABLE 를 사용하여 처리 해 둔 상태 입니다.
        List<Order> orders = orderRepository.findAll();
        assertNotEquals(orders.size(), testThreadPoolSize);

    }
}