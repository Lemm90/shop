package ru.khorolskii.shop.carts.model;

import lombok.Data;
import ru.khorolskii.shop.api.ProductDto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Cart {
    private List<CartItem> items;
    private int totalPrice;


    public Cart() {
        this.items = new ArrayList<>();
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void add(ProductDto product) {
        for (CartItem item : items) {
            if (item.getProductId().equals(product.getId())) {
                addQuantity(item, 1);
                recalculate();
                return;
            }
        }
        items.add(new CartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice()));
        recalculate();
    }

    public void addQuantity(CartItem item, int delta) {
        item.setQuantity(item.getQuantity() + delta);
    }

    private void recalculate() {
        totalPrice = 0;
        for (CartItem item : items) {
            totalPrice += item.getQuantity() * item.getPricePerProduct();
        }
    }

    public void remove(Long productId) {
        if(items.removeIf(item -> item.getProductId().equals(productId))){
            recalculate();
        }
    }

    public void clear() {
        items.clear();
        recalculate();
    }

    public void changeQuantity(Long productId, Integer delta) {
        for (CartItem item : items) {
            if (item.getProductId().equals(productId)) {
                addQuantity(item, delta);
                quantityValidationCheck(item);
                recalculate();
                return;
            }
        }
    }

    private void quantityValidationCheck(CartItem item){
        if (item.getQuantity() < 1){
            item.setQuantity(1);
        }
    }
}
