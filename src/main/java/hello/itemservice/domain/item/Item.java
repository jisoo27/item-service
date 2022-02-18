package hello.itemservice.domain.item;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
public class Item {
    // 자료형을 int 로 할 경우 0이라도 집어넣어야 하기 때문에 null을 사용해 줄 수 없다.
    private Long id;
    private String itemName;
    private Integer price;
    private int quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, int quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
