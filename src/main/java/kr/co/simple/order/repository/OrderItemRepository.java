package kr.co.simple.order.repository;

import kr.co.simple.order.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * 주문 정보 상세 repository
 */
@RepositoryRestResource
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}