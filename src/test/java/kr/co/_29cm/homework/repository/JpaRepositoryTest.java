package kr.co._29cm.homework.repository;

import kr.co._29cm.homework.config.JpaConfig;
import kr.co._29cm.homework.domainn.Items;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * jpa 정상 동작 관련 slice test
 */
@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {

    private final ItemsRepository itemsRepository;
    private final ItemsCommentRepository itemsCommentRepository;

    public JpaRepositoryTest(@Autowired ItemsRepository itemsRepository, @Autowired ItemsCommentRepository itemsCommentRepository) {
        this.itemsRepository = itemsRepository;
        this.itemsCommentRepository = itemsCommentRepository;
    }

    @Test
    @DisplayName("select 테스트")
    void givenTestData_whenSelecting_thenWorksFine(){
        // Given
        // When
        List<Items> itemsList = itemsRepository.findAll();
        // Then
        assertThat(itemsList).isNotNull().hasSize(19);
    }

    @Test
    @DisplayName("insert 테스트")
    void givenTestData_whenInsert_thenWorksFine(){
        // Given
        long previousCount = itemsRepository.count();
        // When
        Items savedItems = itemsRepository.save( Items.of("test", 1, 1));
        // Then
        assertThat(itemsRepository.count()).isEqualTo(previousCount+1);
    }

    @Test
    @DisplayName("update 테스트")
    void givenTestData_whenUpdate_thenWorksFine(){
        // Given
        Items items = itemsRepository.findById(213341L).orElseThrow();
        String itemName = "테스트 상품명";
        items.setName(itemName);
        // When
        Items savedItems = itemsRepository.save(items);
        // Then
        assertThat(savedItems).hasFieldOrPropertyWithValue("name", itemName);
    }

    @Test
    @DisplayName("delete 테스트")
    void givenTestData_whenDelete_thenWorksFine(){
        // Given
        Items items = itemsRepository.findById(213341L).orElseThrow();
        long previousCount = itemsRepository.count();
        // When
        itemsRepository.delete(items);
        // Then
        assertThat(itemsRepository.count()).isEqualTo(previousCount-1);
    }
}