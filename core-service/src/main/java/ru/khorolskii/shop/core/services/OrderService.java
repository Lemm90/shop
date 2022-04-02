package ru.khorolskii.shop.core.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.khorolskii.shop.api.CartDto;
import ru.khorolskii.shop.core.entities.Order;
import ru.khorolskii.shop.core.entities.OrderItems;
import ru.khorolskii.shop.core.entities.User;
import ru.khorolskii.shop.core.integrations.CartServiceIntegration;
import ru.khorolskii.shop.core.repositories.OrderRepository;

import java.util.stream.Collectors;

@Service
@Data
@RequiredArgsConstructor
public class OrderService {
    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final CartServiceIntegration cartServiceIntegration;

    @Transactional
    public void createOrder(User user){
        CartDto cart = cartServiceIntegration.getCurrentCart().orElseThrow(() -> new RuntimeException("Не удается найти корзину"));
        Order order = new Order();
        order.setUser(user);
        order.setTotalPrice(cart.getTotalPrice());
        order.setItems(cart.getItems().stream().map(
                cartItem -> new OrderItems(
                        productService.getProductById(cartItem.getProductId()).get(),
                        order,
                        cartItem.getQuantity(),
                        cartItem.getPricePerProduct(),
                        cartItem.getPrice()
                )
        ).collect(Collectors.toList()));
        orderRepository.save(order);
    }
}
