package kr.co._29cm.homework.repository;

import kr.co._29cm.homework.domainn.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}