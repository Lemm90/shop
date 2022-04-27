package ru.khorolskii.shop.core.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;
import ru.khorolskii.shop.api.CartDto;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@CrossOrigin("*")
public class CartServiceIntegration {
    private final RestTemplate restTemplate;

    public Optional <CartDto> getCurrentCart (){
        CartDto cartDto = restTemplate.getForObject("http://localhost:8190/shop-carts/api/v1/cart", CartDto.class);
        return Optional.ofNullable(cartDto) ;
    }

}
