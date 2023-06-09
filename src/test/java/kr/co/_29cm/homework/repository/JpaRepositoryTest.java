package kr.co._29cm.homework.repository;

import kr.co._29cm.homework.config.JpaConfig;
import kr.co._29cm.homework.domain.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * jpa 정상 동작 관련 slice test
 */
@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {

    private final ItemRepository itemRepository;

    public JpaRepositoryTest(@Autowired ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Test
    @DisplayName("select 테스트")
    void givenTestData_whenSelecting_thenWorksFine() {
        // Given
        // When
        List<Item> itemList = itemRepository.findAll();
        // Then
        assertThat(itemList).isNotNull().hasSize(19);
    }

    @Test
    @DisplayName("insert 테스트")
    void givenTestData_whenInsert_thenWorksFine() {
        // Given
        long previousCount = itemRepository.count();
        // When
        Item savedItem = itemRepository.save(Item.of("test", 1, 1));
        // Then
        assertThat(itemRepository.count()).isEqualTo(previousCount + 1);
    }

    @Test
    @DisplayName("update 테스트")
    void givenTestData_whenUpdate_thenWorksFine() {
        // Given
        Item item = itemRepository.findById(213341L).orElseThrow();
        String itemName = "테스트 상품명";
        item.setName(itemName);
        // When
        Item savedItem = itemRepository.save(item);
        // Then
        assertThat(savedItem).hasFieldOrPropertyWithValue("name", itemName);
    }

    @Test
    @DisplayName("delete 테스트")
    void givenTestData_whenDelete_thenWorksFine() {
        // Given
        Item item = itemRepository.findById(213341L).orElseThrow();
        long previousCount = itemRepository.count();
        // When
        itemRepository.delete(item);
        // Then
        assertThat(itemRepository.count()).isEqualTo(previousCount - 1);
    }
}