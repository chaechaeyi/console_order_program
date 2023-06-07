package kr.co._29cm.homework.repository;

import kr.co._29cm.homework.domainn.Items;
import kr.co._29cm.homework.domainn.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersCommentRepository extends JpaRepository<Orders, Long> {
}
