package kr.co._29cm.homework.repository;

import kr.co._29cm.homework.domainn.ItemOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ItemOrderRepository extends JpaRepository<ItemOrder, Long> {
}