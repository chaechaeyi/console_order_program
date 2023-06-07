package kr.co._29cm.homework.repository;

import kr.co._29cm.homework.domainn.Items;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsCommentRepository extends JpaRepository<Items, Long> {
}
