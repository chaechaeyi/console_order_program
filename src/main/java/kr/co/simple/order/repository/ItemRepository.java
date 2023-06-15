package kr.co.simple.order.repository;

import kr.co.simple.order.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * 상품 정보 repository
 */
@RepositoryRestResource
public interface ItemRepository extends JpaRepository<Item, Long>{


}