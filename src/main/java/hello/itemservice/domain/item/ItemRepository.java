package hello.itemservice.domain.item;


import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {
    // 멀티 쓰레드 환경에서 여러개가 동시에 store에 접근하게 되면 hashmap 사용을 사용하지 않는 것이 좋다. -> ConcurrentHashMap 사용하자!
    private static final Map<Long, Item> store = new HashMap<>(); // static 사용 주의
    private static long sequence = 0L; // static

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() { // 직접 store의 값을 반환해도 되지만 한번 감싸서 반환하게 될 경우 이 List에 어떠한 조작을 가해도 원래의 리스트에는 영향을 끼치지 않는다.
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }
}
