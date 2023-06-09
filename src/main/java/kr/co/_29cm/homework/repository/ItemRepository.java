package kr.co._29cm.homework.repository;

import kr.co._29cm.homework.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * 상품 정보 repository
 */
@RepositoryRestResource
public interface ItemRepository extends JpaRepository<Item, Long>{


}