package kr.co._29cm.homework.repository;

import kr.co._29cm.homework.domain.Item;
import kr.co._29cm.homework.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * 주문 정보 상세 repository
 */
@RepositoryRestResource
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}