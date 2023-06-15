package kr.co.simple.order.repository;

import kr.co.simple.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * 주문 정보 repository
 */
@RepositoryRestResource
public interface OrderRepository extends JpaRepository<Order, Long>{

}