package kr.co.simple.order.service;

import kr.co.simple.order.domain.Item;
import kr.co.simple.order.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional(readOnly = true)
    public List<Item> getItemAll() {
        return itemRepository.findAll();
    }

    @Transactional(readOnly = true)
    public boolean isExistItem(Long itemId) {
        Optional<Item> item = itemRepository.findById(itemId);
        return item.isPresent();
    }


}
