package hello.itemservice.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {
    // 지금은 스프링 없이 테스트 하는 중

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach // 각 테스트가 끝날 때 마다 실행
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test // junit 5에서는 테스트에서 public 안써줘도 괜찮다
    void save() {
        // given
        Item item = new Item("itemA", 10000, 10);
        // when
        Item savedItem = itemRepository.save(item);
        // then
        Item findItem = itemRepository.findById(item.getId());
        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test // junit 5에서는 테스트에서 public 안써줘도 괜찮다
    void findAll() {
        // given
        Item item1 = new Item("item1", 10000, 10);
        Item item2 = new Item("item2", 10000, 10);

        itemRepository.save(item1);
        itemRepository.save(item2);
        // when(이 메서드의 목적을 위한 로직 구현 하는 곳)

        List<Item> result = itemRepository.findAll();

        // then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item1, item2);
    }

    @Test // junit 5에서는 테스트에서 public 안써줘도 괜찮다
    void updateItem() {
        // given
        Item item = new Item("itemA", 10000, 10);

        Item savedItem = itemRepository.save(item);
        Long itemId = savedItem.getId();
        // when
        Item updateParam = new Item("item2", 20000, 30);
        itemRepository.update(itemId, updateParam);

        // then
        Item findItem = itemRepository.findById(itemId);

        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());


    }


}